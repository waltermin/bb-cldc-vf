package net.rim.device.internal.io.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
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

   public static final EncryptedFile readHeader(int var0, DataInputStream var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final EncryptedFile createFile(int var0, DataOutputStream var1, boolean var2, byte[] var3, CodeSigningKey var4) {
      var1.write(82);
      var1.write(69);
      var1.write(77);
      var1.write(70);
      var1.write(1);
      Object var5 = new Object();
      Object var6 = new Object((OutputStream)var5);
      ((DataOutputStream)var6).write(0);
      ((DataOutputStream)var6).write(0);
      if (var4 == null) {
         ((DataOutputStream)var6).writeInt(0);
      } else {
         int var7 = var4.getSignerIdAsInt();
         ((DataOutputStream)var6).writeInt(var7);
         switch (var7) {
            case 51:
            case 4342354:
            case 4408146:
            case 4801362:
            case 5391186:
            case 5526098:
               ((DataOutputStream)var6).writeShort(0);
               break;
            default:
               byte[] var8 = var4.getPublicKey();
               ((DataOutputStream)var6).writeShort(var8.length);
               ((DataOutputStream)var6).write(var8);
         }
      }

      byte[] var11 = new byte[32];
      RandomSource.getBytes(var11);
      ((DataOutputStream)var6).writeShort(var11.length);
      Object var12 = new Object();
      if (!var2) {
         ((DataOutputStream)var6).write(1);
         ((DataOutputStream)var6).write(var11);
      } else {
         ((DataOutputStream)var6).write(2);
         ((SHA256Digest)var12).update(var11);
         ((DataOutputStream)var6).write(((SHA256Digest)var12).getDigest());
         ((SHA256Digest)var12).reset();
         byte[] var9 = new byte[EncryptionUtilities.getCiphertextLength(var11.length)];
         ((DataOutputStream)var6).writeShort(var9.length);
         byte[] var10 = DRMServices.getSubscriberKey();
         if (var10 != null) {
            EncryptionUtilities.encrypt(var10, var11, 0, var11.length, var9, 0);
         }

         ((DataOutputStream)var6).write(var9);
         EncryptionUtilities.encrypt(DRMServices.getDeviceKey(), var11, 0, var11.length, var9, 0);
         ((DataOutputStream)var6).write(var9);
      }

      ((SHA256Digest)var12).update(((NoCopyByteArrayOutputStream)var5).getByteArray(), 0, ((NoCopyByteArrayOutputStream)var5).size());
      ((DataOutputStream)var6).write(((SHA256Digest)var12).getDigest());
      byte[] var13 = new byte[EncryptionUtilities.getCiphertextLength(((NoCopyByteArrayOutputStream)var5).size())];
      EncryptionUtilities.encrypt(var3, ((NoCopyByteArrayOutputStream)var5).getByteArray(), 0, ((NoCopyByteArrayOutputStream)var5).size(), var13, 0);
      var1.writeShort(var13.length);
      var1.write(var13);
      EncryptedFile var14 = new EncryptedFile();
      var14._protectionKey = var4;
      var14._handle = var0;
      var14.init(var11);
      var14._headerLength = (int)FileSystem.tell(var0);
      return var14;
   }

   public final long getFileSize() {
      Object var1 = new Object();
      int var2 = FileSystem.getFileInfo(this._handle, (FileInfo)var1);
      if (var2 != 0) {
         throw new Object(var2);
      } else {
         long var3 = (((FileInfo)var1).getFileSize() - FileSystem.tell(this._handle)) / 16 * 16;
         var2 = FileSystem.seek(this._handle, var3, 1);
         if (var2 != 0) {
            throw new Object(var2);
         } else {
            byte[] var5 = new byte[1];
            long var6 = FileSystem.read(this._handle, var5);
            if ((int)var6 != 0) {
               throw new Object((int)var6);
            } else {
               int var8 = (int)(var6 >> 32);
               if (var8 != 1) {
                  return 0;
               } else if (var5[0] > 0 && var5[0] <= 16) {
                  return var3 - (16 - var5[0]);
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
