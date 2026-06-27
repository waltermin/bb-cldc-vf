package javax.microedition.midlet;

import javax.microedition.io.Connection;
import javax.microedition.io.PushRegistry;

final class MIDletMain$MIDletPushRegistryWorkerThread extends Thread {
   private String _connectionString;
   private Connection _connection;
   private boolean _shutdown;
   private boolean _resume;
   private boolean _paused;
   private boolean _askUser;
   private boolean _allowConnection;
   private final MIDletMain this$0;
   private static final long LOGGER_GUID;
   private static final String LOGGER_NAME;

   public MIDletMain$MIDletPushRegistryWorkerThread(MIDletMain var1, String var2) {
   }

   public final void shutdown() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized void pause() {
      this._paused = true;
   }

   public final synchronized void resume() {
      if (this._paused) {
         this._resume = true;
         super.notify();
      }
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final synchronized void waitForResume() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final boolean checkFilters(String var1) {
      String var2 = PushRegistry.getFilter(this._connectionString);
      return new SimplePatternMatch(var2).match(var1);
   }
}
