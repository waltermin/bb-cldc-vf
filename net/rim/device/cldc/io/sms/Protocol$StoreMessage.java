package net.rim.device.cldc.io.sms;

import javax.wireless.messaging.Message;
import net.rim.device.api.io.DatagramBase;

final class Protocol$StoreMessage {
   private byte[][][] _segments;
   private int _count;
   private String _key;

   public Protocol$StoreMessage(int var1, String var2) {
   }

   public final Message add(DatagramBase var1) {
      int var2 = 1;
      Object var3 = var1.getProperty(SmsUtil.PROPERTY_SEGMENT_NUMBER);
      if (var3 != null) {
         var2 = var3;
         this._segments[var2 - 1] = (byte[][])var1.getData();
         this._count++;

         for (int var4 = 0; var4 < this._segments.length; var4++) {
            if (this._segments[var4] == null) {
               return null;
            }
         }

         return this.getMessage(var1);
      } else {
         return null;
      }
   }

   public final String getKey() {
      return this._key;
   }

   private final Message getMessage(DatagramBase var1) {
      int var3 = 0;

      for (int var4 = 0; var4 < this._segments.length; var4++) {
         var3 += this._segments[var4].length;
      }

      byte[] var2 = new byte[var3];
      int var7 = 0;

      for (int var5 = 0; var5 < this._segments.length; var5++) {
         byte[][] var6 = this._segments[var5];
         System.arraycopy(var6, 0, var2, var7, var6.length);
         var7 += var6.length;
      }

      var1.setData(var2, 0, var2.length);
      return Protocol.makeMessage(var1);
   }
}
