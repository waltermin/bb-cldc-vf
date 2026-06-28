package net.rim.device.api.ui.container;

import net.rim.device.api.system.DeviceInfo;
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
      while (true) {
         try {
            Tooltip$TooltipProvider tooltipProvider = this.getTooltipProvider();
            if (tooltipProvider != null && tooltipProvider.hashCode() != this._previous) {
               long idleTime = DeviceInfo.getIdleTime();
               long notificationIdleTime = System.currentTimeMillis() - this._notificationTime;
               if (1 > idleTime || 1000 > notificationIdleTime) {
                  Thread.sleep(200);
               } else {
                  this._previous = tooltipProvider.hashCode();
                  Tooltip.show(tooltipProvider);
               }
            } else {
               Tooltip$TooltipProvider var8 = null;
               synchronized (this) {
                  this.wait();
               }
            }
         } catch (InterruptedException var7) {
         }
      }
   }

   public static final synchronized void reset() {
      Tooltip$TooltipPollingThread instance = getInstance();
      synchronized (instance) {
         instance._previous = 0;
         instance._notificationTime = System.currentTimeMillis();
         instance.notify();
      }
   }

   private final Tooltip$TooltipProvider getTooltipProvider() {
      throw new RuntimeException("cod2jar: type check");
   }
}
