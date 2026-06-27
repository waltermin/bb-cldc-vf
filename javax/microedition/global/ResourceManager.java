package javax.microedition.global;

import net.rim.device.api.util.Arrays;

public class ResourceManager {
   private String _baseName;
   private String _locale;
   private ResourceFile[] _resourceFile;
   public static final String DEVICE;
   private static final String ROOT_DIRECTORY;
   private static String EMPTY;

   private String getResourceFilename(String var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   ResourceManager(String var1, String var2) {
   }

   public static final ResourceManager getManager(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final ResourceManager getManager(String var0, String var1) {
      if (var0 != null && var1 != null) {
         String var2 = GlobalUtilities.convertUnderscoreToHyphens(var1);
         if (!GlobalUtilities.isValidLocale(var2)) {
            throw new Object();
         }

         while (!isSupportedLocale(var2, var0)) {
            if (var2.equals(EMPTY)) {
               throw new UnsupportedLocaleException();
            }

            var2 = getParentLocale(var2);
         }

         return new ResourceManager(var0, var2);
      } else {
         throw new Object();
      }
   }

   public static final ResourceManager getManager(String var0, String[] var1) {
      if (var0 != null && var1 != null) {
         int var2 = var1.length;
         if (var2 == 0) {
            throw new Object();
         }

         String[] var3 = new String[var2];

         for (int var4 = 0; var4 < var2; var4++) {
            var3[var4] = GlobalUtilities.convertUnderscoreToHyphens(var1[var4]);
            if (!GlobalUtilities.isValidLocale(var3[var4])) {
               throw new Object();
            }
         }

         for (int var5 = 0; var5 < var2; var5++) {
            if (isSupportedLocale(var3[var5], var0)) {
               return new ResourceManager(var0, var3[var5]);
            }
         }

         throw new UnsupportedLocaleException();
      } else {
         throw new Object();
      }
   }

   private static String getParentLocale(String var0) {
      int var1 = var0.lastIndexOf(45);
      return var1 < 0 ? EMPTY : var0.substring(0, var1);
   }

   private int firstIndexContainingId(int var1) {
      if (var1 >= 0 && var1 <= Integer.MAX_VALUE) {
         for (int var2 = 0; var2 < this._resourceFile.length; var2++) {
            if (this._resourceFile[var2].isValidId(var1)) {
               return var2;
            }
         }

         return -1;
      } else {
         throw new Object();
      }
   }

   public String getBaseName() {
      return this._baseName;
   }

   public String getLocale() {
      return this._locale;
   }

   public boolean isValidResourceID(int var1) {
      return this.firstIndexContainingId(var1) != -1;
   }

   public byte[] getData(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public Object getResource(int var1) {
      int var2 = this.firstIndexContainingId(var1);
      if (var2 < 0) {
         throw new ResourceException(1, EMPTY);
      } else {
         return this._resourceFile[var2].getData(var1);
      }
   }

   public String getString(int var1) {
      int var2 = this.firstIndexContainingId(var1);
      if (var2 < 0) {
         throw new ResourceException(1, EMPTY);
      } else if (this._resourceFile[var2].getType(var1) != 1) {
         throw new ResourceException(2, EMPTY);
      } else {
         return (String)this._resourceFile[var2].getData(var1);
      }
   }

   public static String[] getSupportedLocales(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static String removeQuotes(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean isSupportedLocale(String var0, String var1) {
      return Arrays.contains(getSupportedLocales(var1), var0);
   }

   public boolean isCaching() {
      return true;
   }
}
