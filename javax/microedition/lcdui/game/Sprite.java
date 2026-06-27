package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprite extends Layer {
   Image sourceImage;
   int numberFrames;
   int[] frameCoordsX;
   int[] frameCoordsY;
   int srcFrameWidth;
   int srcFrameHeight;
   int[] frameSequence;
   private int sequenceIndex;
   private boolean customSequenceDefined;
   int dRefX;
   int dRefY;
   int collisionRectX;
   int collisionRectY;
   int collisionRectWidth;
   int collisionRectHeight;
   int t_currentTransformation;
   int t_collisionRectX;
   int t_collisionRectY;
   int t_collisionRectWidth;
   int t_collisionRectHeight;
   public static final int TRANS_NONE;
   public static final int TRANS_ROT90;
   public static final int TRANS_ROT180;
   public static final int TRANS_ROT270;
   public static final int TRANS_MIRROR;
   public static final int TRANS_MIRROR_ROT90;
   public static final int TRANS_MIRROR_ROT180;
   public static final int TRANS_MIRROR_ROT270;
   private static final int INVERTED_AXES;
   private static final int X_FLIP;
   private static final int Y_FLIP;
   private static final int ALPHA_BITMASK;

   public Sprite(Image var1) {
      super(var1.getWidth(), var1.getHeight());
      this.initializeFrames(var1, var1.getWidth(), var1.getHeight(), false);
      this.initCollisionRectBounds();
      this.setTransformImpl(0);
   }

   public Sprite(Image var1, int var2, int var3) {
      super(var2, var3);
      if (var2 >= 1 && var3 >= 1 && var1.getWidth() % var2 == 0 && var1.getHeight() % var3 == 0) {
         this.initializeFrames(var1, var2, var3, false);
         this.initCollisionRectBounds();
         this.setTransformImpl(0);
      } else {
         throw new Object();
      }
   }

   public Sprite(Sprite var1) {
      super(var1 != null ? var1.getWidth() : 0, var1 != null ? var1.getHeight() : 0);
      if (var1 == null) {
         throw new Object();
      }

      this.sourceImage = Image.createImage(var1.sourceImage);
      this.numberFrames = var1.numberFrames;
      this.frameCoordsX = new int[this.numberFrames];
      this.frameCoordsY = new int[this.numberFrames];
      System.arraycopy(var1.frameCoordsX, 0, this.frameCoordsX, 0, var1.getRawFrameCount());
      System.arraycopy(var1.frameCoordsY, 0, this.frameCoordsY, 0, var1.getRawFrameCount());
      super.x = var1.getX();
      super.y = var1.getY();
      this.dRefX = var1.dRefX;
      this.dRefY = var1.dRefY;
      this.collisionRectX = var1.collisionRectX;
      this.collisionRectY = var1.collisionRectY;
      this.collisionRectWidth = var1.collisionRectWidth;
      this.collisionRectHeight = var1.collisionRectHeight;
      this.srcFrameWidth = var1.srcFrameWidth;
      this.srcFrameHeight = var1.srcFrameHeight;
      this.setTransformImpl(var1.t_currentTransformation);
      this.setVisible(var1.isVisible());
      this.frameSequence = new int[var1.getFrameSequenceLength()];
      this.setFrameSequence(var1.frameSequence);
      this.setFrame(var1.getFrame());
      this.setRefPixelPosition(var1.getRefPixelX(), var1.getRefPixelY());
   }

   public void defineReferencePixel(int var1, int var2) {
      this.dRefX = var1;
      this.dRefY = var2;
   }

   public void setRefPixelPosition(int var1, int var2) {
      super.x = var1 - this.getTransformedPtX(this.dRefX, this.dRefY, this.t_currentTransformation);
      super.y = var2 - this.getTransformedPtY(this.dRefX, this.dRefY, this.t_currentTransformation);
   }

   public int getRefPixelX() {
      return super.x + this.getTransformedPtX(this.dRefX, this.dRefY, this.t_currentTransformation);
   }

   public int getRefPixelY() {
      return super.y + this.getTransformedPtY(this.dRefX, this.dRefY, this.t_currentTransformation);
   }

   public void setFrame(int var1) {
      if (var1 >= 0 && var1 < this.frameSequence.length) {
         this.sequenceIndex = var1;
      } else {
         throw new Object();
      }
   }

   public final int getFrame() {
      return this.sequenceIndex;
   }

   public int getRawFrameCount() {
      return this.numberFrames;
   }

   public int getFrameSequenceLength() {
      return this.frameSequence.length;
   }

   public void nextFrame() {
      this.sequenceIndex = (this.sequenceIndex + 1) % this.frameSequence.length;
   }

   public void prevFrame() {
      if (this.sequenceIndex == 0) {
         this.sequenceIndex = this.frameSequence.length - 1;
      } else {
         this.sequenceIndex--;
      }
   }

   @Override
   public final void paint(Graphics var1) {
      if (var1 == null) {
         throw new Object();
      }

      if (super.visible) {
         var1.drawRegion(
            this.sourceImage,
            this.frameCoordsX[this.frameSequence[this.sequenceIndex]],
            this.frameCoordsY[this.frameSequence[this.sequenceIndex]],
            this.srcFrameWidth,
            this.srcFrameHeight,
            this.t_currentTransformation,
            super.x,
            super.y,
            20
         );
      }
   }

   public void setFrameSequence(int[] var1) {
      if (var1 == null) {
         this.sequenceIndex = 0;
         this.customSequenceDefined = false;
         this.frameSequence = new int[this.numberFrames];
         int var3 = 0;

         while (var3 < this.numberFrames) {
            this.frameSequence[var3] = var3++;
         }
      } else {
         if (var1.length < 1) {
            throw new Object();
         }

         for (int var2 = 0; var2 < var1.length; var2++) {
            if (var1[var2] < 0 || var1[var2] >= this.numberFrames) {
               throw new Object();
            }
         }

         this.customSequenceDefined = true;
         this.frameSequence = new int[var1.length];
         System.arraycopy(var1, 0, this.frameSequence, 0, var1.length);
         this.sequenceIndex = 0;
      }
   }

   public void setImage(Image var1, int var2, int var3) {
      if (var2 >= 1 && var3 >= 1 && var1.getWidth() % var2 == 0 && var1.getHeight() % var3 == 0) {
         int var4 = var1.getWidth() / var2 * (var1.getHeight() / var3);
         boolean var5 = true;
         if (var4 < this.numberFrames) {
            var5 = false;
            this.customSequenceDefined = false;
         }

         if (this.srcFrameWidth == var2 && this.srcFrameHeight == var3) {
            this.initializeFrames(var1, var2, var3, var5);
         } else {
            int var6 = super.x + this.getTransformedPtX(this.dRefX, this.dRefY, this.t_currentTransformation);
            int var7 = super.y + this.getTransformedPtY(this.dRefX, this.dRefY, this.t_currentTransformation);
            this.setWidthImpl(var2);
            this.setHeightImpl(var3);
            this.initializeFrames(var1, var2, var3, var5);
            this.initCollisionRectBounds();
            super.x = var6 - this.getTransformedPtX(this.dRefX, this.dRefY, this.t_currentTransformation);
            super.y = var7 - this.getTransformedPtY(this.dRefX, this.dRefY, this.t_currentTransformation);
            this.computeTransformedBounds(this.t_currentTransformation);
         }
      } else {
         throw new Object();
      }
   }

   public void defineCollisionRectangle(int var1, int var2, int var3, int var4) {
      if (var3 >= 0 && var4 >= 0) {
         this.collisionRectX = var1;
         this.collisionRectY = var2;
         this.collisionRectWidth = var3;
         this.collisionRectHeight = var4;
         this.setTransformImpl(this.t_currentTransformation);
      } else {
         throw new Object();
      }
   }

   public void setTransform(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final boolean collidesWith(Sprite var1, boolean var2) {
      if (var1.visible && super.visible) {
         int var3 = var1.x + var1.t_collisionRectX;
         int var4 = var1.y + var1.t_collisionRectY;
         int var5 = var3 + var1.t_collisionRectWidth;
         int var6 = var4 + var1.t_collisionRectHeight;
         int var7 = super.x + this.t_collisionRectX;
         int var8 = super.y + this.t_collisionRectY;
         int var9 = var7 + this.t_collisionRectWidth;
         int var10 = var8 + this.t_collisionRectHeight;
         if (!this.intersectRect(var3, var4, var5, var6, var7, var8, var9, var10)) {
            return false;
         }

         if (!var2) {
            return true;
         }

         if (this.t_collisionRectX < 0) {
            var7 = super.x;
         }

         if (this.t_collisionRectY < 0) {
            var8 = super.y;
         }

         if (this.t_collisionRectX + this.t_collisionRectWidth > super.width) {
            var9 = super.x + super.width;
         }

         if (this.t_collisionRectY + this.t_collisionRectHeight > super.height) {
            var10 = super.y + super.height;
         }

         if (var1.t_collisionRectX < 0) {
            var3 = var1.x;
         }

         if (var1.t_collisionRectY < 0) {
            var4 = var1.y;
         }

         if (var1.t_collisionRectX + var1.t_collisionRectWidth > var1.width) {
            var5 = var1.x + var1.width;
         }

         if (var1.t_collisionRectY + var1.t_collisionRectHeight > var1.height) {
            var6 = var1.y + var1.height;
         }

         if (!this.intersectRect(var3, var4, var5, var6, var7, var8, var9, var10)) {
            return false;
         }

         int var11 = var7 < var3 ? var3 : var7;
         int var12 = var8 < var4 ? var4 : var8;
         int var13 = var9 < var5 ? var9 : var5;
         int var14 = var10 < var6 ? var10 : var6;
         int var15 = Math.abs(var13 - var11);
         int var16 = Math.abs(var14 - var12);
         int var17 = this.getImageTopLeftX(var11, var12, var13, var14);
         int var18 = this.getImageTopLeftY(var11, var12, var13, var14);
         int var19 = var1.getImageTopLeftX(var11, var12, var13, var14);
         int var20 = var1.getImageTopLeftY(var11, var12, var13, var14);
         return doPixelCollision(
            var17, var18, var19, var20, this.sourceImage, this.t_currentTransformation, var1.sourceImage, var1.t_currentTransformation, var15, var16
         );
      } else {
         return false;
      }
   }

   public final boolean collidesWith(TiledLayer var1, boolean var2) {
      if (var1.visible && super.visible) {
         int var3 = var1.x;
         int var4 = var1.y;
         int var5 = var3 + var1.width;
         int var6 = var4 + var1.height;
         int var7 = var1.getCellWidth();
         int var8 = var1.getCellHeight();
         int var9 = super.x + this.t_collisionRectX;
         int var10 = super.y + this.t_collisionRectY;
         int var11 = var9 + this.t_collisionRectWidth;
         int var12 = var10 + this.t_collisionRectHeight;
         int var13 = var1.getColumns();
         int var14 = var1.getRows();
         if (!this.intersectRect(var3, var4, var5, var6, var9, var10, var11, var12)) {
            return false;
         }

         int var15 = var9 <= var3 ? 0 : (var9 - var3) / var7;
         int var17 = var10 <= var4 ? 0 : (var10 - var4) / var8;
         int var16 = var11 < var5 ? (var11 - 1 - var3) / var7 : var13 - 1;
         int var18 = var12 < var6 ? (var12 - 1 - var4) / var8 : var14 - 1;
         if (!var2) {
            for (int var40 = var17; var40 <= var18; var40++) {
               for (int var41 = var15; var41 <= var16; var41++) {
                  if (var1.getCell(var41, var40) != 0) {
                     return true;
                  }
               }
            }

            return false;
         } else {
            if (this.t_collisionRectX < 0) {
               var9 = super.x;
            }

            if (this.t_collisionRectY < 0) {
               var10 = super.y;
            }

            if (this.t_collisionRectX + this.t_collisionRectWidth > super.width) {
               var11 = super.x + super.width;
            }

            if (this.t_collisionRectY + this.t_collisionRectHeight > super.height) {
               var12 = super.y + super.height;
            }

            if (!this.intersectRect(var3, var4, var5, var6, var9, var10, var11, var12)) {
               return false;
            }

            var15 = var9 <= var3 ? 0 : (var9 - var3) / var7;
            var17 = var10 <= var4 ? 0 : (var10 - var4) / var8;
            var16 = var11 < var5 ? (var11 - 1 - var3) / var7 : var13 - 1;
            var18 = var12 < var6 ? (var12 - 1 - var4) / var8 : var14 - 1;
            int var19 = var17 * var8 + var4;
            int var20 = var19 + var8;

            for (int var22 = var17; var22 <= var18; var20 += var8) {
               int var23 = var15 * var7 + var3;
               int var24 = var23 + var7;

               for (int var25 = var15; var25 <= var16; var24 += var7) {
                  int var21 = var1.getCell(var25, var22);
                  if (var21 != 0) {
                     int var26 = var9 < var23 ? var23 : var9;
                     int var27 = var10 < var19 ? var19 : var10;
                     int var28 = var11 < var24 ? var11 : var24;
                     int var29 = var12 < var20 ? var12 : var20;
                     if (var26 > var28) {
                        int var30 = var28;
                        var28 = var26;
                        var26 = var30;
                     }

                     if (var27 > var29) {
                        int var42 = var29;
                        var29 = var27;
                        var27 = var42;
                     }

                     int var43 = var28 - var26;
                     int var31 = var29 - var27;
                     int var32 = this.getImageTopLeftX(var26, var27, var28, var29);
                     int var33 = this.getImageTopLeftY(var26, var27, var28, var29);
                     int var34 = var1.tileSetX[var21] + (var26 - var23);
                     int var35 = var1.tileSetY[var21] + (var27 - var19);
                     if (doPixelCollision(var32, var33, var34, var35, this.sourceImage, this.t_currentTransformation, var1.sourceImage, 0, var43, var31)) {
                        return true;
                     }
                  }

                  var25++;
                  var23 += var7;
               }

               var22++;
               var19 += var8;
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public final boolean collidesWith(Image var1, int var2, int var3, boolean var4) {
      if (!super.visible) {
         return false;
      }

      int var5 = var2;
      int var6 = var3;
      int var7 = var2 + var1.getWidth();
      int var8 = var3 + var1.getHeight();
      int var9 = super.x + this.t_collisionRectX;
      int var10 = super.y + this.t_collisionRectY;
      int var11 = var9 + this.t_collisionRectWidth;
      int var12 = var10 + this.t_collisionRectHeight;
      if (!this.intersectRect(var5, var6, var7, var8, var9, var10, var11, var12)) {
         return false;
      }

      if (!var4) {
         return true;
      }

      if (this.t_collisionRectX < 0) {
         var9 = super.x;
      }

      if (this.t_collisionRectY < 0) {
         var10 = super.y;
      }

      if (this.t_collisionRectX + this.t_collisionRectWidth > super.width) {
         var11 = super.x + super.width;
      }

      if (this.t_collisionRectY + this.t_collisionRectHeight > super.height) {
         var12 = super.y + super.height;
      }

      if (!this.intersectRect(var5, var6, var7, var8, var9, var10, var11, var12)) {
         return false;
      }

      int var13 = var9 < var5 ? var5 : var9;
      int var14 = var10 < var6 ? var6 : var10;
      int var15 = var11 < var7 ? var11 : var7;
      int var16 = var12 < var8 ? var12 : var8;
      int var17 = Math.abs(var15 - var13);
      int var18 = Math.abs(var16 - var14);
      int var19 = this.getImageTopLeftX(var13, var14, var15, var16);
      int var20 = this.getImageTopLeftY(var13, var14, var15, var16);
      int var21 = var13 - var2;
      int var22 = var14 - var3;
      return doPixelCollision(var19, var20, var21, var22, this.sourceImage, this.t_currentTransformation, var1, 0, var17, var18);
   }

   private void initializeFrames(Image var1, int var2, int var3, boolean var4) {
      int var5 = var1.getWidth();
      int var6 = var1.getHeight();
      int var7 = var5 / var2;
      int var8 = var6 / var3;
      this.sourceImage = var1;
      this.srcFrameWidth = var2;
      this.srcFrameHeight = var3;
      this.numberFrames = var7 * var8;
      this.frameCoordsX = new int[this.numberFrames];
      this.frameCoordsY = new int[this.numberFrames];
      if (!var4) {
         this.sequenceIndex = 0;
      }

      if (!this.customSequenceDefined) {
         this.frameSequence = new int[this.numberFrames];
      }

      int var9 = 0;

      for (int var10 = 0; var10 < var6; var10 += var3) {
         for (int var11 = 0; var11 < var5; var11 += var2) {
            this.frameCoordsX[var9] = var11;
            this.frameCoordsY[var9] = var10;
            if (!this.customSequenceDefined) {
               this.frameSequence[var9] = var9;
            }

            var9++;
         }
      }
   }

   private void initCollisionRectBounds() {
      this.collisionRectX = 0;
      this.collisionRectY = 0;
      this.collisionRectWidth = super.width;
      this.collisionRectHeight = super.height;
   }

   private boolean intersectRect(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return var5 < var3 && var6 < var4 && var7 > var1 && var8 > var2;
   }

   private static boolean doPixelCollision(int var0, int var1, int var2, int var3, Image var4, int var5, Image var6, int var7, int var8, int var9) {
      int var16 = var9 * var8;
      int[] var17 = new int[var16];
      int[] var18 = new int[var16];
      int var10;
      int var11;
      int var12;
      if (0 != (var5 & 4)) {
         if (0 != (var5 & 1)) {
            var11 = -var9;
            var10 = var16 - var9;
         } else {
            var11 = var9;
            var10 = 0;
         }

         if (0 != (var5 & 2)) {
            var12 = -1;
            var10 += var9 - 1;
         } else {
            var12 = 1;
         }

         var4.getRGB(var17, 0, var9, var0, var1, var9, var8);
      } else {
         if (0 != (var5 & 1)) {
            var10 = var16 - var8;
            var12 = -var8;
         } else {
            var10 = 0;
            var12 = var8;
         }

         if (0 != (var5 & 2)) {
            var11 = -1;
            var10 += var8 - 1;
         } else {
            var11 = 1;
         }

         var4.getRGB(var17, 0, var8, var0, var1, var8, var9);
      }

      int var13;
      int var14;
      int var15;
      if (0 != (var7 & 4)) {
         if (0 != (var7 & 1)) {
            var14 = -var9;
            var13 = var16 - var9;
         } else {
            var14 = var9;
            var13 = 0;
         }

         if (0 != (var7 & 2)) {
            var15 = -1;
            var13 += var9 - 1;
         } else {
            var15 = 1;
         }

         var6.getRGB(var18, 0, var9, var2, var3, var9, var8);
      } else {
         if (0 != (var7 & 1)) {
            var13 = var16 - var8;
            var15 = -var8;
         } else {
            var13 = 0;
            var15 = var8;
         }

         if (0 != (var7 & 2)) {
            var14 = -1;
            var13 += var8 - 1;
         } else {
            var14 = 1;
         }

         var6.getRGB(var18, 0, var8, var2, var3, var8, var9);
      }

      int var23 = 0;
      int var21 = var10;
      int var22 = var13;

      while (var23 < var9) {
         int var24 = 0;
         int var19 = var21;
         int var20 = var22;

         while (var24 < var8) {
            if ((var17[var19] & 0xFF000000) != 0 && (var18[var20] & 0xFF000000) != 0) {
               return true;
            }

            var19 += var11;
            var20 += var14;
            var24++;
         }

         var21 += var12;
         var22 += var15;
         var23++;
      }

      return false;
   }

   private int getImageTopLeftX(int var1, int var2, int var3, int var4) {
      int var5 = 0;
      switch (this.t_currentTransformation) {
         case -1:
            break;
         case 0:
         case 1:
         default:
            var5 = var1 - super.x;
            break;
         case 2:
         case 3:
            var5 = super.x + super.width - var3;
            break;
         case 4:
         case 5:
            var5 = var2 - super.y;
            break;
         case 6:
         case 7:
            var5 = super.y + super.height - var4;
      }

      return var5 + this.frameCoordsX[this.frameSequence[this.sequenceIndex]];
   }

   private int getImageTopLeftY(int var1, int var2, int var3, int var4) {
      int var5 = 0;
      switch (this.t_currentTransformation) {
         case -1:
            break;
         case 0:
         case 2:
         default:
            var5 = var2 - super.y;
            break;
         case 1:
         case 3:
            var5 = super.y + super.height - var4;
            break;
         case 4:
         case 6:
            var5 = var1 - super.x;
            break;
         case 5:
         case 7:
            var5 = super.x + super.width - var3;
      }

      return var5 + this.frameCoordsY[this.frameSequence[this.sequenceIndex]];
   }

   private void setTransformImpl(int var1) {
      super.x = super.x + this.getTransformedPtX(this.dRefX, this.dRefY, this.t_currentTransformation) - this.getTransformedPtX(this.dRefX, this.dRefY, var1);
      super.y = super.y + this.getTransformedPtY(this.dRefX, this.dRefY, this.t_currentTransformation) - this.getTransformedPtY(this.dRefX, this.dRefY, var1);
      this.computeTransformedBounds(var1);
      this.t_currentTransformation = var1;
   }

   private void computeTransformedBounds(int var1) {
      switch (var1) {
         case -1:
            throw new Object();
         case 0:
         default:
            this.t_collisionRectX = this.collisionRectX;
            this.t_collisionRectY = this.collisionRectY;
            this.t_collisionRectWidth = this.collisionRectWidth;
            this.t_collisionRectHeight = this.collisionRectHeight;
            super.width = this.srcFrameWidth;
            super.height = this.srcFrameHeight;
            return;
         case 1:
            this.t_collisionRectY = this.srcFrameHeight - (this.collisionRectY + this.collisionRectHeight);
            this.t_collisionRectX = this.collisionRectX;
            this.t_collisionRectWidth = this.collisionRectWidth;
            this.t_collisionRectHeight = this.collisionRectHeight;
            super.width = this.srcFrameWidth;
            super.height = this.srcFrameHeight;
            return;
         case 2:
            this.t_collisionRectX = this.srcFrameWidth - (this.collisionRectX + this.collisionRectWidth);
            this.t_collisionRectY = this.collisionRectY;
            this.t_collisionRectWidth = this.collisionRectWidth;
            this.t_collisionRectHeight = this.collisionRectHeight;
            super.width = this.srcFrameWidth;
            super.height = this.srcFrameHeight;
            return;
         case 3:
            this.t_collisionRectX = this.srcFrameWidth - (this.collisionRectWidth + this.collisionRectX);
            this.t_collisionRectY = this.srcFrameHeight - (this.collisionRectHeight + this.collisionRectY);
            this.t_collisionRectWidth = this.collisionRectWidth;
            this.t_collisionRectHeight = this.collisionRectHeight;
            super.width = this.srcFrameWidth;
            super.height = this.srcFrameHeight;
            return;
         case 4:
            this.t_collisionRectY = this.collisionRectX;
            this.t_collisionRectX = this.collisionRectY;
            this.t_collisionRectHeight = this.collisionRectWidth;
            this.t_collisionRectWidth = this.collisionRectHeight;
            super.width = this.srcFrameHeight;
            super.height = this.srcFrameWidth;
            return;
         case 5:
            this.t_collisionRectX = this.srcFrameHeight - (this.collisionRectHeight + this.collisionRectY);
            this.t_collisionRectY = this.collisionRectX;
            this.t_collisionRectHeight = this.collisionRectWidth;
            this.t_collisionRectWidth = this.collisionRectHeight;
            super.width = this.srcFrameHeight;
            super.height = this.srcFrameWidth;
            return;
         case 6:
            this.t_collisionRectX = this.collisionRectY;
            this.t_collisionRectY = this.srcFrameWidth - (this.collisionRectWidth + this.collisionRectX);
            this.t_collisionRectHeight = this.collisionRectWidth;
            this.t_collisionRectWidth = this.collisionRectHeight;
            super.width = this.srcFrameHeight;
            super.height = this.srcFrameWidth;
            return;
         case 7:
            this.t_collisionRectX = this.srcFrameHeight - (this.collisionRectHeight + this.collisionRectY);
            this.t_collisionRectY = this.srcFrameWidth - (this.collisionRectWidth + this.collisionRectX);
            this.t_collisionRectHeight = this.collisionRectWidth;
            this.t_collisionRectWidth = this.collisionRectHeight;
            super.width = this.srcFrameHeight;
            super.height = this.srcFrameWidth;
      }
   }

   int getTransformedPtX(int var1, int var2, int var3) {
      int var4 = 0;
      switch (var3) {
         case -1:
            throw new Object();
         case 0:
         default:
            var4 = var1;
            break;
         case 1:
            var4 = var1;
            break;
         case 2:
            var4 = this.srcFrameWidth - var1 - 1;
            break;
         case 3:
            var4 = this.srcFrameWidth - var1 - 1;
            break;
         case 4:
            var4 = var2;
            break;
         case 5:
            var4 = this.srcFrameHeight - var2 - 1;
            break;
         case 6:
            var4 = var2;
            break;
         case 7:
            var4 = this.srcFrameHeight - var2 - 1;
      }

      return var4;
   }

   int getTransformedPtY(int var1, int var2, int var3) {
      int var4 = 0;
      switch (var3) {
         case -1:
            throw new Object();
         case 0:
         default:
            var4 = var2;
            break;
         case 1:
            var4 = this.srcFrameHeight - var2 - 1;
            break;
         case 2:
            var4 = var2;
            break;
         case 3:
            var4 = this.srcFrameHeight - var2 - 1;
            break;
         case 4:
            var4 = var1;
            break;
         case 5:
            var4 = var1;
            break;
         case 6:
            var4 = this.srcFrameWidth - var1 - 1;
            break;
         case 7:
            var4 = this.srcFrameWidth - var1 - 1;
      }

      return var4;
   }
}
