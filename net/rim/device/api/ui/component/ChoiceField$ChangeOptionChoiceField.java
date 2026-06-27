package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;

public class ChoiceField$ChangeOptionChoiceField extends ChoiceField {
   private final ChoiceField this$0;

   public ChoiceField$ChangeOptionChoiceField(ChoiceField var1, String var2, int var3, int var4, long var5) {
      super(var2, var3, var4, var5);
      this.this$0 = var1;
   }

   @Override
   public Object getChoice(int var1) {
      return this.this$0.getChoice(var1);
   }

   @Override
   public int getWidthOfChoice(int var1) {
      return this.this$0.getWidthOfChoice(var1);
   }

   @Override
   public int getHeightOfChoices() {
      return this.this$0.getChoiceLineHeight();
   }

   @Override
   protected void drawChoice(int var1, Graphics var2, int var3, int var4, int var5, int var6) {
      this.this$0.drawChoice(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public Field getOriginal() {
      return this.this$0;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      int var2 = this.getSelectedIndex();
      if (this.isStyle(134217728) && Ui.getIncreaseDirection() == -1) {
         var2 = this.getSize() - var2 - 1;
      }

      int var3 = this.getHeightOfChoices();
      var1.set(0, var2 * var3, this.getContentWidth(), var3);
   }

   @Override
   protected void layout(int var1, int var2) {
      int var3 = this.getHeightOfChoices();
      var2 = var3 * this.getSize();
      var1 = Math.min(var1, this.getWidthOfChoices() + 1);
      this.setExtent(var1, var2);
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = this.getHeightOfChoices();
      super._selectedX = 0;
      if (super._selectedWidth == 0) {
         super._selectedWidth = this.getSelectedWidth();
      }

      int var3 = this.getSize();
      byte var4 = 64;
      switch ((int)((this.getStyle() & 12884901888L) >>> 32)) {
         case -1:
            break;
         case 0:
            if (super._isLabelOwnLine) {
               super._selectedX = 0;
               var4 |= 6;
               break;
            }
         case 2:
            var4 |= 5;
            break;
         case 1:
         default:
            var4 |= 6;
            break;
         case 3:
            var4 |= 4;
      }

      int var5 = this.this$0._lengthOfLongestLine;
      this.this$0._lengthOfLongestLine = 0;
      int var6 = var1.getClippingRect().y / var2;
      int var7 = Math.min(var3, (var1.getClippingRect().Y2() - 1) / var2 + 1);

      for (int var8 = var6; var8 < var7; var8++) {
         int var9 = this.isStyle(134217728) && Ui.getIncreaseDirection() == -1 ? var3 - var8 - 1 : var8;
         this.drawChoice(var9, var1, 0, var8 * var2, var4, this.getContentWidth());
      }

      this.this$0._lengthOfLongestLine = var5;
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      if (this.isEditable() && this.this$0._numChoices > 0) {
         this.moveChoiceFocus(var1);
         return 0;
      } else {
         return super.moveFocus(var1, var2, var3);
      }
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      if (var1 > 0 && var2 > 0 && var1 < this.getContentWidth() && var2 < this.getContentHeight()) {
         int var5 = this.getHeightOfChoices();
         this.setSelectedIndex(var2 / var5, 2);
      }
   }
}
