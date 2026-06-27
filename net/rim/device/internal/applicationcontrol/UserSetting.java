package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.synchronization.UIDGenerator;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;

final class UserSetting implements Persistable, SyncObject {
   private byte[] _hash;
   private long _permissions;
   private long _dontPrompt;
   private long _isSet;
   private int _uid;
   private static final long USER_PERMS_KEY;
   static final int VERSION;
   static final int HASH;
   static final int PERMS;
   static final int DONTPROMPT;
   static final int ISSET;
   static final int L_PERMS;
   static final int L_DONTPROMPT;
   static final int L_ISSET;

   final void setPermissions(UserSetting var1) {
      this.setPermissions(var1._permissions, var1._dontPrompt, var1._isSet);
   }

   final long getPermissions() {
      return this._permissions;
   }

   final void setPermissions(long var1) {
      this._permissions = var1;
   }

   @Override
   public final int getUID() {
      return this._uid;
   }

   final void setPermissions(long var1, long var3, long var5) {
      this._permissions = var1;
      this._dontPrompt = var3;
      this._isSet = var5;
   }

   final boolean hashEquals(byte[] var1) {
      return Arrays.equals(this._hash, var1);
   }

   final byte[] getHash() {
      return this._hash;
   }

   final long getIsSet() {
      return this._isSet;
   }

   final long getDontPrompt() {
      return this._dontPrompt;
   }

   final void resetPrompt(long var1) {
      this._permissions = this._permissions | this._dontPrompt & var1;
      this._dontPrompt &= var1 ^ -1;
   }

   final void resetPrompts() {
      this._permissions = this._permissions | this._dontPrompt;
      this._dontPrompt = 0;
   }

   UserSetting(byte[] var1, long var2) {
   }

   UserSetting(UserSetting var1) {
      this(var1._hash, var1._permissions, var1._dontPrompt, var1._isSet, var1._uid);
   }

   UserSetting(byte[] var1, long var2, long var4, long var6) {
      this._hash = new byte[var1.length];
      System.arraycopy(var1, 0, this._hash, 0, var1.length);
      this._permissions = var2;
      this._dontPrompt = var4;
      this._isSet = var6;
      this._uid = UIDGenerator.getUID();
   }

   UserSetting(byte[] var1, long var2, long var4, long var6, int var8) {
      this._hash = new byte[var1.length];
      System.arraycopy(var1, 0, this._hash, 0, var1.length);
      this._permissions = var2;
      this._dontPrompt = var4;
      this._isSet = var6;
      this._uid = var8;
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
