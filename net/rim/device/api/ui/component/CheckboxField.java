package net.rim.device.api.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.text.TextRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.util.StringProvider;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.SystemIcon;

public class CheckboxField extends Field implements FieldLabelProvider {
   private boolean _checked;
   private String _label;
   private String _optionsMenuText;
   private Bitmap _image;
   private int _iconWidth;
   private int _iconHeight;
   private TextRect _text = (TextRect)(new Object(this));
   private static Tag TAG;
   private static final char CHECKED;
   private static final char UNCHECKED;
   public static final long NO_USE_ALL_WIDTH;
   public static final long SELECT_ON_CLICK;
   private static final int PADDING;
   private static MenuItem _changeOptionsItem;

   public boolean getChecked() {
      return this._checked;
   }

   public void setOptionsMenuText(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setChecked(boolean var1) {
      this._checked = var1;
      this.fieldChangeNotify(Integer.MIN_VALUE);
      this.invalidate();
   }

   public void setImage(Bitmap var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setLabel(String var1) {
      this._label = var1;
      this._text.setText(var1);
      this.updateLayout();
      this.invalidate();
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getLabel() {
      return this._label;
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      this.invalidate();
   }

   @Override
   protected void onUnfocus() {
      super.onUnfocus();
      this.invalidate();
   }

   @Override
   public void getFocusRect(XYRect var1) {
      int var2 = this.getTopAdjustment();
      var1.set(0, 0, this._iconWidth, var2 + this._iconHeight);
   }

   private int getTopAdjustment() {
      if (this._text.getLineCount() == 0) {
         return 0;
      }

      int var1 = this._text.getLineHeight(0) - this.getFont().getHeight();
      if (var1 < 0) {
         var1 = 0;
      }

      return var1;
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFont().getHeight();
      int var2 = SystemIcon.COLLECTION.getHeight(var1, var1);
      return Math.max(var1, var2);
   }

   @Override
   public int getPreferredWidth() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            if (this.isEditable()) {
               this.toggle();
               return true;
            }
         default:
            return false;
      }
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (this.isEditable() && var1 == ' ') {
         this.toggle();
         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(1, new Object(2), new Object(64), this);
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      if (this.isEditable() && Keypad.key(var1) == 71 && InternalServices.isReducedFormFactor() && (Keypad.status(var1) & 1) == 0) {
         this.toggle();
         return true;
      } else {
         return super.keyDown(var1, var2);
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      Font var3 = this.getFont();
      int var4 = var3.getHeight();
      this._iconWidth = SystemIcon.COLLECTION.getWidth(var4, var4);
      this._iconHeight = Math.max(var4, SystemIcon.COLLECTION.getHeight(var4, var4));
      var2 = this._iconHeight;
      int var5 = this._iconWidth;
      int var6 = 0;
      if (this._image != null) {
         var6 = this._image.getWidth() + 2;
         var2 = Math.max(this._image.getHeight(), var2);
      }

      this._text.setPosition(var5 + var6 + 2, 0);
      this._text.layout(Math.max(var1 - var5 - var6 - 2, 0), var2);
      var2 = Math.max(this._text.getHeight(), var2);
      if (!this.isStyle(1152921504606846976L)) {
         var1 = var5 + var6 + 2;
         if (this._text.getText() != null) {
            XYRect var7 = Ui.getTmpXYRect();
            this._text.getTextBounds(var7);
            var1 += var7.x + var7.width;
            Ui.returnTmpXYRect(var7);
         }
      }

      this.setExtent(var1, var2);
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      if (Ui.getMode() < 2 && this.isEditable()) {
         if (this._optionsMenuText == null) {
            var1.addItem(_changeOptionsItem);
            return;
         }

         CheckboxField$ChangeOptionMenuItem var2 = new CheckboxField$ChangeOptionMenuItem(this._optionsMenuText);
         var1.addItem(var2);
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if (this.isEditable() && (var2 & 1) != 0 && (var2 & 196608) == 0) {
         this.toggle();
         return 0;
      } else {
         return var1;
      }
   }

   private int getIconIndex() {
      if (this._checked) {
         return this.isEditable() ? 1 : 8;
      } else {
         return this.isEditable() ? 0 : 7;
      }
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = this.getIconIndex();
      if (this.isFocus() && SystemIcon.COLLECTION.containsIcon(this._iconHeight, this.getIconIndex() + 12)) {
         var2 += 12;
      }

      int var3 = this.getTopAdjustment();
      SystemIcon.COLLECTION.paint(var1, 0, var3, this._iconWidth, this._iconHeight, var2);
      if (this._image != null) {
         var1.drawBitmap(this._iconWidth, var3, this._image.getWidth(), this._image.getHeight(), this._image, 0, 0);
      }

      this._text.paintSelf(var1);
   }

   public CheckboxField(String var1, boolean var2, long var3) {
      super(verifyStyle(var3));
      this.setTag(TAG);
      this.setLabel(var1);
      this._checked = var2;
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this._text.applyTheme();
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (!SystemIcon.COLLECTION.containsIcon(this._iconHeight, this.getIconIndex() + 12)) {
         super.drawFocus(var1, var2);
      }
   }

   public CheckboxField() {
      this(null, false, 0);
   }

   public CheckboxField(String var1, boolean var2) {
      this(var1, var2, 0);
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      if (!this.isEditable()) {
         return false;
      }

      if (var1 < 0) {
         return false;
      }

      if (var2 < 0) {
         return false;
      }

      if (var1 > this._iconWidth) {
         return false;
      }

      if (var2 > this._iconHeight) {
         return false;
      }

      this.toggle();
      return true;
   }

   private void toggle() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(1, new Object(2), new Object(64), this);
      }

      if (this.isStyle(1073741824) && this.isEditable()) {
         this.toggle();
         return true;
      } else {
         return super.trackwheelClick(var1, var2);
      }
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
      return 8;
   }

   @Override
   public String getAccessibleName() {
      return this.getLabel();
   }
}
