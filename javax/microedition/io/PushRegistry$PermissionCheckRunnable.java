package javax.microedition.io;

class PushRegistry$PermissionCheckRunnable implements Runnable {
   boolean _failed;
   boolean _done;
   int _moduleHandle;
   String _permission;
   String _url;
   RuntimeException _re;

   public PushRegistry$PermissionCheckRunnable(int var1, String var2, String var3) {
      this._moduleHandle = var1;
      this._permission = var2;
      this._url = var3;
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
