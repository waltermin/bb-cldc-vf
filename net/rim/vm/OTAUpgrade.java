package net.rim.vm;

import net.rim.device.api.synchronization.SyncCollection;

public final class OTAUpgrade {
   public static final long DEFER_AUTO_OFF_GUID;
   private static final long OTASL_ONLY_COLLECTIONS_GUID;
   private static boolean _considerState;

   private OTAUpgrade() {
   }

   public static final native void setState(int var0);

   public static final native int getState();

   public static final native int getFailureCode();

   public static final native boolean isCapable();

   public static final synchronized boolean isRadioAllowedOn() {
      if (!_considerState) {
         return true;
      }

      int var0 = getState();
      switch (var0) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 6:
         case 12:
         case 15:
            return true;
         default:
            return false;
      }
   }

   public static final synchronized void requestRadioState(boolean var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final synchronized void activateRadios(int var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addOTASLOnlyCollection(SyncCollection var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final SyncCollection[] getOTASLOnlyCollections() {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isOTASLInProgress() {
      int var0 = getState();
      switch (var0) {
         case -1:
            return true;
         case 0:
         case 1:
         case 2:
         default:
            return false;
      }
   }
}
