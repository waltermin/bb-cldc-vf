package net.rim.device.api.system;

import java.lang.ref.Reference;
import net.rim.device.api.util.Arrays;

class Alert$MidiListener implements AlertListener2 {
   private Reference[] _midiListeners = new Reference[0];
   public static final long GUID;

   private Alert$MidiListener() {
   }

   private synchronized int midiStart(AlertListener2 var1, int var2) {
      Arrays.add(this._midiListeners, var1 == null ? null : new Object(var1));
      return var2;
   }

   private synchronized boolean isStopped() {
      return this._midiListeners.length == 0;
   }

   @Override
   public void midiDone(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void audioDone(int var1) {
   }

   @Override
   public void buzzerDone(int var1) {
   }

   @Override
   public void vibrateDone(int var1) {
   }

   Alert$MidiListener(Alert$1 var1) {
      this();
   }
}
