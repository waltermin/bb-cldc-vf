package net.rim.device.internal.ipc;

public class IPCBaseRunnable implements Runnable {
   protected Object _listener;

   public Object getListener() {
      return this._listener;
   }

   protected void doLogging(Throwable t) {
      t.printStackTrace();
   }

   public void setListener(Object listener) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void run() {
      throw null;
   }
}
