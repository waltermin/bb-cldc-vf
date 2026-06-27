package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class Border3d extends Border {
   private int _colorTop0;
   private int _colorTop1;
   private int _colorRight0;
   private int _colorRight1;
   private int _colorBottom0;
   private int _colorBottom1;
   private int _colorLeft0;
   private int _colorLeft1;
   private int[] _temp1 = new int[5];
   private int[] _temp2 = new int[5];

   public Border3d(int var1, int var2, int var3, int var4) {
      this(var1, var2, var3, var4, 13882323, 11119017, 11119017, 13882323, 11119017, 13882323, 13882323, 11119017);
   }

   public Border3d(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super(var1, var2, var3, var4);
      this._colorTop0 = var5;
      this._colorTop1 = var6;
      this._colorRight0 = var7;
      this._colorRight1 = var8;
      this._colorBottom0 = var9;
      this._colorBottom1 = var10;
      this._colorLeft0 = var11;
      this._colorLeft1 = var12;
   }

   @Override
   public void paint(Graphics var1, XYRect var2) {
      int var19 = this.getLeft();
      int var20 = this.getTop();
      int var21 = this.getRight();
      int var22 = this.getBottom();
      int var5 = var2.x;
      int var3 = var2.x;
      int var12 = var2.y;
      int var11 = var2.y;
      int var6;
      int var4 = var6 = var2.X2();
      int var14;
      int var13 = var14 = var2.Y2();
      int var9;
      int var7 = var9 = var2.x + var19;
      int var16;
      int var15 = var16 = var2.y + var20;
      int var10;
      int var8 = var10 = var4 - var21;
      int var18;
      int var17 = var18 = var13 - var22;
      if (var19 == 0) {
         var7 = var3;
         var9 = var5;
      }

      if (var21 == 0) {
         var8 = var4;
         var10 = var6;
      }

      if (var22 == 0) {
         var17 = var13;
         var18 = var14;
      }

      if (var20 == 0) {
         var15 = var11;
         var16 = var12;
      }

      int var23 = var1.getColor();
      if (var19 > 0) {
         this._temp1[0] = var3;
         this._temp1[1] = var7;
         this._temp1[2] = var9;
         this._temp1[3] = var5;
         this._temp1[4] = var3;
         this._temp2[0] = var11;
         this._temp2[1] = var15;
         this._temp2[2] = var17;
         this._temp2[3] = var13;
         this._temp2[4] = var11;
         var1.setColor(this._colorLeft0);
         var1.drawFilledPath(this._temp1, this._temp2, null, null);
         var1.setColor(this._colorLeft1);
         var1.drawLine(var7 - 1, var15, var9 - 1, var17);
      }

      if (var21 > 0) {
         this._temp1[0] = var4;
         this._temp1[1] = var6;
         this._temp1[2] = var10;
         this._temp1[3] = var8;
         this._temp1[4] = var4;
         this._temp2[0] = var12;
         this._temp2[1] = var14;
         this._temp2[2] = var18;
         this._temp2[3] = var16;
         this._temp2[4] = var12;
         var1.setColor(this._colorRight0);
         var1.drawFilledPath(this._temp1, this._temp2, null, null);
         var1.setColor(this._colorRight1);
         var1.drawLine(var8, var16, var10, var18);
      }

      if (var20 > 0) {
         this._temp1[0] = var3;
         this._temp1[1] = var4;
         this._temp1[2] = var8;
         this._temp1[3] = var7;
         this._temp1[4] = var3;
         this._temp2[0] = var11;
         this._temp2[1] = var12;
         this._temp2[2] = var16;
         this._temp2[3] = var15;
         this._temp2[4] = var11;
         var1.setColor(this._colorTop0);
         var1.drawFilledPath(this._temp1, this._temp2, null, null);
         var1.setColor(this._colorTop1);
         var1.drawLine(var7, var15 - 1, var8, var16 - 1);
      }

      if (var22 > 0) {
         this._temp1[0] = var5;
         this._temp1[1] = var6;
         this._temp1[2] = var10;
         this._temp1[3] = var9;
         this._temp1[4] = var5;
         this._temp2[0] = var13;
         this._temp2[1] = var14;
         this._temp2[2] = var18;
         this._temp2[3] = var17;
         this._temp2[4] = var13;
         var1.setColor(this._colorBottom0);
         var1.drawFilledPath(this._temp1, this._temp2, null, null);
         var1.setColor(this._colorBottom1);
         var1.drawLine(var9, var17, var10, var18);
      }

      var1.setColor(var23);
   }
}
