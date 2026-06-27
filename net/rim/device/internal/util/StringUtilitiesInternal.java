package net.rim.device.internal.util;

import net.rim.device.api.util.WeakReferenceUtilities;
import net.rim.vm.WeakReference;

public final class StringUtilitiesInternal {
   private static final long SCRATCH_KEY;
   private static WeakReference _scratchBufferWR;

   private StringUtilitiesInternal() {
   }

   public static final StringBuffer getScratchBuffer() {
      return WeakReferenceUtilities.getStringBuffer(_scratchBufferWR);
   }
}
