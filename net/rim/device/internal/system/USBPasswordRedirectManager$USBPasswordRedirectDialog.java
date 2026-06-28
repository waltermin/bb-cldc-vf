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

   public USBPasswordRedirectManager$USBPasswordRedirectDialog(USBPasswordRedirectManager _1, String usbPeripheralName) {
      super(null, 1, 32, false, 134217728);
      this.this$0 = _1;
      this.setStatusPriority(-2147483644);
      this._usbPeripheralName = usbPeripheralName;
      this._app = Application.getApplication();
      this._app.addGlobalEventListener(this);
      this._app.addSystemListener(this);
      this.setMessage();
   }

   @Override
   protected void close(int closeReason) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void setMessage() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      if (guid == 6345609069135580235L) {
         this.closeDialogLater();
      }
   }

   private void closeDialogLater() {
      this._app.invokeLater(new USBPasswordRedirectManager$USBPasswordRedirectDialog$1(this));
   }

   @Override
   public void usbConnectionStateChange(int state) {
      if (state == 4) {
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
   public void batteryStatusChange(int status) {
   }

   @Override
   public void powerOffRequested(int reason) {
   }

   @Override
   public void cradleMismatch(boolean mismatch) {
   }

   @Override
   public void fastReset() {
   }

   @Override
   public void backlightStateChange(boolean on) {
   }
}
