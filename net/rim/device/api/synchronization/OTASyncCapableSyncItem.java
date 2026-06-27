package net.rim.device.api.synchronization;

import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.util.CollectionListenerManager;

public class OTASyncCapableSyncItem extends SyncItem implements OTASyncCapable, CollectionEventSource {
   private CollectionListenerManager _collectionListeners = (CollectionListenerManager)(new Object());

   protected OTASyncCapableSyncItem() {
   }

   public void fireSyncItemUpdated() {
      this._collectionListeners.fireElementUpdated(this, null, this);
   }

   @Override
   public SyncCollectionSchema getSchema() {
      return null;
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._collectionListeners.addCollectionListener(new Object(var1));
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._collectionListeners.removeCollectionListener(var1);
   }
}
