package net.rim.device.api.bluetooth;

import java.io.IOException;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationProcess;
import net.rim.device.api.system.IOPort;
import net.rim.device.api.system.UnsupportedOperationException;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.bluetooth.BluetoothDeviceManager;
import net.rim.device.internal.bluetooth.BluetoothME;
import net.rim.device.internal.bluetooth.BluetoothSDP;
import net.rim.device.internal.system.EventDispatchManager;
import net.rim.vm.Message;
import net.rim.vm.Process;

public final class BluetoothSerialPort extends IOPort {
   private int _portHandle = -1;
   private boolean _isServer;
   private BluetoothSerialPortListener _listener;
   private Application _app;
   private Runnable _cleanupRunnable;
   private byte[] _remoteAddress;
   private int _sdpRecordHandle = -1;
   public static final int BAUD_2400;
   public static final int BAUD_4800;
   public static final int BAUD_7200;
   public static final int BAUD_9600;
   public static final int BAUD_19200;
   public static final int BAUD_38400;
   public static final int BAUD_57600;
   public static final int BAUD_115200;
   public static final int BAUD_230400;
   public static final int DATA_FORMAT_DATA_BITS_5;
   public static final int DATA_FORMAT_DATA_BITS_6;
   public static final int DATA_FORMAT_DATA_BITS_7;
   public static final int DATA_FORMAT_DATA_BITS_8;
   public static final int DATA_FORMAT_STOP_BITS_1;
   public static final int DATA_FORMAT_STOP_BITS_1_5;
   public static final int DATA_FORMAT_PARITY_NONE;
   public static final int DATA_FORMAT_PARITY_ON;
   public static final int DATA_FORMAT_PARITY_ODD;
   public static final int DATA_FORMAT_PARITY_EVEN;
   public static final int DATA_FORMAT_PARITY_MARK;
   public static final int DATA_FORMAT_PARITY_SPACE;
   public static final int FLOW_CONTROL_NONE;
   public static final int FLOW_CONTROL_RTC_CTS;
   public static final int FLOW_CONTROL_DTR_DSR;
   public static final int FLOW_CONTROL_XON_XOFF;
   private static final boolean DEBUG;
   public static final byte[] DEFAULT_UUID;

   private static final void assertPermission() {
      ApplicationControl.assertBluetoothSerialProfileAllowed(true);
   }

   private BluetoothSerialPort() {
      if (ITPolicy.getBoolean(34, 5, false)) {
         throw new IOException("Disabled by IT Policy");
      }

      if (!isSupported()) {
         throw new UnsupportedOperationException();
      }

      this._app = Application.getApplication();
   }

   public BluetoothSerialPort(
      BluetoothSerialPortInfo info, int baudRate, int dataFormat, int flowControl, int rxBufferSize, int txBufferSize, BluetoothSerialPortListener listener
   ) {
      this();
      this.addListener(listener);
      this._remoteAddress = info.getDeviceAddress();

      try {
         this._portHandle = openClientPort(
            this._remoteAddress, info.getDevicePageScanInfo(), info.getServerID(), baudRate, dataFormat, flowControl, rxBufferSize, txBufferSize
         );
      } catch (IOException e) {
         this._app.removeListener(42, this);
         throw e;
      }

      this._cleanupRunnable = new BluetoothSerialPort$BluetoothSerialPortCleanupRunnable(this);
      ((ApplicationProcess)Process.currentProcess()).addCleanupRunnable(this._cleanupRunnable);
   }

   public BluetoothSerialPort(
      String serviceName, int baudRate, int dataFormat, int flowControl, int rxBufferSize, int txBufferSize, BluetoothSerialPortListener listener
   ) {
      this(null, serviceName, baudRate, dataFormat, flowControl, rxBufferSize, txBufferSize, listener);
   }

   public BluetoothSerialPort(
      byte[] uuid, String serviceName, int baudRate, int dataFormat, int flowControl, int rxBufferSize, int txBufferSize, BluetoothSerialPortListener listener
   ) {
      this(baudRate, dataFormat, flowControl, rxBufferSize, txBufferSize, listener);
      this.addDefaultSDPRecord(uuid, serviceName);
   }

