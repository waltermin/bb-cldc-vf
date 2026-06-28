package net.rim.device.internal.deviceoptions;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;

public final class Owner {
   public static final long GUID_OWNER_OPTIONS_CHANGED;
   public static final int OWNER_NAME_MAX_CHARS;
   public static final int OWNER_INFO_MAX_CHARS;
   private static final long OWNER_DATA_KEY;
   private static PersistentObject _persistentObject;
   private static Owner$OwnerData _ownerData;

   private Owner() {
   }

   public static final String getOwnerName() {
      return _ownerData._name;
   }

   public static final boolean setOwnerName(String name) {
      return setOwnerName(name, false);
   }

   public static final boolean setOwnerName(String name, boolean force) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getOwnerInfo() {
      return _ownerData._info;
   }

   public static final boolean setOwnerInfo(String info) {
      return setOwnerInfo(info, false);
   }

   public static final boolean setOwnerInfo(String info, boolean force) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void resetToDefaults() {
      _ownerData._name = "";
      _ownerData._info = "";
      commit(true);
   }

   private static final void commit(boolean notifyOfChanges) {
      _persistentObject.commit();
      if (notifyOfChanges) {
         RIMGlobalMessagePoster.postGlobalEvent(-3297167379286550693L);
      }
   }
}
