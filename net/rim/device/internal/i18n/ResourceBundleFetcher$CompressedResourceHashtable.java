package net.rim.device.internal.i18n;

import java.util.Hashtable;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.system.CodeStore;
import net.rim.device.internal.system.RIMProcessLauncher;

class ResourceBundleFetcher$CompressedResourceHashtable extends Hashtable {
   private Object _lockObject = new Object();
   private int _cachedFSGeneration = 0;
   ApplicationRegistry _appRegistry = ApplicationRegistry.getApplicationRegistry();
   private static final long CRC_GUID;
   private static final long MODULES_GUID;

   private ResourceBundleFetcher$CompressedResourceHashtable() {
      synchronized (this._lockObject) {
         RIMProcessLauncher.launch(new ResourceBundleFetcher$CompressedResourceHashtable$InitializeRunnable(this, null));

         try {
            this._lockObject.wait();
         } catch (InterruptedException var3) {
         }
      }
   }

   private void initialize() {
      int[] moduleHandles = new int[0];
      int generation = CodeStore.getModuleHandles(moduleHandles);
      int i = 0;

      while (i < moduleHandles.length) {
         if (this.addModule(moduleHandles[i])) {
            i++;
         } else {
            Arrays.removeAt(moduleHandles, i);
         }
      }

      this.setGenerationSingleton(generation, moduleHandles);
   }

   @Override
   public Object get(Object key) {
      this.verify();
      return super.get(key);
   }

   private void setGenerationSingleton(int generation, int[] moduleHandles) {
      synchronized (this) {
         this._cachedFSGeneration = generation;
         ApplicationRegistry.getApplicationRegistry().replace(-2054058001874678213L, new Object(generation));
         ApplicationRegistry.getApplicationRegistry().replace(-5612214964325329634L, moduleHandles);
      }
   }

   private void updateBundleCache() {
      throw new RuntimeException("cod2jar: type check");
   }

   private void verify() {
      if (this._cachedFSGeneration == 0) {
         synchronized (this._lockObject) {
            ;
         }
      }

      int generation = CodeStore.getModuleHandles(null);
      if (generation != this._cachedFSGeneration) {
         synchronized (this._lockObject) {
            RIMProcessLauncher.launch(new ResourceBundleFetcher$CompressedResourceHashtable$UpdateRunnable(this, null));

            try {
               this._lockObject.wait();
            } catch (InterruptedException var4) {
            }
         }
      }
   }

   private boolean addModule(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   boolean isLoaded(String name) {
      Integer data = (Integer)this.get(name);
      return data != null;
   }

   ResourceBundleFetcher$CompressedResourceHashtable(ResourceBundleFetcher$1 x0) {
      this();
   }
}
