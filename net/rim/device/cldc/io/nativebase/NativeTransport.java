package net.rim.device.cldc.io.nativebase;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.ConnEvent;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.DatagramStatusListener;
import net.rim.device.api.io.DatagramTransportBase;
import net.rim.device.api.io.IOProperties;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.EventLogger;
import net.rim.device.api.system.ExtendedRadioStatusListener;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.PhoneListener;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.RadioPacketHeader;
import net.rim.device.api.system.RadioPacketListener;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.system.WLAN;
import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.device.internal.system.DataServices;
import net.rim.device.internal.system.ITPolicyInternal;

public class NativeTransport
   extends DatagramTransportBase
   implements GlobalEventListener,
   RadioPacketListener,
   ExtendedRadioStatusListener,
   SystemListener2,
   PhoneListener,
   Runnable,
   ConnEvent {
   protected int SEND_BACKOFF_DEF = 8000;
   protected DatagramAddressBase _txAddressBase;
   protected RadioPacketHeader _txHeader;
   protected int _txApnId;
   protected byte[] _txData;
   protected int _txOffset;
   protected int _txLength;
   protected DatagramStatusListener _txListener;
   protected int _txDgramId;
   protected int _networkServiceMask;
   protected int _maxPacketSize;
   private int _nextDgramId;
   private int[] _sendStatusIds;
   private int[] _sendStatusCodes;
   private int[] _sendStatusReturn;
   private boolean _sendStatusWaiting;
   private Object _sendChokeLock = new Object();
   private int _sendWait;
   private boolean _networkChoked;
   private int _timerId;
   private int _signalThreshold;
   private int _timeBackoff;
   private int _apnId;
   private Datagram _txDatagram;
   protected boolean _generateTransmittingStatusEvent;
   private NativeListener _nativeListener;
   private DataServices _dataServices;
   protected boolean _dataServicesEnabled;
   protected int _dataServicesMode;
   protected boolean _itPolicyEnabled;
   public static final int STATUS_PENDING;
   public static final int STATUS_SENDING;
   public static final int STATUS_SENT;
   public static final int STATUS_CANCELLED;
   public static final int STATUS_NON_FATAL;
   public static final int STATUS_COVERAGE;
   public static final int SERVICE_VOICE;
   public static final int SERVICE_DATA;
   private static final int NETWORK_STARTED_TMO;
   private static final int SEND_BACKOFF_MAX;
   private static final int SEND_BACKOFF_LEVEL;
   private static final int TX_FLOW_CONTROL_RETRIES;
   private static final int MAX_SEND_STATUS;

   public void nativeInit() {
      throw null;
   }

   public void nativeSendVerify(DatagramAddressBase var1, Datagram var2) {
      throw null;
   }

   public void nativeSendSetupHeader(Datagram var1, IOProperties var2) {
      throw null;
   }

   public void nativeSendSetupData(Datagram var1) {
      throw null;
   }

   public int nativeGetStatus(int var1) {
      throw null;
   }

   public void nativePreSend() {
   }

   public void nativePostSend() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void queueDgslEvent(DatagramStatusListener var1, int var2, int var3, Object var4) {
      this.xmitDgslEvent(var1, var2, var3, var4);
   }

   protected void queueDgslEvent(int var1, int var2, Object var3) {
      this.passDgslEvent(var1, var2, var3);
   }

   protected void setBackoffTimer() {
      this._sendWait = this._timeBackoff;
      this._timeBackoff *= 2;
      if (this._timeBackoff > 900000) {
         this._timeBackoff = 900000;
      }
   }

   protected void queueSendStatus(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void networkStarted(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void baseStationChange() {
   }

   @Override
   public void radioTurnedOff() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void pdpStateChange(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void networkStateChange(int var1) {
   }

   @Override
   public void networkScanComplete(boolean var1) {
   }

   @Override
   public void networkServiceChange(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void networkSelectionFailed(int var1, int var2) {
   }

   @Override
   public void flowControlStatusChange(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void networkScanStatus(int var1) {
   }

   @Override
   public void networkNameChangeViaNITZ(int var1, int var2) {
   }

   @Override
   public void powerOff() {
   }

   @Override
   public void powerUp() {
   }

   @Override
   public void batteryLow() {
   }

   @Override
   public void batteryGood() {
   }

   @Override
   public void batteryStatusChange(int var1) {
   }

   @Override
   public void powerOffRequested(int var1) {
   }

   @Override
   public void cradleMismatch(boolean var1) {
   }

   @Override
   public void fastReset() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void backlightStateChange(boolean var1) {
   }

   @Override
   public void usbConnectionStateChange(int var1) {
   }

   @Override
   public void callIncoming(int var1) {
   }

   @Override
   public void callDisplayUpdated(int var1) {
   }

   @Override
   public void callWaiting(int var1) {
   }

   @Override
   public void callInitiated(int var1) {
   }

   @Override
   public void callConnected(int var1) {
   }

   @Override
   public void callFailed(int var1, int var2) {
      this.callDisconnected(var1);
   }

   @Override
   public void callDelivered(int var1) {
   }

   @Override
   public void callManipulateFailed(int var1, int var2) {
   }

   @Override
   public void callDisconnected(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void callHeld(int var1) {
   }

   @Override
   public void callResumed(int var1) {
   }

   @Override
   public void callAdded(int var1) {
   }

   @Override
   public void callRemoved(int var1) {
   }

   @Override
   public void callTransferred(int var1, int var2) {
   }

   @Override
   public void callTransferStateUpdated(int var1, int var2) {
   }

   @Override
   public void callTimerUpdated(int var1, int var2) {
   }

   @Override
   public void callVoicePrivacyUpdated(int var1, boolean var2) {
   }

   @Override
   public void callOTAStatusUpdated(int var1, int var2) {
   }

   @Override
   public void ssRequestSucceeded(int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
   }

   @Override
   public void ssRequestFailed(int var1, int var2, boolean var3) {
   }

   @Override
   public void ssRequestRejected(boolean var1) {
   }

   @Override
   public void ssRequestReleased(boolean var1) {
   }

   @Override
   public void ssRequestInvalidPassword() {
   }

   @Override
   public void ssPasswordRequested(int var1) {
   }

   @Override
   public void ssUpdated(int var1, int var2) {
   }

   @Override
   public void ssNotification(int var1) {
   }

   @Override
   public void ssUssDisplay(byte[] var1, int var2, boolean var3) {
   }

   @Override
   public void featureReady() {
   }

   @Override
   public void responseEnableFDN(int var1) {
   }

   @Override
   public void voiceLineChanged(int var1) {
   }

   @Override
   public void alternateLinesUpdated() {
   }

   @Override
   public void voicemailCountUpdated(int var1, int var2) {
   }

   @Override
   public void dtmfData(int var1) {
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void signalLevel(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void packetStatus(int var1, int var2) {
      this.queueSendStatus(var1, var2);
   }

   @Override
   public void packetNotSent(int var1, int var2) {
      this.queueSendStatus(var1, var2);
   }

   @Override
   public void packetSent(int var1, int var2) {
      if (this._generateTransmittingStatusEvent) {
         this.queueSendStatus(var1, 2);
      }

      this.queueSendStatus(var1, 0);
   }

   @Override
   protected void processReceivedDatagram(Datagram var1) {
   }

   @Override
   protected boolean passUpDatagram(Datagram var1) {
      if (super._tLogger != null) {
         super._tLogger.bytesReceived(this, 1, var1.getAddress(), var1.getLength(), var1.getData());
      }

      return super.passUpDatagram(var1);
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected byte[] setup(int var1, Object var2) {
      switch (var1) {
         case 334258761:
         default:
            this._nativeListener = (NativeListener)var2;
         case 334258760:
            return null;
      }
   }

   @Override
   public void cancel(Datagram var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void send(Datagram var1, DatagramAddressBase var2, IOProperties var3, DatagramStatusListener var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void send(Datagram var1) {
      this.send(var1, null, null, null, 0);
   }

   private void clearSendStatus() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void possiblyClearSendStatus() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected synchronized int getNextDatagramId(DatagramBase var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private int[] dequeueSendStatus() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void processNetwork(int var1) {
      if ((this._networkServiceMask & var1) != 0) {
         if (this._networkChoked) {
            this.startNetworkTimer();
         }

         if (this._sendWait != 0) {
            this._sendChokeLock.notify();
            return;
         }
      } else {
         this.lostNetwork();
      }
   }

   private void lostNetwork() {
      this.killNetworkTimer();
      if (this._sendWait != 0) {
         this._sendChokeLock.notify();
      }
   }

   private void startNetworkTimer() {
      this.killNetworkTimer();
      this._timerId = Application.getApplication().invokeLater(this, 5000, false);
   }

   private void killNetworkTimer() {
      if (this._timerId != -1) {
         Application.getApplication().cancelInvokeLater(this._timerId);
         this._timerId = -1;
      }
   }

   @Override
   public int getMaximumLength() {
      return this._maxPacketSize;
   }

   @Override
   public void init() {
      super.init(null);
      EventLogger.register(super.GUID, super.STR, 2);
      this._itPolicyEnabled = ITPolicyInternal.isITPolicyEnabled();
      this.nativeInit();
      this._sendStatusIds = new int[0];
      this._sendStatusCodes = new int[0];
      this._sendStatusReturn = new int[2];
      this._timerId = -1;
      if (RadioInfo.getState() != 1 && !WLAN.isRadioOn()) {
         this._networkChoked = true;
      } else if (ApplicationManager.getApplicationManager().inStartup()) {
         this._networkChoked = true;
         this.startNetworkTimer();
      } else {
         this._networkChoked = false;
      }

      this._dataServices = DataServices.getInstance();
      this._dataServicesEnabled = this._dataServices.isDataServicesEnabled();
      this._dataServicesMode = this._dataServices.getMode();
      ProtocolDaemon var1 = ProtocolDaemon.getInstance();
      var1.addGlobalEventListener(this);
      var1.addRadioListener(-1, this);
      var1.addSystemListener(this);
      EventLogger.logEvent(super.GUID, 1229878386, 0);
   }

   private void logEvent(int var1, int var2, int var3) {
      byte[] var4 = new byte[15];
      DatagramAddressBase.writeInt(var4, 0, var1);
      var4[4] = 45;
      var4[5] = 48;
      var4[6] = 120;
      DatagramAddressBase.appendHex(var4, 7, var2, 8);
      EventLogger.logEvent(super.GUID, var4, var3);
   }
}
