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

   private InputStream getLayoutData(int var1, String var2, Locale var3, boolean var4) {
      for (int var5 = 0; var5 < this.MAP_LOCATIONS.length; var5++) {
         InputStream var6 = SLKeyLayout.getLayoutData(var1, var2, var3, this.MAP_LOCATIONS[var5], var4);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   public static SLKeyLayout getDefaultKeyLayout() {
      return _instance._layout;
   }
}
