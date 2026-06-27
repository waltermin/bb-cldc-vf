package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.MathUtilities;

public class HorizontalFieldManager extends Manager {
   private static final int MAX_EXTENT;

   public HorizontalFieldManager() {
      this(0);
   }

   public HorizontalFieldManager(long var1) {
      super(var1);
   }

   @Override
   public int getPreferredWidth() {
      int var1 = this.getFieldCount();
      int var2 = 0;
      int var3 = 0;

      for (int var4 = 0; var4 < var1; var4++) {
         Field var5 = this.getField(var4);
         var3 = Math.max(var3, var5.getMarginLeft());
         var2 += this.getPreferredWidthOfChild(var5) + var3;
         var3 = var5.getMarginRight();
      }

      return var2 + var3;
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFieldCount();
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         Field var4 = this.getField(var3);
         int var5 = this.getPreferredHeightOfChild(var4) + var4.getMarginTop() + var4.getMarginBottom();
         if (var5 > var2) {
            var2 = var5;
         }
      }

      return var2;
   }

   @Override
   protected int nextFocus(int var1, int var2) {
      return var2 != 1 && var2 != 0 ? -1 : super.nextFocus(var1, var2);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var4 = 0;
      int var5 = 0;
      int var6 = var2;
      if (this.isStyle(281474976710656L)) {
         var6 = 1073741823;
      }

      int var7 = var1;
      if (this.isStyle(1125899906842624L)) {
         var7 = 1073741823;
      }

      int var8 = this.getFieldCount();
      if (var8 != 0) {
         var4 += this.getField(var8 - 1).getMarginRight();
      }

      int var9 = 0;

      for (int var10 = 0; var10 < var8; var10++) {
         Field var3 = this.getField(var10);
         var9 = Math.max(var9, var3.getMarginLeft());
         var4 += var9;
         this.layoutChild(var3, var7 - var4, var6);
         var4 += var3.getWidth();
         int var11 = var3.getHeight() + var3.getMarginTop() + var3.getMarginBottom();
         if (var11 > var5) {
            var5 = var11;
         }

         var9 = var3.getMarginRight();
      }

      this.setVirtualExtent(var4, var5);
      if (var4 < var1 && this.isStyle(1152921504606846976L)) {
         var4 = var1;
      }

      if (var5 < var2 && this.isStyle(2305843009213693952L)) {
         var5 = var2;
      }

      int var20 = 0;
      var9 = 0;

      for (int var12 = 0; var12 < var8; var12++) {
         Field var13 = this.getField(var12);
         var9 = Math.max(var9, var13.getMarginLeft());
         int var18;
         switch ((int)((var13.getStyle() & 51539607552L) >> 32)) {
            case 8:
               var18 = var5 - var13.getHeight() - var13.getMarginBottom();
               break;
            case 12:
               var18 = var5 - var13.getHeight() >> 1;
               var18 = MathUtilities.clamp(var13.getMarginTop(), var18, var5 - var13.getHeight() - var13.getMarginBottom());
               break;
            default:
               var18 = var13.getMarginTop();
         }

         var20 += var9;
         this.setPositionChild(var13, var20, var18);
         var20 += var13.getWidth();
         var9 = var13.getMarginRight();
      }

      this.setExtent(Math.min(var4, var1), Math.min(var5, var2));
   }

   @Override
   protected void subpaint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      int var3 = var2.x;
      int var4 = var2.x + var2.width;
      int var5 = this.getFieldCount();
      int var6 = this.getFieldAtLocation(0, var3);
      if (var6 != -1) {
         while (var6 < var5) {
            Field var7 = this.getField(var6);
            if (var7.getLeft() >= var4) {
               return;
            }

            this.paintChild(var1, var7);
            var6++;
         }
      }
   }

   @Override
   public int getFieldAtLocation(int var1, int var2) {
      int var3 = 0;
      int var5 = 0;
      int var6 = this.getFieldCount() - 1;

      while (var5 <= var6) {
         var3 = var5 + var6 >> 1;
         XYRect var4 = this.getField(var3).getExtent();
         int var7 = var4.x;
         if (var7 < var1) {
            var5 = var3 + 1;
         } else {
            if (var7 <= var1) {
               break;
            }

            var6 = var3 - 1;
         }
      }

      if (var5 > var6) {
         var3 = var6;
      }

      return MathUtilities.clamp(0, var3, this.getFieldCount() - 1);
   }
}
