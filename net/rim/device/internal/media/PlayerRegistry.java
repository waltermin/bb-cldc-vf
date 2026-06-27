package net.rim.device.internal.media;

import java.util.Hashtable;
import javax.microedition.lcdui.List;
import javax.microedition.media.Player;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.internal.lcdui.MMAPIConnector;

public class PlayerRegistry {
   private static final long GUID;
   public static final long MMAPI_CONNECTOR_GUID;
   private static Hashtable _classnames;
   private static MMAPIConnector _mediaConnector;

   private PlayerRegistry() {
   }

   public static Player createPlayer(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static void register(String var0, String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static MMAPIConnector getMMAPIConnector() {
      if (_mediaConnector == null) {
         List.SELECT_COMMAND.getPriority();
         _mediaConnector = (MMAPIConnector)ApplicationRegistry.getApplicationRegistry().get(-79929455318757284L);
      }

      return _mediaConnector;
   }

   public static boolean hasInternalMMAPIAccess() {
      return ControlledAccess.verifyRRISignatures(false);
   }
}
