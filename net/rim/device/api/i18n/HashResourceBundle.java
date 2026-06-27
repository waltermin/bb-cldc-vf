package net.rim.device.api.i18n;

import net.rim.device.api.util.IntHashtable;

public class HashResourceBundle extends ResourceBundle {
   private IntHashtable _contents = (IntHashtable)(new Object());

   public HashResourceBundle(Locale var1) {
      super(var1);
   }

   @Override
   protected Object handleGetObject(int var1) {
      return this._contents.get(var1);
   }

   public void put(int var1, Object var2) {
      if (var2 == null) {
         this._contents.remove(var1);
      } else {
         this._contents.put(var1, var2);
      }

      ResourceBundleFamily var3 = this.getFamily();
      if (var3 != null) {
         var3.clearEntry(var1);
      }
   }
}
