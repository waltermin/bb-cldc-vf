package net.rim.device.api.system;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class AudioRouterEventDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      int var3 = var1.getEvent();
      AudioRouterListener var4 = (AudioRouterListener)var2;
      switch (var3) {
         case 1:
         default:
            var4.audioVolumeChanged(var1.getSubMessage() != 0);
            return;
         case 2:
            var4.audioSinkChanged();
            return;
         case 3:
            var4.audioSourceChanged();
         case 0:
      }
   }
}
