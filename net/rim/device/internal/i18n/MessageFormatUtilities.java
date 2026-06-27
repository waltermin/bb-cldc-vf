package net.rim.device.internal.i18n;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.vm.Array;

public final class MessageFormatUtilities {
   public static final RichTextField getBoldArgumentField(String var0, String[] var1) {
      return getBoldArgumentField(var0, var1, 45035996273704960L);
   }

   public static final RichTextField getBoldArgumentField(String var0, String[] var1, long var2) {
      int[] var4 = new int[0];
      byte[] var5 = new byte[0];
      Font[] var6 = new Font[0];
      String var7 = getParametersForBoldArgumentField(var0, var1, var4, var5, var6);
      return (RichTextField)(new Object(var7, var4, var5, var6, var2));
   }

   public static final String getParametersForBoldArgumentField(String var0, String[] var1, int[] var2, byte[] var3, Font[] var4) {
      if (var4 == null) {
         throw new Object();
      }

      Array.resize(var4, 2);
      var4[0] = Font.getDefault();
      var4[1] = Font.getDefault().derive(1);
      return getParametersForField(var0, var1, var2, var3);
   }

   private static final String getParametersForField(String var0, String[] var1, int[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
