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

   ApplicationProcess(ApplicationManagerImpl var1, ApplicationDescriptor var2, boolean var3) {
      this._appManager = var1;
      this._descriptor = var2;
      this._grabForegroundOnStartup = var3;
      this._isRIMProcess = ControlledAccess.verifyRRISignature(var2.getModuleHandle());
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

   public final synchronized void addCleanupRunnable(Runnable var1) {
      if (this._cleanupRunnables == null) {
         this._cleanupRunnables = (Vector)(new Object());
      }

      this._cleanupRunnables.addElement(var1);
   }

   public final synchronized void removeCleanupRunnable(Runnable var1) {
      if (this._cleanupRunnables != null) {
         this._cleanupRunnables.removeElement(var1);
         if (this._cleanupRunnables.size() == 0) {
            this._cleanupRunnables = null;
         }
      }
   }

   final void appStarted(Application var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final Application getApplication() {
      return this._app;
   }

   final void setAcceptsEvents(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void postMessage(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void logMessageQueueOverflow(MessageQueue var0, String var1) {
      System.out.println(var1);
      EventLogger.logEvent(-7509200465648525729L, var1.getBytes());
      var1 = var0.toString();
      System.out.println(var1);
      EventLogger.logEvent(-7509200465648525729L, var1.getBytes());
      DebugSupport.logStackTraces();
   }

   private final boolean checkForKeyOverflow() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void getMessage(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean acceptsForeground() {
      return this.isAlive() && this._app != null && this._app.acceptsForeground();
   }

   final int getOSTimerId(int var1) {
      return this.getProcessId() & 134217727 | var1 << 27;
   }

   static final int getProcessTimerId(int var0) {
      return var0 >> 27 & 31;
   }

   static final int getProcessIdFromOSTimerId(int var0) {
      return var0 & 134217727;
   }

   final void cleanup() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
