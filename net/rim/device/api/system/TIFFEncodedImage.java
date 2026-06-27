package net.rim.device.api.system;

public final class TIFFEncodedImage extends EncodedImage {
   private TIFFEncodedImage$TIFFImageInfo _tiffInfo;
   private TIFFEncodedImage$TIFFFrameInfo[] _tiffFrameInfo;

   TIFFEncodedImage(byte[] var1, int var2, int var3) {
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.init();
   }

   private final void init() {
      this._tiffInfo = new TIFFEncodedImage$TIFFImageInfo();
      super._info = this._tiffInfo;
      this.populateTIFFInfo();
      this._tiffFrameInfo = new TIFFEncodedImage$TIFFFrameInfo[this._tiffInfo.frameCount];
      super._frameInfo = this._tiffFrameInfo;

      for (int var1 = 0; var1 < this._tiffInfo.frameCount; var1++) {
         this._tiffFrameInfo[var1] = new TIFFEncodedImage$TIFFFrameInfo();
      }

      this.populateTIFFFrameInfo();
   }

   TIFFEncodedImage(String var1) {
      super._filename = var1;
      this.init();
   }

   @Override
   public final int getBitmapType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 2) == 0 && this._tiffFrameInfo[var1].isMonochrome ? Bitmap.DEFAULT_TYPE & 128 | 0 | 1 : Bitmap.DEFAULT_TYPE;
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

         this.getTIFFImage((Bitmap)var7, (Bitmap)var8, super._scaleX, super._scaleY, -1, var1, super._decodeMode);
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

   static final native boolean isTIFFSupported();

   private final native void populateTIFFInfo();

   private final native void populateTIFFFrameInfo();

   private final native void getTIFFImage(Bitmap var1, Bitmap var2, int var3, int var4, int var5, int var6, int var7);
}
