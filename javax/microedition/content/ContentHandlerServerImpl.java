package javax.microedition.content;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.internal.applicationcontrol.ApplicationControl;

class ContentHandlerServerImpl extends ContentHandlerImpl implements ContentHandlerServer {
   private String[] _accessAllowed;
   private RequestListener _listener;
   private InvocationQueue _queue = new InvocationQueue();
   private ApplicationDescriptor _application;
   private int _numStaleInvocations;

   void init(String[] var1, String[] var2, String[] var3, ActionNameMap[] var4, String var5, String[] var6) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   void setApplicationDescriptor(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getQueueSize() {
      return this._queue.size();
   }

   void cancelOldInvocations() {
      for (int var1 = 0; var1 < this._numStaleInvocations; var1++) {
         Invocation var2 = this._queue.nextInvocation();
         if (var2 != null) {
            RegistryImpl.invocationFinished(var2, 7);
         }
      }
   }

   String[] getAccessAllowed() {
      return this._accessAllowed;
   }

   void start() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void start(Invocation var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Invocation getRequest(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void cancelGetRequest() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setListener(RequestListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean isAccessAllowed(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean finish(Invocation var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getAccessAllowed(int var1) {
      if (var1 >= 0 && var1 < this.accessAllowedCount()) {
         return this._accessAllowed[var1];
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public int accessAllowedCount() {
      return this._accessAllowed == null ? 0 : this._accessAllowed.length;
   }

   private int wakeAppAndNotifyListener(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void checkActionNameMapArrayValues(ActionNameMap[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void checkID(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }
}
