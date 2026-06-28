package javax.microedition.midlet;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.CodeModuleGroup;
import net.rim.device.api.system.CodeModuleGroupManager;
import net.rim.device.internal.system.CodeModuleGroupProperties;
import net.rim.device.internal.system.CodeModuleGroupPropertiesCollection;
import net.rim.device.internal.system.MIDletSecurity;
import net.rim.device.internal.ui.MIDletApplication;
import net.rim.vm.TraceBack;

public class MIDlet {
   private MIDletMain _main;
   private CodeModuleGroupProperties _properties;
   private boolean _lookForGroupProperties = true;
   static boolean _instantiationAllowed;
   private static ResourceBundle _resources;

   protected MIDlet() {
      if (!_instantiationAllowed) {
         throw new Object();
      }

      MIDletSecurity.checkMIDletCreation();
   }

   protected void startApp() {
      throw null;
   }

   protected void pauseApp() {
      throw null;
   }

   protected void destroyApp(boolean _1) {
      throw null;
   }

   public final void notifyDestroyed() {
      MIDletApplication ma = (MIDletApplication)Application.getApplication();
      ma.exit();
   }

   public final void notifyPaused() {
      this._main.requestBackground();
   }

   public final String getAppProperty(String key) {
      String moduleName = TraceBack.getCallingModuleName(2);
      String property = this.getGroupProperty(moduleName, key);
      return property != null ? property : MIDletApplication.getAppProperty(moduleName, key, true);
   }

   public final void resumeRequest() {
      this._main.requestForeground();
   }

   public final boolean platformRequest(String URL) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int checkPermission(String permission) {
      return MIDletSecurity.checkSymbolicPermission(permission);
   }

   void setMain(MIDletMain main) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private String getRequestDomain(String URL) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private String getGroupProperty(String moduleName, String key) {
      if (this._properties == null) {
         if (!this._lookForGroupProperties) {
            return null;
         }

         CodeModuleGroup[] groups = CodeModuleGroupManager.loadAll();
         if (groups != null) {
            for (int i = 0; i < groups.length; i++) {
               if (groups[i].containsModule(moduleName)) {
                  CodeModuleGroupPropertiesCollection collection = CodeModuleGroupPropertiesCollection.getInstance();
                  if (collection != null) {
                     this._properties = (CodeModuleGroupProperties)collection.getSyncObject(
                        CodeModuleGroupPropertiesCollection.getGroupUID(groups[i].getName())
                     );
                  }
                  break;
               }
            }
         }

         if (this._properties == null) {
            this._lookForGroupProperties = false;
         }
      }

      return (String)(this._properties == null ? null : this._properties.get(key));
   }
}
