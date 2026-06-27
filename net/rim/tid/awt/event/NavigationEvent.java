package net.rim.tid.awt.event;

import net.rim.tid.awt.Event;
import net.rim.tid.itie.IComponent;

public class NavigationEvent extends ComponentEvent {
   private int _dxMagnitude;
   private int _dyMagnitude;
   private int _status;
   private int _eventConsumptionId;
   public static final int COMPOSED_TEXT_NOT_CHANGED;
   public static final int COMPOSED_TEXT_CHANGED;
   public static final int CARET_POSITION_CHANGED;
   public static final int LOOKUP_POSITION_CHANGED;
   public static final int TRACK_BALL_ID;
   public static final int THUMB_WHEEL_ID;

   public NavigationEvent(IComponent var1, int var2) {
      super(var1, var2, Event.NAVIGATION_EVENT_MASK);
   }

   public void init(IComponent var1, int var2, int var3, int var4, int var5) {
      super.init(var1, var2);
      this._dxMagnitude = var3;
      this._dyMagnitude = var4;
      this._status = var5;
   }

   public int getDxMagnitude() {
      return this._dxMagnitude;
   }

   public int getDyMagnitude() {
      return this._dyMagnitude;
   }

   public boolean isLetterKeyHeld() {
      return (this._status & 8) != 0 && (this._status & 3) == 0;
   }

   public boolean isAltHeld() {
      return (this._status & 1) != 0;
   }

   public boolean isShiftHeld() {
      return (this._status & 2) != 0;
   }

   public boolean isKeyHeld() {
      return (this._status & 8) != 0;
   }

   public int getEventConsumptionId() {
      return this._eventConsumptionId;
   }

   public void setEventConsumptionId(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
