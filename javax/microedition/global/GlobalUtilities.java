package javax.microedition.global;

class GlobalUtilities {
   private GlobalUtilities() {
   }

   public static String convertUnderscoreToHyphens(String var0) {
      return var0 == null ? null : var0.replace('_', '-');
   }

   public static boolean isValidLocale(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
