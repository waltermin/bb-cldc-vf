package net.rim.vm;

public class ThreadSpecificData {
   private WeakReference[] data = new WeakReference[3];
   public static final int ECMA_GLOBAL_OBJECT;
   public static final int APPCONTROL_GLOBAL_OBJECT;
   public static final int JAVASCRIPT_ENGINE_GLOBAL_OBJECT;
   private static final int NUM_DATA_SLOTS;

   private static native ThreadSpecificData getData(Thread var0);

   private static native void setData(Thread var0, ThreadSpecificData var1);

   private static ThreadSpecificData get(Thread var0) {
      ThreadSpecificData var1 = getData(var0);
      if (var1 == null) {
         var1 = new ThreadSpecificData();
         setData(var0, var1);
      }

      return var1;
   }

   public static Object get(Thread var0, int var1) {
      ThreadSpecificData var2 = get(var0);
      WeakReference var3 = var2.data[var1];
      return var3 == null ? null : var3.get();
   }

   public static void set(Thread var0, int var1, Object var2) {
      ThreadSpecificData var3 = get(var0);
      WeakReference var4 = var3.data[var1];
      if (var4 == null) {
         var4 = new WeakReference(var2);
         var3.data[var1] = var4;
      } else {
         var4.set(var2);
      }
   }
}
