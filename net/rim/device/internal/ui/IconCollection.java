package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.MathUtilities;
import net.rim.vm.TraceBack;

public class IconCollection {
   private int _columns;
   private int _rows;
   private String _moduleName;
   private EncodedImage[] _defaultImages = new EncodedImage[0];
   private int[] _defaultHeights = new int[0];
   private int[] _defaultWidths = new int[0];
   private EncodedImage[] _images = new EncodedImage[0];
   private int[] _heights = new int[0];
   private int[] _widths = new int[0];
   private boolean _hasThemedImages;
   private Bitmap _cachedBitmap;
   private int _cachedIndex;
   private int _iconWidth;
   private Font _currentFont;

   public IconCollection(int var1, int var2) {
      this(var1, var2, null);
   }

   public IconCollection(int var1, int var2, String var3) {
      if (var1 > 0 && var2 > 0) {
         this._columns = var1;
         this._rows = var2;
         this._moduleName = var3;
      } else {
         throw new Object();
      }
   }

   public synchronized void addImage(EncodedImage var1, int var2, int var3, boolean var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   void addImage(byte[] var1, boolean var2) {
   }

   public synchronized void clear() {
      this._heights = Arrays.copy(this._defaultHeights);
      this._widths = Arrays.copy(this._defaultWidths);
      this._images = new EncodedImage[this._defaultImages.length];

      for (int var1 = this._defaultImages.length - 1; var1 >= 0; var1--) {
         this._images[var1] = this._defaultImages[var1];
      }

      this._currentFont = null;
      this._hasThemedImages = false;
      this._cachedIndex = -1;
   }

   public static synchronized IconCollection get(String var0, int var1) {
      String var2 = TraceBack.getCallingModuleName(0);
      return ThemeManager.getIconCollection(var0, var1, 1, var2);
   }

   public static synchronized IconCollection get(String var0, int var1, int var2) {
      String var3 = TraceBack.getCallingModuleName(0);
      return ThemeManager.getIconCollection(var0, var1, var2, var3);
   }

   public int getHeight(int var1, int var2) {
      return this._heights[this.getIndexForSize(var2, false)];
   }

   public Image getImage(int var1) {
      return new IconCollection$ProxiedImage(this, var1, null);
   }

   private int getIndexForSize(int var1, boolean var2) {
      int[] var3 = var2 ? this._defaultHeights : this._heights;
      int var4 = Arrays.binarySearch(var3, var1);
      if (var4 < 0) {
         var4 = -var4 - 2;
      }

      return MathUtilities.clamp(0, var4, var3.length - 1);
   }

   public int getWidth(int var1, int var2) {
      return this._widths[this.getIndexForSize(var2, false)];
   }

   public int getWidth(Font var1) {
      if (this._currentFont == null || !this._currentFont.equals(var1)) {
         this._currentFont = var1;
         int var2 = var1.getHeight();
         this._iconWidth = this.getWidth(var2, var2);
      }

      return this._iconWidth;
   }

   public int getHeight(Font var1) {
      int var2 = var1.getHeight();
      return this.getHeight(var2, var2);
   }

   public boolean isDefaultSet() {
      return this._defaultImages.length != 0;
   }

   public int paint(Graphics var1, int var2, int var3, int var4, int var5) {
      Font var6 = var1.getFont();
      int var7 = this.getWidth(var6);
      return this.paint(var1, var2, var3, var7, var4, var5);
   }

   public int paint(Graphics var1, int var2, int var3, int var4) {
      Font var5 = var1.getFont();
      int var6 = this.getWidth(var5);
      return this.paint(var1, var2, var3, var6, var5.getHeight(), var4);
   }

   public int paint(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      return this.paint(var1, var2, var3, var4, var5, var6 & 65535, var6 >> 16);
   }

   public int paint(Graphics var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      int var8 = this.getIndexForSize(var5, false);
      int var9 = this._widths[var8];
      int var10 = this._heights[var8];
      if (var8 != this._cachedIndex) {
         this._cachedBitmap = this._images[var8].getBitmap();
         this._cachedIndex = var8;
      }

      Bitmap var11 = this._cachedBitmap;
      int var12 = var6 * var9;
      int var13 = var7 * var10;
      if (var12 >= var11.getWidth() || var13 >= var11.getHeight()) {
         var8 = this.getIndexForSize(var5, true);
         var11 = this._defaultImages[var8].getBitmap();
         var9 = this._defaultWidths[var8];
         var10 = this._defaultHeights[var8];
         var12 = var6 * var9;
         var13 = var7 * var10;
      }

      var2 += Math.max(0, var4 - var9 >> 1);
      var3 += Math.max(0, var5 - var10 >> 1);
      var4 = Math.min(var4, var9);
      var5 = Math.min(var5, var10);
      if (Graphics.isColor() && var11.getType() == 129 && var11.hasAlpha()) {
         var1.rop(-96, var2, var3, var4, var5, var11, var12, var13);
      } else {
         var1.drawBitmap(var2, var3, var4, var5, var11, var12, var13);
      }

      return var9;
   }

   public boolean containsIcon(int var1, int var2) {
      int var3 = this.getIndexForSize(var1, false);
      int var4 = this._widths[var3];
      Bitmap var5 = this._images[var3].getBitmap();
      int var6 = var2 * var4;
      return var6 < var5.getWidth();
   }

   private void render(int var1, int var2) {
   }

   public void verifyModule(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
