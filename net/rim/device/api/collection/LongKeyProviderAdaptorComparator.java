package net.rim.device.api.collection;

import net.rim.device.api.util.Comparator;
import net.rim.vm.Memory;

public class LongKeyProviderAdaptorComparator implements Comparator {
   private LongKeyProviderAdaptor _longKeyProviderAdaptor;

   public LongKeyProviderAdaptorComparator(LongKeyProviderAdaptor var1) {
      this._longKeyProviderAdaptor = var1;
   }

   @Override
   public int compare(Object var1, Object var2) {
      long var3 = this._longKeyProviderAdaptor.getLongKey(var1);
      long var5 = this._longKeyProviderAdaptor.getLongKey(var2);
      if (var3 < var5) {
         return -1;
      } else if (var3 > var5) {
         return 1;
      } else {
         return var1 == var2 ? 0 : Memory.objectToInt(var1) - Memory.objectToInt(var2);
      }
   }
}
