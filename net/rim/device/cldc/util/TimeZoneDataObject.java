package net.rim.device.cldc.util;

import net.rim.device.api.synchronization.SyncObject;
import net.rim.device.api.synchronization.UIDGenerator;
import net.rim.device.api.util.Comparator;
import net.rim.vm.Persistable;

public class TimeZoneDataObject implements Persistable, SyncObject {
   int _uid;
   int _tzid;
   String _zoneStringID;
   int _gmtOffset;
   int _dstAmount;
   int _dstStartMode;
   int _dstStartMonth;
   int _dstStartDayOfWeek;
   int _dstStartDay;
   int _dstStartTime;
   int _dstEndMode;
   int _dstEndMonth;
   int _dstEndDayOfWeek;
   int _dstEndDay;
   int _dstEndTime;
   int _builtInIndex = -1;
   String _defaultLongDescription;
   String _defaultShortDescription;
   int _mappedTZID;
   boolean _hidden;
   private static TimeZoneDataObject$TimeZoneDataObjectComparator _comparator;

   void setBuiltInIndex(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getDefaultLongDescription() {
      return this._defaultLongDescription;
   }

   public String getDefaultShortDescription() {
      return this._defaultShortDescription;
   }

   @Override
   public int getUID() {
      return this._uid;
   }

   public int getBuiltInIndex() {
      return this._builtInIndex;
   }

   public int getTimeZoneID() {
      return this._tzid;
   }

   public boolean isBuiltInData() {
      return this._builtInIndex != -1;
   }

   public int getMappedTZID() {
      return this._mappedTZID;
   }

   public boolean isHidden() {
      return this._hidden;
   }

   public String getTimeZoneStringID() {
      return this._zoneStringID;
   }

   public int getGMTOffset() {
      return this._gmtOffset;
   }

   public int getDSTAmount() {
      return this._dstAmount;
   }

   public int getDSTStartMode() {
      return this._dstStartMode;
   }

   public int getDSTStartMonth() {
      return this._dstStartMonth;
   }

   public int getDSTStartDayOfWeek() {
      return this._dstStartDayOfWeek;
   }

   public int getDSTStartDay() {
      return this._dstStartDay;
   }

   public int getDSTStartTime() {
      return this._dstStartTime;
   }

   public int getDSTEndMode() {
      return this._dstEndMode;
   }

   public int getDSTEndMonth() {
      return this._dstEndMonth;
   }

   public int getDSTEndDayOfWeek() {
      return this._dstEndDayOfWeek;
   }

   public int getDSTEndDay() {
      return this._dstEndDay;
   }

   public int getDSTEndTime() {
      return this._dstEndTime;
   }

   public TimeZoneDataObject(
      int var1,
      int var2,
      String var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      int var14,
      int var15,
      String var16,
      String var17,
      int var18,
      boolean var19
   ) {
      this._uid = var1;
      this._tzid = var2;
      this._zoneStringID = var3;
      this._gmtOffset = var4;
      this._dstAmount = var5;
      this._dstStartMode = var6;
      this._dstStartMonth = var7;
      this._dstStartDayOfWeek = var8;
      this._dstStartDay = var9;
      this._dstStartTime = var10;
      this._dstEndMode = var11;
      this._dstEndMonth = var12;
      this._dstEndDayOfWeek = var13;
      this._dstEndDay = var14;
      this._dstEndTime = var15;
      this._defaultLongDescription = var16;
      this._defaultShortDescription = var17;
      this._mappedTZID = var18;
      this._hidden = var19;
   }

   public static Comparator getComparator() {
      return _comparator;
   }

   public TimeZoneDataObject(
      int var1,
      String var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      int var14,
      String var15,
      String var16,
      int var17,
      boolean var18
   ) {
      this(UIDGenerator.getUID(), var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, var18);
   }
}
