package net.rim.device.internal.system;

import net.rim.device.api.i18n.MessageFormat;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.device.internal.ui.component.BackgroundDialog;
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
      StringBuffer message = new StringBuffer();
      if (this._usbPeripheralName.equals("RIM Bypass")) {
         message.append(CommonResource.getString(10184));
      } else if (this._usbPeripheralName.equals("RIM Desktop")) {
         message.append(CommonResource.getString(10102));
      } else {
         message.append(MessageFormat.format(CommonResource.getString(10046), new String[]{this._usbPeripheralName}));
      }

      int devicePasswordAttempt = this.this$0._security.getPasswordFailureCount() + 1;
      int devicePasswordMaxAttempts = this.this$0._security.getMaxPasswordAttempts();
      if (this._lookingForKnownPassword || devicePasswordAttempt != devicePasswordMaxAttempts / 2 + 1 && devicePasswordAttempt != devicePasswordMaxAttempts - 1
         )
       {
         this._lookingForKnownPassword = false;
         message.append(" " + CommonResource.getString(10048));
         if (devicePasswordAttempt == devicePasswordMaxAttempts) {
            USBPasswordRedirectManager$MessageStatusDialog dialog = new USBPasswordRedirectManager$MessageStatusDialog(CommonResource.getString(10049));
            BackgroundDialog.show(dialog);
         }

         boolean revealDevicePassword = devicePasswordAttempt > this.this$0._security.getRevealPasswordAttempts(devicePasswordMaxAttempts);
         this.setRevealPassword(revealDevicePassword);
         if (devicePasswordAttempt > 1) {
            message.append(' ');
            message.append('(');
            message.append(devicePasswordAttempt);
            message.append('/');
            message.append(devicePasswordMaxAttempts);
            message.append(')');
         }

         message.append(':');
         message.append(' ');
         this.setPrompt(message.toString());
      } else {
         this._lookingForKnownPassword = true;
         this.setRevealPassword(true);
         message.append(MessageFormat.format(CommonResource.getString(10050), new Object[]{USBPasswordRedirectManager._knownPassword}));
         this.setPrompt(message.toString());
      }
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
