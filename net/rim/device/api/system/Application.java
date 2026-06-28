package net.rim.device.api.system;

import javax.microedition.io.file.FileSystemListener;
import net.rim.device.api.io.file.FileSystemJournalListener;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.crypto.vpn.VPNListener;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.io.NativeSocketListener;
import net.rim.device.internal.io.file.FileSystem;
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
      throw new RuntimeException("cod2jar: ldc");
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
      throw new RuntimeException("cod2jar: ldc");
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
      throw new RuntimeException("cod2jar: ldc");
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
      throw new RuntimeException("cod2jar: ldc");
   }

   private void resetTimedRunnables() {
      throw new RuntimeException("cod2jar: ldc");
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
      throw new RuntimeException("cod2jar: ldc");
   }
}
