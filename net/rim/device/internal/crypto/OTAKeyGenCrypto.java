package net.rim.device.internal.crypto;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.crypto.SHA512Digest;
import net.rim.device.api.util.WeakReferenceUtilities;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

public final class OTAKeyGenCrypto {
   private byte[] _deviceSTPrivateKey;
   private byte[] _deviceSTPublicKey;
   private byte _confirmationDataCounter;
   private ByteArrayOutputStream _confirmationDataStream;
   private WeakReference _oneByteBufferWR = (WeakReference)(new Object(null));
   private WeakReference _fourByteBufferWR = (WeakReference)(new Object(null));
   public static String OTAKEYGEN_CID;
   public static final int SUCCESS;
   public static final int BUFFER_TOO_SMALL;
   public static final int CONFIRMATION_FAILED;
   public static final int ILLEGAL_ARGUMENT;
   public static final int ERROR;
   public static final byte REMOVE_ONLY_UID;
   public static final byte REMOVE_ONLY_KEYID;
   public static final byte EXPIRE_KEY;
   private static final int FIELD_ELEMENT_BIT_LENGTH;
   private static final int FIELD_ELEMENT_BYTE_LENGTH;
   private static final int COMPRESSED_POINT_BYTE_LENGTH;
   private static final byte HIGH_BYTE_MASK;
   private static final byte LOW_BYTE_MASK;
   public static final int PRIVATE_KEY_BYTE_LENGTH;
   public static final int PUBLIC_KEY_BYTE_LENGTH;
   public static final int KEY_MATERIAL_BYTE_LENGTH;
   public static final byte REKEY_ALGORITHM_ECMQV;
   private static final byte[] DEVICE_CONFIRMATION_DATA_SUFFIX;
   private static final byte[] SERVER_CONFIRMATION_DATA_SUFFIX;

   public OTAKeyGenCrypto() {
      this._confirmationDataStream = (ByteArrayOutputStream)(new Object());
   }

   public final int beginActivation(byte[] var1, byte[] var2) {
      byte[] var3 = EncryptionUtilities.generateCurvePointFromByteArray(2, var1);
      this._deviceSTPrivateKey = new byte[66];
      RandomSource.getBytes(this._deviceSTPrivateKey);
      this._deviceSTPrivateKey[0] = (byte)(this._deviceSTPrivateKey[0] & 0);
      this._deviceSTPrivateKey[65] = (byte)(this._deviceSTPrivateKey[65] | 2);
      byte[] var4 = new byte[66];
      EncryptionUtilities.calculateKey(2, var3, this._deviceSTPrivateKey, var4);
      this._deviceSTPublicKey = new byte[67];
      this._deviceSTPublicKey[0] = 2;
      System.arraycopy(var4, 0, this._deviceSTPublicKey, 1, var4.length);
      Array.resize(var2, 67);
      System.arraycopy(this._deviceSTPublicKey, 0, var2, 0, this._deviceSTPublicKey.length);
      return 0;
   }

   public final int beginReKey(byte[] var1) {
      this._deviceSTPrivateKey = new byte[66];
      this._deviceSTPublicKey = new byte[67];
      EncryptionUtilities.createKeyPair(2, this._deviceSTPublicKey, this._deviceSTPrivateKey);
      Array.resize(var1, 67);
      System.arraycopy(this._deviceSTPublicKey, 0, var1, 0, this._deviceSTPublicKey.length);
      return 0;
   }

   public final int continueActivation(byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6) {
      byte[] var7 = EncryptionUtilities.calculateKey(2, var2, this._deviceSTPrivateKey);
      byte[] var8 = EncryptionUtilities.calculateKey(2, var1, var3);
      Object var9 = new Object();
      ((SHA512Digest)var9).update(var7);
      ((SHA512Digest)var9).update(var8);
      byte[] var10 = new byte[((SHA512Digest)var9).getDigestLength()];
      ((SHA512Digest)var9).getDigest(var10, 0);
      Array.resize(var5, 32);
      System.arraycopy(var10, 0, var5, 0, var5.length);
      return this.doConfirmationValueWork(var4, var6, var10);
   }

   public final int continueReKey(byte[] var1, byte[] var2, byte[] var3, byte[] var4, byte[] var5, byte[] var6) {
      byte[] var7 = EncryptionUtilities.generateECMQVSharedSecret(2, var3, this._deviceSTPrivateKey, this._deviceSTPublicKey, var1, var2);
      Object var8 = new Object();
      ((SHA512Digest)var8).update(var7);
      byte[] var9 = new byte[((SHA512Digest)var8).getDigestLength()];
      ((SHA512Digest)var8).getDigest(var9, 0);
      Array.resize(var5, 32);
      System.arraycopy(var9, 0, var5, 0, var5.length);
      return this.doConfirmationValueWork(var4, var6, var9);
   }

   private final int doConfirmationValueWork(byte[] var1, byte[] var2, byte[] var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int generateKeyPair(byte[] var0, byte[] var1) {
      Array.resize(var0, 67);
      Array.resize(var1, 66);
      EncryptionUtilities.createKeyPair(2, var0, var1);
      return 0;
   }

   public final int addDataToHash(byte[] var1) {
      this._confirmationDataStream.write(this._confirmationDataCounter);
      this._confirmationDataStream.write(var1, 0, var1.length);
      this._confirmationDataCounter++;
      return 0;
   }

   public final int addDataToHash(byte var1) {
      byte[] var2 = WeakReferenceUtilities.getByteArray(this._oneByteBufferWR, 1);
      var2[0] = var1;
      return this.addDataToHash(var2);
   }

   public final int addDataToHash(int var1) {
      byte[] var2 = WeakReferenceUtilities.getByteArray(this._fourByteBufferWR, 4);
      var2[0] = (byte)(var1 >> 24 & 0xFF);
      var2[1] = (byte)(var1 >> 16 & 0xFF);
      var2[2] = (byte)(var1 >> 8 & 0xFF);
      var2[3] = (byte)(var1 & 0xFF);
      return this.addDataToHash(var2);
   }

   public final int addDataToHash(String var1) {
      return this.addDataToHash(var1.getBytes());
   }

   public static final void addSymmetricKey(String var0, DataInput var1) {
      CryptoBlock.addSymmetricKey(var0, var1);
   }

   public static final void addSymmetricKey(String var0, DataInput var1, long var2) {
      CryptoBlock.addSymmetricKey(var0, var1, var2);
   }

   public static final void addSymmetricKeyAsSecondaryKey(String var0, DataInput var1, long var2) {
      CryptoBlock.addSymmetricKeyAsSecondaryKey(var0, var1, var2);
   }

   public static final byte[] getSymmetricKey(String var0) {
      return CryptoBlock.getSymmetricKey(var0);
   }

   public static final String getKeyIDForUID(String var0) {
      return CryptoBlock.getKeyIDForUID(var0);
   }

   public static final boolean moveKey(String var0, String var1) {
      return CryptoBlock.moveKey(var0, var1);
   }

   public static final boolean removeSymmetricKey(String var0, byte var1) {
      return CryptoBlock.removeSymmetricKey(var0, var1);
   }

   public static final boolean removeSymmetricKeyByKeyID(String var0, byte var1) {
      return CryptoBlock.removeSymmetricKeyByKeyID(var0, var1);
   }

   public static final boolean revertSymmetricKey(String var0) {
      return CryptoBlock.revertSymmetricKey(var0);
   }
}
