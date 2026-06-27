package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class Spacer extends Item {
   private SpacerField _spacerField;

   public Spacer(int var1, int var2) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._spacerField.setChangeListener(null);
      this._spacerField.setChangeListener(var1);
      return this._spacerField;
   }

   public void setMinimumSize(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void addCommand(Command var1) {
      throw new Object();
   }

   @Override
   public void setDefaultCommand(Command var1) {
      throw new Object();
   }

   @Override
   public void setLabel(String var1) {
      throw new Object();
   }

   @Override
   public String getLabel() {
      return null;
   }
}
