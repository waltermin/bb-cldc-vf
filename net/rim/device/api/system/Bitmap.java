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

   public Bitmap(int var1, int var2) {
      this(DEFAULT_TYPE, var1, var2, null, false);
   }

   public Bitmap(int var1, int var2, int var3) {
      this(var1, var2, var3, null, false);
   }

   public Bitmap(int var1, int var2, int var3, byte[] var4) {
      this(var1, var2, var3, var4, false);
   }

   public Bitmap(int var1, int var2, int var3, byte[] var4, boolean var5) {
      this(var1, var2, var3, var4, var5, true);
   }

   Bitmap(int var1, int var2, int var3, byte[] var4, boolean var5, boolean var6) {
      this(var1, var2, var3, var4, var5, var6, false);
   }

   Bitmap(int var1, int var2, int var3, byte[] var4, boolean var5, boolean var6, boolean var7) {
   }

   public static final Bitmap createGreyscaleBitmap(Object var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final Bitmap createScaledBitmap(int var1, int var2) {
      Bitmap var3 = new Bitmap(this._type, var1, var2);
      if (this._alpha != null) {
         switch (this._alpha.getBitsPerPixel()) {
            case 1:
               var3.createAlpha(1);
               return var3;
            default:
               var3.createAlpha(3);
         }
      }

      return var3;
   }

   public final native void getARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7);

   public final Bitmap setRGB565(byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (this._type == 197) {
         this.setRGB565Native(var1, var2, var3, var4, var5, var6, var7);
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

   public final synchronized void setARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final native void promote(Bitmap var1);

   private final native void copyARGB(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

   private static final native int getBppFromARGB(int[] var0, boolean var1);

   public static final Bitmap createBitmapFromPNG(byte[] var0, int var1, int var2) {
      return createBitmapFromBytes(var0, var1, var2, 1, 1, -1, 0);
   }

   public static final Bitmap createBitmapFromPNG(byte[] var0, int var1, int var2, boolean var3) {
      return createBitmapFromBytes(var0, var1, var2, var3 ? 1 : 0, 1, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] var0, int var1, int var2, int var3) {
      return createBitmapFromBytes(var0, var1, var2, 1, var3, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] var0, int var1, int var2, int var3, int var4) {
      return createBitmapFromBytes(var0, var1, var2, var3, var4, -1, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] var0, int var1, int var2, int var3, int var4, int var5) {
      return createBitmapFromBytes(var0, var1, var2, var3, var4, var5, 0);
   }

   public static final Bitmap createBitmapFromBytes(byte[] var0, int var1, int var2, int var3, int var4, int var5, int var6) {
      EncodedImage var7 = EncodedImage.createEncodedImage(var0, var1, var2);
      var7.setDecodeMode(var3);
      var7.setScale(var4);
      return var7.getBitmap(var6);
   }

   @Override
   public final synchronized boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final native boolean compareBitmapData(Bitmap var0, Bitmap var1);

   private final Bitmap cloneBitmap() {
      Bitmap var1 = new Bitmap(this._type, this._width, this._height);
      var1._transColour = this._transColour;
      if (this._data != null) {
         if (var1._data == null) {
            throw new Object();
         }

         System.arraycopy(this._data, 0, var1._data, 0, this._data.length);
         return var1;
      } else {
         if (var1._bands == null) {
            throw new Object();
         }

         if (var1._numBands != this._numBands) {
            throw new Object();
         }

         int var2 = this._numBands;

         for (int var3 = 0; var3 < var2; var3++) {
            byte[][] var4 = this._bands[var3];
            System.arraycopy(var4, 0, var1._bands[var3], 0, var4.length);
         }

         return var1;
      }
   }

   public final boolean hasAlpha() {
      return this._alpha != null;
   }

   public final synchronized void setAlpha(Bitmap var1) {
      if (var1 != null) {
         if (var1._alpha != null || var1._width != this._width || var1._height != this._height) {
            throw new Object();
         }

         if (Display.isRowwise()) {
            if ((var1._type & 128) != 128) {
               throw new Object();
            }
         } else if (var1._type != 1) {
            throw new Object();
         }

         var1 = var1.cloneBitmap();
      }

      this._alpha = var1;
   }

   final void setAlphaDirect(Bitmap var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void createAlpha(int var1) {
      switch (var1) {
         case 0:
            throw new Object();
         case 1:
         default:
            Bitmap var4 = new Bitmap(145, this._width, this._height);
            this.setAlpha(var4);
            return;
         case 2:
            Bitmap var3 = new Bitmap(148, this._width, this._height);
            this.setAlpha(var3);
            return;
         case 3:
            Bitmap var2 = new Bitmap(147, this._width, this._height);
            this.setAlpha(var2);
      }
   }

   public static final Bitmap getBitmapResource(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final Bitmap getBitmapResource(String var0) {
      return getBitmapResource(null, var0);
   }

   public static final Bitmap getPredefinedBitmap(int var0) {
      return ThemeManager.getPredefinedBitmap(var0);
   }

   private static final native void transposeColumnwiseBitmapData(byte[] var0, int var1, byte[] var2, int var3, int var4, int var5, int var6);
}
