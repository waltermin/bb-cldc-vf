package net.rim.device.internal.vad;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.vm.Message;

public final class VADUserEvents {
   public static final void sendEvent(int var0, int var1) {
      Object var2 = new Object(6, var0, var1);
      Object var3 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var3).postMessage((Message)var2);
   }

   public static final void addListener(Application var0, VADUserEventListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, VADUserEventListener var1) {
      var0.removeListener(6, var1);
   }
}
