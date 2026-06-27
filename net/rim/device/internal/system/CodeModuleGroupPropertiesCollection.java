package net.rim.device.internal.system;

import java.util.Enumeration;
import net.rim.device.api.collection.CollectionEventSource;
import net.rim.device.api.collection.util.CollectionListenerManager;
import net.rim.device.api.i18n.Locale;
import net.rim.device.api.synchronization.OTASyncCapable;
import net.rim.device.api.synchronization.SyncCollection;
import net.rim.device.api.synchronization.SyncCollectionSchema;
import net.rim.device.api.synchronization.SyncConverter;
import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.CodeModuleGroup;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.CRC32;
import net.rim.device.api.util.DataBuffer;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.api.util.IntVector;

public class CodeModuleGroupPropertiesCollection implements SyncCollection, SyncConverter, CollectionEventSource, OTASyncCapable, GlobalEventListener {
   private PersistentObject _persist;
   private IntHashtable _properties;
   private CodeModuleGroupPropertiesCollection$CheckGroupsThread _checkGroupsThread;
   private CollectionListenerManager _listeners;
   private static final long PROPERTIES_ID;
   private static final int TYPE_PROPERTY_KEY;
   private static final int TYPE_PROPERTY_VALUE;
   private static Object _lock;

   private CodeModuleGroupPropertiesCollection() {
   }

   public static CodeModuleGroupPropertiesCollection getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      CodeModuleGroupPropertiesCollection var1 = (CodeModuleGroupPropertiesCollection)var0.getOrWaitFor(-1494190557092396307L);
      if (var1 == null) {
         var1 = new CodeModuleGroupPropertiesCollection();
         var0.put(-1494190557092396307L, var1);
      }

      return var1;
   }

   public static void CodeModuleGroupPropertiesCollectionMain() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static int getGroupUID(String var0) {
      return CRC32.update(-1, var0.getBytes());
   }

   private void checkGroupInformation(CodeModuleGroup[] var1) {
      if (var1 != null) {
         this.checkAddedGroupInformation(var1);
         this.checkDeletedGroupInformation(var1);
      }
   }

   private void checkAddedGroupInformation(CodeModuleGroup[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         int var3 = getGroupUID(var1[var2].getName());
         if (this._properties.get(var3) == null) {
            this.addSyncObject(this.createGroupProperties(var1[var2]));
         }
      }
   }

   private void checkDeletedGroupInformation(CodeModuleGroup[] var1) {
      SyncObject[] var2 = this.getSyncObjects();
      IntVector var3 = this.getCurrentModuleGroupUIDs(var1);
      if (var3 != null) {
         for (int var4 = 0; var4 < var2.length; var4++) {
            if (!var3.contains(var2[var4].getUID())) {
               this.removeSyncObject(var2[var4]);
            }
         }
      }
   }

   private CodeModuleGroupProperties createGroupProperties(CodeModuleGroup var1) {
      int var2 = getGroupUID(var1.getName());
      Object var3 = new Object(var2);
      Enumeration var4 = var1.getPropertyNames();

      while (var4.hasMoreElements()) {
         Object var5 = var4.nextElement();
         ((CodeModuleGroupProperties)var3).put((String)var5, var1.getProperty((String)var5));
      }

      return (CodeModuleGroupProperties)var3;
   }

   private IntVector getCurrentModuleGroupUIDs(CodeModuleGroup[] var1) {
      Object var2 = new Object(var1.length);

      for (int var3 = 0; var3 < var1.length; var3++) {
         ((IntVector)var2).addElement(getGroupUID(var1[var3].getName()));
      }

      return (IntVector)var2;
   }

   @Override
   public boolean addSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void beginTransaction() {
   }

   @Override
   public void clearSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public void endTransaction() {
   }

   @Override
   public SyncConverter getSyncConverter() {
      return this;
   }

   @Override
   public String getSyncName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getSyncName(Locale var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public SyncObject getSyncObject(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getSyncObjectCount() {
      return this._properties.size();
   }

   @Override
   public SyncObject[] getSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getSyncVersion() {
      return 1;
   }

   @Override
   public boolean isSyncObjectDirty(SyncObject var1) {
      return false;
   }

   @Override
   public boolean removeAllSyncObjects() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean removeSyncObject(SyncObject var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setSyncObjectDirty(SyncObject var1) {
   }

   @Override
   public boolean updateSyncObject(SyncObject var1, SyncObject var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public SyncObject convert(DataBuffer var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public boolean convert(SyncObject var1, DataBuffer var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public void addCollectionListener(Object var1) {
      this._listeners.addCollectionListener(var1);
   }

   @Override
   public void removeCollectionListener(Object var1) {
      this._listeners.removeCollectionListener(var1);
   }

   @Override
   public SyncCollectionSchema getSchema() {
      return null;
   }
}
