package net.rim.device.api.io;

import net.rim.device.internal.system.EventDispatcher;
import net.rim.vm.Message;

final class FileEventDispatcher extends EventDispatcher {
   private int _operationInProgress;
   private int _operationResult;

   final int waitForCompletion(int operation) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean notify(Message message) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void dispatch(Message message, Object listener) {
   }
}
