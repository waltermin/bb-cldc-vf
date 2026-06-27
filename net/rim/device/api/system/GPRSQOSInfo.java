package net.rim.device.api.system;

public final class GPRSQOSInfo implements QOSInfo {
   private int _preClass;
   private int _relClass;
   private int _delClass;
   private int _peakTPClass;
   private int _meanTPClass;
   public static final int DEL_SUBSCRIBED;
   public static final int DEL_HIGHEST_QUALITY;
   public static final int DEL_MEDIUM_QUALITY;
   public static final int DEL_LOWEST_QUALITY;
   public static final int DEL_BEST_EFFORT;
   public static final int REL_SUBSCRIBED;
   public static final int REL_ALL_ACKED_LLC_PROT;
   public static final int REL_GTP_NOT_ACKED_LLC_PROT;
   public static final int REL_GTP_LLC_NOT_ACKED_LLC_PROT;
   public static final int REL_NO_ACKING_LLC_PROT;
   public static final int REL_NO_ACKING_LLC_UNPROT;
   public static final int PRE_SUBSCRIBED;
   public static final int PRE_HIGHEST;
   public static final int PRE_NORMAL;
   public static final int PRE_LOWEST;
   public static final int PTP_SUBSCRIBED;
   public static final int PTP_UPTO_1K;
   public static final int PTP_UPTO_2K;
   public static final int PTP_UPTO_4K;
   public static final int PTP_UPTO_8K;
   public static final int PTP_UPTO_16K;
   public static final int PTP_UPTO_32K;
   public static final int PTP_UPTO_64K;
   public static final int PTP_UPTO_128K;
   public static final int PTP_UPTO_256K;
   public static final int MTP_SUBSCRIBED;
   public static final int MTP_100_OPH;
   public static final int MTP_200_OPH;
   public static final int MTP_500_OPH;
   public static final int MTP_1K_OPH;
   public static final int MTP_2K_OPH;
   public static final int MTP_5K_OPH;
   public static final int MTP_10K_OPH;
   public static final int MTP_20K_OPH;
   public static final int MTP_50K_OPH;
   public static final int MTP_100K_OPH;
   public static final int MTP_200K_OPH;
   public static final int MTP_500K_OPH;
   public static final int MTP_1M_OPH;
   public static final int MTP_2M_OPH;
   public static final int MTP_5M_OPH;
   public static final int MTP_10M_OPH;
   public static final int MTP_20M_OPH;
   public static final int MTP_50M_OPH;
   public static final int MTP_BEST_EFFORT;

   public GPRSQOSInfo() {
      this(0, 0, 0, 0, 0);
   }

   public GPRSQOSInfo(int var1, int var2, int var3, int var4, int var5) {
      this.setPrecedenceClass(var1);
      this.setReliabilityClass(var2);
      this.setDelayClass(var3);
      this.setPeakThroughputClass(var4);
      this.setMeanThroughputClass(var5);
   }

   public GPRSQOSInfo(GPRSQOSInfo var1) {
      this._preClass = var1._preClass;
      this._relClass = var1._relClass;
      this._delClass = var1._delClass;
      this._peakTPClass = var1._peakTPClass;
      this._meanTPClass = var1._meanTPClass;
   }

   public final int getPrecedenceClass() {
      return this._preClass;
   }

   public final void setPrecedenceClass(int var1) {
      if (var1 >= 0 && var1 <= 3) {
         this._preClass = var1;
      } else {
         throw new Object();
      }
   }

   public final int getReliabilityClass() {
      return this._relClass;
   }

   public final void setReliabilityClass(int var1) {
      if (var1 >= 0 && var1 <= 5) {
         this._relClass = var1;
      } else {
         throw new Object();
      }
   }

   public final int getDelayClass() {
      return this._delClass;
   }

   public final void setDelayClass(int var1) {
      if (var1 >= 0 && var1 <= 4) {
         this._delClass = var1;
      } else {
         throw new Object();
      }
   }

   public final int getPeakThroughputClass() {
      return this._peakTPClass;
   }

   public final void setPeakThroughputClass(int var1) {
      if (var1 >= 0 && var1 <= 9) {
         this._peakTPClass = var1;
      } else {
         throw new Object();
      }
   }

   public final int getMeanThroughputClass() {
      return this._meanTPClass;
   }

   public final void setMeanThroughputClass(int var1) {
      if ((var1 < 0 || var1 > 18) && var1 != 31) {
         throw new Object();
      }

      this._meanTPClass = var1;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final boolean isDefaultQos() {
      return this._preClass == 0 && this._relClass == 0 && this._delClass == 0 && this._peakTPClass == 0 && this._meanTPClass == 0;
   }
}
