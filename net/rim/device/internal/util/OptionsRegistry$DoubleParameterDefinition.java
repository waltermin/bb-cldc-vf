package net.rim.device.internal.util;

public class OptionsRegistry$DoubleParameterDefinition extends OptionsRegistry$ParameterDefinition {
   double _default;
   private double _min;
   private double _max;

   public OptionsRegistry$DoubleParameterDefinition(double var1, double var3, double var5) {
      this._default = var1;
      this._min = var3;
      this._max = var5;
   }

   protected boolean isValid(double var1) {
      return this._min <= var1 && var1 <= this._max;
   }
}
