package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.ScrollChangeListener;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.util.MathUtilities;

public final class Scrollbar extends Field implements ScrollChangeListener {
   private Manager _client;
   private boolean _enabled;
   private boolean _horizontalScroll;
   private int[] _tempDraw;
   private Bitmap _trackBitmap;
   private Bitmap _topOrLeftArrowBitmap;
   private Bitmap _bottomOrRightArrowBitmap;
   private Bitmap _sliderBitmap;
   private int _lastCalculatedSize;
   private int _lastCalculatedOffset;
   private static final Tag TAG;
   private static final int MINIMUM_SCROLLBAR_HEIGHT;
   private static final int PREFERRED_SCROLLBAR_WIDTH;
   private static final int ARROW_HEIGHT;
   private static final int[] ARROW_X_POINTS;
   private static final int[] TOP_ARROW_Y_POINTS;
   private static final String SCROLLBAR_TRACK_VERT_BITMAP;
   private static final String SCROLLBAR_TRACK_HORZ_BITMAP;
   private static final String SCROLLBAR_TOP_ARROW_BITMAP;
   private static final String SCROLLBAR_BOTTOM_ARROW_BITMAP;
   private static final String SCROLLBAR_LEFT_ARROW_BITMAP;
   private static final String SCROLLBAR_RIGHT_ARROW_BITMAP;
   private static final String SCROLLBAR_SLIDER_VERT_BITMAP;
   private static final String SCROLLBAR_SLIDER_HORZ_BITMAP;

   public Scrollbar() {
      this(true);
   }

   public Scrollbar(boolean var1) {
      this(var1, false);
   }

   public Scrollbar(boolean var1, boolean var2) {
      super(0);
      this.setTag(TAG);
      this._enabled = var1;
      this._horizontalScroll = var2;
      this.setupBitmaps();
   }

   @Override
   public final int getPreferredWidth() {
      if (!this._horizontalScroll) {
         if (!this._enabled) {
            return 0;
         } else {
            return this._trackBitmap != null ? this._trackBitmap.getWidth() : 5;
         }
      } else {
         return super.getPreferredWidth();
      }
   }

   @Override
   public final int getPreferredHeight() {
      if (this._horizontalScroll) {
         if (!this._enabled) {
            return 0;
         } else {
            return this._trackBitmap != null ? this._trackBitmap.getHeight() : 5;
         }
      } else {
         return super.getPreferredHeight();
      }
   }

   @Override
   protected final void layout(int var1, int var2) {
      if (this._horizontalScroll) {
         this.setExtent(var1, this.getPreferredHeight());
      } else {
         this.setExtent(this.getPreferredWidth(), var2);
      }
   }

   @Override
   protected final void drawFocus(Graphics var1, boolean var2) {
   }

   @Override
   protected final void applyTheme() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static final Bitmap getBitmap(Theme var0, String var1) {
      EncodedImage var2 = var0.getImage(var1, true);
      return var2 != null ? var2.getBitmap() : null;
   }

