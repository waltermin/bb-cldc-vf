package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.callcontrol.CallControlSystem;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.Security;

public class Phone {
   private int _defaultCLIR = 0;
   public static final long GUID_PHONE_INITIATE_CALL;
   public static final int CALL_STATE_NOT_CONNECTED;
   public static final int CALL_STATE_OUTGOING;
   public static final int CALL_STATE_INCOMING;
   public static final int CALL_STATE_ACTIVE;
   public static final int CALL_STATE_ON_HOLD;
   public static final int CALL_STATE_IN_CONFERENCE;
   public static final int CALL_STATE_CONFERENCE_HOLD;
   public static final int SS_CALL_FORWARD_UNCONDITIONAL;
   public static final int SS_CALL_FORWARD_BUSY;
   public static final int SS_CALL_FORWARD_NO_REPLY;
   public static final int SS_CALL_FORWARD_NOT_REACHABLE;
   public static final int SS_CALL_BARRING_OUTGOING_DEACTIVATE_ALL;
   public static final int SS_CALL_BARRING_OUTGOING;
   public static final int SS_CALL_BARRING_OUTGOING_INTL;
   public static final int SS_CALL_BARRING_OUTGOING_INTL_PLMN;
   public static final int SS_CALL_BARRING_INCOMING_DEACTIVATE_ALL;
   public static final int SS_CALL_BARRING_INCOMING;
   public static final int SS_CALL_BARRING_INCOMING_WHEN_ROAMING;
   public static final int SS_CLIP;
   public static final int SS_CLIR;
   public static final int SS_COLP;
   public static final int SS_COLR;
   public static final int SS_ALL_FORWARDING;
   public static final int SS_ALL_COND_FORWARDING;
   public static final int SS_ALL_CALL_RESTRICTION;
   public static final int SS_CALL_WAITING;
   public static final int CLIR_OFF;
   public static final int CLIR_INVOCATION;
   public static final int CLIR_SUPPRESSION;
   public static final int SS_OPTION_PROVISIONED;
   public static final int SS_OPTION_ACTIVE;
   public static final int SS_OPTION_REGISTERED;
   public static final int SS_OPTION_QUIESCENT;
   public static final int FEATURE_REJECT;
   public static final int FEATURE_FLASH;
   public static final int FEATURE_SINGLE_FLASH_3WC;
   public static final int FEATURE_HOLD;
   public static final int FEATURE_SPLIT;
   public static final int FEATURE_ADD;
   public static final int FEATURE_REMOVE;
   public static final int FEATURE_DIRECT_CONNECT;
   public static final int FEATURE_TTY;
   public static final int FEATURE_ECT;
   public static final int FEATURE_E911CB_EXIT;
   public static final int FEATURE_ALS;
   public static final int FEATURE_SS_CALL_BARRING;
   public static final int FEATURE_SS_CALL_FORWARD_UNCONDITIONAL;
   public static final int FEATURE_SS_CALL_FORWARD_BUSY;
   public static final int FEATURE_SS_CALL_FORWARD_NO_REPLY;
   public static final int FEATURE_SS_CALL_FORWARD_NOT_REACHABLE;
   public static final int FEATURE_SS_CALL_WAITING;
   public static final int FEATURE_SS_CLIR;
   public static final int FEATURE_HAC;
   public static final int CLIP_DISPLAY_MODE_ALLOWED;
   public static final int CLIP_DISPLAY_MODE_PRIVATE;
   public static final int CLIP_DISPLAY_MODE_UNKNOWN;
   public static final int INVALID_CALL_ID;
   public static final int CALL_IN_CONFERENCE;
   public static final int SS_CALL_ID;
   public static final int SS_BEARER_SERVICE_NONE;
   public static final int SS_BEARER_SERVICE_ALL_TELESERVICES;
   public static final int SS_BEARER_SERVICE_TELEPHONY;
   public static final int SS_BEARER_SERVICE_FACSIMILE;
   public static final int SS_BEARER_SERVICE_ALL_SMS;
   public static final int SS_BEARER_SERVICE_ALL_EXCEPT_SMS;
   public static final int SS_BEARER_SERVICE_ALL;
   public static final int SS_BEARER_SERVICE_ASYNC_SERVICES;
   public static final int SS_BEARER_SERVICE_SYNC_SERVICES;
   public static final int SS_BEARER_SERVICE_PLMN_TELESERVICE;
   public static final int SS_BEARER_SERVICE_PLMN;
   public static final int SS_BEARER_SERVICE_AUX_TELEPHONY;
   public static final int SS_BEARER_SERVICE_INVALID;
   public static final int CALL_TRANSFER_ACTION_BEGIN;
   public static final int CALL_TRANSFER_ACTION_SWAP;
   public static final int CALL_TRANSFER_ACTION_JOIN;
   public static final int CALL_TRANSFER_ACTION_COMPLETE;
   public static final int CALL_TRANSFER_ACTION_CANCEL;
   public static final int CALL_TRANSFER_ACTION_COUNT;
   public static final int ALTERNATE_LINE_1;
   public static final int ALTERNATE_LINE_2;

   public static boolean isSupported() {
      return true;
   }

   public static Phone getInstance() {
      return CallControlSystem.getCommandHandler();
   }

   public static int initiateCall(String var0, int var1) {
      ApplicationControl.assertPhonePermitted(true, CommonResource.getBundle(), 10045);
      if (ApplicationManager.getApplicationManager().isSystemLocked() && !Security.getInstance().getAllowOutgoingCallWhileLocked()) {
         return 0;
      }

      int var2 = getInstance().startCall(var0, var1);
      RIMGlobalMessagePoster.postGlobalEvent(-5324686711008477091L, var2, 0, var0, null);
      return var2;
   }

