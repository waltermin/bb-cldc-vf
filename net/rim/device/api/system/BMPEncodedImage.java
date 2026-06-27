package net.rim.device.api.system;

public final class BMPEncodedImage extends EncodedImage {
   private BMPEncodedImage$BMPImageInfo _bmpInfo;

   BMPEncodedImage(byte[] var1, int var2, int var3) {
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.init();
   }

   private final void init() {
      this._bmpInfo = new BMPEncodedImage$BMPImageInfo();
      super._info = this._bmpInfo;
      this.populateBMPInfo();
      super._frameInfo = new EncodedImage$FrameInfo[1];
      super._frameInfo[0] = (EncodedImage$FrameInfo)(new Object());
      super._frameInfo[0].width = this._bmpInfo.width;
      super._frameInfo[0].height = this._bmpInfo.height;
      super._frameInfo[0].isMonochrome = this._bmpInfo.isMonochrome;
      super._frameInfo[0].hasTransparency = this._bmpInfo.hasTransparency;
   }

   BMPEncodedImage(String var1) {
      super._filename = var1;
      this.init();
   }

   public final int getBitDepth() {
      return this._bmpInfo.bitDepth;
   }

   @Override
   public final int getBitmapType(int var1) {
      if (var1 >= 0 && var1 < super._info.frameCount) {
         return Bitmap.DEFAULT_TYPE;
      } else {
         throw new Object();
      }
   }

   @Override
   public final int getAlphaType(int var1) {
      if (var1 >= 0 && var1 < super._info.frameCount) {
         return 0;
      } else {
         throw new Object();
      }
   }

   @Override
   final Bitmap getBitmapImpl(int var1) {
      if (var1 != 0) {
         throw new Object();
      }

      boolean var2 = (super._decodeMode & 4) != 0;
      int var3 = this.getScaledWidth();
      int var4 = this.getScaledHeight();
      Bitmap var5 = new Bitmap(this.getBitmapType(var1), var3, var4, null, var2, false);
      this.getBMPImage(var5, super._scaleX, super._scaleY, super._decodeMode);
      return var5;
   }

   static final native boolean isBMPSupported();

   @Override
   public final String getMIMEType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final native void populateBMPInfo();

   private final native void getBMPImage(Bitmap var1, int var2, int var3, int var4);
}
