package net.rim.device.api.crypto;

import net.rim.device.api.util.Arrays;

public class AbstractPseudoRandomSource {
   protected AbstractPseudoRandomSource() {
   }

   public void xorBytes(byte[] var1, int var2, int var3) {
      throw null;
   }

   public void xorBytes(byte[] var1) {
      if (var1 == null) {
         throw new Object();
      }

      this.xorBytes(var1, 0, var1.length);
   }

   public void xorBytes(byte[] var1, int var2, int var3, byte[] var4, int var5) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2 && var4 != null && var5 >= 0 && var4.length - var3 >= var5) {
         System.arraycopy(var1, var2, var4, var5, var3);
         this.xorBytes(var4, var5, var3);
      } else {
         throw new Object();
      }
   }

   public byte[] xorCopy(byte[] var1) {
      if (var1 == null) {
         throw new Object();
      } else {
         return this.xorCopy(var1, 0, var1.length);
      }
   }

   public byte[] xorCopy(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         byte[] var4 = new byte[var3];
         this.xorBytes(var1, var2, var3, var4, 0);
         return var4;
      } else {
         throw new Object();
      }
   }

   public void getBytes(byte[] var1, int var2, int var3) {
      if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length - var3 >= var2) {
         Arrays.fill(var1, (byte)0, var2, var3);
         this.xorBytes(var1, var2, var3);
      } else {
         throw new Object();
      }
   }

   public byte[] getBytes(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      byte[] var2 = new byte[var1];
      this.xorBytes(var2, 0, var1);
      return var2;
   }

   public void getBytes(byte[] var1) {
      if (var1 == null) {
         throw new Object();
      }

      this.getBytes(var1, 0, var1.length);
   }
}
