package javax.microedition.content;

final class InvocationCleanupManager$HandlerStatus {
   public boolean canExit;
   public boolean invocationsAdded;
   public ContentHandlerServerImpl handler;
   public RegistryImpl registry;

   private InvocationCleanupManager$HandlerStatus() {
      this.canExit = false;
      this.invocationsAdded = false;
   }

   public InvocationCleanupManager$HandlerStatus(ContentHandlerServerImpl var1) {
      this.handler = var1;
   }

   public InvocationCleanupManager$HandlerStatus(RegistryImpl var1) {
      this.registry = var1;
   }
}
