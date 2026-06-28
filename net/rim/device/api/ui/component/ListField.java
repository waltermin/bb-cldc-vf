package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.TextMetrics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.util.CharacterUtilities;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.i18n.CommonResource;
import net.rim.tid.awt.im.InputContext;

public class ListField extends Field implements VariableRowHeightProvider {
   private int _size;
   private int _cursor;
   private int _selectionRange;
   private int _rowHeightSet = -1;
   private int _rowHeight;
   private int _rowCached;
   private int _rowCachedY;
   private XYRect _focusRect = (XYRect)(new Object());
   private TextMetrics _metrics = (TextMetrics)(new Object());
   private RowHeightAdjuster _rowHeightAdjuster;
   private ListFieldCallback _callback;
   private StringBuffer _prefix = (StringBuffer)(new Object());
   private int _prevTime;
   private int _searchResetInterval = 300;
   private String _emptyString;
   private int _emptyStyle = 4;
   private long _rbId;
   private int _cachedLocaleCode;
   private int _rbKey;
   private String _rbName;
   private boolean _delayUpdateLayouts;
   private boolean _doUpdateLayout;
   private ThemeAttributeSet _tasRowEven;
   private ThemeAttributeSet _tasRowOdd;
   private static Tag TAG;
   private static final String TAG_ROW_EVEN_SUFFIX;
   private static final String TAG_ROW_ODD_SUFFIX;
   public static final int MULTI_SELECT;
   public static final int NON_CONTIGUOUS_SELECT;
   public static final int NO_ALTED_PAGE_UP_DOWN;
   public static final int ROW_HEIGHT_FONT;

   public ListField() {
      this(0, 0);
   }

   public ListField(int numRows) {
      this(numRows, 0);
   }

   public ListField(int numRows, long style) {
      super(validateStyle(style));
      this.setTag(TAG);
      this._size = 0;
      this._cursor = -1;
      this._selectionRange = 0;
      this._rowHeight = this.getFont().getHeight();
      this._rowHeightAdjuster = new RowHeightAdjuster(this._rowHeight, numRows);
      this.setSize(numRows, 0);
      if (this instanceof ListFieldCallback) {
         this._callback = (ListFieldCallback)this;
      }
   }

   public int adjustRowHeight(Font font, int index, String text) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void applyFont() {
      super.applyFont();
      if (this._rowHeightSet < 0) {
         int lineHeight = this.getFont().getHeight();
         this._rowHeight = lineHeight * -this._rowHeightSet;
         this._rowHeightAdjuster.setRowHeight(this._rowHeight);
      }
   }

   @Override
   protected void applyTheme() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private void calcFocusRect(boolean move) {
      if (move) {
         this.focusRemove();
      }

      this._focusRect.y = this.getYForRow(this._cursor);
      this._focusRect.height = this.getRowHeight(this._cursor);
      if (move) {
         this.focusAdd(true);
      }
   }

   private void calcSelectedIndex(int index) {
      if (this._size == 0) {
         this._cursor = -1;
         this._selectionRange = 0;
      } else {
         this._cursor = MathUtilities.clamp(0, index, this._size - 1);
         this._selectionRange = 0;
      }
   }

   private void checkLocale() {
      if (this._rbId != 0) {
         int currentCode = Locale.getDefault().getCode();
         if (this._cachedLocaleCode != currentCode) {
            this._cachedLocaleCode = currentCode;
            ResourceBundleFamily family = ResourceBundle.getBundle(this._rbId, this._rbName);
            this._emptyString = family.getString(this._rbKey);
         }
      }
   }

   public void delete(int index) {
      boolean focusMoved = false;
      if (index >= 0 && index <= this._size) {
         this._selectionRange = 0;
         this._size--;
         this._rowHeightAdjuster.deletedRow(index);
         if (index < this._cursor) {
            this._cursor--;
            focusMoved = true;
         }

         if (index == this._cursor && index == this._size) {
            if (index == 0) {
               this._cursor = -1;
            } else {
               this._cursor--;
               focusMoved = true;
               index--;
            }
         }

         this.calcFocusRect(false);
         this.fieldChangeNotify(Integer.MIN_VALUE);
         Manager manager = this.getManager();
         if (manager != null) {
            this.updateLayout();
            if (focusMoved) {
               this.focusAdd(false);
               this.focusChangeNotify(2);
            }

            this.invalidate(0, this.getYForRow(index), this.getWidth(), 1073741823);
         }
      } else {
         throw new Object(index);
      }
   }

