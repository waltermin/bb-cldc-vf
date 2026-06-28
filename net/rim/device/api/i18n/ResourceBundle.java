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

   protected ResourceBundle(Locale locale) {
      this._locale = locale;
   }

   long getId() {
      return -1;
   }

   public static final ResourceBundleFamily getBundle(String name) {
      return getBundle(StringUtilities.stringHashToLong(name), name, null);
   }

   public static final ResourceBundleFamily getBundle(long bundle, String name) {
      return getBundle(bundle, name, null);
   }

   public static final ResourceBundleFamily getBundle(long bundle, String name, CodeSigningKey key) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final ResourceBundleFamily getFamily() {
      return this._family;
   }

   public final Locale getLocale() {
      return this._locale;
   }

   public final Object getObject(int key) {
      Object obj = this.getObject(key, true);
      if (obj == null) {
         throw new MissingResourceException();
      } else {
         return obj;
      }
   }

   public final Object getObject(int key, boolean searchParent) {
      Object obj = this.handleGetObject(key);
      if (obj == null && this._parent != null && searchParent) {
         obj = this._parent.getObject(key, searchParent);
      }

      return obj;
   }

   public final String getString(int key) {
      return (String)this.getObject(key);
   }

   public final String[] getStringArray(int key) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected Object handleGetObject(int _1) {
      throw null;
   }

   public static void registerGlobalEventListener() {
      Application.getApplication().addGlobalEventListener(new ResourceBundle$1());
   }

   void setFamily(ResourceBundleFamily family) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setParent(ResourceBundle parent) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private static void verifyHash(long bundle, String name) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
