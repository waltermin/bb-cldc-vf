package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.Arrays;

public final class SystemPropertyManager {
   private SystemPropertyProvider[] _providers = new SystemPropertyProvider[0];
   private static final long GUID;

   private SystemPropertyManager() {
   }

   public static final SystemPropertyManager getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      if (var0 == null) {
         return null;
      }

      SystemPropertyManager var1 = (SystemPropertyManager)var0.getOrWaitFor(-6763663770985155769L);
      if (var1 == null) {
         var1 = new SystemPropertyManager();
         var0.put(-6763663770985155769L, var1);
      }

      return var1;
   }

   public final void addProvider(SystemPropertyProvider var1) {
      Arrays.add(this._providers, var1);
   }

   public final String getProperty(String var1) {
      for (int var2 = this._providers.length - 1; var2 >= 0; var2--) {
         String var3 = this._providers[var2].getProperty(var1);
         if (var3 != null) {
            return var3;
         }
      }

      return null;
   }
}
