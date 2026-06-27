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

   public ListField(int var1) {
      this(var1, 0);
   }

   public ListField(int var1, long var2) {
      super(validateStyle(var2));
      this.setTag(TAG);
      this._size = 0;
      this._cursor = -1;
      this._selectionRange = 0;
      this._rowHeight = this.getFont().getHeight();
      this._rowHeightAdjuster = new RowHeightAdjuster(this._rowHeight, var1);
      this.setSize(var1, 0);
      if (this instanceof ListFieldCallback) {
         this._callback = (ListFieldCallback)this;
      }
   }

   public int adjustRowHeight(Font var1, int var2, String var3) {
      throw new RuntimeException("cod2jar: string-special");
   }

   @Override
   protected void applyFont() {
      super.applyFont();
      if (this._rowHeightSet < 0) {
         int var1 = this.getFont().getHeight();
         this._rowHeight = var1 * -this._rowHeightSet;
         this._rowHeightAdjuster.setRowHeight(this._rowHeight);
      }
   }

   @Override
   protected void applyTheme() {
      throw new RuntimeException("cod2jar: invokevirtual: slot out of range");
   }

   private void calcFocusRect(boolean var1) {
      if (var1) {
         this.focusRemove();
      }

      this._focusRect.y = this.getYForRow(this._cursor);
      this._focusRect.height = this.getRowHeight(this._cursor);
      if (var1) {
         this.focusAdd(true);
      }
   }

   private void calcSelectedIndex(int var1) {
      if (this._size == 0) {
         this._cursor = -1;
         this._selectionRange = 0;
      } else {
         this._cursor = MathUtilities.clamp(0, var1, this._size - 1);
         this._selectionRange = 0;
      }
   }

   private void checkLocale() {
      if (this._rbId != 0) {
         int var1 = Locale.getDefault().getCode();
         if (this._cachedLocaleCode != var1) {
            this._cachedLocaleCode = var1;
            ResourceBundleFamily var2 = ResourceBundle.getBundle(this._rbId, this._rbName);
            this._emptyString = var2.getString(this._rbKey);
         }
      }
   }

   public void delete(int var1) {
      boolean var2 = false;
      if (var1 >= 0 && var1 <= this._size) {
         this._selectionRange = 0;
         this._size--;
         this._rowHeightAdjuster.deletedRow(var1);
         if (var1 < this._cursor) {
            this._cursor--;
            var2 = true;
         }

         if (var1 == this._cursor && var1 == this._size) {
            if (var1 == 0) {
               this._cursor = -1;
            } else {
               this._cursor--;
               var2 = true;
               var1--;
            }
         }

         this.calcFocusRect(false);
         this.fieldChangeNotify(Integer.MIN_VALUE);
         Manager var3 = this.getManager();
         if (var3 != null) {
            this.updateLayout();
            if (var2) {
               this.focusAdd(false);
               this.focusChangeNotify(2);
            }

            this.invalidate(0, this.getYForRow(var1), this.getWidth(), 1073741823);
         }
      } else {
         throw new Object(var1);
      }
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (!var2) {
         this.invalidate(this._focusRect.x, this._focusRect.y, this._focusRect.width, this._focusRect.height);
      } else {
         super.drawFocus(var1, var2);
      }
   }

   @Override
   public String getAccessibleName() {
      return this.getAccessibleChildAt(0) != null ? this.getAccessibleChildAt(0).getAccessibleName() : null;
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (super.getAccessibleStateSet() & var1) != 0;
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
   public AccessibleContext getAccessibleChildAt(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public int getAccessibleSelectionCount() {
      return this.getSelection().length;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
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

   private int getFirstVisibleLine(int var1) {
      int var2 = this.getManager().getVerticalScroll() - this.getTop();
      var2 = MathUtilities.clamp(0, var2, this.getContentHeight());
      if (!this.hasVariableLineHeights()) {
         if (var1 > 0) {
            var2 += this._rowHeight - 1;
         }

         return this.getRowForY(var2);
      } else {
         int var3 = this.getRowForY(var2);
         int var4 = var2 - this.getYForRow(var3);
         if (var4 > this.getRowHeight(var3) >> 2) {
            var3++;
         }

         return var3;
      }
   }

   @Override
   public void getFocusRect(XYRect var1) {
      var1.set(this._focusRect);
   }

   @Override
   public int getPreferredWidth() {
      return this._callback.getPreferredWidth(this);
   }

   public int getRowForY(int var1) {
      if (!this.hasVariableLineHeights()) {
         return var1 / this._rowHeight;
      }

      int var2 = 0;
      int var3 = Math.abs(this._rowCachedY - var1);
      this._delayUpdateLayouts = true;
      if (var1 <= var3) {
         int var4 = this.getRowHeight(0);

         while (var4 < var1) {
            var4 += this.getRowHeight(++var2);
         }
      } else if (this.getHeight() - this.getRowHeight(this._size - 1) - var1 <= var3) {
         var2 = this._size;

         for (int var5 = this.getHeight(); var5 >= var1; var5 -= this.getRowHeight(var2)) {
            var2--;
         }
      } else {
         var2 = this._rowCached;
         if (this._rowCachedY < var1) {
            int var6 = this._rowCachedY + this.getRowHeight(var2);

            while (var6 < var1) {
               var6 += this.getRowHeight(++var2);
            }
         } else {
            for (int var7 = this._rowCachedY; var7 > var1; var7 -= this.getRowHeight(var2)) {
               var2--;
            }
         }
      }

      this._delayUpdateLayouts = false;
      if (this._doUpdateLayout) {
         this.triggerUpdateLayout();
      }

      return var2;
   }

   public int getRowHeight() {
      return this._rowHeight;
   }

   public int getRowHeight(int var1) {
      return this._rowHeightAdjuster.getRowHeight(var1);
   }

   public int getSelectedIndex() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public int[] getSelection() {
      int var1 = Math.abs(this._selectionRange) + 1;
      int[] var2 = new int[var1];
      int var3 = this._cursor + (this._selectionRange > 0 ? this._selectionRange : 0);
      int var4 = var3;

      while (var1 > 0) {
         var2[--var1] = var4--;
      }

      return var2;
   }

   private int getSelectionAnchor() {
      return this._cursor;
   }

   public int getSize() {
      return this._size;
   }

   public int getVisibleLinesPageDown(int var1) {
      int var2 = Math.min(this.getHeight(), this.getManager().getVisibleHeight());
      if (!this.hasVariableLineHeights()) {
         return var2 / this._rowHeight;
      }

      int var3 = var1;
      int var4 = this._rowHeight - (this._rowHeight >> 2);

      for (int var5 = var2; var3 < this._size && var5 >= var4; var3++) {
         var5 -= this.getRowHeight(var3);
      }

      return var3 - var1;
   }

   public int getVisibleLinesPageUp(int var1) {
      int var2 = Math.min(this.getHeight(), this.getManager().getVisibleHeight());
      if (!this.hasVariableLineHeights()) {
         return var2 / this._rowHeight;
      }

      int var3 = var1;
      int var4 = this._rowHeight - (this._rowHeight >> 2);

      for (int var5 = var2; var3 >= 0 && var5 >= var4; var3--) {
         var5 -= this.getRowHeight(var3);
      }

      return var1 - var3;
   }

   public int getYForRow(int var1) {
      int var2 = 0;
      if (var1 < 0) {
         return 0;
      }

      if (!this.hasVariableLineHeights()) {
         var2 = var1 * this._rowHeight;
      } else {
         int var3 = Math.abs(this._rowCached - var1);
         this._delayUpdateLayouts = true;
         if (var1 <= var3) {
            for (int var7 = 0; var7 < var1; var7++) {
               var2 += this.getRowHeight(var7);
            }
         } else if (this._size - var1 <= var3) {
            var2 = this._rowHeightAdjuster.getHeight();

            for (int var6 = this._size - 1; var6 >= var1; var6--) {
               var2 -= this.getRowHeight(var6);
            }
         } else {
            var2 = this._rowCachedY;
            if (this._rowCached < var1) {
               for (int var5 = this._rowCached; var5 < var1; var5++) {
                  var2 += this.getRowHeight(var5);
               }
            } else {
               for (int var4 = this._rowCached - 1; var4 >= var1; var4--) {
                  var2 -= this.getRowHeight(var4);
               }
            }
         }

         this._delayUpdateLayouts = false;
         if (this._doUpdateLayout) {
            this.triggerUpdateLayout();
         }
      }

      this._rowCached = var1;
      this._rowCachedY = var2;
      return var2;
   }

   protected boolean hasVariableLineHeights() {
      return this._rowHeightAdjuster.hasVariableLineHeights();
   }

   public int indexOfList(String var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void insert(int var1) {
      boolean var2 = false;
      if (var1 >= 0 && var1 <= this._size) {
         this._selectionRange = 0;
         if (this._size == 0) {
            this._cursor = 0;
         } else if (var1 <= this._cursor) {
            var2 = true;
            this._cursor++;
            this.focusChangeNotify(2);
         }

         this._size++;
         this._rowHeightAdjuster.insertedRow(var1);
         this.calcFocusRect(false);
         Manager var3 = this.getManager();
         if (var3 != null) {
            this.updateLayout();
            if (var2) {
               this.focusAdd(false);
            }

            this.invalidate(0, this.getYForRow(var1), this.getWidth(), 1073741823);
         }

         this.fieldChangeNotify(Integer.MIN_VALUE);
      } else {
         throw new Object(var1);
      }
   }

   @Override
   public void invalidate() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   public void invalidate(int var1) {
      this.invalidate(0, this.getYForRow(var1), this.getWidth(), this.getRowHeight(var1));
   }

   public void invalidateRange(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 < var1) {
         throw new Object();
      }

      if (var1 > this._size) {
         throw new Object();
      }

      int var3 = this.getYForRow(var1);
      if (var2 > this._size) {
         var2 = Math.max(0, this._size - 1);
      }

      this.invalidate(0, var3, this.getContentWidth(), this.getYForRow(var2 + 1) - var3);
   }

   public boolean isEmpty() {
      return this._size == 0;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      int[] var2 = this.getSelection();

      for (int var3 = 0; var3 < var2.length; var3++) {
         if (var2[var3] == var1) {
            return true;
         }
      }

      return false;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      if (var1 == 27 && this._selectionRange != 0) {
         this._selectionRange = 0;
         this.calcFocusRect(true);
         if (ThemeAttributeSet.getFocusStyle(this) != 0) {
            this.invalidate();
         }

         return true;
      } else {
         if (this._prefix != null) {
            if (CharacterUtilities.isLetter(var1) || CharacterUtilities.isDigit(var1)) {
               if (InputContext.getInstance(false).isSureType()) {
                  this.searchEntryForMultipleChars(var1, var2);
                  if (Ui.isTTSEnabled()) {
                     super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
                  }

                  return true;
               }

               int var4 = this._cursor;
               if (Math.abs(var3 - this._prevTime) > this._searchResetInterval) {
                  this._prefix.setLength(0);
               }

               if (this._prefix.length() != 1 || this._prefix.length() == 1 && var1 != this._prefix.charAt(0)) {
                  this._prefix.append(var1);
               }

               if (this._prefix.length() == 1 && var1 == this._prefix.charAt(0)) {
                  var4++;
               }

               if (0 > var4) {
                  var4 = 0;
               }

               this._prevTime = var3;
               String var5 = this._prefix.toString();
               if (!this.searchEntryFor(var5, var4)) {
                  this.searchEntryFor(var5, 0);
               }

               return true;
            }

            if (var1 == '\b') {
               this._prefix.setLength(0);
               this._prevTime = 0;
               return true;
            }
         }

         return super.keyChar(var1, var2, var3);
      }
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      switch (var1) {
         case '\u0082':
            return super.keyControl(var1, var2, var3);
         case '\u0083':
         case '\u0084':
         default:
            return true;
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      if (this._rowHeight != 0 && (this._rowHeight == this._rowHeightSet || this._rowHeightSet <= 0)) {
         this._focusRect.x = 0;
         this._focusRect.width = var1;
         this._focusRect.height = this._rowHeight;
         this._rowCached = -1;
         this._rowCachedY = -1;
         this.calcFocusRect(false);
         var2 = this._rowHeightAdjuster.getHeight();
         this.setExtent(var1, var2);
      } else {
         throw new Object();
      }
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if (this._prefix != null) {
         this._prefix.setLength(0);
      }

      if (this._size != 0 && (var2 & 65536) == 0) {
         this.setMuddy(false);
         if ((var2 & 1) == 0 || this.isStyle(8)) {
            int var12 = this._cursor + var1;
            int var14 = this._cursor;
            this._cursor = MathUtilities.clamp(0, var12, this._size - 1);
            var1 = var12 - this._cursor;
            if ((var2 & 2) != 0) {
               if (this.isStyle(2)) {
                  boolean var7 = this._selectionRange != 0;
                  this._selectionRange = this._selectionRange + (var14 - this._cursor);
                  if (this._selectionRange != 0 || var7) {
                     int var8 = Graphics.isColor() ? 1 : 0;
                     int var9 = Math.min(var14, this._cursor + var8);
                     int var10 = Math.max(var14, this._cursor - var8);
                     this.invalidateRange(var9, var10);
                  }
               } else {
                  this._selectionRange = 0;
               }

               var1 = 0;
            } else {
               if (this._selectionRange != 0) {
                  int var15 = Math.min(var14 + this._selectionRange, var14);
                  int var16 = Math.max(var14 + this._selectionRange, var14);
                  this.invalidateRange(var15, var16);
               }

               this._selectionRange = 0;
            }
         } else if ((var2 & 1) != 0) {
            if (this._selectionRange != 0) {
               int var5 = Math.min(this._cursor + this._selectionRange, this._cursor);
               int var6 = Math.max(this._cursor + this._selectionRange, this._cursor);
               this.invalidateRange(var5, var6);
               this._selectionRange = 0;
            }

            int var11 = this.getFirstVisibleLine(var1);
            int var13 = this.getVisibleLinesPageDown(var11);
            if (var1 < 0) {
               if (this._cursor == var11) {
                  this._cursor = this._cursor - this.getVisibleLinesPageUp(this._cursor);
                  this._cursor = Math.max(0, this._cursor);
               } else {
                  this._cursor = var11;
               }
            } else {
               if (this._cursor == var11 + var13 - 1) {
                  this._cursor = this._cursor + this.getVisibleLinesPageDown(this._cursor);
               } else {
                  this._cursor = var11 + var13 - 1;
               }

               this._cursor = Math.min(this._cursor, this._size - 1);
            }

            var1 = 0;
         }

         this.calcFocusRect(false);
         if (var1 == 0 && Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
         }

         return var1;
      } else {
         return var1;
      }
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
      }

      if (this._size != 0) {
         int var5 = this.getRowForY(var2);
         if (var5 < this._size && var5 >= 0) {
            this._cursor = var5;
            this._selectionRange = 0;
            this.focusChangeNotify(2);
            this.calcFocusRect(true);
         }
      }
   }

   @Override
   protected void onFocus(int var1) {
      if (Ui.isTTSEnabled()) {
         super.accessibleEventOccurred(1, new Object(1), new Object(2), this);
      }

      if (this._cursor < 0) {
         if (var1 > 0) {
            this._cursor = Math.min(0, this._size - 1);
         } else if (var1 != 0) {
            this._cursor = this._size - 1;
         }
      }

      this.calcFocusRect(false);
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private boolean searchEntryFor(String var1, int var2) {
      int var3 = this._callback.indexOfList(this, var1, var2);
      if (var3 != -1) {
         this.setSelectedIndex(var3);
         this.focusChangeNotify(2);
         if (Ui.isTTSEnabled()) {
            super.accessibleEventOccurred(6, new Object(1), new Object(2), this);
         }
      }

      return var3 != -1;
   }

   private void searchEntryForMultipleChars(char var1, int var2) {
      throw new RuntimeException("cod2jar: string-special");
   }

   public void setCallback(ListFieldCallback var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setEmptyString(ResourceBundleFamily var1, int var2, int var3) {
      if (var1 != null) {
         var1.getString(var2);
         this.setEmptyStringFamily(var1, var2);
      } else {
         this.setEmptyStringFamily(CommonResource.getBundle(), 1012);
      }

      this._emptyStyle = var3;
   }

   public void setEmptyString(String var1, int var2) {
      this._rbId = 0;
      this._rbName = null;
      this._emptyString = var1;
      this._emptyStyle = var2;
   }

   private void setEmptyStringFamily(ResourceBundleFamily var1, int var2) {
      this._rbId = var1.getId();
      this._rbName = var1.getName();
      this._rbKey = var2;
      this._cachedLocaleCode = 0;
   }

   public void setRowHeight(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setRowHeight(int var1, int var2) {
      if (this._rowHeightAdjuster.setRowHeight(var1, var2)) {
         this.triggerUpdateLayout();
      }
   }

   public void setSearchable(boolean var1) {
      if (var1) {
         this._prefix = (StringBuffer)(new Object());
      } else {
         this._prefix = null;
      }
   }

   public void setSelectedIndex(int var1) {
      if (this._cursor >= 0 && this._selectionRange != 0) {
         int var2 = this._selectionRange > 0 ? this._cursor : this._cursor + this._selectionRange;
         int var3 = this._selectionRange > 0 ? this._cursor + this._selectionRange : this._cursor;
         this.invalidateRange(var2, var3);
      }

      this.calcSelectedIndex(var1);
      this.calcFocusRect(true);
   }

   public void setSize(int var1) {
      this.setSize(var1, 0);
   }

   public void setSize(int var1, int var2) {
      boolean var3 = this._size != var1;
      this._size = var1;
      this._rowHeightAdjuster.setSize(var1);
      this.calcSelectedIndex(var2);
      this._rowCached = -1;
      this._rowCachedY = -1;
      this.calcFocusRect(false);
      this.fieldChangeNotify(Integer.MIN_VALUE);
      Manager var4 = this.getManager();
      if (var4 != null && var4.isValidLayout()) {
         if (var3) {
            this.updateLayout();
         }

         this.invalidate();
         this.focusAdd(false);
      }
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      int var5 = this.getRowForY(var2);
      return var5 != this._cursor ? true : this.getScreen().defaultStylusAction(0);
   }

   @Override
   protected boolean stylusTapHold(int var1, int var2, int var3, int var4) {
      int var5 = this.getRowForY(var2);
      return var5 < this._size && var5 >= 0 ? this.getScreen().onMenu(0) : true;
   }

   private void triggerUpdateLayout() {
      if (this._delayUpdateLayouts) {
         this._doUpdateLayout = true;
      } else {
         this.updateLayout();
         this._doUpdateLayout = false;
      }
   }

   private static long validateStyle(long var0) {
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }

   @Override
   public int getAdjustedY(Font var1, String var2, int var3) {
      return this._rowHeightAdjuster.getAdjustedY(var1, var2, var3);
   }

   @Override
   public int getAdjustedY(Font var1, StringBuffer var2, int var3, int var4, int var5) {
      return this._rowHeightAdjuster.getAdjustedY(var1, var2, var3, var4, var5);
   }

   @Override
   public int getAdjustedY(int var1) {
      return this._rowHeightAdjuster.getAdjustedY(var1);
   }
}
