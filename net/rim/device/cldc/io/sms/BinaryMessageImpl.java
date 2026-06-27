package net.rim.device.cldc.io.sms;

import javax.wireless.messaging.BinaryMessage;

public final class BinaryMessageImpl extends MessageImpl implements BinaryMessage {
   public BinaryMessageImpl(String var1) {
      super(var1);
      super._encoding = 1;
   }

   @Override
   public final byte[] getPayloadData() {
      return super._buffer;
   }

   @Override
   public final void setPayloadData(byte[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
