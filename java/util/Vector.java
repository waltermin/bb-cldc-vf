package java.util;

import net.rim.vm.Array;

public class Vector {
   protected Object[] elementData;
   protected int elementCount;
   protected int capacityIncrement;

   public Vector(int var1, int var2) {
   }

   public Vector(int var1) {
      this(var1, 0);
   }

   public Vector() {
      this(4);
   }

   public synchronized void copyInto(Object[] var1) {
      System.arraycopy(this.elementData, 0, var1, 0, this.elementCount);
   }

   public synchronized void trimToSize() {
      if (this.elementCount < this.elementData.length) {
         Array.resize(this.elementData, this.elementCount);
      }
   }

   public synchronized void ensureCapacity(int var1) {
      if (var1 > this.elementData.length) {
         this.ensureCapacityHelper(var1, true);
      }
   }

   private void ensureCapacityHelper(int var1, boolean var2) {
      int var3 = this.elementData.length;
      int var4 = this.capacityIncrement > 0 ? var3 + this.capacityIncrement : var3 * 2;
      if (var4 < var1) {
         var4 = var1;
      }

      if (var2) {
         Object[] var5 = new Object[var4];
         System.arraycopy(this.elementData, 0, var5, 0, Math.min(var5.length, this.elementData.length));
         this.elementData = var5;
      } else {
         Array.resize(this.elementData, var4);
      }
   }

   public synchronized void setSize(int var1) {
      if (var1 > this.elementCount && var1 > this.elementData.length) {
         this.ensureCapacityHelper(var1, false);
      } else {
         for (int var2 = var1; var2 < this.elementCount; var2++) {
            this.elementData[var2] = null;
         }
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

   public synchronized Enumeration elements() {
      return (Enumeration)(new Object(this));
   }

   public boolean contains(Object var1) {
      return this.indexOf(var1, 0) >= 0;
   }

   public int indexOf(Object var1) {
      return this.indexOf(var1, 0);
   }

   public synchronized int indexOf(Object var1, int var2) {
      if (var1 == null) {
         for (int var3 = var2; var3 < this.elementCount; var3++) {
            if (this.elementData[var3] == null) {
               return var3;
            }
         }
      } else {
         for (int var4 = var2; var4 < this.elementCount; var4++) {
            if (var1.equals(this.elementData[var4])) {
               return var4;
            }
         }
      }

      return -1;
   }

   public int lastIndexOf(Object var1) {
      return this.lastIndexOf(var1, this.elementCount - 1);
   }

   public synchronized int lastIndexOf(Object var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized Object elementAt(int var1) {
      throw new RuntimeException("cod2jar: exception table");
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

   public synchronized void setElementAt(Object var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void removeElementAt(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void insertElementAt(Object var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public synchronized void addElement(Object var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public synchronized boolean removeElement(Object var1) {
      int var2 = this.indexOf(var1);
      if (var2 >= 0) {
         this.removeElementAt(var2);
         return true;
      } else {
         return false;
      }
   }

   public synchronized void removeAllElements() {
      for (int var1 = 0; var1 < this.elementCount; var1++) {
         this.elementData[var1] = null;
      }

      this.elementCount = 0;
   }

   @Override
   public synchronized String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
