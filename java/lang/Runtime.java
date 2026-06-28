package java.lang;

public class Runtime {
   private static Runtime currentRuntime;

   public static Runtime getRuntime() {
      return currentRuntime;
   }

   private Runtime() {
   }

   private native void exitInternal(int var1);

   public void exit(int status) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public native long freeMemory();

   public native long totalMemory();

   public native void gc();
}
