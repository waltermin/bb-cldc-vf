package net.rim.device.cldc.util;

import java.util.TimeZone;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.RIMPersistentStore;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.DateTimeUtilities;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.Array;

public final class TimeService {
   private String _defaultTimeZoneID;
   private TimeZone _tzObject;
   private IntHashtable _timeZoneTable;
   private TimeZoneDataLocalizer _localizer;
   private PersistentObject _persistableTimeZoneData;
   private TimeZoneDataObject[] _sortedTimeZoneDataObjectList;
   private int[] _indexMap;
   private boolean _automaticallyAdjustClockForDSTTime = true;
   private PersistentObject _persistableBitfieldSettings;
   private int _timeServiceSettings;
   private int TIME_SERVICE_SETTINGS_AUTO_ADJUST_FOR_DST = 1;
   private static final long DEFAULT_TIMEZONE;
   private static final long AUTO_ADJUST_CLOCK_FOR_DST_SETTING;
   private static final long TIMEZONEDATA;
   private static final int TIME_ZONE_SHORT_NAME;
   private static final int TIME_ZONE_LONG_NAME;
   private static final int TIME_ZONE_DEFAULT_NAME;
   private static TimeService _instance;
   private static final long REGISTRY_KEY;

   private TimeService() {
      this.intializeTimeZones();
      this._defaultTimeZoneID = this.internalGetDefaultTimeZoneID();
      this._persistableBitfieldSettings = RIMPersistentStore.getPersistentObject(987576202591045751L);
      Object var1 = this._persistableBitfieldSettings.getContents();
      if (var1 == null) {
         this._timeServiceSettings = this.TIME_SERVICE_SETTINGS_AUTO_ADJUST_FOR_DST;
         var1 = new Object(this._timeServiceSettings);
         this._persistableBitfieldSettings.setContents(var1, 51, false);
         this._persistableBitfieldSettings.commit();
      } else {
         this._timeServiceSettings = var1;
         if ((this._timeServiceSettings & this.TIME_SERVICE_SETTINGS_AUTO_ADJUST_FOR_DST) == 0) {
            this._automaticallyAdjustClockForDSTTime = false;
         }
      }

      this.updateTimeZoneOffset();
   }

   public final synchronized void setAutomaticClockAdjustmentForDST(boolean var1) {
      this._automaticallyAdjustClockForDSTTime = var1;
      this._timeServiceSettings = this._timeServiceSettings | this.TIME_SERVICE_SETTINGS_AUTO_ADJUST_FOR_DST;
      this._persistableBitfieldSettings.setContents(new Object(this._timeServiceSettings), 51, false);
      this._persistableBitfieldSettings.commit();
   }

   public final synchronized boolean automaticClockAdjustmentForDST() {
      return this._automaticallyAdjustClockForDSTTime;
   }

   public final synchronized void setLocalizer(TimeZoneDataLocalizer var1) {
      this._localizer = var1;
   }

   private final void intializeTimeZones() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final int getTimeZoneBuiltInIndex(int var1) {
      byte var2 = -1;

      for (int var3 = 0; var3 < 88; var3++) {
         int var4 = var3 * 9;
         if (var1 == TimeZoneData.ZONE_DATA[var4]) {
            return var3;
         }
      }

      return var2;
   }

   private final synchronized String[] getTimeZoneNames(int var1) {
      int var2 = this._indexMap.length;
      String[] var3 = new String[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = this.getTimeZoneDescriptionByIndex(var4, var1);
      }

      return var3;
   }

   public final synchronized String[] getTimeZoneNamesLong() {
      return this.getTimeZoneNames(1);
   }

   public final synchronized String[] getTimeZoneNamesShort() {
      return this.getTimeZoneNames(0);
   }

   public final synchronized String[] getTimeZoneIDs() {
      int var1 = this._indexMap.length;
      String[] var2 = new String[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = this._sortedTimeZoneDataObjectList[this._indexMap[var3]].getTimeZoneStringID();
      }

      return var2;
   }

   public final synchronized int[] getTimeZoneUIDs() {
      int var1 = this._sortedTimeZoneDataObjectList.length;
      int[] var2 = new int[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = this._sortedTimeZoneDataObjectList[var3].getTimeZoneID();
      }

      return var2;
   }

   public final synchronized TimeZone[] getTimeZones() {
      int var1 = this._sortedTimeZoneDataObjectList.length;
      TimeZone[] var2 = new TimeZone[var1];

      for (int var3 = 0; var3 < var1; var3++) {
         var2[var3] = this.getZone(this.getTimeZoneID(this._sortedTimeZoneDataObjectList[var3]));
      }

      return var2;
   }

   public final synchronized TimeZoneDataObject[] getTimeZoneDataObjects() {
      return this._sortedTimeZoneDataObjectList;
   }

