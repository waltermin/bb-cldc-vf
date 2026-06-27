package java.util;

final class VectorEnumerator implements Enumeration {
   Vector vector;
   int count;

   VectorEnumerator(Vector var1) {
      this.vector = var1;
      this.count = 0;
   }

   @Override
   public final boolean hasMoreElements() {
      return this.count < this.vector.elementCount;
   }

   @Override
   public final Object nextElement() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
