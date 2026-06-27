package net.rim.device.internal.deviceoptions;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.SIMCard;
import net.rim.device.internal.system.RadioInternal;

public final class SMSOptions {
   public static final long GUID_SMS_OPTIONS_CHANGED;
   public static final long GUID_SMS_UI_CHANGED;
   private static final long SMS_OPTIONS_DATA_KEY;
   private static PersistentObject _persistentObject;
   private static SMSOptions$SMSOptionsData _smsOptionsData;
   public static final int NUMBER_PREVIOUS_ITEMS_MAX;
   public static final int NUMBER_PREVIOUS_ITEMS_DEFAULT;
   public static final int NUMBER_PREVIOUS_ITEMS_DEFAULT_IDEN;

   private SMSOptions() {
   }

   static final void init() {
      if (RadioInfo.areWAFsSupported(1)) {
         RadioInternal.smsSetRoute(_smsOptionsData._smsRoute);
         RadioInternal.smsStoreOnSIM(_smsOptionsData._storeOnSIM);
      }
   }

   private static final void commit(boolean var0) {
      _persistentObject.commit();
      if (var0) {
         RIMGlobalMessagePoster.postGlobalEvent(6063360555319689575L);
      }
   }

   public static final byte[] getVoicemailIndicators(int var0) {
      return var0 == _smsOptionsData._imsiCRC ? _smsOptionsData._voicemailIndicators : new byte[4];
   }

   static final byte[] getVoicemailIndicators() {
      return _smsOptionsData._voicemailIndicators;
   }

   public static final void setVoicemailIndicators(byte[] var0, int var1) {
      _smsOptionsData._voicemailIndicators = var0;
      _smsOptionsData._imsiCRC = var1;
      commit(false);
   }

   public static final int getRoute() {
      return _smsOptionsData._smsRoute;
   }

   public static final void setRoute(int var0) {
      _smsOptionsData._smsRoute = var0;
      if (RadioInfo.areWAFsSupported(1)) {
         RadioInternal.smsSetRoute(var0);
      }

      commit(true);
   }

   public static final boolean getDeliveryReports() {
      return _smsOptionsData._deliveryReports;
   }

   public static final void setDeliveryReports(boolean var0) {
      _smsOptionsData._deliveryReports = var0;
      commit(true);
   }

   public static final boolean getMultipleRecipients() {
      return _smsOptionsData._multipleRecipients;
   }

   public static final void setMultipleRecipients(boolean var0) {
      _smsOptionsData._multipleRecipients = var0;
      commit(true);
   }

   public static final boolean getStoreOnSIM() {
      return SIMCard.isSupported() ? _smsOptionsData._storeOnSIM : false;
   }

   public static final void setStoreOnSIM(boolean var0) {
      _smsOptionsData._storeOnSIM = var0;
      if (RadioInfo.areWAFsSupported(1)) {
         RadioInternal.smsStoreOnSIM(var0);
      }

      commit(true);
   }

   public static final boolean getDisableAutoText() {
      return _smsOptionsData._disableAutoText;
   }

   public static final boolean getEnableCellBroadcast() {
      return _smsOptionsData._enableCellBroadcast;
   }

   public static final void setEnableCellBroadcast(boolean var0) {
      _smsOptionsData._enableCellBroadcast = var0;
      commit(true);
   }

   public static final void setDisableAutoText(boolean var0) {
      _smsOptionsData._disableAutoText = var0;
      commit(true);
   }

   public static final int getNumPreviousItems() {
      return _smsOptionsData._numPreviousItems;
   }

   public static final void setNumPreviousItems(int var0) {
      _smsOptionsData._numPreviousItems = var0;
      commit(true);
   }

   public static final int getPresetUiId() {
      return _smsOptionsData._presetUiId;
   }

   public static final void setUiId(int var0, boolean var1) {
      if (!_smsOptionsData._smsUiPreset || var1) {
         _smsOptionsData._smsUiPreset = true;
         boolean var2 = _smsOptionsData._presetUiId != var0;
         _smsOptionsData._presetUiId = var0;
         commit(false);
         if (var2) {
            RIMGlobalMessagePoster.postGlobalEvent(7884295420352689779L);
         }
      }
   }

   public static final int getMessageListUiId() {
      return _smsOptionsData._messageListUiId;
   }

   public static final void setMessageListUiId(int var0, boolean var1) {
      if (!_smsOptionsData._messageListUiPreset || var1) {
         _smsOptionsData._messageListUiPreset = true;
         _smsOptionsData._messageListUiId = var0;
         commit(false);
      }
   }

   public static final int getFallbackCoding() {
      return _smsOptionsData._fallbackMessageCoding;
   }

   public static final void setFallbackCoding(int var0) {
      _smsOptionsData._fallbackMessageCoding = var0;
      commit(true);
   }
}
