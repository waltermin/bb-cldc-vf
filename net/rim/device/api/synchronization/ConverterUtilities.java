package net.rim.device.api.synchronization;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.internal.i18n.UnicodeServiceUtilities;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

public final class ConverterUtilities {
   public static final int CONSUMED_FIELD;
   private static final int STRING_CVT_NEWLINES;
   private static final int STRING_CHK_COMPATIBILITY;
   private static final int TEMP_BUFF_SIZE;
   private static WeakReference _tempBytesWR;
   private static WeakReference _tempCharsWR;
   private static Object _calendarInitializer;
   private static Calendar _gmtCal;
   private static Calendar _localCal;
   private static String _currentSerializationEncodingName;
   private static byte _currentSerializationEncodingByte;

   private ConverterUtilities() {
   }

   private static final void initializeCalendars() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final synchronized byte[] getSupportedSerializationEncodings() {
      return UnicodeServiceUtilities.getSupportedEncodings();
   }

   public static final synchronized boolean indicatePossibleSerializationEncodings(byte[] var0, byte[] var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getConversionCurrentEncodingName() {
      return _currentSerializationEncodingName;
   }

   public static final byte getConversionCurrentEncodingByte() {
      return _currentSerializationEncodingByte;
   }

   private static final byte markConsumed(DataBuffer var0, boolean var1) {
      byte var2 = var0.getArray()[var0.getArrayPosition()];
      if (var1) {
         var0.writeByte(255);
         return var2;
      } else {
         var0.skipBytes(1);
         return var2;
      }
   }

   public static final String readString(DataBuffer var0) {
      return readString(var0, false);
   }

   public static final String readString(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String readStringEncoded(byte[] var0, int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int detectFutureData(byte[] var0, int var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean isIntellisyncCompatible(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void writeString(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringEncoded(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void writeStringIntellisync(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringDefault(DataBuffer var0, String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringSmart(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final native boolean convertForDesktop(String var0, int var1, int var2, byte[] var3, int var4, int var5);

   public static final void writeByteArray(DataBuffer var0, int var1, byte[] var2) {
      int var3 = var2.length;
      if (var3 > 65535) {
         throw new Object();
      }

      var0.ensureCapacity(var3 + 3);
      var0.writeShort(var3);
      var0.writeByte(var1);
      var0.write(var2);
   }

   public static final void writeByteStream(DataBuffer var0, int var1, InputStream var2, long var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void writeCharArrayArray(DataBuffer var0, int var1, char[][][] var2) {
      int var3 = var2.length;
      int var4 = 0;

      for (int var5 = 0; var5 < var3; var5++) {
         char[][] var6 = var2[var5];
         if (var6 != null) {
            var4 += var6.length;
         }
      }

      var0.ensureCapacity(var4 * 2 + var3 * 2 + 2 + 3);
      var0.writeShort(var4 * 2 + var3 * 2 + 2);
      var0.writeByte(var1);
      var0.writeShort(var3);

      for (int var9 = 0; var9 < var3; var9++) {
         char[][] var10 = var2[var9];
         if (var10 == null) {
            var0.writeShort(0);
         } else {
            int var7 = var10.length;
            var0.writeShort(var7);

            for (int var8 = 0; var8 < var7; var8++) {
               var0.writeChar((int)var10[var8]);
            }
         }
      }
   }

   public static final void writeInt(DataBuffer var0, int var1, int var2) {
      var0.ensureCapacity(7);
      var0.writeShort(4);
      var0.writeByte(var1);
      var0.writeInt(var2);
   }

   public static final void writeIntArray(DataBuffer var0, int var1, int[] var2) {
      int var3 = var2.length;
      var0.ensureCapacity(var3 * 4 + 3);
      var0.writeShort(var3 * 4);
      var0.writeByte(var1);

      for (int var4 = 0; var4 < var3; var4++) {
         var0.writeInt(var2[var4]);
      }
   }

   public static final void writeIntArrayArray(DataBuffer var0, int var1, int[][][] var2) {
      int var3 = var2.length;
      int var4 = 0;

      for (int var5 = 0; var5 < var3; var5++) {
         int[][] var6 = var2[var5];
         if (var6 != null) {
            var4 += var2[var5].length;
         }
      }

      var0.ensureCapacity(var4 * 4 + var3 * 2 + 2 + 3);
      var0.writeShort(var4 * 4 + var3 * 2 + 2);
      var0.writeByte(var1);
      var0.writeShort(var3);

      for (int var9 = 0; var9 < var3; var9++) {
         int[][] var10 = var2[var9];
         if (var10 == null) {
            var0.writeShort(0);
         } else {
            int var7 = var10.length;
            var0.writeShort(var7);

            for (int var8 = 0; var8 < var7; var8++) {
               var0.writeInt((int)var10[var8]);
            }
         }
      }
   }

   public static final void writeShort(DataBuffer var0, int var1, short var2) {
      var0.ensureCapacity(5);
      var0.writeShort(2);
      var0.writeByte(var1);
      var0.writeShort(var2);
   }

   public static final void writeShortArray(DataBuffer var0, int var1, short[] var2) {
      int var3 = var2.length;
      var0.ensureCapacity(var3 * 2 + 3);
      var0.writeShort(var3 * 2);
      var0.writeByte(var1);

      for (int var4 = 0; var4 < var3; var4++) {
         var0.writeShort(var2[var4]);
      }
   }

   public static final void convertBinary(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void convertInt(DataBuffer var0, int var1, int var2, int var3) {
      var0.ensureCapacity(var3 + 3);
      switch (var3) {
         case 0:
            var0.writeShort(4);
            var0.writeByte(var1);
            var0.writeInt(var2);
            return;
         case 1:
         default:
            var0.writeShort(1);
            var0.writeByte(var1);
            var0.writeByte((byte)var2);
            return;
         case 2:
            var0.writeShort(2);
            var0.writeByte(var1);
            var0.writeShort((short)var2);
      }
   }

   public static final void writeLong(DataBuffer var0, int var1, long var2) {
      var0.ensureCapacity(11);
      var0.writeShort(8);
      var0.writeByte(var1);
      var0.writeLong(var2);
   }

   public static final void writeLongArray(DataBuffer var0, int var1, long[] var2) {
      int var3 = var2.length;
      var0.ensureCapacity(var3 * 8 + 3);
      var0.writeShort(var3 * 8);
      var0.writeByte(var1);

      for (int var4 = 0; var4 < var3; var4++) {
         var0.writeLong(var2[var4]);
      }
   }

   public static final void writeEmptyField(DataBuffer var0, int var1) {
      var0.ensureCapacity(3);
      var0.writeShort(0);
      var0.writeByte(var1);
   }

   public static final int getType(DataBuffer var0) {
      return getType(var0, false);
   }

   public static final int getType(DataBuffer var0, boolean var1) {
      if (var0.available() < 3) {
         throw new Object();
      }

      byte[] var2 = var0.getArray();
      int var3 = var0.getArrayPosition();
      int var4 = var2[var3 + 2] & 255;
      if (var1 && (var4 & 128) != 0 && (var4 & 240) != 240) {
         var4 &= -129;
      }

      return var4;
   }

   public static final void skipField(DataBuffer var0) {
      int var1 = var0.readUnsignedShort();
      if (var0.available() < var1 + 1) {
         throw new Object();
      }

      var0.skipBytes(var1 + 1);
   }

   public static final boolean isType(DataBuffer var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final String getBinaryString(DataBuffer var0) {
      return getBinaryString(var0, false);
   }

   public static final String getBinaryString(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] readByteArray(DataBuffer var0) {
      return readByteArray(var0, false);
   }

   public static final byte[] readByteArray(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void readByteStream(DataBuffer var0, boolean var1, OutputStream var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final short[] readShortArray(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int[] readIntArray(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final int[][][] readIntArrayArray(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long[] readLongArray(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final char[][][] readCharArrayArray(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean findType(DataBuffer var0, int var1) {
      return findType(var0, var1, false);
   }

   public static final boolean findType(DataBuffer var0, int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final short readShort(DataBuffer var0) {
      return (short)readLong(var0, false);
   }

   public static final short readShort(DataBuffer var0, boolean var1) {
      return (short)readLong(var0, var1);
   }

   public static final int readInt(DataBuffer var0) {
      return (int)readLong(var0, false);
   }

   public static final int readInt(DataBuffer var0, boolean var1) {
      return (int)readLong(var0, var1);
   }

   public static final long readLong(DataBuffer var0) {
      return readLong(var0, false);
   }

   public static final long readLong(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long getDateGMT(DataBuffer var0) {
      return getDateGMT(var0, false);
   }

   public static final long getDateGMT(DataBuffer var0, boolean var1) {
      return getDateTime(var0, var1, true, true);
   }

   public static final long getDateTime(DataBuffer var0) {
      return getDateTime(var0, false);
   }

   public static final long getDateTime(DataBuffer var0, boolean var1) {
      return getDateTime(var0, var1, false, false);
   }

   private static final long getDateTime(DataBuffer var0, boolean var1, boolean var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void writeNetworkMessageDate(long var0, byte[] var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final long readNetworkMessageDate(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean extractCBool(byte[] var0, int var1) {
      return var0[var1] != 0;
   }

   private static final long doExtractIntegral(byte[] var0, int var1, boolean var2, int var3) {
      int var4 = var3 - 1;
      byte var5 = -1;
      if (var2) {
         var4 = 0;
         var5 = 1;
      }

      long var6 = 0;

      for (int var8 = 0; var8 < var3; var8++) {
         var6 <<= 8;
         var6 |= var0[var1 + var4] & 0xFF;
         var4 += var5;
      }

      return var6;
   }

   public static final byte extractCByte(byte[] var0, int var1) {
      return (byte)doExtractIntegral(var0, var1, false, 1);
   }

   public static final short extractCShort(byte[] var0, int var1, boolean var2) {
      return (short)doExtractIntegral(var0, var1, var2, 2);
   }

   public static final int extractCInt(byte[] var0, int var1, boolean var2) {
      return (int)doExtractIntegral(var0, var1, var2, 4);
   }

   public static final long extractCLong(byte[] var0, int var1, boolean var2) {
      return doExtractIntegral(var0, var1, var2, 8);
   }

   public static final void injectCBool(byte[] var0, int var1, boolean var2) {
      var0[var1] = (byte)(var2 ? 1 : 0);
   }

   private static final void doInjectIntegral(byte[] var0, int var1, boolean var2, int var3, long var4) {
      int var6 = 0;
      byte var7 = 1;
      if (var2) {
         var6 = var3 - 1;
         var7 = -1;
      }

      for (int var8 = 0; var8 < var3; var8++) {
         var0[var1 + var6] = (byte)(var4 & 255);
         var4 >>>= 8;
         var6 += var7;
      }
   }

   public static final void injectCByte(byte[] var0, int var1, byte var2) {
      doInjectIntegral(var0, var1, false, 1, var2);
   }

   public static final void injectCShort(byte[] var0, int var1, boolean var2, short var3) {
      doInjectIntegral(var0, var1, var2, 2, var3);
   }

   public static final void injectCInt(byte[] var0, int var1, boolean var2, int var3) {
      doInjectIntegral(var0, var1, var2, 4, var3);
   }

   public static final void injectCLong(byte[] var0, int var1, boolean var2, long var3) {
      doInjectIntegral(var0, var1, var2, 8, var3);
   }

   public static final int getFieldIndex(byte[] var0, byte var1) {
      return getFieldIndex(var0, var1, false);
   }

   public static final int getFieldIndex(byte[] var0, byte var1, boolean var2) {
      if (var0 != null) {
         int var3 = 2;

         while (var3 > 1 && var3 < var0.length && (var2 && (var0[var3] & 128) != 0 && (var0[var3] & 240) != 240 ? var0[var3] & 127 : var0[var3]) != var1) {
            var3 += getFieldLength(var0, var3 - 2) + 3;
         }

         if (var3 > 1 && var3 < var0.length) {
            return var3 - 2;
         }
      }

      return -1;
   }

   public static final int getFieldLength(byte[] var0, int var1) {
      return extractCShort(var0, var1, false) & 65535;
   }

   public static final byte[] removeTag(byte[] var0, byte var1) {
      return removeTag(var0, var1, false);
   }

   public static final byte[] removeTag(byte[] var0, byte var1, boolean var2) {
      int var3 = getFieldIndex(var0, var1, var2);
      if (var3 != -1) {
         int var4 = getFieldLength(var0, var3);
         System.arraycopy(var0, var3 + 3 + var4, var0, var3, var0.length - var3 - 3 - var4);
         Array.resize(var0, var0.length - 3 - var4);
         if (var0.length == 0) {
            var0 = null;
         }
      }

      return var0;
   }

   private static final String checkForEmptyString(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] addTag(byte[] var0, byte var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final byte[] addByteTag(byte[] var0, byte var1, byte var2) {
      if (var2 == -1) {
         throw new Object();
      }

      var0 = removeTag(var0, var1, false);
      if (var0 == null) {
         var0 = new byte[0];
      }

      int var3 = var0.length;
      Array.resize(var0, var3 + 4);
      injectCShort(var0, var3, false, (short)1);
      var0[var3 + 2] = var1;
      var0[var3 + 3] = var2;
      return var0;
   }

   public static final String getTagData(byte[] var0, byte var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte getTagByteData(byte[] var0, byte var1) {
      int var2 = getFieldIndex(var0, var1, false);
      return var2 != -1 && getFieldLength(var0, var2) == 1 ? var0[var2 + 3] : -1;
   }
}
