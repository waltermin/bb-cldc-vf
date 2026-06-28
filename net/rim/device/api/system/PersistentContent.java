package net.rim.device.api.system;

import java.io.EOFException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import net.rim.device.api.crypto.CryptoByteArrayArithmetic;
import net.rim.device.api.crypto.Digest;
import net.rim.device.api.crypto.ECPointAtInfinityException;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.crypto.SHA1Digest;
import net.rim.device.api.crypto.SHA256Digest;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.Persistable;
import net.rim.device.api.util.WeakReferenceUtilities;
import net.rim.device.internal.compress.CompressUtilities;
import net.rim.device.internal.crypto.EncryptionUtilities;
import net.rim.device.internal.system.NvStore;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

public final class PersistentContent {
   private boolean _compress;
   private boolean _encrypt;
   private int _encryptStrength;
   private boolean _pendingCompress;
   private boolean _pendingEncrypt;
   private int _modeGeneration;
   private int _lockGeneration;
   private int _numRecodes;
   private int _numPKRecodes;
   private boolean _secureGCNeeded;
   private boolean _secure;
   private final int _symmetricKeyLength;
   private byte[] _deviceSymmetricKey;
   private final int _encryptionOverhead;
   private int[] _eccCurves;
   private final int _publicKeyLength0;
   private final int _publicKeyLength1;
   private final int _publicKeyLength2;
   private int[] _publicKeyLengths;
   private byte[][][] _devicePublicKeys;
   private final int _privateKeyLength0;
   private final int _privateKeyLength1;
   private final int _privateKeyLength2;
   private int[] _privateKeyLengths;
   private byte[][][] _devicePrivateKeys;
   private byte[] _ephemeralPublicKey;
   private Object _ticket;
   private WeakReference _ticketWR;
   private PersistentContent$ObjectCache _objectCache;
   private PersistentContent$SymmetricKeyCache _symmetricKeyCache;
   private PersistentContent$Listeners _listeners;
   private WeakReference _ceBufferWR;
   private String _emptyString;
   private byte[] _emptyByteArray;
   private final int _expectedNvStoreDataLength;
   private Thread _checkSecureThread;
   private int _checkSecureDelay;
   private Hashtable _listenerClassNames;
   private PersistentObject _listenerClassNamesPersistentObject;
   private long _rTimestamp;
   private byte[] _r;
   private byte[] _passwordKey;
   private byte[] _digestOfrD;
   private static final int COMPRESS_MIN_BYTE_LENGTH;
   private static final int MASTER_HEADER_BYTE_LENGTH;
   private static final int MASTER_COMPRESS_FLAG;
   private static final int MASTER_ENCRYPT_FLAG;
   private static final int MASTER_STRING_FLAG;
   private static final int MASTER_BYTES_FLAG;
   private static final int NUM_MASTER_FLAGS;
   private static final int MASTER_FLAGS_MASK;
   private static final int MASTER_BYTE_LENGTH_SHIFT;
   private static final int MASTER_BYTE_LENGTH_BITS;
   private static final int MAX_MASTER_BYTE_LENGTH;
   private static final int BLOCK_HEADER_BYTE_LENGTH;
   private static final int BLOCK_COMPRESS_FLAG;
   private static final int BLOCK_PUBLIC_KEY_FLAG;
   private static final int BLOCK_DEVICE_KEY_FLAG;
   private static final int BLOCK_BYTES_FLAG;
   private static final int NUM_BLOCK_FLAGS;
   private static final int BLOCK_BYTE_LENGTH_SHIFT;
   private static final int BLOCK_BYTE_LENGTH_BITS;
   private static final int MAX_BLOCK_BYTE_LENGTH;
   private static final long ID;
   private static final long EVENT_LOGGER_ID;
   private static PersistentContent _instance;
   private static boolean _fileHook;
   private static final int ECC_CURVE_0;
   private static final int ECC_CURVE_1;
   private static final int ECC_CURVE_2;
   private static final int ONE_SECOND;
   private static final int ONE_MINUTE;
   private static final int ONE_HOUR;
   private static final int INITIAL_CHECK_SECURE_DELAY;
   private static final byte NV_VERSION;
   private static final int NV_COMPRESS_FLAG;
   private static final int NV_ENCRYPT_FLAG;
   private static final int NV_STRENGTH_MASK;
   private static final int NV_STRENGTH_SHIFT;
   private static final long R_LIFETIME;

