package net.rim.device.cldc.io.sms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connection;
import javax.microedition.io.Datagram;
import javax.microedition.io.StreamConnection;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.SmsAddress;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.SMSPacketHeader;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.cldc.io.nativebase.NativeConnectionBase;

public final class Protocol extends NativeConnectionBase implements MessageConnection, StreamConnection {
   private Protocol$MessageSegmentQueue _messageSegmentQueue;
   private boolean _isServerMode;
   private String _address;
   private int _port;
   private Protocol$WMAListeningThread _wlt;
   private MessageListener _listener;
   private Vector _messagequeue = new Vector();
   private boolean _stop;
   public static final int SRC_PORT_INDEX;
   public static final int DEST_PORT_INDEX;
   public static String PROPERTY_USER_DATA_HEADER_LENGTH;
   private static final long ID;
   private static Integer USER_DATA_HEADER_LENGTH_INTEGER;
   private static final int MESSAGE_QUEUE_LENGTH;
   private static IntHashtable _portTable;
   private static final int[] RESERVED_PORTS;

   public final int getPort() {
      return this._port;
   }

   public final Message receiveInternal() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int numberOfSegments(Message msg) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Message receive() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Message newMessage(String type, String address) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void send(Message msg) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void setMessageListener(MessageListener l) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Message newMessage(String type) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final InputStream openInputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final DataInputStream openDataInputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final OutputStream openOutputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final DataOutputStream openDataOutputStream() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void send(Datagram datagram) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int getNominalLength() {
      return this.getMaximumLength();
   }

   @Override
   public final boolean isAddressed(DatagramAddressBase addressBase) {
      if (this._port == 0) {
         return true;
      }

      SmsAddress a = (SmsAddress)addressBase;
      SMSPacketHeader header = a.getHeader();
      int[] ports = a.getPorts();
      return ports == null || ports.length < 1 ? header.getProtocolMeaning() == 255 : ports[0] == this._port;
   }

   @Override
   public final int getMaximumLength() {
      int headerSize = 0;
      int[] ports = ((SmsAddress)super._addressBase).getPorts();
      if (ports != null) {
         headerSize = SmsUtil.getHeaderSize(ports[0]);
      }

      return super._transport.getMaximumLength() - headerSize;
   }

   @Override
   public final void close() {
      if (this._wlt != null) {
         synchronized (this) {
            this._stop = true;
         }
      }

      super.close();
   }

   private final boolean hasStoreMessage(DatagramBase d) {
      if (this._messageSegmentQueue == null) {
         this._messageSegmentQueue = new Protocol$MessageSegmentQueue(10);
      }

      Integer refNumberInteger = (Integer)d.getProperty(SmsUtil.PROPERTY_REF_NUMBER);
      if (refNumberInteger != null) {
         int refNumber = refNumberInteger;
         SmsAddress a = (SmsAddress)d.getAddressBase();
         String address = SmsAddress.makeAddress(false, a.getHeader(), null);
         String key = address + '-' + refNumber;

         for (int i = 0; i < this._messageSegmentQueue.messages.length; i++) {
            if (this._messageSegmentQueue.messages[i] != null && this._messageSegmentQueue.messages[i].getKey().equals(key)) {
               return true;
            }
         }
      }

      return false;
   }

   private final Message storeMessage(DatagramBase d) {
      if (this._messageSegmentQueue == null) {
         this._messageSegmentQueue = new Protocol$MessageSegmentQueue(10);
      }

      int refNumber = -1;
      int totalSegments = 1;
      Integer ref = (Integer)d.getProperty(SmsUtil.PROPERTY_REF_NUMBER);
      if (ref == null) {
         return null;
      }

      refNumber = ref;
      ref = (Integer)d.getProperty(SmsUtil.PROPERTY_TOTAL_SEGMENTS);
      if (ref == null) {
         return null;
      }

      totalSegments = ref;
      SmsAddress a = (SmsAddress)d.getAddressBase();
      String address = SmsAddress.makeAddress(false, a.getHeader(), null);
      String key = address + '-' + refNumber;
      Message msg = null;
      Protocol$StoreMessage sm = null;

      int i;
      for (i = 0; i < this._messageSegmentQueue.messages.length; i++) {
         if (this._messageSegmentQueue.messages[i] != null && this._messageSegmentQueue.messages[i].getKey().equals(key)) {
            sm = this._messageSegmentQueue.messages[i];
            break;
         }
      }

      if (sm != null) {
         msg = sm.add(d);
         if (msg != null) {
            this._messageSegmentQueue.messages[i] = null;
            return msg;
         } else {
            return null;
         }
      } else {
         sm = new Protocol$StoreMessage(totalSegments, key);
         msg = sm.add(d);
         if (msg == null) {
            this._messageSegmentQueue.add(sm);
            return null;
         } else {
            return msg;
         }
      }
   }

   static final Message makeMessage(DatagramBase datagram) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final Message doReceive() {
      DatagramBase d = new DatagramBase();

      while (true) {
         synchronized (this) {
            if (this._stop) {
               return null;
            }
         }

         this.receive(d);
         SmsAddress a = (SmsAddress)d.getAddressBase();
         SMSPacketHeader header = a.getHeader();
         Integer numSegments = (Integer)d.getProperty(SmsUtil.PROPERTY_TOTAL_SEGMENTS);
         Integer segId = (Integer)d.getProperty(SmsUtil.PROPERTY_SEGMENT_NUMBER);
         if (numSegments == null || numSegments == 1) {
            return makeMessage(d);
         }

         if (segId == null || segId <= 1 || header.getProtocolMeaning() != 255 || this.hasStoreMessage(d)) {
            Message msg = this.storeMessage(d);
            if (msg != null) {
               return msg;
            }
         }
      }
   }

   @Override
   protected final boolean checkNetwork() {
      return (RadioInfo.getSupportedWAFs() & 11) != 0;
   }

   @Override
   public final void receive(Datagram datagram) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final boolean validateString(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final Connection openPrim(String name, int mode, boolean timeouts) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
