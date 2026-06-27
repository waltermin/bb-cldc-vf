package net.rim.device.cldc.io.utility;

import net.rim.device.api.util.StringMatch;

public final class URIDecoder {
   private static final String UTF_8;
   private static final String ISO_8859_1;
   private static StringMatch DECODER_MATCH_PLUS;
   private static StringMatch DECODER_MATCH;

   public static final String decode(String var0, String var1) {
      return decode(var0, var1, true);
   }

   public static final String decode(String var0, String var1, boolean var2) {
      return decode(var0, var1, var2, false);
   }

   public static final String decode(String var0, String var1, boolean var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
