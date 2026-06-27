package net.rim.vm;

import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Phone;

public class Process {
   private int _pid = nextProcessId();
   private Throwable _exception;
   private int _state;
   private int _moduleHandle;
   private String _moduleName;
   private String[] _args;
   private int _haltOnProcessDeath;
   private String _displayName;
   protected MessageQueue _messageQueue = new MessageQueue();
   public static final int ID_NONE;

   private static native int nextProcessId();

   private static native boolean createProcess(Process var0, Thread var1);

   private static native void destroy(int var0);

   private static native Process getProcessFromThread(Thread var0);

   private static native Process getProcessFromId(int var0);

   private static native boolean addProcess(Process var0, Thread var1);

   private static native String loadModule(int var0, int var1, byte[] var2);

   private static native boolean start(int var0, Thread var1, String[] var2, int var3);

   private static native void startThreadInProcess(int var0, Thread var1);

   public void addThread(Thread var1) {
      startThreadInProcess(this._pid, var1);
   }

   public static native void bootComplete();

   public static void waitForIdle() {
      waitForIdle(0);
   }

   public static native void waitForIdle(long var0);

   public static native int getLastIdleCounter();

   public static native void killProcessIfThisThreadDies(boolean var0);

   public static native void interrupt(Thread var0);

   public static native void currentThreadSuicide();

   public void haltDeviceIfThisProcessDies(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static native void setHeapQuota(int var0, int var1);

   private static native int getHeapSize(int var0);

   public static native void setExceptionLogging(int var0, boolean var1);

   public static native void registerAppRegistry(Object var0);

   public static native Object getAppRegistry();

   public native void setThreadLimit(int var1);

   protected Process() {
   }

   public void setHeapQuota(int var1) {
      setHeapQuota(this._pid, var1);
   }

   public int getHeapSize() {
      return getHeapSize(this._pid);
   }

   public void setExceptionLogging(boolean var1) {
      setExceptionLogging(this._pid, var1);
   }

   public static int getThrowableHash(Throwable var0) {
      int var1 = getThrowableHash0(var0);
      String var2 = var0.getMessage();
      if (var2 != null) {
         var1 ^= var2.hashCode();
      }

      return var1;
   }

   public static native int getThrowableHash0(Throwable var0);

   public Throwable getException() {
      return this._exception;
   }

   public synchronized Thread start(int var1, String var2, String[] var3) {
      return this.start(var1, var2, var3, 0);
   }

   public synchronized Thread start(int var1, String var2, String[] var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void start(Thread var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void destroy() {
      this.destroy(null);
   }

   public void destroy(Throwable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getProcessId() {
      return this._pid;
   }

   public static Process currentProcess() {
      return getProcess(Thread.currentThread());
   }

   public static Process getProcess(int var0) {
      return getProcessFromId(var0);
   }

   public final synchronized boolean isAlive() {
      return this._state == 1;
   }

   public static Process getProcess(Thread var0) {
      Process var1 = getProcessFromThread(var0);
      if (var1 != null) {
         return var1;
      }

      var1 = new Process();
      return !addProcess(var1, var0) ? null : var1;
   }

   public final int getModuleHandle() {
      return this._moduleHandle;
   }

   public String getModuleName() {
      return this._moduleName;
   }

   public static native int getModuleHandle(String var0);

   public String[] getArgs() {
      return this._args;
   }

   public static native boolean isModuleEldestSibling(int var0);

   public static native byte[] getModuleData(int var0, String var1);

   public static native String getModuleName(int var0, int var1);

   public static native int getModuleHandle(int var0, int var1);

   public MessageQueue getMessageQueue() {
      return this._messageQueue;
   }

   @Override
   public String toString() {
      return this._displayName;
   }

   public static native Class classForName(String var0, int var1);

   public static long ensureMinimumIdleTime(long var0) {
      if (var0 <= 0) {
         throw new Object();
      }

      if (peekMessage(false)) {
         return 0;
      }

      if (Phone.isSupported() && Phone.getInstance().isActive()) {
         return 0;
      }

      long var2 = DeviceInfo.getIdleTime();
      return var2 < var0 ? 0 : var2;
   }

   public static native boolean peekMessage(boolean var0);

   public static native long getRecentCPUTime(Process var0);

   public static native long getRecentCPUTime(Thread var0);

   public static native long getTotalCPUTime(Process var0);

   public static native long getTotalCPUTime(Thread var0);
}
