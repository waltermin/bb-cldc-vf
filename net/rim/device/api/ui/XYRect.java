package net.rim.device.api.ui;

import net.rim.device.api.util.MathUtilities;

public final class XYRect {
   public int x;
   public int y;
   public int width;
   public int height;

   public XYRect() {
   }

   public XYRect(XYPoint var1, XYPoint var2) {
      this.x = var1.x;
      this.y = var1.y;
      this.width = var2.x - this.x;
      this.height = var2.y - this.y;
   }

   public XYRect(XYRect var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.width = var1.width;
      this.height = var1.height;
   }

   public XYRect(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.y = var2;
      this.width = var3;
      this.height = var4;
   }

   public final void add(XYEdges var1) {
      this.x = this.x - var1.left;
      this.y = this.y - var1.top;
      this.width = this.width + var1.left + var1.right;
      this.height = this.height + var1.top + var1.bottom;
   }

   public final boolean contains(int var1, int var2, int var3, int var4) {
      if (this.width > 0 && this.height > 0 && var3 > 0 && var4 > 0) {
         return var1 >= this.x && var2 >= this.y && var1 + var3 <= this.x + this.width && var2 + var4 <= this.y + this.height;
      } else {
         return var3 != 0 && var4 != 0 ? false : var1 >= this.x && var2 >= this.y && var1 < this.x + this.width && var2 < this.y + this.height;
      }
   }

   public final boolean contains(int var1, int var2) {
      return this.width <= 0 || this.height <= 0 ? false : var1 >= this.x && var2 >= this.y && var1 < this.x + this.width && var2 < this.y + this.height;
   }

   public final boolean contains(XYPoint var1) {
      return this.contains(var1.x, var1.y);
   }

   public final boolean contains(XYRect var1) {
      return this.contains(var1.x, var1.y, var1.width, var1.height);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void intersect(int var1, int var2, int var3, int var4) {
      int var5 = this.X2();
      int var6 = this.Y2();
      int var7 = MathUtilities.clamp(this.x, var1, var5);
      int var8 = MathUtilities.clamp(this.y, var2, var6);
      int var9 = MathUtilities.clamp(this.x, var1 + var3, var5);
      int var10 = MathUtilities.clamp(this.y, var2 + var4, var6);
      this.x = var7;
      this.y = var8;
      this.width = var9 - var7;
      this.height = var10 - var8;
   }

   public final void intersect(XYRect var1) {
      int var2 = this.X2();
      int var3 = this.Y2();
      int var4 = MathUtilities.clamp(this.x, var1.x, var2);
      int var5 = MathUtilities.clamp(this.y, var1.y, var3);
      int var6 = MathUtilities.clamp(this.x, var1.X2(), var2);
      int var7 = MathUtilities.clamp(this.y, var1.Y2(), var3);
      this.x = var4;
      this.y = var5;
      this.width = var6 - var4;
      this.height = var7 - var5;
   }

   public final boolean isEmpty() {
      return this.width == 0 || this.height == 0;
   }

   public final boolean intersects(int var1, int var2, int var3, int var4) {
      return this.x + this.width > var1 && this.x < var1 + var3 && this.y + this.height > var2 && this.y < var2 + var4;
   }

   public final boolean intersects(XYRect var1) {
      return this.x + this.width > var1.x && this.x < var1.X2() && this.y + this.height > var1.y && this.y < var1.Y2();
   }

   public final void set(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.y = var2;
      this.width = var3;
      this.height = var4;
   }

   public final void set(XYRect var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.width = var1.width;
      this.height = var1.height;
   }

   public final void setLocation(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public final void setLocation(XYPoint var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public final void setSize(int var1, int var2) {
      this.width = var1;
      this.height = var2;
   }

   public final void subtract(XYEdges var1) {
      this.x = this.x + var1.left;
      this.y = this.y + var1.top;
      this.width = this.width - (var1.left + var1.right);
      this.height = this.height - (var1.top + var1.bottom);
   }

   public final void translate(int var1, int var2) {
      this.x += var1;
      this.y += var2;
   }

   public final void translate(XYPoint var1) {
      this.translate(var1.x, var1.y);
   }

   public final void union(int var1, int var2, int var3, int var4) {
      if (this.width > 0 && this.height > 0) {
         if (var1 + var3 > this.x + this.width) {
            this.width = this.width + (var1 + var3 - (this.x + this.width));
         }

         if (var2 + var4 > this.y + this.height) {
            this.height = this.height + (var2 + var4 - (this.y + this.height));
         }

         if (var1 < this.x) {
            this.width = this.width + (this.x - var1);
            this.x = var1;
         }

         if (var2 < this.y) {
            this.height = this.height + (this.y - var2);
            this.y = var2;
            return;
         }
      } else {
         this.set(var1, var2, var3, var4);
      }
   }

   public final void union(XYRect var1) {
      this.union(var1.x, var1.y, var1.width, var1.height);
   }

   public final void unionNoEmpty(XYRect var1) {
      this.unionNoEmpty(var1.x, var1.y, var1.width, var1.height);
   }

   public final void unionNoEmpty(int var1, int var2, int var3, int var4) {
      if (this.isEmpty()) {
         this.set(var1, var2, var3, var4);
      } else {
         if (var3 > 0 || var4 > 0) {
            this.union(var1, var2, var3, var4);
         }
      }
   }

   public final int X2() {
      return this.x + this.width;
   }

   public final int Y2() {
      return this.y + this.height;
   }
}
