package net.rim.device.api.util;

public class IntStack extends IntVector implements Persistable {
   public IntStack() {
   }

   public IntStack(int var1) {
   }

   public int push(int var1) {
      this.addElement(var1);
      return var1;
   }

   public int pop() {
      int var1 = this.size() - 1;
      int var2 = this.elementAt(var1);
      this.removeElementAt(var1);
      return var2;
   }

   public int peek() {
      int var1 = this.size() - 1;
      return this.elementAt(var1);
   }

   public int search(int var1) {
      int var2 = this.lastIndexOf(var1);
      return var2 >= 0 ? this.size() - var2 : -1;
   }
}
