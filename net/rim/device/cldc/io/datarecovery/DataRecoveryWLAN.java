package net.rim.device.cldc.io.datarecovery;

import net.rim.device.api.system.GAN;
import net.rim.device.api.system.GANSystem;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLAN;
import net.rim.device.api.system.WLANListenerInternal;
import net.rim.device.api.system.WLANSystem;
import net.rim.device.api.util.Arrays;
import net.rim.device.internal.crypto.vpn.VPN;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.system.DataServices;

public final class DataRecoveryWLAN extends DataRecovery implements GlobalEventListener, WLANListenerInternal {
   private int _lastNetwork = -1;
   private int[] _errorLevels = new int[5];
   private long _timeOnTheNetwork;
   private static final int THRESHOLD_RECOVERY_WIFI;
   private static final int START_RECOVERY_BACKOFF_WIFI;
   private static final int MAX_RECOVERY_BACKOFF_WIFI;
   private static final int MIN_RECOVERY_WIFI;

   public DataRecoveryWLAN(int var1, long var2) {
      super(var1, var2);
      Proxy.getInstance().addRadioListener(this);
   }

   @Override
   public final void radioStatus(boolean var1) {
      if (!var1) {
         this.networkFail(1, 0, 0);
      }
   }

   @Override
   public final void networkSuccess() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void networkFail(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void networkFound(int var1) {
   }

   @Override
   public final void networkApChange() {
   }

   @Override
   public final void fileReport(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final synchronized boolean isConnectionAvailable() {
      return WLAN.isWLANAllowed() && this.getCurrentNetworkId() != -1 && this.getErrorLevel() < 4;
   }

   private final int getErrorLevel() {
      return super._errorLevel;
   }

   private final void resetErrorLevel() {
      this.resetErrorLevel(-1);
      super._errorLevel = 0;
   }

   private final void incrementErrorLevel() {
      super._errorLevel++;
   }

   private final void resetErrorLevel(int var1) {
      if (var1 < 0) {
         Arrays.fill(this._errorLevels, 0);
      } else {
         if (var1 < this._errorLevels.length) {
            this._errorLevels[var1] = 0;
         }
      }
   }

   private final int getTotalErrorLevel(int var1) {
      if (var1 == -1) {
         return this._errorLevels[1] + this._errorLevels[2] + this._errorLevels[3] + this._errorLevels[4];
      } else {
         return var1 >= 0 && var1 < this._errorLevels.length ? this._errorLevels[var1] : 0;
      }
   }

   private final void incrementErrorLevel(int var1) {
      if (var1 >= 0 && var1 < this._errorLevels.length) {
         this._errorLevels[var1] = Math.min(this._errorLevels[var1] + 1, 4);
         if ((this._errorLevels[1] >= 4 || this.serviceDisabled(1))
            && (this._errorLevels[2] >= 4 || this.serviceDisabled(2))
            && (this._errorLevels[3] >= 4 || this.serviceDisabled(3))
            && (this._errorLevels[4] >= 4 || this.serviceDisabled(4))) {
            super._errorLevel = 4;
         }
      }
   }

   private final boolean ignoreReport(int var1, int var2) {
      if (var1 != super._linkType) {
         return false;
      } else {
         return this.getCurrentNetworkId() == -1 ? false : this.serviceDisabled(var2);
      }
   }

   private final int getCurrentNetworkId() {
      WLANSystem var1 = WLAN.getWLANSystem();
      return var1 != null ? var1.isAssociated() : -1;
   }

   private final boolean doesNetworkExist(int var1) {
      WLANSystem var2 = WLAN.getWLANSystem();
      return var2 != null && var2.getProfileSSID(var1) != null ? var2.getWLANNetworkInfo(var2.getActiveProfileSet(), var1) != null : false;
   }

   private final boolean serviceDisabled(int var1) {
      switch (var1) {
         case 0:
            return true;
         case 1:
         default:
            boolean var6 = !DataServices.getInstance().isDataServicesEnabled(2, 1);
            if (!var6) {
               WLANSystem var9 = WLAN.getWLANSystem();
               if (var9 != null) {
                  var6 = !var9.isBlackberryInfrastructureConnectionProvisioned();
               }
            }

            return var6;
         case 2:
            boolean var5 = !DataServices.getInstance().isDataServicesEnabled(2, 2) || VPN.isIPSecRequiredForNetwork(null, 6) && !VPN.isConnected();
            if (!var5) {
               WLANSystem var8 = WLAN.getWLANSystem();
               if (var8 != null) {
                  var5 = !var8.isEnterpriseConnectionProvisioned();
               }
            }

            return var5;
         case 3:
            boolean var4 = !GAN.isGANAllowed();
            if (!var4) {
               GANSystem var7 = GAN.getGANSystem();
               if (var7 != null) {
                  var4 = !var7.isGANActive();
               }
            }

            return var4;
         case 4:
            boolean var2 = !VPN.isVPNAllowed() || !VPN.isIPSecRequiredForNetwork(null, 6) || VPN.isConnected() && !VPN.livenessCheckEnabled();
            if (!var2) {
               if (DataServices.getInstance().isDataServicesEnabled(2, 2)) {
                  WLANSystem var3 = WLAN.getWLANSystem();
                  if (var3 != null) {
                     return !var3.isEnterpriseConnectionProvisioned();
                  }
               } else {
                  var2 = false;
               }
            }

            return var2;
      }
   }

   private final boolean serviceAvailable(int var1) {
      if (var1 == -1) {
         if ((RadioInfo.getNetworkService() & 16384) != 0) {
            return true;
         }

         WLANSystem var4 = WLAN.getWLANSystem();
         return var4 != null ? var4.isBlackberryInfrastructureConnectionAvailable() || var4.isEnterpriseConnectionAvailable() : false;
      } else {
         switch (var1) {
            case 0:
               return false;
            case 1:
            default:
               WLANSystem var3 = WLAN.getWLANSystem();
               if (var3 != null && var3.isBlackberryInfrastructureConnectionAvailable()) {
                  return true;
               }

               return false;
            case 2:
               WLANSystem var2 = WLAN.getWLANSystem();
               if (var2 != null && var2.isEnterpriseConnectionAvailable()) {
                  return true;
               }

               return false;
            case 3:
               if ((RadioInfo.getNetworkService() & 16384) != 0) {
                  return true;
               }

               return false;
            case 4:
               return VPN.isConnected();
         }
      }
   }
}
