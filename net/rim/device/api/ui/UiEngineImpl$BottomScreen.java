package net.rim.device.api.ui;

final class UiEngineImpl$BottomScreen extends Screen {
   public UiEngineImpl$BottomScreen() {
      super(new UiEngineImpl$BottomScreen$BottomScreenManager());
   }

   @Override
   protected final void sublayout(int var1, int var2) {
      this.setPosition(0, 0);
      this.setExtent(var1, var2);
   }

   @Override
   protected final void applyFont() {
   }

   @Override
   protected final void paint(Graphics var1) {
   }
}
