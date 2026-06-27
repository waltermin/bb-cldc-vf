package net.rim.device.internal.ipc;

import net.rim.device.api.system.Application;
import net.rim.vm.WeakReference;

class IPCInvokerWeak extends IPCInvoker {
   private WeakReference _listenerReference;

   IPCInvokerWeak(Application var1, Object var2) {
      this(var1, var2, null);
   }

   IPCInvokerWeak(Application var1, Object var2, IPCRunnable var3) {
      super(var1, var3);
      this._listenerReference = (WeakReference)(new Object(var2));
   }

   @Override
   public Object getListener() {
      return this._listenerReference == null ? null : this._listenerReference.get();
   }
}