   private PersistentContent() {
   }

   static final PersistentContent getInstance() {
      return _instance;
   }

   private final byte[] calculateStorageKey(String password, byte[] salt, int offset, int length) {
      throw new RuntimeException("cod2jar: array init");
   }

   private final byte[] getNvStoreData() {
      byte[] data = NvStore.readData(10);
      if (data == null) {
         return null;
      }

      if ((data.length == 2 || data.length == this._expectedNvStoreDataLength) && data[0] == 9) {
         return data;
      }

      NvStore.deleteData(10);
      return null;
   }

   private final void parseNvStoreData(String password) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final void loadSettings(String password) {
      this.parseNvStoreData(password);
      this._ticket = this._ticketWR.get();
      if (this._ticket == null) {
         this._ticket = new Object();
         net.rim.vm.Memory.setPlaintext(this._ticket);
         this._ticketWR.set(this._ticket);
      }
   }

   private final void saveContentCompressionSettings() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void saveContentProtectionSettings(String password) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void registerPersistentContentIndicator(PersistentContentListener listener) {
      this._listeners.addIndicator(listener);
   }

   final synchronized void lock() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final synchronized void lock2(int generation) {
      if (generation == this._lockGeneration && this._encrypt && this._ticket != null) {
         this._ticket = null;
         this._listeners.stateChanged(4, this._lockGeneration);
         this.checkSecure();
      }
   }

   public static final boolean isSecure() {
      synchronized (_instance) {
         return _instance._secure;
      }
   }

   public static final boolean isTicketInUse() {
      synchronized (_instance) {
         return _instance._ticketWR.get() != null;
      }
   }

   private final synchronized void checkSecure() {
      if (this._checkSecureThread != null && this._checkSecureDelay != 1000) {
         this._checkSecureDelay = 1000;
         synchronized (this._checkSecureThread) {
            this._checkSecureThread.notify();
         }
      }
   }

   private final void checkSecureLoop() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void encryptPassword(String password, boolean force) {
      if (password != null) {
         byte[] plaintext = password.getBytes();
         int curve = 2;
         int publicKeyLength = EncryptionUtilities.getPublicKeyLength(curve);
         int privateKeyLength = EncryptionUtilities.getPrivateKeyLength(curve);
         byte[] B = this.getBFromITPolicy(publicKeyLength);
         if (B == null) {
            PersistentContent$ResetPasswordData.clearInstance();
         } else {
            PersistentContent$ResetPasswordData data = PersistentContent$ResetPasswordData.getInstance();
            if (force || data == null || !data.isSameB(B)) {
               byte[] d = net.rim.vm.Memory.allocRAMOnlyBytes(privateKeyLength);
               byte[] D = new byte[publicKeyLength];
               EncryptionUtilities.createKeyPair(curve, D, d);
               byte[] sharedPoint = net.rim.vm.Memory.allocRAMOnlyBytes(privateKeyLength);
               EncryptionUtilities.calculateKey(curve, B, d, sharedPoint);
               Digest digest = new SHA256Digest();
               byte[] key = net.rim.vm.Memory.allocRAMOnlyBytes(digest.getDigestLength());
               digest.update(sharedPoint);
               digest.getDigest(key, 0);
               byte[] ciphertext = new byte[EncryptionUtilities.getCiphertextLength(plaintext.length)];
               EncryptionUtilities.encrypt(key, plaintext, 0, plaintext.length, ciphertext, 0);
               PersistentContent$ResetPasswordData.createInstance(B, D, ciphertext);
            }
         }
      }
   }

