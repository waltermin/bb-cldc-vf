package net.rim.device.api.io;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class FileEventDispatcher extends EventDispatcher {
   private int _operationInProgress;
   private int _operationResult;

   final int waitForCompletion(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean notify(Message var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dispatch(Message var1, Object var2) {
   }
}
