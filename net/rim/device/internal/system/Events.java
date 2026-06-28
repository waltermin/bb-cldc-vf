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
   public static final void dispatchSystemEvent(int event, int subMessage, int dataLength, Object object0, SystemListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean dispatchKeyEvent(int event, char key, int keycode, int time, KeyListener listener) {
      switch (event) {
         case 513:
            return listener.keyDown(keycode, time);
         case 514:
            return listener.keyRepeat(keycode, time);
         case 515:
            return listener.keyUp(keycode, time);
         case 520:
            return listener.keyStatus(keycode, time);
         case 32768:
            return listener.keyChar(key, keycode & 65535, time);
         default:
            return false;
      }
   }

   public static final boolean dispatchStylusEvent(int event, int x, int y, int status, int time, StylusListener listener) {
      switch (event) {
         case 6655:
            return false;
         case 6656:
         default:
            return listener.stylusDown(x, y, status, time);
         case 6657:
            return listener.stylusDrag(x, y, status, time);
         case 6658:
            return listener.stylusUp(x, y, status, time);
         case 6659:
            return listener.stylusTap(x, y, status, time);
         case 6660:
            return listener.stylusTapHold(x, y, status, time);
         case 6661:
            return listener.stylusDoubleTap(x, y, status, time);
      }
   }

   public static final boolean dispatchTrackwheelEvent(int event, int magnitude, int status, int time, TrackwheelListener listener) {
      switch (event) {
         case 515:
         case 518:
            return false;
         case 516:
         default:
            return listener.trackwheelClick(status, time);
         case 517:
            return listener.trackwheelUnclick(status, time);
         case 519:
            return listener.trackwheelRoll(magnitude, status, time);
      }
   }

   public static final void dispatchRealtimeClockEvent(int event, RealtimeClockListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchUSBPortEvent(int event, int subMessage, int dataLength, USBPortListener usbListener) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final RadioPacketListener getListener(RadioListener listener, int data1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioPacketEvent(int event, int subMessage, int data0, int data1, Object object0, Object object1, RadioListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean dispatchPhoneCallEvent(int event, int subMessage, int data0, PhoneCallListener listener) {
      switch (event) {
         case 1553:
            listener.callIncoming(subMessage);
            return true;
         case 1554:
            listener.callWaiting(subMessage);
            return true;
         case 1555:
            listener.callConnected(subMessage);
            return true;
         case 1556:
            listener.callFailed(subMessage, data0);
            return true;
         case 1557:
            listener.callDisconnected(subMessage);
            return true;
         case 1558:
            listener.callHeld(subMessage);
            return true;
         case 1559:
            listener.callResumed(subMessage);
            return true;
         case 1560:
            listener.callAdded(subMessage);
            return true;
         case 1561:
            listener.callRemoved(subMessage);
            return true;
         case 1562:
            listener.callManipulateFailed(subMessage, data0);
            return true;
         case 1563:
            listener.callDelivered(subMessage);
            return true;
         case 1564:
            listener.callInitiated(subMessage);
            return true;
         case 1574:
            listener.callDisplayUpdated(subMessage);
            return true;
         case 1600:
            listener.callOTAStatusUpdated(subMessage, data0);
            return true;
         case 1605:
            listener.callVoicePrivacyUpdated(subMessage, data0 == 0);
            return true;
         case 1668:
            listener.callTransferred(subMessage, data0);
            return true;
         case 5001:
            listener.callTransferStateUpdated(subMessage, data0);
            return true;
         case 5003:
            listener.dtmfData(subMessage);
            return true;
         default:
            return false;
      }
   }

   public static final boolean dispatchPhoneTimerEvent(int event, int subMessage, int data0, PhoneTimerListener listener) {
      switch (event) {
         case 1568:
            listener.callTimerUpdated(subMessage, data0);
            return true;
         default:
            return false;
      }
   }

   public static final boolean dispatchPhoneControlEvent(int event, int subMessage, int data0, int data1, Object object0, PhoneControlListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioEngineeringEvent(int event, int subMessage, int data0, Object object0, EngineeringDataListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchRadioModemEvent(int event, int subMessage, ModemListener listener) {
      switch (event) {
         case 1593:
         case 1594:
            listener.networkChangeResult(event, subMessage);
         default:
            return;
         case 1680:
            listener.networkSelectionModeChanged(subMessage);
            return;
         case 1681:
            listener.queryNetworkDisplayName(RadioInfo.convertNetworkId(subMessage));
      }
   }

   public static final void dispatchRadioDirectConnectEvent(int event, int subMessage, int data0, int data1, DirectConnectListener listener) {
      switch (event) {
         case 2048:
         default:
            listener.dcCallConnected(subMessage, data0, data1 == 1);
            return;
         case 2049:
            listener.dcCallDisconnected(subMessage, data0, data1);
            return;
         case 2050:
            listener.dcRequestFailed(subMessage, data0, data1);
            return;
         case 2051:
            listener.dcCallStatusUpdated(subMessage, data0);
            return;
         case 2052:
            listener.dcTalkStatusUpdated(subMessage, data0, data1);
            return;
         case 2053:
            listener.dcTalkGroupIdUpdated(subMessage, data0 != 1, data1);
            return;
         case 2054:
            listener.dcCallAlertUpdate(subMessage, data0, data1);
            return;
         case 2055:
            listener.dcServiceUpdated(subMessage, data0 == 0, data1 != 0);
         case 2047:
      }
   }

   public static final void dispatchWLANEvent(int event, int subMessage, int data0, int data1, WLANListenerInternal listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchVPNEvent(int event, int subMessage, int data0, int data1, VPNListener listener) {
      switch (event) {
         case 8960:
         case 8961:
         case 8962:
         case 8966:
            listener.vpnStatusChanged(event, subMessage, data0, data1);
      }
   }

   public static final void dispatchGANEvent(int event, int subMessage, int data0, int data1, GANStatusListener listener) {
      switch (event) {
         case 1596:
            listener.ganEventOccurred(subMessage, data0, data1);
      }
   }

   public static final void dispatchHolsterEvent(int event, HolsterListener listener) {
      switch (event) {
         case 1792:
            listener.outOfHolster();
         case 1791:
            return;
         case 1793:
         default:
            listener.inHolster();
      }
   }

   public static final void dispatchAlertEvent(int event, int subMessage, AlertListener listener) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void dispatchNativeSocketEvent(Message message, NativeSocketListener listener) {
   }
}
