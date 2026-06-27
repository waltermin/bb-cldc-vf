package net.rim.device.api.gps;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationProcess;
import net.rim.device.api.system.Branding;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntIntHashtable;
import net.rim.vm.Process;

public final class GPSRegistry {
   private GPSRegistry$GPSListenerImpl _gpsListener;
   private IntIntHashtable _autoFixConsumers;
   private IntIntHashtable _assistFixConsumers;
   private int _autoGCD;
   private int _assistGCD;
   private IntHashtable _pdeTable;
   private IntHashtable _criteriaTable;
   private int _gcd;
   private boolean _simulateGPSPuck;
   private GPSLocationStandard _standardLocation;
   private GPSLocationExtended _extendedLocation;
   private boolean _pdeRequestSuccess;
   private long _lastLogEntryTime;
   Object _locationLock;
   private static final long REGISTRY_NAME;
   private static final long LAST_FIX_TIME;
   public static final long FIX_REQUESTED;
   private static GPSRegistry _registry;
   private static int _maxGPSInterval;
   private static Object _pdeLock;
   private static final long LOG_ENTRY_INTERVAL;
   private static final long EVENT_LOGGER_GUID;
   private static final String EVENT_LOGGER_NAME;
   public static final long LOCATION_EVENT;
   public static final long GPS_TEMPORARILY_UNAVAILABLE;
   private static PersistentObject _lastFixTimeStore;
   private static long[] _lastFixTimes;

   private GPSRegistry() {
   }

   public static final void initialize() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final GPSRegistry getInstance() {
      return _registry;
   }

   public final boolean getSimulateGPSPuck() {
      return this._simulateGPSPuck;
   }

