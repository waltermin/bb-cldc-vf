package net.rim.device.api.ui.theme;

import net.rim.device.api.lowmemory.LowMemoryListener;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.internal.ui.RegistryListener;
import net.rim.device.internal.ui.UiOptionsRegistry;

class ThemeManager$Listeners implements GlobalEventListener, LowMemoryListener, RegistryListener {
   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -4232371946002803201L) {
         Theme var7 = ThemeManager._instance._activeTheme;
         if (var7 != null) {
            var7.clearAppIconCache();
         }
      }
   }

   @Override
   public boolean freeStaleObject(int var1) {
      boolean var2 = false;
      if (var1 == 0) {
         Theme var3 = ThemeManager._instance._activeTheme;
         if (var3 != null) {
            var2 = var3.freeStaleObject(var1);
         }
      }

      return var2;
   }

   @Override
   public void registryChanged() {
      this.updateThemeFromRegistry(true);
   }

   private void updateThemeFromRegistry(boolean var1) {
      String var2 = UiOptionsRegistry.getInstance().getString(-7276267599751932452L);
      if (var2 != null && !var2.equals(ThemeManager._instance._activeThemeName)) {
         int var3 = ThemeManager.getIndex(var2);
         if (var3 == -1 || var3 == 0 && Graphics.isColor()) {
            var2 = null;
         }

         ThemeManager.setActiveTheme(var2, var1);
      }
   }

   @Override
   public void registryChanged(long var1) {
      if (var1 == -7276267599751932452L) {
         this.updateThemeFromRegistry(false);
      }
   }
}
