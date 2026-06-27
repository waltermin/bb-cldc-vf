package net.rim.device.internal.system;

public final class LockEventLogger {
   private static final long GUID;
   private static final String NAME;
   public static final int LOCKACTION_APP;
   public static final int REAL_TIME_CLOCK_UPDATE;
   public static final int IDLE_TIMEOUT;
   public static final int LONGTERM_TIMEOUT;
   public static final int HOLSTER_STATE_CHANGED;
   public static final int RIBBON_NEXT_KEY_REPEAT;
   public static final int RIBBON_ALT_ENTER;
   public static final int SYNC_SECURITY_OPTIONS_PASSWORD_ENABLED;
   public static final int SYNC_SECURITY_OPTIONS_CONTENT_PROTECTION;
   public static final int SYNC_DEVICE_OPTIONS_ITADMIN_SET_ITPOLICY;
   public static final int SYNC_DEVICE_OPTIONS_SECURITY_SET_PASSWORD;
   public static final int BLUETOOH_SEND_CHALLENGE;
   public static final int ITADMIN_OTA_ITPOLICY;
   public static final int ITADMIN_LOCK_HANDHELD;
   public static final int ITADMIN_KILL_HANDHELD;
   public static final int ITADMIN_SET_PASSWORD;
   public static final int SMARTCARD_REMOVED;

   public static final void logLockEvent(int var0) {
      logLockEvent(var0, 0);
   }

   public static final void logLockEvent(int var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private LockEventLogger() {
   }
}
