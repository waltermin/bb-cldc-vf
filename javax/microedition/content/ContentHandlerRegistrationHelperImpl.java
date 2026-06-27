package javax.microedition.content;

import java.util.Hashtable;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.StringTokenizer;
import net.rim.device.internal.content.ContentHandlerRegistrationHelper;

class ContentHandlerRegistrationHelperImpl extends ContentHandlerRegistrationHelper {
   protected ContentHandlerRegistrationHelperImpl() {
   }

   static void register() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      ContentHandlerRegistrationHelperImpl var1 = (ContentHandlerRegistrationHelperImpl)var0.get(-352407102385872585L);
      if (var1 == null) {
         var1 = new ContentHandlerRegistrationHelperImpl();
         var0.put(-352407102385872585L, var1);
      }
   }

   @Override
   public int verifyJadAttributes(Hashtable var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void registerContentHandlers(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void unregisterContentHandler(String var1) {
      RegistryImpl var2 = RegistryImpl.getRegistryImpl();
      var2.unregisterInternal(var1);
   }

   @Override
   public void moduleUpgraded(String var1, int var2) {
      InvocationCleanupManager.getInstance().moduleUpgraded(var1, var2);
   }

   private static String parseHandlerValues(String var0, String[] var1) {
      if (var0 == null) {
         return null;
      }

      int var2 = var0.indexOf(44);
      String var3;
      String var4;
      if (var2 == -1) {
         var3 = var0.trim();
         var4 = null;
      } else {
         var3 = var0.substring(0, var2).trim();
         var4 = var0.substring(var2 + 1);
      }

      Object var5 = new Object(var3);

      while (((StringTokenizer)var5).hasMoreTokens()) {
         Arrays.add(var1, ((StringTokenizer)var5).nextToken());
      }

      return var4;
   }
}
