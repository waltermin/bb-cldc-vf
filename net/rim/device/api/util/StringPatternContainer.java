package net.rim.device.api.util;

import net.rim.device.api.collection.ReadableList;
import net.rim.vm.Array;

public final class StringPatternContainer implements ReadableList {
   private StringPattern[] _elements;

   final StringPattern[] getElements() {
      return this._elements;
   }

   @Override
   public final Object getAt(int var1) {
      return this._elements[var1];
   }

   @Override
   public final int getAt(int var1, int var2, Object[] var3, int var4) {
      int var5 = this._elements.length;
      if (var2 > var5 - var1) {
         var2 = var5 - var1;
      }

      if (var3.length < var2 + var4) {
         Array.resize(var3, var2 + var4);
      }

      System.arraycopy(this._elements, var1, var3, var4, var2);
      return var2;
   }

   @Override
   public final int getIndex(Object var1) {
      return Arrays.getIndex(this._elements, var1);
   }

   @Override
   public final int size() {
      return this._elements.length;
   }

   public StringPatternContainer(StringPattern[] var1) {
      this._elements = var1;
   }
}
