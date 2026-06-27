package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.Array;

public final class ListenerUtilities {
   private ListenerUtilities() {
   }

   public static final Vector addListener(Vector var0, Object var1) {
      if (var1 == null) {
         throw new Object();
      }

      Vector var2;
      if (var0 != null) {
         if (var0.contains(var1)) {
            return var0;
         }

         var2 = CloneableVector.clone(var0);
      } else {
         var2 = new CloneableVector(1, 2);
      }

      var2.addElement(var1);
      return var2;
   }

   public static final Vector fastAddListener(Vector var0, Object var1) {
      if (var1 == null) {
         throw new Object();
      }

      Vector var2;
      if (var0 != null) {
         if (var0.contains(var1)) {
            return var0;
         }

         var2 = var0;
      } else {
         var2 = new CloneableVector(1, 2);
      }

      var2.addElement(var1);
      return var2;
   }

   public static final Vector removeListener(Vector var0, Object var1) {
      if (var0 != null && var0.contains(var1)) {
         if (var0.size() == 1) {
            return null;
         }

         Vector var2 = CloneableVector.clone(var0);
         var2.removeElement(var1);
         return var2;
      } else {
         return var0;
      }
   }

   public static final Object[] addListener(Object[] var0, Object var1) {
      if (var1 == null) {
         throw new Object();
      }

      Object[] var2;
      int var3;
      if (var0 != null) {
         if (Arrays.contains(var0, var1)) {
            return var0;
         }

         var3 = var0.length;
         var2 = new Object[var3 + 1];
         System.arraycopy(var0, 0, var2, 0, var3);
      } else {
         var3 = 0;
         var2 = new Object[1];
      }

      var2[var3] = var1;
      return var2;
   }

   public static final Object[] fastAddListener(Object[] var0, Object var1) {
      if (var1 == null) {
         throw new Object();
      }

      Object[] var2;
      int var3;
      if (var0 != null) {
         if (Arrays.contains(var0, var1)) {
            return var0;
         }

         var3 = var0.length;
         Array.resize(var0, var3 + 1);
         var2 = var0;
      } else {
         var3 = 0;
         var2 = new Object[1];
      }

      var2[var3] = var1;
      return var2;
   }

   public static final Object[] removeListener(Object[] var0, Object var1) {
      if (var0 == null) {
         return var0;
      }

      int var2 = Arrays.getIndex(var0, var1);
      if (var2 == -1) {
         return var0;
      }

      int var3 = var0.length;
      if (var3 == 1) {
         return null;
      }

      Object[] var4 = new Object[--var3];
      System.arraycopy(var0, 0, var4, 0, var2);
      System.arraycopy(var0, var2 + 1, var4, var2, var3 - var2);
      return var4;
   }

   public static final boolean containsListener(Object[] var0, Object var1) {
      return var0 == null ? false : Arrays.getIndex(var0, var1) != -1;
   }
}
