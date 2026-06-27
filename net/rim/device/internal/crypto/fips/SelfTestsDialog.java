package net.rim.device.internal.crypto.fips;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.internal.i18n.CommonResource;

final class SelfTestsDialog extends PopupScreen implements ListFieldCallback {
   private String[] _tests;
   private ListField _listField;
   private boolean[] _testResults;
   private SelfTests _selfTests;
   private ButtonField _okButton;
   private VerticalFieldManager _vfmScroll;
   private boolean _startupRun;
   private final ResourceBundle _rb;

   SelfTestsDialog(SelfTests var1, String[] var2, boolean var3, boolean[] var4) {
   }

   final void display() {
      if (this._startupRun) {
         Ui.getUiEngine().pushGlobalScreen(this, -2147483642, 2);
      } else {
         Ui.getUiEngine().pushScreen(this);
      }
   }

   final void setTestPassed(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void setTestFailed(int var1) {
   }

   final void testsPassed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void addOkButton() {
      Object var1 = new Object(12884901888L);
      this._okButton = (ButtonField)(new Object(CommonResource.getString(100)));
      ((HorizontalFieldManager)var1).add(this._okButton);
      this._vfmScroll.add((Field)var1);
   }

   final void testsFailed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public final int getPreferredWidth(ListField var1) {
      return Display.getWidth();
   }

   @Override
   public final int indexOfList(ListField var1, String var2, int var3) {
      return -1;
   }

   @Override
   public final Object get(ListField var1, int var2) {
      return var1 == this._listField && var2 >= 0 && var2 < this._tests.length ? this._tests[var2] : null;
   }

   @Override
   protected final boolean trackwheelClick(int var1, int var2) {
      super.trackwheelClick(var1, var2);
      if (this._okButton != null) {
         this.closeDialog();
      }

      return true;
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = super.keyChar(var1, var2, var3);
      if (this._okButton != null && (this.getLeafFieldWithFocus() == this._okButton && var1 == '\n' || var1 == 27)) {
         this.closeDialog();
      }

      return var4;
   }

   private final void closeDialog() {
      Ui.getUiEngine().popScreen(this);
   }

   @Override
   public final void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1 && this._selfTests != null) {
         this._selfTests.dialogDisplayed();
      }
   }
}
