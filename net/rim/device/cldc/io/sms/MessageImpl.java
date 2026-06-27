package net.rim.device.cldc.io.sms;

import java.util.Date;
import javax.wireless.messaging.Message;

public class MessageImpl implements Message {
   private String _address;
   private String _scAddress;
   Date _date;
   byte[] _buffer;
   int _encoding;

   void setBuffer(byte[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setEncoding(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   int getEncoding() {
      return this._encoding;
   }

   public byte[] getBuffer() {
      return this._buffer;
   }

   public String getSCAddress() {
      return this._scAddress;
   }

   public void setSCAddress(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void setAddress(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public Date getTimestamp() {
      return this._date;
   }

   @Override
   public String getAddress() {
      return this._address;
   }

   public MessageImpl(String var1) {
      this._address = var1;
   }
}
