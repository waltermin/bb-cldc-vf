package net.rim.device.api.collection.util;

import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.LoadableCollection;
import net.rim.device.api.collection.ReadableList;
import net.rim.vm.Array;

public class UnsortedReadableList implements ChainableCollection, LoadableCollection, ReadableList {
   private Object[] _elements = new Object[20];
   private int _count;
   private CollectionListenerManager _listenerManager = new CollectionListenerManager();
   private static final int GROW_SIZE;

   @Override
   public void loadFrom(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected void setElements(Object[] var1, int var2) {
      this._elements = var1;
      if (var2 <= var1.length && var2 >= 0) {
         this._count = var2;
      } else {
         throw new Object();
      }
   }

   protected CollectionListenerManager getListenerManager() {
      return this._listenerManager;
   }

   protected boolean doRemove(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean doUpdate(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected Object[] getElements() {
      return this._elements;
   }

   protected void reload(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void insertAt(int var1, Object var2) {
      if (var1 > this._count) {
         var1 = this._count;
      }

      if (this._count == this._elements.length) {
         Array.resize(this._elements, this._count + 20);
      }

      if (var1 < this._count) {
         System.arraycopy(this._elements, var1, this._elements, var1 + 1, this._count - var1);
      }

      this._elements[var1] = var2;
      this._count++;
   }

   protected void doAdd(Object var1) {
      this.insertAt(this._count, var1);
   }

   @Override
   public Object getAt(int var1) {
      if (var1 >= this._count) {
         throw new Object();
      } else {
         return this._elements[var1];
      }
   }

   @Override
   public int getAt(int var1, int var2, Object[] var3, int var4) {
      if (var2 > this._count - var1) {
         var2 = this._count - var1;
      }

      if (var3.length < var2 + var4) {
         Array.resize(var3, var2 + var4);
      }

      System.arraycopy(this._elements, var1, var3, var4, var2);
      return var2;
   }

   @Override
   public int getIndex(Object var1) {
      for (int var2 = this._count - 1; var2 >= 0; var2--) {
         if (this._elements[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   @Override
   public void reset(Collection var1) {
      this.loadFrom(var1);
   }

   @Override
   public int size() {
      return this._count;
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      this.doAdd(var2);
      this._listenerManager.fireElementAdded(this, var2);
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listenerManager.removeCollectionListener(var1);
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      if (this.doUpdate(var2, var3)) {
         this._listenerManager.fireElementUpdated(this, var2, var3);
      }
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listenerManager.addCollectionListener(var1);
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      if (this.doRemove(var2)) {
         this._listenerManager.fireElementRemoved(this, var2);
      }
   }

   public UnsortedReadableList(CollectionEventSource var1) {
      var1.addCollectionListener(this);
      if (var1 instanceof Collection) {
         this.reload(var1);
      }
   }

   public UnsortedReadableList() {
   }
}
