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
import net.rim.device.api.util.Arrays;
import net.rim.device.cldc.io.daemon.ProtocolDaemon;
import net.rim.device.internal.system.DataServices;
import net.rim.device.internal.system.ITPolicyInternal;
import net.rim.vm.Array;

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

   public void nativeSendVerify(DatagramAddressBase _1, Datagram _2) {
      throw null;
   }

   public void nativeSendSetupHeader(Datagram _1, IOProperties _2) {
      throw null;
   }

   public void nativeSendSetupData(Datagram _1) {
      throw null;
   }

   public int nativeGetStatus(int _1) {
      throw null;
   }

   public void nativePreSend() {
   }

   public void nativePostSend() {
      synchronized (this._sendChokeLock) {
         this._txAddressBase = null;
         this._txDatagram = null;
         this._txData = null;
      }
   }

   protected void queueDgslEvent(DatagramStatusListener listener, int dgramId, int status, Object context) {
      this.xmitDgslEvent(listener, dgramId, status, context);
   }

   protected void queueDgslEvent(int subId, int status, Object context) {
      this.passDgslEvent(subId, status, context);
   }

   protected void setBackoffTimer() {
      this._sendWait = this._timeBackoff;
      this._timeBackoff *= 2;
      if (this._timeBackoff > 900000) {
         this._timeBackoff = 900000;
      }
   }

   protected void queueSendStatus(int dgramId, int status) {
      synchronized (this._sendStatusIds) {
         Arrays.add(this._sendStatusIds, dgramId);
         Arrays.add(this._sendStatusCodes, status);
         this._sendStatusIds.notify();
      }
   }

   @Override
   public void networkStarted(int networkId, int service) {
      synchronized (this._sendChokeLock) {
         if ((this._networkServiceMask & service) != 0) {
            if (this._networkChoked) {
               this.startNetworkTimer();
            }

            if (this._sendWait != 0) {
               this._sendChokeLock.notify();
            }
         }
      }
   }

   @Override
   public void baseStationChange() {
   }

   @Override
   public void radioTurnedOff() {
      synchronized (this._sendChokeLock) {
         this.lostNetwork();
      }

      this.possiblyClearSendStatus();
      this.kickSend();
   }

   @Override
   public void pdpStateChange(int apn, int state, int cause) {
      synchronized (this._sendChokeLock) {
         if (this._sendWait != 0 && apn == this._apnId && state == 0) {
            EventLogger.logEvent(super.GUID, 1430479726, 4);
            this._sendChokeLock.notify();
         }
      }
   }

   @Override
   public void networkStateChange(int state) {
   }

   @Override
   public void networkScanComplete(boolean success) {
   }

   @Override
   public void networkServiceChange(int networkId, int service) {
      synchronized (this._sendChokeLock) {
         this.processNetwork(service);
      }
   }

   @Override
   public void networkSelectionFailed(int networkId, int cause) {
   }

   @Override
   public void flowControlStatusChange(int flowControlStatus) {
      synchronized (this._sendChokeLock) {
         if (this._sendWait != 0 && flowControlStatus == 2) {
            EventLogger.logEvent(super.GUID, 1430480483, 4);
            this._sendChokeLock.notify();
         }
      }
   }

   @Override
   public void networkScanStatus(int status) {
   }

   @Override
   public void networkNameChangeViaNITZ(int longNameLength, int shortNameLength) {
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
   public void batteryStatusChange(int status) {
   }

   @Override
   public void powerOffRequested(int reason) {
   }

   @Override
   public void cradleMismatch(boolean mismatch) {
   }

   @Override
   public void fastReset() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   public void backlightStateChange(boolean on) {
   }

   @Override
   public void usbConnectionStateChange(int state) {
   }

   @Override
   public void callIncoming(int callId) {
   }

   @Override
   public void callDisplayUpdated(int callId) {
   }

   @Override
   public void callWaiting(int callId) {
   }

   @Override
   public void callInitiated(int callId) {
   }

   @Override
   public void callConnected(int callId) {
   }

   @Override
   public void callFailed(int callId, int error) {
      this.callDisconnected(callId);
   }

   @Override
   public void callDelivered(int callId) {
   }

   @Override
   public void callManipulateFailed(int callId, int error) {
   }

   @Override
   public void callDisconnected(int callId) {
      synchronized (this._sendChokeLock) {
         if (this._sendWait != 0) {
            EventLogger.logEvent(super.GUID, 1430483048, 4);
            this._timeBackoff = this.SEND_BACKOFF_DEF;
            this._sendChokeLock.notify();
         }
      }
   }

   @Override
   public void callHeld(int callId) {
   }

   @Override
   public void callResumed(int callId) {
   }

   @Override
   public void callAdded(int callId) {
   }

   @Override
   public void callRemoved(int callId) {
   }

   @Override
   public void callTransferred(int status, int reason) {
   }

   @Override
   public void callTransferStateUpdated(int callId, int state) {
   }

   @Override
   public void callTimerUpdated(int callId, int time) {
   }

   @Override
   public void callVoicePrivacyUpdated(int callId, boolean on) {
   }

   @Override
   public void callOTAStatusUpdated(int callId, int status) {
   }

   @Override
   public void ssRequestSucceeded(int ss, int action, int result, int bearerService, boolean isUSSDCmd, boolean forwardingNumberAvailable) {
   }

   @Override
   public void ssRequestFailed(int reason, int bearerService, boolean isUSSDCmd) {
   }

   @Override
   public void ssRequestRejected(boolean isUSSDCmd) {
   }

   @Override
   public void ssRequestReleased(boolean isUSSDCmd) {
   }

   @Override
   public void ssRequestInvalidPassword() {
   }

   @Override
   public void ssPasswordRequested(int requestType) {
   }

   @Override
   public void ssUpdated(int ssOption, int state) {
   }

   @Override
   public void ssNotification(int ssOption) {
   }

   @Override
   public void ssUssDisplay(byte[] data, int messageCoding, boolean collectInput) {
   }

   @Override
   public void featureReady() {
   }

   @Override
   public void responseEnableFDN(int status) {
   }

   @Override
   public void voiceLineChanged(int line) {
   }

   @Override
   public void alternateLinesUpdated() {
   }

   @Override
   public void voicemailCountUpdated(int line, int count) {
   }

   @Override
   public void dtmfData(int dtmf) {
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void signalLevel(int level) {
      synchronized (this._sendChokeLock) {
         if (this._sendWait != 0 && level > this._signalThreshold) {
            EventLogger.logEvent(super.GUID, 1430483817, 4);
            this._sendChokeLock.notify();
         }
      }
   }

   @Override
   public void packetStatus(int packetId, int status) {
      this.queueSendStatus(packetId, status);
   }

   @Override
   public void packetNotSent(int packetId, int error) {
      this.queueSendStatus(packetId, error);
   }

   @Override
   public void packetSent(int packetId, int networkId) {
      if (this._generateTransmittingStatusEvent) {
         this.queueSendStatus(packetId, 2);
      }

      this.queueSendStatus(packetId, 0);
   }

   @Override
   protected void processReceivedDatagram(Datagram datagram) {
   }

   @Override
   protected boolean passUpDatagram(Datagram datagram) {
      if (super._tLogger != null) {
         super._tLogger.bytesReceived(this, 1, datagram.getAddress(), datagram.getLength(), datagram.getData());
      }

      return super.passUpDatagram(datagram);
   }

   @Override
   public void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      super.eventOccurred(guid, data0, data1, object0, object1);
      if (guid == -3556743465989743742L) {
         this._dataServicesMode = this._dataServices.getMode();
         boolean dataServicesEnabled = this._dataServices.isDataServicesEnabled();
         if (this._dataServicesEnabled != dataServicesEnabled) {
            this._dataServicesEnabled = dataServicesEnabled;
            if (dataServicesEnabled) {
               synchronized (this._sendChokeLock) {
                  if (this._sendWait != 0) {
                     EventLogger.logEvent(super.GUID, 1430479987, 4);
                     this._sendChokeLock.notify();
                  }

                  return;
               }
            }
         }
      } else if (guid == 8508406279413621091L) {
         this._itPolicyEnabled = ITPolicyInternal.isITPolicyEnabled();
      }
   }

   @Override
   protected byte[] setup(int callType, Object context) {
      switch (callType) {
         case 334258761:
         default:
            this._nativeListener = (NativeListener)context;
         case 334258760:
            return null;
      }
   }

   @Override
   public void cancel(Datagram datagram) {
      synchronized (this._sendChokeLock) {
         if (datagram == this._txDatagram) {
            this._txDatagram = null;
            this._sendChokeLock.notify();
            this.clearSendStatus();
         }
      }
   }

   @Override
   public void send(Datagram datagram, DatagramAddressBase addressBase, IOProperties properties, DatagramStatusListener listener, int dgramId) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   public void send(Datagram datagram) {
      this.send(datagram, null, null, null, 0);
   }

   private void clearSendStatus() {
      synchronized (this._sendStatusIds) {
         Array.resize(this._sendStatusIds, 0);
         Array.resize(this._sendStatusCodes, 0);
         this._sendStatusWaiting = false;
         this._sendStatusIds.notify();
      }
   }

   private void possiblyClearSendStatus() {
      synchronized (this._sendStatusIds) {
         if (this._sendStatusWaiting) {
            Array.resize(this._sendStatusIds, 0);
            Array.resize(this._sendStatusCodes, 0);
            this._sendStatusIds.notify();
         }
      }
   }

   @Override
   protected synchronized int getNextDatagramId(DatagramBase dgram) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private int[] dequeueSendStatus() {
      synchronized (this._sendStatusIds) {
         if (this._sendStatusIds.length == 0) {
            this._sendStatusWaiting = true;

            try {
               this._sendStatusIds.wait();
            } catch (InterruptedException var3) {
            }

            this._sendStatusWaiting = false;
            if (this._sendStatusIds.length == 0) {
               return null;
            }
         }

         this._sendStatusReturn[0] = this._sendStatusIds[0];
         this._sendStatusReturn[1] = this._sendStatusCodes[0];
         Arrays.removeAt(this._sendStatusIds, 0);
         Arrays.removeAt(this._sendStatusCodes, 0);
      }

      return this._sendStatusReturn;
   }

   private void processNetwork(int service) {
      if ((this._networkServiceMask & service) != 0) {
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
      ProtocolDaemon daemon = ProtocolDaemon.getInstance();
      daemon.addGlobalEventListener(this);
      daemon.addRadioListener(-1, this);
      daemon.addSystemListener(this);
      EventLogger.logEvent(super.GUID, 1229878386, 0);
   }

   private void logEvent(int event, int s1, int level) {
      byte[] buf = new byte[15];
      DatagramAddressBase.writeInt(buf, 0, event);
      buf[4] = 45;
      buf[5] = 48;
      buf[6] = 120;
      DatagramAddressBase.appendHex(buf, 7, s1, 8);
      EventLogger.logEvent(super.GUID, buf, level);
   }
}
