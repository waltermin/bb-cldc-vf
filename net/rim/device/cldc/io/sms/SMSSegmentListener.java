package net.rim.device.cldc.io.sms;

import net.rim.device.api.io.DatagramStatusListener;

class SMSSegmentListener implements DatagramStatusListener {
   private DatagramStatusListener _listener;
   private int _totalSegments;
   private int _dgramId;
   private int _sentSegments;
   private boolean _errorOccurred;

   boolean errorOccurred() {
      return this._errorOccurred;
   }

   @Override
   public void updateDatagramStatus(int var1, int var2, Object var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   SMSSegmentListener(DatagramStatusListener var1, int var2, int var3) {
      this._listener = var1;
      this._totalSegments = var2;
      this._dgramId = var3;
   }
}
