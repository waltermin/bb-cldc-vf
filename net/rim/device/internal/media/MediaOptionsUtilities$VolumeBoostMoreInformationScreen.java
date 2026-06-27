package net.rim.device.internal.media;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.internal.util.OptionsRegistry$Listener;

final class MediaOptionsUtilities$VolumeBoostMoreInformationScreen extends MainScreen implements OptionsRegistry$Listener {
   private boolean _closeWhenVisible;

   MediaOptionsUtilities$VolumeBoostMoreInformationScreen() {
      MediaOptionsRegistry.getInstance().addOptionsRegistryChangeListener(this);
   }

   @Override
   protected final boolean invokeAction(int var1) {
      if (var1 == 1 && this.shouldInvoke()) {
         this.invokeDefaultMenuItem(0);
      }

      return true;
   }

   private final boolean shouldInvoke() {
      boolean var1 = false;
      MenuItem var2 = this.getDefaultMenuItem(0);
      if (!(var2 instanceof Object)) {
         var1 = true;
      }

      return var1;
   }

   @Override
   public final void onOptionsRegistryChange(long var1) {
      if (var1 == 2886183832722201160L) {
         this._closeWhenVisible = true;
      }
   }

   @Override
   protected final void onUndisplay() {
      MediaOptionsRegistry.getInstance().removeOptionsRegistryChangeListener(this);
      super.onUndisplay();
   }

   @Override
   protected final void onExposed() {
      if (this._closeWhenVisible) {
         this._closeWhenVisible = false;
         this.close();
      }
   }
}
