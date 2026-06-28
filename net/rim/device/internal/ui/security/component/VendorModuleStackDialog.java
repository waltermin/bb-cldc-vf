package net.rim.device.internal.ui.security.component;

import net.rim.device.api.ui.Manager;
import net.rim.device.internal.ui.component.PopupDialog;
import net.rim.device.internal.ui.container.VerticalIndentFieldManager;

public class VendorModuleStackDialog extends PopupDialog {
   private static final int INDENT_PIXEL_WIDTH;

   public VendorModuleStackDialog(Manager manager) {
      this(manager, 0);
   }

   public VendorModuleStackDialog(Manager manager, long style) {
      super(manager, style);
      this.setStatusPriority(-2147483643);
   }

   public static void populateVendorApplicationModulesStack(VerticalIndentFieldManager vifm) {
      populateVendorApplicationModulesStack(vifm, null, null);
   }

   protected static void populateVendorApplicationModulesStack(VerticalIndentFieldManager vifm, int[] stackHandles, int[] specialStackHandles) {
      throw new RuntimeException("cod2jar: array init");
   }
}
