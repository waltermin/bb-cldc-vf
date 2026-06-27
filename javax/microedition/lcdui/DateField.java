package javax.microedition.lcdui;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class DateField extends Item {
   private net.rim.device.api.ui.component.DateField _field;
   private int _mode;
   private Calendar _calendar;
   private static final long MILLIS_IN_A_DAY;
   public static final int DATE;
   public static final int TIME;
   public static final int DATE_TIME;

   private int toRimMode(int var1) {
      switch (var1) {
         case 0:
            throw new Object();
         case 1:
         default:
            return 16;
         case 2:
            return 32;
         case 3:
            return 48;
      }
   }

   public DateField(String var1, int var2) {
      this(var1, var2, null);
   }

   public DateField(String var1, int var2, TimeZone var3) {
   }

   public Date getDate() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setDate(Date var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getInputMode() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setInputMode(int var1) {
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

   @Override
   Field addToForm(FieldChangeListener var1) {
      this._field.setChangeListener(null);
      this._field.setChangeListener(var1);
      return this._field;
   }
}
