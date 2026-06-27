package javax.microedition.content;

import java.util.Hashtable;

class Transaction extends Hashtable {
   private Invocation _curr;

   Transaction(Invocation var1, RegistryImpl var2) {
      this.put(var1, var2);
      this._curr = var1;
   }

   synchronized Invocation getActiveInvocation() {
      return this._curr;
   }

   synchronized void append(Invocation var1, RegistryImpl var2) {
      this.put(var1, var2);
      this._curr = var1;
   }

   synchronized void remove(Invocation var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   synchronized void replaceActive(Invocation var1) {
      RegistryImpl var2 = (RegistryImpl)this.get(this._curr);
      super.remove(this._curr);
      this.put(var1, var2);
      this._curr = var1;
   }
}
