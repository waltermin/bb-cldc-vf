package javax.microedition.lcdui;

import java.util.TimerTask;
import net.rim.device.api.system.Backlight;

class Display$FlashBacklightTimerTask extends TimerTask {
   int _numIterations;
   int _currentIteration;

   Display$FlashBacklightTimerTask(int var1) {
      this._numIterations = var1;
   }

   @Override
   public void run() {
      if (this._currentIteration == this._numIterations) {
         this.cancel();
      } else {
         if (this._currentIteration % 2 == 1) {
            Backlight.enable(false);
         } else {
            Backlight.enable(true);
         }

         this._currentIteration++;
      }
   }

   @Override
   public boolean cancel() {
      boolean var1 = super.cancel();
      Backlight.enable(true);
      return var1;
   }
}
