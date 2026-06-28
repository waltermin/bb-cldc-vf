package net.rim.device.api.synchronization;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.TimeZone;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.DateTimeUtilities;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.cldc.util.CalendarExtensions;
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
      synchronized (_calendarInitializer) {
         if (_gmtCal == null) {
            _gmtCal = Calendar.getInstance(TimeZone.getTimeZone(DateTimeUtilities.GMT));
            _gmtCal.set(11, 0);
            _gmtCal.set(12, 0);
            _gmtCal.set(13, 0);
            _gmtCal.set(14, 0);
            _localCal = Calendar.getInstance();
         }
      }
   }

   public static final synchronized byte[] getSupportedSerializationEncodings() {
      return UnicodeServiceUtilities.getSupportedEncodings();
   }

   public static final synchronized boolean indicatePossibleSerializationEncodings(byte[] clientServiceEncodings, byte[] hostServiceEncodings) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String getConversionCurrentEncodingName() {
      return _currentSerializationEncodingName;
   }

   public static final byte getConversionCurrentEncodingByte() {
      return _currentSerializationEncodingByte;
   }

   private static final byte markConsumed(DataBuffer buffer, boolean markConsumed) {
      byte tag = buffer.getArray()[buffer.getArrayPosition()];
      if (markConsumed) {
         buffer.writeByte(255);
         return tag;
      } else {
         buffer.skipBytes(1);
         return tag;
      }
   }

   public static final String readString(DataBuffer buffer) {
      return readString(buffer, false);
   }

   public static final String readString(DataBuffer buffer, boolean markConsumed) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         byte type = markConsumed(buffer, markConsumed);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         String returnedString;
         if ((type & 128) != 0 && (type & 240) != 240) {
            if (len < 2) {
               throw new EOFException();
            }

            returnedString = readStringEncoded(buffer.getArray(), buffer.getArrayPosition(), len, buffer.isBigEndian());
         } else {
            returnedString = StringUtilities.decodeBOM(buffer.getArray(), buffer.getArrayPosition(), len, true);
         }

         buffer.skipBytes(len);
         return returnedString;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final String readStringEncoded(byte[] dataBuffer, int readPosition, int lengthToRead, boolean isBigEndian) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final int detectFutureData(byte[] dataBuffer, int readPosition, int lengthToRead, boolean bigEndian) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean isIntellisyncCompatible(String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void writeString(DataBuffer buffer, int type, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringEncoded(DataBuffer buffer, int type, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void writeStringIntellisync(DataBuffer buffer, int type, String string) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringDefault(DataBuffer dataBuffer, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final boolean writeStringSmart(DataBuffer buffer, int type, String string) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final native boolean convertForDesktop(String var0, int var1, int var2, byte[] var3, int var4, int var5);

   public static final void writeByteArray(DataBuffer buffer, int type, byte[] s) {
      int len = s.length;
      if (len > 65535) {
         throw new IllegalArgumentException();
      }

      buffer.ensureCapacity(len + 3);
      buffer.writeShort(len);
      buffer.writeByte(type);
      buffer.write(s);
   }

   public static final void writeByteStream(DataBuffer buffer, int type, InputStream stream, long length) {
      if (length < 0) {
         throw new IllegalArgumentException();
      }

      writeLong(buffer, type, length);

      try {
         while (length > 0) {
            int segmentLength = (int)length;
            if (length > 65535) {
               segmentLength = 65535;
            }

            length -= segmentLength;
            buffer.ensureCapacity(segmentLength + 3);
            buffer.writeShort(segmentLength);
            buffer.writeByte(type);
            byte[] data = buffer.getArray();
            int len = stream.read(data, buffer.getArrayPosition(), segmentLength);
            if (len != segmentLength) {
               throw new IllegalArgumentException();
            }

            buffer.skipBytes(segmentLength);
         }
      } catch (IOException var8) {
      }
   }

   public static final void writeCharArrayArray(DataBuffer buffer, int type, char[][][] array) {
      int len = array.length;
      int count = 0;

      for (int idx = 0; idx < len; idx++) {
         char[] d = (char[])array[idx];
         if (d != null) {
            count += d.length;
         }
      }

      buffer.ensureCapacity(count * 2 + len * 2 + 2 + 3);
      buffer.writeShort(count * 2 + len * 2 + 2);
      buffer.writeByte(type);
      buffer.writeShort(len);

      for (int idx = 0; idx < len; idx++) {
         char[] d = (char[])array[idx];
         if (d == null) {
            buffer.writeShort(0);
         } else {
            int sz = d.length;
            buffer.writeShort(sz);

            for (int i = 0; i < sz; i++) {
               buffer.writeChar(d[i]);
            }
         }
      }
   }

   public static final void writeInt(DataBuffer buffer, int type, int value) {
      buffer.ensureCapacity(7);
      buffer.writeShort(4);
      buffer.writeByte(type);
      buffer.writeInt(value);
   }

   public static final void writeIntArray(DataBuffer buffer, int type, int[] array) {
      int len = array.length;
      buffer.ensureCapacity(len * 4 + 3);
      buffer.writeShort(len * 4);
      buffer.writeByte(type);

      for (int idx = 0; idx < len; idx++) {
         buffer.writeInt(array[idx]);
      }
   }

   public static final void writeIntArrayArray(DataBuffer buffer, int type, int[][][] array) {
      int len = array.length;
      int count = 0;

      for (int idx = 0; idx < len; idx++) {
         int[] d = (int[])array[idx];
         if (d != null) {
            count += array[idx].length;
         }
      }

      buffer.ensureCapacity(count * 4 + len * 2 + 2 + 3);
      buffer.writeShort(count * 4 + len * 2 + 2);
      buffer.writeByte(type);
      buffer.writeShort(len);

      for (int idx = 0; idx < len; idx++) {
         int[] d = (int[])array[idx];
         if (d == null) {
            buffer.writeShort(0);
         } else {
            int sz = d.length;
            buffer.writeShort(sz);

            for (int i = 0; i < sz; i++) {
               buffer.writeInt(d[i]);
            }
         }
      }
   }

   public static final void writeShort(DataBuffer buffer, int type, short value) {
      buffer.ensureCapacity(5);
      buffer.writeShort(2);
      buffer.writeByte(type);
      buffer.writeShort(value);
   }

   public static final void writeShortArray(DataBuffer buffer, int type, short[] array) {
      int len = array.length;
      buffer.ensureCapacity(len * 2 + 3);
      buffer.writeShort(len * 2);
      buffer.writeByte(type);

      for (int idx = 0; idx < len; idx++) {
         buffer.writeShort(array[idx]);
      }
   }

   public static final void convertBinary(DataBuffer buffer, int type, String s) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void convertInt(DataBuffer buffer, int type, int value, int size) {
      buffer.ensureCapacity(size + 3);
      switch (size) {
         case 0:
            buffer.writeShort(4);
            buffer.writeByte(type);
            buffer.writeInt(value);
            return;
         case 1:
         default:
            buffer.writeShort(1);
            buffer.writeByte(type);
            buffer.writeByte((byte)value);
            return;
         case 2:
            buffer.writeShort(2);
            buffer.writeByte(type);
            buffer.writeShort((short)value);
      }
   }

   public static final void writeLong(DataBuffer buffer, int type, long value) {
      buffer.ensureCapacity(11);
      buffer.writeShort(8);
      buffer.writeByte(type);
      buffer.writeLong(value);
   }

   public static final void writeLongArray(DataBuffer buffer, int type, long[] array) {
      int len = array.length;
      buffer.ensureCapacity(len * 8 + 3);
      buffer.writeShort(len * 8);
      buffer.writeByte(type);

      for (int idx = 0; idx < len; idx++) {
         buffer.writeLong(array[idx]);
      }
   }

   public static final void writeEmptyField(DataBuffer buffer, int type) {
      buffer.ensureCapacity(3);
      buffer.writeShort(0);
      buffer.writeByte(type);
   }

   public static final int getType(DataBuffer buffer) {
      return getType(buffer, false);
   }

   public static final int getType(DataBuffer buffer, boolean convertTag) {
      if (buffer.available() < 3) {
         throw new EOFException();
      }

      byte[] data = buffer.getArray();
      int offset = buffer.getArrayPosition();
      int type = data[offset + 2] & 255;
      if (convertTag && (type & 128) != 0 && (type & 240) != 240) {
         type &= -129;
      }

      return type;
   }

   public static final void skipField(DataBuffer buffer) {
      int len = buffer.readUnsignedShort();
      if (buffer.available() < len + 1) {
         throw new EOFException();
      }

      buffer.skipBytes(len + 1);
   }

   public static final boolean isType(DataBuffer buffer, int type) {
      if (buffer.available() < 3) {
         return false;
      }

      int pos = buffer.getPosition();

      try {
         buffer.skipBytes(2);
         int actualType = buffer.readUnsignedByte();
         buffer.setPosition(pos);
         return (type & 0xFF) == actualType;
      } catch (EOFException e) {
         return false;
      }
   }

   public static final String getBinaryString(DataBuffer buffer) {
      return getBinaryString(buffer, false);
   }

   public static final String getBinaryString(DataBuffer buffer, boolean markConsumed) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         markConsumed(buffer, markConsumed);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         byte[] data = buffer.getArray();
         int offset = buffer.getArrayPosition();
         buffer.skipBytes(len);

         while (len > 0 && data[offset + len - 1] == 0) {
            len--;
         }

         return new String(data, offset, len);
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final byte[] readByteArray(DataBuffer buffer) {
      return readByteArray(buffer, false);
   }

   public static final byte[] readByteArray(DataBuffer buffer, boolean markConsumed) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         markConsumed(buffer, markConsumed);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         byte[] data = buffer.getArray();
         int offset = buffer.getArrayPosition();
         buffer.skipBytes(len);
         byte[] result = new byte[len];
         System.arraycopy(data, offset, result, 0, len);
         return result;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final void readByteStream(DataBuffer buffer, boolean markConsumed, OutputStream out) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final short[] readShortArray(DataBuffer buffer) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         buffer.skipBytes(1);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         int count = len >> 1;
         short[] data = new short[count];

         for (int idx = 0; idx < count; idx++) {
            data[idx] = buffer.readShort();
         }

         return data;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final int[] readIntArray(DataBuffer buffer) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         buffer.skipBytes(1);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         int count = len >> 2;
         int[] data = new int[count];

         for (int idx = 0; idx < count; idx++) {
            data[idx] = buffer.readInt();
         }

         return data;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final int[][][] readIntArrayArray(DataBuffer buffer, boolean allowNulls) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static final long[] readLongArray(DataBuffer buffer) {
      int pos = buffer.getPosition();
      int bufflen = buffer.available();
      if (bufflen < 3) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         buffer.skipBytes(1);
         if (bufflen - 3 < len) {
            throw new EOFException();
         }

         int count = len >> 3;
         long[] data = new long[count];

         for (int idx = 0; idx < count; idx++) {
            data[idx] = buffer.readLong();
         }

         return data;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final char[][][] readCharArrayArray(DataBuffer buffer, boolean allowNulls) {
      throw new RuntimeException("cod2jar: array creation");
   }

   public static final boolean findType(DataBuffer buffer, int type) {
      return findType(buffer, type, false);
   }

   public static final boolean findType(DataBuffer buffer, int type, boolean convertTag) {
      int initialPosition = buffer.getPosition();
      int available = buffer.available();
      int position = initialPosition;
      type &= 255;

      try {
         while (available >= 3) {
            int len = buffer.readUnsignedShort();
            int readType = buffer.readUnsignedByte();
            if (convertTag && (readType & 128) != 0 && (readType & 240) != 240) {
               readType &= -129;
            }

            if (readType == type) {
               buffer.setPosition(position);
               return true;
            }

            int totlen = len + 3;
            if (available < totlen) {
               buffer.setPosition(initialPosition);
               return false;
            }

            buffer.skipBytes(len);
            position += totlen;
            available -= totlen;
         }

         buffer.setPosition(initialPosition);
         return false;
      } catch (EOFException e) {
         throw new RuntimeException();
      }
   }

   public static final short readShort(DataBuffer buffer) {
      return (short)readLong(buffer, false);
   }

   public static final short readShort(DataBuffer buffer, boolean markConsumed) {
      return (short)readLong(buffer, markConsumed);
   }

   public static final int readInt(DataBuffer buffer) {
      return (int)readLong(buffer, false);
   }

   public static final int readInt(DataBuffer buffer, boolean markConsumed) {
      return (int)readLong(buffer, markConsumed);
   }

   public static final long readLong(DataBuffer buffer) {
      return readLong(buffer, false);
   }

   public static final long readLong(DataBuffer buffer, boolean markConsumed) {
      int pos = buffer.getPosition();
      int buflen = buffer.available();
      if (buflen < 4) {
         throw new EOFException();
      }

      try {
         int len = buffer.readUnsignedShort();
         markConsumed(buffer, markConsumed);
         if (buflen - 3 < len) {
            throw new EOFException();
         }

         long value;
         switch (len) {
            case 1:
               value = buffer.readByte();
               break;
            case 2:
               value = buffer.readShort();
               break;
            case 4:
               value = buffer.readInt();
               break;
            case 8:
               value = buffer.readLong();
               break;
            default:
               throw new EOFException();
         }

         return value;
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final long getDateGMT(DataBuffer buffer) {
      return getDateGMT(buffer, false);
   }

   public static final long getDateGMT(DataBuffer buffer, boolean markConsumed) {
      return getDateTime(buffer, markConsumed, true, true);
   }

   public static final long getDateTime(DataBuffer buffer) {
      return getDateTime(buffer, false);
   }

   public static final long getDateTime(DataBuffer buffer, boolean markConsumed) {
      return getDateTime(buffer, markConsumed, false, false);
   }

   private static final long getDateTime(DataBuffer buffer, boolean markConsumed, boolean convertToGMT, boolean dateOnly) {
      int pos = buffer.getPosition();
      int buflen = buffer.available();
      if (buflen < 13) {
         throw new EOFException();
      }

      initializeCalendars();
      Calendar cal;
      if (convertToGMT) {
         cal = _gmtCal;
      } else {
         cal = _localCal;
         cal.setTimeZone(TimeZone.getDefault());
      }

      try {
         int len = buffer.readUnsignedShort();
         markConsumed(buffer, markConsumed);
         if (len != 10) {
            throw new EOFException();
         }

         synchronized (cal) {
            int second = buffer.readUnsignedByte();
            int minute = buffer.readUnsignedByte();
            int hour = buffer.readUnsignedByte();
            if (dateOnly) {
               cal.set(13, 0);
               cal.set(12, 0);
               cal.set(11, 0);
            } else {
               cal.set(13, second);
               cal.set(12, minute);
               cal.set(11, hour);
            }

            cal.set(5, buffer.readUnsignedByte());
            cal.set(2, buffer.readUnsignedByte() - 1);
            buffer.skipBytes(1);
            cal.set(1, buffer.readUnsignedShort());
            buffer.readByte();
            long date = ((CalendarExtensions)cal).getTimeLong();
            buffer.skipBytes(1);
            return date;
         }
      } catch (EOFException e) {
         buffer.setPosition(pos);
         throw e;
      }
   }

   public static final void writeNetworkMessageDate(long timeInMillis, byte[] messageHeader, int offset) {
      initializeCalendars();
      synchronized (_localCal) {
         _localCal.setTimeZone(TimeZone.getDefault());
         ((CalendarExtensions)_localCal).setTimeLong(timeInMillis);
         int date = _localCal.get(5);
         date |= _localCal.get(2) + 1 << 5;
         date |= _localCal.get(1) - 1996 << 9;
         messageHeader[offset] = (byte)(date & 0xFF);
         messageHeader[offset + 1] = (byte)(date >>> 8);
         int time = _localCal.get(13) / 2;
         time |= _localCal.get(12) << 5;
         time |= _localCal.get(11) << 11;
         messageHeader[offset + 2] = (byte)(time & 0xFF);
         messageHeader[offset + 3] = (byte)(time >>> 8);
      }
   }

   public static final long readNetworkMessageDate(byte[] messageHeader, int offset) {
      initializeCalendars();
      synchronized (_localCal) {
         int date = (messageHeader[offset] & 255) + ((messageHeader[offset + 1] & 255) << 8);
         _localCal.setTimeZone(TimeZone.getDefault());
         _localCal.set(5, date & 31);
         _localCal.set(2, (date >>> 5 & 15) - 1);
         _localCal.set(1, (date >>> 9 & 63) + 1996);
         int time = (messageHeader[offset + 2] & 255) + ((messageHeader[offset + 3] & 255) << 8);
         _localCal.set(14, 0);
         _localCal.set(13, (time & 31) * 2);
         _localCal.set(12, time >>> 5 & 63);
         _localCal.set(11, time >>> 11 & 31);
         return ((CalendarExtensions)_localCal).getTimeLong();
      }
   }

   public static final boolean extractCBool(byte[] buff, int offset) {
      return buff[offset] != 0;
   }

   private static final long doExtractIntegral(byte[] buff, int offset, boolean big_endian, int len) {
      int curr = len - 1;
      int inc = -1;
      if (big_endian) {
         curr = 0;
         inc = 1;
      }

      long value = 0;

      for (int i = 0; i < len; i++) {
         value <<= 8;
         value |= buff[offset + curr] & 0xFF;
         curr += inc;
      }

      return value;
   }

   public static final byte extractCByte(byte[] buff, int offset) {
      return (byte)doExtractIntegral(buff, offset, false, 1);
   }

   public static final short extractCShort(byte[] buff, int offset, boolean big_endian) {
      return (short)doExtractIntegral(buff, offset, big_endian, 2);
   }

   public static final int extractCInt(byte[] buff, int offset, boolean big_endian) {
      return (int)doExtractIntegral(buff, offset, big_endian, 4);
   }

   public static final long extractCLong(byte[] buff, int offset, boolean big_endian) {
      return doExtractIntegral(buff, offset, big_endian, 8);
   }

   public static final void injectCBool(byte[] buff, int offset, boolean value) {
      buff[offset] = (byte)(value ? 1 : 0);
   }

   private static final void doInjectIntegral(byte[] buff, int offset, boolean big_endian, int len, long value) {
      int curr = 0;
      int inc = 1;
      if (big_endian) {
         curr = len - 1;
         inc = -1;
      }

      for (int i = 0; i < len; i++) {
         buff[offset + curr] = (byte)(value & 255);
         value >>>= 8;
         curr += inc;
      }
   }

   public static final void injectCByte(byte[] buff, int offset, byte value) {
      doInjectIntegral(buff, offset, false, 1, value);
   }

   public static final void injectCShort(byte[] buff, int offset, boolean big_endian, short value) {
      doInjectIntegral(buff, offset, big_endian, 2, value);
   }

   public static final void injectCInt(byte[] buff, int offset, boolean big_endian, int value) {
      doInjectIntegral(buff, offset, big_endian, 4, value);
   }

   public static final void injectCLong(byte[] buff, int offset, boolean big_endian, long value) {
      doInjectIntegral(buff, offset, big_endian, 8, value);
   }

   public static final int getFieldIndex(byte[] data, byte tag) {
      return getFieldIndex(data, tag, false);
   }

   public static final int getFieldIndex(byte[] data, byte tag, boolean convertTag) {
      if (data != null) {
         int index = 2;

         while (
            index > 1 && index < data.length && (convertTag && (data[index] & 128) != 0 && (data[index] & 240) != 240 ? data[index] & 127 : data[index]) != tag
         ) {
            index += getFieldLength(data, index - 2) + 3;
         }

         if (index > 1 && index < data.length) {
            return index - 2;
         }
      }

      return -1;
   }

   public static final int getFieldLength(byte[] data, int index) {
      return extractCShort(data, index, false) & 65535;
   }

   public static final byte[] removeTag(byte[] data, byte tag) {
      return removeTag(data, tag, false);
   }

   public static final byte[] removeTag(byte[] data, byte tag, boolean convertTag) {
      int index = getFieldIndex(data, tag, convertTag);
      if (index != -1) {
         int len = getFieldLength(data, index);
         System.arraycopy(data, index + 3 + len, data, index, data.length - index - 3 - len);
         Array.resize(data, data.length - 3 - len);
         if (data.length == 0) {
            data = null;
         }
      }

      return data;
   }

   private static final String checkForEmptyString(String string) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] addTag(byte[] data, byte tag, String string) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] addByteTag(byte[] data, byte tag, byte value) {
      if (value == -1) {
         throw new IllegalArgumentException();
      }

      data = removeTag(data, tag, false);
      if (data == null) {
         data = new byte[0];
      }

      int index = data.length;
      Array.resize(data, index + 4);
      injectCShort(data, index, false, (short)1);
      data[index + 2] = tag;
      data[index + 3] = value;
      return data;
   }

   public static final String getTagData(byte[] data, byte tag) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final byte getTagByteData(byte[] data, byte tag) {
      int index = getFieldIndex(data, tag, false);
      return index != -1 && getFieldLength(data, index) == 1 ? data[index + 3] : -1;
   }
}
