package javax.microedition.lcdui;

final class Display$SwitchDisplayablesRunnable implements Runnable {
   private Displayable _oldD;
   private Displayable _newD;
   private final Display this$0;

   public final void setNewDisplayable(Displayable var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final void run() {
      this.this$0._switchDisplayablesRunnable = null;
      this.this$0.switchDisplayables(this._oldD, this._newD);
   }

   public Display$SwitchDisplayablesRunnable(Display var1, Displayable var2, Displayable var3) {
      this.this$0 = var1;
      this._oldD = var2;
      this._newD = var3;
   }
}