   final byte[] getBFromITPolicy(int expectedKeyLength) {
      byte[] result = null;
      byte[] policyDataBytes = ITPolicy.getByteArray(248);
      if (policyDataBytes != null && policyDataBytes.length >= expectedKeyLength + 2) {
         DataBuffer policyDataBuffer = new DataBuffer(policyDataBytes, 0, policyDataBytes.length, true);

         try {
            byte itemID = policyDataBuffer.readByte();
            result = policyDataBuffer.readByteArray();
            if (result == null || result.length != expectedKeyLength) {
               return null;
            }
         } catch (EOFException e) {
            return null;
         } catch (IOException e) {
            result = null;
         }
      }

      return result;
   }

   final byte[] getD() {
      int curve = 2;
      int publicKeyLength = EncryptionUtilities.getPublicKeyLength(curve);
      int privateKeyLength = EncryptionUtilities.getPrivateKeyLength(curve);
      PersistentContent$ResetPasswordData data = PersistentContent$ResetPasswordData.getInstance();
      if (data == null) {
         return new byte[0];
      }

      byte[] modulus = new byte[privateKeyLength];
      EncryptionUtilities.getGroupOrder(curve, modulus);
      long now = System.currentTimeMillis();
      if (this._r == null || now - this._rTimestamp < 300000) {
         this._rTimestamp = now;
         this._r = net.rim.vm.Memory.allocRAMOnlyBytes(modulus.length);

         do {
            CryptoByteArrayArithmetic.mod(RandomSource.getBytes(modulus.length), modulus, this._r);
         } while (CryptoByteArrayArithmetic.isZero(this._r));
      }

      byte[] rD = new byte[publicKeyLength];

      try {
         EncryptionUtilities.scalarMultiply(curve, this._r, data.getD(), rD);
      } catch (ECPointAtInfinityException e) {
         throw new RuntimeException(e.toString());
      }

      Digest digest = new SHA1Digest();
      digest.update(rD);
      this._digestOfrD = digest.getDigest();
      byte[] result = new byte[rD.length];
      System.arraycopy(rD, 0, result, 0, rD.length);
      return result;
   }

   final byte[] getBChecksum() {
      byte[] result = null;
      PersistentContent$ResetPasswordData data = PersistentContent$ResetPasswordData.getInstance();
      return data == null ? new byte[0] : data.getDigestOfB();
   }

   final boolean setK(byte[] data, byte[] checksum) {
      int curve = 2;
      int publicKeyLength = EncryptionUtilities.getPublicKeyLength(curve);
      int privateKeyLength = EncryptionUtilities.getPrivateKeyLength(curve);
      PersistentContent$ResetPasswordData resetPasswordData = PersistentContent$ResetPasswordData.getInstance();
      if (data != null
         && checksum != null
         && resetPasswordData != null
         && data.length == privateKeyLength
         && checksum.length == 20
         && Arrays.equals(checksum, 0, this._digestOfrD, 0, this._digestOfrD.length)) {
         byte[] K = new byte[publicKeyLength];
         K[0] = 2;
         System.arraycopy(data, 0, K, 1, data.length);
         byte[] modulus = new byte[privateKeyLength];
         EncryptionUtilities.getGroupOrder(curve, modulus);
         byte[] rInverse = new byte[modulus.length];
         CryptoByteArrayArithmetic.invert(this._r, modulus, rInverse);
         byte[] sharedPoint = net.rim.vm.Memory.allocRAMOnlyBytes(privateKeyLength);
         EncryptionUtilities.calculateKey(curve, K, rInverse, sharedPoint);
         Digest digest = new SHA256Digest();
         this._passwordKey = net.rim.vm.Memory.allocRAMOnlyBytes(digest.getDigestLength());
         digest.update(sharedPoint);
         digest.getDigest(this._passwordKey, 0);
         return true;
      } else {
         return false;
      }
   }

