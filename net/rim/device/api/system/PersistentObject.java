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

   public static final void commit(Object obj) {
      Persistence.commit(obj, false);
   }

   public static final void forceCommit(Object obj) {
      Persistence.commit(obj, true);
   }

   public final synchronized Object getContents() {
      int caller = TraceBack.getCallingModule(0);
      return this.getContents(caller, null, null);
   }

   public final synchronized Object getContents(CodeSigningKey readAndReplaceKey) {
      int caller = TraceBack.getCallingModule(0);
      return this.getContents(caller, readAndReplaceKey, readAndReplaceKey);
   }

   public final Object getContents(CodeSigningKey readKey, CodeSigningKey replaceKey) {
      int caller = TraceBack.getCallingModule(0);
      return this.getContents(caller, readKey, replaceKey);
   }

   private final synchronized Object getContents(int moduleHandle, CodeSigningKey readKey, CodeSigningKey replaceKey) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final synchronized void setContents(Object contents) {
      int caller = TraceBack.getCallingModule(0);
      this.setContents(caller, contents);
   }

   public final void setContents(Object contents, int signerId) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setContents(Object contents, int signerId, boolean preventReadAccess) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized void setContents(int moduleHandle, Object contents) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final ControlledAccess getControlledAccess() {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setReservedMemorySize(int size) {
      if (size < 0) {
         throw new Object();
      }

      super._reservedMemorySize = size;
      this.forceCommit();
   }
}
