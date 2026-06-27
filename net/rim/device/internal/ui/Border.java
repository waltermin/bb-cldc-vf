package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;

public class Border {
   private int _top;
   private int _right;
   private int _bottom;
   private int _left;
   private boolean _transparent;
   private static XYEdges _edges;

   public Border(int var1, int var2, int var3, int var4) {
      this._top = var1;
      this._right = var2;
      this._bottom = var3;
      this._left = var4;
   }

   public Background getBackground() {
      return null;
   }

   public final int getBottom() {
      return this._bottom;
   }

   public final XYEdges getEdges() {
      _edges.set(this._top, this._right, this._bottom, this._left);
      return _edges;
   }

   public final void getEdges(XYEdges var1) {
      var1.set(this._top, this._right, this._bottom, this._left);
   }

   public final int getLeft() {
      return this._left;
   }

   public final int getRight() {
      return this._right;
   }

   public final int getTop() {
      return this._top;
   }

   public final boolean isTransparent() {
      return this._transparent;
   }

   public void paint(Graphics var1, XYRect var2) {
      throw null;
   }

   protected final void setTransparent(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