   @Override
   protected void drawFocus(Graphics graphics, boolean on) {
      if (!on) {
         this.invalidate(this._focusRect.x, this._focusRect.y, this._focusRect.width, this._focusRect.height);
      } else {
         super.drawFocus(graphics, on);
      }
   }

   @Override
   public String getAccessibleName() {
      return this.getAccessibleChildAt(0) != null ? this.getAccessibleChildAt(0).getAccessibleName() : null;
   }

   @Override
   public boolean isAccessibleStateSet(int state) {
      return (super.getAccessibleStateSet() & state) != 0;
   }

   @Override
   public int getAccessibleRole() {
      return 25;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return this.getScreen();
   }

   @Override
   public int getAccessibleChildCount() {
      return this._size;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int index) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int getAccessibleSelectionCount() {
      return this.getSelection().length;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int index) {
      throw new RuntimeException("cod2jar: type check");
   }

   public ListFieldCallback getCallback() {
      return this._callback;
   }

   public String getEmptyString() {
      this.checkLocale();
      return this._emptyString != null ? this._emptyString : CommonResource.getString(1012);
   }

   public int getEmptyStringStyle() {
      return this._emptyStyle;
   }

   private int getFirstVisibleLine(int direction) {
      int y = this.getManager().getVerticalScroll() - this.getTop();
      y = MathUtilities.clamp(0, y, this.getContentHeight());
      if (!this.hasVariableLineHeights()) {
         if (direction > 0) {
            y += this._rowHeight - 1;
         }

         return this.getRowForY(y);
      } else {
         int firstVisibleLine = this.getRowForY(y);
         int rowHeightOffScreen = y - this.getYForRow(firstVisibleLine);
         if (rowHeightOffScreen > this.getRowHeight(firstVisibleLine) >> 2) {
            firstVisibleLine++;
         }

         return firstVisibleLine;
      }
   }

   @Override
   public void getFocusRect(XYRect rect) {
      rect.set(this._focusRect);
   }

   @Override
   public int getPreferredWidth() {
      return this._callback.getPreferredWidth(this);
   }

   public int getRowForY(int y) {
      if (!this.hasVariableLineHeights()) {
         return y / this._rowHeight;
      }

      int row = 0;
      int distance = Math.abs(this._rowCachedY - y);
      this._delayUpdateLayouts = true;
      if (y <= distance) {
         int currY = this.getRowHeight(0);

         while (currY < y) {
            currY += this.getRowHeight(++row);
         }
      } else if (this.getHeight() - this.getRowHeight(this._size - 1) - y <= distance) {
         row = this._size;

         for (int currY = this.getHeight(); currY >= y; currY -= this.getRowHeight(row)) {
            row--;
         }
      } else {
         row = this._rowCached;
         if (this._rowCachedY < y) {
            int currY = this._rowCachedY + this.getRowHeight(row);

            while (currY < y) {
               currY += this.getRowHeight(++row);
            }
         } else {
            for (int currY = this._rowCachedY; currY > y; currY -= this.getRowHeight(row)) {
               row--;
            }
         }
      }

      this._delayUpdateLayouts = false;
      if (this._doUpdateLayout) {
         this.triggerUpdateLayout();
      }

      return row;
   }

   public int getRowHeight() {
      return this._rowHeight;
   }

   public int getRowHeight(int row) {
      return this._rowHeightAdjuster.getRowHeight(row);
   }

   public int getSelectedIndex() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public int[] getSelection() {
      int size = Math.abs(this._selectionRange) + 1;
      int[] selection = new int[size];
      int last = this._cursor + (this._selectionRange > 0 ? this._selectionRange : 0);
      int index = last;

      while (size > 0) {
         selection[--size] = index--;
      }

      return selection;
   }

   private int getSelectionAnchor() {
      return this._cursor;
   }

   public int getSize() {
      return this._size;
   }

   public int getVisibleLinesPageDown(int topVisibleLine) {
      int height = Math.min(this.getHeight(), this.getManager().getVisibleHeight());
      if (!this.hasVariableLineHeights()) {
         return height / this._rowHeight;
      }

      int bottomVisibleLine = topVisibleLine;
      int minRemainingHeight = this._rowHeight - (this._rowHeight >> 2);

      for (int remainingHeight = height; bottomVisibleLine < this._size && remainingHeight >= minRemainingHeight; bottomVisibleLine++) {
         remainingHeight -= this.getRowHeight(bottomVisibleLine);
      }

      return bottomVisibleLine - topVisibleLine;
   }

