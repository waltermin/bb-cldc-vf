package net.rim.tid.awt.im;

import net.rim.device.api.ui.Font;
import net.rim.tid.itie.IComponent;

class InputContext$1 implements Runnable {
   private final IComponent val$comp;
   private final Font val$newFont;
   private final InputContext this$0;

   InputContext$1(InputContext var1, IComponent var2, Font var3) {
      this.this$0 = var1;
      this.val$comp = var2;
      this.val$newFont = var3;
   }

   @Override
   public void run() {
      this.val$comp.setFont(this.val$newFont);
   }
}
