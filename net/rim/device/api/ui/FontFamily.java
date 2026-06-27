package net.rim.device.api.ui;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.CRC32;
import net.rim.device.api.util.LongHashtable;

public class FontFamily {
   private String _name;
   private LongHashtable _fontsUsed = (LongHashtable)(new Object());
   private Font[] _fonts;
   private int[] _heights;
   private int _type;
   private byte[] _hashArray;
   private static int[] FONTSIZES;
   public static String FAMILY_SYSTEM;
   public static int MONO_BITMAP_FONT;
   public static int SCALABLE_FONT;
   public static int UNKNOWN_FONT;

   FontFamily(String var1) {
      this._type = UNKNOWN_FONT;
      this._hashArray = new byte[38];
      this._name = var1;
   }

   public static FontFamily forName(String var0) {
      FontFamily var1 = FontRegistry.get(var0);
      if (var1 == null) {
         throw new Object();
      } else {
         return var1;
      }
   }

   public Font getFont(int var1, int var2) {
      return this.getFont(var1, var2, 0, 1, 0);
   }

   public Font getFont(int var1, int var2, int var3) {
      return this.getFont(var1, var2, var3, 1, 0);
   }

   public Font getFont(int var1, int var2, int var3, int var4, int var5) {
      return this.getFont(var1, var2, var3, var4, var5, 65536, 0, 0, 65536, 0, 0);
   }

   public synchronized Font getFont(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      return this.getFont(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, 0, 16777215);
   }

   public synchronized Font getFont(
      int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13
   ) {
      if (var5 < 0) {
         var5 = 0;
      }

      if (1 > var4 || 4 < var4) {
         var4 = 1;
      }

      var2 = Ui.convertSize(var2, var3, 0);
      if (var2 < 6 || var2 > 128) {
         Font var14 = FontRegistry._getDefaultFont();
         if (var14 != null) {
            var2 = var14.getHeight();
         } else {
            var2 = FontRegistry.DEFAULT_SIZE;
         }
      }

      Font var18 = null;
      this._hashArray[0] = (byte)var2;
      this._hashArray[1] = (byte)var4;
      this._hashArray[2] = (byte)var5;
      this._hashArray[3] = (byte)(var5 >> 8);
      this._hashArray[4] = (byte)(var5 >> 16);
      this._hashArray[5] = (byte)(var5 >> 24);
      this._hashArray[6] = (byte)var6;
      this._hashArray[7] = (byte)(var6 >> 8);
      this._hashArray[8] = (byte)(var6 >> 16);
      this._hashArray[9] = (byte)(var6 >> 24);
      this._hashArray[10] = (byte)var7;
      this._hashArray[11] = (byte)(var7 >> 8);
      this._hashArray[12] = (byte)(var7 >> 16);
      this._hashArray[13] = (byte)(var7 >> 24);
      this._hashArray[14] = (byte)var8;
      this._hashArray[15] = (byte)(var8 >> 8);
      this._hashArray[16] = (byte)(var8 >> 16);
      this._hashArray[17] = (byte)(var8 >> 24);
      this._hashArray[18] = (byte)var9;
      this._hashArray[19] = (byte)(var9 >> 8);
      this._hashArray[20] = (byte)(var9 >> 16);
      this._hashArray[21] = (byte)(var9 >> 24);
      this._hashArray[22] = (byte)var10;
      this._hashArray[23] = (byte)(var10 >> 8);
      this._hashArray[24] = (byte)(var10 >> 16);
      this._hashArray[25] = (byte)(var10 >> 24);
      this._hashArray[26] = (byte)var11;
      this._hashArray[27] = (byte)(var11 >> 8);
      this._hashArray[28] = (byte)(var11 >> 16);
      this._hashArray[29] = (byte)(var11 >> 24);
      this._hashArray[30] = (byte)var12;
      this._hashArray[31] = (byte)(var12 >> 8);
      this._hashArray[32] = (byte)(var12 >> 16);
      this._hashArray[33] = (byte)(var12 >> 24);
      this._hashArray[34] = (byte)var13;
      this._hashArray[35] = (byte)(var13 >> 8);
      this._hashArray[36] = (byte)(var13 >> 16);
      this._hashArray[37] = (byte)(var13 >> 24);
      long var15 = var1;
      var15 = var15 << 32 | CRC32.update(-this._hashArray.length, this._hashArray, 0, this._hashArray.length) & 65535;
      var18 = (Font)this._fontsUsed.get(var15);
      if (var18 != null && var18.hasAttributes(var1, var2, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13)) {
         return var18;
      }

      var18 = new Font(this, var1, var2, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this._fontsUsed.put(var15, var18);
      return var18;
   }

   public static FontFamily[] getFontFamilies() {
      String[] var0 = FontRegistry.getFontFamilies();
      FontFamily[] var1 = new FontFamily[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = FontRegistry.get(var0[var2]);
      }

      return var1;
   }

   public int getTypefaceType() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int[] getHeights() {
      if (this._heights == null) {
         FontRegistry var1 = FontRegistry.getInstance();
         if (var1 != null) {
            int[] var2 = var1.getHeightsForTypeface(this._name);
            if (var2 != null) {
               this._heights = var2;
            } else {
               this._heights = FONTSIZES;
            }
         } else {
            this._heights = FONTSIZES;
         }
      }

      return Arrays.copy(this._heights);
   }

   public final String getName() {
      return this._name;
   }

   @Override
   public String toString() {
      return this._name;
   }

   public final boolean isHeightSupported(int var1) {
      int[] var2 = this.getHeights();
      if (var2 != null && var2.length != 0) {
         int var3 = 0;

         while (var3 < var2.length && var1 != var2[var3]) {
            var3++;
         }

         return var3 < var2.length;
      } else {
         return false;
      }
   }

   public boolean isStyleSupported(int var1) {
      return true;
   }

   public final void publish() {
   }

   public Font[] getFonts() {
      if (this._fonts == null) {
         int[] var1 = this.getHeights();
         if (var1 == null || var1.length == 0) {
            return null;
         }

         this._fonts = new Font[var1.length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            this._fonts[var2] = this.getFont(0, var1[var2]);
         }
      }

      return this._fonts;
   }

   @Override
   public boolean equals(Object var1) {
      return this._name == ((FontFamily)var1).getName();
   }

   void reload() {
      this._fonts = null;
      this._heights = null;
   }
}
