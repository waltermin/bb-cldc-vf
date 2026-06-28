package javax.microedition.content;

import java.io.IOException;

public class ContentHandlerException extends IOException {
   private final int _errcode;
   public static final int NO_REGISTERED_HANDLER;
   public static final int TYPE_UNKNOWN;
   public static final int AMBIGUOUS;

   public ContentHandlerException(String reason, int errcode) {
   }

   public int getErrorCode() {
      return this._errcode;
   }
}
