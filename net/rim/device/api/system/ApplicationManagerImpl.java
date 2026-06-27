package net.rim.device.api.system;

import java.util.Vector;
import net.rim.device.api.lowmemory.LowMemoryManager;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.device.internal.system.EventDispatchManager;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.system.SecurityManager;
import net.rim.vm.Message;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

final class ApplicationManagerImpl extends ApplicationManager implements ApplicationManagerInternal {
   private ApplicationManagerImpl$ApplicationProcessContainer _processes;
   private ApplicationProcess _foregroundProcess;
   private ApplicationProcess _consoleProcess;
   private ApplicationProcess _inHolsterInputProcess;
   private ApplicationProcess _redirectInputProcess;
   private ApplicationProcess _inputProcess;
   private ApplicationProcess _securityProcess;
   private ApplicationProcess _nativeSocketProcess;
   private int _securityLockSupercedingProcessId;
   private EventLogger _eventLogger;
   private Process _thisProcess;
   private Message _switchForegroundMessage;
   private Message _switchBackgroundMessage;
   private Message _refreshDisplayMessage;
   private Message _globalEventMessage;
   private SecurityManager _securityManager;
   private Vector _scheduledApps;
   private long _alarmTime = Long.MAX_VALUE;
   private ApplicationDescriptor _engScreenDescriptor;
   private Object[] _foregroundChangeListeners;
   private boolean _isFastReset;
   private boolean _fastResetHavePowerUp;
   private ApplicationManagerImpl$StartupGetMessageThread _startupThread;
   private ApplicationRegistry _applicationRegistry;
   private EventDispatchManager _eventDispatchManager;
   private int _currentPowerOnBehavior = 1;
   private boolean _usePowerOnBehaviourForScheduling;
   private boolean _dateTimeWasValidOnStartup;
   String[] _schedulingLog;
   int _schedulingLogIndex = 0;
   private long _lastQuincy;
   private static final boolean OUTPUT_APPLICATION_MESSAGES;
   private static final int TIER_TIMEOUT;
   private static final int MAX_TIMER_ID;

   final boolean postInternalGlobalEvent(int var1, long var2, int var4, int var5, Object var6, Object var7) {
      ApplicationProcess var8 = this.findProcess(var1);
      if (var8 == null) {
         return false;
      }

      this.postGlobalEventImpl(var8, var2, var4, var5, var6, var7, true);
      return true;
   }

   final boolean postInternalGlobalEvent(long var1, int var3, int var4, Object var5, Object var6) {
      return this.postGlobalEventImpl(var1, var3, var4, var5, var6, true);
   }

   public final boolean postMessageToForegroundProcess(Message var1) {
      if (this._inputProcess != null) {
         this._inputProcess.postMessage(var1);
         return true;
      } else {
         return false;
      }
   }

   final EventLogger getEventLogger() {
      return this._eventLogger;
   }

