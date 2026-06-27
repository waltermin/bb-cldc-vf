package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;

public class Axis {
   private int _scaleTextWidth;
   private int _scalePadding = 0;
   private int _min;
   private int _max;
   private int _increment = 1;
   private int _edge;
   private int _x;
   private int _y;
   private int _width;
   private int _height;
   private int _majorLength;
   private int _scaleTickSpacing;
   private boolean _showValues = true;
   ChartField _field;
   private static final int AXIS_WIDTH;
   public static final int PADDING_NONE;
   public static final int PADDING_LOW;
   public static final int PADDING_MEDIUM;
   public static final int PADDING_HIGH;
   private static final int X_SCALE_SPACER;
   private static final int Y_SCALE_SPACER;
   private static final int SCALE_TICK_BREADTH;
   private static final int SCALE_TICK_LENGTH;
   public static final int EDGE_TOP;
   public static final int EDGE_RIGHT;
   public static final int EDGE_BOTTOM;
   public static final int EDGE_LEFT;

   public Axis(ChartField var1) {
      this._field = var1;
   }

   private void calculateScaleAdvance(Font var1) {
      int var2 = 0;

      for (int var3 = this._min + this._min % this._increment; var3 <= this._max; var3 += this._increment) {
         int var4 = var1.getAdvance(Integer.toString(var3));
         var2 = Math.max(var2, var4);
      }

      this._scaleTextWidth = var2;
   }

   private void calculateScaleTickIndents() {
   }

   private void calculateScaleTickSpacings() {
      if (this._edge != 0 && this._edge != 2) {
         if (this._showValues) {
            this._scaleTickSpacing = this._scaleTextWidth;
         } else {
            this._scaleTickSpacing = this._scalePadding;
         }
      } else if (this._showValues) {
         Font var1 = this._field.getFont();
         this._scaleTickSpacing = var1.getHeight();
      } else {
         this._scaleTickSpacing = this._scalePadding;
      }
   }

   void calculateWidth() {
   }

   public int getHeight() {
      return this._height;
   }

   public int getIncrement() {
      return this._increment;
   }

   public int getLeft() {
      return this._x;
   }

   public int getMax() {
      return this._max;
   }

   public int getMin() {
      return this._min;
   }

   public int getPreferredHeight() {
      this.calculateScaleAdvance(this._field.getFont());
      switch (this._edge) {
         case 0:
         case 2:
            return this._scalePadding + this._field.getFont().getHeight() + 6 + 2 + 2;
         default:
            return 0;
      }
   }

   public int getPreferredWidth() {
      this.calculateScaleAdvance(this._field.getFont());
      switch (this._edge) {
         case 1:
         case 3:
            return this._scalePadding + this._scaleTextWidth + 6 + 2 + 4;
         default:
            return 0;
      }
   }

   public int getScalePadding() {
      return this._scalePadding;
   }

   public int getTop() {
      return this._y;
   }

   public int getWidth() {
      return this._width;
   }

   public boolean isValuesDisplayed() {
      return this._showValues;
   }

   public void layout(int var1, int var2) {
      this._width = var1;
      this._height = var2;
      switch (this._edge) {
         case -1:
            return;
         case 0:
         default:
            this._majorLength = var1;
            break;
         case 1:
            this._majorLength = var2;
            break;
         case 2:
            this._majorLength = var1;
            break;
         case 3:
            this._majorLength = var2;
      }

      this._scaleTickSpacing = this._majorLength / ((this._max - this._min) / this._increment);
   }

   public void paint(Graphics var1) {
      int var2;
      int var3;
      int var4;
      int var5;
      switch (this._edge) {
         case -1:
            return;
         case 0:
         default:
            var2 = 0;
            var3 = this._height - 2;
            var4 = this._width;
            var5 = 2;
            break;
         case 1:
            var2 = 0;
            var3 = 0;
            var4 = 2;
            var5 = this._height;
            break;
         case 2:
            var2 = 0;
            var3 = 0;
            var4 = this._width;
            var5 = 2;
            break;
         case 3:
            var2 = this._width - 2;
            var3 = 0;
            var4 = 2;
            var5 = this._height;
      }

      var1.fillRect(var2, var3, var4, var5);
      this.paintScales(var1);
   }

   private void paintScales(Graphics var1) {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var9 = 0;
      byte var6;
      byte var7;
      int var8;
      byte var10;
      if (this._edge != 0 && this._edge != 2) {
         if (this._edge == 3) {
            var2 = this.getWidth() - 6;
            var8 = -this._scaleTextWidth;
         } else {
            var8 = 0;
         }

         var9 = -(this._field.getFont().getHeight() >> 1);
         var3 = this.getHeight();
         var5 = -this._scaleTickSpacing;
         var6 = 6;
         var7 = 2;
         var10 = 5;
      } else {
         var4 = this._scaleTickSpacing;
         var6 = 2;
         var7 = 6;
         var8 = -(this._scaleTextWidth >> 1);
         if (this._edge == 2) {
            var9 += 6;
         }

         var10 = 4;
      }

      for (int var11 = this._min + Math.abs(this._min) % this._increment; var11 <= this._max; var11 += this._increment) {
         if (this._showValues) {
            var1.drawText(Integer.toString(var11), var2 + var8, var3 + var9, var10, this._scaleTextWidth);
         }

         var1.fillRect(var2, var3, var6, var7);
         var2 += var4;
         var3 += var5;
      }
   }

   int mapValueToPixel(int var1) {
      return this._majorLength - (var1 - this._min) * this._majorLength / (this._max - this._min);
   }

   void setEdge(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setIncrement(int var1) {
      if (this._increment != var1) {
         if (0 >= var1 || var1 > this._max - this._min) {
            throw new Object();
         }

         this._increment = var1;
      }
   }

   public void setMinMax(int var1, int var2) {
      if (var1 >= var2) {
         throw new Object();
      }

      this._min = var1;
      this._max = var2;
   }

   public void setPosition(int var1, int var2) {
      this._x = var1;
      this._y = var2;
   }

   public void setScalePadding(int var1) {
      if (this._scalePadding != var1) {
         switch (var1) {
            case 0:
            case 12:
            case 18:
            case 24:
               this._scalePadding = var1;
               break;
            default:
               throw new Object();
         }
      }
   }

   public void setScaleValuesDisplayed(boolean var1) {
      if (this._showValues != var1) {
         this._showValues = var1;
      }
   }
}
