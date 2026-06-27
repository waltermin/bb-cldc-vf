package net.rim.device.api.ui.component;

import net.rim.device.api.util.StringUtilities;

public class ObjectComboField extends ComboField {
   protected ObjectComboField$ListCallback _listCallback;

   public ObjectComboField(String var1, Object[] var2) {
      this.setEditable(new ObjectComboField$Editable(var1));
      this.setList(new ListField());
      this.setController(new ObjectComboField$Controller(this));
      this._listCallback = new ObjectComboField$ListCallback(this, var2);
      this.getList().setCallback(this._listCallback);
   }

   protected boolean doFilter() {
      return true;
   }

   protected boolean matches(String var1, String var2) {
      return StringUtilities.startsWithIgnoreCase(var1, var2);
   }
}
