package net.rim.device.api.system;

import net.rim.device.api.util.Arrays;
import net.rim.device.internal.system.SIMCardEfHandler;

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
      CellBroadcast$ChannelInfo[] internalInfos = getInternalChannelInfos();
      synchronized (internalInfos) {
         boolean first = true;
         boolean commit = false;
         boolean[] found = new boolean[internalInfos.length];

         int id;
         while ((id = getNextChannelIdInternal(first)) != -1) {
            first = false;

            int i;
            for (i = internalInfos.length - 1; i >= 0; i--) {
               if (internalInfos[i].getId() == id) {
                  if (i < found.length) {
                     found[i] = true;
                  }
                  break;
               }
            }

            if (i < 0) {
               Arrays.add(internalInfos, new CellBroadcast$ChannelInfo(id));
               commit = true;
            }
         }

         for (int i = found.length - 1; i >= 0; i--) {
            if (internalInfos[i].isEnabled() && !found[i]) {
               internalInfos[i].setEnabled(false);
               commit = true;
            }
         }

         if (commit) {
            PersistentObject.commit(internalInfos);
         }

         CellBroadcast$ChannelInfo[] infos = new CellBroadcast$ChannelInfo[internalInfos.length];

         for (int i = infos.length - 1; i >= 0; i--) {
            infos[i] = internalInfos[i].clone();
         }

         return infos;
      }
   }

   public static final boolean addChannelInfo(CellBroadcast$ChannelInfo ci) {
      CellBroadcast$ChannelInfo[] infos = getInternalChannelInfos();
      synchronized (infos) {
         int id = ci.getId();

         for (int i = infos.length - 1; i >= 0; i--) {
            if (infos[i].getId() == id) {
               return false;
            }
         }

         Arrays.add(infos, ci);
         PersistentObject.commit(infos);
         writeChannelInfoInternal(id, ci.isEnabled());
         return true;
      }
   }

   public static final boolean deleteChannelInfo(int channelId) {
      CellBroadcast$ChannelInfo[] infos = getInternalChannelInfos();
      synchronized (infos) {
         for (int i = infos.length - 1; i >= 0; i--) {
            if (infos[i].getId() == channelId) {
               Arrays.removeAt(infos, i);
               PersistentObject.commit(infos);
               writeChannelInfoInternal(channelId, false);
               return true;
            }
         }

         return false;
      }
   }

   public static final boolean setChannelInfo(CellBroadcast$ChannelInfo info) {
      return setChannelInfo(info.getId(), info.isEnabled(), info.getNickname());
   }

   public static final boolean setChannelInfo(int channelId, boolean enabled, String nickname) {
      CellBroadcast$ChannelInfo[] infos = getInternalChannelInfos();
      synchronized (infos) {
         for (int i = infos.length - 1; i >= 0; i--) {
            if (infos[i].getId() == channelId) {
               infos[i].setEnabled(enabled);
               infos[i].setNickname(nickname);
               PersistentObject.commit(infos);
               if (enabled ^ channelExistsInternally(channelId)) {
                  writeChannelInfoInternal(channelId, enabled);
               }

               return true;
            }
         }

         return false;
      }
   }

   private static final boolean channelExistsInternally(int channelId) {
      boolean first = true;

      int id;
      while ((id = getNextChannelIdInternal(first)) != -1) {
         first = false;
         if (id == channelId) {
            return true;
         }
      }

      return false;
   }

   private static final void fillLP_TableWithDefaults(CellBroadcast$LanguagePreference[] prefs, int seenLangs) {
      for (int i = 0; i < prefs.length; i++) {
         if (prefs[i] == null) {
            for (int bit = 0; bit < MAX_LANG_PREFS; bit++) {
               if ((seenLangs & 1 << bit) == 0) {
                  prefs[i] = new CellBroadcast$LanguagePreference(getLanguagePrefEntry(bit));
                  prefs[i].setPriority(i);
                  seenLangs |= 1 << bit;
                  break;
               }
            }
         }
      }
   }

   public static final CellBroadcast$LanguagePreference[] getLanguagePreferences() {
      CellBroadcast$LanguagePreference[] prefs = new CellBroadcast$LanguagePreference[MAX_LANG_PREFS];
      boolean usimPresent = false;

      try {
         usimPresent = SIMCard.isUSIMPresent();
      } catch (SIMCardException var9) {
      }

      if (usimPresent) {
         CellBroadcast$LanguageIndication _langIndication = new CellBroadcast$LanguageIndication();
         new SIMCardEfHandler().startTask(_langIndication, true);
         prefs = _langIndication.getLangPrefs();
         return removeUnspecifiedFromLangPrefs(prefs);
      }

      int seenLangs = 0;
      boolean first = true;
      synchronized (getInternalChannelInfos()) {
         CellBroadcast$LanguagePreference pref = new CellBroadcast$LanguagePreference();

         while (getNextLanguagePrefInternal(pref, first)) {
            first = false;
            int priority = pref.getPriority();
            if (priority >= 0 && priority < MAX_LANG_PREFS && prefs[priority] == null) {
               prefs[priority] = pref;
               seenLangs |= 1 << getLanguagePrefIndex(pref.getId());
               pref = new CellBroadcast$LanguagePreference();
            }
         }

         fillLP_TableWithDefaults(prefs, seenLangs);
         return prefs;
      }
   }

   public static final CellBroadcast$LanguagePreference[] removeUnspecifiedFromLangPrefs(CellBroadcast$LanguagePreference[] prefs) {
      CellBroadcast$LanguagePreference[] newPrefs = new CellBroadcast$LanguagePreference[prefs.length - 1];
      boolean foundPrefs = false;
      int posInNewPrefs = 0;

      for (int i = 0; i < prefs.length; i++) {
         if (prefs[i].getId() == 15) {
            foundPrefs = true;
         } else {
            newPrefs[posInNewPrefs] = prefs[i];
            posInNewPrefs++;
         }
      }

      return foundPrefs ? newPrefs : prefs;
   }

   public static final boolean setLanguagePreference(CellBroadcast$LanguagePreference pref) {
      return setLanguagePreference(pref.getId(), pref.isEnabled(), pref.getPriority());
   }

   public static final boolean setLanguagePreference(int id, boolean enabled, int priority) {
      synchronized (getInternalChannelInfos()) {
         writeLanguagePrefInternal(id, enabled, priority);
         return true;
      }
   }

   public static final boolean setLanguageIndication(CellBroadcast$LanguagePreference[] langPrefs) {
      CellBroadcast$LanguageIndicationWriter _liw = new CellBroadcast$LanguageIndicationWriter(langPrefs);
      new SIMCardEfHandler().startTask(_liw, true);
      return true;
   }

   public static final int getLanguagePrefIndex(int langPref) {
      for (int index = MAX_LANG_PREFS - 1; index >= 0; index--) {
         if (langPrefTable[index] == langPref) {
            return index;
         }
      }

      return MAX_LANG_PREFS;
   }

   public static final int getLanguagePrefEntry(int index) {
      return langPrefTable[index];
   }

   private static final native int getNextChannelIdInternal(boolean var0);

   private static final native void writeChannelInfoInternal(int var0, boolean var1);

   private static final native boolean getNextLanguagePrefInternal(CellBroadcast$LanguagePreference var0, boolean var1);

   private static final native void writeLanguagePrefInternal(int var0, boolean var1, int var2);

   private static final CellBroadcast$ChannelInfo[] getInternalChannelInfos() {
      synchronized (PersistentStore.getSynchObject()) {
         PersistentObject po = PersistentStore.getPersistentObject(757118313738273256L);
         CellBroadcast$ChannelInfo[] infos = (CellBroadcast$ChannelInfo[])po.getContents();
         if (infos == null) {
            infos = new CellBroadcast$ChannelInfo[0];
            po.setContents(infos, 51);
            po.commit();
         }

         return infos;
      }
   }
}
