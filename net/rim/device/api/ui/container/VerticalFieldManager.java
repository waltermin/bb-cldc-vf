package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.MathUtilities;

public class VerticalFieldManager extends Manager {
   private static final int MAX_EXTENT;
   private static Class _myclass;
   private static final long REQ_STYLES;

   public VerticalFieldManager() {
      this(0);
   }

   public VerticalFieldManager(long var1) {
      super(var1);
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFieldCount();
      int var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < var1; var4++) {
         Field var5 = this.getField(var4);
         var3 = Math.max(var3, var5.getMarginTop());
         var2 += this.getPreferredHeightOfChild(var5) + var3;
         var3 = var5.getMarginBottom();
      }

      return var2 + var3;
   }

   @Override
   public int getPreferredWidth() {
      int var1 = this.getFieldCount();
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         Field var4 = this.getField(var3);
         int var5 = this.getPreferredWidthOfChild(var4) + var4.getMarginLeft() + var4.getMarginRight();
         if (var5 > var2) {
            var2 = var5;
         }
      }

      return var2;
   }

   @Override
   protected boolean incrementalLayout(int var1, int var2, int var3) {
      long var4 = this.getStyle();
      if (_myclass != super.getClass() && (var4 & 576460752303423488L) == 0) {
         return false;
      }

      if ((var4 & 3459045988797251584L) != 3459045988797251584L) {
         return false;
      }

      int var6 = this.getFieldCount();
      int var7 = 0;
      if (var1 > 0) {
         var7 = this.getField(var1 - 1).getExtent().Y2();
      }

      int var8 = this.getVirtualHeight();
      if (var1 + var2 < this.getFieldCount()) {
         var8 = this.getField(var1 + var2).getTop();
      }

      int var9 = var8 - var7;
      int var10 = 0;
      int var11 = 0;
      if (var2 > 0) {
         int var12;
         if (this.isStyle(281474976710656L)) {
            var12 = 1073741823 - this.getVirtualHeight() + var9;
         } else {
            var12 = this.getContentHeight() - var7;
         }

         int var13;
         if (this.isStyle(1125899906842624L)) {
            var13 = 1073741823;
         } else {
            var13 = this.getContentWidth();
         }

         int var14 = var1 + var2;

         for (int var15 = var1; var15 < var14; var15++) {
            Field var16 = this.getField(var15);
            if (var16.isStyle(4611686018427387904L)) {
               XYRect var17 = Ui.getTmpXYRect();
               var16.getFocusRect(var17);
               this.layoutChild(var16, var13, Math.max(this.getContentHeight() - var7, var17.height + var17.y));
               Ui.returnTmpXYRect(var17);
            } else {
               this.layoutChild(var16, var13, var12 - var10);
            }

            var10 += var16.getHeight();
            if (var16.getWidth() > var11) {
               var11 = var16.getWidth();
            }
         }
      }

      int var18;
      if (var3 > 0) {
         var18 = 0;

         for (int var19 = 0; var19 < var6; var19++) {
            Field var21 = this.getField(var19);
            if (var21.getWidth() > var18) {
               var18 = var21.getWidth();
            }
         }
      } else {
         var18 = Math.max(this.getVirtualWidth(), var11);
      }

      this.setVirtualExtent(var18, this.getVirtualHeight() - var9 + var10);
      boolean var20 = this.setFieldPositions2(var18, var1, var1 + var2, var7);
      if (this.isStyle(281474976710656L) && var1 < this.getFieldWithFocusIndex()) {
         int var22 = this.getVerticalScroll();
         var22 += var10;
         var22 -= var9;
         var22 = Math.max(0, var22);
         this.setVerticalScroll(var22);
      }

      this.removeBlankSpace();
      int var26 = this.getFieldCount() > 0 ? this.getField(Math.min(var1, this.getFieldCount() - 1)).getTop() : 0;
      int var28 = var1 + var2 - var3;
      int var27;
      if (!var20 && var28 < this.getFieldCount() - 1 && var28 >= 0) {
         Field var29 = this.getField(var28);
         var27 = var29.getTop() + var29.getHeight();
      } else {
         var27 = this.getVirtualHeight();
      }

      this.invalidate(0, var26, this.getWidth(), var27 - var26);
      return true;
   }

   @Override
   protected int nextFocus(int var1, int var2) {
      return var2 != 2 && var2 != 0 ? -1 : super.nextFocus(var1, var2);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var4 = 0;
      int var5 = 0;
      int var6 = var2;
      boolean var7 = this.isStyle(281474976710656L);
      boolean var8 = this.isStyle(562949953421312L);
      if (var7 && !var8) {
         var6 = 1073741823;
      }

      int var9 = var1;
      boolean var10 = this.isStyle(1125899906842624L);
      boolean var11 = this.isStyle(2251799813685248L);
      if (var10 && !var11) {
         var9 = 1073741823;
      }

      int var12 = this.getFieldCount();
      if (var12 != 0) {
         var5 += this.getField(var12 - 1).getMarginBottom();
      }

      int var13 = 0;

      for (int var14 = 0; var14 < var12; var14++) {
         Field var3 = this.getField(var14);
         var13 = Math.max(var13, var3.getMarginTop());
         var5 += var13;
         if (var3.isStyle(4611686018427387904L)) {
            this.layoutChild(var3, var9, var2 - var5);
         } else {
            this.layoutChild(var3, var9, var6 - var5);
         }

         if (this.isStyle(4611686018427387904L)) {
            var5 += Math.max(var6, var3.getHeight());
         } else {
            var5 += var3.getHeight();
         }

         int var15 = var3.getWidth() + var3.getMarginRight() + var3.getMarginLeft();
         if (var15 > var4) {
            var4 = var15;
         }

         var13 = var3.getMarginBottom();
      }

      this.setVirtualExtent(var4, var5);
      if (var4 < var1 && this.isStyle(1152921504606846976L)) {
         var4 = var1;
      }

      if (var5 < var2 && this.isStyle(2305843009213693952L)) {
         var5 = var2;
      }

      this.setFieldPositions2(var4, 0, var12, 0);
      if (this.isStyle(4611686018427387904L)) {
         this.setExtent(Math.min(var4, var1), Math.max(var5, var2));
      } else {
         this.setExtent(Math.min(var4, var1), Math.min(var5, var2));
      }
   }

   protected int setFieldPositions(int var1, int var2, int var3, int var4) {
      int var5 = var2 + var3;
      int var7 = 0;

      for (int var8 = var2; var8 < var5; var8++) {
         Field var9 = this.getField(var8);
         var7 = Math.max(var7, var9.getMarginTop());
         int var11;
         switch ((int)((var9.getStyle() & 12884901888L) >> 32)) {
            case 1:
               var11 = var9.getMarginLeft();
               break;
            case 2:
            default:
               var11 = var1 - var9.getWidth() - var9.getMarginRight();
               break;
            case 3:
               var11 = var1 - var9.getWidth() >> 1;
               var11 = MathUtilities.clamp(var9.getMarginLeft(), var11, var1 - var9.getWidth() - var9.getMarginRight());
         }

         var4 += var7;
         this.setPositionChild(var9, var11, var4);
         var4 += var9.getHeight();
         var7 = var9.getMarginBottom();
      }

      return var4;
   }

   private boolean setFieldPositions2(int var1, int var2, int var3, int var4) {
      boolean var5 = false;
      int var7 = 0;

      while (var2 < this.getFieldCount()) {
         for (int var8 = var2; var8 < var3; var8++) {
            Field var9 = this.getField(var8);
            var7 = Math.max(var7, var9.getMarginTop());
            int var12;
            switch ((int)((var9.getStyle() & 12884901888L) >> 32)) {
               case 1:
                  var12 = var9.getMarginLeft();
                  break;
               case 2:
               default:
                  var12 = var1 - var9.getWidth() - var9.getMarginRight();
                  break;
               case 3:
                  var12 = var1 - var9.getWidth() >> 1;
                  var12 = MathUtilities.clamp(var9.getMarginLeft(), var12, var1 - var9.getWidth() - var9.getMarginRight());
            }

            var4 += var7;
            this.setPositionChild(var9, var12, var4);
            var4 += var9.getHeight();
            var7 = var9.getMarginBottom();
         }

         var2 = var3;
         var3 = this.getFieldCount();
         if (var2 < var3 && var2 > 0) {
            Field var14 = this.getField(var2 - 1);
            Field var15 = this.getField(var2);
            int var10 = Math.max(var14.getMarginBottom(), var15.getMarginTop());
            if (var14.getTop() + var14.getHeight() + var10 == var15.getTop()) {
               break;
            }

            var5 = true;
         }
      }

      return var5;
   }

   @Override
   protected void subpaint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      int var3 = var2.y;
      int var4 = var2.y + var2.height;
      int var5 = this.getFieldCount();
      if (this.getFieldCount() != 0) {
         for (int var6 = MathUtilities.clamp(0, this.getNextField(0, var3), this.getFieldCount() - 1); var6 < var5; var6++) {
            Field var7 = this.getField(var6);
            if (var7.getTop() >= var4) {
               return;
            }

            this.paintChild(var1, var7);
         }
      }
   }

   @Override
   public int getFieldAtLocation(int var1, int var2) {
      int var3 = this.getVirtualWidth();
      if (var1 < 0) {
         var1 = 0;
      } else if (var1 >= var3) {
         var1 = var3 - 1;
      }

      int var4 = this.getVirtualHeight();
      if (var2 < 0) {
         var2 = 0;
      } else if (var2 >= var4) {
         var2 = var4 - 1;
      }

      int var5 = this.getNextField(var1, var2);
      if (var5 > -1) {
         XYRect var6 = this.getField(var5).getExtent();
         int var7 = var1 - var6.x;
         if (var7 < 0 || var7 >= var6.width) {
            var5 = -1;
         }
      }

      return var5;
   }

   private int getNextField(int var1, int var2) {
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

      return var3;
   }
}
