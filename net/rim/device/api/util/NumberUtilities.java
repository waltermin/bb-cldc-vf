package net.rim.device.api.util;

public final class NumberUtilities {
   private NumberUtilities() {
   }

   private static final int cast2Int(byte var0, int var1) {
      switch (var1) {
         case 2:
         case 4:
         case 8:
         case 16:
            return var0 & 0xFF;
         default:
            return var0;
      }
   }

   private static final int cast2Int(short var0, int var1) {
      switch (var1) {
         case 2:
         case 4:
         case 8:
         case 16:
            return var0 & 65535;
         default:
            return var0;
      }
   }

   public static final void appendNumber(StringBuffer var0, byte var1, int var2) {
      appendNumber(var0, var1, var2, -1);
   }

   public static final void appendNumber(StringBuffer var0, byte var1, int var2, int var3) {
      if (var2 == 10 && var3 <= 0) {
         var0.append(var1);
      } else {
         appendNumber(var0, cast2Int(var1, var2), var2, var3);
      }
   }

   public static final String toString(byte var0, int var1) {
      return toString(var0, var1, -1);
   }

   public static final String toString(byte var0, int var1, int var2) {
      return toString(cast2Int(var0, var1), var1, var2);
   }

   public static final void appendNumber(StringBuffer var0, short var1, int var2) {
      appendNumber(var0, var1, var2, -1);
   }

   public static final void appendNumber(StringBuffer var0, short var1, int var2, int var3) {
      appendNumber(var0, cast2Int(var1, var2), var2, var3);
   }

   public static final String toString(short var0, int var1) {
      return toString(var0, var1, -1);
   }

   public static final String toString(short var0, int var1, int var2) {
      return toString(cast2Int(var0, var1), var1, var2);
   }

   public static final void appendNumber(StringBuffer var0, int var1) {
      appendNumber(var0, var1, 10, -1);
   }

   public static final void appendNumber(StringBuffer var0, int var1, int var2) {
      appendNumber(var0, var1, var2, -1);
   }

   public static final native void appendNumber(StringBuffer var0, int var1, int var2, int var3);

   public static final int appendNumber(int var0, byte[] var1, int var2, int var3) {
      return appendNumber(var1, var0, var2, var3);
   }

   public static final int appendNumber(int var0, byte[] var1, long var2, int var4) {
      return appendNumber(var1, var0, var2, var4);
   }

   private static final native int appendNumber(byte[] var0, int var1, long var2, int var4);

   public static final String toString(int var0, int var1) {
      return toString(var0, var1, -1);
   }

   public static final String toString(int var0, int var1, int var2) {
      Object var3 = new Object(Math.max(10, var2));
      appendNumber((StringBuffer)var3, var0, var1, var2);
      return ((StringBuffer)var3).toString();
   }

   public static final void appendNumber(StringBuffer var0, long var1) {
      appendNumber(var0, var1, 10, -1);
   }

   public static final void appendNumber(StringBuffer var0, long var1, int var3) {
      appendNumber(var0, var1, var3, -1);
   }

   public static final native void appendNumber(StringBuffer var0, long var1, int var3, int var4);

   public static final String toString(long var0, int var2) {
      return toString(var0, var2, -1);
   }

   public static final String toString(long var0, int var2, int var3) {
      Object var4 = new Object(Math.max(20, var3));
      appendNumber((StringBuffer)var4, var0, var2, var3);
      return ((StringBuffer)var4).toString();
   }

   public static final char intToHexDigit(int var0) {
      var0 &= 15;
      return var0 < 10 ? (char)(var0 + 48) : (char)(var0 - 10 + 97);
   }

   public static final char intToUpperHexDigit(int var0) {
      var0 &= 15;
      return var0 < 10 ? (char)(var0 + 48) : (char)(var0 - 10 + 65);
   }

   public static final int hexDigitToInt(char var0) {
      switch (var0) {
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
            return var0 - 48;
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
            return var0 - 65 + 10;
         case 'a':
         case 'b':
         case 'c':
         case 'd':
         case 'e':
         case 'f':
            return var0 - 97 + 10;
         default:
            throw new Object();
      }
   }

   public static final int hexDigitToInt(char var0, int var1) {
      switch (var0) {
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
            return var0 - 48;
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
            return var0 - 65 + 10;
         case 'a':
         case 'b':
         case 'c':
         case 'd':
         case 'e':
         case 'f':
            return var0 - 97 + 10;
         default:
            return var1;
      }
   }

   public static final int parseInt(String var0, int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
