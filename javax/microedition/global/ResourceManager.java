package javax.microedition.global;

import net.rim.device.api.util.Arrays;

public class ResourceManager {
   private String _baseName;
   private String _locale;
   private ResourceFile[] _resourceFile;
   public static final String DEVICE;
   private static final String ROOT_DIRECTORY;
   private static String EMPTY;

   private String getResourceFilename(String baseName, String locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   ResourceManager(String baseName, String locale) {
   }

   public static final ResourceManager getManager(String baseName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final ResourceManager getManager(String baseName, String locale) {
      if (baseName != null && locale != null) {
         String nextLocale = GlobalUtilities.convertUnderscoreToHyphens(locale);
         if (!GlobalUtilities.isValidLocale(nextLocale)) {
            throw new Object();
         }

         while (!isSupportedLocale(nextLocale, baseName)) {
            if (nextLocale.equals(EMPTY)) {
               throw new UnsupportedLocaleException();
            }

            nextLocale = getParentLocale(nextLocale);
         }

         return new ResourceManager(baseName, nextLocale);
      } else {
         throw new Object();
      }
   }

   public static final ResourceManager getManager(String baseName, String[] locales) {
      if (baseName != null && locales != null) {
         int numLocales = locales.length;
         if (numLocales == 0) {
            throw new Object();
         }

         String[] updatedLocales = new String[numLocales];

         for (int i = 0; i < numLocales; i++) {
            updatedLocales[i] = GlobalUtilities.convertUnderscoreToHyphens(locales[i]);
            if (!GlobalUtilities.isValidLocale(updatedLocales[i])) {
               throw new Object();
            }
         }

         for (int i = 0; i < numLocales; i++) {
            if (isSupportedLocale(updatedLocales[i], baseName)) {
               return new ResourceManager(baseName, updatedLocales[i]);
            }
         }

         throw new UnsupportedLocaleException();
      } else {
         throw new Object();
      }
   }

   private static String getParentLocale(String locale) {
      int lastHyphen = locale.lastIndexOf(45);
      return lastHyphen < 0 ? EMPTY : locale.substring(0, lastHyphen);
   }

   private int firstIndexContainingId(int id) {
      if (id >= 0 && id <= Integer.MAX_VALUE) {
         for (int i = 0; i < this._resourceFile.length; i++) {
            if (this._resourceFile[i].isValidId(id)) {
               return i;
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

   public boolean isValidResourceID(int id) {
      return this.firstIndexContainingId(id) != -1;
   }

   public byte[] getData(int id) {
      throw new RuntimeException("cod2jar: type check");
   }

   public Object getResource(int id) {
      int index = this.firstIndexContainingId(id);
      if (index < 0) {
         throw new ResourceException(1, EMPTY);
      } else {
         return this._resourceFile[index].getData(id);
      }
   }

   public String getString(int id) {
      int index = this.firstIndexContainingId(id);
      if (index < 0) {
         throw new ResourceException(1, EMPTY);
      } else if (this._resourceFile[index].getType(id) != 1) {
         throw new ResourceException(2, EMPTY);
      } else {
         return (String)this._resourceFile[index].getData(id);
      }
   }

   public static String[] getSupportedLocales(String baseName) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static String removeQuotes(String locale) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static boolean isSupportedLocale(String locale, String baseName) {
      return Arrays.contains(getSupportedLocales(baseName), locale);
   }

   public boolean isCaching() {
      return true;
   }
}
