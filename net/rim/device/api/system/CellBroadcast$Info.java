package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;

public class CellBroadcast$Info implements Persistable {
   private int _id;
   private boolean _enabled;

   public CellBroadcast$Info(int var1) {
      this._id = var1;
   }

   protected CellBroadcast$Info() {
   }

   public int getId() {
      return this._id;
   }

   public boolean isEnabled() {
      return this._enabled;
   }

   public void setEnabled(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void copyInto(CellBroadcast$Info var1) {
      var1._id = this._id;
      var1._enabled = this._enabled;
   }
}
