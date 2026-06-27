package net.rim.device.api.ui.accessibility;

import net.rim.device.api.i18n.ResourceBundleFamily;

public class AccessibleState {
   public static final int UNSET;
   public static final int FOCUSED;
   public static final int SELECTED;
   public static final int ACTIVE;
   public static final int PUSHED;
   public static final int BUSY;
   public static final int CHECKED;
   public static final int EDITABLE;
   public static final int EXPANDABLE;
   public static final int EXPANDED;
   public static final int COLLAPSED;
   public static final int FOCUSABLE;
   public static final int MODAL;
   public static final int OPAQUE;
   public static final int MULTI_SELECTABLE;
   public static final int SELECTABLE;
   public static final int VISIBLE;
   public static final int VERTICAL;
   public static final int HORIZONTAL;
   public static final int SINGLE_LINE;
   public static final int MULTI_LINE;
   public static final int TRUNCATED;
   public static final int AVAILABLE;
   private static ResourceBundleFamily _rb;

   public static String toDisplayString(int var0) {
      int var1;
      for (var1 = 0; var0 >= 1; var1++) {
         var0 >>= 1;
      }

      return _rb.getString(var1);
   }
}
