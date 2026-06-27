package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.ResourceBundle;

final class ResourceBundleFetcher$ResourceBundleFetcherRequestRunnable implements Runnable {
   private ResourceBundle _bundle;
   private String _requestName;
   private int _requestHandle;
   private Object _lockObject = new Object();

   private ResourceBundleFetcher$ResourceBundleFetcherRequestRunnable() {
   }

   private final void reset(String var1, int var2) {
      this._requestName = var1;
      this._requestHandle = var2;
      this._bundle = null;
   }

   private final Object getLock() {
      return this._lockObject;
   }

   private final ResourceBundle getResult() {
      return this._bundle;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   ResourceBundleFetcher$ResourceBundleFetcherRequestRunnable(ResourceBundleFetcher$1 var1) {
      this();
   }
}
