package net.rim.device.internal.util;

public class OptionsRegistry$LongParameterDefinition extends OptionsRegistry$ParameterDefinition {
   long _default;
   private long _min;
   private long _max;

   public OptionsRegistry$LongParameterDefinition(long var1, long var3, long var5) {
      this._default = var1;
      this._min = var3;
      this._max = var5;
   }

   protected boolean isValid(long var1) {
      return this._min <= var1 && var1 <= this._max;
   }
}
