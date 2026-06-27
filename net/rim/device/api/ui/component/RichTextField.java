package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.ui.FormatParams;
import net.rim.tid.text.AttributedString;
import net.rim.vm.Array;

public class RichTextField extends TextField implements ActiveRegionSupport$ActiveRegionFieldIf {
   ActiveRegionSupport _arSupport;
   private boolean _legacyArraysUpdated;
   private boolean _legacyAttributesUsed;
   private int[] _offsets;
   private int _offset_no;
   private byte[] _attributes;
   private Font[] _fonts;
   private int[] _colorForeground;
   private int[] _colorBackground;
   private Object[] _cookies;
   private int _layoutWidth;
   public static final int TEXT_ALIGN_LEFT;
   public static final int USE_TEXT_WIDTH;
   public static final int TEXT_ALIGN_HCENTER;
   public static final int TEXT_ALIGN_RIGHT;
   private static final long LEGACY_FONT_INDEX_ATTRIB_MASK;
   private static final int LEGACY_FONT_INDEX_ATTRIB_SHIFT;

   public void setText(String var1, int[] var2, byte[] var3, Font[] var4) {
      this.setText(var1, var2, var3, var4, null);
   }

   public void setText(String var1, int[] var2, byte[] var3, Font[] var4, Object[] var5) {
      throw new RuntimeException("cod2jar: string-special");
   }

   protected void setText(AttributedString var1, int[] var2, byte[] var3, Font[] var4, Object[] var5) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   protected int getRunEnd() {
      return this._arSupport.getRunEnd();
   }

   protected int getRunStart() {
      return this._arSupport.getRunStart();
   }

   void setFont(int var1, Font var2) {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      this._fonts[var1] = var2;
      this.convertOffsetsToAttribString(null, 0, this._offset_no - 1, this._offsets, this._attributes, this._fonts);
   }

   public int getRegion(int var1) {
      if (var1 == super._text.length() && var1 != 0) {
         var1--;
      }

      return this._arSupport.getRegion(var1);
   }

   public Object getRegionCookie() {
      return this._arSupport.getCookieWithFocus(this.getCaretPosition());
   }

   public Object getRegionCookie(int var1) {
      return null != this._cookies && this._cookies.length != 0 && var1 >= 0 ? this._cookies[var1] : null;
   }

   public String getRegionText() {
      return this._arSupport.getCurrentRegionText(this.getCaretPosition(), super._text);
   }

   public String getRegionText(int var1) {
      return this._arSupport.getRegionText(var1, super._text);
   }

   public Font getRegionFont() {
      return Ui.getFontFromAttributes(super._insertionAttrib, this.getFont());
   }

   public Font getRegionFont(int var1) {
      return !this._arSupport.adjustCurrentRunForIndex(var1) ? null : Ui.getFontFromAttributes(this._arSupport.getRunAttrib(), this.getFont());
   }

   protected Font[] getFonts() {
      return this.getFonts(true);
   }

