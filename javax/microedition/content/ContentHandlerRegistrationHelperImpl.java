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
      ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
      ContentHandlerRegistrationHelperImpl instance = (ContentHandlerRegistrationHelperImpl)ar.get(-352407102385872585L);
      if (instance == null) {
         instance = new ContentHandlerRegistrationHelperImpl();
         ar.put(-352407102385872585L, instance);
      }
   }

   @Override
   public int verifyJadAttributes(Hashtable jadAttributes, boolean upgrade) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void registerContentHandlers(int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void unregisterContentHandler(String classname) {
      RegistryImpl ri = RegistryImpl.getRegistryImpl();
      ri.unregisterInternal(classname);
   }

   @Override
   public void moduleUpgraded(String moduleName, int moduleHandle) {
      InvocationCleanupManager.getInstance().moduleUpgraded(moduleName, moduleHandle);
   }

   private static String parseHandlerValues(String s, String[] a) {
      if (s == null) {
         return null;
      }

      int comma = s.indexOf(44);
      String values;
      String result;
      if (comma == -1) {
         values = s.trim();
         result = null;
      } else {
         values = s.substring(0, comma).trim();
         result = s.substring(comma + 1);
      }

      StringTokenizer tokens = (StringTokenizer)(new Object(values));

      while (tokens.hasMoreTokens()) {
         Arrays.add(a, tokens.nextToken());
      }

      return result;
   }
}
