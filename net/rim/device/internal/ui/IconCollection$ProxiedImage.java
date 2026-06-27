package net.rim.device.internal.ui;

import net.rim.device.api.ui.Graphics;

final class IconCollection$ProxiedImage implements Image {
   private IconCollection _collection;
   private int _index;

   private IconCollection$ProxiedImage(IconCollection var1, int var2) {
      this._collection = var1;
      this._index = var2;
   }

   @Override
   public final int getHeight(int var1, int var2) {
      return this._collection.getHeight(var1, var2);
   }

   @Override
   public final int getWidth(int var1, int var2) {
      return this._collection.getWidth(var1, var2);
   }

   @Override
   public final void paint(Graphics var1, int var2, int var3, int var4, int var5) {
      this._collection.paint(var1, var2, var3, var4, var5, this._index);
   }

   IconCollection$ProxiedImage(IconCollection var1, int var2, IconCollection$1 var3) {
      this(var1, var2);
   }
}
