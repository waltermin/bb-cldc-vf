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

   final void pushBack(Message var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final Message newMessage(String var1, String var2) {
      return this._messageConnection.newMessage(var1, var2);
   }

   @Override
   public final int numberOfSegments(Message var1) {
      return this._messageConnection.numberOfSegments(var1);
   }

   @Override
   public final Message receive() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void send(Message var1) {
      this._messageConnection.send(var1);
   }

   @Override
   public final void setMessageListener(MessageListener var1) {
      this._messageConnection.setMessageListener(var1);
   }

   @Override
   public final Message newMessage(String var1) {
      return this._messageConnection.newMessage(var1);
   }

   public MIDletMain$MessageConnection_PushRegistryConnectionWrapper(MessageConnection var1) {
      this._messageConnection = var1;
   }
}
