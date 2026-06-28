package net.rim.device.internal.bluetooth;

public final class UUIDUtilities {
   private static final byte[] BASE_UUID;

   private UUIDUtilities() {
   }

   public static final String toString(byte[] uuidData) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] toBytes(String uuid) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public static final byte[] promoteTo128Bits(String uuid) {
      byte[] data = toBytes(uuid);
      int length = data.length;
      if (length == 16) {
         return data;
      }

      byte[] newData = new byte[16];
      System.arraycopy(BASE_UUID, 0, newData, 0, BASE_UUID.length);
      System.arraycopy(data, 0, newData, 4 - length, length);
      return newData;
   }

   public static final byte[] serialize(String uuid) {
      return serialize(toBytes(uuid));
   }

   private static final byte[] serialize(byte[] data) {
      byte[] serializedData = new byte[data.length + 1];
      System.arraycopy(data, 0, serializedData, 1, data.length);
      serializedData[0] = 24;
      switch (data.length) {
         case 2:
            serializedData[0] = (byte)(serializedData[0] | 1);
            return serializedData;
         case 4:
            serializedData[0] = (byte)(serializedData[0] | 2);
            return serializedData;
         case 16:
            serializedData[0] = (byte)(serializedData[0] | 4);
         default:
            return serializedData;
      }
   }
}
