package net.rim.device.internal.system;

import net.rim.device.api.system.RadioStatusListener;

public class RadioStatusListenerFilter {
   private int _wafFilter;
   private RadioStatusListener _listener;

   public RadioStatusListenerFilter(int var1, RadioStatusListener var2) {
      this._wafFilter = var1;
      this._listener = var2;
   }

   public void dispatchEvent(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      return this._listener.hashCode();
   }
}
