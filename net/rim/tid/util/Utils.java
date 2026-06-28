package net.rim.tid.util;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.resources.Resource;
import net.rim.device.resources.Resource$Internal;
import net.rim.vm.Array;

public class Utils {
   private static StringBuffer _resourceIdCompositionTempBuffer;

   public static native int hashCode(char[] var0, int var1, int var2, boolean var3);

   public static int bytes2Int(byte byte1, byte byte2) {
      return bytes2Int(byte1 & 0xFF, byte2 & 0xFF);
   }

   public static int bytes2Int(int byte1, int byte2) {
      int result = 0;
      result |= byte1;
      result <<= 8;
      return result | byte2;
   }

   public static int bytes2Int(byte byte1, byte byte2, byte byte3, byte byte4) {
      return bytes2Int(byte1 & 0xFF, byte2 & 0xFF, byte3 & 0xFF, byte4 & 0xFF);
   }

   public static int bytes2Int(int byte1, int byte2, int byte3, int byte4) {
      int result = 0;
      result |= byte1;
      result <<= 8;
      result |= byte2;
      result <<= 8;
      result |= byte3;
      result <<= 8;
      return result | byte4;
   }

   public static int bytes2Int(byte byte1, byte byte2, byte byte3) {
      return bytes2Int(byte1 & 0xFF, byte2 & 0xFF, byte3 & 0xFF);
   }

   public static int bytes2Int(int byte1, int byte2, int byte3) {
      int result = 0;
      result |= byte1;
      result <<= 8;
      result |= byte2;
      result <<= 8;
      return result | byte3;
   }

   public static int findShift(int aAttribMask) {
      int shift = 0;

      while ((aAttribMask & 1) == 0) {
         shift++;
         aAttribMask >>>= 1;
      }

      return shift;
   }

   public static String[] getInputLocalesDisplayNames(Locale[] locales) {
      String[] res = new String[locales.length];

      for (int i = locales.length - 1; i >= 0; i--) {
         res[i] = getDisplayStringFor(locales[i]);
      }

      return res;
   }

   public static void filterRootInputLocales(Locale[] locales) {
      for (int i = 0; i < locales.length; i++) {
         int code = locales[i].getCode();
         String variant = locales[i].getVariant();

         for (int j = 0; j < locales.length; j++) {
            if (j != i
               && (locales[j].getCode() & -65536) == (code & -65536)
               && variant.equals(locales[j].getVariant())
               && (code & 65535) != (locales[j].getCode() & 65535)) {
               if ((code & 65535) == 0) {
                  if (code != 1886650368) {
                     Arrays.removeAt(locales, i);
                     i--;
                  }
                  break;
               }

               if ((locales[j].getCode() & 65535) == 0) {
                  if (locales[j].getCode() != 1886650368) {
                     Arrays.removeAt(locales, j);
                     if (j < i) {
                        i--;
                     }
                  }
                  break;
               }
            }
         }
      }
   }

   public static void filterUnsupportedMultitapInputLocales(Locale[] locales) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void moveToIndex(Locale[] data, Locale src, int index) {
      if (src != null && data.length > index && !src.equals(data[index])) {
         int locIndex = getIndexOf(data, src);
         if (locIndex != -1) {
            moveLocaleToIndex(data, locIndex, index);
         }
      }
   }

   private static int getIndexOf(Locale[] data, Locale src) {
      int ret = -1;
      if (src != null && data.length > 1) {
         int i;
         for (i = data.length - 1; i >= 0; i--) {
            if (src.equals(data[i])) {
               ret = i;
               break;
            }
         }

         if (i == -1) {
            for (i = data.length - 1; i >= 0; i--) {
               if (src.getCode() == data[i].getCode()) {
                  ret = i;
                  break;
               }
            }
         }

         if (i == -1) {
            for (int var4 = data.length - 1; var4 >= 0; var4--) {
               if ((src.getCode() & -65536) == (data[var4].getCode() & -65536)) {
                  return var4;
               }
            }
         }
      }

      return ret;
   }

   private static void moveLocaleToIndex(Locale[] locales, int fromIndex, int index) {
      if (fromIndex != index) {
         Locale locale = locales[fromIndex];
         Arrays.removeAt(locales, fromIndex);
         Arrays.insertAt(locales, locale, index);
      }
   }

   public static String getDisplayStringFor(Locale aLocale) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static Locale[] getAvailableInputLocales(boolean reorder) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static boolean localesEqual(Locale first, Locale second) {
      if (first != null && second != null) {
         if (first.equals(second)) {
            return true;
         }

         if ((first.getCode() & -65536) == 1784741888 && (first.getCode() == second.getCode() || (first.getCode() & -65536) == (second.getCode() & -65536))) {
            return true;
         }
      }

      return false;
   }

   public static char toUpperCase(char aChar) {
      return CharacterUtilities.toUpperCase(aChar, Locale.getDefaultInputForSystem().getCode());
   }

   public static char toLowerCase(char aChar) {
      return CharacterUtilities.toLowerCase(aChar, Locale.getDefaultInputForSystem().getCode());
   }

   public static String toUpperCase(String aStr) {
      return StringUtilities.toUpperCase(aStr, Locale.getDefaultInputForSystem().getCode());
   }

   public static String toLowerCase(String aStr) {
      return StringUtilities.toLowerCase(aStr, Locale.getDefaultInputForSystem().getCode());
   }

   public static boolean startsWithIgnoreCase(String str1, String str2) {
      return StringUtilities.startsWithIgnoreCase(str1, str2, Locale.getDefaultInputForSystem().getCode());
   }

   public static int getDefaultCountryForLanguage(Locale lang) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public static void removeAt(int[] array, int index) {
      int newLength = array.length - 1;
      System.arraycopy(array, index + 1, array, index, newLength - index);
      Array.resize(array, newLength);
   }

   public static String composeResourceID(int aKeyboardId, String aKeyboardType, Locale anInputLocale, boolean useDefaultLang) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static void addDelimiters(StringBuffer toAdd, int aCount) {
      for (int i = 0; i < aCount; i++) {
         toAdd.append('_');
      }
   }

   public static byte[] loadRimRes(String libName, String toLoad) {
      Resource resource = Resource$Internal.getResourceClass(libName.trim());
      return resource == null ? null : resource.getResource(toLoad.trim());
   }

   public static void reportException(Throwable th) {
      long RADIO_LOGWORTHY_REPORT_REQUEST = -2816799803471967993L;
      RIMGlobalMessagePoster.postGlobalEvent(RADIO_LOGWORTHY_REPORT_REQUEST, 0, 0, th.getMessage(), null);
   }

   public static int binarySearch(char[] a, char key) {
      int low = 0;
      int high = a.length - 1;

      while (low <= high) {
         int mid = low + high >> 1;
         char midVal = a[mid];
         if (midVal < key) {
            low = mid + 1;
         } else {
            if (midVal <= key) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   public static int stringBufferIndexOf(StringBuffer sb, int startIndex, char c) {
      int sbLength = sb.length();

      for (int i = startIndex; i < sbLength; i++) {
         if (sb.charAt(i) == c) {
            return i;
         }
      }

      return -1;
   }
}
