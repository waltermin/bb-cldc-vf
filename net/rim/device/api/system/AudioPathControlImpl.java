package net.rim.device.api.system;

import net.rim.device.api.media.control.AudioPathControl;

final class AudioPathControlImpl implements AudioPathControl {
   private int _source;
   private int _sink;
   private AudioRouter _audioRouter;
   private int _previousSink;

   final void sinkChanged(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final int getSink() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getAudioPath() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean canSwitchToPath(int var1) {
      boolean var2 = this._audioRouter.canEnableSink(this._source, var1, false);
      return var2 && var1 == this.getAudioPath() ? false : var2;
   }

   @Override
   public final synchronized void setAudioPath(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final boolean isPathExplicitlySet() {
      return this._sink != -1;
   }

   @Override
   public final void toggleSpeakerphone() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void resetAudioPath() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void forceActive(boolean var1) {
   }

   AudioPathControlImpl(AudioRouter var1, int var2) {
      this._audioRouter = var1;
      this._source = var2;
      this._sink = -1;
   }
}
