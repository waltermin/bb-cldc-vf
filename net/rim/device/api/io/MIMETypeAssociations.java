package net.rim.device.api.io;

import java.util.Hashtable;
import net.rim.device.api.util.StringCaseInsensitiveHashtable;
import net.rim.device.api.util.StringToIntCaseInsensitiveHashtable;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;

public final class MIMETypeAssociations {
   private Hashtable _mimeTypeToExt;
   private StringCaseInsensitiveHashtable _extToMimeType;
   private Hashtable _typeMappings;
   private StringToIntCaseInsensitiveHashtable _extToMediaType;
   public static final int MEDIA_TYPE_UNKNOWN;
   public static final int MEDIA_TYPE_IMAGE;
   public static final int MEDIA_TYPE_AUDIO;
   public static final int MEDIA_TYPE_VIDEO;
   public static final int MEDIA_TYPE_TEXT;
   public static final int MEDIA_TYPE_ARCHIVE;
   public static final int MEDIA_TYPE_APPLICATION;
   public static final int MEDIA_TYPE_PLAY_LIST;
   private static final long FILE_TYPE_REGISTRY;
   private static MIMETypeAssociations _instance;
   private static String ENCRYPTED_MEDIA_EXTENSION;
   private static String DIGITAL_RIGHTS_EXTENSION;

   private MIMETypeAssociations() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   private final void registerTypeInternal(String type, String ext, int mediaType) {
      this._extToMimeType.put(ext, type);
      if (!this._mimeTypeToExt.containsKey(type)) {
         this._mimeTypeToExt.put(type, ext);
      }

      this._extToMediaType.put(ext, mediaType);
   }

   private final void registerMapping(String typeFrom, String typeTo) {
      this._typeMappings.put(typeFrom, typeTo);
   }

   public static final void registerMIMETypeMapping(String typeFrom, String typeTo) {
      assertPermission();
      typeFrom = StringUtilities.toLowerCase(typeFrom, 1701707776);
      typeTo = StringUtilities.toLowerCase(typeTo, 1701707776);
      _instance.registerMapping(typeFrom, typeTo);
   }

   public static final String getMIMEType(String filename) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getMediaType(String filename) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getMediaTypeFromMIMEType(String mimeType) {
      if (mimeType == null) {
         return 0;
      }

      String ext = (String)_instance._mimeTypeToExt.get(getNormalizedType(mimeType));
      if (ext == null) {
         return 0;
      }

      int result = _instance._extToMediaType.get(ext);
      return result == -1 ? 0 : result;
   }

   public static final String getExtensionFromMIMEType(String mimeType) {
      return mimeType == null ? null : (String)_instance._mimeTypeToExt.get(getNormalizedType(mimeType));
   }

   public static final void registerType(String extension, String mimeType, int mediaType) {
      assertPermission();
      if (extension != null && mimeType != null) {
         synchronized (_instance) {
            if (!_instance._extToMimeType.containsKey(extension)) {
               extension = StringUtilities.toLowerCase(extension, 1701707776);
               mimeType = StringUtilities.toLowerCase(mimeType, 1701707776);
               _instance.registerTypeInternal(mimeType, extension, mediaType);
            }
         }
      } else {
         throw new NullPointerException();
      }
   }

   public static final String getNormalizedType(String mimeType) {
      if (mimeType == null) {
         return null;
      }

      int indexOfSemicolon = mimeType.indexOf(59);
      if (indexOfSemicolon > 0) {
         mimeType = mimeType.substring(0, indexOfSemicolon).trim();
      }

      mimeType = StringUtilities.toLowerCase(mimeType, 1701707776);
      String normal = (String)_instance._typeMappings.get(mimeType);
      return normal != null ? normal : mimeType;
   }
}
