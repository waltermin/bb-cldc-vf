package net.rim.device.api.ui;

import net.rim.device.api.util.Arrays;
import net.rim.vm.Array;

public final class ContextMenu {
   private MenuItem[] _items;
   private Field _target;
   private int _defaultItem = -1;
   private boolean _defaultIsSet;
   private static ContextMenu _menu;
   public static final int UNDEFINED;

   private ContextMenu() {
      this._items = new MenuItem[0];
   }

   public final void addItem(MenuItem var1) {
      if (this._target == null) {
         throw new Object();
      }

      int var2 = this._items.length;
      Array.resize(this._items, var2 + 1);
      this._items[var2] = var1;
   }

   public final int addSeparatorInternal() {
      int var1 = this._items.length > 0 ? this._items[this._items.length - 1].getOrdinal() : 0;
      this.addItem(MenuItem.separator(var1 + 1));
      return this._items.length - 1;
   }

   public final MenuItem getDefaultItem() {
      if (this._defaultItem == -1) {
         return this.getSize() > 0 ? this._items[this._items.length - 1] : null;
      } else {
         return this._items[this._defaultItem];
      }
   }

   public final int getDefault() {
      return this._defaultItem;
   }

   public final boolean setDefaultItem(MenuItem var1) {
      int var2 = this._items.length;

      for (int var3 = 0; var3 < var2; var3++) {
         MenuItem var4 = this._items[var3];
         if (var4 == var1) {
            this._defaultItem = var3;
            this._defaultIsSet = true;
            return true;
         }
      }

      return false;
   }

   public final void setDefault(int var1) {
      this._defaultItem = var1;
      if (this._defaultItem == -1) {
         this._defaultIsSet = false;
      }
   }

   public final boolean isDefaultSet() {
      return this._defaultIsSet;
   }

   public static final ContextMenu getInstance() {
      return _menu;
   }

   public final int getSize() {
      return this._items.length;
   }

   public final Field getTarget() {
      return this._target;
   }

   public final MenuItem[] getItems() {
      MenuItem[] var1 = new MenuItem[this._items.length];
      System.arraycopy(this._items, 0, var1, 0, this._items.length);
      return var1;
   }

   public final boolean isEmpty() {
      return this.getSize() == 0;
   }

   public final void setTarget(Field var1) {
      this._target = var1;
      this.clear();
   }

   public final void clear() {
      this._defaultItem = -1;
      this._defaultIsSet = false;
      Array.resize(this._items, 0);
   }

   public final void sort() {
      MenuItem var1 = null;
      if (this._defaultItem != -1) {
         var1 = this.getDefaultItem();
      }

      Arrays.sort(this._items, 0, this._items.length, MenuItem.ORDINAL_COMPARATOR);
      if (var1 != null) {
         this.setDefaultItem(var1);
      }
   }
}
