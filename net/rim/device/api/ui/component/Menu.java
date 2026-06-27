package net.rim.device.api.ui.component;

import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.menu.MenuList;
import net.rim.device.api.ui.menu.MenuScreen;
import net.rim.device.api.ui.menu.MenuScreenFactory;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.vm.Array;

public class Menu {
   private MenuItem[] _items = new MenuItem[0];
   private long _style;
   private MenuItem[] _displayItems;
   private boolean[] _highlights;
   private MenuItem _emptyMenuItem;
   private MenuScreen _screen;
   private int _selectedPosition = -1;
   private int _default = -1;
   private boolean _contextMenuDefaultSet;
   private Menu$Listeners _listeners = new Menu$Listeners(this);
   private int _instance;
   private Menu _parentMenu;
   private static String SEPARATOR_STRING;
   public static final int CANCELLED;
   public static final int UNDEFINED;
   public static final long SORTED;
   public static final long HORIZONTAL_ROLL_TO_CLOSE;
   public static final int INSTANCE_DEFAULT;
   public static final int INSTANCE_CONTEXT;
   public static final int INSTANCE_CONTEXT_SELECTION;
   public static final int INSTANCE_FROM_MENU_KEY;
   public static final int MENU_FULL;
   public static final int MENU_SHORT;
   private static Screen _targetScreen;

   public Menu() {
      this(0);
   }

   public Menu(long var1) {
      this._style = var1;
      this._screen = MenuScreenFactory.createScreenWithDefault();
      this._screen.setMenu(this);
      this._emptyMenuItem = new Menu$1(this, CommonResource.getBundle(), 10104, 0, Integer.MAX_VALUE);
   }

   public void add(ContextMenu var1) {
      this.add(var1, false);
   }

   public void add(ContextMenu var1, boolean var2) {
      var1.sort();
      MenuItem[] var3 = var1.getItems();
      int var4 = 0;

      for (MenuItem var7 : var3) {
         if (var7.getOrdinal() > var4) {
            var4 = var7.getOrdinal();
         }

         this.add(var7);
      }

      if (var1.isDefaultSet()) {
         this.setDefault(var1.getDefault());
         this._contextMenuDefaultSet = true;
      } else {
         this._contextMenuDefaultSet = false;
      }

      if (var2 && !var1.isEmpty()) {
         this.add(MenuItem.separator(var4));
      }
   }

   public void add(MenuItem var1) {
      if (var1 != null) {
         int var2;
         if ((this._style & 65536) != 0) {
            var2 = Arrays.binarySearch(this._items, var1, MenuItem.ORDINAL_COMPARATOR, 0, this._items.length);
            if (var2 < 0) {
               var2 = -var2 - 1;
            } else {
               int var3 = var1.getOrdinal();

               while (var2 < this._items.length && this._items[var2].getOrdinal() == var3) {
                  var2++;
               }
            }
         } else {
            var2 = this._items.length;
         }

         Arrays.insertAt(this._items, var1, var2);
         if (var2 <= this._default) {
            this._default++;
         }

         if (this._highlights != null) {
            int var4 = this._highlights.length;
            Array.resize(this._highlights, var4 + 1);
            System.arraycopy(this._highlights, var2, this._highlights, var2 + 1, var4 - var2);
            this._highlights[var2] = false;
         }
      }
   }

   public void add(String var1, Object var2, int var3) {
      if (var1.equals(SEPARATOR_STRING)) {
         this.addSeparator();
      } else {
         this.add(new Menu$OldMenuItem(var1, var2, var3));
      }
   }

   public int addItem(String var1, Object var2, int var3) {
      this.add(var1, var2, var3);
      return this._items.length - 1;
   }

   public int addSeparator() {
      int var1 = this._items.length > 0 ? this._items[this._items.length - 1].getOrdinal() : 0;
      this.add(MenuItem.separator(var1));
      return this._items.length - 1;
   }

