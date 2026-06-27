package net.rim.device.internal.system;

import net.rim.device.api.system.IOPort;

public final class USBPortInternal extends IOPort {
   private int _channel;
   private Runnable _cleanupRunnable;
   private static final String PORT_NOT_OPEN;

   private USBPortInternal() {
   }

   public static final boolean isSupported() {
      return InternalServices.isDeviceCapable(1);
   }

   public USBPortInternal(int var1) {
   }

   @Override
   public final void close() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public final int write(byte[] var1) {
      return this.write(var1, 0, var1.length);
   }

   @Override
   public final synchronized int write(byte[] var1, int var2, int var3) {
      return write(this._channel, var1, var2, var3);
   }

   @Override
   public final synchronized int write(int var1) {
      return write(this._channel, var1);
   }

   @Override
   public final int read(byte[] var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      return read(this._channel, var1, var2, var3);
   }

   @Override
   public final int read() {
      return read(this._channel);
   }

   public static final int registerChannel(String var0, int var1, int var2) {
      return registerChannelImpl(var0, var1, var2);
   }

   private static final native int registerChannelImpl(String var0, int var1, int var2);

   public static final void deregisterChannel(int var0) {
      deregisterChannelImpl(var0);
   }

   private static final native void deregisterChannelImpl(int var0);

   public static final native int getConnectionState();

   public static final int getMaximumRxSize() {
      return getMaximumTransferSize(true);
   }

   public static final int getMaximumTxSize() {
      return getMaximumTransferSize(false);
   }

   private static final native int getMaximumTransferSize(boolean var0);

   private static final native void openChannel(int var0);

   private static final native void closeChannel(int var0);

   private static final native int write(int var0, byte[] var1, int var2, int var3);

   private static final native int write(int var0, int var1);

   private static final native int read(int var0, byte[] var1, int var2, int var3);

   private static final native int read(int var0);
}
