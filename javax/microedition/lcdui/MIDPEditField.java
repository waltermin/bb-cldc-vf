package javax.microedition.lcdui;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.text.TextFilter;

class MIDPEditField extends VerticalFieldManager {
   int _constraints;
   BasicEditField _field;
   FieldChangeListener _fieldChangeListener;
   Item _item;
   private static TextFilter _phoneFilter;

   public MIDPEditField(String label, String text, int maxSize, int constraints, Item item) {
      super(1152921504606846976L);
      this._item = item;
      if (!this.validateText(text, constraints)) {
         throw new Object();
      }

      if (!this.init(label, text, maxSize, constraints)) {
         throw new Object();
      }
   }

   @Override
   public void setChangeListener(FieldChangeListener listener) {
      this._fieldChangeListener = listener;
      this._field.setChangeListener(listener);
   }

   private boolean validateText(String text, int constraints) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final String augmentLabel(String label) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private boolean init(String label, String text, int maxSize, int constraints) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getString() {
      synchronized (Application.getEventLock()) {
         return this._field.getText();
      }
   }

   public void setString(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int getChars(char[] data) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setChars(char[] data, int offset, int length) {
      synchronized (Application.getEventLock()) {
         String text = null;
         if (data != null) {
            try {
               text = (String)(new Object(data, offset, length));
            } catch (StringIndexOutOfBoundsException e) {
               throw new Object();
            }
         }

         this.setString(text);
      }
   }

   public void insert(String src, int position) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void insert(char[] data, int offset, int length, int position) {
      synchronized (Application.getEventLock()) {
         if (offset >= 0 && length >= 0 && offset <= data.length - length) {
            this.insert((String)(new Object(data, offset, length)), position);
         } else {
            throw new Object();
         }
      }
   }

   public void delete(int offset, int length) {
      synchronized (Application.getEventLock()) {
         if (length > 0) {
            String currentText = this._field.getText();
            StringBuffer textAfterDelete = (StringBuffer)(new Object(currentText));
            if (!this.validateText(textAfterDelete.delete(offset, offset + length).toString(), this._constraints)) {
               throw new Object();
            }
         }

         if (offset >= 0 && length >= 0 && offset + length <= this._field.getTextLength()) {
            this._field.setCursorPosition(offset + length);
            this._field.backspace(length);
         } else {
            throw new Object();
         }
      }
   }

   public int getMaxSize() {
      synchronized (Application.getEventLock()) {
         return this._field.getMaxSize();
      }
   }

   public int setMaxSize(int maxSize) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public int size() {
      synchronized (Application.getEventLock()) {
         return this._field.getTextLength();
      }
   }

   public int getCaretPosition() {
      synchronized (Application.getEventLock()) {
         return this._field.getCursorPosition();
      }
   }

   public int getConstraints() {
      synchronized (Application.getEventLock()) {
         return this._constraints;
      }
   }

   public void setConstraints(int constraints) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public String getLabel() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setLabel(String label) {
      synchronized (Application.getEventLock()) {
         this._field.setLabel(this.augmentLabel(label));
      }
   }
}
