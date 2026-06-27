package net.rim.device.internal.applicationcontrol;

import java.util.Enumeration;
import java.util.Vector;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.IntHashtable;

final class StackTracePermissions {
   private IntHashtable _delegate;
   private PersistentObject _persistentDelegate;
   private static final long STACK_HASHES_KEY;

   final void setResponse(int var1, int[] var2, int var3, int var4, boolean var5) {
      byte[] var6 = this.getHashOfStackTrace(var2);
      if (var6 != null) {
         Object var7 = this.getResponses(var1);
         if (var7 == null) {
            var7 = new Object();
            this._delegate.put(var1, var7);
         }

         Object var8 = this.getResponse((Vector)var7, var6);
         if (var8 == null) {
            var8 = new Object(var6);
            ((Vector)var7).addElement(var8);
         }

         ((CachedStackTraceResponse)var8).setAllowed(var3, var4, var5);
         this.persistChanges();
      }
   }

   final int getResponsePermission(int var1, int[] var2, int var3, int var4) {
      byte[] var5 = this.getHashOfStackTrace(var2);
      CachedStackTraceResponse var6 = this.getResponse(var1, var5);
      return CachedStackTraceResponse.responseToPermission(var6, var3, var4);
   }

   final void removeAllResponses() {
      this._delegate.clear();
      this.persistChanges();
   }

   final void removeAllResponses(int var1, int var2) {
      Enumeration var3 = this._delegate.elements();
      Object var4 = null;

      while (var3.hasMoreElements()) {
         var4 = var3.nextElement();
         this.removeResponses((Vector)var4, var1, var2);
      }

      this.persistChanges();
   }

   final void removeResponses(int var1) {
      this._delegate.remove(var1);
      this.persistChanges();
   }

   final void removeResponses(int var1, int var2, int var3) {
      this.removeResponses(this.getResponses(var1), var2, var3);
      this.persistChanges();
   }

   private final CachedStackTraceResponse getResponse(int var1, byte[] var2) {
      Vector var3 = this.getResponses(var1);
      return var3 == null ? null : this.getResponse(var3, var2);
   }

   private final CachedStackTraceResponse getResponse(Vector var1, byte[] var2) {
      Enumeration var3 = var1.elements();
      Object var4 = null;

      while (var3.hasMoreElements()) {
         var4 = var3.nextElement();
         if (((CachedStackTraceResponse)var4).equals(var2)) {
            return (CachedStackTraceResponse)var4;
         }
      }

      return null;
   }

   private final Vector getResponses(int var1) {
      return (Vector)this._delegate.get(var1);
   }

   private final void removeResponses(Vector var1, int var2, int var3) {
      Enumeration var4 = var1.elements();
      Object var5 = null;

      while (var4.hasMoreElements()) {
         var5 = var4.nextElement();
         ((CachedStackTraceResponse)var5).reset(var2, var3);
      }
   }

   private final void persistChanges() {
      this._persistentDelegate.commit();
   }

   private final byte[] getHashOfStackTrace(int[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
