package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.ResourceFetcher;

public class Background {
   private int _positionX;
   private int _positionY;
   private int _repeat;
   public static final int POSITION_X_INHERIT;
   public static final int POSITION_X_LEFT;
   public static final int POSITION_X_RIGHT;
   public static final int POSITION_X_CENTER;
   public static final int POSITION_Y_INHERIT;
   public static final int POSITION_Y_TOP;
   public static final int POSITION_Y_BOTTOM;
   public static final int POSITION_Y_CENTER;
   public static final int REPEAT_INHERIT;
   public static final int REPEAT_NONE;
   public static final int REPEAT_HORIZONTAL;
   public static final int REPEAT_VERTICAL;
   public static final int REPEAT_BOTH;

   public static Background createSolidBackground(int var0) {
      return new BackgroundSolid(var0);
   }

   public static Background createBitmapBackground(Bitmap var0) {
      BackgroundBitmap var1 = new BackgroundBitmap(var0);
      var1.setOrigin(0, 0);
      return var1;
   }

   public static Background createBitmapBackground(ResourceFetcher var0, String var1) {
      NamedBackgroundBitmap var2 = new NamedBackgroundBitmap(var0, var1);
      var2.setOrigin(0, 0);
      return var2;
   }

   public void draw(Graphics var1, XYRect var2) {
      throw null;
   }

   public boolean freeStaleObject(int var1) {
      return false;
   }

   public boolean isTransparent() {
      throw null;
   }

   public final int getPositionX() {
      return this._positionX;
   }

   public final int getPositionY() {
      return this._positionY;
   }

   public final int getRepeat() {
      return this._repeat;
   }

   public void setPosition(int var1, int var2) {
      this._positionX = var1;
      this._positionY = var2;
   }

   public void setRepeat(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
