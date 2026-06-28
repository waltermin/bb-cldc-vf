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

   public ActiveRichTextField$RegionQueue(int initMaxRegions, int initMaxFonts) {
      if (initMaxRegions > 0) {
         this.resizeBuffers(initMaxRegions);
      }

      if (initMaxFonts > 0) {
         this.fonts = new Font[initMaxFonts];
         this.foregroundColors = new int[initMaxFonts];
         this.backgroundColors = new int[initMaxFonts];
         this._maxfonts = initMaxFonts;
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

   public final byte appendFont(Font font) {
      return this.appendFont(font, -1, -1);
   }

   public final byte appendFont(Font font, int foregroundColor, int backgroundColor) {
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

      this.fonts[this._numfonts] = font;
      this.foregroundColors[this._numfonts] = foregroundColor;
      this.backgroundColors[this._numfonts] = backgroundColor;
      this._numfonts++;
      return (byte)(this._numfonts - 1);
   }

   protected void resizeBuffers(int newCapacity) {
      if (this._maxregions != newCapacity) {
         if (newCapacity == 0) {
            this.offsets = null;
            this.attributes = null;
            this.cookieID = null;
         } else if (this._maxregions == 0) {
            this.offsets = new int[newCapacity + 1];
            this.attributes = new byte[newCapacity];
            this.cookieID = new IntHashtable(newCapacity);
            this.offsets[0] = 0;
         } else {
            Array.resize(this.offsets, newCapacity + 1);
            Array.resize(this.attributes, newCapacity);
         }

         this._maxregions = newCapacity;
      }
   }

   public boolean appendRegion(int offset, byte attribute, long id) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public void appendCookieID(long id) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public long[] getSingleCookieRegions() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
