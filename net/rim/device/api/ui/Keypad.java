package net.rim.device.api.ui;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.AudioRouter;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.UiInternal;
import net.rim.device.internal.ui.UiSettings;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.im.layout.SLKeyLayout;
import net.rim.vm.TraceBack;

public final class Keypad {
   public static final long GUID_KEYPAD_OPTIONS_CHANGED;
   public static final long GUID_KEYPAD_CHANGED;
   public static final int HW_MAP_QWERTY;
   public static final int HW_MAP_AZERTY;
   public static final int HW_MAP_QWERTZ;
   public static final int HW_LAYOUT_NUM_ROW;
   public static final int HW_LAYOUT_PHONE;
   public static final int HW_LAYOUT_REDUCED;
   public static final int HW_LAYOUT_REDUCED_24;
   public static final int HW_LAYOUT_LEGACY;
   public static final int HW_LAYOUT_32;
   public static final int HW_LAYOUT_39;
   public static final int KEY_BACKLIGHT;
   public static final int KEY_BACKSPACE;
   public static final int KEY_DELETE;
   public static final int KEY_ESCAPE;
   public static final int KEY_ENTER;
   public static final int KEY_SPACE;
   public static final int KEY_SHIFT_RIGHT;
   public static final int KEY_SHIFT_X;
   public static final int KEY_ALT;
   public static final int KEY_SHIFT_LEFT;
   public static final int KEY_APPLICATION;
   public static final int KEY_SPEAKERPHONE;
   public static final int KEY_SEND;
   public static final int KEY_END;
   public static final int KEY_MIDDLE;
   public static final int KEY_CONVENIENCE_1;
   public static final int KEY_CONVENIENCE_2;
   public static final int KEY_NEXT;
   public static final int KEY_VOLUME_UP;
   public static final int KEY_VOLUME_DOWN;
   public static final int KEY_MENU;
   public static final int KEY_REPEAT_DELAY_SLOW;
   public static final int KEY_REPEAT_DELAY_NORMAL;
   public static final int KEY_REPEAT_DELAY_FAST;
   public static final int KEY_REPEAT_OFF;
   public static final int KEY_REPEAT_SLOW;
   public static final int KEY_REPEAT_NORMAL;
   public static final int KEY_REPEAT_FAST;
   public static final int STATUS_CHARACTER;
   public static final int STATUS_UNALT;
   private static final int KEYPAD_DATA_VERSION_KEY;
   private static final int KEYPAD_DATA_VERSION;
   private static final int KEYPAD_LOCALE_VARIANT_KEY;
   private static final int NUM_LAYOUT_KEY;
   private static final int KEYPAD_OS_LOCALE_KEY;
   private static final int KEYPAD_OS_LOCALE_VARIANT_KEY;
   private static final int KEYPAD_ID;
   public static final int KEYPAD_MODE_NORMAL;
   public static final int KEYPAD_MODE_NUMLOCK;
   public static final int KEYPAD_MODE_MULTITAP;
   public static final int KEYPAD_MODE_CUSTOM;
   private static final long KEYPAD_DATA_KEY;
   private static PersistentObject _persistentKeypadData;
   private static Keypad$KeypadData _keypadData;

   private Keypad() {
   }

   public static final void commit() {
      commit(false, false);
   }

   private static final void commit(boolean var0, boolean var1) {
      _persistentKeypadData.commit();
      if (var0) {
         RIMGlobalMessagePoster.postGlobalEvent(6498096261923284925L);
      }

      if (var1) {
         RIMGlobalMessagePoster.postGlobalEvent(-3769281743063593175L);
      }
   }

   public static final char getAltedChar(char var0) {
      SLKeyLayout var1 = getLayout();
      return var1 == null ? '\u0000' : var1.getAltedChar(var0);
   }

