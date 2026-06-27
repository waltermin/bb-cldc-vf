package java.util;

public class Hashtable {
   private Object[] _key;
   private int[] _hash;
   private Object[] _value;
   private Object _empty;
   private int _numberOfKeys;
   private int _threshold;
   private static final int _loadFactorMul;
   private static final int _loadFactorRShift;

   public Hashtable(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      if (var1 < 1) {
         var1 = 1;
      }

      this._key = new Object[var1];
      this._hash = new int[var1];
      this._value = new Object[var1];
      this._empty = new Object();
      this._threshold = var1 * 3 >> 2;
   }

   public Hashtable() {
      this(11);
   }

   public int size() {
      return this._numberOfKeys;
   }

   public boolean isEmpty() {
      return this._numberOfKeys == 0;
   }

   public synchronized Enumeration keys() {
      return (Enumeration)(new Object(this._key, this._empty));
   }

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this._value, this._empty));
   }

   public synchronized boolean contains(Object var1) {
      if (var1 == null) {
         throw new Object();
      }

      Object var2 = this._empty;
      Object[] var3 = this._key;
      Object[] var4 = this._value;
      int var5 = var4.length;

      while (--var5 >= 0) {
         if (var3[var5] != null && var3[var5] != var2 && var4[var5].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public synchronized boolean containsKey(Object var1) {
      int var2 = this.find(var1, var1.hashCode());
      return this._key[var2] != null && this._key[var2] != this._empty;
   }

   public synchronized void clear() {
      for (int var1 = this._key.length; --var1 >= 0; this._key[var1] = null) {
         this._value[var1] = null;
      }

      this._numberOfKeys = 0;
   }

   @Override
   public synchronized String toString() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized Object remove(Object var1) {
      int var2 = this.find(var1, var1.hashCode());
      if (this._key[var2] != null && this._key[var2] != this._empty) {
         Object var3 = this._value[var2];
         this._numberOfKeys--;
         if (this._numberOfKeys == 0) {
            this._key[var2] = null;
            this._value[var2] = null;
            return var3;
         } else {
            this._key[var2] = this._empty;
            this._value[var2] = null;
            return var3;
         }
      } else {
         return null;
      }
   }

   public synchronized Object get(Object var1) {
      int var2 = this.find(var1, var1.hashCode());
      return this._value[var2];
   }

   protected void rehash() {
      Object[] var1 = this._key;
      int var2 = var1.length;
      int var3 = (var2 << 1) + 1;
      Object[] var4 = this._value;
      int[] var5 = this._hash;
      int[] var6 = new int[var3];
      Object[] var7 = new Object[var3];
      Object[] var8 = new Object[var3];
      Object var9 = this._empty;
      this._key = var7;
      this._hash = var6;
      this._value = var8;
      this._threshold = var3 * 3 >> 2;

      while (--var2 >= 0) {
         if (var1[var2] != null && var1[var2] != var9) {
            Object var10 = var1[var2];
            int var11 = var5[var2];
            int var12 = this.find(var10, var11);
            var6[var12] = var11;
            var7[var12] = var10;
            var8[var12] = var4[var2];
            var4[var2] = null;
            var1[var2] = null;
         }
      }
   }

   public synchronized Object put(Object var1, Object var2) {
      if (var2 == null) {
         throw new Object();
      }

      int var3 = var1.hashCode();
      int var4 = this.find(var1, var3);
      if (this._key[var4] == null || this._key[var4] == this._empty) {
         this._numberOfKeys++;
      }

      if (this._numberOfKeys > this._threshold) {
         this.rehash();
         var4 = this.find(var1, var3);
      }

      Object var5 = this._value[var4];
      this._key[var4] = var1;
      this._hash[var4] = var3;
      this._value[var4] = var2;
      return var5;
   }

   private int find(Object var1, int var2) {
      Object var3 = this._empty;
      int[] var4 = this._hash;
      Object[] var5 = this._key;
      int var6 = var5.length;
      int var7 = (var2 & 2147483647) % var6;
      int var8 = var6;
      int var9 = -1;
      int var10 = 0;

      label55:
      while (true) {
         int var11 = var7;

         while (var5[var11] != null) {
            if (var5[var11] != var3) {
               if (var4[var11] == var2 && var5[var11].equals(var1)) {
                  return var11;
               }
            } else if (var9 == -1) {
               var9 = var11;
            }

            var11 += var8;
            if (var11 >= var6) {
               if (var8 >= var6) {
                  if (var6 > 3) {
                     var8 = (var2 >>> 1) % (var6 - 1) + 1;
                  } else {
                     var8 = (var2 >>> 1) % var6;
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
               continue label55;
            }
         }

         if (var9 != -1) {
            return var9;
         }

         return var11;
      }
   }
}
