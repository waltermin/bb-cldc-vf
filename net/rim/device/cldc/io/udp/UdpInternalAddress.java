package net.rim.device.cldc.io.udp;

import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.UdpAddress;
import net.rim.device.api.util.StringUtilities;

public final class UdpInternalAddress extends UdpAddress {
   private byte[] _data;
   private int _gpakHostAddress = 0;
   private static String PROMISCUOUS_MODE;
   private static String NO_TUNNEL;
   private static String ALLOW_NO_CONTEXT_RETRY;
   private static String INTERFACE_WIFI;

   protected UdpInternalAddress(byte[] var1, int var2, int var3, String var4, int var5, byte[] var6) {
      super(var1, var2, var3, var4, var5);
      this._data = var6;
   }

   public UdpInternalAddress(DatagramAddressBase var1) {
      super(var1);
      if (var1 instanceof UdpInternalAddress) {
         this._gpakHostAddress = ((UdpInternalAddress)var1)._gpakHostAddress;
      }
   }

   public UdpInternalAddress(String var1) {
      super(var1);
   }

   protected final void setType(int var1) {
      super._type = var1;
      super._address = null;
   }

   protected final boolean equals(DatagramAddressBase var1, boolean var2) {
      if (!(var1 instanceof UdpInternalAddress)) {
         return false;
      }

      UdpInternalAddress var3 = (UdpInternalAddress)var1;
      if ((super._ipAddress == -1 || var3._ipAddress == -1 || super._ipAddress == var3._ipAddress)
         && (super._destPort == -1 || var3._destPort == -1 || super._destPort == var3._destPort)
         && (super._srcPort == -1 || var3._srcPort == -1 || super._srcPort == var3._srcPort)
         && (super._apn == null || var3._apn == null || super._apn.equalsIgnoreCase(var3._apn))) {
         int var4;
         if (super._type != -1 && (super._type & 6) != 0) {
            var4 = GpakUtil.decode(var3._data);
         } else {
            var4 = var3._type;
         }

         if ((super._type == -1 || var4 == -1 || (super._type & var4) != 0) && (super._destPort != -1 || var2)) {
            return true;
         }
      }

      return false;
   }

   public static final String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5, int var6, int var7, boolean var8) {
      return makeAddress(var0, var1, var2, var3, var4, var5, var6, var7, var8, var8);
   }

   public static final String makeAddress(boolean var0, byte[] var1, int var2, int var3, String var4, int var5, int var6, int var7, boolean var8, boolean var9) {
      String var10 = UdpAddress.makeAddress(var0, var1, var2, var3, var4 != null ? var4.substring(var5, var5 + var6) : null, var7, null, null);
      if (var8) {
         var10 = var10.concat(PROMISCUOUS_MODE);
      }

      if (!var9) {
         var10 = var10.concat(NO_TUNNEL);
      }

      return var10;
   }

   static final boolean isPromiscuousMode(String var0) {
      return var0 != null && var0.indexOf(PROMISCUOUS_MODE) >= 0;
   }

   static final boolean noTunnelRequired(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   static final boolean retriesOnNoContextRequested(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   static final boolean wifiRequested(String var0) {
      return var0 != null && StringUtilities.toLowerCase(var0, 1701707776).indexOf(INTERFACE_WIFI) >= 0;
   }

   @Override
   public final int hashCode() {
      int var1 = super.hashCode();
      if ((super._type & 2) == 2 && this._gpakHostAddress != 0) {
         var1 = 31 * var1 + this._gpakHostAddress;
      }

      return var1;
   }

   public final void setGpakHostAddress(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getGpakHostAddress() {
      return this._gpakHostAddress;
   }
}
