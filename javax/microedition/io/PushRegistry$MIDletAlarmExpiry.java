package javax.microedition.io;

import java.util.Date;

final class PushRegistry$MIDletAlarmExpiry implements Runnable {
   private String _midletClassName;
   private Date _expiry;

   public final long getExpiry() {
      return this._expiry.getTime();
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public PushRegistry$MIDletAlarmExpiry(String var1, Date var2) {
      this._midletClassName = var1;
      this._expiry = var2;
   }
}
