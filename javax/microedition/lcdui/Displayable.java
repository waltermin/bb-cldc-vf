package javax.microedition.lcdui;

import net.rim.device.api.ui.XYRect;

public class Displayable {
   private MIDPScreen _peer;

   Displayable(MIDPScreen var1) {
      this._peer = var1;
   }

   final MIDPScreen getPeer() {
      return this._peer;
   }

   final void setPeer(MIDPScreen var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public String getTitle() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setTitle(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public Ticker getTicker() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setTicker(Ticker var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean isShown() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void addCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeCommand(Command var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setCommandListener(CommandListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public int getWidth() {
      MIDPScreen var1 = this.getPeer();
      return var1 != null ? var1.getWidth() : 0;
   }

   public int getHeight() {
      MIDPScreen var1 = this.getPeer();
      if (var1 != null) {
         XYRect var2 = var1.getDisplayableAreaExtent();
         if (var2 != null) {
            return var2.height;
         }
      }

      return 0;
   }

   protected void sizeChanged(int var1, int var2) {
   }
}
