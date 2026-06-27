package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Font;
import net.rim.device.api.util.IntHashtable;
import net.rim.vm.Array;

public class ActiveRichTextField$RegionQueue {
   public int[] offsets;
   public byte[] attributes;
   public Font[] fonts;
   public IntHashtable cookieID;
   public int[] foregroundColors;
   public int[] backgroundColors;
   protected int _numregions;
   protected int _maxregions;
   protected int _numfonts;
   protected int _maxfonts;
   private static final int REGION_ARRAY_INCR;
   private static final int FONT_ARRAY_INCR;

   public ActiveRichTextField$RegionQueue(int var1, int var2) {
      if (var1 > 0) {
         this.resizeBuffers(var1);
      }

      if (var2 > 0) {
         this.fonts = new Font[var2];
         this.foregroundColors = new int[var2];
         this.backgroundColors = new int[var2];
         this._maxfonts = var2;
      }
   }

   public final void trim() {
      this.resizeBuffers(this._numregions);
      if (this._maxfonts != this._numfonts) {
         Array.resize(this.fonts, this._numfonts);
         Array.resize(this.foregroundColors, this._numfonts);
         Array.resize(this.backgroundColors, this._numfonts);
         this._maxfonts = this._numfonts;
      }
   }

   public final byte appendFont(Font var1) {
      return this.appendFont(var1, -1, -1);
   }

   public final byte appendFont(Font var1, int var2, int var3) {
      if (this._numfonts == this._maxfonts) {
         if (this.fonts == null) {
            this.fonts = new Font[this._maxfonts + 2];
            this.foregroundColors = new int[this._maxfonts + 2];
            this.backgroundColors = new int[this._maxfonts + 2];
         } else {
            Array.resize(this.fonts, this._maxfonts + 2);
            Array.resize(this.foregroundColors, this._maxfonts + 2);
            Array.resize(this.backgroundColors, this._maxfonts + 2);
         }

         this._maxfonts += 2;
      }

      this.fonts[this._numfonts] = var1;
      this.foregroundColors[this._numfonts] = var2;
      this.backgroundColors[this._numfonts] = var3;
      this._numfonts++;
      return (byte)(this._numfonts - 1);
   }

   protected void resizeBuffers(int var1) {
      if (this._maxregions != var1) {
         if (var1 == 0) {
            this.offsets = null;
            this.attributes = null;
            this.cookieID = null;
         } else if (this._maxregions == 0) {
            this.offsets = new int[var1 + 1];
            this.attributes = new byte[var1];
            this.cookieID = (IntHashtable)(new Object(var1));
            this.offsets[0] = 0;
         } else {
            Array.resize(this.offsets, var1 + 1);
            Array.resize(this.attributes, var1);
         }

         this._maxregions = var1;
      }
   }

   public boolean appendRegion(int var1, byte var2, long var3) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public void appendCookieID(long var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public long[] getSingleCookieRegions() {
      throw new RuntimeException("cod2jar: type check");
   }
}