   public final void setSimulateGPSPuck(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final Object getLocationLock() {
      return this._locationLock;
   }

   public final GPSLocation getLocation(int var1) {
      if (var1 == 0) {
         return this._standardLocation.getUTCTime() > this._extendedLocation.getUTCTime() ? this._standardLocation : this._extendedLocation;
      }

      if (isVerizon() && var1 == 1) {
         int var2 = Application.getApplication().getProcessId();
         GPSRegistry$PDEInfoStatus var3 = (GPSRegistry$PDEInfoStatus)this._pdeTable.get(var2);
         if (var3 == null || !var3.getCredStatus()) {
            return null;
         }
      }

      return this._extendedLocation;
   }

   public final long getLastFixTime(int var1) {
      switch (var1) {
         case -1:
            return 0;
         case 0:
            return _lastFixTimes[2];
         case 1:
            return _lastFixTimes[1];
         case 2:
         default:
            return _lastFixTimes[0];
      }
   }

   public static final boolean isVerizon() {
      return Branding.getVendorId() == 105 || Branding.getVendorId() == 226;
   }

   public final boolean getCredentialStatus(int var1) {
      GPSRegistry$PDEInfoStatus var2 = (GPSRegistry$PDEInfoStatus)this._pdeTable.get(var1);
      return var2 != null ? var2.getCredStatus() : false;
   }

   public final synchronized boolean startLocationUpdate(int var1, int var2, GPS$AppCriteria var3) {
      if (!GPS.isSupportedOnCurrentNetwork()) {
         return false;
      }

      if (var3 == null) {
         var3 = new Object();
      }

      if (var2 == 2) {
         _lastFixTimes[0] = System.currentTimeMillis();
      } else if (var2 == 1) {
         _lastFixTimes[1] = System.currentTimeMillis();
      } else {
         _lastFixTimes[2] = System.currentTimeMillis();
      }

      _lastFixTimeStore.commit();
      RIMGlobalMessagePoster.postGlobalEvent(-2101050060249085778L, var2, 0);
      if (var2 == 0) {
         return GPS.startLocationUpdate(0, var2, 0, null);
      }

      int var4 = Application.getApplication().getProcessId();
      if (RadioInfo.getNetworkType() == 4 && var2 == 1) {
         this._criteriaTable.put(var4, var3);
         this.setPDEInfo(var4);
      }

      IntIntHashtable var5 = null;
      if (var2 == 2) {
         var5 = this._autoFixConsumers;
      } else {
         if (var2 != 1) {
            return false;
         }

         var5 = this._assistFixConsumers;
      }

      if (var1 == 0) {
         if (var5.size() == 0) {
            return GPS.startLocationUpdate(3, var2, 0, (GPS$AppCriteria)var3);
         } else if (var2 == 1) {
            return GPS.startLocationUpdate(3, var2, this._assistGCD, (GPS$AppCriteria)var3);
         } else {
            return var2 == 2 ? GPS.startLocationUpdate(3, var2, this._autoGCD, (GPS$AppCriteria)var3) : false;
         }
      } else {
         if (!var5.contains(var4)) {
            Object var6 = Process.currentProcess();
            ((ApplicationProcess)var6).addCleanupRunnable(new GPSRegistry$GPSCleanerRunnable(this, var4, var2));
         }

         var5.put(var4, var1);
         int var8 = this.calculateGCD(var5.elements());
         if (var2 == 2) {
            this._autoFixConsumers = var5;
            this._autoGCD = var8;
         } else if (var2 == 1) {
            this._assistFixConsumers = var5;
            this._assistGCD = var8;
         }

         this._gcd = this.getCommonGCD();
         this._gpsListener._errorCount = 0;
         return GPS.startLocationUpdate(3, var2, var8, (GPS$AppCriteria)var3);
      }
   }

   private final synchronized void restartCDMAAssistedLocationUpdate() {
      if (this._assistFixConsumers.size() != 0) {
         this._assistGCD = this.calculateGCD(this._assistFixConsumers.elements());
         this._gcd = this.getCommonGCD();
         Object var1 = null;
         IntEnumeration var2 = this._assistFixConsumers.keys();
         if (var2.hasMoreElements()) {
            int var3 = var2.nextElement();
            this.setPDEInfo(var3);
            var1 = this._criteriaTable.get(var3);
         }

         GPS.startLocationUpdate(3, 1, this._assistGCD, (GPS$AppCriteria)var1);
      }
   }

   private final int calculateGCD(int var1, int var2) {
      while (var2 != 0) {
         int var3 = var2;
         var2 = var1 % var2;
         var1 = var3;
      }

      return var1;
   }

   private final int calculateGCD(IntEnumeration var1) {
      int var2 = 0;

      while (var1.hasMoreElements()) {
         if (var2 == 0) {
            var2 = var1.nextElement();
         } else {
            var2 = this.calculateGCD(var2, var1.nextElement());
         }
      }

      if (var2 > _maxGPSInterval) {
         for (int var3 = _maxGPSInterval; var3 > 0; var3--) {
            if (var2 % var3 == 0) {
               return var3;
            }
         }
      }

      return var2;
   }

   public final synchronized boolean stopLocationUpdate(int var1) {
      if (var1 == 0) {
         return true;
      }

      int var2 = Application.getApplication().getProcessId();
      return this.stopLocationUpdate(var1, var2);
   }

   private final boolean stopLocationUpdate(int var1, int var2) {
      IntIntHashtable var3 = null;
      if (var1 == 2) {
         var3 = this._autoFixConsumers;
      } else {
         if (var1 != 1) {
            return false;
         }

         var3 = this._assistFixConsumers;
      }

      if (!var3.containsKey(var2)) {
         if (var3.size() == 0) {
            GPS.stopLocationUpdate(var1);
         }

         return true;
      } else {
         var3.remove(var2);
         if (var3.size() == 0) {
            if (var1 == 2) {
               this._autoGCD = 0;
            } else if (var1 == 1) {
               this._assistGCD = 0;
            }

            return GPS.stopLocationUpdate(var1);
         } else {
            int var4 = this.calculateGCD(var3.elements());
            if (var1 == 2) {
               this._autoFixConsumers = var3;
               this._autoGCD = var4;
            } else if (var1 == 1) {
               this._assistFixConsumers = var3;
               this._assistGCD = var4;
            }

            this._criteriaTable.remove(var2);
            this._gcd = this.getCommonGCD();
            Object var5 = new Object();
            if (RadioInfo.getNetworkType() == 4 && var1 == 1) {
               this._pdeTable.remove(var2);
               if (this._assistFixConsumers.size() != 0) {
                  IntEnumeration var6 = this._assistFixConsumers.keys();
                  if (var6.hasMoreElements()) {
                     int var7 = var6.nextElement();
                     this.setPDEInfo(var7);
                     var5 = this._criteriaTable.get(var7);
                  }
               }
            }

            GPS.startLocationUpdate(3, var1, var4, (GPS$AppCriteria)var5);
            return true;
         }
      }
   }

   private final int getCommonGCD() {
      if (this._autoFixConsumers.size() == 0) {
         return this._assistGCD;
      } else {
         return this._assistFixConsumers.size() == 0 ? this._autoGCD : this.calculateGCD(this._autoGCD, this._assistGCD);
      }
   }

   public final boolean addPDEInfo(GPS$GPSPDEInfo var1) {
      int var2 = Application.getApplication().getProcessId();
      GPSRegistry$PDEInfoStatus var3 = new GPSRegistry$PDEInfoStatus(var1, false, false, null);
      this._pdeTable.put(var2, var3);
      Object var4 = Process.currentProcess();
      ((ApplicationProcess)var4).addCleanupRunnable(new GPSRegistry$1(this, var2));
      return isVerizon() ? true : this.setPDEInfo(var1);
   }

   private final boolean setPDEInfo(int var1) {
      if (this._pdeTable.size() != 0 && this._pdeTable.containsKey(var1)) {
         GPSRegistry$PDEInfoStatus var2 = (GPSRegistry$PDEInfoStatus)this._pdeTable.get(var1);
         return this.setPDEInfo(var2.getPDEInfo());
      } else {
         this.logPDEFailure();
         return false;
      }
   }

   private final boolean setPDEInfo(GPS$GPSPDEInfo var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void logPDEFailure() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final int gpsGetLocation(GPSLocationStandard var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int getGCD() {
      return this._gcd;
   }

   private final void notifyListeners(int var1, long var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void restartLocationUpdate(int var1) {
      if ((var1 != 2 || this._autoFixConsumers.size() != 0) && (var1 != 1 || this._assistFixConsumers.size() != 0)) {
         if (RadioInfo.getNetworkType() == 4 && var1 == 1) {
            new GPSRegistry$2(this).start();
         } else {
            GPS.startLocationUpdate(3, var1, this._gcd, (GPS$AppCriteria)(new Object()));
         }
      }
   }
}
