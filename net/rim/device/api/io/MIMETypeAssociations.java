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

   private final void registerTypeInternal(String var1, String var2, int var3) {
      this._extToMimeType.put(var2, var1);
      if (!this._mimeTypeToExt.containsKey(var1)) {
         this._mimeTypeToExt.put(var1, var2);
      }

      this._extToMediaType.put(var2, var3);
   }

   private final void registerMapping(String var1, String var2) {
      this._typeMappings.put(var1, var2);
   }

   public static final void registerMIMETypeMapping(String var0, String var1) {
      assertPermission();
      var0 = StringUtilities.toLowerCase(var0, 1701707776);
      var1 = StringUtilities.toLowerCase(var1, 1701707776);
      _instance.registerMapping(var0, var1);
   }

   public static final String getMIMEType(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getMediaType(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final int getMediaTypeFromMIMEType(String var0) {
      if (var0 == null) {
         return 0;
      }

      Object var1 = _instance._mimeTypeToExt.get(getNormalizedType(var0));
      if (var1 == null) {
         return 0;
      }

      int var2 = _instance._extToMediaType.get((String)var1);
      return var2 == -1 ? 0 : var2;
   }

   public static final String getExtensionFromMIMEType(String var0) {
      return (String)(var0 == null ? null : _instance._mimeTypeToExt.get(getNormalizedType(var0)));
   }

   public static final void registerType(String var0, String var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getNormalizedType(String var0) {
      if (var0 == null) {
         return null;
      }

      int var1 = var0.indexOf(59);
      if (var1 > 0) {
         var0 = var0.substring(0, var1).trim();
      }

      var0 = StringUtilities.toLowerCase(var0, 1701707776);
      Object var2 = _instance._typeMappings.get(var0);
      return (String)(var2 != null ? var2 : var0);
   }
}
