package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.container.FullScreen;

class TraceBackDialog$TraceBackScreen extends FullScreen {
   private final TraceBackDialog this$0;

   TraceBackDialog$TraceBackScreen(TraceBackDialog var1) {
      super(281474976710656L);
      this.this$0 = var1;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == 27) {
         this.this$0._app.popScreen(this);
         return true;
      } else {
         return super.keyChar(var1, var2, var3);
      }
   }
}
