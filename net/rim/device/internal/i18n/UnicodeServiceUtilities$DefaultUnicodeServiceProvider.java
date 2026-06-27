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
   public final byte resolveEncoding(byte[] var1, byte[] var2) {
      if (var2 != null && var2.length != 0 && var1 != null && var1.length != 0) {
         byte var3 = UnicodeServiceUtilities.getPreferredEncoding();
         byte var4 = -1;
         if (this._defaultSupportedEncodings == null) {
            this._defaultSupportedEncodings = this.getSupportedEncodings();
            if (this._defaultSupportedEncodings == null) {
               return -1;
            }
         }

         for (int var5 = 0; var5 < var1.length; var5++) {
            for (int var6 = 0; var6 < var2.length; var6++) {
               if (var2[var6] == var1[var5]) {
                  int var7 = 0;

                  while (var7 < this._defaultSupportedEncodings.length && this._defaultSupportedEncodings[var7] != var1[var5]) {
                     var7++;
                  }

                  if (var7 < this._defaultSupportedEncodings.length) {
                     if (var4 == -1) {
                        var4 = var1[var5];
                     }

                     if (var3 == var1[var5] && var3 != -1) {
                        return var3;
                     }
                  }
               }
            }
         }

         return var4;
      } else {
         return -1;
      }
   }

   @Override
   public final String getEncoding(byte var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final byte getEncoding(String var1) {
      if (var1 != null) {
         for (int var2 = 0; var2 < _definedSerializationEncodingsBytes.length; var2++) {
            if (StringUtilities.startsWithIgnoreCase(_definedSerializationEncodingsNames[var2], var1, 1701707776)) {
               return _definedSerializationEncodingsBytes[var2];
            }
         }
      }

      return -1;
   }

   UnicodeServiceUtilities$DefaultUnicodeServiceProvider(UnicodeServiceUtilities$1 var1) {
      this();
   }
}