   protected Font[] getFonts(boolean var1) {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      if (this._fonts == null) {
         return null;
      }

      Font[] var2 = new Font[this._fonts.length];
      System.arraycopy(this._fonts, 0, var2, 0, this._fonts.length);
      if (var1) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            if (var2[var3] == null) {
               var2[var3] = this.getFont();
            }
         }
      }

      return var2;
   }

   protected int[] getOffsets() {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      return Arrays.copy(this._offsets);
   }

   protected byte[] getAttributes() {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      return Arrays.copy(this._attributes);
   }

   public void setAttribute(int var1, int var2) {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      if (var2 >= 0 && var2 < this._fonts.length) {
         if (this._attributes[var1] != (byte)var2) {
            this._attributes[var1] = (byte)var2;
            long var3 = this._fonts[var2] == null ? this.getDefaultFontAttributes() : Ui.getAttributesFromFont(this._fonts[var2]);
            long var5 = (long)var2 << 16;
            this.setAttrib(this._offsets[var1], this._offsets[var1 + 1], var3, 675086335, var5, 16711680);
            this.updateLayout();
         }
      } else {
         throw new Object();
      }
   }

   public synchronized void setAttributes(int[] var1, int[] var2) {
      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      if (var1 != null && var1.length != this._fonts.length) {
         throw new Object();
      }

      if (var2 != null && var2.length != this._fonts.length) {
         throw new Object();
      }

      this._colorForeground = Arrays.copy(var1);
      this._colorBackground = Arrays.copy(var2);
      this.startLayoutUpdate();

      for (int var3 = 0; var3 < this._offset_no - 1; var3++) {
         long var4 = 0;
         byte var6 = this._attributes[var3];
         long var7 = var1 != null && var1[var6] != -1 ? Ui.convertColorTo16bit(var1[var6]) : Ui.DEFAULT_16BIT_COLOR;
         long var9 = var2 != null && var2[var6] != -1 ? Ui.convertColorTo16bit(var2[var6]) : Ui.DEFAULT_16BIT_COLOR;
         var4 |= var7 << 32 | var9 << 48;
         super._text.setAttrib(this._offsets[var3], this._offsets[var3 + 1], var4, -4294967296L, 0, 0);
      }

      this.endLayoutUpdate();
   }

   protected int[] getForegroundColors() {
      return Arrays.copy(this._colorForeground);
   }

   protected int[] getBackgroundColors() {
      return Arrays.copy(this._colorBackground);
   }

   protected void appendFonts(Font[] var1) {
      if (var1 != null && var1.length != 0) {
         if (!this._legacyArraysUpdated) {
            this.initLegacyArrays();
         }

         int var2 = var1.length;
         if (this._fonts == null) {
            this._fonts = new Font[var2];
            System.arraycopy(var1, 0, this._fonts, 0, var2);
         } else {
            int var3 = this._fonts.length;
            Array.resize(this._fonts, var3 + var2);
            System.arraycopy(var1, 0, this._fonts, var3, var2);
         }
      }
   }

   protected int append(String var1, int[] var2, int[] var3, byte[] var4) {
      if (var1 == null) {
         return 0;
      }

      if (!this._legacyArraysUpdated) {
         this.initLegacyArrays();
      }

      this.vetArguments(var1, var2, var3, var4);
      int var6 = super._text.length();
      int[] var5;
      if (var3 == null) {
         var5 = this.insertText(var1, var2);
      } else {
         var5 = this.insertText(var1, var2, var3);
      }

      int var7 = super._text.length() - var6;
      if (var7 > 0) {
         this.addOffsetAndAttributes(var5, var4);
         int var8 = this._offset_no - var5.length - 1;
         this.convertOffsetsToAttribString(null, var8, this._offset_no - 1, this._offsets, this._attributes, this._fonts);
      }

      this.fireTextChangeEvent(0);
      return var7;
   }

   protected void setFont(int[] var1, Font[] var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean regionsHaveSameCookie(int var1, int var2) {
      return false;
   }

   @Override
   public Object getCookie(int var1) {
      return null;
   }

   @Override
   public boolean isCookieValid(int var1) {
      return true;
   }

   private void setCookies(Object[] var1) {
      if (var1 != null) {
         this._cookies = new Object[var1.length];
         System.arraycopy(var1, 0, this._cookies, 0, var1.length);
      } else {
         this._cookies = null;
      }
   }

   private void vetOffsets(String var1, int[] var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void vetAttributes(byte[] var1, Font[] var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (super.keyChar(var1, var2, var3)) {
         return true;
      }

      if (this.isSelecting() && var1 == '\n') {
         return true;
      }

      switch (var1) {
         case ' ':
            if ((var2 & 1) == 0) {
               int var4 = (var2 & 2) == 0 ? 512 : 256;
               this.getScreen().scroll(var4);
               return true;
            }
         default:
            return false;
      }
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      return super._text.length() == 0 ? var1 : super.moveFocus(var1, var2, var3);
   }

   @Override
   public void setText(String var1) {
      super.setText(var1);
      this.setCookies(null);
   }

   private static long validateStyle(long var0) {
      var0 &= -13510798882111489L;
      var0 |= 9007199254740992L;
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }

   @Override
   protected long getDefaultFontAttributes() {
      long var1 = super.getDefaultFontAttributes();
      if (this.isStyle(524288)) {
         return var1 | 4194304;
      }

      if (this.isStyle(262144)) {
         var1 |= 12582912;
      }

      return var1;
   }

   private void initLegacyArrays() {
      byte var1 = 10;
      byte var2 = var1;
      int[] var3 = new int[var2];
      byte[] var4 = new byte[var2];
      Font[] var5 = new Font[var2];
      int var6 = 0;
      var3[0] = 0;
      int var7 = 0;

      while (this._arSupport.adjustCurrentRunForIndex(var6)) {
         if (var6 + 1 == var2) {
            var2 += var1;
            Array.resize(var3, var2);
            Array.resize(var4, var2);
            Array.resize(var5, var2);
         }

         var3[var6 + 1] = this._arSupport.getRunEnd();
         if (this._legacyAttributesUsed) {
            byte var8 = (byte)((this._arSupport.getRunXAttrib() & 16711680) >> 16);
            var4[var6] = var8;
         } else {
            Font var10 = Ui.getFontFromAttributes(this._arSupport.getRunAttrib(), this.getFont());
            var4[var6] = this.findFontIndex(var5, var10);
         }

         var7 = Math.max(var7, var4[var6]);
         var6++;
      }

      this._offsets = Arrays.copy(var3, 0, ++var6);
      this._attributes = Arrays.copy(var4, 0, var6 - 1);
      if (this._fonts == null || !this._legacyAttributesUsed) {
         this._fonts = new Font[var7 + 1];
         System.arraycopy(var5, 0, this._fonts, 0, var7 + 1);
      }

      this._offset_no = var6;
      this._legacyArraysUpdated = true;
   }

   private byte findFontIndex(Font[] var1, Font var2) {
      byte var3;
      for (var3 = 0; var1[var3] != null; var3++) {
         if (var1[var3] == var2) {
            return var3;
         }
      }

      var1[var3] = var2;
      return var3;
   }

   public RichTextField() {
   }

   public RichTextField(long var1) {
   }

   private synchronized int[] insertText(String var1, int[] var2, int[] var3) {
      int var4 = var2.length;
      int var5 = super._text.length();
      int[] var6 = new int[var4];

      for (int var7 = 0; var7 < var4; var7++) {
         super._text.insert(var5, var1.substring(var2[var7], var2[var7] + var3[var7]));
         var5 += var3[var7];
         var6[var7] = var5;
      }

      return var6;
   }

   private synchronized int[] insertText(String var1, int[] var2) {
      int var3 = var2.length - 1;
      int var4 = super._text.length();
      int[] var5 = new int[var3];

      for (int var6 = 0; var6 < var3; var6++) {
         super._text.insert(var4, var1.substring(var2[var6], var2[var6 + 1]));
         int var7 = var2[var6 + 1] - var2[var6];
         var4 += var7;
         var5[var6] = var4;
      }

      return var5;
   }

   private void addOffsetAndAttributes(int[] var1, byte[] var2) {
      int var3 = this._offset_no;
      int var4 = this._attributes.length;
      if (var3 == 2 && this._offsets[0] == this._offsets[1]) {
         var3--;
         var4--;
         this._offset_no--;
      }

      Array.resize(this._offsets, var3 + var1.length);
      this._offset_no += var1.length;
      System.arraycopy(var1, 0, this._offsets, var3, var1.length);
      Array.resize(this._attributes, var4 + var2.length);
      System.arraycopy(var2, 0, this._attributes, var4, var2.length);
   }

   private void vetArguments(String var1, int[] var2, int[] var3, byte[] var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public RichTextField(String var1) {
      this(var1, 0);
   }

   public RichTextField(String var1, int[] var2, byte[] var3, Font[] var4, Object[] var5, long var6) {
      super(validateStyle(var6));
      this._legacyArraysUpdated = false;
      this._legacyAttributesUsed = false;
      this.setText(var1, var2, var3, var4, var5);
      this._arSupport = (ActiveRegionSupport)(new Object(super._text.getIterator(), this));
   }

   @Override
   public int getPreferredHeight() {
      return 0;
   }

   @Override
   int getLayoutWidth(int var1) {
      return this.isStyle(67108864) ? this._layoutWidth : var1;
   }

   @Override
   void handleLinesAfterFormat(FormatParams var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected int getMaxLinesToFormat() {
      return this.isStyle(67108864) && (this.isStyle(262144) || this.isStyle(524288)) ? Integer.MAX_VALUE : super.getMaxLinesToFormat();
   }

   private synchronized void convertOffsetsToAttribString(AttributedString var1, int var2, int var3, int[] var4, byte[] var5, Font[] var6) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void notifyTextChanged(FormatParams var1, boolean var2) {
      super.notifyTextChanged(var1, var2);
      this._arSupport.init();
      if (var2) {
         this._legacyArraysUpdated = false;
      }
   }

   public RichTextField(String var1, int[] var2, byte[] var3, Font[] var4, long var5) {
      this(var1, var2, var3, var4, null, var5);
   }

   public RichTextField(String var1, long var2) {
   }

   @Override
   public int getCursorPosition() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public String getText() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setCursorPosition(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public String getText(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public int getTextLength() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public boolean isSelectable() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public char charAt(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void setFont(Font var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
