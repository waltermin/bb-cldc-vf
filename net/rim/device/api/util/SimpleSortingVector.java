package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.Array;

public class SimpleSortingVector extends Vector {
   private Comparator _sortComparator;
   private boolean _sorted = true;
   private boolean _sortAsAdded;

   public synchronized Vector getVector() {
      Object var1 = new Object(super.elementCount);

      for (int var2 = 0; var2 < super.elementCount; var2++) {
         ((Vector)var1).addElement(super.elementData[var2]);
      }

      return (Vector)var1;
   }

   public void setSortComparator(Comparator var1) {
      this._sortComparator = var1;
      if (this._sortAsAdded) {
         this.doBulkSort();
      }
   }

   public boolean setSort(boolean var1) {
      boolean var2 = this._sortAsAdded;
      this._sortAsAdded = var1;
      if (this._sortAsAdded && !this._sorted) {
         this.doBulkSort();
      }

      return var2;
   }

   public void reSort() {
      this._sorted = false;
      this.doBulkSort();
   }

   public void add(Object var1) {
      this.addElement(var1);
   }

   @Override
   public synchronized void addElement(Object var1) {
      int var2 = super.elementCount + 1;
      if (var2 > super.elementData.length) {
         int var3 = super.elementData.length;
         int var4 = super.capacityIncrement > 0 ? var3 + super.capacityIncrement : var3 * 2;
         if (var4 < var2) {
            var4 = var2;
         }

         Array.resize(super.elementData, var4);
      }

      if (this._sortAsAdded) {
         int var5 = this.find(var1);
         if (var5 < 0) {
            var5 = -var5 - 1;
         }

         System.arraycopy(super.elementData, var5, super.elementData, var5 + 1, super.elementCount - var5);
         super.elementData[var5] = var1;
      } else {
         this._sorted = false;
         super.elementData[super.elementCount] = var1;
      }

      super.elementCount++;
   }

   public Object getAt(int var1) {
      return var1 >= 0 && var1 < super.elementCount ? this.elementAt(var1) : null;
   }

   public void remove(int var1) {
      if (var1 >= 0 && var1 < super.elementCount) {
         this.removeElementAt(var1);
      }
   }

   public void removeAll() {
      this.removeAllElements();
   }

   public synchronized int find(Object var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void doBulkSort() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
