package net.rim.device.internal.ui.security.component;

import net.rim.device.api.ui.Manager;
import net.rim.device.internal.ui.component.PopupDialog;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;

public class VendorModuleStackDialog extends PopupDialog {
   private static final int INDENT_PIXEL_WIDTH;

   public VendorModuleStackDialog(Manager var1) {
      this(var1, 0);
   }

   public VendorModuleStackDialog(Manager var1, long var2) {
      super(var1, var2);
      this.setStatusPriority(-2147483643);
   }

   public static void populateVendorApplicationModulesStack(VerticalIndentFieldManager var0) {
      populateVendorApplicationModulesStack(var0, null, null);
   }

   protected static void populateVendorApplicationModulesStack(VerticalIndentFieldManager var0, int[] var1, int[] var2) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
