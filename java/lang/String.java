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

   private String(Object array) {
      this.arrayToThisString(array);
   }

   private static final String arrayToString(Object array) {
      return new String(array);
   }

   public String() {
      this.arrayToThisString(new byte[0]);
   }

   public String(String other) {
      this.arrayToThisString(other.getCopyAsArray());
   }

   public String(char[] value) {
      this(value, 0, value.length);
   }

   public String(char[] value, int offset, int length) {
      if (offset < 0) {
         throw new StringIndexOutOfBoundsException(offset);
      }

      if (length < 0) {
         throw new StringIndexOutOfBoundsException(length);
      }

      if (offset > value.length - length) {
         throw new StringIndexOutOfBoundsException(offset + length);
      }

      this.arrayToThisString(newArrayWithPossibleDemotion(value, offset, length));
   }

   public String(byte[] value, int offset, int length, String enc) {
      if (offset < 0) {
         throw new StringIndexOutOfBoundsException(offset);
      }

      if (length < 0) {
         throw new StringIndexOutOfBoundsException(length);
      }

      if (offset > value.length - length) {
         throw new StringIndexOutOfBoundsException(offset + length);
      }

      this.arrayToThisString(Helper.byteToCharArray(value, offset, length, enc));
   }

   public String(byte[] bytes, String enc) {
      this(bytes, 0, bytes.length, enc);
   }

   public String(byte[] bytes, int off, int len) {
      if (off >= 0 && off <= bytes.length && len >= 0 && len <= bytes.length && off + len <= bytes.length) {
         byte[] newValue = new byte[len];
         System.arraycopy(bytes, off, newValue, 0, len);
         this.arrayToThisString(newValue);
      } else {
         throw new StringIndexOutOfBoundsException(len);
      }
   }

   public String(byte[] bytes) {
      this(bytes, 0, bytes.length);
   }

   public String(StringBuffer buffer) {
   }

   public final native void getChars(int var1, int var2, char[] var3, int var4);

   public final byte[] getBytes(String enc) {
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

   public final boolean startsWith(String prefix) {
      return this.startsWith(prefix, 0);
   }

   public final boolean endsWith(String suffix) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public final native int hashCode();

   public final int indexOf(int ch) {
      return this.indexOf(ch, 0);
   }

   public final int indexOf(int ch, int fromIndex) {
      return StringUtilities.indexOf(this, ch, fromIndex, Integer.MAX_VALUE);
   }

   public final int lastIndexOf(int ch) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final native int lastIndexOf(int var1, int var2);

   public final int indexOf(String str) {
      return this.indexOf(str, 0);
   }

   public final native int indexOf(String var1, int var2);

   public final String substring(int beginIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String substring(int beginIndex, int endIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private final native void concatBytes(byte[] var1, String var2);

   public final String concat(String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final String replace(char oldChar, char newChar) {
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

   public static final String valueOf(Object obj) {
      return obj == null ? "null" : obj.toString();
   }

   public static final String valueOf(char[] data) {
      return new String(data);
   }

   public static final String valueOf(char[] data, int offset, int count) {
      return new String(data, offset, count);
   }

   public static final String valueOf(boolean b) {
      return b ? "true" : "false";
   }

   public static final String valueOf(char c) {
      if (c <= 255) {
         byte[] data = new byte[]{(byte)c};
         return arrayToString(data);
      } else {
         char[] data = new char[]{c};
         return arrayToString(data);
      }
   }

   public static final String valueOf(int i) {
      return Integer.toString(i, 10);
   }

   public static final String valueOf(long l) {
      return Long.toString(l, 10);
   }

   final boolean copyInto(Object dst, int dstOffset) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final boolean equalsIgnoreCase(String anotherString) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String valueOf(float f) {
      return Float.toString(f);
   }

   public static final String valueOf(double d) {
      return Double.toString(d);
   }

   public final String intern() {
      return Memory.stringIntern(this);
   }
}
