package net.rim.device.api.ui;

import net.rim.device.internal.ui.UiInternal;
import net.rim.tid.im.layout.DefaultKeyLayout;
import net.rim.tid.im.layout.SLKeyLayout;
import net.rim.tid.im.layout.UILocaleKeyLayout;

public class KeypadUtil {
   public static final int MODE_UI_CURRENT_LOCALE;
   public static final int MODE_EN_LOCALE;

   private KeypadUtil() {
   }

   public static char getKeyChar(int var0, int var1) {
      switch (var1) {
         case -1:
            throw new Object();
         case 0:
         default:
            return UiInternal.map(var0);
         case 1:
            return UiInternal.mapFromFallbackLayout(var0);
      }
   }

   public static int getKeyCode(char var0, int var1, int var2) {
      SLKeyLayout var3 = null;
      switch (var2) {
         case -1:
            throw new Object();
         case 0:
         default:
            var3 = UILocaleKeyLayout.getUIKeyLayout();
            break;
         case 1:
            var3 = DefaultKeyLayout.getDefaultKeyLayout();
      }

      return var3 == null ? 0 : var3.getOriginalKeyCode(var0, SLKeyLayout.convertStatusToModifiers(var1)) << 16 | var1;
   }
}
