package net.rim.device.api.util;

import java.util.Enumeration;
import net.rim.vm.Array;

public class IntMultiMap implements Persistable {
   private int[] _ints;
   private Object[] _objects;
   private int _num;
   private boolean _sortRequired;
   private boolean _allowDuplicates;

   public IntMultiMap(int var1, boolean var2) {
      if (var1 < 0) {
         throw new Object();
      }

      this._ints = new int[var1 + 1];
      this._objects = new Object[var1 + 1];
      this._allowDuplicates = var2;
   }

   public IntMultiMap() {
      this(16, false);
   }

   public boolean isEmpty() {
      return this._num == 0;
   }

   public void clear() {
      this._num = 0;
      this._sortRequired = false;
   }

   public void trim() {
      this.verifySorted();
      Array.resize(this._ints, this._num + 1);
      Array.resize(this._objects, this._num + 1);
   }

   public void add(int var1, Object var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void verifySorted() {
      if (this._sortRequired) {
         this._sortRequired = false;
         Arrays.sort(this._ints, 0, this._num, this._objects);
         this._ints[this._num] = this._ints[this._num - 1] + 1;
         if (!this._allowDuplicates) {
            for (int var1 = 0; var1 < this._num; var1++) {
               int var2 = var1 + 1;

               while (var2 < this._num && this._ints[var1] == this._ints[var2]) {
                  if (this._objects[var2].equals(this._objects[var1])) {
                     int var3 = this._num - var2;
                     System.arraycopy(this._ints, var2 + 1, this._ints, var2, var3);
                     System.arraycopy(this._objects, var2 + 1, this._objects, var2, var3);
                     this._num--;
                  } else {
                     var2++;
                  }
               }
            }
         }
      }
   }

   protected int findKey(int var1) {
      this.verifySorted();
      int var2 = Arrays.binarySearch(this._ints, var1, 0, this._num);

      while (var2 > 0 && this._ints[var2 - 1] == var1) {
         var2--;
      }

      return var2;
   }

   public boolean removeKey(int var1) {
      int var2 = this.findKey(var1);
      if (var2 < 0) {
         return false;
      }

      int var3 = var2;

      while (this._ints[++var3] == var1) {
      }

      int var4 = this._num - var3 + 1;
      System.arraycopy(this._ints, var3, this._ints, var2, var4);
      System.arraycopy(this._objects, var3, this._objects, var2, var4);
      this._num -= var3 - var2;
      return true;
   }

   public boolean removeValue(int var1, Object var2) {
      int var3 = this.findKey(var1);
      if (var3 < 0) {
         return false;
      }

      do {
         if (this._objects[var3].equals(var2)) {
            int var4 = var3 + 1;
            int var5 = this._num - var4 + 1;
            System.arraycopy(this._ints, var4, this._ints, var3, var5);
            System.arraycopy(this._objects, var4, this._objects, var3, var5);
            this._num--;
         } else {
            var3++;
         }
      } while (this._ints[var3] == var1);

      return true;
   }

   public boolean removeValue(Object var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean containsKey(int var1) {
      return this.findKey(var1) >= 0;
   }

   public boolean containsValue(int var1, Object var2) {
      int var3 = this.findKey(var1);
      if (var3 < 0) {
         return false;
      }

      while (!this._objects[var3].equals(var2)) {
         if (this._ints[++var3] != var1) {
            return false;
         }
      }

      return true;
   }

   public IntEnumeration keys() {
      this.verifySorted();
      return (IntEnumeration)(new Object(this));
   }

   public Enumeration elements(int var1) {
      this.verifySorted();
      return (Enumeration)(new Object(this, var1));
   }

   public Enumeration elements() {
      this.verifySorted();
      return (Enumeration)(new Object(this));
   }

   public int size() {
      if (this._num == 0) {
         return 0;
      }

      this.verifySorted();
      int var1 = 1;
      int var2 = this._ints[0];

      for (int var3 = 0; var3 < this._num; var3++) {
         if (this._ints[var3] != var2) {
            var2 = this._ints[var3];
            var1++;
         }
      }

      return var1;
   }

   public int size(int var1) {
      int var2 = this.findKey(var1);
      if (var2 < 0) {
         return 0;
      }

      int var3 = 1;

      while (this._ints[++var2] == var1) {
         var3++;
      }

      return var3;
   }
}
