package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
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

   public LabelField(Object text) {
      this(text, 0, -1, 0);
   }

   public LabelField(Object text, long style) {
      this(text, 0, -1, style);
   }

   public LabelField(Object text, int offset, int length, long style) {
      super(style);
      if ((style & 18014398509481984L) != 0) {
         this._position = 1;
      }

      this._labelText = new TextRect(this, this.getFieldStyle() & 199);
      this.setText(text, offset, length);
   }

   public LabelField(ResourceBundleFamily rb, int key) {
      this(null);
      this.setText(rb, key);
   }

   private void checkLocale() {
      if (this._rbId != 0) {
         int currentCode = Locale.getDefault().getCode();
         if (this._cachedLocaleCode != currentCode) {
            this._cachedLocaleCode = currentCode;
            ResourceBundleFamily family = ResourceBundle.getBundle(this._rbId, this._rbName);
            String translated = family.getString(this._rbKey);
            this.setTextInternal(translated, 0, translated.length());
         }
      }
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
      Font font = this.getFont();
      int width = 0;
      if (this._image != null) {
         width += this._image.getWidth();
         width += font.getHeight() >> 2;
      }

      if (this._length != 0) {
         int measureLength = Math.min(this._length, Display.getWidth() >> 1);
         width += font.getBounds(this._text, 0, measureLength) + this._position + 1;
      }

      return width;
   }

   public String getText() {
      this.checkLocale();
      return this._text;
   }

   @Override
   protected void layout(int width, int height) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected void paint(Graphics graphics) {
      this.checkLocale();
      int x = this._position;
      int imageHeight = 0;
      int textHeight = this.getFont().getHeight();
      if (this._image != null) {
         imageHeight = this._image.getHeight();
         graphics.drawBitmap(x, 0, this._image.getWidth(), imageHeight, this._image, 0, 0);
         x += this._image.getWidth();
         x += textHeight >> 2;
      }

      if (this._isSingleLine) {
         int y = 0;
         int labelTextHeight = this._labelText.getHeight();
         if (imageHeight > labelTextHeight) {
            y = imageHeight - labelTextHeight >> 1;
         }

         y = Math.max(labelTextHeight - textHeight, y);
         int width = this.getContentWidth() - 1 - x;
         graphics.drawText(this._text, x, y, this.getFieldStyle() & 199, width);
      } else {
         this._labelText.paintSelf(graphics);
      }
   }

   @Override
   public void selectionCopy(Clipboard cb) {
      cb.put(this.getText());
   }

   public void setImage(Bitmap image) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setPosition(int position) {
      if (position < 0) {
         throw new IllegalArgumentException();
      }

      this._position = position;
      Manager manager = this.getManager();
      if (manager != null && this.isStyle(1152921504606846976L)) {
         this.updateLayout();
         this.focusAdd(false);
      }

      this.invalidate();
   }

   public void setText(Object text) {
      this.setText(text, 0, -1);
   }

   public void setText(Object text, int offset, int length) {
      this._rbId = 0;
      this._rbName = null;
      this.setTextInternal(text, offset, length);
   }

   public void setText(String text, int offset, int length) {
      this.setText((Object)text, offset, length);
   }

   public void setText(ResourceBundleFamily rb, int key) {
      if (rb == null) {
         this.setText(null);
      } else {
         this._cachedLocaleCode = Locale.getDefault().getCode();
         this._rbId = rb.getId();
         this._rbName = rb.getName();
         this._rbKey = key;
         String translated = rb.getString(key);
         this.setTextInternal(translated, 0, translated.length());
      }
   }

   private void setTextInternal(Object text, int offset, int length) {
      throw new RuntimeException("cod2jar: type check");
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
