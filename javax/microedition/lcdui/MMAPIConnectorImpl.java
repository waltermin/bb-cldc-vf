package javax.microedition.lcdui;

import javax.microedition.media.Player;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.lcdui.LcduiPlayerController;
import net.rim.device.internal.lcdui.MMAPIConnector;
import net.rim.device.internal.ui.component.MMAPIMediaField;

class MMAPIConnectorImpl implements MMAPIConnector {
   @Override
   public LcduiPlayerController setMediaCanvas(Canvas var1, Player var2) {
      Object var3 = var1.getPeer();
      ((LcduiPlayerController)var3).setPlayer(var2);
      return (LcduiPlayerController)var3;
   }

   @Override
   public LcduiPlayerController getMediaItem(Player var1, String var2, int var3, int var4) {
      MediaItem var5 = new MediaItem(var2, var3, var4);
      MMAPIMediaField var6 = var5.getMediaField();
      var6.setPlayer(var1);
      return var6;
   }

   @Override
   public LcduiPlayerController getMediaField(Player var1, int var2, int var3) {
      Object var4 = new Object(var2, var3);
      ((LcduiPlayerController)var4).setComponent(var4);
      ((LcduiPlayerController)var4).setPlayer(var1);
      return (LcduiPlayerController)var4;
   }

   @Override
   public void notifyPlayerPositionChange(Player var1, XYRect var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void notifyPlayerOffsetChange(Player var1, XYRect var2) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
