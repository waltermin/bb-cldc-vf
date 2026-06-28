package net.rim.device.cldc.io.sms;

import javax.microedition.io.Datagram;
import javax.wireless.messaging.MessageListener;
import net.rim.blackberry.api.sms.OutboundMessageListener;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.DatagramConnectionBase;
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
   public final void nativeSendVerify(DatagramAddressBase addressBase, Datagram datagram) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void nativeSendSetupHeader(Datagram datagram, IOProperties properties) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void nativeSendSetupData(Datagram datagram) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void packetNotSent(int packetId, int error) {
      this.queueSendStatus(packetId, -2147483648 | error);
   }

   @Override
   public final int nativeGetStatus(int status) {
      switch (status) {
         case -3:
         case -2:
         case 0:
         case 1:
         case 2:
         case 129:
            return status;
         default:
            switch (status & 2147483647) {
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
   protected final void queueDgslEvent(int subId, int status, Object context) {
      this._statusThread.addStatus(false, null, subId, status, context);
   }

   @Override
   protected final void queueDgslEvent(DatagramStatusListener listener, int dgramId, int status, Object context) {
      this._statusThread.addStatus(true, listener, dgramId, status, context);
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(String address, boolean swap) {
      SmsAddress ret = (SmsAddress)(new Object(address));
      if (swap) {
         ret.swap();
      }

      return ret;
   }

   @Override
   public final DatagramAddressBase newDatagramAddressBase(DatagramAddressBase addressBase, boolean swap) {
      SmsAddress ret = (SmsAddress)(new Object(addressBase));
      if (swap) {
         ret.swap();
      }

      return ret;
   }

   @Override
   public final void send(Datagram datagram, DatagramAddressBase addressBase, IOProperties properties, DatagramStatusListener listener, int dgramId) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void notifyListener(Datagram d) {
      try {
         DatagramBase db = (DatagramBase)d;
         if (this._outboundListener != null) {
            this._outboundListener.notifyOutgoingMessage(Protocol.makeMessage(db));
            return;
         }
      } catch (Throwable var3) {
      }
   }

   public final void addOutboundMessageListener(MessageListener l) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void packetSent(int packetId, int networkId) {
      this.addDgramId(super._txListener, super._txDgramId, networkId);
      super.packetSent(packetId, networkId);
   }

   public final boolean isPortReserved(int port) {
      for (int i = super._superConnections.length - 1; i >= 0; i--) {
         WeakReference w = super._superConnections[i];
         DatagramConnectionBase c = null;
         if (w != null && (c = (DatagramConnectionBase)w.get()) != null) {
            Protocol protocol = (Protocol)c;
            if (protocol.getPort() == port) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public final void packetReceived(SMSPacketHeader header, byte[] data) {
      EventLogger.logEvent(super.GUID, 1381528436, 4);
      DatagramBase dgram = SmsUtil.decode(this, header, data);
      EventLogger.logEvent(super.GUID, 1381527669, 5);
      if (this.passUpDatagram(dgram)) {
         VoiceDataUsage.addDataBytes(data.length);
      } else {
         EventLogger.logEvent(super.GUID, 1381527152, 3);
      }
   }

   @Override
   public final void packetDelivered(int networkId, int status, int messageTypeAndCauseCode) {
      int event;
      switch (status) {
         case 0:
            event = 5;
            break;
         case 69:
            event = 12929;
            break;
         case 70:
            event = 12931;
            break;
         case 130:
            event = 13185;
            break;
         case 131:
            event = 13186;
            break;
         case 132:
            event = 13187;
            break;
         case 133:
            event = 13188;
            break;
         case 134:
            event = 13189;
            break;
         case 135:
            event = 13190;
            break;
         case 136:
            event = 13191;
            break;
         case 137:
            event = 13192;
            break;
         default:
            int bits = status & 96;
            switch (bits) {
               case 0:
                  return;
               case 32:
                  return;
               case 64:
                  event = 12932;
                  break;
               case 96:
                  return;
               default:
                  return;
            }
      }

      int tagIDAndCauseCode = messageTypeAndCauseCode & -65536 | networkId & 65535;
      Integer intObject = (Integer)(new Object(tagIDAndCauseCode));
      this.queueDgslEvent(networkId, event, intObject);
   }
}
