package javax.microedition.content;

import java.util.Vector;

class InvocationQueue extends Vector {
   public InvocationQueue() {
   }

   synchronized void addInvocation(Invocation var1) {
      this.addElement(var1);
   }

   synchronized Invocation nextInvocation() {
      Invocation var1 = null;
      if (this.size() > 0) {
         var1 = (Invocation)this.elementAt(0);
         this.removeElementAt(0);
      }

      return var1;
   }
}
