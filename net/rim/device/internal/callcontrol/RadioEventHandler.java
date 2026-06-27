package net.rim.device.internal.callcontrol;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Audio;
import net.rim.device.internal.system.AudioInternal;
import net.rim.device.internal.system.AudioInternalListener;
import net.rim.device.internal.system.EventDispatchManager;

final class RadioEventHandler extends CallEventHandler implements AudioInternalListener {
   private byte[] _dtmfBuffer = new byte[16];

   public RadioEventHandler() {
      super(10);
      AbstractCallEventHandler.internalRegister(this);
      EventDispatchManager.getInstance().setDispatcher(35, new RadioEventDispatcher());
   }

   public final void startListening(Application var1) {
      var1.addListener(35, this);
      Audio.addListener(var1, this);
   }

   @Override
   public final void recordStreamDone(int var1, int var2) {
   }

   @Override
   public final void recordStreamFail(int var1) {
   }

   @Override
   public final void responseAVCModeChange(boolean var1, int var2) {
   }

   @Override
   public final void micStatusChange(boolean var1) {
   }

   @Override
   public final void dtmfDataAvailable() {
      int var1 = AudioInternal.dtmfRead(this._dtmfBuffer);

      for (int var2 = 0; var2 < var1; var2++) {
         this.dtmfData(this._dtmfBuffer[var2]);
      }
   }

   @Override
   public final void dtmfDataBufferFull() {
      this.dtmfDataAvailable();
   }
}
