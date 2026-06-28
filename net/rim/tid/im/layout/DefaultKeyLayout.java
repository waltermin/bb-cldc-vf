package net.rim.tid.im.layout;

import java.io.InputStream;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.ui.Keypad;

public class DefaultKeyLayout {
   private String[] MAP_LOCATIONS;
   private SLKeyLayout _layout;
   private static final long REGISTRY_NAME;
   private static DefaultKeyLayout _instance;

   private DefaultKeyLayout() {
   }

   private synchronized void init() {
      String keyboardType = SLKeyLayout.getKeyboardType(1701729619);
      int keyboardID = Keypad.getHardwareLayout();
      Locale englishLocale = Locale.get("en", "", "");
      InputStream is = this.getLayoutData(keyboardID, keyboardType, englishLocale, false);
      if (is == null) {
         is = this.getLayoutData(keyboardID, keyboardType, englishLocale, true);
      }

      if (is == null) {
         throw new RuntimeException("Can't find Key Layout for English US locale");
      }

      this._layout = new SLKeyLayout(englishLocale, false, (byte)0, is);
   }

   private InputStream getLayoutData(int aKeyboardId, String aKeyboardType, Locale anInputLocale, boolean useDefault) {
      for (int i = 0; i < this.MAP_LOCATIONS.length; i++) {
         InputStream is = SLKeyLayout.getLayoutData(aKeyboardId, aKeyboardType, anInputLocale, this.MAP_LOCATIONS[i], useDefault);
         if (is != null) {
            return is;
         }
      }

      return null;
   }

   public static SLKeyLayout getDefaultKeyLayout() {
      return _instance._layout;
   }
}
