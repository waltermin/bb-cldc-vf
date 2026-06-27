package net.rim.device.internal.ui;

import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeManager;

public class ImageThemed implements Image {
   private String _moduleName;
   private String _name;
   private int _themeGeneration;
   private Image _image;

   public ImageThemed(String var1, String var2) {
      this._moduleName = var2;
      this._name = var1;
      this.update();
   }

   @Override
   public int getHeight(int var1, int var2) {
      this.update();
      return this._image.getHeight(var1, var2);
   }

   @Override
   public int getWidth(int var1, int var2) {
      this.update();
      return this._image.getWidth(var1, var2);
   }

   @Override
   public void paint(Graphics var1, int var2, int var3, int var4, int var5) {
      this.update();
      this._image.paint(var1, var2, var3, var4, var5);
   }

   private void update() {
      int var1 = ThemeManager.getGeneration();
      if (this._themeGeneration != var1) {
         this._themeGeneration = var1;
         Theme var2 = ThemeManager.getActiveTheme();
         EncodedImage var3 = var2.getImage(this._name, this._moduleName, false);
         this._image = ImageEncoded.create(var3);
      }
   }
}
