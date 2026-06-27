package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

class SymbolScreen$Header extends Manager {
   SymbolScreen$Encoding _encoding;
   SymbolScreen$CharacterCodeField _code;
   int _pageWidth;
   TextField _page;
   AttributedString _pageAttributedString;
   AttributedString$Iterator _pageAttributedStringIterator;
   StringBuffer _pageBuffer;
   private final SymbolScreen this$0;

   public SymbolScreen$Header(SymbolScreen var1) {
   }

   public void setHeader(int var1, int var2, boolean var3) {
      this._encoding.setEncoding(var1);
      if (!this._code.setCode(var2) && !var3) {
         this._code.setType(-1);
      }

      this.update();
   }

   public int getEncoding() {
      return this._encoding.getEncoding();
   }

   public int getCode() {
      return this._code.getCode();
   }

   @Override
   protected void sublayout(int var1, int var2) {
      this.layoutChild(this._encoding, var1, var2);
      this._code.setHeight(this._encoding.getHeight());
      this.layoutChild(this._code, this._code.getWidth(), var2);
      this.layoutChild(this._page, this._pageWidth, var2);
      int var3 = this._encoding.getHeight();
      this.setVirtualExtent(var1, var3);
      this.setPositionChild(this._encoding, 0, 0);
      this.setPositionChild(this._code, this._encoding.getWidth() + 2, 0);
      this.setPositionChild(this._page, var1 - this._page.getWidth(), 0);
      this.setExtent(var1, var3);
   }

   public void update() {
      this.sublayout(this.getWidth(), this.getHeight());
      this.invalidate();
   }

   public void setFocusOnCode() {
      this.setFieldWithFocus(this._code);
   }

   @Override
   public int moveFocus(int var1, int var2, int var3) {
      Field var4 = this.getFieldWithFocus();
      if (var4 == this._encoding && var1 < 0) {
         this.this$0._symbols.nextPage(false, true);
         return 0;
      } else if (var4 == this._code && var1 > 0) {
         this.this$0._symbols._focusRow = 0;
         this.this$0._symbols._focusColumn = 0;
         return var1;
      } else {
         return super.moveFocus(var1, var2, var3);
      }
   }

   public void setPageNumber(int var1) {
      if (var1 < 10) {
         this._pageBuffer.setCharAt(0, ' ');
         this._pageBuffer.setCharAt(1, (char)(var1 + 48));
      } else if (var1 < 20) {
         this._pageBuffer.setCharAt(0, (char)(var1 / 10 + 48));
         this._pageBuffer.setCharAt(1, (char)(var1 % 10 + 48));
      } else {
         this._pageBuffer.setCharAt(0, '?');
         this._pageBuffer.setCharAt(1, '?');
      }

      var1 = this.this$0._symbols._pages.length;
      if (var1 < 10) {
         this._pageBuffer.setCharAt(3, (char)(var1 + 48));
         this._pageBuffer.setCharAt(4, ' ');
      } else if (var1 < 20) {
         this._pageBuffer.setCharAt(3, (char)(var1 / 10 + 48));
         this._pageBuffer.setCharAt(4, (char)(var1 % 10 + 48));
      } else {
         this._pageBuffer.setCharAt(3, '?');
         this._pageBuffer.setCharAt(4, '?');
      }

      this._pageAttributedString.replace(0, this._pageAttributedString.length(), this._pageBuffer);
      this._pageAttributedStringIterator.set(0, this._pageAttributedString.length());
      this._page.replace(0, this._page.getTextLength(), this._pageAttributedStringIterator, 0, 0);
   }
}
