package net.rim.device.api.ui.container;

import net.rim.device.api.ui.UiApplication;

final class Tooltip$TooltipPollingThread extends Thread {
   private int _previous;
   private long _notificationTime;
   private UiApplication _app = UiApplication.getUiApplication();
   private static final int TIP_DELAY;
   private static final int TIP_DELAY_MS;
   private static Tooltip$TooltipPollingThread _instance;

   private static final synchronized Tooltip$TooltipPollingThread getInstance() {
      if (_instance == null) {
         _instance = new Tooltip$TooltipPollingThread();
         _instance.start();
      }

      return _instance;
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final synchronized void reset() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final Tooltip$TooltipProvider getTooltipProvider() {
      throw new RuntimeException("cod2jar: type check");
   }
}
