package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;

public final class EventInjector {
   private EventInjector() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertEventInjectorAllowed(true);
   }

   public static final void invokeEvent(EventInjector$Event var0) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
