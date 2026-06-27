package net.rim.device.cldc.io.dns;

import java.util.Vector;

class DNSResolverIPv4$DNSResolverIPv4Thread extends Thread {
   private DNSResolverIPv4 _resolver;
   private Vector _queue;

   DNSResolverIPv4$DNSResolverIPv4Thread(DNSResolverIPv4 var1) {
      this._resolver = var1;
      this._queue = (Vector)(new Object());
   }

   public void addRequest(DNSRequest var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
