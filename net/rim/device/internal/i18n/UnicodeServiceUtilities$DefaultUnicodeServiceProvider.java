package net.rim.device.internal.i18n;

import net.rim.device.api.util.StringUtilities;

final class UnicodeServiceUtilities$DefaultUnicodeServiceProvider implements UnicodeServiceProvider {
   private byte[] _defaultSupportedEncodings;
   private static String[] _definedSerializationEncodingsNames;
   private static byte[] _definedSerializationEncodingsBytes;

   private UnicodeServiceUtilities$DefaultUnicodeServiceProvider() {
   }

   @Override
   public final synchronized byte[] getSupportedEncodings() {
      throw new RuntimeException("cod2jar: array init");
   }

   @Override
   public final byte resolveEncoding(byte[] clientServiceEncodings, byte[] hostServiceEncodings) {
      if (hostServiceEncodings != null && hostServiceEncodings.length != 0 && clientServiceEncodings != null && clientServiceEncodings.length != 0) {
         byte prefEnc = UnicodeServiceUtilities.getPreferredEncoding();
         byte curEnc = -1;
         if (this._defaultSupportedEncodings == null) {
            this._defaultSupportedEncodings = this.getSupportedEncodings();
            if (this._defaultSupportedEncodings == null) {
               return -1;
            }
         }

         for (int i = 0; i < clientServiceEncodings.length; i++) {
            for (int j = 0; j < hostServiceEncodings.length; j++) {
               if (hostServiceEncodings[j] == clientServiceEncodings[i]) {
                  int k = 0;

                  while (k < this._defaultSupportedEncodings.length && this._defaultSupportedEncodings[k] != clientServiceEncodings[i]) {
                     k++;
                  }

                  if (k < this._defaultSupportedEncodings.length) {
                     if (curEnc == -1) {
                        curEnc = clientServiceEncodings[i];
                     }

                     if (prefEnc == clientServiceEncodings[i] && prefEnc != -1) {
                        return prefEnc;
                     }
                  }
               }
            }
         }

         return curEnc;
      } else {
         return -1;
      }
   }

   @Override
   public final String getEncoding(byte encodingType) {
      for (int i = 0; i < _definedSerializationEncodingsBytes.length; i++) {
         if (encodingType == _definedSerializationEncodingsBytes[i]) {
            return _definedSerializationEncodingsNames[i];
         }
      }

      return "";
   }

   @Override
   public final byte getEncoding(String encodingType) {
      if (encodingType != null) {
         for (int i = 0; i < _definedSerializationEncodingsBytes.length; i++) {
            if (StringUtilities.startsWithIgnoreCase(_definedSerializationEncodingsNames[i], encodingType, 1701707776)) {
               return _definedSerializationEncodingsBytes[i];
            }
         }
      }

      return -1;
   }

   UnicodeServiceUtilities$DefaultUnicodeServiceProvider(UnicodeServiceUtilities$1 x0) {
      this();
   }
}
