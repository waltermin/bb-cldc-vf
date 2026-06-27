package net.rim.device.internal.i18n;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.vm.WeakReference;

public final class CommonResource implements net.rim.device.internal.resource.CommonResource {
   public static final int RESPONSES;
   public static final int RESPONSE_VALUES;
   public static final int DEFAULT;
   public static final int MESSAGE;
   public static final int DLG_SAVE;
   public static final int DLG_OK;
   public static final int DLG_DELETE;
   public static final int DLG_YES_NO;
   public static final int DLG_OK_CANCEL;
   public static final int DLG_ALLOW_DENY;
   private static ResourceBundleFamily _bundle;
   private static Object[] _params;
   private static WeakReference _bufferWR;

   public static final ResourceBundleFamily getBundle() {
      return _bundle;
   }

   public static final String format(int var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String format(int var0, Object[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getString(int var0) {
      return _bundle.getString(var0);
   }

   public static final String[] getStringArray(int var0) {
      return _bundle.getStringArray(var0);
   }
}
