package net.rim.device.api.io;

import net.rim.device.api.system.SMSPacketHeader;

public final class SmsAddress extends DatagramAddressBase {
   private SMSPacketHeader _header;
   private boolean _statusReportSpecified;
   private boolean _userHeaderSpecified;
   private int[] _ports;
   public static final byte ORIGINATOR_PORT_INDEX;
   public static final byte DESTINATION_PORT_INDEX;
   public static final int PORT_NBS_VCARD;
   public static final int PORT_NBS_VCALENDAR;
   public static final int PORT_NBS_RING_TONE;
   public static final int PORT_NBS_SIMPLE_EMAIL;
   public static final int PORT_WAP_PUSH;
   public static final int PORT_GCMP;
   public static final int PORT_KODIAK_NETWORKS_WV;

   public SmsAddress() {
      this._header = (SMSPacketHeader)(new Object());
   }

   public SmsAddress(SMSPacketHeader var1, int[] var2) {
      this.init(var1, var2);
   }

   public SmsAddress(DatagramAddressBase var1) {
   }

   public SmsAddress(String var1) {
      this._header = (SMSPacketHeader)(new Object());
      this.setAddress(var1);
   }

   private final void init(SMSPacketHeader var1, int[] var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final SMSPacketHeader getHeader() {
      return this._header;
   }

   public final int[] getPorts() {
      return this._ports;
   }

   @Override
   public final void setAddress(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final String getAddress() {
      if (super._address == null) {
         super._address = makeAddress(false, this._header, this._ports);
      }

      return super._address;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int hashCode() {
      int var1 = 7;
      String var2 = this._header.getPeerAddress();
      if (var2 != null) {
         var1 = 31 * var1 + var2.hashCode();
      }

      if (this._ports != null) {
         for (int var3 = this._ports.length - 1; var3 >= 0; var3--) {
            var1 = 31 * var1 + this._ports[var3];
         }
      }

      return var1;
   }

   private final boolean matchPort(SmsAddress var1) {
      if (this._ports == null) {
         return true;
      }

      if (var1._ports != null) {
         for (int var2 = this._ports.length - 1; var2 >= 0; var2--) {
            if (this._ports[var2] == var1._ports[0]) {
               return true;
            }
         }
      }

      return false;
   }

   public static final String makeAddress(boolean var0, SMSPacketHeader var1, int[] var2) {
      Object var3 = new Object();
      if (var1 == null) {
         var1 = new Object();
      }

      appendAddress((StringBuffer)var3, var0, ((SMSPacketHeader)var1).getPeerAddress(), var2);
      return ((StringBuffer)var3).toString();
   }

   private static final void appendAddress(StringBuffer var0, boolean var1, String var2, int[] var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
