package net.rim.device.cldc.io.utility;

import net.rim.device.api.util.StringMatch;

public final class URIDecoder {
   private static final String UTF_8;
   private static final String ISO_8859_1;
   private static StringMatch DECODER_MATCH_PLUS;
   private static StringMatch DECODER_MATCH;

   public static final String decode(String str, String encoding) {
      return decode(str, encoding, true);
   }

   public static final String decode(String str, String encoding, boolean decodePlusAsSpace) {
      return decode(str, encoding, decodePlusAsSpace, false);
   }

   public static final String decode(String str, String encoding, boolean decodePlusAsSpace, boolean returnNullOnError) {
      throw new RuntimeException("cod2jar: string-special");
   }
}
