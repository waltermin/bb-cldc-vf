package net.rim.device.api.util;

import net.rim.vm.Array;

public class IntVector implements Persistable {
   protected int[] elementData;
   protected int elementCount;
   protected int capacityIncrement;

   public IntVector(int var1, int var2) {
   }

   public IntVector(int var1) {
      this(var1, 0);
   }

   public IntVector() {
      this(10);
   }

   public void copyInto(int[] var1) {
      int var2 = this.elementCount;

      while (var2-- > 0) {
         var1[var2] = this.elementData[var2];
      }
   }

   public int[] toArray() {
      int[] var1 = new int[this.elementCount];
      System.arraycopy(this.elementData, 0, var1, 0, this.elementCount);
      return var1;
   }

   public int[] getArray() {
      return this.elementData;
   }

   public void trimToSize() {
      if (this.elementCount < this.elementData.length) {
         Array.resize(this.elementData, this.elementCount);
      }
   }

   public void ensureCapacity(int var1) {
      if (var1 > this.elementData.length) {
         this.ensureCapacityHelper(var1);
      }
   }

   private void ensureCapacityHelper(int var1) {
      int var2 = this.elementData.length;
      int var3 = this.capacityIncrement > 0 ? var2 + this.capacityIncrement : var2 * 2;
      if (var3 < var1) {
         var3 = var1;
      }

      Array.resize(this.elementData, var3);
   }

   public void setSize(int var1) {
      if (var1 > this.elementCount && var1 > this.elementData.length) {
         this.ensureCapacityHelper(var1);
      }

      this.elementCount = var1;
   }

   public int capacity() {
      return this.elementData.length;
   }

   public int size() {
      return this.elementCount;
   }

   public boolean isEmpty() {
      return this.elementCount == 0;
   }

   public boolean contains(int var1) {
      return this.indexOf(var1, 0) >= 0;
   }

   public int indexOf(int var1) {
      return this.indexOf(var1, 0);
   }

   public int indexOf(int var1, int var2) {
      for (int var3 = var2; var3 < this.elementCount; var3++) {
         if (var1 == this.elementData[var3]) {
            return var3;
         }
      }

      return -1;
   }

   public int lastIndexOf(int var1) {
      return this.lastIndexOf(var1, this.elementCount - 1);
   }

   public int lastIndexOf(int var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public int elementAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int firstElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[0];
      }
   }

   public int lastElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[this.elementCount - 1];
      }
   }

   public void setElementAt(int var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void removeElementAt(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void insertElementAt(int var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void addElement(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean removeElement(int var1) {
      int var2 = this.indexOf(var1);
      if (var2 >= 0) {
         this.removeElementAt(var2);
         return true;
      } else {
         return false;
      }
   }

   public void removeAllElements() {
      this.elementCount = 0;
   }
}
