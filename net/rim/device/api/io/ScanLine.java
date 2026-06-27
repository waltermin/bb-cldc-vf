package net.rim.device.api.io;

public final class ScanLine extends LineReader {
   private byte[] _boundary = new byte[74];

   public ScanLine(SharedInputStream var1) {
      super(var1);
   }

   public final int searchForBoundary(byte[] var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static final native int searchForBoundary(byte[] var0, int var1, int var2, int var3, byte[] var4, int var5, int var6, boolean var7, int var8);
}
