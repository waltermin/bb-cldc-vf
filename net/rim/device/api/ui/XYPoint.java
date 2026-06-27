package net.rim.device.api.ui;

public final class XYPoint {
   public int x;
   public int y;

   public XYPoint() {
   }

   public XYPoint(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public XYPoint(XYPoint var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public final void set(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public final void set(XYPoint var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void translate(int var1, int var2) {
      this.x += var1;
      this.y += var2;
   }

   public final void translate(XYPoint var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
   }
}
