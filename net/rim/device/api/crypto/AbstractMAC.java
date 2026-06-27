package net.rim.device.api.crypto;

import net.rim.device.api.util.Arrays;

public class AbstractMAC {
   byte[] buffer;

   protected AbstractMAC() {
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

   public int getLength() {
      throw null;
   }

   public byte[] getMAC() {
      return this.getMAC(true);
   }

   public byte[] getMAC(boolean var1) {
      byte[] var2 = new byte[this.getLength()];
      this.getMAC(var2, 0, var1);
      return var2;
   }

   public int getMAC(byte[] var1, int var2) {
      return this.getMAC(var1, var2, true);
   }

   public int getMAC(byte[] var1, int var2, boolean var3) {
      throw null;
   }

   public boolean checkMAC(byte[] var1) {
      return this.checkMAC(var1, 0, true);
   }

   public boolean checkMAC(byte[] var1, boolean var2) {
      return this.checkMAC(var1, 0, var2);
   }

   public boolean checkMAC(byte[] var1, int var2) {
      return this.checkMAC(var1, var2, true);
   }

   public boolean checkMAC(byte[] var1, int var2, boolean var3) {
      int var4 = this.getLength();
      if (var1 == null || var2 < 0 || var1.length - var4 < var2) {
         throw new Object();
      } else {
         return var4 > 0 ? Arrays.equals(var1, var2, this.getMAC(var3), 0, var4) : true;
      }
   }
}
