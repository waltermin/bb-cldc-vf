package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.GaugeField;
import net.rim.device.api.ui.container.PopupScreen;

public final class ProgressDialog extends PopupScreen {
   private GaugeField _gaugeField = (GaugeField)(new Object());

   public ProgressDialog(String var1) {
      super((Manager)(new Object(1152921504606846976L)));
      this.add((Field)(new Object(var1, 12884901952L)));
      this.add(this._gaugeField);
   }

   public final void show() {
      UiApplication.getUiApplication().pushScreen(this);
   }

   public final void dismiss() {
      UiApplication.getUiApplication().popScreen(this);
   }

   public final void setProgress(int var1) {
      this._gaugeField.setValue(var1);
   }
}
