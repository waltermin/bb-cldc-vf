package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontRegistry;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UserInputEventListener;
import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.ui.XYRect;

class Tooltip$TooltipScreen extends Screen implements UserInputEventListener {
   private Tooltip _tooltip;
   private final Tooltip this$0;

   public void setTooltip(Tooltip var1) {
      this._tooltip = var1;
      HorizontalFieldManager var2 = (HorizontalFieldManager)this.getDelegate();
      if (var2.getFieldCount() != 0) {
         var2.delete(var2.getField(0));
      }

      var2.add(var1._content);
   }

   @Override
   public void onUserInput(int var1, int var2) {
      this.this$0.dismiss();
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1) {
         Ui.getUiEngine().addUserInputEventListener(this);
         this.this$0._popScreenRunnable.init();
      } else {
         Ui.getUiEngine().removeUserInputEventListener(this);
      }
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      int var1 = Ui.convertSize(FontRegistry.DEFAULT_SIZE, 3, 0);
      this.setFont(null);
      Font var2 = this.getFont();
      if (var2.getHeight() > var1) {
         this.setFont(var2.derive(var2.getStyle(), var1));
      }
   }

   public Tooltip$TooltipScreen(Tooltip var1) {
      super((Manager)(new Object()));
      this.this$0 = var1;
      this.setTag(Tooltip.TAG);
      this.setAcceptsInput(false);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      var1 -= this.getMarginLeft() + this.getMarginRight();
      var2 -= this.getMarginTop() + this.getMarginBottom();
      this.setPositionDelegate(0, 0);
      this.layoutDelegate(var1, var2);
      XYRect var3 = this.getDelegate().getExtent();
      this.setExtent(var3.width, var3.height);
      XYPoint var4 = this._tooltip.getPosition();
      this.setPosition(var4.x, var4.y);
   }
}
