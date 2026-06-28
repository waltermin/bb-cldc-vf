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
   public void updateDatagramStatus(int dgId, int code, Object context) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   SMSSegmentListener(DatagramStatusListener listener, int totalSegments, int dgramId) {
      this._listener = listener;
      this._totalSegments = totalSegments;
      this._dgramId = dgramId;
   }
}
