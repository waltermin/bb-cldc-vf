package net.rim.tid.im.spellcheck;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.Field;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.spi.InputMethod;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.itie.IMManager;

public class SpellCheckUtilities implements SpellCheckConstants {
   public static boolean isSpellCheckable(Field field) {
      if (field.isSpellCheckable() && field.isEditable()) {
         long style = field.getStyle();
         int textFilterType = (int)(style >> 24 & 31);
         switch (textFilterType) {
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

   public static boolean isSpellCheckAvailable(Locale locale) {
      InputContext ic = InputContext.getInstance();
      IMManager imMgr = ic.getInputMethodsManager();
      locale = createSpellCheckingLocale(locale);
      return locale == null ? false : imMgr.getInputMethodIDForLocale(locale) == 8192;
   }

   public static boolean isSpellCheckAvailable() {
      InputContext ic = InputContext.getInstance();
      Locale currLocale = ic.getLocale();
      return isSpellCheckAvailable(currLocale);
   }

   public static boolean activateSpellCheckIM() {
      InputContext ic = InputContext.getInstance();
      Locale currLocale = ic.getLocale();
      Locale spl = createSpellCheckingLocale(currLocale);
      return spl == null ? false : ic.selectInputMethod(spl);
   }

   public static void deactivateSpellCheckIM() {
      InputContext ic = InputContext.getInstance();
      SLControlObject co = (SLControlObject)ic.getInputMethodControlObject();
      if (co != null) {
         co.actionPerformed(42, null);
      }
   }

   public static Locale createSpellCheckingLocale(Locale locale) {
      String variant = createSpellCheckingVariant(locale.getVariant());
      locale = Locale.get(locale.getLanguage(), locale.getCountry(), variant);
      return isSpellCheckVariant(locale) ? locale : null;
   }

   public static Locale removeSpellCheckingLocale(Locale locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int spellCheckActionPerformed(int action, Object param) {
      InputMethod spellCheckInputMethod = getSpellCheckInputMethod();
      return spellCheckInputMethod == null ? 4 : spellCheckInputMethod.actionPerformed(null, action, param);
   }

   public static byte[] getIMProperties() {
      InputMethod spellCheckInputMethod = getSpellCheckInputMethod();
      return spellCheckInputMethod == null ? null : spellCheckInputMethod.getIMProperties((byte)2);
   }

   public static void setIMProperties(byte[] props) {
      InputMethod spellCheckInputMethod = getSpellCheckInputMethod();
      if (spellCheckInputMethod != null) {
         spellCheckInputMethod.setIMProperties((byte)2, props);

         try {
            RIMGlobalMessagePoster.postGlobalEvent(8906172480279495146L);
         } catch (IllegalStateException var3) {
         }
      }
   }

   public static boolean isSpellCheckVariant(Locale locale) {
      if (locale == null) {
         return false;
      }

      String variant = locale.getVariant();
      return variant != null && variant.indexOf("SpellCheck") != -1;
   }

   public static String createSpellCheckingVariant(String oldVariant) {
      return "SpellCheck";
   }

   public static InputMethod getSpellCheckInputMethod() {
      InputContext ic = InputContext.getInstance();
      IMManager imMgr = ic.getInputMethodsManager();
      Locale scLocale = createSpellCheckingLocale(ic.getLocale());
      if (scLocale == null) {
         return null;
      }

      InputMethod im = imMgr.getInputMethod(scLocale);
      Locale replacedLocale = removeSpellCheckingLocale(scLocale);
      imMgr.getInputMethod(replacedLocale);
      return im;
   }
}
