package net.rim.device.api.system;

import net.rim.device.api.media.control.AudioPathControl;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.IntVector;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.device.internal.system.AudioInternal;
import net.rim.device.internal.system.TTY;
import net.rim.vm.Message;

public final class AudioRouter implements AudioHeadsetListener {
   private boolean _masterVolumeChangeSupported;
   private int _audioSink;
   private int[] _numAudioSources;
   private AudioPathControlImpl[] _audioControlsForSource;
   private boolean _bluetoothSCOSinkAvailable;
   private boolean _bluetoothSCORemoteVolumeControl;
   private boolean _bluetoothSCONREC;
   private boolean _bluetoothSCOHasVoiceRecognition;
   private String _bluetoothSCODeviceName;
   private boolean _bluetoothA2DPSinkAvailable;
   private String _bluetoothA2DPDeviceName;
   private PersistentObject _masterVolumePersistentObject;
   private byte[] _masterVolume;
   private byte[] _volumeBoostData;
   private byte[] _eqPresetData;
   private IntVector _feedbackEnabledSources;
   private AudioSinkCallback _callback;
   private static final long GUID;
   private static final long AUDIO_VOLUME_PERSISTENT_KEY;
   static final int EVENT_AUDIO_VOLUME_CHANGED;
   static final int EVENT_AUDIO_SINK_CHANGED;
   static final int EVENT_AUDIO_SOURCE_CHANGED;
   private static final int DEFAULT_TONE_VOLUME;
   private static final boolean DEBUG;
   private static final boolean _a2dpSupportedForVideo;
   public static final int AUDIO_SOURCE_PHONE;
   public static final int AUDIO_SOURCE_VAD;
   public static final int AUDIO_SOURCE_VOICE_NOTES;
   public static final int AUDIO_SOURCE_MEDIA_PLAYER_VIDEO;
   public static final int AUDIO_SOURCE_MEDIA_PLAYER_AUDIO;
   public static final int AUDIO_SOURCE_ATTACHMENT_VIEWER;
   public static final int AUDIO_SOURCE_ALERTS;
   public static final int AUDIO_SOURCE_VIDEO_RECORDING;
   public static final int AUDIO_SOURCE_AUDIO_RECORDING;
   public static final int AUDIO_SOURCE_VIDEO_PLAYBACK;
   public static final int AUDIO_SOURCE_AUDIO_PLAYBACK;
   public static final int AUDIO_SOURCE_DEFAULT;
   public static final int NUM_AUDIO_SOURCES;
   public static final int AUDIO_SINK_HANDSET;
   public static final int AUDIO_SINK_HANDSFREE;
   public static final int AUDIO_SINK_BLUETOOTH_SCO;
   public static final int AUDIO_SINK_HEADSET;
   public static final int AUDIO_SINK_HEADSET_HANDSFREE;
   public static final int AUDIO_SINK_BLUETOOTH_A2DP;
   public static final int NUM_AUDIO_SINKS;
   public static final int FEEDBACK_TRACKBALL_CLICK;
   public static final int FEEDBACK_KEYPAD_BEEP;
   public static final int EQ_PRESET_NORMAL;
   public static final int EQ_PRESET_BOOST_BASS;
   public static final int EQ_PRESET_BOOST_TREBLE;
   private static final int[] _validSourceSinks;

