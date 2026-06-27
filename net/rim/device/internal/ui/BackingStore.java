package net.rim.device.internal.ui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;

public class BackingStore {
   private int _width;
   private int _height;
   private boolean _transparency;
   private Bitmap _backBuffer;
   private Bitmap _frontBuffer;
   private XYRect _dirty = (XYRect)(new Object(0, 0, 0, 0));
   private XYRect _totalDirty = (XYRect)(new Object(0, 0, 0, 0));

   public BackingStore(int var1, int var2, boolean var3) {
      this._width = var1;
      this._height = var2;
      this._transparency = var3;
   }

   public void cleanBackingStore() {
      if (this._backBuffer != null) {
         Object var1 = new Object(this._backBuffer);
         ((Graphics)var1).setGlobalAlpha(0);
         ((Graphics)var1).setBackgroundColor(0);
         ((Graphics)var1).clear();
      }

      if (this._frontBuffer != null) {
         Object var2 = new Object(this._frontBuffer);
         ((Graphics)var2).setGlobalAlpha(0);
         ((Graphics)var2).setBackgroundColor(0);
         ((Graphics)var2).clear();
      }
   }

   private static Bitmap createBitmap(int var0, int var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized Graphics getGraphics(XYRect var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void getTotalDirty(XYRect var1) {
      var1.set(this._totalDirty);
      this._totalDirty.set(0, 0, 0, 0);
   }

   public synchronized void paint(Graphics var1, int var2, int var3) {
      if (this._frontBuffer != null) {
         int var4 = this._frontBuffer.getWidth();
         int var5 = this._frontBuffer.getHeight();
         if (var4 != 0 && var5 != 0) {
            var1.drawBitmap(var2, var3, var4, var5, this._frontBuffer, 0, 0);
         }
      }
   }

   public synchronized void updateBuffers() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
