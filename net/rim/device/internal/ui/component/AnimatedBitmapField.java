package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.internal.ui.Animation;
import net.rim.device.internal.ui.AnimationListener;
import net.rim.device.internal.ui.AnimationThread;
import net.rim.vm.WeakReference;

public class AnimatedBitmapField extends BitmapField implements Animation {
   private EncodedImage _image;
   private int _frameCount;
   private int _currentFrame;
   private int _backgroundColour;
   private int _maxIterations;
   private int _maxImageIterations;
   private int _iterationsLeft;
   private boolean _running;
   private int _backgroundColourBelow = -1;
   private boolean _addedToQueue;
   private boolean _visible;
   private long _timeToExecuteAt;
   WeakReference[] _listeners = new WeakReference[0];
   private static final int GIF_DELAY_FACTOR;
   private static final int MAX_FRAMES_PER_SECOND;
   private static final int MIN_DELAY_PER_FRAME;
   private static final int ANIMATION_STARTED;
   private static final int ANIMATION_STOPPED;
   private static final int ANIMATION_PAUSED;
   private static final int ANIMATION_RESUMED;
   private static final int MAX_MESSAGE_INDEX;

   public AnimatedBitmapField() {
      this((Bitmap)((Object)null), 0);
   }

   public AnimatedBitmapField(Bitmap var1) {
      this(var1, 0);
   }

   public AnimatedBitmapField(Bitmap var1, long var2) {
      super(var1, var2);
   }

   public AnimatedBitmapField(EncodedImage var1, int var2, long var3) {
      super((Bitmap)((Object)null), var3);
      this.setImage(var1);
      this._maxIterations = var2;
      this._iterationsLeft = this._maxIterations;
      this.startAnimation();
   }

   @Override
   public void setBitmap(Bitmap var1) {
      super.setBitmap(var1);
      AnimationThread.removeAnimation(this);
      this._frameCount = 1;
      this._image = null;
   }

   @Override
   public void setImage(EncodedImage var1) {
      super.setImage(var1);
      this._image = null;
      this._backgroundColour = -1;
      if (var1 != null) {
         this._frameCount = var1.getFrameCount();
         if (this._frameCount >= 2) {
            this._image = var1;
            int var2 = var1.getImageType();
            switch (var2) {
               case 1:
                  Object var3 = var1;
                  this._backgroundColour = ((GIFEncodedImage)var3).getBackgroundColor();
                  this._maxImageIterations = ((GIFEncodedImage)var3).getIterations();
                  if (this._maxImageIterations == 0) {
                     this._maxImageIterations = Integer.MAX_VALUE;
                  }

                  this._maxIterations = Math.min(this._maxImageIterations, this._maxIterations);
               default:
                  this._currentFrame = -1;
                  if (this._running) {
                     this.notify(1);
                  }

                  this._running = false;
            }
         }
      }
   }

   public void setMaximumLoopIterations(int var1) {
      if (this._image != null) {
         this._maxIterations = Math.min(this._maxImageIterations, var1);
      }
   }

