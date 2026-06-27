package net.rim.device.internal.ui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.RichTextField;

public final class RichTextFieldUtilities {
   private static final char MODIFICATION_FLAG;
   private static final byte FONT_REGULAR;
   private static final byte FONT_MODIFIED;

   public static final RichTextField getBoldFormattedRichTextField(String var0) {
      return getBoldFormattedRichTextField(var0, 0);
   }

   public static final RichTextField getBoldFormattedRichTextField(String var0, long var1) {
      Font var3 = Font.getDefault();
      Font[] var4 = new Font[]{null, var3.derive(var3.getStyle() | 1)};
      return getRichTextField(var0, var1, var4);
   }

   private static final RichTextField getRichTextField(String var0, long var1, Font[] var3) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
