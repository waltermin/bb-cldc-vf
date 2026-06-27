package net.rim.device.internal.ui.component;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngine;
import net.rim.device.api.ui.container.PopupScreen;

public class PopupDialog extends PopupScreen {
   private boolean _modal = true;
   private PopupDialogClosedListener _dialogClosedListener;
   private UiEngine _owner;
   private int _closeReason = 0;
   private int _statusPriority = 50;
   private boolean _open;
   private boolean _cancelAllowed = true;
   public static final int GLOBAL_STATUS;
   public static final int PUSHED_GLOBAL_SCREEN;
   public static final int CANCEL;
   public static final int CLOSE;

   public PopupDialog(Manager var1) {
      this(var1, 0);
   }

   public PopupDialog(Manager var1, long var2) {
      super(var1, var2);
      if ((var2 & 167772160) != 0) {
         this.setModal(false);
      }
   }

   public void setModal(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean isModal() {
      return this._modal;
   }

   public void setOwner(UiEngine var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public UiEngine getOwner() {
      return this._owner;
   }

   public void setPopupDialogClosedListener(PopupDialogClosedListener var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public PopupDialogClosedListener getPopupDialogClosedListener() {
      return this._dialogClosedListener;
   }

   public void setStatusPriority(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getStatusPriority() {
      return this._statusPriority;
   }

   public int getCloseReason() {
      return this._closeReason;
   }

   @Override
   protected void onDisplay() {
      if (!this._open) {
         this._open = true;
      }

      super.onDisplay();
   }

   @Override
   protected void onUiEngineAttached(boolean var1) {
      if (var1 && !this._open) {
         Ui.getUiEngine();
         this._open = true;
      }

      super.onUiEngineAttached(var1);
   }

   public void show() {
      if (!this._open) {
         UiEngine var1 = this._owner == null ? Ui.getUiEngine() : this._owner;
         this._open = true;
         if (this._modal) {
            var1.pushModalScreen(this);
         } else if (this.isStyle(33554432)) {
            var1.pushGlobalScreen(this, this._statusPriority, 2);
         } else if (this.isStyle(134217728)) {
            var1.pushGlobalScreen(this, this._statusPriority, 0);
         } else {
            var1.pushScreen(this);
         }
      }
   }

   protected void close(int var1) {
      if (this._open) {
         if (var1 != -1 || this.isCancelAllowed()) {
            UiEngine var2 = this._owner == null ? Ui.getUiEngine() : this._owner;
            this._closeReason = var1;
            var2.popScreen(this);
            if (this._dialogClosedListener != null) {
               this._dialogClosedListener.dialogClosed(this, this._closeReason);
            }

            this._open = false;
         }
      }
   }

   public void setCancelAllowed(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isCancelAllowed() {
      return this._cancelAllowed;
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return super.stylusTap(var1, var2, var3, var4) ? true : this.trackwheelClick(var3, var4);
   }
}
