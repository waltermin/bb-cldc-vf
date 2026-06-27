package net.rim.device.internal.system;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.vm.Message;

public final class EventDispatchManager {
   private EventDispatcher[] _dispatchers = new EventDispatcher[59];
   private static final long GUID;

   public static final EventDispatchManager getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      EventDispatchManager var1 = (EventDispatchManager)var0.getOrWaitFor(-7708749290975591471L);
      if (var1 == null) {
         var1 = new EventDispatchManager();
         var0.put(-7708749290975591471L, var1);
      }

      return var1;
   }

   private EventDispatchManager() {
   }

   public final EventDispatcher getDispatcher(int var1) {
      return this._dispatchers[var1];
   }

   public final void setDispatcher(int var1, EventDispatcher var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void dispatch(int var1, Message var2, Object[] var3) {
      EventDispatcher var4 = this._dispatchers[var1];
      if (var4 != null && var3 != null) {
         for (int var5 = var3.length - 1; var5 >= 0; var5--) {
            var4.dispatch(var2, var3[var5]);
         }
      }
   }

   public final boolean notify(Message var1) {
      int var2 = var1.getDevice();
      return this._dispatchers[var2] == null ? true : this._dispatchers[var2].notify(var1);
   }

   public final int getNotifyProcessId(Message var1) {
      int var2 = var1.getDevice();
      return this._dispatchers[var2] == null ? -1 : this._dispatchers[var2].getNotifyProcessId(var1);
   }
}
