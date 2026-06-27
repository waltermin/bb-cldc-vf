package net.rim.device.api.math;

public final class VecMath {
   private static final int ONE;
   public static final int[] IDENTITY_3X3;
   private static final int[] tempMatrix;

   private VecMath() {
   }

   public static final native long multiplyPoint(int[] var0, int var1, int var2, int var3);

   public static final native void transformPoints(int[] var0, int var1, int[] var2, int[] var3, int[] var4, int[] var5);

   public static final native void transformPoints32(int[] var0, int var1, int[] var2, int[] var3, int[] var4, int[] var5);

   public static final native void transformPoints32(int[] var0, int var1, int[] var2, int[] var3, int[] var4, int[] var5, int var6);

   public static final boolean isIdentity(int[] var0, int var1) {
      return var0[var1] == 65536
         && var0[var1 + 1] == 0
         && var0[var1 + 2] == 0
         && var0[var1 + 3] == 0
         && var0[var1 + 4] == 65536
         && var0[var1 + 5] == 0
         && var0[var1 + 6] == 0
         && var0[var1 + 7] == 0
         && var0[var1 + 8] == 65536;
   }

   public static final boolean isTranslation(int[] var0, int var1) {
      return var0[var1] == 65536
         && var0[var1 + 1] == 0
         && var0[var1 + 3] == 0
         && var0[var1 + 4] == 65536
         && var0[var1 + 6] == 0
         && var0[var1 + 7] == 0
         && var0[var1 + 8] == 65536;
   }

   public static final native void rotateMatrix(int[] var0, int var1, int var2, int var3, int var4, int[] var5);

   public static final native void translate(int[] var0, int var1, int var2, int var3, int[] var4);

   public static final native void scale(int[] var0, int var1, int var2, int var3, int[] var4);

   public static final native void skew(int[] var0, int var1, int var2, int var3, int[] var4);

   public static final native boolean intersects(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public static final void multiply3x3(int[] var0, int var1, int[] var2, int var3) {
      multiply3x3(var0, var1, var2, var3, tempMatrix, 0);
      System.arraycopy(tempMatrix, 0, var2, var3, 9);
   }

   public static final native void multiply3x3(int[] var0, int var1, int[] var2, int var3, int[] var4, int var5);

   public static final native void multiply3x3Affine(int[] var0, int var1, int[] var2, int var3, int[] var4, int var5);

   public static final native void pointMultiply3x3(int[] var0, int var1, int var2, int var3, int[] var4, int var5);

   public static final void copyIdentity3x3(int[] var0, int var1) {
      System.arraycopy(IDENTITY_3X3, 0, var0, var1, 9);
   }

   public static final void invert2x2Mat(int var0, int var1, int var2, int var3, int[] var4, int var5) {
      int var6 = Fixed32.mul(var0, var3) - Fixed32.mul(var1, var2);
      if (var6 == 0) {
         var4[var5++] = var0;
         var4[var5++] = var1;
         var4[var5++] = var2;
         var4[var5++] = var3;
      } else {
         var4[var5++] = Fixed32.div(var3, var6);
         var4[var5++] = Fixed32.div(-var1, var6);
         var4[var5++] = Fixed32.div(-var2, var6);
         var4[var5++] = Fixed32.div(var0, var6);
      }
   }
}
