package javax.microedition.lcdui;

import java.io.InputStream;
import net.rim.device.api.system.Bitmap;

public class Image {
   private Bitmap _bitmap;
   private boolean _mutable;

   final Bitmap getBitmap() {
      return this._bitmap;
   }

   private Image() {
   }

   public static Image createImage(int var0, int var1) {
      if (var0 > 0 && var1 > 0) {
         Image var2 = new Image();
         var2._bitmap = (Bitmap)(new Object(var0, var1));
         var2._mutable = true;
         return var2;
      } else {
         throw new Object();
      }
   }

   public static Image createImage(Image var0) {
      if (var0._mutable) {
         int var1 = var0.getWidth();
         int var2 = var0.getHeight();
         Image var3 = createImage(var1, var2);
         var3.getGraphics().getPeer().drawBitmap(0, 0, var1, var2, var0._bitmap, 0, 0);
         var3._mutable = false;
         return var3;
      } else {
         return var0;
      }
   }

   public static Image createImage(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static Image createImage(byte[] var0, int var1, int var2) {
      if (var1 >= 0 && var2 >= 0 && var1 + var2 <= var0.length) {
         Image var3 = new Image();
         var3._bitmap = Bitmap.createBitmapFromPNG(var0, var1, var2);
         var3._mutable = false;
         return var3;
      } else {
         throw new Object();
      }
   }

   private static void getNextScanLine(int[] var0, int var1, int[] var2, int var3, int var4, int var5, int var6, int var7) {
      var0[var1] = var2[var5 + var6 * var3];
      if (var4 > 1) {
         for (int var8 = 1; var8 < var4; var8++) {
            if (Graphics.DUX[var7] == 0) {
               var0[var1 + var8] = var2[var5 + (var6 + var8 * Graphics.DUY[var7]) * var3];
            } else {
               var0[var1 + var8] = var2[var5 + Graphics.DUX[var7] * var8 + var3 * var6];
            }
         }
      }
   }

   public static Image createImage(Image var0, int var1, int var2, int var3, int var4, int var5) {
      var0.isMutable();
      if (var1 < 0 || var2 < 0 || var1 + var3 > var0.getWidth() || var2 + var4 > var0.getHeight() || var3 <= 0 || var4 <= 0 || var5 < 0 || var5 > 7) {
         throw new Object();
      }

      if (var1 == 0 && var2 == 0 && var3 == var0.getWidth() && var4 == var0.getHeight() && var5 == 0) {
         return var0;
      }

      int[] var7 = null;
      int[] var8 = null;
      var7 = new int[var3 * var4];
      var0.getRGB(var7, 0, var3, var1, var2, var3, var4);
      Image var6;
      if (var5 == 0) {
         var6 = new Image();
         var6._bitmap = (Bitmap)(new Object(var3, var4));
         var6._bitmap.setARGB(var7, 0, var3, 0, 0, var3, var4);
      } else {
         int var9 = 0;
         int var10 = 0;
         int var11 = 0;
         int var12 = 0;
         switch (var5) {
            case 0:
               break;
            case 1:
               var9 = 0;
               var10 = var4 - 1;
               var11 = var3;
               var12 = var4;
               break;
            case 2:
            default:
               var9 = var3 - 1;
               var10 = 0;
               var11 = var3;
               var12 = var4;
               break;
            case 3:
               var9 = var3 - 1;
               var10 = var4 - 1;
               var11 = var3;
               var12 = var4;
               break;
            case 4:
               var9 = 0;
               var10 = 0;
               var11 = var4;
               var12 = var3;
               break;
            case 5:
               var9 = 0;
               var10 = var4 - 1;
               var5 = 6;
               var11 = var4;
               var12 = var3;
               break;
            case 6:
               var9 = var3 - 1;
               var10 = 0;
               var5 = 5;
               var11 = var4;
               var12 = var3;
               break;
            case 7:
               var9 = var3 - 1;
               var10 = var4 - 1;
               var11 = var4;
               var12 = var3;
         }

         var8 = new int[var11 * var12];
         int var13 = 0;

         for (int var14 = 0; var14 < var12; var14++) {
            getNextScanLine(var8, var13, var7, var3, var11, var9, var10, var5);
            var13 += var11;
            if (Graphics.DUX[var5] == 0) {
               var9 += Graphics.DVX[var5];
            } else {
               var10 += Graphics.DVY[var5];
            }
         }

         var6 = new Image();
         var6._bitmap = (Bitmap)(new Object(var11, var12));
         var6._bitmap.setARGB(var8, 0, var11, 0, 0, var11, var12);
      }

      return var6;
   }

   public static Image createImage(InputStream var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Graphics getGraphics() {
      if (!this._mutable) {
         throw new Object();
      }

      Graphics var1 = new Graphics(this);
      Object var2 = new Object(this._bitmap);
      ((net.rim.device.api.ui.Graphics)var2).pushRegion(0, 0, this.getWidth(), this.getHeight(), 0, 0);
      var1.setGraphics((net.rim.device.api.ui.Graphics)var2, false);
      return var1;
   }

   public int getWidth() {
      return this._bitmap.getWidth();
   }

   public int getHeight() {
      return this._bitmap.getHeight();
   }

   public boolean isMutable() {
      return this._mutable;
   }

   public static Image createRGBImage(int[] var0, int var1, int var2, boolean var3) {
      if (var1 > 0 && var2 > 0) {
         if (var0.length < var1 * var2) {
            throw new Object();
         }

         Image var4 = new Image();
         var4._bitmap = (Bitmap)(new Object(var1, var2));
         var4._bitmap.setARGB(var0, 0, var4.getWidth(), 0, 0, var1, var2);
         if (!var3) {
            var4._bitmap.setAlpha(null);
         }

         var4._mutable = false;
         return var4;
      } else {
         throw new Object();
      }
   }

   public void getRGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = var1.length;
      boolean var9 = var4 < 0 || var5 < 0 || var4 + var6 > this._bitmap.getWidth() || var5 + var7 > this._bitmap.getHeight();
      if (!var9 && Math.abs(var3) >= var6) {
         if (var6 > 0 && var7 > 0) {
            if (var2 < 0 || var2 + (var7 - 1) * var3 + var6 > var8 || var2 + (var7 - 1) * var3 < 0) {
               throw new Object();
            }

            if (var3 < 0) {
               int var10 = 0 - var3;

               for (int var11 = var7 - 1; var11 >= 0; var11--) {
                  this._bitmap.getARGB(var1, var2, var10, var4, var5 + var11, var6, 1);
                  var2 += var6;
               }
            } else {
               this._bitmap.getARGB(var1, var2, var3, var4, var5, var6, var7);
            }
         }
      } else {
         throw new Object();
      }
   }
}
