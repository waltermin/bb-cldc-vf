package net.rim.tid.text;

import net.rim.device.internal.ui.StringBufferGap;

public class AttributedString$Iterator {
   private AttributedString$Run _cur_run;
   private int _total_length;
   private int _run_length;
   private int _pos;
   private int _start;
   private final AttributedString this$0;

   public AttributedString$Iterator(AttributedString _1) {
   }

   public AttributedString$Iterator(AttributedString _1, int aStart, int aEnd) {
      this.this$0 = _1;
      this.set(aStart, aEnd);
   }

   public void set(int aStart, int aEnd) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int pos() {
      return this._pos;
   }

   public int length() {
      return this._total_length;
   }

   public StringBufferGap text() {
      return this.this$0._text;
   }

   public int runLength() {
      return this._run_length;
   }

   public long runAttrib() {
      return this._cur_run._attrib;
   }

   public long runXAttrib() {
      return this._cur_run._xAttrib;
   }

   public AttributedString$PictureInfo runPictureInfo() {
      return this._cur_run._pictureInfo;
   }

   public AttributedString$Picture runPicture() {
      return this._cur_run._pictureInfo == null ? null : this._cur_run._pictureInfo._picture;
   }

   public boolean next() {
      if (this._total_length == 0) {
         return false;
      }

      this._total_length = this._total_length - this._run_length;
      if (this._total_length == 0) {
         this._total_length = this._total_length + this._run_length;
         return false;
      }

      this._pos = this._pos + this._run_length;
      this._cur_run = this._cur_run._next;
      this._run_length = this._cur_run._length;
      if (this._total_length < this._run_length) {
         this._run_length = this._total_length;
      }

      return true;
   }

   public boolean prev() {
      if (this._pos == this._start) {
         return false;
      } else {
         this._cur_run = this._cur_run._prev;
         this._run_length = this._cur_run._length;
         this._pos = this._pos - this._run_length;
         if (this._pos < this._start) {
            int diff = this._start - this._pos;
            this._pos = this._start;
            this._run_length -= diff;
            this._total_length -= diff;
            return true;
         } else {
            this._total_length = this._total_length + this._run_length;
            return true;
         }
      }
   }

   public long getGlobalAttrib() {
      return this.this$0._global_attrib;
   }
}
