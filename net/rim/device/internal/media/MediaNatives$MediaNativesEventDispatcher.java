package net.rim.device.internal.media;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

class MediaNatives$MediaNativesEventDispatcher extends EventDispatcher {
   @Override
   public void dispatch(Message var1, Object var2) {
      int var3 = var1.getEvent();
      int var4 = var1.getSubMessage();
      int var5 = var1.getData0();
      int var6 = var1.getData1();
      MediaEventListener var7 = (MediaEventListener)var2;
      switch (var3) {
         case 8192:
         default:
            var7.mediaStopped(var5);
            return;
         case 8193:
            var7.mediaPauseComplete(var5);
            return;
         case 8194:
            var7.mediaError(var5, var4);
            return;
         case 8195:
            var7.mediaSeek(var5, var6);
            return;
         case 8196:
            var7.mediaLoaded(var5);
            return;
         case 8197:
            var7.mediaStatusUpdate(var5, var6);
            return;
         case 8198:
            Object var8 = var1.getObject0();
            Object var9 = var1.getObject1();
            var7.mediaAuthenticationRequired(var5, var8, var9);
            return;
         case 8199:
            var7.mediaParametersChangedComplete(var5);
         case 8191:
      }
   }
}
