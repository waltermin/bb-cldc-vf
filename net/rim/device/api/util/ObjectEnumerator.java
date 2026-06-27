package net.rim.device.api.util;

import java.util.Enumeration;

public class ObjectEnumerator implements Enumeration {
   protected Object[] _elements;
   protected int _index;

   public ObjectEnumerator(Object[] var1) {
      this.resetEnumeration(var1);
   }

   protected void resetEnumeration(Object[] var1) {
      if (var1 == null) {
         throw new Object();
      }

      this._elements = var1;
      this._index = 0;
   }

   protected boolean getNextElement() {
      int var1 = this._index;

      for (int var2 = this._elements.length; var1 < var2; var1++) {
         if (this._elements[var1] != null) {
            this._index = var1;
            return true;
         }
      }

      this._index = var1;
      return false;
   }

   @Override
   public boolean hasMoreElements() {
      return this.getNextElement();
   }

   @Override
   public Object nextElement() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
