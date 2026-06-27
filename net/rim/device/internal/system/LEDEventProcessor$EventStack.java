package net.rim.device.internal.system;

import net.rim.vm.Array;

final class LEDEventProcessor$EventStack {
   private long[] _sourceIds = new long[10];
   private long[] _expirationDates = new long[10];
   private int[] _groupInfos = new int[10];
   private int _count;

   public final void pushEvent(long var1, boolean var3, int var4) {
      if (this._count == this._sourceIds.length) {
         int var5 = this._sourceIds.length + 10;
         Array.resize(this._sourceIds, var5);
         Array.resize(this._expirationDates, var5);
         Array.resize(this._groupInfos, var5);
      }

      this._sourceIds[this._count] = var1;
      this._expirationDates[this._count] = System.currentTimeMillis() + 900000;
      this._groupInfos[this._count] = var4;
      this._count++;
   }

   public final void expireEvents(long var1, int var3) {
      if (var3 != -1) {
         for (int var4 = 0; var4 < this._count; var4++) {
            if (this._groupInfos[var4] == var3) {
               this._expirationDates[var4] = 0;
            }
         }
      } else {
         for (int var5 = 0; var5 < this._count; var5++) {
            if (this._sourceIds[var5] == var1) {
               this._expirationDates[var5] = 0;
            }
         }
      }
   }

   public final boolean contains(long var1) {
      for (int var3 = 0; var3 < this._count; var3++) {
         if (this._sourceIds[var3] == var1) {
            return true;
         }
      }

      return false;
   }

   public final void popEvent() {
      this._count--;
   }

   public final void peekEvent(LEDEventProcessor$EventHolder var1) {
      if (this._count == 0) {
         throw new Object();
      }

      int var2 = this._count - 1;
      var1.fill(this._sourceIds[var2], this._expirationDates[var2]);
   }

   public final boolean isEmpty() {
      return this._count == 0;
   }
}
