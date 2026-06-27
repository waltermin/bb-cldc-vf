package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.util.Persistable;
import net.rim.device.internal.system.CodeStore;
import net.rim.device.internal.system.ITPolicyInternal;

final class SystemPermissions implements Persistable {
   private long _defaultPermissions;
   private long _permittedPermissions;
   private boolean _AppControlPolicyDataPresent;
   private boolean _disableServiceBookXmit;
   private int _crc;
   private int[] _handles;
   private int _check = 0;

   final long getDefaultPermissions() {
      return this._defaultPermissions;
   }

   final long getPermittedPermissions() {
      return this._permittedPermissions;
   }

   final int[] getHandles() {
      return this._handles;
   }

   final boolean areValid(int var1) {
      return this._crc == var1;
   }

   final void invalidate() {
      this._crc++;
   }

   final boolean isAppControlPolicyDataPresent() {
      return this._AppControlPolicyDataPresent;
   }

   final void disableXmit() {
      this._disableServiceBookXmit = true;
   }

   final boolean isXmitDisabled() {
      return this._disableServiceBookXmit;
   }

   final int getCheck() {
      return this._check;
   }

   final void init() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final int[] loadHandles() {
      int[] var1 = this._handles;
      this._handles = new int[0];
      this._crc = CodeStore.getModuleHandles(this._handles);
      return var1;
   }

   private final long getDefaults() {
      byte[] var1;
      if (ITPolicyInternal.isITPolicyEnabled()) {
         var1 = getDefaultPermissionsImpl();
      } else {
         var1 = getCorporateDefaults();
      }

      return this.permsIntoMask(var1);
   }

   private final long permsIntoMask(byte[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final native byte[] getDefaultPermissionsImpl();

   private static final native byte[] getConsumerDefaults();

   private static final native byte[] getCorporateDefaults();
}
