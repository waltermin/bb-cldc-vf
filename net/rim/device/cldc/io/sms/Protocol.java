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
   private Vector _messagequeue = (Vector)(new Object());
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
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int numberOfSegments(Message var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Message receive() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Message newMessage(String var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void send(Message var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void setMessageListener(MessageListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final Message newMessage(String var1) {
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
   public final void send(Datagram var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final int getNominalLength() {
      return this.getMaximumLength();
   }

   @Override
   public final boolean isAddressed(DatagramAddressBase var1) {
      if (this._port == 0) {
         return true;
      }

      Object var2 = var1;
      SMSPacketHeader var3 = ((SmsAddress)var2).getHeader();
      int[] var4 = ((SmsAddress)var2).getPorts();
      return var4 == null || var4.length < 1 ? var3.getProtocolMeaning() == 255 : var4[0] == this._port;
   }

   @Override
   public final int getMaximumLength() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final boolean hasStoreMessage(DatagramBase var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final Message storeMessage(DatagramBase var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   static final Message makeMessage(DatagramBase var0) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private final Message doReceive() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final boolean checkNetwork() {
      return (RadioInfo.getSupportedWAFs() & 11) != 0;
   }

   @Override
   public final void receive(Datagram var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final boolean validateString(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final Connection openPrim(String var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
