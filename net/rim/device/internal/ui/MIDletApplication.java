package net.rim.device.internal.ui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.resources.Resource;
import net.rim.device.resources.Resource$Internal;

public class MIDletApplication extends UiApplication {
   public void updateScreen() {
      throw null;
   }

   public void addPushRegistry(String var1, String var2) {
      throw null;
   }

   public void removePushRegistry(String var1, String var2) {
      throw null;
   }

   public void shutdownWorkerThread(String var1) {
      throw null;
   }

   public void exit() {
      throw null;
   }

   public void registerAlarm(Runnable var1) {
      throw null;
   }

   public void bringToForeground() {
      throw null;
   }

   public void setForegroundable(boolean var1) {
      throw null;
   }

   public static final String getAppProperty(String var0, String var1, boolean var2) {
      if (var1 == null) {
         throw new Object();
      }

      Resource var3 = Resource$Internal.getResourceClass(var0, var2);
      if (var3 != null) {
         byte[] var4 = var3.getProperty(var1);
         if (var4 != null) {
            return (String)(new Object(var4, 2, var4.length - 2));
         }
      }

      return null;
   }
}
