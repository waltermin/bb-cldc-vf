package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.util.Arrays;

public class XYDataset {
   private int[] _x = new int[0];
   private int[] _y = new int[0];
   private int[] _xBounds;
   private int[] _yBounds;

   public void add(int var1, int var2) {
      Arrays.add(this._x, var1);
      Arrays.add(this._y, var2);
   }

   public void add(XYPoint var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void calcBounds() {
      if (this._xBounds == null) {
         this._xBounds = this.calcBounds(this._x);
         this._yBounds = this.calcBounds(this._y);
      }
   }

   private int[] calcBounds(int[] var1) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public int getMaxX() {
      this.calcBounds();
      return this._xBounds[1];
   }

   public int getMaxY() {
      this.calcBounds();
      return this._yBounds[1];
   }

   public int getMinX() {
      this.calcBounds();
      return this._xBounds[0];
   }

   public int getMinY() {
      this.calcBounds();
      return this._yBounds[0];
   }

   public void getPoint(int var1, XYPoint var2) {
      var2.x = this._x[var1];
      var2.y = this._y[var1];
   }

   public int getSize() {
      return this._x.length;
   }

   public int getX(int var1) {
      return this._x[var1];
   }

   int[] getXArrayInternal() {
      return this._x;
   }

   public int getY(int var1) {
      return this._y[var1];
   }

   int[] getYArrayInternal() {
      return this._y;
   }

   protected void insert(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void insert(int var1, XYPoint var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean isEmpty() {
      return this._x.length == 0;
   }

   public void setPoint(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setPoint(int var1, XYPoint var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
