package javax.microedition.lcdui;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;

class MIDPAlert extends MIDPScreen {
   private AlertType _alertType;
   private static final int SCALE_FACTOR;
   private static final int INSIDE_SPACE;

   MIDPAlert(Manager var1, AlertType var2) {
      super(var1);
      this._alertType = var2;
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      ((Alert)this.getDisplayable()).dismiss();
      return true;
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this.trackwheelClick(var3, var4);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = false;
      if (var1 == '\n' || var1 == 27 || var1 == ' ' || var1 == '\b') {
         ((Alert)this.getDisplayable()).dismiss();
         var4 = true;
      }

      if (!var4) {
         var4 = super.keyChar(var1, var2, var3);
      }

      return var4;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var3 = var2;
      Manager var4 = this.getDelegate();
      if (super._ticker != null) {
         super._ticker.setStuff(this.getFont());
         var2 -= super._ticker.getHeight();
      }

      int var5 = var1 / 10 * 9 - 4;
      int var6 = var2 / 10 * 9 - 4;
      this.layoutDelegate(var5, var6);
      int var7 = var1 - var4.getWidth() >> 1;
      int var8 = var2 - var4.getHeight() >> 1;
      this.setPositionDelegate(var7, var8);
      this.setPosition(0, 0);
      this.setExtent(var1, var3);
   }

   @Override
   protected void paint(net.rim.device.api.ui.Graphics var1) {
      XYRect var2 = this.getDelegate().getExtent();
      var1.drawRect(var2.x - 2, var2.y - 2, var2.width + 4, var2.height + 4);
      super.paint(var1);
   }
}
