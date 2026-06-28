package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.WeakReference;

public final class WeakReferenceUtilities {
   private WeakReferenceUtilities() {
   }

   public static final byte[] getByteArray(WeakReference wr, int size) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final char[] getCharArray(WeakReference wr, int size) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final StringBuffer getStringBuffer(WeakReference wr) {
      StringBuffer buffer = (StringBuffer)wr.get();
      if (buffer == null) {
         buffer = (StringBuffer)(new Object());
         wr.set(buffer);
      }

      return buffer;
   }

   public static final DataBuffer getDataBuffer(WeakReference wr, boolean bigEndianFlag) {
      DataBuffer buffer = (DataBuffer)wr.get();
      if (buffer == null) {
         buffer = (DataBuffer)(new Object(bigEndianFlag));
         wr.set(buffer);
      }

      return buffer;
   }

   public static final Object[] getObjectArray(WeakReference wr, int size) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final String[] getStringArray(WeakReference wr, int size) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final int incrementalWRArrayPurge(int curr, WeakReference[] wr) {
      synchronized (wr) {
         int len = wr.length;
         if (len > 0) {
            int n = len == 1 ? 1 : 2;

            for (int i = 0; i < n; i++) {
               if (++curr >= len) {
                  curr = 0;
               }

               WeakReference w = wr[curr];
               if (w == null || w.get() == null) {
                  Arrays.removeAt(wr, curr);
                  len--;
               }
            }
         }

         return curr;
      }
   }

   public static final void purge(IntHashtable ht) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final void purge(Vector v) {
      synchronized (v) {
         for (int i = v.size() - 1; i >= 0; i--) {
            WeakReference wr = (WeakReference)v.elementAt(i);
            if (wr.get() == null) {
               v.removeElementAt(i);
            }
         }
      }
   }
}
