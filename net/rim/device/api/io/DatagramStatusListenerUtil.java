package net.rim.device.api.io;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.util.IntIntHashtable;

public final class DatagramStatusListenerUtil {
   private static IntIntHashtable _statusToResourceIdMap;
   private static ResourceBundle _rb;
   private static final int[] _statusToResourceIdMapInitData;

   public static final synchronized String getStatusMessage(int code) {
      return getStatusMessage(code, -1, -1);
   }

   public static final synchronized String getStatusMessage(int code, int originalCode, int messageID) {
      if (_statusToResourceIdMap == null) {
         _rb = ResourceBundle.getBundle(-578169177139877362L, "net.rim.device.internal.resource.Datagram");
         _statusToResourceIdMap = new IntIntHashtable();
         int count = _statusToResourceIdMapInitData.length;

         for (int i = 0; i < count; i += 2) {
            _statusToResourceIdMap.put(_statusToResourceIdMapInitData[i], _statusToResourceIdMapInitData[i + 1]);
         }
      }

      int statusResourceId = _statusToResourceIdMap.get(code);
      if (statusResourceId == -1) {
         return null;
      }

      String message = _rb.getString(statusResourceId);
      if ((originalCode > -1 || messageID > 0) && RadioInfo.getNetworkType() == 4) {
         StringBuffer additionalMessage = new StringBuffer();
         additionalMessage.append('(');
         if (originalCode > -1) {
            additionalMessage.append(_rb.getString(465)).append(' ').append(originalCode);
            if (messageID > 0) {
               additionalMessage.append(',').append(' ');
            }
         }

         if (messageID > 0) {
            additionalMessage.append(_rb.getString(466)).append(' ').append(messageID);
         }

         additionalMessage.append(')').append(' ').append(message);
         message = additionalMessage.toString();
      }

      return message;
   }

   public static final synchronized String getString(int id) {
      if (_rb == null) {
         _rb = ResourceBundle.getBundle(-578169177139877362L, "net.rim.device.internal.resource.Datagram");
      }

      return _rb.getString(id);
   }
}
