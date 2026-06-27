package net.rim.device.api.applicationcontrol;

import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntEnumeration;
import net.rim.device.api.util.IntIntHashtable;

public final class ApplicationPermissions {
   private IntIntHashtable _permissions = (IntIntHashtable)(new Object());
   public static final int PERMISSION_AUTHENTICATOR_API;
   public static final int PERMISSION_BLUETOOTH;
   public static final int PERMISSION_BROWSER_FILTER;
   public static final int PERMISSION_CHANGE_DEVICE_SETTINGS;
   public static final int PERMISSION_CODE_MODULE_MANAGEMENT;
   public static final int PERMISSION_EMAIL;
   public static final int PERMISSION_EVENT_INJECTOR;
   public static final int PERMISSION_EXTERNAL_CONNECTIONS;
   public static final int PERMISSION_FILE_API;
   public static final int PERMISSION_HANDHELD_KEYSTORE;
   public static final int PERMISSION_INTERNAL_CONNECTIONS;
   public static final int PERMISSION_INTER_PROCESS_COMMUNICATION;
   public static final int PERMISSION_INTER_PROCESS_COMMUNUCATION;
   public static final int PERMISSION_KEYSTORE_MEDIUM_SECURITY;
   public static final int PERMISSION_LOCAL_CONNECTIONS;
   public static final int PERMISSION_LOCATION_API;
   public static final int PERMISSION_PHONE;
   public static final int PERMISSION_PIM;
   public static final int PERMISSION_SCREEN_CAPTURE;
   public static final int PERMISSION_THEME_DATA;
   public static final int PERMISSION_WIFI;
   public static final int PERMISSION_IDLE_TIMER;
   public static final int PERMISSION_MEDIA;
   public static final int VALUE_ALLOW;
   public static final int VALUE_PROMPT;
   public static final int VALUE_DENY;
   public static final int VALUE_NOT_SET;

   public final void addPermission(int var1) {
      this.addPermission(var1, 999);
   }

   public final void addPermission(int var1, int var2) {
      if (!this.isValidPermission(var1)) {
         throw new Object();
      }

      this._permissions.put(var1, var2);
   }

   public final int getPermission(int var1) {
      if (!this.isValidPermission(var1)) {
         throw new Object();
      } else {
         int var2 = this._permissions.get(var1);
         if (var2 != -1) {
            return var2;
         } else {
            throw new Object();
         }
      }
   }

   public final int getPermissionInternal(int var1) {
      if (!this.isValidPermission(var1)) {
         throw new Object();
      }

      int var2 = this._permissions.get(var1);
      return var2 != -1 ? var2 : 997;
   }

   public final int[] difference(ApplicationPermissions var1) {
      if (var1 == null) {
         throw new Object();
      }

      int[] var2 = new int[0];
      IntIntHashtable var3 = var1._permissions;
      int[] var4 = this.getPermissionKeys();
      int var5 = -1;

      for (int var6 = 0; var6 < var4.length; var6++) {
         var5 = var4[var6];
         if (this._permissions.get(var5) != var3.get(var5) && var3.get(var5) != -1) {
            Arrays.add(var2, var5);
         }
      }

      return var2;
   }

   public final int[] getPermissionKeys() {
      int[] var1 = new int[0];
      IntEnumeration var2 = this._permissions.keys();

      while (var2.hasMoreElements()) {
         Arrays.add(var1, var2.nextElement());
      }

      return var1;
   }

   public final boolean containsPermissionKey(int var1) {
      if (!this.isValidPermission(var1)) {
         throw new Object();
      } else {
         return this._permissions.containsKey(var1);
      }
   }

   private final boolean isValidPermission(int var1) {
      switch (var1) {
         case -1:
            return false;
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         default:
            return true;
      }
   }

   public final void addAllPermissions() {
      this._permissions.put(0, 999);
      this._permissions.put(1, 999);
      this._permissions.put(2, 999);
      this._permissions.put(3, 999);
      this._permissions.put(4, 999);
      this._permissions.put(5, 999);
      this._permissions.put(6, 999);
      this._permissions.put(7, 999);
      this._permissions.put(8, 999);
      this._permissions.put(9, 999);
      this._permissions.put(11, 999);
      this._permissions.put(10, 999);
      this._permissions.put(12, 999);
      this._permissions.put(13, 999);
      this._permissions.put(14, 999);
      this._permissions.put(15, 999);
      this._permissions.put(16, 999);
      this._permissions.put(17, 999);
      this._permissions.put(18, 999);
      this._permissions.put(19, 999);
      this._permissions.put(20, 999);
      this._permissions.put(21, 999);
   }
}
