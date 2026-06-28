package javax.microedition.lcdui;

import javax.microedition.media.Player;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.lcdui.LcduiPlayerController;
import net.rim.device.internal.lcdui.MMAPIConnector;

class MMAPIConnectorImpl implements MMAPIConnector {
   @Override
   public LcduiPlayerController setMediaCanvas(Canvas c, Player player) {
      LcduiPlayerController controller = (LcduiPlayerController)c.getPeer();
      controller.setPlayer(player);
      return controller;
   }

   @Override
   public LcduiPlayerController getMediaItem(Player player, String label, int width, int height) {
      MediaItem mediaItem = new MediaItem(label, width, height);
      LcduiPlayerController controller = mediaItem.getMediaField();
      controller.setPlayer(player);
      return controller;
   }

   @Override
   public LcduiPlayerController getMediaField(Player player, int width, int height) {
      LcduiPlayerController controller = (LcduiPlayerController)(new Object(width, height));
      controller.setComponent(controller);
      controller.setPlayer(player);
      return controller;
   }

   @Override
   public void notifyPlayerPositionChange(Player player, XYRect rect) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void notifyPlayerOffsetChange(Player player, XYRect rect) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
