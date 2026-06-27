package net.rim.device.api.util;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.RuntimeStore;

public final class FactoryUtil {
   private FactoryUtil() {
   }

   public static final Object createInstance(long var0, Object var2) {
      if (var0 != 0 && var0 != -1 && var0 != Long.MAX_VALUE && var0 != Long.MIN_VALUE) {
         Factory var3 = (Factory)ApplicationRegistry.getApplicationRegistry().waitFor(var0);
         if (var3 == null) {
            var3 = (Factory)RuntimeStore.getRuntimeStore().waitFor(var0);
         }

         return var3.createInstance(var2);
      } else {
         return null;
      }
   }
}
