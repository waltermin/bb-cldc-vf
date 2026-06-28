package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.util.Arrays;

public class XYDataset {
   private int[] _x = new int[0];
   private int[] _y = new int[0];
   private int[] _xBounds;
   private int[] _yBounds;

   public void add(int x, int y) {
      Arrays.add(this._x, x);
      Arrays.add(this._y, y);
   }

   public void add(XYPoint point) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void calcBounds() {
      if (this._xBounds == null) {
         this._xBounds = this.calcBounds(this._x);
         this._yBounds = this.calcBounds(this._y);
      }
   }

   private int[] calcBounds(int[] array) {
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

   public void getPoint(int index, XYPoint point) {
      point.x = this._x[index];
      point.y = this._y[index];
   }

   public int getSize() {
      return this._x.length;
   }

   public int getX(int index) {
      return this._x[index];
   }

   int[] getXArrayInternal() {
      return this._x;
   }

   public int getY(int index) {
      return this._y[index];
   }

   int[] getYArrayInternal() {
      return this._y;
   }

   protected void insert(int index, int x, int y) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void insert(int index, XYPoint point) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean isEmpty() {
      return this._x.length == 0;
   }

   public void setPoint(int index, int x, int y) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setPoint(int index, XYPoint point) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