   public int getVisibleLinesPageUp(int bottomVisibleLine) {
      int height = Math.min(this.getHeight(), this.getManager().getVisibleHeight());
      if (!this.hasVariableLineHeights()) {
         return height / this._rowHeight;
      }

      int topVisibleLine = bottomVisibleLine;
      int minRemainingHeight = this._rowHeight - (this._rowHeight >> 2);

      for (int remainingHeight = height; topVisibleLine >= 0 && remainingHeight >= minRemainingHeight; topVisibleLine--) {
         remainingHeight -= this.getRowHeight(topVisibleLine);
      }

      return bottomVisibleLine - topVisibleLine;
   }

   public int getYForRow(int row) {
      int y = 0;
      if (row < 0) {
         return 0;
      }

      if (!this.hasVariableLineHeights()) {
         y = row * this._rowHeight;
      } else {
         int distance = Math.abs(this._rowCached - row);
         this._delayUpdateLayouts = true;
         if (row <= distance) {
            for (int currRow = 0; currRow < row; currRow++) {
               y += this.getRowHeight(currRow);
            }
         } else if (this._size - row <= distance) {
            y = this._rowHeightAdjuster.getHeight();

            for (int currRow = this._size - 1; currRow >= row; currRow--) {
               y -= this.getRowHeight(currRow);
            }
         } else {
            y = this._rowCachedY;
            if (this._rowCached < row) {
               for (int currRow = this._rowCached; currRow < row; currRow++) {
                  y += this.getRowHeight(currRow);
               }
            } else {
               for (int currRow = this._rowCached - 1; currRow >= row; currRow--) {
                  y -= this.getRowHeight(currRow);
               }
            }
         }

         this._delayUpdateLayouts = false;
         if (this._doUpdateLayout) {
            this.triggerUpdateLayout();
         }
      }

      this._rowCached = row;
      this._rowCachedY = y;
      return y;
   }

   protected boolean hasVariableLineHeights() {
      return this._rowHeightAdjuster.hasVariableLineHeights();
   }

   public int indexOfList(String prefix, int start) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void insert(int index) {
      boolean focusMoved = false;
      if (index >= 0 && index <= this._size) {
         this._selectionRange = 0;
         if (this._size == 0) {
            this._cursor = 0;
         } else if (index <= this._cursor) {
            focusMoved = true;
            this._cursor++;
            this.focusChangeNotify(2);
         }

         this._size++;
         this._rowHeightAdjuster.insertedRow(index);
         this.calcFocusRect(false);
         Manager manager = this.getManager();
         if (manager != null) {
            this.updateLayout();
            if (focusMoved) {
               this.focusAdd(false);
            }

            this.invalidate(0, this.getYForRow(index), this.getWidth(), 1073741823);
         }

         this.fieldChangeNotify(Integer.MIN_VALUE);
      } else {
         throw new Object(index);
      }
   }

   @Override
   public void invalidate() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void invalidate(int index) {
      this.invalidate(0, this.getYForRow(index), this.getWidth(), this.getRowHeight(index));
   }

   public void invalidateRange(int start, int end) {
      if (start < 0) {
         start = 0;
      }

      if (end < start) {
         throw new Object();
      }

      if (start > this._size) {
         throw new Object();
      }

      int y = this.getYForRow(start);
      if (end > this._size) {
         end = Math.max(0, this._size - 1);
      }

      this.invalidate(0, y, this.getContentWidth(), this.getYForRow(end + 1) - y);
   }

   public boolean isEmpty() {
      return this._size == 0;
   }

   @Override
   public boolean isAccessibleChildSelected(int index) {
      int[] selectionRange = this.getSelection();

      for (int i = 0; i < selectionRange.length; i++) {
         if (selectionRange[i] == index) {
            return true;
         }
      }

      return false;
   }

