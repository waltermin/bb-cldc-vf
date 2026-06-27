package net.rim.device.cldc.io.sms;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramBase;
import net.rim.device.api.io.SmsAddress;
import net.rim.device.api.system.SMSPacketHeader;
import net.rim.device.api.util.Arrays;

public final class SmsUtil {
   public static final byte IEID_CONCATENATEDSHORTMESSAGE;
   public static final byte IEID_SPECIALSMS_MESSAGE_WAITING_INDICATION;
   public static final byte IEID_RESERVED;
   public static final byte IEID_APPLICATION_PORT_ADDRESS_8BITADDRESS;
   public static final byte IEID_APPLICATION_PORT_ADDRESS_16BITADDRESS;
   public static final byte IEID_SMSC_CONTROL_PARAMS;
   public static final byte IEID_UDH_SOURCE_INDICATOR;
   public static final byte IEID_CONCATENATEDSHORTMESSAGE_16BIT;
   public static final byte IEID_WIRELESS_CONTROL_MESSAGE_PROTOCOL;
   public static final byte IEID_REPLY_ADDRESS;
   public static final int UDH_MSGWAITING_FLAG_STORE_MSG;
   public static final int UDH_MSGWAITING_TYPE_MASK;
   private static final int DATA_HEADER_SIZE;
   private static final int SCK_MIN_SIZE;
   private static final int GCMP_HEADER_SIZE;
   private static final int KODIAK_WV_HEADER_SIZE;
   public static String PROPERTY_USER_DATA_HEADER;
   public static String PROPERTY_REF_NUMBER;
   public static String PROPERTY_TOTAL_SEGMENTS;
   public static String PROPERTY_SEGMENT_NUMBER;

   public static final int encode(int var0, byte[] var1, byte[] var2, int var3, int var4) {
      int var5 = getHeaderSize(var0);
      if (var4 > var1.length - var5) {
         return -1;
      }

      switch (var0) {
         case 65536:
         default:
            var1[0] = 47;
            var1[1] = 47;
            var1[2] = 71;
            var1[3] = 67;
            var1[4] = 77;
            var1[5] = 80;
         case 65535:
            System.arraycopy(var2, var3, var1, var5, var4);
            return var4 + var5;
      }
   }

   public static final byte[] encodeUserDataHeader(byte var0, byte[] var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final DatagramBase encode(DatagramBase var0, byte var1, byte[] var2) {
      Object var3 = var0.getAddressBase();
      SMSPacketHeader var4 = ((SmsAddress)var3).getHeader();
      byte[] var5 = Arrays.copy(var0.getData());
      int var6 = 0;
      byte var7 = 0;
      int var8 = var5.length;
      if (!var4.isUserDataHeaderPresent()) {
         var4.setUserDataHeaderPresent(true);
      } else {
         var6 = var5[0];
         var7 = 1;
         var8 = var5.length - 1;
      }

      var0.setLength(0);
      byte[] var9 = encodeUserDataHeader(var1, var2);
      var6 += var9.length;
      var0.writeByte(var6);
      var0.write(var9);
      var0.write(var5, var7, var8);
      return var0;
   }

   public static final DatagramBase decode(Transport var0, SMSPacketHeader var1, byte[] var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int getHeaderSize(int var0) {
      switch (var0) {
         case 65535:
            return 0;
         case 65536:
         default:
            return 6;
      }
   }

   public static final void constructSegment(Datagram var0, SMSPacketHeader var1, byte[] var2, int var3, int var4, int var5, boolean var6) {
      byte var7;
      if (!var6) {
         var1.setUserDataHeaderPresent(true);
         var7 = 0;
      } else {
         var7 = var2[0];
      }

      int var8 = var1.getMessageCoding();
      int var9 = SMSPacketHeader.getBitsPerCharacter(var8) / SMSPacketHeader.getBytesPerCharacter(var8);
      int var10 = SMSPacketHeader.getBitsPerSegment(var8, var7) / var9;
      int var11 = var4 * var10;
      int var12 = Math.min(var2.length - var11, var10);
      var0.setLength(0);
      var7 += 5;
      var0.writeByte(var7);
      var0.write(0);
      var0.write(3);
      var0.write(var5);
      var0.write(var3);
      var0.write(var4 + 1);
      if (var4 == 0) {
         if (var6) {
            var0.write(var2, 1, var12 - 1);
         } else {
            var0.write(var2, 0, var12);
         }
      } else {
         if (var6) {
            var0.write(var2, 1, var2[0]);
         }

         var0.write(var2, var11, var12);
      }
   }

   public static final void constructSegmentCDMA(Datagram var0, SMSPacketHeader var1, byte[] var2, int var3, int var4) {
      int var5 = var1.getMessageCoding();
      int var6 = SMSPacketHeader.getBitsPerCharacter(var5) / SMSPacketHeader.getBytesPerCharacter(var5);
      int var7 = SMSPacketHeader.getBitsPerSegmentCDMA(var5, 3) / var6;
      int var8 = var4 * var7;
      int var9 = Math.min(var2.length - var8, var7);
      var0.setLength(0);
      var0.write(0);
      var0.write(var3);
      var0.write(var4);
      var0.write(var2, var8, var9);
   }

   public static final byte[] decodeWDPData(byte[] var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
