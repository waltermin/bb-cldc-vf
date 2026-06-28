package net.rim.device.api.memorycleaner;

public final class MemoryCleanerAction {
   private Object _listener;

   public MemoryCleanerAction(Object listener) {
      this._listener = listener;
   }

   public final MemoryCleanerListener getListener() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final boolean doAction(int event) {
      MemoryCleanerListener listener = this.getListener();
      if (listener != null) {
         try {
            return listener.cleanNow(event);
         } catch (Throwable t) {
            return true;
         }
      } else {
         return false;
      }
   }

   public final boolean doAction() {
      return this.doAction(4);
   }

   public final String getDescription() {
      MemoryCleanerListener listener = this.getListener();
      if (listener != null) {
         try {
            return listener.getDescription();
         } catch (Throwable var3) {
         }
      }

      return "";
   }

   @Override
   public final boolean equals(Object other) {
      throw new RuntimeException("cod2jar: type check");
   }
}
