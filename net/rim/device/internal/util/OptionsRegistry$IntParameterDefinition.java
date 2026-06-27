package net.rim.device.internal.util;

public class OptionsRegistry$IntParameterDefinition extends OptionsRegistry$ParameterDefinition {
   int _default;
   private int _min;
   private int _max;

   public OptionsRegistry$IntParameterDefinition(int var1, int var2, int var3) {
      this._default = var1;
      this._min = var2;
      this._max = var3;
   }

   protected boolean isValid(int var1) {
      return this._min <= var1 && var1 <= this._max;
   }
}
