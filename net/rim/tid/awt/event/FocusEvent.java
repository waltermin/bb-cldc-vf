package net.rim.tid.awt.event;

import net.rim.tid.awt.Event;
import net.rim.tid.itie.IComponent;

public final class FocusEvent extends ComponentEvent {
   private int _appID;
   public static final int FOCUS_GAINED;
   public static final int FOCUS_LOST;

   public FocusEvent(IComponent var1, int var2, int var3, int var4) {
      super(var1, var2, var3 | Event.FOCUS_EVENT_MASK);
      this._appID = var4;
   }

   public final void init(IComponent var1, int var2, int var3) {
      super.init(var1, var2);
      this._appID = var3;
   }

   public final int getApplicationId() {
      return this._appID;
   }
}
