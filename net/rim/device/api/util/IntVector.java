package net.rim.device.api.util;

import net.rim.vm.Array;

public class IntVector implements Persistable {
   protected int[] elementData;
   protected int elementCount;
   protected int capacityIncrement;

   public IntVector(int initialCapacity, int capacityIncrement) {
   }

   public IntVector(int initialCapacity) {
      this(initialCapacity, 0);
   }

   public IntVector() {
      this(10);
   }

   public void copyInto(int[] anArray) {
      int i = this.elementCount;

      while (i-- > 0) {
         anArray[i] = this.elementData[i];
      }
   }

   public int[] toArray() {
      int[] newArray = new int[this.elementCount];
      System.arraycopy(this.elementData, 0, newArray, 0, this.elementCount);
      return newArray;
   }

   public int[] getArray() {
      return this.elementData;
   }

   public void trimToSize() {
      if (this.elementCount < this.elementData.length) {
         Array.resize(this.elementData, this.elementCount);
      }
   }

   public void ensureCapacity(int minCapacity) {
      if (minCapacity > this.elementData.length) {
         this.ensureCapacityHelper(minCapacity);
      }
   }

   private void ensureCapacityHelper(int minCapacity) {
      int oldCapacity = this.elementData.length;
      int newCapacity = this.capacityIncrement > 0 ? oldCapacity + this.capacityIncrement : oldCapacity * 2;
      if (newCapacity < minCapacity) {
         newCapacity = minCapacity;
      }

      Array.resize(this.elementData, newCapacity);
   }

   public void setSize(int newSize) {
      if (newSize > this.elementCount && newSize > this.elementData.length) {
         this.ensureCapacityHelper(newSize);
      }

      this.elementCount = newSize;
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

   public boolean contains(int elem) {
      return this.indexOf(elem, 0) >= 0;
   }

   public int indexOf(int elem) {
      return this.indexOf(elem, 0);
   }

   public int indexOf(int elem, int index) {
      for (int i = index; i < this.elementCount; i++) {
         if (elem == this.elementData[i]) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(int elem) {
      return this.lastIndexOf(elem, this.elementCount - 1);
   }

   public int lastIndexOf(int elem, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public int elementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
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

   public void setElementAt(int obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void removeElementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void insertElementAt(int obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void addElement(int obj) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean removeElement(int obj) {
      int i = this.indexOf(obj);
      if (i >= 0) {
         this.removeElementAt(i);
         return true;
      } else {
         return false;
      }
   }

   public void removeAllElements() {
      this.elementCount = 0;
   }
}
