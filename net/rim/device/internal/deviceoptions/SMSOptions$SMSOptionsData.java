package net.rim.device.internal.deviceoptions;

import net.rim.vm.Persistable;

final class SMSOptions$SMSOptionsData implements Persistable {
   public int _smsRoute;
   public boolean _deliveryReports;
   public boolean _storeOnSIM;
   public int _numPreviousItems;
   public int _presetUiId;
   public int _messageListUiId;
   public boolean _smsUiPreset;
   public boolean _messageListUiPreset;
   public boolean _multipleRecipients;
   public boolean _disableAutoText;
   public boolean _enableCellBroadcast;
   public byte[] _voicemailIndicators;
   public int _imsiCRC;
   public int _fallbackMessageCoding;

   public SMSOptions$SMSOptionsData() {
   }
}
