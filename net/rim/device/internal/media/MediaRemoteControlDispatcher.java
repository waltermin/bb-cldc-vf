package net.rim.device.internal.media;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class MediaRemoteControlDispatcher extends EventDispatcher {
   @Override
   public final void dispatch(Message var1, Object var2) {
      MediaRemoteControlListener var3 = (MediaRemoteControlListener)var2;
      var3.mediaPanelEvent(var1.getEvent(), var1.getSubMessage());
   }
}