   public void close() {
      if (this._screen != null) {
         this._screen.close();
      }
   }

   public void deleteAll() {
      Array.resize(this._items, 0);
      this._default = -1;
   }

   public void deleteItem(int var1) {
      if (var1 >= 0 && var1 < this.getSize()) {
         Arrays.removeAt(this._items, var1);
         if (var1 < this._default) {
            this._default--;
         } else if (var1 == this._default) {
            this._default = -1;
         }

         if (this._highlights != null) {
            int var2 = var1;
            this._highlights[var2] = false;
            int var3 = this._highlights.length - 1;
            System.arraycopy(this._highlights, var2 + 1, this._highlights, var2, var3 - var2);
            Array.resize(this._highlights, var3);
         }
      } else {
         throw new Object();
      }
   }

   public Object getCookie(MenuItem var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public MenuItem getCurrentMenuItem() {
      return this._screen.getCurrentItem();
   }

   public MenuItem getDefault() {
      int var1 = this.getDefaultIndex();
      return var1 != -1 ? this._items[var1] : this._emptyMenuItem;
   }

   public int getInstance() {
      return this._instance;
   }

   public boolean isDefaultSet() {
      return this.getDefaultIndex() != -1;
   }

   private int getDefaultIndex() {
      int var1 = this._default;
      if (var1 == -1) {
         int var2 = Integer.MAX_VALUE;
         int var3 = this._items.length;

         for (int var4 = 0; var4 < var3; var4++) {
            MenuItem var5 = this._items[var4];
            if (var5.getPriority() < var2) {
               var2 = var5.getPriority();
               var1 = var4;
            }
         }
      }

      if (this._items.length > 0) {
         var1 = MathUtilities.clamp(0, var1, this._items.length - 1);
         byte var7 = 1;

         while (this._items[var1].isSeparator()) {
            if (var1 >= this._items.length - 1) {
               var7 = -1;
            }

            var1 += var7;
            if (var1 < 0) {
               return var1;
            }
         }
      } else {
         var1 = -1;
      }

      return var1;
   }

   public MenuItem[] getDisplayItems() {
      return this._displayItems;
   }

   public MenuItem getItem(int var1) {
      if (var1 >= 0 && var1 < this.getSize()) {
         return this._items[var1];
      } else {
         throw new Object();
      }
   }

   public Object getItemCookie(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getItemId(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getSelectedId() {
      throw new RuntimeException("cod2jar: type check");
   }

   public Object getSelectedCookie() {
      throw new RuntimeException("cod2jar: type check");
   }

   public MenuItem getSelectedItem() {
      MenuItem var1 = null;
      if (this._selectedPosition >= 0) {
         var1 = this._displayItems[this._selectedPosition];
      }

      return var1;
   }

   public int getSize() {
      return this._items.length;
   }

   public long getStyle() {
      return this._style;
   }

   public static Screen getTargetScreen() {
      return _targetScreen;
   }

   public void invokeDefaultItem() {
      int var1 = this.getDefaultIndex();
      MenuItem var2 = this._items[var1];
      var2.run();
   }

   public boolean isDisplayed() {
      return this._screen.isDisplayed();
   }

   public void notifySelected(MenuItem var1) {
      this._selectedPosition = -1;

      for (int var2 = this._displayItems.length - 1; var2 >= 0; var2--) {
         if (this._displayItems[var2] == var1) {
            this._selectedPosition = var2;
            if (this._parentMenu != null) {
               this._parentMenu.notifySubItemSelected(var1);
               return;
            }
            break;
         }
      }
   }

   public void notifySubItemSelected(MenuItem var1) {
      this._screen.close();
      if (this._parentMenu != null) {
         this._parentMenu.notifySubItemSelected(var1);
      }
   }

   public void setAlignment(long var1, long var3) {
      this._screen.setAlignment(var1, var3);
   }

   public void setCurrentItem(MenuItem var1) {
      this._screen.setCurrentItem(var1);
   }

   public void setDefault(int var1) {
      if (!this._contextMenuDefaultSet) {
         if (var1 >= 0 && var1 < this.getSize()) {
            this._default = var1;
         } else {
            throw new Object();
         }
      }
   }

   public void setDefaultIgnoreContextMenuDefault(MenuItem var1) {
      int var2 = this._items.length;

      for (int var3 = 0; var3 < var2; var3++) {
         MenuItem var4 = this._items[var3];
         if (var4 == var1) {
            this._default = var3;
            return;
         }
      }
   }

   public void setDefault(MenuItem var1) {
      if (!this._contextMenuDefaultSet) {
         int var2 = this._items.length;

         for (int var3 = 0; var3 < var2; var3++) {
            MenuItem var4 = this._items[var3];
            if (var4 == var1) {
               this._default = var3;
               return;
            }
         }
      }
   }

   public void setInstance(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setItemHighlight(int var1, boolean var2) {
      if (this._highlights == null) {
         this._highlights = new boolean[this._items.length];
      }

      this._highlights[var1] = var2;
   }

   public void setOrigin(int var1, int var2) {
      this._screen.setOrigin(var1, var2);
   }

   public void setParentMenu(Menu var1) {
      this._parentMenu = var1;
      if (_targetScreen != null) {
         _targetScreen.addScreenUiEngineAttachedListener(this._listeners);
      }
   }

   public void setTarget(Field var1) {
   }

   public static void setTargetScreen(Screen var0) {
      _targetScreen = var0;
   }

   public void setTargetScreenVirtual(Screen var1) {
      if (var1 != null) {
         var1.addScreenUiEngineAttachedListener(this._listeners);
      }
   }

   public int show() {
      this._displayItems = new MenuItem[2 * this._items.length];
      int var1 = 0;
      boolean var2 = true;
      int var3 = 0;
      int var4 = this._items.length;

      for (int var5 = 0; var5 < var4; var5++) {
         MenuItem var6 = this._items[var5];
         if (!var6.isSeparator() || !var2) {
            int var7 = var6.getOrdinal();
            if ((this._style & 65536) != 0 && !var2 && !var6.isSeparator() && (var7 ^ var3) >> 16 != 0) {
               this._displayItems[var1++] = MenuItem.separator(var3);
               var2 = true;
            }

            var3 = var7;
            this._displayItems[var1++] = var6;
            var2 = var6.isSeparator();
         }
      }

      if (var2 && var1 > 0) {
         var1--;
      }

      Array.resize(this._displayItems, var1);
      if (Ui.getMode() < 2 && var1 == 0) {
         Array.resize(this._displayItems, 1);
         this._displayItems[0] = this._emptyMenuItem;
         boolean var9 = true;
      }

      MenuList var11 = MenuScreenFactory.createListFieldWithDefault();
      var11.setMenuItems(this._displayItems);
      var11.setCurrentItem(this.getDefault());
      this._screen.setList(var11);
      this._selectedPosition = -1;
      boolean var12 = _targetScreen != null ? _targetScreen.isGlobal() : false;
      if (!var12) {
         Ui.getUiEngine().pushModalScreen((Screen)this._screen);
      } else {
         int var13 = Ui.getUiEngine().getGlobalPriority(_targetScreen);
         Ui.getUiEngine().pushGlobalScreen((Screen)this._screen, var13, 5);
      }

      if (this._selectedPosition >= 0) {
         this._displayItems[this._selectedPosition].run();
      }

      ContextMenu.getInstance().setTarget(null);
      _targetScreen = null;
      MenuItem var14 = this.getSelectedItem();
      if (var14 != null) {
         int var8 = var14.getId();
         if (var8 != 6 && var8 != 3 && var8 != 4) {
            Clipboard.getClipboard().setNotYetPasted(false);
         }
      }

      return this._selectedPosition;
   }
}
