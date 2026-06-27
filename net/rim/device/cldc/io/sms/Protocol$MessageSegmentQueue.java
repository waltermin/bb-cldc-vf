package net.rim.device.cldc.io.sms;

final class Protocol$MessageSegmentQueue {
   Protocol$StoreMessage[] messages;
   private int currentSize;
   private int front;
   private int back;

   public Protocol$MessageSegmentQueue(int var1) {
      this.messages = new Protocol$StoreMessage[var1];
      this.currentSize = 0;
      this.front = 0;
      this.back = -1;
   }

   public final void add(Protocol$StoreMessage var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
