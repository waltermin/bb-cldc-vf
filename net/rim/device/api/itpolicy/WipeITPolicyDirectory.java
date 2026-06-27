package net.rim.device.api.itpolicy;

public final class WipeITPolicyDirectory {
   private static int WIPE_ALL;
   private static int[] WIPEABLE_POLICY_GROUPS;
   private static int[][][] WIPEABLE_POLICY_IDS;

   public static final boolean isWipeableGroup(int var0) {
      for (int var1 = 0; var1 < WIPEABLE_POLICY_GROUPS.length; var1++) {
         if (WIPEABLE_POLICY_GROUPS[var1] == var0) {
            return true;
         }
      }

      return false;
   }

   public static final boolean isWipeableId(int var0, int var1) {
      int var2 = -1;

      for (int var3 = 0; var3 < WIPEABLE_POLICY_GROUPS.length; var3++) {
         if (WIPEABLE_POLICY_GROUPS[var3] == var0) {
            var2 = var3;
            break;
         }
      }

      if (var2 == -1) {
         return false;
      }

      int[][] var5 = WIPEABLE_POLICY_IDS[var2];
      if (var5[0] == WIPE_ALL) {
         return true;
      }

      for (int var4 = 0; var4 < var5.length; var4++) {
         if (var5[var4] == var1) {
            return true;
         }
      }

      return false;
   }
}
