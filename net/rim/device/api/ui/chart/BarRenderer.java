package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.util.MathUtilities;

public class BarRenderer extends ChartRenderer {
   private int _barWidth = 20;
   private int _barPadding = 5;
   private boolean _displayBarValues;
   private int _fillColor;
   private int _strokeColor;

   public BarRenderer(ChartField var1) {
      super(var1);
   }

   @Override
   public int getPreferredWidth() {
      return (this._barWidth + this._barPadding) * this.getDataset().getSize() + this._barPadding;
   }

   @Override
   public int getPreferredHeight() {
      return this.getRangeAxis().getMax();
   }

   @Override
   public void layout(int var1, int var2) {
      this.setExtent(var1, var2);
   }

   @Override
   public void paint(Graphics var1) {
      XYDataset var2 = this.getDataset();
      int var3 = this._barWidth + this._barPadding;
      int var4 = var1.getColor();
      Font var5 = this.getField().getFont();
      int var6 = var5.getHeight();
      XYPoint var7 = new XYPoint();
      int var8 = this.getRangeAxis().getMax();
      int var9 = this.getRangeAxis().getMin();
      int var10 = var8 - var9;
      int var11 = MathUtilities.clamp(-1, var9, 1) == MathUtilities.clamp(-1, var8, 1) ? var9 : 0;
      int var12 = var11 == 0 ? var8 : -var9;
      int var13 = var2.getSize();
      int var14 = 0;

      for (int var15 = 0; var14 < var13; var15 += var3) {
         var2.getPoint(var14, var7);
         int var16 = var7.y;
         int var17 = (var16 - var11) * this.getHeight() / var10;
         int var18;
         if (var17 < 0) {
            var18 = var12;
            var17 = -var17;
         } else {
            var18 = var12 - var17;
         }

         var1.setColor(this._fillColor);
         var1.fillRect(var15, var18, this._barWidth, var17);
         var1.setColor(this._strokeColor);
         var1.drawRect(var15, var18, this._barWidth, var17);
         if (this._displayBarValues) {
            String var19 = Integer.toString(var16);
            if (var16 < 0) {
               var18 += var17;
            } else {
               var18 -= var6;
            }

            var1.setColor(var4);
            var1.drawText(var19, var15, var18, 4, this._barWidth);
         }

         var14++;
      }
   }

   public void setDisplayBarValues(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setFillColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setStrokeColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
