package net.rim.tid.im.layout;

import java.io.InputStream;
import net.rim.device.api.i18n.Locale;

public class DefaultKeyLayout {
   private String[] MAP_LOCATIONS;
   private SLKeyLayout _layout;
   private static final long REGISTRY_NAME;
   private static DefaultKeyLayout _instance;

   private DefaultKeyLayout() {
   }

   private synchronized void init() {
      throw new RuntimeException("cod2jar: ldc");
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
