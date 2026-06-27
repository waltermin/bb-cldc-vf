package java.lang;

public class Object {
   public final native Class getClass();

   public native int hashCode();

   public boolean equals(Object var1) {
      return this == var1;
   }

   public String toString() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final native void notify();

   public final native void notifyAll();

   public final native void wait(long var1);

   public final void wait(long var1, int var3) {
      if (var1 >= 0 && var3 >= 0 && var3 <= 999999) {
         if (var3 >= 500000 || var3 != 0 && var1 == 0) {
            var1 += 1;
         }

         this.wait(var1);
      } else {
         throw new Object();
      }
   }

   public final void wait() {
      this.wait(0);
   }
}
