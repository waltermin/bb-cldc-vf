package net.rim.device.api.io;

import javax.microedition.io.Datagram;
import net.rim.device.api.util.CyclicQueue;

final class SendPacketThread extends Thread {
   private CyclicQueue _requests = new CyclicQueue(8);

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void addRequest(Object sendObj, Datagram datagram) {
      synchronized (this._requests) {
         this._requests.enqueue(new SendPacketThread$SPTRequest(sendObj, datagram));
         this._requests.notify();
      }
   }
}
