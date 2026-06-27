package net.rim.device.internal.media;

import net.rim.device.api.system.AudioRouter;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.DialogClosedListener;
import net.rim.device.internal.util.OptionsRegistry$Listener;

final class MediaOptionsUtilities$VolumeBoostDialog extends Dialog implements DialogClosedListener, OptionsRegistry$Listener {
   private boolean _closeWhenVisible;
   private static final String[] _options;
   private static final int[] _values;

   private final void createDialog() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void dialogClosed(Dialog var1, int var2) {
      MediaOptionsRegistry var3 = MediaOptionsRegistry.getInstance();
      var3.removeOptionsRegistryChangeListener(this);
      boolean var4 = false;
      if (var2 == 4) {
         var3.setInt(-811168513825316359L, MediaOptionsUtilities.computeCurrentImsiValue());
         var4 = true;
      }

      var3.setBoolean(2886183832722201160L, var4);
      AudioRouter.getInstance().setVolumeBoostMode(var4);
      if (this.isDontAskAgainChecked()) {
         var3.setBoolean(-4387502259448276168L, true);
      } else {
         var3.setBoolean(-4387502259448276168L, false);
      }
   }

   @Override
   public final void fieldChanged(Field var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void onOptionsRegistryChange(long var1) {
      if (var1 == 2886183832722201160L) {
         MediaOptionsRegistry.getInstance().removeOptionsRegistryChangeListener(this);
         this.setDialogClosedListener(null);
         this._closeWhenVisible = true;
      }
   }

   @Override
   protected final void onExposed() {
      if (this._closeWhenVisible) {
         this._closeWhenVisible = false;
         this.close();
      }
   }
}