   @Override
   protected boolean keyChar(char key, int status, int time) {
      if (key == 27 && this._selectionRange != 0) {
         this._selectionRange = 0;
         this.calcFocusRect(true);
         if (ThemeAttributeSet.getFocusStyle(this) != 0) {
            this.invalidate();
         }

         return true;
      } else {
         if (this._prefix != null) {
            if (CharacterUtilities.isLetter(key) || CharacterUtilities.isDigit(key)) {
               if (InputContext.getInstance(false).isSureType()) {
                  this.searchEntryForMultipleChars(key, status);
                  if (Ui.isTTSEnabled()) {
                     super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
                  }

                  return true;
               }

               int start = this._cursor;
               if (Math.abs(time - this._prevTime) > this._searchResetInterval) {
                  this._prefix.setLength(0);
               }

               if (this._prefix.length() != 1 || this._prefix.length() == 1 && key != this._prefix.charAt(0)) {
                  this._prefix.append(key);
               }

               if (this._prefix.length() == 1 && key == this._prefix.charAt(0)) {
                  start++;
               }

               if (0 > start) {
                  start = 0;
               }

               this._prevTime = time;
               String prefix = this._prefix.toString();
               if (!this.searchEntryFor(prefix, start)) {
                  this.searchEntryFor(prefix, 0);
               }

               return true;
            }

            if (key == '\b') {
               this._prefix.setLength(0);
               this._prevTime = 0;
               return true;
            }
         }

         return super.keyChar(key, status, time);
      }
   }

   @Override
   protected boolean keyControl(char key, int status, int time) {
      switch (key) {
         case '\u0082':
            return super.keyControl(key, status, time);
         case '\u0083':
         case '\u0084':
         default:
            return true;
      }
   }

   @Override
   protected void layout(int width, int height) {
      if (this._rowHeight != 0 && (this._rowHeight == this._rowHeightSet || this._rowHeightSet <= 0)) {
         this._focusRect.x = 0;
         this._focusRect.width = width;
         this._focusRect.height = this._rowHeight;
         this._rowCached = -1;
         this._rowCachedY = -1;
         this.calcFocusRect(false);
         height = this._rowHeightAdjuster.getHeight();
         this.setExtent(width, height);
      } else {
         throw new Object();
      }
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      if (this._prefix != null) {
         this._prefix.setLength(0);
      }

      if (this._size != 0 && (status & 65536) == 0) {
         this.setMuddy(false);
         if ((status & 1) == 0 || this.isStyle(8)) {
            int desiredAnchor = this._cursor + amount;
            int oldCursor = this._cursor;
            this._cursor = MathUtilities.clamp(0, desiredAnchor, this._size - 1);
            amount = desiredAnchor - this._cursor;
            if ((status & 2) != 0) {
               if (this.isStyle(2)) {
                  boolean wasMultiple = this._selectionRange != 0;
                  this._selectionRange = this._selectionRange + (oldCursor - this._cursor);
                  if (this._selectionRange != 0 || wasMultiple) {
                     int fudge = Graphics.isColor() ? 1 : 0;
                     int start = Math.min(oldCursor, this._cursor + fudge);
                     int end = Math.max(oldCursor, this._cursor - fudge);
                     this.invalidateRange(start, end);
                  }
               } else {
                  this._selectionRange = 0;
               }

               amount = 0;
            } else {
               if (this._selectionRange != 0) {
                  int start = Math.min(oldCursor + this._selectionRange, oldCursor);
                  int end = Math.max(oldCursor + this._selectionRange, oldCursor);
                  this.invalidateRange(start, end);
               }

               this._selectionRange = 0;
            }
         } else if ((status & 1) != 0) {
            if (this._selectionRange != 0) {
               int start = Math.min(this._cursor + this._selectionRange, this._cursor);
               int end = Math.max(this._cursor + this._selectionRange, this._cursor);
               this.invalidateRange(start, end);
               this._selectionRange = 0;
            }

            int firstVisibleLine = this.getFirstVisibleLine(amount);
            int visibleLines = this.getVisibleLinesPageDown(firstVisibleLine);
            if (amount < 0) {
               if (this._cursor == firstVisibleLine) {
                  this._cursor = this._cursor - this.getVisibleLinesPageUp(this._cursor);
                  this._cursor = Math.max(0, this._cursor);
               } else {
                  this._cursor = firstVisibleLine;
               }
            } else {
               if (this._cursor == firstVisibleLine + visibleLines - 1) {
                  this._cursor = this._cursor + this.getVisibleLinesPageDown(this._cursor);
               } else {
                  this._cursor = firstVisibleLine + visibleLines - 1;
               }

               this._cursor = Math.min(this._cursor, this._size - 1);
            }

            amount = 0;
         }

         this.calcFocusRect(false);
         if (amount == 0 && Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
         }

         return amount;
      } else {
         return amount;
      }
   }

