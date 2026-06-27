package net.rim.device.internal.ui;

import net.rim.device.api.ui.XYRect;

public class FormatParams {
   public int _changedTextStart;
   public int _oldLength;
   public int _newLength;
   public int _cursorOffset;
   public boolean _moveCursor;
   public boolean _isBackspace;
   public boolean _isFormatComplete = true;
   int _nextStartPosToFormat;
   int _formatOldLength;
   int _formatNewLength;
   boolean _formatTextUnchanged = false;
   int _linesToFormatCount;
   int _formatFlags;
   public int _layoutWidth = 0;
   public int _lineCount = 1;
   public ArticInterface$Line _lineList;
   public ArticInterface$LineInfo _cursorLineInfo = new ArticInterface$LineInfo();
   public XYRect _invalidRect = (XYRect)(new Object());

   public void init(int var1, int var2, int var3, int var4, boolean var5, ArticInterface$Line var6) {
      this._changedTextStart = var1;
      this._oldLength = var2;
      this._newLength = var3;
      this._cursorOffset = var4;
      this._moveCursor = var5;
      this._lineList = var6;
   }

   public void initCursorLine(ArticInterface$Line var1, int var2, int var3) {
      this._cursorLineInfo._line = var1;
      this._cursorLineInfo._start = var2;
      this._cursorLineInfo._top = var3;
   }

   public int getNextStartPosToFormat() {
      return this._nextStartPosToFormat;
   }

   public int getDelta() {
      return this._newLength - this._oldLength;
   }

   public void computeParamsAfterTextChanged(boolean var1, int var2) {
      if (!this._isFormatComplete && var1) {
         int var3 = this._changedTextStart > this._nextStartPosToFormat ? this._changedTextStart - this._nextStartPosToFormat : 0;
         int var4 = this._nextStartPosToFormat + this._formatOldLength > this._changedTextStart + this._oldLength
            ? this._nextStartPosToFormat + this._formatOldLength - (this._changedTextStart + this._oldLength)
            : 0;
         this._nextStartPosToFormat = this._changedTextStart - var3;
         this._formatOldLength = this._oldLength + var3 + var4;
         this._formatNewLength = this._newLength + var3 + var4;
      } else {
         this._formatOldLength = this._oldLength;
         this._formatNewLength = this._newLength;
         this._nextStartPosToFormat = this._changedTextStart;
      }

      this._formatTextUnchanged = false;
      this._isFormatComplete = false;
      this._linesToFormatCount = var2;
      this._layoutWidth = 0;
   }

   public void setFormatFlags(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }
}
