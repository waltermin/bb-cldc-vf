package net.rim.device.cldc.io.dns;

import net.rim.device.api.util.Comparator;

class DNSCache$1 implements Comparator {
   private final DNSCache this$0;

   DNSCache$1(DNSCache var1) {
      this.this$0 = var1;
   }

   @Override
   public int compare(Object var1, Object var2) {
      return ((DNSCacheNode)var1).getExpiryTime() - ((DNSCacheNode)var2).getExpiryTime();
   }
}
