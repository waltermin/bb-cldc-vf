package net.rim.device.cldc.io.udp;

import javax.microedition.io.Connection;
import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.cldc.io.nativebase.NativeConnectionBase;
import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.internal.io.PortAssigner;

public final class Protocol extends NativeConnectionBase {
   private boolean _promiscuousMode;
   private boolean _promiscuousApnMode;
   private byte _isMidlet;
   private int _localPort = -1;
   private String _apnName;
   private Tunnel _tunnel;
   private static final byte IS_MIDLET_UNDETERMINED;
   private static final byte IS_MIDLET_TRUE;
   private static final byte IS_MIDLET_FALSE;
   private static PortAssigner _hpa;
   private static String SERVER_CHECK_STRING;
   private static String SLASH_SLASH;
   private static String SLASH;
   private static String UDP_TYPE;
   private static String UDP_TUNNEL;

   @Override
   public final Connection openPrim(String name, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void close() {
      super.close();
      if (this._localPort != -1 && _hpa != null) {
         synchronized (_hpa) {
            if (this._promiscuousApnMode) {
               _hpa.deregisterConnection(this._localPort, this);
            } else {
               _hpa.deregisterConnection(this._localPort, this, this._apnName);
            }
         }
      }

      if (this._tunnel != null) {
         this._tunnel.close();
      }
   }

   @Override
   public final int getLocalPort() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final Datagram newDatagram(byte[] buf, int size) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(byte[] buf, int size, String addr) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(int size) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(int size, String addr) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final Datagram newMidletDatagram(byte[] buf, int size, String addr) {
      this.checkForClosed();
      addr = this.midletSpecificWork(addr);
      Datagram datagram = ((Transport)super._transport).newMidletDatagram(buf, 0, size, addr);
      if (buf == null && size > 0) {
         datagram.setLength(size);
      }

      return datagram;
   }

   @Override
   protected final boolean checkNetwork() {
      switch (RadioInfo.getNetworkType()) {
         case 2:
            return false;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         default:
            return true;
      }
   }

   @Override
   public final int getMaximumLength() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final int getNominalLength() {
      this.checkForClosed();
      return this.getMaximumLength();
   }

   @Override
   protected final boolean isAddressed(DatagramAddressBase addressBase) {
      return ((UdpInternalAddress)super._receiveFilter).equals(addressBase, this._promiscuousMode);
   }

   @Override
   public final void receive(Datagram datagram) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final byte[] setup(int callType, Object context) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final String midletSpecificWork(String address) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int getProperties(String name) {
      int properties = 2;
      if (UdpInternalAddress.wifiRequested(name)) {
         properties |= 16;
      }

      return properties;
   }
}
