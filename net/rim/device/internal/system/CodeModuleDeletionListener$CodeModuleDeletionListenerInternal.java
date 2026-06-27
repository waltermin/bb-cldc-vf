package net.rim.device.internal.system;

import net.rim.device.api.system.CodeModuleGroupManager;
import net.rim.device.api.system.GlobalEventListener;

final class CodeModuleDeletionListener$CodeModuleDeletionListenerInternal implements GlobalEventListener {
   private CodeModuleDeletionListener$CodeModuleDeletionListenerInternal() {
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == -4232371946002803201L) {
         CodeModuleGroupManager.deleteEmptyGroups();
      }
   }

   CodeModuleDeletionListener$CodeModuleDeletionListenerInternal(CodeModuleDeletionListener$1 var1) {
      this();
   }
}
