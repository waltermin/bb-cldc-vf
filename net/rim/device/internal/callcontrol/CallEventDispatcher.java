package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.ApplicationManager;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.vm.Message;

final class CallEventDispatcher extends AbstractCallEventHandler {
   public CallEventDispatcher() {
      super(2100);
   }

   @Override
   public final void callAdded(int var1) {
      this.postCallEvent(1560, var1);
   }

   @Override
   public final void callConnected(int var1) {
      this.postCallEvent(1555, var1);
   }

   @Override
   public final void callDelivered(int var1) {
      this.postCallEvent(1563, var1);
   }

   @Override
   public final void callDisconnected(int var1) {
      this.postCallEvent(1557, var1);
   }

   @Override
   public final void callDisplayUpdated(int var1) {
      this.postCallEvent(1574, var1);
   }

   @Override
   public final void callFailed(int var1, int var2) {
      this.postCallEvent(1556, var1, var2, 0);
   }

   @Override
   public final void callHeld(int var1) {
      this.postCallEvent(1558, var1);
   }

   @Override
   public final void callIncoming(int var1) {
      this.postCallEvent(1553, var1);
   }

   @Override
   public final void callInitiated(int var1) {
      this.postCallEvent(1564, var1);
   }

   @Override
   public final void callManipulateFailed(int var1, int var2) {
      this.postCallEvent(1562, var1, var2, 0);
   }

   @Override
   public final void callOTAStatusUpdated(int var1, int var2) {
      this.postCallEvent(1600, var1, var2, 0);
   }

   @Override
   public final void callRemoved(int var1) {
      this.postCallEvent(1561, var1);
   }

   @Override
   public final void callResumed(int var1) {
      this.postCallEvent(1559, var1);
   }

   @Override
   public final void callTransferred(int var1, int var2) {
      this.postCallEvent(1668, var1, var2, 0);
   }

   @Override
   public final void callTransferStateUpdated(int var1, int var2) {
      this.postCallEvent(5001, var1, var2, 0);
   }

   @Override
   public final void callWaiting(int var1) {
      this.postCallEvent(1554, var1);
   }

   @Override
   public final void callVoicePrivacyUpdated(int var1, boolean var2) {
      this.postCallEvent(1605, var1, var2 ? 0 : 1, 0);
   }

   @Override
   public final void dtmfData(int var1) {
      this.postCallEvent(5003, var1);
   }

   @Override
   public final void callTimerUpdated(int var1, int var2) {
      this.postTimerEvent(1568, var1, var2, 0);
   }

   @Override
   public final void featureReady() {
      this.postControlEvent(1589, 0);
   }

   @Override
   public final void responseEnableFDN(int var1) {
      this.postControlEvent(var1, 0);
   }

   @Override
   public final void ssNotification(int var1) {
      this.postControlEvent(1609, var1);
   }

   @Override
   public final void ssPasswordRequested(int var1) {
      this.postControlEvent(1610, var1);
   }

   @Override
   public final void ssRequestFailed(int var1, int var2, boolean var3) {
      int var4 = var2 | (var3 ? 256 : 0);
      this.postControlEvent(1569, var1, 0, var4, null);
   }

   @Override
   public final void ssRequestInvalidPassword() {
      this.postControlEvent(1573, 0);
   }

   @Override
   public final void ssRequestRejected(boolean var1) {
      int var2 = var1 ? 256 : 0;
      this.postControlEvent(1571, 0, 0, var2, null);
   }

   @Override
   public final void ssRequestReleased(boolean var1) {
      int var2 = var1 ? 256 : 0;
      this.postControlEvent(1572, 0, 0, var2, null);
   }

   @Override
   public final void ssRequestSucceeded(int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      int var7 = var2 | var3 << 16;
      int var8 = var4 | (var5 ? 256 : 0) | (var6 ? 65536 : 0);
      this.postControlEvent(1570, var1, var7, var8, null);
   }

   @Override
   public final void ssUpdated(int var1, int var2) {
      this.postControlEvent(1606, 0, var1, var2, null);
   }

   @Override
   public final void ssUssDisplay(byte[] var1, int var2, boolean var3) {
      int var4 = var3 ? 1608 : 1607;
      this.postControlEvent(var4, 0, var2, 0, var1);
   }

   @Override
   public final void voiceLineChanged(int var1) {
      this.postControlEvent(1669, var1);
   }

   @Override
   public final void alternateLinesUpdated() {
      this.postControlEvent(5000, 0);
   }

   @Override
   public final void voicemailCountUpdated(int var1, int var2) {
      this.postControlEvent(5002, var1, var2, 0, null);
   }

   private final void postCallEvent(int var1, int var2) {
      this.postCallEvent(var1, var2, 0, 0);
   }

   private final void postCallEvent(int var1, int var2, int var3, int var4) {
      Object var5 = new Object(52, var1, var2, var3, var4);
      Object var6 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var6).postMessage((Message)var5);
   }

   private final void postCallEvent(int var1, int var2, int var3, int var4, Object var5) {
      Object var6 = new Object(52, var1, var2, var3, var4);
      ((Message)var6).setObject0(var5);
      Object var7 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var7).postMessage((Message)var6);
   }

   private final void postTimerEvent(int var1, int var2, int var3, int var4) {
      Object var5 = new Object(53, var1, var2, var3, var4);
      Object var6 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var6).postMessage((Message)var5);
   }

   private final void postControlEvent(int var1, int var2) {
      this.postControlEvent(var1, var2, 0, 0, null);
   }

   private final void postControlEvent(int var1, int var2, int var3, int var4, Object var5) {
      Object var6 = new Object(54, var1, var2, var3, var4);
      ((Message)var6).setObject0(var5);
      Object var7 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var7).postMessage((Message)var6);
   }
}
