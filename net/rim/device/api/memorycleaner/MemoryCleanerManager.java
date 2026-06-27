package net.rim.device.api.memorycleaner;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.listener.EventListenerManager;
import net.rim.device.api.synchronization.SyncEventListener;
import net.rim.device.api.synchronization.SyncManager;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.HolsterListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RealtimeClockListener;
import net.rim.device.api.system.SystemListener;
import net.rim.device.api.util.Comparator;
import net.rim.device.internal.proxy.Proxy;
import net.rim.vm.Memory;

public final class MemoryCleanerManager implements HolsterListener, RealtimeClockListener, GlobalEventListener, SyncEventListener, SystemListener, Comparator {
   private EventListenerManager _listeners;
   private MemoryCleanerSettings _settings;
   private PersistentObject _settingsHolder;
   private long _lastProcessedUserAction;
   private boolean _userEnabledSecureOldObjects;
   private boolean _memoryCleanerSecureOldObjects;
   private boolean _persistentContentSecureOldObjects;
   private boolean _cryptoAPISecureOldObjects;
   private boolean _SMIMESecureOldObjects;
   private boolean _PGPSecureOldObjects;
   private boolean _registeredListeners;
   private boolean _otaSyncOperationStopped;
   private MemoryCleanerManager$MemoryCleanerSyncItem _syncItem;
   private MemoryCleanerManager$ClipboardMemoryCleaner _clipboardMemoryCleaner;
   private static final long MINIMUM_TIMEOUT_SECONDS;
   private static final long PERSISTENT_STORE_KEY;
   private static final long SINGLETON_ID;
   private static final long LOGGER_GUID;
   public static final long SHOW_APP_ON_RIBBON_CHANGED;
   private static final int[] EVENT_LOGGER_CODES;

   public final void registerWithSyncManager() {
      SyncManager var1 = SyncManager.getInstance();
      if (var1 != null) {
         var1.enableSynchronization(this._syncItem);
      }
   }

   public final synchronized void setMemoryCleanerSecureOldObjects(boolean var1) {
      this._memoryCleanerSecureOldObjects |= var1;
      this.setSecureOldObjects();
   }

   public final synchronized void setPersistentContentSecureOldObjects(boolean var1) {
      this._persistentContentSecureOldObjects = var1;
      this.setSecureOldObjects();
   }

   public final synchronized void setCryptoAPISecureOldObjects(boolean var1) {
      byte var2 = ITPolicy.getByte(24, 39, (byte)1);
      if (var2 > 1) {
         this._cryptoAPISecureOldObjects |= var1;
         this.setSecureOldObjects();
      }
   }

   public final synchronized void setSMIMESecureOldObjects(boolean var1) {
      this._SMIMESecureOldObjects = var1;
      this.setSecureOldObjects();
   }

   public final synchronized void setPGPSecureOldObjects(boolean var1) {
      this._PGPSecureOldObjects = var1;
      this.setSecureOldObjects();
   }

   public final boolean userEnabled() {
      return this._userEnabledSecureOldObjects
         && !this._memoryCleanerSecureOldObjects
         && !this._persistentContentSecureOldObjects
         && !this._cryptoAPISecureOldObjects
         && !this._SMIMESecureOldObjects
         && !this._PGPSecureOldObjects;
   }

   public final boolean enabled() {
      return this._userEnabledSecureOldObjects
         || this._memoryCleanerSecureOldObjects
         || this._persistentContentSecureOldObjects
         || this._cryptoAPISecureOldObjects
         || this._SMIMESecureOldObjects
         || this._PGPSecureOldObjects;
   }

   public final synchronized void addListener(MemoryCleanerListener var1, boolean var2, boolean var3) {
      if (var1 == null) {
         throw new Object();
      }

      this._listeners.add(var1, var2);
      this.setMemoryCleanerSecureOldObjects(var3);
   }

   public final synchronized void removeListener(MemoryCleanerListener var1) {
      this._listeners.remove(var1);
   }

