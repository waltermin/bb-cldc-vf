package net.rim.device.api.util;

import java.util.Enumeration;
import net.rim.device.api.system.PersistentContent;

class ContentProtectedEnumeration implements Enumeration {
   private Enumeration _enum;

   public ContentProtectedEnumeration(Enumeration var1) {
      this._enum = var1;
   }

   @Override
   public boolean hasMoreElements() {
      return this._enum.hasMoreElements();
   }

   @Override
   public Object nextElement() {
      return PersistentContent.decode(this._enum.nextElement());
   }
}
