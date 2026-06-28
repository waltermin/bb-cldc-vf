package net.rim.tid.im.layout;

import java.io.InputStream;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.GlobalEventListener;

public class UILocaleKeyLayout implements GlobalEventListener {
   private String[] MAP_LOCATIONS;
   private SLKeyLayout _layout;
   private Locale _lastLocaleUsed;
   private static final long REGISTRY_NAME;
   private static UILocaleKeyLayout _instance;

   private UILocaleKeyLayout() {
   }

   public synchronized void init() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public synchronized void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      if (guid == -7464003439710973532L) {
         this.init();
      }
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

   public static SLKeyLayout getUIKeyLayout() {
      return _instance._layout;
   }
}
