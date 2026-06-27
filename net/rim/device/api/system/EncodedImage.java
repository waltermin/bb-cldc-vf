package net.rim.device.api.system;

import java.util.Enumeration;
import javax.microedition.media.control.MetaDataControl;
import net.rim.device.api.math.Fixed32;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.api.util.ToIntHashtable;
import net.rim.device.internal.media.metadata.MetadataHandlerFactory;
import net.rim.vm.TraceBack;

public class EncodedImage {
   EncodedImage$ImageInfo _info;
   EncodedImage$FrameInfo[] _frameInfo;
   int _decodeMode;
   int _scaleX;
   int _scaleY;
   byte[] _data;
   int _offset;
   int _length;
   String _filename;
   Bitmap _cacheBitmap;
   int _cacheDecodeMode;
   int _cacheScaleX;
   int _cacheScaleY;
   int _decodeSteps;
   private static ToIntHashtable _mimeTypes;
   public static final int DECODE_ALPHA;
   public static final int DECODE_NATIVE;
   public static final int DECODE_READONLY;
   public static final int DECODE_NO_DITHER;
   private static final int IMAGE_TYPE_UNKNOWN;
   public static final int IMAGE_TYPE_GIF;
   public static final int IMAGE_TYPE_PNG;
   public static final int IMAGE_TYPE_JPEG;
   public static final int IMAGE_TYPE_WBMP;
   public static final int IMAGE_TYPE_BMP;
   public static final int IMAGE_TYPE_TIFF;
   public static final int IMAGE_TYPE_RWI;
   public static final int IMAGE_TYPE_RGI;

   EncodedImage() {
   }

   public static EncodedImage createEncodedImage(byte[] var0, int var1, int var2) {
      return createEncodedImage(var0, var1, var2, null);
   }

   public static EncodedImage createEncodedImage(byte[] var0, int var1, int var2, String var3) {
      int var4;
      if (var3 == null) {
         var4 = getImageType(var0, var1, var2);
      } else {
         var4 = getMIMETypes().get(StringUtilities.toLowerCase(var3, 1701707776));
      }

      switch (var4) {
         case 0:
            throw new Object();
         case 1:
         default:
            return new GIFEncodedImage(var0, var1, var2);
         case 2:
            return new PNGEncodedImage(var0, var1, var2);
         case 3:
            if (JPEGEncodedImage.isJPEGSupported()) {
               return new JPEGEncodedImage(var0, var1, var2);
            }

            throw new Object();
         case 4:
            return new WBMPEncodedImage(var0, var1, var2);
         case 5:
            if (BMPEncodedImage.isBMPSupported()) {
               return (EncodedImage)(new Object(var0, var1, var2));
            }

            throw new Object();
         case 6:
            if (TIFFEncodedImage.isTIFFSupported()) {
               return new TIFFEncodedImage(var0, var1, var2);
            }

            throw new Object();
         case 7:
         case 8:
            if (ProgressiveImage.isProgressiveSupported()) {
               return new ProgressiveImage(var0, var1, var2, true);
            } else {
               throw new Object();
            }
      }
   }

   public static EncodedImage createEncodedImage(String var0, String var1) {
      int var2;
      if (var1 == null) {
         var2 = 1;
      } else {
         var2 = getMIMETypes().get(StringUtilities.toLowerCase(var1, 1701707776));
      }

      switch (var2) {
         case 0:
            throw new Object();
         case 1:
         default:
            return new GIFEncodedImage(var0);
         case 2:
            return new PNGEncodedImage(var0);
         case 3:
            if (JPEGEncodedImage.isJPEGSupported()) {
               return new JPEGEncodedImage(var0);
            }

            throw new Object();
         case 4:
            return new WBMPEncodedImage(var0);
         case 5:
            if (BMPEncodedImage.isBMPSupported()) {
               return (EncodedImage)(new Object(var0));
            }

            throw new Object();
         case 6:
            if (TIFFEncodedImage.isTIFFSupported()) {
               return new TIFFEncodedImage(var0);
            } else {
               throw new Object();
            }
      }
   }

   public static EncodedImage getEncodedImageResource(String var0) {
      return getEncodedImageResource(null, var0);
   }

