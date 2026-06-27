package net.rim.device.api.system;

import net.rim.vm.TraceBack;

public final class PNGEncodedImage extends EncodedImage {
   private PNGEncodedImage$PNGImageInfo _pngInfo;

   public PNGEncodedImage(byte[] var1, int var2, int var3) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      super._data = var1;
      super._offset = var2;
      super._length = var3;
      this.init();
   }

   PNGEncodedImage(String var1) {
      super._filename = var1;
      this.init();
   }

   private PNGEncodedImage(Bitmap var1, int var2, int var3, int var4, int var5) {
   }

   public static final PNGEncodedImage encode(Bitmap var0) {
      return new PNGEncodedImage(var0, 0, 0, var0.getWidth(), var0.getHeight());
   }

   public static final PNGEncodedImage encode(Bitmap var0, int var1, int var2, int var3, int var4) {
      return new PNGEncodedImage(var0, var1, var2, var3, var4);
   }

   private final void init() {
      this._pngInfo = new PNGEncodedImage$PNGImageInfo();
      super._info = this._pngInfo;
      this.populatePNGInfo();
      super._frameInfo = new EncodedImage$FrameInfo[1];
      super._frameInfo[0] = new EncodedImage$FrameInfo();
      super._frameInfo[0].width = this._pngInfo.width;
      super._frameInfo[0].height = this._pngInfo.height;
      super._frameInfo[0].isMonochrome = this._pngInfo.isMonochrome;
      super._frameInfo[0].hasTransparency = this._pngInfo.hasTransparency;
   }

   public final int getAlphaBitDepth() {
      return this._pngInfo.alphaBitDepth;
   }

   @Override
   public final int getAlphaType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      }

      if ((super._decodeMode & 1) != 0 && this.getFrameTransparency(var1)) {
         int var2 = 0;
         if (this._pngInfo.alpha && Display.isRowwise()) {
            var2 |= 3;
         } else {
            var2 |= 1;
         }

         return var2 | Bitmap.DEFAULT_TYPE & 128;
      } else {
         return 0;
      }
   }

   public final int getBitDepth() {
      return this._pngInfo.bitDepth;
   }

   @Override
   final Bitmap getBitmapImpl(int var1) {
      if (var1 != 0) {
         throw new Object();
      }

      boolean var2 = (super._decodeMode & 4) != 0;
      int var3 = this.getBitmapType(var1);
      int var4 = this.getAlphaType(var1);
      int var5 = this.getScaledWidth();
      int var6 = this.getScaledHeight();
      Object var7 = new Object(var3, var5, var6, null, var2, false);
      Object var8 = null;
      if (var4 != 0) {
         var8 = new Object(var4, var5, var6, null, var2, false);
      }

      this.getPNGImage((Bitmap)var7, (Bitmap)var8, super._scaleX, super._scaleY, -1, super._decodeSteps, super._decodeMode);
      ((Bitmap)var7).setAlphaDirect((Bitmap)var8);
      return (Bitmap)var7;
   }

   @Override
   public final int getBitmapType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 2) == 0 && this._pngInfo.colourType == 0 && this._pngInfo.bitDepth == 1
            ? Bitmap.DEFAULT_TYPE & 128 | 0 | 1
            : Bitmap.DEFAULT_TYPE;
      }
   }

   public final int getColorType() {
      return this._pngInfo.colourType;
   }

   @Override
   public final String getMIMEType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final native byte[] getPNGData(Bitmap var0, int var1, int var2, int var3, int var4);

   private final native void getPNGImage(Bitmap var1, Bitmap var2, int var3, int var4, int var5, int var6, int var7);

   private final native void populatePNGInfo();

   public final boolean hasAlpha() {
      return this._pngInfo.alpha;
   }
}
