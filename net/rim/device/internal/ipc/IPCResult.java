package net.rim.device.internal.ipc;

public class IPCResult {
   private boolean _wasSuccessful;
   private Object _result;
   private String _message;
   public static final IPCResult FAILED_RESULT;

   IPCResult(Object var1, boolean var2) {
   }

   IPCResult(Object var1, boolean var2, String var3) {
      this._result = var1;
      this._wasSuccessful = var2;
      this._message = var3;
   }

   public Object getResult() {
      return this._result;
   }

   public boolean wasSuccessful() {
      return this._wasSuccessful;
   }

   public String getMessage() {
      return this._message;
   }
}
