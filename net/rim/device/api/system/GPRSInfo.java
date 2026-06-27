package net.rim.device.api.system;

public final class GPRSInfo {
   private byte[] _imei;
   private byte[] _imeisv;
   private int _gprsState;
   private String _zoneName;
   public static final int GPRS_STATE_IDLE;
   public static final int GPRS_STATE_STANDBY;
   public static final int GPRS_STATE_READY;
   private static GPRSInfo _info;

   private GPRSInfo() {
   }

   public static final GPRSInfo$GPRSCellInfo[] createCellInfoArray() {
      GPRSInfo$GPRSCellInfo[] var0 = new GPRSInfo$GPRSCellInfo[11];

      for (int var1 = var0.length - 1; var1 >= 0; var1--) {
         var0[var1] = new GPRSInfo$GPRSCellInfo(null);
      }

      return var0;
   }

   private static final synchronized GPRSInfo getInfo() {
      if (_info == null) {
         _info = new GPRSInfo();
      }

      getInfo(_info);
      return _info;
   }

   private static final native void getInfo(GPRSInfo var0);

   public static final byte[] getIMEI() {
      return getInfo()._imei;
   }

   public static final byte[] getIMEISV() {
      return getInfo()._imeisv;
   }

   public static final String imeiToString(byte[] var0) {
      return imeiToString(var0, true);
   }

   public static final int getHomeMCC() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getHomeMNC() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String imeiToString(byte[] var0, boolean var1) {
      if (var0 == null) {
         return null;
      }

      Object var2 = new Object();

      for (int var3 = 0; var3 < var0.length; var3++) {
         ((StringBuffer)var2).append((char)(var0[var3] + 48));
         if (var1 && (var3 == 5 || var3 == 7 || var3 == 13)) {
            ((StringBuffer)var2).append('.');
         }
      }

      return ((StringBuffer)var2).toString();
   }

   public static final String imeisvToString(byte[] var0, boolean var1) {
      if (var0 == null) {
         return null;
      }

      Object var2 = new Object();

      for (int var3 = 0; var3 < var0.length - 2; var3++) {
         ((StringBuffer)var2).append((char)(var0[var3] + 48));
         if (var1 && (var3 == 5 || var3 == 7 || var3 == 13)) {
            ((StringBuffer)var2).append('.');
         }
      }

      ((StringBuffer)var2).append((char)((var0[var0.length - 1] & 15) + 48));
      ((StringBuffer)var2).append((char)((var0[var0.length - 1] >> 4 & 15) + 48));
      return ((StringBuffer)var2).toString();
   }

   public static final int getGPRSState() {
      return getInfo()._gprsState;
   }

   public static final String getRegistrationAddress() {
      return null;
   }

   public static final String getZoneName() {
      return getInfo()._zoneName;
   }

   public static final GPRSInfo$GPRSCellInfo getCellInfo() {
      GPRSInfo$GPRSCellInfo var0 = new GPRSInfo$GPRSCellInfo(null);
      getCellInfo(var0);
      return var0;
   }

   private static final native void getCellInfo(GPRSInfo$GPRSCellInfo var0);

   public static final native int getCellInfo(GPRSInfo$GPRSCellInfo[] var0);
}
