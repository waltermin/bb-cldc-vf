package net.rim.device.api.synchronization;

import net.rim.device.api.util.IntHashtable;

public final class SyncCollectionSchema {
   private int _recordTypeTag;
   private int _defaultRecordType;
   private IntHashtable _keyFieldIds = (IntHashtable)(new Object(1));

   public final int getRecordTypeTag() {
      return this._recordTypeTag;
   }

   public final void setRecordTypeTag(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getDefaultRecordType() {
      return this._defaultRecordType;
   }

   public final void setDefaultRecordType(int var1) {
      if (var1 == 0) {
         throw new Object();
      }

      this._defaultRecordType = var1;
   }

   public final int[] getRecordTypes() {
      int var1 = this._keyFieldIds.size();
      if (var1 <= 0) {
         return new int[0];
      }

      int[] var2 = new int[var1];
      this._keyFieldIds.keysToArray(var2);
      return var2;
   }

   public final int[] getKeyFieldIds(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setKeyFieldIds(int var1, int[] var2) {
      if (var1 == 0) {
         throw new Object();
      }

      if (var2 != null && var2.length != 0) {
         this._keyFieldIds.put(var1, var2);
      } else {
         this._keyFieldIds.remove(var1);
      }
   }
}
