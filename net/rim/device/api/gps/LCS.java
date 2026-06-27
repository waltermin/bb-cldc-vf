package net.rim.device.api.gps;

import net.rim.device.api.system.Application;

public final class LCS {
   public static final int LCS_NOTIFICATION_ONLY;
   public static final int LCS_VERIFICATION_DEFAULT_ALLOWED;
   public static final int LCS_VERIFICATION_DEFAULT_NOT_ALLOWED;

   public static final void addListener(Application var0, LCSListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, LCSListener var1) {
      var0.removeListener(23, var1);
   }

   public static final native void assistDataRequestFailed();

   public static final native boolean getTxRrlp(byte[] var0, int var1);

   public static final native boolean processRrlp(byte[] var0, int var1);

   public static final native boolean verificationResponse(boolean var0);

   public static final native boolean getLCSClientName(byte[] var0);
}
