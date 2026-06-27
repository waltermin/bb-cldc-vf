package net.rim.device.internal.ui;

import net.rim.device.api.system.ApplicationDescriptor;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.KeypadUtil;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.TextInputDialog;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.internal.ui.component.ApplicationIconField;

public final class ApplicationSwitcher extends PopupScreen implements FocusChangeListener, ListFieldCallback, TextInputDialog, SystemListener2 {
   private ApplicationDescriptor[] _descriptors;
   private Runnable _onExit;
   private boolean _verticalDisplay;
   private boolean _ignoreAltKey;
   private int _convenienceKey;
   private boolean _closing;
   private Manager _icons;
   private LabelField _selectedTitle;
   private int _selectedApp;
   private ApplicationIconField[] _appIcons;
   UiApplication _app;
   private ListField _listField;
   private String[] _appNames;

   public final void selectNext(int var1) {
      if (!this._ignoreAltKey && (var1 & 1) == 0) {
         this.closeAppSwitcher(false);
      }

      if (this._verticalDisplay || this._icons.getLeafFieldWithFocus() != null) {
         this.setIndex(this._selectedApp + 1);
      }
   }

   @Override
   public final Object get(ListField var1, int var2) {
      return this._appNames[var2];
   }

   @Override
   public final int indexOfList(ListField var1, String var2, int var3) {
      return -1;
   }

   @Override
   public final void drawListRow(ListField var1, Graphics var2, int var3, int var4, int var5) {
      if (var3 > -1 && var3 < this._descriptors.length) {
         var2.drawText(this._appNames[var3], 0, var4, 64, var5);
      }
   }

   @Override
   public final void focusChanged(Field var1, int var2) {
      if (var2 == 2) {
         if (this._verticalDisplay) {
            this._selectedApp = this._listField.getSelectedIndex();
            return;
         }

         Field var3 = this._icons.getFieldWithFocus();
         if (var3 != null) {
            this._selectedTitle.setText(var3.getCookie());
            this._selectedApp = var3.getIndex();
         }
      }
   }

   @Override
   public final int getPreferredWidth(ListField var1) {
      return this.getPreferredWidth();
   }

   @Override
   public final void backlightStateChange(boolean var1) {
      if (!var1) {
         this.closeAppSwitcher(false);
      }
   }

   @Override
   public final void cradleMismatch(boolean var1) {
   }

   @Override
   public final void fastReset() {
   }

   @Override
   public final void powerOffRequested(int var1) {
   }

   @Override
   public final void usbConnectionStateChange(int var1) {
   }

   @Override
   public final void powerOff() {
   }

   @Override
   public final void powerUp() {
   }

   @Override
   public final void batteryLow() {
   }

   @Override
   public final void batteryGood() {
   }

   @Override
   public final void batteryStatusChange(int var1) {
   }

   @Override
   protected final boolean keyStatus(int var1, int var2) {
      int var3 = Keypad.key(var1);
      if (!this._ignoreAltKey) {
         if (var3 == 257) {
            this.closeAppSwitcher(true);
            return true;
         }

         this.closeAppSwitcher(false);
      }

      return true;
   }

   private final boolean startsWithCharacterUnderscored(char var1, String var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final void onUiEngineAttached(boolean var1) {
      super.onUiEngineAttached(var1);
      if (var1) {
         this.selectNext(1);
      }
   }

   @Override
   protected final boolean navigationClick(int var1, int var2) {
      this.closeAppSwitcher(true);
      return true;
   }

   @Override
   protected final boolean navigationMovement(int var1, int var2, int var3, int var4) {
      var3 &= -2;
      return super.navigationMovement(var1, var2, var3, var4);
   }

   private final boolean getTaskSwitcherDisplayType(ThemeAttributeSet var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public ApplicationSwitcher(Runnable var1, int var2) {
   }

   private final void setIndex(int var1) {
      this._selectedApp = var1;
      if (this._selectedApp >= this._descriptors.length) {
         this._selectedApp = 0;
      }

      if (this._verticalDisplay) {
         this._listField.setSelectedIndex(this._selectedApp);
      } else {
         this._appIcons[this._selectedApp].setFocus();
         this._selectedTitle.setText(this._appIcons[this._selectedApp].getCookie());
      }
   }

   private final void closeAppSwitcher(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void doCloseAppSwitcher(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected final boolean keyChar(char var1, int var2, int var3) {
      if (!this._ignoreAltKey) {
         this.closeAppSwitcher(false);
         return true;
      } else {
         return super.keyChar(var1, var2, var3);
      }
   }

   @Override
   protected final boolean keyDown(int var1, int var2) {
      boolean var3 = false;
      int var4 = Keypad.key(var1);
      var1 = Keypad.keycode((char)var4, Keypad.status(var1) & -2);
      char var5 = Character.toUpperCase(KeypadUtil.getKeyChar(var1, 0));
      int var8 = this._descriptors.length;

      for (int var7 = 0; var7 < var8; var7++) {
         if (this.startsWithCharacterUnderscored(var5, (String)(this._verticalDisplay ? this._appNames[var7] : this._appIcons[var7].getCookie()))) {
            this.setIndex(var7);
            break;
         }
      }

      if (this._ignoreAltKey) {
         switch (Keypad.key(var1)) {
            case 10:
               this.closeAppSwitcher(true);
               var3 = true;
               break;
            case 19:
               if (this._convenienceKey == 1) {
                  this.closeAppSwitcher(true);
               }

               var3 = true;
               break;
            case 21:
               if (this._convenienceKey == 2) {
                  this.closeAppSwitcher(true);
               }

               var3 = true;
         }
      }

      if (var4 == 17 || var4 == 18 || var5 == 27) {
         this.closeAppSwitcher(false);
         var3 = true;
      }

      if (!var3) {
         var3 = super.keyDown(var1, var2);
      }

      return var3;
   }
}
