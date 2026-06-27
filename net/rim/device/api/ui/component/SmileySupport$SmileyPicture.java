package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.tid.text.AttributedString$Picture;
import net.rim.tid.text.AttributedString$PictureInfo;

class SmileySupport$SmileyPicture implements AttributedString$Picture {
   private int _id;
   private AttributedString$PictureInfo _info;
   private final SmileySupport this$0;

   public int getId() {
      return this._id;
   }

   @Override
   public void draw(Graphics var1, int var2, int var3) {
      this.this$0._smileyFacility.drawEmoticon(var1, this._id, var2, var3);
   }

   @Override
   public AttributedString$PictureInfo getInfo() {
      return this._info;
   }

   SmileySupport$SmileyPicture(SmileySupport var1, int var2) {
      this.this$0 = var1;
      this._id = var2;
      this._info = (AttributedString$PictureInfo)(new Object(this));
      this._info._width = SmileySupport._size;
      this._info._height = SmileySupport._size;
      this._info._advance = SmileySupport._size + 1;
      int var3 = SmileySupport._size >= 20 ? 3 : (SmileySupport._size >= 10 ? 2 : 1);
      this._info._y = -SmileySupport._size + var3;
   }
}
