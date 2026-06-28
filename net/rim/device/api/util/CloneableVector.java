package net.rim.device.api.util;

import java.util.Vector;
import net.rim.vm.Array;

public class CloneableVector extends Vector {
   public CloneableVector(int initialCapacity, int capacityIncrement) {
      super(initialCapacity, capacityIncrement);
   }

   public CloneableVector(int initialCapacity) {
      this(initialCapacity, 0);
   }

   public CloneableVector() {
      this(4);
   }

   public synchronized Object clone() {
      CloneableVector v = new CloneableVector();
      Array.resize(v.elementData, super.elementCount);
      v.elementCount = super.elementCount;
      v.capacityIncrement = super.capacityIncrement;
      System.arraycopy(super.elementData, 0, v.elementData, 0, super.elementCount);
      return v;
   }

   public static final Vector clone(Vector vector) {
      throw new RuntimeException("cod2jar: type check");
   }
}
