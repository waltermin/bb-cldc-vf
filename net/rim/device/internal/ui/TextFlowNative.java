package net.rim.device.internal.ui;

import net.rim.vm.Array;

public final class TextFlowNative {
   private StringBuffer _text;
   private int[] _regionStartOffsets;
   private int[] _regionEndOffsets;
   private TextFlowRegion[] _regions;
   private short[] _regionFlags;
   private int[] _regionParentIds;
   private short _currentCellId;
   TextFlowNative$Lines _lines;
   private int _endTextPosition;
   private int _totalHeight;
   private int _inlineCount;
   private int[] _totalHeightStack;
   private int _startLine;
   private int _totalHeightTop;
   private int _maxXOffset;
   private int _endLine;
   private int _startYPos;
   private int _lastRegionIndex;

   public TextFlowNative(StringBuffer var1, int[] var2, int[] var3, TextFlowRegion[] var4, short[] var5, int[] var6) {
      this._text = var1;
      this._regionStartOffsets = var2;
      this._regionEndOffsets = var3;
      this._regions = var4;
      this._regionFlags = var5;
      this._regionParentIds = var6;
      this._lines = new TextFlowNative$Lines();
      this._totalHeightStack = new int[16];
      this.reset();
   }

   public final void reset() {
      this._lines.reset();
      this._endTextPosition = -1;
      this._totalHeight = 0;
      this._inlineCount = 0;
      this._currentCellId = 0;
      this._startLine = -1;
      this._totalHeightTop = 0;
      this._endLine = 0;
      this._startYPos = 0;
      this._lastRegionIndex = 0;
      this._maxXOffset = 0;
   }

   public final void appendVerticalSpace(int var1, int var2, int var3, short var4, short var5) {
      if (this._startLine == -1 && var3 > 0) {
         this._startLine = var2;
         this._startYPos = var1;
      }

      this._totalHeight += var3;
      this._lines.appendVerticalSpace(var3, var4, var5, this._currentCellId);
   }

   public final void appendZeroHeightCharacters(int var1, int var2, int var3, short var4, short var5) {
      if (this._startLine == -1 && var3 > 0) {
         this._startLine = var2;
         this._startYPos = var1;
      }

      this._lines.appendZeroHeightCharacters(var3, var4, var5, this._currentCellId);
   }

   public final void appendZeroHeightZeroWidthCharacter(int var1, int var2, short var3) {
      if (this._startLine == -1) {
         this._startLine = var2;
         this._startYPos = var1;
      }

      this._lines.appendZeroHeightZeroWidthCharacter(var3, this._currentCellId);
   }

   public final void pushCell(short var1) {
      if (this._totalHeightTop + 1 >= this._totalHeightStack.length) {
         Array.resize(this._totalHeightStack, this._totalHeightStack.length << 1);
      }

      this._totalHeightTop++;
      this._totalHeightStack[this._totalHeightTop] = this._totalHeight;
      this._currentCellId = var1;
      this._totalHeight = 0;
   }

   public final void popCell(short var1) {
      this._totalHeight = this._totalHeightStack[this._totalHeightTop];
      this._totalHeightStack[this._totalHeightTop] = 0;
      this._totalHeightTop--;
      this._currentCellId = var1;
   }

   public final native void wordWrap(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

   public final int getLineCount() {
      return this._lines.getCount();
   }

   public final int getEndTextPosition() {
      return this._endTextPosition;
   }

   public final int getInlineCount() {
      return this._inlineCount;
   }

   public final int getStartLine() {
      return this._startLine;
   }

   public final int getStartYPos() {
      return this._startYPos;
   }

   public final void setEndLine(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getEndLine() {
      return this._endLine;
   }

   public final void setMaxXOffset(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final int getMaxXOffset() {
      return this._maxXOffset;
   }

   public final int getCellYPos() {
      return this._totalHeightStack[this._totalHeightTop];
   }

   public final int getCellHeight() {
      return this._totalHeight;
   }

   public final TextFlowNative$Lines getLines() {
      return this._lines;
   }

   public final short[] getLineLengths() {
      return this._lines.getLengths();
   }

   public final short[] getLineXOffsets() {
      return this._lines.getXOffsets();
   }

   public final short[] getLineWidth(int var1) {
      return this._lines.getWidths();
   }

   public final short[] getLineWidthsNominal() {
      return this._lines.getWidthsNominal();
   }

   public final byte[] getLineFlags() {
      return this._lines.getFlags();
   }

   public final short[] getLineBaselines() {
      return this._lines.getBaselines();
   }

   public final short[] getLineHeights() {
      return this._lines.getHeights();
   }

   public final short[] getLineCellIds() {
      return this._lines.getCellIds();
   }

   public final byte[] getLineBidiState(int var1) {
      return this._lines.getBidiState(var1);
   }

   public final void append(int var1, TextFlowNative$Lines var2, int var3, int var4) {
      this._lines.append(var2, var3, var4);
      this._totalHeight += var1;
   }
}
