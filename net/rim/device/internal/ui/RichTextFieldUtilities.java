package net.rim.device.internal.ui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.RichTextField;

public final class RichTextFieldUtilities {
   private static final char MODIFICATION_FLAG;
   private static final byte FONT_REGULAR;
   private static final byte FONT_MODIFIED;

   public static final RichTextField getBoldFormattedRichTextField(String text) {
      return getBoldFormattedRichTextField(text, 0);
   }

   public static final RichTextField getBoldFormattedRichTextField(String text, long style) {
      Font defaultFont = Font.getDefault();
      Font[] fontFormats = new Font[]{null, defaultFont.derive(defaultFont.getStyle() | 1)};
      return getRichTextField(text, style, fontFormats);
   }

   private static final RichTextField getRichTextField(String text, long style, Font[] fontFormats) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
