package net.rim.device.api.system;

import net.rim.device.internal.system.SIMCardEfHandler;
import net.rim.device.internal.system.SIMCardEfTask;

class CellBroadcast$LanguageIndication implements SIMCardEfTask {
   private byte[] _buffer;
   private CellBroadcast$LanguagePreference[] _prefs;

   public CellBroadcast$LanguagePreference[] getLangPrefs() {
      if (this._prefs == null) {
         this._prefs = new CellBroadcast$LanguagePreference[CellBroadcast.MAX_LANG_PREFS];
         CellBroadcast.fillLP_TableWithDefaults(this._prefs, 0);
      }

      return this._prefs;
   }

   @Override
   public void doWork(SIMCardEfHandler var1) {
      int var2 = 0;
      int var3 = 0;
      this._prefs = new CellBroadcast$LanguagePreference[CellBroadcast.MAX_LANG_PREFS];
      int var4 = var1.infoRequest(102);
      if (var4 == 0) {
         this._buffer = new byte[var1.getFileSize()];
         var4 = var1.readRequest(0, this._buffer);
      }

      if (var4 == 0 && this._buffer.length >= 1) {
         if (this._buffer[0] == 255 && this._buffer[1] == 255) {
            CellBroadcast.fillLP_TableWithDefaults(this._prefs, var2);
         } else {
            for (byte var6 = 0; var6 < this._buffer.length - 1; var6 += 2) {
               for (int var7 = 0; var7 < CellBroadcast.MAX_LANG_PREFS; var7++) {
                  int var5 = this._buffer[var6] << 8;
                  var5 |= this._buffer[var6 + 1];
                  if (var5 == CellBroadcast.ISO639_TO_DEFAULTS[var7] && var3 < CellBroadcast.MAX_LANG_PREFS) {
                     Object var8 = new Object(CellBroadcast.langPrefTable[var7]);
                     ((CellBroadcast$LanguagePreference)var8).setPriority(var3);
                     ((CellBroadcast$LanguagePreference)var8).setEnabled(true);
                     this._prefs[var3] = (CellBroadcast$LanguagePreference)var8;
                     var2 |= 1 << CellBroadcast.langPrefTable[var7];
                     var3++;
                  }
               }
            }

            CellBroadcast.fillLP_TableWithDefaults(this._prefs, var2);
         }
      } else {
         CellBroadcast.fillLP_TableWithDefaults(this._prefs, var2);
      }
   }

   public CellBroadcast$LanguageIndication() {
   }
}
