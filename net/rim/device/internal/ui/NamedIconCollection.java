package net.rim.device.internal.ui;

public class NamedIconCollection extends IconCollection {
   private String _name;

   public NamedIconCollection(String var1, int var2, int var3, String var4) {
      super(var2, var3, var4);
      this._name = var1;
   }

   public String getName() {
      return this._name;
   }
}
