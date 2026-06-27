package net.rim.device.internal.io.file;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.Persistable;

public final class FileSystemOptions implements Persistable {
   private boolean _usbMassStorageEnabled;
   private boolean _externalMemoryEnabled;
   private int _safelyRemoveMode;
   private int _externalEncryptionLevel;
   private int _autoEnableUsbMassStorageMode;
   private long _contentStoreTotalSize;
   private long _picturesReservedSize;
   private boolean _sizesSet;
   private int _dirty;
   private static String BLACKBERRY_VIDEOS;
   private static String BLACKBERRY_MUSIC;
   private static String BLACKBERRY_PICTURES;
   private static String BLACKBERRY_RINGTONES;
   private static String BLACKBERRY_VOICENOTES;
   public static final long GUID_FILE_OPTIONS_CHANGED;
   public static final long FILE_SYSTEM_OPTIONS_KEY;
   public static final int MEGA_BYTES_SHIFT;
   public static final long SMALL_MEMORY_LIMIT;
   public static final long LARGE_MEMORY_LIMIT;
   public static final long SMALL_RESERVED_MEM;
   public static final long LARGE_RESERVED_MEM;
   public static final int ENCRYPTION_NOT_REQUIRED;
   public static final int ENCRYPT_TO_USER_PASSWORD;
   public static final int ENCRYPT_TO_USER_PASSWORD_WITH_MUTLIMEDIA;
   public static final int ENCRYPT_TO_DEVICE_KEY;
   public static final int ENCRYPT_TO_DEVICE_KEY_WITH_MULTIMEDIA;
   public static final int ENCRYPT_TO_USER_PASSWORD_AND_DEVICE_KEY;
   public static final int ENCRYPT_TO_USER_PASSWORD_AND_DEVICE_KEY_WITH_MULTIMEDIA;
   public static final int AUTO_USB_MASS_STORAGE_YES;
   public static final int AUTO_USB_MASS_STORAGE_NO;
   public static final int AUTO_USB_MASS_STORAGE_PROMPT;
   public static final int SAFELY_REMOVE_YES;
   public static final int SAFELY_REMOVE_NO;
   public static final int SAFELY_REMOVE_PROMPT;
   public static final int AUTO_USB_MASS_STORAGE_DIRTY;
   public static final int ENCRYPTION_LEVEL_DIRTY;
   public static final int EXTERNAL_MEMORY_DIRTY;
   public static final int USB_MASS_STORAGE_DIRTY;
   public static final int QUOTA_SIZES_DIRTY;
   public static final int SAFELY_REMOVE_DIRTY;
   private static PersistentObject _persistentObject;
   private static FileSystemOptions _instance;

   private FileSystemOptions() {
      this.restoreDefaultsInternal();
   }

   private final void restoreDefaultsInternal() {
      this._externalMemoryEnabled = true;
      this._usbMassStorageEnabled = true;
      this._safelyRemoveMode = 0;
      this._externalEncryptionLevel = 0;
      this._autoEnableUsbMassStorageMode = 2;
      this._contentStoreTotalSize = 12582912;
      this._picturesReservedSize = 2097152;
      this._dirty = 0;
   }

   public static final PersistentObject getPersistentObjet() {
      return _persistentObject;
   }

   public static final void restoreDefaults() {
      _instance.restoreDefaultsInternal();
   }

   public static final long getContentStoreMaxFileSize() {
      return 3145728;
   }

   public static final long getContentStoreTotalSize() {
      return _instance._contentStoreTotalSize;
   }

   public static final void setContentSizes(long var0, long var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final long getPicturesReservedSize() {
      return InternalServices.isDeviceCapable(21) ? _instance._picturesReservedSize : 0;
   }

   public static final boolean isSizesSet() {
      return _instance._sizesSet;
   }

   public static final boolean getUSBMassStorageMode() {
      return !ITPolicy.getBoolean(24, 59, false) && _instance._usbMassStorageEnabled;
   }

   public static final boolean isDevicePasswordRequired() {
      if (!getExternalMemoryEnabled()) {
         return false;
      }

      switch (getExternalEncryptionLevel()) {
         case 0:
         case 3:
         case 4:
            return false;
         case 1:
         case 2:
         case 5:
         case 6:
         default:
            return true;
      }
   }

   public static final boolean isMediaDirectory(String var0) {
      return StringUtilities.startsWithIgnoreCase(var0, BLACKBERRY_MUSIC, 1701707776)
         || StringUtilities.startsWithIgnoreCase(var0, BLACKBERRY_PICTURES, 1701707776)
         || StringUtilities.startsWithIgnoreCase(var0, BLACKBERRY_RINGTONES, 1701707776)
         || StringUtilities.startsWithIgnoreCase(var0, BLACKBERRY_VOICENOTES, 1701707776)
         || StringUtilities.startsWithIgnoreCase(var0, BLACKBERRY_VIDEOS, 1701707776);
   }

   public static final boolean isExternalEncryptionRequired(String var0) {
      switch (getExternalEncryptionLevel()) {
         case 0:
            return false;
         case 1:
         case 3:
         case 5:
            if (!isMediaDirectory(var0)) {
               return true;
            }

            return false;
         case 2:
         case 4:
         case 6:
         default:
            return true;
      }
   }

   public static final int getExternalEncryptionLevel() {
      if (!FileSystem.isFileSystemSupported(1)) {
         return 0;
      }

      int var0 = ITPolicy.getInteger(24, 60, 0);
      int var1 = _instance._externalEncryptionLevel;
      switch (var0) {
         case -1:
            return 6;
         case 0:
         default:
            return var1;
         case 1:
            if (var1 != 2 && var1 != 5 && var1 != 6) {
               return var0;
            }

            return var1;
         case 2:
         case 4:
            if (var1 != 5 && var1 != 6) {
               return var0;
            }

            return 6;
         case 3:
            if (var1 != 4 && var1 != 5 && var1 != 6) {
               return var0;
            }

            return var1;
         case 5:
         case 6:
            return var1 == 6 ? 6 : var0;
      }
   }

   public static final boolean getExternalMemoryEnabled() {
      return !ITPolicy.getBoolean(24, 58, false) && _instance._externalMemoryEnabled;
   }

   public static final int getAutoEnableUSBMassStorageMode() {
      return _instance._autoEnableUsbMassStorageMode;
   }

   public static final int getSafelyRemoveMode() {
      return _instance._safelyRemoveMode;
   }

   public static final void setUSBMassStorageMode(boolean var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void setExternalEncryptionLevel(int var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void setExternalMemoryEnabled(boolean var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void setSafelyRemoveMode(int var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void setAutoEnableUSBMassStorageMode(int var0) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void save() {
      commit(true);
   }

   private static final void commit(boolean var0) {
      _persistentObject.commit();
      if (var0) {
         RIMGlobalMessagePoster.postGlobalEvent(-3054176912083292366L, _instance._dirty, 0);
         _instance._dirty = 0;
      }
   }
}
