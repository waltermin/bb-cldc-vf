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
   public synchronized void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -7464003439710973532L) {
         this.init();
      }
   }

   private InputStream getLayoutData(int var1, String var2, Locale var3, boolean var4) {
      for (int var5 = 0; var5 < this.MAP_LOCATIONS.length; var5++) {
         InputStream var6 = SLKeyLayout.getLayoutData(var1, var2, var3, this.MAP_LOCATIONS[var5], var4);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   public static SLKeyLayout getUIKeyLayout() {
      return _instance._layout;
   }
}
