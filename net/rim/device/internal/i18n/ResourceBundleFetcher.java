package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.internal.system.RIMProcessLauncher;
import net.rim.device.internal.system.RIMProcessLauncher$ApplicationCallback;

public final class ResourceBundleFetcher implements RIMProcessLauncher$ApplicationCallback {
   private final ResourceBundleFetcher$CompressedResourceHashtable _bundleCache;
   private Application _fetcherAppRef = null;
   private Object _requestLock = new Object();
   private ResourceBundleFetcher$ResourceBundleFetcherRequestRunnable _requestRunnable = new ResourceBundleFetcher$ResourceBundleFetcherRequestRunnable(null);
   private static final long REGISTRY_NAME;
   private static ResourceBundleFetcher _instance;

   private ResourceBundleFetcher() {
      this._bundleCache = new ResourceBundleFetcher$CompressedResourceHashtable(null);
   }

   private static final ResourceBundleFetcher getInstance() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _instance = (ResourceBundleFetcher)var0.waitFor(3190551698550597928L);
      }

      return _instance;
   }

   public static final void initialize() {
      _instance = new ResourceBundleFetcher();
      ApplicationRegistry.getApplicationRegistry().put(3190551698550597928L, _instance);
      Locale.get(0);
      launchRequestProcess();
   }

   public static final void flushRequestData() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final ResourceBundle fetch(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean verifyCompressedResourcePresent(String var0) {
      return getInstance()._bundleCache.isLoaded(var0);
   }

   @Override
   public final void applicationStarted(Application var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void launchRequestProcess() {
      RIMProcessLauncher.launchApplication(_instance);
   }

   private static final ResourceBundle fetchResourceBundleInternal(String var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
