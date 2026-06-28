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
         XYRect extent = this.getExtent();
         int oldWidth = extent.width;
         int oldHeight = extent.height;
         this.calcLayoutData(Math.max(this._oldWidth, this.getContentWidth()));
         extent = this.getExtent();
         if (extent.width != oldWidth || extent.height != oldHeight) {
            this.updateLayout();
         }

         this.invalidate();
      } else {
         this.formatDate(null);
      }
   }

   boolean changeOptionDialog() {
      if (this.isEditable() && this.getOriginal() == this) {
         DateField field = new DateField$2(this, null, this.getDate(), this._format, 6);
         field.setMinuteIncrements(this._minuteIncrements);
         field.setTimeZone(this._timezone);
         field.setChangeListener(this.getChangeListener());
         DateInPlaceScreen changeDialog = new DateInPlaceScreen(this, field, 0);
         boolean accepted = changeDialog.doModal();
         if (accepted) {
            this.setDate(field.getDate(), 0);
         } else {
            field.setDate(this.getDate());
         }

         this._position = field._position;
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

   void getInPlaceRect(XYRect rect) {
      this._dateTextRect.getTextBounds(rect);
   }

   public void setTimeZone(TimeZone tz) {
      if (tz == null) {
         throw new NullPointerException();
      }

      this._timezone = tz;
      if (this._utilCal != null) {
         this._utilCal.setTimeZone(tz);
      }

      this.calcCachedData();
   }

   public int getMode() {
      int[] fields = !this.isEditable() && this._format != null ? this._format.getFields() : this._c_fields;
      int mode = 0;

      for (int lv = 0; lv < fields.length; lv++) {
         switch (fields[lv]) {
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
               mode |= 16;
               break;
            case 9:
            case 10:
            case 12:
               mode |= 32;
         }
      }

      return mode;
   }

   public TimeZone getTimeZone() {
      return this._timezone;
   }

   boolean isSubFieldEditable(int testPosition) {
      boolean supported = false;
      int testField = this._c_fields[testPosition];
      switch (testField) {
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
            supported = true;
         case 0:
         case 3:
         case 4:
         case 6:
         case 7:
         case 8:
            return supported;
      }
   }

   public void setDate(long date) {
      this.setDate(date, Integer.MIN_VALUE);
   }

   protected void setDate(long date, int context) {
      boolean changed = false;
      if (date != Long.MIN_VALUE) {
         if (this._utilCal == null) {
            this._utilCal = Calendar.getInstance(this._timezone);
            changed = true;
         }

         if (date != this.getDate()) {
            ((CalendarExtensions)this._utilCal).setTimeLong(date);
            changed = true;
         }
      } else if (this._utilCal != null) {
         this._utilCal = null;
         changed = true;
      }

      if (changed) {
         this.calcCachedData();
         this.fieldChangeNotify(context);
      }
   }

   public void setDate(Date date) {
      this.setDate(date, Integer.MIN_VALUE);
   }

   protected void setDate(Date date, int context) {
      boolean changed = false;
      if (date != null) {
         if (this._utilCal == null) {
            this._utilCal = Calendar.getInstance(this._timezone);
            changed = true;
         }

         if (!date.equals(this._utilCal.getTime())) {
            this._utilCal.setTime(date);
            changed = true;
         }
      } else if (this._utilCal != null) {
         this._utilCal = null;
         changed = true;
      }

      if (changed) {
         this.calcCachedData();
         this.fieldChangeNotify(context);
      }
   }

   public void setFormat(DateFormat format) {
      this._format = format;
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

   void setMinimumWidth(int minimumWidth) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setMinuteIncrements(long minuteIncrements) {
      this._minuteIncrements = minuteIncrements;
   }

   @Override
   public void setLabelStringProvider(StringProvider label) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setLabel(String label) {
      this._label.setText(label);
      this.calcCachedData();
   }

   @Override
   public String getLabel() {
      return (String)this._label.getText();
   }

   private boolean isEraSupported() {
      for (int lv = 0; lv < this._c_fields.length; lv++) {
         if (this._c_fields[lv] == 0) {
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
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private char transformKeyChar(char key, int status) {
      if (InternalServices.isReducedFormFactor()) {
         switch (key) {
            case ' ':
               switch (this._c_position.getField()) {
                  case 2:
                  case 9:
                     return key;
                  default:
                     return Keypad.map(key, 1);
               }
            case '0':
               key = Keypad.getUnaltedChar('0');
         }
      }

      return key;
   }

   @Override
   protected boolean keyControl(char key, int status, int time) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected boolean keyStatus(int keycode, int time) {
      return Keypad.key(keycode) == 257 && (Keypad.status(keycode) & 1) != 0 ? this.changeOptionDialog() : false;
   }

   @Override
   protected boolean invokeAction(int action) {
      switch (action) {
         case 1:
            return this.changeOptionDialog();
         default:
            return false;
      }
   }

   @Override
   protected void layout(int width, int height) {
      if (this.isEditable()) {
         this._c_fields = this._format.getFields();
      }

      this._oldWidth = width;
      this.calcLayoutData(width);
   }

   @Override
   protected void makeContextMenu(ContextMenu contextMenu) {
      super.makeContextMenu(contextMenu);
      if (Ui.getMode() < 2 && this.isEditable()) {
         contextMenu.addItem(_changeOptionsItem);
      }

      contextMenu.setDefault(-1);
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      super.moveFocus(amount, status, time);
      if (((status & 536870912) == 0 || (status & 65536) == 0 && (status & 131072) != 0) && (status & 1) != 0 && this.isEditable()) {
         this.calcDateFromFieldChange(Ui.getIncreaseDirection() * amount);
         this.calcCachedData();
         this.fieldChangeNotify(0);
         this.clampDateOnInterval(status, amount);
         return 0;
      }

      if ((status & 536870912) != 0 && (status & 65536) == 0) {
         if ((status & 131072) != 0 && this.isChangeOnVerticalScrollSet()) {
            this.calcDateFromFieldChange(Ui.getIncreaseDirection() * amount);
            this.calcCachedData();
            this.fieldChangeNotify(0);
            return 0;
         } else {
            return amount;
         }
      } else {
         int dir;
         if (amount > 0) {
            dir = 1;
         } else {
            dir = -1;
         }

         int last = this._c_fields.length - 1;
         int position = this._position;
         int newposition = position;

         label90:
         while (amount != 0) {
            do {
               if ((status & 536870912) != 0) {
                  if (!this.isEditable()) {
                     amount = 0;
                     break label90;
                  }

                  if (position + dir > last) {
                     if (this.isFocusMoveOnHorizontalScrollSet()) {
                        return amount;
                     }

                     position = last;

                     while (!this.isSubFieldEditable(position)) {
                        position--;
                     }
                  } else if (position + dir < 0) {
                     if (this.isFocusMoveOnHorizontalScrollSet()) {
                        return amount;
                     }

                     position = 0;

                     while (!this.isSubFieldEditable(position)) {
                        position++;
                     }
                  } else {
                     position += dir;
                  }
               } else {
                  position += dir;
               }

               if (position < 0 || position > last) {
                  break label90;
               }
            } while (!this.isSubFieldEditable(position));

            newposition = position;
            amount -= dir;
         }

         if (this._position != newposition) {
            this._position = newposition;
         }

         this.calcCachedData();
         this.clampDateOnInterval(status, amount);
         return amount;
      }
   }

   @Override
   public void getFocusRect(XYRect rect) {
      if (this._cursor == null) {
         super.getFocusRect(rect);
      } else {
         rect.set(this._cursor[0]);
         int i = this._cursor.length;

         while (--i > 0) {
            rect.union(this._cursor[i]);
         }
      }
   }

   private void clampDateOnInterval(int status, int amount) {
      if ((status & 1) != 0 && this.isEditable() && this.getCurrentSubfield() == 12) {
         long date = this.getDate();
         long clampedDate = date / 60000 * 60000;
         long newDate = date / this._minuteIncrements;
         newDate *= this._minuteIncrements;
         if (newDate != clampedDate) {
            if (clampedDate > 0 && amount > 0) {
               newDate += this._minuteIncrements;
            } else if (clampedDate < 0 && amount < 0) {
               newDate -= this._minuteIncrements;
            }
         }

         this.setDate(newDate + (date - clampedDate), 0);
      }
   }

   @Override
   protected void onFocus(int direction) {
      super.onFocus(direction);
      if (!Trackball.isSupported()) {
         if (direction == 1) {
            this._position = 0;

            while (this._position < this._c_fields.length - 1 && !this.isSubFieldEditable(this._position)) {
               this._position++;
            }
         } else if (direction != -1) {
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
            int i = 0;

            while (i < this._focus_order.length && this._focus_order[i] != this._position) {
               i++;
            }

            label93:
            for (int j = 0; j < i; j++) {
               for (int k = 0; k < this._c_fields.length; k++) {
                  if (this._c_fields[k] == this._focus_order[j] && this.isSubFieldEditable(k)) {
                     this._position = k;
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
   protected void paint(Graphics graphics) {
      this._label.paintSelf(graphics);
      this._dateTextRect.paintSelf(graphics);
   }

   @Override
   public void selectionCopy(Clipboard cb) {
      DateField$FormattedDate formattedDate = new DateField$FormattedDate(this.getDate(), this._format, this._timezone);
      if (ControlledAccess.verifyRRISignatures(true)) {
         cb.put(formattedDate);
      } else {
         cb.put(formattedDate.toString());
      }
   }

   private void calcDateFromFieldChange(int changeAmount) {
      int currentField = this._c_fields[this._position];
      if (this._utilCal == null) {
         this._utilCal = Calendar.getInstance(this._timezone);
      } else {
         switch (currentField) {
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
               ((CalendarExtensions)this._utilCal).add(currentField, changeAmount);
               break;
            case 7:
               ((CalendarExtensions)this._utilCal).add(5, changeAmount);
               break;
            case 9:
               ((CalendarExtensions)this._utilCal).roll(9, changeAmount);
               break;
            case 12:
               int increment = (int)(this._minuteIncrements / 60000);
               int currentValue = this._utilCal.get(currentField) % increment;
               ((CalendarExtensions)this._utilCal)
                  .add(
                     currentField,
                     0 > changeAmount && 0 < currentValue ? (changeAmount + 1) * increment - currentValue : changeAmount * increment - currentValue
                  );
         }

         if (!this.isEraSupported()) {
            int era = this._utilCal.get(0);
            if (era == 0) {
               this._utilCal.set(0, 1);
            }
         }
      }
   }

   public DateField(String label, long date, long style) {
      this(label, date, getDateFormatFromStyle(style), style);
   }

   @Override
   protected void drawFocus(Graphics graphics, boolean on) {
      if (this._cursor == null) {
         synchronized (_rect) {
            this.getInPlaceRect(_rect);
            this.drawHighlightRegion(graphics, 1, on, _rect.x, _rect.y, _rect.width, _rect.height);
         }
      } else {
         int i = this._cursor.length;

         while (--i >= 0) {
            XYRect rect = this._cursor[i];
            this.drawHighlightRegion(graphics, 1, on, rect.x, rect.y, rect.width, rect.height);
         }
      }
   }

   public DateField(String label, long date, DateFormat format) {
      this(label, date, format, 0);
   }

   @Override
   public void setEditable(boolean editable) {
      super.setEditable(editable);
      if (this.isEditable()) {
         this._c_fields = this._format.getFields();
      } else {
         this._c_fields = new int[1];
         this._position = 0;
      }
   }

   public DateField(String label, long date, long style, DateFormat format) {
      this(label, date, format, style);
   }

   @Override
   public int getPreferredHeight() {
      int height = this.getFont().getHeight();
      int rows = this._isLabelOwnLine && this._label.getTextString() != null ? 2 : 1;
      return height * rows;
   }

   @Override
   public int getPreferredWidth() {
      StringBuffer text = (StringBuffer)this._dateTextRect.getText();
      text.setLength(0);
      this._format.format(this._utilCal, text, null);
      Font font = this.getFont();
      int dateWidth = font.getBounds(text, 0, text.length());
      int labelWidth = font.getBounds(this._label.getTextString());
      if (labelWidth != 0) {
         labelWidth += 5;
      }

      return Math.max(this._minimumWidth, dateWidth + labelWidth);
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this._label.applyTheme();
      this._isLabelOwnLine = ThemeManager.getActiveTheme().isLabelOnOwnLine();
      if (this._isLabelOwnLine) {
         int textStyle = this.getFieldStyle();
         textStyle &= -8;
         textStyle |= 6;
         this._dateTextRect.setStyle(textStyle);
      }
   }

   private void calcLayoutData(int width) {
      this._position = MathUtilities.clamp(0, this._position, this._c_fields.length - 1);
      this._c_position.setField(this._c_fields[this._position]);
      this.formatDate(this._c_position);
      int labelXOffset = 0;
      int labelYOffset = 0;
      this._label.layout(width, Integer.MAX_VALUE);
      int textStyle = this.getFieldStyle();
      this._dateTextRect.layout(width, Integer.MAX_VALUE);
      int extentX = this._dateTextRect.getWidth();
      int extentY = this._dateTextRect.getHeight();
      int labelWidth = this._label.getWidth();
      int dateXOffset;
      int dateYOffset;
      if (labelWidth <= 0) {
         dateXOffset = 0;
         dateYOffset = 0;
      } else if (this._isLabelOwnLine) {
         dateXOffset = 0;
         dateYOffset = this._label.getHeight();
         extentY += dateYOffset;
      } else {
         int desiredWidth = labelWidth + 5 + extentX;
         if (desiredWidth <= width) {
            dateXOffset = labelWidth + 5;
            dateYOffset = this._label.getLineHeight(0) - this._dateTextRect.getLineHeight(0);
            if (dateYOffset < 0) {
               labelYOffset = -dateYOffset;
               dateYOffset = 0;
            }

            extentX = desiredWidth;
            this._dateTextRect.reduceWidthToFit(width - dateXOffset);
         } else {
            dateXOffset = 0;
            dateYOffset = this._label.getHeight();
            extentX = Math.max(labelWidth, extentX);
            extentY += dateYOffset;
         }
      }

      if ((textStyle & 1152921504606846976L) != 0 || (textStyle & 7) != 6) {
         extentX = width;
      }

      if (this._minimumWidth > 0) {
         extentX = Math.max(extentX, this._minimumWidth);
      }

      this._label.setPosition(labelXOffset, labelYOffset);
      this._dateTextRect.setPosition(dateXOffset, dateYOffset);
      this.setExtent(extentX, extentY);
      if (this.isEditable() && this.isSubFieldEditable(this._position)) {
         this._cursor = this._dateTextRect.getTextBounds(this._c_position.getBeginIndex(), this._c_position.getEndIndex());
      } else {
         this._cursor = null;
      }
   }

   public DateField(String label, long date, int style, DateFormat format) {
      this(label, date, (long)style, format);
   }

   public DateField(String label, long date, DateFormat format, long style) {
   }

   @Override
   public String toString() {
      return this._dateTextRect.getTextString();
   }

   private static long validateStyle(long style) {
      if ((style & 7) == 0) {
         style |= 5;
      }

      if ((style & 13510798882111488L) == 0) {
         style |= 4503599627370496L;
      }

      if ((style & 54043195528445952L) == 0) {
         style |= 18014398509481984L;
      }

      return style & -49;
   }

   private static DateFormat getDateFormatFromStyle(long style) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean usingAlphaMonth() {
      throw new RuntimeException("cod2jar: string-special");
   }

   private boolean isChangeOnVerticalScrollSet() {
      return (this.getStyle() & 64) > 0;
   }

   private boolean isFocusMoveOnHorizontalScrollSet() {
      return (this.getStyle() & 128) > 0;
   }

   private void formatDate(FieldPosition fieldPosition) {
      StringBuffer dateStringBuffer = (StringBuffer)this._dateTextRect.getText();
      dateStringBuffer.setLength(0);
      this._format.format(this._utilCal, dateStringBuffer, fieldPosition);
      this._dateTextRect.setText(dateStringBuffer);
   }

   @Override
   public int getAccessibleRole() {
      return 9;
   }

   @Override
   public String getAccessibleName() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
