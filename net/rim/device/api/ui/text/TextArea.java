package net.rim.device.api.ui.text;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.DrawTextParam;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.internal.ui.ArticInterface$Line;
import net.rim.device.internal.ui.ArticInterface$LineInfo;
import net.rim.device.internal.ui.FormatParams;
import net.rim.device.internal.ui.Formatter;
import net.rim.device.internal.ui.Formatter$TextRenderer;
import net.rim.tid.text.AttributedString;
import net.rim.tid.text.AttributedString$Iterator;

public class TextArea implements Formatter$TextRenderer {
   private Field _field;
   private Tag _tag;
   private String _id;
   private ThemeAttributeSet _tas;
   private int _style;
   private int _rbKey;
   private int _cachedLocaleCode;
   private long _rbId;
   private String _rbName;
   private Object _textObject;
   private XYRect _extent = (XYRect)(new Object());
   boolean _layoutValid = false;
   private int _layoutWidth;
   private int _layoutOffsetX = 0;
   private int _layoutOffsetY = 0;
   protected int _width;
   private int _lineCount;
   private AttributedString _text;
   private int _anchor;
   private int _cursor;
   private ArticInterface$Line _lineList;
   private ArticInterface$LineInfo _tempLineInfo = (ArticInterface$LineInfo)(new Object());
   private FormatParams _formatParams = (FormatParams)(new Object());
   private int _lastFormatLength;
   private boolean _removeLastLine;
   protected int _widthForPaintWithEllipsis;

   public void applyTheme() {
      this._tas = ThemeManager.getActiveTheme().getAttributeSet(this._tag, this._id, 0);
   }

   public XYRect getExtent() {
      return this._extent;
   }

   public int getHeight() {
      return this._extent.height;
   }

   public int getLineCount() {
      return this._lineCount;
   }

   public int getLineHeight(int var1) {
      int var2 = 0;
      Object var3 = null;

      for (var3 = this._lineList; var3 != null && var2 < var1; var3 = ((ArticInterface$Line)var3)._next) {
         var2++;
      }

      return var3 == null ? 0 : ((ArticInterface$Line)var3)._boundsBottom - ((ArticInterface$Line)var3)._boundsTop;
   }

   public Object getText() {
      this.checkLocale();
      return this._textObject;
   }

   public String getTextString() {
      this.checkLocale();
      return this._text.toString();
   }

   public int getWidth() {
      return this._layoutWidth - this._layoutOffsetX;
   }

   public boolean isLayoutValid() {
      return this._layoutValid;
   }

   public void invalidateLayout() {
      this._layoutValid = false;
   }

   public synchronized void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setSelection(int var1, int var2) {
      this._lineList = Formatter.setSelection(this._lineList, this._anchor, this._cursor, var1, var2, true, this._width, this._text, this._formatParams);
      this._anchor = var1;
      this._cursor = var2;
      if (this._removeLastLine) {
         this.removeLastLine();
      }
   }

   protected void paint(Graphics var1) {
      throw null;
   }

