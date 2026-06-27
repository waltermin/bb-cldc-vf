package net.rim.device.api.system;

public class EventInjector$TrackwheelEvent extends EventInjector$Event {
   private boolean _isThumbRollUp;
   public static final int THUMB_CLICK;
   public static final int THUMB_UNCLICK;
   public static final int THUMB_ROLL_UP;
   public static final int THUMB_ROLL_DOWN;

   public EventInjector$TrackwheelEvent(int var1, int var2, int var3) {
      super(2, var1 == 518 ? 519 : var1, var1 == 518 ? -var2 : var2, var3, 0, null, null);
      if (var1 == 518) {
         this._isThumbRollUp = true;
      }
   }

   @Override
   public int getEvent() {
      return this._isThumbRollUp ? 518 : super.getEvent();
   }

   @Override
   public void setEvent(int var1) {
      if (var1 != this.getEvent()) {
         if (this._isThumbRollUp) {
            this._isThumbRollUp = false;
            this.setAmount(-this.getAmount());
         } else if (var1 == 518) {
            var1 = 519;
            this._isThumbRollUp = true;
            this.setAmount(-this.getAmount());
         }
      }

      super.setEvent(var1);
   }

   public void setAmount(int var1) {
      if (this._isThumbRollUp) {
         var1 = -var1;
      }

      super._msg.setSubMessage(var1);
   }

   public int getAmount() {
      int var1 = super._msg.getSubMessage();
      return this._isThumbRollUp ? -var1 : var1;
   }
}
