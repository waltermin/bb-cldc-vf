package javax.microedition.lcdui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Keypad;

class CustomField extends Field {
   private CustomItem _customItem;
   private Graphics _graphics;
   private boolean _isVisible;
   private int _translateOffset = 0;
   private boolean _traverseSuccessful = false;
   private int[] _focusRectInfo;

   public CustomField(CustomItem customItem) {
      super(18014398509481984L);
      this._customItem = customItem;
      this._graphics = new Graphics();
   }

   @Override
   protected void layout(int width, int height) {
      int oldWidth = this.getWidth();
      int oldHeight = this.getHeight();
      int newWidth = Math.min(this._customItem.getPrefContentWidth(-1), width);
      int newHeight = Math.min(this._customItem.getPrefContentHeight(-1), height);
      this.setExtent(newWidth, newHeight);
      if (newWidth != oldWidth || newHeight != oldHeight) {
         this._customItem.sizeChanged(newWidth, newHeight);
      }
   }

   @Override
   protected void onVisibilityChange(boolean visible) {
      if (visible != this._isVisible) {
         this._isVisible = visible;
         if (visible) {
            this._customItem.showNotify();
            return;
         }

         this._customItem.hideNotify();
      }
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   protected void onFocus(int direction) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   protected void onUnfocus() {
      this._translateOffset = 0;
      super.onUnfocus();
      this._customItem.traverseOut();
   }

   @Override
   protected void drawFocus(net.rim.device.api.ui.Graphics graphics, boolean on) {
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      char c = Keypad.map(keycode);
      if (c == 27) {
         return false;
      } else if (0 != c) {
         this._customItem.keyPressed(c);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean keyUp(int keycode, int time) {
      char c = Keypad.map(keycode);
      if (0 != c) {
         this._customItem.keyReleased(c);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected void paint(net.rim.device.api.ui.Graphics graphics) {
      if (this._graphics.getPeer() == null) {
         this._graphics.setGraphics(graphics, true);
      }

      if (this._focusRectInfo != null && this._traverseSuccessful) {
         int screenHeight = this._graphics.getClipHeight() - this._focusRectInfo[3];
         if (this._translateOffset + this._focusRectInfo[1] < 0) {
            this._translateOffset = -this._focusRectInfo[1];
         } else if (this._translateOffset + this._focusRectInfo[1] > screenHeight) {
            this._translateOffset = screenHeight - this._focusRectInfo[1];
         }

         this._traverseSuccessful = false;
      }

      graphics.translate(0, this._translateOffset);
      this._graphics.setGraphics(graphics, true);
      this._customItem.paint(this._graphics, this.getWidth(), this.getHeight());
   }

   public void redoLayout() {
      this.updateLayout();
   }

   public void callInvalidate() {
      this.invalidate();
   }

   public void callInvalidate(int x, int y, int width, int height) {
      this.invalidate(x, y, width, height);
   }
}
