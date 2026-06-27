package net.rim.device.api.ui.menu;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.InvokableAction;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.util.IntHashtable;
import net.rim.device.internal.i18n.CommonResource;

public class MenuItemPrefab extends MenuItem implements FieldChangeListener {
   private int _id;
   private static final int RSRC_ID_OFFSET;
   private static final int ORDINAL_OFFSET;
   private static final int PRIORITY_OFFSET;
   private static final int DATA_WIDTH;
   private static final int[] DATA;
   private static IntHashtable _itemCache;

   MenuItemPrefab(int var1) {
      super(CommonResource.getBundle(), DATA[3 * var1 + 0], DATA[3 * var1 + 1], DATA[3 * var1 + 2]);
      this._id = var1;
   }

   public static MenuItemPrefab get(int var0) {
      MenuItemPrefab var1 = (MenuItemPrefab)_itemCache.get(var0);
      if (var1 == null) {
         var1 = new MenuItemPrefab(var0);
         _itemCache.put(var0, var1);
      }

      return var1;
   }

   public static MenuItemPrefab get(InvokableAction var0) {
      int var1 = var0.getActionId();
      MenuItemPrefab var2 = (MenuItemPrefab)_itemCache.get(var1);
      if (var2 == null) {
         var2 = new MenuItemPrefabInvokableAction(var0);
         _itemCache.put(var1, var2);
      }

      return var2;
   }

   @Override
   public int getPriority() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   static int getDefaultPriority(int var0) {
      return 3 * var0 < DATA.length ? DATA[3 * var0 + 2] : -1;
   }

   @Override
   public void run() {
      Field var1 = this.getTarget();
      Screen var2 = Menu.getTargetScreen();
      switch (this._id) {
         case 0:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
            break;
         case 1:
         default:
            var1.selectionCopy(Clipboard.getClipboard());
            var1.select(false);
            Clipboard.getClipboard().setNotYetPasted(true);
            return;
         case 2:
            var1.selectionCut(Clipboard.getClipboard());
            var1.select(false);
            Clipboard.getClipboard().setNotYetPasted(true);
            return;
         case 3:
            var1.paste(Clipboard.getClipboard());
            var1.select(false);
            Clipboard.getClipboard().setNotYetPasted(false);
            return;
         case 4:
            var1.select(true);
            return;
         case 5:
            var1.select(false);
            return;
         case 14:
            Clipboard.getClipboard().setNotYetPasted(false);
            var2.onClose();
            return;
         case 15:
            if (var2.doSaveInternal()) {
               var2.close();
               return;
            }
            break;
         case 16:
            var2.setScrollBehaviourSelect(true);
            return;
         case 17:
            var2.setScrollBehaviourSelect(false);
            return;
         case 18:
            var2.onMenu(1073741824);
            var2.setScrollBehaviourSelect(false);
      }
   }

   @Override
   public void fieldChanged(Field var1, int var2) {
   }
}
