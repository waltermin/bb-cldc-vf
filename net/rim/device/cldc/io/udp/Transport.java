package net.rim.device.cldc.io.udp;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.IOProperties;
import net.rim.device.api.system.RadioPacketHeader;
import net.rim.device.api.system.UDPPacketHeader;
import net.rim.device.api.system.UDPPacketListener;
import net.rim.device.api.util.Arrays;
import net.rim.device.cldc.io.datarecovery.DataRecovery;
import net.rim.device.cldc.io.datarecovery.DataRecoveryListener;
import net.rim.device.cldc.io.nativebase.NativeTransport;

public final class Transport extends NativeTransport implements UDPPacketListener, DataRecoveryListener {
   private byte[] _txAddress;
   private byte[] _txEncode;
   private boolean _txRetryOnNoContext;
   private boolean _connectionAvailable;
   private boolean _isBlackBerryTrafficInvalid;

   public final Datagram newMidletDatagram(byte[] var1, int var2, int var3, String var4) {
      return new MIDletDatagram(var1, var2, var3, var4);
   }

   @Override
   public final void packetReceived(UDPPacketHeader var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dataRecoveryEventOccurred(int var1, int var2) {
      switch (var1) {
         case 1:
            this._connectionAvailable = true;
         default:
            return;
         case 3:
            this._connectionAvailable = false;
      }
   }

   @Override
   public final void nativePreSend() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void nativeSendSetupHeader(Datagram var1, IOProperties var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final void nativeSendSetupData(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int nativeGetStatus(int var1) {
      switch (var1) {
         case -3:
         case 249:
            return -3;
         case -2:
         case 0:
         case 1:
         case 2:
         case 129:
            return var1;
         case 250:
            if (this._txRetryOnNoContext) {
               return -3;
            }
         default:
            return 12673;
         case 252:
            return -4;
         case 255:
            return 12674;
      }
   }

   @Override
   public final void nativePostSend() {
      if (super._txData == this._txEncode) {
         Arrays.fill(this._txEncode, (byte)0);
      }

      super._txHeader.reset();
      super.nativePostSend();
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(String var1, boolean var2) {
      UdpInternalAddress var3 = new UdpInternalAddress(var1);
      if (var2) {
         var3.swap();
      }

      return var3;
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(DatagramAddressBase var1, boolean var2) {
      UdpInternalAddress var3 = new UdpInternalAddress(var1);
      if (var2) {
         var3.swap();
      }

      return var3;
   }

   @Override
   public final void nativeInit() {
      super._networkServiceMask = 4;
      super._maxPacketSize = UDPPacketHeader.getMaxPacketSize();
      super._txHeader = (RadioPacketHeader)(new Object());
      this._txEncode = new byte[super._maxPacketSize];
      DataRecovery.getInstance().addListener(this);
   }

   @Override
   public final void nativeSendVerify(DatagramAddressBase var1, Datagram var2) {
      throw new RuntimeException("cod2jar: type check");
   }
}
