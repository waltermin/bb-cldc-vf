package net.rim.device.cldc.io.sms;

import java.io.UnsupportedEncodingException;
import javax.wireless.messaging.TextMessage;
import net.rim.device.api.system.SMSPacketHeader;

public final class TextMessageImpl extends MessageImpl implements TextMessage {
   String _encoder;
   public static final String DEFAULT_SMS_ENCODER;
   public static final String UCS2_SMS_ENCODER;

   public TextMessageImpl(String address) {
   }

   @Override
   public final String getPayloadText() {
      if (super._buffer == null) {
         return null;
      }

      try {
         return (String)(new Object(super._buffer, this._encoder));
      } catch (UnsupportedEncodingException e) {
         return null;
      }
   }

   @Override
   public final void setPayloadText(String data) {
      if (data != null) {
         char[] c = data.toCharArray();

         for (int i = 0; i < c.length; i++) {
            if (!SMSPacketHeader.validateForDefaultMessageCoding(c[i])) {
               this.setEncoding(2);
               break;
            }
         }

         try {
            super._buffer = data.getBytes(this._encoder);
         } catch (UnsupportedEncodingException var4) {
         }
      }
   }

   @Override
   final void setEncoding(int coding) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
