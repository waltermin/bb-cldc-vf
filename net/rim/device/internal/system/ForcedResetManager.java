package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.proxy.Proxy;

public final class ForcedResetManager implements GlobalEventListener {
   private int _currentFIPSLevel = ITPolicy.getInteger(24, 39, 1);
   private static final long GUID;
   private static final long RESET_SCHEDULE_GUID;
   private static final int NUM_RESET_WARNINGS;
   private static final long TIME_BETWEEN_RESET_WARNINGS;
   private static final long SIXTY_SECONDS;
   private static ForcedResetManager _resetManager;

   public final void scheduleDeviceResetNoTimeout(String var1, int var2, long var3, boolean var5) {
      this.scheduleDeviceReset(var1, var2, var3, var5, false);
   }

   public final void scheduleDeviceReset(String var1) {
      this.scheduleDeviceReset(var1, 5, 600000, false, true);
   }

   public final void scheduleDeviceResetNoTimeout(String var1) {
      this.scheduleDeviceReset(var1, 5, 600000, false, false);
   }

   public final void scheduleDeviceReset(String var1, boolean var2) {
      this.scheduleDeviceReset(var1, 5, 600000, var2, true);
   }

   public final void scheduleDeviceResetNoTimeout(String var1, boolean var2) {
      this.scheduleDeviceReset(var1, 5, 600000, var2, false);
   }

   public final void scheduleDeviceReset(String var1, long var2, boolean var4) {
      this.scheduleDeviceReset(var1, 5, var2, var4, true);
   }

   public final void scheduleDeviceResetNoTimeout(String var1, long var2, boolean var4) {
      this.scheduleDeviceReset(var1, 5, var2, var4, false);
   }

   public final void scheduleDeviceReset(String var1, int var2, long var3, boolean var5) {
      this.scheduleDeviceReset(var1, var2, var3, var5, true);
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -594020114676189989L || var1 == 8508406279413621091L) {
         if (CodeModuleManager.verifyApplicationControlModules() == 6) {
            this.scheduleDeviceReset(CommonResource.getString(10086));
            return;
         }

         int var7 = this._currentFIPSLevel;
         this._currentFIPSLevel = ITPolicy.getInteger(24, 39, 1);
         if (var7 != this._currentFIPSLevel && (var7 == 3 || this._currentFIPSLevel == 3)) {
            this.scheduleDeviceReset(CommonResource.getString(10088));
         }
      }
   }

   public static final ForcedResetManager getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void initialize() {
      getInstance();
   }

   public ForcedResetManager() {
      Proxy.getInstance().addGlobalEventListenerInternal(this);
   }

   private final void scheduleDeviceReset(String var1, int var2, long var3, boolean var5, boolean var6) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
