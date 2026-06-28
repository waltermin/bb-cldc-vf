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

   public SmsAddress(SMSPacketHeader header, int[] ports) {
      this.init(header, ports);
   }

   public SmsAddress(DatagramAddressBase addressBase) {
   }

   public SmsAddress(String address) {
      this._header = (SMSPacketHeader)(new Object());
      this.setAddress(address);
   }

   private final void init(SMSPacketHeader header, int[] ports) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final SMSPacketHeader getHeader() {
      return this._header;
   }

   public final int[] getPorts() {
      return this._ports;
   }

   @Override
   public final void setAddress(String address) {
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
   public final boolean equals(Object addressBase) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int hashCode() {
      int hash = 7;
      String peer = this._header.getPeerAddress();
      if (peer != null) {
         hash = 31 * hash + peer.hashCode();
      }

      if (this._ports != null) {
         for (int i = this._ports.length - 1; i >= 0; i--) {
            hash = 31 * hash + this._ports[i];
         }
      }

      return hash;
   }

   private final boolean matchPort(SmsAddress address) {
      if (this._ports == null) {
         return true;
      }

      if (address._ports != null) {
         for (int i = this._ports.length - 1; i >= 0; i--) {
            if (this._ports[i] == address._ports[0]) {
               return true;
            }
         }
      }

      return false;
   }

   public static final String makeAddress(boolean open, SMSPacketHeader header, int[] ports) {
      StringBuffer buf = (StringBuffer)(new Object());
      if (header == null) {
         header = (SMSPacketHeader)(new Object());
      }

      appendAddress(buf, open, header.getPeerAddress(), ports);
      return buf.toString();
   }

   private static final void appendAddress(StringBuffer buf, boolean open, String peerAddress, int[] ports) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
