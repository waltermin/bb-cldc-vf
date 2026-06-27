package javax.microedition.content;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.CodeModuleGroup;
import net.rim.device.api.system.CodeModuleGroupManager;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.StringUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.vm.Array;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

class RegistryImpl extends Registry {
   private String _classname;
   private String _authority;
   private ResponseListener _listener;
   private InvocationQueue _queue;
   private ApplicationDescriptor _application;
   private int _numStaleInvocations;
   private static final long REGISTRY_KEY;
   private static final long TRANSACTIONS_KEY;
   private static final long REGISTRY_IMPLS_KEY;
   private static final long PERSISTENT_KEY;
   private static final int REGISTRY_INDEX;
   private static final int BY_TYPE_INDEX;
   private static final int BY_ACTION_INDEX;
   private static final int BY_SUFFIX_INDEX;
   private static final int BY_ID_INDEX;
   private static final int REGISTRY_ARRAY_SIZE;
   private static Hashtable _registry;
   private static PersistentObject _persist;
   private static Hashtable _handlersByType;
   private static Hashtable _handlersByAction;
   private static Hashtable _handlersBySuffix;
   private static Hashtable _handlersByID;
   private static Transaction[] _transactions;
   private static Hashtable _registryImpls;
   private static Hashtable _persistedHandlers;

   private RegistryImpl(String var1) {
      this._classname = var1;
      this._queue = (InvocationQueue)(new Object());
   }

   static RegistryImpl getRegistryImpl() {
      return new RegistryImpl(null);
   }

