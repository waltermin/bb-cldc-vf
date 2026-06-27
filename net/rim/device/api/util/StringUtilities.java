package net.rim.device.api.util;

import java.io.DataOutput;
import net.rim.device.api.crypto.SHA1Digest;
import net.rim.device.internal.i18n.CollatorImpl;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

public final class StringUtilities {
   private static CollatorImpl _collator;
   private static WeakReference _scratchWR;

   private StringUtilities() {
   }

   public static final native void convertToOriginal(StringBuffer var0, int var1, int var2);

   public static final native int compareToIgnoreCase(String var0, String var1);

   public static final native int compareToIgnoreCase(String var0, String var1, int var2);

   public static final native String toLowerCase(String var0, int var1);

   public static final native String toUpperCase(String var0, int var1);

   public static final native int computeHashCode(StringBuffer var0);

   public static final int hashCodeIgnoreCase(String var0) {
      return hashCode(var0, 0, Integer.MAX_VALUE, true);
   }

   public static final native int hashCode(String var0, int var1, int var2, boolean var3);

   public static final int computeReverseLookupHashCodeString(String var0) {
      return computeReverseLookupHashCodeString(var0, false);
   }

   public static final native int computeReverseLookupHashCodeString(String var0, boolean var1);

   public static final int computeReverseLookupHashCodeBytes(byte[] var0, int var1, int var2) {
      return computeReverseLookupHashCodeBytes(var0, var1, var2, false);
   }

   public static final native int computeReverseLookupHashCodeBytes(byte[] var0, int var1, int var2, boolean var3);

   public static final native int codeBOM(String var0, int var1, int var2, byte[] var3, int var4);

   public static final String decodeBOM(byte[] var0, int var1, int var2) {
      return decodeBOM(var0, var1, var2, false);
   }

   public static final String decodeBOM(byte[] var0, int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final native int getCharacterSize(String var0);

   public static final native boolean isASCII(String var0);

   public static final String intToString(int var0) {
      if (var0 == -1) {
         return null;
      }

      Object var1 = new Object();

      for (byte var2 = 24; var2 >= 0; var2 -= 8) {
         char var3 = (char)(var0 >> var2 & 0xFF);
         if (var3 == 0) {
            if (var0 != 0) {
               throw new Object();
            }
            break;
         }

         ((StringBuffer)var1).append(var3);
      }

      return ((StringBuffer)var1).toString();
   }

   public static final boolean startsWithIgnoreCase(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean startsWithIgnoreCase(String var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean endsWithIgnoreCase(String var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean startsWithIgnoreCaseAndAccents(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String removeLineBreaksInString(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String[] stringToWords(String var0) {
      String[] var1 = new String[10];
      int var2 = stringToWords(var0, var1, 0);
      Array.resize(var1, var2);
      return var1;
   }

   public static final String[] stringToKeywords(String var0) {
      String[] var1 = new String[10];
      int var2 = stringToKeywords(var0, var1, 0);
      Array.resize(var1, var2);
      return var1;
   }

   public static final int stringToWords(String var0, int[] var1, int var2) {
      return stringToWordsOrKeywords(var0, var1, null, var2, false);
   }

   public static final int stringToKeywords(String var0, int[] var1, int var2) {
      return stringToWordsOrKeywords(var0, var1, null, var2, true);
   }

   public static final native int stringToWordsOrKeywords(String var0, int[] var1, int[] var2, int var3, boolean var4);

   public static final int stringToWords(String var0, String[] var1, int var2) {
      return stringToWordsOrKeywords(var0, var1, var2, false);
   }

   public static final int stringToKeywords(String var0, String[] var1, int var2) {
      return stringToWordsOrKeywords(var0, var1, var2, true);
   }

   private static final int stringToWordsOrKeywords(String var0, String[] var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean strEqualIgnoreCase(String var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean strEqualIgnoreCase(String var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean strEqual(String var0, String var1) {
      if (var0 == var1) {
         return true;
      } else {
         return var0 != null && var1 != null ? var0.equals(var1) : false;
      }
   }

   public static final String cStr2String(byte[] var0, int var1, int var2) {
      int var3 = var2;

      while (var3 > 0 && var0[var3 + var1 - 1] == 0) {
         var3--;
      }

      return (String)(new Object(var0, var1, var3));
   }

   public static final int compareObjectToStringIgnoreCase(Object var0, Object var1) {
      if (var0 == var1) {
         return 0;
      }

      if (var0 == null && var1 != null) {
         return -1;
      }

      if (var1 == null) {
         return 1;
      }

      String var2 = var0.toString();
      String var3 = var1.toString();
      return compareToIgnoreCase(var2, var3);
   }

   public static final int compareObjectToStringIgnoreCase(Object var0, Object var1, int var2) {
      if (var0 == var1) {
         return 0;
      }

      if (var0 == null && var1 != null) {
         return -1;
      }

      if (var1 == null) {
         return 1;
      }

      String var3 = var0.toString();
      String var4 = var1.toString();
      return compareToIgnoreCase(var3, var4, var2);
   }

   public static final int indexOf(String var0, char var1, int var2, int var3) {
      return indexOf(var0, var1 & 65535, var2, var3);
   }

   public static final native int indexOf(String var0, int var1, int var2, int var3);

   public static final String removeChars(String var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long stringHashToLong(String var0) {
      Object var1 = new Object();
      ((SHA1Digest)var1).update(var0.getBytes());
      byte[] var2 = ((SHA1Digest)var1).getDigest();
      long var3 = 0;

      for (int var5 = 0; var5 < 8; var5++) {
         var3 |= (var2[var5] & 255) << 8 * var5;
      }

      return var3;
   }

   public static final int stringToInt(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final StringBuffer append(StringBuffer var0, String var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native void doAppend(StringBuffer var0, String var1, int var2, int var3);

   public static final StringBuffer append(StringBuffer var0, byte[] var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native void doAppend(StringBuffer var0, byte[] var1, int var2, int var3);

   public static final StringBuffer append(StringBuffer var0, StringBuffer var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final StringBuffer append(StringBuffer var0, StringBuffer var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final native void doAppend(StringBuffer var0, StringBuffer var1, int var2, int var3);

   private static final native int strConversionRequired(byte[] var0, int var1, int var2, char var3, char var4);

   public static final int writeUTF(String var0, DataOutput var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final native boolean regionMatches(String var0, boolean var1, int var2, String var3, int var4, int var5, int var6);

   public static final native boolean isHan(String var0, int var1, int var2);
}
