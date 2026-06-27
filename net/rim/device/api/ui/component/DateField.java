package net.rim.device.api.ui.component;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.i18n.DateFormatSymbols;
import net.rim.device.api.i18n.FieldPosition;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Trackball;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.text.TextRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.StringProvider;
import net.rim.device.cldc.util.CalendarExtensions;
import net.rim.device.internal.system.InternalServices;

public class DateField extends Field implements DrawStyle, FieldLabelProvider {
   private int _minimumWidth;
   private TextRect _label;
   private TextRect _dateTextRect;
   private DateFormat _format;
   private DateFormatSymbols _symbols;
   private TimeZone _timezone;
   private Calendar _utilCal;
   int _position;
   private int[] _c_fields;
   private XYRect[] _cursor;
   private int _oldWidth;
   private FieldPosition _c_position;
   private long _minuteIncrements;
   private boolean _isLabelOwnLine;
   private int[] _focus_order;
   boolean _first_focus;
   private static Tag TAG;
   private static Tag TAG_LABEL;
   public static final int DATE;
   public static final int TIME;
   public static final int CHANGE_ON_VERTICAL_SCROLL;
   public static final int FOCUS_MOVE_ON_HORIZONTAL_SCROLL;
   public static final int DATE_TIME;
   private static final int PADDING;
   private static MenuItem _changeOptionsItem;
   private static XYRect _rect;

   void calcCachedData() {
      if (this.getManager() != null && this.getManager().isValidLayout()) {
         XYRect var1 = this.getExtent();
         int var2 = var1.width;
         int var3 = var1.height;
         this.calcLayoutData(Math.max(this._oldWidth, this.getContentWidth()));
         var1 = this.getExtent();
         if (var1.width != var2 || var1.height != var3) {
            this.updateLayout();
         }

         this.invalidate();
      } else {
         this.formatDate(null);
      }
   }

   boolean changeOptionDialog() {
      if (this.isEditable() && this.getOriginal() == this) {
         DateField$2 var1 = new DateField$2(this, null, this.getDate(), this._format, 6);
         var1.setMinuteIncrements(this._minuteIncrements);
         var1.setTimeZone(this._timezone);
         var1.setChangeListener(this.getChangeListener());
         DateInPlaceScreen var2 = new DateInPlaceScreen(this, var1, 0);
         boolean var3 = var2.doModal();
         if (var3) {
            this.setDate(var1.getDate(), 0);
         } else {
            var1.setDate(this.getDate());
         }

         this._position = var1._position;
         this.calcCachedData();
         return true;
      } else {
         return false;
      }
   }

   protected int getCurrentSubfield() {
      return this._c_fields[this._position];
   }

   public long getDate() {
      return this._utilCal == null ? Long.MIN_VALUE : ((CalendarExtensions)this._utilCal).getTimeLong();
   }

   void getInPlaceRect(XYRect var1) {
      this._dateTextRect.getTextBounds(var1);
   }

   public void setTimeZone(TimeZone var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._timezone = var1;
      if (this._utilCal != null) {
         this._utilCal.setTimeZone(var1);
      }

      this.calcCachedData();
   }

   public int getMode() {
      int[] var1 = !this.isEditable() && this._format != null ? this._format.getFields() : this._c_fields;
      byte var2 = 0;

      for (int var3 = 0; var3 < var1.length; var3++) {
         switch (var1[var3]) {
            case 0:
            case 3:
            case 4:
            case 6:
            case 8:
            case 11:
               break;
            case 1:
            case 2:
            case 5:
            case 7:
            default:
               var2 |= 16;
               break;
            case 9:
            case 10:
            case 12:
               var2 |= 32;
         }
      }

      return var2;
   }

   public TimeZone getTimeZone() {
      return this._timezone;
   }

   boolean isSubFieldEditable(int var1) {
      boolean var2 = false;
      int var3 = this._c_fields[var1];
      switch (var3) {
         case 1:
         case 2:
         case 5:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         default:
            var2 = true;
         case 0:
         case 3:
         case 4:
         case 6:
         case 7:
         case 8:
            return var2;
      }
   }

   public void setDate(long var1) {
      this.setDate(var1, Integer.MIN_VALUE);
   }

   protected void setDate(long var1, int var3) {
      boolean var4 = false;
      if (var1 != Long.MIN_VALUE) {
         if (this._utilCal == null) {
            this._utilCal = Calendar.getInstance(this._timezone);
            var4 = true;
         }

         if (var1 != this.getDate()) {
            ((CalendarExtensions)this._utilCal).setTimeLong(var1);
            var4 = true;
         }
      } else if (this._utilCal != null) {
         this._utilCal = null;
         var4 = true;
      }

      if (var4) {
         this.calcCachedData();
         this.fieldChangeNotify(var3);
      }
   }

