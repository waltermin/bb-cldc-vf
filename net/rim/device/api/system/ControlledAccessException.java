package net.rim.device.api.system;

public final class ControlledAccessException extends SecurityException {
   private CodeSigningKey _key;
   private String _deniedPermission;

   public ControlledAccessException() {
   }

   public ControlledAccessException(String var1) {
   }

   public ControlledAccessException(String var1, String var2) {
      super(var1);
      this._deniedPermission = var2;
   }

   public ControlledAccessException(CodeSigningKey var1) {
      this._key = var1;
   }

   public final CodeSigningKey getCodeSigningKey() {
      return this._key;
   }

   public final String getDeniedPermissionString() {
      return this._deniedPermission;
   }
}
