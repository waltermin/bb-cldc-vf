package javax.microedition.lcdui;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class StringItem extends Item {
   StringItem$PrivateRichTextField _field;
   String _text;
   String _label;
   Font _font;
   int _appearanceMode;

   public StringItem(String label, String text) {
      synchronized (Application.getEventLock()) {
         this._label = label;
         this._text = text;
         this._field = new StringItem$PrivateRichTextField(this.makeValue());
         this._field.setCookie(this);
         this._font = Font.getDefaultFont();
         this._field.setFont(this._font.getPeer());
         this.setPeer(this._field);
      }
   }

   public StringItem(String label, String text, int appearanceMode) {
      this(label, text);
      synchronized (Application.getEventLock()) {
         if (appearanceMode != 0 && appearanceMode != 1 && appearanceMode != 2) {
            throw new Object();
         }

         this._appearanceMode = appearanceMode;
      }
   }

   @Override
   Field addToForm(FieldChangeListener changeListener) {
      return this._field;
   }

   private String makeValue() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getText() {
      synchronized (Application.getEventLock()) {
         return this._text;
      }
   }

   public void setText(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public void setLabel(String label) {
      synchronized (Application.getEventLock()) {
         this._label = label;
         this._field.setText(this.makeValue());
      }
   }

   @Override
   public String getLabel() {
      synchronized (Application.getEventLock()) {
         return this._label;
      }
   }

   public int getAppearanceMode() {
      synchronized (Application.getEventLock()) {
         return this._appearanceMode;
      }
   }

   public void setFont(Font font) {
      synchronized (Application.getEventLock()) {
         if (font == null) {
            this._font = Font.getDefaultFont();
         } else {
            this._font = font;
         }

         this._field.setFont(this._font.getPeer());
      }
   }

   public Font getFont() {
      synchronized (Application.getEventLock()) {
         return this._font;
      }
   }
}
