package net.rim.device.api.ui.component;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.tid.itie.EventHandler;

class ComboField$DropListScreen extends Screen implements TextInputDialog, TextInputObscuringScreen {
   private int _x;
   private int _y;
   private int _width;
   private final ComboField this$0;

   public ComboField$DropListScreen(ComboField var1) {
      super((Manager)(new Object(281474976710656L)), 281474976710656L);
      this.this$0 = var1;
      this.add(var1._list);
   }

   @Override
   protected void paint(Graphics var1) {
      var1.drawRect(0, 0, this.getWidth(), this.getHeight());
      super.paint(var1);
   }

   public void setPositionAndWidth(int var1, int var2, int var3) {
      this._x = var1;
      this._y = var2;
      this._width = var3;
   }

   public void update() {
      this.updateLayout();
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var3 = var2;
      byte var4 = 3;
      var1 = Math.min(this._width, var1 - this._x);
      var2 -= this._y;
      this.layoutDelegate(var1 - 2 * var4, var2 - 2 * var4);
      this.setPositionDelegate(var4, var4);
      var2 = this.this$0._list.getHeight() + 2 * var4;
      XYRect var5 = new XYRect(this.this$0._editable.getExtent());
      this.this$0._editable.transformToScreen(var5);
      int var6 = Display.getHeight() - var5.Y2();
      int var7 = Math.min(var2, this.this$0._list.getRowHeight() * 3 + 2 * var4);
      if (var7 > var6) {
         Manager var8 = this.this$0._editable.getManager();
         int var9 = var8 == null ? 0 : this.this$0._editable.getExtent().y - var8.getVerticalScroll();

         while (var8 != null && !var8.isStyle(281474976710656L)) {
            Manager var10 = var8.getManager();
            var9 += var10 == null ? 0 : var8.getExtent().y - var10.getVerticalScroll();
            var8 = var10;
         }

         int var11 = var6 + var9;
         if (var8 != null && var11 >= var7) {
            var8.setVerticalScroll(var8.getVerticalScroll() + var7 - var6);
            var5 = new XYRect(this.this$0._editable.getExtent());
            this.this$0._editable.transformToScreen(var5);
            this._y = var5.Y2();
            this.layoutDelegate(var1 - 2 * var4, var3 - this._y - 2 * var4);
         } else if (var6 < var5.y) {
            var5 = new XYRect(this.this$0._editable.getExtent());
            this.this$0._editable.transformToScreen(var5);
            this._y = Math.max(0, var5.y - var2);
            var2 = var5.y - this._y;
            this.layoutDelegate(var1 - 2 * var4, var2 - 2 * var4);
         }
      }

      this.setPosition(this._x, this._y);
      this.setExtent(var1, var2);
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      int var5 = Keypad.key(var3);
      if (var1 == 513 && var5 == 10) {
         this.this$0._control.select(this.getSelectedObject(), 3);
         return 0;
      }

      if (var1 == 513 && var5 == 27) {
         this.this$0._control.escape();
         return 0;
      }

      switch (var1) {
         case 513:
         case 514:
         case 515:
         case 520:
            int var6 = 0;
            boolean var7 = false;
            if (this.this$0.usingSureType()) {
               var6 = EventHandler.getInstance().processKeyEvent(var1, var3, var2, var3, var4, true);
               var7 = (var6 & 65536) == 65536;
            }

            if (!var7) {
               Screen var8 = this.getScreenBelow();
               if (var8 != null) {
                  var6 = var8.processKeyEvent(var1, var2, var3, var4);
               }
            }

            if (Ui.isTTSEnabled()) {
               super.accessibleEventOccurred(6, new Object(1), new Object(2), this.this$0._list);
            }

            return var6;
         default:
            return 0;
      }
   }

   @Override
   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      switch (var1) {
         case 516:
            this.this$0._control.select(this.getSelectedObject(), 1);
            return true;
         case 519:
            if ((var4 & 8) == 0) {
               return false;
            }
         default:
            if (Ui.isTTSEnabled()) {
               super.accessibleEventOccurred(6, new Object(1), new Object(2), this.this$0._list);
            }

            return EventHandler.getInstance().processNavigationEvent(var1, var2, var3, var4, var5);
         case 6914:
            this.this$0._control.select(this.getSelectedObject(), 2);
            return true;
      }
   }

   private Object getSelectedObject() {
      int var1 = this.this$0._list.getSelectedIndex();
      if (var1 == -1) {
         return null;
      }

      ListFieldCallback var2 = this.this$0._list.getCallback();
      return var2 == null ? null : var2.get(this.this$0._list, var1);
   }
}
