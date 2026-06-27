package net.rim.device.cldc.io.sms;

import net.rim.device.api.io.DatagramStatusListener;

class StatusThread$StatusObject {
   protected boolean _dgramIdFlag;
   protected DatagramStatusListener _listener;
   protected int _id;
   protected int _status;
   protected Object _context;

   public StatusThread$StatusObject(boolean var1, DatagramStatusListener var2, int var3, int var4, Object var5) {
      this._dgramIdFlag = var1;
      this._listener = var2;
      this._id = var3;
      this._status = var4;
      this._context = var5;
   }
}
