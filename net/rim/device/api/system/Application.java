package net.rim.device.api.system;

import javax.microedition.io.file.FileSystemListener;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.crypto.vpn.VPNListener;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.io.NativeSocketListener;
import net.rim.device.internal.io.file.FileSystem;
import net.rim.device.internal.system.CodeStore;
import net.rim.device.internal.system.EngineeringDataListener;
import net.rim.device.internal.system.EventDispatchManager;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.system.MessageListener;
import net.rim.device.internal.system.RadioStatusListenerFilter;
import net.rim.vm.Message;
import net.rim.vm.MessageQueue;
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

   public final void setMessageListener(MessageListener listener) {
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

   public final void enableKeyUpEvents(boolean enable) {
      this._enableKeyUpMessages = enable;
      synchronized (this._eventLock) {
         if (this.isForeground()) {
            InternalServices.enableKeyUpMessages(this._enableKeyUpMessages);
         }
      }
   }

   public final boolean acceptsKeyUpEvents() {
      return this._enableKeyUpMessages;
   }

   public synchronized void addListener(int device, Object listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (device == 57 && !(listener instanceof NativeSocketListener)) {
         throw new IllegalArgumentException();
      }

      this._listeners[device] = ListenerUtilities.addListener(this._listeners[device], listener);
   }

   public synchronized void removeListener(int device, Object listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._listeners[device] = ListenerUtilities.removeListener(this._listeners[device], listener);
   }

   public synchronized Object[] getListeners(int device) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      Object[] array = this._listeners[device];
      if (array == null) {
         return null;
      }

      Object[] copy = new Object[array.length];
      System.arraycopy(array, 0, copy, 0, array.length);
      return copy;
   }

   public void addKeyListener(KeyListener listener) {
      if (!ControlledAccess.verifyRRISignatures(true) && !CodeStore.isPartOfCurrentApp(TraceBack.getCallingModule(0))) {
         throw new ControlledAccessException("Unauthorized attempt to monitor key presses");
      }

      this.addListener(2, listener);
   }

   public void removeKeyListener(KeyListener listener) {
      this.removeListener(2, listener);
   }

   public void addLockedKeyListener(KeyListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.addListener(49, listener);
   }

   public void removeLockedKeyListener(KeyListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.removeListener(49, listener);
   }

   public void addStylusListener(StylusListener listener) {
      this.addListener(26, listener);
   }

   public void removeStylusListener(StylusListener listener) {
      this.removeListener(26, listener);
   }

   public void addTrackwheelListener(TrackwheelListener listener) {
      this.addListener(2, listener);
   }

   public void removeTrackwheelListener(TrackwheelListener listener) {
      this.removeListener(2, listener);
   }

   public void addRealtimeClockListener(RealtimeClockListener listener) {
      this.addListener(3, listener);
   }

   public void removeRealtimeClockListener(RealtimeClockListener listener) {
      this.removeListener(3, listener);
   }

   public void addSystemListener(SystemListener listener) {
      this.addListener(1, listener);
   }

   public void removeSystemListener(SystemListener listener) {
      this.removeListener(1, listener);
   }

   public void addHolsterListener(HolsterListener listener) {
      this.addListener(7, listener);
   }

   public void removeHolsterListener(HolsterListener listener) {
      this.removeListener(7, listener);
   }

   public void addRadioListener(RadioListener listener) {
      this.addRadioListener(-5, listener);
   }

   public void addRadioListener(int WAFFilter, RadioListener listener) {
      if (listener == null) {
         throw new NullPointerException();
      }

      if (listener instanceof RadioStatusListener) {
         if ((WAFFilter & 4) != 0) {
            ApplicationControl.assertWiFiPermitted(true, CommonResource.getBundle(), 10165);
         }

         RadioStatusListener rsl = (RadioStatusListener)listener;
         this.addListener(33, new RadioStatusListenerFilter(WAFFilter, rsl));
      }

      if (listener instanceof RadioPacketListener) {
         this.addListener(34, listener);
      }

      if (listener instanceof PhoneCallListener) {
         this.addListener(52, listener);
      }

      if (listener instanceof PhoneTimerListener) {
         this.addListener(53, listener);
      }

      if (listener instanceof PhoneControlListener) {
         this.addListener(54, listener);
      }

      if (listener instanceof EngineeringDataListener) {
         this.addListener(36, listener);
      }

      if (listener instanceof ModemListener) {
         this.addListener(48, listener);
      }

      if (listener instanceof DirectConnectListener) {
         this.addListener(37, listener);
      }

      if (listener instanceof WLANListenerInternal) {
         this.addListener(18, listener);
      }

      if (listener instanceof VPNListener) {
         this.addListener(51, listener);
      }

      if (listener instanceof GANStatusListener) {
         this.addListener(58, listener);
      }
   }

   public void removeRadioListener(RadioListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void addIOPortListener(IOPortListener listener) {
      if (listener == null) {
         throw new NullPointerException();
      }

      ApplicationControl.assertLocalConnectionAllowed(true);
      if (listener instanceof USBPortListener) {
         this.addListener(14, listener);
      }
   }

   public void removeIOPortListener(IOPortListener listener) {
      this.removeListener(14, listener);
   }

   public void addFileSystemListener(FileSystemListener listener) {
      FileSystem.addFileSystemListener(listener, this, false);
   }

   public void removeFileSystemListener(FileSystemListener listener) {
      FileSystem.removeFileSystemListener(listener);
   }

   public void addFileSystemJournalListener(FileSystemJournalListener listener) {
      FileSystem.addJournalListener(listener, this, false);
   }

   public void removeFileSystemJournalListener(FileSystemJournalListener listener) {
      FileSystem.removeJournalListener(listener);
   }

   public void addGlobalEventListener(GlobalEventListener listener) {
      ApplicationControl.assertIPCAllowed(true);
      this.addListener(32, listener);
   }

   public void addGlobalEventListenerInternal(GlobalEventListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.addListener(32, listener);
   }

   public void removeGlobalEventListener(GlobalEventListener listener) {
      this.removeListener(32, listener);
   }

   public void addPeripheralListener(PeripheralListener listener) {
   }

   public void removePeripheralListener(PeripheralListener listener) {
   }

   public void addAlertListener(AlertListener listener) {
      this.addListener(10, listener);
   }

   public void removeAlertListener(AlertListener listener) {
      this.removeListener(10, listener);
   }

   public void enterEventDispatcher() {
      try {
         synchronized (this._eventLock) {
            if (this._currentEventThread != null) {
               throw new IllegalStateException("Only one event dispatcher per process is allowed.");
            }

            Process.killProcessIfThisThreadDies(true);
            this._currentEventThread = Thread.currentThread();
            if (this.isForeground()) {
               this.activate();
               if (this._messageListener != null) {
                  this._messageListener.processMessage(this._eventLock, this._refreshDisplay, false);
               }
            }
         }

         Message message = new Message();

         while (true) {
            this.processNextMessage(message);
         }
      } finally {
         this._currentEventThread = null;
      }
   }

   public final void setAcceptEvents(boolean on) {
      this._process.setAcceptsEvents(on);
   }

   public static void setAcceptEventsForProcess(boolean on) {
      ApplicationProcess process = (ApplicationProcess)Process.currentProcess();
      process.setAcceptsEvents(on);
   }

   public boolean isAlive() {
      return this._process.isAlive();
   }

   protected void dispatchInvokeLater(Runnable runnable, Object notifier, int data0) {
      if (runnable != null) {
         runnable.run();
         if (notifier != null) {
            synchronized (notifier) {
               notifier.notifyAll();
               return;
            }
         }
      }
   }

   final void processNextMessage(Message message) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected boolean allowKeyEventFromPreviousApp(int event, int keycode) {
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
      if (_application == null) {
         _application = ((ApplicationProcess)Process.currentProcess()).getApplication();
         if (_application == null) {
            throw new IllegalStateException("no application instance");
         }
      }

      if (!CodeStore.isPartOfCurrentApp(TraceBack.getCallingModule(3)) && !ApplicationControl.isIPCAllowed(true)) {
         throw new ControlledAccessException("Unauthorized attempt to attach to this application");
      } else {
         return _application;
      }
   }

   public final void invokeLater(Runnable runnable) {
      this.invokeLaterSpecial(runnable, 0);
   }

   public final void invokeLaterSpecial(Runnable runnable, int data0) {
      if (runnable == null) {
         throw new NullPointerException();
      }

      synchronized (this._invokeLaterMessage) {
         this._invokeLaterMessage.setObject0(runnable);
         this._invokeLaterMessage.setObject1(null);
         this._invokeLaterMessage.setData0(data0);
         this._process.postMessage(this._invokeLaterMessage);
         this._invokeLaterMessage.setObject0(null);
      }
   }

   public final int invokeLater(Runnable runnable, long time, boolean repeat) {
      return this.invokeLater(runnable, time, repeat, 16);
   }

   public final int invokeLaterInternal(Runnable runnable, long time, boolean repeat) {
      return this.invokeLater(runnable, time, repeat, 20);
   }

   private int invokeLater(Runnable runnable, long time, boolean repeat, int poolSize) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final void cancelInvokeLater(int id) {
      synchronized (this._timedRunnables) {
         int slot = id & 31;
         if (slot >= 0 && slot < this._invokeLaterIds.length) {
            if (this._invokeLaterIds[slot] != id && this._invokeLaterIds[slot] != 0) {
               StringBuffer logData = new StringBuffer();
               logData.append("Trying to cancel invoke later for id: ");
               logData.append(Integer.toHexString(id));
               logData.append(" in slot: ");
               logData.append(slot);
               logData.append(" which has already run is now being used by another runnable.");
               Object tb = TraceBack.getTraceBack();
               StringBuffer stackTrace = new StringBuffer();
               int i = 0;

               while (true) {
                  String msg = TraceBack.getMessage(tb, i);
                  if (msg == null) {
                     EventLogger.logEvent(-7509200465648525729L, stackTrace.toString().trim().getBytes(), 0);
                     EventLogger.logEvent(-7509200465648525729L, logData.toString().getBytes(), 0);
                     return;
                  }

                  stackTrace.append(msg);
                  stackTrace.append("\n");
                  i++;
               }
            } else {
               this._invokeLaterIds[slot] = 0;
               if (slot >= 0 && slot < this._timedRunnables.length) {
                  if (this._timedRunnables[slot] != null) {
                     InternalServices.killTimer(this._process.getOSTimerId(slot));
                     this._cachedTimedRunnable = this._timedRunnables[slot];
                     this._cachedTimedRunnable.clear();
                     this._timedRunnables[slot] = null;
                     this._lastTimedRunnableSlot = slot;
                  }
               } else {
                  throw new IllegalArgumentException();
               }
            }
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   private void resetTimedRunnables() {
      synchronized (this._timedRunnables) {
         for (int i = 0; i < 20; i++) {
            if (this._timedRunnables[i] != null
               && !InternalServices.setTimer(this._process.getOSTimerId(i), this._timedRunnables[i].getTime(), this._timedRunnables[i].getRepeat())) {
               throw new RuntimeException("resetTimedRunnables() failed");
            }
         }
      }
   }

   public final void invokeAndWait(Runnable runnable) {
      if (this.isEventThread() || this._currentEventThread == null) {
         runnable.run();
      } else {
         if (runnable == null) {
            throw new NullPointerException();
         }

         synchronized (this._invokeAndWaitMonitor) {
            synchronized (this._invokeLaterMessage) {
               this._invokeLaterMessage.setObject0(runnable);
               this._invokeLaterMessage.setObject1(this._invokeLaterMessage);
               this._invokeLaterMessage.setData0(0);
               this._process.postMessage(this._invokeLaterMessage);
               this._invokeLaterMessage.setObject0(null);
               this._invokeLaterMessage.setObject1(null);

               try {
                  this._invokeLaterMessage.wait();
               } catch (InterruptedException var7) {
               }
            }
         }
      }
   }

   public final int getMessageQueueSize() {
      MessageQueue queue = this._process.getMessageQueue();
      synchronized (queue) {
         return queue.getSize();
      }
   }

   public final int getProcessId() {
      return this._processId;
   }

   public final void startModalEventThread(ModalEventThread modalEventThread) {
      if (!this.isEventThread()) {
         throw new RuntimeException("startModalEventThread called by a non-event thread");
      }

      this._currentEventThread = modalEventThread;
      modalEventThread.start();

      do {
         try {
            this.getAppEventLock().wait();
         } catch (InterruptedException var3) {
         }
      } while (!modalEventThread.isExiting());

      this._currentEventThread = Thread.currentThread();
   }
}
