package net.rim.device.api.ui.chart;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.math.VecMath;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYPoint;

public class PointRenderer extends ChartRenderer {
   private boolean _showLines = true;
   private int _lineColor;
   private int _pointSize = 4;
   private int _markerColor;
   private int[] _matrix = new int[9];

   public PointRenderer(ChartField var1) {
      super(var1);
   }

   @Override
   public int getPreferredWidth() {
      return 2 * this._pointSize * this.getDataset().getSize() + this._pointSize;
   }

   @Override
   public int getPreferredHeight() {
      return this.getRangeAxis().getMax();
   }

   public boolean isLinesDisplayed() {
      return this._showLines;
   }

   @Override
   public void layout(int var1, int var2) {
      this.setExtent(var1, var2);
   }

   @Override
   public void paint(Graphics var1) {
      XYDataset var2 = this.getDataset();
      int var3 = this.getRangeAxis().getMax();
      int var4 = this.getRangeAxis().getMin();
      int var5 = this.getDomainAxis().getMax();
      int var6 = this.getDomainAxis().getMin();
      int var7 = 65536 * this.getWidth() / (var5 - var6);
      int var8 = 65536 * this.getHeight() / (var3 - var4);
      VecMath.scale(VecMath.IDENTITY_3X3, 0, var7, -var8, this._matrix);
      VecMath.translate(this._matrix, 0, -65536 * this.getWidth() * var6 / (var5 - var6), 65536 * this.getHeight() * var3 / (var3 - var4), this._matrix);
      var1.setMatrix(this._matrix);
      if (this._showLines) {
         int[] var9 = var2.getXArrayInternal();
         int[] var10 = var2.getYArrayInternal();
         var1.setColor(this._lineColor);
         var1.setStrokeWidth(2);
         var1.drawPathOutline(var9, var10, null, null, false);
         var1.setStrokeWidth(1);
      }

      if (this._pointSize > 0) {
         XYPoint var13 = new XYPoint();
         int var14 = this._pointSize >> 1;
         var1.setColor(this._markerColor);
         int var11 = var2.getSize();

         for (int var12 = 0; var12 < var11; var12++) {
            var2.getPoint(var12, var13);
            var1.fillEllipse32(
               65536 * var13.x,
               65536 * var13.y,
               65536 * var13.x + Fixed32.div(65536 * var14, var7),
               65536 * var13.y,
               65536 * var13.x,
               65536 * var13.y + Fixed32.div(65536 * var14, var8),
               0,
               360
            );
         }
      }

      var1.setMatrix(VecMath.IDENTITY_3X3);
   }

   public void setLineColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setLinesDisplayed(boolean var1) {
      if (this._showLines != var1) {
         this._showLines = var1;
      }
   }

   public void setMarkerColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setPointSize(int var1) {
      if (this._pointSize != var1) {
         if (var1 < 0) {
            throw new Object();
         }

         this._pointSize = var1;
      }
   }
}
