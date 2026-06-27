package javax.microedition.lcdui;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.util.Comparator;
import net.rim.device.api.util.SimpleSortingVector;
import net.rim.device.internal.lcdui.Lcdui;

class MIDPScreen extends net.rim.device.api.ui.Screen implements Comparator {
   private Displayable _displayable;
   protected Ticker _ticker;
   private LabelField _title;
   private SeparatorField _separator;
   private boolean _fullScreenMode;
   private XYRect _displayableAreaExtent = (XYRect)(new Object(
      0, 0, net.rim.device.api.system.Display.getWidth(), net.rim.device.api.system.Display.getHeight()
   ));
   private SimpleSortingVector _commands = (SimpleSortingVector)(new Object());
   private CommandListener _commandListener;
   private Command _escapeCommand;
   private static Runnable _tickerRunnable;
   private static boolean _pendingTimer;
   private static boolean _idleTimer;

   Displayable getDisplayable() {
      return this._displayable;
   }

   final void setDisplayable(Displayable var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   final void setDisplay(Display var1) {
   }

   void setFullScreenMode(boolean var1) {
      if (this._fullScreenMode != var1) {
         this._fullScreenMode = var1;
         if (var1) {
            if (this._title != null) {
               this.deleteRange(0, 2);
            }
         } else if (this._title != null) {
            this.insert(this._title, 0);
            this.insert(this._separator, 1);
            this._title.setDirty(true);
         }

         this.recalcDisplayableAreaHeight();
         this.invalidate();
      }
   }

   XYRect getDisplayableAreaExtent() {
      return this._displayableAreaExtent;
   }

   String getTitle() {
      throw new RuntimeException("cod2jar: exception table");
   }

   void setTitle(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void init() {
   }

   public final void setTicker(Ticker var1) {
      boolean var2 = false;
      if (var1 != null) {
         if (this._ticker == null && this.isValidLayout()) {
            var2 = true;
            scheduleTickerTimer(0);
         }

         this._ticker = var1;
         var1.setStuff(this.getFont());
      } else if (this._ticker != null) {
         var2 = true;
         this._ticker = var1;
      }

      if (var2) {
         this.recalcDisplayableAreaHeight();
         this.invalidate();
      }
   }

   public final Ticker getTicker() {
      return this._ticker;
   }

   boolean advanceTicker() {
      if (this._ticker != null) {
         this._ticker.advanceTicker();
         boolean var1 = !this._fullScreenMode;
         if (var1) {
            int var2 = this._ticker.getHeight();
            net.rim.device.api.ui.Graphics var3 = this.getGraphics();
            var3.clear(0, this.getHeight() - var2, this.getWidth(), var2);
            this._ticker.draw(var3, this.getHeight() - var2);
            this.updateDisplay();
         }

         return true;
      } else {
         return false;
      }
   }

   void restartTickerTimer() {
      if (_idleTimer) {
         scheduleTickerTimer(0);
      }
   }

   public void addCommand(Command var1) {
      int var2 = this._commands.size();

      for (int var3 = 0; var3 < var2; var3++) {
         if (this._commands.elementAt(var3) == var1) {
            return;
         }
      }

      this._commands.addElement(var1);
      this.updateKeyMappings(var1);
   }

   public void removeCommand(Command var1) {
      if (this._commands != null) {
         int var2 = this._commands.size();

         for (int var3 = 0; var3 < var2; var3++) {
            if (this._commands.elementAt(var3) == var1) {
               this._commands.removeElementAt(var3);
               break;
            }
         }

         if (var1 == this._escapeCommand) {
            this.updateEscapeCommand();
         }
      }
   }

   public void setCommandListener(CommandListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public int compare(Object var1, Object var2) {
      return ((Command)var1).getPriority() - ((Command)var2).getPriority();
   }

   @Override
   protected void sublayout(int var1, int var2) {
      this.setPosition(0, 0);
      this.setExtent(var1, var2);
      boolean var3 = !this._fullScreenMode && this._ticker != null;
      if (var3) {
         this._ticker.setStuff(this.getFont());
         var2 -= this._ticker.getHeight();
      }

      this.setPositionDelegate(0, 0);
      this.layoutDelegate(var1, var2);
   }

   @Override
   protected void paint(net.rim.device.api.ui.Graphics var1) {
      super.paint(var1);
      boolean var2 = !this._fullScreenMode && this._ticker != null;
      if (var2) {
         this._ticker.draw(var1, this.getHeight() - this._ticker.getHeight());
      }
   }

   private final void destroyMIDlet() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (super.keyChar(var1, var2, var3)) {
         return true;
      } else if (var1 == 27 && this._escapeCommand != null) {
         Lcdui.setCommandActionCallback(this._commandListener, this._escapeCommand, this.getDisplayable());
         return true;
      } else {
         return false;
      }
   }

   private long getEscapeKeyPriority(Command var1) {
      if (var1 == null) {
         return Long.MAX_VALUE;
      }

      long var2;
      switch (var1.getCommandType()) {
         case 2:
            var2 = 8589934592L;
            break;
         case 3:
            var2 = 4294967296L;
            break;
         case 7:
            var2 = 12884901888L;
            break;
         default:
            return Long.MAX_VALUE;
      }

      return var2 + var1.getPriority();
   }

   private void updateKeyMappings(Command var1) {
      switch (var1.getCommandType()) {
         case 2:
         case 3:
         case 7:
            if (this.getEscapeKeyPriority(var1) < this.getEscapeKeyPriority(this._escapeCommand)) {
               this._escapeCommand = var1;
            }

            return;
      }
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private static final void scheduleTickerTimer(long var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   MIDPScreen() {
      super((Manager)(new Object(3458764513820540928L)), 65536);
      this._commands.setSortComparator(this);
      this._commands.setSort(true);
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1 && this._ticker != null) {
         scheduleTickerTimer(0);
      }
   }

   private void updateEscapeCommand() {
      long var1 = Long.MAX_VALUE;
      this._escapeCommand = null;
      int var3 = this._commands.size();

      for (int var4 = 0; var4 < var3; var4++) {
         Command var5 = (Command)this._commands.elementAt(var4);
         if (this.getEscapeKeyPriority(var5) < var1) {
            var1 = this.getEscapeKeyPriority(var5);
            this._escapeCommand = var5;
         }
      }
   }

   MIDPScreen(Manager var1) {
      super(var1);
      this._commands.setSortComparator(this);
      this._commands.setSort(true);
   }

   private void recalcDisplayableAreaHeight() {
      int var1 = net.rim.device.api.system.Display.getHeight();
      int var2 = 0;
      if (!this._fullScreenMode) {
         if (this._title != null) {
            var2 = this._title.getPreferredHeight() + this._separator.getPreferredHeight();
            var1 -= var2;
         }

         if (this._ticker != null) {
            var1 -= this._ticker.getHeight();
         }
      }

      this._displayableAreaExtent.set(0, var2, net.rim.device.api.system.Display.getWidth(), var1);
   }

   @Override
   public boolean dispatchKeyEvent(int var1, char var2, int var3, int var4) {
      this.restartTickerTimer();
      return var1 == 32768 && var2 == 0 ? false : super.dispatchKeyEvent(var1, var2, var3, var4);
   }

   @Override
   public boolean dispatchTrackwheelEvent(int var1, int var2, int var3, int var4) {
      this.restartTickerTimer();
      return super.dispatchTrackwheelEvent(var1, var2, var3, var4);
   }
}
