package net.rim.device.internal.crypto.fips;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.GaugeField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.internal.i18n.CommonResource;

final class SelfTestsStartupDialog extends PopupScreen {
   private String[] _tests;
   private boolean[] _testResults;
   private SelfTests _selfTests;
   private ButtonField _okButton;
   private ButtonField _detailsButton;
   RichTextField _resultField;
   GaugeField _gaugeField;
   int _numTestsCompleted;
   private static final ResourceBundle _rb;

   SelfTestsStartupDialog(SelfTests var1, String[] var2) {
      super((Manager)(new Object()));
      this._selfTests = var1;
      this._tests = var2;
      this._testResults = new boolean[var2.length];
      this.add((Field)(new Object(_rb.getString(5), 36028797018963968L)));
      this.add((Field)(new Object()));
      this._gaugeField = (GaugeField)(new Object(null, 0, this._tests.length, 0, 4));
      this.add(this._gaugeField);
      this._resultField = (RichTextField)(new Object(null, 36028797019226112L));
      this.add(this._resultField);
   }

   final void display() {
      Ui.getUiEngine().pushGlobalScreen(this, Integer.MIN_VALUE, 0);
   }

   final void setTestPassed(int var1) {
      this._testResults[var1] = true;
      this.testCompleted();
   }

   final void setTestFailed(int var1) {
      this._testResults[var1] = false;
      this.testCompleted();
   }

   final void testCompleted() {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void testsPassed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void addFailButtons() {
      Object var1 = new Object(12884901888L);
      this._okButton = (ButtonField)(new Object(CommonResource.getString(100)));
      this._detailsButton = (ButtonField)(new Object(_rb.getString(7)));
      ((HorizontalFieldManager)var1).add(this._okButton);
      ((HorizontalFieldManager)var1).add(this._detailsButton);
      this.add((Field)var1);
   }

   final void testsFailed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected final boolean trackwheelClick(int var1, int var2) {
      super.trackwheelClick(var1, var2);
      if (this._okButton != null) {
         Field var3 = this.getLeafFieldWithFocus();
         if (var3 == this._okButton) {
            Ui.getUiEngine().popScreen(this);
            return true;
         }

         if (var3 == this._detailsButton) {
            this.showDetails();
            Ui.getUiEngine().popScreen(this);
         }
      }

      return true;
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = super.keyChar(var1, var2, var3);
      if (this._okButton != null) {
         Field var5 = this.getLeafFieldWithFocus();
         if ((var5 != this._okButton || var1 != '\n') && var1 != 27) {
            if (var5 == this._detailsButton) {
               this.showDetails();
               Ui.getUiEngine().popScreen(this);
            }
         } else {
            Ui.getUiEngine().popScreen(this);
         }
      }

      return var4;
   }

   private final void showDetails() {
      SelfTestsDialog var1 = new SelfTestsDialog(null, this._tests, true, this._testResults);
      var1.testsFailed();
      var1.display();
   }

   @Override
   public final void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1) {
         this._selfTests.dialogDisplayed();
      }
   }
}
