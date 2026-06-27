package net.rim.device.api.system;

import java.util.Calendar;
import net.rim.device.api.util.DateTimeUtilities;
import net.rim.device.cldc.util.CalendarExtensions;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.vm.Process;

public final class ApplicationDescriptor {
   private int _moduleHandle;
   private String _moduleName;
   private int _index;
   private String _name;
   private String _version;
   private String[] _args;
   private int _flags;
   private Bitmap _icon;
   private int _position;
   private String _nameResourceBundle;
   private String _overrideNameResourceBundle;
   private int _nameResourceId;
   private int _overrideNameResourceId;
   private long _scheduledTime;
   private long _absoluteTime;
   private boolean _absolute;
   private int _powerOnBehavior;
   public static final int DO_NOT_POWER_ON;
   public static final int POWER_ON;
   public static final int POWER_ON_FOR_AUTO_ON;
   public static final int POWER_ON_ALWAYS;
   public static final byte FLAG_RUN_ON_STARTUP;
   public static final byte FLAG_SYSTEM;
   public static final byte FLAG_AUTO_RESTART;
   static final int NUM_TIERS;
   static final int TIERS_SHIFT;

   private ApplicationDescriptor() {
      this._index = -1;
      this._flags = -1;
      this._position = -1;
      this._nameResourceId = -1;
      this._overrideNameResourceId = -1;
      this._scheduledTime = Long.MAX_VALUE;
      this._absoluteTime = Long.MAX_VALUE;
      this._powerOnBehavior = 0;
   }

   ApplicationDescriptor(int var1, int var2) {
      this._index = -1;
      this._flags = -1;
      this._position = -1;
      this._nameResourceId = -1;
      this._overrideNameResourceId = -1;
      this._scheduledTime = Long.MAX_VALUE;
      this._absoluteTime = Long.MAX_VALUE;
      this._powerOnBehavior = 0;
      this._moduleHandle = var1;
      this._index = var2;
   }

   public ApplicationDescriptor(ApplicationDescriptor var1, String[] var2) {
   }

   public ApplicationDescriptor(ApplicationDescriptor var1, String var2, String[] var3) {
   }

   public ApplicationDescriptor(ApplicationDescriptor var1, String var2, String[] var3, Bitmap var4, int var5, String var6, int var7) {
   }

   public ApplicationDescriptor(ApplicationDescriptor var1, String var2, String[] var3, Bitmap var4, int var5, String var6, int var7, int var8) {
   }

   private final void assertPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   public final int getModuleHandle() {
      return this._moduleHandle;
   }

   public final String getModuleName() {
      if (this._moduleName == null) {
         this._moduleName = CodeModuleManager.getModuleName(this._moduleHandle);
      }

      return this._moduleName;
   }

   public final synchronized String getName() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getIndex() {
      return this._index;
   }

   public final synchronized String getNameResourceBundle() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getNameResourceId() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setOverrideNameResourceId(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setOverrideNameResourceBundle(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getLocalizedName() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized String getVersion() {
      if (this._version == null) {
         this._version = CodeModuleManager.getModuleVersion(this._moduleHandle);
      }

      return this._version;
   }

   public final synchronized String[] getArgs() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getFlags() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getStartupTier() {
      this.getFlags();
      return 7 - (this._flags >> 5);
   }

   public final synchronized Bitmap getIcon() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized int getPosition() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final Bitmap getBitmap(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final int getIconLength(int var1, byte[] var2) {
      return ((var2[var1] & 0xFF) << 8) + (var2[var1 + 1] & 0xFF);
   }

   @Override
   public final boolean equals(Object var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static final ApplicationDescriptor currentApplicationDescriptor() {
      return ((ApplicationProcess)Process.currentProcess()).getApplicationDescriptor();
   }

   final long getNextScheduledTime() {
      if (!this._absolute) {
         this._absoluteTime = getNextDate(this._scheduledTime);
      }

      return this._absoluteTime;
   }

   private static final long getNextDate(long var0) {
      Calendar var2 = DateTimeUtilities.getNextDate((int)var0);
      return ((CalendarExtensions)var2).getTimeLong();
   }

   final long getScheduledTime() {
      return this._absoluteTime;
   }

   final long setScheduledTime(long var1, boolean var3) {
      this._scheduledTime = var1;
      this._absolute = var3;
      if (var3) {
         this._absoluteTime = this._scheduledTime - this._scheduledTime % 60000;
      } else {
         this._absoluteTime = getNextDate(this._scheduledTime);
      }

      return this._absoluteTime;
   }

   public final void setPowerOnBehavior(int var1) {
      this.assertPermission();
      switch (var1) {
         case 0:
            this._powerOnBehavior = 0;
            return;
         case 1:
         case 2:
         case 3:
         default:
            this._powerOnBehavior = var1;
      }
   }

   public final int getPowerOnBehavior() {
      return this._powerOnBehavior;
   }

   private final void copy(ApplicationDescriptor var1) {
      this._moduleHandle = var1._moduleHandle;
      this._moduleName = var1._moduleName;
      this._index = var1._index;
      this._name = var1._name;
      this._version = var1._version;
      this._args = var1._args;
      this._flags = var1._flags;
      this._icon = var1._icon;
      this._position = var1._position;
      this._nameResourceBundle = var1._nameResourceBundle;
      this._overrideNameResourceBundle = var1._overrideNameResourceBundle;
      this._nameResourceId = var1._nameResourceId;
      this._overrideNameResourceId = var1._overrideNameResourceId;
      this._powerOnBehavior = var1._powerOnBehavior;
   }
}
