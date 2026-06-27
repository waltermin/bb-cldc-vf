package net.rim.device.api.system;

import net.rim.device.api.util.Persistable;
import net.rim.vm.Persistence;
import net.rim.vm.PersistentRootObject;
import net.rim.vm.TraceBack;

public final class PersistentObject extends PersistentRootObject implements Persistable {
   PersistentObject() {
   }

   public final void commit() {
      Persistence.commit(this, false);
   }

   public final void forceCommit() {
      Persistence.commit(this, true);
   }

   public static final void commit(Object var0) {
      Persistence.commit(var0, false);
   }

   public static final void forceCommit(Object var0) {
      Persistence.commit(var0, true);
   }

   public final synchronized Object getContents() {
      int var1 = TraceBack.getCallingModule(0);
      return this.getContents(var1, null, null);
   }

   public final synchronized Object getContents(CodeSigningKey var1) {
      int var2 = TraceBack.getCallingModule(0);
      return this.getContents(var2, var1, var1);
   }

   public final Object getContents(CodeSigningKey var1, CodeSigningKey var2) {
      int var3 = TraceBack.getCallingModule(0);
      return this.getContents(var3, var1, var2);
   }

   private final synchronized Object getContents(int var1, CodeSigningKey var2, CodeSigningKey var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void setContents(Object var1) {
      int var2 = TraceBack.getCallingModule(0);
      this.setContents(var2, var1);
   }

   public final void setContents(Object var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setContents(Object var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized void setContents(int var1, Object var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final ControlledAccess getControlledAccess() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setReservedMemorySize(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      super._reservedMemorySize = var1;
      this.forceCommit();
   }
}
