package net.rim.device.api.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.theme.Tag;

public class BitmapField extends Field implements DrawStyle {
   private EncodedImage _image;
   private EncodedImage _replacementForCorruptEncodedImage;
   private Bitmap _bitmap;
   private int _x;
   private int _y;
   private int _hSpace;
   private int _vSpace;
   private static Tag TAG;
   public static final int STAMP_MONOCHROME;

   public BitmapField() {
      this(null, 0);
   }

   public BitmapField(Bitmap var1) {
      this(var1, 0);
   }

   public BitmapField(Bitmap var1, long var2) {
      super(var2);
      this.setTag(TAG);
      this._bitmap = var1;
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      this.drawHighlightRegion(var1, 1, var2, 0, 0, this.getWidth(), this.getHeight());
   }

   public int getBitmapHeight() {
      if (this._bitmap != null) {
         return this._bitmap.getHeight();
      } else {
         return this._image != null ? this._image.getScaledHeight() : 0;
      }
   }

   public int getBitmapWidth() {
      if (this._bitmap != null) {
         return this._bitmap.getWidth();
      } else {
         return this._image != null ? this._image.getScaledWidth() : 0;
      }
   }

   @Override
   public int getPreferredHeight() {
      return this.getBitmapHeight() + 2 * this._vSpace;
   }

   @Override
   public int getPreferredWidth() {
      return this.getBitmapWidth() + 2 * this._hSpace;
   }

   protected int getXPos() {
      return this._x;
   }

   protected int getYPos() {
      return this._y;
   }

   @Override
   protected void layout(int var1, int var2) {
      switch ((int)(this.getStyle() & 56)) {
         case 32:
            this._y = var2 - this.getPreferredHeight() >> 1;
            break;
         case 40:
            this._y = var2 - this.getPreferredHeight();
            break;
         case 48:
            this._y = 0;
            break;
         default:
            this._y = 0;
            var2 = Math.min(var2, this.getPreferredHeight());
      }

      this._y = this._y + this._vSpace;
      switch ((int)(this.getStyle() & 7)) {
         case 3:
            this._x = 0;
            var1 = Math.min(var1, this.getPreferredWidth());
            break;
         case 4:
         default:
            this._x = var1 - this.getPreferredWidth() >> 1;
            break;
         case 5:
            this._x = var1 - this.getPreferredWidth();
            break;
         case 6:
            this._x = 0;
      }

      this._x = this._x + this._hSpace;
      this.setExtent(var1, var2);
   }

   protected void paintImage(Graphics var1, int var2, int var3, int var4, int var5, EncodedImage var6, int var7, int var8, int var9) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void paintBitmap(Graphics var1, int var2, int var3, int var4, int var5, Bitmap var6, int var7, int var8) {
      var1.drawBitmap(var2, var3, var4, var5, var6, var7, var8);
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setBitmap(Bitmap var1) {
      int var2 = this.getPreferredWidth();
      int var3 = this.getPreferredHeight();
      this._bitmap = var1;
      this._image = null;
      this.fieldChangeNotify(Integer.MIN_VALUE);
      Manager var4 = this.getManager();
      if (var4 != null && var4.isValidLayout()) {
         if (var2 != this.getPreferredWidth() || var3 != this.getPreferredHeight()) {
            this.updateLayout();
         }

         this.invalidate();
      }
   }

   public void setImage(EncodedImage var1) {
      int var2 = this.getPreferredWidth();
      int var3 = this.getPreferredHeight();
      this._bitmap = null;
      this._image = var1;
      this.fieldChangeNotify(Integer.MIN_VALUE);
      Manager var4 = this.getManager();
      if (var4 != null && var4.isValidLayout()) {
         if (var2 != this.getPreferredWidth() || var3 != this.getPreferredHeight()) {
            this.updateLayout();
         }

         this.invalidate();
      }
   }

   public void setReplacementForCorruptImage(EncodedImage var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setSpace(int var1, int var2) {
      this._hSpace = var1;
      this._vSpace = var2;
      this.updateLayout();
   }

   protected Bitmap getBitmap() {
      return this._bitmap;
   }

   protected EncodedImage getImage() {
      return this._image;
   }
}
