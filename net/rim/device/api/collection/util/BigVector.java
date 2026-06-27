package net.rim.device.api.collection.util;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.Persistable;
import net.rim.vm.Array;

public class BigVector implements Persistable {
   private Object[] _contiguousArray;
   private int _arrayChunkSize;
   private int _numArrayChunks;
   private int _vectorSize;
   private Object[][][] _arrayChunks;
   private int[] _firstElementIndex;
   private int[] _chunkStartIndex;
   private Object[] _currChunk;
   private int _currChunkIndex;
   private int _currChunkFirstElementIndex;
   private int _currChunkLastElementIndexPlusOne;
   private int _currChunkStartIndex;

   private void adjustSubsequentElementIndexes(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void removeCurrentChunk() {
      if (this._numArrayChunks == 1) {
         this.init(new Object[this._arrayChunkSize]);
      } else {
         this._numArrayChunks--;
         int var1 = this._numArrayChunks - this._currChunkIndex;
         System.arraycopy(this._firstElementIndex, this._currChunkIndex + 1, this._firstElementIndex, this._currChunkIndex, var1 + 1);
         System.arraycopy(this._arrayChunks, this._currChunkIndex + 1, this._arrayChunks, this._currChunkIndex, var1);
         System.arraycopy(this._chunkStartIndex, this._currChunkIndex + 1, this._chunkStartIndex, this._currChunkIndex, var1);
         Array.resize(this._firstElementIndex, this._numArrayChunks + 1);
         Array.resize(this._arrayChunks, this._numArrayChunks);
         Array.resize(this._chunkStartIndex, this._numArrayChunks);
      }
   }

   private void uncacheIndex() {
      this._contiguousArray = null;
      this._currChunkIndex = -1;
      this._currChunk = null;
      this._currChunkStartIndex = -1;
      this._currChunkFirstElementIndex = -1;
      this._currChunkLastElementIndexPlusOne = -1;
   }

   private void cacheIndex(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private void splitCurrentChunk(int var1) {
      this._numArrayChunks++;
      int var2 = this._currChunkIndex + 1;
      int var3 = this._numArrayChunks - this._currChunkIndex - 2;
      Array.resize(this._firstElementIndex, this._numArrayChunks + 1);
      Array.resize(this._arrayChunks, this._numArrayChunks);
      Array.resize(this._chunkStartIndex, this._numArrayChunks);
      System.arraycopy(this._firstElementIndex, var2, this._firstElementIndex, var2 + 1, var3 + 1);
      System.arraycopy(this._arrayChunks, var2, this._arrayChunks, var2 + 1, var3);
      System.arraycopy(this._chunkStartIndex, var2, this._chunkStartIndex, var2 + 1, var3);
      Object[] var4 = new Object[this._arrayChunkSize];
      this._firstElementIndex[var2] = this._firstElementIndex[this._currChunkIndex] + var1;
      this._arrayChunks[var2] = var4;
      this._chunkStartIndex[var2] = 0;
      System.arraycopy(this._currChunk, var1 + this._currChunkStartIndex, var4, 0, this._arrayChunkSize - var1);

      for (int var5 = var1; var5 < this._arrayChunkSize; var5++) {
         this._currChunk[var5 + this._currChunkStartIndex] = null;
      }

      this._currChunkIndex = 0;
   }

   private void appendChunk() {
      Array.resize(this._firstElementIndex, this._numArrayChunks + 2);
      Array.resize(this._arrayChunks, this._numArrayChunks + 1);
      Array.resize(this._chunkStartIndex, this._numArrayChunks + 1);
      this._firstElementIndex[this._numArrayChunks + 1] = this._firstElementIndex[this._numArrayChunks] + this._arrayChunkSize;
      this._arrayChunks[this._numArrayChunks] = new Object[this._arrayChunkSize];
      this._chunkStartIndex[this._numArrayChunks] = 0;
      this._numArrayChunks++;
      this._currChunkIndex = 0;
   }

   private int getNumChunks(int var1) {
      return (var1 + this._arrayChunkSize - 1) / this._arrayChunkSize;
   }

   private void getNumArrayChunks(int var1, int var2) {
      if (var2 > 0 && var1 >= 0) {
         if (var2 > 256) {
            var2 = 256;
         }

         if (var2 < 32) {
            var2 = 32;
         }

         if (var1 < var2) {
            var1 = var2;
         }

         this._arrayChunkSize = var2;
         this._numArrayChunks = this.getNumChunks(var1);
      } else {
         throw new Object();
      }
   }

   private void init(Object[] var1) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public BigVector(int var1, int var2) {
      this.getNumArrayChunks(var1, var2);
      this.init(new Object[this._numArrayChunks * this._arrayChunkSize]);
      this._vectorSize = 0;
   }

   public BigVector(int var1) {
      this(var1, 64);
   }

   public BigVector() {
      this(64, 64);
   }

   public int size() {
      return this._vectorSize;
   }

   public Object[] getContiguousArray() {
      if (this._contiguousArray != null) {
         return this._contiguousArray;
      }

      this.uncacheIndex();
      int var1 = this.getNumChunks(this._vectorSize) * this._arrayChunkSize;
      Object[] var2 = new Object[var1];

      for (this._currChunkIndex = 0; this._currChunkIndex < this._numArrayChunks; this._currChunkIndex++) {
         int var3 = this._firstElementIndex[this._currChunkIndex];
         Object[][] var4 = this._arrayChunks[this._currChunkIndex];
         int var5 = this._chunkStartIndex[this._currChunkIndex];
         int var6 = this._firstElementIndex[this._currChunkIndex + 1];
         if (var6 > this._vectorSize) {
            var6 = this._vectorSize;
         }

         while (var3 < var6) {
            var2[var3] = var4[var5];
            var3++;
            var5++;
         }
      }

      this.getNumArrayChunks(this._vectorSize, this._arrayChunkSize);
      this.init(var2);
      return var2;
   }

   public synchronized void optimize() {
      this.getContiguousArray();
   }

   public boolean isEmpty() {
      return this._vectorSize == 0;
   }

   public synchronized Object elementAt(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized int firstIndexOf(Object var1) {
      for (int var2 = 0; var2 < this._numArrayChunks; var2++) {
         int var3 = this._firstElementIndex[var2];
         int var4 = this._firstElementIndex[var2 + 1] - var3;
         int var5 = this._chunkStartIndex[var2];
         int var6 = var5 + var4;
         Object[][] var7 = this._arrayChunks[var2];

         for (int var8 = var5; var8 < var6; var8++) {
            if (var7[var8] == var1) {
               int var9 = var3 + var8 - var5;
               if (var9 >= this._vectorSize) {
                  return -1;
               }

               return var9;
            }
         }
      }

      return -1;
   }

   public synchronized void setElementAt(Object var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void removeElementAt(int var1) {
      this.cacheIndex(var1);
      this.adjustSubsequentElementIndexes(-1);
      int var2 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
      if (var2 == 1) {
         this._vectorSize--;
         this.removeCurrentChunk();
      } else {
         int var3 = var1 - this._currChunkFirstElementIndex;
         int var4 = var2 - var3 - 1;
         if (var4 > 0) {
            System.arraycopy(this._currChunk, var3 + this._currChunkStartIndex + 1, this._currChunk, var3 + this._currChunkStartIndex, var4);
         }

         this._vectorSize--;
         this._currChunk[var2 + this._currChunkStartIndex - 1] = null;
      }

      this.uncacheIndex();
   }

   public synchronized void removeAll() {
      if (this._vectorSize != 0) {
         this._numArrayChunks = 1;
         this.uncacheIndex();
         this.init(new Object[this._arrayChunkSize]);
         this._vectorSize = 0;
      }
   }

   public synchronized void insertElementAt(Object var1, int var2) {
      if (var2 == this._vectorSize) {
         this.addElement(var1);
      } else {
         this.cacheIndex(var2);
         int var3;
         if (this._currChunkLastElementIndexPlusOne > this._vectorSize) {
            var3 = this._vectorSize - this._currChunkFirstElementIndex;
         } else {
            var3 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
         }

         if (var3 >= this._arrayChunkSize) {
            this.splitCurrentChunk(var3 >> 1);
            this.cacheIndex(var2);
            var3 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
         }

         int var4 = var2 - this._currChunkFirstElementIndex;
         int var5 = var3 - var4;
         if (var5 > 0) {
            System.arraycopy(this._currChunk, var4 + this._currChunkStartIndex, this._currChunk, var4 + this._currChunkStartIndex + 1, var5);
         }

         if (this._currChunkLastElementIndexPlusOne <= this._vectorSize) {
            this.adjustSubsequentElementIndexes(1);
         }

         this._currChunk[var4 + this._currChunkStartIndex] = var1;
         this._vectorSize++;
         this.uncacheIndex();
      }
   }

   public synchronized void insertElementsAt(Object[] var1, int var2) {
      int var3 = 0;

      while (var3 < var1.length) {
         this.insertElementAt(var1[var3], var2);
         var3++;
         var2++;
      }
   }

   public synchronized void addElement(Object var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized void addElements(Object[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         this.addElement(var1[var2]);
      }
   }

   public synchronized int copyInto(int var1, int var2, Object[] var3, int var4) {
      int var5 = this._vectorSize;
      if (var1 >= 0 && var1 <= var5) {
         if (var2 < 0) {
            var2 = 0;
         } else if (var1 + var2 > var5) {
            var2 = var5 - var1;
         }

         if (var2 != 0) {
            if (this._contiguousArray != null) {
               System.arraycopy(this._contiguousArray, var1, var3, var4, var2);
               return var2;
            }

            int var6 = var2;

            do {
               if (var1 < this._currChunkFirstElementIndex || var1 >= this._currChunkLastElementIndexPlusOne) {
                  this.cacheIndex(var1);
               }

               int var7 = var1 - this._currChunkFirstElementIndex;
               int var8 = var7 + this._currChunkStartIndex;
               int var9 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
               var9 -= var7;
               if (var9 > var6) {
                  var9 = var6;
               }

               System.arraycopy(this._currChunk, var8, var3, var4, var9);
               var1 += var9;
               var4 += var9;
               var6 -= var9;
            } while (var6 > 0);
         }

         return var2;
      } else {
         throw new Object();
      }
   }

   public synchronized int binarySearch(Comparator var1, Object var2) {
      if (this._contiguousArray != null) {
         return Arrays.binarySearch(this._contiguousArray, var2, var1, 0, this._vectorSize);
      }

      int var3 = 0;
      int var4 = this._numArrayChunks;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      Object[][] var9 = null;

      while (var4 != var3) {
         var5 = var4 + var3 >> 1;
         var9 = this._arrayChunks[var5];
         int var10 = this._firstElementIndex[var5 + 1];
         if (var10 > this._vectorSize) {
            var10 = this._vectorSize;
         }

         int var11 = this._firstElementIndex[var5];
         if (var11 > this._vectorSize) {
            var11 = this._vectorSize;
         }

         var6 = var10 - var11;
         var7 = this._chunkStartIndex[var5];
         var8 = var7 + var6;
         if (var6 != 0 && var1.compare(var9[var7], var2) <= 0) {
            if (var1.compare(var9[var8 - 1], var2) >= 0) {
               break;
            }

            var3 = var5 + 1;
         } else {
            var4 = var5;
         }
      }

      if (this._firstElementIndex[var5] > this._vectorSize) {
         return -this._vectorSize - 1;
      }

      int var13 = Arrays.binarySearch(var9, var2, var1, var7, var8);
      return var13 < 0 ? var13 - this._firstElementIndex[var5] + var7 : this._firstElementIndex[var5] + var13 - var7;
   }

   public synchronized void sort(Comparator var1) {
      Arrays.sort(this.getContiguousArray(), 0, this._vectorSize, var1);
   }

   public synchronized boolean removeElement(Comparator var1, Object var2) {
      boolean var3 = false;
      int var4 = this.binarySearch(var1, var2);
      if (var4 >= 0) {
         this.removeElementAt(var4);
         var3 = true;
      }

      return var3;
   }

   public synchronized void insertElement(Comparator var1, Object var2) {
      int var3 = this.binarySearch(var1, var2);
      if (var3 < 0) {
         var3 = -var3 - 1;
      }

      this.insertElementAt(var2, var3);
   }

   public synchronized boolean updateElement(Comparator var1, Object var2, Object var3) {
      boolean var4 = true;
      if (var2 != var3) {
         if (this.removeElement(var1, var2)) {
            this.insertElement(var1, var3);
         } else {
            var4 = false;
         }
      } else {
         var4 = this.binarySearch(var1, var2) >= 0;
      }

      return var4;
   }

   public synchronized int getIndex(Comparator var1, Object var2) {
      if (var2 == null) {
         return -1;
      }

      int var3 = this.binarySearch(var1, var2);
      if (var3 < 0) {
         var3 = -1;
      }

      return var3;
   }
}
