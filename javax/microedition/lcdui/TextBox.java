package javax.microedition.lcdui;

public class TextBox extends Screen {
   MIDPEditField _edit;

   public TextBox(String var1, String var2, int var3, int var4) {
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

   public void setInitialInputMode(String var1) {
   }
}
