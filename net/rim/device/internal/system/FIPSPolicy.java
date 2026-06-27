package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;

public final class FIPSPolicy {
   public static final boolean PASSWORD_REQUIRED_FIPS_DEFAULT;
   public static final int PASSWORD_MIN_LENGTH_FIPS_DEFAULT;
   public static final boolean SUPPRESS_PASSWORD_ECHO_FIPS_DEFAULT;
   public static final boolean TLS_RESTRICT_FIPS_CIPHERS_FIPS_DEFAULT;
   public static final boolean WTLS_RESTRICT_FIPS_CIPHERS_FIPS_DEFAULT;

   public static final boolean getBoolean(int var0, boolean var1, boolean var2) {
      return getFIPSLevel() >= 2 ? var2 : ITPolicy.getBoolean(var0, var1);
   }

   public static final boolean getBoolean(int var0, int var1, boolean var2, boolean var3) {
      return getFIPSLevel() >= 2 ? var3 : ITPolicy.getBoolean(var0, var1, var2);
   }

   public static final int getMaxInteger(int var0, int var1, int var2) {
      return getFIPSLevel() >= 2 ? Math.max(ITPolicy.getInteger(var0, var1), var2) : ITPolicy.getInteger(var0, var1);
   }

   public static final int getInteger(int var0, int var1, int var2, int var3) {
      return getFIPSLevel() >= 2 ? var3 : ITPolicy.getInteger(var0, var1, var2);
   }

   public static final boolean isDevicePasswordRequired() {
      return getBoolean(6, false, true);
   }

   public static final boolean disallowThirdPartyAppDownload() {
      return getFIPSLevel() >= 2 ? true : ITPolicy.getBoolean(24, 11, false);
   }

   public static final int getFIPSLevel() {
      return ITPolicy.getByte(24, 39, (byte)1);
   }
}
