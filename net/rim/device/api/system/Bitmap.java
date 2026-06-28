package net.rim.device.api.system;

import net.rim.device.api.ui.theme.ThemeManager;

public final class Bitmap {
   private int _type;
   private int _width;
   private int _height;
   private int _stride;
   private byte[] _data;
   private int _numBands;
   private int _axesPerBand;
   private byte[][][] _bands;
   private Bitmap _alpha;
   private int _cacheId;
   private int _transColour;
   private boolean _readonly;
   public static final int INFORMATION;
   public static final int QUESTION;
   public static final int EXCLAMATION;
   public static final int HOURGLASS;
   static final int BITMAP_ORIENTATION_MASK;
   static final int BITMAP_COLOUR_MASK;
   static final int BITMAP_DEPTH_MASK;
   static final int ORIENTATION_COLUMNWISE;
   static final int ORIENTATION_ROWWISE;
   static final int GREYSCALE;
   static final int COLOUR;
   static final int BITDEPTH_MONO;
   static final int BITDEPTH_2BPP;
   static final int BITDEPTH_4BPP;
   static final int BITDEPTH_16BPP;
   public static final int ALPHA_BITDEPTH_MONO;
   public static final int ALPHA_BITDEPTH_4BPP;
   public static final int ALPHA_BITDEPTH_8BPP;
   public static final int COLUMNWISE_MONOCHROME;
   public static final int ROWWISE_16BIT_COLOR;
   public static final int ROWWISE_MONOCHROME;
   public static final int COLUMNWISE_2BIT_GREYSCALE;
   public static final int ROWWISE_4BIT_GREYSCALE;
   private static final int ROWWISE_8BIT_ALPHA;
   private static final int ROWWISE_4BIT_ALPHA;
   private static final int ROWWISE_1BIT_ALPHA;
   private static final int MAX_BAND_SIZE;
   private static final int BITMAP_BAND_GUARD_SAFETY_SIZE;
   private static final int TRANSPARENCY_COLOR_NONE;
   public static final int TRUE_WHITE;
   public static final int TRUE_BLACK;
   public static final int DECODE_ALPHA;
   public static final int DECODE_NATIVE;
   public static final int DECODE_READONLY;
   public static final int DEFAULT_TYPE;

   public static final int getDefaultType() {
      return DEFAULT_TYPE;
   }

   public Bitmap(int width, int height) {
      this(DEFAULT_TYPE, width, height, null, false);
   }

   public Bitmap(int type, int width, int height) {
      this(type, width, height, null, false);
   }

   public Bitmap(int type, int width, int height, byte[] data) {
      this(type, width, height, data, false);
   }

   public Bitmap(int type, int width, int height, byte[] data, boolean readonly) {
      this(type, width, height, data, readonly, true);
   }

   Bitmap(int type, int width, int height, byte[] data, boolean readonly, boolean externalCall) {
      this(type, width, height, data, readonly, externalCall, false);
   }

   Bitmap(int type, int width, int height, byte[] data, boolean readonly, boolean externalCall, boolean alpha) {
   }

   public static final Bitmap createGreyscaleBitmap(Object src, int width, int height) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final Bitmap createScaledBitmap(int width, int height) {
      Bitmap dst = new Bitmap(this._type, width, height);
      if (this._alpha != null) {
         switch (this._alpha.getBitsPerPixel()) {
            case 1:
               dst.createAlpha(1);
               return dst;
            default:
               dst.createAlpha(3);
         }
      }

      return dst;
   }

   public final native void getARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final Bitmap setRGB565(byte[] rgbData, int offset, int scanLength, int x, int y, int width, int height) {
      if (this._type == 197) {
         this.setRGB565Native(rgbData, offset, scanLength, x, y, width, height);
      }

      return this;
   }

