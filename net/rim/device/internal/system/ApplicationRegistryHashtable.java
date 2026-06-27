package net.rim.device.internal.system;

import net.rim.device.api.util.Persistable;

public final class ApplicationRegistryHashtable implements Persistable {
   private long[] _key;
   private Object[] _value;
   private boolean[] _protect;
   private Object _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public ApplicationRegistryHashtable(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new long[var1];
      this._value = new Object[var1];
      this._protect = new boolean[var1];
      this._empty = new Object();
      this._threshold = var1 * 3 >> 2;
   }

   public final synchronized Object remove(long var1, boolean var3) {
      int var4 = this.find(var1);
      if (this._value[var4] != null && this._value[var4] != this._empty) {
         Object var5 = this._value[var4];
         if (!var3 && this._protect[var4]) {
            throw new Object();
         }

         this._numberOfKeys--;
         if (this._numberOfKeys == 0) {
            this._value[var4] = null;
         } else {
            this._value[var4] = this._empty;
         }

         this._protect[var4] = false;
         return var5;
      } else {
         return null;
      }
   }

   public final synchronized Object get(long var1, boolean var3) {
      int var4 = this.find(var1);
      if (this._value[var4] == this._empty) {
         return null;
      } else if (!var3 && this._protect[var4]) {
         throw new Object();
      } else {
         return this._value[var4];
      }
   }

   protected final void rehash() {
      long[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      Object[] var4 = this._value;
      boolean[] var5 = this._protect;
      long[] var6 = new long[var3];
      Object[] var7 = new Object[var3];
      boolean[] var8 = new boolean[var3];
      Object var9 = this._empty;
      this._key = var6;
      this._value = var7;
      this._protect = var8;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var4[var2] != null && var4[var2] != var9) {
            long var10 = var1[var2];
            int var12 = this.find(var10);
            var6[var12] = var10;
            var7[var12] = var4[var2];
            var8[var12] = var5[var2];
            var4[var2] = null;
            var5[var2] = false;
         }
      }
   }

   public final synchronized Object put(long var1, Object var3, boolean var4) {
      if (var3 == null) {
         throw new NullPointerException();
      }

      if (this._numberOfKeys + 1 > this._threshold) {
         this.rehash();
      }

      int var5 = this.find(var1);
      Object var6;
      if (this._value[var5] != null && this._value[var5] != this._empty) {
         var6 = this._value[var5];
      } else {
         this._numberOfKeys++;
         var6 = null;
      }

      this._key[var5] = var1;
      this._value[var5] = var3;
      this._protect[var5] = var4;
      return var6;
   }

   private final int find(long var1) {
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
