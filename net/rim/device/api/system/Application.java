package net.rim.device.api.system;

import javax.microedition.io.file.FileSystemListener;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.io.file.FileSystem;
import net.rim.device.internal.system.EventDispatchManager;
import net.rim.device.internal.system.MessageListener;
import net.rim.vm.Message;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

public class Application {
   Object[][][] _listeners;
   private ApplicationProcess _process;
   private int _processId;
   private ApplicationManagerImpl _appManager;
   private boolean _foregroundRequestInProgress;
   private boolean _ignoreKeyEvents;
   protected Thread _currentEventThread;
   private TimedRunnable[] _timedRunnables;
   private int _lastTimedRunnableSlot;
   private TimedRunnable _cachedTimedRunnable;
   private Object _eventLock;
   private Message _invokeLaterMessage;
   private Message _refreshDisplay;
   private boolean _enableKeyUpMessages;
   private MessageListener _messageListener;
   private Object _invokeAndWaitMonitor;
   private EventDispatchManager _eventDispatchManager;
   private int[] _invokeLaterIds;
   private int _uniqueInvokeLaterCounter;
   private static Application _application;
   private static final int NUM_TIMED_RUNNABLES;
   private static final int NUM_EXTERNAL_TIMED_RUNNABLES;
   private static final boolean DEBUG;

   protected Application() {
   }

