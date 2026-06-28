package net.rim.device.api.io;

public class DatagramAddressBase {
   protected String _address;
   protected int _key;
   public static final int NONE;
   private static final byte[] DIGITS;

   public DatagramAddressBase() {
   }

   public DatagramAddressBase(DatagramAddressBase addressBase) {
      this.setAddress(addressBase.getAddress());
   }

   public DatagramAddressBase(String address) {
      this.setAddress(address);
   }

   public void setAddress(String address) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getAddress() {
      return this._address;
   }

   public String getSubAddress() {
      return null;
   }

   public DatagramAddressBase getSubAddressBase() {
      return null;
   }

   public int getKey() {
      return this._key;
   }

   public void swap() {
   }

   @Override
   public boolean equals(Object addressBase) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      return this._address == null ? 7 : this._address.hashCode();
   }

   public static int indexOfNextDelim(String str, int start) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static short readShort(byte[] buf, int offset) {
      short ret = 0;
      ret = (short)(ret | buf[offset] & 0xFF);
      offset++;
      ret = (short)(ret << 8);
      return (short)(ret | buf[offset] & 0xFF);
   }

   public static int readInt(byte[] buf, int offset) {
      int ret = 0;
      ret |= buf[offset] & 255;
      offset++;
      ret <<= 8;
      ret |= buf[offset] & 255;
      offset++;
      ret <<= 8;
      ret |= buf[offset] & 255;
      offset++;
      ret <<= 8;
      return ret | buf[offset] & 0xFF;
   }

   public static void writeInt(byte[] buf, int offset, int value) {
      offset += 3;
      buf[offset] = (byte)value;
      offset--;
      value >>>= 8;
      buf[offset] = (byte)value;
      offset--;
      value >>>= 8;
      buf[offset] = (byte)value;
      offset--;
      value >>>= 8;
      buf[offset] = (byte)value;
   }

   public static void writeShort(byte[] buf, int offset, int value) {
      buf[++offset] = (byte)value;
      offset--;
      value >>>= 8;
      buf[offset] = (byte)value;
   }

   public static int parseInt(String buf, int start, int end, int radix) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int parseInt(byte[] buf, int start, int end, int radix) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static long parseLong(String buf, int start, int end, int radix) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static void appendHex(StringBuffer buf, int offset, int value, int length) {
      int index = offset + 8;
      buf.setLength(index);

      do {
         buf.setCharAt(--index, (char)DIGITS[value & 15]);
         value >>>= 4;
         length--;
      } while (value != 0 || length > 0);

      buf.delete(offset, index);
   }

   public static void appendHex(byte[] buf, int offset, int value, int length) {
      int index = offset + 8;

      do {
         buf[--index] = DIGITS[value & 15];
         value >>>= 4;
         length--;
      } while (value != 0 || length > 0);

      System.arraycopy(buf, index, buf, offset, offset + 8 - index);
   }

   public static int parseIpAddressInt(String address, int offset) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected static boolean isDomainName(String address, int startIndex, int endIndex) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
