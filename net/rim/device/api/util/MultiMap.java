package net.rim.device.api.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class MultiMap implements Persistable {
   private Hashtable _hashtable;
   private int _initialVectorCapacity;

   public MultiMap(int var1, int var2) {
      if (var2 < 0) {
         throw new Object();
      }

      this._hashtable = new Hashtable(var1);
      this._initialVectorCapacity = var2;
   }

   public MultiMap() {
      this(16, 10);
   }

   public boolean isEmpty() {
      return this._hashtable.isEmpty();
   }

   public void clear() {
      this._hashtable.clear();
   }

   public boolean add(Object var1, Object var2) {
      Vector var3 = (Vector)this._hashtable.get(var1);
      if (var3 == null) {
         var3 = new Vector(this._initialVectorCapacity);
         this._hashtable.put(var1, var3);
      }

      if (var3.contains(var2)) {
         return false;
      }

      var3.addElement(var2);
      return true;
   }

   public boolean removeKey(Object var1) {
      return this._hashtable.remove(var1) != null;
   }

   public boolean removeValue(Object var1, Object var2) {
      Vector var3 = (Vector)this._hashtable.get(var1);
      if (var3 == null) {
         return false;
      }

      if (!var3.removeElement(var2)) {
         return false;
      }

      if (var3.size() == 0) {
         this._hashtable.remove(var1);
      }

      return true;
   }

   public boolean removeValue(Object var1) {
      boolean var2 = false;
      Enumeration var3 = this._hashtable.keys();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         if (this.removeValue(var4, var1)) {
            var2 = true;
         }
      }

      return var2;
   }

   public boolean containsKey(Object var1) {
      return this._hashtable.containsKey(var1);
   }

   public boolean containsValue(Object var1, Object var2) {
      Vector var3 = (Vector)this._hashtable.get(var1);
      return var3 == null ? false : var3.contains(var2);
   }

   public Enumeration keys() {
      return this._hashtable.keys();
   }

   public Enumeration elements(Object var1) {
      Vector var2 = (Vector)this._hashtable.get(var1);
      return (Enumeration)(var2 == null ? new Object() : var2.elements());
   }

   public Enumeration elements() {
      Vector var1 = new Vector();
      Enumeration var2 = this._hashtable.keys();

      while (var2.hasMoreElements()) {
         Enumeration var3 = this.elements(var2.nextElement());

         while (var3.hasMoreElements()) {
            Object var4 = var3.nextElement();
            var1.addElement(var4);
         }
      }

      return var1.elements();
   }

   public int size() {
      int var1 = 0;
      Enumeration var2 = this._hashtable.keys();

      while (var2.hasMoreElements()) {
         Vector var3 = (Vector)this._hashtable.get(var2.nextElement());
         if (var3 != null) {
            var1 += var3.size();
         }
      }

      return var1;
   }

   public int size(Object var1) {
      Vector var2 = (Vector)this._hashtable.get(var1);
      return var2 == null ? 0 : var2.size();
   }

   @Override
   public String toString() {
      return this._hashtable.toString();
   }
}
