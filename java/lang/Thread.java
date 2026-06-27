package java.lang;

public class Thread implements Runnable {
   private int priority;
   private int flags;
   private Runnable target;
   private Object threadSpecificData;
   private String name;
   public static final int MIN_PRIORITY;
   public static final int NORM_PRIORITY;
   public static final int MAX_PRIORITY;

   public synchronized void start() {
      this.start0();
   }

   public final void join() {
      while (this.isAlive()) {
         sleep(0);
      }
   }

   public void interrupt() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public final native boolean isAlive();

   public final void setPriority(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public final int getPriority() {
      return this.priority;
   }

   public final String getName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void run() {
      if (this.target != null) {
         this.target.run();
      }
   }

   public static native Thread currentThread();

   public Thread(String var1) {
      this.init(null, var1, true);
   }

   public static native void sleep(long var0);

   private void init(Runnable var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public Thread() {
      this.init(null, null, false);
   }

   public Thread(Runnable var1) {
      this.init(var1, null, false);
   }

   public Thread(Runnable var1, String var2) {
      this.init(var1, var2, true);
   }

   public static native int activeCount();

   public static native void yield();

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private native void setPriority0(int var1);

   private native void interrupt0();

   private native void start0();
}
