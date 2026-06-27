package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.AccessibleEventDispatcher;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.DrawTextParam;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.TextChangeListener;
import net.rim.device.api.ui.Trackball;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.text.TextFilter;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.AbstractString;
import net.rim.device.api.util.AbstractStringWrapper;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.StringProvider;
import net.rim.device.internal.ui.ArticInterface;
import net.rim.device.internal.ui.ArticInterface$LayoutRun;
import net.rim.device.internal.ui.ArticInterface$Line;
import net.rim.device.internal.ui.ArticInterface$LineInfo;
import net.rim.device.internal.ui.Cursor;
import net.rim.device.internal.ui.FormatParams;
import net.rim.device.internal.ui.Formatter;
import net.rim.device.internal.ui.Formatter$TextRenderer;
import net.rim.device.internal.ui.StringBufferGap;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.InputMethodRequests;
import net.rim.tid.im.SLControlObject;
import net.rim.tid.itie.EventHandler;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;
import net.rim.tid.text.AttributedString$Picture;
import net.rim.tid.text.AttributedTextIterator;
import net.rim.tid.text.BreakIterator;
import net.rim.tid.text.TextHitInfo;
import net.rim.vm.Memory;
import net.rim.vm.TraceBack;
import net.rim.vm.WeakReference;

public class TextField extends Field implements InputMethodRequests, FieldLabelProvider, Formatter$TextRenderer, AccessibleText {
   private byte _caretShape;
   private boolean _selecting;
   private int _cursor;
   private boolean _cursorLeadingEdge;
   private boolean _cursorIsAtBidiBorder;
   private int _anchor;
   private ArticInterface$Line _lineList;
   private int _lineCount;
   private ArticInterface$Line _cursorLine;
   private int _cursorLineStart;
   private int _cursorLineTop;
   private boolean _isDefaultR2L;
   private int _preferredXCoord;
   private boolean _inMoveFocus;
   AttributedString _text;
   private Object _imCookie;
   private int _width;
   private int _maxNumChars;
   private TextFilter _filter;
   private int _themeGeneration;
   private Font _font;
   private int _composedStart;
   private int _composedEnd;
   private long _composedTextAttributeMask;
   private int _convertedCharactersCount;
   private int _latestCommittedStart;
   private int _latestCommittedEnd;
   boolean _isLabelOwnLine;
   private int _labelLength;
   private int _lastLabelLineTop;
   private String _labelSet;
   private XYRect _tempRect;
   private TextHitInfo _tempDocPosInfo;
   private ArticInterface$LineInfo _tempLineInfo;
   private AttributedString$Iterator _iterator;
   private long _lastFieldFull;
   private Runnable _fieldFullMsgInvoker;
   private boolean _pasteable;
   private boolean _cursorPositionSet;
   private AbstractStringWrapper _stringData;
   private boolean _notifyingTextChanged;
   private int _focusOffset;
   private int _focus_x;
   private int _focus_y;
   private int _focus_width;
   private int _focus_height;
   private boolean _isPreLayout;
   private boolean _inLayout;
   private int _formatThreadId;
   private FormatParams _formatParams;
   private int _lastFormatLength;
   protected long _insertionAttrib;
   protected long _insertionXAttrib;
   private boolean _composed_highlighted;
   private Object[] _textChangeListeners;
   private boolean _navigationClickHandled;
   private boolean _isAutoSelectModeOn;
   private TextField$ConvertedString _convertedString;
   private Screen _dependentScreen;
   private boolean _isUnicodeInputAllowed;
   private static Tag TAG_EDIT;
   private static final boolean EDIT_DEBUG;
   public static final int DEFAULT_MAXCHARS;
   public static final long IM_NO_PREDICTION;
   public static final long IM_PROVIDE_SUPPLEMENTARY_INPUT;
   public static final long IM_USE_ADDR_BOOK;
   public static final long CONSUME_INPUT;
   public static final long NO_IM_SIDE_CONVERSION;
   public static final int JUMP_FOCUS_AT_END;
   public static final long NO_KEY_DOWN_PROCESS;
   public static final long NO_LEARNING;
   public static final long NO_AUTOSPACE;
   public static final long NO_LOOKUP;
   public static final long NO_COMPLEX_INPUT;
   public static final long NO_NEWLINE;
   private static MenuItem _clearFieldItem;
   private static MenuItem _showSymbolsItem;
   private static WeakReference _toggleInputItem;
   protected static final long DEFAULT_FONT_ATTRIB_MASK;
   protected static final long FONT_ATTRIB_MASK;
   private static final int MAX_LINES_TO_FORMAT;
   static final long COOKIES_ATTRIB_MASK;
   static final int COOKIES_ATTRIB_SHIFT;

   public boolean isUnicodeInputAllowed() {
      return this._isUnicodeInputAllowed;
   }

   public void setAllowUnicodeInput(boolean var1) {
      if (this._isUnicodeInputAllowed != var1) {
         this._isUnicodeInputAllowed = var1;
         if (this._labelSet != null) {
            this.setLabel(this._labelSet);
         }
      }
   }

