package net.rim.device.internal.system;

import net.rim.device.api.system.AlertListener;
import net.rim.device.api.system.DirectConnectListener;
import net.rim.device.api.system.GANStatusListener;
import net.rim.device.api.system.HolsterListener;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.system.ModemListener;
import net.rim.device.api.system.PhoneCallListener;
import net.rim.device.api.system.PhoneControlListener;
import net.rim.device.api.system.PhoneTimerListener;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.RadioListener;
import net.rim.device.api.system.RadioPacketListener;
import net.rim.device.api.system.RealtimeClockListener;
import net.rim.device.api.system.StylusListener;
import net.rim.device.api.system.SystemListener;
import net.rim.device.api.system.TrackwheelListener;
import net.rim.device.api.system.USBPortListener;
import net.rim.device.api.system.WLANListenerInternal;
import net.rim.device.internal.crypto.vpn.VPNListener;
import net.rim.device.internal.io.NativeSocketListener;
import net.rim.vm.Message;

public final class Events {
   public static final void dispatchSystemEvent(int var0, int var1, int var2, Object var3, SystemListener var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean dispatchKeyEvent(int var0, char var1, int var2, int var3, KeyListener var4) {
      switch (var0) {
         case 513:
            return var4.keyDown(var2, var3);
         case 514:
            return var4.keyRepeat(var2, var3);
         case 515:
            return var4.keyUp(var2, var3);
         case 520:
            return var4.keyStatus(var2, var3);
         case 32768:
            return var4.keyChar(var1, var2 & 65535, var3);
         default:
            return false;
      }
   }

   public static final boolean dispatchStylusEvent(int var0, int var1, int var2, int var3, int var4, StylusListener var5) {
      switch (var0) {
         case 6655:
            return false;
         case 6656:
         default:
            return var5.stylusDown(var1, var2, var3, var4);
         case 6657:
            return var5.stylusDrag(var1, var2, var3, var4);
         case 6658:
            return var5.stylusUp(var1, var2, var3, var4);
         case 6659:
            return var5.stylusTap(var1, var2, var3, var4);
         case 6660:
            return var5.stylusTapHold(var1, var2, var3, var4);
         case 6661:
            return var5.stylusDoubleTap(var1, var2, var3, var4);
      }
   }

   public static final boolean dispatchTrackwheelEvent(int var0, int var1, int var2, int var3, TrackwheelListener var4) {
      switch (var0) {
         case 515:
         case 518:
            return false;
         case 516:
         default:
            return var4.trackwheelClick(var2, var3);
         case 517:
            return var4.trackwheelUnclick(var2, var3);
         case 519:
            return var4.trackwheelRoll(var1, var2, var3);
      }
   }

   public static final void dispatchRealtimeClockEvent(int var0, RealtimeClockListener var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchUSBPortEvent(int var0, int var1, int var2, USBPortListener var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final RadioPacketListener getListener(RadioListener var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioPacketEvent(int var0, int var1, int var2, int var3, Object var4, Object var5, RadioListener var6) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean dispatchPhoneCallEvent(int var0, int var1, int var2, PhoneCallListener var3) {
      switch (var0) {
         case 1553:
            var3.callIncoming(var1);
            return true;
         case 1554:
            var3.callWaiting(var1);
            return true;
         case 1555:
            var3.callConnected(var1);
            return true;
         case 1556:
            var3.callFailed(var1, var2);
            return true;
         case 1557:
            var3.callDisconnected(var1);
            return true;
         case 1558:
            var3.callHeld(var1);
            return true;
         case 1559:
            var3.callResumed(var1);
            return true;
         case 1560:
            var3.callAdded(var1);
            return true;
         case 1561:
            var3.callRemoved(var1);
            return true;
         case 1562:
            var3.callManipulateFailed(var1, var2);
            return true;
         case 1563:
            var3.callDelivered(var1);
            return true;
         case 1564:
            var3.callInitiated(var1);
            return true;
         case 1574:
            var3.callDisplayUpdated(var1);
            return true;
         case 1600:
            var3.callOTAStatusUpdated(var1, var2);
            return true;
         case 1605:
            var3.callVoicePrivacyUpdated(var1, var2 == 0);
            return true;
         case 1668:
            var3.callTransferred(var1, var2);
            return true;
         case 5001:
            var3.callTransferStateUpdated(var1, var2);
            return true;
         case 5003:
            var3.dtmfData(var1);
            return true;
         default:
            return false;
      }
   }

   public static final boolean dispatchPhoneTimerEvent(int var0, int var1, int var2, PhoneTimerListener var3) {
      switch (var0) {
         case 1568:
            var3.callTimerUpdated(var1, var2);
            return true;
         default:
            return false;
      }
   }

   public static final boolean dispatchPhoneControlEvent(int var0, int var1, int var2, int var3, Object var4, PhoneControlListener var5) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioEngineeringEvent(int var0, int var1, int var2, Object var3, EngineeringDataListener var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioModemEvent(int var0, int var1, ModemListener var2) {
      switch (var0) {
         case 1593:
         case 1594:
            var2.networkChangeResult(var0, var1);
         default:
            return;
         case 1680:
            var2.networkSelectionModeChanged(var1);
            return;
         case 1681:
            var2.queryNetworkDisplayName(RadioInfo.convertNetworkId(var1));
      }
   }

   public static final void dispatchRadioDirectConnectEvent(int var0, int var1, int var2, int var3, DirectConnectListener var4) {
      switch (var0) {
         case 2048:
         default:
            var4.dcCallConnected(var1, var2, var3 == 1);
            return;
         case 2049:
            var4.dcCallDisconnected(var1, var2, var3);
            return;
         case 2050:
            var4.dcRequestFailed(var1, var2, var3);
            return;
         case 2051:
            var4.dcCallStatusUpdated(var1, var2);
            return;
         case 2052:
            var4.dcTalkStatusUpdated(var1, var2, var3);
            return;
         case 2053:
            var4.dcTalkGroupIdUpdated(var1, var2 != 1, var3);
            return;
         case 2054:
            var4.dcCallAlertUpdate(var1, var2, var3);
            return;
         case 2055:
            var4.dcServiceUpdated(var1, var2 == 0, var3 != 0);
         case 2047:
      }
   }

   public static final void dispatchWLANEvent(int var0, int var1, int var2, int var3, WLANListenerInternal var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchVPNEvent(int var0, int var1, int var2, int var3, VPNListener var4) {
      switch (var0) {
         case 8960:
         case 8961:
         case 8962:
         case 8966:
            var4.vpnStatusChanged(var0, var1, var2, var3);
      }
   }

   public static final void dispatchGANEvent(int var0, int var1, int var2, int var3, GANStatusListener var4) {
      switch (var0) {
         case 1596:
            var4.ganEventOccurred(var1, var2, var3);
      }
   }

   public static final void dispatchHolsterEvent(int var0, HolsterListener var1) {
      switch (var0) {
         case 1792:
            var1.outOfHolster();
         case 1791:
            return;
         case 1793:
         default:
            var1.inHolster();
      }
   }

   public static final void dispatchAlertEvent(int var0, int var1, AlertListener var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchNativeSocketEvent(Message var0, NativeSocketListener var1) {
   }
}
