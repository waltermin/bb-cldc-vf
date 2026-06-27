package net.rim.device.cldc.io.utility;

import java.util.Vector;
import net.rim.device.api.util.StringUtilities;

public class URLParameters {
   private Vector _keys = (Vector)(new Object());
   private Vector _values = (Vector)(new Object());

   public boolean containParameter(String var1) {
      int var2 = this.searchForIndex(var1);
      return var2 >= 0;
   }

   public Vector getKeys() {
      return this._keys;
   }

   public Vector getValues() {
      return this._values;
   }

   public String getValue(String var1) {
      Object var2 = null;
      int var3 = this.searchForIndex(var1);
      if (var3 >= 0) {
         var2 = this._values.elementAt(var3);
      }

      return (String)var2;
   }

   public String remove(String var1) {
      Object var2 = null;
      int var3 = this.searchForIndex(var1);
      if (var3 >= 0) {
         var2 = this._values.elementAt(var3);
         this._keys.removeElementAt(var3);
         this._values.removeElementAt(var3);
      }

      return (String)var2;
   }

   private int searchForIndex(String var1) {
      int var2 = this._keys.size();
      int var3 = 0;

      while (var3 < var2 && !StringUtilities.strEqualIgnoreCase(var1, (String)this._keys.elementAt(var3), 1701707776)) {
         var3++;
      }

      return var3 < var2 ? var3 : -1;
   }

   public void setParameter(String var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String toString() {
      Object var1 = new Object();
      int var2 = this._keys.size();

      for (int var3 = 0; var3 < var2; var3++) {
         ((StringBuffer)var1).append(';').append(this._keys.elementAt(var3)).append('=').append(this._values.elementAt(var3));
      }

      return ((StringBuffer)var1).toString();
   }
}
