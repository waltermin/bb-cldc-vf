package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.PhoneListener;

class AbstractCallEventHandler implements PhoneListener {
   private int _order;
   private AbstractCallEventHandler _next;
   private boolean _registered;
   private static final long HANDLERS_GUID;
   private static AbstractCallEventHandler _first;

   protected AbstractCallEventHandler(int var1) {
      this._order = var1;
   }

   public final void register() {
      if (this._order >= 100 && this._order <= 2000) {
         internalRegister(this);
      } else {
         throw new Object();
      }
   }

   public final void deregister() {
      this._registered = false;
      this.onDeregistration();
   }

   public final boolean isRegistered() {
      return this._registered;
   }

   protected void onRegistration() {
   }

   protected void onDeregistration() {
   }

   protected final PhoneListener getNext() {
      AbstractCallEventHandler var1 = this._next;

      while (!var1.isRegistered()) {
         var1 = var1._next;
      }

      return var1;
   }

   static void internalRegister(AbstractCallEventHandler var0) {
      if (var0._next != null) {
         if (!var0._registered) {
            var0._registered = true;
            var0.onRegistration();
         }
      } else {
         int var1 = var0._order;
         AbstractCallEventHandler var2 = null;

         AbstractCallEventHandler var3;
         for (var3 = getFirstHandler(); var3 != null; var3 = var3._next) {
            if (var3._order == var1) {
               AbstractCallEventHandler var4 = var3;
               var3 = var4._next;
               var4._next = null;
               var4.deregister();
               break;
            }

            if (var3._order > var1) {
               break;
            }

            var2 = var3;
         }

         var0._next = var3;
         if (var2 == null) {
            setFirstHandler(var0);
         } else {
            var2._next = var0;
         }

         var0._registered = true;
         var0.onRegistration();
      }
   }

   private static AbstractCallEventHandler getFirstHandler() {
      if (_first == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _first = (AbstractCallEventHandler)var0.getOrWaitFor(-3955160615553205537L);
      }

      return _first;
   }

   private static void setFirstHandler(AbstractCallEventHandler var0) {
      _first = var0;
      ApplicationRegistry var1 = ApplicationRegistry.getApplicationRegistry();
      var1.replace(-3955160615553205537L, _first);
   }

   @Override
   public void dtmfData(int var1) {
      throw null;
   }

   @Override
   public void callOTAStatusUpdated(int var1, int var2) {
      throw null;
   }

   @Override
   public void callVoicePrivacyUpdated(int var1, boolean var2) {
      throw null;
   }

   @Override
   public void callTransferStateUpdated(int var1, int var2) {
      throw null;
   }

   @Override
   public void callTransferred(int var1, int var2) {
      throw null;
   }

   @Override
   public void callRemoved(int var1) {
      throw null;
   }

   @Override
   public void callAdded(int var1) {
      throw null;
   }

   @Override
   public void callResumed(int var1) {
      throw null;
   }

   @Override
   public void callHeld(int var1) {
      throw null;
   }

   @Override
   public void callDisconnected(int var1) {
      throw null;
   }

   @Override
   public void callManipulateFailed(int var1, int var2) {
      throw null;
   }

   @Override
   public void callDelivered(int var1) {
      throw null;
   }

   @Override
   public void callFailed(int var1, int var2) {
      throw null;
   }

   @Override
   public void callConnected(int var1) {
      throw null;
   }

   @Override
   public void callInitiated(int var1) {
      throw null;
   }

   @Override
   public void callWaiting(int var1) {
      throw null;
   }

   @Override
   public void callDisplayUpdated(int var1) {
      throw null;
   }

   @Override
   public void callIncoming(int var1) {
      throw null;
   }

   @Override
   public void callTimerUpdated(int var1, int var2) {
      throw null;
   }

   @Override
   public void alternateLinesUpdated() {
      throw null;
   }

   @Override
   public void voicemailCountUpdated(int var1, int var2) {
      throw null;
   }

   @Override
   public void voiceLineChanged(int var1) {
      throw null;
   }

   @Override
   public void responseEnableFDN(int var1) {
      throw null;
   }

   @Override
   public void featureReady() {
      throw null;
   }

   @Override
   public void ssUssDisplay(byte[] var1, int var2, boolean var3) {
      throw null;
   }

   @Override
   public void ssNotification(int var1) {
      throw null;
   }

   @Override
   public void ssUpdated(int var1, int var2) {
      throw null;
   }

   @Override
   public void ssPasswordRequested(int var1) {
      throw null;
   }

   @Override
   public void ssRequestInvalidPassword() {
      throw null;
   }

   @Override
   public void ssRequestReleased(boolean var1) {
      throw null;
   }

   @Override
   public void ssRequestRejected(boolean var1) {
      throw null;
   }

   @Override
   public void ssRequestFailed(int var1, int var2, boolean var3) {
      throw null;
   }

   @Override
   public void ssRequestSucceeded(int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      throw null;
   }
}
