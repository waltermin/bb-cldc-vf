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

   public NumericChoiceField(String var1, int var2, int var3, int var4) {
      this(var1, var2, var3, var4, 0, 0);
   }

   public NumericChoiceField(String var1, int var2, int var3, int var4, int var5) {
      this(var1, var2, var3, var4, var5, 0);
   }

   public NumericChoiceField(String var1, int var2, int var3, int var4, int var5, long var6) {
   }

   @Override
   protected ChoiceField getChangeOptionChoiceField(String var1) {
      NumericChoiceField$1 var2 = new NumericChoiceField$1(this, var1, this._begin, this._intendedEnd, this._increment, this.getSelectedIndex());
      var2.setChangeListener(this.getChangeListener());
      return var2;
   }

   @Override
   public int getPreferredWidth() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public Object getChoice(int var1) {
      this._buffer.setLength(0);
      if (this._inEditMode) {
         this._buffer.append(Integer.toString(MathUtilities.clamp(this._begin, this._intendedValue, this._end)));
      } else {
         this._buffer.append(this.getValue(var1));
      }

      return this._buffer.toString();
   }

   public int getSelectedValue() {
      return this.getValue(this.getSelectedIndex());
   }

   private int getValue(int var1) {
      return MathUtilities.clamp(this._begin, this.indexToValue(var1), this._intendedEnd);
   }

   private int indexToValue(int var1) {
      return this._begin + var1 * this._increment;
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      this.terminateEditMode();
      super.makeContextMenu(var1);
   }

   @Override
   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      this.terminateEditMode();
      this._isKeyRepeat = false;
      return super.trackwheelRoll(var1, var2, var3);
   }

   public void setSelectedValue(int var1, int var2) {
      var1 = MathUtilities.clamp(this._begin, var1, this._end);
      this.setSelectedIndex(this.roundValueToNearestIndex(var1), var2);
   }

   public void setSelectedValue(int var1) {
      this.setSelectedValue(var1, Integer.MIN_VALUE);
   }

   @Override
   public int getWidthOfChoices() {
      Font var1 = this.getFont();
      int var2 = var1.getBounds('0');

      for (char var3 = '1'; var3 <= '9'; var3++) {
         int var4 = var1.getBounds(var3);
         if (var4 > var2) {
            var2 = var4;
         }
      }

      int var5 = var2;
      if (this._begin < 0 || this._end < 0) {
         var5 += var1.getBounds('-');
      }

      for (int var6 = Math.max(Math.abs(this._end), Math.abs(this._begin)) / 10; var6 > 0; var6 /= 10) {
         var5 += var2;
      }

      return var5;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      if (InternalServices.isReducedFormFactor() && Keypad.key(var1) == 32) {
         this._isKeyRepeat = true;
         this._inEditMode = true;
         this._intendedValue = 0;
         this.setSelectedValue(this._intendedValue, 0);
      }

      return super.keyRepeat(var1, var2);
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      boolean var3 = super.navigationClick(var1, var2);
      this.terminateEditMode();
      return var3;
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
   protected boolean keyControl(char var1, int var2, int var3) {
      char var4 = Keypad.getAltedChar(var1);
      return Character.isDigit(var4) ? this.keyChar(var4, var2, var3) : super.keyControl(var1, var2, var3);
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      if (Keypad.key(var1) == 257) {
         this.terminateEditMode();
      }

      return super.keyStatus(var1, var2);
   }

   private int roundValueToNearestIndex(int var1) {
      if (this._increment == 1) {
         return var1 - this._begin;
      }

      if (var1 >= this._intendedEnd && var1 <= this._end) {
         var1 = this._end;
      }

      return ((var1 - this._begin) * 10 / this._increment + 5) / 10;
   }

   private boolean validate(char var1) {
      if (Character.isDigit(var1)) {
         return true;
      }

      switch (var1) {
         case '\b':
         case ' ':
         case '\u007f':
            return true;
         default:
            return false;
      }
   }
}
