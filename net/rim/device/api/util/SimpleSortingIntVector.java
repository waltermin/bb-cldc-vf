package net.rim.device.api.util;

import net.rim.vm.Array;

public class SimpleSortingIntVector extends IntVector {
   private IntComparator _sortComparator;
   private short _sorted;
   private short _sortAsAdded;
   private boolean _uniqueComparator;
   public static final short SORT_TYPE_NUMERIC;
   public static final short SORT_TYPE_COMPARISON;
   public static final short SORT_TYPE_NONE;

   public short getSortState() {
      return this._sorted;
   }

   public void setSortComparator(IntComparator var1, boolean var2, boolean var3) {
      this._sortComparator = var1;
      this._uniqueComparator = var3;
      if (var2) {
         this.doBulkSort((short)2);
      }
   }

   public boolean setSortAsAdded(short var1) {
      switch (var1) {
         case -1:
            return false;
         case 0:
         case 1:
         case 2:
         default:
            this._sortAsAdded = var1;
            return true;
      }
   }

   public void reSort(short var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public synchronized void addElement(int var1) {
      int var2 = super.elementCount + 1;
      if (var2 > super.elementData.length) {
         int var3 = super.elementData.length;
         int var4 = super.capacityIncrement > 0 ? var3 + super.capacityIncrement : var3 * 2;
         if (var4 < var2) {
            var4 = var2;
         }

         Array.resize(super.elementData, var4);
      }

      int var5 = -1;
      if (this._sortAsAdded != 0) {
         var5 = this.binarySearch(var1, this._sortAsAdded);
         if (var5 < 0) {
            var5 = -var5 - 1;
         }

         System.arraycopy(super.elementData, var5, super.elementData, var5 + 1, super.elementCount - var5);
         super.elementData[var5] = var1;
      } else {
         this._sorted = 0;
         super.elementData[super.elementCount] = var1;
      }

      super.elementCount++;
   }

   @Override
   public boolean removeElement(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized int binarySearch(int var1, short var2) {
      if (this._sorted != var2) {
         this.doBulkSort(var2);
      }

      if (var2 == 1) {
         return Arrays.binarySearch(super.elementData, var1, 0, super.elementCount);
      } else if (this._sortComparator != null && var2 == 2) {
         return Arrays.binarySearch(super.elementData, var1, this._sortComparator, 0, super.elementCount);
      } else {
         throw new Object();
      }
   }

   public int bestGuessBinarySearch(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public synchronized int linearSearch(int var1) {
      return Arrays.getIndex(super.elementData, var1);
   }

   private void doBulkSort(short var1) {
      if (super.elementCount > 0) {
         this._sorted = var1;
         switch (var1) {
            case 0:
               this._sorted = 0;
               break;
            case 1:
            default:
               Arrays.sort(super.elementData, 0, super.elementCount);
               return;
            case 2:
               Arrays.sort(super.elementData, 0, super.elementCount, this._sortComparator);
               return;
         }
      }
   }

   private int findObjectUsingNonUniqueComparason(int var1) {
      int var2 = 0;
      boolean var3 = false;
      boolean var4 = false;
      int var5 = this.binarySearch(var1, this._sorted);
      if (super.elementData[var5] == var1) {
         return var5;
      }

      for (; !var3 && !var4; var2++) {
         if (var5 + var2 < super.elementCount && this._sortComparator.compare(var1, super.elementData[var5 + var2]) == 0) {
            if (super.elementData[var5 + var2] == var1) {
               return var5 + var2;
            }
         } else if (!var3) {
            var3 = true;
         }

         if (var5 - var2 >= 0 && this._sortComparator.compare(var1, super.elementData[var5 - var2]) == 0) {
            if (super.elementData[var5 - var2] == var1) {
               return var5 - var2;
            }
         } else if (!var4) {
            var4 = true;
         }
      }

      return -1;
   }
}
