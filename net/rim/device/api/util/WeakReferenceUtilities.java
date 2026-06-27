package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.WeakReference;

public final class WeakReferenceUtilities {
   private WeakReferenceUtilities() {
   }

   public static final byte[] getByteArray(WeakReference var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final char[] getCharArray(WeakReference var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final StringBuffer getStringBuffer(WeakReference var0) {
      Object var1 = var0.get();
      if (var1 == null) {
         var1 = new Object();
         var0.set(var1);
      }

      return (StringBuffer)var1;
   }

   public static final DataBuffer getDataBuffer(WeakReference var0, boolean var1) {
      Object var2 = var0.get();
      if (var2 == null) {
         var2 = new Object(var1);
         var0.set(var2);
      }

      return (DataBuffer)var2;
   }

   public static final Object[] getObjectArray(WeakReference var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final String[] getStringArray(WeakReference var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final int incrementalWRArrayPurge(int var0, WeakReference[] var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void purge(IntHashtable var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void purge(Vector var0) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
