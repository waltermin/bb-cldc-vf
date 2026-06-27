package net.rim.device.api.system;

import net.rim.vm.TraceBack;

public class ApplicationManager {
   private static ApplicationManager _appManager;

   ApplicationManager() {
      _appManager = this;
   }

   public boolean setInHolsterInputProcess() {
      throw null;
   }

   public boolean isInHolsterInputProcess() {
      throw null;
   }

   public void launch(String var1) {
      throw null;
   }

   public void lockSystem(boolean var1) {
      throw null;
   }

   public boolean isSystemLocked() {
      throw null;
   }

   public void unlockSystem() {
      throw null;
   }

   public final int runApplication(ApplicationDescriptor var1) {
      int var2 = TraceBack.getCallingModule(0);
      return this.runApplicationImpl(var1, true, var2);
   }

   protected int runApplicationImpl(ApplicationDescriptor var1, boolean var2, int var3) {
      return this.runApplication(var1, var2);
   }

   public int runApplication(ApplicationDescriptor var1, boolean var2) {
      throw null;
   }

   public boolean scheduleApplication(ApplicationDescriptor var1, long var2, boolean var4) {
      throw null;
   }

   public void requestForeground(int var1) {
      throw null;
   }

   public void requestForegroundForConsole() {
      throw null;
   }

   public boolean isConsoleDescriptor(ApplicationDescriptor var1) {
      throw null;
   }

   public int getProcessId(ApplicationDescriptor var1) {
      throw null;
   }

   public int getForegroundProcessId() {
      throw null;
   }

   public ApplicationDescriptor[] getVisibleApplications() {
      throw null;
   }

   public final boolean postGlobalEvent(long var1) {
      int var3 = TraceBack.getCallingModule(0);
      return this.postGlobalEventImpl(var1, 0, 0, null, null, var3);
   }

   public final boolean postGlobalEvent(long var1, int var3, int var4) {
      int var5 = TraceBack.getCallingModule(0);
      return this.postGlobalEventImpl(var1, var3, var4, null, null, var5);
   }

   public boolean postGlobalEvent(long var1, int var3, int var4, Object var5, Object var6) {
      throw null;
   }

   protected boolean postGlobalEventImpl(long var1, int var3, int var4, Object var5, Object var6, int var7) {
      return this.postGlobalEvent(var1, var3, var4, var5, var6);
   }

   public boolean postGlobalEvent(int var1, long var2, int var4, int var5, Object var6, Object var7) {
      throw null;
   }

   public boolean inStartup() {
      throw null;
   }

   public void setCurrentPowerOnBehavior(int var1) {
      throw null;
   }

   public long getNextAlarm(int var1) {
      throw null;
   }

   public static final ApplicationManager getApplicationManager() {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean wasDeviceTimeValidOnStartup() {
      throw null;
   }
}
