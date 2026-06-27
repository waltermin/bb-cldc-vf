package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class TextField extends Item {
   MIDPEditField _edit;
   public static final int ANY;
   public static final int EMAILADDR;
   public static final int NUMERIC;
   public static final int PHONENUMBER;
   public static final int URL;
   public static final int DECIMAL;
   public static final int PASSWORD;
   public static final int UNEDITABLE;
   public static final int SENSITIVE;
   public static final int NON_PREDICTIVE;
   public static final int INITIAL_CAPS_WORD;
   public static final int INITIAL_CAPS_SENTENCE;
   public static final int CONSTRAINT_MASK;

   public TextField(String var1, String var2, int var3, int var4) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._edit.setChangeListener(null);
      this._edit.setChangeListener(var1);
      return this._edit;
   }

   public String getString() {
      return this._edit.getString();
   }

   public void setString(String var1) {
      this._edit.setString(var1);
   }

   public int getChars(char[] var1) {
      return this._edit.getChars(var1);
   }

   public void setChars(char[] var1, int var2, int var3) {
      this._edit.setChars(var1, var2, var3);
   }

   public void insert(String var1, int var2) {
      this._edit.insert(var1, var2);
   }

   public void insert(char[] var1, int var2, int var3, int var4) {
      this._edit.insert(var1, var2, var3, var4);
   }

   public void delete(int var1, int var2) {
      this._edit.delete(var1, var2);
   }

   public int getMaxSize() {
      return this._edit.getMaxSize();
   }

   public int setMaxSize(int var1) {
      return this._edit.setMaxSize(var1);
   }

   public int size() {
      return this._edit.size();
   }

   public int getCaretPosition() {
      return this._edit.getCaretPosition();
   }

   public void setConstraints(int var1) {
      this._edit.setConstraints(var1);
   }

   public int getConstraints() {
      return this._edit.getConstraints();
   }

   @Override
   public String getLabel() {
      return this._edit.getLabel();
   }

   @Override
   public void setLabel(String var1) {
      this._edit.setLabel(var1);
   }

   public void setInitialInputMode(String var1) {
   }
}