   public final synchronized String getTimeZoneIDFromIndex(int var1) {
      String var2 = null;
      if (var1 >= 0 && var1 < this._indexMap.length) {
         var2 = this._sortedTimeZoneDataObjectList[this._indexMap[var1]].getTimeZoneStringID();
      }

      return var2;
   }

   public final synchronized int getTimeZoneUIDFromIndex(int var1) {
      int var2 = -1;
      if (var1 >= 0 && var1 < this._indexMap.length) {
         var2 = this._sortedTimeZoneDataObjectList[this._indexMap[var1]].getTimeZoneID();
      }

      return var2;
   }

   public final synchronized TimeZone getTimeZone(String var1) {
      if (this._tzObject != null && var1.equals(this._tzObject.getID())) {
         return this._tzObject;
      }

      TimeZoneImpl var2 = this.getZone(this.getTimeZoneID(var1));
      if (var2 != null) {
         this._tzObject = var2;
         return var2;
      }

      CustomTimeZoneImpl var3 = this.getCustomZone(var1);
      if (var3 != null) {
         this._tzObject = var3;
      }

      return var3;
   }

   public final int getTimeZoneID(TimeZoneDataObject var1) {
      int var2 = var1.getMappedTZID();
      return var2 == -1 ? var1.getTimeZoneID() : var2;
   }

   public final int getTimeZoneID(String var1) {
      int var2 = -1;

      for (int var3 = 0; var1 != null && var3 < this._sortedTimeZoneDataObjectList.length; var3++) {
         TimeZoneDataObject var4 = this._sortedTimeZoneDataObjectList[var3];
         if (var1.equalsIgnoreCase(var4.getTimeZoneStringID())) {
            var2 = var4.getMappedTZID();
            if (var2 == -1) {
               return var4.getTimeZoneID();
            }
            break;
         }
      }

      return var2;
   }

   public final boolean isTimeZoneConfigured() {
      return this._defaultTimeZoneID != null;
   }

   private final void updateTimeZoneOffset() {
      TimeZone var1 = this.getTimeZone(this.getDefaultTimeZoneID());
      if (var1 != null) {
         InternalServices.setTimeZoneOffset(var1.getRawOffset());
      }
   }

   private final String internalGetDefaultTimeZoneID() {
      PersistentObject var1 = RIMPersistentStore.getPersistentObject(2989539104253779367L);
      return (String)var1.getContents();
   }

   private final void internalSetDefaultTimeZoneID(String var1) {
      PersistentObject var2 = RIMPersistentStore.getPersistentObject(2989539104253779367L);
      this._defaultTimeZoneID = var1;
      var2.setContents(var1, 51, false);
      this.updateTimeZoneOffset();
      var2.commit();
   }

   public final String getDefaultTimeZoneID() {
      String var1 = this._defaultTimeZoneID;
      if (var1 == null) {
         var1 = DateTimeUtilities.GMT;
      }

      return var1;
   }

   public final synchronized void setDefaultTimeZone(String var1) {
      if (var1 != null && this.getTimeZoneID(var1) != -1 && !var1.equals(this._defaultTimeZoneID)) {
         this._defaultTimeZoneID = this.getTimeZoneInfo(this.getTimeZoneID(var1)).getTimeZoneStringID();
         this.internalSetDefaultTimeZoneID(this._defaultTimeZoneID);
         TimeZone var2 = this.getTimeZone(this._defaultTimeZoneID);
         if (var2 != null) {
            InternalServices.setTimeZoneOffset(var2.getRawOffset());
         }

         RIMGlobalMessagePoster.postGlobalEvent(3596208183088439728L);
      }
   }

   public final int getTimeZoneIndex(String var1) {
      int var2 = -1;

      for (int var3 = 0; var1 != null && var3 < this._indexMap.length; var3++) {
         TimeZoneDataObject var4 = this._sortedTimeZoneDataObjectList[this._indexMap[var3]];
         if (var1.equalsIgnoreCase(var4.getTimeZoneStringID())) {
            var2 = var3;
            break;
         }
      }

      if (var2 == -1 && (var1 == null || !var1.equalsIgnoreCase(DateTimeUtilities.GMT))) {
         var2 = this.getTimeZoneIndex(DateTimeUtilities.GMT);
      }

      return var2;
   }

   public final int getTimeZoneIndexByUID(int var1) {
      int var2 = -1;

      for (int var3 = 0; var3 < this._indexMap.length; var3++) {
         TimeZoneDataObject var4 = this._sortedTimeZoneDataObjectList[this._indexMap[var3]];
         if (var4.getTimeZoneID() == var1) {
            var2 = var3;
            break;
         }
      }

      if (var2 == -1) {
         var2 = this.getTimeZoneIndex(DateTimeUtilities.GMT);
      }

      return var2;
   }

