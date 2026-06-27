package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;

public class Layer {
   int x;
   int y;
   int width;
   int height;
   boolean visible = true;

   Layer(int var1, int var2) {
      this.setWidthImpl(var1);
      this.setHeightImpl(var2);
   }

   public void setPosition(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public void move(int var1, int var2) {
      this.x += var1;
      this.y += var2;
   }

   public final int getX() {
      return this.x;
   }

   public final int getY() {
      return this.y;
   }

   public final int getWidth() {
      return this.width;
   }

   public final int getHeight() {
      return this.height;
   }

   public void setVisible(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean isVisible() {
      return this.visible;
   }

   public void paint(Graphics var1) {
      throw null;
   }

   void setWidthImpl(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this.width = var1;
   }

   void setHeightImpl(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this.height = var1;
   }
}
