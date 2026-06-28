package net.rim.device.api.util;

import java.io.DataOutput;
import java.io.UnsupportedEncodingException;
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

   public static final int hashCodeIgnoreCase(String str) {
      return hashCode(str, 0, Integer.MAX_VALUE, true);
   }

   public static final native int hashCode(String var0, int var1, int var2, boolean var3);

   public static final int computeReverseLookupHashCodeString(String string) {
      return computeReverseLookupHashCodeString(string, false);
   }

   public static final native int computeReverseLookupHashCodeString(String var0, boolean var1);

   public static final int computeReverseLookupHashCodeBytes(byte[] bytes, int start, int length) {
      return computeReverseLookupHashCodeBytes(bytes, start, length, false);
   }

   public static final native int computeReverseLookupHashCodeBytes(byte[] var0, int var1, int var2, boolean var3);

   public static final native int codeBOM(String var0, int var1, int var2, byte[] var3, int var4);

   public static final String decodeBOM(byte[] data, int offset, int len) {
      return decodeBOM(data, offset, len, false);
   }

   public static final String decodeBOM(byte[] data, int offset, int len, boolean stripNullLatin1) {
      String returnedString;
      if (len >= 2 && data[offset] == -2 && data[offset + 1] == -1) {
         try {
            returnedString = new String(data, offset + 2, len - 2, "utf-16be");
         } catch (UnsupportedEncodingException e) {
            returnedString = null;
         }
      } else if (len >= 3 && data[offset] == -17 && data[offset + 1] == -69 && data[offset + 2] == -65) {
         try {
            returnedString = new String(data, offset + 3, len - 3, "utf-8");
         } catch (UnsupportedEncodingException e) {
            returnedString = null;
         }
      } else {
         int end = offset + len;
         int byteEncoded = strConversionRequired(data, offset, end, '\u0080', ' ');
         if (stripNullLatin1 && len > 0 && data[offset + len - 1] == 0) {
            len--;
         }

         switch (byteEncoded) {
            case -1:
               char[] inputChars = new char[len];
               int out = 0;
               end = offset + len;
               char prevc = 0;
               char thisc = '\u0000';

               for (int lv = offset; lv < end; lv++) {
                  char c = (char)(data[lv] & 0xFF);
                  thisc = c;
                  switch (c) {
                     case '\n':
                        if (prevc == '\r') {
                           char var24 = false;
                           continue;
                        }
                        break;
                     case '\r':
                        c = '\n';
                        break;
                     case '\u0080':
                        c = 8364;
                        break;
                     case '\u0082':
                        c = 8218;
                        break;
                     case '\u0083':
                        c = 402;
                        break;
                     case '\u0084':
                        c = 8222;
                        break;
                     case '\u0085':
                        c = 8230;
                        break;
                     case '\u0086':
                        c = 8224;
                        break;
                     case '\u0087':
                        c = 8225;
                        break;
                     case '\u0088':
                        c = 710;
                        break;
                     case '\u0089':
                        c = 8240;
                        break;
                     case '\u008a':
                        c = 352;
                        break;
                     case '\u008b':
                        c = 8249;
                        break;
                     case '\u008c':
                        c = 338;
                        break;
                     case '\u008e':
                        c = 381;
                        break;
                     case '\u0091':
                        c = 8216;
                        break;
                     case '\u0092':
                        c = 8217;
                        break;
                     case '\u0093':
                        c = 8220;
                        break;
                     case '\u0094':
                        c = 8221;
                        break;
                     case '\u0095':
                        c = 8226;
                        break;
                     case '\u0096':
                        c = 8211;
                        break;
                     case '\u0097':
                        c = 8212;
                        break;
                     case '\u0098':
                        c = 732;
                        break;
                     case '\u0099':
                        c = 8482;
                        break;
                     case '\u009a':
                        c = 353;
                        break;
                     case '\u009b':
                        c = 8250;
                        break;
                     case '\u009c':
                        c = 339;
                        break;
                     case '\u009e':
                        c = 382;
                        break;
                     case '\u009f':
                        c = 376;
                  }

                  prevc = thisc;
                  inputChars[out] = c;
                  out++;
               }

               returnedString = new String(inputChars, 0, out);
               break;
            case 0:
            default:
               returnedString = new String(data, offset, len);
               break;
            case 1:
               byte[] inputChars = new byte[len];
               int out = 0;
               end = offset + len;
               byte prevc = 0;
               byte thisc = 0;

               for (int lv = offset; lv < end; lv++) {
                  byte c = (byte)(data[lv] & 0xFF);
                  thisc = c;
                  switch (c) {
                     case 10:
                        if (prevc == 13) {
                           byte var21 = false;
                           continue;
                        }
                        break;
                     case 13:
                        c = 10;
                  }

                  prevc = thisc;
                  inputChars[out] = c;
                  out++;
               }

               returnedString = new String(inputChars, 0, out);
         }
      }

      return returnedString;
   }

   public static final native int getCharacterSize(String var0);

   public static final native boolean isASCII(String var0);

   public static final String intToString(int code) {
      if (code == -1) {
         return null;
      }

      StringBuffer buffer = new StringBuffer();

      for (int lv = 24; lv >= 0; lv -= 8) {
         char ch = (char)(code >> lv & 0xFF);
         if (ch == 0) {
            if (code != 0) {
               throw new IllegalArgumentException();
            }
            break;
         }

         buffer.append(ch);
      }

      return buffer.toString();
   }

   public static final boolean startsWithIgnoreCase(String string, String prefix) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean startsWithIgnoreCase(String string, String prefix, int locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean endsWithIgnoreCase(String string, String suffix, int locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean startsWithIgnoreCaseAndAccents(String string, String prefix) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String removeLineBreaksInString(String string) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String[] stringToWords(String string) {
      String[] theWords = new String[10];
      int count = stringToWords(string, theWords, 0);
      Array.resize(theWords, count);
      return theWords;
   }

   public static final String[] stringToKeywords(String string) {
      String[] theKeywords = new String[10];
      int count = stringToKeywords(string, theKeywords, 0);
      Array.resize(theKeywords, count);
      return theKeywords;
   }

   public static final int stringToWords(String stringContainingWords, int[] indexResults, int resultOffset) {
      return stringToWordsOrKeywords(stringContainingWords, indexResults, null, resultOffset, false);
   }

   public static final int stringToKeywords(String stringContainingKeywords, int[] startOffsets, int resultOffset) {
      return stringToWordsOrKeywords(stringContainingKeywords, startOffsets, null, resultOffset, true);
   }

   public static final native int stringToWordsOrKeywords(String var0, int[] var1, int[] var2, int var3, boolean var4);

   public static final int stringToWords(String string, String[] wordArray, int index) {
      return stringToWordsOrKeywords(string, wordArray, index, false);
   }

   public static final int stringToKeywords(String string, String[] wordArray, int index) {
      return stringToWordsOrKeywords(string, wordArray, index, true);
   }

   private static final int stringToWordsOrKeywords(String string, String[] wordArray, int index, boolean keywords) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean strEqualIgnoreCase(String s1, String s2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean strEqualIgnoreCase(String s1, String s2, int locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean strEqual(String s1, String s2) {
      if (s1 == s2) {
         return true;
      } else {
         return s1 != null && s2 != null ? s1.equals(s2) : false;
      }
   }

   public static final String cStr2String(byte[] b, int start, int len) {
      int i = len;

      while (i > 0 && b[i + start - 1] == 0) {
         i--;
      }

      return new String(b, start, i);
   }

   public static final int compareObjectToStringIgnoreCase(Object o1, Object o2) {
      if (o1 == o2) {
         return 0;
      }

      if (o1 == null && o2 != null) {
         return -1;
      }

      if (o2 == null) {
         return 1;
      }

      String s1 = o1.toString();
      String s2 = o2.toString();
      return compareToIgnoreCase(s1, s2);
   }

   public static final int compareObjectToStringIgnoreCase(Object o1, Object o2, int locale) {
      if (o1 == o2) {
         return 0;
      }

      if (o1 == null && o2 != null) {
         return -1;
      }

      if (o2 == null) {
         return 1;
      }

      String s1 = o1.toString();
      String s2 = o2.toString();
      return compareToIgnoreCase(s1, s2, locale);
   }

   public static final int indexOf(String string, char ch, int fromIndex, int toIndex) {
      return indexOf(string, ch & 65535, fromIndex, toIndex);
   }

   public static final native int indexOf(String var0, int var1, int var2, int var3);

   public static final String removeChars(String src, String remove) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final long stringHashToLong(String key) {
      SHA1Digest digest = new SHA1Digest();
      digest.update(key.getBytes());
      byte[] hashValBytes = digest.getDigest();
      long hashValLong = 0;

      for (int i = 0; i < 8; i++) {
         hashValLong |= (hashValBytes[i] & 255) << 8 * i;
      }

      return hashValLong;
   }

   public static final int stringToInt(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final StringBuffer append(StringBuffer strBuf, String str, int offset, int length) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final native void doAppend(StringBuffer var0, String var1, int var2, int var3);

   public static final StringBuffer append(StringBuffer strBuf, byte[] buffer, int offset, int length) {
      synchronized (strBuf) {
         if (offset >= 0 && length >= 0 && offset + length <= buffer.length) {
            doAppend(strBuf, buffer, offset, length);
            return strBuf;
         } else {
            throw new ArrayIndexOutOfBoundsException();
         }
      }
   }

   private static final native void doAppend(StringBuffer var0, byte[] var1, int var2, int var3);

   public static final StringBuffer append(StringBuffer strBuf, StringBuffer other) {
      synchronized (other) {
         return append(strBuf, other, 0, other.length());
      }
   }

   public static final StringBuffer append(StringBuffer strBuf, StringBuffer other, int offset, int length) {
      synchronized (other) {
         synchronized (strBuf) {
            if (offset < 0 || length < 0 || offset + length > other.length()) {
               throw new ArrayIndexOutOfBoundsException();
            }

            doAppend(strBuf, other, offset, length);
         }

         return strBuf;
      }
   }

   private static final native void doAppend(StringBuffer var0, StringBuffer var1, int var2, int var3);

   private static final native int strConversionRequired(byte[] var0, int var1, int var2, char var3, char var4);

   public static final int writeUTF(String str, DataOutput out) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final native boolean regionMatches(String var0, boolean var1, int var2, String var3, int var4, int var5, int var6);

   public static final native boolean isHan(String var0, int var1, int var2);
}
