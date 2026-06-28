package net.rim.device.internal.i18n;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.vm.Array;

public final class MessageFormatUtilities {
   public static final RichTextField getBoldArgumentField(String pattern, String[] arguments) {
      return getBoldArgumentField(pattern, arguments, 45035996273704960L);
   }

   public static final RichTextField getBoldArgumentField(String pattern, String[] arguments, long richTextStyle) {
      int[] offsets = new int[0];
      byte[] attributes = new byte[0];
      Font[] fonts = new Font[0];
      String message = getParametersForBoldArgumentField(pattern, arguments, offsets, attributes, fonts);
      return (RichTextField)(new Object(message, offsets, attributes, fonts, richTextStyle));
   }

   public static final String getParametersForBoldArgumentField(String pattern, String[] arguments, int[] offsets, byte[] attributes, Font[] fonts) {
      if (fonts == null) {
         throw new Object();
      }

      Array.resize(fonts, 2);
      fonts[0] = Font.getDefault();
      fonts[1] = Font.getDefault().derive(1);
      return getParametersForField(pattern, arguments, offsets, attributes);
   }

   private static final String getParametersForField(String pattern, String[] arguments, int[] offsets, byte[] attributes) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