   private final String decryptPassword() {
      PersistentContent$ResetPasswordData resetPasswordData = PersistentContent$ResetPasswordData.getInstance();
      if (resetPasswordData == null) {
         return null;
      }

      byte[] ciphertext = resetPasswordData.getPasswordCiphertext();
      byte[] plaintext = net.rim.vm.Memory.allocRAMOnlyBytes(ciphertext.length);
      int plaintextLength = EncryptionUtilities.decrypt(this._passwordKey, ciphertext, 0, ciphertext.length, plaintext, 0);
      String password = new String(plaintext, 0, plaintextLength);
      net.rim.vm.Memory.setPlaintext(password);
      return password;
   }

   final void clearK() {
      this._r = null;
      this._passwordKey = null;
   }

   final void unlock(String password) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final boolean isContentProtectionSupported() {
      return EncryptionUtilities.isSupported(0);
   }

   final synchronized void setContentProtection(String password, boolean encrypt, int strength) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void changePassword(String oldPassword, String newPassword) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void setContentCompression(boolean compress) {
      if (this._pendingCompress != compress) {
         this._numRecodes++;
      }

      this._pendingCompress = compress;
      this.saveContentCompressionSettings();
   }

   private final synchronized void setMode(int modeGeneration) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final synchronized void setModeComplete(int modeGeneration) {
      if (modeGeneration == this._modeGeneration) {
         this._numRecodes = 0;
         this._numPKRecodes = 0;
      }
   }

   final boolean doesEncryptionKeyExist() {
      if (this._devicePrivateKeys != null) {
         return true;
      }

      byte[] data = this.getNvStoreData();
      return data != null && data.length > 2;
   }

   public static final boolean isEncryptionEnabled() {
      synchronized (_instance) {
         return _instance._pendingEncrypt;
      }
   }

   public static final int getEncryptionStrength() {
      return MathUtilities.clamp(0, Math.max(_instance._encryptStrength, ITPolicy.getInteger(24, 18, 0)), _instance._publicKeyLengths.length - 1);
   }

   public static final boolean isCompressionEnabled() {
      synchronized (_instance) {
         return _instance._pendingCompress;
      }
   }

   public static final int getModeGeneration() {
      synchronized (_instance) {
         return _instance._modeGeneration;
      }
   }

   public static final int getLockGeneration() {
      synchronized (_instance) {
         return _instance._lockGeneration;
      }
   }

   public static final int getState() {
      synchronized (_instance) {
         return _instance._listeners.getState();
      }
   }

   public static final void addListener(PersistentContentListener listener) {
      addListener(listener, false);
   }

   public static final void addWeakListener(PersistentContentListener listener) {
      addListener(listener, true);
   }

   private static final void addListener(PersistentContentListener listener, boolean weakListener) {
      synchronized (_instance) {
         _instance._listeners.add(listener, weakListener);
         _instance.addListenerClassName(listener);
      }
   }

   public static final void removeListener(PersistentContentListener listener) {
      synchronized (_instance) {
         _instance._listeners.remove(listener);
      }
   }

   private final void addListenerClassName(PersistentContentListener listener) {
      if (listener instanceof Persistable) {
         String className = listener.getClass().getName();
         this._listenerClassNames.put(className, className);
         this._listenerClassNamesPersistentObject.commit();
      }
   }

   private final synchronized void findListeners() {
      Enumeration e = this._listenerClassNames.elements();

      while (e.hasMoreElements()) {
         String className = (String)e.nextElement();

         try {
            Object[] listeners = net.rim.vm.Memory.getAllInstances(Class.forName(className));

            for (int i = listeners.length - 1; i >= 0; i--) {
               PersistentContentListener listener = (PersistentContentListener)listeners[i];
               if (!this._listeners.isListener(listener)) {
                  this._listeners.add(listener, true);
               }
            }
         } catch (ClassNotFoundException ex) {
            this._listenerClassNames.remove(className);
            this._listenerClassNamesPersistentObject.commit();
         }
      }
   }

