package net.rim.device.api.system;

import java.util.Calendar;
import net.rim.device.api.ui.Graphics;
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

   ApplicationDescriptor(int moduleHandle, int index) {
      this._index = -1;
      this._flags = -1;
      this._position = -1;
      this._nameResourceId = -1;
      this._overrideNameResourceId = -1;
      this._scheduledTime = Long.MAX_VALUE;
      this._absoluteTime = Long.MAX_VALUE;
      this._powerOnBehavior = 0;
      this._moduleHandle = moduleHandle;
      this._index = index;
   }

   public ApplicationDescriptor(ApplicationDescriptor original, String[] args) {
   }

   public ApplicationDescriptor(ApplicationDescriptor original, String name, String[] args) {
   }

   public ApplicationDescriptor(
      ApplicationDescriptor original, String name, String[] args, Bitmap icon, int position, String nameResourceBundle, int nameResourceId
   ) {
   }

   public ApplicationDescriptor(
      ApplicationDescriptor original, String name, String[] args, Bitmap icon, int position, String nameResourceBundle, int nameResourceId, int flags
   ) {
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

   public final void setOverrideNameResourceId(int id) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setOverrideNameResourceBundle(String bundleName) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getLocalizedName() {
      throw new RuntimeException("cod2jar: ldc");
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

   private final Bitmap getBitmap(String id) {
      byte[] data = CodeModuleManager.getModuleData(this._moduleHandle, id, this._index);
      if (data == null) {
         return null;
      }

      boolean findColour = Graphics.isColor();
      boolean loopCheck = findColour;

      do {
         int offset = 0;

         while (offset < data.length) {
            int length = this.getIconLength(offset, data);

            EncodedImage image;
            boolean mono;
            try {
               image = EncodedImage.createEncodedImage(data, offset + 2, length);
               switch (image.getImageType()) {
                  case 2:
                     PNGEncodedImage pngImage = (PNGEncodedImage)image;
                     int colourType = pngImage.getColorType();
                     mono = pngImage.getBitDepth() == 1 && (colourType == 0 || colourType == 4);
                     break;
                  case 4:
                     mono = true;
                     break;
                  default:
                     mono = false;
               }
            } catch (IllegalArgumentException iae) {
               offset += length + 2;
               continue;
            }

            if (findColour != mono) {
               try {
                  return image.getBitmap();
               } catch (IllegalArgumentException iae) {
                  return null;
               }
            }

            offset += length + 2;
         }

         findColour = !findColour;
      } while (loopCheck != findColour);

      return null;
   }

   private final int getIconLength(int start, byte[] data) {
      return ((data[start] & 0xFF) << 8) + (data[start + 1] & 0xFF);
   }

   @Override
   public final boolean equals(Object o) {
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

   private static final long getNextDate(long relativeTime) {
      Calendar cal = DateTimeUtilities.getNextDate((int)relativeTime);
      return ((CalendarExtensions)cal).getTimeLong();
   }

   final long getScheduledTime() {
      return this._absoluteTime;
   }

   final long setScheduledTime(long scheduledTime, boolean absolute) {
      this._scheduledTime = scheduledTime;
      this._absolute = absolute;
      if (absolute) {
         this._absoluteTime = this._scheduledTime - this._scheduledTime % 60000;
      } else {
         this._absoluteTime = getNextDate(this._scheduledTime);
      }

      return this._absoluteTime;
   }

   public final void setPowerOnBehavior(int behavior) {
      this.assertPermission();
      switch (behavior) {
         case 0:
            this._powerOnBehavior = 0;
            return;
         case 1:
         case 2:
         case 3:
         default:
            this._powerOnBehavior = behavior;
      }
   }

   public final int getPowerOnBehavior() {
      return this._powerOnBehavior;
   }

   private final void copy(ApplicationDescriptor original) {
      this._moduleHandle = original._moduleHandle;
      this._moduleName = original._moduleName;
      this._index = original._index;
      this._name = original._name;
      this._version = original._version;
      this._args = original._args;
      this._flags = original._flags;
      this._icon = original._icon;
      this._position = original._position;
      this._nameResourceBundle = original._nameResourceBundle;
      this._overrideNameResourceBundle = original._overrideNameResourceBundle;
      this._nameResourceId = original._nameResourceId;
      this._overrideNameResourceId = original._overrideNameResourceId;
      this._powerOnBehavior = original._powerOnBehavior;
   }
}
