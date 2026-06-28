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

   public boolean playSound(Display display) {
      display.isColor();
      this.playSound();
      return true;
   }

   void playSound() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void logit(Exception e) {
      System.err.println(e.toString());
   }
}
