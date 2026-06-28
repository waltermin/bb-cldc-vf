package javax.microedition.midlet;

import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;

final class MIDletMain$MessageConnection_PushRegistryConnectionWrapper implements MessageConnection {
   private MessageConnection _messageConnection;
   private Message _message;

   @Override
   public final void close() {
      this._messageConnection.close();
   }

   final void pushBack(Message m) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final Message newMessage(String type, String address) {
      return this._messageConnection.newMessage(type, address);
   }

   @Override
   public final int numberOfSegments(Message msg) {
      return this._messageConnection.numberOfSegments(msg);
   }

   @Override
   public final Message receive() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void send(Message msg) {
      this._messageConnection.send(msg);
   }

   @Override
   public final void setMessageListener(MessageListener l) {
      this._messageConnection.setMessageListener(l);
   }

   @Override
   public final Message newMessage(String type) {
      return this._messageConnection.newMessage(type);
   }

   public MIDletMain$MessageConnection_PushRegistryConnectionWrapper(MessageConnection impl) {
      this._messageConnection = impl;
   }
}
