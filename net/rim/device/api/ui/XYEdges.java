package net.rim.device.api.ui;

public final class XYEdges {
   public int bottom;
   public int left;
   public int right;
   public int top;

   public XYEdges() {
   }

   public XYEdges(int var1, int var2, int var3, int var4) {
      this.top = var1;
      this.right = var2;
      this.bottom = var3;
      this.left = var4;
   }

   public final boolean isEmpty() {
      return this.top == 0 && this.right == 0 && this.bottom == 0 && this.left == 0;
   }

   public final void set(int var1, int var2, int var3, int var4) {
      this.top = var1;
      this.right = var2;
      this.bottom = var3;
      this.left = var4;
   }
}
