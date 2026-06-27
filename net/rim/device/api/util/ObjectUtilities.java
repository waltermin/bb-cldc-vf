package net.rim.device.api.util;

public final class ObjectUtilities {
   private ObjectUtilities() {
   }

   public static final boolean objEqual(Object var0, Object var1) {
      if (var0 != null) {
         if (var1 != null) {
            return var0.equals(var1);
         }
      } else if (var1 == null) {
         return true;
      }

      return false;
   }

   public static final boolean classesEqual(Object var0, Object var1) {
      if (var0 == var1) {
         return true;
      }

      if (var0 != null && var1 != null) {
         Class var2 = var0.getClass();
         Class var3 = var1.getClass();
         if (var2 == var3) {
            return true;
         }

         String var4 = var2.getName();
         String var5 = var3.getName();
         return var4.equals(var5);
      } else {
         return false;
      }
   }
}
