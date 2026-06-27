package net.rim.device.internal.ipc;

public class IPCBlockingReturnRunnable extends IPCBaseRunnable {
   private IPCFutureResult _result = new IPCFutureResult();
   private boolean _success;
   private String _message;

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected Object doRun(Object var1) {
      throw null;
   }

   private void setResult(Object var1) {
      this._result.setResult(var1);
   }

   Object getResult() {
      return this._result.getResult();
   }

   boolean wasSuccessful() {
      return this._success;
   }

   String getMessage() {
      return this._message;
   }

   protected void setMessage(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   IPCResult getIPCResult() {
      return new IPCResult(this.getResult(), this.wasSuccessful(), this.getMessage());
   }
}
