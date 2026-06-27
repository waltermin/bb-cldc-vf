package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;

public class CoverageInfoInternal {
   public static final int COVERAGE_NONE;
   public static final int COVERAGE_CARRIER;
   public static final int COVERAGE_MDS;
   public static final int COVERAGE_BIS_B;
   public static final int COVERAGE_STATUS_CHANGED;
   protected static final long ID;

   public static CoverageInfoInternal getInstance() {
      CoverageInfoInternal var0 = (CoverageInfoInternal)ApplicationRegistry.getApplicationRegistry().waitFor(-809192429028495755L);
      var0.ensureInitialized();
      return var0;
   }

   protected void ensureInitialized() {
      throw null;
   }

   public int getCoverage(int var1, boolean var2) {
      throw null;
   }

   public boolean isCoverageSufficient(int var1, int var2, boolean var3) {
      throw null;
   }
}
