package net.rim.device.internal.system;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.IOPort;
import net.rim.device.internal.applicationcontrol.ApplicationControl;

public final class SmartCardPort extends IOPort {
   private boolean _opened;
   public int SC_PROTOCOL_DEFAULT = -1;
   public int SC_PROTOCOL_T0 = 0;
   public int SC_PROTOCOL_T1 = 1;

   private static final void assertPermission() {
      ApplicationControl.assertLocalConnectionAllowed(true);
   }

   public SmartCardPort() {
      this._opened = false;
   }

   public static final boolean isSupported() {
      return InternalServices.isDeviceCapable(5);
   }

   public final void reset() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int write(byte[] var1) {
      return this.write(var1, 0, var1.length);
   }

   @Override
   public final int write(int var1) {
      assertPermission();
      return this.write((byte)var1);
   }

   @Override
   public final int read(byte[] var1) {
      return this.read(var1, 0, var1.length);
   }

   @Override
   public final void close() {
      assertPermission();
      this.closeDevice();
      this._opened = false;
   }

   public final boolean open(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final boolean setProtocol(int var1) {
      assertPermission();
      return var1 != this.SC_PROTOCOL_T1 && var1 != this.SC_PROTOCOL_T0 ? false : this.setProtocolImpl(var1);
   }

   private final native boolean setProtocolImpl(int var1);

   public static final boolean isCardPresent() {
      assertPermission();
      return isCardPresentImpl();
   }

   private static final native boolean isCardPresentImpl();

   @Override
   public final int write(byte[] var1, int var2, int var3) {
      assertPermission();
      return this.writeImpl(var1, var2, var3);
   }

   private final native int writeImpl(byte[] var1, int var2, int var3);

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      assertPermission();
      return this.readImpl(var1, var2, var3);
   }

   private final native int readImpl(byte[] var1, int var2, int var3);

   @Override
   public final int read() {
      assertPermission();
      return this.readImpl();
   }

   private final native int readImpl();

   public final void flushRead() {
      assertPermission();
      this.flushReadImpl();
   }

   private final native void flushReadImpl();

   private final native boolean openDevice(int var1);

   private final native boolean closeDevice();

   private final native boolean resetDevice(boolean var1);

   private final native int write(byte var1);

   public static final void addListener(Application var0, SmartCardPortListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, SmartCardPortListener var1) {
      var0.removeListener(16, var1);
   }
}
