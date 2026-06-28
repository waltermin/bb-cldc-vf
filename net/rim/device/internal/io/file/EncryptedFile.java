package net.rim.device.internal.io.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.crypto.SHA256Digest;
import net.rim.device.api.io.FileInfo;
import net.rim.device.api.io.NoCopyByteArrayOutputStream;
import net.rim.device.api.system.CodeSigningKey;
import net.rim.device.internal.crypto.EncryptionUtilities;
import net.rim.device.internal.system.DRMServices;

public final class EncryptedFile {
   private byte[] _context;
   private int _handle;
   private CodeSigningKey _protectionKey;
   private int _headerLength;
   private FileInfo _fileInfo = (FileInfo)(new Object());
   private boolean _drmForwardLocked;
   public static final int BLOCK_LENGTH;
   private static final int SIGNATURE_LENGTH;

   private EncryptedFile() {
   }

   public static final EncryptedFile readHeader(int handle, DataInputStream in, byte[] masterKey) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final EncryptedFile createFile(int handle, DataOutputStream out, boolean isDRMProtected, byte[] key, CodeSigningKey protectionKey) {
      out.write(82);
      out.write(69);
      out.write(77);
      out.write(70);
      out.write(1);
      NoCopyByteArrayOutputStream bytesOut = (NoCopyByteArrayOutputStream)(new Object());
      DataOutputStream dataOut = (DataOutputStream)(new Object(bytesOut));
      dataOut.write(0);
      dataOut.write(0);
      if (protectionKey == null) {
         dataOut.writeInt(0);
      } else {
         int signerId = protectionKey.getSignerIdAsInt();
         dataOut.writeInt(signerId);
         switch (signerId) {
            case 51:
            case 4342354:
            case 4408146:
            case 4801362:
            case 5391186:
            case 5526098:
               dataOut.writeShort(0);
               break;
            default:
               byte[] keyData = protectionKey.getPublicKey();
               dataOut.writeShort(keyData.length);
               dataOut.write(keyData);
         }
      }

      byte[] sessionKey = new byte[32];
      RandomSource.getBytes(sessionKey);
      dataOut.writeShort(sessionKey.length);
      SHA256Digest digest = (SHA256Digest)(new Object());
      if (!isDRMProtected) {
         dataOut.write(1);
         dataOut.write(sessionKey);
      } else {
         dataOut.write(2);
         digest.update(sessionKey);
         dataOut.write(digest.getDigest());
         digest.reset();
         byte[] encryptedKey = new byte[EncryptionUtilities.getCiphertextLength(sessionKey.length)];
         dataOut.writeShort(encryptedKey.length);
         byte[] subKey = DRMServices.getSubscriberKey();
         if (subKey != null) {
            EncryptionUtilities.encrypt(subKey, sessionKey, 0, sessionKey.length, encryptedKey, 0);
         }

         dataOut.write(encryptedKey);
         EncryptionUtilities.encrypt(DRMServices.getDeviceKey(), sessionKey, 0, sessionKey.length, encryptedKey, 0);
         dataOut.write(encryptedKey);
      }

      digest.update(bytesOut.getByteArray(), 0, bytesOut.size());
      dataOut.write(digest.getDigest());
      byte[] cipher = new byte[EncryptionUtilities.getCiphertextLength(bytesOut.size())];
      EncryptionUtilities.encrypt(key, bytesOut.getByteArray(), 0, bytesOut.size(), cipher, 0);
      out.writeShort(cipher.length);
      out.write(cipher);
      EncryptedFile ef = new EncryptedFile();
      ef._protectionKey = protectionKey;
      ef._handle = handle;
      ef.init(sessionKey);
      ef._headerLength = (int)FileSystem.tell(handle);
      return ef;
   }

   public final long getFileSize() {
      FileInfo info = (FileInfo)(new Object());
      int status = FileSystem.getFileInfo(this._handle, info);
      if (status != 0) {
         throw new Object(status);
      } else {
         long bytesToSkip = (info.getFileSize() - FileSystem.tell(this._handle)) / 16 * 16;
         status = FileSystem.seek(this._handle, bytesToSkip, 1);
         if (status != 0) {
            throw new Object(status);
         } else {
            byte[] lastByte = new byte[1];
            long readStatus = FileSystem.read(this._handle, lastByte);
            if ((int)readStatus != 0) {
               throw new Object((int)readStatus);
            } else {
               int size = (int)(readStatus >> 32);
               if (size != 1) {
                  return 0;
               } else if (lastByte[0] > 0 && lastByte[0] <= 16) {
                  return bytesToSkip - (16 - lastByte[0]);
               } else {
                  throw new Object(6);
               }
            }
         }
      }
   }

   public final int getHeaderLength() {
      return this._headerLength;
   }

   public final CodeSigningKey getCodeSigningKey() {
      return this._protectionKey;
   }

   public final boolean isDrmForwardLocked() {
      return this._drmForwardLocked;
   }

   private final native void init(byte[] var1);

   public final native long readBlocks(byte[] var1);

   public final native int writeBlocks(byte[] var1, int var2, int var3);

   public final native int truncate(long var1);
}