   private final void setupBitmaps() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected final void paint(Graphics var1) {
      XYRect var2 = this.getExtent();
      if (this._client != null && this._enabled) {
         if (this._trackBitmap == null) {
            if (this._tempDraw == null) {
               this._tempDraw = new int[12];
            }

            if (!this._horizontalScroll) {
               int var16 = this._client.getVirtualHeight();
               if (var16 > var2.height) {
                  int var19 = var2.height - 7 - 7;
                  if (var19 > 0) {
                     byte var22 = 7;
                     int var26 = var2.height - 7;
                     int var31 = var2.height * var2.height / var16;
                     this._lastCalculatedSize = var31;
                     var31 = MathUtilities.clamp(7, var31, var19);
                     int var36 = this._client.getVerticalScroll() * var2.height / var16 % var2.height;
                     this._lastCalculatedOffset = var36;
                     var36 = MathUtilities.clamp(var22, var36, var26 - var31);
                     var1.clear(0, 0, var2.width, var2.height);
                     var26--;
                     var1.setStipple(1431655765);
                     var1.drawLine(1, var22, 1, var26);
                     var1.drawLine(3, var22, 3, var26);
                     var1.setStipple(-1431655766);
                     var1.drawLine(2, var22, 2, var26);
                     var1.setStipple(-1);
                     var1.clear(0, var36 - 1, var2.width, var31);
                     var1.fillRect(1, var36, var2.width - 2, var31);
                     var1.drawFilledPath(ARROW_X_POINTS, TOP_ARROW_Y_POINTS, null, null);
                     int var42 = var2.height;
                     int var47 = var42 - 6;
                     int var50 = var42 - 4;
                     int var53 = var42 - 2;
                     this._tempDraw[0] = var47;
                     this._tempDraw[1] = var47;
                     this._tempDraw[2] = var50;
                     this._tempDraw[3] = var50;
                     this._tempDraw[4] = var53;
                     this._tempDraw[5] = var53;
                     this._tempDraw[6] = var42;
                     this._tempDraw[7] = var42;
                     this._tempDraw[8] = var53;
                     this._tempDraw[9] = var53;
                     this._tempDraw[10] = var50;
                     this._tempDraw[11] = var50;
                     var1.drawFilledPath(ARROW_X_POINTS, this._tempDraw, null, null);
                  }
               }
            } else {
               int var15 = this._client.getVirtualWidth();
               if (var15 > var2.width) {
                  int var18 = var2.width - 7 - 7;
                  if (var18 > 0) {
                     byte var21 = 7;
                     int var24 = var2.width - 7;
                     int var29 = var2.width * var2.width / var15;
                     this._lastCalculatedSize = var29;
                     var29 = MathUtilities.clamp(7, var29, var18);
                     int var34 = this._client.getHorizontalScroll() * var2.width / var15 % var2.width;
                     this._lastCalculatedOffset = var34;
                     var34 = MathUtilities.clamp(var21, var34, var24 - var29);
                     var1.clear(0, 0, var2.width, var2.height);
                     var24--;
                     var1.setStipple(1431655765);
                     var1.drawLine(var21, 1, var24, 1);
                     var1.drawLine(var21, 3, var24, 3);
                     var1.setStipple(-1431655766);
                     var1.drawLine(var21, 2, var24, 2);
                     var1.setStipple(-1);
                     var1.clear(var34 - 1, 0, var29, var2.height);
                     var1.fillRect(var34, 1, var29, var2.height - 2);
                     var1.drawFilledPath(TOP_ARROW_Y_POINTS, ARROW_X_POINTS, null, null);
                     int var41 = var2.width;
                     int var46 = var41 - 6;
                     int var49 = var41 - 4;
                     int var52 = var41 - 2;
                     this._tempDraw[0] = var46;
                     this._tempDraw[1] = var46;
                     this._tempDraw[2] = var49;
                     this._tempDraw[3] = var49;
                     this._tempDraw[4] = var52;
                     this._tempDraw[5] = var52;
                     this._tempDraw[6] = var41;
                     this._tempDraw[7] = var41;
                     this._tempDraw[8] = var52;
                     this._tempDraw[9] = var52;
                     this._tempDraw[10] = var49;
                     this._tempDraw[11] = var49;
                     var1.drawFilledPath(this._tempDraw, ARROW_X_POINTS, null, null);
                  }
               }
            }
         } else if (this._horizontalScroll) {
            int var14 = this._client.getVirtualWidth();
            if (var14 <= var2.width) {
               var14 = this._client.getWidth();
            }

            if (var14 == 0) {
               var14 = var2.width;
            }

            int var17 = this._topOrLeftArrowBitmap.getWidth();
            int var20 = this._bottomOrRightArrowBitmap.getWidth();
            int var23 = var2.width - var17 - var20;
            if (var23 > 0) {
               int var28 = var17;
               int var33 = var2.width - var20;
               int var39 = var2.width * var2.width / var14;
               this._lastCalculatedSize = var39;
               var39 = MathUtilities.clamp(7, var39, var23);
               int var44 = this._client.getHorizontalScroll() * var2.width / var14 % var2.width;
               this._lastCalculatedOffset = var44;
               var44 = MathUtilities.clamp(var28, var44, var33 - var39);
               var1.drawBitmap(0, 0, var2.width, var2.height, this._trackBitmap, 0, 0);
               var1.drawBitmap(0, 0, this._topOrLeftArrowBitmap.getWidth(), this._topOrLeftArrowBitmap.getHeight(), this._topOrLeftArrowBitmap, 0, 0);
               var1.drawBitmap(
                  var33, 0, this._bottomOrRightArrowBitmap.getWidth(), this._bottomOrRightArrowBitmap.getHeight(), this._bottomOrRightArrowBitmap, 0, 0
               );
               int var48 = this._sliderBitmap.getWidth();
               int var51 = this._sliderBitmap.getHeight();
               int var54 = var39 >> 1;
               var1.drawBitmap(var44, 0, var54, var51, this._sliderBitmap, 0, 0);
               var1.drawBitmap(var44 + var54, 0, var54, var51, this._sliderBitmap, Math.max(0, var48 - var54), 0);
            }
         } else {
            int var3 = this._client.getVirtualHeight();
            if (var3 <= var2.height) {
               if (this._client.isStyle(288230376151711744L) && this._client.getVerticalScroll() > 0) {
                  var3 = var2.height + this._client.getVerticalScroll();
               } else {
                  var3 = this._client.getHeight();
               }
            }

            if (var3 == 0) {
               var3 = var2.height;
            }

            int var4 = this._topOrLeftArrowBitmap.getHeight();
            int var5 = this._bottomOrRightArrowBitmap.getHeight();
            int var6 = var2.height - var4 - var5;
            if (var6 > 0) {
               int var7 = var4;
               int var8 = var2.height - var5;
               int var9 = var2.height * var2.height / var3;
               this._lastCalculatedSize = var9;
               var9 = MathUtilities.clamp(7, var9, var6);
               int var10 = this._client.getVerticalScroll() * var2.height / var3 % var2.height;
               this._lastCalculatedOffset = var10;
               var10 = MathUtilities.clamp(var7, var10, var8 - var9);
               var1.drawBitmap(0, 0, var2.width, var2.height, this._trackBitmap, 0, 0);
               var1.drawBitmap(0, 0, this._topOrLeftArrowBitmap.getWidth(), this._topOrLeftArrowBitmap.getHeight(), this._topOrLeftArrowBitmap, 0, 0);
               var1.drawBitmap(
                  0, var8, this._bottomOrRightArrowBitmap.getWidth(), this._bottomOrRightArrowBitmap.getHeight(), this._bottomOrRightArrowBitmap, 0, 0
               );
               int var11 = this._sliderBitmap.getWidth();
               int var12 = this._sliderBitmap.getHeight();
               int var13 = var9 >> 1;
               var1.drawBitmap(0, var10, var11, var13, this._sliderBitmap, 0, 0);
               var1.drawBitmap(0, var10 + var13, var11, var13, this._sliderBitmap, 0, Math.max(0, var12 - var13));
            }
         }
      }
   }

