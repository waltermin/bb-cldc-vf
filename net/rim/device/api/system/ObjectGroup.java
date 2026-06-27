package net.rim.device.api.system;

public final class ObjectGroup {
   private ObjectGroup() {
   }

   public static final void createGroup(Object var0) {
      if (!net.rim.vm.Memory.createGroup(var0)) {
         throw new ObjectGroupTooBigException();
      }
   }

   public static final void createGroupIgnoreTooBig(Object var0) {
      net.rim.vm.Memory.createGroup(var0);
   }

   public static final Object expandGroup(Object var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isInGroup(Object var0) {
      return net.rim.vm.Memory.isObjectInGroup(var0);
   }
}
