package net.rim.vm;

public class WeakReference {
   private Object reference;
   private Object next;

   private native void register();

   public WeakReference(Object var1) {
      this.reference = var1;
      this.register();
   }

   public Object get() {
      return this.reference;
   }

   public void set(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
