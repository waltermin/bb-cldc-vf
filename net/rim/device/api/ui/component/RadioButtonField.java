package net.rim.device.api.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
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

public class RadioButtonField extends Field implements FieldLabelProvider {
   private String _label;
   RadioButtonGroup _group;
   int _index = -1;
   private String _optionsMenuText;
   private Bitmap _image;
   private int _iconWidth;
   private int _iconHeight;
   private TextRect _text = (TextRect)(new Object(this));
   private XYRect _focus = (XYRect)(new Object());
   private static Tag TAG;
   private static final int PADDING;
   public static final long NO_USE_ALL_WIDTH;
   private static MenuItem _changeOptionsItem;

   public void setSelected(boolean var1) {
      this.setSelected(var1, Integer.MIN_VALUE);
   }

   public RadioButtonGroup getGroup() {
      return this._group;
   }

   public boolean isSelected() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void selectionChange(int var1) {
      this.invalidate();
      this.fieldChangeNotify(var1);
   }

   public void setImage(Bitmap var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setOptionsMenuText(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setLabel(String var1) {
      this._label = var1;
      this._text.setText(var1);
      this.updateLayout();
      this.invalidate();
   }

   @Override
   public String getLabel() {
      return this._label;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      var1.set(0, 0, this._iconWidth, this._iconHeight);
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

   public RadioButtonField(String var1) {
      this(var1, null, false, 0);
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            this.setSelected(true, 0);
            return true;
         default:
            return false;
      }
   }

   public RadioButtonField(String var1, RadioButtonGroup var2, boolean var3) {
      this(var1, var2, var3, 0);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = false;
      if (this.isEditable()) {
         switch (var1) {
            case '\n':
            case ' ':
               this.setSelected(true, 0);
               var4 = true;
               if (Ui.isTTSEnabled()) {
                  super.accessibleEventOccurred(1, new Object(2), new Object(4), this);
               }
         }
      }

      return var4;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      if (this.isEditable() && Keypad.key(var1) == 71 && InternalServices.isReducedFormFactor()) {
         this.setSelected(true, 0);
         return true;
      } else {
         return super.keyDown(var1, var2);
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      if (Ui.getMode() < 2 && this.isEditable()) {
         if (this._optionsMenuText == null) {
            var1.addItem(_changeOptionsItem);
            return;
         }

         RadioButtonField$ChangeOptionMenuItem var2 = new RadioButtonField$ChangeOptionMenuItem(this._optionsMenuText);
         var1.addItem(var2);
      }
   }

   private int getIconIndex() {
      if (this.isSelected()) {
         return this.isEditable() ? 3 : 11;
      } else {
         return this.isEditable() ? 2 : 10;
      }
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = this.getIconIndex();
      if (this.isFocus() && SystemIcon.COLLECTION.containsIcon(this._iconHeight, this.getIconIndex() + 12)) {
         var2 += 12;
      }

      int var3 = this._iconWidth;
      int var4 = 0;
      SystemIcon.COLLECTION.paint(var1, 0, 0, this._iconWidth, this._iconHeight, var2);
      if (this._image != null) {
         var4 = this._image.getHeight();
         var1.drawBitmap(var3, 0, this._image.getWidth(), var4, this._image, 0, 0);
      }

      this._text.paintSelf(var1);
   }

   public RadioButtonField(String var1, RadioButtonGroup var2, boolean var3, long var4) {
      super(verifyStyle(var4));
      this.setTag(TAG);
      this.setLabel(var1);
      if (var2 != null) {
         var2.add(this);
         if (var3) {
            var2.setSelectedIndex(this._index);
         }
      }
   }

   @Override
   protected void applyTheme() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void onUnfocus() {
      super.onUnfocus();
      this.invalidate();
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      this.invalidate();
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (!SystemIcon.COLLECTION.containsIcon(this._iconHeight, this.getIconIndex() + 12)) {
         this.getFocusRect(this._focus);
         var1.invert(this._focus);
         int var3 = this.getFont().getHeight();
         var1.invert(this._focus.x, this._focus.y + var3, this._focus.width - 1, this._focus.height - var3);
      }
   }

   public RadioButtonField() {
      this(null, null, false, 0);
   }

   private void setSelected(boolean var1, int var2) {
      if (this._group == null) {
         throw new Object();
      }

      if (var1) {
         this._group.setSelectedIndex(this._index, var2);
      } else if (this.isSelected()) {
         this._group.setSelectedIndex(-1, var2);
      }

      this.invalidate();
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(1, new Object(2), new Object(4), this);
      }

      return super.trackwheelClick(var1, var2);
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
      return 11;
   }

   @Override
   public String getAccessibleName() {
      return this.getLabel();
   }
}
