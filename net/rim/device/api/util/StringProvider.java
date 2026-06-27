package net.rim.device.api.util;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundleFamily;

public final class StringProvider {
   private boolean _useResource;
   private String _stringSet;
   private ResourceBundleFamily _family;
   private int _id;
   private String _string;
   private Locale _locale;

   public StringProvider(ResourceBundleFamily var1, int var2) {
      this._family = var1;
      this._id = var2;
      this._useResource = true;
      this.resetStringBaseline();
   }

   public StringProvider(String var1) {
      this.setString(var1);
      this.resetStringBaseline();
   }

   public final String getString() {
      return this._string;
   }

   public final boolean isStringDifferent() {
      return this._useResource ? this._locale != Locale.getDefault() : this._string == this._stringSet;
   }

   public final void resetStringBaseline() {
      if (this._useResource) {
         this._string = this._family.getString(this._id);
         this._locale = Locale.getDefault();
      } else {
         this._string = this._stringSet;
      }
   }

   public final void setString(ResourceBundleFamily var1, int var2) {
      this._family = var1;
      this._id = var2;
      this._useResource = true;
      this._locale = null;
   }

   public final void setString(String var1) {
      this._useResource = false;
      this._stringSet = var1;
   }
}
