package net.rim.device.api.ui;

class Screen$ScreenFieldVisitor implements FieldVisitor {
   private int _type;
   private final Screen this$0;

   Screen$ScreenFieldVisitor(Screen var1, int var2) {
      this.this$0 = var1;
      this._type = var2;
   }

   @Override
   public boolean visit(Field var1, int var2) {
      if (var2 != 1) {
         return true;
      }

      switch (this._type) {
         case 0:
            this.this$0.assertLayoutComplete();
         default:
            return true;
      }
   }
}
