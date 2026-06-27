package net.rim.device.api.collection.util;

import java.util.Vector;
import net.rim.device.api.collection.ChainableCollection;
import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionCombiner;
import net.rim.device.api.collection.IntRangedActionTarget;
import net.rim.device.api.collection.LongRangedActionTarget;
import net.rim.device.api.collection.NotificationSuspension;
import net.rim.device.api.collection.ReadableList;

public class ReadableListCombiner
   implements ChainableCollection,
   CollectionCombiner,
   ReadableList,
   LongRangedActionTarget,
   IntRangedActionTarget,
   NotificationSuspension {
   private CollectionListenerManager _listeners = new CollectionListenerManager();
   private boolean _inReset;
   private Vector _sources = (Vector)(new Object());

   protected Vector getSources() {
      return this._sources;
   }

   @Override
   public void apply(int var1, int var2, long var3, Object var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listeners.removeCollectionListener(var1);
   }

   @Override
   public void reset(Collection var1) {
      if (!this._inReset) {
         this._inReset = true;
         this._listeners.fireReset(this);
         this._inReset = false;
      }
   }

   @Override
   public void elementAdded(Collection var1, Object var2) {
      this._listeners.fireElementAdded(this, var2);
   }

   @Override
   public void elementUpdated(Collection var1, Object var2, Object var3) {
      this._listeners.fireElementUpdated(this, var2, var3);
   }

   @Override
   public void elementRemoved(Collection var1, Object var2) {
      this._listeners.fireElementRemoved(this, var2);
   }

   @Override
   public void addSource(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void removeSource(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int size() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public Object getAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getAt(int var1, int var2, Object[] var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getIndex(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void apply(long var1, long var3, long var5, Object var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listeners.addCollectionListener(var1);
   }

   @Override
   public void suspendNotification(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void resumeNotification(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
