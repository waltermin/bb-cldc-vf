package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;

public final class CellBroadcast$LanguagePreference extends CellBroadcast$Info implements Persistable {
   private int _priority;

   public CellBroadcast$LanguagePreference() {
      this.setEnabled(false);
   }

   public CellBroadcast$LanguagePreference(int var1) {
      super(var1);
      this.setEnabled(false);
   }

   public final int getPriority() {
      return this._priority;
   }

   public final void setPriority(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final CellBroadcast$LanguagePreference clone() {
      CellBroadcast$LanguagePreference var1 = new CellBroadcast$LanguagePreference();
      var1._priority = this._priority;
      this.copyInto(var1);
      return var1;
   }
}
