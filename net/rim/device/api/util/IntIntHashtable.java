package net.rim.device.api.util;

public class IntIntHashtable implements Persistable {
   private int[] _key;
   private int[] _value;
   private byte[] _occupied;
   private int _numberOfKeys;
   private int _threshold;
   static final byte OCCUPIED_NO;
   static final byte OCCUPIED_YES;
   static final byte OCCUPIED_NIL;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public IntIntHashtable(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new int[var1];
      this._value = new int[var1];
      this._occupied = new byte[var1];
      this._threshold = var1 * 3 >> 2;
   }

   public IntIntHashtable() {
      this(11);
   }

   public int size() {
      return this._numberOfKeys;
   }

   public boolean isEmpty() {
      return this._numberOfKeys == 0;
   }

   public synchronized IntEnumeration keys() {
      return (IntEnumeration)(new Object(this._key, this._occupied));
   }

   public synchronized IntEnumeration elements() {
      return (IntEnumeration)(new Object(this._value, this._occupied));
   }

   public synchronized boolean contains(int var1) {
      byte[] var2 = this._occupied;
      int[] var3 = this._value;
      int var4 = var3.length;

      while (--var4 >= 0) {
         if (var2[var4] == 1 && var3[var4] == var1) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean containsKey(int var1) {
      int var2 = this.find(var1);
      return this._occupied[var2] == 1;
   }

   public synchronized void clear() {
      int var1 = this._key.length;

      while (--var1 >= 0) {
         this._occupied[var1] = 0;
      }

      this._numberOfKeys = 0;
   }

   public synchronized int remove(int var1) {
      int var2 = this.find(var1);
      if (this._occupied[var2] != 1) {
         return -1;
      } else {
         int var3 = this._value[var2];
         this._numberOfKeys--;
         if (this._numberOfKeys == 0) {
            this._occupied[var2] = 0;
            return var3;
         } else {
            this._occupied[var2] = 2;
            return var3;
         }
      }
   }

   public synchronized int get(int var1) {
      int var2 = this.find(var1);
      return this._occupied[var2] != 1 ? -1 : this._value[var2];
   }

   protected void rehash() {
      int[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      int[] var4 = this._value;
      int[] var5 = new int[var3];
      int[] var6 = new int[var3];
      byte[] var7 = this._occupied;
      byte[] var8 = new byte[var3];
      this._key = var5;
      this._value = var6;
      this._occupied = var8;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var7[var2] == 1) {
            int var9 = var1[var2];
            int var10 = this.find(var9);
            var5[var10] = var9;
            var6[var10] = var4[var2];
            var8[var10] = 1;
         }
      }
   }

   public synchronized int put(int var1, int var2) {
      if (this._numberOfKeys + 1 > this._threshold) {
         this.rehash();
      }

      int var3 = this.find(var1);
      int var4;
      if (this._occupied[var3] == 1) {
         var4 = this._value[var3];
      } else {
         this._numberOfKeys++;
         var4 = -1;
      }

      this._occupied[var3] = 1;
      this._key[var3] = var1;
      this._value[var3] = var2;
      return var4;
   }

   private int find(int var1) {
      byte[] var2 = this._occupied;
      int[] var3 = this._key;
      int var4 = var1;
      int var5 = var3.length;
      int var6 = (var4 & 2147483647) % var5;
      int var7 = var5;
      int var8 = -1;
      int var9 = 0;

      label52:
      while (true) {
         int var10 = var6;

         while (var2[var10] != 0) {
            if (var2[var10] != 2) {
               if (var3[var10] == var1) {
                  return var10;
               }
            } else if (var8 == -1) {
               var8 = var10;
            }

            var10 += var7;
            if (var10 >= var5) {
               if (var7 >= var5) {
                  if (var5 > 3) {
                     var7 = (var4 >>> 1) % (var5 - 1) + 1;
                  } else {
                     var7 = (var4 >>> 1) % var5;
                  }

                  var10 = var10 - var5 + var7;
                  if (var10 < var5) {
                     var10 += var5;
                  }
               }

               var10 -= var5;
            }

            var9++;
            if (var10 == var6) {
               if (++var6 >= var5) {
                  var6 = 0;
               }

               if (var9 >= var5) {
                  return var8;
               }
               continue label52;
            }
         }

         if (var8 != -1) {
            return var8;
         }

         return var10;
      }
   }
}
