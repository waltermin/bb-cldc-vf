package javax.microedition.global;

public final class ResourceException extends RuntimeException {
   private int _err;
   public static final int UNKNOWN_ERROR;
   public static final int RESOURCE_NOT_FOUND;
   public static final int WRONG_RESOURCE_TYPE;
   public static final int NO_RESOURCES_FOR_BASE_NAME;
   public static final int NO_SYSTEM_DEFAULT_LOCALE;
   public static final int DATA_ERROR;
   public static final int UNKNOWN_RESOURCE_TYPE;
   public static final int METAFILE_NOT_FOUND;
   private static final int MAX_ERROR_CODE;

   public ResourceException(int var1, String var2) {
      super(var2);
      if (var1 >= 0 && var1 <= 7) {
         this._err = var1;
      } else {
         throw new Object();
      }
   }

   public final int getErrorCode() {
      return this._err;
   }
}
