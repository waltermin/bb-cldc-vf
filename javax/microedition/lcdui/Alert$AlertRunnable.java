package javax.microedition.lcdui;

class Alert$AlertRunnable implements Runnable {
   private final Alert this$0;

   Alert$AlertRunnable(Alert var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      this.this$0.dismiss();
   }
}
