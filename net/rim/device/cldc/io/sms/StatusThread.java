package net.rim.device.cldc.io.sms;

import java.util.Vector;
import net.rim.device.api.io.DatagramStatusListener;

final class StatusThread extends Thread {
   private Transport _transport;
   private Vector _status = (Vector)(new Object());

   public StatusThread(Transport var1) {
      this._transport = var1;
   }

   public final void addStatus(boolean var1, DatagramStatusListener var2, int var3, int var4, Object var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
