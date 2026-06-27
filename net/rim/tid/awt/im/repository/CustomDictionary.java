package net.rim.tid.awt.im.repository;

import java.util.Enumeration;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.ReadableSet;
import net.rim.device.api.collection.util.CollectionListenerManager;

public class CustomDictionary implements ReadableSet, CollectionEventSource {
   protected CollectionListenerManager _listeners = (CollectionListenerManager)(new Object());
   public static final byte NEW_WORDS;
   public static final byte FREQUENCY_WORDS;
   public static final byte URL_EMAIL_WORDS;
   public static final byte EXTRACTED_WORDS;
   public static final byte REJECTED_WORDS;

   public Object add(Object var1) {
      this._listeners.fireElementAdded(this, var1);
      return var1;
   }

   public void remove(Object var1) {
      this._listeners.fireElementRemoved(this, var1);
   }

   public boolean isInRepository(Object var1) {
      throw null;
   }

   public Object find(Object var1) {
      return null;
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listeners.addCollectionListener(var1);
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listeners.addCollectionListener(var1);
   }

   @Override
   public int getElements(Object[] var1) {
      throw null;
   }

   @Override
   public Enumeration getElements() {
      throw null;
   }

   @Override
   public boolean contains(Object var1) {
      throw null;
   }

   @Override
   public int size() {
      throw null;
   }
}
