package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.media.MediaNatives;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.system.InternalServices;
import net.rim.vm.TraceBack;

public final class Alert {
   public static final int ALERT_OK;
   public static final int ALERT_ERROR_UNKNOWN;
   public static final int ALERT_ERROR_BAD_DATA;
   public static final int ALERT_ERROR_BAD_STATE;
   public static final int ALERT_ERROR_FILESYSTEM_FULL;
   public static final int ALERT_INTERRUPT_OFF;
   public static final int ALERT_INTERRUPT_ON;
   public static final int ALERT_INTERRUPT_ON_NO_TRACKBALL;

   private Alert() {
   }

   private static final Alert$MidiListener getMidiListener() {
      Alert$MidiListener var0 = (Alert$MidiListener)ApplicationRegistry.getApplicationRegistry().getOrWaitFor(2808165152854904955L);
      if (var0 == null) {
         var0 = new Alert$MidiListener(null);
         ApplicationRegistry.getApplicationRegistry().put(2808165152854904955L, var0);
         Proxy.getInstance().addAlertListener(var0);
      }

      return var0;
   }

   public static final boolean isAudioSupported() {
      return Audio.isSupported();
   }

   public static final void startAudio(short[] var0, int var1) {
      assertMediaPermission();
      stopAudioImpl();
      startAudioImpl(var0, var1);
   }

   private static final native void startAudioImpl(short[] var0, int var1);

   public static final void stopAudio() {
      assertMediaPermission();
      stopAudioImpl();
   }

   private static final native void stopAudioImpl();

   public static final boolean isBuzzerSupported() {
      return InternalServices.isDeviceCapable(9);
   }

   public static final void startBuzzer(short[] var0, int var1) {
      startBuzzer(var0, var1, 2);
   }

   public static final void startBuzzer(short[] var0, int var1, boolean var2) {
      startBuzzer(var0, var1, var2 ? 2 : 0);
   }

   public static final void startBuzzer(short[] var0, int var1, int var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      assertMediaPermission();
      stopBuzzerImpl();
      startBuzzerImpl(var0, var1, var2);
   }

   private static final native void startBuzzerImpl(short[] var0, int var1, int var2);

   public static final void playBuzzer(short[] var0, int var1) {
      startBuzzer(var0, var1, 0);
   }

   public static final void stopBuzzer() {
      assertMediaPermission();
      stopBuzzerImpl();
   }

   private static final native void stopBuzzerImpl();

   public static final boolean isVibrateSupported() {
      return true;
   }

   public static final void startVibrate(int var0) {
      assertMediaPermission();
      startVibrateImpl(var0);
   }

   private static final native void startVibrateImpl(int var0);

   public static final void stopVibrate() {
      assertMediaPermission();
      stopVibrateImpl();
   }

   private static final native void stopVibrateImpl();

   public static final native boolean isMIDISupported();

   public static final int startMIDI(byte[] var0, boolean var1) {
      return startMIDI(var0, var1 ? 1 : 0, null);
   }

   public static final int startMIDI(byte[] var0, int var1) {
      return startMIDI(var0, var1, null);
   }

   public static final synchronized int startMIDI(byte[] var0, int var1, AlertListener2 var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isSingleSharedAudioChannel() {
      return MediaNatives.getNumberOfStreamingChannels() == 1;
   }

   private static final native int startMIDIImpl(byte[] var0, int var1);

   public static final synchronized void stopMIDI() {
      if (!getMidiListener().isStopped()) {
         assertMediaPermission();
         stopMIDIImpl();
      }
   }

   private static final native void stopMIDIImpl();

   public static final int pauseMIDI() {
      assertMediaPermission();
      return pauseMIDIImpl();
   }

   private static final native int pauseMIDIImpl();

   public static final int resumeMIDI() {
      assertMediaPermission();
      return resumeMIDIImpl();
   }

   private static final native int resumeMIDIImpl();

   public static final boolean isADPCMSupported() {
      return false;
   }

   public static final int startADPCM(byte[] var0, boolean var1) {
      throw new Object();
   }

   public static final void stopADPCM() {
   }

   public static final void enablePWMSync(boolean var0) {
   }

   public static final void setADPCMVolume(int var0) {
   }

   public static final void setBuzzerVolume(int var0) {
      assertDeviceSettingsPermission();
      setBuzzerVolumeImpl(var0);
   }

   private static final native void setBuzzerVolumeImpl(int var0);

   public static final void setVolume(int var0) {
      assertDeviceSettingsPermission();
      setVolumeImpl(var0);
   }

   private static final native void setVolumeImpl(int var0);

   public static final native int getVolume();

   public static final void mute(boolean var0) {
      assertDeviceSettingsPermission();
      muteImpl(var0);
   }

   private static final native void muteImpl(boolean var0);

   public static final void enablePowerAmp(boolean var0) {
   }

   private static final void assertMediaPermission() {
      ApplicationControl.assertMediaPermitted(true, CommonResource.getBundle(), 10177);
   }

   private static final void assertDeviceSettingsPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }
}
