package net.rim.device.cldc.io.udp;

import javax.microedition.io.Datagram;
import net.rim.device.api.io.DatagramAddressBase;
import net.rim.device.api.io.DatagramBase;

public class MIDletDatagram extends DatagramBase {
   private boolean initcomplete = true;
   private static final String DATAGRAMSCHEME;

   MIDletDatagram(byte[] var1, int var2, int var3, String var4) {
      super(var1, var2, var3, var4);
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
   public void setAddress(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setData(byte[] var1, int var2, int var3) {
      if (var1 == null || var2 < var1.length && var2 + var3 <= var1.length && var2 + var3 >= 0) {
         super.setData(var1, var2, var3);
      } else {
         throw new Object();
      }
   }

   @Override
   public void setLength(int var1) {
      if (var1 > this.getArray().length - this.getArrayStart()) {
         throw new Object();
      }

      super.setLength(var1);
   }

   @Override
   public void setAddress(Datagram var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void reset() {
      byte[] var1 = this.getArray();
      super.reset();
      this.setData(var1, 0, 0, this.isBigEndian());
   }
}
