package net.rim.device.api.memorycleaner;

public final class MemoryCleanerAction {
   private Object _listener;

   public MemoryCleanerAction(Object var1) {
      this._listener = var1;
   }

   public final MemoryCleanerListener getListener() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final boolean doAction(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean doAction() {
      return this.doAction(4);
   }

   public final String getDescription() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }
}
