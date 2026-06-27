package net.rim.device.internal.ipc;

public class IPCBaseRunnable implements Runnable {
   protected Object _listener;

   public Object getListener() {
      return this._listener;
   }

   protected void doLogging(Throwable var1) {
      var1.printStackTrace();
   }

   public void setListener(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void run() {
      throw null;
   }
}
