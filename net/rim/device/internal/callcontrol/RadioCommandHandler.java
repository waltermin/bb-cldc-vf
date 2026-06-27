package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Phone;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.internal.system.PhoneFirmwareImpl;

final class RadioCommandHandler extends AbstractCallCommandHandler {
   private RadioEventHandler _eventHandler;
   private static final int WORLD_PHONE_WAFS;
   private static final boolean CDMA_GSM_WORLD_PHONE;
   static final int CELL_WAFS;

   public RadioCommandHandler() {
      super(10);
      AbstractCallCommandHandler.internalRegister(this);
      this._eventHandler = new RadioEventHandler();
   }

   public final void startListening(Application var1) {
      this._eventHandler.startListening(var1);
   }

   @Override
   public final boolean canInvokeCallTransferAction(int var1, int var2) {
      return false;
   }

   @Override
   public final boolean canHold(int var1) {
      int var2 = Phone.getInstance().getNetworkFeatures();
      return (var2 & 4) != 0;
   }

   @Override
   public final boolean canSwap(int var1) {
      return this.canHold(var1);
   }

   @Override
   public final boolean canJoin(int var1) {
      return true;
   }

   @Override
   public final boolean canPark(int var1) {
      return false;
   }

   @Override
   public final boolean canSendToVoicemail(int var1) {
      return false;
   }

   @Override
   public final String getAlternateLineLabel(int var1) {
      return null;
   }

   @Override
   public final boolean isAlternateLineAvailable(int var1) {
      int var2 = this.getActiveCallId();
      return var2 == 0 ? true : var1 == this.getAlternateLine(var2);
   }

   @Override
   public final void setAlternateLineLabel(int var1, String var2) {
   }

   @Override
   public final String getAlternateLineNumber(int var1) {
      switch (var1) {
         case 0:
            return null;
         case 1:
         default:
            return this.getNumber(0);
         case 2:
            return this.getNumber(1);
      }
   }

   @Override
   public final int[] getAlternateLines() {
      throw new RuntimeException("cod2jar: array init");
   }

   @Override
   public final int getCallTransferState(int var1) {
      return 1;
   }

