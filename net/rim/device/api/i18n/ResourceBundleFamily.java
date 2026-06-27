package net.rim.device.api.i18n;

import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.CodeSigningKey;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntHashtable;
import net.rim.vm.TraceBack;

public class ResourceBundleFamily extends ResourceBundle {
   private final long _id;
   private final String _name;
   private CodeSigningKey _key;
   private String _module;
   private Hashtable _table = (Hashtable)(new Object());
   private ResourceBundle _bundleFallback;
   private ResourceBundle _bundleSystem;
   private ResourceBundle _bundleApp;
   private Locale _localeSystem;
   private Locale _localeApp;
   private IntHashtable _cache = new ResourceBundleFamily$MyIntHashtable(this, 37);

   ResourceBundleFamily(long var1, String var3, CodeSigningKey var4) {
      super(null);
      this._id = var1;
      this._name = var3;
      this._key = var4;
      this.setFamily(this);
      this.setFallbackLocale();
   }

   private final void setFallbackLocale() {
      Locale var1 = Locale.get(1701729619);
      this._bundleFallback = this.getBundle(var1);
   }

   private void checkLocale() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void clearEntry(int var1) {
      this._cache.remove(var1);
   }

   public synchronized ResourceBundle getBundle(Locale var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public long getId() {
      return this._id;
   }

   public String getName() {
      return this._name;
   }

   @Override
   protected Object handleGetObject(int var1) {
      this.checkLocale();
      Object var2 = this._cache.get(var1);
      if (var2 == null) {
         var2 = this._bundleApp.getObject(var1, true);
         if (var2 == null) {
            var2 = this._bundleSystem.getObject(var1, true);
            if (var2 == null) {
               var2 = this._bundleFallback.getObject(var1, true);
            }
         }

         if (var2 != null) {
            this._cache.put(var1, var2);
         }
      }

      return var2;
   }

   public boolean isEmpty() {
      boolean var1 = true;
      Enumeration var2 = this._table.elements();

      while (var2.hasMoreElements()) {
         ResourceBundle var3 = (ResourceBundle)var2.nextElement();
         if (!(var3 instanceof EmptyResourceBundle)) {
            return false;
         }
      }

      return var1;
   }

   private synchronized ResourceBundle getInstance(Locale var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void onModuleLoad() {
      IntEnumeration var1 = this._cache.keys();

      while (var1.hasMoreElements()) {
         int var2 = var1.nextElement();
         if (this._cache.get(var2) instanceof EmptyResourceBundle) {
            this._cache.remove(var2);
         }
      }

      this._localeSystem = null;
      this._localeApp = null;
      this.checkLocale();
   }

   public synchronized void put(Locale var1, ResourceBundle var2) {
      if (this._key != null && !ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), this._key)) {
         throw new Object();
      }

      if (this._module == null) {
         this.setModule(ApplicationDescriptor.currentApplicationDescriptor().getModuleName());
      } else if (!ApplicationDescriptor.currentApplicationDescriptor().getModuleName().startsWith(this._module)) {
         throw new Object();
      }

      Locale var3 = var1.getParent();
      if (var3 != null) {
         ResourceBundle var4 = this.getInstance(var3);
         var2.setParent(var4);
      }

      if (this._id == 8736789735327653723L) {
         Locale.addLocaleInternal(var1);
      }

      ResourceBundle var5 = (ResourceBundle)this._table.get(var1);
      if (var5 == null || var5 instanceof EmptyResourceBundle) {
         var2.setFamily(this);
         this._table.put(var1, var2);
      }
   }

   private synchronized void setModule(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean verify(CodeSigningKey var1) {
      if (var1 == null) {
         return true;
      }

      if (this._key != null) {
         return var1.equals(this._key);
      }

      Enumeration var2 = this._table.elements();

      while (var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         int var4 = CodeModuleManager.getModuleHandleForObject(var3);
         if (!ControlledAccess.verifyCodeModuleSignature(var4, var1)) {
            return false;
         }
      }

      this._key = var1;
      return true;
   }
}
