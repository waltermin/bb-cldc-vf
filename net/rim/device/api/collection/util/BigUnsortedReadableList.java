package net.rim.device.api.collection.util;

import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.LoadableCollection;
import net.rim.device.api.collection.ReadableList;
import net.rim.vm.Array;

public class BigUnsortedReadableList implements ChainableCollection, LoadableCollection, ReadableList {
   protected BigVector _elements = (BigVector)(new Object(64));
   protected Object _lastInsertedUpdated;
   protected int _lastInsertedUpdatedIndex;
   protected CollectionListenerManager _listenerManager = new CollectionListenerManager();
   private static final int MINIMUM_CAPACITY;

   @Override
   public void loadFrom(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected synchronized boolean doRemove(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void fireReset(Collection var1) {
      this._listenerManager.fireReset(var1);
   }

   protected void fireElementAdded(Collection var1, Object var2) {
      this._listenerManager.fireElementAdded(var1, var2);
   }

   protected void fireElementUpdated(Collection var1, Object var2, Object var3) {
      this._listenerManager.fireElementUpdated(var1, var2, var3);
   }

   protected void fireElementRemoved(Collection var1, Object var2) {
      this._listenerManager.fireElementRemoved(var1, var2);
   }

   protected synchronized boolean doUpdate(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void reload(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected synchronized void insertAt(int var1, Object var2) {
      this._elements.insertElementAt(var2, var1);
      this._lastInsertedUpdated = var2;
      this._lastInsertedUpdatedIndex = var1;
   }

   protected synchronized void doAdd(Object var1) {
      this._lastInsertedUpdated = var1;
      this._lastInsertedUpdatedIndex = this._elements.size();
      this._elements.addElement(var1);
   }

   public void replaceAt(Object var1, int var2) {
      this._elements.setElementAt(var1, var2);
   }

   @Override
   public synchronized int getAt(int var1, int var2, Object[] var3, int var4) {
      int var5 = this._elements.size();
      if (var2 > var5 - var1) {
         var2 = var5 - var1;
      }

      if (var3.length < var2 + var4) {
         Array.resize(var3, var2 + var4);
      }

      this._elements.copyInto(var1, var2, var3, var4);
      return var2;
   }

   @Override
   public Object getAt(int var1) {
      return this._elements.elementAt(var1);
   }

   @Override
   public int getIndex(Object var1) {
      return var1 == this._lastInsertedUpdated && var1 != null ? this._lastInsertedUpdatedIndex : this._elements.firstIndexOf(var1);
   }

   @Override
   public void reset(Collection var1) {
      this.loadFrom(var1);
   }

   @Override
   public int size() {
      return this._elements.size();
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      this.doAdd(var2);
      this.fireElementAdded(this, var2);
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listenerManager.addCollectionListener(var1);
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      if (this.doUpdate(var2, var3)) {
         this.fireElementUpdated(this, var2, var3);
      }
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listenerManager.removeCollectionListener(var1);
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      if (this.doRemove(var2)) {
         this.fireElementRemoved(this, var2);
      }
   }

   public BigUnsortedReadableList(CollectionEventSource var1) {
      var1.addCollectionListener(this);
      if (var1 instanceof Collection) {
         this.reload(var1);
      }
   }

   public BigUnsortedReadableList() {
   }
}
