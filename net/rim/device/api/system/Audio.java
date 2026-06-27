package net.rim.device.api.system;

import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.media.MediaNatives;
import net.rim.device.internal.system.AudioInternal;
import net.rim.device.internal.system.InternalServices;

public final class Audio {
   public static final int AUDIO_CODEC_WAVE;
   public static final int AUDIO_CODEC_ADPCM;
   public static final int AUDIO_CODEC_MIDI;
   public static final int AUDIO_CODEC_MP3;
   public static final int AUDIO_CODEC_G711A;
   public static final int AUDIO_CODEC_G711U;
   public static final int AUDIO_CODEC_VOICENOTE;
   public static final int AUDIO_CODEC_AMR;
   public static final int AUDIO_CODEC_RAW_PCM;
   public static final int AUDIO_CODEC_AAC;
   public static final int AUDIO_CODEC_GSM610;
   public static final int AUDIO_CODEC_WMA;
   public static final int AUDIO_CODEC_QCELP;
   public static final int AUDIO_CODEC_EVRC;
   public static final int SAMPLE_RATE_8KHZ;
   public static final int SAMPLE_RATE_16KHZ;
   public static final int SAMPLE_RATE_32KHZ;
   public static final int SAMPLE_RATE_44_1KHZ;
   public static final int SAMPLE_RATE_48KHZ;
   public static final int AUDIO_OK;
   public static final int AUDIO_ERROR_UNKNOWN;
   public static final int AUDIO_ERROR_BAD_DATA;
   public static final int AUDIO_ERROR_BAD_STATE;
   public static final int AUDIO_ERROR_FILESYSTEM_FULL;
   public static final int AUDIO_REQUEST_PENDING;

   private Audio() {
   }

   private static final void assertDeviceSettingsPermission() {
      ApplicationControl.assertChangeDeviceSettingsPermitted(true, CommonResource.getBundle(), 10133);
   }

   private static final void assertMediaPermission() {
      ApplicationControl.assertMediaPermitted(true, CommonResource.getBundle(), 10177);
   }

   public static final boolean isSupported() {
      return true;
   }

   public static final void enable(boolean var0) {
   }

   public static final native int getVolume();

   public static final boolean setVolume(int var0) {
      assertDeviceSettingsPermission();
      return AudioInternal.setVolume(var0);
   }

   public static final native boolean isHeadsetConnected();

   public static final boolean hasBuiltInHeadset() {
      return InternalServices.isDeviceCapable(3);
   }

   public static final boolean isCodecSupported(int var0) {
      return var0 >= 0 && var0 < 15 ? MediaNatives.isAudioDecoderCodecSupported(var0) : false;
   }

   public static final boolean isRecordingCodecSupported(int var0) {
      return var0 >= 0 && var0 < 15 ? MediaNatives.isAudioEncoderCodecSupported(var0) : false;
   }

   public static final int playFile(int var0, int var1, String var2) {
      throw new Object();
   }

   public static final int recordFile(int var0, int var1, String var2) {
      throw new Object();
   }

   public static final int stopFile(int var0, int var1, String var2) {
      throw new Object();
   }

   public static final void addListener(Application var0, AudioListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, AudioListener var1) {
      assertMediaPermission();
      var0.removeListener(21, var1);
   }
}
