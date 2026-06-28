package javax.microedition.lcdui;

import net.rim.device.internal.media.SimpleTonePlayer;

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
      try {
         Class c = Class.forName("net.rim.device.internal.media.TonePlayer");
         SimpleTonePlayer stp = (SimpleTonePlayer)c.newInstance();
         stp.playSimpleMidiSequence(this._tune);
      } catch (ArrayIndexOutOfBoundsException e) {
         this.logit(e);
      } catch (ClassNotFoundException e) {
         this.logit(e);
      } catch (IllegalAccessException e) {
         this.logit(e);
      } catch (InstantiationException e) {
         this.logit(e);
      }
   }

   private void logit(Exception e) {
      System.err.println(e.toString());
   }
}
