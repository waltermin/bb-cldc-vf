package net.rim.device.api.ui;

class Screen$LocationFocusSelector implements Screen$FocusSelector {
   private int _x;
   private int _y;
   private int _status;
   private int _time;
   private Field _field;
   private boolean _success;
   private static Screen$LocationFocusSelector _selector;

   public static Screen$LocationFocusSelector getSelector(Field var0, int var1, int var2, int var3, int var4) {
      _selector._field = var0;
      _selector._x = var1;
      _selector._y = var2;
      _selector._status = var3;
      _selector._time = var4;
      return _selector;
   }

   @Override
   public void select() {
      Field var1 = this._field;
      if (var1 == null) {
         this._success = false;
      } else {
         Screen var2 = var1.getScreen();
         Field var3 = var2.getLeafFieldWithFocus();
         var2.onUnfocus();
         this._success = var1.moveFocusToPoint(this._x, this._y, this._status, this._time);
         if (this._success) {
            var1.focusChangeNotify(1);

            for (Manager var4 = var1.getManager(); var4 != var2; var4 = var4.getManager()) {
               var4.focusChangeNotify(1);
               var4.setFieldWithFocus(var1);
               var1 = var4;
            }
         } else if (var3 != null) {
            var3.setFocus();
         }

         this._field = null;
      }
   }

   public final boolean getSuccess() {
      return this._success;
   }
}
