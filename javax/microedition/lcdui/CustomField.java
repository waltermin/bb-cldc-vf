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

   public CustomField(CustomItem var1) {
      super(18014398509481984L);
      this._customItem = var1;
      this._graphics = new Graphics();
   }

   @Override
   protected void layout(int var1, int var2) {
      int var3 = this.getWidth();
      int var4 = this.getHeight();
      int var5 = Math.min(this._customItem.getPrefContentWidth(-1), var1);
      int var6 = Math.min(this._customItem.getPrefContentHeight(-1), var2);
      this.setExtent(var5, var6);
      if (var5 != var3 || var6 != var4) {
         this._customItem.sizeChanged(var5, var6);
      }
   }

   @Override
   protected void onVisibilityChange(boolean var1) {
      if (var1 != this._isVisible) {
         this._isVisible = var1;
         if (var1) {
            this._customItem.showNotify();
            return;
         }

         this._customItem.hideNotify();
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   protected void onFocus(int var1) {
      throw new RuntimeException("cod2jar: array store: unknown element");
   }

   @Override
   protected void onUnfocus() {
      this._translateOffset = 0;
      super.onUnfocus();
      this._customItem.traverseOut();
   }

   @Override
   protected void drawFocus(net.rim.device.api.ui.Graphics var1, boolean var2) {
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      char var3 = Keypad.map(var1);
      if (var3 == 27) {
         return false;
      } else if (0 != var3) {
         this._customItem.keyPressed(var3);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      char var3 = Keypad.map(var1);
      if (0 != var3) {
         this._customItem.keyReleased(var3);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected void paint(net.rim.device.api.ui.Graphics var1) {
      if (this._graphics.getPeer() == null) {
         this._graphics.setGraphics(var1, true);
      }

      if (this._focusRectInfo != null && this._traverseSuccessful) {
         int var2 = this._graphics.getClipHeight() - this._focusRectInfo[3];
         if (this._translateOffset + this._focusRectInfo[1] < 0) {
            this._translateOffset = -this._focusRectInfo[1];
         } else if (this._translateOffset + this._focusRectInfo[1] > var2) {
            this._translateOffset = var2 - this._focusRectInfo[1];
         }

         this._traverseSuccessful = false;
      }

      var1.translate(0, this._translateOffset);
      this._graphics.setGraphics(var1, true);
      this._customItem.paint(this._graphics, this.getWidth(), this.getHeight());
   }

   public void redoLayout() {
      this.updateLayout();
   }

   public void callInvalidate() {
      this.invalidate();
   }

   public void callInvalidate(int var1, int var2, int var3, int var4) {
      this.invalidate(var1, var2, var3, var4);
   }
}
