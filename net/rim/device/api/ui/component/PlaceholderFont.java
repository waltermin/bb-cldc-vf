package net.rim.device.api.ui.component;

import java.io.IOException;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.FontRegistry;

class PlaceholderFont {
   private static final byte[] _template;

   public static Font getFont(int height) {
      if (height >= 0 && height <= 100) {
         int registered = FontFamily.UNKNOWN_FONT;

         try {
            registered = FontRegistry.getInstance().getTypefaceType("ph");
         } catch (IOException var3) {
         }

         if (registered == FontFamily.UNKNOWN_FONT) {
            FontRegistry.loadFont(_template, "ph", false);
         }

         return FontRegistry.get("ph").getFont(0, height);
      } else {
         throw new IllegalArgumentException("");
      }
   }
}