   public final synchronized TimeZoneDataObject getTimeZoneInfo(int var1) {
      return (TimeZoneDataObject)this._timeZoneTable.get(var1);
   }

   public final synchronized TimeZoneDataObject getTimeZoneByUID(int var1) {
      for (int var2 = 0; var2 < this._sortedTimeZoneDataObjectList.length; var2++) {
         TimeZoneDataObject var3 = this._sortedTimeZoneDataObjectList[var2];
         if (var3.getUID() == var1) {
            return var3;
         }
      }

      return null;
   }

   public final synchronized String getTimeZoneIDFromSerialSyncID(int var1) {
      String var2 = null;
      Object var3 = this._timeZoneTable.get(var1);
      if (var3 != null) {
         var2 = ((TimeZoneDataObject)var3).getTimeZoneStringID();
      }

      return var2;
   }

   public final synchronized int getSerialSyncID(String var1) {
      byte var2 = -1;

      for (int var3 = 0; var1 != null && var3 < this._sortedTimeZoneDataObjectList.length; var3++) {
         TimeZoneDataObject var4 = this._sortedTimeZoneDataObjectList[var3];
         if (var1.equalsIgnoreCase(var4.getTimeZoneStringID())) {
            return var4.getTimeZoneID();
         }
      }

      return var2;
   }

