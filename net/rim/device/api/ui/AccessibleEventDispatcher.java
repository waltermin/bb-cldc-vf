package net.rim.device.api.ui;

import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleEventListener;

public class AccessibleEventDispatcher {
   public static boolean dispatchAccessibleEvent(int var0, Object var1, Object var2, AccessibleContext var3) {
      AccessibleEventListener var4 = GlobalScreenManager.getAccessibleEventListener();
      if (var4 != null) {
         var4.accessibleEventOccurred(var0, var1, var2, var3);
         return true;
      } else {
         return false;
      }
   }
}
