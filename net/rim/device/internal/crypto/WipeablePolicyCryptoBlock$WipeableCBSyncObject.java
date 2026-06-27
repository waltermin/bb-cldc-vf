package net.rim.device.internal.crypto;

import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.synchronization.UIDGenerator;
import net.rim.device.api.util.DataBuffer;

public final class WipeablePolicyCryptoBlock$WipeableCBSyncObject implements SyncObject {
   int _uid;
   byte[] _key;

   public final byte[] getKeyData() {
      return this._key;
   }

   @Override
   public final int getUID() {
      return this._uid;
   }

   WipeablePolicyCryptoBlock$WipeableCBSyncObject(int var1, byte[] var2) {
      this._uid = var1;
      this._key = var2;
   }

   WipeablePolicyCryptoBlock$WipeableCBSyncObject() {
      this._uid = UIDGenerator.getUID();
      this._key = WipeablePolicyCryptoBlock.getWLANKey();
   }

   public static final boolean convert(SyncObject var0, DataBuffer var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final SyncObject convert(DataBuffer var0, int var1) {
      throw new RuntimeException("cod2jar: array creation");
   }
}
