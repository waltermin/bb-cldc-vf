package net.rim.device.api.ui.menu;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.internal.ui.UiInternal;
import net.rim.tid.im.layout.SLKeyLayout;

public class DefaultMenuScreen extends PopupScreen implements MenuScreen {
   private Menu _menu;
   private MenuList _list;
   private long _hAlign = 8589934592L;
   private long _vAlign = 17179869184L;
   private int _xOrigin = -1;
   private int _yOrigin = -1;
   private static Tag TAG;

   public DefaultMenuScreen() {
      this((Manager)(new Object(299067162755072L)));
   }

   public DefaultMenuScreen(Manager var1) {
      super(var1);
      this.setTag(TAG);
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this.setMargin(0, 0, 0, this.getMarginLeft());
   }

   @Override
   public MenuItem getCurrentItem() {
      return this._list.getCurrentItem();
   }

   @Override
   public Menu getMenu() {
      return this._menu;
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            this.notifyMenuSelected();
            return true;
         default:
            return false;
      }
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      if ((var3 & 536936448) != 0) {
         if (var1 > 0 && this._list.getCurrentItem() instanceof CascadingMenuItem) {
            this.notifyMenuSelected();
            return true;
         }

         if (var1 < 0 && (this._menu.getStyle() & 262144) != 0) {
            this.popMenu();
            return true;
         }
      }

      return super.navigationMovement(var1, var2, var3, var4);
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = false;
      switch (var1) {
         case '\n':
            this.notifyMenuSelected();
            var4 = true;
            break;
         case '\u001b':
            this.popMenu();
            var4 = true;
            break;
         default:
            char var5 = UiInternal.map(Keypad.getLayout().getOriginalKeyCode(var1, SLKeyLayout.convertStatusToModifiers(var2)), var2);
            var5 = Character.toLowerCase(var5);
            var4 = super.keyChar(var5, var2, var3);
      }

      return var4;
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      boolean var4 = super.keyControl(var1, var2, var3);
      if (var4) {
         return true;
      }

      switch (var1) {
         case '\u0095':
            this.popMenu();
            if (this._menu.getInstance() == 65536) {
               MenuItem.getPrefab(18).run();
            }

            var4 = true;
         default:
            return var4;
      }
   }

   private void notifyMenuSelected() {
      MenuItem var1 = this._list.getCurrentItem();
      if (var1 instanceof CascadingMenuItem) {
         XYRect var2 = Ui.getTmpXYRect();
         Field var3 = this.getFieldWithFocus();
         var3.getFocusRect(var2);
         var3.transformToScreen(var2);
         int var4 = var2.x + 8;
         int var5 = var2.y + (var2.height >> 1);
         Ui.returnTmpXYRect(var2);
         ((CascadingMenuItem)var1).invokeSubMenu(this._menu, var4, var5);
      } else {
         this._menu.notifySelected(var1);
         this.popMenu();
      }
   }

   @Override
   protected void onUndisplay() {
      super.onUndisplay();
      this.onUnfocus();
   }

   @Override
   protected boolean openDevelopmentBackdoor(int var1) {
      switch (var1) {
         case 1229870670:
            Ui.setNewInvalidate(true);
            break;
         case 1229870671:
            Ui.setNewInvalidate(false);
            break;
         case 1263288909:
            Ui.setTrackballClickAction(0);
            break;
         case 1263288911:
            Ui.setTrackballClickAction(1);
            break;
         case 1263294285:
            Ui.setTrackwheelClickAction(0);
            break;
         case 1263294287:
            Ui.setTrackwheelClickAction(1);
      }

      return super.openDevelopmentBackdoor(var1);
   }

   @Override
   protected boolean openProductionBackdoor(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void popMenu() {
      Ui.getUiEngine().popScreen(this);
   }

   @Override
   public void setList(MenuList var1) {
      Object var2 = var1;
      this._list = var1;
      this.deleteAll();
      this.add((Field)var2);
   }

   @Override
   public void setMenu(Menu var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void close() {
      if (this.isDisplayed()) {
         super.close();
      }
   }

   @Override
   public void setAlignment(long var1, long var3) {
      this._hAlign = var1;
      this._vAlign = var3;
   }

   @Override
   public void setCurrentItem(MenuItem var1) {
      this._list.setCurrentItem(var1);
   }

   @Override
   public void setOrigin(int var1, int var2) {
      this._xOrigin = var1;
      this._yOrigin = var2;
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      XYRect var5 = this.getExtent();
      if (var5.contains(var5.x, var5.y, var1, var2)) {
         return this.invokeAction(1);
      }

      this.popMenu();
      return true;
   }

   @Override
   protected boolean stylusTapHold(int var1, int var2, int var3, int var4) {
      return true;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var3 = this.getBorderBottom();
      var2 += this.getBorderTop() + var3;
      int var4 = this._xOrigin != -1 ? this._xOrigin : Ui.getUiEngine().getStylusX();
      if (var4 != -1) {
         super.sublayout(var1, var2);
         int var16 = this.getWidth();
         int var17 = this.getHeight();
         int var18 = this._yOrigin != -1 ? this._yOrigin : Ui.getUiEngine().getStylusY();
         int var10 = var1 - (var4 + var16);
         int var14;
         if (var10 > 0) {
            var14 = var4;
         } else {
            int var11 = var4 - var16;
            if (var11 > 0) {
               var14 = var11;
            } else if (var11 > var10) {
               var14 = 0;
            } else {
               var14 = var1 - var16;
            }
         }

         int var19 = var2 - (var18 + var17);
         int var15;
         if (var19 > 0) {
            var15 = var18;
         } else {
            int var12 = var18 - var17;
            if (var12 > 0) {
               var15 = var12;
            } else if (var12 > var19) {
               var15 = 0;
            } else {
               var15 = var2 - var17;
            }
         }

         this.setPosition(var14, var15);
      } else {
         this.getMenu();
         Screen var5 = Menu.getTargetScreen();
         int var6 = var1 + this.getBorderLeft() + this.getPaddingLeft() + this.getBorderRight() + this.getPaddingRight();
         int var7 = 0;
         if (var5 != null) {
            var6 = var5.getLeft() + var5.getWidth();
            var7 = var5.getTop();
         }

         super.sublayout(var1, var2 - this.getBorderTop() - var3 - var7);
         int var8 = var6 - this.getWidth() + this.getBorderRight();
         if (this._hAlign == 4294967296L) {
            var8 = (var5 != null ? var5.getLeft() : 0) - this.getBorderLeft();
         } else if (this._hAlign == 12884901888L) {
            var8 = var6 - this.getWidth() >> 1;
         }

         int var9 = var7 - this.getBorderTop();
         if (this._vAlign == 34359738368L) {
            var9 = var7 + Math.max(0, (var5 != null ? var5.getHeight() + var3 : var2 + var3 + this.getPaddingBottom()) - this.getHeight());
         } else if (this._vAlign == 51539607552L) {
            var9 = var7 + (var5 != null ? var5.getHeight() + var3 : var2) - this.getHeight() >> 1;
         }

         this.setPosition(var8, var9);
      }
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      return this.invokeAction(1);
   }

   @Override
   public int getAccessibleRole() {
      return 4;
   }

   @Override
   public int getAccessibleChildCount() {
      return this.getMenu().getSize();
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return this.getMenu().getItem(var1);
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return this.getMenu().getCurrentMenuItem();
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this.getMenu().getItem(var1).equals(this.getMenu().getCurrentMenuItem());
   }
}
