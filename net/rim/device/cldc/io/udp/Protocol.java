package net.rim.device.cldc.io.udp;

import javax.microedition.io.Connection;
import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.UdpAddress;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.ControlledAccessException;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.cldc.io.nativebase.NativeConnectionBase;
import net.rim.device.cldc.io.tunnel.Tunnel;
import net.rim.device.internal.io.PortAssigner;
import net.rim.vm.Process;

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
      throw new RuntimeException("cod2jar: field: unresolved slot");
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
      this.checkForClosed();
      return ((UdpAddress)super._receiveFilter).getDestPort();
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
      this.checkForClosed();
      int length = super._transport.getMaximumLength();
      switch (((UdpAddress)super._addressBase).getType()) {
         case 2:
         case 4:
            length -= GpakUtil.getHeaderSize();
         default:
            return length;
      }
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
      super.receive(datagram);
      int type = ((UdpAddress)super._receiveFilter).getType();
      if (type != -1 && (type & 6) != 0) {
         if (datagram instanceof DatagramBase) {
            UdpInternalAddress addressBase = new UdpInternalAddress(((DatagramBase)datagram).getAddressBase());
            int packetType = GpakUtil.decode(datagram, addressBase);
            addressBase.setType(packetType);
            ((DatagramBase)datagram).setAddressBase(addressBase);
            return;
         }

         UdpInternalAddress addressBase = new UdpInternalAddress(datagram.getAddress());
         int packetType = GpakUtil.decode(datagram);
         addressBase.setType(packetType);
         datagram.setAddress(addressBase.getAddress());
      }
   }

   @Override
   public final byte[] setup(int callType, Object context) {
      switch (callType) {
         case -157135626:
            return super.setup(callType, context);
         case -157135625:
         default:
            if (!this._promiscuousMode) {
               try {
                  ControlledAccess.assertRRISignatures(true);
               } catch (ControlledAccessException cae) {
                  return null;
               }

               this._promiscuousMode = true;
               ((UdpAddress)super._receiveFilter).setDestPort(-1);
               if (this._localPort != -1 && _hpa != null) {
                  synchronized (_hpa) {
                     _hpa.deregisterConnection(this._localPort, this, this._apnName);
                  }
               }

               this._localPort = -1;
               if (this._tunnel != null) {
                  this._tunnel.close();
                  return null;
               }
            }

            return null;
      }
   }

   private final String midletSpecificWork(String address) {
      int mh = Process.currentProcess().getModuleHandle();
      if (CodeModuleManager.isMidlet(mh) && address != null) {
         int index = address.indexOf(SLASH_SLASH);
         if (index > 0) {
            address = address.substring(index);
         }

         if (super._addressBase instanceof UdpAddress) {
            UdpAddress uab = (UdpAddress)super._addressBase;
            if (uab.getSrcPort() == -1) {
               address = address + ';' + this._localPort;
            }

            String apn = uab.getApn();
            if (apn == null || apn.length() == 0) {
               apn = this._apnName;
            }

            if (apn != null && apn.length() > 0) {
               address = address + SLASH + apn;
            }
         }
      }

      return address;
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
