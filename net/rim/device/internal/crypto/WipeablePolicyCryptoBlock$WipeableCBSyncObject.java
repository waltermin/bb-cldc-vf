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

   WipeablePolicyCryptoBlock$WipeableCBSyncObject(int uid, byte[] keyData) {
      this._uid = uid;
      this._key = keyData;
   }

   WipeablePolicyCryptoBlock$WipeableCBSyncObject() {
      this._uid = UIDGenerator.getUID();
      this._key = WipeablePolicyCryptoBlock.getWLANKey();
   }

   public static final boolean convert(SyncObject object, DataBuffer buffer) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final SyncObject convert(DataBuffer data, int UID) {
      throw new RuntimeException("cod2jar: array creation");
   }
}
