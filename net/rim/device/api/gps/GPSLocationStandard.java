package net.rim.device.api.gps;

public class GPSLocationStandard extends GPSLocation {
   private long _utcTime;
   private int _latitude;
   private int _longitude;
   private int _timeToFirstFix;
   private boolean _assistDataUsed;
   private int _uncertaintyCode;

   public void reset() {
      this._utcTime = 0;
      this._latitude = 0;
      this._longitude = 0;
      this._timeToFirstFix = 0;
      this._assistDataUsed = false;
      this._uncertaintyCode = 0;
   }

   public long getUTCTime() {
      return this._utcTime;
   }

   public void setUTCTime(long var1) {
      this._utcTime = var1;
   }

   public double getLatitude() {
      if ((this._latitude & -1) == -1) {
         return (double)9221120237041090560L;
      }

      int var1 = this._latitude;
      int var2 = (var1 & -2147483648) == 0 ? 1 : -1;
      var1 &= Integer.MAX_VALUE;
      return var2 * var1 / 4696837146684686336L;
   }

   public String getLatitudeString() {
      return getLatitudeString(this.getLatitude());
   }

   public static String getLatitudeString(double var0) {
      String var2 = null;
      int var3 = var0 >= 0L ? 78 : 83;
      var0 = Math.abs(var0);
      double var4 = var0;
      int var6 = (int)var0 & 65535;
      var4 = (var4 - var6) * 4633641066610819072L;
      Object var7 = new Object();
      ((StringBuffer)var7).append((char)var3).append(' ').append(var6).append('°');
      int var8 = (int)var4;
      double var9 = var4 - var8;
      var8 = (int)(var9 * 4666723172467343360L);
      var9 = var8 / 4666723172467343360L;
      var4 = (int)var4 + var9;
      ((StringBuffer)var7).append(' ').append(var4).append('\'');
      var2 = ((StringBuffer)var7).toString();
      var7 = null;
      return var2;
   }

   public double getLongitude() {
      if ((this._longitude & -1) == -1) {
         return (double)9221120237041090560L;
      }

      int var1 = this._longitude;
      int var2 = (var1 & -2147483648) == 0 ? 1 : -1;
      var1 &= Integer.MAX_VALUE;
      return var2 * var1 / 4696837146684686336L;
   }

   public String getLongitudeString() {
      return getLongitudeString(this.getLongitude());
   }

   public static String getLongitudeString(double var0) {
      String var2 = null;
      int var3 = var0 >= 0L ? 69 : 87;
      var0 = Math.abs(var0);
      double var4 = var0;
      int var6 = (int)var0 & 65535;
      var4 = (var4 - var6) * 4633641066610819072L;
      Object var7 = new Object();
      ((StringBuffer)var7).append((char)var3).append(' ').append(var6).append('°');
      int var8 = (int)var4;
      double var9 = var4 - var8;
      var8 = (int)(var9 * 4666723172467343360L);
      var9 = var8 / 4666723172467343360L;
      var4 = (int)var4 + var9;
      ((StringBuffer)var7).append(' ').append(var4).append('\'');
      var2 = ((StringBuffer)var7).toString();
      var7 = null;
      return var2;
   }

   public int getTimeToFirstFix() {
      return this._timeToFirstFix;
   }

   public boolean wasAssistDataUsed() {
      return this._assistDataUsed;
   }

   public String getUncertaintyString() {
      return String.valueOf(this.getHorizontalUncertainty());
   }

   public double getHorizontalUncertainty() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IllegalStateException: No common supertype for ternary expression
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.getExprType(FunctionExprent.java:224)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.checkExprTypeBounds(FunctionExprent.java:372)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.checkTypeExpr(VarTypeProcessor.java:156)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.checkTypeExprent(VarTypeProcessor.java:132)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.lambda$processVarTypes$2(VarTypeProcessor.java:125)
      //   at org.jetbrains.java.decompiler.modules.decompiler.flow.DirectGraph.iterateExprents(DirectGraph.java:114)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.processVarTypes(VarTypeProcessor.java:125)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarTypeProcessor.calculateVarTypes(VarTypeProcessor.java:44)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarVersionsProcessor.setVarVersions(VarVersionsProcessor.java:68)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.setVarVersions(VarProcessor.java:47)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:241)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield net/rim/device/api/gps/GPSLocationStandard._uncertaintyCode I
      // 04: bipush -1
      // 06: iand
      // 07: bipush -1
      // 09: if_icmpne 12
      // 0c: nop
      // 0d: ldc2_w 9221120237041090560
      // 10: nop
      // 11: dreturn
      // 12: aload 0
      // 13: getfield net/rim/device/api/gps/GPSLocationStandard._uncertaintyCode I
      // 16: i2d
      // 17: nop
      // 18: ldc2_w 4636737291354636288
      // 1b: ddiv
      // 1c: nop
      // 1d: dreturn
   }
}
