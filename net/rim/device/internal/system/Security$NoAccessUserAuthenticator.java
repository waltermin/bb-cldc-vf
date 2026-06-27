package net.rim.device.internal.system;

import net.rim.device.api.system.UserAuthenticator;

public final class Security$NoAccessUserAuthenticator extends UserAuthenticator {
   @Override
   public final String getName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final Object getInformation(long var1, Object var3, Object var4) {
      return var4;
   }

   @Override
   public final boolean authenticate(String var1) {
      return false;
   }

   @Override
   public final boolean initialize(String var1) {
      throw new Object();
   }

   @Override
   public final boolean isInitialized() {
      return true;
   }

   @Override
   public final int getMaxAuthenticationAttempts() {
      return 1;
   }

   @Override
   public final int getRemainingAuthenticationAttempts() {
      return 1;
   }

   @Override
   public final boolean setStateData(byte[] var1) {
      return true;
   }

   @Override
   public final void uninitialize() {
   }

   @Override
   public final byte[] getStateData() {
      return null;
   }

   @Override
   public final boolean isInitializationPossible() {
      return true;
   }

   @Override
   public final boolean isReadyForInitialization() {
      return true;
   }

   @Override
   public final boolean isConfigured() {
      return true;
   }

   @Override
   public final void configure() {
   }
}
