package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.util.Arrays;

final class ApplicationControlImpl$CachedPermissions {
   int[] _trace;
   long _perms;
   int _check;

   ApplicationControlImpl$CachedPermissions(int[] var1) {
      this._trace = var1;
   }

   final void setCachedPerms(long var1, int var3) {
      this._perms = var1;
      this._check = var3;
   }

   final boolean equals(ApplicationControlImpl$CachedPermissions var1) {
      return Arrays.equals(this._trace, var1._trace);
   }

   final boolean equals(int[] var1) {
      return Arrays.equals(this._trace, var1);
   }
}
