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
      throw new RuntimeException("cod2jar: type check");
   }

   void addActiveInvocation(int pid, Invocation invocation) {
      throw new RuntimeException("cod2jar: type check");
   }

   void removeActiveInvocation(int pid, Invocation invocation) {
      throw new RuntimeException("cod2jar: type check");
   }

   boolean requestHandlerStarted(int pid, ContentHandlerServerImpl handler) {
      InvocationCleanupManager$HandlerStatus status = (InvocationCleanupManager$HandlerStatus)this._activeRequestHandlers.get(pid);
      if (status == null) {
         this._activeRequestHandlers.put(pid, new InvocationCleanupManager$HandlerStatus(handler));
         return true;
      } else {
         status.invocationsAdded = true;
         return false;
      }
   }

   boolean responseHandlerStarted(int pid, RegistryImpl registry) {
      InvocationCleanupManager$HandlerStatus status = (InvocationCleanupManager$HandlerStatus)this._activeResponseHandlers.get(pid);
      if (status == null) {
         this._activeResponseHandlers.put(pid, new InvocationCleanupManager$HandlerStatus(registry));
         return true;
      } else {
         status.invocationsAdded = true;
         return false;
      }
   }

   void requestRetreived(int pid) {
      InvocationCleanupManager$HandlerStatus status = (InvocationCleanupManager$HandlerStatus)this._activeRequestHandlers.get(pid);
      if (status != null) {
         status.canExit = true;
      }
   }

   void responseRetreived(int pid) {
      InvocationCleanupManager$HandlerStatus status = (InvocationCleanupManager$HandlerStatus)this._activeResponseHandlers.get(pid);
      if (status != null) {
         status.canExit = true;
      }
   }

   void addContentHandlerModule(int moduleHandle, String classname, boolean dynamic) {
      throw new RuntimeException("cod2jar: type check");
   }

   void removeContentHandler(int moduleHandle, String classname) {
      throw new RuntimeException("cod2jar: type check");
   }

   void moduleUpgraded(String moduleName, int moduleHandle) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      throw new RuntimeException("cod2jar: type check");
   }

   static InvocationCleanupManager getInstance() {
      if (_instance == null) {
         ApplicationRegistry ar = ApplicationRegistry.getApplicationRegistry();
         _instance = (InvocationCleanupManager)ar.getOrWaitFor(-863891308161769843L);
         if (_instance == null) {
            _instance = new InvocationCleanupManager();
            ar.put(-863891308161769843L, _instance);
         }
      }

      return _instance;
   }

   private InvocationCleanupManager() {
      this._crc = CodeStore.getModuleHandles(this._currentHandles);
   }
}
