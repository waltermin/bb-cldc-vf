package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYPoint;

public class PieRenderer extends ChartRenderer {
   private int _slicePadding;
   private int _valuesTotal;
   public static final int PADDING_NONE;
   public static final int PADDING_LOW;
   public static final int PADDING_MEDIUM;
   public static final int PADDING_HIGH;

   public PieRenderer(ChartField var1) {
   }

   public int getSlicePadding() {
      return this._slicePadding;
   }

   @Override
   public void layout(int var1, int var2) {
      this.setExtent(var1, var2);
   }

   @Override
   public void paint(Graphics var1) {
      XYDataset var2 = this.getDataset();
      int var3 = 0;
      int var4 = 0;
      int var5 = var2.getSize();
      XYPoint var6 = new XYPoint();
      int var7 = this.getWidth();
      int var8 = this.getHeight();
      this._valuesTotal = 0;

      for (int var9 = 0; var9 < var5; var9++) {
         this._valuesTotal = this._valuesTotal + var2.getY(var9);
      }

      for (int var13 = 0; var13 < var5; var13++) {
         var2.getPoint(var13, var6);
         int var10 = var6.x;
         int var11 = var6.y;
         var1.setColor(var10);
         var3 = var11 * 360 / this._valuesTotal - this._slicePadding;
         if (var3 != 0) {
            var1.fillArc(0, 0, var7, var8, var4, var3);
         }

         var1.setColor(0);
         var1.drawArc(0, 0, var7, var8, var4, var3);
         var4 += var3 + this._slicePadding;
      }
   }

   public void setSlicePadding(int var1) {
      if (this._slicePadding != var1) {
         checkSlicePadding(var1);
         this._slicePadding = var1;
      }
   }

   private static void checkSlicePadding(int var0) {
      switch (var0) {
         case 0:
         case 4:
         case 8:
         case 12:
            return;
         default:
            throw new Object();
      }
   }
}
