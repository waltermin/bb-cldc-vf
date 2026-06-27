package net.rim.device.internal.ui;

class MessageTiming implements Runnable {
   private long _start = System.currentTimeMillis();

   public MessageTiming() {
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
