package net.rim.tid.awt.event;

import net.rim.tid.awt.Event;
import net.rim.tid.itie.IComponent;

public final class KeyEvent extends InputEvent {
   private int _keyCode;
   private char _keyChar;
   private boolean _inputEvent;
   private boolean _componentDispatch;
   public static final int VK_ENTER;
   public static final int VK_BACK_SPACE;
   public static final int VK_TAB;
   public static final int VK_ESCAPE;
   public static final int VK_SPACE;
   public static final int VK_END;
   public static final int VK_HOME;
   public static final int VK_LEFT;
   public static final int VK_UP;
   public static final int VK_RIGHT;
   public static final int VK_DOWN;
   public static final int VK_DELETE;
   public static final int VK_SHIFT_X;
   public static final int VK_SYM;
   public static final int VK_HELP;
   public static final int VK_CHAR_CONTEXT;
   public static final int VK_UNDEFINED;
   public static final char CHAR_UNDEFINED;
   public static final int KEY_PRESSED;
   public static final int KEY_RELEASED;
   public static final int KEY_REPEATED;
   public static final int KEY_ROLLED;
   public static final int THUMB_CLICK;

   public KeyEvent(IComponent var1, int var2, long var3, int var5, int var6, char var7, int var8) {
      super(var1, var2, var3, var5, var8 | Event.KEY_EVENT_MASK);
      this._keyCode = var6;
      this._keyChar = var7;
   }

   public final void init(IComponent var1, int var2, long var3, int var5, int var6, char var7) {
      this.init(var1, var2, var3, var5, var6, var7, true);
   }

   public final void init(IComponent var1, int var2, long var3, int var5, int var6, char var7, boolean var8) {
      super._source = var1;
      super._ID = var2;
      super._when = var3;
      super._modifiers = var5;
      this._keyCode = var6;
      this._keyChar = var7;
      super._consumed = false;
      this._inputEvent = var8;
      this._componentDispatch = false;
   }

   public final boolean isInputEvent() {
      return this._inputEvent;
   }

   public final char getKeyChar() {
      return this._keyChar;
   }

   public final int getKeyCode() {
      return this._keyCode;
   }

   public final void setKeyChar(char var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setKeyCode(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setComponentDispatchEnabled(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public final boolean isComponentDispatchEnabled() {
      return this._componentDispatch;
   }
}
