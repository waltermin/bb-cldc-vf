package javax.microedition.lcdui;

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

   public MIDPEditField(String var1, String var2, int var3, int var4, Item var5) {
      super(1152921504606846976L);
      this._item = var5;
      if (!this.validateText(var2, var4)) {
         throw new Object();
      }

      if (!this.init(var1, var2, var3, var4)) {
         throw new Object();
      }
   }

   @Override
   public void setChangeListener(FieldChangeListener var1) {
      this._fieldChangeListener = var1;
      this._field.setChangeListener(var1);
   }

   private boolean validateText(String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final String augmentLabel(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private boolean init(String var1, String var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getString() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setString(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getChars(char[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setChars(char[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void insert(String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void insert(char[] var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void delete(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getMaxSize() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int setMaxSize(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int size() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getCaretPosition() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getConstraints() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setConstraints(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