   public final void setUserCleanEnabled(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized boolean getUserCleanEnabled() {
      return this._settings._userCleanEnabled;
   }

   public final void setCleanWhenHolstered(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized boolean getCleanWhenHolstered() {
      return this._settings._cleanWhenHolstered;
   }

   public final void setShowAppOnRibbon(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized boolean getShowAppOnRibbon() {
      return this._settings._showAppOnRibbon;
   }

   public final void setCleanWhenIdle(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized boolean getCleanWhenIdle() {
      return this._settings._cleanWhenIdle;
   }

   public final void setIdleTimeout(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized long getIdleTimeout() {
      return this._settings._idleTimeoutSeconds * 1000;
   }

   public final synchronized MemoryCleanerListener[] getListeners() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final boolean isUpdateComplete() {
      return this._listeners.isUpdateComplete();
   }

   public final void cleanAll() {
      this.notifyAllListeners(7);
   }

   public final void cleanPersistentContent() {
      this.notifyAllListeners(10);
   }

   @Override
   public final int compare(Object var1, Object var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void inHolster() {
      if (this._settings._cleanWhenHolstered) {
         this.notifyAllListeners(0);
      }
   }

   @Override
   public final void outOfHolster() {
   }

   @Override
   public final void clockUpdated() {
      if (this._settings._cleanWhenIdle) {
         long var1 = DeviceInfo.getIdleTime();
         if (var1 > this._settings._idleTimeoutSeconds - 60) {
            long var3 = System.currentTimeMillis();
            long var5 = var3 - var1 * 1000;
            if (var5 > this._lastProcessedUserAction) {
               this._lastProcessedUserAction = var5 + 1000;
               this.notifyAllListeners(1);
            }
         }
      }

      if (this._otaSyncOperationStopped) {
         this._otaSyncOperationStopped = false;
         this.notifyAllListeners(9);
      }
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 8877632280522743328L || var1 == 3596208183088439728L) {
         this.notifyAllListeners(5);
         this._lastProcessedUserAction = 0;
      } else if (var1 == -7131874474196788121L) {
         this.notifyAllListeners(6);
      } else {
         if (var1 == 8508406279413621091L || var1 == -594020114676189989L) {
            this.setCleanWhenHolstered(this.getCleanWhenHolstered());
            this.setCleanWhenIdle(this.getCleanWhenIdle());
            this.setIdleTimeout(this.getIdleTimeout());
            this.notifyAllListeners(11);
         }
      }
   }

   @Override
   public final void syncEventOccurred(int var1, Object var2) {
      switch (var1) {
         case 1:
         default:
            this.notifyAllListeners(2);
            return;
         case 2:
            this.notifyAllListeners(3);
            return;
         case 4:
            this._otaSyncOperationStopped = true;
         case 0:
         case 3:
      }
   }

   @Override
   public final void powerOff() {
      this.notifyAllListeners(8);
   }

   @Override
   public final void powerUp() {
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

   private final synchronized void setUserEnabledSecureOldObjects(boolean var1) {
      this._userEnabledSecureOldObjects = var1;
      this.setSecureOldObjects();
   }

   public static final MemoryCleanerManager getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      MemoryCleanerManager var1 = (MemoryCleanerManager)var0.getOrWaitFor(-63698109761663168L);
      if (var1 == null) {
         var1 = new MemoryCleanerManager();
         var0.put(-63698109761663168L, var1);
      }

      return var1;
   }

   private final void resetOptions() {
      this._settings._userCleanEnabled = this.enabled();
      this._settings._cleanWhenHolstered = true;
      this._settings._cleanWhenIdle = true;
      this._settings._idleTimeoutSeconds = 300;
      this._settings._showAppOnRibbon = false;
   }

   private MemoryCleanerManager() {
   }

   private final void notifyAllListeners(int var1) {
      if (this.enabled()) {
         this._listeners.update(new MemoryCleanerManager$MemoryCleanerEvent(var1));
      }
   }

   private final void setSecureOldObjects() {
      if (this.enabled()) {
         if (!this._registeredListeners) {
            Memory.setSecureOldObjects(true);
            Proxy.getInstance().invokeLater(new MemoryCleanerManager$AddListeners(this, null));
            this._registeredListeners = true;
            return;
         }
      } else if (this._registeredListeners) {
         Memory.setSecureOldObjects(false);
         Proxy.getInstance().invokeLater(new MemoryCleanerManager$RemoveListeners(this, null));
         this._registeredListeners = false;
      }
   }
}
