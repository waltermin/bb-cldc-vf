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

   protected void destroyApp(boolean var1) {
      throw null;
   }

   public final void notifyDestroyed() {
      Object var1 = Application.getApplication();
      ((MIDletApplication)var1).exit();
   }

   public final void notifyPaused() {
      this._main.requestBackground();
   }

   public final String getAppProperty(String var1) {
      String var2 = TraceBack.getCallingModuleName(2);
      String var3 = this.getGroupProperty(var2, var1);
      return var3 != null ? var3 : MIDletApplication.getAppProperty(var2, var1, true);
   }

   public final void resumeRequest() {
      this._main.requestForeground();
   }

   public final boolean platformRequest(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final int checkPermission(String var1) {
      return MIDletSecurity.checkSymbolicPermission(var1);
   }

   void setMain(MIDletMain var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private String getRequestDomain(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private String getGroupProperty(String var1, String var2) {
      if (this._properties == null) {
         if (!this._lookForGroupProperties) {
            return null;
         }

         CodeModuleGroup[] var3 = CodeModuleGroupManager.loadAll();
         if (var3 != null) {
            for (int var4 = 0; var4 < var3.length; var4++) {
               if (var3[var4].containsModule(var1)) {
                  CodeModuleGroupPropertiesCollection var5 = CodeModuleGroupPropertiesCollection.getInstance();
                  if (var5 != null) {
                     this._properties = (CodeModuleGroupProperties)var5.getSyncObject(CodeModuleGroupPropertiesCollection.getGroupUID(var3[var4].getName()));
                  }
                  break;
               }
            }
         }

         if (this._properties == null) {
            this._lookForGroupProperties = false;
         }
      }

      return (String)(this._properties == null ? null : this._properties.get(var2));
   }
}
