package net.rim.device.api.util;

import net.rim.vm.Array;

public class ByteVector implements Persistable {
   protected byte[] elementData;
   protected int elementCount;
   protected int capacityIncrement;

   public ByteVector(int initialCapacity, int capacityIncrement) {
   }

   public ByteVector(int initialCapacity) {
      this(initialCapacity, 0);
   }

   public ByteVector() {
      this(10);
   }

   public void copyInto(byte[] anArray) {
      int i = this.elementCount;

      while (i-- > 0) {
         anArray[i] = this.elementData[i];
      }
   }

   public byte[] toArray() {
      byte[] newArray = new byte[this.elementCount];
      System.arraycopy(this.elementData, 0, newArray, 0, this.elementCount);
      return newArray;
   }

   public byte[] getArray() {
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

   public boolean contains(byte elem) {
      return this.indexOf(elem, 0) >= 0;
   }

   public int indexOf(byte elem) {
      return this.indexOf(elem, 0);
   }

   public int indexOf(byte elem, int index) {
      for (int i = index; i < this.elementCount; i++) {
         if (elem == this.elementData[i]) {
            return i;
         }
      }

      return -1;
   }

   public int lastIndexOf(byte elem) {
      return this.lastIndexOf(elem, this.elementCount - 1);
   }

   public int lastIndexOf(byte elem, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public byte elementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public byte firstElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[0];
      }
   }

   public byte lastElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[this.elementCount - 1];
      }
   }

   public void setElementAt(byte obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void removeElementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void insertElementAt(byte obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public void addElement(byte obj) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public boolean removeElement(byte obj) {
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
