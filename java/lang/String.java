package java.lang;

import com.sun.cldc.i18n.Helper;
import net.rim.device.api.util.StringUtilities;
import net.rim.vm.Memory;

public final class String {
   private final native boolean usesBytes();

   private final native void arrayToThisString(Object var1);

   private final native Object getCopyAsArray();

   private static final native Object newArrayWithPossibleDemotion(char[] var0, int var1, int var2);

   public final native int length();

   public final native char charAt(int var1);

   private final native Object getSubArray(int var1, int var2);

   private String(Object var1) {
      this.arrayToThisString(var1);
   }

   private static final String arrayToString(Object var0) {
      return new String(var0);
   }

   public String() {
      this.arrayToThisString(new byte[0]);
   }

   public String(String var1) {
      this.arrayToThisString(var1.getCopyAsArray());
   }

   public String(char[] var1) {
      this(var1, 0, var1.length);
   }

   public String(char[] var1, int var2, int var3) {
      if (var2 < 0) {
         throw new Object(var2);
      }

      if (var3 < 0) {
         throw new Object(var3);
      }

      if (var2 > var1.length - var3) {
         throw new Object(var2 + var3);
      }

      this.arrayToThisString(newArrayWithPossibleDemotion(var1, var2, var3));
   }

   public String(byte[] var1, int var2, int var3, String var4) {
      if (var2 < 0) {
         throw new Object(var2);
      }

      if (var3 < 0) {
         throw new Object(var3);
      }

      if (var2 > var1.length - var3) {
         throw new Object(var2 + var3);
      }

      this.arrayToThisString(Helper.byteToCharArray(var1, var2, var3, var4));
   }

   public String(byte[] var1, String var2) {
      this(var1, 0, var1.length, var2);
   }

   public String(byte[] var1, int var2, int var3) {
      if (var2 >= 0 && var2 <= var1.length && var3 >= 0 && var3 <= var1.length && var2 + var3 <= var1.length) {
         byte[] var4 = new byte[var3];
         System.arraycopy(var1, var2, var4, 0, var3);
         this.arrayToThisString(var4);
      } else {
         throw new Object(var3);
      }
   }

   public String(byte[] var1) {
      this(var1, 0, var1.length);
   }

   public String(StringBuffer var1) {
   }

   public final native void getChars(int var1, int var2, char[] var3, int var4);

   public final byte[] getBytes(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final byte[] getBytes() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final native void getBytesChar(byte[] var1, int var2);

   @Override
   public final native boolean equals(Object var1);

   public final native int compareTo(String var1);

   public final native boolean regionMatches(boolean var1, int var2, String var3, int var4, int var5);

   public final native boolean startsWith(String var1, int var2);

   public final boolean startsWith(String var1) {
      return this.startsWith(var1, 0);
   }

   public final boolean endsWith(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final native int hashCode();

   public final int indexOf(int var1) {
      return this.indexOf(var1, 0);
   }

   public final int indexOf(int var1, int var2) {
      return StringUtilities.indexOf(this, var1, var2, Integer.MAX_VALUE);
   }

   public final int lastIndexOf(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final native int lastIndexOf(int var1, int var2);

   public final int indexOf(String var1) {
      return this.indexOf(var1, 0);
   }

   public final native int indexOf(String var1, int var2);

   public final String substring(int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String substring(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final native void concatBytes(byte[] var1, String var2);

   public final String concat(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String replace(char var1, char var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final native void doReplace(Object var1, char var2, char var3);

   public final native String toLowerCase();

   public final native String toUpperCase();

   public final String trim() {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final String toString() {
      return this;
   }

   public final char[] toCharArray() {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String valueOf(Object var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String valueOf(char[] var0) {
      return new String(var0);
   }

   public static final String valueOf(char[] var0, int var1, int var2) {
      return new String(var0, var1, var2);
   }

   public static final String valueOf(boolean var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final String valueOf(char var0) {
      if (var0 <= 255) {
         byte[] var2 = new byte[]{(byte)var0};
         return arrayToString(var2);
      } else {
         char[] var1 = new char[]{var0};
         return arrayToString(var1);
      }
   }

   public static final String valueOf(int var0) {
      return Integer.toString(var0, 10);
   }

   public static final String valueOf(long var0) {
      return Long.toString(var0, 10);
   }

   final boolean copyInto(Object var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final boolean equalsIgnoreCase(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String valueOf(float var0) {
      return Float.toString(var0);
   }

   public static final String valueOf(double var0) {
      return Double.toString(var0);
   }

   public final String intern() {
      return Memory.stringIntern(this);
   }
}