   protected void findDefaultDirectionality(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   TextFilter getFilterFromStyle(long var1) {
      return null;
   }

   protected void updateInputStyle() {
      Object var1 = this.getInputContext().getInputMethodControlObject();
      ((SLControlObject)var1).actionPerformed(38, null);
      int var2 = (int)this.getStyle();
      if (this._filter != null) {
         var2 = (int)(var2 | this._filter.getPreferredInputStyle());
      }

      ((SLControlObject)var1).setTextInputStyle(var2);
      ((SLControlObject)var1).setFilter(this._filter);
   }

   public int getTextInputStyle() {
      int var1 = (int)this.getStyle();
      if (this._filter != null) {
         var1 = (int)(var1 | this._filter.getPreferredInputStyle());
      }

      return var1;
   }

   final void fireInputMethodTextChanged(InputMethodEvent var1) {
      Object[] var2 = this._textChangeListeners;
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            ((TextChangeListener)var2[var3]).inputMethodTextChanged(this, var1);
         }
      }
   }

   public int getConvertedCharactersCount() {
      return this._convertedCharactersCount;
   }

   final void fireTextChangeEvent(int var1) {
      Object[] var2 = this._textChangeListeners;
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            ((TextChangeListener)var2[var3]).textValueChanged(this, var1);
         }
      }
   }

   protected synchronized void hitTest(int var1, XYRect var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final synchronized void removeTextChangeListener(TextChangeListener var1) {
      this._textChangeListeners = ListenerUtilities.removeListener(this._textChangeListeners, var1);
   }

   public final synchronized void addTextChangeListener(TextChangeListener var1) {
      this._textChangeListeners = ListenerUtilities.addListener(this._textChangeListeners, var1);
   }

   public void setAdjustAlignments(boolean var1) {
   }

   protected void endLayoutUpdate() {
      if (!this._inLayout) {
         this.updateLayoutNowOrLater();
      }
   }

   protected void startLayoutUpdate() {
      if (!this._inLayout) {
         this._isPreLayout = true;
      }
   }

   public void setPreLayoutInternal(boolean var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(2));
      this._isPreLayout = var1;
   }

   protected synchronized int replace(int var1, int var2, AttributedString$Iterator var3, long var4, long var6, int var8, int var9, boolean var10, int var11) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int replace(int var1, int var2, AttributedString$Iterator var3, long var4, long var6) {
      return this.replace(var1, var2, var3, var4, var6, var3.length(), 0, true, 0);
   }

   public void insert(int var1, AttributedString$Iterator var2, long var3, long var5) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void handleCursorPositionChanged() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   protected void invalidateFocusRect() {
      this._focusOffset = -1;
   }

   public void setAttrib(int var1, int var2, long var3, long var5, long var7, long var9) {
      int var11 = var1;
      int var12 = var2 - var1;
      if (var12 < 0) {
         var11 = var2;
         var12 = -var12;
      }

      this._text.seek(var11);
      this._text.setAttrib(var12, var3, var5, var7, var9);
      this.update(var11, var12, var12, 0, false, false);
      if (this._cursor >= var1 && this._cursor <= var2) {
         this.setInsertionAttributesToSelection();
      }
   }

   public int getComposedTextLength() {
      return this._composedEnd - this._composedStart;
   }

   public void deleteAttrib(long var1) {
      this.setAttrib(this._cursor, this._anchor, 0, var1, 0, 0);
   }

   public void setAttrib(long var1, long var3) {
      this.setAttrib(this._cursor, this._anchor, var1, var3, 0, 0);
   }

   public void setFontHeight(int var1) {
      this.setAttrib(this._cursor, this._anchor, var1 << 0, 63, 0, 0);
   }

   protected synchronized int backspace(int var1, int var2) {
      if (this._cursor - var1 < this._labelLength) {
         var1 = this._cursor - this._labelLength;
      }

      if ((var2 & -2147483648) == 0 && !this.isEditable()) {
         return 0;
      }

      if (var1 == 0) {
         return 0;
      }

      if (this._cursor < var1) {
         var1 -= this._cursor;
      }

      String var3 = this._text.getText(this._cursor - var1, this._cursor);
      this._text.delete(this._cursor - var1, this._cursor);
      this.update(this._cursor - var1, var1, 0, 0, true, true);
      if (Ui.isTTSEnabled()) {
         this.accessibleEventOccurred(2, var3, null, this);
      }

      this.fieldChangeNotify(var2);
      return var1;
   }

   protected synchronized boolean backspace() {
      boolean var1 = false;
      if (this.isSelecting()) {
         var1 = this.deleteSelectedText() != 0;
      } else if (this._cursor != this._labelLength) {
         String var2 = this._text.getText(this._cursor - 1, this._cursor);
         this._text.delete(this._cursor - 1, this._cursor);
         this.update(this._cursor - 1, 1, 0, 0, true, true);
         if (Ui.isTTSEnabled()) {
            this.accessibleEventOccurred(2, var2, null, this);
         }

         this.fieldChangeNotify(0);
         var1 = true;
      }

      if (this._latestCommittedEnd != this._latestCommittedStart && this._latestCommittedEnd > this._cursor) {
         this.resetLatestCommittedText();
      }

      return var1;
   }

   public Screen dependentScreen() {
      return this._dependentScreen;
   }

   public int backspace(int var1) {
      return this.backspace(var1, Integer.MIN_VALUE);
   }

   public char charAt(int var1) {
      if (var1 < 0) {
         throw new Object();
      } else {
         return this._text.charAt(var1 + this._labelLength);
      }
   }

   public void clear(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void wipe() {
      if (Memory.getSecureOldObjects()) {
         this._text.getText().wipe();
      }
   }

   protected char convert(char var1, int var2) {
      if (this._filter != null) {
         var1 = this._filter.convert(var1, this.getTextAbstractString(), this.getCursorPosition(), var2);
      }

      return var1;
   }

   public boolean isInMoveFocus() {
      return this._inMoveFocus;
   }

   public int getMaxSize() {
      return this._maxNumChars;
   }

   public synchronized String getText() {
      return this._text.getText(this._labelLength, this.getDisplayTextLength());
   }

   public synchronized String getText(int var1, int var2) {
      return this._text.getText(var1, var1 + var2);
   }

   public synchronized void getText(int var1, int var2, char[] var3, int var4) {
      this._text.getText().getText(var1 + this._labelLength, var2 + this._labelLength, var3, var4);
   }

   public AbstractString getTextAbstractString() {
      return this._text.getText().getAbstractString();
   }

   public int getTextLength() {
      return this.getDisplayTextLength() - this._labelLength;
   }

   public int getDecodedTextLength() {
      return this.getDecodedTextLength(this.getLabelLength(), this._text.length());
   }

   public int getDecodedTextLength(int var1, int var2) {
      return var2 - var1;
   }

   public int insert(String var1) {
      return this.insert(var1, Integer.MIN_VALUE, true, true);
   }

   public void insert(AttributedString$Picture var1) {
      this.insert(null, var1, Integer.MIN_VALUE, false, false);
   }

   protected int insert(String var1, int var2) {
      return this.insert(var1, null, var2, true, true);
   }

   public int insert(String var1, int var2, boolean var3, boolean var4) {
      return this.insert(var1, null, var2, var3, var4);
   }

   public synchronized int insert(String var1, AttributedString$Picture var2, int var3, boolean var4, boolean var5) {
      throw new RuntimeException("cod2jar: ldc");
   }

   int getLayoutWidth(int var1) {
      return var1;
   }

   void handleLinesAfterFormat(FormatParams var1) {
      this._lineCount = var1._lineCount;
   }

   protected int getMaxLinesToFormat() {
      return Math.max(20, Display.getHeight() / this.getFont().getHeight() + 1);
   }

   public void inMoveFocus(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void setCursorPositionSet(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public boolean isFieldFull() {
      return this.getDecodedTextLength() >= this._maxNumChars;
   }

   protected void update(int var1) {
   }

   protected void notifyTextChanged(FormatParams var1, boolean var2) {
   }

   public synchronized void setMaxSize(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public void setCursorPosition(int var1) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public int getCursorPosition() {
      return this._cursor - this._labelLength;
   }

   public void setFilter(TextFilter var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public TextFilter getFilter() {
      return this._filter;
   }

   public void setText(String var1) {
      this.setText(var1, Integer.MIN_VALUE);
   }

   protected void setText(String var1, int var2) {
      this.setTextInternal(var1, var2, true);
   }

   public void setAttributedText(AttributedString var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected boolean validate(char var1) {
      if ((var1 > 31 || var1 == '\n') && (127 > var1 || var1 > 159)) {
         return this._filter != null ? this._filter.validate(var1, this.getTextAbstractString(), this.getCursorPosition()) : true;
      } else {
         return false;
      }
   }

   protected boolean validate(String var1) {
      if (this._filter != null) {
         this._stringData.reset(var1);
         return this._filter.validate(this._stringData);
      } else {
         return true;
      }
   }

   protected int getLineTop(int var1) {
      int var2 = 0;
      if (var1 >= 0 && var1 <= this._lineCount) {
         int var3 = 0;

         for (ArticInterface$Line var4 = this._lineList; var4 != null && var3 < var1; var3++) {
            var2 += var4._boundsBottom - var4._boundsTop;
            var4 = var4._next;
         }

         return var2;
      } else {
         throw new Object();
      }
   }

   protected int getLineTop(ArticInterface$Line var1) {
      int var2 = 0;

      for (ArticInterface$Line var3 = this._lineList; var3 != var1; var3 = var3._next) {
         var2 += var3._boundsBottom - var3._boundsTop;
      }

      return var2;
   }

   protected int getDisplayLinePosition(int var1) {
      return this.getLineTop(var1);
   }

   public ArticInterface$LineInfo getLineInfoForYPos(int var1) {
      this._tempLineInfo._start = this._cursorLineStart;
      this._tempLineInfo._top = this._cursorLineTop;
      this._tempLineInfo._line = this._cursorLine;
      Formatter.getLineInfoForYPos(var1, this._tempLineInfo);
      return this._tempLineInfo;
   }

   public ArticInterface$LineInfo getLineInfoForDocPos(int var1, boolean var2) {
      this._tempLineInfo._line = this._cursorLine;
      this._tempLineInfo._start = this._cursorLineStart;
      this._tempLineInfo._top = this._cursorLineTop;
      Formatter.getLineInfoForDocPos(var1, var2, this._lineList, this._tempLineInfo, true);
      return this._tempLineInfo;
   }

   public void setSelection(int var1, boolean var2, int var3) {
      this.setSelection(var1, var2, var3, true);
   }

   protected synchronized int scrollHorizontally(int var1) {
      int var2 = this.getLabelLength();
      if ((this._cursorLine._flags & 16) != 0) {
         var1 = -var1;
      }

      if ((this._cursor != var2 || var1 >= 0) && (this._cursor != this.getDisplayTextLength() || var1 <= 0)) {
         this._tempDocPosInfo.set(this._cursor, this._cursorLeadingEdge);
         int var3 = ArticInterface.AdjustDocPos(this._text.getText(), this._tempDocPosInfo, var1);
         int var4 = this._tempDocPosInfo._index;
         int var5 = var1 - var3;
         if (var4 < var2) {
            var5 += var4 - var2;
            var4 = var2;
         } else if (var4 < this.getDisplayTextLength()) {
            int var6 = 0;
            this.getLineInfoForDocPos(var4, this._cursorLeadingEdge);
            ArticInterface$Line var7 = this._tempLineInfo._line;
            int var8 = this._tempLineInfo._start;
            int var9 = this.getRightMargin();
            if (var1 < 0) {
               while (true) {
                  var6 = this.getCaretX(var4, this._cursorLeadingEdge);
                  if (var4 == var8 || var6 <= var9) {
                     break;
                  }

                  var4--;
               }
            } else {
               var6 = this.getCaretX(var4, this._cursorLeadingEdge);
               if (var6 > var9) {
                  if (var7._next == null) {
                     while (true) {
                        var6 = this.getCaretX(var4, this._cursorLeadingEdge);
                        if (var4 == var8) {
                           return 1;
                        }

                        if (var6 <= var9) {
                           return 1;
                        }

                        var4--;
                     }
                  }

                  var4 = var8 + this.getLineLength(var7);
                  boolean var11 = false;
               }
            }
         }

         boolean var14 = true;
         if (this.posIsAtBidiBorder(var4, false)) {
            var14 = var1 < 0;
         }

         if (this._selecting) {
            this.setSelection(var4, var14, this._anchor, true);
         } else {
            this.setSelection(var4, var14, var4, true);
         }

         return var5;
      } else {
         return this._selecting ? 0 : var1;
      }
   }

   protected synchronized int scrollVertically(int var1) {
      if (this.isComposedTextExist()) {
         return 0;
      }

      int var2 = this.getLabelLength();
      ArticInterface$LineInfo var3 = this.getCursorLine();
      int var4 = var3._top;
      ArticInterface$Line var5 = var3._line;
      int var6 = var4;
      int var7 = var3._start;
      boolean var8 = false;
      int var9 = var1;
      if (var1 >= 0) {
         for (int var10 = 0; var10 < var1 && var5._next != null; var10++) {
            var6 += var5._boundsBottom - var5._boundsTop;
            var5 = var5._next;
            var9--;
         }

         var8 = var9 > 0;
      } else {
         for (int var12 = var1; var12 < 0 && var6 > this._lastLabelLineTop && var7 > var2; var12++) {
            var5 = var5._prev;
            var6 -= var5._boundsBottom - var5._boundsTop;
            var9++;
            var7 -= var5._textLength + var5._skippedCharacters;
         }
      }

      while (var6 < this._lastLabelLineTop) {
         if (var5 == null) {
            throw new Object();
         }

         var6 += var5._boundsBottom - var5._boundsTop;
         var5 = var5._next;
      }

      int var13 = 0;
      if (var1 < 0 && (var9 < 0 || this._cursor == var2) && var5 != null) {
         this.setPreferredXToLineStartOrEnd(var5, true);
         if (!this._selecting) {
            var13 = var9;
         }
      }

      if (var8) {
         this.setPreferredXToLineStartOrEnd(var5, false);
         if (!this._selecting && ((this.getStyle() & 4096) == 0 || this._cursor == this.getDisplayTextLength())) {
            var13 = var9;
         }
      }

      if (this._preferredXCoord == -1) {
         this._preferredXCoord = this.getCaretX(this._cursor, this._cursorLeadingEdge);
      }

      this.getDocPos(this._preferredXCoord, var6, this._tempDocPosInfo);
      int var11 = Math.max(this._tempDocPosInfo._index, var2);
      if (this._selecting) {
         this.setSelection(var11, this._tempDocPosInfo._leadingEdge, this._anchor, false);
      } else {
         this.setSelection(var11, this._tempDocPosInfo._leadingEdge, var11, false);
      }

      return var13;
   }

   protected void setFormatFlags(int var1) {
      this._formatParams.setFormatFlags(var1);
   }

   protected void setDefaultInsertionAttributes() {
      this.setFontInsertionAttributes(this.getDefaultFontAttributes());
   }

   protected long getDefaultFontAttributes() {
      long var1 = Ui.getAttributesFromFont(this.getFont()) | 536870912 | Ui.DEFAULT_COLOR_ATTRIBS;
      if (this._isDefaultR2L) {
         var1 |= 16777216;
      }

      return var1;
   }

   synchronized void getCaretRect(int var1, boolean var2, XYRect var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void getDocPos(int var1, int var2, TextHitInfo var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void highlightSelectedArea(Graphics var1, boolean var2, int var3, int var4) {
      XYRect var5 = this.getContentRect();
      XYRect var6 = var1.getClippingRect();
      if (var5.width >= var6.x && 0 <= var6.X2() && var5.height >= var6.y && 0 <= var6.Y2()) {
         if (var3 > var4) {
            int var7 = var3;
            var3 = var4;
            var4 = var7;
         }

         ArticInterface$LineInfo var18 = this.getLineInfoForYPos(var6.y + var6.height);
         int var8 = var18._start + var18._line._textLength + var18._line._skippedCharacters;
         int var9 = Math.min(var4 - 1, var8);
         this.hitTest(var9, this._tempRect);
         int var10 = this._tempRect.y;
         var18 = this.getLineInfoForYPos(var6.y);
         int var11 = var18._start;
         var3 = Math.max(var3, var11);
         if (var3 != var11) {
            var18 = this.getLineInfoForDocPos(var3, true);
         }

         ArticInterface$Line var12 = var18._line;
         int var13 = var18._top;
         int var14 = var18._start;

         while (var13 <= var10 && var12 != null) {
            int var15 = var14 <= var3 ? var3 : var14;
            int var16 = var14 + var12._textLength > var4 ? var4 : var14 + var12._textLength;
            Formatter.getTextBounds(var15, var16, this._tempRect, var12, var14, var13);
            this.drawHighlightRegion(var1, 2, var2, this._tempRect.x, this._tempRect.y, this._tempRect.width, this._tempRect.height);
            var13 += this._tempRect.height;
            var14 += var12._textLength + var12._skippedCharacters;
            var12 = var12._next;
         }
      }
   }

   public void autoSelectFullText() {
      UiApplication.getUiApplication().invokeLater(new TextField$4(this));
   }

   public void setPasteable(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected boolean insert(char var1, int var2) {
      var1 = this.convert(var1, var2);
      if (!this.validate(var1)) {
         return false;
      }

      if (this.isSelecting()) {
         this.selectionDelete();
      }

      if (this.isFieldFull()) {
         this.displayFieldFullMessage();
         return false;
      }

      if (this.validate(var1)) {
         this._text.insert(this._cursor, var1);
         if (this._latestCommittedEnd - this._latestCommittedStart > 0 && this._latestCommittedEnd > this._cursor) {
            this.resetLatestCommittedText();
         }

         this.update(this._cursor, 0, 1, 1, true, true);
         if (Ui.isTTSEnabled()) {
            this.accessibleEventOccurred(2, null, String.valueOf(var1), this);
         }

         this.fieldChangeNotify(0);
         return true;
      } else {
         return false;
      }
   }

   protected boolean isClearMenuItemAllowed() {
      return this.getTextLength() != 0;
   }

   protected boolean isSymbolScreenAllowed() {
      return this.isEditable() && !SymbolScreen.getSymbolScreen().isEmpty(this);
   }

   protected ArticInterface$LineInfo getCursorLine() {
      this.getLineInfoForDocPos(this._cursor, this._cursorLeadingEdge);
      return this._tempLineInfo;
   }

   protected void displayFieldFullMessage() {
      if (this._lastFieldFull + 2000 < System.currentTimeMillis()) {
         this._lastFieldFull = System.currentTimeMillis();
         Screen var1 = this.getScreen();
         if (var1 != null && this._fieldFullMsgInvoker == null) {
            this._fieldFullMsgInvoker = new TextField$5(this, var1);
            Application.getApplication().invokeLater(this._fieldFullMsgInvoker);
         }
      }
   }

   protected int getDisplayLineCount() {
      return this._lineCount;
   }

   protected StringBufferGap getDisplayText() {
      return this._text.getText();
   }

   protected int getDisplayTextLength() {
      return this._text.length();
   }

   public void setCaretPosition(int var1) {
      this.setCursorPosition(var1 - this._labelLength, 0);
   }

   protected void setCursorPosition(int var1, int var2) {
      var1 += this._labelLength;
      if (var1 >= this._labelLength && this.getDisplayTextLength() >= var1) {
         this._text.getText().seek(var1);
         this.setCursorPosition(var1, true, var2);
      } else {
         throw new Object();
      }
   }

   protected boolean isCursorPositionSet() {
      return this._cursorPositionSet;
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void setLabel(String var1) {
      this.resetComposedText();
      int var2 = this._labelLength;
      var1 = this.handleLabelOwnLine(var1);
      Object var3 = new Object(var1, this.getDefaultFontAttributes(), 0);
      AttributedString$Iterator var4 = ((AttributedString)var3).getIterator();
      this.replace(0, var2, var4, -1, -1, var4.length(), 0, true, Integer.MIN_VALUE);
      if (!this._isPreLayout) {
         this.layoutLabel();
      }

      this._text.getText().setLabelLength(this._labelLength);
   }

   @Override
   public String getLabel() {
      return this._labelSet;
   }

   @Override
   public AttributedTextIterator getCommittedText(int var1, int var2, String[] var3) {
      return null;
   }

   @Override
   public AttributedTextIterator getText(int var1, int var2, boolean var3) {
      return null;
   }

   @Override
   public int drawText(Graphics var1, int var2, int var3, int var4, int var5, DrawTextParam var6) {
      var1.drawText(this._text.getText(), var2, var3, var4, var5, var6, null);
      return 0;
   }

   @Override
   public int getSelectionStart() {
      return this._anchor;
   }

   @Override
   public int getSelectionEnd() {
      return this._cursor;
   }

   @Override
   public int getSelectionOffset() {
      return Math.min(this._anchor, this._cursor);
   }

   @Override
   public AttributedTextIterator getSelectedText() {
      return null;
   }

   @Override
   public AttributedTextIterator cancelLatestCommittedText() {
      return null;
   }

   @Override
   public int getCommittedTextLength() {
      return this._text.length() - (this._composedEnd - this._composedStart) - this._labelLength;
   }

   @Override
   public int getInsertPositionOffset() {
      return this.getComposedTextStart();
   }

   @Override
   public TextHitInfo getLocationOffset(int var1, int var2) {
      return null;
   }

   @Override
   public int getLabelLength() {
      return this._labelLength;
   }

   @Override
   public AttributedString getAttributedText() {
      return this._text;
   }

   @Override
   public int getLatestCommittedTextEnd() {
      return this._latestCommittedEnd;
   }

   @Override
   public int getLatestCommittedTextStart() {
      return this._latestCommittedStart;
   }

   @Override
   public int getAnchorPosition() {
      return this._anchor;
   }

   @Override
   public int getCaretPosition() {
      return this._cursor;
   }

   @Override
   public int getComposedTextEnd() {
      return this._composedEnd == this._composedStart ? this._cursor : this._composedEnd;
   }

   @Override
   public int getComposedTextStart() {
      return this._composedEnd == this._composedStart ? this._cursor : this._composedStart;
   }

   @Override
   public void setComposedText(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this._text.length()) {
         this._composedStart = var1;
         this._composedEnd = var2;
         if (this._cursor < var1 || this._cursor > var2 + 1) {
            this.setCaretPosition(this._composedStart);
         }
      } else {
         throw new Object();
      }
   }

   @Override
   public void getTextLocation(TextHitInfo var1, XYRect var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public Object getIMCookieCache() {
      Object var1 = this._imCookie;
      this._imCookie = null;
      return var1;
   }

   @Override
   public String getAfterIndex(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public String getAtIndex(int var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   public String getBeforeIndex(int var1, int var2) {
      int var3 = 0;
      int var4 = 0;
      String var5 = this._text.toString();
      switch (var1) {
         case -1:
            if (this.getDisplayLineCount() > 1) {
               int var9 = this.getLineInfoForDocPos(var2, false)._start;
               if (--var9 >= 0) {
                  ArticInterface$Line var11 = this.getLineInfoForDocPos(var9, false)._line;
                  var3 = var11 == null ? 0 : this.getLineInfoForDocPos(var9, false)._start;
                  var4 = var11 == null ? 0 : var3 + var11._textLength;
               }
            }
            break;
         case 0:
         default:
            if (var2 != 0) {
               var3 = var2 - 1;
               var4 = var2;
            }
            break;
         case 1:
            BreakIterator var6 = BreakIterator.getInstance(1, Locale.getDefault());
            var6.setText(var5);
            int var7 = var6.preceding(var2 + 1);
            if (var7 != Integer.MAX_VALUE) {
               int var8 = var6.preceding(var7 - 1);
               var3 = var8 == Integer.MAX_VALUE ? 0 : var8;
               var4 = var7;
            }
      }

      return this._text.getText(var3, var4);
   }

   @Override
   protected boolean navigationUnclick(int var1, int var2) {
      if (this._navigationClickHandled) {
         this._navigationClickHandled = false;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getPreferredHeight() {
      int var1 = this.getFont().getHeight();
      if (this._isLabelOwnLine) {
         var1 *= 2;
      }

      return var1;
   }

   private synchronized void setAutoSelectFullText() {
      if (this.isEditable() && this.getTextLength() > 0) {
         this._isAutoSelectModeOn = true;
         this.setCursorPosition(0, 0);
         this.select(true);
         this.setCursorPosition(this.getTextLength(), 0);
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      int var4 = 0;
      int var5 = this._cursor;
      if (this._selecting) {
         if ((var2 & 1) == 0 && (!Trackball.isSupported() || (var2 & 65536) == 0)) {
            if (0 == (var2 & 2)) {
               this.scrollVertically(var1);
            } else if (Trackball.isSupported() && (var2 & 65536) == 0) {
               this.scrollVertically(var1);
            } else {
               this.scrollHorizontally(var1);
            }
         } else {
            this.scrollHorizontally(var1);
         }

         if (this._isAutoSelectModeOn && 0 == (var2 & 2)) {
            this.select(false);
         }
      } else if (0 != (var2 & 2)) {
         if (this.isSelectable()) {
            this.select(true);
         }
      } else if ((var2 & 1) == 0 && (!Trackball.isSupported() || (var2 & 65536) == 0)) {
         var4 = this.scrollVertically(var1);
      } else {
         var4 = this.scrollHorizontally(var1);
      }

      this._text.getText().seek(this._cursor);
      if (var1 != var4 && Ui.isTTSEnabled()) {
         AccessibleEventDispatcher.dispatchAccessibleEvent(3, new Object(var5), new Object(this._cursor), this);
      }

      return var4;
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      var1 = MathUtilities.clamp(0, var1, this.getRightMargin() - 1);
      var2 = MathUtilities.clamp(0, var2, this.getBottomMargin() - 1);
      ArticInterface$LineInfo var5 = this.getLineInfoForYPos(var2);
      int var6 = var5._top;
      int var7 = 0;
      ArticInterface$Line var8 = this._cursorLine;
      int var9 = this._cursorLineTop;
      if (var6 < var9) {
         while (true) {
            if (var6 >= var9 || var8._prev == null) {
               if (var9 != var2 && var8._next != null && (var3 & 512) != 0) {
                  var7++;
               }
               break;
            }

            var8 = var8._prev;
            var9 -= var8._boundsBottom - var8._boundsTop;
            var7--;
         }
      } else {
         while (var8 != null && var6 >= var9 + var8._boundsBottom - var8._boundsTop) {
            var9 += var8._boundsBottom - var8._boundsTop;
            var8 = var8._next;
            var7++;
         }

         if (var9 != var2 && var8 != null && var8._next != null && (var3 & 512) != 0) {
            var7++;
         }
      }

      this.moveFocus(var7, var3, var4);
      this._text.getText().seek(this._cursor);
   }

   @Override
   protected void onFocus(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isSelecting() {
      return this._selecting;
   }

   @Override
   public boolean isSelectable() {
      return this.getTextLength() != 0;
   }

   @Override
   public void select(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean isSelectionCopyable() {
      return this._selecting;
   }

   @Override
   public boolean isSelectionDeleteable() {
      return this._cursor != this._anchor && this.isEditable();
   }

   @Override
   public boolean isPasteable() {
      return this._pasteable && this.isEditable();
   }

   @Override
   public int getPreferredWidth() {
      return this.isEditable() ? 536870911 : 10;
   }

   @Override
   public synchronized void getFocusRect(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void getFocusRectPhantom(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public Cursor getFocusCursor() {
      return Cursor.getPredefinedCursor(2);
   }

   private synchronized int getLastSpaceWidth() {
      char var1 = ' ';
      int var3 = this._text.length();
      if (var3 == 0) {
         Font var5 = this.getFont();
         return var5.getBounds(var1);
      } else {
         this._iterator.set(var3 - 1, var3);
         Font var2 = Ui.getFontFromAttributes(this._iterator.runAttrib(), this.getFont());
         this.getCaretRect(var3, false, this._tempRect);
         ArticInterface$Line var4 = this.getLineInfoForDocPos(var3, false)._line;
         int var10000 = var2.getBounds(var1);
         return this._tempRect.x <= var4._boundsLeft ? var10000 + (var4._boundsLeft - this._tempRect.x) : var10000 + (this._tempRect.x - var4._boundsRight);
      }
   }

   private static long validateStyle(long var0) {
      if ((var0 & 13510798882111488L) == 0) {
         var0 |= 4503599627370496L;
      }

      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }

   private void handleIMReset() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public boolean paste(Clipboard var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private void setPreferredXToLineStartOrEnd(ArticInterface$Line var1, boolean var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private void setFontInsertionAttributes(long var1) {
      this._insertionAttrib = var1;
      this._text.setInsertAttrib(this._insertionAttrib);
   }

   private String convert(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void layout(int var1, int var2) {
      if (var1 >= 0 && var2 >= 0) {
         Font var3 = this.getFont();
         int var4 = var1 == Display.getWidth() ? var1 - 1 : var1;
         if (this._font != var3 || ThemeManager.getGeneration() != this._themeGeneration || this._width != var4) {
            this._themeGeneration = ThemeManager.getGeneration();
            this._isPreLayout = true;
            this._width = var4;
         }

         if (this._isPreLayout) {
            this._inLayout = true;
            this.setFontInternal(var3);
            this._isPreLayout = false;
            this.update(0, this._lastFormatLength, this._text.length(), this._text.length(), false, true);
            this._inLayout = false;
            this.layoutLabel();
         }

         int var5 = this.getLineTop(this._lineCount);
         var4 = this.getLayoutWidth(var1);
         this.setExtent(var4, var5);
      } else {
         throw new Object();
      }
   }

   @Override
   protected synchronized void paint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      DrawTextParam var3 = Ui.getTmpDrawTextParam();
      var3.iAlignment = 8;
      var3.iDrawNonPrintableCharacters = false;
      if (this.getWidth() > 0) {
         var3.iMaxAdvance = this.getRightMargin();
      }

      boolean var4 = var1.isDrawingStyleSet(8);
      boolean var5 = var1.isDrawingStyleSet(16);
      if (this.isStyle(137438953472L) && !var4 && !var5) {
         int var6 = var1.getColor();
         if (this.isEditable()) {
            var1.setColor(ThemeAttributeSet.getColor(this, 0));
         } else {
            var1.setColor(13882323);
         }

         var1.fillRect(var2.x, var2.y, var2.width, var2.height);
         var1.setColor(var6);
      }

      ArticInterface$LineInfo var7 = this.getLineInfoForYPos(var2.y);
      Formatter.paint(var1, var3, var7, this._iterator, this, this);
      Ui.returnTmpDrawTextParam(var3);
   }

   private void resetLatestCommittedText() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private int deleteSelectedText() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void setTextInternal(String var1, int var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void actionPerformed(int var1, Object var2) {
      if ((var1 & 0xFF) > 0) {
         switch (var1 & 0xFF) {
            case 112:
               if (var2 != null && var2 instanceof Object) {
                  this.fillInternalDebugInfo((StringBuffer)var2);
               }
               break;
            case 113:
               if (this.isSymbolScreenAllowed()) {
                  SymbolScreen.show(this);
                  this._dependentScreen = SymbolScreen.getSymbolScreen();
                  return;
               }
               break;
            case 141:
               if (this.isSymbolScreenAllowed() && SymbolScreen.getSymbolScreen().isUiEngineAttached()) {
                  SymbolScreen.getSymbolScreen().close();
                  this._dependentScreen = null;
                  return;
               }
         }
      }
   }

   private void setCursorPosition(int var1, boolean var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public String toString() {
      return this.getText();
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      if (super.keyControl(var1, var2, var3)) {
         return true;
      }

      if (!this.isEditable()) {
         return this.isSelecting() && var1 == '\n';
      }

      switch (var1) {
         case '\b':
            if (0 != (var2 & 1)) {
               this.selectionDelete();
               return true;
            }

            this.backspace();
            return true;
         case '\u001b':
            return false;
         case '\u007f':
            this.selectionDelete();
            return true;
         case '\u0080':
            if (this.isSymbolScreenAllowed()) {
               SymbolScreen.show(this);
               this._dependentScreen = SymbolScreen.getSymbolScreen();
               return true;
            } else {
               if (!this.insert(var1, var2) && !this.isFieldFull()) {
                  return false;
               }

               return true;
            }
         default:
            char var4 = this.convert(var1, var2);
            return this.validate(var4) ? this.insert(var4, var2) || this.isFieldFull() : false;
      }
   }

   @Override
   public void selectionCopy(Clipboard var1) {
      int var2 = Math.min(this._cursor, this._anchor);
      int var3 = Math.max(this._cursor, this._anchor);
      int var4 = var3 - var2;
      if (var4 > 0) {
         var1.put(this.getText(var2, var4));
      } else {
         var1.put(null);
      }
   }

   @Override
   public synchronized void selectionDelete() {
      if (this.isSelecting()) {
         this.deleteSelectedText();
      } else if (this._cursor != this.getDisplayTextLength()) {
         String var1 = this._text.getText(this._cursor, this._cursor + 1);
         this._text.delete(this._cursor, this._cursor + 1);
         this.update(this._cursor, 1, 0, 0, true, true);
         if (Ui.isTTSEnabled()) {
            this.accessibleEventOccurred(2, var1, null, this);
         }

         this.fieldChangeNotify(0);
      }
   }

   private boolean posIsAtBidiBorder(int var1, boolean var2) {
      ArticInterface$LineInfo var3 = this.getLineInfoForDocPos(var1, true);
      ArticInterface$Line var4 = var3._line;
      int var5 = var3._start;
      boolean var6 = false;
      if (var5 == var1) {
         if (!var2 || (var4._flags & 1) != 0) {
            return false;
         }

         ArticInterface$LayoutRun var7 = var4._prev._layoutRun[var4._prev._layoutRun.length - 1];
         var6 = (var7._flags & 2) != 0;
      }

      int var12 = var4._layoutRun == null ? 0 : var4._layoutRun.length;

      for (int var8 = 0; var8 < var12; var8++) {
         ArticInterface$LayoutRun var9 = var4._layoutRun[var8];
         int var10 = var5 + var9._textStart;
         if (var10 > var1) {
            return false;
         }

         if (var10 == var1) {
            boolean var11 = (var9._flags & 2) != 0;
            if (var6 != var11) {
               return true;
            }

            return false;
         }

         if (var10 + var9._textLength == var1) {
            var6 = (var9._flags & 2) != 0;
         }
      }

      return false;
   }

   private synchronized void update(int var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   private void spawnDelayedFormatting() {
      if (!this._formatParams._isFormatComplete) {
         this._formatThreadId = Application.getApplication().invokeLater(new TextField$3(this), 100, false);
      }
   }

   private String removeNewlines(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void removeNewlines(AttributedString var1) {
      int var2 = var1.length();

      for (int var3 = var2 - 1; var3 >= 0; var3--) {
         if (var1.charAt(var3) == '\n') {
            var1.delete(var3, var3 + 1);
         }
      }
   }

   private boolean isComposedTextExist() {
      return this._composedStart != this._composedEnd;
   }

   private void resetComposedText() {
      if (this.isComposedTextExist()) {
         InputContext var1 = this.getInputContext();
         if (var1.getInputComponent() == this) {
            var1.endComposition();
         }
      }
   }

   private void initCursor() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private int getCaretX(int var1, boolean var2) {
      this.getCaretRect(var1, var2, this._tempRect);
      return this._tempRect.x;
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();
      this.setLabel(this._labelSet);
   }

   @Override
   protected void fieldChangeNotify(int var1) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private String handleLabelOwnLine(String var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final int getLineLength(ArticInterface$Line var1) {
      return var1._textLength + var1._skippedCharacters;
   }

   private void layoutLabel() {
      int var1 = 0;
      this._lastLabelLineTop = 0;

      ArticInterface$Line var2;
      for (var2 = this._lineList; var2 != null; var2 = var2._next) {
         var1 += this.getLineLength(var2);
         if (var1 >= this._labelLength) {
            break;
         }

         this._lastLabelLineTop = this._lastLabelLineTop + (var2._boundsBottom - var2._boundsTop);
      }

      if (this._labelLength > 0 && var2 != null && this._text.charAt(this._labelLength - 1) == '\n') {
         this._lastLabelLineTop = this._lastLabelLineTop + (var2._boundsBottom - var2._boundsTop);
      }
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private void adjustCursorAfterTextChange(FormatParams var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private synchronized void setSelection(int var1, boolean var2, int var3, boolean var4) {
      if (this._lineCount == 0) {
         throw new Object();
      }

      if (this._width == -1) {
         this._cursor = var1;
         this._cursorLeadingEdge = var2;
         this._anchor = var3;
      } else {
         if (!this._formatParams._isFormatComplete) {
            int var5 = Math.max(20, Display.getHeight() / this.getFont().getHeight() + 1) * (this.getWidth() / this.getFont().getAdvance(' '));
            int var6 = Math.max(var1, var3) + var5;
            int var7 = this._formatParams.getNextStartPosToFormat();
            if (var6 > var7 && this._formatThreadId != -1) {
               Application.getApplication().cancelInvokeLater(this._formatThreadId);
            }

            while (!this._formatParams._isFormatComplete && var6 > var7) {
               Formatter.incrementalFormat(
                  this._formatParams, this, this._width, this._text, this._cursor, this._cursorLeadingEdge, this._anchor, !this._inLayout
               );
               var7 = this._formatParams.getNextStartPosToFormat();
               this.handleLinesAfterFormat(this._formatParams);
               if (!this._inLayout && this._formatParams._invalidRect.height == Integer.MAX_VALUE) {
                  this.updateLayout();
               }
            }

            this.spawnDelayedFormatting();
         }

         this._formatParams.initCursorLine(this._cursorLine, this._cursorLineStart, this._cursorLineTop);
         XYRect var8 = this._formatParams._invalidRect;
         var8.set(this._focus_x, this._focus_y, this._focus_width, this._focus_height);
         this._lineList = Formatter.setSelection(this._lineList, this._anchor, this._cursor, var3, var1, var2, this._width, this._text, this._formatParams);
         this._cursorLineStart = this._formatParams._cursorLineInfo._start;
         this._cursorLineTop = this._formatParams._cursorLineInfo._top;
         this._cursorLine = this._formatParams._cursorLineInfo._line;
         this._cursor = var1;
         this._cursorLeadingEdge = var2;
         this._anchor = var3;
         this._cursorIsAtBidiBorder = this.posIsAtBidiBorder(this._cursor, true);
         if (var4) {
            this._preferredXCoord = this.getCaretX(this._cursor, this._cursorLeadingEdge);
         }

         this.invalidate(var8.x, var8.y, var8.width, var8.height);
         this.handleCursorPositionChanged();
      }
   }

   @Override
   public void setFont(Font var1) {
      if (this.getFontIfSet() != var1) {
         super.setFont(var1);
      }

      if (this._font != var1) {
         this.setFontInternal(var1);
      }
   }

   private void setFontInternal(Font var1) {
      long var2 = 134283263;
      long var4 = this.getDefaultFontAttributes();
      if ((var4 & 786432) != 0) {
         var2 |= 786432;
      }

      this.invalidateFocusRect();
      this.startLayoutUpdate();
      AttributedString$Iterator var6 = this._text.getIterator();
      int var7 = 0;

      do {
         int var8 = var7 + var6.runLength();
         if ((var6.runAttrib() & 536870912) != 0) {
            this.setAttrib(var7, var8, var4, var2, 0, 0);
         }

         var7 = var8;
      } while (var6.next());

      this.endLayoutUpdate();
      this._font = var1;
      this.setInsertionAttributesToSelection();
      long var10 = 0;
      switch (this.getFont().getStyle() & 7168) {
         case 1024:
            var10 = 1;
            break;
         case 2048:
            var10 = 2;
            break;
         case 3072:
            var10 = 3;
            break;
         case 4096:
            var10 = 4;
      }

      this._text.setGlobalAttrib(var10, 7);
   }

   private synchronized void incrementalFormat() {
      Formatter.incrementalFormat(this._formatParams, this, this._width, this._text, this._cursor, this._cursorLeadingEdge, this._anchor, !this._inLayout);
      this.handleLinesAfterFormat(this._formatParams);
      if (!this._inLayout && this._formatParams._invalidRect.height == Integer.MAX_VALUE) {
         this.updateLayout();
      }

      this.spawnDelayedFormatting();
   }

   public TextField(String var1, String var2, int var3, long var4) {
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public int caretPositionChanged(InputMethodEvent var1) {
      switch (var1.getID()) {
         case 1100:
         case 1103:
            break;
         case 1101:
         default:
            if (this.isComposedTextExist()) {
               this.setCaretPosition(this._composedStart + var1.getCaret()._index);
               this.update(this._cursor, 0, 0, 0, true, false);
               this.fieldChangeNotify(0);
               return 0;
            }
            break;
         case 1102:
            if (!this.isComposedTextExist()) {
               this.setCaretPosition(var1.getCaret()._index);
               this.update(this._cursor, 0, 0, 0, true, false);
               this.fieldChangeNotify(0);
               return 0;
            }
            break;
         case 1104:
            this._caretShape = (byte)var1.getModifiers();
            this.invalidate(this._focus_x, this._focus_y, this._focus_width, this._focus_height);
      }

      return 0;
   }

   @Override
   public void setIMCookieCache(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void updateCursorAfterFormat(FormatParams var1) {
      this._cursorLine = var1._cursorLineInfo._line;
      this._cursorLineStart = var1._cursorLineInfo._start;
      this._cursorLineTop = var1._cursorLineInfo._top;
      boolean var2 = this.posIsAtBidiBorder(this._cursor, true);
      if (this._cursorLineStart == this._cursor) {
         this._cursorLeadingEdge = true;
      } else if (var2 && !this._cursorIsAtBidiBorder && this._formatParams._moveCursor) {
         this._cursorLeadingEdge = this._formatParams._isBackspace;
      } else if (!var2) {
         this._cursorLeadingEdge = true;
      }

      this.getLineInfoForDocPos(this._cursor, this._cursorLeadingEdge);
      this._cursorLine = this._tempLineInfo._line;
      this._cursorLineStart = this._tempLineInfo._start;
      this._cursorLineTop = this._tempLineInfo._top;
      this._cursorIsAtBidiBorder = var2;
   }

   private void setInsertionAttributesToSelection() {
      if (this.getTextLength() == 0) {
         this.setDefaultInsertionAttributes();
      } else {
         this._insertionAttrib = this.getInsertionAttributesOfSelection();
         this._text.setInsertAttrib(this._insertionAttrib);
      }
   }

   private long getInsertionAttributesOfSelection() {
      int var1 = this._cursor;
      if (this._cursor > this._labelLength
         && this._cursor >= this._anchor
         && (this._composedStart == this._composedEnd || this._cursor > this._composedStart)
         && (this._cursor > this._cursorLineStart || (this._cursorLine._flags & 1) == 0)) {
         var1--;
      }

      this._iterator.set(var1, var1);
      long var2 = this._iterator.runAttrib();
      if (this._composedStart != this._composedEnd) {
         var2 = var2 & (this._composedTextAttributeMask ^ -1) | this._insertionAttrib & this._composedTextAttributeMask;
      }

      long var4 = this._iterator.runXAttrib();
      if ((var4 & 65504) != 0) {
         var2 &= -786433;
      }

      return var2;
   }

   @Override
   public synchronized int inputMethodTextChanged(InputMethodEvent var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   protected void onUndisplay() {
      super.onUndisplay();
      this._themeGeneration = -1;
   }

   @Override
   public InputMethodRequests getInputMethodRequests() {
      return this;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return this;
   }

   @Override
   public int getAccessibleRole() {
      return 2;
   }

   @Override
   public void dispatchEvent(Event var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      this.getDocPos(var1, var2, this._tempDocPosInfo);
      int var5 = Math.max(this._tempDocPosInfo._index, this.getLabelLength());
      if (this._selecting) {
         this.setSelection(var5, this._tempDocPosInfo._leadingEdge, this._anchor, false);
      } else {
         this.setSelection(var5, this._tempDocPosInfo._leadingEdge, var5, false);
      }

      this.getFocusRect(this._tempRect);
      this.invalidate(this._focus_x, this._focus_y, this._focus_width, this._focus_height);
      return true;
   }

   @Override
   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      EventHandler var6 = EventHandler.getInstance();
      switch (var1) {
         case 516:
         case 6914:
            boolean var7 = this.isInputMethodEnabled() && this.isEditable();
            return (var6.processKeyEvent(516, 0, '\u0000', var4, var5, var7) & 65536) == 65536;
         default:
            return var6.processNavigationEvent(var1, var2, var3, var4, var5);
      }
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      EventHandler var5 = EventHandler.getInstance();
      boolean var6 = this.isInputMethodEnabled() && this.isEditable();
      return var5.processKeyEvent(var1, var3, var2, var3, var4, var6);
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      boolean var3 = false;
      int var4 = this._focus_width;
      if (this._composedStart != this._composedEnd) {
         switch (this._caretShape) {
            case -1:
            case 1:
               break;
            case 0:
            default:
               return;
            case 2:
               var3 = true;
               break;
            case 3:
               var4 = 2;
               break;
            case 4:
               if (this._composed_highlighted) {
                  return;
               }
         }
      }

      int var5 = this._focus_x;
      int var6 = this._focus_y;
      int var7 = this._focus_height;
      if (this._selecting) {
         var6 += var7 + 1 >> 1;
         var7 >>= 1;
      }

      if (var3) {
         int var8 = var1.getColor();
         var1.setColor(ThemeAttributeSet.getColor(this, 2));
         var1.drawRect(var5, var6, var4, var7);
         var1.setColor(ThemeAttributeSet.getColor(this, 1));
         this.paint(var1);
         var1.setColor(var8);
      } else {
         this.drawHighlightRegion(var1, 1, var2, var5, var6, var4, var7);
      }
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      if (super.navigationClick(var1, var2)) {
         this._navigationClickHandled = true;
         return true;
      } else {
         return false;
      }
   }

   public TextField(String var1, String var2) {
      this(var1, var2, 1000000, 0);
   }

   private boolean isDirectionalityR2L(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   private void fillInternalDebugInfo(StringBuffer var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public TextField(long var1) {
      this(null, null, 1000000, var1);
   }

   public TextField() {
      this(null, null, 1000000, 0);
   }

   private int getRightMargin() {
      Manager var1 = this.getManager();
      int var2 = var1 == null ? 0 : var1.getHorizontalScroll();
      return var2 + this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
   }

   private int getBottomMargin() {
      Manager var1 = this.getManager();
      int var2 = var1 == null ? 0 : var1.getVerticalScroll();
      return var2 + this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
   }
}
