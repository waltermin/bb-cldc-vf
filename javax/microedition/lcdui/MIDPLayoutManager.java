package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.container.FlowFieldManager;

class MIDPLayoutManager extends FlowFieldManager {
   private static final int MAX_HEIGHT;

   public MIDPLayoutManager() {
      super(1153220571769602048L);
   }

   private int layoutChildren(int var1, int var2, int var3) {
      int var4 = this.getFieldCount();
      int var5 = 0;
      int var6 = 0;
      byte var7 = 0;
      boolean var8 = false;

      for (int var9 = var7; var9 < var4; var9++) {
         Field var10 = this.getField(var9);
         Item var11 = (Item)var10.getCookie();
         int var12 = var11.getLayout();
         int var13 = var11.getPreferredWidth();
         boolean var14 = var13 > var1 - var5 && var5 != 0;
         if (var14 && (var12 & 1024) != 0) {
            int var15 = var11.getMinimumWidth();
            if (var15 <= var1 - var5) {
               var14 = false;
            }
         }

         if (!var14 && (var11.getLayout() & 256) == 0 && !var8) {
            this.layoutChild(var10, var1 - var5, var2 - var3);
            var13 = var10.getWidth();
            this.setPositionChild(var10, var5, var3);
            var5 += var13;
            int var22 = var10.getHeight();
            if (var6 < var22) {
               var6 = var22;
            }

            if (var5 >= var1) {
               var5 = 0;
               var3 += var6;
               var6 = 0;
            }
         } else {
            byte var16 = 0;
            var3 += var6;
            this.layoutChild(var10, var1 - var16, var2 - var3);
            var13 = var10.getWidth();
            this.setPositionChild(var10, var16, var3);
            var6 = var10.getHeight();
            var5 = var16 + var13;
            if (var5 >= var1) {
               var5 = 0;
               var3 += var6;
               var6 = 0;
            }
         }

         var8 = (var11.getLayout() & 512) != 0;
      }

      if ((this.getStyle() & 12884901888L) != 0) {
         int var17 = var1 - var5 >> 1;

         for (int var18 = var7; var18 < var4; var18++) {
            Field var19 = this.getField(var18);
            this.setPositionChild(var19, var19.getLeft() + var17, var19.getTop());
         }
      }

      return var3 + var6;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      int var3 = var2;
      if ((this.getStyle() & 281474976710656L) > 0) {
         var3 = 1073741823;
      }

      int var4 = this.layoutChildren(var1, var3, 0);
      if ((this.getStyle() & 2305843009213693952L) > 0) {
         this.setExtent(var1, var2);
      } else {
         this.setExtent(var1, Math.min(var2, var4));
      }

      this.setVirtualExtent(var1, var4);
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      Field var5 = this.getFieldWithFocus();
      return var5 != null && var5.getCookie() instanceof CustomItem ? false : super.navigationMovement(var1, var2, var3, var4);
   }
}
