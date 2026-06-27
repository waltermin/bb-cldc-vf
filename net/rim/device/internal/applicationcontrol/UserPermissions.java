package net.rim.device.internal.applicationcontrol;

import java.util.Enumeration;
import java.util.Vector;
import net.rim.device.api.system.CodeModuleManager;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.system.CodeStore;
import net.rim.vm.Array;

final class UserPermissions {
   private IntHashtable _lookupTable;
   private Vector _storage;
   private PersistentObject _persistentStorage;
   private UserSetting _backedUpDefaults;
   private UserSetting _defaults;
   private UserSettingsSync _sync;
   private static final long STORAGE_KEY;
   private static final int DEFAULTS_HANDLE;
   private static final int BACKUP_DEFAULTS_HANDLE;
   private static final long USER_SETTINGS_SYNC_KEY;

   public final void load(long var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void clear(long var1) {
      this._sync.reset();
      this._storage = null;
      this._persistentStorage.setContents(null, 51);
      this.load(var1);
   }

   final void putDefaultSetting(UserSetting var1) {
      this.putDefaultSetting(var1, true);
   }

   final void putDefaultSetting(UserSetting var1, boolean var2) {
      this._defaults = var1;
      this.putSetting(0, this._defaults, var2);
   }

   final void putBackedUpDefaultSetting(UserSetting var1) {
      this.putBackedUpDefaultSetting(var1, true);
   }

   final void putBackedUpDefaultSetting(UserSetting var1, boolean var2) {
      this._backedUpDefaults = var1;
      this.putSetting(-1, this._backedUpDefaults, var2);
   }

   final void putSetting(int var1, UserSetting var2) {
      this.putSetting(var1, var2, true);
   }

   final void putSetting(int var1, UserSetting var2, boolean var3) {
      this.putSetting(var1, var2, var3, true);
   }

   final void putSetting(int var1, UserSetting var2, boolean var3, boolean var4) {
      this._lookupTable.put(var1, var2);
      if (!this._storage.contains(var2)) {
         this._storage.addElement(var2);
         this._sync.settingAdded(var2);
      }

      if (var4) {
         this.applyToSiblings(var1, var2);
      }

      if (var3) {
         this.commit();
      }
   }

   final UserSetting getBackedUpDefaultSetting() {
      return this._backedUpDefaults;
   }

   final UserSetting getDefaultSetting() {
      return this._defaults;
   }

   final UserSetting getSetting(int var1) {
      return (UserSetting)this._lookupTable.get(var1);
   }

   final UserSetting getSetting(byte[] var1) {
      Enumeration var2 = this._storage.elements();

      while (var2.hasMoreElements()) {
         Object var3 = var2.nextElement();
         if (((UserSetting)var3).hashEquals(var1)) {
            return (UserSetting)var3;
         }
      }

      return null;
   }

   final int[] getLoadedHandles() {
      int[] var1 = new int[this._lookupTable.size()];
      IntEnumeration var2 = this._lookupTable.keys();
      int var3 = 0;

      while (var2.hasMoreElements()) {
         int var4 = var2.nextElement();
         if (var4 != 0 && var4 != -1) {
            var1[var3++] = var4;
         }
      }

      Array.resize(var1, var3);
      return var1;
   }

   final void removeBackedUpDefaultSetting() {
      if (this._backedUpDefaults != null) {
         this._lookupTable.removeValue(this._backedUpDefaults);
         this._storage.removeElement(this._backedUpDefaults);
         this._sync.settingRemoved(this._backedUpDefaults);
         this.commit();
      }

      this._backedUpDefaults = null;
   }

   final void removeSetting(int var1) {
      this.removeSetting(var1, null);
   }

   final void removeSetting(UserSetting var1) {
      this._lookupTable.removeValue(var1);
      if (var1 != null) {
         this._sync.settingRemoved(var1);
         this._storage.removeElement(var1);
         this.removeSiblings(var1);
         this.commit();
      }
   }

   final void removeSetting(int var1, UserSetting var2) {
      this._lookupTable.remove(var1);
      if (var2 != null) {
         this._sync.settingRemoved(var2);
         this._storage.removeElement(var2);
         this.removeSiblings(var1, var2);
         this.commit();
      }
   }

   final boolean containsSetting(UserSetting var1) {
      return this._lookupTable.contains(var1);
   }

   final boolean containsSetting(int var1) {
      return this._lookupTable.containsKey(var1);
   }

   final int numberOfSettings() {
      return this._lookupTable.size();
   }

   final void commit() {
      this._persistentStorage.commit();
   }

   final Vector getStorage() {
      return this._storage;
   }

   final void setPermissions(UserSetting var1, UserSetting var2) {
      this.setPermissions(null, var1, var2, true, true);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, boolean var3) {
      this.setPermissions(null, var1, var2, var3, true);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, UserSetting var3) {
      this.setPermissions(var1, var2, var3, true, true);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, UserSetting var3, boolean var4) {
      this.setPermissions(var1, var2, var3, var4, true);
   }

   private final void setPermissions(UserSetting var1, UserSetting var2, UserSetting var3, boolean var4, boolean var5) {
      var2.setPermissions(var3);
      if (var1 != null) {
         this._sync.settingUpdated(var1, var2);
      }

      if (var5) {
         this.applyToSiblings(var2);
      }

      if (var4) {
         this.commit();
      }
   }

   final void setPermissions(UserSetting var1, long var2) {
      this.setPermissions(null, var1, var2);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, long var3) {
      var2.setPermissions(var3);
      if (var1 != null) {
         this._sync.settingUpdated(var1, var2);
      }

      this.applyToSiblings(var2);
      this.commit();
   }

   final void setPermissions(UserSetting var1, long var2, long var4, long var6) {
      this.setPermissions(null, var1, var2, var4, var6);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, long var3, long var5, long var7) {
      this.setPermissions(var1, var2, var3, var5, var7, true);
   }

   final void setPermissions(UserSetting var1, UserSetting var2, long var3, long var5, long var7, boolean var9) {
      var2.setPermissions(var3, var5, var7);
      if (var1 != null) {
         this._sync.settingUpdated(var1, var2);
      }

      this.applyToSiblings(var2);
      if (var9) {
         this.commit();
      }
   }

   final void resetPrompt(UserSetting var1, UserSetting var2, long var3) {
      this.resetPrompt(var1, var2, var3, true);
   }

   final void resetPrompt(UserSetting var1, UserSetting var2, long var3, boolean var5) {
      var2.resetPrompt(var3);
      if (var1 != null) {
         this._sync.settingUpdated(var1, var2);
      }

      this.applyToSiblings(var2);
      if (var5) {
         this.commit();
      }
   }

   final void resetPrompts(UserSetting var1, UserSetting var2) {
      this.resetPrompts(var1, var2, true);
   }

   final void resetPrompts(UserSetting var1, UserSetting var2, boolean var3) {
      var2.resetPrompts();
      if (var1 != null) {
         this._sync.settingUpdated(var1, var2);
      }

      this.applyToSiblings(var2);
      if (var3) {
         this.commit();
      }
   }

   private final void applyToSiblings(UserSetting var1) {
      int var2 = CodeModuleManager.getModuleHandle(var1.getHash());
      this.applyToSiblings(var2, var1);
   }

   private final void applyToSiblings(int var1, UserSetting var2) {
      this.applyToSiblings(var1, var2, false);
   }

   private final void removeSiblings(UserSetting var1) {
      int var2 = CodeModuleManager.getModuleHandle(var1.getHash());
      this.removeSiblings(var2, var1);
   }

   private final void removeSiblings(int var1, UserSetting var2) {
      this.applyToSiblings(var1, var2, true);
   }

   private final void applyToSiblings(int var1, UserSetting var2, boolean var3) {
      if (var1 != 0 && var1 != -1) {
         boolean var4 = var3 | var2.getIsSet() == 0;
         int[] var5 = CodeStore.getSiblingHandles(var1);
         byte[] var6 = new byte[20];
         int var7 = var5 == null ? 0 : var5.length;
         if (var7 > 1) {
            for (int var9 = 0; var9 < var7; var9++) {
               int var8 = var5[var9];
               UserSetting var10 = this.getSetting(var8);
               if (var10 == null) {
                  if (CodeModuleManager.getModuleHash(var8, var6) && !var4 && this.getSetting(var6) == null) {
                     var10 = (UserSetting)(new Object(var6, var2.getPermissions(), var2.getDontPrompt(), var2.getIsSet()));
                     this.putSetting(var8, var10, false, false);
                  }
               } else if (var4) {
                  ApplicationControlImpl.removeUserSetting(var8);
               } else {
                  Object var11 = new Object(var10);
                  this.setPermissions((UserSetting)var11, var10, var2, false, false);
               }
            }
         }
      }
   }
}