   private final synchronized String getTimeZoneDescription(TimeZoneDataObject var1, int var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private final synchronized String getTimeZoneDescriptionByIndex(int var1, int var2) {
      TimeZoneDataObject var3 = this._sortedTimeZoneDataObjectList[this._indexMap[var1]];
      return this.getTimeZoneDescription(var3, var2);
   }

   public final synchronized String getTimeZoneLongDescription(String var1) {
      return this.getTimeZoneDescriptionByIndex(this.getTimeZoneIndex(var1), 1);
   }

   public final synchronized String getTimeZoneShortDescription(String var1) {
      return this.getTimeZoneDescriptionByIndex(this.getTimeZoneIndex(var1), 0);
   }

   private final synchronized String getTimeZoneDescriptionByID(int var1, int var2) {
      Object var3 = this._timeZoneTable.get(var1);
      return this.getTimeZoneDescription((TimeZoneDataObject)var3, var2);
   }

   public final synchronized String getTimeZoneLongDescription(int var1) {
      return this.getTimeZoneDescriptionByID(var1, 0);
   }

   public final synchronized String getLocalizedTimeZoneShortDescription(int var1) {
      return this.getTimeZoneDescriptionByID(var1, 0);
   }

   public final synchronized String getDefaultTimeZoneDescription(int var1) {
      return this.getTimeZoneDescriptionByID(var1, 2);
   }

   private final TimeZoneImpl getZone(int var1) {
      TimeZoneImpl var2 = null;
      Object var3 = this._timeZoneTable.get(var1);
      if (var3 != null) {
         var2 = new TimeZoneImpl(
            ((TimeZoneDataObject)var3).getTimeZoneStringID(),
            ((TimeZoneDataObject)var3).getGMTOffset(),
            ((TimeZoneDataObject)var3).getDSTAmount(),
            ((TimeZoneDataObject)var3).getDSTStartMode(),
            ((TimeZoneDataObject)var3).getDSTStartMonth(),
            ((TimeZoneDataObject)var3).getDSTStartDayOfWeek(),
            ((TimeZoneDataObject)var3).getDSTStartDay(),
            ((TimeZoneDataObject)var3).getDSTStartTime(),
            ((TimeZoneDataObject)var3).getDSTEndMode(),
            ((TimeZoneDataObject)var3).getDSTEndMonth(),
            ((TimeZoneDataObject)var3).getDSTEndDayOfWeek(),
            ((TimeZoneDataObject)var3).getDSTEndDay(),
            ((TimeZoneDataObject)var3).getDSTEndTime(),
            var1,
            this._automaticallyAdjustClockForDSTTime
         );
      }

      return var2;
   }

   private final CustomTimeZoneImpl getCustomZone(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized void deleteTimeZone(int var1) {
      for (int var2 = 0; var2 < this._sortedTimeZoneDataObjectList.length; var2++) {
         TimeZoneDataObject var3 = this._sortedTimeZoneDataObjectList[var2];
         if (var3.getTimeZoneID() == var1) {
            if (var3.getTimeZoneStringID().equalsIgnoreCase(DateTimeUtilities.GMT)) {
               return;
            }

            this._sortedTimeZoneDataObjectList[var2] = this._sortedTimeZoneDataObjectList[this._sortedTimeZoneDataObjectList.length - 1];
            Array.resize(this._sortedTimeZoneDataObjectList, this._sortedTimeZoneDataObjectList.length - 1);
            this._timeZoneTable.remove(var1);
            Arrays.sort(this._sortedTimeZoneDataObjectList, TimeZoneDataObject.getComparator());
            this._indexMap = new int[this._sortedTimeZoneDataObjectList.length];
            int var4 = 0;

            for (int var5 = 0; var5 < this._sortedTimeZoneDataObjectList.length; var5++) {
               if (!this._sortedTimeZoneDataObjectList[var5].isHidden()) {
                  this._indexMap[var4] = var5;
                  var4++;
               }
            }

            Array.resize(this._indexMap, var4);
            if (this._defaultTimeZoneID != null && this._defaultTimeZoneID.equalsIgnoreCase(var3.getTimeZoneStringID())) {
               this._tzObject = null;
               this.internalSetDefaultTimeZoneID(null);
            }

            this._persistableTimeZoneData.commit();
            return;
         }
      }
   }

   public final synchronized void addTimeZone(TimeZoneDataObject var1) {
      this.addTimeZone(
         var1.getUID(),
         var1.getTimeZoneID(),
         var1.getTimeZoneStringID(),
         var1.getDefaultLongDescription(),
         var1.getDefaultShortDescription(),
         var1.getMappedTZID(),
         var1.isHidden(),
         var1.getGMTOffset(),
         var1.getDSTAmount(),
         var1.getDSTStartMode(),
         var1.getDSTStartMonth(),
         var1.getDSTStartDayOfWeek(),
         var1.getDSTStartDay(),
         var1.getDSTStartTime(),
         var1.getDSTEndMode(),
         var1.getDSTEndMonth(),
         var1.getDSTEndDayOfWeek(),
         var1.getDSTEndDay(),
         var1.getDSTEndTime()
      );
   }

   public final synchronized void addTimeZone(
      int var1,
      int var2,
      String var3,
      String var4,
      String var5,
      int var6,
      boolean var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      int var14,
      int var15,
      int var16,
      int var17,
      int var18,
      int var19
   ) {
      int var20 = this.getTimeZoneBuiltInIndex(var2);
      boolean var21 = true;
      Object var22 = null;
      if (this._timeZoneTable.containsKey(var2)) {
         var22 = this._timeZoneTable.get(var2);
         var20 = ((TimeZoneDataObject)var22).getBuiltInIndex();
         var21 = false;
      }

      if (var3.equalsIgnoreCase(DateTimeUtilities.GMT)) {
         var7 = false;
      }

      var22 = new Object(var1, var2, var3, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18, var19, var4, var5, var6, var7);
      ((TimeZoneDataObject)var22).setBuiltInIndex(var20);
      int var23 = this.getSerialSyncID(this.getDefaultTimeZoneID());
      boolean var24 = var2 == var23;
      this._timeZoneTable.put(var2, var22);
      if (var21) {
         Array.resize(this._sortedTimeZoneDataObjectList, this._sortedTimeZoneDataObjectList.length + 1);
         this._sortedTimeZoneDataObjectList[this._sortedTimeZoneDataObjectList.length - 1] = (TimeZoneDataObject)var22;
      } else {
         for (int var25 = 0; var25 < this._sortedTimeZoneDataObjectList.length; var25++) {
            if (this._sortedTimeZoneDataObjectList[var25].getTimeZoneID() == var2) {
               this._sortedTimeZoneDataObjectList[var25] = (TimeZoneDataObject)var22;
               break;
            }
         }
      }

      Arrays.sort(this._sortedTimeZoneDataObjectList, TimeZoneDataObject.getComparator());
      this._indexMap = new int[this._sortedTimeZoneDataObjectList.length];
      int var29 = 0;

      for (int var26 = 0; var26 < this._sortedTimeZoneDataObjectList.length; var26++) {
         if (!this._sortedTimeZoneDataObjectList[var26].isHidden()) {
            this._indexMap[var29] = var26;
            var29++;
         }
      }

      Array.resize(this._indexMap, var29);
      this._persistableTimeZoneData.commit();
      if (var24) {
         this._tzObject = null;
         this.setDefaultTimeZone(((TimeZoneDataObject)var22).getTimeZoneStringID());
      }
   }

   public final int getTimeZoneCount() {
      return this._sortedTimeZoneDataObjectList.length;
   }

   public static final synchronized TimeService getTimeService() {
      if (_instance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         if (var0 == null) {
            return null;
         }

         _instance = (TimeService)var0.getOrWaitFor(2569363511892233325L);
         if (_instance == null) {
            _instance = new TimeService();
            var0.put(2569363511892233325L, _instance);
         }
      }

      return _instance;
   }
}
