package net.rim.device.api.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.text.TextRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.StringProvider;
import net.rim.device.internal.ui.Border;
import net.rim.device.internal.ui.Image;

public class ButtonField extends Field implements DrawStyle, FieldLabelProvider {
   private String _label;
   private Image _image;
   private int _imageWidth = -1;
   private int _imageHeight = -1;
   private boolean _active;
   private boolean _acceptsKeyUp;
   private boolean _acceptsKeyUpSet;
   private boolean _borderAllSet;
   private Border _borderAll;
   private boolean _borderFocusSet;
   private Border _borderFocus;
   private boolean _borderActiveSet;
   private Border _borderActive;
   private boolean _borderDisabledSet;
   private Border _borderDisabled;
   private boolean _borderDisabledFocusSet;
   private Border _borderDisabledFocus;
   private int _topExtra;
   private int _bottomExtra;
   private int _rightExtra;
   private int _leftExtra;
   private ThemeAttributeSet _attrNormal;
   private ThemeAttributeSet _attrFocus;
   private ThemeAttributeSet _attrActive;
   private ThemeAttributeSet _attrDisabled;
   private ThemeAttributeSet _attrDisabledFocus;
   private TextRect _text;
   private static Tag TAG;
   public static final int BARE;
   public static final long CONSUME_CLICK;
   public static final long NEVER_DIRTY;
   private static final int H_INSIDE_SPACE;
   private static final int V_INSIDE_SPACE;
   private static final int H_OUTSIDE_SPACE;
   private static final int V_OUTSIDE_SPACE;

   TextRect getTextRect() {
      return this._text;
   }

