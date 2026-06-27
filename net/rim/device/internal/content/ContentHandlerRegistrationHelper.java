package net.rim.device.internal.content;

import java.util.Hashtable;
import net.rim.device.api.system.ApplicationRegistry;

public class ContentHandlerRegistrationHelper {
   public static final String HANDLER_PREFIX;
   public static final String APPLICATION_DESCRIPTOR_ARG_CONTENT_HANDLER_WORK;
   protected static final int VERIFICATION_SUCCESS;
   protected static final int VERIFICATION_AUTHENTICATION_FAILED;
   protected static final int VERIFICATION_DUPLICATE_ID;
   protected static final int VERIFICATION_FAILED;
   protected static final long APP_REGISTRY_KEY;

   protected ContentHandlerRegistrationHelper() {
   }

   public static ContentHandlerRegistrationHelper getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      return (ContentHandlerRegistrationHelper)var0.waitFor(-352407102385872585L);
   }

   public int verifyJadAttributes(Hashtable var1, boolean var2) {
      throw null;
   }

   public void registerContentHandlers(int var1) {
      throw null;
   }

   public void unregisterContentHandler(String var1) {
      throw null;
   }

   public void moduleUpgraded(String var1, int var2) {
      throw null;
   }
}
