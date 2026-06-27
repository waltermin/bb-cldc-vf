package net.rim.device.api.system;

public class IOPort {
   protected IOPort() {
   }

   public void close() {
      throw null;
   }

   public int write(byte[] var1) {
      throw null;
   }

   public int write(byte[] var1, int var2, int var3) {
      throw null;
   }

   public int write(int var1) {
      throw null;
   }

   public int read(byte[] var1) {
      throw null;
   }

   public int read(byte[] var1, int var2, int var3) {
      throw null;
   }

   public int read() {
      throw null;
   }

   public static void registerNotifyPattern(byte[] var0) {
      SerialPort.registerNotifyPattern(var0);
   }

   public void cleanup() {
   }
}
