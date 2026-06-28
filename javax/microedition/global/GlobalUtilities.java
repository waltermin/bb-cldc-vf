package javax.microedition.global;

class GlobalUtilities {
   private GlobalUtilities() {
   }

   public static String convertUnderscoreToHyphens(String locale) {
      return locale == null ? null : locale.replace('_', '-');
   }

   public static boolean isValidLocale(String locale) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
