package javax.microedition.content;

import net.rim.device.api.util.DataBuffer;

final class ContentHandlerConverter {
   private static final int HANDLER_ID;
   private static final int HANDLER_TYPES;
   private static final int HANDLER_SUFFIXES;
   private static final int HANDLER_ACTIONS;
   private static final int HANDLER_ACTION_NAMES;
   private static final int HANDLER_APP_NAME;
   private static final int HANDLER_VERSION;
   private static final int HANDLER_AUTHORITY;
   private static final int HANDLER_CLASSNAME;
   private static final int HANDLER_ACCESS_ALLOWED;
   private static final int HANDLER_REGISTRATION;

   private ContentHandlerConverter() {
   }

   static final ContentHandlerServerImpl convert(DataBuffer buffer) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private static final String[] readStringArray(DataBuffer buffer) {
      int size = buffer.readByte();
      String[] strs = new String[size];

      for (int i = 0; i < size; i++) {
         strs[i] = buffer.readUTF();
      }

      return strs;
   }

   static final DataBuffer convert(ContentHandlerServerImpl server) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private static final void writeString(DataBuffer buffer, int param, String str) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final void writeStringArray(DataBuffer buffer, int param, String[] strs) {
      if (strs != null && strs.length != 0) {
         buffer.writeByte(param);
         buffer.writeByte(strs.length);

         for (int i = 0; i < strs.length; i++) {
            buffer.writeUTF(strs[i]);
         }
      }
   }
}
