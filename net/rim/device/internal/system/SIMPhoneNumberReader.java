package net.rim.device.internal.system;

import net.rim.device.api.system.SIMCard;

public final class SIMPhoneNumberReader implements SIMCardEfTask {
   SIMPhoneNumberReader$PhoneNumberList _list;
   int _recNum;
   int _efID;
   static final byte INTERNATIONAL_CONST;
   static final byte UNKNOWN_CONST;
   static StringBuffer _tempNumber;

   public SIMPhoneNumberReader(SIMPhoneNumberReader$PhoneNumberList var1, int var2, int var3) {
      if (var1 == null) {
         throw new Object();
      }

      this._list = var1;
      this._efID = var2;
      this._recNum = var3;
   }

   @Override
   public final void doWork(SIMCardEfHandler var1) {
      int var2 = var1.infoRequest(this._efID);
      if (var2 == 0) {
         int var3 = var1.getNumRecords();
         if (var3 > 0 && this._recNum <= var3) {
            int var4 = var1.getRecordLength();
            byte[] var5 = new byte[var4];
            int var6 = 1;
            if (this._recNum > 0) {
               var6 = this._recNum;
               var3 = this._recNum;
            }

            for (int var7 = var6; var7 <= var3; var7++) {
               var2 = var1.readRequest(var7, var5);
               if (var2 != 0) {
                  return;
               }

               this.readFromBuffer(var7, var4, var5);
            }
         }
      }
   }

   private final void readFromBuffer(int var1, int var2, byte[] var3) {
      String var4 = null;
      String var5 = null;
      if (var2 > 14) {
         var4 = SIMCard.decodeAlphaId(var3, 0, var2 - 14);
      }

      if (var4 != null) {
         var4 = var4.trim();
      }

      _tempNumber.setLength(0);
      int var6 = var3[var2 - 14];
      if (var6 > 1) {
         var6--;
         byte var7 = var3[var2 - 14 + 1];
         int var8 = var2 - 14 + 2;
         readBCDNumber(_tempNumber, var3, var8, var8 + var6);
         var5 = _tempNumber.toString();
         byte var9 = var3[var2 - 14 + 13];
         this._list.addPhoneNumber(var1, var4, var5, var9, var7);
      }
   }

   public static final void readBCDNumber(StringBuffer var0, byte[] var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         byte var5 = var1[var4];
         if (var5 == -1) {
            return;
         }

         int var6 = (var5 & 240) >> 4;
         int var7 = var5 & 15;
         handleDigit(var0, var7);
         handleDigit(var0, var6);
      }
   }

   private static final void handleDigit(StringBuffer var0, int var1) {
      if (var1 < 10) {
         char var2 = (char)(48 + var1);
         var0.append(var2);
      } else if (var1 == 10) {
         var0.append('*');
      } else if (var1 == 11) {
         var0.append('#');
      } else if (var1 == 12) {
         var0.append(',');
      } else {
         if (var1 == 13) {
            var0.append('?');
         }
      }
   }
}