   final boolean setForegroundProcess(ApplicationProcess var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void requestForeground(ApplicationProcess var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int runApplication(ApplicationDescriptor var1, boolean var2, Thread var3) {
      int var4 = TraceBack.getCallingModule(0);
      return this.runApplication(var1, var2, var3, var4);
   }

   @Override
   public final boolean setSecurityManager(SecurityManager var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final SecurityManager getSecurityManager() {
      return this._securityManager;
   }

   @Override
   public final boolean setEngScreenDescriptor(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final ApplicationDescriptor getEngScreenDescriptor() {
      return this._engScreenDescriptor;
   }

   @Override
   public final void setNativeSocketProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int runApplication(ApplicationDescriptor var1, Thread var2) {
      int var3 = TraceBack.getCallingModule(0);
      return this.runApplication(var1, true, var2, var3);
   }

   @Override
   public final boolean setSecurityLockSupercedingProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean setConsoleProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isSecurityLockSupercedingProcessForeground() {
      return this.getForegroundProcessId() == this._securityLockSupercedingProcessId;
   }

   @Override
   public final void enableSchedulingLog(boolean var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final ApplicationProcess[] getProcesses() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void redirectInput(Process var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void repaintForeground() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean postMessage(int var1, Message var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void postMessage(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final String[] getSchedulingLog() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void addForegroundChangeListener(Runnable var1) {
      this._foregroundChangeListeners = ListenerUtilities.addListener(this._foregroundChangeListeners, var1);
   }

   @Override
   public final void removeForegroundChangeListener(Runnable var1) {
      this._foregroundChangeListeners = ListenerUtilities.removeListener(this._foregroundChangeListeners, var1);
   }

   @Override
   public final Application getForegroundApplication() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void resetAppDescriptorOverrides() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void updateAppDescriptor(String var1, String var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final int runApplicationImpl(ApplicationDescriptor var1, boolean var2, int var3) {
      return this.runApplication(var1, var2, null, var3);
   }

   @Override
   public final boolean setInHolsterInputProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void logSchedulingEvent(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final int runApplication(ApplicationDescriptor var1, boolean var2, Thread var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean scheduleApplication(ApplicationDescriptor var1, long var2, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void checkDescriptorSecurity(ApplicationDescriptor var1, int var2) {
      String[] var3 = var1.getArgs();
      if (var3 != null && var3.length != 0 && ControlledAccess.verifyRRISignature(var1.getModuleHandle())) {
         ControlledAccess.assertRRISignature(var2);
      }
   }

   @Override
   public final void setCurrentPowerOnBehavior(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final long getNextAlarm(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final boolean setNextAlarm(boolean var1, ApplicationDescriptor var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final ApplicationProcess findProcess(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final ApplicationProcess findProcess(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void requestForeground(int var1) {
      ApplicationProcess var2 = this.findProcess(var1);
      if (var2 != null) {
         this.requestForeground(var2, false);
      }
   }

   @Override
   public final void requestForegroundForConsole() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void appError(String var0) {
      System.out.println(var0);
      EventLogger.logEvent(-7509200465648525729L, var0.getBytes());
   }

   @Override
   public final int getProcessId(ApplicationDescriptor var1) {
      ApplicationProcess var2 = this.findProcess(var1);
      return var2 == null ? -1 : var2.getProcessId();
   }

   @Override
   public final int getForegroundProcessId() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int runApplication(ApplicationDescriptor var1, boolean var2) {
      int var3 = TraceBack.getCallingModule(0);
      return this.runApplication(var1, var2, null, var3);
   }

   private final void processExited(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean wasDeviceTimeValidOnStartup() {
      return this._dateTimeWasValidOnStartup;
   }

   @Override
   public final ApplicationDescriptor[] getVisibleApplications() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void lockSystemInternal(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isConsoleDescriptor(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isInHolsterInputProcess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void launch(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void lockSystem(boolean var1) {
      this.assertChangeDeviceSettingsPermission();
      this.lockSystemInternal(var1);
   }

   private final void assertIPCPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }

   @Override
   public final boolean isSystemLocked() {
      return this._securityProcess != null;
   }

   @Override
   public final void unlockSystem() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final ApplicationDescriptor getNewDescriptor(ApplicationDescriptor var0, String[] var1) {
      int var2 = var0.getFlags();
      if (var1 == null && (var2 & 4) == 0) {
         return var0;
      }

      var2 &= -5;
      return new ApplicationDescriptor(
         var0, var0.getName(), var1, var0.getIcon(), var0.getPosition(), var0.getNameResourceBundle(), var0.getNameResourceId(), var2
      );
   }

   @Override
   protected final boolean postGlobalEventImpl(long var1, int var3, int var4, Object var5, Object var6, int var7) {
      return this.postGlobalEvent(var1, var3, var4, var5, var6, var7);
   }

   @Override
   public final boolean postGlobalEvent(long var1, int var3, int var4, Object var5, Object var6) {
      int var7 = TraceBack.getCallingModule(0);
      return this.postGlobalEvent(var1, var3, var4, var5, var6, var7);
   }

   private final boolean postGlobalEvent(long var1, int var3, int var4, Object var5, Object var6, int var7) {
      this.assertIPCPermission();
      boolean var8 = ControlledAccess.verifyRRISignature(var7);
      return this.postGlobalEventImpl(var1, var3, var4, var5, var6, var8);
   }

   private final void assertChangeDeviceSettingsPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   private final boolean postGlobalEventImpl(long var1, int var3, int var4, Object var5, Object var6, boolean var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean postGlobalEvent(int var1, long var2, int var4, int var5, Object var6, Object var7) {
      this.assertIPCPermission();
      ApplicationProcess var8 = this.findProcess(var1);
      if (var8 == null) {
         return false;
      }

      int var9 = TraceBack.getCallingModule(0);
      boolean var10 = ControlledAccess.verifyRRISignature(var9);
      this.postGlobalEventImpl(var8, var2, var4, var5, var6, var7, var10);
      return true;
   }

   ApplicationManagerImpl() {
      this._applicationRegistry = new ApplicationRegistry();
      this._eventLogger = (EventLogger)(new Object());
      this._processes = new ApplicationManagerImpl$ApplicationProcessContainer();
      this._scheduledApps = (Vector)(new Object());
      this._thisProcess = Process.currentProcess();
      this._switchForegroundMessage = (Message)(new Object(0, 12));
      this._switchBackgroundMessage = (Message)(new Object(0, 13));
      this._refreshDisplayMessage = (Message)(new Object(0, 3));
      this._globalEventMessage = (Message)(new Object(32, 0));
      this._eventDispatchManager = EventDispatchManager.getInstance();
      if (!InternalServices.isDateTimeValid()) {
         this._dateTimeWasValidOnStartup = false;
         DeviceInternal.setDateTime(1141171200000L);
      } else {
         this._dateTimeWasValidOnStartup = true;
      }

      this.run();
   }

   private final void postInternalGlobalEvent(ApplicationProcess var1, long var2, int var4, int var5, Object var6, Object var7) {
      this.postGlobalEventImpl(var1, var2, var4, var5, var6, var7, true);
   }

   private final void postGlobalEventImpl(ApplicationProcess var1, long var2, int var4, int var5, Object var6, Object var7, boolean var8) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void postMessageToProcess(long var1, ApplicationProcess var3, Message var4, boolean var5) {
      if (var5 == var3.isRIMProcess()) {
         var3.postMessage(var4);
      } else {
         if (var5
            && (
               var1 == 7207871974803693937L
                  || var1 == 8877632280522743328L
                  || var1 == 3596208183088439728L
                  || var1 == -8040378802380461050L
                  || var1 == -7464003439710973532L
                  || var1 == 8508406279413621091L
                  || var1 == -594020114676189989L
                  || var1 == 1309561383038111736L
                  || var1 == -8392006003204551101L
                  || var1 == 4681343386835470834L
                  || var1 == 945659952435832745L
                  || var1 == -4394903006263251010L
                  || var1 == -4220058463650496006L
                  || var1 == -583230596614878690L
                  || var1 == 1348796660760556312L
                  || var1 == 8288627527798139133L
                  || var1 == -5256071285987383000L
                  || var1 == 6213587377148297993L
                  || var1 == 1077267820605375385L
                  || var1 == 2522898683889177438L
                  || var1 == -5448760422790860711L
                  || var1 == -3769281743063593175L
                  || var1 == 6498096261923284925L
                  || var1 == 158775118060600435L
                  || var1 == -860845403685493259L
                  || var1 == 8478935834746748823L
                  || var1 == -7853136852381124900L
                  || var1 == 5061624963542184609L
            )) {
            var3.postMessage(var4);
         }
      }
   }

   private final void setGlobalEventMessage(long var1, int var3, int var4, Object var5, Object var6) {
      this._globalEventMessage.setEvent((int)var1);
      this._globalEventMessage.setSubMessage((int)(var1 >> 32));
      this._globalEventMessage.setData0(var3);
      this._globalEventMessage.setData1(var4);
      this._globalEventMessage.setObject0(var5);
      this._globalEventMessage.setObject1(var6);
   }

   static final long getGlobalEventGUID(Message var0) {
      return ((var0.getSubMessage() & 4294967295L) << 32) + (var0.getEvent() & 4294967295L);
   }

   private final void processGlobalEvent(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void runOnStartup(int[] var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void startTier(Vector var1, boolean var2, CodeSigningKey var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void waitForTier(boolean var1, boolean var2) {
      if (var1) {
         if (var2) {
            Process.waitForIdle(300000);
         } else {
            Process.waitForIdle();
         }

         this.processExited(true);
         net.rim.vm.Memory.quickGC();
         LowMemoryManager.poll();
      }
   }

   private final void holsterStateChange(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean inStartup() {
      return this._startupThread != null;
   }

   private final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void checkForKeyboardLag(Message var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void processMessage(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void executeScheduledApplications() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native void startupDone();
}
