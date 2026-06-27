package net.rim.device.api.io;

import javax.microedition.io.Datagram;
import net.rim.device.api.util.CyclicQueue;

final class SendPacketThread extends Thread {
   private CyclicQueue _requests = (CyclicQueue)(new Object(8));

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void addRequest(Object var1, Datagram var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