   @Override
   protected void moveFocus(int x, int y, int status, int time) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }

      if (this._size != 0) {
         int row = this.getRowForY(y);
         if (row < this._size && row >= 0) {
            this._cursor = row;
            this._selectionRange = 0;
            this.focusChangeNotify(2);
            this.calcFocusRect(true);
         }
      }
   }

   @Override
   protected void onFocus(int direction) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(1, new Object(1), new Object(2), this);
      }

      if (this._cursor < 0) {
         if (direction > 0) {
            this._cursor = Math.min(0, this._size - 1);
         } else if (direction != 0) {
            this._cursor = this._size - 1;
         }
      }

      this.calcFocusRect(false);
   }

   @Override
   protected void paint(Graphics graphics) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private boolean searchEntryFor(String prefix, int start) {
      int index = this._callback.indexOfList(this, prefix, start);
      if (index != -1) {
         this.setSelectedIndex(index);
         this.focusChangeNotify(2);
         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
         }
      }

      return index != -1;
   }

   private void searchEntryForMultipleChars(char key, int status) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setCallback(ListFieldCallback callback) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setEmptyString(ResourceBundleFamily family, int id, int style) {
      if (family != null) {
         family.getString(id);
         this.setEmptyStringFamily(family, id);
      } else {
         this.setEmptyStringFamily(CommonResource.getBundle(), 1012);
      }

      this._emptyStyle = style;
   }

   public void setEmptyString(String emptyString, int style) {
      this._rbId = 0;
      this._rbName = null;
      this._emptyString = emptyString;
      this._emptyStyle = style;
   }

   private void setEmptyStringFamily(ResourceBundleFamily rb, int key) {
      this._rbId = rb.getId();
      this._rbName = rb.getName();
      this._rbKey = key;
      this._cachedLocaleCode = 0;
   }

   public void setRowHeight(int rowHeight) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setRowHeight(int row, int rowHeight) {
      if (this._rowHeightAdjuster.setRowHeight(row, rowHeight)) {
         this.triggerUpdateLayout();
      }
   }

   public void setSearchable(boolean searchable) {
      if (searchable) {
         this._prefix = (StringBuffer)(new Object());
      } else {
         this._prefix = null;
      }
   }

   public void setSelectedIndex(int index) {
      if (this._cursor >= 0 && this._selectionRange != 0) {
         int topIndex = this._selectionRange > 0 ? this._cursor : this._cursor + this._selectionRange;
         int bottomIndex = this._selectionRange > 0 ? this._cursor + this._selectionRange : this._cursor;
         this.invalidateRange(topIndex, bottomIndex);
      }

      this.calcSelectedIndex(index);
      this.calcFocusRect(true);
   }

   public void setSize(int size) {
      this.setSize(size, 0);
   }

   public void setSize(int size, int focusRow) {
      boolean sizeChange = this._size != size;
      this._size = size;
      this._rowHeightAdjuster.setSize(size);
      this.calcSelectedIndex(focusRow);
      this._rowCached = -1;
      this._rowCachedY = -1;
      this.calcFocusRect(false);
      this.fieldChangeNotify(Integer.MIN_VALUE);
      Manager manager = this.getManager();
      if (manager != null && manager.isValidLayout()) {
         if (sizeChange) {
            this.updateLayout();
         }

         this.invalidate();
         this.focusAdd(false);
      }
   }

   @Override
   protected boolean stylusTap(int x, int y, int status, int time) {
      int row = this.getRowForY(y);
      return row != this._cursor ? true : this.getScreen().defaultStylusAction(0);
   }

   @Override
   protected boolean stylusTapHold(int x, int y, int status, int time) {
      int row = this.getRowForY(y);
      return row < this._size && row >= 0 ? this.getScreen().onMenu(0) : true;
   }

   private void triggerUpdateLayout() {
      if (this._delayUpdateLayouts) {
         this._doUpdateLayout = true;
      } else {
         this.updateLayout();
         this._doUpdateLayout = false;
      }
   }

   private static long validateStyle(long style) {
      if ((style & 54043195528445952L) == 0) {
         style |= 18014398509481984L;
      }

      return style;
   }

   @Override
   public int getAdjustedY(Font font, String text, int y) {
      return this._rowHeightAdjuster.getAdjustedY(font, text, y);
   }

   @Override
   public int getAdjustedY(Font font, StringBuffer text, int offset, int len, int y) {
      return this._rowHeightAdjuster.getAdjustedY(font, text, offset, len, y);
   }

   @Override
   public int getAdjustedY(int currentY) {
      return this._rowHeightAdjuster.getAdjustedY(currentY);
   }
}
