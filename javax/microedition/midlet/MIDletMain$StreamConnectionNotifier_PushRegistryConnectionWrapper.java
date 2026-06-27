package javax.microedition.midlet;

import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

class MIDletMain$StreamConnectionNotifier_PushRegistryConnectionWrapper implements StreamConnectionNotifier {
   protected StreamConnectionNotifier _scn;
   protected StreamConnection _streamConnection;

   @Override
   public void close() {
      this._scn.close();
   }

   @Override
   public StreamConnection acceptAndOpen() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void pushBack(StreamConnection var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public MIDletMain$StreamConnectionNotifier_PushRegistryConnectionWrapper(StreamConnectionNotifier var1) {
      this._scn = var1;
   }
}
