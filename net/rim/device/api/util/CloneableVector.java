package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.Array;

public class CloneableVector extends Vector {
   public CloneableVector(int var1, int var2) {
   }

   public CloneableVector(int var1) {
      this(var1, 0);
   }

   public CloneableVector() {
      this(4);
   }

   public synchronized Object clone() {
      CloneableVector var1 = new CloneableVector();
      Array.resize(var1.elementData, super.elementCount);
      var1.elementCount = super.elementCount;
      var1.capacityIncrement = super.capacityIncrement;
      System.arraycopy(super.elementData, 0, var1.elementData, 0, super.elementCount);
      return var1;
   }

   public static final Vector clone(Vector var0) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
