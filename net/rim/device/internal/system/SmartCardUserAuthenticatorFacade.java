package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.UserAuthenticator;

public class SmartCardUserAuthenticatorFacade {
   public static void checkITPolicy() {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected String checkITPolicyImpl(UserAuthenticator[] _1, boolean _2, boolean _3) {
      throw null;
   }

   public static boolean isInitializationRequired() {
      boolean forceSmartCardTwoFactorAuthentication = ITPolicy.getBoolean(24, 2, false);
      UserAuthenticator authenticator = Security.getInstance().getUserAuthenticator();
      return FIPSPolicy.isDevicePasswordRequired()
         && forceSmartCardTwoFactorAuthentication
         && authenticator == null
         && Security.getInstance().isPasswordEnabled();
   }
}
