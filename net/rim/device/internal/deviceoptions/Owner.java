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

   public static final boolean setOwnerName(String var0) {
      return setOwnerName(var0, false);
   }

   public static final boolean setOwnerName(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getOwnerInfo() {
      return _ownerData._info;
   }

   public static final boolean setOwnerInfo(String var0) {
      return setOwnerInfo(var0, false);
   }

   public static final boolean setOwnerInfo(String var0, boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void resetToDefaults() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void commit(boolean var0) {
      _persistentObject.commit();
      if (var0) {
         RIMGlobalMessagePoster.postGlobalEvent(-3297167379286550693L);
      }
   }
}
