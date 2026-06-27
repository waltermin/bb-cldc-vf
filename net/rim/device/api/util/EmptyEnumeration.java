package net.rim.device.api.util;

import java.util.Enumeration;

public final class EmptyEnumeration implements Enumeration {
   @Override
   public final boolean hasMoreElements() {
      return false;
   }

   @Override
   public final Object nextElement() {
      throw new Object();
   }
}
