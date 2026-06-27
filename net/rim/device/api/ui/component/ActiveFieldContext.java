package net.rim.device.api.ui.component;

public final class ActiveFieldContext {
   private String _data;
   private long _id;

   public ActiveFieldContext(String var1) {
      this._data = var1;
   }

   public final String getData() {
      return this._data;
   }

   public final void setData(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final long getID() {
      return this._id;
   }

   public final void setID(long var1) {
      this._id = var1;
   }
}
