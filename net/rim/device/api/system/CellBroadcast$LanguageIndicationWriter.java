package net.rim.device.api.system;

import net.rim.device.internal.system.SIMCardEfHandler;
import net.rim.device.internal.system.SIMCardEfTask;

class CellBroadcast$LanguageIndicationWriter implements SIMCardEfTask {
   private byte[] _buffer;
   private CellBroadcast$LanguagePreference[] prefs;
   private int BUFFER_SIZE = 2;

   public CellBroadcast$LanguageIndicationWriter(CellBroadcast$LanguagePreference[] var1) {
      this.prefs = var1;
   }

   private static int findLanguagePosition(int var0) {
      for (int var1 = 0; var1 < CellBroadcast.langPrefTable.length; var1++) {
         if (var0 == CellBroadcast.langPrefTable[var1]) {
            return var1;
         }
      }

      return CellBroadcast.MAX_LANG_PREFS;
   }

   @Override
   public void doWork(SIMCardEfHandler var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