   public void setUnderlyingBackgroundColor(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void startAnimation() {
      if (this._maxIterations > 0) {
         this._iterationsLeft = this._maxIterations;
         if (!this._running && this._image != null) {
            this._running = true;
            AnimationThread.addAnimation(this);
            this._addedToQueue = true;
            this.notify(0);
         }
      }
   }

   public void stopAnimation() {
      if (this._running) {
         this.notify(1);
      }

      this._running = false;
      AnimationThread.removeAnimation(this);
      this._addedToQueue = false;
   }

   public void pauseAnimation() {
      if (this._running && this._addedToQueue) {
         this.notify(2);
         this.toggleAnimation(false);
      }
   }

   public void resumeAnimation() {
      if (this._running && !this._addedToQueue) {
         this.notify(3);
         this.toggleAnimation(true);
      }
   }

   public boolean isAnimated() {
      return this._frameCount > 1;
   }

   public boolean isAnimationRunning() {
      return this._running;
   }

   public void reset() {
      this._currentFrame = -1;
      if (!this._running && this._image != null) {
         this.invalidate();
      }
   }

   private int pushContext(Screen var1, Graphics var2, Manager var3) {
      if (var3 == var1) {
         return 0;
      }

      int var4 = this.pushContext(var1, var2, var3.getManager());
      var2.pushRegion(var3.getExtent(), -var3.getHorizontalScroll(), -var3.getVerticalScroll());
      return var4 + 1;
   }

   private boolean paintFrame() {
      boolean var1 = false;
      if (this._currentFrame < this._frameCount) {
         int var2 = this._currentFrame;
         if (var2 == -1) {
            var2 = 0;
         }

         if (this._image.getImageType() == 1) {
            Object var3 = this._image;
            if (this._backgroundColourBelow == -1
               && ((GIFEncodedImage)var3).isBackgroundTransparent()
               && (((GIFEncodedImage)var3).getFrameTransition((var2 + this._frameCount - 1) % this._frameCount) == 2 || var2 == 0)) {
               this.invalidate();
               return true;
            }
         }

         Screen var7 = this.getScreen();
         if (var7 != null) {
            Graphics var4 = var7.getGraphics();
            int var5 = this.pushContext(var7, var4, this.getManager());
            if (var4.pushRegion(this.getContentRect())) {
               this.paintFrame(var2, var4);
               var7.updateDisplay();
               var1 = true;
            }

            for (int var6 = 0; var6 <= var5; var6++) {
               var4.popContext();
            }
         }
      }

      return var1;
   }

   private void paintFrame(int var1, Graphics var2) {
      int var3 = 2;
      int var4 = 0;
      int var5 = 0;
      int var6 = this.getXPos();
      int var7 = this.getYPos();
      boolean var8 = false;
      if (this._image.getImageType() == 1) {
         Object var9 = this._image;
         var3 = ((GIFEncodedImage)var9).getFrameTransition((var1 + this._frameCount - 1) % this._frameCount);
         var4 = ((GIFEncodedImage)var9).getScaledFrameLeft(var1);
         var5 = ((GIFEncodedImage)var9).getScaledFrameTop(var1);
         var8 = ((GIFEncodedImage)var9).isBackgroundTransparent();
      }

      if (var1 == 0 || var3 == 2) {
         int var11 = var8 ? this._backgroundColourBelow : this._backgroundColour;
         if (var11 != -1) {
            var2.setColor(var11);
            var2.fillRect(var6, var7, this._image.getScaledWidth(), this._image.getScaledHeight());
         }
      }

      int var12 = this._image.getScaledFrameHeight(var1);
      if (var12 > 0) {
         int var10 = this._image.getScaledFrameWidth(var1);
         if (var10 > 0) {
            this.paintImage(var2, var4 + var6, var5 + var7, var10, var12, this._image, var1, 0, 0);
         }
      }
   }

   @Override
   protected void paint(Graphics var1) {
      if (this._image == null) {
         super.paint(var1);
      } else {
         if (this._visible && !this._addedToQueue && this._running) {
            this.toggleAnimation(true);
         }

         if (this._currentFrame >= this._frameCount) {
            boolean var5 = true;
            if (this._frameCount >= 2 && this._image.getImageType() == 1) {
               Object var6 = this._image;
               if (((GIFEncodedImage)var6).getFrameTransition(this._frameCount - 2) == 2) {
                  var5 = false;
               }
            }

            if (var5) {
               this.paintFrame(0, var1);
               if (this._frameCount > 20) {
                  int var7 = this._frameCount / 10;
                  var7 -= 2;

                  for (int var10 = 1; var10 < var7; var10++) {
                     this.paintFrame(var10, var1);
                  }

                  for (int var11 = var7; var11 > 1; var11--) {
                     this.paintFrame(this._frameCount - var11, var1);
                  }
               } else {
                  for (int var9 = 1; var9 < this._frameCount - 1; var9++) {
                     this.paintFrame(var9, var1);
                  }
               }
            }

            this.paintFrame(this._frameCount - 1, var1);
            return;
         }

         int var2 = this._currentFrame;
         if (var2 == -1) {
            var2 = 0;
         }

         int var3 = 0;
         if (var2 > 0 && this._image.getImageType() == 1) {
            Object var4 = this._image;
            if (((GIFEncodedImage)var4).getFrameTransition((var2 + this._frameCount - 1) % this._frameCount) == 2) {
               var3 = var2;
            }
         }

         while (var3 <= var2) {
            this.paintFrame(var3, var1);
            var3++;
         }
      }
   }

   @Override
   protected void onVisibilityChange(boolean var1) {
      super.onVisibilityChange(var1);
      this.toggleAnimation(var1);
      this._visible = var1;
   }

   @Override
   protected void onExposed() {
      super.onExposed();
      this.toggleAnimation(true);
      this._visible = true;
   }

   @Override
   protected void onUndisplay() {
      super.onUndisplay();
      this.toggleAnimation(false);
      this._visible = false;
   }

   @Override
   protected void onObscured() {
      super.onObscured();
      this.toggleAnimation(false);
      this._visible = false;
   }

   private void toggleAnimation(boolean var1) {
      if (var1 && this._running) {
         AnimationThread.addAnimation(this);
         this._addedToQueue = true;
      } else {
         AnimationThread.removeAnimation(this);
         this._addedToQueue = false;
      }
   }

   @Override
   public boolean animate() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public long getExecutionTime() {
      return this._timeToExecuteAt;
   }

   @Override
   public void addAnimationListener(AnimationListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void removeAnimationListener(AnimationListener var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private int findListener(Object var1, WeakReference[] var2) {
      for (int var3 = var2.length - 1; var3 >= 0; var3--) {
         Object var4 = var2[var3].get();
         if (var4 == var1) {
            return var3;
         }
      }

      return -1;
   }

   private void notify(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }
}