   public BluetoothSerialPort(int baudRate, int dataFormat, int flowControl, int rxBufferSize, int txBufferSize, BluetoothSerialPortListener listener) {
      this();
      this._isServer = true;
      this.addListener(listener);

      try {
         this._portHandle = openServerPort(baudRate, dataFormat, flowControl, rxBufferSize, txBufferSize);
      } catch (IOException e) {
         this._app.removeListener(42, this);
         throw e;
      }

      this._cleanupRunnable = new BluetoothSerialPort$BluetoothSerialPortCleanupRunnable(this);
      ((ApplicationProcess)Process.currentProcess()).addCleanupRunnable(this._cleanupRunnable);
   }

   public final int getSDPRecordHandle() {
      return this._sdpRecordHandle;
   }

   public final void addSDPRecord(int[] attributeIDs, byte[][][] attributeValues, int classOfDevice) {
      if (this._sdpRecordHandle == -1) {
         int securityLevel = 11;
         this._sdpRecordHandle = BluetoothSDP.addRecord(
            this._portHandle, false, attributeIDs, attributeValues, classOfDevice, 0, this.getRFCOMMChannel(), securityLevel
         );
         if (this._sdpRecordHandle == -1) {
            throw new IOException("Unable to register SDP record");
         }
      }
   }

   public final byte[] getRemoteAddress() {
      return this._remoteAddress;
   }

   public final int getRFCOMMChannel() {
      return getRFCOMMChannel(this._portHandle);
   }

   private final void addDefaultSDPRecord(byte[] uuid, String serviceName) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static final boolean isSupported() {
      return BluetoothME.isSupported();
   }

   public final void setProperties(int baudRate, int dataFormat, int flowControl) {
   }

   public final void setDsr(boolean state) {
      assertPermission();
      setDsr(this._portHandle, state);
   }

   public final boolean getDtr() {
      assertPermission();
      return getDtr(this._portHandle);
   }

   public final void disconnect() {
      if (this._isServer) {
         assertPermission();
         if (this._portHandle != -1) {
            disconnect(this._portHandle);
            return;
         }
      } else {
         this.close();
      }
   }

   @Override
   public final void close() {
      assertPermission();
      this.closePort();
      this._app.removeListener(42, this);
      ((ApplicationProcess)Process.currentProcess()).removeCleanupRunnable(this._cleanupRunnable);
   }

   private final void closePort() {
      if (this._portHandle != -1) {
         close(this._portHandle);
         this._portHandle = -1;
      }

      if (this._sdpRecordHandle != -1) {
         BluetoothSDP.removeRecord(this._sdpRecordHandle);
         this._sdpRecordHandle = -1;
      }

      this._remoteAddress = null;
   }

   @Override
   public final int write(byte[] data) {
      return this.write(data, 0, data.length);
   }

   @Override
   public final int write(byte[] data, int offset, int length) {
      assertPermission();
      return write(this._portHandle, data, offset, length);
   }

   @Override
   public final int write(int b) {
      assertPermission();
      return write(this._portHandle, b);
   }

   @Override
   public final int read(byte[] data) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public final int read(byte[] data, int offset, int length) {
      assertPermission();
      return read(this._portHandle, data, offset, length);
   }

   @Override
   public final int read() {
      assertPermission();
      return read(this._portHandle);
   }

   public static final BluetoothSerialPortInfo[] getSerialPortInfo() {
      assertPermission();
      BluetoothDeviceManager btManager = BluetoothDeviceManager.getInstance();
      if (btManager == null) {
         throw new UnsupportedOperationException();
      } else {
         return btManager.getSerialPortInfo();
      }
   }

   private static final native int openClientPort(byte[] var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7);

   private static final native int openServerPort(int var0, int var1, int var2, int var3, int var4);

   private static final native void disconnect(int var0);

   private static final native void close(int var0);

   private static final native int write(int var0, byte[] var1, int var2, int var3);

   private static final native int write(int var0, int var1);

   private static final native int read(int var0, byte[] var1, int var2, int var3);

   private static final native int read(int var0);

   private static final native void setDsr(int var0, boolean var1);

   private static final native boolean getDtr(int var0);

   private static final native int getRFCOMMChannel(int var0);

   private final void addListener(BluetoothSerialPortListener listener) {
      assertPermission();
      EventDispatchManager dispatchManager = EventDispatchManager.getInstance();
      synchronized (dispatchManager) {
         if (dispatchManager.getDispatcher(42) == null) {
            dispatchManager.setDispatcher(42, new BluetoothSerialEventDispatcher());
         }
      }

      this._listener = listener;
      this._app.addListener(42, this);
   }

   final void dispatch(Message message) {
      throw new RuntimeException("cod2jar: type check");
   }
}
