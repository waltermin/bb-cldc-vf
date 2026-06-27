package net.rim.device.api.crypto;

public class AbstractDigest {
   byte[] buffer;

   protected AbstractDigest() {
   }

   public String getAlgorithm() {
      throw null;
   }

   public void reset() {
      throw null;
   }

   public void update(int var1) {
      if (this.buffer == null) {
         this.buffer = new byte[1];
      }

      this.buffer[0] = (byte)var1;
      this.update(this.buffer, 0, 1);
   }

   public void update(byte[] var1) {
      if (var1 == null) {
         throw new Object();
      }

      this.update(var1, 0, var1.length);
   }

   public void update(byte[] var1, int var2, int var3) {
      throw null;
   }

   public int getDigestLength() {
      throw null;
   }

   public int getBlockLength() {
      return 0;
   }

   public byte[] getDigest() {
      return this.getDigest(true);
   }

   public byte[] getDigest(boolean var1) {
      byte[] var2 = new byte[this.getDigestLength()];
      this.getDigest(var2, 0, var1);
      return var2;
   }

   public int getDigest(byte[] var1, int var2) {
      return this.getDigest(var1, var2, true);
   }

   public int getDigest(byte[] var1, int var2, boolean var3) {
      throw null;
   }
}
