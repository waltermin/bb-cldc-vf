package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;

public final class Cursor {
   private Bitmap _bitmap;
   private int _originX;
   private int _originY;
   public static final int PREDEFINED_CURSOR_RIGHT_POINTER;
   public static final int PREDEFINED_CURSOR_HAND;
   public static final int PREDEFINED_CURSOR_I_BAR;
   public static final int PREDEFINED_CURSOR_ZOOM_IN;
   public static final int PREDEFINED_CURSOR_ZOOM_OUT;
   private static Cursor[] PREDEFINED_CURSORS;

   public Cursor(Bitmap var1, int var2, int var3) {
      this._bitmap = var1;
      this._originX = var2;
      this._originY = var3;
   }

   public final Bitmap getBitmap() {
      return this._bitmap;
   }

   public final int getOriginX() {
      return this._originX;
   }

   public final int getOriginY() {
      return this._originY;
   }

   public final int getOrginX() {
      return this.getOriginX();
   }

   public final int getOrginY() {
      return this.getOriginY();
   }

   public static final Cursor getPredefinedCursor(int var0) {
      return PREDEFINED_CURSORS[var0];
   }
}
