package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.text.TextRect;

public class LabelField extends Field implements DrawStyle {
   private long _rbId;
   private int _rbKey;
   private String _rbName;
   private int _cachedLocaleCode;
   private int _position;
   private String _text;
   private TextRect _labelText;
   private int _length;
   private Bitmap _image;
   private boolean _inSetTextInternal;
   private boolean _isSingleLine;
   private boolean _inLayout;
   public static final int DEFAULT_POSITION;
   public static final long REMOVE_COMBINING_LOW_LINE;
   private static final int ATTRIBUTES_MASK;
   private static final int STRING;
   private static final int STRINGBUFFER;
   private static final int CHARARRAY;
   private static final int BYTEARRAY;

   public LabelField() {
      this(null);
   }

   public LabelField(Object var1) {
      this(var1, 0, -1, 0);
   }

   public LabelField(Object var1, long var2) {
      this(var1, 0, -1, var2);
   }

   public LabelField(Object var1, int var2, int var3, long var4) {
      super(var4);
      if ((var4 & 18014398509481984L) != 0) {
         this._position = 1;
      }

      this._labelText = (TextRect)(new Object(this, this.getFieldStyle() & 199));
      this.setText(var1, var2, var3);
   }

   public LabelField(ResourceBundleFamily var1, int var2) {
      this(null);
      this.setText(var1, var2);
   }

   private void checkLocale() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public boolean isSelectionCopyable() {
      return true;
   }

   public int getPosition() {
      return this._position;
   }

   @Override
   public int getPreferredHeight() {
      return this.getFont().getHeight();
   }

   @Override
   public int getPreferredWidth() {
      this.checkLocale();
      Font var1 = this.getFont();
      int var2 = 0;
      if (this._image != null) {
         var2 += this._image.getWidth();
         var2 += var1.getHeight() >> 2;
      }

      if (this._length != 0) {
         int var3 = Math.min(this._length, Display.getWidth() >> 1);
         var2 += var1.getBounds(this._text, 0, var3) + this._position + 1;
      }

      return var2;
   }

   public String getText() {
      this.checkLocale();
      return this._text;
   }

   @Override
   protected void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected void paint(Graphics var1) {
      this.checkLocale();
      int var2 = this._position;
      int var3 = 0;
      int var4 = this.getFont().getHeight();
      if (this._image != null) {
         var3 = this._image.getHeight();
         var1.drawBitmap(var2, 0, this._image.getWidth(), var3, this._image, 0, 0);
         var2 += this._image.getWidth();
         var2 += var4 >> 2;
      }

      if (this._isSingleLine) {
         int var5 = 0;
         int var6 = this._labelText.getHeight();
         if (var3 > var6) {
            var5 = var3 - var6 >> 1;
         }

         var5 = Math.max(var6 - var4, var5);
         int var7 = this.getContentWidth() - 1 - var2;
         var1.drawText(this._text, var2, var5, this.getFieldStyle() & 199, var7);
      } else {
         this._labelText.paintSelf(var1);
      }
   }

   @Override
   public void selectionCopy(Clipboard var1) {
      var1.put(this.getText());
   }

   public void setImage(Bitmap var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setPosition(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this._position = var1;
      Manager var2 = this.getManager();
      if (var2 != null && this.isStyle(1152921504606846976L)) {
         this.updateLayout();
         this.focusAdd(false);
      }

      this.invalidate();
   }

   public void setText(Object var1) {
      this.setText(var1, 0, -1);
   }

   public void setText(Object var1, int var2, int var3) {
      this._rbId = 0;
      this._rbName = null;
      this.setTextInternal(var1, var2, var3);
   }

   public void setText(String var1, int var2, int var3) {
      this.setText((Object)var1, var2, var3);
   }

   public void setText(ResourceBundleFamily var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void setTextInternal(Object var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String toString() {
      return this.getText();
   }

   @Override
   public int getAccessibleRole() {
      return 16;
   }

   @Override
   public String getAccessibleName() {
      return this.toString();
   }
}
