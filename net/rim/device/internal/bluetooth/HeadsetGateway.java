package net.rim.device.internal.bluetooth;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.Application;

public final class HeadsetGateway {
   public static final boolean isEnabled() {
      return !ITPolicy.getBoolean(34, 3, false);
   }

   public static final native int connect(byte[] var0, int var1);

   public static final native int disconnect();

   public static final native int enableAudio(boolean var0);

   public static final native int sendOK();

   public static final native int sendError();

   public static final native int sendRing();

   public static final native int sendSpeakerVolume(int var0);

   public static final void addListener(Application var0, HeadsetGatewayListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, HeadsetGatewayListener var1) {
      var0.removeListener(41, var1);
   }
}
