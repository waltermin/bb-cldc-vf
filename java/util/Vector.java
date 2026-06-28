package java.util;

import net.rim.vm.Array;

public class Vector {
   protected Object[] elementData;
   protected int elementCount;
   protected int capacityIncrement;

   public Vector(int initialCapacity, int capacityIncrement) {
   }

   public Vector(int initialCapacity) {
      this(initialCapacity, 0);
   }

   public Vector() {
      this(4);
   }

   public synchronized void copyInto(Object[] anArray) {
      System.arraycopy(this.elementData, 0, anArray, 0, this.elementCount);
   }

   public synchronized void trimToSize() {
      if (this.elementCount < this.elementData.length) {
         Array.resize(this.elementData, this.elementCount);
      }
   }

   public synchronized void ensureCapacity(int minCapacity) {
      if (minCapacity > this.elementData.length) {
         this.ensureCapacityHelper(minCapacity, true);
      }
   }

   private void ensureCapacityHelper(int minCapacity, boolean forceNew) {
      int oldCapacity = this.elementData.length;
      int newCapacity = this.capacityIncrement > 0 ? oldCapacity + this.capacityIncrement : oldCapacity * 2;
      if (newCapacity < minCapacity) {
         newCapacity = minCapacity;
      }

      if (forceNew) {
         Object[] newData = new Object[newCapacity];
         System.arraycopy(this.elementData, 0, newData, 0, Math.min(newData.length, this.elementData.length));
         this.elementData = newData;
      } else {
         Array.resize(this.elementData, newCapacity);
      }
   }

   public synchronized void setSize(int newSize) {
      if (newSize > this.elementCount && newSize > this.elementData.length) {
         this.ensureCapacityHelper(newSize, false);
      } else {
         for (int i = newSize; i < this.elementCount; i++) {
            this.elementData[i] = null;
         }
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

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this));
   }

   public boolean contains(Object elem) {
      return this.indexOf(elem, 0) >= 0;
   }

   public int indexOf(Object elem) {
      return this.indexOf(elem, 0);
   }

   public synchronized int indexOf(Object elem, int index) {
      if (elem == null) {
         for (int i = index; i < this.elementCount; i++) {
            if (this.elementData[i] == null) {
               return i;
            }
         }
      } else {
         for (int i = index; i < this.elementCount; i++) {
            if (elem.equals(this.elementData[i])) {
               return i;
            }
         }
      }

      return -1;
   }

   public int lastIndexOf(Object elem) {
      return this.lastIndexOf(elem, this.elementCount - 1);
   }

   public synchronized int lastIndexOf(Object elem, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized Object elementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized Object firstElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[0];
      }
   }

   public synchronized Object lastElement() {
      if (this.elementCount == 0) {
         throw new Object();
      } else {
         return this.elementData[this.elementCount - 1];
      }
   }

   public synchronized void setElementAt(Object obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void removeElementAt(int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void insertElementAt(Object obj, int index) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void addElement(Object obj) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized boolean removeElement(Object obj) {
      int i = this.indexOf(obj);
      if (i >= 0) {
         this.removeElementAt(i);
         return true;
      } else {
         return false;
      }
   }

   public synchronized void removeAllElements() {
      for (int i = 0; i < this.elementCount; i++) {
         this.elementData[i] = null;
      }

      this.elementCount = 0;
   }

   @Override
   public synchronized String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
