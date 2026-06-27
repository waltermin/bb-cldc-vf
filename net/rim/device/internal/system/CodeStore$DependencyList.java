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
      throw new RuntimeException("cod2jar: exception table");
   }

   private IntIntHashtable buildModuleDependencyList(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   synchronized boolean isDependency(int var1, int var2) {
      if (this._generation != CodeStore.getModuleHandles(null)) {
         this.reset();
      }

      Object var3 = this._dependencyLists.get(var1);
      if (var3 == null) {
         var3 = this.buildModuleDependencyList(var1);
         this._dependencyLists.put(var1, var3);
      }

      return ((IntIntHashtable)var3).get(var2) == 1;
   }
}
