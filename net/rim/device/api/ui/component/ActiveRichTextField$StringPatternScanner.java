package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Font;
import net.rim.vm.Process;

class ActiveRichTextField$StringPatternScanner implements Runnable {
   private final ActiveRichTextField this$0;

   private ActiveRichTextField$StringPatternScanner(ActiveRichTextField var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      Process.waitForIdle(1000);
      String var1 = this.this$0.getText();
      int[] var2 = this.this$0.getOffsets();
      byte[] var3 = this.this$0.getAttributes();
      Font[] var4 = this.this$0.getFonts(false);
      int[] var5 = this.this$0.getForegroundColors();
      int[] var6 = this.this$0.getBackgroundColors();
      ActiveRichTextField$RegionQueue var7;
      if (var2 != null) {
         var7 = ActiveRichTextField.scanForActiveRegions(
            var1, var2, var3, var4, this.this$0.getFont(), var5, var6, this.this$0.getLabelLength(), this.this$0._patterns
         );
      } else {
         var7 = ActiveRichTextField.scanForActiveRegions(var1, this.this$0.getFont(), this.this$0.getLabelLength(), this.this$0._patterns);
      }

      this.this$0.setTextFromBackgroundScanner(var1, var2, var3, var4, var5, var6, var7);
   }

   ActiveRichTextField$StringPatternScanner(ActiveRichTextField var1, ActiveRichTextField$1 var2) {
      this(var1);
   }
}
