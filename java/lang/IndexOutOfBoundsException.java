package java.lang;

public class IndexOutOfBoundsException extends RuntimeException {
   private int index;
   private boolean indexSet;
   private int maxIndex;
   private boolean maxIndexSet;

   public IndexOutOfBoundsException() {
   }

   @Override
   public String getMessage() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public IndexOutOfBoundsException(String s) {
   }
}
