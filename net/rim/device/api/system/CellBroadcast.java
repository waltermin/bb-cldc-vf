package net.rim.device.api.system;

public final class CellBroadcast {
   private static final long CHANNEL_INFOS_GUID;
   private static final int[] langPrefTable;
   private static final int MAX_LANG_PREFS;
   private static final int[] ISO639_TO_DEFAULTS;

   public static final boolean isSupported() {
      return RadioInfo.areWAFsSupported(1);
   }

   public static final native void enableCellBroadcast(boolean var0);

   public static final CellBroadcast$ChannelInfo[] getChannelInfos() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean addChannelInfo(CellBroadcast$ChannelInfo var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean deleteChannelInfo(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean setChannelInfo(CellBroadcast$ChannelInfo var0) {
      return setChannelInfo(var0.getId(), var0.isEnabled(), var0.getNickname());
   }

   public static final boolean setChannelInfo(int var0, boolean var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final boolean channelExistsInternally(int var0) {
      boolean var1 = true;

      int var2;
      while ((var2 = getNextChannelIdInternal(var1)) != -1) {
         var1 = false;
         if (var2 == var0) {
            return true;
         }
      }

      return false;
   }

   private static final void fillLP_TableWithDefaults(CellBroadcast$LanguagePreference[] var0, int var1) {
      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var0[var2] == null) {
            for (int var3 = 0; var3 < MAX_LANG_PREFS; var3++) {
               if ((var1 & 1 << var3) == 0) {
                  var0[var2] = (CellBroadcast$LanguagePreference)(new Object(getLanguagePrefEntry(var3)));
                  var0[var2].setPriority(var2);
                  var1 |= 1 << var3;
                  break;
               }
            }
         }
      }
   }

   public static final CellBroadcast$LanguagePreference[] getLanguagePreferences() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final CellBroadcast$LanguagePreference[] removeUnspecifiedFromLangPrefs(CellBroadcast$LanguagePreference[] var0) {
      CellBroadcast$LanguagePreference[] var1 = new CellBroadcast$LanguagePreference[var0.length - 1];
      boolean var2 = false;
      int var3 = 0;

      for (int var4 = 0; var4 < var0.length; var4++) {
         if (var0[var4].getId() == 15) {
            var2 = true;
         } else {
            var1[var3] = var0[var4];
            var3++;
         }
      }

      return var2 ? var1 : var0;
   }

   public static final boolean setLanguagePreference(CellBroadcast$LanguagePreference var0) {
      return setLanguagePreference(var0.getId(), var0.isEnabled(), var0.getPriority());
   }

   public static final boolean setLanguagePreference(int var0, boolean var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean setLanguageIndication(CellBroadcast$LanguagePreference[] var0) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public static final int getLanguagePrefIndex(int var0) {
      for (int var1 = MAX_LANG_PREFS - 1; var1 >= 0; var1--) {
         if (langPrefTable[var1] == var0) {
            return var1;
         }
      }

      return MAX_LANG_PREFS;
   }

   public static final int getLanguagePrefEntry(int var0) {
      return langPrefTable[var0];
   }

   private static final native int getNextChannelIdInternal(boolean var0);

   private static final native void writeChannelInfoInternal(int var0, boolean var1);

   private static final native boolean getNextLanguagePrefInternal(CellBroadcast$LanguagePreference var0, boolean var1);

   private static final native void writeLanguagePrefInternal(int var0, boolean var1, int var2);

   private static final CellBroadcast$ChannelInfo[] getInternalChannelInfos() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
