package net.rim.device.api.ui;

public final class XYDimension {
   public int width;
   public int height;

   public XYDimension() {
   }

   public XYDimension(int var1, int var2) {
      this.width = var1;
      this.height = var2;
   }

   public XYDimension(XYDimension var1) {
      this.width = var1.width;
      this.height = var1.height;
   }

   public final void set(int var1, int var2) {
      this.width = var1;
      this.height = var2;
   }

   public final void set(XYDimension var1) {
      this.width = var1.width;
      this.height = var1.height;
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
