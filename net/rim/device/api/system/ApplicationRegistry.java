package net.rim.device.api.system;

import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntVector;
import net.rim.device.api.util.LongHashtable;
import net.rim.device.internal.system.ApplicationRegistryHashtable;
import net.rim.vm.Process;

public final class ApplicationRegistry {
   private ApplicationRegistryHashtable _registry = (ApplicationRegistryHashtable)(new Object(1223));
   private LongHashtable _monitors = (LongHashtable)(new Object());
   private boolean _startupComplete;
   public static final int MAX_WAIT_MILLIS;
   private static final int TYPE_HASHTABLE;
   private static final int TYPE_LONG_HASHTABLE;
   private static final int TYPE_INT_HASHTABLE;
   private static final int TYPE_VECTOR;
   private static final int TYPE_INT_VECTOR;
   private static final int TYPE_OBJECT;

   ApplicationRegistry() {
      Process.registerAppRegistry(this);
   }

   public final Object get(long var1) {
      return this.get(0, var1, true, null, null);
   }

   public final Object getOrWaitFor(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final Object get(int var1, long var2, boolean var4, CodeSigningKey var5, CodeSigningKey var6) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final Object remove(long var1) {
      return this.remove(0, var1, true, null, null);
   }

   final Object remove(int var1, long var2, boolean var4, CodeSigningKey var5, CodeSigningKey var6) {
      Object var7 = this.get(var1, var2, var4, var5, var6);
      if (var7 != null) {
         this._registry.remove(var2, var4);
      }

      return var7;
   }

   final void kickAllWaitingThreads() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final ControlledAccess getControlledAccess(long var1) {
      return this.getControlledAccess(var1, true);
   }

   final ControlledAccess getControlledAccess(long var1, boolean var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void put(long var1, Object var3) {
      this.put(0, var1, true, var3, false);
   }

   public final Object replace(long var1, Object var3) {
      return this.put(0, var1, true, var3, true);
   }

   final Object put(int var1, long var2, boolean var4, Object var5, boolean var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final Object waitFor(long var1) {
      return this.waitFor(0, var1, true, null, null, false);
   }

   public final Object waitForStartup(long var1) {
      return this.waitFor(0, var1, true, null, null, true);
   }

   final Object waitFor(int var1, long var2, boolean var4, CodeSigningKey var5, CodeSigningKey var6, boolean var7) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final Object waitForObjectToBeRegistered(Monitor var1, int var2, long var3, boolean var5, CodeSigningKey var6, CodeSigningKey var7, boolean var8) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final Vector getVector(long var1) {
      return (Vector)this.get(var1, 4);
   }

   public final IntVector getIntVector(long var1) {
      return (IntVector)this.get(var1, 5);
   }

   public final Hashtable getHashtable(long var1) {
      return (Hashtable)this.get(var1, 1);
   }

   public final IntHashtable getIntHashtable(long var1) {
      return (IntHashtable)this.get(var1, 3);
   }

   public final LongHashtable getLongHashtable(long var1) {
      return (LongHashtable)this.get(var1, 2);
   }

   public final Object getObject(long var1) {
      return this.get(var1, 6);
   }

   private final Object get(long var1, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final ApplicationRegistry getApplicationRegistry() {
      return (ApplicationRegistry)Process.getAppRegistry();
   }
}
