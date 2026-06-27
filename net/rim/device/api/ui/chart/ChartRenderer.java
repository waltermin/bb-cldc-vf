package net.rim.device.api.ui.chart;

import net.rim.device.api.ui.Graphics;

public class ChartRenderer {
   private ChartField _field;
   private XYDataset _dataset;
   private Axis _domain;
   private Axis _range;
   private int _x;
   private int _y;
   private int _width;
   private int _height;
   private int _preferredWidth;
   private int _preferredHeight;

   protected ChartRenderer(ChartField var1) {
      this._field = var1;
   }

   public Axis getDomainAxis() {
      return this._domain;
   }

   public ChartField getField() {
      return this._field;
   }

   public int getHeight() {
      return this._height;
   }

   public int getLeft() {
      return this._x;
   }

   public int getPreferredHeight() {
      return this._preferredHeight;
   }

   public int getPreferredWidth() {
      return this._preferredWidth;
   }

   public Axis getRangeAxis() {
      return this._range;
   }

   public int getTop() {
      return this._y;
   }

   public int getWidth() {
      return this._width;
   }

   public void layout(int var1, int var2) {
      throw null;
   }

   public void paint(Graphics var1) {
      throw null;
   }

   public XYDataset getDataset() {
      return this._dataset;
   }

   public void setAxis(Axis var1, Axis var2) {
      this._domain = var1;
      this._range = var2;
   }

   public void setDataset(XYDataset var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void setExtent(int var1, int var2) {
      this._width = var1;
      this._height = var2;
   }

   public void setPosition(int var1, int var2) {
      this._x = var1;
      this._y = var2;
   }

   public void setPreferredHeight(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setPreferredWidth(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
