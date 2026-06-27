package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;

public class FlowFieldManager extends Manager {
   private static final int MAX_HEIGHT;

   public FlowFieldManager() {
      super(1153202979583557632L);
   }

   public FlowFieldManager(long var1) {
      super(var1);
   }

   @Override
   public int getFieldAtLocation(int var1, int var2) {
      int var3 = 0;
      int var5 = 0;
      int var6 = this.getFieldCount() - 1;

      while (var5 <= var6) {
         var3 = var5 + var6 >> 1;
         XYRect var4 = this.getField(var3).getExtent();
         int var7 = var4.y;
         if (var7 < var2) {
            var5 = var3 + 1;
         } else {
            if (var7 <= var2) {
               break;
            }

            var6 = var3 - 1;
         }
      }

      if (var5 > var6) {
         var3 = var6;
      }

      if (var3 < 0) {
         return var3;
      }

      if (this.getField(var3).getLeft() > var1) {
         while (var3 > 0) {
            Field var9 = this.getField(var3);
            if (var9.getTop() + var9.getHeight() < var2) {
               break;
            }

            if (var9.getLeft() <= var1) {
               return var3;
            }

            var3--;
         }
      } else {
         while (var3 < this.getFieldCount() - 1) {
            Field var8 = this.getField(var3);
            if (var8.getTop() > var2) {
               break;
            }

            if (var8.getLeft() + var8.getWidth() >= var1) {
               return var3;
            }

            var3++;
         }
      }

      return var3;
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFieldCount();
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         var2 += this.getField(var3).getPreferredHeight();
      }

      return var2;
   }

   @Override
   public int getPreferredWidth() {
      int var1 = this.getFieldCount();
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         Field var4 = this.getField(var3);
         if (var4.getPreferredWidth() > var2) {
            var2 = var4.getPreferredWidth();
         }
      }

      return var2;
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      byte var4 = 0;
      if (var1 != 131 && var1 != 129) {
         if (var1 != 132 && var1 != 130) {
            return super.keyControl(var1, var2, var3);
         }

         var4 = 1;
      } else {
         var4 = -1;
      }

      this.getScreen().dispatchTrackwheelEvent(519, var4, 0, var3);
      return true;
   }

   private int layoutChildren(int var1, int var2, int var3) {
      int var4 = this.getFieldCount();
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;

      for (int var11 = var8; var11 < var4; var11++) {
         Field var12 = this.getField(var11);
         var9 = Math.max(var9, var12.getMarginLeft());
         var5 += var9;
         int var13 = this.getPreferredWidthOfChild(var12) + var12.getMarginRight();
         if (var13 > var1 - var5 && var5 != 0) {
            if (this.isStyle(12884901888L)) {
               int var14 = var1 - var5 >> 1;

               for (int var15 = var8; var15 <= var11; var15++) {
                  Field var16 = this.getField(var15);
                  this.setPositionChild(var16, var16.getLeft() + var14, var16.getTop());
               }
            }

            var8 = var11;
            var3 += var7;
            var5 = var12.getMarginLeft();
            var10 = var7 - var6;
            var6 = 0;
            var7 = 0;
         }

         this.layoutChild(var12, var1 - var5, var2 - var3);
         var13 = var12.getWidth();
         int var23 = Math.max(0, var12.getMarginTop() - var10);
         this.setPositionChild(var12, var5, var3 + var23);
         var5 += var13;
         var9 = var12.getMarginRight();
         var6 = Math.max(var6, var12.getHeight() + var23);
         var7 = Math.max(var7, var12.getHeight() + var23 + var12.getMarginBottom());
      }

      if (this.isStyle(12884901888L)) {
         int var19 = var1 - var5 >> 1;

         for (int var20 = var8; var20 < var4; var20++) {
            Field var22 = this.getField(var20);
            this.setPositionChild(var22, var22.getLeft() + var19, var22.getTop());
         }
      }

      return var3 + var7;
   }

   @Override
   public int nextFocus(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected int nextFocus(int var1, int var2) {
      if (var2 == 0) {
         return super.nextFocus(var1, var2);
      }

      if (var2 != 2) {
         if (var2 != 1) {
            return -1;
         }

         int var12 = this.getFieldWithFocusIndex();
         if (var12 <= -1) {
            return super.nextFocus(var1, var2);
         }

         Field var13 = this.getField(var12);

         Field var14;
         do {
            var12 += var1;
            if (var12 >= this.getFieldCount() || var12 <= -1) {
               return -1;
            }

            var14 = this.getField(var12);
         } while (
            (var1 <= 0 || var12 >= this.getFieldCount() || var13.getLeft() >= var14.getLeft())
                  && (var1 >= 0 || var12 <= -1 || var13.getLeft() <= var14.getLeft())
               || !var14.isFocusable()
         );

         return var12;
      } else {
         Field var3 = this.getFieldWithFocus();
         if (var3 == null) {
            return super.nextFocus(var1, var2);
         }

         XYRect var4 = var3.getExtent();
         int var5 = var4.y;
         int var6 = var4.x;
         int var7 = this.getFieldWithFocusIndex();

         Field var8;
         do {
            var7 += var1;
            if (var7 >= this.getFieldCount() || var7 <= -1) {
               return -1;
            }

            var8 = this.getField(var7);
         } while (!var8.isFocusable() || (var8.getTop() - var5) * var1 <= 0);

         int var15 = var7;
         int var9 = Math.abs(this.getField(var15).getLeft() - var6);
         if (var9 == 0) {
            return var15;
         }

         while (true) {
            var7 += var1;
            if (var7 >= this.getFieldCount() || var7 <= -1) {
               break;
            }

            Field var10 = this.getField(var7);
            if (var10.isFocusable()) {
               int var11 = Math.abs(this.getField(var7).getLeft() - var6);
               if (var11 >= var9) {
                  break;
               }

               var15 = var7;
               var9 = var11;
            }
         }

         return var15;
      }
   }

   private boolean tappedDirectlyOnFocusField(int var1, int var2) {
      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      int var3 = this.getFieldAtLocation(var1, var2);
      if (var3 < 0) {
         return false;
      }

      Field var4 = this.getField(var3);
      if (var4 != this.getFieldWithFocus()) {
         return false;
      }

      int var5 = var4.getTop();
      if (var2 < var5) {
         return false;
      }

      if (var2 > var5 + var4.getHeight()) {
         return false;
      }

      int var6 = var4.getLeft();
      return var1 < var6 ? false : var1 <= var6 + var4.getWidth();
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this.tappedDirectlyOnFocusField(var1, var2) ? this.getScreen().defaultStylusAction(0) : super.stylusTap(var1, var2, var3, var4);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var3 = var2;
      if (this.isStyle(281474976710656L)) {
         var3 = 1073741823;
      }

      int var4 = this.layoutChildren(var1, var3, 0);
      if (this.isStyle(2305843009213693952L)) {
         this.setExtent(var1, var2);
      } else {
         this.setExtent(var1, Math.min(var2, var4));
      }

      this.setVirtualExtent(var1, var4);
   }

   @Override
   protected void subpaint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      int var3 = var2.y;
      int var4 = var2.y + var2.height;
      int var5 = this.getFieldCount();
      if (var5 > 0) {
         Field var6 = this.getField(0);
         if (var6 != null) {
            var3 += var6.getMarginTop();
         }
      }

      int var7 = this.getFieldAtLocation(0, var3);
      if (var7 != -1) {
         while (var7 < var5) {
            Field var8 = this.getField(var7);
            if (var8.getTop() >= var4) {
               return;
            }

            this.paintChild(var1, var8);
            var7++;
         }
      }
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }
}
