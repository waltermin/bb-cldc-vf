package java.lang;

import net.rim.vm.Array;

public final class StringBuffer {
   private Object value;
   private int count;

   public StringBuffer() {
      this(16);
   }

   public StringBuffer(int var1) {
      this.value = new byte[var1];
   }

   public StringBuffer(String var1) {
   }

   public final int length() {
      return this.count;
   }

   public final int capacity() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void ensureCapacity(int var1) {
      if (var1 > this.capacity()) {
         this.expandCapacity(var1);
      }
   }

   private final void expandCapacity(int var1) {
      int var2 = this.capacity();
      int var3 = (var2 + 1) * 2;
      if (var1 > var3) {
         var3 = var1;
      }

      if (var3 < 4096) {
         Array.resize(this.value, var3);
      } else {
         int var4 = var1 - var2;
         if (var4 <= 0) {
            var4 = 1;
         }

         Array.extend(this.value, var4);
      }
   }

   public final synchronized void setLength(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized char charAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void getChars(int var1, int var2, char[] var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final native void promote();

   public final synchronized void setCharAt(int var1, char var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer append(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer append(String var1) {
      if (var1 == null) {
         var1 = String.valueOf(var1);
      }

      this.doAppend(var1);
      return this;
   }

   private final native void doAppend(String var1);

   public final StringBuffer append(char[] var1) {
      return this.append(var1, 0, var1.length);
   }

   public final synchronized StringBuffer append(char[] var1, int var2, int var3) {
      int var4 = this.count + var3;
      this.promote();
      if (var4 > this.capacity()) {
         this.expandCapacity(var4);
      }

      System.arraycopy(var1, var2, this.value, this.count, var3);
      this.count = var4;
      return this;
   }

   public final StringBuffer append(boolean var1) {
      return this.append(String.valueOf(var1));
   }

   public final synchronized StringBuffer append(char var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final StringBuffer append(int var1) {
      return this.append((long)var1);
   }

   public final synchronized StringBuffer append(long var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized StringBuffer delete(int var1, int var2) {
      if (var1 < 0) {
         throw new StringIndexOutOfBoundsException(var1);
      }

      if (var2 > this.count) {
         var2 = this.count;
      }

      if (var1 > var2) {
         throw new StringIndexOutOfBoundsException();
      }

      int var3 = var2 - var1;
      if (var3 > 0) {
         System.arraycopy(this.value, var1 + var3, this.value, var1, this.count - var2);
         this.count -= var3;
      }

      return this;
   }

   public final synchronized StringBuffer deleteCharAt(int var1) {
      if (var1 >= 0 && var1 < this.count) {
         System.arraycopy(this.value, var1 + 1, this.value, var1, this.count - var1 - 1);
         this.count--;
         return this;
      } else {
         throw new StringIndexOutOfBoundsException();
      }
   }

   public final synchronized StringBuffer insert(int var1, Object var2) {
      return this.insert(var1, String.valueOf(var2));
   }

   public final synchronized StringBuffer insert(int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final synchronized StringBuffer insert(int var1, char[] var2) {
      if (var1 >= 0 && var1 <= this.count) {
         int var3 = var2.length;
         int var4 = this.count + var3;
         if (var4 > this.capacity()) {
            this.expandCapacity(var4);
         }

         System.arraycopy(this.value, var1, this.value, var1 + var3, this.count - var1);
         this.count = var4;
         this.promote();
         System.arraycopy(var2, 0, this.value, var1, var3);
         return this;
      } else {
         throw new StringIndexOutOfBoundsException();
      }
   }

   public final StringBuffer insert(int var1, boolean var2) {
      return this.insert(var1, String.valueOf(var2));
   }

   public final synchronized StringBuffer insert(int var1, char var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final StringBuffer insert(int var1, int var2) {
      return this.insert(var1, String.valueOf(var2));
   }

   public final StringBuffer insert(int var1, long var2) {
      return this.insert(var1, String.valueOf(var2));
   }

   public final native StringBuffer reverse();

   @Override
   public final String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final Object getValue() {
      return this.value;
   }

   static final native int formatNumeric(byte[] var0, int var1, int var2, long var3);

   static final native int formatNumeric(char[] var0, int var1, int var2, long var3);

   public final StringBuffer append(float var1) {
      return this.append(String.valueOf(var1));
   }

   public final StringBuffer append(double var1) {
      return this.append(String.valueOf(var1));
   }

   public final StringBuffer insert(int var1, float var2) {
      return this.insert(var1, String.valueOf(var2));
   }

   public final StringBuffer insert(int var1, double var2) {
      return this.insert(var1, String.valueOf(var2));
   }
}
