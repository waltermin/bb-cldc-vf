package net.rim.device.api.system;

import java.util.Vector;
import net.rim.device.api.lowmemory.LowMemoryManager;
import net.rim.device.api.util.Arrays;
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

   final boolean postInternalGlobalEvent(int processId, long guid, int data0, int data1, Object object0, Object object1) {
      ApplicationProcess process = this.findProcess(processId);
      if (process == null) {
         return false;
      }

      this.postGlobalEventImpl(process, guid, data0, data1, object0, object1, true);
      return true;
   }

   final boolean postInternalGlobalEvent(long guid, int data0, int data1, Object object0, Object object1) {
      return this.postGlobalEventImpl(guid, data0, data1, object0, object1, true);
   }

   public final boolean postMessageToForegroundProcess(Message msg) {
      if (this._inputProcess != null) {
         this._inputProcess.postMessage(msg);
         return true;
      } else {
         return false;
      }
   }

   final EventLogger getEventLogger() {
      return this._eventLogger;
   }

   final boolean setForegroundProcess(ApplicationProcess newForegroundProcess, boolean bottomOfZOrder) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void requestForeground(ApplicationProcess newForegroundProcess, boolean wantsBackground) {
      synchronized (this._processes) {
         this._switchForegroundMessage.setSubMessage(0);
         if (newForegroundProcess == null || wantsBackground) {
            int numProcesses = this._processes.numberOfProcesses();
            int i = 0;
            if (this._foregroundProcess != null && this._foregroundProcess == newForegroundProcess) {
               i++;
               this._switchForegroundMessage.setSubMessage(1);
            }

            while (true) {
               if (i < numProcesses) {
                  newForegroundProcess = this._processes.getProcessAtIndex(i);
                  if (!newForegroundProcess.acceptsForeground()) {
                     i++;
                     continue;
                  }
               }

               if (newForegroundProcess == null || i == numProcesses) {
                  return;
               }
               break;
            }
         }

         if (newForegroundProcess.isAlive()) {
            newForegroundProcess.postMessage(this._switchForegroundMessage);
         }
      }
   }

   public final int runApplication(ApplicationDescriptor descriptor, boolean grabForeground, Thread testingThread) {
      int callingModule = TraceBack.getCallingModule(0);
      return this.runApplication(descriptor, grabForeground, testingThread, callingModule);
   }

   @Override
   public final boolean setSecurityManager(SecurityManager securityManager) {
      synchronized (this._processes) {
         if (this._securityManager != null) {
            return false;
         }

         this._securityManager = securityManager;
         return true;
      }
   }

   @Override
   public final SecurityManager getSecurityManager() {
      return this._securityManager;
   }

   @Override
   public final boolean setEngScreenDescriptor(ApplicationDescriptor descriptor) {
      synchronized (this._processes) {
         if (this._engScreenDescriptor != null) {
            return false;
         }

         this._engScreenDescriptor = descriptor;
         return true;
      }
   }

   @Override
   public final ApplicationDescriptor getEngScreenDescriptor() {
      return this._engScreenDescriptor;
   }

   @Override
   public final void setNativeSocketProcess() {
      synchronized (this._processes) {
         if (this._nativeSocketProcess != null) {
            throw new Object();
         }

         this._nativeSocketProcess = (ApplicationProcess)Process.currentProcess();
      }
   }

   @Override
   public final int runApplication(ApplicationDescriptor descriptor, Thread testingThread) {
      int callingModule = TraceBack.getCallingModule(0);
      return this.runApplication(descriptor, true, testingThread, callingModule);
   }

   @Override
   public final boolean setSecurityLockSupercedingProcess() {
      synchronized (this._processes) {
         this._securityLockSupercedingProcessId = ((ApplicationProcess)Process.currentProcess()).getProcessId();
         return true;
      }
   }

   @Override
   public final boolean setConsoleProcess() {
      synchronized (this._processes) {
         if (this._consoleProcess != null) {
            return false;
         }

         this._consoleProcess = (ApplicationProcess)Process.currentProcess();
         this._consoleProcess.haltDeviceIfThisProcessDies(true);
         return true;
      }
   }

   @Override
   public final boolean isSecurityLockSupercedingProcessForeground() {
      return this.getForegroundProcessId() == this._securityLockSupercedingProcessId;
   }

   @Override
   public final void enableSchedulingLog(boolean enable, int size) {
      synchronized (this._scheduledApps) {
         if (enable) {
            if (size < 1) {
               size = 1;
            }

            this._schedulingLog = new String[size];
         } else {
            this._schedulingLog = null;
         }
      }
   }

   @Override
   public final ApplicationProcess[] getProcesses() {
      synchronized (this._processes) {
         return this._processes.getCopyOfProcesses();
      }
   }

   @Override
   public final void redirectInput(Process process, boolean repaint) {
      synchronized (this._processes) {
         if (process != null && process.isAlive()) {
            this._redirectInputProcess = (ApplicationProcess)process;
            if (!DeviceInfo.isInHolster()) {
               this._inputProcess = this._redirectInputProcess;
            }
         } else {
            this._redirectInputProcess = null;
            if (!DeviceInfo.isInHolster()) {
               this._inputProcess = this._foregroundProcess;
            }

            if (repaint) {
               this.repaintForeground();
            }
         }
      }
   }

   @Override
   public final void repaintForeground() {
      try {
         this._foregroundProcess.postMessage(this._refreshDisplayMessage);
      } catch (NullPointerException var2) {
      }
   }

   @Override
   public final boolean postMessage(int pid, Message message) {
      synchronized (this._processes) {
         if (pid != -1) {
            Process process = Process.getProcess(pid);
            if (process != null && process.isAlive()) {
               ((ApplicationProcess)process).postMessage(message);
               return true;
            } else {
               return false;
            }
         } else {
            for (int i = this._processes.numberOfProcesses() - 1; i >= 0; i--) {
               this._processes.getProcessAtIndex(i).postMessage(message);
            }

            return true;
         }
      }
   }

   @Override
   public final void postMessage(Message message) {
      if (this._eventDispatchManager.notify(message)) {
         int processId = this._eventDispatchManager.getNotifyProcessId(message);
         synchronized (this._processes) {
            ApplicationProcess currentProcess = null;

            for (int i = this._processes.numberOfProcesses() - 1; i >= 0; i--) {
               currentProcess = this._processes.getProcessAtIndex(i);
               if (processId == -1 || currentProcess.getProcessId() == processId) {
                  currentProcess.postMessage(message);
               }
            }
         }
      }
   }

   @Override
   public final String[] getSchedulingLog() {
      synchronized (this._scheduledApps) {
         String[] result = new String[this._schedulingLog.length];

         for (int i = 0; i < this._schedulingLog.length; i++) {
            result[i] = this._schedulingLog[i];
         }

         return result;
      }
   }

   @Override
   public final void addForegroundChangeListener(Runnable r) {
      this._foregroundChangeListeners = ListenerUtilities.addListener(this._foregroundChangeListeners, r);
   }

   @Override
   public final void removeForegroundChangeListener(Runnable r) {
      this._foregroundChangeListeners = ListenerUtilities.removeListener(this._foregroundChangeListeners, r);
   }

   @Override
   public final Application getForegroundApplication() {
      synchronized (this._processes) {
         return this._foregroundProcess == null ? null : this._foregroundProcess.getApplication();
      }
   }

   @Override
   public final void resetAppDescriptorOverrides() {
      synchronized (this._processes) {
         int numProcesses = this._processes.numberOfProcesses();

         for (int i = 0; i < numProcesses; i++) {
            ApplicationDescriptor appDescriptor = this._processes.getProcessAtIndex(i).getApplicationDescriptor();
            appDescriptor.setOverrideNameResourceBundle(null);
            appDescriptor.setOverrideNameResourceId(-1);
         }
      }
   }

   @Override
   public final void updateAppDescriptor(String moduleName, String overrideNameResourceBundle, int overrideNameResourceId) {
      synchronized (this._processes) {
         int numProcesses = this._processes.numberOfProcesses();
         int i = 0;
         ApplicationProcess currentProcess = null;

         while (i < numProcesses) {
            currentProcess = this._processes.getProcessAtIndex(i);
            if (currentProcess.getModuleName().equals(moduleName)) {
               ApplicationDescriptor appDescriptor = currentProcess.getApplicationDescriptor();
               appDescriptor.setOverrideNameResourceBundle(overrideNameResourceBundle);
               appDescriptor.setOverrideNameResourceId(overrideNameResourceId);
               break;
            }

            i++;
         }
      }
   }

   @Override
   protected final int runApplicationImpl(ApplicationDescriptor descriptor, boolean grabForeground, int callingModule) {
      return this.runApplication(descriptor, grabForeground, null, callingModule);
   }

   @Override
   public final boolean setInHolsterInputProcess() {
      synchronized (this._processes) {
         if (this._inHolsterInputProcess != null) {
            return false;
         }

         this._inHolsterInputProcess = (ApplicationProcess)Process.currentProcess();
         return true;
      }
   }

   private final void logSchedulingEvent(String msg) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final int runApplication(ApplicationDescriptor descriptor, boolean grabForeground, Thread testingThread, int callingModule) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean scheduleApplication(ApplicationDescriptor descriptor, long time, boolean absolute) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void checkDescriptorSecurity(ApplicationDescriptor descriptor, int callingModule) {
      String[] args = descriptor.getArgs();
      if (args != null && args.length != 0 && ControlledAccess.verifyRRISignature(descriptor.getModuleHandle())) {
         ControlledAccess.assertRRISignature(callingModule);
      }
   }

   @Override
   public final void setCurrentPowerOnBehavior(int powerOnBehavior) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final long getNextAlarm(int powerOnBehavior) {
      synchronized (this._scheduledApps) {
         long nextAlarmTime = Long.MAX_VALUE;
         int numApps = this._scheduledApps.size();

         for (int i = 0; i < numApps; i++) {
            ApplicationDescriptor descriptor = (ApplicationDescriptor)this._scheduledApps.elementAt(i);
            int behavior = descriptor.getPowerOnBehavior();
            if ((behavior & this._currentPowerOnBehavior) > 0) {
               long appTime = descriptor.getNextScheduledTime();
               if (appTime < nextAlarmTime) {
                  nextAlarmTime = appTime;
               }
            }
         }

         return nextAlarmTime;
      }
   }

   private final boolean setNextAlarm(boolean reset, ApplicationDescriptor desiredApp) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final ApplicationProcess findProcess(ApplicationDescriptor descriptor) {
      synchronized (this._processes) {
         ApplicationProcess currentProcess = null;

         for (int i = this._processes.numberOfProcesses() - 1; i >= 0; i--) {
            currentProcess = this._processes.getProcessAtIndex(i);
            if (currentProcess.getApplicationDescriptor().equals(descriptor)) {
               return currentProcess;
            }
         }

         return null;
      }
   }

   private final ApplicationProcess findProcess(int processId) {
      synchronized (this._processes) {
         ApplicationProcess currentProcess = null;

         for (int i = this._processes.numberOfProcesses() - 1; i >= 0; i--) {
            currentProcess = this._processes.getProcessAtIndex(i);
            if (currentProcess.getProcessId() == processId) {
               return currentProcess;
            }
         }

         return null;
      }
   }

   @Override
   public final void requestForeground(int processId) {
      ApplicationProcess newForeground = this.findProcess(processId);
      if (newForeground != null) {
         this.requestForeground(newForeground, false);
      }
   }

   @Override
   public final void requestForegroundForConsole() {
      synchronized (this._processes) {
         if (this._consoleProcess != null) {
            this.requestForeground(this._consoleProcess, false);
         }
      }
   }

   private static final void appError(String msg) {
      System.out.println(msg);
      EventLogger.logEvent(-7509200465648525729L, msg.getBytes());
   }

   @Override
   public final int getProcessId(ApplicationDescriptor descriptor) {
      ApplicationProcess process = this.findProcess(descriptor);
      return process == null ? -1 : process.getProcessId();
   }

   @Override
   public final int getForegroundProcessId() {
      synchronized (this._processes) {
         return this._foregroundProcess == null ? -1 : this._foregroundProcess.getProcessId();
      }
   }

   @Override
   public final int runApplication(ApplicationDescriptor descriptor, boolean grabForeground) {
      int callingModule = TraceBack.getCallingModule(0);
      return this.runApplication(descriptor, grabForeground, null, callingModule);
   }

   private final void processExited(boolean inStartup) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean wasDeviceTimeValidOnStartup() {
      return this._dateTimeWasValidOnStartup;
   }

   @Override
   public final ApplicationDescriptor[] getVisibleApplications() {
      synchronized (this._processes) {
         int numProcesses = this._processes.numberOfProcesses();
         ApplicationDescriptor[] descriptors = new ApplicationDescriptor[0];
         int i = 0;
         ApplicationProcess currentProcess = null;

         while (i < numProcesses) {
            currentProcess = this._processes.getProcessAtIndex(i);
            if (currentProcess.acceptsForeground()) {
               Arrays.add(descriptors, currentProcess.getApplicationDescriptor());
            }

            i++;
         }

         return descriptors;
      }
   }

   private final void lockSystemInternal(boolean force) {
      synchronized (this._processes) {
         if (this._securityManager != null && this._securityProcess == null) {
            int pid = this._securityManager.lockSystem(force);
            if (pid != -1) {
               this._securityProcess = this.findProcess(pid);
            }
         }
      }
   }

   @Override
   public final boolean isConsoleDescriptor(ApplicationDescriptor descriptor) {
      synchronized (this._processes) {
         return this._consoleProcess != null ? this._consoleProcess.getApplicationDescriptor() == descriptor : false;
      }
   }

   @Override
   public final boolean isInHolsterInputProcess() {
      synchronized (this._processes) {
         return this._inHolsterInputProcess == null ? false : this._inHolsterInputProcess == (ApplicationProcess)Process.currentProcess();
      }
   }

   @Override
   public final void launch(String url) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void lockSystem(boolean force) {
      this.assertChangeDeviceSettingsPermission();
      this.lockSystemInternal(force);
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
      this.assertChangeDeviceSettingsPermission();
      synchronized (this._processes) {
         if (this._securityProcess != null) {
            this.postInternalGlobalEvent(this._securityProcess, 1597563888101360867L, 0, 0, null, null);

            try {
               this._processes.wait();
            } catch (InterruptedException var3) {
            }
         }
      }
   }

   private static final ApplicationDescriptor getNewDescriptor(ApplicationDescriptor descriptor, String[] args) {
      int flags = descriptor.getFlags();
      if (args == null && (flags & 4) == 0) {
         return descriptor;
      }

      flags &= -5;
      return new ApplicationDescriptor(
         descriptor,
         descriptor.getName(),
         args,
         descriptor.getIcon(),
         descriptor.getPosition(),
         descriptor.getNameResourceBundle(),
         descriptor.getNameResourceId(),
         flags
      );
   }

   @Override
   protected final boolean postGlobalEventImpl(long guid, int data0, int data1, Object object0, Object object1, int callingModule) {
      return this.postGlobalEvent(guid, data0, data1, object0, object1, callingModule);
   }

   @Override
   public final boolean postGlobalEvent(long guid, int data0, int data1, Object object0, Object object1) {
      int callingModule = TraceBack.getCallingModule(0);
      return this.postGlobalEvent(guid, data0, data1, object0, object1, callingModule);
   }

   private final boolean postGlobalEvent(long guid, int data0, int data1, Object object0, Object object1, int callingModule) {
      this.assertIPCPermission();
      boolean internal = ControlledAccess.verifyRRISignature(callingModule);
      return this.postGlobalEventImpl(guid, data0, data1, object0, object1, internal);
   }

   private final void assertChangeDeviceSettingsPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   private final boolean postGlobalEventImpl(long guid, int data0, int data1, Object object0, Object object1, boolean internal) {
      synchronized (this._processes) {
         this.setGlobalEventMessage(guid, data0, data1, object0, object1);
         ApplicationProcess currentProcess = null;

         for (int i = this._processes.numberOfProcesses() - 1; i >= 0; i--) {
            currentProcess = this._processes.getProcessAtIndex(i);
            this.postMessageToProcess(guid, currentProcess, this._globalEventMessage, internal);
         }

         this.setGlobalEventMessage(0, 0, 0, null, null);
      }

      this.processGlobalEvent(guid);
      return true;
   }

   @Override
   public final boolean postGlobalEvent(int processId, long guid, int data0, int data1, Object object0, Object object1) {
      this.assertIPCPermission();
      ApplicationProcess process = this.findProcess(processId);
      if (process == null) {
         return false;
      }

      int callingModule = TraceBack.getCallingModule(0);
      boolean internal = ControlledAccess.verifyRRISignature(callingModule);
      this.postGlobalEventImpl(process, guid, data0, data1, object0, object1, internal);
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

   private final void postInternalGlobalEvent(ApplicationProcess process, long guid, int data0, int data1, Object object0, Object object1) {
      this.postGlobalEventImpl(process, guid, data0, data1, object0, object1, true);
   }

   private final void postGlobalEventImpl(ApplicationProcess process, long guid, int data0, int data1, Object object0, Object object1, boolean internal) {
      synchronized (this._processes) {
         if (process != null) {
            this.setGlobalEventMessage(guid, data0, data1, object0, object1);
            this.postMessageToProcess(guid, process, this._globalEventMessage, internal);
            this.setGlobalEventMessage(0, 0, 0, null, null);
         }
      }

      this.processGlobalEvent(guid);
   }

   private final void postMessageToProcess(long guid, ApplicationProcess process, Message message, boolean internal) {
      if (internal == process.isRIMProcess()) {
         process.postMessage(message);
      } else {
         if (internal
            && (
               guid == 7207871974803693937L
                  || guid == 8877632280522743328L
                  || guid == 3596208183088439728L
                  || guid == -8040378802380461050L
                  || guid == -7464003439710973532L
                  || guid == 8508406279413621091L
                  || guid == -594020114676189989L
                  || guid == 1309561383038111736L
                  || guid == -8392006003204551101L
                  || guid == 4681343386835470834L
                  || guid == 945659952435832745L
                  || guid == -4394903006263251010L
                  || guid == -4220058463650496006L
                  || guid == -583230596614878690L
                  || guid == 1348796660760556312L
                  || guid == 8288627527798139133L
                  || guid == -5256071285987383000L
                  || guid == 6213587377148297993L
                  || guid == 1077267820605375385L
                  || guid == 2522898683889177438L
                  || guid == -5448760422790860711L
                  || guid == -3769281743063593175L
                  || guid == 6498096261923284925L
                  || guid == 158775118060600435L
                  || guid == -860845403685493259L
                  || guid == 8478935834746748823L
                  || guid == -7853136852381124900L
                  || guid == 5061624963542184609L
            )) {
            process.postMessage(message);
         }
      }
   }

   private final void setGlobalEventMessage(long guid, int data0, int data1, Object object0, Object object1) {
      this._globalEventMessage.setEvent((int)guid);
      this._globalEventMessage.setSubMessage((int)(guid >> 32));
      this._globalEventMessage.setData0(data0);
      this._globalEventMessage.setData1(data1);
      this._globalEventMessage.setObject0(object0);
      this._globalEventMessage.setObject1(object1);
   }

   static final long getGlobalEventGUID(Message message) {
      return ((message.getSubMessage() & 4294967295L) << 32) + (message.getEvent() & 4294967295L);
   }

   private final void processGlobalEvent(long guid) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void runOnStartup(int[] handles, boolean inStartup) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void startTier(Vector tier, boolean inStartup, CodeSigningKey rri) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void waitForTier(boolean inStartup, boolean allowTimeout) {
      if (inStartup) {
         if (allowTimeout) {
            Process.waitForIdle(300000);
         } else {
            Process.waitForIdle();
         }

         this.processExited(true);
         net.rim.vm.Memory.quickGC();
         LowMemoryManager.poll();
      }
   }

   private final void holsterStateChange(boolean inHolster) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final boolean inStartup() {
      return this._startupThread != null;
   }

   private final void run() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void checkForKeyboardLag(Message message) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void processMessage(Message message) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void executeScheduledApplications() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final native void startupDone();
}
