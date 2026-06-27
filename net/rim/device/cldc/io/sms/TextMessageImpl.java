package net.rim.device.cldc.io.sms;

import javax.wireless.messaging.TextMessage;

public final class TextMessageImpl extends MessageImpl implements TextMessage {
   String _encoder;
   public static final String DEFAULT_SMS_ENCODER;
   public static final String UCS2_SMS_ENCODER;

   public TextMessageImpl(String var1) {
   }

   @Override
   public final String getPayloadText() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void setPayloadText(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   final void setEncoding(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
