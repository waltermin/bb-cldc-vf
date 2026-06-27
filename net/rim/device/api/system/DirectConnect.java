package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;

public final class DirectConnect {
   public static final int SERVICE_PHONE_ONLY;
   public static final int SERVICE_PRIVATE_SILENT;
   public static final int SERVICE_CALL_ALERT_SILENT;
   public static final int SERVICE_GROUP_SILENT;
   public static final int CALL_TYPE_NONE;
   public static final int CALL_TYPE_PRIVATE;
   public static final int CALL_TYPE_ALERT;
   public static final int CALL_TYPE_GROUP;
   public static final int GROUP_CALL_TYPE_LOCAL_AREA;
   public static final int GROUP_CALL_TYPE_WIDE_AREA;
   public static final int GROUP_CALL_TYPE_SELECTED_AREA;
   public static final int TALK_STATUS_CAN_TALK;
   public static final int TALK_STATUS_CANNOT_TALK;
   public static final int TALK_STATUS_PUSH_TO_TALK;
   public static final int ID_TYPE_URBAN;
   public static final int ID_TYPE_FLEET;
   public static final int ID_TYPE_MEMBER;
   public static final int PROFILE_TYPE_NONE;
   public static final int PROFILE_TYPE_TONE;
   public static final int PROFILE_TYPE_VIBRATE;
   public static final int PROFILE_TYPE_VIBRATE_TONE;

   private static final void assertPermission() {
      if (ApplicationControl.isPhoneAllowed(true) == 1) {
         throw new ControlledAccessException();
      }
   }

   private DirectConnect() {
   }

   public static final boolean isSupported() {
      return RadioInfo.areWAFsSupported(8);
   }

   public static final int getId(int var0) {
      assertPermission();
      return getIdImpl(var0);
   }

   private static final native int getIdImpl(int var0);

   public static final String getUFMI() {
      assertPermission();
      Object var0 = new Object();
      ((StringBuffer)var0).append(getId(1));
      ((StringBuffer)var0).append('*');
      ((StringBuffer)var0).append(getId(2));
      ((StringBuffer)var0).append('*');
      ((StringBuffer)var0).append(getId(0));
      return ((StringBuffer)var0).toString();
   }

   public static final void enableService(int var0, boolean var1) {
      assertPermission();
      enableServiceImpl(var0, var1);
   }

   private static final native void enableServiceImpl(int var0, boolean var1);

   public static final void queryService(int var0) {
      assertPermission();
      queryServiceImpl(var0);
   }

   private static final native void queryServiceImpl(int var0);

   public static final int startPrivateCall(int var0, int var1, int var2) {
      assertPermission();
      return startPrivateCallImpl(var0, var1, var2);
   }

   private static final native int startPrivateCallImpl(int var0, int var1, int var2);

   public static final int startCallAlert(int var0, int var1, int var2) {
      assertPermission();
      return startCallAlertImpl(var0, var1, var2);
   }

   private static final native int startCallAlertImpl(int var0, int var1, int var2);

   public static final int startGroupCall(int var0, int var1) {
      assertPermission();
      return startGroupCallImpl(var0, var1);
   }

   private static final native int startGroupCallImpl(int var0, int var1);

   public static final void stopCall(int var0, int var1) {
      assertPermission();
      stopCallImpl(var0, var1);
   }

   private static final native void stopCallImpl(int var0, int var1);

   public static final void clearCallAlert(int var0) {
      assertPermission();
      clearCallAlertImpl(var0);
   }

   private static final native void clearCallAlertImpl(int var0);

   public static final int getCallState(int var0, int var1) {
      assertPermission();
      return getCallStateImpl(var0, var1);
   }

   private static final native int getCallStateImpl(int var0, int var1);

   public static final int getCallId(int var0, int var1, int var2) {
      assertPermission();
      return getCallIdImpl(var0, var1, var2);
   }

   private static final native int getCallIdImpl(int var0, int var1, int var2);

   public static final int getActiveCallType() {
      assertPermission();
      return getActiveCallTypeImpl();
   }

   private static final native int getActiveCallTypeImpl();

   public static final void queryTalkGroupId() {
      assertPermission();
      queryTalkGroupIdImpl();
   }

   private static final native void queryTalkGroupIdImpl();

   public static final void setTalkGroupId(int var0) {
      assertPermission();
      setTalkGroupIdImpl(var0);
   }

   private static final native void setTalkGroupIdImpl(int var0);

   public static final boolean profileUpdate(int var0, int var1, byte var2, byte var3) {
      assertPermission();
      return profileUpdateImpl(var0, var1, var2, var3);
   }

   private static final native boolean profileUpdateImpl(int var0, int var1, byte var2, byte var3);
}