   public final int startCall(String var1) {
      return this.startCall(var1, this.getCLIR());
   }

   public int startCall(String var1, int var2) {
      throw null;
   }

   public void answerCall(int var1) {
      throw null;
   }

   public String getNumber(int var1) {
      throw null;
   }

   public void stopCall(int var1) {
      throw null;
   }

   public void stopAllCalls(boolean var1) {
      throw null;
   }

   public void rejectCall(int var1) {
      throw null;
   }

   public void holdCall() {
      throw null;
   }

   public void resumeCall() {
      throw null;
   }

   public void swapCalls() {
      throw null;
   }

   public void transferCall() {
      throw null;
   }

   public void addCallToConference() {
      throw null;
   }

   public void removeCallFromConference(int var1) {
      throw null;
   }

   public void startDTMF(int var1, byte var2) {
      throw null;
   }

   public void stopDTMF(int var1) {
      throw null;
   }

   public int getMaxConferenceMembers() {
      throw null;
   }

   public int getActiveCallId() {
      throw null;
   }

   public int getHeldCallId() {
      throw null;
   }

   public int getIncomingCallId() {
      throw null;
   }

   public int getCallState(int var1) {
      throw null;
   }

   public int getCallDuration(int var1) {
      throw null;
   }

   public boolean isCallRedirected(int var1) {
      throw null;
   }

   public int getCLIPDisplayMode(int var1) {
      throw null;
   }

   public final int getCLIR() {
      return this._defaultCLIR;
   }

   public final void setCLIR(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getCallPhoneNumber(int var1) {
      throw null;
   }

   public String getCallPhoneNumber(int var1, boolean var2) {
      throw null;
   }

   public String getCallName(int var1) {
      throw null;
   }

   public String getCallName(int var1, boolean var2) {
      throw null;
   }

   public void querySSOption(int var1) {
      throw null;
   }

   public int querySSOptionResult(int var1, int var2) {
      throw null;
   }

   public void setCallForwardingNumber(int var1, String var2) {
      throw null;
   }

   public String getCallForwardingNumber(int var1) {
      throw null;
   }

   public boolean isCallForwardUnconditionalActive(int var1) {
      throw null;
   }

   public void deactivateCallForwarding() {
      throw null;
   }

   public void activateCallBarring(boolean var1, int var2, String var3) {
      throw null;
   }

   public void setCallBarringPassword(String var1, String var2) {
      throw null;
   }

   public void flash(String var1) {
      throw null;
   }

   public void activateCallWaiting(boolean var1) {
      throw null;
   }

   public int getNetworkFeatures() {
      throw null;
   }

   public void setUSSDResponse(byte[] var1) {
      throw null;
   }

   public String getForwardingNumber() {
      throw null;
   }

   public String getForwardingNumberForService(int var1, int var2) {
      throw null;
   }

   public void requestEnableFDN(boolean var1) {
      throw null;
   }

   public boolean isFDNAvailable() {
      throw null;
   }

   public boolean isFDNEnabled() {
      throw null;
   }

   public boolean inCallDTMFDigitsEntered(String var1) {
      throw null;
   }

   public boolean endEmergencyCallbackMode() {
      throw null;
   }

   public boolean isActive() {
      throw null;
   }

   public static boolean isPhoneActive() {
      return getInstance().isActive();
   }

   public void disableDTMFEcho(boolean var1) {
      throw null;
   }

   public boolean isEmergencyNumber(String var1) {
      throw null;
   }

   public String getEmergencyNumber() {
      throw null;
   }

   public static void setDTMFMode(boolean var0) {
      getInstance().disableDTMFEcho(var0);
   }

   public void setSSBasicService(int var1) {
      throw null;
   }

   public void sendSSPasswordResponse(String var1) {
      throw null;
   }

   public boolean setAlternateLine(int var1) {
      throw null;
   }

   public int getAlternateLine(int var1) {
      throw null;
   }

   public boolean canInvokeCallTransferAction(int var1, int var2) {
      throw null;
   }

   public boolean canHold(int var1) {
      throw null;
   }

   public boolean canSwap(int var1) {
      throw null;
   }

   public boolean canJoin(int var1) {
      throw null;
   }

   public boolean canPark(int var1) {
      throw null;
   }

   public boolean canSendToVoicemail(int var1) {
      throw null;
   }

   public String getAlternateLineLabel(int var1) {
      throw null;
   }

   public boolean isAlternateLineAvailable(int var1) {
      throw null;
   }

   public void setAlternateLineLabel(int var1, String var2) {
      throw null;
   }

   public String getAlternateLineNumber(int var1) {
      throw null;
   }

   public int[] getAlternateLines() {
      throw null;
   }

   public int getCallTransferState(int var1) {
      throw null;
   }

   public String getVoiceMailNumber(int var1) {
      throw null;
   }

   public int getVoiceMailCount(int var1) {
      throw null;
   }

   public int getWAFs(int var1) {
      throw null;
   }

   public boolean invokeCallTransferAction(int var1, int var2, Object var3) {
      throw null;
   }

   public void parkCall(int var1) {
      throw null;
   }

   public void sendToVoicemail(int var1) {
      throw null;
   }

   public boolean supportsCorporateExtensions(int var1) {
      throw null;
   }
}
