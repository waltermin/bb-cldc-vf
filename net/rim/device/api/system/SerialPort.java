package net.rim.device.api.system;

public final class SerialPort extends IOPort {
   public static final int PARITY_NONE;
   public static final int PARITY_EVEN;
   public static final int PARITY_ODD;
   public static final int DEFAULT_PORT;

   private SerialPort() {
   }

   public static final boolean isSupported() {
      return false;
   }

   public SerialPort(int var1, int var2, int var3, int var4, int var5, int var6) {
      throw new UnsupportedOperationException();
   }

   public final void setProperties(int var1, int var2, int var3, int var4) {
   }

   public final void setDsr(boolean var1) {
   }

   public final void standbyMode(boolean var1) {
   }

   public final boolean getDtr() {
      throw new UnsupportedOperationException();
   }

   public final int getTxCount() {
      throw new UnsupportedOperationException();
   }

   @Override
   public final void close() {
   }

   @Override
   public final int write(byte[] var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public final int write(byte[] var1, int var2, int var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public final int write(int var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public final int read(byte[] var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public final int read(byte[] var1, int var2, int var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public final int read() {
      throw new UnsupportedOperationException();
   }

   public static final void registerNotifyPattern(byte[] var0) {
      throw new UnsupportedOperationException();
   }

   public static final void enableDtrFix() {
   }
}
