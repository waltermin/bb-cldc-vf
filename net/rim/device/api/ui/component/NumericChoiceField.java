package net.rim.device.api.ui.component;

import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.system.InternalServices;

public class NumericChoiceField extends ChoiceField {
   private int _begin;
   private int _end;
   private int _intendedEnd;
   private int _increment;
   private boolean _inEditMode;
   private int _intendedValue;
   private StringBuffer _buffer;
   private boolean _isKeyRepeat;

   public NumericChoiceField() {
      this(null, 0, 0, 1);
   }

   public NumericChoiceField(String label, int begin, int end, int increment) {
      this(label, begin, end, increment, 0, 0);
   }

   public NumericChoiceField(String label, int begin, int end, int increment, int initialIndex) {
      this(label, begin, end, increment, initialIndex, 0);
   }

   public NumericChoiceField(String label, int begin, int end, int increment, int initialIndex, long style) {
   }

   @Override
   protected ChoiceField getChangeOptionChoiceField(String label) {
      NumericChoiceField field = new NumericChoiceField$1(this, label, this._begin, this._intendedEnd, this._increment, this.getSelectedIndex());
      field.setChangeListener(this.getChangeListener());
      return field;
   }

   @Override
   public int getPreferredWidth() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public Object getChoice(int index) {
      this._buffer.setLength(0);
      if (this._inEditMode) {
         this._buffer.append(Integer.toString(MathUtilities.clamp(this._begin, this._intendedValue, this._end)));
      } else {
         this._buffer.append(this.getValue(index));
      }

      return this._buffer.toString();
   }

   public int getSelectedValue() {
      return this.getValue(this.getSelectedIndex());
   }

   private int getValue(int index) {
      return MathUtilities.clamp(this._begin, this.indexToValue(index), this._intendedEnd);
   }

   private int indexToValue(int index) {
      return this._begin + index * this._increment;
   }

   @Override
   protected void makeContextMenu(ContextMenu contextMenu) {
      this.terminateEditMode();
      super.makeContextMenu(contextMenu);
   }

   @Override
   protected boolean trackwheelRoll(int amount, int status, int time) {
      this.terminateEditMode();
      this._isKeyRepeat = false;
      return super.trackwheelRoll(amount, status, time);
   }

   public void setSelectedValue(int value, int context) {
      value = MathUtilities.clamp(this._begin, value, this._end);
      this.setSelectedIndex(this.roundValueToNearestIndex(value), context);
   }

   public void setSelectedValue(int value) {
      this.setSelectedValue(value, Integer.MIN_VALUE);
   }

   @Override
   public int getWidthOfChoices() {
      Font font = this.getFont();
      int widestCharWidth = font.getBounds('0');

      for (char c = '1'; c <= '9'; c++) {
         int charWidth = font.getBounds(c);
         if (charWidth > widestCharWidth) {
            widestCharWidth = charWidth;
         }
      }

      int width = widestCharWidth;
      if (this._begin < 0 || this._end < 0) {
         width += font.getBounds('-');
      }

      for (int delta = Math.max(Math.abs(this._end), Math.abs(this._begin)) / 10; delta > 0; delta /= 10) {
         width += widestCharWidth;
      }

      return width;
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      if (InternalServices.isReducedFormFactor() && Keypad.key(keycode) == 32) {
         this._isKeyRepeat = true;
         this._inEditMode = true;
         this._intendedValue = 0;
         this.setSelectedValue(this._intendedValue, 0);
      }

      return super.keyRepeat(keycode, time);
   }

   @Override
   protected boolean navigationClick(int status, int time) {
      boolean retVal = super.navigationClick(status, time);
      this.terminateEditMode();
      return retVal;
   }

   @Override
   protected void onUnfocus() {
      super.onUnfocus();
      this.terminateEditMode();
   }

   private void terminateEditMode() {
      if (this._inEditMode) {
         this._inEditMode = false;
         this.setSelectedValue(this._intendedValue, 0);
         this.invalidate();
      }
   }

   @Override
   protected boolean keyControl(char character, int status, int time) {
      char alted = Keypad.getAltedChar(character);
      return Character.isDigit(alted) ? this.keyChar(alted, status, time) : super.keyControl(character, status, time);
   }

   @Override
   protected boolean keyStatus(int keycode, int time) {
      if (Keypad.key(keycode) == 257) {
         this.terminateEditMode();
      }

      return super.keyStatus(keycode, time);
   }

   private int roundValueToNearestIndex(int value) {
      if (this._increment == 1) {
         return value - this._begin;
      }

      if (value >= this._intendedEnd && value <= this._end) {
         value = this._end;
      }

      return ((value - this._begin) * 10 / this._increment + 5) / 10;
   }

   private boolean validate(char ch) {
      if (Character.isDigit(ch)) {
         return true;
      }

      switch (ch) {
         case '\b':
         case ' ':
         case '\u007f':
            return true;
         default:
            return false;
      }
   }
}
