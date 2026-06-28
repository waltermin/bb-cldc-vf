package java.lang;

import net.rim.vm.Array;

public final class StringBuffer {
   private Object value;
   private int count;

   public StringBuffer() {
      this(16);
   }

   public StringBuffer(int length) {
      this.value = new byte[length];
   }

   public StringBuffer(String str) {
   }

   public final int length() {
      return this.count;
   }

   public final int capacity() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void ensureCapacity(int minimumCapacity) {
      if (minimumCapacity > this.capacity()) {
         this.expandCapacity(minimumCapacity);
      }
   }

   private final void expandCapacity(int minimumCapacity) {
      int oldCapacity = this.capacity();
      int newCapacity = (oldCapacity + 1) * 2;
      if (minimumCapacity > newCapacity) {
         newCapacity = minimumCapacity;
      }

      if (newCapacity < 4096) {
         Array.resize(this.value, newCapacity);
      } else {
         int incSize = minimumCapacity - oldCapacity;
         if (incSize <= 0) {
            incSize = 1;
         }

         Array.extend(this.value, incSize);
      }
   }

   public final synchronized void setLength(int newLength) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized char charAt(int index) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final native void promote();

   public final synchronized void setCharAt(int index, char ch) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer append(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer append(String str) {
      if (str == null) {
         str = String.valueOf(str);
      }

      this.doAppend(str);
      return this;
   }

   private final native void doAppend(String var1);

   public final StringBuffer append(char[] str) {
      return this.append(str, 0, str.length);
   }

   public final synchronized StringBuffer append(char[] str, int offset, int len) {
      int newcount = this.count + len;
      this.promote();
      if (newcount > this.capacity()) {
         this.expandCapacity(newcount);
      }

      System.arraycopy(str, offset, this.value, this.count, len);
      this.count = newcount;
      return this;
   }

   public final StringBuffer append(boolean b) {
      return this.append(String.valueOf(b));
   }

   public final synchronized StringBuffer append(char c) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final StringBuffer append(int i) {
      return this.append((long)i);
   }

   public final synchronized StringBuffer append(long l) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer delete(int start, int end) {
      if (start < 0) {
         throw new StringIndexOutOfBoundsException(start);
      }

      if (end > this.count) {
         end = this.count;
      }

      if (start > end) {
         throw new StringIndexOutOfBoundsException();
      }

      int len = end - start;
      if (len > 0) {
         System.arraycopy(this.value, start + len, this.value, start, this.count - end);
         this.count -= len;
      }

      return this;
   }

   public final synchronized StringBuffer deleteCharAt(int index) {
      if (index >= 0 && index < this.count) {
         System.arraycopy(this.value, index + 1, this.value, index, this.count - index - 1);
         this.count--;
         return this;
      } else {
         throw new StringIndexOutOfBoundsException();
      }
   }

   public final synchronized StringBuffer insert(int offset, Object obj) {
      return this.insert(offset, String.valueOf(obj));
   }

   public final synchronized StringBuffer insert(int offset, String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final synchronized StringBuffer insert(int offset, char[] str) {
      if (offset >= 0 && offset <= this.count) {
         int len = str.length;
         int newcount = this.count + len;
         if (newcount > this.capacity()) {
            this.expandCapacity(newcount);
         }

         System.arraycopy(this.value, offset, this.value, offset + len, this.count - offset);
         this.count = newcount;
         this.promote();
         System.arraycopy(str, 0, this.value, offset, len);
         return this;
      } else {
         throw new StringIndexOutOfBoundsException();
      }
   }

   public final StringBuffer insert(int offset, boolean b) {
      return this.insert(offset, String.valueOf(b));
   }

   public final synchronized StringBuffer insert(int offset, char c) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final StringBuffer insert(int offset, int i) {
      return this.insert(offset, String.valueOf(i));
   }

   public final StringBuffer insert(int offset, long l) {
      return this.insert(offset, String.valueOf(l));
   }

   public final native StringBuffer reverse();

   @Override
   public final String toString() {
      return this.count == 0 ? "" : new String(this);
   }

   final Object getValue() {
      return this.value;
   }

   static final native int formatNumeric(byte[] var0, int var1, int var2, long var3);

   static final native int formatNumeric(char[] var0, int var1, int var2, long var3);

   public final StringBuffer append(float f) {
      return this.append(String.valueOf(f));
   }

   public final StringBuffer append(double d) {
      return this.append(String.valueOf(d));
   }

   public final StringBuffer insert(int offset, float f) {
      return this.insert(offset, String.valueOf(f));
   }

   public final StringBuffer insert(int offset, double d) {
      return this.insert(offset, String.valueOf(d));
   }
}
