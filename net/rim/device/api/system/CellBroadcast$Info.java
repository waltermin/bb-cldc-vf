package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;

public class CellBroadcast$Info implements Persistable {
   private int _id;
   private boolean _enabled;

   public CellBroadcast$Info(int id) {
      this._id = id;
   }

   protected CellBroadcast$Info() {
   }

   public int getId() {
      return this._id;
   }

   public boolean isEnabled() {
      return this._enabled;
   }

   public void setEnabled(boolean enabled) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void copyInto(CellBroadcast$Info i) {
      i._id = this._id;
      i._enabled = this._enabled;
   }
}
