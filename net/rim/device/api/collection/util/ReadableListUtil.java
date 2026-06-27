package net.rim.device.api.collection.util;

import net.rim.device.api.collection.ReadableList;

public class ReadableListUtil {
   public static int getAt(int var0, int var1, Object[] var2, int var3, ReadableList var4) {
      int var5 = var4.size();
      if (var1 < 0) {
         var1 = 0;
      } else if (var0 >= var5) {
         var1 = 0;
      } else if (var0 + var1 > var5) {
         var1 = var5 - var0;
      }

      for (int var6 = var1; var6 != 0; var6--) {
         var2[var3++] = var4.getAt(var0++);
      }

      return var1;
   }

   public static int getIndex(Object var0, ReadableList var1) {
      int var2 = var1.size();

      for (int var3 = 0; var3 < var2; var3++) {
         if (var1.getAt(var3) == var0) {
            return var3;
         }
      }

      return -1;
   }
}