   public final void setMessageListener(MessageListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected boolean acceptsForeground() {
      return false;
   }

   protected boolean acceptLaunchRequest() {
      return this.acceptsForeground();
   }

   public void activate() {
   }

   public void deactivate() {
   }

   public final boolean isForeground() {
      return this._appManager.getForegroundProcessId() == this._processId;
   }

   public final boolean isForegroundRequestInProgress() {
      return this._foregroundRequestInProgress;
   }

   public final void requestBackground() {
      if (this.isForeground()) {
         this._appManager.requestForeground(this._process, true);
      }
   }

   public final void requestForeground() {
      this._appManager.requestForeground(this._process, false);
      this._foregroundRequestInProgress = true;
   }

   public final void enableKeyUpEvents(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean acceptsKeyUpEvents() {
      return this._enableKeyUpMessages;
   }

   public synchronized void addListener(int var1, Object var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (var1 == 57 && !(var2 instanceof Object)) {
         throw new Object();
      }

      this._listeners[var1] = ListenerUtilities.addListener(this._listeners[var1], var2);
   }

   public synchronized void removeListener(int var1, Object var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._listeners[var1] = ListenerUtilities.removeListener(this._listeners[var1], var2);
   }

   public synchronized Object[] getListeners(int var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      Object[][] var2 = this._listeners[var1];
      if (var2 == null) {
         return null;
      }

      Object[] var3 = new Object[var2.length];
      System.arraycopy(var2, 0, var3, 0, var2.length);
      return var3;
   }

   public void addKeyListener(KeyListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void removeKeyListener(KeyListener var1) {
      this.removeListener(2, var1);
   }

   public void addLockedKeyListener(KeyListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.addListener(49, var1);
   }

   public void removeLockedKeyListener(KeyListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.removeListener(49, var1);
   }

   public void addStylusListener(StylusListener var1) {
      this.addListener(26, var1);
   }

   public void removeStylusListener(StylusListener var1) {
      this.removeListener(26, var1);
   }

   public void addTrackwheelListener(TrackwheelListener var1) {
      this.addListener(2, var1);
   }

   public void removeTrackwheelListener(TrackwheelListener var1) {
      this.removeListener(2, var1);
   }

   public void addRealtimeClockListener(RealtimeClockListener var1) {
      this.addListener(3, var1);
   }

   public void removeRealtimeClockListener(RealtimeClockListener var1) {
      this.removeListener(3, var1);
   }

   public void addSystemListener(SystemListener var1) {
      this.addListener(1, var1);
   }

   public void removeSystemListener(SystemListener var1) {
      this.removeListener(1, var1);
   }

   public void addHolsterListener(HolsterListener var1) {
      this.addListener(7, var1);
   }

   public void removeHolsterListener(HolsterListener var1) {
      this.removeListener(7, var1);
   }

   public void addRadioListener(RadioListener var1) {
      this.addRadioListener(-5, var1);
   }

   public void addRadioListener(int var1, RadioListener var2) {
      if (var2 == null) {
         throw new Object();
      }

      if (var2 instanceof Object) {
         if ((var1 & 4) != 0) {
            ApplicationControl.assertWiFiPermitted(true, CommonResource.getBundle(), 10165);
         }

         Object var3 = var2;
         this.addListener(33, new Object(var1, (RadioStatusListener)var3));
      }

      if (var2 instanceof Object) {
         this.addListener(34, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(52, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(53, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(54, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(36, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(48, var2);
      }

      if (var2 instanceof DirectConnectListener) {
         this.addListener(37, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(18, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(51, var2);
      }

      if (var2 instanceof Object) {
         this.addListener(58, var2);
      }
   }

   public void removeRadioListener(RadioListener var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void addIOPortListener(IOPortListener var1) {
      if (var1 == null) {
         throw new Object();
      }

      ApplicationControl.assertLocalConnectionAllowed(true);
      if (var1 instanceof Object) {
         this.addListener(14, var1);
      }
   }

   public void removeIOPortListener(IOPortListener var1) {
      this.removeListener(14, var1);
   }

   public void addFileSystemListener(FileSystemListener var1) {
      FileSystem.addFileSystemListener(var1, this, false);
   }

   public void removeFileSystemListener(FileSystemListener var1) {
      FileSystem.removeFileSystemListener(var1);
   }

   public void addFileSystemJournalListener(FileSystemJournalListener var1) {
      FileSystem.addJournalListener(var1, this, false);
   }

   public void removeFileSystemJournalListener(FileSystemJournalListener var1) {
      FileSystem.removeJournalListener(var1);
   }

   public void addGlobalEventListener(GlobalEventListener var1) {
      ApplicationControl.assertIPCAllowed(true);
      this.addListener(32, var1);
   }

   public void addGlobalEventListenerInternal(GlobalEventListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.addListener(32, var1);
   }

   public void removeGlobalEventListener(GlobalEventListener var1) {
      this.removeListener(32, var1);
   }

   public void addPeripheralListener(PeripheralListener var1) {
   }

   public void removePeripheralListener(PeripheralListener var1) {
   }

   public void addAlertListener(AlertListener var1) {
      this.addListener(10, var1);
   }

   public void removeAlertListener(AlertListener var1) {
      this.removeListener(10, var1);
   }

   public void enterEventDispatcher() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setAcceptEvents(boolean var1) {
      this._process.setAcceptsEvents(var1);
   }

   public static void setAcceptEventsForProcess(boolean var0) {
      ApplicationProcess var1 = (ApplicationProcess)Process.currentProcess();
      var1.setAcceptsEvents(var0);
   }

   public boolean isAlive() {
      return this._process.isAlive();
   }

   protected void dispatchInvokeLater(Runnable var1, Object var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void processNextMessage(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean allowKeyEventFromPreviousApp(int var1, int var2) {
      return false;
   }

   public static final boolean isEventDispatchThread() {
      return _application != null ? _application.isEventThread() : getApplication().isEventThread();
   }

   public boolean isEventThread() {
      return this._currentEventThread == Thread.currentThread();
   }

   public boolean hasEventThread() {
      return this._currentEventThread != null;
   }

   public final boolean isHandlingEvents() {
      return this._currentEventThread != null;
   }

   public static final Object getEventLock() {
      return getApplication().getAppEventLock();
   }

   public final Object getAppEventLock() {
      return this._eventLock;
   }

   public static final Application getApplication() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void invokeLater(Runnable var1) {
      this.invokeLaterSpecial(var1, 0);
   }

   public final void invokeLaterSpecial(Runnable var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int invokeLater(Runnable var1, long var2, boolean var4) {
      return this.invokeLater(var1, var2, var4, 16);
   }

   public final int invokeLaterInternal(Runnable var1, long var2, boolean var4) {
      return this.invokeLater(var1, var2, var4, 20);
   }

   private int invokeLater(Runnable var1, long var2, boolean var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void cancelInvokeLater(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void resetTimedRunnables() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void invokeAndWait(Runnable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getMessageQueueSize() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getProcessId() {
      return this._processId;
   }

   public final void startModalEventThread(ModalEventThread var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
