package net.rim.device.internal.system;

import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntIntHashtable;

class CodeStore$DependencyList {
   private int _generation;
   private IntHashtable _dependencyLists;
   private IntHashtable _moduleHandleToDependencyHandles;
   private int[] _queue;
   private static final int IS_DEPENDENCY;

   CodeStore$DependencyList() {
      this.reset();
   }

   private void reset() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private IntIntHashtable buildModuleDependencyList(int moduleHandle) {
      throw new RuntimeException("cod2jar: type check");
   }

   synchronized boolean isDependency(int processModuleHandle, int callingModuleHandle) {
      if (this._generation != CodeStore.getModuleHandles(null)) {
         this.reset();
      }

      IntIntHashtable moduleDependencyList = (IntIntHashtable)this._dependencyLists.get(processModuleHandle);
      if (moduleDependencyList == null) {
         moduleDependencyList = this.buildModuleDependencyList(processModuleHandle);
         this._dependencyLists.put(processModuleHandle, moduleDependencyList);
      }

      return moduleDependencyList.get(callingModuleHandle) == 1;
   }
}
