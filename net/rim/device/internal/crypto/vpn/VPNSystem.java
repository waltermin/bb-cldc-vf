package net.rim.device.internal.crypto.vpn;

public class VPNSystem {
   public synchronized int connect(int var1) {
      return this.connectVPN(this.getActiveProfileSet(), var1);
   }

   public int disconnect() {
      return this.disconnectVPN(this.getActiveProfileSet());
   }

   public int abort() {
      return this.abortVPN(this.getActiveProfileSet());
   }

   public void createSession() {
      if (VPN.isSupported()) {
         this.setActiveProfileSet(this.createVPNProfileSet());
      } else {
         throw new Object();
      }
   }

   public void closeSession() {
      this.destroyVPNProfileSet(this.getActiveProfileSet());
   }

   public void resetSession() {
      throw null;
   }

   public int checkSessionStatus() {
      return this.getVPNStatus(this.getActiveProfileSet());
   }

   protected void checkSession() {
      if (this.getActiveProfileSet() == -1) {
         throw new Object();
      }
   }

   public boolean isVPNAllowed() {
      throw null;
   }

   public int getActiveProfileSet() {
      throw null;
   }

   protected int setActiveProfileSet(int var1) {
      throw null;
   }

   public int getActiveProfileId() {
      throw null;
   }

   public boolean isIPSecRequiredForNetwork(String var1, int var2) {
      throw null;
   }

   public boolean isConnected() {
      throw null;
   }

   public boolean livenessCheckEnabled() {
      throw null;
   }

   public VPNPolicy allocatePolicy() {
      throw null;
   }

   protected VPNProfile createVPNProfile(VPNPolicy var1) {
      throw null;
   }

   public int addPolicy(VPNPolicy var1) {
      return this.addVPNProfile(this.createVPNProfile(var1));
   }

   public void removePolicy(int var1) {
      this.removeVPNProfile(var1);
   }

   public String getChallenge(int[] var1) {
      this.checkSession();
      return VPN.getChallenge(this.getActiveProfileSet(), var1);
   }

   public int setResponse(String var1, String var2, int var3) {
      this.checkSession();
      return VPN.setResponse(this.getActiveProfileSet(), var1, var2, var3);
   }

   public int getSessionLifetime() {
      this.checkSession();
      return VPN.getSessionLifetime(this.getActiveProfileSet());
   }

   public String getBanner() {
      this.checkSession();
      return VPN.getBanner(this.getActiveProfileSet());
   }

   public byte[] getCertificate() {
      this.checkSession();
      return VPN.getCertificate(this.getActiveProfileSet());
   }

   public int acceptCertificate(boolean var1) {
      this.checkSession();
      return VPN.acceptCertificate(this.getActiveProfileSet(), var1);
   }

   protected final int createVPNProfileSet() {
      if (this.getActiveProfileSet() == -1) {
         return createProfileSet();
      } else {
         throw new Object();
      }
   }

   protected final int destroyVPNProfileSet(int var1) {
      this.checkSession();
      return destroyProfileSet(var1);
   }

   protected final int addVPNProfile(VPNProfile var1) {
      return addProfile(var1);
   }

   protected final int removeVPNProfile(int var1) {
      return removeProfile(var1);
   }

   protected final int connectVPN(int var1, int var2) {
      this.checkSession();
      return connect(var1, var2);
   }

   protected final int disconnectVPN(int var1) {
      this.checkSession();
      return disconnect(var1);
   }

   protected final int abortVPN(int var1) {
      this.checkSession();
      return abort(var1);
   }

   protected final int getVPNStatus(int var1) {
      return getStatus(var1);
   }

   private static native int createProfileSet();

   private static native int destroyProfileSet(int var0);

   private static native int addProfile(VPNProfile var0);

   private static native int removeProfile(int var0);

   private static native int connect(int var0, int var1);

   private static native int disconnect(int var0);

   private static native int abort(int var0);

   private static native int getStatus(int var0);
}
