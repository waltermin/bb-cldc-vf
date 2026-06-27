package net.rim.device.api.crypto;

public final class Certicom {
   private Certicom() {
   }

   public static final native boolean isAccessAllowed();

   public static final void assertAccessAllowed() {
      throw new RuntimeException("cod2jar: ldc");
   }
}
