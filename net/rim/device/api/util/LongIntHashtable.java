package net.rim.device.api.util;

public class LongIntHashtable implements Persistable {
   private long[] _key;
   private int[] _value;
   private byte[] _occupied;
   private int _numberOfKeys;
   private int _threshold;
   static final byte OCCUPIED_NO;
   static final byte OCCUPIED_YES;
   static final byte OCCUPIED_NIL;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public LongIntHashtable(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new long[var1];
      this._value = new int[var1];
      this._occupied = new byte[var1];
      this._threshold = var1 * 3 >> 2;
   }

   public LongIntHashtable() {
      this(11);
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

   public synchronized boolean containsKey(long var1) {
      int var3 = this.find(var1);
      return this._occupied[var3] == 1;
   }

   public synchronized void clear() {
      int var1 = this._key.length;

      while (--var1 >= 0) {
         this._occupied[var1] = 0;
      }

      this._numberOfKeys = 0;
   }

   public synchronized int remove(long var1) {
      int var3 = this.find(var1);
      if (this._occupied[var3] != 1) {
         return -1;
      }

      int var4 = this._value[var3];
      this._numberOfKeys--;
      if (this._numberOfKeys == 0) {
         this._occupied[var3] = 0;
      } else {
         this._occupied[var3] = 2;
      }

      return var4;
   }

   public synchronized int get(long var1) {
      int var3 = this.find(var1);
      return this._occupied[var3] != 1 ? -1 : this._value[var3];
   }

   protected void rehash() {
      long[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      int[] var4 = this._value;
      long[] var5 = new long[var3];
      int[] var6 = new int[var3];
      byte[] var7 = this._occupied;
      byte[] var8 = new byte[var3];
      this._key = var5;
      this._value = var6;
      this._occupied = var8;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var7[var2] == 1) {
            long var9 = var1[var2];
            int var11 = this.find(var9);
            var5[var11] = var9;
            var6[var11] = var4[var2];
            var8[var11] = 1;
         }
      }
   }

   public synchronized int put(long var1, int var3) {
      if (this._numberOfKeys + 1 > this._threshold) {
         this.rehash();
      }

      int var4 = this.find(var1);
      int var5;
      if (this._occupied[var4] == 1) {
         var5 = this._value[var4];
      } else {
         this._numberOfKeys++;
         var5 = -1;
      }

      this._occupied[var4] = 1;
      this._key[var4] = var1;
      this._value[var4] = var3;
      return var5;
   }

   private int find(long var1) {
      byte[] var3 = this._occupied;
      long[] var4 = this._key;
      int var5 = (int)(var1 ^ var1 >> 32);
      int var6 = var4.length;
      int var7 = (var5 & 2147483647) % var6;
      int var8 = var6;
      int var9 = -1;
      int var10 = 0;

      label52:
      while (true) {
         int var11 = var7;

         while (var3[var11] != 0) {
            if (var3[var11] != 2) {
               if (var4[var11] == var1) {
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
