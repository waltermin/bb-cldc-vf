package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;

public class WLANInfo {
   public static final int WLAN_BAND_A;
   public static final int WLAN_BAND_B;
   public static final int WLAN_BAND_BG;
   public static final int WLAN_STATE_CONNECTED;
   public static final int WLAN_STATE_DISCONNECTED;

   private WLANInfo() {
   }

   public static void addListener(WLANListener var0) {
      if (var0 == null) {
         throw new Object();
      }

      if (WLAN.isSupported()) {
         ApplicationControl.assertWiFiPermitted(true, CommonResource.getBundle(), 10165);
         WLANInfoImpl.getInstance().addListener(var0);
      }
   }

   public static void removeListener(WLANListener var0) {
      if (WLAN.isSupported()) {
         WLANInfoImpl.getInstance().removeListener(var0);
      }
   }

   public static int getWLANState() {
      return WLAN.isSupported() && WLANInfoImpl.getInstance().isConnected() ? 4620 : 4621;
   }

   public static WLANInfo$WLANAPInfo getAPInfo() {
      if (WLAN.isSupported()) {
         ApplicationControl.assertWiFiPermitted(true, CommonResource.getBundle(), 10165);
         return WLANInfoImpl.getInstance().getAPInfo();
      } else {
         return null;
      }
   }
}
