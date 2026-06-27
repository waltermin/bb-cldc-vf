package net.rim.device.internal.streamingnatives;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

class StreamingNatives$StreamingNativesEventDispatcher extends EventDispatcher {
   @Override
   public void dispatch(Message var1, Object var2) {
      int var3 = var1.getEvent();
      int var4 = var1.getSubMessage();
      int var5 = var1.getData0();
      StreamingNativesListener var6 = (StreamingNativesListener)var2;
      switch (var3) {
         case 3329:
         default:
            var6.streamHitWatermark(var4, var5);
            return;
         case 3331:
            var6.streamNewData(var4, var5);
            return;
         case 3332:
            var6.streamSourceDone(var4);
            return;
         case 3333:
            var6.streamErrorFromSource(var4, var5);
            return;
         case 3334:
            var6.streamErrorFromSink(var4, var5);
            return;
         case 3335:
            var6.streamSessionClosed(var4);
            return;
         case 3336:
            var6.streamLostData(var4, var5);
            return;
         case 3337:
            var6.streamSinkDone(var4);
         case 3328:
         case 3330:
      }
   }
}
