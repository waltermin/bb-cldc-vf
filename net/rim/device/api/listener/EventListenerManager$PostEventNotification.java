package net.rim.device.api.listener;

import net.rim.vm.WeakReference;

final class EventListenerManager$PostEventNotification extends EventListenerManager$EventNotification {
   EventListenerManager$PostEventNotification(WeakReference var1, Event var2) {
      super(var1, null, var2);
   }

   @Override
   final Thread invoke() {
      return super._event.postUpdateEventListener();
   }
}
