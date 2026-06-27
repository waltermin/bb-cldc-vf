package net.rim.device.internal.system;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.internal.ui.component.SimplePasswordDialog;

public class USBPasswordRedirectManager$USBPasswordRedirectDialog extends SimplePasswordDialog implements GlobalEventListener, SystemListener2 {
   private Application _app;
   private String _usbPeripheralName;
   private boolean _lookingForKnownPassword;
   private final USBPasswordRedirectManager this$0;
   public static final int DISMISS;

   public USBPasswordRedirectManager$USBPasswordRedirectDialog(USBPasswordRedirectManager var1, String var2) {
      super(null, 1, 32, false, 134217728);
      this.this$0 = var1;
      this.setStatusPriority(-2147483644);
      this._usbPeripheralName = var2;
      this._app = Application.getApplication();
      this._app.addGlobalEventListener(this);
      this._app.addSystemListener(this);
      this.setMessage();
   }

   @Override
   protected void close(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void setMessage() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 6345609069135580235L) {
         this.closeDialogLater();
      }
   }

   private void closeDialogLater() {
      this._app.invokeLater(new USBPasswordRedirectManager$USBPasswordRedirectDialog$1(this));
   }

   @Override
   public void usbConnectionStateChange(int var1) {
      if (var1 == 4) {
         this.closeDialogLater();
      }
   }

   @Override
   public void powerOff() {
   }

   @Override
   public void powerUp() {
   }

   @Override
   public void batteryLow() {
   }

   @Override
   public void batteryGood() {
   }

   @Override
   public void batteryStatusChange(int var1) {
   }

   @Override
   public void powerOffRequested(int var1) {
   }

   @Override
   public void cradleMismatch(boolean var1) {
   }

   @Override
   public void fastReset() {
   }

   @Override
   public void backlightStateChange(boolean var1) {
   }
}
