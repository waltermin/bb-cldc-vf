package net.rim.device.api.system;

import net.rim.device.internal.system.CoverageInfoInternal;

public class CoverageInfo {
   public static final int COVERAGE_NONE;
   public static final int COVERAGE_CARRIER;
   public static final int COVERAGE_MDS;
   public static final int COVERAGE_BIS_B;

   private CoverageInfo() {
   }

   public static void addListener(CoverageStatusListener var0) {
      addListener(Application.getApplication(), var0);
   }

   public static void addListener(Application var0, CoverageStatusListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void removeListener(CoverageStatusListener var0) {
      removeListener(Application.getApplication(), var0);
   }

   public static void removeListener(Application var0, CoverageStatusListener var1) {
      var0.removeListener(9, var1);
   }

   public static int getCoverageStatus() {
      return getCoverageStatus(RadioInfo.getSupportedWAFs(), true);
   }

   public static int getCoverageStatus(int var0, boolean var1) {
      return CoverageInfoInternal.getInstance().getCoverage(var0, var1);
   }

   public static boolean isCoverageSufficient(int var0) {
      return isCoverageSufficient(var0, RadioInfo.getSupportedWAFs(), true);
   }

   public static boolean isCoverageSufficient(int var0, int var1, boolean var2) {
      return CoverageInfoInternal.getInstance().isCoverageSufficient(var0, var1, var2);
   }

   public static boolean isOutOfCoverage() {
      return isOutOfCoverage(RadioInfo.getSupportedWAFs(), true);
   }

   public static boolean isOutOfCoverage(int var0, boolean var1) {
      return getCoverageStatus(var0, var1) == 0;
   }
}
