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
   public final Connection openPrim(String var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getLocalPort() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final Datagram newDatagram(byte[] var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(byte[] var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public final Datagram newDatagram(int var1, String var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final Datagram newMidletDatagram(byte[] var1, int var2, String var3) {
      this.checkForClosed();
      var3 = this.midletSpecificWork(var3);
      Datagram var4 = ((Transport)super._transport).newMidletDatagram(var1, 0, var2, var3);
      if (var1 == null && var2 > 0) {
         var4.setLength(var2);
      }

      return var4;
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
   protected final boolean isAddressed(DatagramAddressBase var1) {
      return ((UdpInternalAddress)super._receiveFilter).equals(var1, this._promiscuousMode);
   }

   @Override
   public final void receive(Datagram var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final byte[] setup(int var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final String midletSpecificWork(String var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int getProperties(String var1) {
      byte var2 = 2;
      if (UdpInternalAddress.wifiRequested(var1)) {
         var2 |= 16;
      }

      return var2;
   }
}
