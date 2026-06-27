package net.rim.device.internal.ipc;

import net.rim.device.api.system.Application;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.vm.WeakReference;

public class IPCInvoker {
   protected WeakReference _applicationReference;
   protected IPCBaseRunnable _runnable;

   protected IPCInvoker(Application var1) {
      this(var1, null);
   }

   protected IPCInvoker(Application var1, IPCBaseRunnable var2) {
      if (var1 == null) {
         throw new Object();
      }

      ApplicationControl.assertIPCAllowed(true);
      this._applicationReference = (WeakReference)(new Object(var1));
      this._runnable = var2;
   }

   public boolean invoke() {
      return this._runnable != null ? this.invoke(this._runnable) : false;
   }

   public boolean invoke(IPCBaseRunnable var1) {
      Object var2 = this._applicationReference.get();
      if (this.isAlive()) {
         var1.setListener(this.getListener());
         ((Application)var2).invokeLater(var1);
         return true;
      } else {
         return false;
      }
   }

   public IPCResult invokeResultWait() {
      return this._runnable instanceof IPCBlockingReturnRunnable ? this.invokeResultWait((IPCBlockingReturnRunnable)this._runnable) : IPCResult.FAILED_RESULT;
   }

   public IPCResult invokeResultWait(IPCBlockingReturnRunnable var1) {
      Object var2 = this._applicationReference.get();
      if (this.isAlive()) {
         var1.setListener(this.getListener());
         ((Application)var2).invokeLater(var1);
         return var1.getIPCResult();
      } else {
         return IPCResult.FAILED_RESULT;
      }
   }

   public boolean isAlive() {
      Object var1 = this._applicationReference.get();
      return var1 != null && this.getListener() != null && ((Application)var1).isAlive();
   }

   public Application getApplication() {
      return (Application)this._applicationReference.get();
   }

   public Runnable getRunnable() {
      return this._runnable;
   }

   public void setRunnable(IPCRunnable var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public Object getListener() {
      throw null;
   }
}