   public static Registry getRegistry(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static ContentHandlerServer getServer(String var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public ContentHandlerServer register(String var1, String[] var2, String[] var3, String[] var4, ActionNameMap[] var5, String var6, String[] var7) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   void registerInternal(
      String var1, String[] var2, String[] var3, String[] var4, ActionNameMap[] var5, String var6, String[] var7, ApplicationDescriptor var8, int var9
   ) {
      Object var10 = new Object();
      ((ContentHandlerServerImpl)var10).init(var2, var3, var4, var5, var6, var7);
      verifyID(((ContentHandlerServerImpl)var10).getID(), var1, false);
      ((ContentHandlerServerImpl)var10).setApplicationDescriptor(var8);
      ((ContentHandlerServerImpl)var10).setAppName(getAppName(var9, var1, var8));
      ((ContentHandlerServerImpl)var10).setVersion(var8.getVersion());
      ((ContentHandlerServerImpl)var10).setAuthority(getAuthority(var9));
      ((ContentHandlerServerImpl)var10).setClassname(var1);
      ((ContentHandlerServerImpl)var10).setModuleHandle(var9);
      ((ContentHandlerServerImpl)var10).setDynamic(false);
      this.registerAndPersistHandler(var1, (ContentHandlerServerImpl)var10);
      InvocationCleanupManager.getInstance().addContentHandlerModule(var9, var1, false);
   }

   private void registerAndPersistHandler(String var1, ContentHandlerServerImpl var2) {
      registerHandler(var1, var2);
      DataBuffer var3 = ContentHandlerConverter.convert(var2);
      if (var3 != null) {
         _persistedHandlers.put(var1, var3);
         _persist.commit();
      }
   }

   private static void registerHandler(String var0, ContentHandlerServer var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static void addOrReplaceHandler(Vector var0, ContentHandlerServer var1) {
      int var2 = var0.size();

      for (int var3 = 0; var3 < var2; var3++) {
         Object var4 = var0.elementAt(var3);
         if (((ContentHandlerServer)var4).equals(var1)) {
            return;
         }

         if (((ContentHandler)var4).getID().equals(var1.getID())) {
            var0.setElementAt(var1, var3);
            return;
         }
      }

      var0.addElement(var1);
   }

   @Override
   public boolean unregister(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   boolean unregisterInternal(String var1) {
      Object var2 = null;
      if (_registry.containsKey(var1)) {
         var2 = _registry.remove(var1);
         this.removeHandler(_handlersByType, (ContentHandler)var2);
         this.removeHandler(_handlersByAction, (ContentHandler)var2);
         this.removeHandler(_handlersBySuffix, (ContentHandler)var2);
         _handlersByID.remove(((ContentHandlerServerImpl)var2).getID());
         _persistedHandlers.remove(var1);
         _persist.commit();
         InvocationCleanupManager.getInstance().removeContentHandler(((ContentHandlerServerImpl)var2).getModuleHandle(), var1);
         return true;
      } else {
         return false;
      }
   }

   private void removeHandler(Hashtable var1, ContentHandler var2) {
      Enumeration var3 = var1.keys();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         Object var5 = var1.get(var4);
         ((Vector)var5).removeElement(var2);
         if (((Vector)var5).size() == 0) {
            var1.remove(var4);
         }
      }
   }

   @Override
   public String[] getTypes() {
      return this.retrieveAccessibleKeys(_handlersByType);
   }

   @Override
   public String[] getIDs() {
      String[] var1 = new String[0];
      Enumeration var2 = _handlersByID.keys();

      while (var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         if (((ContentHandlerServer)_handlersByID.get(var3)).isAccessAllowed(this.getID())) {
            Array.resize(var1, var1.length + 1);
            var1[var1.length - 1] = (String)var3;
         }
      }

      return var1;
   }

   @Override
   public String[] getActions() {
      return this.retrieveAccessibleKeys(_handlersByAction);
   }

   @Override
   public String[] getSuffixes() {
      return this.retrieveAccessibleKeys(_handlersBySuffix);
   }

   private String[] retrieveAccessibleKeys(Hashtable var1) {
      String[] var2 = new String[0];
      Enumeration var3 = var1.keys();

      while (var3.hasMoreElements()) {
         Object var4 = var3.nextElement();
         Object var5 = var1.get(var4);
         int var6 = ((Vector)var5).size();

         for (int var7 = 0; var7 < var6; var7++) {
            Object var8 = ((Vector)var5).elementAt(var7);
            if (((ContentHandlerServer)var8).isAccessAllowed(this.getID())) {
               Array.resize(var2, var2.length + 1);
               var2[var2.length - 1] = (String)var4;
               break;
            }
         }
      }

      return var2;
   }

   @Override
   public ContentHandler[] forType(String var1) {
      return this.retrieveAccessibleHandlers(_handlersByType, StringUtilities.toLowerCase(var1, 1701707776));
   }

   @Override
   public ContentHandler[] forAction(String var1) {
      return this.retrieveAccessibleHandlers(_handlersByAction, var1);
   }

   @Override
   public ContentHandler[] forSuffix(String var1) {
      return this.retrieveAccessibleHandlers(_handlersBySuffix, StringUtilities.toLowerCase(var1, 1701707776));
   }

   private ContentHandler[] retrieveAccessibleHandlers(Hashtable var1, String var2) {
      ContentHandler[] var3 = new ContentHandler[0];
      Object var4 = var1.get(var2);
      if (var4 != null) {
         int var5 = ((Vector)var4).size();

         for (int var6 = 0; var6 < var5; var6++) {
            Object var7 = ((Vector)var4).elementAt(var6);
            if (((ContentHandlerServerImpl)var7).isAccessAllowed(this.getID())) {
               Array.resize(var3, var3.length + 1);
               var3[var3.length - 1] = (ContentHandler)(new Object((ContentHandlerImpl)var7));
            }
         }
      }

      return var3;
   }

   @Override
   public ContentHandler forID(String var1, boolean var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public ContentHandler[] findHandler(Invocation var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private ContentHandler[] filterByAction(ContentHandler[] var1, String var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean invoke(Invocation var1) {
      return this.invoke(var1, null);
   }

   @Override
   public boolean invoke(Invocation var1, Invocation var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean reinvoke(Invocation var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private ContentHandlerImpl findAppropriateHandler(ContentHandler[] var1) {
      int var2 = 0;
      int var3 = -1;

      while (var1.length > 1) {
         ContentHandler[] var4 = new ContentHandler[0];
         var3 = this._classname.indexOf(46, var2);
         if (var3 == -1) {
            break;
         }

         var2 = var3;

         for (int var5 = 0; var5 < var1.length; var5++) {
            Object var6 = var1[var5];
            if (((ContentHandlerImpl)var6).getClassname().startsWith(this._classname.substring(0, var3))) {
               Arrays.add(var4, var6);
            }
         }

         if (var4.length == 0) {
            break;
         }

         var1 = var4;
      }

      return (ContentHandlerImpl)var1[0];
   }

   @Override
   public Invocation getResponse(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void cancelGetResponse() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void addResponse(Invocation var1) {
      this._queue.addInvocation(var1);
      this.respond();
   }

   void respond() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void removeOldInvocations() {
      for (int var1 = 0; var1 < this._numStaleInvocations; var1++) {
         this._queue.removeElementAt(0);
      }
   }

   int getQueueSize() {
      return this._queue.size();
   }

   @Override
   public void setListener(ResponseListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private int wakeAppAndNotifyListener(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public String getID() {
      int var1 = TraceBack.getCallingModule(2);
      return getOrCreateID(var1, this._classname, false);
   }

   static void invocationFinished(Invocation var0, int var1) {
      var0.setStatus(var1);

      for (int var2 = 0; var2 < _transactions.length; var2++) {
         if (_transactions[var2].getActiveInvocation() == var0) {
            if (var0.getResponseRequired()) {
               Invocation var3 = var0.getOriginal();
               var3.setURL(var0.getURL());
               var3.setType(var0.getType());
               var3.setAction(var0.getAction());
               var3.setArgs(var0.getArgs());
               var3.setData(var0.getData());
               var3.setStatus(var1);
               RegistryImpl var4 = (RegistryImpl)_transactions[var2].get(var0);
               var4.addResponse(var3);
               return;
            }

            removeTransactionElement(_transactions[var2], var0);
            return;
         }
      }
   }

   static void removeTransactionElement(Transaction var0, Invocation var1) {
      var0.remove(var1);
      if (var0.size() == 0) {
         Arrays.remove(_transactions, var0);
      }
   }

   private void checkTransaction(Invocation var1) {
      for (int var2 = 0; var2 < _transactions.length; var2++) {
         Transaction var3 = _transactions[var2];
         if (var3.getActiveInvocation().getOriginal() == var1) {
            removeTransactionElement(var3, var3.getActiveInvocation());
            Invocation var4 = var3.getActiveInvocation();
            if (var4 != null) {
               var4.setStatus(2);
               InvocationCleanupManager.getInstance().addActiveInvocation(Process.currentProcess().getProcessId(), var4);
               return;
            }
            break;
         }
      }
   }

   private void setApplication(ApplicationDescriptor var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void setAuthority(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private String getAppName() {
      int var1 = TraceBack.getCallingModule(2);
      return getAppName(var1, this._classname, ApplicationDescriptor.currentApplicationDescriptor());
   }

   private static String getOrCreateID(int var0, String var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static String getAppName(int var0, String var1, ApplicationDescriptor var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static String getAuthority(int var0) {
      if (!CodeModuleManager.isMidlet(var0)) {
         return null;
      }

      CodeModuleGroup[] var1 = CodeModuleGroupManager.loadAll();
      CodeModuleGroup var2 = null;
      if (var1 != null) {
         for (int var3 = 0; var3 < var1.length; var3++) {
            if (var1[var3].containsModule(CodeModuleManager.getModuleName(var0))) {
               var2 = var1[var3];
               break;
            }
         }
      }

      return var2 == null ? null : var2.getMIDletSigner();
   }

   private static int verifyClassname(String var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static void verifyID(String var0, String var1, boolean var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static void verifyCharacters(String var0) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private static void verifyRegistered(int var0, String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static void assertPermission() {
      ApplicationControl.assertIPCAllowed(true);
   }
}
