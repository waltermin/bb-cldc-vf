package net.rim.device.api.ui.container;

class DialogFieldManager$1 extends VerticalFieldManager {
   private final DialogFieldManager this$0;

   DialogFieldManager$1(DialogFieldManager var1, long var2) {
      super(var2);
      this.this$0 = var1;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      super.sublayout(var1, var2);
      this.setVerticalQuantization(1);
   }
}
