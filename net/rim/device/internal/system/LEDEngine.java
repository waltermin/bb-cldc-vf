package net.rim.device.internal.system;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.ApplicationRegistry;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.internal.ui.UiSettings;

public class LEDEngine implements SystemListener2, GlobalEventListener, LEDConstants, AudioInternalListener {
   private int _stateFlags;
   private int[] _polyPattern;
   private boolean _poweredOff;
   private boolean _isPolychromatic;
   private boolean _isMicEnabled;
   protected LEDEventProcessor _ledEventProcessor;
   private static long LED_ENGINE_GUID;
   private static LEDEngine _ledEngineInstance;

   public void setConfigurationInternal(int var1, int var2, int var3, int var4) {
      if (!this.isLEDAccessLocked()) {
         setConfigurationNative(var1, var2, var3, var4);
      }
   }

   public void setColorConfigurationInternal(int var1, int var2, int var3, int var4) {
      if (!isValidColor(var4)) {
         throw new Object();
      }

      if (!this.isLEDAccessLocked()) {
         setColorConfigurationNative(var1, var2, var3, var4);
      }
   }

   public void setStateInternal(int var1, int var2) {
      if (!this.isLEDAccessLocked()) {
         setStateNative(var1, var2);
      }
   }

   public void setColorPatternInternal(int var1, int[] var2, boolean var3) {
      if (!this.isLEDAccessLocked()) {
         setColorPatternNative(var1, var2, var3);
      }
   }

   void lock(boolean var1) {
      ControlledAccess.assertRRISignatures(true);
      lock0(var1);
   }

   public void updateGSMFlag(boolean var1) {
      if (var1) {
         this.setFlag(8);
      } else {
         this.clearFlag(8);
      }
   }

   public void clearFlag(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setFlag(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void notifyLEDThread() {
      this._ledEventProcessor.notifyLEDThread();
   }

   public boolean contains(long var1) {
      return this._ledEventProcessor.contains(var1);
   }

   public void removeEvents(long var1, int var3) {
      this._ledEventProcessor.removeEvents(var1, var3);
   }

   public boolean isLEDAccessLocked() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isLEDAccessLocked(boolean var1) {
      boolean var2 = ITPolicy.getBoolean(24, 54, false);
      return var1 && var2;
   }

   public void addEvent(long var1, boolean var3, int var4) {
      this._ledEventProcessor.addEvent(var1, var3, var4);
   }

   @Override
   public void micStatusChange(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void dtmfDataBufferFull() {
   }

   @Override
   public void dtmfDataAvailable() {
   }

   @Override
   public void responseAVCModeChange(boolean var1, int var2) {
   }

   @Override
   public void recordStreamFail(int var1) {
   }

   @Override
   public void recordStreamDone(int var1, int var2) {
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 6270993390899536868L) {
         this.updateGSMFlag(UiSettings.getLEDCoverageIndicatorStatus());
      }
   }

   @Override
   public void powerOffRequested(int var1) {
   }

   @Override
   public void cradleMismatch(boolean var1) {
   }

   @Override
   public void fastReset() {
   }

   @Override
   public void backlightStateChange(boolean var1) {
   }

   @Override
   public void usbConnectionStateChange(int var1) {
   }

   @Override
   public void batteryLow() {
   }

   @Override
   public void batteryGood() {
   }

   @Override
   public void powerOff() {
      this._poweredOff = true;
   }

   @Override
   public void powerUp() {
      this._poweredOff = false;
      this.batteryStatusChange(DeviceInfo.getBatteryStatus());
      this.setPattern(this._stateFlags);
   }

   @Override
   public void batteryStatusChange(int var1) {
      if ((268435456 & var1) != 268435456 && (-2147483648 & var1) != Integer.MIN_VALUE && (16384 & var1) != 16384) {
         this.setFlag(4);
      } else {
         this.clearFlag(4);
      }
   }

   private static native void lock0(boolean var0);

   private void setPattern(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static boolean isSupported(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static native void setStateNative(int var0, int var1);

   public static boolean isPolychromatic(int var0) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private static native void setColorPatternNative(int var0, int[] var1, boolean var2);

   public static boolean isPolychromatic() {
      return isPolychromatic(0);
   }

   private static native void setColorConfigurationNative(int var0, int var1, int var2, int var3);

   private static boolean isValidColor(int var0) {
      return var0 >= 0 && var0 <= 16777215;
   }

   public static LEDEngine getInstance() {
      if (_ledEngineInstance == null) {
         ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
         _ledEngineInstance = (LEDEngine)var0.getOrWaitFor(LED_ENGINE_GUID);
         if (_ledEngineInstance == null) {
            _ledEngineInstance = new LEDEngine();
            var0.put(LED_ENGINE_GUID, _ledEngineInstance);
         }
      }

      return _ledEngineInstance;
   }

   private static native void setConfigurationNative(int var0, int var1, int var2, int var3);
}
