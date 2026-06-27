package net.rim.device.api.util;

import java.util.Enumeration;

public class LongHashtable implements Persistable {
   private long[] _key;
   private Object[] _value;
   private Object _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public LongHashtable(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new long[var1];
      this._value = new Object[var1];
      this._empty = new Object();
      this._threshold = var1 * 3 >> 2;
   }

   public LongHashtable() {
      this(11);
   }

   public LongHashtable(LongHashtable var1) {
   }

   public int size() {
      return this._numberOfKeys;
   }

   public boolean isEmpty() {
      return this._numberOfKeys == 0;
   }

   public synchronized LongEnumeration keys() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this._value, this._empty));
   }

   public synchronized void resetElements(Enumeration var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
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

   public synchronized boolean containsKey(long var1) {
      int var3 = this.find(var1);
      return this._value[var3] != null && this._value[var3] != this._empty;
   }

   public synchronized long getKey(Object var1) {
      Object[] var2 = this._value;
      int var3 = var2.length;

      while (--var3 >= 0) {
         if (var2[var3] != null && var2[var3] != this._empty && var2[var3].equals(var1)) {
            return this._key[var3];
         }
      }

      return 0;
   }

   public synchronized void clear() {
      int var1 = this._key.length;

      while (--var1 >= 0) {
         this._value[var1] = null;
      }

      this._numberOfKeys = 0;
   }

   public synchronized int keysToArray(long[] var1) {
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

   public synchronized Object remove(long var1) {
      int var3 = this.find(var1);
      if (this._value[var3] != null && this._value[var3] != this._empty) {
         Object var4 = this._value[var3];
         this._numberOfKeys--;
         if (this._numberOfKeys == 0) {
            this._value[var3] = null;
         } else {
            this._value[var3] = this._empty;
         }

         return var4;
      } else {
         return null;
      }
   }

   public synchronized Object get(long var1) {
      int var3 = this.find(var1);
      return this._value[var3] == this._empty ? null : this._value[var3];
   }

   protected void rehash() {
      long[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      Object[] var4 = this._value;
      long[] var5 = new long[var3];
      Object[] var6 = new Object[var3];
      Object var7 = this._empty;
      this._key = var5;
      this._value = var6;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var4[var2] != null && var4[var2] != var7) {
            long var8 = var1[var2];
            int var10 = this.find(var8);
            var5[var10] = var8;
            var6[var10] = var4[var2];
            var4[var2] = null;
         }
      }
   }

   public synchronized Object put(long var1, Object var3) {
      if (var3 == null) {
         throw new Object();
      }

      if (this._numberOfKeys + 1 > this._threshold) {
         this.rehash();
      }

      int var4 = this.find(var1);
      Object var5;
      if (this._value[var4] != null && this._value[var4] != this._empty) {
         var5 = this._value[var4];
      } else {
         this._numberOfKeys++;
         var5 = null;
      }

      this._key[var4] = var1;
      this._value[var4] = var3;
      return var5;
   }

   private int find(long var1) {
      Object var3 = this._empty;
      long[] var4 = this._key;
      Object[] var5 = this._value;
      int var6 = (int)(var1 ^ var1 >> 32);
      int var7 = var4.length;
      int var8 = (var6 & 2147483647) % var7;
      int var9 = var7;
      int var10 = -1;
      int var11 = 0;

      label52:
      while (true) {
         int var12 = var8;

         while (var5[var12] != null) {
            if (var5[var12] != var3) {
               if (var4[var12] == var1) {
                  return var12;
               }
            } else if (var10 == -1) {
               var10 = var12;
            }

            var12 += var9;
            if (var12 >= var7) {
               if (var9 >= var7) {
                  if (var7 > 3) {
                     var9 = (var6 >>> 1) % (var7 - 1) + 1;
                  } else {
                     var9 = (var6 >>> 1) % var7;
                  }

                  var12 = var12 - var7 + var9;
                  if (var12 < var7) {
                     var12 += var7;
                  }
               }

               var12 -= var7;
            }

            var11++;
            if (var12 == var8) {
               if (++var8 >= var7) {
                  var8 = 0;
               }

               if (var11 >= var7) {
                  return var10;
               }
               continue label52;
            }
         }

         if (var10 != -1) {
            return var10;
         }

         return var12;
      }
   }
}