   public final void setAudioSinkCallback(AudioSinkCallback var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void updateMasterVolume() {
      this.updateMasterVolume(false);
   }

   public final boolean canChangeMasterVolume() {
      switch (this.getActiveSource()) {
         case -1:
            return false;
         case 0:
         case 1:
         case 2:
         default:
            return true;
      }
   }

   public final synchronized int getSink() {
      return this._audioSink;
   }

   public final synchronized boolean setSink(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final boolean canEnableSink(int var1) {
      return this.canEnableSink(this.getActiveSource(), var1, true);
   }

   public final boolean canEnableSink(int var1, int var2, boolean var3) {
      return this.getAudioMode(var1, var2, var3) != -1;
   }

   public final boolean isVolumeChangeSupported() {
      return this._masterVolumeChangeSupported;
   }

   public final int getMasterVolume() {
      int var1 = this.getMasterVolumeIndex();
      return this._masterVolume[var1];
   }

   public final void setMasterVolume(int var1) {
      this.setMasterVolume(var1, false);
   }

   public final boolean startTone(int var1) {
      if (!this.anyActiveSources() && Audio.isHeadsetConnected()) {
         this.setSink(3);
      }

      return AudioInternal.startTone(var1);
   }

   public final boolean stopTone() {
      boolean var1 = AudioInternal.stopTone();
      if (!this.anyActiveSources() && Audio.isHeadsetConnected()) {
         this.resetSink();
      }

      return var1;
   }

   public final void setMasterVolume(int var1, boolean var2) {
      if (this._masterVolumeChangeSupported) {
         int var3 = this.getMasterVolumeIndex();
         this.setMasterVolume(var1, var2, var3);
      }
   }

   public final void setMasterVolume(int var1, boolean var2, int var3) {
      byte var4 = 10;
      if (var3 == 2) {
         var4 = 0;
      }

      var1 = MathUtilities.clamp(var4, var1, 100);
      if (var1 != this._masterVolume[var3]) {
         this._masterVolume[var3] = (byte)var1;
         this.updateMasterVolume(true);
         this.postEvent(1, var2 ? 1 : 0);
      }
   }

   public final int incrementMasterVolume(int var1) {
      int var2 = this.getMasterVolumeIndex();
      if (!this._masterVolumeChangeSupported) {
         return this._masterVolume[var2];
      }

      byte var3 = 10;
      if (var2 == 2) {
         var3 = 0;
      }

      int var4 = MathUtilities.clamp(var3, this._masterVolume[var2] + var1, 100);
      if (var4 == this._masterVolume[var2]) {
         return this._masterVolume[var2];
      }

      this._masterVolume[var2] = (byte)var4;
      this.updateMasterVolume(true);
      this.postEvent(1);
      return this._masterVolume[var2];
   }

   public final synchronized void setBluetoothSCOSinkProperties(boolean var1, boolean var2, boolean var3, String var4, boolean var5) {
      this._bluetoothSCOSinkAvailable = var1;
      this._bluetoothSCORemoteVolumeControl = var2;
      this._bluetoothSCONREC = var3;
      this._bluetoothSCODeviceName = var4;
      this._bluetoothSCOHasVoiceRecognition = var5;
      if (var1 && this.isValidSink(2) || !var1 && this.getSink() == 2) {
         this.resetSink();
      }
   }

   public final synchronized void setBluetoothA2DPSinkProperties(boolean var1, String var2) {
      this._bluetoothA2DPSinkAvailable = var1;
      this._bluetoothA2DPDeviceName = var2;
      if (var1 && this.isValidSink(5) || !var1 && this.getSink() == 5) {
         this.resetSink();
      }
   }

   public final boolean isVolumeBoostModeSupported() {
      byte[] var1 = Branding.getData(23);
      return var1 != null && var1.length == 1 && var1[0] != 0 ? false : isAudioModeParamSupported(33, 3);
   }

   public final synchronized void setVolumeBoostMode(boolean var1) {
      byte[] var2 = Branding.getData(23);
      if (var2 == null || var2.length != 1 || var2[0] == 0) {
         this._volumeBoostData = new byte[3];
         this._volumeBoostData[0] = 3;
         this._volumeBoostData[1] = 1;
         this._volumeBoostData[2] = (byte)(var1 ? 1 : 0);
         this.setSink(this.getSink());
      }
   }

   public final boolean isEQPresetSupported() {
      return isAudioModeParamSupported(9, 1);
   }

   public final synchronized void setEQPreset(int var1) {
      if (var1 >= 0 && var1 <= 2) {
         this._eqPresetData = new byte[3];
         this._eqPresetData[0] = 1;
         this._eqPresetData[1] = 1;
         this._eqPresetData[2] = (byte)var1;
         this.setSink(this.getSink());
      } else {
         throw new Object();
      }
   }

   public final synchronized int getEQPreset() {
      return this._eqPresetData != null ? this._eqPresetData[2] : 0;
   }

   public final synchronized void addSource(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized void removeSource(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized int getActiveSource() {
      int var1;
      for (var1 = 0; var1 < 11; var1++) {
         if (this._numAudioSources[var1] != 0) {
            return var1;
         }
      }

      return var1;
   }

   public final synchronized boolean isSourceAdded(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int getDefaultSink(int var1) {
      boolean var2 = Audio.isHeadsetConnected();
      switch (var1) {
         case -1:
            return 1;
         case 0:
         default:
            if (var2 && this.headsetHasMic()) {
               return 3;
            } else {
               if (this._bluetoothSCOSinkAvailable) {
                  return 2;
               }

               return 0;
            }
         case 1:
            if (var2 && this.headsetHasMic()) {
               return 3;
            } else {
               if (this._bluetoothSCOSinkAvailable && this._bluetoothSCOHasVoiceRecognition) {
                  return 2;
               }

               return 1;
            }
         case 2:
            if (var2 && this.headsetHasMic()) {
               return 3;
            } else {
               if (this._bluetoothSCOSinkAvailable) {
                  return 2;
               }

               return 1;
            }
         case 3:
            if (var2) {
               return 3;
            }

            return 1;
         case 4:
            if (var2) {
               return 3;
            } else {
               if (this._bluetoothA2DPSinkAvailable) {
                  return 5;
               }

               return 1;
            }
         case 5:
            if (var2) {
               return 3;
            } else if (this._bluetoothA2DPSinkAvailable) {
               return 5;
            } else {
               if (this._bluetoothSCOSinkAvailable) {
                  return 2;
               }

               return 0;
            }
         case 6:
            return 1;
         case 7:
         case 8:
            if (var2 && this.headsetHasMic()) {
               return 3;
            }

            return 1;
         case 9:
            if (var2) {
               return 3;
            }

            return 1;
         case 10:
            if (var2) {
               return 3;
            } else {
               return this._bluetoothA2DPSinkAvailable ? 5 : 1;
            }
      }
   }

   public final synchronized void resetSink() {
      for (int var1 = 0; var1 < 11; var1++) {
         if (this._numAudioSources[var1] != 0) {
            this.setSink(this.getDefaultSink(var1));
            return;
         }
      }

      this.setSink(1);
   }

   public final synchronized boolean isValidSink(int var1) {
      for (int var2 = 0; var2 < 11; var2++) {
         if (this._numAudioSources[var2] != 0) {
            if ((_validSourceSinks[var2] & 1 << var1) != 0) {
               return true;
            }

            return false;
         }
      }

      return (_validSourceSinks[11] & 1 << var1) != 0;
   }

   public final synchronized void enableInputFeedback(int var1, boolean var2) {
      if (var2) {
         if (!this._feedbackEnabledSources.contains(var1)) {
            this._feedbackEnabledSources.addElement(var1);
            this.updateInputFeedback();
            return;
         }
      } else {
         this._feedbackEnabledSources.removeElement(var1);
         enableInputFeedback0(var1, false);
      }
   }

   public final AudioPathControl getAudioPathControl(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized void fastReset() {
      Arrays.zero(this._numAudioSources);
      this.updateInputFeedback();
      this.resetSink();
   }

   @Override
   public final void headsetButtonUnclick(int var1, int var2) {
   }

   @Override
   public final synchronized void headsetInserted(int var1) {
      this.resetSink();
   }

   @Override
   public final synchronized void headsetRemoved() {
      if (this.getSink() == 3 || this.getSink() == 4) {
         this.resetSink();
      }
   }

   @Override
   public final void headsetButtonClick(int var1, int var2) {
   }

   private final boolean anyActiveSources() {
      for (int var1 = 0; var1 < 11; var1++) {
         if (this._numAudioSources[var1] != 0) {
            return true;
         }
      }

      return false;
   }

   private final int getAudioMode(int var1, int var2, boolean var3) {
      if (isAudioModeSupported(16) && var1 != 6 && TTY.getSource() == 0) {
         switch (TTY.getMode()) {
            case -1:
               break;
            case 0:
            default:
               return 16;
            case 1:
               return 17;
            case 2:
               return 18;
         }
      }

      switch (var2) {
         case -1:
            return -1;
         case 0:
         default:
            if (!this.checkHeadset(var1)) {
               return -1;
            } else {
               switch (var1) {
                  case -1:
                  case 1:
                  case 3:
                  case 4:
                     return 34;
                  case 0:
                  case 2:
                  case 5:
                  case 7:
                  case 8:
                  default:
                     return 9;
                  case 6:
                     return 3;
               }
            }
         case 1:
            if (!this.checkHeadset(var1)) {
               return -1;
            } else {
               switch (var1) {
                  case -1:
                  case 3:
                  case 4:
                     return 32;
                  case 0:
                  case 5:
                  default:
                     return 6;
                  case 1:
                     return 20;
                  case 2:
                  case 7:
                  case 8:
                     return 41;
                  case 6:
                     return 0;
               }
            }
         case 2:
            if (!this.checkHeadset(var1)) {
               return -1;
            } else if (var3 && !this._bluetoothSCOSinkAvailable) {
               return -1;
            } else {
               switch (var1) {
                  case -1:
                  case 3:
                  case 4:
                  case 6:
                     return -1;
                  case 0:
                  case 2:
                  case 5:
                  case 7:
                  case 8:
                  case 9:
                  case 10:
                  default:
                     if (this._bluetoothSCONREC) {
                        return 11;
                     }

                     return 10;
                  case 1:
                     if (!this._bluetoothSCOHasVoiceRecognition) {
                        return -1;
                     } else {
                        if (this._bluetoothSCONREC) {
                           return 22;
                        }

                        return 21;
                     }
               }
            }
         case 3:
            if (!Audio.isHeadsetConnected()) {
               return -1;
            } else {
               switch (var1) {
                  case -1:
                  case 3:
                  case 4:
                     return 33;
                  case 0:
                  default:
                     switch (TTY.getMode()) {
                        case -1:
                           break;
                        case 0:
                        default:
                           return 13;
                        case 1:
                           return 14;
                        case 2:
                           return 15;
                     }
                  case 2:
                  case 5:
                  case 7:
                  case 8:
                     return 7;
                  case 1:
                     return 19;
                  case 6:
                     return 1;
               }
            }
         case 4:
            if (!Audio.isHeadsetConnected()) {
               return -1;
            }

            return 4;
         case 5:
            if (!this.checkHeadset(var1)) {
               return -1;
            } else if (var3 && !this._bluetoothA2DPSinkAvailable) {
               return -1;
            } else {
               switch (var1) {
                  case 4:
                  case 5:
                  case 10:
                     return 36;
                  default:
                     return -1;
               }
            }
      }
   }

   private final void updateMasterVolume(boolean var1) {
      if (this.canChangeMasterVolume()) {
         if (this.getSink() == 2) {
            AudioInternal.setVolume(50);
            return;
         }

         AudioInternal.setVolume(this.getMasterVolume());
         AudioInternal.setToneVolume(100);
         if (var1) {
            this._masterVolumePersistentObject.commit();
            return;
         }
      } else {
         AudioInternal.setVolume(100);
         AudioInternal.setToneVolume(75);
      }
   }

   public static final AudioRouter getInstance() {
      ApplicationRegistry var0 = ApplicationRegistry.getApplicationRegistry();
      AudioRouter var1 = (AudioRouter)var0.getOrWaitFor(-672359094277821107L);
      if (var1 == null) {
         var1 = new AudioRouter();
         var0.put(-672359094277821107L, var1);
      }

      return var1;
   }

   private final synchronized void updateInputFeedback() {
      boolean var1 = true;
      if (this._numAudioSources[0] != 0
         || this._numAudioSources[1] != 0
         || this._numAudioSources[2] != 0
         || this._numAudioSources[3] != 0
         || this._numAudioSources[4] != 0
         || this._numAudioSources[5] != 0
         || this._numAudioSources[7] != 0
         || this._numAudioSources[8] != 0
         || this._numAudioSources[9] != 0
         || this._numAudioSources[10] != 0) {
         var1 = false;
      }

      for (int var2 = this._feedbackEnabledSources.size() - 1; var2 >= 0; var2--) {
         enableInputFeedback0(this._feedbackEnabledSources.elementAt(var2), var1);
      }
   }

   private AudioRouter() {
   }

   private final boolean checkHeadset(int var1) {
      if (Audio.isHeadsetConnected()) {
         switch (var1) {
            case -1:
               return false;
            case 0:
            case 1:
            case 2:
               if (!this.headsetHasMic()) {
                  return true;
               }

               return false;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            default:
               return true;
         }
      } else {
         return true;
      }
   }

   private final int getMasterVolumeIndex() {
      return this.getSink();
   }

   private final void postEvent(int var1, int var2) {
      Object var3 = new Object(8, var1, var2);
      Object var4 = ApplicationManager.getApplicationManager();
      ((ApplicationManagerInternal)var4).postMessage((Message)var3);
   }

   private final void postEvent(int var1) {
      this.postEvent(var1, 0);
   }

   private final boolean headsetHasMic() {
      switch (AudioInternal.getHeadsetType()) {
         case 2:
            return true;
         case 3:
         case 4:
         default:
            return false;
      }
   }

   public static final void addListener(Application var0, AudioRouterListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void removeListener(Application var0, AudioRouterListener var1) {
      var0.removeListener(8, var1);
   }

   public static final native boolean isAudioModeSupported(int var0);

   private static final native boolean isAudioModeParamSupported(int var0, int var1);

   private static final native void setAudioMode(int var0, byte[] var1);

   private static final native int getAudioMode();

   private static final native void enableInputFeedback0(int var0, boolean var1);
}
