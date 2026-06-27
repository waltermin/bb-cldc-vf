package net.rim.device.api.system;

import java.util.Hashtable;
import net.rim.device.api.crypto.CryptoByteArrayArithmetic;
import net.rim.device.api.crypto.Digest;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.MathUtilities;
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

   private final byte[] calculateStorageKey(String var1, byte[] var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: array init");
   }

   private final byte[] getNvStoreData() {
      byte[] var1 = NvStore.readData(10);
      if (var1 == null) {
         return null;
      }

      if ((var1.length == 2 || var1.length == this._expectedNvStoreDataLength) && var1[0] == 9) {
         return var1;
      }

      NvStore.deleteData(10);
      return null;
   }

   private final void parseNvStoreData(String var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final void loadSettings(String var1) {
      this.parseNvStoreData(var1);
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

   private final void saveContentProtectionSettings(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void registerPersistentContentIndicator(PersistentContentListener var1) {
      this._listeners.addIndicator(var1);
   }

   final synchronized void lock() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final synchronized void lock2(int var1) {
      if (var1 == this._lockGeneration && this._encrypt && this._ticket != null) {
         this._ticket = null;
         this._listeners.stateChanged(4, this._lockGeneration);
         this.checkSecure();
      }
   }

   public static final boolean isSecure() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isTicketInUse() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final synchronized void checkSecure() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void checkSecureLoop() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void encryptPassword(String var1, boolean var2) {
      if (var1 != null) {
         byte[] var3 = var1.getBytes();
         byte var4 = 2;
         int var5 = EncryptionUtilities.getPublicKeyLength(var4);
         int var6 = EncryptionUtilities.getPrivateKeyLength(var4);
         byte[] var7 = this.getBFromITPolicy(var5);
         if (var7 == null) {
            PersistentContent$ResetPasswordData.clearInstance();
         } else {
            PersistentContent$ResetPasswordData var8 = PersistentContent$ResetPasswordData.getInstance();
            if (var2 || var8 == null || !var8.isSameB(var7)) {
               byte[] var9 = net.rim.vm.Memory.allocRAMOnlyBytes(var6);
               byte[] var10 = new byte[var5];
               EncryptionUtilities.createKeyPair(var4, var10, var9);
               byte[] var11 = net.rim.vm.Memory.allocRAMOnlyBytes(var6);
               EncryptionUtilities.calculateKey(var4, var7, var9, var11);
               Object var12 = new Object();
               byte[] var13 = net.rim.vm.Memory.allocRAMOnlyBytes(((Digest)var12).getDigestLength());
               ((Digest)var12).update(var11);
               ((Digest)var12).getDigest(var13, 0);
               byte[] var14 = new byte[EncryptionUtilities.getCiphertextLength(var3.length)];
               EncryptionUtilities.encrypt(var13, var3, 0, var3.length, var14, 0);
               PersistentContent$ResetPasswordData.createInstance(var7, var10, var14);
            }
         }
      }
   }

   final byte[] getBFromITPolicy(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final byte[] getD() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final byte[] getBChecksum() {
      Object var1 = null;
      PersistentContent$ResetPasswordData var2 = PersistentContent$ResetPasswordData.getInstance();
      return var2 == null ? new byte[0] : var2.getDigestOfB();
   }

   final boolean setK(byte[] var1, byte[] var2) {
      byte var3 = 2;
      int var4 = EncryptionUtilities.getPublicKeyLength(var3);
      int var5 = EncryptionUtilities.getPrivateKeyLength(var3);
      PersistentContent$ResetPasswordData var6 = PersistentContent$ResetPasswordData.getInstance();
      if (var1 != null
         && var2 != null
         && var6 != null
         && var1.length == var5
         && var2.length == 20
         && Arrays.equals(var2, 0, this._digestOfrD, 0, this._digestOfrD.length)) {
         byte[] var7 = new byte[var4];
         var7[0] = 2;
         System.arraycopy(var1, 0, var7, 1, var1.length);
         byte[] var8 = new byte[var5];
         EncryptionUtilities.getGroupOrder(var3, var8);
         byte[] var9 = new byte[var8.length];
         CryptoByteArrayArithmetic.invert(this._r, var8, var9);
         byte[] var10 = net.rim.vm.Memory.allocRAMOnlyBytes(var5);
         EncryptionUtilities.calculateKey(var3, var7, var9, var10);
         Object var11 = new Object();
         this._passwordKey = net.rim.vm.Memory.allocRAMOnlyBytes(((Digest)var11).getDigestLength());
         ((Digest)var11).update(var10);
         ((Digest)var11).getDigest(this._passwordKey, 0);
         return true;
      } else {
         return false;
      }
   }

   private final String decryptPassword() {
      PersistentContent$ResetPasswordData var1 = PersistentContent$ResetPasswordData.getInstance();
      if (var1 == null) {
         return null;
      }

      byte[] var2 = var1.getPasswordCiphertext();
      byte[] var3 = net.rim.vm.Memory.allocRAMOnlyBytes(var2.length);
      int var4 = EncryptionUtilities.decrypt(this._passwordKey, var2, 0, var2.length, var3, 0);
      Object var5 = new Object(var3, 0, var4);
      net.rim.vm.Memory.setPlaintext(var5);
      return (String)var5;
   }

   final void clearK() {
      this._r = null;
      this._passwordKey = null;
   }

   final void unlock(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isContentProtectionSupported() {
      return EncryptionUtilities.isSupported(0);
   }

   final synchronized void setContentProtection(String var1, boolean var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void changePassword(String var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final synchronized void setContentCompression(boolean var1) {
      if (this._pendingCompress != var1) {
         this._numRecodes++;
      }

      this._pendingCompress = var1;
      this.saveContentCompressionSettings();
   }

   private final synchronized void setMode(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private final synchronized void setModeComplete(int var1) {
      if (var1 == this._modeGeneration) {
         this._numRecodes = 0;
         this._numPKRecodes = 0;
      }
   }

   final boolean doesEncryptionKeyExist() {
      if (this._devicePrivateKeys != null) {
         return true;
      }

      byte[] var1 = this.getNvStoreData();
      return var1 != null && var1.length > 2;
   }

   public static final boolean isEncryptionEnabled() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getEncryptionStrength() {
      return MathUtilities.clamp(0, Math.max(_instance._encryptStrength, ITPolicy.getInteger(24, 18, 0)), _instance._publicKeyLengths.length - 1);
   }

   public static final boolean isCompressionEnabled() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getModeGeneration() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getLockGeneration() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getState() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void addListener(PersistentContentListener var0) {
      addListener(var0, false);
   }

   public static final void addWeakListener(PersistentContentListener var0) {
      addListener(var0, true);
   }

   private static final void addListener(PersistentContentListener var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(PersistentContentListener var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void addListenerClassName(PersistentContentListener var1) {
      if (var1 instanceof Object) {
         String var2 = var1.getClass().getName();
         this._listenerClassNames.put(var2, var2);
         this._listenerClassNamesPersistentObject.commit();
      }
   }

   private final synchronized void findListeners() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void requestReEncode() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void markAsPlaintext(Object var0) {
      if (var0 != null) {
         _instance.setPlaintext(var0);
      }
   }

   private final synchronized void setPlaintext(Object var1) {
      if (this._encrypt || this._pendingEncrypt) {
         net.rim.vm.Memory.setPlaintext(var1);
         this.checkSecure();
      }
   }

   public static final Object copyEncoding(Object var0) {
      Object var1 = copy(var0);
      if (net.rim.vm.Memory.isPlaintext(var0)) {
         markAsPlaintext(var1);
      }

      return var1;
   }

   private static final Object copy(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object getTicket() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object waitForTicket() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object encodeObject(Object var0) {
      return encodeObject(var0, true, true);
   }

   public static final Object encodeObject(Object var0, boolean var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object encode(String var0) {
      return encodeAndAppend(var0, 0, -1, true, true, null);
   }

   public static final Object encode(String var0, boolean var1, boolean var2) {
      return encodeAndAppend(var0, 0, -1, var1, var2, null);
   }

   public static final Object encode(String var0, int var1, int var2, boolean var3, boolean var4) {
      return encodeAndAppend(var0, var1, var2, var3, var4, null);
   }

   public static final Object encodeAndAppend(String var0, Object var1) {
      return encodeAndAppend(var0, 0, -1, true, true, var1);
   }

   public static final Object encodeAndAppend(String var0, boolean var1, boolean var2, Object var3) {
      return encodeAndAppend(var0, 0, -1, var1, var2, var3);
   }

   public static final Object encodeAndAppend(String var0, int var1, int var2, boolean var3, boolean var4, Object var5) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final Object encode(byte[] var0) {
      return encodeAndAppend(var0, 0, -1, true, true, null);
   }

   public static final Object encode(byte[] var0, boolean var1, boolean var2) {
      return encodeAndAppend(var0, 0, -1, var1, var2, null);
   }

   public static final Object encode(byte[] var0, int var1, int var2, boolean var3, boolean var4) {
      return encodeAndAppend(var0, var1, var2, var3, var4, null);
   }

   public static final Object encodeAndAppend(byte[] var0, Object var1) {
      return encodeAndAppend(var0, 0, -1, true, true, var1);
   }

   public static final Object encodeAndAppend(byte[] var0, boolean var1, boolean var2, Object var3) {
      return encodeAndAppend(var0, 0, -1, var1, var2, var3);
   }

   public static final Object encodeAndAppend(byte[] var0, int var1, int var2, boolean var3, boolean var4, Object var5) {
      throw new RuntimeException("cod2jar: type check");
   }

   private static final char[] read(char[] var0) {
      return !_fileHook ? var0 : Array.readCharArrayFromFile(var0);
   }

   private static final char[] write(char[] var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final synchronized Object encode(Object var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7, boolean var8, char[] var9) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final char[] grow(char[] var1, int var2) {
      int var3 = var2 + 3 + 1 >> 1;
      if (var1 == null) {
         return new char[var3];
      }

      Array.resize(var1, var3);
      return var1;
   }

   private final synchronized int encodeBlocks(
      Object var1, int var2, int var3, boolean var4, boolean var5, byte[] var6, int var7, boolean var8, char[] var9, int var10
   ) {
      int var11 = var8 ? 8 : 0;
      byte[] var12 = null;
      if (var5) {
         if (var6 != null) {
            var11 |= 4;
         } else {
            int var13 = this._publicKeyLengths[var7];
            var12 = new byte[var13];
            byte[] var14 = new byte[this._privateKeyLengths[var7]];
            int var15 = this._eccCurves[var7];
            EncryptionUtilities.createKeyPair(var15, var12, var14);
            var6 = EncryptionUtilities.calculateKey(var15, (byte[])this._devicePublicKeys[var7], var14);
            net.rim.vm.Memory.setPlaintext(var14);
            net.rim.vm.Memory.setPlaintext(var6);
         }
      }

      do {
         int var22 = var3 > 4096 ? 4096 : var3;
         int var23 = var11;
         int var25 = var10;
         var10 += 2;
         int var16;
         int var17;
         if (!var5) {
            if (var4) {
               int var18 = CompressUtilities.compressBlock(var1, var2, var22, var9, var10, false);
               var23 |= var18 < var22 ? 1 : 0;
               var17 = var18;
               var16 = var22;
            } else {
               copyBytes(var1, var2, var9, var10, var22, false);
               var17 = var22;
               var16 = var22;
            }
         } else {
            if (var12 != null) {
               setBytes(var9, var10, var7, 1);
               int var26 = this._publicKeyLengths[var7];
               copyBytes(var12, 0, var9, ++var10, var26, false);
               var12 = null;
               var10 += var26;
               var23 |= 2;
            }

            if (var4) {
               char[] var27 = WeakReferenceUtilities.getCharArray(this._ceBufferWR, 2048);
               net.rim.vm.Memory.setPlaintext(var27);
               int var19 = CompressUtilities.compressBlock(var1, var2, var22, var27, 0, true);
               RandomSource.add(var27);
               var23 |= var19 < var22 ? 1 : 0;
               var17 = EncryptionUtilities.encrypt(var6, var27, 0, var19, var9, var10);
               var16 = var19;
            } else {
               var17 = EncryptionUtilities.encrypt(var6, var1, var2, var22, var9, var10);
               var16 = var22;
            }
         }

         var23 |= var16 << 4;
         setBytes(var9, var25, var23, 2);
         var2 += var22;
         var3 -= var22;
         var10 += var17;
      } while (var3 > 0);

      return var10;
   }

   public static final Object decode(Object var0) {
      return decode(var0, false);
   }

   public static final String decodeString(Object var0) {
      return (String)decode(var0, false);
   }

   public static final String decodeString(Object var0, boolean var1) {
      return (String)decode(var0, var1);
   }

   public static final byte[] decodeByteArray(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final byte[] decodeByteArray(Object var0, boolean var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object decode(Object var0, boolean var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   final synchronized Object decode(char[] var1, boolean var2, boolean var3) {
      Object var4 = this._objectCache.get(var1, var2);
      if (var4 != null) {
         return var4;
      }

      char[] var5 = var1;
      var1 = read(var1);
      int var6 = getMasterHeader(var1);
      boolean var7 = getFlag(var6, 1);
      boolean var8 = getFlag(var6, 2);
      boolean var9 = getFlag(var6, 4);
      boolean var10 = getFlag(var6, 8);
      if (var8 && this._devicePrivateKeys == null) {
         throw new Object();
      }

      int var11 = var6 >> 4;
      if (var2) {
         int var12 = getBytes(var1, 0, 2);
         var10 = getFlag(var12, 8);
         if (!var7 || !var8) {
            var11 = var12 >> 4;
            if (var11 == 0) {
               var11 = 4096;
            }
         } else if (var11 > 4096) {
            var11 = 4096;
         }
      }

      if (var10) {
         if (var3) {
            var4 = net.rim.vm.Memory.allocRAMOnlyBytes(var11);
            if (var4 == null) {
               throw new Object();
            }
         } else {
            var4 = new byte[var11];
         }
      } else {
         var4 = new char[var11 >> 1];
      }

      if (var8 != this._encrypt || var7 != this._compress) {
         this._numRecodes++;
      }

      this.decodeBlocks(var1, var7, var8, !var10, var4, var11, var2);
      if (var9) {
         CompressUtilities.convertToString(var4);
      }

      if (var8 && this._encrypt) {
         this.setPlaintext(var4);
      }

      if (!var3) {
         this._objectCache.put(var5, var2, var4);
      }

      return var4;
   }

   private final synchronized void decodeBlocks(char[] var1, boolean var2, boolean var3, boolean var4, Object var5, int var6, boolean var7) {
      int var8 = 0;
      int var9 = 0;
      byte[] var10 = null;

      do {
         int var11 = getBytes(var1, var8, 2);
         var8 += 2;
         boolean var12 = var4 && getFlag(var11, 8);
         int var13 = var11 >> 4;
         if (var13 == 0) {
            var13 = 4096;
         }

         int var14;
         int var15;
         if (!var3) {
            if (var2 && getFlag(var11, 1)) {
               var15 = var13;
               var14 = CompressUtilities.decompressBlock(var1, var8, var5, var9, var15, var12);
               if (var12) {
                  var15 <<= 1;
               }
            } else {
               var14 = var13;
               var15 = var13;
               copyBytes(var1, var8, var5, var9, var14, var12);
               if (var12) {
                  var15 <<= 1;
               }
            }
         } else {
            if (getFlag(var11, 2)) {
               int var16 = var8;
               var10 = this._symmetricKeyCache.get(var1, var16);
               if (var10 == null) {
                  this._numPKRecodes++;
                  int var17 = getBytes(var1, var8++, 1);
                  int var18 = this._publicKeyLengths[var17];
                  byte[] var19 = new byte[var18];
                  copyBytes(var1, var8, var19, 0, var18, false);
                  var8 += var18;
                  var10 = EncryptionUtilities.calculateKey(this._eccCurves[var17], var19, (byte[])this._devicePrivateKeys[var17]);
                  net.rim.vm.Memory.setPlaintext(var10);
                  this._symmetricKeyCache.put(var1, var16, var10);
               }
            } else if (getFlag(var11, 4)) {
               var10 = this._deviceSymmetricKey;
            }

            int var22 = var13;
            var14 = EncryptionUtilities.getCiphertextLength(var22);
            if (var2 && getFlag(var11, 1)) {
               char[] var23 = WeakReferenceUtilities.getCharArray(this._ceBufferWR, 2048);
               net.rim.vm.Memory.setPlaintext(var23);
               EncryptionUtilities.decrypt(var10, var1, var8, var14, var23, 0, false);
               var15 = CompressUtilities.decompressBlock(var23, 0, var5, var9, -1, var12);
            } else {
               var15 = EncryptionUtilities.decrypt(var10, var1, var8, var14, var5, var9, var12);
            }
         }

         var9 += var15;
         var8 += var14;
         if (var7) {
            Array.resize(var5, var4 ? var15 >> 1 : var15);
            return;
         }
      } while (var9 < var6);
   }

   private static final int createMasterHeader(boolean var0, boolean var1, boolean var2, boolean var3, int var4) {
      int var5 = var4 << 4;
      if (var0) {
         var5 |= 1;
      }

      if (var1) {
         var5 |= 2;
      }

      if (var2) {
         var5 |= 4;
      }

      if (var3) {
         var5 |= 8;
      }

      return var5;
   }

   private static final int getMasterHeader(char[] var0) {
      int var1 = var0.length << 1;
      int var2 = getBytes(var0, var1 - 4, 4);
      int var3;
      return (var2 & 0xFF) == 0 ? (var3 = var2 >>> 8) : var2 & 16777215;
   }

   private static final int getOutputByteOffset(char[] var0) {
      int var1 = var0.length << 1;
      if (getBytes(var0, var1 - 1, 1) == 0) {
         var1--;
      }

      return var1 - 3;
   }

   private static final boolean getFlag(int var0, int var1) {
      return (var0 & var1) != 0;
   }

   static final native void copyBytes(Object var0, int var1, Object var2, int var3, int var4, boolean var5);

   static final native void setBytes(Object var0, int var1, int var2, int var3);

   static final native int getBytes(Object var0, int var1, int var2);

   public static final boolean isCompressed(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isEncrypted(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isString(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean isByteArray(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final int getLength(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final boolean checkEncoding(Object var0) {
      return checkEncoding(var0, true, true);
   }

   public static final boolean checkEncoding(Object var0, boolean var1, boolean var2) {
      return _instance.checkEncoding2(var0, var1, var2);
   }

   private final synchronized boolean checkEncoding2(Object var1, boolean var2, boolean var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object reEncode(Object var0) {
      return reEncode(var0, true, true);
   }

   public static final Object reEncode(Object var0, boolean var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] convertEncodingToByteArray(Object var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final Object convertByteArrayToEncoding(byte[] var0) {
      if (var0 != null && var0.length != 0) {
         int var1 = var0.length - 1;
         byte var2 = var0[0];
         switch (var2) {
            case -1:
               throw new Object();
            case 0:
            case 1:
            default:
               byte[] var4 = new byte[var1];
               copyBytes(var0, 1, var4, 0, var1, false);
               if (var2 == 1) {
                  CompressUtilities.convertToString(var4);
               }

               return var4;
            case 2:
            case 3:
               if ((var1 & 1) == 1) {
                  throw new Object();
               } else {
                  char[] var3 = new char[var1 / 2];
                  copyBytes(var0, 1, var3, 0, var1, false);
                  if (var2 == 2) {
                     CompressUtilities.convertToString(var3);
                  }

                  return var3;
               }
         }
      } else {
         return null;
      }
   }
}
