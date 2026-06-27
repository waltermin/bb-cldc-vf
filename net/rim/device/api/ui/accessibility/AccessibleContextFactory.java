package net.rim.device.api.ui.accessibility;

public class AccessibleContextFactory implements AccessibleContext {
   String _name;
   String _description;
   int _roll;
   int _state;
   AccessibleContext _parent;
   AccessibleText _text;

   public AccessibleContextFactory(String var1) {
      this(var1, null, 0, 0, null, null);
   }

   public AccessibleContextFactory(String var1, int var2) {
      this(var1, null, var2, 0, null, null);
   }

   public AccessibleContextFactory(String var1, int var2, int var3) {
      this(var1, null, var2, var3, null, null);
   }

   public AccessibleContextFactory(String var1, int var2, int var3, AccessibleContext var4) {
      this(var1, null, var2, var3, var4, null);
   }

   public AccessibleContextFactory(String var1, String var2, int var3, int var4, AccessibleContext var5, AccessibleText var6) {
      this._name = var1;
      this._description = var2;
      this._roll = var3;
      this._state = var4;
      this._parent = var5;
      this._text = var6;
   }

   @Override
   public String getAccessibleName() {
      return this._name;
   }

   @Override
   public String getAccessibleDescription() {
      return this._description;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return this._text;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public int getAccessibleStateSet() {
      return this._state;
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (this._state & var1) != 0;
   }

   @Override
   public int getAccessibleRole() {
      return this._roll;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return this._parent;
   }

   @Override
   public int getAccessibleChildCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return null;
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return false;
   }
}
