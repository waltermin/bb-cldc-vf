package net.rim.device.api.itpolicy;

public final class WipeITPolicyDirectory {
   private static int WIPE_ALL;
   private static int[] WIPEABLE_POLICY_GROUPS;
   private static int[][][] WIPEABLE_POLICY_IDS;

   public static final boolean isWipeableGroup(int group) {
      for (int i = 0; i < WIPEABLE_POLICY_GROUPS.length; i++) {
         if (WIPEABLE_POLICY_GROUPS[i] == group) {
            return true;
         }
      }

      return false;
   }

   public static final boolean isWipeableId(int group, int id) {
      int groupLookupId = -1;

      for (int i = 0; i < WIPEABLE_POLICY_GROUPS.length; i++) {
         if (WIPEABLE_POLICY_GROUPS[i] == group) {
            groupLookupId = i;
            break;
         }
      }

      if (groupLookupId == -1) {
         return false;
      }

      int[] wipeableIds = (int[])WIPEABLE_POLICY_IDS[groupLookupId];
      if (wipeableIds[0] == WIPE_ALL) {
         return true;
      }

      for (int i = 0; i < wipeableIds.length; i++) {
         if (wipeableIds[i] == id) {
            return true;
         }
      }

      return false;
   }
}
