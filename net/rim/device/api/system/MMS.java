package net.rim.device.api.system;

import net.rim.device.api.itpolicy.ITPolicy;

public final class MMS {
   public static final long BACKDOOR_INJECT_MMS_SERVICE_BOOK;

   public static final boolean isSupported() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isEnabled() {
      return isSupported() && isITPolicyEnabled() && (MMSStatus.getInstance().hasServiceBook() || wasPreviouslyEnabled());
   }

   private static final boolean isITPolicyEnabled() {
      return !ITPolicy.getBoolean(21, 7, false);
   }

   public static final void setServiceBookStatus(boolean var0) {
      MMSStatus.getInstance().setServiceBookStatus(var0);
   }

   public static final void onEnabled(Runnable var0) {
      if (isSupported()) {
         MMSStatus.getInstance().onEnabled(var0);
      }
   }

   private static final boolean wasPreviouslyEnabled() {
      long var0 = 572030951534635290L;
      return RIMPersistentStore.getPersistentObject(var0).getContents() != null;
   }

   public static final MMS$ClientOptions getClientOptions() {
      long var0 = -4248845697481338338L;
      return (MMS$ClientOptions)ApplicationRegistry.getApplicationRegistry().get(var0);
   }
}
