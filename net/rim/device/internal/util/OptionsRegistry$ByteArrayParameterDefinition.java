package net.rim.device.internal.util;

public class OptionsRegistry$ByteArrayParameterDefinition extends OptionsRegistry$ParameterDefinition {
   byte[] _default;

   public OptionsRegistry$ByteArrayParameterDefinition(byte[] var1) {
      this._default = var1;
   }

   protected boolean isValid(byte[] var1) {
      return true;
   }
}
