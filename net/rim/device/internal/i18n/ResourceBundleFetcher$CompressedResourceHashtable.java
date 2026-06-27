package net.rim.device.internal.i18n;

import java.util.Hashtable;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.system.CodeStore;

class ResourceBundleFetcher$CompressedResourceHashtable extends Hashtable {
   private Object _lockObject;
   private int _cachedFSGeneration;
   ApplicationRegistry _appRegistry;
   private static final long CRC_GUID;
   private static final long MODULES_GUID;

   private ResourceBundleFetcher$CompressedResourceHashtable() {
   }

   private void initialize() {
      int[] var1 = new int[0];
      int var2 = CodeStore.getModuleHandles(var1);
      int var3 = 0;

      while (var3 < var1.length) {
         if (this.addModule(var1[var3])) {
            var3++;
         } else {
            Arrays.removeAt(var1, var3);
         }
      }

      this.setGenerationSingleton(var2, var1);
   }

   @Override
   public Object get(Object var1) {
      this.verify();
      return super.get(var1);
   }

   private void setGenerationSingleton(int var1, int[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void updateBundleCache() {
      throw new RuntimeException("cod2jar: type check");
   }

   private void verify() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean addModule(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   boolean isLoaded(String var1) {
      Object var2 = this.get(var1);
      return var2 != null;
   }

   ResourceBundleFetcher$CompressedResourceHashtable(ResourceBundleFetcher$1 var1) {
      this();
   }
}
