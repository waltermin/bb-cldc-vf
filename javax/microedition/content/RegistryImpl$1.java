package javax.microedition.content;

class RegistryImpl$1 implements Runnable {
   private final RegistryImpl this$0;

   RegistryImpl$1(RegistryImpl var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      this.this$0._listener.invocationResponseNotify(this.this$0);
   }
}
