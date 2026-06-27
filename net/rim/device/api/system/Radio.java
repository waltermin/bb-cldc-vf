package net.rim.device.api.system;

import net.rim.device.internal.crypto.CryptoBlock;
import net.rim.device.internal.system.RadioInternal;
import net.rim.device.internal.system.USBPasswordRedirectManager;
import net.rim.vm.OTAUpgrade;
import net.rim.vm.TraceBack;

public final class Radio {
   private static final long ID;
   private static Radio _instance;

   private Radio() {
   }

   public static final boolean activateWAFs(int var0) {
      RadioInternal.assertWAFAccessPermission(var0);
      if (var0 == 0) {
         return true;
      }

      if (!CryptoBlock.areMasterKeysAvailable()) {
         return false;
      }

      if (!USBPasswordRedirectManager.getInstance().isRadioAllowedOn()) {
         return false;
      }

      if (!OTAUpgrade.isRadioAllowedOn()) {
         return false;
      }

      if ((var0 & 4) != 0 && !WLAN.isWLANAllowed()) {
         if (var0 == 4) {
            return false;
         }

         var0 &= -5;
      }

      int var1 = RadioInternal.get3GPPEnabledRats();
      if ((var0 & 1) != 0) {
         if (RadioInternal.getGANPreference() == 2) {
            return false;
         }

         var1 |= RadioInternal.get3GPPSupportedRats() & -5;
      }

      if ((var0 & 4) != 0 && GAN.isGANAllowed()) {
         var1 |= RadioInternal.get3GPPSupportedRats() & 4;
      }

      RadioInternal.set3GPPEnabledRats(var1);
      int var2 = RadioInfo.getNetworkType();
      if (var2 != 7 && var2 != 4) {
         int var3 = RadioInternal.getNetworkSelectionMode();
         if ((1 << var3 & RadioInternal.getAvailableNetworkSelectionModes()) == 0) {
            RadioInternal.setNetworkSelectionMode(0);
         } else {
            RadioInternal.setNetworkSelectionMode(var3);
         }
      }

      RadioInternal.set3GPPRatConfig(RadioInternal.get3GPPActiveRats(), RadioInternal.get3GPPRATPreference(RadioInternal.getGANPreference()));
      if (RadioInfo.areWAFsSupported(1)) {
         if (RadioInternal.get3GPPActiveRats() != 0 && (RadioInfo.getEnabledWAFs() & 1) != 0) {
            var0 |= 1;
         } else {
            var0 &= -2;
         }
      }

      RadioInternal.activateWAFsInternal(var0);
      return true;
   }

   public static final void deactivateWAFs(int var0) {
      RadioInternal.assertWAFAccessPermission(var0);
      int var1 = RadioInternal.get3GPPEnabledRats();
      if ((var0 & 1) != 0) {
         var1 &= ~(RadioInternal.get3GPPSupportedRats() & -5);
      }

      if ((var0 & 4) != 0) {
         var1 &= ~(RadioInternal.get3GPPSupportedRats() & 4);
      }

      RadioInternal.set3GPPEnabledRats(var1);
      RadioInternal.set3GPPRatConfig(RadioInternal.get3GPPActiveRats(), RadioInternal.get3GPPRATPreference(RadioInternal.getGANPreference()));
      if (RadioInfo.areWAFsSupported(1)) {
         if (RadioInternal.get3GPPActiveRats() == 0) {
            var0 |= 1;
         } else {
            var0 &= -2;
            if (var0 == 0) {
               return;
            }
         }
      }

      RadioInternal.deactivateWAFsInternal(var0);
   }

   public static final boolean setEnabledWAFs(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return setEnabledWAFsInternal(var0);
   }

   private static final native boolean setEnabledWAFsInternal(int var0);

   public static final boolean powerOn() {
      return RadioInternal.activateRadios(RadioInternal.getEnabledRadios());
   }

   public static final void requestPowerOn() {
      powerOn();
   }

   public static final void requestPowerOff() {
      RadioInternal.deactivateRadios(RadioInternal.getSupportedRadios());
   }
}
