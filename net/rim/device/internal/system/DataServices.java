package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.RadioStatusListener;
import net.rim.device.api.system.SystemListener2;

public final class DataServices implements RadioStatusListener, SystemListener2 {
   private int _mode;
   private int _modeWiFiRouter;
   private int _modeWiFiRelay;
   private boolean _roaming;
   public static final long GUID;
   public static final int LINK_TYPE_RF;
   public static final int LINK_TYPE_WIFI;
   public static final int LINK_TYPE_SERIAL;
   public static final int CONNECTION_TYPE_ANY;
   public static final int CONNECTION_TYPE_RELAY;
   public static final int CONNECTION_TYPE_ROUTER;
   public static final int MODE_UNSUPPORTED;
   public static final int MODE_OFF;
   public static final int MODE_ON;
   public static final int MODE_ROAM;
   public static final long MODE_RF_ID;
   public static final long MODE_WIFI_ROUTER_ID;
   public static final long MODE_WIFI_RELAY_ID;
   private static final int PERSIST_ID_MODE_RF;
   private static final int PERSIST_ID_MODE_WIFI_ROUTER;
   private static final int PERSIST_ID_MODE_WIFI_RELAY;
   private static DataServices _instance;

   public static final DataServices getInstance() {
      if (_instance != null) {
         return _instance;
      }

      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      _instance = (DataServices)var0.getOrWaitFor(-3556743465989743742L);
      if (_instance == null) {
         _instance = new DataServices();
         var0.put(-3556743465989743742L, _instance);
      }

      return _instance;
   }

   private DataServices() {
   }

   public final boolean isDataServicesEnabled() {
      return this.isDataServicesEnabled(1, 1);
   }

   public final boolean isDataServicesEnabled(int var1) {
      return this.isDataServicesEnabled(var1, 0);
   }

   public final boolean isDataServicesEnabled(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getMode() {
      return this.getMode(1, 1);
   }

   public final int getMode(int var1) {
      return this.getMode(var1, 0);
   }

   public final int getMode(int var1, int var2) {
      switch (var1) {
         case 0:
            throw new Object();
         case 1:
         default:
            switch (var2) {
               case -1:
                  return 2;
               case 0:
               case 1:
               case 2:
               default:
                  return this._mode;
            }
         case 2:
            switch (var2) {
               case -1:
                  return 2;
               case 0:
                  if (this._modeWiFiRelay != 1 && this._modeWiFiRouter != 1) {
                     return 2;
                  }

                  return 1;
               case 1:
               default:
                  return this._modeWiFiRelay;
               case 2:
                  return this._modeWiFiRouter;
            }
         case 3:
            switch (var2) {
               case 0:
               case 2:
                  return 1;
               default:
                  return 2;
            }
      }
   }

   public final void setMode(int var1) {
      this.setMode(var1, 1, 1);
   }

   public final void setMode(int var1, int var2, int var3) {
      switch (var2) {
         case 0:
            return;
         case 1:
         default:
            this.setModeRF(var1, 1);
            return;
         case 2:
            this.setModeWiFi(var1, var3);
      }
   }

   private final void setModeRF(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void setModeWiFi(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean getRoaming() {
      return this._roaming;
   }

   private final void setRoaming(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void networkStarted(int var1, int var2) {
      this.setRoaming(var2);
   }

   @Override
   public final void networkServiceChange(int var1, int var2) {
      this.setRoaming(var2);
   }

   @Override
   public final void radioTurnedOff() {
      this.setRoaming(0);
   }

   @Override
   public final void fastReset() {
      RadioInternal.setDataServiceMode(this._mode);
      this.setRoaming(RadioInfo.getNetworkService());
   }

   @Override
   public final void powerUp() {
      this.setRoaming(RadioInfo.getNetworkService());
   }

   @Override
   public final void signalLevel(int var1) {
   }

   @Override
   public final void baseStationChange() {
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
   public final void powerOff() {
   }

   @Override
   public final void batteryLow() {
   }

   @Override
   public final void batteryGood() {
   }

   @Override
   public final void batteryStatusChange(int var1) {
   }

   @Override
   public final void powerOffRequested(int var1) {
   }

   @Override
   public final void cradleMismatch(boolean var1) {
   }

   @Override
   public final void backlightStateChange(boolean var1) {
   }

   @Override
   public final void usbConnectionStateChange(int var1) {
   }
}
