package net.rim.tid.awt.event;

import net.rim.tid.awt.Event;
import net.rim.tid.itie.IComponent;

public class InputEvent extends ComponentEvent {
   protected long _when;
   protected int _modifiers;
   public static final int SHIFT_MASK;
   public static final int CTRL_MASK;
   public static final int ALT_MASK;
   public static final int LEFT_MASK;
   public static final int RIGHT_MASK;
   public static final int NOT_FROM_KEYPAD_MASK;

   public InputEvent(IComponent var1, int var2, long var3, int var5, int var6) {
      super(var1, var2, var6 | Event.INPUT_EVENT_MASK);
      this._when = var3;
      this._modifiers = var5;
   }

   public int getModifiers() {
      return this._modifiers;
   }

   public long getWhen() {
      return this._when;
   }

   public void setModifiers(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
