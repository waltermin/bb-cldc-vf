package net.rim.device.internal.compress;

final class YKCleaner implements Runnable {
   private boolean _registered;
   private Object _objToClean;

   public YKCleaner(Object var1) {
      this._objToClean = var1;
   }

   public final void register() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final void unregister() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: type check");
   }
}
