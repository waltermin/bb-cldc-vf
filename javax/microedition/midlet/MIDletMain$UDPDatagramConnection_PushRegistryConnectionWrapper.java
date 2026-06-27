package javax.microedition.midlet;

import javax.microedition.io.Datagram;
import javax.microedition.io.UDPDatagramConnection;

final class MIDletMain$UDPDatagramConnection_PushRegistryConnectionWrapper implements UDPDatagramConnection {
   private UDPDatagramConnection _udpDatagramConnection;
   private Datagram _datagram;

   @Override
   public final void close() {
      this._udpDatagramConnection.close();
   }

   final void pushBack(Datagram var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final int getLocalPort() {
      return this._udpDatagramConnection.getLocalPort();
   }

   @Override
   public final int getMaximumLength() {
      return this._udpDatagramConnection.getMaximumLength();
   }

   @Override
   public final int getNominalLength() {
      return this._udpDatagramConnection.getNominalLength();
   }

   @Override
   public final Datagram newDatagram(byte[] var1, int var2) {
      return this._udpDatagramConnection.newDatagram(var1, var2);
   }

   @Override
   public final Datagram newDatagram(byte[] var1, int var2, String var3) {
      return this._udpDatagramConnection.newDatagram(var1, var2, var3);
   }

   @Override
   public final Datagram newDatagram(int var1) {
      return this._udpDatagramConnection.newDatagram(var1);
   }

   @Override
   public final Datagram newDatagram(int var1, String var2) {
      return this._udpDatagramConnection.newDatagram(var1, var2);
   }

   @Override
   public final void receive(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void send(Datagram var1) {
      this._udpDatagramConnection.send(var1);
   }

   @Override
   public final String getLocalAddress() {
      return this._udpDatagramConnection.getLocalAddress();
   }

   public MIDletMain$UDPDatagramConnection_PushRegistryConnectionWrapper(UDPDatagramConnection var1) {
      this._udpDatagramConnection = var1;
   }
}
