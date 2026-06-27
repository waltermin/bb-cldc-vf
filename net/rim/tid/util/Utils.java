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

   public static int bytes2Int(byte var0, byte var1) {
      return bytes2Int(var0 & 0xFF, var1 & 0xFF);
   }

   public static int bytes2Int(int var0, int var1) {
      int var2 = 0;
      var2 |= var0;
      var2 <<= 8;
      return var2 | var1;
   }

   public static int bytes2Int(byte var0, byte var1, byte var2, byte var3) {
      return bytes2Int(var0 & 0xFF, var1 & 0xFF, var2 & 0xFF, var3 & 0xFF);
   }

   public static int bytes2Int(int var0, int var1, int var2, int var3) {
      int var4 = 0;
      var4 |= var0;
      var4 <<= 8;
      var4 |= var1;
      var4 <<= 8;
      var4 |= var2;
      var4 <<= 8;
      return var4 | var3;
   }

   public static int bytes2Int(byte var0, byte var1, byte var2) {
      return bytes2Int(var0 & 0xFF, var1 & 0xFF, var2 & 0xFF);
   }

   public static int bytes2Int(int var0, int var1, int var2) {
      int var3 = 0;
      var3 |= var0;
      var3 <<= 8;
      var3 |= var1;
      var3 <<= 8;
      return var3 | var2;
   }

   public static int findShift(int var0) {
      int var1 = 0;

      while ((var0 & 1) == 0) {
         var1++;
         var0 >>>= 1;
      }

      return var1;
   }

   public static String[] getInputLocalesDisplayNames(Locale[] var0) {
      String[] var1 = new String[var0.length];

      for (int var2 = var0.length - 1; var2 >= 0; var2--) {
         var1[var2] = getDisplayStringFor(var0[var2]);
      }

      return var1;
   }

   public static void filterRootInputLocales(Locale[] var0) {
      for (int var1 = 0; var1 < var0.length; var1++) {
         int var2 = var0[var1].getCode();
         String var3 = var0[var1].getVariant();

         for (int var4 = 0; var4 < var0.length; var4++) {
            if (var4 != var1
               && (var0[var4].getCode() & -65536) == (var2 & -65536)
               && var3.equals(var0[var4].getVariant())
               && (var2 & 65535) != (var0[var4].getCode() & 65535)) {
               if ((var2 & 65535) == 0) {
                  if (var2 != 1886650368) {
                     Arrays.removeAt(var0, var1);
                     var1--;
                  }
                  break;
               }

               if ((var0[var4].getCode() & 65535) == 0) {
                  if (var0[var4].getCode() != 1886650368) {
                     Arrays.removeAt(var0, var4);
                     if (var4 < var1) {
                        var1--;
                     }
                  }
                  break;
               }
            }
         }
      }
   }

   public static void filterUnsupportedMultitapInputLocales(Locale[] var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static void moveToIndex(Locale[] var0, Locale var1, int var2) {
      if (var1 != null && var0.length > var2 && !var1.equals(var0[var2])) {
         int var3 = getIndexOf(var0, var1);
         if (var3 != -1) {
            moveLocaleToIndex(var0, var3, var2);
         }
      }
   }

   private static int getIndexOf(Locale[] var0, Locale var1) {
      int var2 = -1;
      if (var1 != null && var0.length > 1) {
         int var3;
         for (var3 = var0.length - 1; var3 >= 0; var3--) {
            if (var1.equals(var0[var3])) {
               var2 = var3;
               break;
            }
         }

         if (var3 == -1) {
            for (var3 = var0.length - 1; var3 >= 0; var3--) {
               if (var1.getCode() == var0[var3].getCode()) {
                  var2 = var3;
                  break;
               }
            }
         }

         if (var3 == -1) {
            for (int var4 = var0.length - 1; var4 >= 0; var4--) {
               if ((var1.getCode() & -65536) == (var0[var4].getCode() & -65536)) {
                  return var4;
               }
            }
         }
      }

      return var2;
   }

   private static void moveLocaleToIndex(Locale[] var0, int var1, int var2) {
      if (var1 != var2) {
         Locale var3 = var0[var1];
         Arrays.removeAt(var0, var1);
         Arrays.insertAt(var0, var3, var2);
      }
   }

   public static String getDisplayStringFor(Locale var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static Locale[] getAvailableInputLocales(boolean var0) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private static boolean localesEqual(Locale var0, Locale var1) {
      if (var0 != null && var1 != null) {
         if (var0.equals(var1)) {
            return true;
         }

         if ((var0.getCode() & -65536) == 1784741888 && (var0.getCode() == var1.getCode() || (var0.getCode() & -65536) == (var1.getCode() & -65536))) {
            return true;
         }
      }

      return false;
   }

   public static char toUpperCase(char var0) {
      return CharacterUtilities.toUpperCase(var0, Locale.getDefaultInputForSystem().getCode());
   }

   public static char toLowerCase(char var0) {
      return CharacterUtilities.toLowerCase(var0, Locale.getDefaultInputForSystem().getCode());
   }

   public static String toUpperCase(String var0) {
      return StringUtilities.toUpperCase(var0, Locale.getDefaultInputForSystem().getCode());
   }

   public static String toLowerCase(String var0) {
      return StringUtilities.toLowerCase(var0, Locale.getDefaultInputForSystem().getCode());
   }

   public static boolean startsWithIgnoreCase(String var0, String var1) {
      return StringUtilities.startsWithIgnoreCase(var0, var1, Locale.getDefaultInputForSystem().getCode());
   }

   public static int getDefaultCountryForLanguage(Locale var0) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   public static void removeAt(int[] var0, int var1) {
      int var2 = var0.length - 1;
      System.arraycopy(var0, var1 + 1, var0, var1, var2 - var1);
      Array.resize(var0, var2);
   }

   public static String composeResourceID(int var0, String var1, Locale var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static void addDelimiters(StringBuffer var0, int var1) {
      for (int var2 = 0; var2 < var1; var2++) {
         var0.append('_');
      }
   }

   public static byte[] loadRimRes(String var0, String var1) {
      Resource var2 = Resource$Internal.getResourceClass(var0.trim());
      return var2 == null ? null : var2.getResource(var1.trim());
   }

   public static void reportException(Throwable var0) {
      long var1 = -2816799803471967993L;
      RIMGlobalMessagePoster.postGlobalEvent(var1, 0, 0, var0.getMessage(), null);
   }

   public static int binarySearch(char[] var0, char var1) {
      int var2 = 0;
      int var3 = var0.length - 1;

      while (var2 <= var3) {
         int var4 = var2 + var3 >> 1;
         char var5 = var0[var4];
         if (var5 < var1) {
            var2 = var4 + 1;
         } else {
            if (var5 <= var1) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      return -(var2 + 1);
   }

   public static int stringBufferIndexOf(StringBuffer var0, int var1, char var2) {
      int var3 = var0.length();

      for (int var4 = var1; var4 < var3; var4++) {
         if (var0.charAt(var4) == var2) {
            return var4;
         }
      }

      return -1;
   }
}
