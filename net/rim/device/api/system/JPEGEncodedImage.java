package net.rim.device.api.system;

import net.rim.vm.TraceBack;

public final class JPEGEncodedImage extends EncodedImage {
   private JPEGEncodedImage$JPEGImageInfo _jpegInfo;
   public static final int FILETYPE_UNKNOWN;
   public static final int FILETYPE_JFIF;
   public static final int FILETYPE_EXIF;
   public static final int FILETYPE_SPIFF;
   public static final int FRAMETYPE_BASELINE;
   public static final int FRAMETYPE_SEQUENTIAL;
   public static final int FRAMETYPE_PROGRESSIVE;

   JPEGEncodedImage(byte[] var1, int var2, int var3) {
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.$initJPEGImage();
   }

   JPEGEncodedImage(String var1) {
      super._filename = var1;
      this.$initJPEGImage();
   }

   public JPEGEncodedImage(Bitmap var1, int var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      byte[] var3 = getJPEGData(var1, var2);
      super._data = var3;
      super._offset = 0;
      super._length = var3.length;
      this.$initJPEGImage();
   }

   private final void $initJPEGImage() {
      this._jpegInfo = new JPEGEncodedImage$JPEGImageInfo();
      super._info = this._jpegInfo;
      this.populateJPEGInfo();
      super._frameInfo = new EncodedImage$FrameInfo[1];
      super._frameInfo[0] = new EncodedImage$FrameInfo();
      super._frameInfo[0].width = this._jpegInfo.width;
      super._frameInfo[0].height = this._jpegInfo.height;
      super._frameInfo[0].isMonochrome = this._jpegInfo.isMonochrome;
      super._frameInfo[0].hasTransparency = this._jpegInfo.hasTransparency;
   }

   public static final JPEGEncodedImage encode(Bitmap var0, int var1) {
      byte[] var2 = getJPEGData(var0, var1);
      return new JPEGEncodedImage(var2, 0, var2.length);
   }

   public final boolean isColor() {
      return this._jpegInfo.isColour;
   }

   public final int getFileType() {
      return this._jpegInfo.fileType;
   }

   public final int getFrameType() {
      return this._jpegInfo.frameType;
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
      Object var5 = new Object(this.getBitmapType(var1), var3, var4, null, var2, false);
      this.getJPEGImage((Bitmap)var5, super._scaleX, super._scaleY, super._decodeMode);
      ((Bitmap)var5).setAlphaDirect(null);
      return (Bitmap)var5;
   }

   public static final boolean isJPEGSupported() {
      return true;
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

   private final native void populateJPEGInfo();

   private final native void getJPEGImage(Bitmap var1, int var2, int var3, int var4);

   private static final native byte[] getJPEGData(Bitmap var0, int var1);
}
