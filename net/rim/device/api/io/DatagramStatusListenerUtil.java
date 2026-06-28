package net.rim.device.api.io;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.util.IntIntHashtable;

public final class DatagramStatusListenerUtil {
   private static IntIntHashtable _statusToResourceIdMap;
   private static ResourceBundle _rb;
   private static final int[] _statusToResourceIdMapInitData;

   public static final synchronized String getStatusMessage(int code) {
      return getStatusMessage(code, -1, -1);
   }

   public static final synchronized String getStatusMessage(int code, int originalCode, int messageID) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final synchronized String getString(int id) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
