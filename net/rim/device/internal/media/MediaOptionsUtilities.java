package net.rim.device.internal.media;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.SIMCard;

public class MediaOptionsUtilities {
   private static final long GUID;
   private static final int SIM_CARD_NOT_PRESENT_IMSI_VALUE;

   public static MediaOptionsUtilities getInstance() {
      MediaOptionsUtilities var0 = (MediaOptionsUtilities)ApplicationRegistry.getApplicationRegistry().get(8285784959406531370L);
      if (var0 == null) {
         var0 = new MediaOptionsUtilities();
         ApplicationRegistry.getApplicationRegistry().replace(8285784959406531370L, var0);
      }

      return var0;
   }

   private MediaOptionsUtilities() {
   }

   private static int computeCurrentImsiValue() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean isVolumeBoostKeyExpired() {
      return !SIMCard.isSupported() ? false : computeCurrentImsiValue() != MediaOptionsRegistry.getInstance().getInt(-811168513825316359L);
   }

   public void showBoostVolumeWarning() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
