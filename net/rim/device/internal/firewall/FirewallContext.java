package net.rim.device.internal.firewall;

import net.rim.device.api.system.ApplicationDescriptor;

public class FirewallContext {
   private ApplicationDescriptor _requestingDescriptor;
   private int[] _additionalModules;

   public void setRequestingDescriptor(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public ApplicationDescriptor getRequestingDescriptor() {
      return this._requestingDescriptor;
   }

   public void setAdditionalModules(int[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int[] getAdditionalModules() {
      return this._additionalModules;
   }
}
