package net.rim.tid.text;

final class AttributedString$Run {
   long _attrib;
   long _xAttrib;
   AttributedString$Run _prev;
   AttributedString$Run _next;
   int _length;
   AttributedString$PictureInfo _pictureInfo;

   AttributedString$Run() {
   }

   AttributedString$Run(AttributedString$Run var1, AttributedString$Run var2, int var3, long var4, long var6, AttributedString$PictureInfo var8) {
      this._prev = var1;
      this._next = var2;
      this._length = var3;
      this._attrib = var4;
      this._xAttrib = var6;
      this._pictureInfo = var8;
   }
}