   public static final void requestReEncode() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public static final void markAsPlaintext(Object object) {
      if (object != null) {
         _instance.setPlaintext(object);
      }
   }

   private final synchronized void setPlaintext(Object object) {
      if (this._encrypt || this._pendingEncrypt) {
         net.rim.vm.Memory.setPlaintext(object);
         this.checkSecure();
      }
   }

   public static final Object copyEncoding(Object encoding) {
      Object copy = copy(encoding);
      if (net.rim.vm.Memory.isPlaintext(encoding)) {
         markAsPlaintext(copy);
      }

      return copy;
   }

   private static final Object copy(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object getTicket() {
      synchronized (_instance) {
         return _instance._ticket;
      }
   }

   public static final Object waitForTicket() {
      while (true) {
         Object ticket = getTicket();
         if (ticket != null) {
            return ticket;
         }

         synchronized (_instance) {
            try {
               _instance.wait();
            } catch (InterruptedException var4) {
            }
         }
      }
   }

   public static final Object encodeObject(Object obj) {
      return encodeObject(obj, true, true);
   }

   public static final Object encodeObject(Object obj, boolean compress, boolean encrypt) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object encode(String string) {
      return encodeAndAppend(string, 0, -1, true, true, null);
   }

   public static final Object encode(String string, boolean compress, boolean encrypt) {
      return encodeAndAppend(string, 0, -1, compress, encrypt, null);
   }

   public static final Object encode(String string, int index, int length, boolean compress, boolean encrypt) {
      return encodeAndAppend(string, index, length, compress, encrypt, null);
   }

   public static final Object encodeAndAppend(String string, Object content) {
      return encodeAndAppend(string, 0, -1, true, true, content);
   }

   public static final Object encodeAndAppend(String string, boolean compress, boolean encrypt, Object content) {
      return encodeAndAppend(string, 0, -1, compress, encrypt, content);
   }

   public static final Object encodeAndAppend(String string, int index, int length, boolean compress, boolean encrypt, Object content) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final Object encode(byte[] data) {
      return encodeAndAppend(data, 0, -1, true, true, null);
   }

   public static final Object encode(byte[] data, boolean compress, boolean encrypt) {
      return encodeAndAppend(data, 0, -1, compress, encrypt, null);
   }

   public static final Object encode(byte[] data, int offset, int length, boolean compress, boolean encrypt) {
      return encodeAndAppend(data, offset, length, compress, encrypt, null);
   }

   public static final Object encodeAndAppend(byte[] data, Object content) {
      return encodeAndAppend(data, 0, -1, true, true, content);
   }

   public static final Object encodeAndAppend(byte[] data, boolean compress, boolean encrypt, Object content) {
      return encodeAndAppend(data, 0, -1, compress, encrypt, content);
   }

   public static final Object encodeAndAppend(byte[] data, int offset, int length, boolean compress, boolean encrypt, Object content) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final char[] read(char[] o) {
      return !_fileHook ? o : Array.readCharArrayFromFile(o);
   }

   private static final char[] write(char[] o) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final synchronized Object encode(
      Object input,
      int inputByteOffset,
      int inputByteLength,
      int inputElementLength,
      int inputMaxElementLength,
      boolean string,
      boolean compress,
      boolean encrypt,
      char[] output
   ) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final char[] grow(char[] output, int maxByteOffset) {
      int maxCharOffset = maxByteOffset + 3 + 1 >> 1;
      if (output == null) {
         return new char[maxCharOffset];
      }

      Array.resize(output, maxCharOffset);
      return output;
   }

   private final synchronized int encodeBlocks(
      Object input,
      int inputByteOffset,
      int inputByteLength,
      boolean compress,
      boolean encrypt,
      byte[] symmetricKey,
      int eccCurveId,
      boolean bytes,
      char[] output,
      int outputByteOffset
   ) {
      int initialHeader = bytes ? 8 : 0;
      byte[] publicKey = null;
      if (encrypt) {
         if (symmetricKey != null) {
            initialHeader |= 4;
         } else {
            int publicKeyLength = this._publicKeyLengths[eccCurveId];
            publicKey = new byte[publicKeyLength];
            byte[] privateKey = new byte[this._privateKeyLengths[eccCurveId]];
            int eccCurve = this._eccCurves[eccCurveId];
            EncryptionUtilities.createKeyPair(eccCurve, publicKey, privateKey);
            symmetricKey = EncryptionUtilities.calculateKey(eccCurve, (byte[])this._devicePublicKeys[eccCurveId], privateKey);
            net.rim.vm.Memory.setPlaintext(privateKey);
            net.rim.vm.Memory.setPlaintext(symmetricKey);
         }
      }

      do {
         int blockByteLength = inputByteLength > 4096 ? 4096 : inputByteLength;
         int header = initialHeader;
         int headerByteOffset = outputByteOffset;
         outputByteOffset += 2;
         int headerByteLength;
         int encodedByteLength;
         if (!encrypt) {
            if (compress) {
               int compressedByteLength = CompressUtilities.compressBlock(input, inputByteOffset, blockByteLength, output, outputByteOffset, false);
               header |= compressedByteLength < blockByteLength ? 1 : 0;
               encodedByteLength = compressedByteLength;
               headerByteLength = blockByteLength;
            } else {
               copyBytes(input, inputByteOffset, output, outputByteOffset, blockByteLength, false);
               encodedByteLength = blockByteLength;
               headerByteLength = blockByteLength;
            }
         } else {
            if (publicKey != null) {
               setBytes(output, outputByteOffset, eccCurveId, 1);
               int publicKeyLength = this._publicKeyLengths[eccCurveId];
               copyBytes(publicKey, 0, output, ++outputByteOffset, publicKeyLength, false);
               publicKey = null;
               outputByteOffset += publicKeyLength;
               header |= 2;
            }

            if (compress) {
               char[] ceBuffer = WeakReferenceUtilities.getCharArray(this._ceBufferWR, 2048);
               net.rim.vm.Memory.setPlaintext(ceBuffer);
               int compressedByteLength = CompressUtilities.compressBlock(input, inputByteOffset, blockByteLength, ceBuffer, 0, true);
               RandomSource.add(ceBuffer);
               header |= compressedByteLength < blockByteLength ? 1 : 0;
               encodedByteLength = EncryptionUtilities.encrypt(symmetricKey, ceBuffer, 0, compressedByteLength, output, outputByteOffset);
               headerByteLength = compressedByteLength;
            } else {
               encodedByteLength = EncryptionUtilities.encrypt(symmetricKey, input, inputByteOffset, blockByteLength, output, outputByteOffset);
               headerByteLength = blockByteLength;
            }
         }

         header |= headerByteLength << 4;
         setBytes(output, headerByteOffset, header, 2);
         inputByteOffset += blockByteLength;
         inputByteLength -= blockByteLength;
         outputByteOffset += encodedByteLength;
      } while (inputByteLength > 0);

      return outputByteOffset;
   }

   public static final Object decode(Object content) {
      return decode(content, false);
   }

   public static final String decodeString(Object content) {
      return (String)decode(content, false);
   }

   public static final String decodeString(Object content, boolean firstBlockOnly) {
      return (String)decode(content, firstBlockOnly);
   }

   public static final byte[] decodeByteArray(Object content) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final byte[] decodeByteArray(Object content, boolean firstBlockOnly) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object decode(Object content, boolean firstBlockOnly) {
      throw new RuntimeException("cod2jar: type check");
   }

   final synchronized Object decode(char[] input, boolean firstBlockOnly, boolean keepPlaintextInRAM) {
      Object output = this._objectCache.get(input, firstBlockOnly);
      if (output != null) {
         return output;
      }

      char[] originalInput = input;
      input = read(input);
      int header = getMasterHeader(input);
      boolean compress = getFlag(header, 1);
      boolean encrypt = getFlag(header, 2);
      boolean string = getFlag(header, 4);
      boolean bytes = getFlag(header, 8);
      if (encrypt && this._devicePrivateKeys == null) {
         throw new IllegalStateException();
      }

      int byteLength = header >> 4;
      if (firstBlockOnly) {
         int blockHeader = getBytes(input, 0, 2);
         bytes = getFlag(blockHeader, 8);
         if (!compress || !encrypt) {
            byteLength = blockHeader >> 4;
            if (byteLength == 0) {
               byteLength = 4096;
            }
         } else if (byteLength > 4096) {
            byteLength = 4096;
         }
      }

      if (bytes) {
         if (keepPlaintextInRAM) {
            output = net.rim.vm.Memory.allocRAMOnlyBytes(byteLength);
            if (output == null) {
               throw new OutOfMemoryError();
            }
         } else {
            output = new byte[byteLength];
         }
      } else {
         output = new char[byteLength >> 1];
      }

      if (encrypt != this._encrypt || compress != this._compress) {
         this._numRecodes++;
      }

      this.decodeBlocks(input, compress, encrypt, !bytes, output, byteLength, firstBlockOnly);
      if (string) {
         CompressUtilities.convertToString(output);
      }

      if (encrypt && this._encrypt) {
         this.setPlaintext(output);
      }

      if (!keepPlaintextInRAM) {
         this._objectCache.put(originalInput, firstBlockOnly, output);
      }

      return output;
   }

   private final synchronized void decodeBlocks(
      char[] input, boolean compress, boolean encrypt, boolean masterConvertBytesToChars, Object output, int outputByteLength, boolean firstBlockOnly
   ) {
      int inputByteOffset = 0;
      int outputByteOffset = 0;
      byte[] symmetricKey = null;

      do {
         int blockHeader = getBytes(input, inputByteOffset, 2);
         inputByteOffset += 2;
         boolean convertBytesToChars = masterConvertBytesToChars && getFlag(blockHeader, 8);
         int headerByteLength = blockHeader >> 4;
         if (headerByteLength == 0) {
            headerByteLength = 4096;
         }

         int ciphertextByteLength;
         int decompressedByteLength;
         if (!encrypt) {
            if (compress && getFlag(blockHeader, 1)) {
               decompressedByteLength = headerByteLength;
               ciphertextByteLength = CompressUtilities.decompressBlock(
                  input, inputByteOffset, output, outputByteOffset, decompressedByteLength, convertBytesToChars
               );
               if (convertBytesToChars) {
                  decompressedByteLength <<= 1;
               }
            } else {
               ciphertextByteLength = headerByteLength;
               decompressedByteLength = headerByteLength;
               copyBytes(input, inputByteOffset, output, outputByteOffset, ciphertextByteLength, convertBytesToChars);
               if (convertBytesToChars) {
                  decompressedByteLength <<= 1;
               }
            }
         } else {
            if (getFlag(blockHeader, 2)) {
               int hashOffset = inputByteOffset;
               symmetricKey = this._symmetricKeyCache.get(input, hashOffset);
               if (symmetricKey == null) {
                  this._numPKRecodes++;
                  int eccCurveId = getBytes(input, inputByteOffset++, 1);
                  int publicKeyLength = this._publicKeyLengths[eccCurveId];
                  byte[] publicKey = new byte[publicKeyLength];
                  copyBytes(input, inputByteOffset, publicKey, 0, publicKeyLength, false);
                  inputByteOffset += publicKeyLength;
                  symmetricKey = EncryptionUtilities.calculateKey(this._eccCurves[eccCurveId], publicKey, (byte[])this._devicePrivateKeys[eccCurveId]);
                  net.rim.vm.Memory.setPlaintext(symmetricKey);
                  this._symmetricKeyCache.put(input, hashOffset, symmetricKey);
               }
            } else if (getFlag(blockHeader, 4)) {
               symmetricKey = this._deviceSymmetricKey;
            }

            int plaintextByteLength = headerByteLength;
            ciphertextByteLength = EncryptionUtilities.getCiphertextLength(plaintextByteLength);
            if (compress && getFlag(blockHeader, 1)) {
               char[] ceBuffer = WeakReferenceUtilities.getCharArray(this._ceBufferWR, 2048);
               net.rim.vm.Memory.setPlaintext(ceBuffer);
               EncryptionUtilities.decrypt(symmetricKey, input, inputByteOffset, ciphertextByteLength, ceBuffer, 0, false);
               decompressedByteLength = CompressUtilities.decompressBlock(ceBuffer, 0, output, outputByteOffset, -1, convertBytesToChars);
            } else {
               decompressedByteLength = EncryptionUtilities.decrypt(
                  symmetricKey, input, inputByteOffset, ciphertextByteLength, output, outputByteOffset, convertBytesToChars
               );
            }
         }

         outputByteOffset += decompressedByteLength;
         inputByteOffset += ciphertextByteLength;
         if (firstBlockOnly) {
            Array.resize(output, masterConvertBytesToChars ? decompressedByteLength >> 1 : decompressedByteLength);
            return;
         }
      } while (outputByteOffset < outputByteLength);
   }

   private static final int createMasterHeader(boolean compress, boolean encrypt, boolean string, boolean bytes, int inputByteLength) {
      int header = inputByteLength << 4;
      if (compress) {
         header |= 1;
      }

      if (encrypt) {
         header |= 2;
      }

      if (string) {
         header |= 4;
      }

      if (bytes) {
         header |= 8;
      }

      return header;
   }

   private static final int getMasterHeader(char[] output) {
      int outputByteOffset = output.length << 1;
      int header = getBytes(output, outputByteOffset - 4, 4);
      int var3;
      return (header & 0xFF) == 0 ? (var3 = header >>> 8) : header & 16777215;
   }

   private static final int getOutputByteOffset(char[] output) {
      int outputByteOffset = output.length << 1;
      if (getBytes(output, outputByteOffset - 1, 1) == 0) {
         outputByteOffset--;
      }

      return outputByteOffset - 3;
   }

   private static final boolean getFlag(int header, int flag) {
      return (header & flag) != 0;
   }

   static final native void copyBytes(Object var0, int var1, Object var2, int var3, int var4, boolean var5);

   static final native void setBytes(Object var0, int var1, int var2, int var3);

   static final native int getBytes(Object var0, int var1, int var2);

   public static final boolean isCompressed(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isEncrypted(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isString(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isByteArray(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final int getLength(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean checkEncoding(Object encoding) {
      return checkEncoding(encoding, true, true);
   }

   public static final boolean checkEncoding(Object encoding, boolean compress, boolean encrypt) {
      return _instance.checkEncoding2(encoding, compress, encrypt);
   }

   private final synchronized boolean checkEncoding2(Object encoding, boolean compress, boolean encrypt) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object reEncode(Object encoding) {
      return reEncode(encoding, true, true);
   }

   public static final Object reEncode(Object encoding, boolean compress, boolean encrypt) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final byte[] convertEncodingToByteArray(Object encoding) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object convertByteArrayToEncoding(byte[] array) {
      if (array != null && array.length != 0) {
         int byteLength = array.length - 1;
         int type = array[0];
         switch (type) {
            case -1:
               throw new IllegalArgumentException();
            case 0:
            case 1:
            default:
               byte[] encoding = new byte[byteLength];
               copyBytes(array, 1, encoding, 0, byteLength, false);
               if (type == 1) {
                  CompressUtilities.convertToString(encoding);
               }

               return encoding;
            case 2:
            case 3:
               if ((byteLength & 1) == 1) {
                  throw new IllegalArgumentException();
               } else {
                  char[] encoding = new char[byteLength / 2];
                  copyBytes(array, 1, encoding, 0, byteLength, false);
                  if (type == 2) {
                     CompressUtilities.convertToString(encoding);
                  }

                  return encoding;
               }
         }
      } else {
         return null;
      }
   }
}
