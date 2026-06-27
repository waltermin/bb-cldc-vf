package net.rim.device.internal.callcontrol;

public class CallEventHandler extends AbstractCallEventHandler {
   protected CallEventHandler(int var1) {
   }

   @Override
   public void callAdded(int var1) {
      this.getNext().callAdded(var1);
   }

   @Override
   public void callConnected(int var1) {
      this.getNext().callConnected(var1);
   }

   @Override
   public void callDelivered(int var1) {
      this.getNext().callDelivered(var1);
   }

   @Override
   public void callDisconnected(int var1) {
      this.getNext().callDisconnected(var1);
   }

   @Override
   public void callDisplayUpdated(int var1) {
      this.getNext().callDisplayUpdated(var1);
   }

   @Override
   public void callFailed(int var1, int var2) {
      this.getNext().callFailed(var1, var2);
   }

   @Override
   public void callHeld(int var1) {
      this.getNext().callHeld(var1);
   }

   @Override
   public void callIncoming(int var1) {
      this.getNext().callIncoming(var1);
   }

   @Override
   public void callInitiated(int var1) {
      this.getNext().callInitiated(var1);
   }

   @Override
   public void callManipulateFailed(int var1, int var2) {
      this.getNext().callManipulateFailed(var1, var2);
   }

   @Override
   public void callOTAStatusUpdated(int var1, int var2) {
      this.getNext().callOTAStatusUpdated(var1, var2);
   }

   @Override
   public void callRemoved(int var1) {
      this.getNext().callRemoved(var1);
   }

   @Override
   public void callResumed(int var1) {
      this.getNext().callResumed(var1);
   }

   @Override
   public void callTransferred(int var1, int var2) {
      this.getNext().callTransferred(var1, var2);
   }

   @Override
   public void callWaiting(int var1) {
      this.getNext().callWaiting(var1);
   }

   @Override
   public void callVoicePrivacyUpdated(int var1, boolean var2) {
      this.getNext().callVoicePrivacyUpdated(var1, var2);
   }

   @Override
   public void callTransferStateUpdated(int var1, int var2) {
      this.getNext().callTransferStateUpdated(var1, var2);
   }

   @Override
   public void dtmfData(int var1) {
      this.getNext().dtmfData(var1);
   }

   @Override
   public void callTimerUpdated(int var1, int var2) {
      this.getNext().callTimerUpdated(var1, var2);
   }

   @Override
   public void featureReady() {
      this.getNext().featureReady();
   }

   @Override
   public void responseEnableFDN(int var1) {
      this.getNext().responseEnableFDN(var1);
   }

   @Override
   public void ssNotification(int var1) {
      this.getNext().ssNotification(var1);
   }

   @Override
   public void ssPasswordRequested(int var1) {
      this.getNext().ssPasswordRequested(var1);
   }

   @Override
   public void ssRequestFailed(int var1, int var2, boolean var3) {
      this.getNext().ssRequestFailed(var1, var2, var3);
   }

   @Override
   public void ssRequestInvalidPassword() {
      this.getNext().ssRequestInvalidPassword();
   }

   @Override
   public void ssRequestRejected(boolean var1) {
      this.getNext().ssRequestRejected(var1);
   }

   @Override
   public void ssRequestReleased(boolean var1) {
      this.getNext().ssRequestReleased(var1);
   }

   @Override
   public void ssRequestSucceeded(int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      this.getNext().ssRequestSucceeded(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void ssUpdated(int var1, int var2) {
      this.getNext().ssUpdated(var1, var2);
   }

   @Override
   public void ssUssDisplay(byte[] var1, int var2, boolean var3) {
      this.getNext().ssUssDisplay(var1, var2, var3);
   }

   @Override
   public void voiceLineChanged(int var1) {
      this.getNext().voiceLineChanged(var1);
   }

   @Override
   public void alternateLinesUpdated() {
      this.getNext().alternateLinesUpdated();
   }

   @Override
   public void voicemailCountUpdated(int var1, int var2) {
      this.getNext().voicemailCountUpdated(var1, var2);
   }
}
