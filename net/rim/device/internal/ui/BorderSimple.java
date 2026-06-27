package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class BorderSimple extends Border {
   private boolean _colorSet;
   private int _colorTop;
   private int _colorRight;
   private int _colorBottom;
   private int _colorLeft;
   public static final int STYLE_NONE;
   public static final int STYLE_HIDDEN;
   public static final int STYLE_DOTTED;
   public static final int STYLE_DASHED;
   public static final int STYLE_SOLID;
   public static final int STYLE_DOUBLE;
   public static final int STYLE_GROOVE;
   public static final int STYLE_RIDGE;
   public static final int STYLE_INSET;
   public static final int STYLE_OUTSET;

   public BorderSimple(int var1, int var2, int var3, int var4) {
   }

   public BorderSimple(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      super(var1, var2, var3, var4);
      this._colorSet = true;
      this._colorTop = var5;
      this._colorRight = var6;
      this._colorBottom = var7;
      this._colorLeft = var8;
   }

   @Override
   public void paint(Graphics var1, XYRect var2) {
      int var3 = var1.getColor();
      if (this._colorSet) {
         var1.setColor(this._colorTop);
      }

      var1.fillRect(var2.x, var2.y, var2.width, this.getTop());
      if (this._colorSet) {
         var1.setColor(this._colorRight);
      }

      var1.fillRect(var2.X2() - this.getRight(), var2.y, this.getRight(), var2.height);
      if (this._colorSet) {
         var1.setColor(this._colorBottom);
      }

      var1.fillRect(var2.x, var2.Y2() - this.getBottom(), var2.width, this.getBottom());
      if (this._colorSet) {
         var1.setColor(this._colorLeft);
      }

      var1.fillRect(var2.x, var2.y, this.getLeft(), var2.height);
      var1.setColor(var3);
   }
}
