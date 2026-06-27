package net.rim.device.api.util;

public final class TLEUtilities {
   private TLEUtilities() {
   }

   public static final void parseBuffer(DataBuffer var0, TLEFieldController var1) {
      int var2;
      while (!var0.eof() && (var2 = var0.readUnsignedByte()) != 0) {
         int var3 = var0.readCompressedInt();
         if (!var1.processField(var2, var3, var0)) {
            var0.skipBytes(var3);
         }
      }
   }

   public static final void parseField(DataBuffer var0, TLEFieldController var1, int var2) {
      int var3 = var0.getLength();
      var0.setLength(var0.getPosition() + var2);
      parseBuffer(var0, var1);
      var0.setLength(var3);
   }

   public static final int readIntegerField(DataBuffer var0, int var1) {
      if (var0.readUnsignedByte() == var1) {
         return readIntegerField(var0);
      } else {
         throw new Object();
      }
   }

   public static final int readIntegerField(DataBuffer var0) {
      return readIntegerFieldWithLength(var0, var0.readCompressedInt());
   }

   public static final int readIntegerFieldWithLength(DataBuffer var0, int var1) {
      switch (var1) {
         case 0:
         case 3:
            throw new Object();
         case 1:
         default:
            return var0.readUnsignedByte();
         case 2:
            return var0.readUnsignedShort();
         case 4:
            return var0.readInt();
      }
   }

   public static final byte[] readDataField(DataBuffer var0, int var1) {
      if (var0.readUnsignedByte() == var1) {
         return readDataField(var0);
      } else {
         throw new Object();
      }
   }

   public static final byte[] readDataField(DataBuffer var0) {
      byte[] var1 = new byte[var0.readCompressedInt()];
      var0.readFully(var1);
      return var1;
   }

   public static final String readStringField(DataBuffer var0, int var1) {
      return readStringField(var0, var1, true);
   }

   public static final String readStringField(DataBuffer var0, int var1, boolean var2) {
      if (var0.readUnsignedByte() != var1) {
         throw new Object();
      } else {
         return readStringField(var0, var2);
      }
   }

   public static final String readStringField(DataBuffer var0, boolean var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final String readStringFieldEncoded(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void writeDataField(DataBuffer var0, int var1, byte[] var2) {
      int var3 = var2 != null ? var2.length : 0;
      writeDataField(var0, var1, var2, 0, var3);
   }

   public static final void writeDataField(DataBuffer var0, int var1, byte[] var2, int var3, int var4) {
      var0.writeByte(var1);
      var0.writeCompressedInt(var4);
      if (var2 != null) {
         var0.write(var2, var3, var4);
      }
   }

   public static final void writeDataField(DataBuffer var0, int var1, String var2, int var3, int var4, boolean var5) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public static final void writeField(DataBuffer var0, int var1, TLEFieldController var2) {
      var0.writeByte(var1);
      int var3 = var0.getPosition();
      var0.writeInt(0);
      var0.writeByte(0);
      var2.dumpField(var1, var0);
      int var4 = var0.getPosition();
      int var5 = var4 - var3 - 5;
      var0.setPosition(var3);

      for (byte var6 = 28; var6 > 0; var6 -= 7) {
         var0.writeByte(var5 >>> var6 | 128);
      }

      var0.writeByte(var5 & 127);
      var0.setPosition(var4);
   }

   public static final void writeIntegerField(DataBuffer var0, int var1, int var2, boolean var3) {
      var0.writeByte(var1);
      if ((var2 & -65536) != 0 || var3) {
         var0.writeCompressedInt(4);
         var0.writeInt(var2);
      } else if ((var2 & 0xFF00) != 0) {
         var0.writeCompressedInt(2);
         var0.writeShort(var2);
      } else {
         var0.writeCompressedInt(1);
         var0.writeByte(var2);
      }
   }

   public static final void writeStringField(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void writeStringField(DataBuffer var0, int var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final void writeStringField(DataBuffer var0, int var1, String var2, int var3, int var4) {
      writeStringField(var0, var1, var2, var3, var4, false);
   }

   public static final void writeStringField(DataBuffer var0, int var1, String var2, int var3, int var4, boolean var5) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public static final void writeStringFieldEncoded(DataBuffer var0, int var1, String var2, String var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean findType(DataBuffer var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void skipField(DataBuffer var0) {
      var0.skipBytes(1);
      int var1 = var0.readCompressedInt();
      var0.skipBytes(var1);
   }

   public static final int getType(DataBuffer var0) {
      return getType(var0, false);
   }

   public static final int getType(DataBuffer var0, boolean var1) {
      if (var0.available() < 1) {
         throw new Object();
      }

      int var2 = var0.getArray()[var0.getArrayPosition()] & 255;
      return var1 && (var2 & 128) != 0 && (var2 & 240) != 240 ? var2 & -129 : var2;
   }

   public static final int getIntegerFieldSize(int var0) {
      if ((var0 & -65536) != 0) {
         return 6;
      } else {
         return (var0 & 0xFF00) != 0 ? 4 : 3;
      }
   }

   private static final int getLengthStructureSize(int var0) {
      int var2 = 5;

      for (byte var1 = 28; var1 > 0; var1 -= 7) {
         if ((var0 >>> var1 & 127) != 0) {
            return var2;
         }

         var2--;
      }

      return var2;
   }

   public static final int getFieldSize(int var0) {
      return getLengthStructureSize(var0) + 1 + var0;
   }

   public static final String getStringFromBuffer(DataBuffer var0, int var1) {
      String var2 = StringUtilities.cStr2String(var0.getArray(), var0.getArrayPosition(), var1);
      var0.skipBytes(var1);
      return var2;
   }
}
