package net.rim.tid.awt;

import net.rim.tid.itie.IComponent;

public class Event {
   protected IComponent _source;
   protected int _ID;
   protected int _mask;
   protected boolean _consumed;
   public static int COMPONENT_EVENT_MASK;
   public static int NAVIGATION_EVENT_MASK;
   public static int FOCUS_EVENT_MASK;
   public static int KEY_EVENT_MASK;
   public static int ACTION_EVENT_MASK;
   public static int INPUT_EVENT_MASK;
   public static int INPUT_METHOD_EVENT_MASK;

   public Event(IComponent var1, int var2, int var3) {
      this._source = var1;
      this._ID = var2;
      this._mask = var3;
   }

   public void init(IComponent var1, int var2) {
      this._source = var1;
      this._ID = var2;
      this._consumed = false;
   }

   public void setSource(IComponent var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getEventMask() {
      return this._mask;
   }

   public int getID() {
      return this._ID;
   }

   public IComponent getSource() {
      return this._source;
   }

   public boolean isConsumed() {
      return this._consumed;
   }

   public void consume() {
      this._consumed = true;
   }

   public boolean isComponentDispatchEnabled() {
      return false;
   }
}
