package net.rim.device.api.collection.util;

import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.IntKeyProviderAdaptor;
import net.rim.device.api.collection.LoadableCollection;
import net.rim.device.api.collection.ReadableIntList;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.collection.ReadableSet;
import net.rim.vm.Array;

public class IntSortedReadableList implements ChainableCollection, LoadableCollection, ReadableList, ReadableIntList {
   private int[] _keyArray = new int[20];
   private Object[] _dataArray = new Object[20];
   private int _numElements;
   private CollectionListenerManager _collectionNotifier = new CollectionListenerManager();
   private IntKeyProviderAdaptor _keyProvider;
   private static final int GROW_SIZE;

   @Override
   public void loadFrom(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void reset() {
      this._numElements = 0;
      Array.resize(this._dataArray, 20);
      Array.resize(this._keyArray, 20);
   }

   protected void mergeCollection(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getKey(int var1) {
      if (var1 >= this._numElements) {
         throw new Object();
      } else {
         return this._keyArray[var1];
      }
   }

   @Override
   public int getAt(int var1, int var2, int[] var3, int var4) {
      if (var1 >= 0 && var1 <= this._numElements) {
         if (var2 < 0) {
            var2 = 0;
         } else if (var2 + var1 > this._numElements) {
            var2 = this._numElements - var1;
         }

         if (var2 != 0) {
            if (var3.length < var2 + var4) {
               Array.resize(var3, var2 + var4);
            }

            System.arraycopy(this._keyArray, var1, var3, var4, var2);
         }

         return var2;
      } else {
         throw new Object();
      }
   }

   @Override
   public int getIndex(int var1) {
      for (int var2 = 0; var2 < this._numElements; var2++) {
         if (this._keyArray[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   @Override
   public int getIntAt(int var1) {
      return this._keyArray[var1];
   }

   @Override
   public void reset(Collection var1) {
      this.loadFrom(var1);
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._collectionNotifier.addCollectionListener(var1);
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._collectionNotifier.removeCollectionListener(var1);
   }

   @Override
   public int size() {
      return this._numElements;
   }

   @Override
   public int getAt(int var1, int var2, Object[] var3, int var4) {
      if (var1 >= 0 && var1 <= this._numElements) {
         if (var2 < 0) {
            var2 = 0;
         } else if (var2 + var1 > this._numElements) {
            var2 = this._numElements - var1;
         }

         if (var2 != 0) {
            if (var3.length < var2 + var4) {
               Array.resize(var3, var2 + var4);
            }

            System.arraycopy(this._dataArray, var1, var3, var4, var2);
         }

         return var2;
      } else {
         throw new Object();
      }
   }

   @Override
   public Object getAt(int var1) {
      if (var1 >= this._numElements) {
         throw new Object();
      } else {
         return this._dataArray[var1];
      }
   }

   @Override
   public int getIndex(Object var1) {
      for (int var2 = 0; var2 < this._numElements; var2++) {
         if (this._dataArray[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   public IntSortedReadableList(IntKeyProviderAdaptor var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._keyProvider = var1;
   }

   public IntSortedReadableList(CollectionEventSource var1, IntKeyProviderAdaptor var2) {
      this(var2);
      if (!(var1 instanceof ReadableList) && !(var1 instanceof ReadableSet)) {
         throw new Object();
      }

      var1.addCollectionListener(this);
      this.mergeCollection(var1);
   }

   private void makeRoomFor(int var1) {
      if (var1 > this._dataArray.length) {
         int var2 = var1 + 20;
         Array.resize(this._dataArray, var2);
         Array.resize(this._keyArray, var2);
      }
   }
}