   public void setDate(Date var1) {
      this.setDate(var1, Integer.MIN_VALUE);
   }

   protected void setDate(Date var1, int var2) {
      boolean var3 = false;
      if (var1 != null) {
         if (this._utilCal == null) {
            this._utilCal = Calendar.getInstance(this._timezone);
            var3 = true;
         }

         if (!var1.equals(this._utilCal.getTime())) {
            this._utilCal.setTime(var1);
            var3 = true;
         }
      } else if (this._utilCal != null) {
         this._utilCal = null;
         var3 = true;
      }

      if (var3) {
         this.calcCachedData();
         this.fieldChangeNotify(var2);
      }
   }

   public void setFormat(DateFormat var1) {
      this._format = var1;
      if (this.isEditable()) {
         this._c_fields = this._format.getFields();
      } else {
         this._c_fields = new int[1];
      }

      this._position = 0;
      this.calcCachedData();
   }

   int getMinimumWidth() {
      return this._minimumWidth;
   }

   void setMinimumWidth(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setMinuteIncrements(long var1) {
      this._minuteIncrements = var1;
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setLabel(String var1) {
      this._label.setText(var1);
      this.calcCachedData();
   }

   @Override
   public String getLabel() {
      return (String)this._label.getText();
   }

   private boolean isEraSupported() {
      for (int var1 = 0; var1 < this._c_fields.length; var1++) {
         if (this._c_fields[var1] == 0) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isSelectionCopyable() {
      return true;
   }

   public DateField() {
      this(null, 0, 48);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char transformKeyChar(char var1, int var2) {
      if (InternalServices.isReducedFormFactor()) {
         switch (var1) {
            case ' ':
               switch (this._c_position.getField()) {
                  case 2:
                  case 9:
                     return var1;
                  default:
                     return Keypad.map(var1, 1);
               }
            case '0':
               var1 = Keypad.getUnaltedChar('0');
         }
      }

      return var1;
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      return Keypad.key(var1) == 257 && (Keypad.status(var1) & 1) != 0 ? this.changeOptionDialog() : false;
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            return this.changeOptionDialog();
         default:
            return false;
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      if (this.isEditable()) {
         this._c_fields = this._format.getFields();
      }

      this._oldWidth = var1;
      this.calcLayoutData(var1);
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      if (Ui.getMode() < 2 && this.isEditable()) {
         var1.addItem(_changeOptionsItem);
      }

      var1.setDefault(-1);
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      super.moveFocus(var1, var2, var3);
      if (((var2 & 536870912) == 0 || (var2 & 65536) == 0 && (var2 & 131072) != 0) && (var2 & 1) != 0 && this.isEditable()) {
         this.calcDateFromFieldChange(Ui.getIncreaseDirection() * var1);
         this.calcCachedData();
         this.fieldChangeNotify(0);
         this.clampDateOnInterval(var2, var1);
         return 0;
      }

      if ((var2 & 536870912) != 0 && (var2 & 65536) == 0) {
         if ((var2 & 131072) != 0 && this.isChangeOnVerticalScrollSet()) {
            this.calcDateFromFieldChange(Ui.getIncreaseDirection() * var1);
            this.calcCachedData();
            this.fieldChangeNotify(0);
            return 0;
         } else {
            return var1;
         }
      } else {
         byte var4;
         if (var1 > 0) {
            var4 = 1;
         } else {
            var4 = -1;
         }

         int var5 = this._c_fields.length - 1;
         int var6 = this._position;
         int var7 = var6;

         label90:
         while (var1 != 0) {
            do {
               if ((var2 & 536870912) != 0) {
                  if (!this.isEditable()) {
                     var1 = 0;
                     break label90;
                  }

                  if (var6 + var4 > var5) {
                     if (this.isFocusMoveOnHorizontalScrollSet()) {
                        return var1;
                     }

                     var6 = var5;

                     while (!this.isSubFieldEditable(var6)) {
                        var6--;
                     }
                  } else if (var6 + var4 < 0) {
                     if (this.isFocusMoveOnHorizontalScrollSet()) {
                        return var1;
                     }

                     var6 = 0;

                     while (!this.isSubFieldEditable(var6)) {
                        var6++;
                     }
                  } else {
                     var6 += var4;
                  }
               } else {
                  var6 += var4;
               }

               if (var6 < 0 || var6 > var5) {
                  break label90;
               }
            } while (!this.isSubFieldEditable(var6));

            var7 = var6;
            var1 -= var4;
         }

         if (this._position != var7) {
            this._position = var7;
         }

         this.calcCachedData();
         this.clampDateOnInterval(var2, var1);
         return var1;
      }
   }

   @Override
   public void getFocusRect(XYRect var1) {
      if (this._cursor == null) {
         super.getFocusRect(var1);
      } else {
         var1.set(this._cursor[0]);
         int var2 = this._cursor.length;

         while (--var2 > 0) {
            var1.union(this._cursor[var2]);
         }
      }
   }

   private void clampDateOnInterval(int var1, int var2) {
      if ((var1 & 1) != 0 && this.isEditable() && this.getCurrentSubfield() == 12) {
         long var3 = this.getDate();
         long var5 = var3 / 60000 * 60000;
         long var7 = var3 / this._minuteIncrements;
         var7 *= this._minuteIncrements;
         if (var7 != var5) {
            if (var5 > 0 && var2 > 0) {
               var7 += this._minuteIncrements;
            } else if (var5 < 0 && var2 < 0) {
               var7 -= this._minuteIncrements;
            }
         }

         this.setDate(var7 + (var3 - var5), 0);
      }
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      if (!Trackball.isSupported()) {
         if (var1 == 1) {
            this._position = 0;

            while (this._position < this._c_fields.length - 1 && !this.isSubFieldEditable(this._position)) {
               this._position++;
            }
         } else if (var1 != -1) {
            while (this._position < this._c_fields.length - 1 && !this.isSubFieldEditable(this._position)) {
               this._position++;
            }

            if (!this.isSubFieldEditable(this._position)) {
               while (this._position > 0 && !this.isSubFieldEditable(this._position)) {
                  this._position--;
               }
            }
         } else {
            this._position = this._c_fields.length - 1;

            while (this._position > 0 && !this.isSubFieldEditable(this._position)) {
               this._position--;
            }
         }
      } else {
         while (this._position < this._c_fields.length && !this.isSubFieldEditable(this._position)) {
            this._position++;
         }

         if (this._first_focus) {
            int var2 = 0;

            while (var2 < this._focus_order.length && this._focus_order[var2] != this._position) {
               var2++;
            }

            label93:
            for (int var3 = 0; var3 < var2; var3++) {
               for (int var4 = 0; var4 < this._c_fields.length; var4++) {
                  if (this._c_fields[var4] == this._focus_order[var3] && this.isSubFieldEditable(var4)) {
                     this._position = var4;
                     break label93;
                  }
               }
            }
         }

         this._first_focus = false;
      }

      this.calcCachedData();
   }

   @Override
   protected void paint(Graphics var1) {
      this._label.paintSelf(var1);
      this._dateTextRect.paintSelf(var1);
   }

   @Override
   public void selectionCopy(Clipboard var1) {
      DateField$FormattedDate var2 = new DateField$FormattedDate(this.getDate(), this._format, this._timezone);
      if (ControlledAccess.verifyRRISignatures(true)) {
         var1.put(var2);
      } else {
         var1.put(var2.toString());
      }
   }

   private void calcDateFromFieldChange(int var1) {
      int var2 = this._c_fields[this._position];
      if (this._utilCal == null) {
         this._utilCal = Calendar.getInstance(this._timezone);
      } else {
         switch (var2) {
            case 0:
            case 3:
            case 4:
            case 6:
            case 8:
               break;
            case 1:
            case 2:
            case 5:
            case 10:
            case 11:
            case 13:
            case 14:
            default:
               ((CalendarExtensions)this._utilCal).add(var2, var1);
               break;
            case 7:
               ((CalendarExtensions)this._utilCal).add(5, var1);
               break;
            case 9:
               ((CalendarExtensions)this._utilCal).roll(9, var1);
               break;
            case 12:
               int var3 = (int)(this._minuteIncrements / 60000);
               int var4 = this._utilCal.get(var2) % var3;
               ((CalendarExtensions)this._utilCal).add(var2, 0 > var1 && 0 < var4 ? (var1 + 1) * var3 - var4 : var1 * var3 - var4);
         }

         if (!this.isEraSupported()) {
            int var5 = this._utilCal.get(0);
            if (var5 == 0) {
               this._utilCal.set(0, 1);
            }
         }
      }
   }

   public DateField(String var1, long var2, long var4) {
      this(var1, var2, getDateFormatFromStyle(var4), var4);
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public DateField(String var1, long var2, DateFormat var4) {
      this(var1, var2, var4, 0);
   }

   @Override
   public void setEditable(boolean var1) {
      super.setEditable(var1);
      if (this.isEditable()) {
         this._c_fields = this._format.getFields();
      } else {
         this._c_fields = new int[1];
         this._position = 0;
      }
   }

   public DateField(String var1, long var2, long var4, DateFormat var6) {
      this(var1, var2, var6, var4);
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFont().getHeight();
      int var2 = this._isLabelOwnLine && this._label.getTextString() != null ? 2 : 1;
      return var1 * var2;
   }

   @Override
   public int getPreferredWidth() {
      Object var1 = this._dateTextRect.getText();
      ((StringBuffer)var1).setLength(0);
      this._format.format(this._utilCal, (StringBuffer)var1, null);
      Font var2 = this.getFont();
      int var3 = var2.getBounds((StringBuffer)var1, 0, ((StringBuffer)var1).length());
      int var4 = var2.getBounds(this._label.getTextString());
      if (var4 != 0) {
         var4 += 5;
      }

      return Math.max(this._minimumWidth, var3 + var4);
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this._label.applyTheme();
      this._isLabelOwnLine = ThemeManager.getActiveTheme().isLabelOnOwnLine();
      if (this._isLabelOwnLine) {
         int var1 = this.getFieldStyle();
         var1 &= -8;
         var1 |= 6;
         this._dateTextRect.setStyle(var1);
      }
   }

   private void calcLayoutData(int var1) {
      this._position = MathUtilities.clamp(0, this._position, this._c_fields.length - 1);
      this._c_position.setField(this._c_fields[this._position]);
      this.formatDate(this._c_position);
      byte var2 = 0;
      int var3 = 0;
      this._label.layout(var1, Integer.MAX_VALUE);
      int var6 = this.getFieldStyle();
      this._dateTextRect.layout(var1, Integer.MAX_VALUE);
      int var7 = this._dateTextRect.getWidth();
      int var8 = this._dateTextRect.getHeight();
      int var9 = this._label.getWidth();
      int var4;
      int var5;
      if (var9 <= 0) {
         var4 = 0;
         var5 = 0;
      } else if (this._isLabelOwnLine) {
         var4 = 0;
         var5 = this._label.getHeight();
         var8 += var5;
      } else {
         int var10 = var9 + 5 + var7;
         if (var10 <= var1) {
            var4 = var9 + 5;
            var5 = this._label.getLineHeight(0) - this._dateTextRect.getLineHeight(0);
            if (var5 < 0) {
               var3 = -var5;
               var5 = 0;
            }

            var7 = var10;
            this._dateTextRect.reduceWidthToFit(var1 - var4);
         } else {
            var4 = 0;
            var5 = this._label.getHeight();
            var7 = Math.max(var9, var7);
            var8 += var5;
         }
      }

      if ((var6 & 1152921504606846976L) != 0 || (var6 & 7) != 6) {
         var7 = var1;
      }

      if (this._minimumWidth > 0) {
         var7 = Math.max(var7, this._minimumWidth);
      }

      this._label.setPosition(var2, var3);
      this._dateTextRect.setPosition(var4, var5);
      this.setExtent(var7, var8);
      if (this.isEditable() && this.isSubFieldEditable(this._position)) {
         this._cursor = this._dateTextRect.getTextBounds(this._c_position.getBeginIndex(), this._c_position.getEndIndex());
      } else {
         this._cursor = null;
      }
   }

   public DateField(String var1, long var2, int var4, DateFormat var5) {
      this(var1, var2, (long)var4, var5);
   }

   public DateField(String var1, long var2, DateFormat var4, long var5) {
   }

   @Override
   public String toString() {
      return this._dateTextRect.getTextString();
   }

   private static long validateStyle(long var0) {
      if ((var0 & 7) == 0) {
         var0 |= 5;
      }

      if ((var0 & 13510798882111488L) == 0) {
         var0 |= 4503599627370496L;
      }

      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0 & -49;
   }

   private static DateFormat getDateFormatFromStyle(long var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean usingAlphaMonth() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private boolean isChangeOnVerticalScrollSet() {
      return (this.getStyle() & 64) > 0;
   }

   private boolean isFocusMoveOnHorizontalScrollSet() {
      return (this.getStyle() & 128) > 0;
   }

   private void formatDate(FieldPosition var1) {
      Object var2 = this._dateTextRect.getText();
      ((StringBuffer)var2).setLength(0);
      this._format.format(this._utilCal, (StringBuffer)var2, var1);
      this._dateTextRect.setText(var2);
   }

   @Override
   public int getAccessibleRole() {
      return 9;
   }

   @Override
   public String getAccessibleName() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }
}
