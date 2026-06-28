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

   public final void selectNext(int status) {
      if (!this._ignoreAltKey && (status & 1) == 0) {
         this.closeAppSwitcher(false);
      }

      if (this._verticalDisplay || this._icons.getLeafFieldWithFocus() != null) {
         this.setIndex(this._selectedApp + 1);
      }
   }

   @Override
   public final Object get(ListField listField, int index) {
      return this._appNames[index];
   }

   @Override
   public final int indexOfList(ListField listField, String prefix, int start) {
      return -1;
   }

   @Override
   public final void drawListRow(ListField listField, Graphics graphics, int index, int y, int width) {
      if (index > -1 && index < this._descriptors.length) {
         graphics.drawText(this._appNames[index], 0, y, 64, width);
      }
   }

   @Override
   public final void focusChanged(Field field, int action) {
      if (action == 2) {
         if (this._verticalDisplay) {
            this._selectedApp = this._listField.getSelectedIndex();
            return;
         }

         Field focus = this._icons.getFieldWithFocus();
         if (focus != null) {
            this._selectedTitle.setText(focus.getCookie());
            this._selectedApp = focus.getIndex();
         }
      }
   }

   @Override
   public final int getPreferredWidth(ListField listField) {
      return this.getPreferredWidth();
   }

   @Override
   public final void backlightStateChange(boolean on) {
      if (!on) {
         this.closeAppSwitcher(false);
      }
   }

   @Override
   public final void cradleMismatch(boolean mismatch) {
   }

   @Override
   public final void fastReset() {
   }

   @Override
   public final void powerOffRequested(int reason) {
   }

   @Override
   public final void usbConnectionStateChange(int state) {
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
   public final void batteryStatusChange(int status) {
   }

   @Override
   protected final boolean keyStatus(int keycode, int time) {
      int key = Keypad.key(keycode);
      if (!this._ignoreAltKey) {
         if (key == 257) {
            this.closeAppSwitcher(true);
            return true;
         }

         this.closeAppSwitcher(false);
      }

      return true;
   }

   private final boolean startsWithCharacterUnderscored(char ch, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected final void onUiEngineAttached(boolean attached) {
      super.onUiEngineAttached(attached);
      if (attached) {
         this.selectNext(1);
      }
   }

   @Override
   protected final boolean navigationClick(int status, int time) {
      this.closeAppSwitcher(true);
      return true;
   }

   @Override
   protected final boolean navigationMovement(int dx, int dy, int status, int time) {
      status &= -2;
      return super.navigationMovement(dx, dy, status, time);
   }

   private final boolean getTaskSwitcherDisplayType(ThemeAttributeSet taskSwitcherTheme) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public ApplicationSwitcher(Runnable onExit, int convenienceKey) {
   }

   private final void setIndex(int index) {
      this._selectedApp = index;
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

   private final void closeAppSwitcher(boolean selected) {
      boolean oldClosing;
      synchronized (this) {
         oldClosing = this._closing;
         this._closing = true;
      }

      if (!oldClosing) {
         this.doCloseAppSwitcher(selected);
      }
   }

   private final void doCloseAppSwitcher(boolean selected) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected final boolean keyChar(char key, int status, int time) {
      if (!this._ignoreAltKey) {
         this.closeAppSwitcher(false);
         return true;
      } else {
         return super.keyChar(key, status, time);
      }
   }

   @Override
   protected final boolean keyDown(int keycode, int time) {
      boolean result = false;
      int key = Keypad.key(keycode);
      keycode = Keypad.keycode((char)key, Keypad.status(keycode) & -2);
      char ch = Character.toUpperCase(KeypadUtil.getKeyChar(keycode, 0));
      int end = this._descriptors.length;

      for (int lv = 0; lv < end; lv++) {
         if (this.startsWithCharacterUnderscored(ch, (String)(this._verticalDisplay ? this._appNames[lv] : this._appIcons[lv].getCookie()))) {
            this.setIndex(lv);
            break;
         }
      }

      if (this._ignoreAltKey) {
         switch (Keypad.key(keycode)) {
            case 10:
               this.closeAppSwitcher(true);
               result = true;
               break;
            case 19:
               if (this._convenienceKey == 1) {
                  this.closeAppSwitcher(true);
               }

               result = true;
               break;
            case 21:
               if (this._convenienceKey == 2) {
                  this.closeAppSwitcher(true);
               }

               result = true;
         }
      }

      if (key == 17 || key == 18 || ch == 27) {
         this.closeAppSwitcher(false);
         result = true;
      }

      if (!result) {
         result = super.keyDown(keycode, time);
      }

      return result;
   }
}
