package net.rim.device.api.system;

import java.util.Vector;

final class MMSStatus {
   private boolean _hasServiceBook;
   private Vector _actions;
   private static final long MMS_STATUS_GUID;
   private static MMSStatus _instance;

   static final MMSStatus getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean hasServiceBook() {
      return this._hasServiceBook;
   }

   public final void setServiceBookStatus(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized void onEnabled(Runnable var1) {
      if (MMS.isEnabled()) {
         var1.run();
      } else {
         if (this._actions == null) {
            this._actions = (Vector)(new Object());
         }

         this._actions.addElement(var1);
      }
   }

   private static final void runActions(Vector var0) {
      int var1 = var0.size();

      for (int var2 = 0; var2 < var1; var2++) {
         Object var3 = var0.elementAt(var2);
         ((Runnable)var3).run();
      }
   }
}
