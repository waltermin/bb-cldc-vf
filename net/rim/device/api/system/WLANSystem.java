package net.rim.device.api.system;

public class WLANSystem {
   protected boolean isWLANAllowed() {
      throw null;
   }

   protected void setWLANOverride(boolean var1) {
      throw null;
   }

   protected boolean isWLANRadioOn() {
      throw null;
   }

   public int getActiveProfileSet() {
      throw null;
   }

   public int isAssociated() {
      int var1 = this.getActiveProfileSet();
      return var1 != -1 ? this.getActiveProfileId(var1) : -1;
   }

   public WLANNetInfo[] getWLANNetworkInfo(int var1) {
      throw null;
   }

   public WLANNetInfo getWLANNetworkInfo(int var1, int var2) {
      throw null;
   }

   public final int getActiveProfileId(int var1) {
      int var2 = getCurrentProfileId(var1);
      return var2 >= 0 ? var2 : -1;
   }

   public String getActiveProfileSSID() {
      return this.getProfileSSID(this.isAssociated());
   }

   public String getActiveProfileName() {
      return this.getProfileName(this.isAssociated());
   }

   public String getActiveProfileNameOrSSID() {
      throw null;
   }

   public String getProfileSSID(int var1) {
      throw null;
   }

   public String getProfileName(int var1) {
      throw null;
   }

   public boolean isEnterpriseConnectionAvailable() {
      throw null;
   }

   public boolean isBlackberryInfrastructureConnectionAvailable() {
      throw null;
   }

   public boolean isEnterpriseConnectionProvisioned() {
      throw null;
   }

   public boolean isBlackberryInfrastructureConnectionProvisioned() {
      throw null;
   }

   protected final void registerWLANEvents() {
      this.registerWLANEvent(4609);
      this.registerWLANEvent(4610);
      this.registerWLANEvent(4618);
      this.registerWLANEvent(4619);
      this.registerWLANEvent(4620);
      this.registerWLANEvent(4621);
      this.registerWLANEvent(4623);
      this.registerWLANEvent(4622);
   }

   protected final void deregisterWLANEvents() {
      this.deregisterWLANEvent(4609);
      this.deregisterWLANEvent(4610);
      this.deregisterWLANEvent(4618);
      this.deregisterWLANEvent(4619);
      this.deregisterWLANEvent(4620);
      this.deregisterWLANEvent(4621);
      this.deregisterWLANEvent(4623);
      this.deregisterWLANEvent(4622);
   }

   protected final void registerWLANEvent(int var1) {
      register(var1);
   }

   protected final void deregisterWLANEvent(int var1) {
      deregister(var1);
   }

   protected final int createWLANProfileSet() {
      return createProfileSet();
   }

   protected final int destroyWLANProfileSet(int var1) {
      return destroyProfileSet(var1);
   }

   protected final int addWLANProfile(int var1, WLANProfile var2, boolean var3) {
      return addProfile(var1, var2, var3);
   }

   protected final int updateWLANProfile(int var1, WLANProfile var2) {
      return updateProfile(var1, var2);
   }

   protected final int flushWLANProfiles(int var1) {
      return flushProfiles(var1);
   }

   protected final int enableWLANProfileScanning(int var1) {
      return enableProfileScanning(var1);
   }

   protected final int disableWLANProfileScanning(int var1) {
      return disableProfileScanning(var1);
   }

   protected final int connectWLANProfile(int var1, int var2) {
      return connectProfile(var1, var2);
   }

   protected final int disconnectWLANProfile(int var1, int var2) {
      return disconnectProfile(var1, var2);
   }

   private static native int register(int var0);

   private static native int deregister(int var0);

   private static native int createProfileSet();

   private static native int destroyProfileSet(int var0);

   private static native int addProfile(int var0, WLANProfile var1, boolean var2);

   private static native int updateProfile(int var0, WLANProfile var1);

   private static native int flushProfiles(int var0);

   private static native int enableProfileScanning(int var0);

   private static native int disableProfileScanning(int var0);

   private static native int connectProfile(int var0, int var1);

   private static native int disconnectProfile(int var0, int var1);

   private static native int getCurrentProfileId(int var0);
}
