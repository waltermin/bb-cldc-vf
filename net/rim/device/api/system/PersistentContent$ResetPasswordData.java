package net.rim.device.api.system;

import net.rim.device.api.crypto.Digest;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;

class PersistentContent$ResetPasswordData implements Persistable {
   private byte[] _B;
   private byte[] _digestOfB;
   private byte[] _D;
   private byte[] _passwordCiphertext;
   private static final long ID;

   private PersistentContent$ResetPasswordData(byte[] var1, byte[] var2, byte[] var3) {
      this._B = var1;
      this._D = var2;
      this._passwordCiphertext = var3;
      Object var4 = new Object();
      ((Digest)var4).update(var1);
      this._digestOfB = ((Digest)var4).getDigest();
   }

   byte[] getD() {
      return this._D;
   }

   byte[] getDigestOfB() {
      return this._digestOfB;
   }

   boolean isSameB(byte[] var1) {
      return Arrays.equals(this._B, var1);
   }

   byte[] getPasswordCiphertext() {
      return this._passwordCiphertext;
   }

   static void createInstance(byte[] var0, byte[] var1, byte[] var2) {
      PersistentObject var3 = PersistentStore.getPersistentObject(-4896566637383887503L);
      var3.setContents(new PersistentContent$ResetPasswordData(var0, var1, var2), 51);
   }

   static PersistentContent$ResetPasswordData getInstance() {
      PersistentObject var0 = PersistentStore.getPersistentObject(-4896566637383887503L);
      return (PersistentContent$ResetPasswordData)var0.getContents();
   }

   static void clearInstance() {
      PersistentObject var0 = PersistentStore.getPersistentObject(-4896566637383887503L);
      var0.setContents(null, 51);
   }
}
