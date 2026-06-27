package javax.microedition.content;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.util.Arrays;
import net.rim.device.resources.Resource;

final class ContentHandlerUtilities {
   private static final String SLASH_SLASH;
   private static final String DEVICE_SIDE;

   private ContentHandlerUtilities() {
   }

   static final boolean containsDuplicates(String[] var0) {
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2];
      }

      Arrays.sort(var1, new ContentHandlerUtilities$StringComparator());

      for (int var3 = 0; var3 < var1.length - 1; var3++) {
         if (var1[var3].equals(var1[var3 + 1])) {
            return true;
         }
      }

      return false;
   }

   static final void checkStringArrayValues(String[] var0, boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void checkStringArrayValues(String[] var0) {
      checkStringArrayValues(var0, true);
   }

   static final String getStringValue(String var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final String getStringValue(String var0, Resource var1) {
      byte[] var2 = var1.getProperty(var0);
      return (String)(var2 != null ? new Object(var2, 2, var2.length - 2) : null);
   }

   static final String checkURL(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final ApplicationDescriptor findApplicationDescriptor(int var0, String var1) {
      ApplicationDescriptor[] var2 = CodeModuleManager.getApplicationDescriptors(var0);
      Object var3 = null;
      if (CodeModuleManager.isMidlet(var0)) {
         for (int var4 = 0; var4 < var2.length; var4++) {
            String[] var5 = var2[var4].getArgs();
            if (var5[0].equals(var1)) {
               var3 = var2[var4];
               break;
            }
         }

         if (var3 == null && var2[0] != null) {
            return (ApplicationDescriptor)(new Object(var2[0], new String[]{var1}));
         }
      } else {
         if (var2 != null && var2.length > 0) {
            return (ApplicationDescriptor)(new Object(var2[0], new String[0]));
         }

         var3 = new Object(ApplicationDescriptor.currentApplicationDescriptor(), new String[0]);
      }

      return (ApplicationDescriptor)var3;
   }
}
