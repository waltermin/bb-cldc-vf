package net.rim.device.api.bluetooth;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.IOPort;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.bluetooth.BluetoothDeviceManager;
import net.rim.device.internal.bluetooth.BluetoothME;
import net.rim.device.internal.bluetooth.BluetoothSDP;
import net.rim.vm.Message;

public final class BluetoothSerialPort extends IOPort {
   private int _portHandle;
   private boolean _isServer;
   private BluetoothSerialPortListener _listener;
   private Application _app;
   private Runnable _cleanupRunnable;
   private byte[] _remoteAddress;
   private int _sdpRecordHandle;
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
   }

   public BluetoothSerialPort(BluetoothSerialPortInfo var1, int var2, int var3, int var4, int var5, int var6, BluetoothSerialPortListener var7) {
   }

   public BluetoothSerialPort(String var1, int var2, int var3, int var4, int var5, int var6, BluetoothSerialPortListener var7) {
      this(null, var1, var2, var3, var4, var5, var6, var7);
   }

   public BluetoothSerialPort(byte[] var1, String var2, int var3, int var4, int var5, int var6, int var7, BluetoothSerialPortListener var8) {
      this(var3, var4, var5, var6, var7, var8);
      this.addDefaultSDPRecord(var1, var2);
   }

   public BluetoothSerialPort(int var1, int var2, int var3, int var4, int var5, BluetoothSerialPortListener var6) {
   }

   public final int getSDPRecordHandle() {
      return this._sdpRecordHandle;
   }

   public final void addSDPRecord(int[] var1, byte[][][] var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final byte[] getRemoteAddress() {
      return this._remoteAddress;
   }

   public final int getRFCOMMChannel() {
      return getRFCOMMChannel(this._portHandle);
   }

   private final void addDefaultSDPRecord(byte[] var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isSupported() {
      return BluetoothME.isSupported();
   }

   public final void setProperties(int var1, int var2, int var3) {
   }

   public final void setDsr(boolean var1) {
      assertPermission();
      setDsr(this._portHandle, var1);
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
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
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
   public final int write(byte[] var1) {
      return this.write(var1, 0, var1.length);
   }

   @Override
   public final int write(byte[] var1, int var2, int var3) {
      assertPermission();
      return write(this._portHandle, var1, var2, var3);
   }

   @Override
   public final int write(int var1) {
      assertPermission();
      return write(this._portHandle, var1);
   }

   @Override
   public final int read(byte[] var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      assertPermission();
      return read(this._portHandle, var1, var2, var3);
   }

   @Override
   public final int read() {
      assertPermission();
      return read(this._portHandle);
   }

   public static final BluetoothSerialPortInfo[] getSerialPortInfo() {
      assertPermission();
      BluetoothDeviceManager var0 = BluetoothDeviceManager.getInstance();
      if (var0 == null) {
         throw new Object();
      } else {
         return var0.getSerialPortInfo();
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

   private final void addListener(BluetoothSerialPortListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void dispatch(Message var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
