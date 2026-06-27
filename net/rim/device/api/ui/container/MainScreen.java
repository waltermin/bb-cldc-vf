package net.rim.device.api.ui.container;

import java.util.Vector;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;

public class MainScreen extends FullScreen {
   private TitleStatusManager _manager = (TitleStatusManager)this.getDelegate();
   private Vector _menu;
   public static final long NO_TITLE_SEPARATOR;
   public static final long NO_STATUS_SEPARATOR;

   public MainScreen() {
      this(0);
   }

   public MainScreen(long var1) {
      super(new TitleStatusManager(var1), validateStyle(var1));
   }

   public void addMenuItem(MenuItem var1) {
      if (this._menu == null) {
         this._menu = (Vector)(new Object());
      }

      this._menu.addElement(var1);
   }

   @Override
   public String getAccessibleName() {
      return this._manager.getAccessibleName();
   }

   @Override
   public int getFieldCount() {
      return this.getMainManager().getFieldCount();
   }

   @Override
   public Field getFieldWithFocus() {
      Object var1 = this._manager.getFieldWithFocus();
      return var1 != null ? ((Manager)var1).getFieldWithFocus() : null;
   }

   @Override
   public int getFieldWithFocusIndex() {
      return this.getMainManager().getFieldWithFocusIndex();
   }

   @Override
   public Field getField(int var1) {
      return this.getMainManager().getField(var1);
   }

   public Manager getMainManager() {
      return this._manager.getMainManager();
   }

   @Override
   protected boolean keyCharUnhandled(char var1, int var2, int var3) {
      if (super.keyCharUnhandled(var1, var2, var3)) {
         return true;
      } else if (var1 == '\n' && !this.isSelecting()) {
         this.trackwheelRoll(1, 0, var3);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
      super.makeMenu(var1, var2);
      if (this._menu != null) {
         int var3 = this._menu.size();

         for (int var4 = 0; var4 < var3; var4++) {
            var1.add((MenuItem)this._menu.elementAt(var4));
         }
      }
   }

   @Override
   protected boolean onSavePrompt() {
      boolean var1 = true;
      int var2 = Dialog.ask(1);
      if (var2 == 1) {
         return this.onSave();
      }

      if (var2 == -1) {
         var1 = false;
      }

      return var1;
   }

   public void removeAllMenuItems() {
      if (this._menu != null) {
         this._menu.removeAllElements();
      }
   }

   public void removeMenuItem(MenuItem var1) {
      if (this._menu != null) {
         this._menu.removeElement(var1);
      }
   }

   public void setBanner(Field var1) {
      this._manager.setBanner(var1);
   }

   public void setStatus(Field var1) {
      this._manager.setStatus(var1);
   }

   public void setTitle(Field var1) {
      this._manager.setTitle(var1);
   }

   public void setTitle(String var1) {
      this._manager.setTitle(var1);
   }

   public void setTitle(ResourceBundleFamily var1, int var2) {
      this._manager.setTitle(var1, var2);
   }

   private static long validateStyle(long var0) {
      var0 &= -4486007441326081L;
      var0 |= 196608;
      return var0 & -65536;
   }

   @Override
   public void setId(String var1) {
      super.setId(var1);
      this._manager.setId(var1);
   }
}
