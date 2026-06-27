package net.rim.device.api.ui.theme;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Manager;
import net.rim.device.internal.ui.Border;

public final class Theme$Writer {
   private ResourceFetcher _resourceFetcher;
   private final Theme this$0;

   public Theme$Writer(Theme var1) {
      this.this$0 = var1;
   }

   public final void setResourceBundle(ResourceBundle var1) {
      this.this$0.setResourceBundle(var1);
   }

   public final void addResources() {
      this.this$0.addResources(this._resourceFetcher, false);
   }

   public final void setOption(String var1, String var2) {
      if (!this.this$0._options.containsKey(var1)) {
         this.this$0._options.put(var1, var2);
      }
   }

   public final void loadScrollbar(
      String var1,
      String var2,
      String var3,
      String var4,
      String var5,
      String var6,
      String var7,
      String var8,
      String var9,
      String var10,
      String var11,
      String var12,
      String var13,
      String var14,
      String var15,
      String var16,
      String var17,
      String var18,
      String var19
   ) {
      if (this.this$0.getScrollbar(var1) == null) {
         Bitmap[] var20 = new Bitmap[18];
         EncodedImage var21 = this.this$0.getImage(var2, true);
         var20[0] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var3, true);
         var20[1] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var4, true);
         var20[2] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var5, true);
         var20[3] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var6, true);
         var20[4] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var7, true);
         var20[5] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var8, true);
         var20[6] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var9, true);
         var20[7] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var10, true);
         var20[8] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var11, true);
         var20[9] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var12, true);
         var20[10] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var13, true);
         var20[11] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var14, true);
         var20[12] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var15, true);
         var20[13] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var16, true);
         var20[14] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var17, true);
         var20[15] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var18, true);
         var20[16] = var21 == null ? null : var21.getBitmap();
         var21 = this.this$0.getImage(var19, true);
         var20[17] = var21 == null ? null : var21.getBitmap();
         this.this$0._scrollbars.put(var1, var20);
      }
   }

   public final void createBorder(String var1, int var2, int var3, int var4, int var5) {
      this.createBorderBitmap(var1, var2, var3, var4, var5);
   }

   public final void createBorderBitmap(String var1, int var2, int var3, int var4, int var5) {
      this.createBorderBitmap(var1, var2, var3, var4, var5, var2, var3, var4, var5);
   }

   public final void createBorderBitmap(String var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void createBorderRounded(String var1, int var2, int var3, int var4, int var5) {
      if (this.getBorder(var1) == null) {
         Object var6 = new Object(var2, var3, var4, var5, 2);
         this.putBorder(var1, (Border)var6);
      }
   }

   public final void createBorderSimple(String var1, int var2, int var3, int var4, int var5) {
      if (this.getBorder(var1) == null) {
         Object var6 = new Object(var2, var3, var4, var5);
         this.putBorder(var1, (Border)var6);
      }
   }

   public final void createBorder3d(
      String var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13
   ) {
      if (this.getBorder(var1) == null) {
         Object var14 = new Object(var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
         this.putBorder(var1, (Border)var14);
      }
   }

   public final ThemeAttributeSet$Writer createThemeAttributeSetWriter(String var1) {
      return this.createThemeAttributeSetWriter(var1, this._resourceFetcher);
   }

   public final ThemeAttributeSet$Writer createThemeAttributeSetWriter(String var1, ResourceFetcher var2) {
      Object var3 = new Object(var1);
      return ((ThemeAttributeSet)var3).getWriterInternal(var2);
   }

   public final Manager getLayout(String var1, Object var2) {
      return null;
   }

   public final Border getBorder(String var1) {
      return (Border)this.this$0._borders.get(var1);
   }

   public final int getBorderStyle() {
      return this.this$0._borderStyle;
   }

   public final void setBorderStyle(int var1) {
      if (var1 >= 0 && var1 < 3) {
         this.this$0._borderStyle = var1;
      } else {
         throw new Object();
      }
   }

   private final int getStateForName(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void put(ThemeAttributeSet$Writer var1) {
      this.put(var1, false);
   }

   public final void put(ThemeAttributeSet$Writer var1, boolean var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void putBorder(String var1, Border var2) {
      if (this.this$0._borders.get(var1) == null) {
         this.this$0._borders.put(var1, var2);
      }
   }

   public final void putPaletteEntry(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public final void setApplicationIconSize(int var1, int var2) {
      if (!this.this$0._ribbonIconSizeSet) {
         this.this$0._ribbonIconSizeSet = true;
         this.this$0._ribbonIconWidth = var1;
         this.this$0._ribbonIconHeight = var2;
      }
   }

   public final void setIdleScreenName(String var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   public final void setThumbnailName(String var1) {
      if (!this.this$0._thumbnailNameSet) {
         this.this$0._thumbnailNameSet = true;
         this.this$0._thumbnailName = var1;
      }
   }

   public final void setLabelOnOwnLine(boolean var1) {
      this.this$0._isLabelOnOwnLine = var1;
   }

   public final void setLayoutFactory(Theme$LayoutFactory var1) {
      this.this$0._layoutFactory = var1;
   }

   public final void setResourceFetcher(ResourceFetcher var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void addAlias(String var1, String var2) {
      this.this$0._aliasList.put(var1, var2);
   }
}
