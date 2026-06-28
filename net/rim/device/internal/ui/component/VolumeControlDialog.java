package net.rim.device.internal.ui.component;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
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
      synchronized (_lock) {
         if (_vcd == null) {
            _vcd = new VolumeControlDialog();
         }

         return _vcd;
      }
   }

   public void setCallback(Field callback) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setVolume(int volume) {
      this._volume = volume;
      if (this.isDisplayed()) {
         this.makeAudibleFeedback();
      }

      this._volumeGraphics.changeIndex(volume / 10);
   }

   public void setText(String text) {
      if (this._volumeText == null) {
         this._volumeText = new RichTextField(text, 36028797086072832L);
         this._volumeText.setTag(TAG_TEXT);
         ((HorizontalFieldManager)this.getDelegate()).insert(this._volumeText, 0);
      } else {
         this._volumeText.setText(text);
      }
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
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      if (key != 27 && key != '\n') {
         return super.keyChar(key, status, time);
      }

      this.close(0);
      return true;
   }

   @Override
   public int adjustVolume(int volumeLevelChange) {
      this._keyClickTime = InternalServices.getUptime();
      return this._callback != null ? this._callback.adjustVolume(volumeLevelChange) : -1;
   }

   @Override
   protected void sublayout(int width, int height) {
      super.sublayout(width, height);
      this.setPosition(width - this.getDelegate().getExtent().width, 0);
   }
}
