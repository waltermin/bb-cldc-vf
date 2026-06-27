package net.rim.device.api.ui;

import net.rim.device.api.system.Display;
import net.rim.device.internal.ui.BackingStore;

class UiEngineImpl$ProxyScreen extends Screen {
   Screen _screen;

   public UiEngineImpl$ProxyScreen(Screen var1) {
      super((Manager)(new Object()), !var1.isTransparent() && !var1.isTransparentBorder() ? 0 : 68719476736L);
      this._screen = var1;
      this.setAcceptsInput(this._screen.acceptsInput());
   }

   public Screen getWrappedScreen() {
      return this._screen;
   }

   public void updateInvalid() {
      BackingStore var1 = this._screen.getBackingStore();
      if (var1 != null) {
         XYRect var2 = Ui.getTmpXYRect();
         var1.getTotalDirty(var2);
         XYRect var3 = this._screen.getExtent();
         var2.translate(var3.x, var3.y);
         this.getInvalid().unionNoEmpty(var2);
         Ui.returnTmpXYRect(var2);
      }
   }

   @Override
   public boolean isGlobal() {
      return this._screen.isGlobal();
   }

   @Override
   protected void paint(Graphics var1) {
      BackingStore var2 = this._screen.getBackingStore();
      if (var2 != null && !this.shouldNotPaint()) {
         int var3 = this._screen.getExtent().x - var1.getTranslateX();
         int var4 = this._screen.getExtent().y - var1.getTranslateY();
         if (var3 <= 0 && var4 <= 0) {
            var2.paint(var1, 0, 0);
         } else {
            var1.pushContext(var1.getClippingRect(), var3, var4);
            var2.paint(var1, 0, 0);
            var1.popContext();
         }
      }
   }

   private boolean shouldNotPaint() {
      return !this._screen.isGlobal()
         && this._screen.getExtent().width == Display.getWidth()
         && this._screen.getExtent().height == Display.getHeight()
         && !this._screen.isBackingStoreUpdated();
   }

   @Override
   protected void paintBackground(Graphics var1) {
   }

   @Override
   protected void sublayout(int var1, int var2) {
      XYRect var3 = this._screen.getExtent();
      this.setExtent(var3.width, var3.height);
      this.setPosition(var3.x, var3.y);
   }
}