   public final void enable() {
      this._enabled = true;
   }

   public final void disable() {
      this._enabled = false;
   }

   public final boolean isEnabled() {
      return this._enabled;
   }

   public final void setClient(Manager var1) {
      this.setClient(var1, true);
   }

   public final void setClient(Manager var1, boolean var2) {
      this._client = var1;
      if (var2 && this._client != null) {
         this._client.setScrollListener(this);
      }
   }

   @Override
   public final void scrollChanged(Manager var1, int var2, int var3) {
      if (this._client != null && this._enabled) {
         if (this._horizontalScroll) {
            int var4 = this.getWidth();
            int var5 = this._client.getVirtualWidth();
            if (var5 == 0) {
               return;
            }

            int var6 = var4 * var4 / var5;
            int var7 = this._client.getHorizontalScroll() * var4 / var5 % var4;
            if (this._lastCalculatedSize != var6 || this._lastCalculatedOffset != var7) {
               this.invalidate();
               return;
            }
         } else {
            int var8 = this.getHeight();
            int var9 = this._client.getVirtualHeight();
            if (var9 == 0) {
               return;
            }

            int var10 = var8 * var8 / var9;
            int var11 = this._client.getVerticalScroll() * var8 / var9 % var8;
            if (this._lastCalculatedSize != var10 || this._lastCalculatedOffset != var11) {
               this.invalidate();
            }
         }
      }
   }
}
