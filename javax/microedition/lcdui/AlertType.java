package javax.microedition.lcdui;

public class AlertType {
   int[] _tune;
   public static final AlertType INFO;
   public static final AlertType WARNING;
   public static final AlertType ERROR;
   public static final AlertType ALARM;
   public static final AlertType CONFIRMATION;

   protected AlertType() {
   }

   public boolean playSound(Display var1) {
      var1.isColor();
      this.playSound();
      return true;
   }

   void playSound() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void logit(Exception var1) {
      System.err.println(var1.toString());
   }
}
