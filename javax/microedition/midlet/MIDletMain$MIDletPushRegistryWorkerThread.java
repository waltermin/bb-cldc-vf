package javax.microedition.midlet;

import java.io.IOException;
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

   public MIDletMain$MIDletPushRegistryWorkerThread(MIDletMain _1, String connection) {
   }

   public final void shutdown() {
      synchronized (this) {
         this._shutdown = true;

         try {
            if (this._connection != null) {
               this._connection.close();
            }
         } catch (IOException var4) {
         }
      }
   }

   public final synchronized void pause() {
      this._paused = true;
   }

   public final synchronized void resume() {
      if (this._paused) {
         this._resume = true;
         this.notify();
      }
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized void waitForResume() {
      if (!this._resume) {
         try {
            this.wait();
         } catch (InterruptedException var2) {
         }
      }

      this._resume = false;
      this._paused = false;
   }

   private final boolean checkFilters(String hostString) {
      String pattern = PushRegistry.getFilter(this._connectionString);
      return new SimplePatternMatch(pattern).match(hostString);
   }
}
