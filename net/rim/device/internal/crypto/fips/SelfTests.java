package net.rim.device.internal.crypto.fips;

import net.rim.device.api.crypto.SelfTestModule;
import net.rim.vm.Array;

public final class SelfTests {
   private SelfTestsDialog _detailedDialog;
   private SelfTestsStartupDialog _startupDialog;
   private String[] _tests;
   private boolean _testsFailed;
   private boolean _startupRun;
   SelfTestModule[] _modules;
   private static final long LOGGER_GUID;

   public SelfTests(boolean var1) {
   }

   private final SelfTestModule getSelfTestModule(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void getTestStrings(SelfTestModule var1) {
      if (var1 != null) {
         String[] var2 = var1.getTestNames(this._startupRun);
         int var3 = this._tests.length;
         Array.resize(this._tests, this._tests.length + var2.length);
         System.arraycopy(var2, 0, this._tests, var3, var2.length);
      }
   }

   public final void start() {
      if (this._startupRun) {
         this._startupDialog = new SelfTestsStartupDialog(this, this._tests);
         this._startupDialog.display();
      } else {
         this._detailedDialog = new SelfTestsDialog(this, this._tests, this._startupRun, null);
         this._detailedDialog.display();
      }
   }

   public final void dialogDisplayed() {
      new SelfTests$Tester(this).start();
   }

   public final void failure() {
      this._testsFailed = true;
   }
}
