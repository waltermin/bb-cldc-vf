package net.rim.device.api.system;

public final class SIMCardPhoneBookEntry {
   private int _slot;
   private int _nameId;
   private String _name;
   private int _numberType;
   private int _memberId;
   private int _fleetId;
   private int _urbanId;
   private String _phoneNumber;
   public static final int MAX_NAME_LENGTH;
   public static final int MAX_PHONE_NUMBER_LENGTH;
   public static final int NUMBER_TYPE_MAIN_PHONE;
   public static final int NUMBER_TYPE_PRIVATE_PHONE;
   public static final int NUMBER_TYPE_HOME_PHONE;
   public static final int NUMBER_TYPE_WORK_PHONE;
   public static final int NUMBER_TYPE_MOBILE_PHONE;
   public static final int NUMBER_TYPE_FAX;
   public static final int NUMBER_TYPE_PAGER;
   public static final int NUMBER_TYPE_TALK_GROUP;
   public static final int NUMBER_TYPE_IP_ADDRESS;
   public static final int NUMBER_TYPE_OTHER;

   public final int getSlot() {
      return this._slot;
   }

   public final void setSlot(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getNameId() {
      return this._nameId;
   }

   public final void setNameId(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final String getName() {
      return this._name;
   }

   public final void setName(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getPhoneNumberType() {
      return this._numberType;
   }

   public final void setPhoneNumber(int var1, int var2, int var3) {
      this._numberType = 1;
      this._memberId = var1;
      this._fleetId = var2;
      this._urbanId = var3;
   }

   public final void setPhoneNumber(int var1, String var2) {
      this._numberType = var1;
      this._phoneNumber = var2;
   }

   public final int getMemberId() {
      return this._memberId;
   }

   public final int getFleetId() {
      return this._fleetId;
   }

   public final int getUrbanId() {
      return this._urbanId;
   }

   public final String getPhoneNumber() {
      return this._phoneNumber;
   }
}
