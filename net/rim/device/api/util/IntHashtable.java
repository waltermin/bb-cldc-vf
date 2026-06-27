package net.rim.device.api.util;

import java.util.Enumeration;

public class IntHashtable implements Persistable {
   private int[] _key;
   private Object[] _value;
   private Object _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public IntHashtable(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new int[var1];
      this._value = new Object[var1];
      this._empty = new Object();
      this._threshold = var1 * 3 >> 2;
   }

   public IntHashtable() {
      this(11);
   }

   public int size() {
      return this._numberOfKeys;
   }

   public boolean isEmpty() {
      return this._numberOfKeys == 0;
   }

   public synchronized IntEnumeration keys() {
      return (IntEnumeration)(new Object(this._key, this._value, this._empty));
   }

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this._value, this._empty));
   }

   public synchronized boolean contains(Object var1) {
      Object var2 = this._empty;
      Object[] var3 = this._value;
      int var4 = var3.length;

      while (--var4 >= 0) {
         if (var3[var4] != null && var3[var4] != var2 && var3[var4].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean containsKey(int var1) {
      int var2 = this.find(var1);
      return this._value[var2] != null && this._value[var2] != this._empty;
   }

   public synchronized void removeValue(Object var1) {
      Object[] var2 = this._value;
      int var3 = var2.length;

      while (--var3 >= 0) {
         if (var2[var3] == var1) {
            var2[var3] = this._empty;
            this._numberOfKeys--;
         }
      }
   }

   public synchronized void clear() {
      int var1 = this._key.length;

      while (--var1 >= 0) {
         this._value[var1] = null;
      }

      this._numberOfKeys = 0;
   }

   public synchronized int keysToArray(int[] var1) {
      int var2 = 0;
      int var3 = this._value.length;
      Object var4 = this._empty;

      for (int var5 = 0; var5 < var3; var5++) {
         Object var6 = this._value[var5];
         if (var6 != null && var6 != var4) {
            var1[var2++] = this._key[var5];
         }
      }

      return this._numberOfKeys;
   }

   public synchronized Object remove(int var1) {
      int var2 = this.find(var1);
      if (this._value[var2] != null && this._value[var2] != this._empty) {
         Object var3 = this._value[var2];
         this._numberOfKeys--;
         if (this._numberOfKeys == 0) {
            this._value[var2] = null;
            return var3;
         } else {
            this._value[var2] = this._empty;
            return var3;
         }
      } else {
         return null;
      }
   }

   public synchronized Object get(int var1) {
      int var2 = this.find(var1);
      return this._value[var2] == this._empty ? null : this._value[var2];
   }

   protected void rehash() {
      int[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      Object[] var4 = this._value;
      int[] var5 = new int[var3];
      Object[] var6 = new Object[var3];
      Object var7 = this._empty;
      this._key = var5;
      this._value = var6;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var4[var2] != null && var4[var2] != var7) {
            int var8 = var1[var2];
            int var9 = this.find(var8);
            var5[var9] = var8;
            var6[var9] = var4[var2];
            var4[var2] = null;
         }
      }
   }

   public synchronized Object put(int var1, Object var2) {
      if (var2 == null) {
         throw new Object();
      }

      if (this._numberOfKeys + 1 > this._threshold) {
         this.rehash();
      }

      int var3 = this.find(var1);
      Object var4;
      if (this._value[var3] != null && this._value[var3] != this._empty) {
         var4 = this._value[var3];
      } else {
         this._numberOfKeys++;
         var4 = null;
      }

      this._key[var3] = var1;
      this._value[var3] = var2;
      return var4;
   }

   private int find(int var1) {
      Object var2 = this._empty;
      int[] var3 = this._key;
      Object[] var4 = this._value;
      int var5 = var1;
      int var6 = var3.length;
      int var7 = (var5 & 2147483647) % var6;
      int var8 = var6;
      int var9 = -1;
      int var10 = 0;

      label52:
      while (true) {
         int var11 = var7;

         while (var4[var11] != null) {
            if (var4[var11] != var2) {
               if (var3[var11] == var1) {
                  return var11;
               }
            } else if (var9 == -1) {
               var9 = var11;
            }

            var11 += var8;
            if (var11 >= var6) {
               if (var8 >= var6) {
                  if (var6 > 3) {
                     var8 = (var5 >>> 1) % (var6 - 1) + 1;
                  } else {
                     var8 = (var5 >>> 1) % var6;
                  }

                  var11 = var11 - var6 + var8;
                  if (var11 < var6) {
                     var11 += var6;
                  }
               }

               var11 -= var6;
            }

            var10++;
            if (var11 == var7) {
               if (++var7 >= var6) {
                  var7 = 0;
               }

               if (var10 >= var6) {
                  return var9;
               }
               continue label52;
            }
         }

         if (var9 != -1) {
            return var9;
         }

         return var11;
      }
   }
}
