package net.rim.device.internal.callcontrol;

class CallControlLogger$CallCommandLogger extends AbstractCallCommandHandler {
   private final CallControlLogger this$0;

   public CallControlLogger$CallCommandLogger(CallControlLogger var1, int var2) {
      super(var2);
      this.this$0 = var1;
   }

   @Override
   public boolean canInvokeCallTransferAction(int var1, int var2) {
      this.this$0.logEvent(5, CallControlLogger.CANINVOKEXFER, var1, var2);
      boolean var3 = this.getNext().canInvokeCallTransferAction(var1, var2);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var3);
      return var3;
   }

   @Override
   public boolean canHold(int var1) {
      this.this$0.logEvent(5, CallControlLogger.CANHOLD, var1);
      boolean var2 = this.getNext().canHold(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean canSwap(int var1) {
      this.this$0.logEvent(5, CallControlLogger.CANSWAP, var1);
      boolean var2 = this.getNext().canSwap(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean canJoin(int var1) {
      this.this$0.logEvent(5, CallControlLogger.CANJOIN, var1);
      boolean var2 = this.getNext().canJoin(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean canPark(int var1) {
      this.this$0.logEvent(5, CallControlLogger.CANPARK, var1);
      boolean var2 = this.getNext().canPark(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean canSendToVoicemail(int var1) {
      this.this$0.logEvent(5, CallControlLogger.CANSENDTOVM, var1);
      boolean var2 = this.getNext().canSendToVoicemail(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public String getAlternateLineLabel(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETLINELABEL, var1);
      String var2 = this.getNext().getAlternateLineLabel(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean isAlternateLineAvailable(int var1) {
      this.this$0.logEvent(5, CallControlLogger.ISLINEAVAILABLE, var1);
      boolean var2 = this.getNext().isAlternateLineAvailable(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public void setAlternateLineLabel(int var1, String var2) {
      this.this$0.logEvent(5, CallControlLogger.SETLINELABEL, var1, var2);
      this.getNext().setAlternateLineLabel(var1, var2);
   }

   @Override
   public String getAlternateLineNumber(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETLINENUMBER, var1);
      String var2 = this.getNext().getAlternateLineNumber(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public int[] getAlternateLines() {
      this.this$0.logEvent(5, CallControlLogger.GETLINES);
      int[] var1 = this.getNext().getAlternateLines();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public int getCallTransferState(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETXFERSTATE, var1);
      int var2 = this.getNext().getCallTransferState(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public String getVoiceMailNumber(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETVMNUMBER, var1);
      String var2 = this.getNext().getVoiceMailNumber(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public int getVoiceMailCount(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETVMCOUNT, var1);
      int var2 = this.getNext().getVoiceMailCount(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public int getWAFs(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETWAFS, var1);
      int var2 = this.getNext().getWAFs(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean invokeCallTransferAction(int var1, int var2, Object var3) {
      this.this$0.logEvent(5, CallControlLogger.XFERACTION, var1, var2, var3);
      boolean var4 = this.getNext().invokeCallTransferAction(var1, var2, var3);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var4);
      return var4;
   }

   @Override
   public void parkCall(int var1) {
      this.this$0.logEvent(0, CallControlLogger.PARK, var1);
      this.getNext().parkCall(var1);
   }

   @Override
   public void sendToVoicemail(int var1) {
      this.this$0.logEvent(0, CallControlLogger.SENDTOVM, var1);
      this.getNext().sendToVoicemail(var1);
   }

   @Override
   public boolean supportsCorporateExtensions(int var1) {
      this.this$0.logEvent(5, CallControlLogger.SUPPORTSEXT, var1);
      boolean var2 = this.getNext().supportsCorporateExtensions(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public void activateCallBarring(boolean var1, int var2, String var3) {
      this.this$0.logEvent(5, CallControlLogger.ACTIVATEBARRING, var1, var2, var3);
      this.getNext().activateCallBarring(var1, var2, var3);
   }

   @Override
   public void activateCallWaiting(boolean var1) {
      this.this$0.logEvent(5, CallControlLogger.ACTIVATEWAITING, var1);
      this.getNext().activateCallWaiting(var1);
   }

   @Override
   public void addCallToConference() {
      this.this$0.logEvent(0, CallControlLogger.ADDTOCONF);
      this.getNext().addCallToConference();
   }

   @Override
   public void answerCall(int var1) {
      this.this$0.logEvent(0, CallControlLogger.ANSWER, var1);
      this.getNext().answerCall(var1);
   }

   @Override
   public void deactivateCallForwarding() {
      this.this$0.logEvent(5, CallControlLogger.DEACTIVATECALLFWD);
      this.getNext().deactivateCallForwarding();
   }

   @Override
   public void disableDTMFEcho(boolean var1) {
      this.this$0.logEvent(5, CallControlLogger.DISABLEDTMFECHO, var1);
      this.getNext().disableDTMFEcho(var1);
   }

   @Override
   public boolean endEmergencyCallbackMode() {
      this.this$0.logEvent(0, CallControlLogger.END911CALLBACKMODE);
      boolean var1 = this.getNext().endEmergencyCallbackMode();
      this.this$0.logEvent(0, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public void flash(String var1) {
      this.this$0.logEvent(0, CallControlLogger.FLASH, CallControlLogger.obfuscate(var1));
      this.getNext().flash(var1);
   }

   @Override
   public int getActiveCallId() {
      this.this$0.logEvent(5, CallControlLogger.GETACTIVECALL);
      int var1 = this.getNext().getActiveCallId();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public int getAlternateLine(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETLINE, var1);
      int var2 = this.getNext().getAlternateLine(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public int getCallDuration(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETDURATION, var1);
      int var2 = this.getNext().getCallDuration(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public String getCallForwardingNumber(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETCALLFWDNUMBER, var1);
      String var2 = this.getNext().getCallForwardingNumber(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public String getCallName(int var1, boolean var2) {
      this.this$0.logEvent(0, CallControlLogger.CALLNAME, var1, var2);
      String var3 = this.getNext().getCallName(var1, var2);
      this.this$0.logEvent(0, CallControlLogger.RESULT, CallControlLogger.obfuscate(var3));
      return var3;
   }

   @Override
   public String getCallPhoneNumber(int var1, boolean var2) {
      this.this$0.logEvent(0, CallControlLogger.GETCALLNUMBER, var1, var2);
      String var3 = this.getNext().getCallPhoneNumber(var1, var2);
      this.this$0.logEvent(0, CallControlLogger.RESULT, CallControlLogger.obfuscate(var3));
      return var3;
   }

   @Override
   public int getCallState(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETCALLSTATE, var1);
      int var2 = this.getNext().getCallState(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public int getCLIPDisplayMode(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETCLIP, var1);
      int var2 = this.getNext().getCLIPDisplayMode(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public String getEmergencyNumber() {
      this.this$0.logEvent(5, CallControlLogger.GETEMERGENCYNUMBER);
      String var1 = this.getNext().getEmergencyNumber();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public String getForwardingNumber() {
      this.this$0.logEvent(5, CallControlLogger.GETFWDNUMBER);
      String var1 = this.getNext().getForwardingNumber();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public String getForwardingNumberForService(int var1, int var2) {
      this.this$0.logEvent(5, CallControlLogger.GETFWDNUMBER, var1, var2);
      String var3 = this.getNext().getForwardingNumberForService(var1, var2);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var3);
      return var3;
   }

   @Override
   public int getHeldCallId() {
      this.this$0.logEvent(5, CallControlLogger.GETHELD);
      int var1 = this.getNext().getHeldCallId();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public int getIncomingCallId() {
      this.this$0.logEvent(5, CallControlLogger.GETINCOMING);
      int var1 = this.getNext().getIncomingCallId();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public int getMaxConferenceMembers() {
      this.this$0.logEvent(5, CallControlLogger.GETMAXCONFMEMBERS);
      int var1 = this.getNext().getMaxConferenceMembers();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public int getNetworkFeatures() {
      this.this$0.logEvent(5, CallControlLogger.GETNETWORKFEATURES);
      int var1 = this.getNext().getNetworkFeatures();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public String getNumber(int var1) {
      this.this$0.logEvent(5, CallControlLogger.GETNUMBER, var1);
      String var2 = this.getNext().getNumber(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public void holdCall() {
      this.this$0.logEvent(0, CallControlLogger.HOLD);
      this.getNext().holdCall();
   }

   @Override
   public boolean inCallDTMFDigitsEntered(String var1) {
      this.this$0.logEvent(5, CallControlLogger.INCALLDTMF, var1);
      boolean var2 = this.getNext().inCallDTMFDigitsEntered(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean isActive() {
      this.this$0.logEvent(5, CallControlLogger.ISACTIVE);
      boolean var1 = this.getNext().isActive();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public boolean isCallForwardUnconditionalActive(int var1) {
      this.this$0.logEvent(5, CallControlLogger.ISCFUACTIVE, var1);
      boolean var2 = this.getNext().isCallForwardUnconditionalActive(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean isCallRedirected(int var1) {
      this.this$0.logEvent(5, CallControlLogger.ISREDIRECTED, var1);
      boolean var2 = this.getNext().isCallRedirected(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean isEmergencyNumber(String var1) {
      this.this$0.logEvent(5, CallControlLogger.ISEMERGENCYNUMBER, var1);
      boolean var2 = this.getNext().isEmergencyNumber(var1);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public boolean isFDNAvailable() {
      this.this$0.logEvent(5, CallControlLogger.ISFDNAVAILABLE);
      boolean var1 = this.getNext().isFDNAvailable();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public boolean isFDNEnabled() {
      this.this$0.logEvent(5, CallControlLogger.ISFDNENABLED);
      boolean var1 = this.getNext().isFDNEnabled();
      this.this$0.logEvent(5, CallControlLogger.RESULT, var1);
      return var1;
   }

   @Override
   public void querySSOption(int var1) {
      this.this$0.logEvent(5, CallControlLogger.QUERYSSOPTION, var1);
      this.getNext().querySSOption(var1);
   }

   @Override
   public int querySSOptionResult(int var1, int var2) {
      this.this$0.logEvent(5, CallControlLogger.QUERYSSOPTIONRESULT, var1, var2);
      int var3 = this.getNext().querySSOptionResult(var1, var2);
      this.this$0.logEvent(5, CallControlLogger.RESULT, var3);
      return var3;
   }

   @Override
   public void removeCallFromConference(int var1) {
      this.this$0.logEvent(5, CallControlLogger.REMOVECALL, var1);
      this.getNext().removeCallFromConference(var1);
   }

   @Override
   public void rejectCall(int var1) {
      this.this$0.logEvent(0, CallControlLogger.REJECTCALL, var1);
      this.getNext().rejectCall(var1);
   }

   @Override
   public void requestEnableFDN(boolean var1) {
      this.this$0.logEvent(5, CallControlLogger.REQUESTENABLEFDN);
      this.getNext().requestEnableFDN(var1);
   }

   @Override
   public void resumeCall() {
      this.this$0.logEvent(0, CallControlLogger.RESUMECALL);
      this.getNext().resumeCall();
   }

   @Override
   public void sendSSPasswordResponse(String var1) {
      this.this$0.logEvent(5, CallControlLogger.SENDSSPWDRESPONSE, var1);
      this.getNext().sendSSPasswordResponse(var1);
   }

   @Override
   public boolean setAlternateLine(int var1) {
      this.this$0.logEvent(0, CallControlLogger.SETLINE, var1);
      boolean var2 = this.getNext().setAlternateLine(var1);
      this.this$0.logEvent(0, CallControlLogger.RESULT, var2);
      return var2;
   }

   @Override
   public void setCallBarringPassword(String var1, String var2) {
      this.this$0.logEvent(5, CallControlLogger.SETBARRINGPWD, var1, var2);
      this.getNext().setCallBarringPassword(var1, var2);
   }

   @Override
   public void setCallForwardingNumber(int var1, String var2) {
      this.this$0.logEvent(5, CallControlLogger.SETFWDNUMBER, var1, var2);
      this.getNext().setCallForwardingNumber(var1, var2);
   }

   @Override
   public void setSSBasicService(int var1) {
      this.this$0.logEvent(5, CallControlLogger.SETSSBASICSERVICE, var1);
      this.getNext().setSSBasicService(var1);
   }

   @Override
   public void setUSSDResponse(byte[] var1) {
      this.this$0.logEvent(5, CallControlLogger.SETUSSDRESPONSE, var1);
      this.getNext().setUSSDResponse(var1);
   }

   @Override
   public int startCall(String var1, int var2) {
      this.this$0.logEvent(0, CallControlLogger.STARTCALL, CallControlLogger.obfuscate(var1), var2);
      int var3 = this.getNext().startCall(var1, var2);
      this.this$0.logEvent(0, CallControlLogger.RESULT, var3);
      return var3;
   }

   @Override
   public void startDTMF(int var1, byte var2) {
      this.this$0.logEvent(5, CallControlLogger.STARTDTMF, var1, var2);
      this.getNext().startDTMF(var1, var2);
   }

   @Override
   public void stopAllCalls(boolean var1) {
      this.this$0.logEvent(0, CallControlLogger.STOPALLCALLS, var1);
      this.getNext().stopAllCalls(var1);
   }

   @Override
   public void stopCall(int var1) {
      this.this$0.logEvent(0, CallControlLogger.STOPCALL, var1);
      this.getNext().stopCall(var1);
   }

   @Override
   public void stopDTMF(int var1) {
      this.this$0.logEvent(5, CallControlLogger.STOPDTMF, var1);
      this.getNext().stopDTMF(var1);
   }

   @Override
   public void swapCalls() {
      this.this$0.logEvent(0, CallControlLogger.SWAP);
      this.getNext().swapCalls();
   }

   @Override
   public void transferCall() {
      this.this$0.logEvent(0, CallControlLogger.XFER);
      this.getNext().transferCall();
   }
}
