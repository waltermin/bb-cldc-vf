package javax.microedition.lcdui;

import net.rim.device.api.ui.text.NumericTextFilter;

class MIDPEditField$1 extends NumericTextFilter {
   @Override
   public boolean validate(char var1) {
      return var1 != '.' && var1 != '-' ? super.validate(var1) : true;
   }
}
