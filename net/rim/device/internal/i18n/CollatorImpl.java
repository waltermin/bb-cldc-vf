package net.rim.device.internal.i18n;

public final class CollatorImpl {
   private int _decomposition;
   private int _strength;

   public final int compare(String var1, String var2) {
      if (var1 == null) {
         return var2 == null ? 0 : -1;
      } else {
         return var2 == null ? 1 : this.compare(var1, var2, Integer.MAX_VALUE);
      }
   }

   public final int compare(Object var1, Object var2) {
      if (var1 == null) {
         return var2 == null ? 0 : -1;
      } else {
         return var2 == null ? 1 : this.compare(var1.toString(), var2.toString(), Integer.MAX_VALUE);
      }
   }

   public final native int compare(String var1, String var2, int var3);

   public final boolean equals(String var1, String var2) {
      return this.compare(var1, var2) == 0;
   }
}
