package net.rim.device.api.system;

import net.rim.device.api.math.Fixed32;

public final class GIFEncodedImage extends EncodedImage {
   private GIFEncodedImage$GIFImageInfo _gifInfo;
   private GIFEncodedImage$GIFFrameInfo[] _gifFrameInfo;
   public static final int TRANSITION_DEFAULT;
   public static final int TRANSITION_COMBINE;
   public static final int TRANSITION_REPLACE;

   GIFEncodedImage(byte[] var1, int var2, int var3) {
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.init();
   }

   GIFEncodedImage(String var1) {
      super._filename = var1;
      this.init();
   }

   private final void init() {
      this._gifInfo = new GIFEncodedImage$GIFImageInfo();
      super._info = this._gifInfo;
      this.populateGIFInfo();
      this._gifFrameInfo = new GIFEncodedImage$GIFFrameInfo[this._gifInfo.frameCount];
      super._frameInfo = this._gifFrameInfo;

      for (int var1 = 0; var1 < this._gifInfo.frameCount; var1++) {
         this._gifFrameInfo[var1] = new GIFEncodedImage$GIFFrameInfo();
      }

      this.populateGIFFrameInfo();
   }

   public final int getBackgroundColor() {
      return this._gifInfo.backgroundColour;
   }

   public final int getIterations() {
      return this._gifInfo.iterations;
   }

   public final int getFrameLeft(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return this._gifFrameInfo[var1].left;
      } else {
         throw new Object();
      }
   }

   public final int getFrameTop(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return this._gifFrameInfo[var1].top;
      } else {
         throw new Object();
      }
   }

   public final int getScaledFrameLeft(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return Fixed32.toInt(Fixed32.div(Fixed32.toFP(this._gifFrameInfo[var1].left), super._scaleX));
      } else {
         throw new Object();
      }
   }

   public final int getScaledFrameTop(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return Fixed32.toInt(Fixed32.div(Fixed32.toFP(this._gifFrameInfo[var1].top), super._scaleY));
      } else {
         throw new Object();
      }
   }

   public final int getFrameDelay(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return this._gifFrameInfo[var1].frameDelay;
      } else {
         throw new Object();
      }
   }

   public final int getFrameTransition(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return this._gifFrameInfo[var1].frameTransition;
      } else {
         throw new Object();
      }
   }

   public final int getFrameNumColors(int var1) {
      if (var1 >= 0 && var1 <= super._info.frameCount) {
         return this._gifFrameInfo[var1].numColours;
      } else {
         throw new Object();
      }
   }

   public final boolean isBackgroundTransparent() {
      return this._gifInfo.backgroundTransparent;
   }

   @Override
   public final int getBitmapType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 2) == 0 && this._gifFrameInfo[var1].isMonochrome ? Bitmap.DEFAULT_TYPE & 128 | 0 | 1 : Bitmap.DEFAULT_TYPE;
      }
   }

   @Override
   public final int getAlphaType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 1) != 0 && this.getFrameTransparency(var1) ? 1 | Bitmap.DEFAULT_TYPE & 128 : 0;
      }
   }

   @Override
   final Bitmap getBitmapImpl(int var1) {
      if (var1 >= 0 && var1 < super._info.frameCount) {
         boolean var2 = (super._decodeMode & 4) != 0;
         int var3 = this.getBitmapType(var1);
         int var4 = this.getAlphaType(var1);
         int var5 = this.getFrameWidth(var1);
         int var6 = this.getFrameHeight(var1);
         Object var7 = new Object(var3, var5, var6, null, var2, false);
         Object var8 = null;
         if (var4 != 0) {
            var8 = new Object(var4, var5, var6, null, var2, false);
         }

         this.getGIFImage((Bitmap)var7, (Bitmap)var8, super._scaleX, super._scaleY, -1, var1, super._decodeMode);
         ((Bitmap)var7).setAlphaDirect((Bitmap)var8);
         return (Bitmap)var7;
      } else {
         throw new Object();
      }
   }

   @Override
   public final String getMIMEType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final EncodedImage getStandardsCompliantEncodedImage() {
      byte[] var1 = getStandardEncodedData(super._data, super._offset, super._length);
      return var1 == super._data ? this : EncodedImage.createEncodedImage(var1, 0, var1.length);
   }

   private static final native byte[] getStandardEncodedData(byte[] var0, int var1, int var2);

   private final native void populateGIFInfo();

   private final native void populateGIFFrameInfo();

   private final native void getGIFImage(Bitmap var1, Bitmap var2, int var3, int var4, int var5, int var6, int var7);
}
