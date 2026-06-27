package java.lang;

import java.io.PrintStream;

public final class System {
   public static final PrintStream out;
   public static final PrintStream err;

   private System() {
   }

   private static final PrintStream getOutput() {
      return new PrintStream(new DebugOutputStream());
   }

   public static final native long currentTimeMillis();

   public static final native void arraycopy(Object var0, int var1, Object var2, int var3, int var4);

   public static final native int identityHashCode(Object var0);

   public static final String getProperty(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void exit(int var0) {
      Runtime.getRuntime().exit(var0);
   }

   public static final void gc() {
      Runtime.getRuntime().gc();
   }
}
