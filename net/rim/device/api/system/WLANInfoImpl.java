package net.rim.device.api.system;

final class WLANInfoImpl implements WLANExtendedListener, RadioStatusListener {
   private String _profileName;
   private String _ssid;
   private String _bssid;
   private int _radioBand;
   private int _dataRate;
   private int _signalLevel;
   private static final long ID;
   private static WLANInfoImpl _instance;

   private WLANInfoImpl() {
   }

   static final WLANInfoImpl getInstance() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _instance = (WLANInfoImpl)var0.getOrWaitFor(7226364515336065262L);
         if (_instance == null) {
            _instance = new WLANInfoImpl();
            var0.put(7226364515336065262L, _instance);
         }
      }

      return _instance;
   }

   final void addListener(WLANListener var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   final void removeListener(WLANListener var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   final synchronized boolean isConnected() {
      return this._ssid != null;
   }

   final synchronized WLANInfo$WLANAPInfo getAPInfo() {
      return this.isConnected()
         ? new WLANInfo$WLANAPInfo(this._profileName, this._ssid, this._bssid, this._radioBand, this._dataRate, this._signalLevel)
         : null;
   }

   private final synchronized void updateWLANInfo() {
      WLANExtendedNetInfo var1 = WLAN.getExtendedWLANNetworkInfo();
      if (var1 != null) {
         this._profileName = WLAN.getWLANSystem().getActiveProfileName();
         this._ssid = WLAN.getWLANSystem().getActiveProfileSSID();
         this._bssid = WLAN.bssidToString(WLAN.getBSSID());
         this._radioBand = var1._band;
         this._dataRate = var1._dataRateMbps;
         this._signalLevel = var1._signalRssi;
      } else {
         this._profileName = null;
         this._ssid = null;
         this._bssid = null;
         this._radioBand = -1;
         this._dataRate = -1;
         this._signalLevel = -1;
      }
   }

   private final synchronized void updateSignalLevel(int var1) {
      this._signalLevel = var1;
   }

   @Override
   public final void wlanExtendedInfoChange() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public final void radioStatus(boolean var1) {
   }

   @Override
   public final void networkSuccess() {
   }

   @Override
   public final void networkFail(int var1, int var2, int var3) {
   }

   @Override
   public final void networkFound(int var1) {
   }

   @Override
   public final void networkApChange() {
   }

   @Override
   public final void wlanChallengeOccurred(int var1) {
   }

   @Override
   public final void wlanRecordChangeOccurred(int var1) {
   }

   @Override
   public final void signalLevel(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public final void networkStarted(int var1, int var2) {
   }

   @Override
   public final void baseStationChange() {
   }

   @Override
   public final void radioTurnedOff() {
   }

   @Override
   public final void pdpStateChange(int var1, int var2, int var3) {
   }

   @Override
   public final void networkStateChange(int var1) {
   }

   @Override
   public final void networkScanComplete(boolean var1) {
   }

   @Override
   public final void networkServiceChange(int var1, int var2) {
   }
}
