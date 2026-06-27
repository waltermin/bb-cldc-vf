package net.rim.device.api.i18n;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.CodeSigningKey;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.api.util.StringUtilities;

public class ResourceBundle {
   private ResourceBundleFamily _family;
   private Locale _locale;
   private ResourceBundle _parent;
   private static final long COLLECTION_ID;
   private static LongHashtable _table;

   protected ResourceBundle(Locale var1) {
      this._locale = var1;
   }

   long getId() {
      return -1;
   }

   public static final ResourceBundleFamily getBundle(String var0) {
      return getBundle(StringUtilities.stringHashToLong(var0), var0, null);
   }

   public static final ResourceBundleFamily getBundle(long var0, String var2) {
      return getBundle(var0, var2, null);
   }

   public static final ResourceBundleFamily getBundle(long var0, String var2, CodeSigningKey var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final ResourceBundleFamily getFamily() {
      return this._family;
   }

   public final Locale getLocale() {
      return this._locale;
   }

   public final Object getObject(int var1) {
      Object var2 = this.getObject(var1, true);
      if (var2 == null) {
         throw new MissingResourceException();
      } else {
         return var2;
      }
   }

   public final Object getObject(int var1, boolean var2) {
      Object var3 = this.handleGetObject(var1);
      if (var3 == null && this._parent != null && var2) {
         var3 = this._parent.getObject(var1, var2);
      }

      return var3;
   }

   public final String getString(int var1) {
      return (String)this.getObject(var1);
   }

   public final String[] getStringArray(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected Object handleGetObject(int var1) {
      throw null;
   }

   public static void registerGlobalEventListener() {
      Application.getApplication().addGlobalEventListener(new ResourceBundle$1());
   }

   void setFamily(ResourceBundleFamily var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setParent(ResourceBundle var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private static void verifyHash(long var0, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
