package net.rim.device.internal.system;

import net.rim.device.api.crypto.SHA1Digest;
import net.rim.device.api.util.Arrays;

public final class DRMServices {
   private static final long LOCK_GUID;
   public static final int DRM_DEVICE_KEY_SIZE;
   public static final int DRM_SUBSCRIBER_KEY_SIZE;
   private static final byte[] DRM_SIM_KEY_SCRAMBLE_SET;

   private DRMServices() {
   }

   private static final Object getDRMLock() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] getDeviceKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] getSubscriberKey() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final byte[] getHash(byte[] var0) {
      if (var0 != null) {
         Object var1 = new Object();
         ((SHA1Digest)var1).update(var0);
         var0 = ((SHA1Digest)var1).getDigest();
      }

      return var0;
   }

   public static final byte[] getDeviceHash() {
      return getHash(getDeviceKey());
   }

   public static final byte[] getSubscriberHash() {
      return getHash(getSubscriberKey());
   }

   public static final boolean checkTrailerBytes(byte[] var0) {
      byte[] var1 = getDeviceHash();
      if (Arrays.equals(var0, var1)) {
         return true;
      }

      var1 = getSubscriberHash();
      return var1 != null && Arrays.equals(var0, var1);
   }
}
