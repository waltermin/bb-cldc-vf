package net.rim.device.api.io.file;

import java.io.IOException;
import net.rim.device.api.i18n.ResourceBundle;

public class FileIOException extends IOException {
   private int _errorCode;
   public static final int INVALID_HANDLE;
   public static final int INVALID_PARAMETER;
   public static final int FILE_SYSTEM_UNAVAILABLE;
   public static final int OS_BUSY;
   public static final int GENERAL_ERROR;
   public static final int FILENAME_ALREADY_EXISTS;
   public static final int FILENAME_NOT_FOUND;
   public static final int FILESYSTEM_FULL;
   public static final int FILESYSTEM_EMPTY;
   public static final int NO_FREE_HANDLES;
   public static final int INVALID_OPERATION;
   public static final int FILENAME_TOO_LONG;
   public static final int DIRECTORY_FULL;
   public static final int DIRECTORY_ALREADY_EXISTS;
   public static final int DIRECTORY_NOT_FOUND;
   public static final int DIRECTORY_NOT_EMPTY;
   public static final int MEDIUM_NOT_FORMATTED;
   public static final int FS_ALREADY_MOUNTED;
   public static final int FILE_HANDLES_OPEN;
   public static final int FS_NOT_MOUNTED;
   public static final int FS_VERIFICATION_FAILED;
   public static final int FILE_NOT_OPEN;
   public static final int NOT_A_FILE;
   public static final int NOT_A_DIRECTORY;
   public static final int NO_SUCH_ROOT;
   public static final int INVALID_CHARACTERS;
   public static final int STREAM_ALREADY_OPENED;
   public static final int IS_A_DIRECTORY;
   public static final int CONTENT_BUILT_IN;
   public static final int FILE_TOO_LARGE;
   private static ResourceBundle _rb;

   public FileIOException(int var1) {
      this._errorCode = var1;
   }

   public int getErrorCode() {
      return this._errorCode;
   }

   @Override
   public String getMessage() {
      byte var1;
      switch (this._errorCode) {
         case 4:
         case 5:
         case 18:
         case 19:
         case 21:
            var1 = 7;
            break;
         case 7:
            var1 = 1;
            break;
         case 8:
            var1 = 3;
            break;
         case 9:
            var1 = 0;
            break;
         case 10:
            var1 = 4;
            break;
         case 11:
         case 20:
            var1 = 6;
            break;
         case 13:
            var1 = 5;
            break;
         case 14:
            var1 = 8;
            break;
         case 15:
            var1 = 9;
            break;
         case 16:
            var1 = 10;
            break;
         case 17:
            var1 = 11;
            break;
         case 1008:
            var1 = 12;
            break;
         default:
            var1 = 2;
      }

      return _rb.getString(var1);
   }
}
