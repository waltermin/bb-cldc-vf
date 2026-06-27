package net.rim.device.api.collection;

public final class CollectionLock {
   private static CollectionLock _lockObject;
   private static final long GLOBAL_COLLECTION_LOCK_OBJECT_LUID;

   private CollectionLock() {
   }

   public static final CollectionLock getGlobalLock() {
      return _lockObject;
   }
}
