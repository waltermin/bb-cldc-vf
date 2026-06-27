package net.rim.device.internal.system;

final class CodeModuleGroupPropertiesCollection$CheckGroupsThread extends Thread {
   private boolean _shouldCheckAdded = false;
   private boolean _shouldCheckDeleted = false;

   private CodeModuleGroupPropertiesCollection$CheckGroupsThread() {
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void setFlag(long var1) {
      if (var1 == 256826950193107649L) {
         this._shouldCheckAdded = true;
      } else {
         if (var1 == -4232371946002803201L) {
            this._shouldCheckDeleted = true;
         }
      }
   }

   CodeModuleGroupPropertiesCollection$CheckGroupsThread(CodeModuleGroupPropertiesCollection$1 var1) {
      this();
   }
}
