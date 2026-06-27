package net.rim.device.api.util;

public final class InvertedOrderComparator implements Comparator {
   private Comparator _comparator;

   public InvertedOrderComparator(Comparator var1) {
      this._comparator = var1;
   }

   @Override
   public final int compare(Object var1, Object var2) {
      return this._comparator.compare(var2, var1);
   }
}
