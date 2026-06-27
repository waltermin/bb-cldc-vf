package net.rim.device.api.io;

public class DatagramAddressBase {
   protected String _address;
   protected int _key;
   public static final int NONE;
   private static final byte[] DIGITS;

   public DatagramAddressBase() {
   }

   public DatagramAddressBase(DatagramAddressBase var1) {
      this.setAddress(var1.getAddress());
   }

   public DatagramAddressBase(String var1) {
      this.setAddress(var1);
   }

   public void setAddress(String var1) {
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
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      return this._address == null ? 7 : this._address.hashCode();
   }

   public static int indexOfNextDelim(String var0, int var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static short readShort(byte[] var0, int var1) {
      short var2 = 0;
      var2 = (short)(var2 | var0[var1] & 0xFF);
      var1++;
      var2 = (short)(var2 << 8);
      return (short)(var2 | var0[var1] & 0xFF);
   }

   public static int readInt(byte[] var0, int var1) {
      int var2 = 0;
      var2 |= var0[var1] & 255;
      var1++;
      var2 <<= 8;
      var2 |= var0[var1] & 255;
      var1++;
      var2 <<= 8;
      var2 |= var0[var1] & 255;
      var1++;
      var2 <<= 8;
      return var2 | var0[var1] & 0xFF;
   }

   public static void writeInt(byte[] var0, int var1, int var2) {
      var1 += 3;
      var0[var1] = (byte)var2;
      var1--;
      var2 >>>= 8;
      var0[var1] = (byte)var2;
      var1--;
      var2 >>>= 8;
      var0[var1] = (byte)var2;
      var1--;
      var2 >>>= 8;
      var0[var1] = (byte)var2;
   }

   public static void writeShort(byte[] var0, int var1, int var2) {
      var0[++var1] = (byte)var2;
      var1--;
      var2 >>>= 8;
      var0[var1] = (byte)var2;
   }

   public static int parseInt(String var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static int parseInt(byte[] var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static long parseLong(String var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static void appendHex(StringBuffer var0, int var1, int var2, int var3) {
      int var4 = var1 + 8;
      var0.setLength(var4);

      do {
         var0.setCharAt(--var4, (char)DIGITS[var2 & 15]);
         var2 >>>= 4;
         var3--;
      } while (var2 != 0 || var3 > 0);

      var0.delete(var1, var4);
   }

   public static void appendHex(byte[] var0, int var1, int var2, int var3) {
      int var4 = var1 + 8;

      do {
         var0[--var4] = DIGITS[var2 & 15];
         var2 >>>= 4;
         var3--;
      } while (var2 != 0 || var3 > 0);

      System.arraycopy(var0, var4, var0, var1, var1 + 8 - var4);
   }

   public static int parseIpAddressInt(String var0, int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected static boolean isDomainName(String var0, int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
