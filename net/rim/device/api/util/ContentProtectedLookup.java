package net.rim.device.api.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.system.PersistentContent;
import net.rim.device.api.system.PersistentContentListener;

public class ContentProtectedLookup implements PersistentContentListener, Persistable {
   private Vector _keys;
   private Vector _values;

   public ContentProtectedLookup(int var1) {
      this._keys = new Vector(var1);
      this._values = new Vector(var1);
      PersistentContent.addWeakListener(this);
   }

   public ContentProtectedLookup() {
      this(10);
   }

   public ContentProtectedLookup(Hashtable var1) {
      this(var1.size());
      Enumeration var2 = var1.keys();

      while (var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         this.put(var3, var1.get(var3));
      }

      PersistentContent.addWeakListener(this);
   }

   public void clear() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int size() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public int indexOfKey(Object var1) {
      return this.indexOfKey(var1, 0);
   }

   public int indexOfKey(Object var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int indexOfValue(Object var1) {
      return this.indexOfValue(var1, 0);
   }

   public int indexOfValue(Object var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean containsKey(Object var1) {
      return this.indexOfKey(var1) >= 0;
   }

   public boolean containsValue(Object var1) {
      return this.indexOfValue(var1) >= 0;
   }

   public void put(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Object get(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Object keyAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Object valueAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeKey(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeValue(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Enumeration keys() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Enumeration values() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void persistentContentStateChanged(int var1) {
   }

   @Override
   public void persistentContentModeChanged(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
