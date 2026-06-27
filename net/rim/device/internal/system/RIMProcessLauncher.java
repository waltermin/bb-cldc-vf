package net.rim.device.internal.system;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.GlobalEventListener;

public class RIMProcessLauncher implements GlobalEventListener {
   private int _pid = 0;
   public static final long GUID;
   public static final int FLAG_START_APP;
   public static final int FLAG_START_UI_APP;
   public static final int FLAG_WAIT_FOR_TERMINATION;
   private static Object _processTermination;
   private static RIMProcessLauncher _instance;
   private static RIMProcessLauncher$Data _data;

   private RIMProcessLauncher() {
   }

   private void addAsGlobalEventListener(int var1) {
      Application var2 = Application.getApplication();
      if (var2 != null) {
         this._pid = var1;
         var2.addGlobalEventListener(this);
      }
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static int launch(Runnable var0) {
      return launch(var0, 0);
   }

   public static int launchApplication(RIMProcessLauncher$ApplicationCallback var0) {
      return launch(new RIMProcessLauncher$1(), 1, var0);
   }

   public static int launch(Runnable var0, int var1) {
      return launch(var0, var1, null);
   }

   private static int launch(Runnable var0, int var1, RIMProcessLauncher$ApplicationCallback var2) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
