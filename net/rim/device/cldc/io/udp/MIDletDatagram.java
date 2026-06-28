package net.rim.device.cldc.io.udp;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;

public class MIDletDatagram extends DatagramBase {
   private boolean initcomplete = true;
   private static final String DATAGRAMSCHEME;

   MIDletDatagram(byte[] buffer, int offset, int length, String address) {
      super(buffer, offset, length, address);
   }

   @Override
   public String getAddress() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected DatagramAddressBase newAddressBase() {
      return (DatagramAddressBase)(new Object());
   }

   @Override
   public void setAddress(String addr) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setData(byte[] buffer, int offset, int length) {
      if (buffer == null || offset < buffer.length && offset + length <= buffer.length && offset + length >= 0) {
         super.setData(buffer, offset, length);
      } else {
         throw new Object();
      }
   }

   @Override
   public void setLength(int length) {
      if (length > this.getArray().length - this.getArrayStart()) {
         throw new Object();
      }

      super.setLength(length);
   }

   @Override
   public void setAddress(Datagram d) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void reset() {
      byte[] buf = this.getArray();
      super.reset();
      this.setData(buf, 0, 0, this.isBigEndian());
   }
}
