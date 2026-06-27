package javax.microedition.content;

class ContentHandlerServerImpl$1 implements Runnable {
   private final ContentHandlerServerImpl this$0;

   ContentHandlerServerImpl$1(ContentHandlerServerImpl var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      this.this$0._listener.invocationRequestNotify(this.this$0);
   }
}
