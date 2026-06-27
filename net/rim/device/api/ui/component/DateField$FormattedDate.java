package net.rim.device.api.ui.component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.rim.device.api.i18n.DateFormat;

class DateField$FormattedDate extends Date {
   private DateFormat _format;
   private Calendar _calendar;

   public DateField$FormattedDate(long var1, DateFormat var3, TimeZone var4) {
      super(var1);
      this._format = var3;
      this._calendar = Calendar.getInstance(var4);
      this._calendar.setTime(this);
   }

   @Override
   public void setTime(long var1) {
      super.setTime(var1);
      this._calendar.setTime(this);
   }

   @Override
   public String toString() {
      return this._format.format(this._calendar);
   }
}
