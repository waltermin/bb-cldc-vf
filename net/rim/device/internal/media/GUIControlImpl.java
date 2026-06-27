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
   public Object initDisplayMode(int var1, Object var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public GUIControlImpl(Item var1) {
      this._item = var1;
   }

   public GUIControlImpl(Object var1) {
      this._item = var1;
   }
}
