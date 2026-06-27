package net.rim.device.internal.ipc;

import net.rim.device.api.system.Application;
import net.rim.device.api.util.Arrays;

public class IPCInvokerList {
   private IPCInvoker[] _list = new IPCInvoker[0];

   public int size() {
      return this._list.length;
   }

   public IPCInvoker elementAt(int var1) {
      IPCInvoker var2 = null;
      if (var1 < this._list.length && var1 >= 0) {
         var2 = this._list[var1];
      }

      return var2;
   }

   public int indexOf(Object var1) {
      for (int var2 = 0; var2 < this._list.length; var2++) {
         Object var3 = this._list[var2].getListener();
         if (var1.equals(var3)) {
            return var2;
         }
      }

      return -1;
   }

   public boolean add(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean add(Application var1, Object var2) {
      if (var2 != null && !Arrays.contains(this._list, var2)) {
         this.add(IPCInvokerFactory.getStrongIPCInvoker(var1, var2));
         return true;
      } else {
         return false;
      }
   }

   public boolean addWeak(Application var1, Object var2) {
      if (var2 != null && !Arrays.contains(this._list, var2)) {
         this.add(IPCInvokerFactory.getIPCInvoker(var1, var2));
         return true;
      } else {
         return false;
      }
   }

   protected void add(IPCInvoker var1) {
      Arrays.add(this._list, var1);
   }

   public void clear() {
      this._list = new IPCInvoker[0];
   }

   public void remove(int var1) {
      Arrays.removeAt(this._list, var1);
   }

   public int remove(Object var1) {
      int var2 = Arrays.getIndex(this._list, var1);
      if (var2 > -1) {
         this.remove(var2);
      }

      return var2;
   }

   public void removeDeadIPCInvokers() {
      for (int var1 = 0; var1 < this._list.length; var1++) {
         if (!this.isAlive(var1)) {
            this.remove(var1);
            var1--;
         }
      }
   }

   public boolean invoke(int var1, IPCRunnable var2) {
      boolean var3 = this._list[var1].invoke(var2);
      if (!var3) {
         this.remove(var1);
         return false;
      } else {
         return true;
      }
   }

   public IPCResult invokeResultWait(int var1, IPCBlockingReturnRunnable var2) {
      return this._list[var1].invokeResultWait(var2);
   }

   public void invokeAll(IPCRunnable var1) {
      for (int var2 = 0; var2 < this._list.length; var2++) {
         if (!this.invoke(var2, var1)) {
            var2--;
         }
      }
   }

   public boolean isAlive(int var1) {
      return this._list[var1].isAlive();
   }
}
