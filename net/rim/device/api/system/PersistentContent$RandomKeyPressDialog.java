package net.rim.device.api.system;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.GaugeField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.component.PopupDialog;

final class PersistentContent$RandomKeyPressDialog extends PopupDialog {
   GaugeField _gaugeField;
   int _numBitsNeeded;
   int _numBitsObtained;

   public PersistentContent$RandomKeyPressDialog(String var1, int var2, long var3) {
      super((Manager)(new Object(1153220571769602048L)), var3);
      Object var5 = this.getDelegate();
      this._numBitsNeeded = var2;
      ((VerticalFieldManager)var5).add((Field)(new Object(var1, 45035996273704960L)));
      this._gaugeField = (GaugeField)(new Object(CommonResource.getString(10017), 0, var2, 0, 0));
      ((VerticalFieldManager)var5).add(this._gaugeField);
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      return this.increaseBits(4);
   }

   @Override
   protected final boolean navigationClick(int var1, int var2) {
      return this.increaseBits(1);
   }

   @Override
   public final boolean navigationMovement(int var1, int var2, int var3, int var4) {
      return var1 == 0 && var2 == 0 ? false : this.increaseBits(1);
   }

   @Override
   public final boolean navigationUnclick(int var1, int var2) {
      return false;
   }

   private final boolean increaseBits(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
