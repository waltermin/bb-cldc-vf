package net.rim.device.resources;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

public class Resource {
   private Hashtable _resources;
   private Hashtable _properties;
   private byte[] _icons;
   private String _name;

   protected Resource(Hashtable var1, Hashtable var2, byte[] var3) {
      this._resources = var1;
      this._properties = var2;
      this._icons = var3;
   }

   public static Resource getResourceClass() {
      return Resource$Internal.getResourceClass(TraceBack.getCallingModuleName(2), true);
   }

   public Enumeration getResourceKeys() {
      return this._resources != null ? this._resources.keys() : null;
   }

   private byte[] findResource(String var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public Vector listResourcesEndingWith(String var1) {
      Object var2 = new Object();
      if (this._resources != null) {
         Enumeration var3 = this._resources.keys();

         while (var3.hasMoreElements()) {
            String var4 = var3.nextElement().toString();
            if (var4.endsWith(var1)) {
               ((Vector)var2).addElement(var4);
            }
         }
      }

      return (Vector)var2;
   }

   public byte[] getResource(String var1) {
      byte[] var2 = this.findResource(var1);
      if (var2 == null) {
         int var3 = Process.getModuleHandle(this._name);
         int var4 = Process.getModuleHandle(Process.currentProcess().getModuleName());
         var2 = Resource$Internal.FindResourceInDependencyGraph(var3, var4, var1);
      }

      return var2;
   }

   public byte[] getProperty(String var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static int getIconOffset(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static int getIconLength(byte[] var0, int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static byte[] getIconBytes(byte[] var0, int var1) {
      int var2 = getIconOffset(var0, var1);
      int var3 = getIconLength(var0, var2);
      byte[] var4 = new byte[var3];
      System.arraycopy(var0, var2, var4, 0, var3);
      return var4;
   }

   public Object instantiateMIDlet(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
