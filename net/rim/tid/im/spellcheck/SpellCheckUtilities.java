package net.rim.tid.im.spellcheck;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.Field;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.itie.IMManager;

public class SpellCheckUtilities implements SpellCheckConstants {
   public static boolean isSpellCheckable(Field var0) {
      if (var0.isSpellCheckable() && var0.isEditable()) {
         long var1 = var0.getStyle();
         int var3 = (int)(var1 >> 24 & 31);
         switch (var3) {
            case -1:
            case 1:
               return false;
            case 0:
            case 2:
            case 3:
            default:
               return true;
         }
      } else {
         return false;
      }
   }

   public static boolean isSpellCheckAvailable(Locale var0) {
      InputContext var1 = InputContext.getInstance();
      IMManager var2 = var1.getInputMethodsManager();
      var0 = createSpellCheckingLocale(var0);
      return var0 == null ? false : var2.getInputMethodIDForLocale(var0) == 8192;
   }

   public static boolean isSpellCheckAvailable() {
      InputContext var0 = InputContext.getInstance();
      Locale var1 = var0.getLocale();
      return isSpellCheckAvailable(var1);
   }

   public static boolean activateSpellCheckIM() {
      InputContext var0 = InputContext.getInstance();
      Locale var1 = var0.getLocale();
      Locale var2 = createSpellCheckingLocale(var1);
      return var2 == null ? false : var0.selectInputMethod(var2);
   }

   public static void deactivateSpellCheckIM() {
      InputContext var0 = InputContext.getInstance();
      SLControlObject var1 = (SLControlObject)var0.getInputMethodControlObject();
      if (var1 != null) {
         var1.actionPerformed(42, null);
      }
   }

   public static Locale createSpellCheckingLocale(Locale var0) {
      String var1 = createSpellCheckingVariant(var0.getVariant());
      var0 = Locale.get(var0.getLanguage(), var0.getCountry(), var1);
      return isSpellCheckVariant(var0) ? var0 : null;
   }

   public static Locale removeSpellCheckingLocale(Locale var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static int spellCheckActionPerformed(int var0, Object var1) {
      InputMethod var2 = getSpellCheckInputMethod();
      return var2 == null ? 4 : var2.actionPerformed(null, var0, var1);
   }

   public static byte[] getIMProperties() {
      InputMethod var0 = getSpellCheckInputMethod();
      return var0 == null ? null : var0.getIMProperties((byte)2);
   }

   public static void setIMProperties(byte[] var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean isSpellCheckVariant(Locale var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static String createSpellCheckingVariant(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static InputMethod getSpellCheckInputMethod() {
      InputContext var0 = InputContext.getInstance();
      IMManager var1 = var0.getInputMethodsManager();
      Locale var2 = createSpellCheckingLocale(var0.getLocale());
      if (var2 == null) {
         return null;
      }

      InputMethod var3 = var1.getInputMethod(var2);
      Locale var4 = removeSpellCheckingLocale(var2);
      var1.getInputMethod(var4);
      return var3;
   }
}
