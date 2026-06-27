package net.rim.device.cldc.io.sms;

import javax.microedition.io.Datagram;
import javax.wireless.messaging.MessageListener;
import net.rim.blackberry.api.sms.OutboundMessageListener;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.DatagramStatusListener;
import net.rim.device.api.io.IOProperties;
import net.rim.device.api.io.SmsAddress;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.system.SMSPacketHeader;
import net.rim.device.api.system.SMSPacketListener;
import net.rim.device.api.util.Arrays;
import net.rim.device.cldc.io.nativebase.NativeTransport;
import net.rim.device.internal.system.VoiceDataUsage;
import net.rim.vm.WeakReference;

public final class Transport extends NativeTransport implements SMSPacketListener {
   private byte[] _txEncode;
   private StatusThread _statusThread;
   private OutboundMessageListener _outboundListener;

   @Override
   public final void nativeInit() {
      super._networkServiceMask = 6;
      super._maxPacketSize = SMSPacketHeader.getCharacters(1, 0);
      this._txEncode = new byte[super._maxPacketSize];
      this._statusThread = new StatusThread(this);
      this._statusThread.start();
   }

   @Override
   public final void nativeSendVerify(DatagramAddressBase var1, Datagram var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void nativeSendSetupHeader(Datagram var1, IOProperties var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void nativeSendSetupData(Datagram var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void packetNotSent(int var1, int var2) {
      this.queueSendStatus(var1, -2147483648 | var2);
   }

   @Override
   public final int nativeGetStatus(int var1) {
      switch (var1) {
         case -3:
         case -2:
         case 0:
         case 1:
         case 2:
         case 129:
            return var1;
         default:
            switch (var1 & 2147483647) {
               case 0:
               case 2:
               case 3:
               case 5:
               case 6:
               case 7:
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:
               case 15:
                  return 12673;
               case 1:
               case 8:
                  return -3;
               case 4:
                  return 13469;
               case 14:
                  return 13470;
               case 16:
               default:
                  return 13442;
               case 17:
                  return 13443;
               case 18:
                  return 13444;
               case 19:
                  return 13445;
               case 20:
                  return 13446;
               case 21:
                  return 13447;
               case 22:
                  return 13448;
               case 23:
                  return 13449;
               case 24:
                  return 13450;
               case 25:
                  return 13451;
               case 26:
                  return 13452;
               case 27:
                  return 13453;
               case 28:
                  return 13454;
               case 29:
                  return 13455;
               case 30:
                  return 13456;
               case 31:
                  return 13457;
               case 32:
                  return 13440;
               case 33:
                  return 13458;
               case 34:
                  return 13459;
               case 35:
                  return 13460;
               case 36:
                  return 13461;
               case 37:
                  return 13462;
               case 38:
                  return 13463;
               case 39:
                  return 13464;
               case 40:
                  return 13441;
               case 41:
                  return 13465;
               case 42:
                  return 13466;
               case 43:
                  return 13467;
               case 44:
                  return 13468;
               case 45:
                  return -4;
            }
      }
   }

   @Override
   public final void nativePostSend() {
      if (super._txData == this._txEncode) {
         Arrays.fill(this._txEncode, (byte)0, 0, super._txLength);
      }

      super._txHeader = null;
      super.nativePostSend();
   }

   @Override
   protected final void queueDgslEvent(int var1, int var2, Object var3) {
      this._statusThread.addStatus(false, null, var1, var2, var3);
   }

   @Override
   protected final void queueDgslEvent(DatagramStatusListener var1, int var2, int var3, Object var4) {
      this._statusThread.addStatus(true, var1, var2, var3, var4);
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(String var1, boolean var2) {
      Object var3 = new Object(var1);
      if (var2) {
         ((SmsAddress)var3).swap();
      }

      return (DatagramAddressBase)var3;
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(DatagramAddressBase var1, boolean var2) {
      Object var3 = new Object(var1);
      if (var2) {
         ((SmsAddress)var3).swap();
      }

      return (DatagramAddressBase)var3;
   }

   @Override
   public final void send(Datagram var1, DatagramAddressBase var2, IOProperties var3, DatagramStatusListener var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void notifyListener(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void addOutboundMessageListener(MessageListener var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void packetSent(int var1, int var2) {
      this.addDgramId(super._txListener, super._txDgramId, var2);
      super.packetSent(var1, var2);
   }

   public final boolean isPortReserved(int var1) {
      for (int var2 = super._superConnections.length - 1; var2 >= 0; var2--) {
         WeakReference var3 = super._superConnections[var2];
         Object var4 = null;
         if (var3 != null && (var4 = var3.get()) != null) {
            Protocol var5 = (Protocol)var4;
            if (var5.getPort() == var1) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public final void packetReceived(SMSPacketHeader var1, byte[] var2) {
      EventLogger.logEvent(super.GUID, 1381528436, 4);
      DatagramBase var3 = SmsUtil.decode(this, var1, var2);
      EventLogger.logEvent(super.GUID, 1381527669, 5);
      if (this.passUpDatagram(var3)) {
         VoiceDataUsage.addDataBytes(var2.length);
      } else {
         EventLogger.logEvent(super.GUID, 1381527152, 3);
      }
   }

   @Override
   public final void packetDelivered(int var1, int var2, int var3) {
      short var4;
      switch (var2) {
         case 0:
            var4 = 5;
            break;
         case 69:
            var4 = 12929;
            break;
         case 70:
            var4 = 12931;
            break;
         case 130:
            var4 = 13185;
            break;
         case 131:
            var4 = 13186;
            break;
         case 132:
            var4 = 13187;
            break;
         case 133:
            var4 = 13188;
            break;
         case 134:
            var4 = 13189;
            break;
         case 135:
            var4 = 13190;
            break;
         case 136:
            var4 = 13191;
            break;
         case 137:
            var4 = 13192;
            break;
         default:
            int var5 = var2 & 96;
            switch (var5) {
               case 0:
                  return;
               case 32:
                  return;
               case 64:
                  var4 = 12932;
                  break;
               case 96:
                  return;
               default:
                  return;
            }
      }

      int var6 = var3 & -65536 | var1 & 65535;
      Object var7 = new Object(var6);
      this.queueDgslEvent(var1, var4, var7);
   }
}
