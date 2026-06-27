package javax.microedition.global;

import net.rim.device.api.util.Arrays;

public final class StringComparator {
   private int _level;
   private String _locale;
   public static final int IDENTICAL;
   public static final int LEVEL1;
   public static final int LEVEL2;
   public static final int LEVEL3;

   public StringComparator() {
   }

   public StringComparator(String var1) {
      this(var1, 1);
   }

   public StringComparator(String var1, int var2) {
   }

   public final int compare(String var1, String var2) {
      if (var1 == null || var2 == null) {
         throw new Object();
      }

      if (this._locale == null) {
         return var1.compareTo(var2);
      }

      switch (this._level) {
         case 3:
            int var5 = this.compare(var1, var2, 1);
            if (var5 == 0) {
               var5 = this.compare(var1, var2, 2);
               if (var5 == 0) {
                  var5 = this.compare(var1, var2, 3);
               }
            }

            return var5;
         case 15:
            int var3 = this.compare(var1, var2, 15);
         case 2:
            int var4 = this.compare(var1, var2, 1);
            if (var4 == 0) {
               var4 = this.compare(var1, var2, 2);
            }

            return var4;
         default:
            return this.compare(var1, var2, 1);
      }
   }

   private final int compare(String var1, String var2, int var3) {
      switch (this._level) {
         default:
            return var1.compareTo(var2);
      }
   }

   public final boolean equals(String var1, String var2) {
      return this.compare(var1, var2) == 0;
   }

   public final int getLevel() {
      return this._level;
   }

   public final String getLocale() {
      return this._locale;
   }

   public static final String[] getSupportedLocales() {
      return new String[0];
   }

   private final boolean isSupportedLocale(String var1) {
      return Arrays.contains(getSupportedLocales(), var1);
   }
}