   @Override
   public final String getVoiceMailNumber(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getVoiceMailCount(int var1) {
      return 0;
   }

   @Override
   public final int getWAFs(int var1) {
      return CELL_WAFS;
   }

   @Override
   public final boolean invokeCallTransferAction(int var1, int var2, Object var3) {
      return false;
   }

   @Override
   public final void parkCall(int var1) {
      this._eventHandler.callManipulateFailed(var1, 0);
   }

   @Override
   public final void sendToVoicemail(int var1) {
      this._eventHandler.callManipulateFailed(var1, 0);
   }

   @Override
   public final boolean supportsCorporateExtensions(int var1) {
      return false;
   }

   @Override
   public final void activateCallBarring(boolean var1, int var2, String var3) {
      PhoneFirmwareImpl.activateCallBarring(var1, var2, var3);
   }

   @Override
   public final void activateCallWaiting(boolean var1) {
      PhoneFirmwareImpl.activateCallWaiting(var1);
   }

   @Override
   public final void addCallToConference() {
      PhoneFirmwareImpl.addCallToConference();
   }

   @Override
   public final void answerCall(int var1) {
      PhoneFirmwareImpl.answerCall(var1);
   }

   @Override
   public final void deactivateCallForwarding() {
      PhoneFirmwareImpl.deactivateCallForwarding();
   }

   @Override
   public final void disableDTMFEcho(boolean var1) {
      PhoneFirmwareImpl.disableDTMFEcho(var1);
   }

   @Override
   public final boolean endEmergencyCallbackMode() {
      return PhoneFirmwareImpl.endEmergencyCallbackMode();
   }

   @Override
   public final void flash(String var1) {
      PhoneFirmwareImpl.flash(var1);
   }

   @Override
   public final int getActiveCallId() {
      return PhoneFirmwareImpl.getActiveCallId();
   }

   @Override
   public final int getAlternateLine(int var1) {
      return (2097152 & this.getNetworkFeatures()) != 0 ? PhoneFirmwareImpl.getAlternateLine(var1) : 1;
   }

   @Override
   public final int getCallDuration(int var1) {
      return PhoneFirmwareImpl.getCallDuration(var1);
   }

   @Override
   public final String getCallForwardingNumber(int var1) {
      return PhoneFirmwareImpl.getCallForwardingNumber(var1);
   }

   @Override
   public final String getCallName(int var1, boolean var2) {
      return PhoneFirmwareImpl.getCallName(var1, var2);
   }

   @Override
   public final String getCallPhoneNumber(int var1, boolean var2) {
      return PhoneFirmwareImpl.getCallPhoneNumber(var1, var2);
   }

   @Override
   public final int getCallState(int var1) {
      return PhoneFirmwareImpl.getCallState(var1);
   }

   @Override
   public final int getCLIPDisplayMode(int var1) {
      return PhoneFirmwareImpl.getCLIPDisplayMode(var1);
   }

   @Override
   public final String getEmergencyNumber() {
      return PhoneFirmwareImpl.getEmergencyNumber();
   }

   @Override
   public final String getForwardingNumber() {
      return PhoneFirmwareImpl.getForwardingNumber();
   }

   @Override
   public final String getForwardingNumberForService(int var1, int var2) {
      return PhoneFirmwareImpl.getForwardingNumberForService(var1, var2);
   }

   @Override
   public final int getHeldCallId() {
      return PhoneFirmwareImpl.getHeldCallId();
   }

   @Override
   public final int getIncomingCallId() {
      return PhoneFirmwareImpl.getIncomingCallId();
   }

   @Override
   public final int getMaxConferenceMembers() {
      return PhoneFirmwareImpl.getMaxConferenceMembers();
   }

   @Override
   public final int getNetworkFeatures() {
      return PhoneFirmwareImpl.getNetworkFeatures();
   }

   @Override
   public final String getNumber(int var1) {
      return PhoneFirmwareImpl.getNumber(var1);
   }

   @Override
   public final void holdCall() {
      PhoneFirmwareImpl.holdCall();
   }

   @Override
   public final boolean inCallDTMFDigitsEntered(String var1) {
      return PhoneFirmwareImpl.inCallDTMFDigitsEntered(var1);
   }

   @Override
   public final boolean isActive() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isCallForwardUnconditionalActive(int var1) {
      return PhoneFirmwareImpl.isCallForwardUnconditionalActive(var1);
   }

   @Override
   public final boolean isCallRedirected(int var1) {
      return PhoneFirmwareImpl.isCallRedirected(var1);
   }

   @Override
   public final boolean isEmergencyNumber(String var1) {
      return PhoneFirmwareImpl.isEmergencyNumber(var1);
   }

   @Override
   public final boolean isFDNAvailable() {
      return PhoneFirmwareImpl.isFDNAvailable();
   }

   @Override
   public final boolean isFDNEnabled() {
      return PhoneFirmwareImpl.isFDNEnabled();
   }

   @Override
   public final void querySSOption(int var1) {
      PhoneFirmwareImpl.querySSOption(var1);
   }

   @Override
   public final int querySSOptionResult(int var1, int var2) {
      return PhoneFirmwareImpl.querySSOptionResult(var1, var2);
   }

   @Override
   public final void removeCallFromConference(int var1) {
      PhoneFirmwareImpl.removeCallFromConference(var1);
   }

   @Override
   public final void rejectCall(int var1) {
      PhoneFirmwareImpl.rejectCall(var1);
   }

   @Override
   public final void requestEnableFDN(boolean var1) {
      PhoneFirmwareImpl.requestEnableFDN(var1);
   }

   @Override
   public final void resumeCall() {
      PhoneFirmwareImpl.resumeCall();
   }

   @Override
   public final void sendSSPasswordResponse(String var1) {
      PhoneFirmwareImpl.sendSSPasswordResponse(var1);
   }

   @Override
   public final boolean setAlternateLine(int var1) {
      if (RadioInfo.getNetworkType() == 3) {
         PhoneFirmwareImpl.setSSBasicService(2);
      }

      return PhoneFirmwareImpl.setAlternateLine(var1);
   }

   @Override
   public final void setCallBarringPassword(String var1, String var2) {
      PhoneFirmwareImpl.setCallBarringPassword(var1, var2);
   }

   @Override
   public final void setCallForwardingNumber(int var1, String var2) {
      PhoneFirmwareImpl.setCallForwardingNumber(var1, var2);
   }

   @Override
   public final void setSSBasicService(int var1) {
      PhoneFirmwareImpl.setSSBasicService(var1);
   }

   @Override
   public final void setUSSDResponse(byte[] var1) {
      PhoneFirmwareImpl.setUSSDResponse(var1);
   }

   @Override
   public final int startCall(String var1, int var2) {
      return PhoneFirmwareImpl.startCall(var1, var2);
   }

   @Override
   public final void startDTMF(int var1, byte var2) {
      PhoneFirmwareImpl.startDTMF(var1, var2);
   }

   @Override
   public final void stopAllCalls(boolean var1) {
      PhoneFirmwareImpl.stopAllCalls(var1);
   }

   @Override
   public final void stopCall(int var1) {
      PhoneFirmwareImpl.stopCall(var1);
   }

   @Override
   public final void stopDTMF(int var1) {
      PhoneFirmwareImpl.stopDTMF(var1);
   }

   @Override
   public final void swapCalls() {
      PhoneFirmwareImpl.swapCalls();
   }

   @Override
   public final void transferCall() {
      PhoneFirmwareImpl.transferCall();
   }
}
