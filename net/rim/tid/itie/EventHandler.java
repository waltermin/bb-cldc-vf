package net.rim.tid.itie;

import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.FocusEvent;
import net.rim.tid.awt.event.KeyEvent;
import net.rim.tid.awt.event.NavigationEvent;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.im.layout.CurrencyKeyDialog;
import net.rim.tid.im.layout.SLKeyLayout;

public final class EventHandler {
   private KeyEvent _keyEvent = new KeyEvent(null, 0, 0, 0, 0, '\u0000', Event.KEY_EVENT_MASK);
   private FocusEvent _focusEvent = new FocusEvent(null, 1004, Event.FOCUS_EVENT_MASK, 0);
   private NavigationEvent _navigationEvent = new NavigationEvent(null, 0);
   private int _focusHistoryStart = -1;
   private int _focusHistoryIndex = -1;
   private int _focusHistoryLogCount = 0;
   private int[] _focusHistoryAppId = new int[3];
   private String[] _focusHistoryComponent = new String[3];
   private boolean[] _focusHistoryEvent = new boolean[3];
   private int _focusHistoryLastAppId = -1;
   public static final int EH_CLOSE_CURRENCY_KEY_DIALOG;
   public static final int EVENT_COMMITTED_MASK;
   private static final int MAX_FOCUS_HISTORY_COUNT;

   EventHandler() {
   }

   private final void initCurrencySign() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final int processKeyEvent(int var1, int var2, char var3, int var4, int var5, boolean var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final synchronized int processKeyEvent0(int var1, int var2, char var3, int var4, int var5, boolean var6) {
      InputContext var7 = InputContext.getInstance();
      IComponent var8 = var7.getInputComponent();
      if (var1 == 6913) {
         if (var2 == 0) {
            return 0;
         }

         var1 = 519;
      }

      switch (var1) {
         case 512:
         case 517:
         case 518:
            return 0;
         case 513:
         case 514:
         case 515:
         case 520:
         default:
            this._keyEvent.init(var8, var1, var5, SLKeyLayout.convertStatusToModifiers(var4), var2 >> 16, var3, var6);
            break;
         case 516:
         case 519:
            this._keyEvent.init(var8, var1, var5, SLKeyLayout.convertStatusToModifiers(var4), var2, var3, var6);
      }

      if (var8 != null) {
         var8.dispatchEvent(this._keyEvent);
      } else {
         var7.dispatchEvent(this._keyEvent);
      }

      this._keyEvent.setSource(null);
      return this._keyEvent.isConsumed() ? this._keyEvent.getKeyChar() | 65536 : this._keyEvent.getKeyChar();
   }

   public final boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final boolean processNavigationEvent0(int var1, int var2, int var3, int var4, int var5) {
      byte var6 = 0;
      if (var1 == 519) {
         var6 = 2;
      } else {
         if (var1 != 6913) {
            return false;
         }

         var6 = 1;
      }

      InputContext var7 = InputContext.getInstance();
      IComponent var8 = var7.getInputComponent();
      this._navigationEvent.init(var8, var6, var2, var3, var4);
      if (var8 != null) {
         var8.dispatchEvent(this._navigationEvent);
      } else {
         var7.dispatchEvent(this._navigationEvent);
      }

      this._navigationEvent.setSource(null);
      if (!this._navigationEvent.isConsumed()) {
         switch (var1) {
            case 519:
               if ((this.processKeyEvent(519, var3, (char)var4, var4, var5, true) & 65536) == 65536) {
                  return true;
               }

               return false;
            case 6913:
               if ((this.processKeyEvent(519, var2, (char)var4, var4, var5, true) & 65536) == 65536) {
                  return true;
               }

               return false;
         }
      }

      return this._navigationEvent.isConsumed();
   }

   public final void focusGained(IComponent var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void focusGained0(IComponent var1, int var2, int var3) {
      if (var1 != null) {
         InputContext var4 = InputContext.getInstance();
         int var5 = var4.getAppId();
         if (var4.getInputComponent() != var1 || var5 == -1 || var5 != var3 || var4.getInputComponent() == var1 && var5 == var3) {
            this.fillFocusHistory(var3, var1, true);
            this._focusEvent.init(var1, 1004, var3);
            var1.dispatchEvent(this._focusEvent);
            this._focusEvent.setSource(null);
         }
      }
   }

   public final void focusLost(IComponent var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void focusLost0(IComponent var1, int var2, int var3) {
      this.fillFocusHistory(var3, var1, false);
      if (var1 instanceof Object) {
         InputContext.getInstance().endComposition();
      } else {
         IComponent var4 = InputContext.getInstance().getInputComponent();
         if (var4 != null) {
            IComponent var5 = InputContext.getInstance().getAppId() == var3 ? var4 : var1;
            if (var5 == null) {
               return;
            }

            this._focusEvent.init(var5, 1005, var3);
            var5.dispatchEvent(this._focusEvent);
            this._focusEvent.setSource(null);
         }
      }
   }

   public static final EventHandler getInstance() {
      return ((IMContext)InputContext.getInstance()).getEventHandler();
   }

   public final void actionPerformed(int var1, Object var2) {
      switch (var1) {
         case 1:
            CurrencyKeyDialog.closeDialog();
      }
   }

   private final void fillFocusHistory(int var1, IComponent var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void printFocusEvent(int var1, String var2, boolean var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void printFocusHistory() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final String findAppName(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }
}
