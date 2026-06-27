package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.UserAuthenticator;

public class SmartCardUserAuthenticatorFacade {
   public static void checkITPolicy() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected String checkITPolicyImpl(UserAuthenticator[] var1, boolean var2, boolean var3) {
      throw null;
   }

   public static boolean isInitializationRequired() {
      boolean var0 = ITPolicy.getBoolean(24, 2, false);
      UserAuthenticator var1 = Security.getInstance().getUserAuthenticator();
      return FIPSPolicy.isDevicePasswordRequired() && var0 && var1 == null && Security.getInstance().isPasswordEnabled();
   }
}
