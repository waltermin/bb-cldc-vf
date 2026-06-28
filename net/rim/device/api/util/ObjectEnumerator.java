package net.rim.device.api.util;

import java.util.Enumeration;

public class ObjectEnumerator implements Enumeration {
   protected Object[] _elements;
   protected int _index;

   public ObjectEnumerator(Object[] elements) {
      this.resetEnumeration(elements);
   }

   protected void resetEnumeration(Object[] elements) {
      if (elements == null) {
         throw new Object();
      }

      this._elements = elements;
      this._index = 0;
   }

   protected boolean getNextElement() {
      int i = this._index;

      for (int len = this._elements.length; i < len; i++) {
         if (this._elements[i] != null) {
            this._index = i;
            return true;
         }
      }

      this._index = i;
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