   public static final Locale[] getAvailableLocales() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static final void addLocaleTo(Locale[] var0, Locale var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final Locale getLocale() {
      return _keypadData.getLocale();
   }

   public static final char getUnaltedChar(char var0) {
      SLKeyLayout var1 = getLayout();
      return var1 == null ? '\u0000' : Character.toUpperCase(var1.getUnaltedChar(var0));
   }

   public static final SLKeyLayout getLayout() {
      Object var0 = InputContext.getInstance().getInputMethodControlObject();
      return var0 == null ? null : ((SLControlObject)var0).getKeyLayout();
   }

   public static final boolean isKeyToneSupported() {
      return !InternalServices.isSoftwareCapable(4);
   }

   public static final char map(int var0) {
      return map(key(var0), status(var0));
   }

   public static final char map(int var0, int var1) {
      var1 &= 28695;
      if ((var1 & 4) != 0 && (var1 & 16) != 0) {
         var1 &= -7;
      }

      if ((var1 & 4) != 0) {
         var1 &= -3;
         if ((var1 & 1) != 0) {
            var1 &= -5;
         }
      }

      if ((var1 & 16) != 0) {
         var1 &= -2;
         if ((var1 & 2) != 0) {
            var1 &= -17;
         }
      }

      SLKeyLayout var2 = getLayout();
      return var2 == null ? '\u0000' : var2.getKeyChars(var0, SLKeyLayout.convertStatusToModifiers(var1), false).charAt(0);
   }

   public static final int key(int var0) {
      return var0 >>> 16;
   }

   public static final native int getHardwareMap();

   public static final native int getHardwareLayout();

   public static final int getKeyCode(char var0, int var1) {
      SLKeyLayout var2 = getLayout();
      return var2 == null ? 0 : getLayout().getOriginalKeyCode(var0, SLKeyLayout.convertStatusToModifiers(var1));
   }

   public static final int status(int var0) {
      return var0 & 65535;
   }

   public static final void updateKeyTone() {
      if (isKeyToneSupported()) {
         boolean var0 = UiSettings.getKeypadToneEnabled();
         if (var0 != isKeyToneEnabled()) {
            AudioRouter.getInstance().enableInputFeedback(1, var0);
         }
      }
   }

   public static final boolean isOnKeypad(char var0) {
      SLKeyLayout var1 = getLayout();
      return var1.contains(var0);
   }

   public static final boolean isValidKeyCode(int var0) {
      int var1 = key(var0);
      return isValidScanCode(var1);
   }

   private static final boolean isValidScanCode(int var0) {
      switch (var0) {
         case 0:
         case 8:
         case 10:
         case 17:
         case 18:
         case 19:
         case 21:
         case 27:
         case 32:
         case 127:
         case 256:
         case 257:
         case 258:
         case 259:
         case 261:
         case 273:
         case 4096:
         case 4097:
         case 4098:
            return true;
         default:
            return var0 >= 65 && var0 <= 90;
      }
   }

   public static final int keycode(char var0, int var1) {
      if ((var1 & 12288) == 0 && !isValidScanCode(var0)) {
         return 0;
      }

      if (var0 < 127) {
         var0 = CharacterUtilities.toUpperCase(var0, 1701707776);
      }

      return var0 << 16 | var1 & 65535;
   }

   private static final void loadKeypadData() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean setKeypadLocale(Locale var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      _keypadData.setLocale(var0);
      commit(false, true);
      return true;
   }

   private static final native void changeShiftState0(int var0);

   public static final void changeShiftState(int var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      changeShiftState0(var0);
   }

   private static final native void enableStandbyMode0(boolean var0);

   public static final void enableStandbyMode(boolean var0) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      enableStandbyMode0(var0);
   }

   public static final boolean hasSendEndKeys() {
      switch (getHardwareLayout()) {
         case 1364341300:
         case 1364346180:
         case 1364669234:
         case 1364669241:
            return true;
         default:
            return false;
      }
   }

   public static final boolean hasCurrencyKey() {
      switch (getHardwareLayout()) {
         case 1364669234:
         case 1364669241:
            return true;
         default:
            return false;
      }
   }

   public static final boolean hasFrontConvenienceKey() {
      switch (getHardwareLayout()) {
         case 1364346180:
         case 1364669234:
            return true;
         default:
            return false;
      }
   }

   public static final boolean hasLeftSideConvenienceKey() {
      switch (getHardwareLayout()) {
         case 1364341300:
         case 1364669234:
         case 1364669241:
            return true;
         case 1364346180:
            if (InternalServices.getHardwareID() == -2080372477) {
               return true;
            }
         default:
            return false;
      }
   }

   public static final boolean hasRightSideConvenienceKey() {
      switch (getHardwareLayout()) {
         case 1364341300:
            return true;
         case 1364669241:
            if (InternalServices.getFormFactor() == 15) {
               return true;
            }

            return false;
         default:
            return false;
      }
   }

   public static final boolean hasMuteKey() {
      switch (getHardwareLayout()) {
         case 1364341300:
         case 1364669234:
         case 1364669241:
            return true;
         case 1364346180:
            if (InternalServices.getHardwareID() == -2080372477) {
               return true;
            }
         default:
            return false;
      }
   }

   public static final boolean hasSpeakerphoneKey() {
      return InternalServices.getHardwareID() == 67111173;
   }

   public static final void setMode(int var0) {
      if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
         ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
      }

      setModeImpl(var0);
   }

   private static final native void setModeImpl(int var0);

   public static final void setMode(int var0, String var1) {
      if (!ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(0), 51)) {
         ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
      }

      if ((var1 != null || var0 != 2) && (var1 == null || var0 == 2)) {
         if (var1 != null) {
            EncodedImage var2 = ThemeManager.getActiveTheme().getImage(var1, true);
            if (var2 != null) {
               UiInternal.setThemeIcon(8, var2);
            }
         }

         setModeImpl(var0);
      }
   }

   public static final void setKeyTone(boolean var0) {
      if (isKeyToneSupported()) {
         AudioRouter.getInstance().enableInputFeedback(1, var0);
      }
   }

   public static final boolean isKeyToneEnabled() {
      return isKeyToneSupported() && UiSettings.getKeypadToneEnabled();
   }
}
