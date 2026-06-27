package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.vm.TraceBack;

public final class RuntimeStore {
   private ApplicationRegistry _ar;
   private static final long GUID;

   private RuntimeStore(ApplicationRegistry var1) {
      this._ar = var1;
   }

   public final Object get(long var1) {
      assertPermission();
      int var3 = TraceBack.getCallingModule(0);
      return this._ar.get(var3, var1, false, null, null);
   }

   public final Object get(long var1, CodeSigningKey var3) {
      assertPermission();
      int var4 = TraceBack.getCallingModule(0);
      return this._ar.get(var4, var1, false, var3, var3);
   }

   public final Object get(long var1, CodeSigningKey var3, CodeSigningKey var4) {
      assertPermission();
      int var5 = TraceBack.getCallingModule(0);
      return this._ar.get(var5, var1, false, var3, var4);
   }

   public final ControlledAccess getControlledAccess(long var1) {
      assertPermission();
      return this._ar.getControlledAccess(var1, false);
   }

   public final void put(long var1, Object var3) {
      assertPermission();
      int var4 = TraceBack.getCallingModule(0);
      this._ar.put(var4, var1, false, var3, false);
   }

   public final Object replace(long var1, Object var3) {
      assertPermission();
      int var4 = TraceBack.getCallingModule(0);
      return this._ar.put(var4, var1, false, var3, true);
   }

   public final Object remove(long var1) {
      assertPermission();
      int var3 = TraceBack.getCallingModule(0);
      return this._ar.remove(var3, var1, false, null, null);
   }

   public final Object remove(long var1, CodeSigningKey var3) {
      assertPermission();
      int var4 = TraceBack.getCallingModule(0);
      return this._ar.remove(var4, var1, false, var3, var3);
   }

   public final Object remove(long var1, CodeSigningKey var3, CodeSigningKey var4) {
      assertPermission();
      int var5 = TraceBack.getCallingModule(0);
      return this._ar.remove(var5, var1, false, var3, var4);
   }

   public final Object waitFor(long var1) {
      assertPermission();
      int var3 = TraceBack.getCallingModule(0);
      return this._ar.waitFor(var3, var1, false, null, null, false);
   }

   public final Object waitFor(long var1, CodeSigningKey var3) {
      assertPermission();
      int var4 = TraceBack.getCallingModule(0);
      return this._ar.waitFor(var4, var1, false, var3, var3, false);
   }

   public final Object waitFor(long var1, CodeSigningKey var3, CodeSigningKey var4) {
      assertPermission();
      int var5 = TraceBack.getCallingModule(0);
      return this._ar.waitFor(var5, var1, false, var3, var4, false);
   }

   private static final void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }

   public static final RuntimeStore getRuntimeStore() {
      assertPermission();
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      RuntimeStore var1 = (RuntimeStore)var0.getOrWaitFor(-4040261540098774066L);
      if (var1 == null) {
         var1 = new RuntimeStore(var0);
         var0.put(-4040261540098774066L, var1);
      }

      return var1;
   }
}
