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

   static final ContentHandlerServerImpl convert(DataBuffer var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final String[] readStringArray(DataBuffer var0) {
      byte var1 = var0.readByte();
      String[] var2 = new String[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = var0.readUTF();
      }

      return var2;
   }

   static final DataBuffer convert(ContentHandlerServerImpl var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void writeString(DataBuffer var0, int var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static final void writeStringArray(DataBuffer var0, int var1, String[] var2) {
      if (var2 != null && var2.length != 0) {
         var0.writeByte(var1);
         var0.writeByte(var2.length);

         for (int var3 = 0; var3 < var2.length; var3++) {
            var0.writeUTF(var2[var3]);
         }
      }
   }
}
