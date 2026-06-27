package net.rim.device.api.ui.component;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.internal.i18n.CommonResource;

class TreeField$TreeFieldMenuItem extends MenuItem {
   private boolean _expand;

   public TreeField$TreeFieldMenuItem(int var1, boolean var2) {
      super(CommonResource.getBundle(), var1, 65552, 10);
      this._expand = var2;
   }

   @Override
   public void run() {
      TreeField var1 = (TreeField)this.getTarget();
      int var2 = var1.getCurrentNode();
      var1.setExpanded(var2, this._expand);
      if (this._expand) {
         var1.showDescendants(var2);
      }
   }
}
