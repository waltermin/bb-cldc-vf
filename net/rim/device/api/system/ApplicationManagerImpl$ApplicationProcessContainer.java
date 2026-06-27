package net.rim.device.api.system;

import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

class ApplicationManagerImpl$ApplicationProcessContainer {
   private ApplicationProcess[] _processes = new ApplicationProcess[0];

   int numberOfProcesses() {
      return this._processes.length;
   }

   ApplicationProcess getProcessAtIndex(int var1) {
      return this._processes[var1];
   }

   int getProcessIndex(ApplicationProcess var1) {
      for (int var2 = this._processes.length - 1; var2 >= 0; var2--) {
         if (this._processes[var2] == var1) {
            return var2;
         }
      }

      return -1;
   }

   void addProcess(ApplicationProcess var1) {
      Arrays.add(this._processes, var1);
   }

   void removeProcess(int var1) {
      this.moveProcessToRear(var1);
      Array.resize(this._processes, this._processes.length - 1);
   }

   void moveProcess(ApplicationProcess var1, int var2) {
      int var3 = this.getProcessIndex(var1);
      if (var3 == -1) {
         throw new Object();
      }

      this.moveProcess(var3, var2);
   }

   void moveProcessToRear(ApplicationProcess var1) {
      int var2 = this.getProcessIndex(var1);
      if (var2 == -1) {
         throw new Object();
      }

      this.moveProcessToRear(var2);
   }

   void moveProcessToRear(int var1) {
      this.moveProcess(var1, this._processes.length - 1);
   }

   void moveProcess(int var1, int var2) {
      ApplicationProcess var3 = this._processes[var1];
      if (var2 < var1) {
         System.arraycopy(this._processes, var2, this._processes, var2 + 1, var1 - var2);
      } else if (var2 > var1) {
         System.arraycopy(this._processes, var1 + 1, this._processes, var1, var2 - var1);
      }

      this._processes[var2] = var3;
   }

   ApplicationProcess[] getCopyOfProcesses() {
      int var1 = this._processes.length;
      ApplicationProcess[] var2 = new ApplicationProcess[var1];
      System.arraycopy(this._processes, 0, var2, 0, var1);
      return var2;
   }
}
