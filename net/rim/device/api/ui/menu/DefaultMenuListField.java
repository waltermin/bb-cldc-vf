package net.rim.device.api.ui.menu;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontLogicHelper;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.ui.Image;

class DefaultMenuListField extends Field implements MenuList {
   private MenuItem[] _items;
   private byte[] _heights;
   private byte[] _offsets;
   private int _focusHeight;
   private int _fontHeight;
   private int _separatorHeight = 3;
   private int _selection;
   private boolean _focusOn;
   private int _contentPaddingLeft = 1;
   private int _contentPaddingRight = 1;
   private Font _font;
   private ThemeAttributeSet _tasFocus;
   private Font _fontFocus;
   private boolean _focusDrawWithInvalidate;
   private static Tag TAG;

   DefaultMenuListField() {
      super(18014398509481984L);
      this.setTag(TAG);
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this._font = this.getFont();
      int var1 = Locale.getDefaultForSystem().getCode();
      if (!FontLogicHelper.fontLegible(this._font, var1)) {
         this._font = FontLogicHelper.getSuggestedFont(this._font, var1, true);
      }

      Theme var2 = ThemeManager.getActiveTheme();
      this._tasFocus = var2.getAttributeSet(this, 6);
      if (this._tasFocus != null) {
         this._fontFocus = this._tasFocus.getFont();
         if (this._fontFocus != null && !FontLogicHelper.fontLegible(this._fontFocus, var1)) {
            this._fontFocus = FontLogicHelper.getSuggestedFont(this._fontFocus, var1, true);
         }
      }

      if (this._fontFocus == null) {
         this._fontFocus = this._font;
      }

      this._fontHeight = this._font.getHeight();
      this._focusHeight = this._fontFocus.getHeight();
      if (this._fontHeight != this._focusHeight) {
         this._focusDrawWithInvalidate = true;
      }
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public MenuItem getCurrentItem() {
      return this._items[this._selection];
   }

   private int getDisplayIndex(MenuItem var1) {
      int var2 = this._items.length;

      for (int var3 = 0; var3 < var2; var3++) {
         if (this._items[var3] == var1) {
            return var3;
         }
      }

      return 0;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      int var2 = this.getPosOfItem(this._selection);
      var1.set(0, var2, this.getWidth(), this.getHeightOfItem(this._selection));
   }

   private int getHeightOfItem(int var1) {
      return var1 == this._selection ? Math.max(this._focusHeight + this._heights[var1] - this._fontHeight, this._heights[var1]) : this._heights[var1];
   }

   private int getItemForPosition(int var1, int var2) {
      var1 -= var2;
      int var3 = 0;
      int var4 = this._items.length;

      for (int var5 = 0; var5 < var4; var5++) {
         var3 += this.getHeightOfItem(var5);
         if (var3 >= var1) {
            return var5;
         }
      }

      return var4;
   }

   private int getPosOfItem(int var1) {
      int var2 = Arrays.sum(this._heights, 0, var1, false);
      if (var1 > this._selection) {
         var2 = var2 + this.getHeightOfItem(this._selection) - this._heights[this._selection];
      }

      return var2;
   }

   @Override
   public int getPreferredWidth() {
      int var1 = 0;
      int var2 = 0;
      int var3 = this._items.length;

      for (int var4 = 0; var4 < var3; var4++) {
         MenuItem var5 = this._items[var4];
         Image var6 = var5.getIcon();
         if (var6 != null) {
            var2 = Math.max(var2, var6.getWidth(Display.getWidth(), Display.getHeight()));
         }
      }

      for (int var7 = var3 - 1; var7 >= 0; var7--) {
         MenuItem var8 = this._items[var7];
         if (!var8.isSeparator()) {
            String var9 = var8.toString();
            var1 = Math.max(var1, this._font.getBounds(var9) + var2);
            if (this._fontFocus != this._font) {
               var1 = Math.max(var1, this._fontFocus.getBounds(var9) + var2);
            }
         }
      }

      return var1 + this._contentPaddingLeft + this._contentPaddingRight;
   }

   private boolean containsChar(StringBuffer var1, char var2) {
      if (var1 != null) {
         for (int var3 = var1.length() - 1; var3 >= 0; var3--) {
            if (var1.charAt(var3) == var2) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if ((var2 & 65536) != 0) {
         return var1;
      }

      int var4 = this._selection;
      int var5 = MathUtilities.clamp(-1, var1, 1);
      int var6 = this._items.length - 1;

      while (var1 != 0) {
         this._selection = MathUtilities.clamp(0, this._selection + var5, var6);
         if (this._items[this._selection].isSeparator()) {
            this._selection = MathUtilities.clamp(0, this._selection + var5, var6);
         }

         var1 -= var5;
      }

      if (this._focusDrawWithInvalidate) {
         int var7 = this.getPosOfItem(Math.min(var4, this._selection));
         this.invalidate(0, var7, this.getWidth(), this.getPosOfItem(Math.max(var4, this._selection) + 1));
      }

      if (Ui.isTTSEnabled()) {
         this.accessibleEventOccurred(1, new Object(1), new Object(2), this.getCurrentItem());
      }

      return 0;
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      if (this.getExtent().contains(var1, var2)) {
         int var5 = MathUtilities.clamp(0, this.getItemForPosition(var2, -1), this._items.length - 1);
         if (!this._items[var5].isSeparator()) {
            this._selection = var5;
         }
      }
   }

   @Override
   public boolean stylusDrag(int var1, int var2, int var3, int var4) {
      this.focusRemove();
      this.moveFocus(var1, var2, var3, var4);
      this.focusAdd(true);
      return true;
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public void setCurrentItem(MenuItem var1) {
      this._selection = this.getDisplayIndex(var1);
   }

   @Override
   public void setMenuItems(MenuItem[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
