package javax.microedition.content;

import java.util.Hashtable;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.system.CodeStore;

class InvocationCleanupManager implements GlobalEventListener {
   private IntHashtable _activeInvocations = (IntHashtable)(new Object());
   private IntHashtable _activeRequestHandlers = (IntHashtable)(new Object());
   private IntHashtable _activeResponseHandlers = (IntHashtable)(new Object());
   private IntHashtable _contentHandlerModules = (IntHashtable)(new Object());
   private Hashtable _moduleNameToHandleMap = (Hashtable)(new Object());
   private Hashtable _classnameToRegistrationTypeMap = (Hashtable)(new Object());
   private int _crc;
   private int[] _currentHandles = new int[0];
   private static final long INVOCATION_CLEANUP_MANAGER_ID;
   private static InvocationCleanupManager _instance;

   void checkModules() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void addActiveInvocation(int var1, Invocation var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   void removeActiveInvocation(int var1, Invocation var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   boolean requestHandlerStarted(int var1, ContentHandlerServerImpl var2) {
      InvocationCleanupManager$HandlerStatus var3 = (InvocationCleanupManager$HandlerStatus)this._activeRequestHandlers.get(var1);
      if (var3 == null) {
         this._activeRequestHandlers.put(var1, new InvocationCleanupManager$HandlerStatus(var2));
         return true;
      } else {
         var3.invocationsAdded = true;
         return false;
      }
   }

   boolean responseHandlerStarted(int var1, RegistryImpl var2) {
      InvocationCleanupManager$HandlerStatus var3 = (InvocationCleanupManager$HandlerStatus)this._activeResponseHandlers.get(var1);
      if (var3 == null) {
         this._activeResponseHandlers.put(var1, new InvocationCleanupManager$HandlerStatus(var2));
         return true;
      } else {
         var3.invocationsAdded = true;
         return false;
      }
   }

   void requestRetreived(int var1) {
      InvocationCleanupManager$HandlerStatus var2 = (InvocationCleanupManager$HandlerStatus)this._activeRequestHandlers.get(var1);
      if (var2 != null) {
         var2.canExit = true;
      }
   }

   void responseRetreived(int var1) {
      InvocationCleanupManager$HandlerStatus var2 = (InvocationCleanupManager$HandlerStatus)this._activeResponseHandlers.get(var1);
      if (var2 != null) {
         var2.canExit = true;
      }
   }

   void addContentHandlerModule(int var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   void removeContentHandler(int var1, String var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void moduleUpgraded(String var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: type check");
   }

   static InvocationCleanupManager getInstance() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _instance = (InvocationCleanupManager)var0.getOrWaitFor(-863891308161769843L);
         if (_instance == null) {
            _instance = new InvocationCleanupManager();
            var0.put(-863891308161769843L, _instance);
         }
      }

      return _instance;
   }

   private InvocationCleanupManager() {
      this._crc = CodeStore.getModuleHandles(this._currentHandles);
   }
}
