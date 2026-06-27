package net.rim.device.api.util;

import java.util.Enumeration;
import net.rim.device.api.system.PersistentContent;

class ContentProtectedLookup$MyEnumeration implements Enumeration {
   private Enumeration _e;

   ContentProtectedLookup$MyEnumeration(Enumeration var1) {
      this._e = var1;
   }

   @Override
   public boolean hasMoreElements() {
      return this._e.hasMoreElements();
   }

   @Override
   public Object nextElement() {
      return PersistentContent.decode(this._e.nextElement());
   }
}
