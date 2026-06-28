package net.rim.device.internal.system;

import net.rim.device.api.system.RadioStatusListener;

public class RadioStatusListenerFilter {
   private int _wafFilter;
   private RadioStatusListener _listener;

   public RadioStatusListenerFilter(int wafFilter, RadioStatusListener listener) {
      this._wafFilter = wafFilter;
      this._listener = listener;
   }

   public void dispatchEvent(int event, int subMessage, int data0, int data1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public boolean equals(Object obj) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int hashCode() {
      return this._listener.hashCode();
   }
}
