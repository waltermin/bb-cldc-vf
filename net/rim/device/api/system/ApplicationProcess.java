package net.rim.device.api.system;

import java.util.Vector;
import net.rim.vm.DebugSupport;
import net.rim.vm.Message;
import net.rim.vm.MessageQueue;
import net.rim.vm.Process;

public final class ApplicationProcess extends Process implements Runnable {
   private Application _app;
   private ApplicationDescriptor _descriptor;
   private ApplicationManagerImpl _appManager;
   private boolean _grabForegroundOnStartup;
   private boolean _acceptsEvents = true;
   private boolean _isHandlingEvents;
   private boolean _droppedDiagnosed;
   private boolean _isRIMProcess;
   private int _lastMessageType;
   private int _lastMagnitude;
   private int _lastHandle;
   private Vector _cleanupRunnables;
   private boolean _droppingKeys;
   private static final int LAST_MESSAGE_GENERIC;
   private static final int LAST_MESSAGE_THUMB_ROLL;
   private static final int LAST_MESSAGE_STYLUS_DRAG;
   private static final int LAST_MESSAGE_TRACKBALL_ROLL;
   private static final int LAST_MESSAGE_STREAMING_SESSION_WATERMARK;
   private static final int LAST_MESSAGE_MEDIA_STATUS;

   ApplicationProcess(ApplicationManagerImpl appManager, ApplicationDescriptor descriptor, boolean grabForegroundOnStartup) {
      this._appManager = appManager;
      this._descriptor = descriptor;
      this._grabForegroundOnStartup = grabForegroundOnStartup;
      this._isRIMProcess = ControlledAccess.verifyRRISignature(descriptor.getModuleHandle());
   }

   final ApplicationManagerImpl getApplicationManager() {
      return this._appManager;
   }

   final ApplicationDescriptor getApplicationDescriptor() {
      return this._descriptor;
   }

   final boolean grabForegroundOnStartup() {
      return this._grabForegroundOnStartup;
   }

   final boolean isRIMProcess() {
      return this._isRIMProcess;
   }

   public final synchronized void addCleanupRunnable(Runnable runnable) {
      if (this._cleanupRunnables == null) {
         this._cleanupRunnables = (Vector)(new Object());
      }

      this._cleanupRunnables.addElement(runnable);
   }

   public final synchronized void removeCleanupRunnable(Runnable runnable) {
      if (this._cleanupRunnables != null) {
         this._cleanupRunnables.removeElement(runnable);
         if (this._cleanupRunnables.size() == 0) {
            this._cleanupRunnables = null;
         }
      }
   }

   final void appStarted(Application app) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final Application getApplication() {
      return this._app;
   }

   final void setAcceptsEvents(boolean on) {
      if (this.isAlive()) {
         synchronized (super._messageQueue) {
            if (!on) {
               super._messageQueue.flush();
            }

            this._acceptsEvents = on;
         }
      }
   }

   final void postMessage(Message message) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void logMessageQueueOverflow(MessageQueue msgQueue, String msg) {
      System.out.println(msg);
      EventLogger.logEvent(-7509200465648525729L, msg.getBytes());
      msg = msgQueue.toString();
      System.out.println(msg);
      EventLogger.logEvent(-7509200465648525729L, msg.getBytes());
      DebugSupport.logStackTraces();
   }

   private final boolean checkForKeyOverflow() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void getMessage(Message message) {
      synchronized (super._messageQueue) {
         if (!this._isHandlingEvents) {
            this._isHandlingEvents = true;
            super._messageQueue.setProcessState(1);
         }

         super._messageQueue.dequeue(message, true);
      }
   }

   public final boolean acceptsForeground() {
      return this.isAlive() && this._app != null && this._app.acceptsForeground();
   }

   final int getOSTimerId(int processTimerId) {
      return this.getProcessId() & 134217727 | processTimerId << 27;
   }

   static final int getProcessTimerId(int osTimerId) {
      return osTimerId >> 27 & 31;
   }

   static final int getProcessIdFromOSTimerId(int osTimerId) {
      return osTimerId & 134217727;
   }

   final void cleanup() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
