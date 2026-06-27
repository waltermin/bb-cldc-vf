package net.rim.device.internal.system;

import net.rim.device.internal.proxy.Proxy;

public class MethodRunnable implements Runnable {
   protected Throwable _throwable;
   protected int _intResult;
   protected String _stringResult;

   public String getStringResult() {
      return this._stringResult;
   }

   protected void call() {
      throw null;
   }

   public void checkException() {
      throw new RuntimeException("cod2jar: type check");
   }

   public void runOnProxy() {
      Proxy var1 = Proxy.getInstance();
      var1.invokeAndWait(this);
   }

   public int getIntegerResult() {
      return this._intResult;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
