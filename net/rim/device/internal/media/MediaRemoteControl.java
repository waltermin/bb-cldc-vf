package net.rim.device.internal.media;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.vm.Message;

public class MediaRemoteControl {
   public static void addListener(Application var0, MediaRemoteControlListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void removeListener(Application var0, MediaRemoteControlListener var1) {
      var0.removeListener(28, var1);
   }

   public static void postPanelEvent(int var0, int var1) {
      Object var2 = new Object(28, var0, var1);
      ApplicationManagerInternal var3 = (ApplicationManagerInternal)ApplicationManager.getApplicationManager();
      var3.postMessage((Message)var2);
   }
}
