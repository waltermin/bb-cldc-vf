package net.rim.device.api.io;

import java.util.Hashtable;
import javax.microedition.io.Connection;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.CyclicQueue;
import net.rim.device.cldc.io.daemon.ProtocolDaemon;

final class DatagramReceiveThread extends Thread implements ConnectionListener {
   private Hashtable _connections = (Hashtable)(new Object());
   private CyclicQueue _queue = (CyclicQueue)(new Object(32));
   private static final long ID;

   public static final DatagramReceiveThread getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      DatagramReceiveThread var1 = (DatagramReceiveThread)var0.getOrWaitFor(-4931091334314128113L);
      if (var1 == null) {
         var1 = new DatagramReceiveThread();
         ProtocolDaemon.getInstance().startThread(var1);
         var0.put(-4931091334314128113L, var1);
      }

      return var1;
   }

   public final void addConnection(DatagramConnectionBase var1, DatagramTransportBase var2) {
      this._connections.put(var1, var2);
      var1.setConnectionListener(this);
   }

   @Override
   public final void dataAvailable(Connection var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
