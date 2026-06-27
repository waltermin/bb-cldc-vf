package net.rim.device.api.system;

public final class WBMPEncodedImage extends EncodedImage {
   private WBMPEncodedImage$WBMPImageInfo _wbmpInfo;

   WBMPEncodedImage(byte[] var1, int var2, int var3) {
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.init();
   }

   private final void init() {
      this._wbmpInfo = new WBMPEncodedImage$WBMPImageInfo();
      super._info = this._wbmpInfo;
      this.populateWBMPInfo();
      super._frameInfo = new EncodedImage$FrameInfo[1];
      super._frameInfo[0] = new EncodedImage$FrameInfo();
      super._frameInfo[0].width = this._wbmpInfo.width;
      super._frameInfo[0].height = this._wbmpInfo.height;
      super._frameInfo[0].isMonochrome = this._wbmpInfo.isMonochrome;
      super._frameInfo[0].hasTransparency = this._wbmpInfo.hasTransparency;
   }

   WBMPEncodedImage(String var1) {
      super._filename = var1;
      this.init();
   }

   public final int getType() {
      return this._wbmpInfo.type;
   }

   @Override
   public final int getBitmapType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 2) != 0 ? Bitmap.DEFAULT_TYPE : Bitmap.DEFAULT_TYPE & 128 | 0 | 1;
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
      Object var5 = new Object(this.getBitmapType(var1), var3, var4, null, var2, false);
      this.getWBMPImage((Bitmap)var5, super._scaleX, super._scaleY, super._decodeMode);
      return (Bitmap)var5;
   }

   @Override
   public final String getMIMEType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final native void populateWBMPInfo();

   private final native void getWBMPImage(Bitmap var1, int var2, int var3, int var4);
}
