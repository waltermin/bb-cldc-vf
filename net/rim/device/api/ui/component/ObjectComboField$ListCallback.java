package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

public class ObjectComboField$ListCallback implements ListFieldCallback {
   protected Object[] _choices;
   protected Object[] _subset;
   private final ObjectComboField this$0;

   public ObjectComboField$ListCallback(ObjectComboField var1, Object[] var2) {
      this.this$0 = var1;
      this._subset = new Object[0];
      this._choices = var2;
   }

   public void update(String var1) {
      if (!this.this$0.doFilter()) {
         this._subset = this._choices;
      } else {
         Array.resize(this._subset, 0);
         int var2 = this._choices.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = this._choices[var3].toString();
            if (this.this$0.matches(var4, var1)) {
               Arrays.add(this._subset, this._choices[var3]);
            }
         }
      }
   }

   public int length() {
      return this._subset.length;
   }

   @Override
   public void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      Object var6 = this.get(var1, var3);
      if (var6 != null) {
         String var7 = var6.toString();
         var4 = var1.getAdjustedY(var2.getFont(), var7, var4);
         var2.drawText(var7, 0, Integer.MAX_VALUE, 0, var4, 64, var5);
      }
   }

   @Override
   public int getPreferredWidth(ListField var1) {
      return 0;
   }

   @Override
   public Object get(ListField var1, int var2) {
      return this._subset[var2];
   }

   @Override
   public int indexOfList(ListField var1, String var2, int var3) {
      return -1;
   }
}
