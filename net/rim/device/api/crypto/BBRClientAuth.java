package net.rim.device.api.crypto;

public class BBRClientAuth {
   private byte[] _h;
   private byte[] _RD;
   private byte[] _RB;
   private byte[] _rD;
   private byte[] _eB;
   private static final String CRYPTO_SYSTEM;
   private static final int COMPRESSED_PUBLIC_KEY_LENGTH;
   private static final int PRIVATE_KEY_LENGTH;

   public BBRClientAuth(byte[] secret) {
   }

   public byte[] getRD() {
      return this._RD;
   }

   public byte[] getYD(byte[] RB, byte[] eD) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public byte[] getEB() {
      return this._eB;
   }

   public boolean verify(byte[] yB) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
