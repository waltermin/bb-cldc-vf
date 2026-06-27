package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.USBPortInternal;

public final class USBPort extends IOPort {
   private USBPortInternal _usbPortInternal;

   private USBPort() {
   }

   private static final void assertPermission() {
      ApplicationControl.assertLocalConnectionAllowed(true);
   }

   public static final boolean isSupported() {
      return USBPortInternal.isSupported();
   }

   public USBPort(int var1) {
      assertPermission();
      this._usbPortInternal = (USBPortInternal)(new Object(var1));
   }

   @Override
   public final void close() {
      assertPermission();
      this._usbPortInternal.close();
   }

   @Override
   public final int write(byte[] var1) {
      assertPermission();
      return this._usbPortInternal.write(var1);
   }

   @Override
   public final synchronized int write(byte[] var1, int var2, int var3) {
      assertPermission();
      return this._usbPortInternal.write(var1, var2, var3);
   }

   @Override
   public final synchronized int write(int var1) {
      assertPermission();
      return this._usbPortInternal.write(var1);
   }

   @Override
   public final int read(byte[] var1) {
      assertPermission();
      return this._usbPortInternal.read(var1);
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      assertPermission();
      return this._usbPortInternal.read(var1, var2, var3);
   }

   @Override
   public final int read() {
      assertPermission();
      return this._usbPortInternal.read();
   }

   public static final int registerChannel(String var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void deregisterChannel(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int getConnectionState() {
      return USBPortInternal.getConnectionState();
   }

   public static final int getMaximumRxSize() {
      assertPermission();
      return USBPortInternal.getMaximumRxSize();
   }

   public static final int getMaximumTxSize() {
      assertPermission();
      return USBPortInternal.getMaximumTxSize();
   }
}
