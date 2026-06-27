package net.rim.device.internal.ui.component;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.lcdui.LcduiPlayerController;

public class MMAPIMediaField extends Field implements LcduiPlayerController, PlayerListener {
   private Player _player;
   private Object _component;
   private Bitmap _bitmap;
   private int _width;
   private int _height;
   private XYRect _currentRect;
   private boolean _playerPausedByObscure = false;
   private boolean _obscured;
   private boolean _firstPaint = true;

   public MMAPIMediaField(int var1, int var2) {
      this._width = var1;
      this._height = var2;
      this._currentRect = (XYRect)(new Object());
   }

   public MMAPIMediaField(int var1, int var2, long var3) {
      super(var3);
      this._width = var1;
      this._height = var2;
      this._currentRect = (XYRect)(new Object());
   }

   @Override
   public void setPlayer(Player var1) {
      this._player = var1;
      this._player.addPlayerListener(this);
   }

   @Override
   public void resizeComponent(int var1, int var2) {
      this._width = var1;
      this._height = var2;
   }

   @Override
   public void setComponent(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public Object getComponent() {
      return this._component;
   }

   @Override
   public void refresh() {
      if (!this._obscured) {
         Screen var1 = this.getScreen();
         if (var1 != null) {
            var1.invalidate();
            return;
         }

         Manager var2 = this.getManager();
         if (var2 != null) {
            var2.invalidate();
            return;
         }

         this.invalidate();
      }
   }

   @Override
   protected void paint(Graphics var1) {
      int var2 = var1.getColor();
      var1.setColor(0);
      var1.fillRect(0, 0, this._width, this._height);
      var1.setColor(var2);
      if (this._bitmap != null) {
         var1.drawBitmap(var1.getClippingRect(), this._bitmap, 0, 0);
      }

      if (this._firstPaint) {
         this.layoutMedia(true);
         this._firstPaint = false;
      } else {
         this.layoutMedia(!this._obscured);
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      this._width = MathUtilities.clamp(0, this._width, var1);
      this._height = MathUtilities.clamp(0, this._height, var2);
      this.setExtent(this._width, this._height);
   }

   @Override
   public int getPreferredHeight() {
      return this._height;
   }

   @Override
   public int getPreferredWidth() {
      return this._width;
   }

   @Override
   protected void onDisplay() {
      super.onDisplay();
      this.layoutMedia(true);
   }

   @Override
   protected void onVisibilityChange(boolean var1) {
      super.onVisibilityChange(var1);
      this.layoutMedia(var1);
   }

   @Override
   protected void onExposed() {
      super.onExposed();
      this.layoutMedia(true);
   }

   @Override
   protected void onObscured() {
      super.onObscured();
      this.layoutMedia(false);
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      this.layoutMedia(true);
   }

   @Override
   protected void onUnfocus() {
      super.onUnfocus();
      this.layoutMedia(true);
   }

   private void layoutMedia(boolean var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private void toggleVideoVisibility(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void playerUpdate(Player var1, String var2, Object var3) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
