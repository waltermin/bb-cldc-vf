package net.rim.device.api.system;

public class ProgressiveImage extends EncodedImage {
   private boolean _appMode;
   private int _nextMissingChunk;
   private ProgressiveImage$DataChunk[] _outOfOrderChunks;
   private EncodedImage _standardEncoding;
   private boolean _isReplacementNecessary;
   private boolean _isReplacementNecessaryDetermined;
   private static final int REPLACEMENT_IMAGE_THRESHOLD;

   public ProgressiveImage(byte[] var1, int var2, int var3, boolean var4) {
      this._appMode = var4;
      int var5 = this.initInfo(var1, var2, var3);
      this._standardEncoding = null;
      this._isReplacementNecessaryDetermined = false;
      if (var4) {
         super._data = var1;
         super._offset = var2;
         super._length = var3;
         this._nextMissingChunk = var5;
      } else {
         if (var5 > 0) {
            this._outOfOrderChunks = new ProgressiveImage$DataChunk[var5];

            for (int var6 = var5 - 1; var6 >= 0; var6--) {
               this._outOfOrderChunks[var6] = new ProgressiveImage$DataChunk(null);
            }
         }

         super._data = new byte[0];
         initializeImage(var1, var2, var3, super._data);
         super._offset = 0;
         super._length = super._data.length;
         this.appendDataChunks(var1, var2, var3);
      }
   }

   private int initInfo(byte[] var1, int var2, int var3) {
      ProgressiveImage$ProgressiveImageInfo var4 = new ProgressiveImage$ProgressiveImageInfo(null);
      getImageInfo(var1, var2, var3, var4);
      super._info = var4;
      EncodedImage$FrameInfo[] var5 = new EncodedImage$FrameInfo[var4.frameCount];

      for (int var6 = var4.frameCount - 1; var6 >= 0; var6--) {
         var5[var6] = new EncodedImage$FrameInfo();
      }

      getFrameInfo(var1, var2, var3, var5);
      super._frameInfo = var5;
      return var4._numChunks;
   }

   public boolean appendDataChunks(byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int updateLength(int var1) {
      if (!this._appMode) {
         throw new UnsupportedOperationException();
      }

      int var2 = super._length;
      super._length = var1;
      return var2;
   }

   public int getNumSegmentsObtained() {
      return this._appMode ? getSegmentsInFile(super._data, super._offset, super._length) : this._nextMissingChunk;
   }

   public int getNumTotalSegments() {
      return ((ProgressiveImage$ProgressiveImageInfo)super._info)._numChunks;
   }

   @Override
   public String getMIMEType() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public int getBitmapType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 2) == 0 && super._frameInfo[var1].isMonochrome ? Bitmap.DEFAULT_TYPE & 128 | 0 | 1 : Bitmap.DEFAULT_TYPE;
      }
   }

   @Override
   public int getAlphaType(int var1) {
      if (var1 < 0 || var1 >= super._info.frameCount) {
         throw new Object();
      } else {
         return (super._decodeMode & 1) != 0 && this.getFrameTransparency(var1) ? Bitmap.DEFAULT_TYPE & 128 | 0 | 1 : 0;
      }
   }

   @Override
   Bitmap getBitmapImpl(int var1) {
      if (var1 >= 0 && var1 < super._info.frameCount) {
         boolean var2 = (super._decodeMode & 4) != 0;
         int var3 = this.getAlphaType(var1);
         int var4 = this.getFrameWidth(var1);
         int var5 = this.getFrameHeight(var1);
         Object var6 = new Object(this.getBitmapType(var1), var4, var5, null, var2, false);
         Object var7 = null;
         if (var3 != 0) {
            var7 = new Object(var3, var4, var5, null, var2, false);
         }

         decodeBitmaps((Bitmap)var6, (Bitmap)var7, super._data, super._offset, super._length, var1, super._scaleX, super._scaleY, super._decodeMode);
         ((Bitmap)var6).setAlphaDirect((Bitmap)var7);
         return (Bitmap)var6;
      } else {
         throw new Object();
      }
   }

   @Override
   public EncodedImage getStandardsCompliantEncodedImage() {
      if (this._standardEncoding == null) {
         byte[] var1 = getStandardEncodedData(super._data, super._offset, super._length);
         if (var1 == super._data) {
            return this;
         }

         this._standardEncoding = EncodedImage.createEncodedImage(var1, 0, var1.length);
      }

      return this._standardEncoding;
   }

   @Override
   public EncodedImage getReplacementImage(int var1, int var2) {
      return this.isReplacementImageNecessary(var1, var2) && this.getNumTotalSegments() == this.getNumSegmentsObtained()
         ? this.getStandardsCompliantEncodedImage()
         : this;
   }

   public boolean isReplacementImageNecessary(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static native boolean isProgressiveSupported();

   public static native byte[] getImageIdentifier(byte[] var0, int var1, int var2);

   private static native void getImageInfo(byte[] var0, int var1, int var2, ProgressiveImage$ProgressiveImageInfo var3);

   private static native void getFrameInfo(byte[] var0, int var1, int var2, EncodedImage$FrameInfo[] var3);

   private static native void initializeImage(byte[] var0, int var1, int var2, byte[] var3);

   private static native void parseProgressiveChunks(byte[] var0, int var1, int var2, ProgressiveImage$DataChunk[] var3, int var4);

   private static native int appendChunk(byte[] var0, int var1, int var2, ProgressiveImage$DataChunk var3);

   private static native void decodeBitmaps(Bitmap var0, Bitmap var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8);

   private static final native byte[] getStandardEncodedData(byte[] var0, int var1, int var2);

   private static final native int getSegmentsInFile(byte[] var0, int var1, int var2);

   public static final native int getRWIVersion(byte[] var0, int var1, int var2);
}