   public static EncodedImage getEncodedImageResource(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setDecodeMode(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getDecodeMode() {
      return this._decodeMode;
   }

   private static ToIntHashtable getMIMETypes() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static Enumeration getSupportedMIMETypes() {
      return getMIMETypes().keys();
   }

   public void setScale(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int getScale() {
      return Fixed32.toInt(this._scaleX);
   }

   public void setScaleX32(int var1) {
      if (var1 <= 0) {
         throw new Object();
      }

      this._scaleX = var1;
   }

   public void setScaleY32(int var1) {
      if (var1 <= 0) {
         throw new Object();
      }

      this._scaleY = var1;
   }

   public int getScaleX32() {
      return this._scaleX;
   }

   public int getScaleY32() {
      return this._scaleY;
   }

   public EncodedImage scaleImage32(int var1, int var2) {
      EncodedImage var3 = createEncodedImage(this._data, this._offset, this._length);
      var3.setScaleX32(var1);
      var3.setScaleY32(var2);
      return var3;
   }

   public int getImageType() {
      return this._info.imageType;
   }

   public MetaDataControl getMetaData() {
      return MetadataHandlerFactory.extract(this);
   }

   public int getWidth() {
      return this._info.width;
   }

   public final int getScaledWidth() {
      return Fixed32.divtoInt(Fixed32.toFP(this._info.width), this._scaleX);
   }

   public int getHeight() {
      return this._info.height;
   }

   public final int getScaledHeight() {
      return Fixed32.divtoInt(Fixed32.toFP(this._info.height), this._scaleY);
   }

   public int getFrameCount() {
      return this._info.frameCount;
   }

   private boolean isCacheable(int var1) {
      if ((this._decodeMode & 4) == 0) {
         return false;
      } else {
         return (this.getBitmapType(var1) & 7) > 1 ? false : (this.getAlphaType(var1) & 7) <= 1;
      }
   }

   public static native boolean isCFISupported();

   public static boolean isMIMETypeSupported(String var0) {
      return getMIMETypes().get(StringUtilities.toLowerCase(var0, 1701707776)) != -1;
   }

   public boolean isMonochrome() {
      return this._info.isMonochrome;
   }

   public static boolean isImageValid(byte[] var0, int var1, int var2) {
      int var3 = getImageType(var0, var1, var2);
      switch (var3) {
         case 0:
            return false;
         case 1:
         case 2:
         case 4:
         default:
            return true;
         case 3:
            return JPEGEncodedImage.isJPEGSupported();
         case 5:
            return BMPEncodedImage.isBMPSupported();
         case 6:
            return TIFFEncodedImage.isTIFFSupported();
         case 7:
         case 8:
            return ProgressiveImage.isProgressiveSupported();
      }
   }

   public boolean hasTransparency() {
      return this._info.hasTransparency;
   }

   public int getFrameWidth(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return this._frameInfo[var1].width;
      } else {
         throw new Object();
      }
   }

   public int getFrameHeight(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return this._frameInfo[var1].height;
      } else {
         throw new Object();
      }
   }

   public int getScaledFrameWidth(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return Fixed32.toRoundedInt(Fixed32.div(Fixed32.toFP(this._frameInfo[var1].width), this._scaleX));
      } else {
         throw new Object();
      }
   }

   public int getScaledFrameHeight(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return Fixed32.toRoundedInt(Fixed32.div(Fixed32.toFP(this._frameInfo[var1].height), this._scaleY));
      } else {
         throw new Object();
      }
   }

   public boolean getFrameMonochrome(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return this._frameInfo[var1].isMonochrome;
      } else {
         throw new Object();
      }
   }

   public boolean getFrameTransparency(int var1) {
      if (var1 >= 0 && var1 <= this._info.frameCount) {
         return this._frameInfo[var1].hasTransparency;
      } else {
         throw new Object();
      }
   }

   public Bitmap getBitmap() {
      return this.getBitmap(0);
   }

   public Bitmap getBitmap(int var1) {
      if (this._cacheBitmap != null && this._decodeMode == this._cacheDecodeMode && this._scaleX == this._cacheScaleX && this._scaleY == this._cacheScaleY) {
         return this._cacheBitmap;
      }

      Bitmap var2 = this.getBitmapImpl(var1);
      if (this.isCacheable(var1)) {
         this._cacheBitmap = var2;
         this._cacheDecodeMode = this._decodeMode;
         this._cacheScaleX = this._scaleX;
         this._cacheScaleY = this._scaleY;
      }

      return var2;
   }

   public String getMIMEType() {
      throw null;
   }

   Bitmap getBitmapImpl(int var1) {
      throw null;
   }

   public final byte[] getData() {
      return this._data;
   }

   public final int getOffset() {
      return this._offset;
   }

   public final int getLength() {
      return this._length;
   }

   public int getBitmapType(int var1) {
      throw null;
   }

   public int getAlphaType(int var1) {
      throw null;
   }

   private static final native int getImageType(byte[] var0, int var1, int var2);

   public EncodedImage getStandardsCompliantEncodedImage() {
      return this;
   }

   public EncodedImage getReplacementImage(int var1, int var2) {
      return this;
   }

   public final void setDecodeSteps(int var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._decodeSteps = var1;
   }
}
