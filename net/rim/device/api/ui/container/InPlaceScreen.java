package net.rim.device.api.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.theme.Tag;

public class InPlaceScreen extends Screen {
   private Field _original;
   private Field _fake;
   private Manager _delegate;
   int _fontHeight;
   int _result;
   public static final long ALT_DISMISS;
   private static final Tag TAG;

   public InPlaceScreen(Field var1, Field var2, long var3) {
      super(new VerticalFieldManager(var3), var3);
      this.setTag(TAG);
      this._delegate = this.getDelegate();
      this._original = var1;
      this._fake = var2;
      this._delegate.add(this._fake);
   }

   public boolean doModal() {
      Ui.getUiEngine().pushModalScreen(this);
      return this._result != -1;
   }

   public Field getOriginalField() {
      return this._original;
   }

   public Field getField() {
      return this._fake;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == '\n') {
         this._result = 0;
         this.close();
         return true;
      } else if (var1 == 27) {
         this._result = -1;
         this.close();
         return true;
      } else {
         return super.keyChar(var1, var2, var3);
      }
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      if (this.isStyle(1) && Keypad.key(var1) == 257 && (Keypad.status(var1) & 1) == 0) {
         this._result = 0;
         this.close();
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      if (var1 > 0 && var2 > 0 && var1 < this.getWidth() && var2 < this.getHeight()) {
         this._result = 0;
      } else {
         this._result = -1;
      }

      this.close();
      return true;
   }

   @Override
   protected boolean invokeAction(int var1) {
      switch (var1) {
         case 1:
            this._result = 0;
            this.close();
            return true;
         default:
            return false;
      }
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      return this.invokeAction(1);
   }
}
