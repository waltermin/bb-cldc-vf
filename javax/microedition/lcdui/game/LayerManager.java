package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Graphics;

public class LayerManager {
   private int nlayers;
   private Layer[] component = new Layer[4];
   private int viewX;
   private int viewY;
   private int viewWidth;
   private int viewHeight;

   public LayerManager() {
      this.setViewWindow(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
   }

   public void append(Layer var1) {
      this.removeImpl(var1);
      this.addImpl(var1, this.nlayers);
   }

   public void insert(Layer var1, int var2) {
      if (var2 >= 0 && var2 <= this.nlayers) {
         this.removeImpl(var1);
         this.addImpl(var1, var2);
      } else {
         throw new Object();
      }
   }

   public Layer getLayerAt(int var1) {
      if (var1 >= 0 && var1 < this.nlayers) {
         return this.component[var1];
      } else {
         throw new Object();
      }
   }

   public int getSize() {
      return this.nlayers;
   }

   public void remove(Layer var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void paint(Graphics var1, int var2, int var3) {
      int var4 = var1.getClipX();
      int var5 = var1.getClipY();
      int var6 = var1.getClipWidth();
      int var7 = var1.getClipHeight();
      var1.translate(var2 - this.viewX, var3 - this.viewY);
      var1.clipRect(this.viewX, this.viewY, this.viewWidth, this.viewHeight);
      int var8 = this.nlayers;

      while (--var8 >= 0) {
         Layer var9 = this.component[var8];
         if (var9.visible) {
            var9.paint(var1);
         }
      }

      var1.translate(-var2 + this.viewX, -var3 + this.viewY);
      var1.setClip(var4, var5, var6, var7);
   }

   public void setViewWindow(int var1, int var2, int var3, int var4) {
      if (var3 >= 0 && var4 >= 0) {
         this.viewX = var1;
         this.viewY = var2;
         this.viewWidth = var3;
         this.viewHeight = var4;
      } else {
         throw new Object();
      }
   }

   private void addImpl(Layer var1, int var2) {
      if (this.nlayers == this.component.length) {
         Layer[] var3 = new Layer[this.nlayers + 4];
         System.arraycopy(this.component, 0, var3, 0, this.nlayers);
         System.arraycopy(this.component, var2, var3, var2 + 1, this.nlayers - var2);
         this.component = var3;
      } else {
         System.arraycopy(this.component, var2, this.component, var2 + 1, this.nlayers - var2);
      }

      this.component[var2] = var1;
      this.nlayers++;
   }

   private void removeImpl(Layer var1) {
      if (var1 == null) {
         throw new Object();
      }

      int var2 = this.nlayers;

      while (--var2 >= 0) {
         if (this.component[var2] == var1) {
            this.remove(var2);
         }
      }
   }

   private void remove(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }
}
