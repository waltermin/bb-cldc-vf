package net.rim.device.internal.ui;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.tid.awt.im.InputContext;
import net.rim.vm.TraceBack;

public final class InputMethodSwitcher extends PopupScreen implements FocusChangeListener, ListFieldCallback, Runnable, GlobalEventListener {
   private int _selectedIM;
   UiApplication _app;
   private ListField _listField;
   private String[] _imNames;
   private Locale[] _imLocales;
   private boolean _startedFromMenu;
   private Runnable _onExit;
   private Locale _selectedLocale;
   private boolean _closing;

   public final void selectNext(int var1) {
      if ((var1 & 1) == 0) {
         this.closeSwitcher(false);
      }

      this.setIndex(this._selectedIM + 1);
   }

   @Override
   public final int getPreferredWidth(ListField var1) {
      return this.getPreferredWidth();
   }

   @Override
   public final Object get(ListField var1, int var2) {
      return this._imNames[var2];
   }

   @Override
   public final int indexOfList(ListField var1, String var2, int var3) {
      return -1;
   }

   @Override
   public final void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      if (var3 > -1 && var3 < this._imNames.length) {
         var2.drawText(this._imNames[var3], 0, var4, 64, var5);
      }
   }

   @Override
   public final void focusChanged(Field var1, int var2) {
      if (var2 == 2) {
         this._selectedIM = this._listField.getSelectedIndex();
      }
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      if (var1 == 5961289116197897667L && var3 == 1 && var4 != 1) {
         this.doCloseSwitcher(false);
      }
   }

   @Override
   public final void run() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected final void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1 && !this._startedFromMenu) {
         this.selectNext(1);
      }
   }

   public InputMethodSwitcher(Locale[] var1, String[] var2, boolean var3, Runnable var4) {
      super((Manager)(new Object(299067162755072L)), 0);
      this._startedFromMenu = var3;
      if (this._startedFromMenu) {
         InputContext.getInstance().setIMSwitchEnabled(false);
      }

      this._onExit = var4;
      this._app = UiApplication.getUiApplication();
      this._app.addGlobalEventListener(this);
      this._imNames = var2;
      this._imLocales = var1;
      this._listField = (ListField)(new Object(this._imLocales.length, 8));
      this._listField.setCallback(this);
      this._listField.setFocusListener(this);
      this.add(this._listField);
      this.setIndex(0);
      boolean var5 = ControlledAccess.verifyCodeModuleSignature(TraceBack.getCallingModule(2), 51);
      int var6 = var5 ? -1073741823 : -1;
      if (ApplicationManager.getApplicationManager().isSystemLocked()) {
         var6 = -1073741825;
      }

      this._app.pushGlobalScreen(this, var6, 6);
   }

   private final void setIndex(int var1) {
      this._selectedIM = var1;
      if (this._selectedIM >= this._imNames.length) {
         this._selectedIM = 0;
      }

      this._listField.setSelectedIndex(this._selectedIM);
   }

   @Override
   protected final boolean trackwheelClick(int var1, int var2) {
      this.closeSwitcher(true);
      return true;
   }

   private final void closeSwitcher(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void doCloseSwitcher(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected final boolean keyStatus(int var1, int var2) {
      if (!this._startedFromMenu && Keypad.status(var1) != 32768) {
         this.closeSwitcher(Keypad.key(var1) == 257);
      }

      return true;
   }

   private final void onEnter(int var1) {
      if (!this._startedFromMenu && (var1 & 1) != 0) {
         this.setIndex(this._selectedIM + 1);
      } else {
         this.closeSwitcher(true);
      }
   }

   @Override
   protected final boolean keyDown(int var1, int var2) {
      int var3 = Keypad.status(var1);
      var1 = Keypad.keycode((char)Keypad.key(var1), var3 & -2);
      switch (Keypad.map(var1)) {
         case '\n':
            this.onEnter(var3);
            return true;
         case '\u001b':
            if (this._startedFromMenu || (var3 & 1) == 0) {
               this.closeSwitcher(false);
            }

            return true;
         default:
            return super.keyDown(var1, var2);
      }
   }

   @Override
   protected final boolean navigationClick(int var1, int var2) {
      this.onEnter(var1);
      return true;
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      return true;
   }
}
