package net.rim.device.internal.system;

import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.internal.proxy.Proxy;

class ITPolicyInternal$WipeablePolicyWriter extends Thread implements GlobalEventListener {
   private Object _data;
   private boolean _completionEventPosted = false;
   private boolean _writingComplete = false;
   private boolean _isThisThreadTheWriter = false;
   private static final long ENCODING_ID;

   public ITPolicyInternal$WipeablePolicyWriter(byte[] data) {
      this._data = data;
      Proxy.getInstance().addGlobalEventListener(this);
   }

   @Override
   public void run() {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      if (guid == 8508406279413621091L || guid == -594020114676189989L) {
         synchronized (this) {
            if (this._isThisThreadTheWriter && this._writingComplete) {
               Proxy.getInstance().removeGlobalEventListener(this);
               RIMGlobalMessagePoster.postGlobalEvent(-467871197336216166L);
            }

            this._completionEventPosted = true;
         }
      }
   }
}
