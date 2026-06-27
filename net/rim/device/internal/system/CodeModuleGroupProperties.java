package net.rim.device.internal.system;

import java.util.Hashtable;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.PersistentContent;
import net.rim.vm.Persistable;

public class CodeModuleGroupProperties extends Hashtable implements Persistable, SyncObject {
   private int _uid;

   public Object put(String var1, String var2) {
      Object var3 = PersistentContent.encode(var2);
      return super.put(var1, var3);
   }

   @Override
   public int getUID() {
      return this._uid;
   }

   public CodeModuleGroupProperties(int var1) {
      this._uid = var1;
   }

   @Override
   public Object get(Object var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
