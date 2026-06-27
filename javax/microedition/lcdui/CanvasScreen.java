package javax.microedition.lcdui;

import javax.microedition.media.Player;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Trackball;
import net.rim.device.internal.lcdui.Callbacks;
import net.rim.device.internal.lcdui.Lcdui;
import net.rim.device.internal.lcdui.LcduiPlayerController;
import net.rim.device.internal.system.InternalServices;

class CanvasScreen extends MIDPScreen implements Callbacks, LcduiPlayerController {
   private Graphics _graphics;
   private Canvas _canvas;
   private boolean _firstPaint = true;
   private boolean _clearBackground;
   private boolean _playerPausedByObscure = false;
   private boolean _obscured;
   private boolean _dirty = false;
   private Player _player;

   public void setCanvas(Canvas var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDirty() {
      this._dirty = true;
   }

   @Override
   public void keyCallback(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void paintCallback(net.rim.device.api.ui.Graphics var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setPlayer(Player var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public void resizeComponent(int var1, int var2) {
   }

   @Override
   public Object getComponent() {
      return this._canvas;
   }

   @Override
   public void setComponent(Object var1) {
   }

   @Override
   public void refresh() {
      if (!this._obscured) {
         this.invalidate();
      }
   }

   @Override
   protected void onObscured() {
      super.onObscured();
      this.toggleVideoVisibility(false);
   }

   private void toggleVideoVisibility(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void init() {
      this._firstPaint = true;
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      if (var1 > 0) {
         Lcdui.setKeyCallback(5, this, 5);
      } else if (var1 < 0) {
         Lcdui.setKeyCallback(5, this, 2);
      }

      if (var2 < 0) {
         Lcdui.setKeyCallback(5, this, 1);
         return true;
      }

      if (var2 > 0) {
         Lcdui.setKeyCallback(5, this, 6);
      }

      return true;
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      if (Trackball.isSupported()) {
         Lcdui.setKeyCallback(5, this, -8);
         return true;
      } else {
         return super.navigationClick(var1, var2);
      }
   }

   @Override
   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      if (var1 < 0) {
         if ((var2 & 1) > 0) {
            Lcdui.setKeyCallback(5, this, 2);
            return true;
         } else {
            Lcdui.setKeyCallback(5, this, 1);
            return true;
         }
      } else {
         if (var1 > 0) {
            if ((var2 & 1) > 0) {
               Lcdui.setKeyCallback(5, this, 5);
               return true;
            }

            Lcdui.setKeyCallback(5, this, 6);
         }

         return true;
      }
   }

   @Override
   public boolean dispatchKeyEvent(int var1, char var2, int var3, int var4) {
      switch (var2) {
         case '\u0095':
            return super.dispatchKeyEvent(var1, var2, var3, var4);
         case '\u0096':
         case '\u0097':
         default:
            this.restartTickerTimer();
            Lcdui.setKeyCallback(5, this, var2 == 150 ? -150 : -151);
            return true;
      }
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      if (Keypad.hasSendEndKeys()) {
         switch (Keypad.key(var1)) {
            case 17:
            case 18:
            case 4098:
               return false;
            case 19:
               Lcdui.setKeyCallback(2, this, -19);
               return true;
            case 21:
               Lcdui.setKeyCallback(2, this, -21);
               return true;
         }
      }

      char var3 = Keypad.map(var1);
      if (var3 == 27) {
         return false;
      }

      if (0 != var3) {
         if (InternalServices.isReducedFormFactor()) {
            var3 = this.getCharmRemappedCharacter(var3);
         }

         Lcdui.setKeyCallback(2, this, var3);
      }

      return true;
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      if (Keypad.hasSendEndKeys()) {
         switch (Keypad.key(var1)) {
            case 16:
            case 20:
               break;
            case 17:
            case 18:
            default:
               return false;
            case 19:
               Lcdui.setKeyCallback(4, this, -19);
               return true;
            case 21:
               Lcdui.setKeyCallback(4, this, -21);
               return true;
         }
      }

      char var3 = Keypad.map(var1);
      if (0 != var3) {
         if (InternalServices.isReducedFormFactor()) {
            var3 = this.getCharmRemappedCharacter(var3);
         }

         Lcdui.setKeyCallback(4, this, var3);
      }

      return true;
   }

   private char getCharmRemappedCharacter(char var1) {
      if (var1 != '*' && var1 != '#' && (var1 < '0' || var1 > '9')) {
         char var2 = Keypad.getAltedChar(var1);
         return var2 != '*' && var2 != '#' && (var2 < '0' || var2 > '9') ? var1 : var2;
      } else {
         return Keypad.getUnaltedChar(var1);
      }
   }

   public CanvasScreen() {
      this._graphics = new Graphics();
   }

   @Override
   public boolean isDirty() {
      return this._dirty;
   }

   @Override
   Displayable getDisplayable() {
      return this._canvas;
   }

   @Override
   public void paint(net.rim.device.api.ui.Graphics var1) {
      if (this._firstPaint) {
         var1.clear();
         this.paintCallback(var1);
         this._firstPaint = false;
      } else {
         Lcdui.setPaintCallback(var1, this, this);
      }
   }

   @Override
   public void paintBackground(net.rim.device.api.ui.Graphics var1) {
   }

   @Override
   protected void onVisibilityChange(boolean var1) {
      super.onVisibilityChange(var1);
      this.toggleVideoVisibility(var1);
   }

   @Override
   protected void onExposed() {
      this._clearBackground = true;
      this._canvas.repaint();
      super.onExposed();
      this.toggleVideoVisibility(true);
   }
}
