package net.rim.tid.im.layout;

import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextInputDialog;

public final class CurrencyKeyDialog extends Dialog implements TextInputDialog {
   private static CurrencyKeyDialog _instance;

   public CurrencyKeyDialog(String var1, Object[] var2, int var3) {
      super(var1, var2, null, var3, null, 1);
      this.setEscapeEnabled(true);
   }

   public static final void closeDialog() {
      if (_instance != null) {
         _instance.close();
         _instance = null;
      }
   }

   public static final int ask(String var0, Object[] var1, int var2) {
      _instance = new CurrencyKeyDialog(var0, var1, var2);
      return _instance.doModal();
   }

   @Override
   public final int processKeyEvent(int var1, char var2, int var3, int var4) {
      return Keypad.key(var3) == 36 ? 65536 : super.processKeyEvent(var1, var2, var3, var4);
   }
}
