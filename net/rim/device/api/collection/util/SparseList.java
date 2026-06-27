package net.rim.device.api.collection.util;

import java.util.Enumeration;
import net.rim.device.api.collection.ReadableIntMap;
import net.rim.device.api.collection.WritableList;
import net.rim.device.api.util.Persistable;
import net.rim.vm.Array;

public class SparseList implements Persistable, WritableList, ReadableIntMap {
   private int _chunkSize;
   private Object[] _objects;
   private int _objectCount;
   private int[] _holes;
   private int _holeCount;

   public Enumeration elements() {
      return (Enumeration)(new Object(this._objects));
   }

   public int addAndGetIndex(Object var1) {
      int var2 = -1;
      if (var1 == null) {
         throw new Object();
      }

      if (this._holeCount > 0) {
         this._holeCount--;
         var2 = this._holes[this._holeCount];
      } else if (this._objectCount < this._objects.length) {
         var2 = this._objectCount;
      } else {
         Array.resize(this._objects, this._objects.length + this._chunkSize);
         var2 = this._objectCount;
      }

      this._objects[var2] = var1;
      this._objectCount++;
      return var2;
   }

   @Override
   public void insertAt(int var1, Object var2) {
      if (var2 == null) {
         throw new Object();
      }

      this.get(var1);
      this._objects[var1] = var2;
   }

   @Override
   public void removeAt(int var1) {
      if (var1 >= 0 && var1 < this._objects.length && this._objects[var1] != null) {
         this._objects[var1] = null;
         this._objectCount--;
         if (var1 < this._objectCount + this._holeCount) {
            if (this._holeCount == this._holes.length) {
               Array.resize(this._holes, this._holeCount * 2);
            }

            this._holes[this._holeCount] = var1;
            this._holeCount++;
         } else {
            this.removeUnneededHoles();
         }
      } else {
         throw new Object();
      }
   }

   @Override
   public void remove(Object var1) {
      for (int var2 = this._objects.length - 1; var2 >= 0; var2--) {
         if (this._objects[var2] == var1) {
            this.removeAt(var2);
            return;
         }
      }
   }

   @Override
   public void removeAll() {
      this.initialize(0);
   }

   @Override
   public int size() {
      return this._objectCount;
   }

   @Override
   public Object get(int var1) {
      return var1 >= 0 && var1 < this._objects.length && this._objects[var1] != null ? this._objects[var1] : null;
   }

   @Override
   public int getKey(Object var1) {
      for (int var2 = 0; var2 < this._objects.length; var2++) {
         if (this._objects[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   @Override
   public boolean contains(int var1) {
      return var1 >= 0 && var1 < this._objects.length && this._objects[var1] != null;
   }

   @Override
   public void add(Object var1) {
      this.addAndGetIndex(var1);
   }

   public SparseList() {
      this(0);
   }

   private void initialize(int var1) {
      if (var1 <= 0) {
         var1 = 0;
      }

      this._objects = new Object[0];
      this._holes = new int[4];
      this._objectCount = 0;
      this._holeCount = 0;
      this._chunkSize = Array.getSectionSize(this._objects) / 4;
      Array.resize(this._objects, (var1 / this._chunkSize + 1) * this._chunkSize);
   }

   private synchronized void removeUnneededHoles() {
      int var1 = 0;

      while (var1 < this._holeCount) {
         if (this._holes[var1] >= this._objectCount + this._holeCount) {
            this._holeCount--;
            this._holes[var1] = this._holes[this._holeCount];
            var1 = 0;
         } else {
            var1++;
         }
      }

      int var2 = this._holeCount * 4 / 3 + 4;
      if (var2 < this._holes.length) {
         Array.resize(this._holes, var2);
      }
   }

   public SparseList(int var1) {
      this.initialize(var1);
   }
}
