package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.ui.Field;

class DateField$2 extends DateField {
   private final DateField this$0;

   DateField$2(DateField var1, String var2, long var3, DateFormat var5, long var6) {
      super(var2, var3, var5, var6);
      this.this$0 = var1;
   }

   @Override
   protected void onFocus(int var1) {
      boolean var2 = false;
      if (super._first_focus) {
         var2 = true;
      }

      super.onFocus(var1);
      if (var2) {
         DateField var3 = this.this$0;
         super._position = var3._position;
         super.calcCachedData();
      }
   }

   @Override
   public Field getOriginal() {
      return this.this$0;
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      return super.moveFocus(var1, var2 | 1, var3);
   }
}
