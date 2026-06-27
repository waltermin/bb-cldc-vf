package net.rim.device.api.util;

import net.rim.vm.Array;

public class BitSet implements net.rim.vm.Persistable {
   private int[] _data;
   private int _numSet;

   public BitSet() {
      this(128);
   }

   public BitSet(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this._data = new int[var1 + 31 >> 5];
      this._numSet = -1;
   }

   public BitSet(BitSet var1) {
      int[] var2 = var1._data;
      this._data = new int[var2.length];
      System.arraycopy(var2, 0, this._data, 0, var2.length);
      this._numSet = var1._numSet;
   }

   public void fastSet(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public void set(int var1) {
      int var2 = 1 << (var1 & 31);
      int var3 = var1 >> 5;
      if (var3 >= this._data.length) {
         Array.resize(this._data, var3 + 1);
      }

      int var4 = this._data[var3];
      if ((var4 & var2) == 0) {
         var4 |= var2;
         if (this._numSet != -1) {
            this._numSet++;
         }

         this._data[var3] = var4;
      }
   }

   public void fastClear(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public void clear(int var1) {
      int var2 = 1 << (var1 & 31);
      int var3 = var1 >> 5;
      if (var3 < this._data.length) {
         int var4 = this._data[var3];
         if ((var4 & var2) != 0) {
            var4 &= ~var2;
            this._data[var3] = var4;
            if (this._numSet != -1) {
               this._numSet--;
            }
         }
      }
   }

   public void reset() {
      for (int var1 = 0; var1 < this._data.length; var1++) {
         this._data[var1] = 0;
      }

      this._numSet = 0;
   }

   public boolean isSet(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getNumSet() {
      if (this._numSet == -1) {
         this._numSet = 0;

         for (int var1 = 0; var1 < this._data.length; var1++) {
            for (int var2 = this._data[var1]; var2 != 0; var2 &= var2 - 1) {
               this._numSet++;
            }
         }
      }

      return this._numSet;
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getNextSet(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getPreviousSet(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getFirstSet() {
      return this.getNextSet(0);
   }

   public int getLastSet() {
      return this.getPreviousSet(this._data.length * 32 - 1);
   }

   public void and(BitSet var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public void or(BitSet var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   public void not() {
      int var1 = this._data.length;

      for (int var2 = 0; var2 < var1; var2++) {
         this._data[var2] = ~this._data[var2];
      }

      if (this._numSet != -1) {
         this._numSet = this._data.length * 32 - this._numSet;
      }
   }

   public void xor(BitSet var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }
}
