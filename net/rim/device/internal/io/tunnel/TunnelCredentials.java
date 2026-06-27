package net.rim.device.internal.io.tunnel;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.util.Persistable;

final class TunnelCredentials implements Persistable {
   String apn;
   String apnUsername;
   String apnPassword;
   boolean editingOptionsAllowed;
   boolean outgoingSocketsAllowed;
   boolean incomingSocketsAllowed;

   TunnelCredentials(boolean var1, boolean var2, boolean var3) {
   }

   final void loadValuesFromBranding() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   final void loadValuesFromITPolicy() {
      String var1 = ITPolicy.getString(32, 4);
      if (var1 != null) {
         this.apn = var1;
         this.apnUsername = ITPolicy.getString(32, 5);
         this.apnPassword = ITPolicy.getString(32, 6);
      }

      this.editingOptionsAllowed = ITPolicy.getBoolean(32, 3, this.editingOptionsAllowed);
      this.outgoingSocketsAllowed = ITPolicy.getBoolean(32, 7, this.outgoingSocketsAllowed);
      this.incomingSocketsAllowed = ITPolicy.getBoolean(32, 8, this.incomingSocketsAllowed);
   }
}
