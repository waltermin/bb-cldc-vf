package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class StringItem extends Item {
   StringItem$PrivateRichTextField _field;
   String _text;
   String _label;
   Font _font;
   int _appearanceMode;

   public StringItem(String var1, String var2) {
   }

   public StringItem(String var1, String var2, int var3) {
   }

   @Override
   Field addToForm(FieldChangeListener var1) {
      return this._field;
   }

   private String makeValue() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public String getText() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setText(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setLabel(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getLabel() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getAppearanceMode() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setFont(Font var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Font getFont() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
