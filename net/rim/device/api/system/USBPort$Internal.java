package net.rim.device.api.system;

import net.rim.device.internal.system.USBPortInternal$Internal;

public class USBPort$Internal {
   public static final int USB_MODE_HANDHELD;
   public static final int USB_MODE_MODEM;

   public static void setMode(int var0) {
      USBPortInternal$Internal.setMode(var0);
   }

   public static void redirectedPasswordChallenge(int var0, boolean var1) {
      USBPortInternal$Internal.redirectedPasswordChallenge(var0, var1);
   }

   public static String getChannelName(int var0) {
      return USBPortInternal$Internal.getChannelName(var0);
   }
}
