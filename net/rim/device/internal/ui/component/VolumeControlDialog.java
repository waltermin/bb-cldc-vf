package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.internal.system.InternalServices;

public class VolumeControlDialog extends PopupDialog {
   private Field _callback;
   private VolumeControlDialog$VolumeGraphicsField _volumeGraphics;
   private RichTextField _volumeText;
   private long _keyClickTime;
   private VolumeControlDialog$DialogTimer _dismisser;
   private byte[] _feedbackSound;
   private int _volume;
   private static Object _lock;
   private static VolumeControlDialog _vcd;
   private static final int TIMEOUT;
   private static Tag TAG;
   private static Tag TAG_TEXT;

   private VolumeControlDialog() {
   }

   public static VolumeControlDialog getInstance() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void setCallback(Field var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setVolume(int var1) {
      this._volume = var1;
      if (this.isDisplayed()) {
         this.makeAudibleFeedback();
      }

      this._volumeGraphics.changeIndex(var1 / 10);
   }

   public void setText(String var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   @Override
   public void show() {
      if (!this.isDisplayed()) {
         this.makeAudibleFeedback();
         super.show();
         this._keyClickTime = InternalServices.getUptime();
         Application.getApplication().invokeLater(this._dismisser, 2500, false);
      }
   }

   private void makeAudibleFeedback() {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 != 27 && var1 != '\n') {
         return super.keyChar(var1, var2, var3);
      }

      this.close(0);
      return true;
   }

   @Override
   public int adjustVolume(int var1) {
      this._keyClickTime = InternalServices.getUptime();
      return this._callback != null ? this._callback.adjustVolume(var1) : -1;
   }

   @Override
   protected void sublayout(int var1, int var2) {
      super.sublayout(var1, var2);
      this.setPosition(var1 - this.getDelegate().getExtent().width, 0);
   }
}
