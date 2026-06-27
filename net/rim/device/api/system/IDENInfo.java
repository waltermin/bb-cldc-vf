package net.rim.device.api.system;

public final class IDENInfo {
   private byte[] _imei;
   private int _homeMCC;
   private int _homeNDC;
   private String _homeNetworkName;
   private static IDENInfo _info;

   private IDENInfo() {
   }

   private static final synchronized IDENInfo getInfo() {
      if (_info == null) {
         _info = new IDENInfo();
      }

      getInfo(_info);
      return _info;
   }

   private static final native void getInfo(IDENInfo var0);

   public static final byte[] getIMEI() {
      return getInfo()._imei;
   }

   public static final String imeiToString(byte[] var0) {
      if (var0 == null) {
         return null;
      }

      Object var1 = new Object();

      for (int var2 = 0; var2 < var0.length; var2++) {
         ((StringBuffer)var1).append((char)(var0[var2] + 48));
      }

      return ((StringBuffer)var1).toString();
   }

   public static final String getRegistrationAddress() {
      return null;
   }

   public static final int getHomeMCC() {
      return getInfo()._homeMCC;
   }

   public static final int getHomeNDC() {
      return getInfo()._homeNDC;
   }

   public static final String getHomeNetworkName() {
      return getInfo()._homeNetworkName;
   }

   public static final IDENInfo$IDENCellInfo getCellInfo() {
      IDENInfo$IDENCellInfo var0 = new IDENInfo$IDENCellInfo(null);
      getCellInfo(var0);
      return var0;
   }

   public static final native int getSQELevel();

   private static final native void getCellInfo(IDENInfo$IDENCellInfo var0);
}
