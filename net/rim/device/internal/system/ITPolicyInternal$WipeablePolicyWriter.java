package net.rim.device.internal.system;

import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.internal.proxy.Proxy;

class ITPolicyInternal$WipeablePolicyWriter extends Thread implements GlobalEventListener {
   private Object _data;
   private boolean _completionEventPosted = false;
   private boolean _writingComplete = false;
   private boolean _isThisThreadTheWriter = false;
   private static final long ENCODING_ID;

   public ITPolicyInternal$WipeablePolicyWriter(byte[] var1) {
      this._data = var1;
      Proxy.getInstance().addGlobalEventListener(this);
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