   public void setImage(Image var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setImageSize(int var1, int var2) {
      this._imageWidth = var1;
      this._imageHeight = var2;
   }

   @Override
   public void setLabel(String var1) {
      this._label = var1;
      this._text.setText(this._label);
      this.updateLayout();
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getLabel() {
      return this._label;
   }

   private void applyFocusDifference() {
      int var1 = this._topExtra;
      int var2 = this._bottomExtra;
      int var3 = this._leftExtra;
      int var4 = this._rightExtra;
      XYEdges var5 = ThemeAttributeSet.getEdges(this, 2);
      if (var5 != null) {
         var1 += var5.top;
         var4 += var5.right;
         var2 += var5.bottom;
         var3 += var5.left;
      }

      this.setMargin(var1, var4, var2, var3);
   }

   @Override
   protected void applyThemeOnStateChange() {
      boolean var1 = this.getState() == 6;
      ThemeAttributeSet var2 = null;
      ThemeAttributeSet var3 = null;
      Border var4;
      if (this.isEditable()) {
         if (var1 && this._active) {
            if (this._borderActive != null) {
               var4 = this._borderActive;
            } else {
               var4 = var1 ? this._borderFocus : this._borderAll;
            }

            if (this._attrActive != null) {
               var2 = this._attrActive;
               var3 = this._attrActive;
            }
         } else {
            var4 = var1 ? this._borderFocus : this._borderAll;
         }
      } else {
         var4 = var1 ? this._borderDisabledFocus : this._borderDisabled;
         if (this._attrDisabled != null) {
            var2 = this._attrDisabled;
            var3 = this._attrDisabledFocus;
         }
      }

      if (var2 == null) {
         var2 = this._attrNormal;
         if (var3 == null) {
            var3 = this._attrFocus;
         }
      }

      this.setBorderWithoutLayout(var4);
      this.calculateFocusDifference();
      this.applyFocusDifference();
      this.setThemeAttributesAll(var2, var3);
      super.applyThemeOnStateChange();
      int var5 = this.getBorderLeft() + this.getBorderRight() + this.getPaddingLeft() + this.getPaddingRight();
      this._text.layout(Math.max(0, this.getExtent().width - var5), this.getExtent().height);
      this.invalidate();
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private void calculateFocusDifference() {
      if (this.isEditable() && this._borderFocus != null && this._borderAll != null) {
         if (this.getState() != 6) {
            this._topExtra = Math.max(0, this._borderFocus.getTop() - this._borderAll.getTop());
            this._rightExtra = Math.max(0, this._borderFocus.getRight() - this._borderAll.getRight());
            this._bottomExtra = Math.max(0, this._borderFocus.getBottom() - this._borderAll.getBottom());
            this._leftExtra = Math.max(0, this._borderFocus.getLeft() - this._borderAll.getLeft());
         } else {
            this._topExtra = Math.max(0, this._borderAll.getTop() - this._borderFocus.getTop());
            this._rightExtra = Math.max(0, this._borderAll.getRight() - this._borderFocus.getRight());
            this._bottomExtra = Math.max(0, this._borderAll.getBottom() - this._borderFocus.getBottom());
            this._leftExtra = Math.max(0, this._borderAll.getLeft() - this._borderFocus.getLeft());
         }
      } else {
         if (!this.isEditable() && this._borderDisabledFocus != null && this._borderDisabled != null) {
            this._topExtra = Math.abs(this._borderDisabledFocus.getTop() - this._borderDisabled.getTop());
            this._rightExtra = Math.abs(this._borderDisabledFocus.getRight() - this._borderDisabled.getRight());
            this._bottomExtra = Math.abs(this._borderDisabledFocus.getBottom() - this._borderDisabled.getBottom());
            this._leftExtra = Math.abs(this._borderDisabledFocus.getLeft() - this._borderDisabled.getLeft());
         }
      }
   }

   @Override
   public int getPreferredWidth() {
      Font var1 = this.getFontIfSet();
      return this.getPreferredWidth(var1);
   }

   private int getPreferredWidth(Font var1) {
      int var2 = 0;
      int var3 = 0;
      if (var1 != null) {
         var2 = var1.getBounds(this._label);
         var3 = var1.getHeight();
      } else if (this._attrNormal != null) {
         var1 = this._attrNormal.getFont();
         if (var1 != null) {
            int var4 = var1.getBounds(this._label);
            if (var4 > var2) {
               var2 = var4;
            }

            int var5 = var1.getHeight();
            if (var5 > var3) {
               var3 = var5;
            }
         }

         if (this._attrFocus != this._attrNormal && this._attrFocus != null) {
            var1 = this._attrFocus.getFont();
            if (var1 != null) {
               int var12 = var1.getBounds(this._label);
               if (var12 > var2) {
                  var2 = var12;
               }

               int var16 = var1.getHeight();
               if (var16 > var3) {
                  var3 = var16;
               }
            }
         }

         if (this._attrDisabled != this._attrNormal && this._attrDisabled != null) {
            var1 = this._attrDisabled.getFont();
            if (var1 != null) {
               int var13 = var1.getBounds(this._label);
               if (var13 > var2) {
                  var2 = var13;
               }

               int var17 = var1.getHeight();
               if (var17 > var3) {
                  var3 = var17;
               }
            }
         }

         if (this._attrDisabledFocus != this._attrNormal && this._attrDisabledFocus != null) {
            var1 = this._attrDisabledFocus.getFont();
            if (var1 != null) {
               int var14 = var1.getBounds(this._label);
               if (var14 > var2) {
                  var2 = var14;
               }

               int var18 = var1.getHeight();
               if (var18 > var3) {
                  var3 = var18;
               }
            }
         }

         if (this._attrActive != this._attrNormal && this._attrActive != null) {
            var1 = this._attrActive.getFont();
            if (var1 != null) {
               int var15 = var1.getBounds(this._label);
               if (var15 > var2) {
                  var2 = var15;
               }

               int var19 = var1.getHeight();
               if (var19 > var3) {
                  var3 = var19;
               }
            }
         }
      }

      if (var2 == 0 || var3 == 0) {
         var1 = this.getFont();
         var2 = var1.getBounds(this._label);
         var3 = var1.getHeight();
      }

      if (this._image != null) {
         var2 = var2 + this.getImageXYRect(var3).width + 2;
      }

      return var2 + this._rightExtra + this._leftExtra;
   }

   private XYRect getImageXYRect(int var1) {
      int var2 = this._imageWidth;
      if (var2 < 0) {
         var2 *= -var1;
      }

      int var3 = this._imageHeight;
      if (var3 < 0) {
         var3 *= -var1;
      }

      return new XYRect(0, 0, this._image.getWidth(var2, var3), this._image.getHeight(var2, var3));
   }

   @Override
   public int getPreferredHeight() {
      Font var1 = this.getFontIfSet();
      return this.getPreferredHeight(var1);
   }

   private int getPreferredHeight(Font var1) {
      int var2 = 0;
      if (var1 != null) {
         var2 = var1.getHeight();
      } else if (this._attrNormal != null) {
         var1 = this._attrNormal.getFont();
         if (var1 != null) {
            int var3 = var1.getHeight();
            if (var3 > var2) {
               var2 = var3;
            }
         }

         if (this._attrFocus != this._attrNormal && this._attrFocus != null) {
            var1 = this._attrFocus.getFont();
            if (var1 != null) {
               int var12 = var1.getHeight();
               if (var12 > var2) {
                  var2 = var12;
               }
            }
         }

         if (this._attrDisabled != this._attrNormal && this._attrDisabled != null) {
            var1 = this._attrDisabled.getFont();
            if (var1 != null) {
               int var13 = var1.getHeight();
               if (var13 > var2) {
                  var2 = var13;
               }
            }
         }

         if (this._attrDisabledFocus != this._attrNormal && this._attrDisabledFocus != null) {
            var1 = this._attrDisabledFocus.getFont();
            if (var1 != null) {
               int var14 = var1.getHeight();
               if (var14 > var2) {
                  var2 = var14;
               }
            }
         }

         if (this._attrActive != this._attrNormal && this._attrActive != null) {
            var1 = this._attrActive.getFont();
            if (var1 != null) {
               int var15 = var1.getHeight();
               if (var15 > var2) {
                  var2 = var15;
               }
            }
         }
      }

      if (var2 == 0) {
         var1 = this.getFont();
         var2 = var1.getHeight();
      }

      if (this._image != null) {
         int var16 = this._imageWidth;
         if (var16 < 0) {
            var16 *= -var2;
         }

         int var4 = this._imageHeight;
         if (var4 < 0) {
            var4 *= -var2;
         }

         var2 = Math.max(var2, this._image.getHeight(var16, var4));
      }

      var2 = Math.max(var2, this._text.getHeight());
      return var2 + this._topExtra + this._bottomExtra;
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            if (this.isEditable()) {
               this._active = true;
               this.fieldChangeNotify(0);
               if (this._attrActive != null) {
                  this.applyThemeOnStateChange();
               }

               return this.isStyle(65536);
            }

            return false;
         default:
            return false;
      }
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      if (!this._acceptsKeyUpSet) {
         this._acceptsKeyUp = Application.getApplication().acceptsKeyUpEvents();
         this._acceptsKeyUpSet = true;
      }

      if (this._acceptsKeyUp && Keypad.map(Keypad.key(var1), Keypad.status(var1)) == '\n') {
         this._active = true;
         if (this._attrActive != null) {
            this.applyThemeOnStateChange();
         }
      }

      return super.keyDown(var1, var2);
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      this._active = false;
      if (this._attrActive != null) {
         this.applyThemeOnStateChange();
      }

      return false;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (this.isEditable() && var1 == '\n') {
         this.fieldChangeNotify(0);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      ThemeAttributeSet var3 = ThemeManager.getActiveTheme().getAttributeSet(this, 6);
      Font var4 = null;
      if (var3 != null) {
         var4 = var3.getFont();
      }

      int var5 = var1;
      var1 = Math.min(var1, this.getPreferredWidth());
      var1 = var4 != null ? Math.max(var1, this.getPreferredWidth(var4)) : var1;
      var1 = Math.min(var5, var1);
      int var6 = 0;
      if (this._image != null) {
         var6 = this.getImageXYRect(this.getFont().getHeight()).width + 2;
         this._text.setPosition(var6, 0);
      }

      this._text.layout(var1 - var6, var2);
      var2 = Math.min(var2, this.getPreferredHeight());
      var2 = var4 != null ? Math.max(var2, this.getPreferredHeight(var4)) : var2;
      var2 = Math.max(this._text.getLineHeight(0), var2);
      this.setExtent(var1, var2);
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = var1.getColor();
      boolean var3 = this.getState() == 6;
      if (var3 && !this.isStyle(1024)) {
         if (this._attrFocus != null) {
            var1.setColor(this._attrFocus.getColor(1));
         } else {
            var1.setColor(var1.getBackgroundColor());
         }
      }

      if (!this.isEditable()) {
         if (var3 && this._attrDisabledFocus != null) {
            var1.setColor(this._attrDisabledFocus.getColor(1));
         } else if (!var3 && this._attrDisabled != null) {
            var1.setColor(this._attrDisabled.getColor(1));
         }
      }

      Font var4 = var1.getFont();
      int var5 = var4.getHeight();
      int var6 = this.getContentHeight();
      if (this._image != null) {
         XYRect var7 = this.getImageXYRect(var5);
         int var8 = var7.width;
         int var9 = var7.height;
         this._image.paint(var1, 0, var6 - var9 >>> 1, var8, var9);
      }

      this._text.paintSelf(var1);
      var1.setColor(var2);
   }

   @Override
   protected void onUnfocus() {
      this._active = false;
      super.onUnfocus();
   }

   @Override
   public void setBorder(int var1, Border var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void setDirty(boolean var1) {
      if (!this.isStyle(32768)) {
         super.setDirty(var1);
      }
   }

   public ButtonField(long var1) {
      this(null, var1);
   }

   public ButtonField(String var1) {
      this(var1, 0);
   }

   public ButtonField(String var1, long var2) {
      super(verifyStyle(var2));
      this.setTag(TAG);
      this._label = var1;
      this._text = (TextRect)(new Object(this));
      this._text.setText(this._label);
      this._text.setStyle(64);
   }

   @Override
   protected void applyTheme() {
      Theme var1 = ThemeManager.getActiveTheme();
      this._attrNormal = var1.getAttributeSet(this.getTag(), this.getId(), 0);
      this._attrFocus = var1.getAttributeSet(this.getTag(), this.getId(), 6);
      this._attrActive = var1.getAttributeSet(this.getTag(), this.getId(), 4, true);
      this._attrDisabled = var1.getAttributeSet(this.getTag(), this.getId(), 7);
      this._attrDisabledFocus = var1.getAttributeSet(this.getTag(), this.getId(), 8);
      if (this._attrFocus == null) {
         this._attrFocus = this._attrNormal;
      }

      if (this._attrDisabled == null) {
         this._attrDisabled = this._attrNormal;
      }

      if (this._attrDisabledFocus == null) {
         this._attrDisabledFocus = this._attrFocus;
      }

      if (!this._borderAllSet) {
         this.setThemeAttributeSet(this._attrNormal);
         this._borderAll = ThemeAttributeSet.getBorder(this);
      }

      if (!this._borderFocusSet) {
         this.setThemeAttributeSet(this._attrFocus);
         this._borderFocus = ThemeAttributeSet.getBorder(this);
      }

      if (!this._borderDisabledSet) {
         this.setThemeAttributeSet(this._attrDisabled);
         this._borderDisabled = ThemeAttributeSet.getBorder(this);
      }

      if (!this._borderDisabledFocusSet) {
         this.setThemeAttributeSet(this._attrDisabledFocus);
         this._borderDisabledFocus = ThemeAttributeSet.getBorder(this);
      }

      if (this._attrActive != null && !this._borderActiveSet) {
         this.setThemeAttributeSet(this._attrActive);
         this._borderActive = ThemeAttributeSet.getBorder(this);
      }

      if (this._borderAll == null) {
         boolean var2 = this.isStyle(1024);
         if (var2) {
            this._borderAll = (Border)(new Object(2, 4, 2, 4));
            this._borderDisabled = this._borderAll;
         } else {
            this._borderAll = (Border)(new Object(2, 4, 2, 4, 4));
            this._borderDisabled = (Border)(new Object(2, 4, 2, 4, 6));
         }

         int var3 = 4 | (var2 ? 0 : 1);
         this._borderFocus = (Border)(new Object(2, 4, 2, 4, var3));
         this._borderDisabledFocus = (Border)(new Object(2, 4, 2, 4, var3 | 1));
      }

      this.calculateFocusDifference();
      this.applyFocusDifference();
      this.setThemeAttributeSet(null);
      this._text.applyTheme();
      super.applyTheme();
   }

   @Override
   public void setMuddy(boolean var1) {
      if (!this.isStyle(32768)) {
         super.setMuddy(var1);
      }
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this.trackwheelClick(var3, var4);
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      if (!this.isEditable()) {
         return false;
      }

      this._active = true;
      if (this.isStyle(65536) || (var1 & 1073741824) != 0) {
         this.fieldChangeNotify(0);
      }

      if (this._attrActive != null) {
         this.applyThemeOnStateChange();
      }

      return this.isStyle(65536);
   }

   @Override
   protected boolean trackwheelUnclick(int var1, int var2) {
      this._active = false;
      if (this._attrActive != null) {
         this.applyThemeOnStateChange();
      }

      return false;
   }

   private static long verifyStyle(long var0) {
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      if ((var0 & 13510798882111488L) == 0) {
         var0 |= 4503599627370496L;
      }

      return var0;
   }

   @Override
   public int getAccessibleRole() {
      return 6;
   }

   @Override
   public String getAccessibleName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public ButtonField() {
      this(0);
   }
}
