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

   static final boolean containsDuplicates(String[] strings) {
      String[] sortedStrings = new String[strings.length];

      for (int i = 0; i < strings.length; i++) {
         sortedStrings[i] = strings[i];
      }

      Arrays.sort(sortedStrings, new ContentHandlerUtilities$StringComparator());

      for (int i = 0; i < sortedStrings.length - 1; i++) {
         if (sortedStrings[i].equals(sortedStrings[i + 1])) {
            return true;
         }
      }

      return false;
   }

   static final void checkStringArrayValues(String[] strings, boolean throwExceptionIfNull) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void checkStringArrayValues(String[] strings) {
      checkStringArrayValues(strings, true);
   }

   static final String getStringValue(String key, int moduleHandle) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final String getStringValue(String key, Resource resource) {
      byte[] data = resource.getProperty(key);
      return (String)(data != null ? new Object(data, 2, data.length - 2) : null);
   }

   static final String checkURL(String url) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final ApplicationDescriptor findApplicationDescriptor(int moduleHandle, String classname) {
      ApplicationDescriptor[] descriptors = CodeModuleManager.getApplicationDescriptors(moduleHandle);
      ApplicationDescriptor application = null;
      if (CodeModuleManager.isMidlet(moduleHandle)) {
         for (int i = 0; i < descriptors.length; i++) {
            String[] args = descriptors[i].getArgs();
            if (args[0].equals(classname)) {
               application = descriptors[i];
               break;
            }
         }

         if (application == null && descriptors[0] != null) {
            return (ApplicationDescriptor)(new Object(descriptors[0], new String[]{classname}));
         }
      } else {
         if (descriptors != null && descriptors.length > 0) {
            return (ApplicationDescriptor)(new Object(descriptors[0], new String[0]));
         }

         application = (ApplicationDescriptor)(new Object(ApplicationDescriptor.currentApplicationDescriptor(), new String[0]));
      }

      return application;
   }
}
