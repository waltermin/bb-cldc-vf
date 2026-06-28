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

   MenuItemPrefab(int id) {
      super(CommonResource.getBundle(), DATA[3 * id + 0], DATA[3 * id + 1], DATA[3 * id + 2]);
      this._id = id;
   }

   public static MenuItemPrefab get(int id) {
      MenuItemPrefab item = (MenuItemPrefab)_itemCache.get(id);
      if (item == null) {
         item = new MenuItemPrefab(id);
         _itemCache.put(id, item);
      }

      return item;
   }

   public static MenuItemPrefab get(InvokableAction action) {
      int actionId = action.getActionId();
      MenuItemPrefab item = (MenuItemPrefab)_itemCache.get(actionId);
      if (item == null) {
         item = new MenuItemPrefabInvokableAction(action);
         _itemCache.put(actionId, item);
      }

      return item;
   }

   @Override
   public int getPriority() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   static int getDefaultPriority(int id) {
      return 3 * id < DATA.length ? DATA[3 * id + 2] : -1;
   }

   @Override
   public void run() {
      Field target = this.getTarget();
      Screen screen = Menu.getTargetScreen();
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
            target.selectionCopy(Clipboard.getClipboard());
            target.select(false);
            Clipboard.getClipboard().setNotYetPasted(true);
            return;
         case 2:
            target.selectionCut(Clipboard.getClipboard());
            target.select(false);
            Clipboard.getClipboard().setNotYetPasted(true);
            return;
         case 3:
            target.paste(Clipboard.getClipboard());
            target.select(false);
            Clipboard.getClipboard().setNotYetPasted(false);
            return;
         case 4:
            target.select(true);
            return;
         case 5:
            target.select(false);
            return;
         case 14:
            Clipboard.getClipboard().setNotYetPasted(false);
            screen.onClose();
            return;
         case 15:
            if (screen.doSaveInternal()) {
               screen.close();
               return;
            }
            break;
         case 16:
            screen.setScrollBehaviourSelect(true);
            return;
         case 17:
            screen.setScrollBehaviourSelect(false);
            return;
         case 18:
            screen.onMenu(1073741824);
            screen.setScrollBehaviourSelect(false);
      }
   }

   @Override
   public void fieldChanged(Field field, int context) {
   }
}
