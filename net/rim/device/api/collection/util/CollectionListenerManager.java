package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.CollectionListener;
import net.rim.vm.Array;

public class CollectionListenerManager implements CollectionEventSource {
   private Object[] _listeners;

   public boolean isEmpty() {
      Object[] var1 = this._listeners;
      return var1 == null || var1.length == 0;
   }

   public void clearOut() {
      Object[] var1 = this._listeners;
      this._listeners = null;
      if (var1 != null) {
         Array.resize(var1, 0);
      }
   }

   public void fireReset(Collection var1, Object var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void fireReset(Collection var1) {
      this.fireReset(var1, null);
   }

   public void fireElementAdded(Collection var1, Object var2) {
      Object[] var3 = this._listeners;
      if (var3 != null) {
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            CollectionListener var6 = this.getListener(var3, var5);
            if (var6 != null) {
               var6.elementAdded(var1, var2);
            }
         }
      }
   }

   public void fireElementUpdated(Collection var1, Object var2, Object var3) {
      Object[] var4 = this._listeners;
      if (var4 != null) {
         int var5 = var4.length;

         for (int var6 = 0; var6 < var5; var6++) {
            CollectionListener var7 = this.getListener(var4, var6);
            if (var7 != null) {
               var7.elementUpdated(var1, var2, var3);
            }
         }
      }
   }

   public void fireElementRemoved(Collection var1, Object var2) {
      Object[] var3 = this._listeners;
      if (var3 != null) {
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            CollectionListener var6 = this.getListener(var3, var5);
            if (var6 != null) {
               var6.elementRemoved(var1, var2);
            }
         }
      }
   }

   public void forEachListener(Collection var1, CollectionListenerAction var2) {
      Object[] var3 = this._listeners;
      if (var3 != null) {
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            CollectionListener var6 = this.getListener(var3, var5);
            if (var6 != null) {
               var2.invoke(var1, var6);
            }
         }
      }
   }

   @Override
   public void removeCollectionListener(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void addCollectionListener(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private CollectionListener getListener(Object[] var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }
}
