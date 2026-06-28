package javax.microedition.content;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ApplicationManagerException;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.vm.Message;
import net.rim.vm.Process;

class ContentHandlerServerImpl extends ContentHandlerImpl implements ContentHandlerServer {
   private String[] _accessAllowed;
   private RequestListener _listener;
   private InvocationQueue _queue = new InvocationQueue();
   private ApplicationDescriptor _application;
   private int _numStaleInvocations;

   void init(String[] types, String[] suffixes, String[] actions, ActionNameMap[] actionnames, String ID, String[] accessAllowed) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   void setApplicationDescriptor(ApplicationDescriptor application) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getQueueSize() {
      return this._queue.size();
   }

   void cancelOldInvocations() {
      for (int i = 0; i < this._numStaleInvocations; i++) {
         Invocation next = this._queue.nextInvocation();
         if (next != null) {
            RegistryImpl.invocationFinished(next, 7);
         }
      }
   }

   String[] getAccessAllowed() {
      return this._accessAllowed;
   }

   void start() {
      synchronized (this._queue) {
         int pid = this.wakeAppAndNotifyListener(true);
         if (pid == -1) {
            int numInvocations = this._queue.size();

            for (int i = 0; i < numInvocations; i++) {
               RegistryImpl.invocationFinished(this._queue.nextInvocation(), 7);
            }
         } else {
            boolean firstRun = InvocationCleanupManager.getInstance().requestHandlerStarted(pid, this);
            if (firstRun) {
               this._numStaleInvocations = this._queue.size();
            }

            this._queue.notifyAll();
         }
      }
   }

   void start(Invocation invocation) {
      synchronized (this._queue) {
         this._queue.addInvocation(invocation);
         this.start();
      }
   }

   @Override
   public Invocation getRequest(boolean wait) {
      this.assertPermission();
      synchronized (this._queue) {
         Invocation next = this._queue.nextInvocation();
         if (next == null && wait) {
            try {
               this._queue.wait();
            } catch (InterruptedException var6) {
            }

            next = this._queue.nextInvocation();
         }

         if (next != null) {
            InvocationCleanupManager icm = InvocationCleanupManager.getInstance();
            int pid = Process.currentProcess().getProcessId();
            icm.addActiveInvocation(pid, next);
            icm.requestRetreived(pid);
         }

         return next;
      }
   }

   @Override
   public void cancelGetRequest() {
      this.assertPermission();
      synchronized (this._queue) {
         this._queue.notifyAll();
      }
   }

   @Override
   public void setListener(RequestListener listener) {
      this.assertPermission();
      this._listener = listener;
      synchronized (this._queue) {
         if (this._queue.size() > 0) {
            this.wakeAppAndNotifyListener(false);
         }
      }
   }

   @Override
   public boolean isAccessAllowed(String ID) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean finish(Invocation invocation, int status) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getAccessAllowed(int index) {
      if (index >= 0 && index < this.accessAllowedCount()) {
         return this._accessAllowed[index];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int accessAllowedCount() {
      return this._accessAllowed == null ? 0 : this._accessAllowed.length;
   }

   private int wakeAppAndNotifyListener(boolean grabForeground) {
      int pid = -1;
      ApplicationManager am = ApplicationManager.getApplicationManager();

      try {
         pid = am.runApplication(this._application, grabForeground);
      } catch (ApplicationManagerException var7) {
      } finally {
         if (pid == -1) {
            return pid;
         }
      }

      if (this._listener != null) {
         Message invokeLaterMessage = (Message)(new Object(0, 2, new ContentHandlerServerImpl$1(this), null));
         ((ApplicationManagerInternal)am).postMessage(pid, invokeLaterMessage);
      }

      return pid;
   }

   private void checkActionNameMapArrayValues(ActionNameMap[] actionnames) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void checkID(String ID) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }
}
