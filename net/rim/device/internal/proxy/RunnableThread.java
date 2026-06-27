package net.rim.device.internal.proxy;

import net.rim.device.api.util.CyclicQueue;

final class RunnableThread extends Thread {
   private CyclicQueue _queue = (CyclicQueue)(new Object());

   final void add(Runnable var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
