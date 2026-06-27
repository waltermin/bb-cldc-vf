package javax.microedition.rms;

import net.rim.device.api.util.ListenerUtilities;

class RecordEventGenerator {
   protected Object[] _listeners;

   public synchronized void addRecordListener(RecordListener var1) {
      this._listeners = ListenerUtilities.addListener(this._listeners, var1);
   }

   public synchronized void removeRecordListener(RecordListener var1) {
      this._listeners = ListenerUtilities.removeListener(this._listeners, var1);
   }

   void loadRecordIDs(int[] var1) {
      throw null;
   }

   protected final void notifyRecordAdded(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected final void notifyRecordChanged(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected final void notifyRecordDeleted(RecordStore var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
