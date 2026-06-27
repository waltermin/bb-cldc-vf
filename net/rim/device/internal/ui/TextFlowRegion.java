package net.rim.device.internal.ui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.XYRect;

public class TextFlowRegion {
   public Object _obj;
   private String _fontFamily;
   private int _fontStyle;
   private int _fontHeight;
   private Font _font;
   private short _flags;
   private int _offsetYTop;
   private int _offsetYBottom;
   private short _offsetXLeft;
   private short _offsetXRight;
   public int _foregroundColour;
   public int _backgroundColour;
   public short _width;
   public short _height;
   public short _margin;
   private int _minTextWidth;
   private int _preferredTextWidth;
   private int _calcWidthStartOffset;
   private int _calcWidthEndOffset;
   private short _offsetXLeftmost;
   private short _offsetXRightmost;
   public static final short REGION_ALIGN_MASK;
   public static final short REGION_ALIGN_DEFAULT;
   public static final short REGION_ALIGN_LEFT;
   public static final short REGION_ALIGN_RIGHT;
   public static final short REGION_ALIGN_CENTER;
   public static final short REGION_DIR_MASK;
   public static final short REGION_DIR_LTR;
   public static final short REGION_DIR_RTL;
   public static final short REGION_DIR_OVERRIDE_LTR;
   public static final short REGION_DIR_OVERRIDE_RTL;
   public static final short REGION_VALIGN_MASK;
   public static final short REGION_VALIGN_DEFAULT;
   public static final short REGION_VALIGN_BOTTOM;
   public static final short REGION_VALIGN_TOP;
   public static final short REGION_VALIGN_MIDDLE;
   public static final short REGION_BREAKING_BEFORE;
   public static final short REGION_BREAKING_BEFORE_WITH_NEWLINE;
   public static final short REGION_BREAKING_AFTER;
   public static final short REGION_BREAKING_AFTER_WITH_NEWLINE;
   public static final short REGION_BREAK_MASK;
   public static final short REGION_DISPLAY_NONE;
   public static final short REGION_VISIBILITY_HIDDEN;
   public static final short REGION_ADDITIONAL_FLAGS_MASK;
   public static final short REGION_FIND_MAX_WIDTH;

   public short getFlags() {
      return this._flags;
   }

   public void setAdditionalFlags(short var1) {
      this._flags = (short)(this._flags & -3073);
      this._flags = (short)(this._flags | var1 & 3072);
   }

   public void setVAlignment(short var1) {
      this._flags = (short)(this._flags & -12289);
      this._flags = (short)(this._flags | var1 & 12288);
   }

   public void setFindMaxWidthFlag(boolean var1) {
      if (var1) {
         this._flags = (short)(this._flags | 16384);
      } else {
         this._flags = (short)(this._flags & -16385);
      }
   }

   public void setAlignment(short var1) {
      this._flags = (short)(this._flags & -8);
      this._flags = (short)(this._flags | var1 & 7);
   }

   public void setDirection(short var1) {
      this._flags = (short)(this._flags & -9);
      this._flags = (short)(this._flags | var1 & 8);
   }

   public void setBreakingFlags(short var1) {
      this._flags = (short)(this._flags & -961);
      this._flags = (short)(this._flags | var1 & 960);
   }

   public void inherit(TextFlowRegion var1) {
      this._fontFamily = var1._fontFamily;
      this._fontStyle = var1._fontStyle;
      this._fontHeight = var1._fontHeight;
      this._font = var1._font;
      this._foregroundColour = var1._foregroundColour;
      this._backgroundColour = var1._backgroundColour;
      this._flags = (short)(var1._flags & -961);
      this._margin = var1._margin;
   }

   public void setFont(Font var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Font getFont() {
      return this._font;
   }

   public void setFontHeight(int var1) {
      if (this._fontHeight != var1) {
         this._fontHeight = var1;
         this._font = null;
      }
   }

   public void setFontFamily(String var1) {
      if (!var1.equals(this._fontFamily)) {
         this._fontFamily = var1;
         this._font = null;
      }
   }

   public void setFontStyle(int var1) {
      if (this._fontStyle != var1) {
         this._fontStyle = var1;
         this._font = null;
      }
   }

   public int getFontStyle() {
      return this._fontStyle;
   }

   public int getFontHeight() {
      return this._fontHeight;
   }

   public String getFontFamily() {
      return this._fontFamily;
   }

   public int getOffsetYTop() {
      return this._offsetYTop;
   }

   public int getOffsetYBottom() {
      return this._offsetYBottom;
   }

   public int getOffsetXLeft() {
      return this._offsetXLeft;
   }

   public int getOffsetXRight() {
      return this._offsetXRight;
   }

   public void getCoords(XYRect var1) {
      if ((this._flags & 16384) != 0) {
         var1.set(this._offsetXLeftmost, this._offsetYTop, this._offsetXRightmost - this._offsetXLeftmost, this._offsetYBottom - this._offsetYTop);
      } else {
         var1.set(this._offsetXLeft, this._offsetYTop, this._offsetXRight - this._offsetXLeft, this._offsetYBottom - this._offsetYTop);
      }
   }

   public void setPosition(int var1, int var2, int var3, int var4) {
      this._offsetXLeft = (short)var1;
      this._offsetYTop = var2;
      this._offsetXRight = (short)var3;
      this._offsetYBottom = var4;
   }

   public void calculateTextWidth(StringBuffer var1, int var2, int var3) {
      if (this._calcWidthEndOffset != var3 || this._calcWidthStartOffset != var2) {
         this.calculateTextWidth0(var1, var2, var3);
      }
   }

   public void invalidateTextWidths() {
      this._minTextWidth = 0;
      this._preferredTextWidth = 0;
      this._calcWidthEndOffset = 0;
      this._calcWidthStartOffset = 0;
   }

   private native void calculateTextWidth0(StringBuffer var1, int var2, int var3);

   public int getMinTextWidth() {
      return this._minTextWidth;
   }

   public int getPreferredTextWidth() {
      return this._preferredTextWidth;
   }
}
