package net.rim.device.api.i18n;

import net.rim.device.api.util.Comparator;

class Locale$Locales$1 implements Comparator {
   private final Locale$Locales this$0;

   Locale$Locales$1(Locale$Locales var1) {
      this.this$0 = var1;
   }

   @Override
   public int compare(Object var1, Object var2) {
      Locale var3 = (Locale)var1;
      Locale var4 = (Locale)var2;
      int var5 = var3.getCode() - var4.getCode();
      if (var5 == 0 && (var3.getVariant() != Locale.EMPTY || var4.getVariant() != Locale.EMPTY)) {
         var5 = var3.getVariant().compareTo(var4.getVariant());
      }

      return var5;
   }

   @Override
   public boolean equals(Object var1) {
      return false;
   }
}
