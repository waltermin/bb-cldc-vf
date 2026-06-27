package net.rim.device.api.ui.menu;

import net.rim.device.api.ui.InvokableAction;

final class MenuItemPrefabInvokableAction extends MenuItemPrefab {
   private InvokableAction _action;

   MenuItemPrefabInvokableAction(InvokableAction var1) {
      super(var1.getActionId());
      this._action = var1;
   }

   @Override
   public final void run() {
      this._action.actionPerformed(this);
   }
}
