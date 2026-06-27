package net.rim.device.internal.applicationcontrol;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.Persistable;

final class CachedStackTraceResponse implements Persistable {
   private byte[] _traceHash;
   private long _responseReceivedMask;
   private static final long FLAG;

   CachedStackTraceResponse(byte[] var1) {
      this._traceHash = var1;
      this._responseReceivedMask = Integer.MIN_VALUE;
   }

   final boolean equals(CachedStackTraceResponse var1) {
      return Arrays.equals(this._traceHash, var1._traceHash);
   }

   final boolean equals(byte[] var1) {
      return Arrays.equals(this._traceHash, var1);
   }

   final void setAllowed(int var1, int var2, boolean var3) {
      if (var3) {
         this._responseReceivedMask |= Long.MIN_VALUE >>> var1;
         this._responseReceivedMask &= Long.MIN_VALUE >>> var2 ^ -1;
      } else {
         this._responseReceivedMask &= Long.MIN_VALUE >>> var1 ^ -1;
         this._responseReceivedMask &= Long.MIN_VALUE >>> var2 ^ -1;
      }
   }

   final int isAllowed(int var1, int var2) {
      return ApplicationControlImpl.permissionMaskToTriState(this._responseReceivedMask, var1, var2);
   }

   final void reset(int var1, int var2) {
      this._responseReceivedMask |= Long.MIN_VALUE >>> var1;
      this._responseReceivedMask |= Long.MIN_VALUE >>> var2;
   }

   static final int responseToPermission(CachedStackTraceResponse var0, int var1, int var2) {
      return var0 != null ? var0.isAllowed(var1, var2) : 2;
   }
}
