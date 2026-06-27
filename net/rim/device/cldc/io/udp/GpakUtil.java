package net.rim.device.cldc.io.udp;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.EventLogger;

public final class GpakUtil {
   private static final int HEADER_SIZE;
   private static final int OFFSET_VERSION;
   private static final int OFFSET_TYPE;
   private static final int OFFSET_FIRST_INT;
   private static final int OFFSET_SECOND_INT;
   private static final byte VERSION_1;
   static final byte TYPE_DATA;
   static final byte TYPE_GCMP;
   private static final byte ERROR_NONE;
   private static final byte ERROR_RESERVED_1;
   private static final byte ERROR_RESERVED_2;
   private static final byte ERROR_RESERVED_NIA_PACKET_ERROR;
   private static final byte ERROR_RESERVED_NIA_PACKET_FAILED;
   private static final byte ERROR_RESERVED_NIA_NO_TX;
   private static final byte ERROR_VERSION;
   private static final int TYPE_MASK;
   private static final int ERROR_MASK;
   static final int EMPTY_VALUE;
   private static String STR;
   private static long GUID;
   private static final int RX_ERROR_ADDR_MATCH_1;
   private static final int RX_ERROR_VERSION;
   private static final int RX_ERROR_UNKNOWN;
   private static final int RX_VERSION_UNKNOWN;

   public static final int getHeaderSize() {
      return 10;
   }

   static final int encode(byte var0, byte[] var1, byte[] var2, int var3, int var4, int var5) {
      var1[0] = 16;
      var1[1] = var0;
      DatagramAddressBase.writeInt(var1, 2, DeviceInfo.getDeviceId());
      DatagramAddressBase.writeInt(var1, 6, var5);
      System.arraycopy(var2, var3, var1, 10, var4);
      return var4 + 10;
   }

   static final int decode(Datagram var0) {
      return decode(var0, null);
   }

   static final int decode(Datagram var0, UdpInternalAddress var1) {
      byte[] var2 = var0.getData();
      int var3 = var0.getOffset();
      int var4 = var0.getLength();
      int var5 = decodeGpak(var2, var3, var4, var1);
      switch (var5) {
         case 2:
         case 4:
            var0.setData(var2, var3 + 10, var4 - 10);
         default:
            return var5;
      }
   }

   static final int decode(byte[] var0) {
      return decodeGpak(var0, 0, var0.length, null);
   }

   private static final int decodeGpak(byte[] var0, int var1, int var2, UdpInternalAddress var3) {
      if (var2 >= 1) {
         switch (var0[var1 + 0]) {
            case 16:
               if (var2 >= 10) {
                  return decodeVersionOne(var0[var1 + 1] & 24, var0, var1, var3);
               }
         }
      }

      return 1;
   }

   private static final int decodeVersionOne(int var0, byte[] var1, int var2, UdpInternalAddress var3) {
      switch (var0) {
         case 8:
         case 16:
            if (DatagramAddressBase.readInt(var1, var2 + 6) == DeviceInfo.getDeviceId()) {
               if (checkError(var1[var2 + 1], (byte)16)) {
                  return 1;
               } else {
                  if (var0 == 8) {
                     if (var3 != null) {
                        var3.setGpakHostAddress(DatagramAddressBase.readInt(var1, var2 + 2));
                     }

                     return 2;
                  }

                  return 4;
               }
            } else {
               EventLogger.logEvent(GUID, 1382116657, 3);
            }
         default:
            return 1;
      }
   }

   private static final boolean checkError(int var0, byte var1) {
      switch (var0 & -32) {
         case -32:
            EventLogger.logEvent(GUID, 1383233138, 2);
            return true;
         case 0:
            return false;
         default:
            EventLogger.logEvent(GUID, 1383232878, 2);
            return true;
      }
   }
}