   public void paintSelf(Graphics var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected final void setExtent(int var1, int var2) {
      this._extent.width = var1;
      this._extent.height = var2;
      this._layoutValid = true;
   }

   public boolean reduceWidthToFit(int var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setId(String var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setPosition(int var1, int var2) {
      this._extent.x = var1;
      this._extent.y = var2;
   }

   public void setTag(Tag var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setText(Object var1) {
      this._rbId = 0;
      this._rbName = null;
      this.setTextInternal(var1);
   }

   public void setText(ResourceBundleFamily var1, int var2) {
      this._rbId = var1.getId();
      this._rbName = var1.getName();
      this._rbKey = var2;
      this._cachedLocaleCode = 0;
      this.checkLocale();
   }

   public int getStyle() {
      return this._style;
   }

   public void setStyle(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public XYRect[] getTextBounds(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public ArticInterface$LineInfo getLineInfoForYPos(int var1) {
      this._tempLineInfo._start = 0;
      this._tempLineInfo._top = 0;
      this._tempLineInfo._line = this._lineList;
      Formatter.getLineInfoForYPos(var1, this._tempLineInfo);
      return this._tempLineInfo;
   }

   public ArticInterface$LineInfo getLineInfoForDocPos(int var1, boolean var2) {
      this._tempLineInfo._start = 0;
      this._tempLineInfo._top = 0;
      this._tempLineInfo._line = this._lineList;
      Formatter.getLineInfoForDocPos(var1, var2, this._lineList, this._tempLineInfo, true);
      return this._tempLineInfo;
   }

   public synchronized void getSelectionRect(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void getTextBounds(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   Field getField() {
      return this._field;
   }

   protected void setDrawTextParamFromStyle(DrawTextParam var1) {
      int var2 = this.getStyle();
      var1.iAlignment = 8;
      if (this._width > 0) {
         var1.iMaxAdvance = this._width;
      }

      if ((var2 & 64) != 0) {
         if ((var2 & 128) != 0) {
            var1.iTruncateWithEllipsis = 1;
         } else {
            var1.iTruncateWithEllipsis = 2;
         }

         var1.iMaxAdvance = this._widthForPaintWithEllipsis;
      } else {
         if ((var2 & 128) != 0) {
            var1.iTruncateWithEllipsis = 3;
         }
      }
   }

   public AttributedString$Iterator getTextIterator() {
      return this._text.getIterator();
   }

   @Override
   public int drawText(Graphics var1, int var2, int var3, int var4, int var5, DrawTextParam var6) {
      return var1.drawText(this._text.getText(), var2, var3, var4, var5, var6, null);
   }

   @Override
   public Font getFont() {
      return this._field.getFont();
   }

   private XYRect getTruncatedTextBounds(int var1, int var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected TextArea(Field var1) {
      this(var1, 0);
   }

   private synchronized void setTextInternal(Object var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void setAttributesFromFont() {
      Font var1 = null;
      if (this._tas != null) {
         var1 = this._tas.getFont();
      }

      if (var1 == null) {
         var1 = this._field.getFont();
      }

      if (var1 != null) {
         long var2 = Ui.getAttributesFromFont(var1) | Ui.DEFAULT_COLOR_ATTRIBS;
         int var4 = this._style & 7;
         if (var4 == 5) {
            var2 |= 4194304;
         } else if (var4 == 4) {
            var2 |= 12582912;
         }

         this._text.setAttrib(0, this._text.length(), var2, -1);
         long var5 = 0;
         switch (var1.getStyle() & 7168) {
            case 1024:
               var5 = 1;
               break;
            case 2048:
               var5 = 2;
               break;
            case 3072:
               var5 = 3;
               break;
            case 4096:
               var5 = 4;
         }

         this._text.setGlobalAttrib(var5, 7);
      }
   }

   private void update(int var1) {
      this._formatParams.init(0, this._lastFormatLength, this._text.length(), 0, false, this._lineList);
      this._formatParams.computeParamsAfterTextChanged(false, Integer.MAX_VALUE);
      this._formatParams.initCursorLine(this._lineList, 0, this._lineList._boundsTop);
      this._lastFormatLength = this._text.length();
      this._lineList = Formatter.incrementalFormat(this._formatParams, this._field, var1, this._text, 0, true, 0, false);
      this._lineCount = this._formatParams._lineCount;
      this._layoutWidth = this._formatParams._layoutWidth;
      this._width = var1;
      this._layoutOffsetX = Integer.MAX_VALUE;
      this._removeLastLine = false;

      for (ArticInterface$Line var2 = this._lineList; var2 != null; var2 = var2._next) {
         int var3 = var2._boundsLeft < 0 ? var2._originX + var2._boundsLeft : var2._originX;
         this._layoutOffsetX = Math.min(this._layoutOffsetX, var3);
         if (var2._next == null && this._lineCount > 1 && var2._textLength + var2._skippedCharacters == 0) {
            this._removeLastLine = true;
         }
      }

      if (this._removeLastLine) {
         this.removeLastLine();
      }
   }

   private void checkLocale() {
      if (this._rbId != 0) {
         int var1 = Locale.getDefault().getCode();
         if (this._cachedLocaleCode != var1) {
            this._cachedLocaleCode = var1;
            ResourceBundleFamily var2 = ResourceBundle.getBundle(this._rbId, this._rbName);
            String var3 = var2.getString(this._rbKey);
            this.setTextInternal(var3);
         }
      }
   }

   private void invertForR2L(XYRect var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private int getLineTop(int var1) {
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

   protected TextArea(Field var1, int var2) {
      this._field = var1;
      this._style = var2;
      this._text = (AttributedString)(new Object());
      this._lineList = (ArticInterface$Line)(new Object());
      this._lineList._flags = 3;
      this._lineCount = 1;
   }

   private void removeLastLine() {
      ArticInterface$Line var1 = null;

      for (ArticInterface$Line var2 = this._lineList; var2 != null; var2 = var2._next) {
         int var3 = var2._boundsLeft < 0 ? var2._originX + var2._boundsLeft : var2._originX;
         this._layoutOffsetX = Math.min(this._layoutOffsetX, var3);
         if (var2._next == null && var2._textLength + var2._skippedCharacters == 0 && var1 != null) {
            this._removeLastLine = true;
            var1._next = null;
            this._lineCount--;
         }

         var1 = var2;
      }
   }
}
