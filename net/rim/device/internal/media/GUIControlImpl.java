package net.rim.device.internal.media;

import javax.microedition.lcdui.Item;
import javax.microedition.media.control.GUIControl;

public class GUIControlImpl implements GUIControl {
   Object _item;
   private boolean _displayModeInitialized;

   public Object getGUIObject() {
      return this._item;
   }

   @Override
   public Object initDisplayMode(int mode, Object arg) {
      throw new RuntimeException("cod2jar: type check");
   }

   public GUIControlImpl(Item item) {
      this._item = item;
   }

   public GUIControlImpl(Object item) {
      this._item = item;
   }
}