   final native void setRGB565Native(byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final int getBitsPerPixel() {
      return 1 << (this._type & 7) - 1;
   }

   public final int getHeight() {
      return this._height;
   }

   public final native void getScaled(Bitmap var1, int var2);

   public final int getTransColor() {
      return this._transColour;
   }

   public final int getType() {
      return this._type;
   }

   public final int getWidth() {
      return this._width;
   }

   public final boolean isWritable() {
      return !this._readonly;
   }

   public final synchronized void setARGB(int[] data, int offset, int scanLength, int left, int top, int width, int height) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final native void promote(Bitmap var1);

   private final native void copyARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

   private static final native int getBppFromARGB(int[] var0, boolean var1);

   public static final Bitmap createBitmapFromPNG(byte[] png, int offset, int length) {
      return createBitmapFromBytes(png, offset, length, 1, 1, -1, 0);
   }

   public static final Bitmap createBitmapFromPNG(byte[] png, int offset, int length, boolean decodeAlpha) {
      return createBitmapFromBytes(png, offset, length, decodeAlpha ? 1 : 0, 1, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] bytes, int offset, int length, int scale) {
      return createBitmapFromBytes(bytes, offset, length, 1, scale, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] bytes, int offset, int length, int decodeMode, int scale) {
      return createBitmapFromBytes(bytes, offset, length, decodeMode, scale, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] bytes, int offset, int length, int decodeMode, int scale, int trans) {
      return createBitmapFromBytes(bytes, offset, length, decodeMode, scale, trans, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] bytes, int offset, int length, int decodeMode, int scale, int trans, int frameIndex) {
      EncodedImage image = EncodedImage.createEncodedImage(bytes, offset, length);
      image.setDecodeMode(decodeMode);
      image.setScale(scale);
      return image.getBitmap(frameIndex);
   }

   @Override
   public final synchronized boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final native boolean compareBitmapData(Bitmap var0, Bitmap var1);

   private final Bitmap cloneBitmap() {
      Bitmap clone = new Bitmap(this._type, this._width, this._height);
      clone._transColour = this._transColour;
      if (this._data != null) {
         if (clone._data == null) {
            throw new RuntimeException();
         }

         System.arraycopy(this._data, 0, clone._data, 0, this._data.length);
         return clone;
      } else {
         if (clone._bands == null) {
            throw new RuntimeException();
         }

         if (clone._numBands != this._numBands) {
            throw new RuntimeException();
         }

         int bands = this._numBands;

         for (int i = 0; i < bands; i++) {
            byte[] src = (byte[])this._bands[i];
            System.arraycopy(src, 0, clone._bands[i], 0, src.length);
         }

         return clone;
      }
   }

   public final boolean hasAlpha() {
      return this._alpha != null;
   }

   public final synchronized void setAlpha(Bitmap alpha) {
      if (alpha != null) {
         if (alpha._alpha != null || alpha._width != this._width || alpha._height != this._height) {
            throw new IllegalArgumentException();
         }

         if (Display.isRowwise()) {
            if ((alpha._type & 128) != 128) {
               throw new IllegalArgumentException();
            }
         } else if (alpha._type != 1) {
            throw new IllegalArgumentException();
         }

         alpha = alpha.cloneBitmap();
      }

      this._alpha = alpha;
   }

   final void setAlphaDirect(Bitmap alpha) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void createAlpha(int bitDepth) {
      switch (bitDepth) {
         case 0:
            throw new IllegalArgumentException();
         case 1:
         default: {
            Bitmap alpha = new Bitmap(145, this._width, this._height);
            this.setAlpha(alpha);
            return;
         }
         case 2: {
            Bitmap alpha = new Bitmap(148, this._width, this._height);
            this.setAlpha(alpha);
            return;
         }
         case 3: {
            Bitmap alpha = new Bitmap(147, this._width, this._height);
            this.setAlpha(alpha);
         }
      }
   }

   public static final Bitmap getBitmapResource(String module, String name) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final Bitmap getBitmapResource(String name) {
      return getBitmapResource(null, name);
   }

   public static final Bitmap getPredefinedBitmap(int predefinedBitmap) {
      return ThemeManager.getPredefinedBitmap(predefinedBitmap);
   }

   private static final native void transposeColumnwiseBitmapData(byte[] var0, int var1, byte[] var2, int var3, int var4, int var5, int var6);
}
