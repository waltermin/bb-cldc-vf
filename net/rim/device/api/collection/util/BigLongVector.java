package net.rim.device.api.collection.util;

import net.rim.device.api.util.Persistable;
import net.rim.vm.Array;

public class BigLongVector implements Persistable {
   private long[] _contiguousArray;
   private int _arrayChunkSize;
   private int _numArrayChunks;
   private int _vectorSize;
   private long[][][] _arrayChunks;
   private int[] _firstElementIndex;
   private int[] _chunkStartIndex;
   private long[] _currChunk;
   private int _currChunkIndex;
   private int _currChunkFirstElementIndex;
   private int _currChunkLastElementIndexPlusOne;
   private int _currChunkStartIndex;

   private void adjustSubsequentElementIndexes(int var1) {
      throw new RuntimeException("cod2jar: array load: unknown element");
   }

   private void removeCurrentChunk() {
      throw new RuntimeException("cod2jar: stack imbalance");
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
      long[] var4 = new long[this._arrayChunkSize];
      this._firstElementIndex[var2] = this._firstElementIndex[this._currChunkIndex] + var1;
      this._arrayChunks[var2] = (long[][])var4;
      this._chunkStartIndex[var2] = 0;
      System.arraycopy(this._currChunk, var1 + this._currChunkStartIndex, var4, 0, this._arrayChunkSize - var1);
      this._currChunkIndex = 0;
   }

   private void appendChunk() {
      Array.resize(this._firstElementIndex, this._numArrayChunks + 2);
      Array.resize(this._arrayChunks, this._numArrayChunks + 1);
      Array.resize(this._chunkStartIndex, this._numArrayChunks + 1);
      this._firstElementIndex[this._numArrayChunks + 1] = this._firstElementIndex[this._numArrayChunks] + this._arrayChunkSize;
      this._arrayChunks[this._numArrayChunks] = (long[][])(new long[this._arrayChunkSize]);
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

   private void init(long[] var1) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public BigLongVector(int var1, int var2) {
   }

   public BigLongVector(int var1) {
      this(var1, 64);
   }

   public BigLongVector() {
      this(64, 64);
   }

   public int size() {
      return this._vectorSize;
   }

   public long[] getContiguousArray() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public synchronized void optimize() {
      this.getContiguousArray();
   }

   public boolean isEmpty() {
      return this._vectorSize == 0;
   }

   public synchronized long elementAt(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized int firstIndexOf(long var1) {
      for (int var3 = 0; var3 < this._numArrayChunks; var3++) {
         int var4 = this._firstElementIndex[var3];
         int var5 = this._firstElementIndex[var3 + 1] - var4;
         int var6 = this._chunkStartIndex[var3];
         int var7 = var6 + var5;
         long[][] var8 = this._arrayChunks[var3];

         for (int var9 = var6; var9 < var7; var9++) {
            if (var8[var9] == var1) {
               int var10 = var4 + var9 - var6;
               if (var10 >= this._vectorSize) {
                  return -1;
               }

               return var10;
            }
         }
      }

      return -1;
   }

   public synchronized void setElementAt(long var1, int var3) {
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
      }

      this.uncacheIndex();
   }

   public synchronized void removeAll() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public synchronized void insertElementAt(long var1, int var3) {
      if (var3 == this._vectorSize) {
         this.addElement(var1);
      } else {
         this.cacheIndex(var3);
         int var4;
         if (this._currChunkLastElementIndexPlusOne > this._vectorSize) {
            var4 = this._vectorSize - this._currChunkFirstElementIndex;
         } else {
            var4 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
         }

         if (var4 >= this._arrayChunkSize) {
            this.splitCurrentChunk(var4 >> 1);
            this.cacheIndex(var3);
            var4 = this._currChunkLastElementIndexPlusOne - this._currChunkFirstElementIndex;
         }

         int var5 = var3 - this._currChunkFirstElementIndex;
         int var6 = var4 - var5;
         if (var6 > 0) {
            System.arraycopy(this._currChunk, var5 + this._currChunkStartIndex, this._currChunk, var5 + this._currChunkStartIndex + 1, var6);
         }

         if (this._currChunkLastElementIndexPlusOne <= this._vectorSize) {
            this.adjustSubsequentElementIndexes(1);
         }

         this._currChunk[var5 + this._currChunkStartIndex] = var1;
         this._vectorSize++;
         this.uncacheIndex();
      }
   }

   public synchronized void insertElementsAt(long[] var1, int var2) {
      int var3 = 0;

      while (var3 < var1.length) {
         this.insertElementAt(var1[var3], var2);
         var3++;
         var2++;
      }
   }

   public synchronized void addElement(long var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized void addElements(long[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         this.addElement(var1[var2]);
      }
   }

   public synchronized int copyInto(int var1, int var2, long[] var3, int var4) {
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

   public synchronized int binarySearch(long var1) {
      throw new RuntimeException("cod2jar: stack imbalance");
   }

   public synchronized void sort() {
      throw new RuntimeException("cod2jar: stack imbalance");
   }
}
