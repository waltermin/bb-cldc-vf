package net.rim.device.api.collection.util;

import net.rim.device.api.collection.Collection;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.CollectionListener;
import net.rim.vm.Array;

public class CollectionListenerManager implements CollectionEventSource {
   private Object[] _listeners;

   public boolean isEmpty() {
      Object[] listeners = this._listeners;
      return listeners == null || listeners.length == 0;
   }

   public void clearOut() {
      Object[] listeners = this._listeners;
      this._listeners = null;
      if (listeners != null) {
         Array.resize(listeners, 0);
      }
   }

   public void fireReset(Collection collection, Object context) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void fireReset(Collection collection) {
      this.fireReset(collection, null);
   }

   public void fireElementAdded(Collection collection, Object element) {
      Object[] listeners = this._listeners;
      if (listeners != null) {
         int count = listeners.length;

         for (int i = 0; i < count; i++) {
            CollectionListener listener = this.getListener(listeners, i);
            if (listener != null) {
               listener.elementAdded(collection, element);
            }
         }
      }
   }

   public void fireElementUpdated(Collection collection, Object oldElement, Object newElement) {
      Object[] listeners = this._listeners;
      if (listeners != null) {
         int count = listeners.length;

         for (int i = 0; i < count; i++) {
            CollectionListener listener = this.getListener(listeners, i);
            if (listener != null) {
               listener.elementUpdated(collection, oldElement, newElement);
            }
         }
      }
   }

   public void fireElementRemoved(Collection collection, Object element) {
      Object[] listeners = this._listeners;
      if (listeners != null) {
         int count = listeners.length;

         for (int i = 0; i < count; i++) {
            CollectionListener listener = this.getListener(listeners, i);
            if (listener != null) {
               listener.elementRemoved(collection, element);
            }
         }
      }
   }

   public void forEachListener(Collection collection, CollectionListenerAction action) {
      Object[] listeners = this._listeners;
      if (listeners != null) {
         int count = listeners.length;

         for (int i = 0; i < count; i++) {
            CollectionListener listener = this.getListener(listeners, i);
            if (listener != null) {
               action.invoke(collection, listener);
            }
         }
      }
   }

   @Override
   public void removeCollectionListener(Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void addCollectionListener(Object listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   private CollectionListener getListener(Object[] listeners, int index) {
      throw new RuntimeException("cod2jar: type check");
   }
}
