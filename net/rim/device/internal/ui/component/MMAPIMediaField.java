package net.rim.device.internal.ui.component;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import net.rim.device.api.system.Backlight;
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

   public MMAPIMediaField(int width, int height) {
      this._width = width;
      this._height = height;
      this._currentRect = new XYRect();
   }

   public MMAPIMediaField(int width, int height, long style) {
      super(style);
      this._width = width;
      this._height = height;
      this._currentRect = new XYRect();
   }

   @Override
   public void setPlayer(Player player) {
      this._player = player;
      this._player.addPlayerListener(this);
   }

   @Override
   public void resizeComponent(int width, int height) {
      this._width = width;
      this._height = height;
   }

   @Override
   public void setComponent(Object component) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public Object getComponent() {
      return this._component;
   }

   @Override
   public void refresh() {
      if (!this._obscured) {
         Screen s = this.getScreen();
         if (s != null) {
            s.invalidate();
            return;
         }

         Manager m = this.getManager();
         if (m != null) {
            m.invalidate();
            return;
         }

         this.invalidate();
      }
   }

   @Override
   protected void paint(Graphics graphics) {
      int colour = graphics.getColor();
      graphics.setColor(0);
      graphics.fillRect(0, 0, this._width, this._height);
      graphics.setColor(colour);
      if (this._bitmap != null) {
         graphics.drawBitmap(graphics.getClippingRect(), this._bitmap, 0, 0);
      }

      if (this._firstPaint) {
         this.layoutMedia(true);
         this._firstPaint = false;
      } else {
         this.layoutMedia(!this._obscured);
      }
   }

   @Override
   protected void layout(int width, int height) {
      this._width = MathUtilities.clamp(0, this._width, width);
      this._height = MathUtilities.clamp(0, this._height, height);
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
   protected void onVisibilityChange(boolean visible) {
      super.onVisibilityChange(visible);
      this.layoutMedia(visible);
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
   protected void onFocus(int direction) {
      super.onFocus(direction);
      this.layoutMedia(true);
   }

   @Override
   protected void onUnfocus() {
      super.onUnfocus();
      this.layoutMedia(true);
   }

   private void layoutMedia(boolean visible) {
      throw new RuntimeException("cod2jar: type check");
   }

   private void toggleVideoVisibility(boolean visible) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void playerUpdate(Player player, String event, Object eventData) {
      if (player == this._player) {
         if (event.equals("endOfMedia") || event.equals("stopped") || event.equals("error")) {
            this._bitmap = null;
            this.invalidate();
            return;
         }

         if (event.equals("com.rim.pauseBitmap")) {
            if (eventData instanceof Bitmap) {
               this._bitmap = (Bitmap)eventData;
               this.invalidate();
               return;
            }
         } else if (event.equals("com.rim.timeUpdate") && this._player != null && this._player.getState() == 400) {
            Backlight.enable(true);
         }
      }
   }
}
