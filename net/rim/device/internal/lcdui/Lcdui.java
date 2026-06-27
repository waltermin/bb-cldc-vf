package net.rim.device.internal.lcdui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.XYRect;

public final class Lcdui {
   private boolean _paintCallback;
   private Callbacks _paintCallbacks;
   private Screen _screen;
   private XYRect _invalid = (XYRect)(new Object());
   private int _callbackType = 0;
   private int _callbackState = 0;
   private Callbacks _keyCallbacks;
   private int _keycode;
   private CommandListener _commandListener;
   private Command _command;
   private Displayable _displayable;
   private Runnable _runnable;
   private Object _notifier;
   private Graphics _midpGraphics;
   private boolean _suppressKeyEvents;
   private int _currentKeyStates;
   private int _keyDownHistory;
   public static final long MIDLET_NOTIFICATION_GUID;
   private static Lcdui _impl;
   private static final int NONE;
   public static final int KEYDOWN;
   public static final int KEYREPEAT;
   public static final int KEYUP;
   public static final int KEYDOWNUP;
   private static final int COMMANDACTION;
   private static final int INVOKELATER;
   public static final int USER_INVOKELATER;
   private static final int FRESH;
   private static final int STALE;
   private static final Object _eventDeliveryLock;

   public static final void init() {
      _impl._paintCallback = false;
      _impl._paintCallbacks = null;
      _impl._screen = null;
      _impl._callbackType = 0;
      _impl._keyCallbacks = null;
      _impl._commandListener = null;
      _impl._command = null;
      _impl._displayable = null;
      _impl._suppressKeyEvents = false;
      _impl._currentKeyStates = 0;
      _impl._keyDownHistory = 0;
      _impl._midpGraphics = null;
   }

   public static final void setMIDPGraphics(Graphics var0) {
      _impl._midpGraphics = var0;
   }

   public static final Graphics getMIDPGraphics() {
      return _impl._midpGraphics;
   }

   public static final void setSuppressKeyEvents(boolean var0) {
      _impl._suppressKeyEvents = var0;
   }

   public static final boolean getSuppressKeyEvents() {
      return _impl._suppressKeyEvents;
   }

   public static final int getKeyDownHistory() {
      return _impl._keyDownHistory;
   }

   public static final int getCurrentKeyStates() {
      return _impl._currentKeyStates;
   }

   public static final void setKeyDownHistory(int var0) {
      _impl._keyDownHistory = var0;
   }

   public static final void setCurrentKeyStates(int var0) {
      _impl._currentKeyStates = var0;
   }

   public static final void setInvokeLaterCallback(Runnable var0, Object var1) {
      _impl._callbackType = 7;
      _impl._runnable = var0;
      _impl._notifier = var1;
   }

   public static final void setPaintCallback(net.rim.device.api.ui.Graphics var0, Screen var1, Callbacks var2) {
      XYRect var3 = var0.getClippingRect();
      _impl._paintCallback = true;
      _impl._screen = var1;
      _impl._paintCallbacks = var2;
      _impl._invalid.union(var3);
   }

   public static final void setKeyCallback(int var0, Callbacks var1, int var2) {
      _impl._callbackType = var0;
      _impl._keyCallbacks = var1;
      _impl._keycode = var2;
   }

   public static final void setCommandActionCallback(CommandListener var0, Command var1, Displayable var2) {
      _impl._callbackState = 0;
      _impl._callbackType = 6;
      _impl._commandListener = var0;
      _impl._command = var1;
      _impl._displayable = var2;
   }

   public static final void runCallback() {
      _impl.runCallback0();
   }

   private final void runCallback0() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object getEventDeliveryLock() {
      return _eventDeliveryLock;
   }

   public static final void runPaintCallback() {
      _impl.runPaintCallback0();
   }

   private final void runPaintCallback0() {
      throw new RuntimeException("cod2jar: exception table");
   }
}
