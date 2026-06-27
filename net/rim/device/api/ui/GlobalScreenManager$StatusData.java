package net.rim.device.api.ui;

import net.rim.vm.Process;

class GlobalScreenManager$StatusData {
   Screen screen;
   Process process;
   UiEngineImpl engine;
   int priority;
   boolean inputRequired;
   boolean suppress;
   boolean redisplay;

   GlobalScreenManager$StatusData(Screen var1, int var2, boolean var3, boolean var4, Process var5, UiEngineImpl var6) {
      this.screen = var1;
      this.priority = var2;
      this.inputRequired = var3;
      this.suppress = var4;
      this.process = var5;
      this.engine = var6;
      this.redisplay = false;
   }
}
