package net.rim.device.api.util;

final class HashtableObjectEnumerator extends ObjectEnumerator {
   private Object _empty;

   HashtableObjectEnumerator(Object[] var1, Object var2) {
      super(var1);
      this.resetEnumeration(var1, var2);
   }

   final void resetEnumeration(Object[] var1, Object var2) {
      this.resetEnumeration(var1);
      this._empty = var2;
   }

   @Override
   protected final boolean getNextElement() {
      int var1 = super._index;

      for (int var2 = super._elements.length; var1 < var2; var1++) {
         if (super._elements[var1] != null && super._elements[var1] != this._empty) {
            super._index = var1;
            return true;
         }
      }

      super._index = var1;
      return false;
   }
}
