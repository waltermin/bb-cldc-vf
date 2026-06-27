package net.rim.device.api.ui.component;

import net.rim.device.api.i18n.Locale;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.i18n.CommonResource;

public class VariableHeightListField extends Field {
   private int _size;
   private int _topRow;
   private int _cursor;
   private boolean _upScrollArrowVisible;
   private boolean _downScrollArrowVisible;
   private boolean _layoutOnSizeChange;
   private XYRect _focusRect = (XYRect)(new Object());
   private int _selectionRange;
   private VariableHeightListFieldCallback _callback;
   private String _emptyString;
   private int _emptyStyle = 4;
   private long _rbId;
   private int _cachedLocaleCode;
   private int _rbKey;
   private String _rbName;
   private Bitmap _upScrollArrow;
   private Bitmap _downScrollArrow;
   private ThemeAttributeSet _tasRowEven;
   private ThemeAttributeSet _tasRowOdd;
   private static Tag TAG;
   private static final String TAG_ROW_EVEN_SUFFIX;
   private static final String TAG_ROW_ODD_SUFFIX;
   public static final int MULTI_SELECT;
   public static final int NO_ALTED_PAGE_UP_DOWN;

   public VariableHeightListField() {
      this(0, 0);
   }

   public VariableHeightListField(int var1) {
      this(var1, 0);
   }

   public VariableHeightListField(int var1, long var2) {
      super(validateStyle(var2));
      this.setTag(TAG);
      this._size = 0;
      this._topRow = 0;
      this._cursor = -1;
      this._selectionRange = 0;
      this.setSize(var1, 0);
      if (this instanceof VariableHeightListFieldCallback) {
         this._callback = (VariableHeightListFieldCallback)this;
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

      if (this._cursor >= 0) {
         this._focusRect.y = this.getYForRow(this._cursor);
         this._focusRect.height = this.getRowHeight(this._cursor);
      }

      if (var1) {
         this.focusAdd(true);
      }
   }

   private void calcSelectedIndex(int var1) {
      this.calcSelectedIndex(var1, false, false);
   }

   private void calcSelectedIndex(int var1, boolean var2) {
      this.calcSelectedIndex(var1, var2, false);
   }

   private void calcSelectedIndex(int var1, boolean var2, boolean var3) {
      if (this._size == 0) {
         this._cursor = -1;
         this._topRow = 0;
         this._selectionRange = 0;
         this.updateScrollbar();
      } else {
         if (var3) {
            this.focusRemove();
         }

         this._cursor = MathUtilities.clamp(0, var1, this._size - 1);
         this._selectionRange = 0;
         if (var2 || this._cursor < this._topRow) {
            this.setTopRow(this._cursor, this._cursor);
         }

         this.makeFocusVisible(var3);
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

   private int computeFieldHeight() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void delete(int var1) {
      boolean var2 = false;
      if (var1 >= 0 && var1 <= this._size) {
         this._selectionRange = 0;
         this._size--;
         if (var1 < this._topRow) {
            this._topRow--;
         }

         if (var1 < this._cursor) {
            this._cursor--;
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

         this.updateScrollbar();
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
   public int getAccessibleStateSet() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
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

   public VariableHeightListFieldCallback getCallback() {
      return this._callback;
   }

   public String getEmptyString() {
      this.checkLocale();
      return this._emptyString != null ? this._emptyString : CommonResource.getString(1012);
   }

   public int getEmptyStringStyle() {
      return this._emptyStyle;
   }

   public int getFirstVisibleRow() {
      return this._topRow;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      var1.set(this._focusRect);
   }

   public int getLastVisibleRow() {
      int var1 = this._topRow + this.getVisibleLinesPageDown(this._topRow) - 1;
      if (var1 >= this._size) {
         var1 = this._size - 1;
      }

      return var1;
   }

   @Override
   public int getPreferredWidth() {
      return this._callback.getPreferredWidth(this);
   }

   protected int getRowHeight(int var1) {
      return this._callback != null ? this._callback.getRowHeight(this, var1) : 0;
   }

   protected int getRowHeight(int var1, int var2) {
      int var3 = this._topRow;
      this._topRow = var1;
      int var4 = this.getRowHeight(var2);
      this._topRow = var3;
      return var4;
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

   public int getTopRow() {
      return this._topRow;
   }

   private int getVisibleLinesPageDown(int var1) {
      Manager var2 = this.getManager();
      int var3 = this.getHeight();
      if (var2 != null && var2.getVisibleHeight() < var3) {
         var3 = var2.getVisibleHeight();
      }

      for (int var4 = var1; var4 < this._size; var4++) {
         int var5 = this.getRowHeight(var4);
         if (var5 > var3) {
            return var4 - var1;
         }

         var3 -= var5;
      }

      return this._size - var1;
   }

   private int getVisibleLinesPageUp(int var1) {
      int var2 = Math.min(this.getHeight(), this.getManager().getVisibleHeight());

      for (int var3 = var1; var3 >= 0; var3--) {
         int var4 = this.getRowHeight(var3);
         if (var4 > var2) {
            return var1 - var3;
         }

         var2 -= var4;
      }

      return var1;
   }

   protected int getYForRow(int var1) {
      if (var1 < this._topRow) {
         throw new Object();
      }

      int var2 = 0;

      for (int var3 = this._topRow; var3 < var1; var3++) {
         var2 += this.getRowHeight(var3);
      }

      return var2;
   }

   public void insert(int var1) {
      boolean var2 = false;
      if (var1 >= 0 && var1 <= this._size) {
         this._selectionRange = 0;
         if (var1 <= this._topRow) {
            this._topRow++;
            this.updateScrollbar();
         }

         if (this._size == 0) {
            this._cursor = 0;
            this._topRow = 0;
         } else if (var1 <= this._cursor) {
            this._cursor++;
            var2 = true;
         }

         this._size++;
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
         this.updateScrollbar();
      } else {
         throw new Object(var1);
      }
   }

   @Override
   public void invalidate() {
      this.makeFocusVisible(false);
      this.updateScrollbar();
      super.invalidate();
   }

   public void invalidate(int var1) {
      if (var1 >= this._topRow) {
         this.invalidate(0, this.getYForRow(var1), this.getWidth(), this.getRowHeight(var1));
      }
   }

   public void invalidateRange(int var1, int var2) {
      if (var2 < var1) {
         throw new Object();
      }

      if (var1 < this._topRow) {
         var1 = this._topRow;
      }

      if (var2 >= this._size) {
         var2 = this._size - 1;
      }

      int var3 = this.getYForRow(var1);
      var2 = MathUtilities.clamp(var1, var2, this._topRow + this.getVisibleLinesPageDown(this._topRow) + 1);
      this.invalidate(0, var3, this.getContentWidth(), this.getYForRow(var2 + 1) - var3);
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (super.getAccessibleStateSet() & var1) != 0;
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

   public boolean isEmpty() {
      return this._size == 0;
   }

   protected boolean isOnTopRow(int var1) {
      return var1 == this._topRow;
   }

   @Override
   protected void layout(int var1, int var2) {
      this._focusRect.x = 0;
      this._focusRect.width = var1;
      this.calcFocusRect(false);
      if (var2 > 100000) {
         this._layoutOnSizeChange = true;
         var2 = this.computeFieldHeight();
      }

      this.setExtent(var1, var2);
      this.updateScrollbar();
   }

   private void makeFocusVisible(boolean var1) {
      if (this._cursor < this._topRow) {
         if (this._cursor >= 0) {
            this.setTopRow(this._cursor, this._cursor);
         }
      } else {
         int var2 = this.getYForRow(this._cursor);
         int var3 = this.getRowHeight(this._cursor);
         int var4 = this.getContentHeight();
         if (var4 > 0 && var2 + var3 > var4) {
            int var5 = var4;

            int var6;
            for (var6 = this._cursor; var6 >= 0; var6--) {
               var3 = this.getRowHeight(var6);
               if (var5 - var3 < 0) {
                  break;
               }

               var5 -= var3;
            }

            this.setTopRow(var6 + 1, this._cursor);
         } else {
            this.setTopRow(this._topRow, this._cursor);
         }
      }

      this.updateScrollbar();
      this.calcFocusRect(var1);
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if (this._size != 0 && (var2 & 65536) == 0) {
         this.setMuddy(false);
         if ((var2 & 1) == 0 || this.isStyle(8)) {
            int var11 = this._cursor + var1;
            int var13 = this._cursor;
            this._cursor = MathUtilities.clamp(0, var11, this._size - 1);
            var1 = var11 - this._cursor;
            if ((var2 & 2) != 0) {
               if (this.isStyle(2)) {
                  boolean var7 = this._selectionRange != 0;
                  this._selectionRange = this._selectionRange + (var13 - this._cursor);
                  if (this._selectionRange != 0 || var7) {
                     int var8 = Math.min(var13, this._cursor + 1);
                     int var9 = Math.max(var13, this._cursor - 1);
                     this.invalidateRange(var8, var9);
                  }
               } else {
                  this._selectionRange = 0;
               }

               var1 = 0;
            } else {
               if (this._selectionRange != 0) {
                  int var14 = Math.min(var13 + this._selectionRange, var13);
                  int var15 = Math.max(var13 + this._selectionRange, var13);
                  this.invalidateRange(var14, var15);
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

            int var10 = this._topRow;
            int var12 = this.getVisibleLinesPageDown(var10);
            if (var1 < 0) {
               if (this.isOnTopRow(this._cursor)) {
                  this._cursor = this._cursor - this.getVisibleLinesPageUp(this._cursor);
                  this._cursor = Math.max(0, this._cursor);
               } else {
                  this._cursor = var10;
               }
            } else {
               if (this._cursor == var10 + var12 - 1) {
                  this._cursor = this._cursor + this.getVisibleLinesPageDown(this._cursor);
               } else {
                  this._cursor = var10 + var12 - 1;
               }

               this._cursor = Math.min(this._cursor, this._size - 1);
            }

            var1 = 0;
         }

         this.makeFocusVisible(false);
         return var1;
      } else {
         return var1;
      }
   }

   @Override
   protected void onFocus(int var1) {
      if (this._cursor < 0) {
         if (var1 > 0) {
            this._cursor = Math.min(0, this._size - 1);
            this._topRow = this._cursor;
            this.updateScrollbar();
         } else if (var1 != 0) {
            this._cursor = this._size - 1;
            this._topRow = this._cursor - this.getVisibleLinesPageUp(this._cursor);
            this.updateScrollbar();
         }
      }

      this.calcFocusRect(false);
   }

   @Override
   protected void paint(Graphics var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private void paintVerticalScrollbar(Graphics var1) {
      if (this._upScrollArrowVisible) {
         if (this._upScrollArrow != null) {
            Manager var4 = this.getManager();
            int var5 = var4.getWidth();
            int var2 = var5 - this._upScrollArrow.getWidth();
            var1.setOverlay(0, this._upScrollArrow, var2, 0);
         }
      } else {
         var1.setOverlay(0, null, 0, 0);
      }

      if (this._downScrollArrowVisible) {
         if (this._downScrollArrow != null) {
            Manager var7 = this.getManager();
            int var8 = var7.getWidth();
            int var6 = var8 - this._downScrollArrow.getWidth();
            int var3 = var7.getHeight() - this._downScrollArrow.getHeight();
            var1.setOverlay(1, this._downScrollArrow, var6, var3);
            return;
         }
      } else {
         var1.setOverlay(1, null, 0, 0);
      }
   }

   protected void scrollRegionVertically(Graphics var1, int var2, int var3, int var4, int var5) {
      var1.copyArea(0, var2, this.getContentWidth(), var3, 0, var4);
   }

   public void setCallback(VariableHeightListFieldCallback var1) {
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

   public void setSelectedIndex(int var1) {
      this.setSelectedIndex(var1, false);
   }

   public void setSelectedIndex(int var1, boolean var2) {
      if (this._cursor >= 0 && this._selectionRange != 0) {
         int var3 = this._selectionRange > 0 ? this._cursor : this._cursor + this._selectionRange;
         int var4 = this._selectionRange > 0 ? this._cursor + this._selectionRange : this._cursor;
         this.invalidateRange(var3, var4);
      }

      this.calcSelectedIndex(var1, var2, true);
      this.updateScrollbar();
      this.makeFocusVisible(true);
   }

   public void setSize(int var1) {
      this.setSize(var1, 0);
   }

   public void setSize(int var1, int var2) {
      boolean var3 = this._size != var1;
      this._size = var1;
      this.calcSelectedIndex(var2);
      this.calcFocusRect(false);
      this.fieldChangeNotify(Integer.MIN_VALUE);
      Manager var4 = this.getManager();
      if (var4 != null && var4.isValidLayout()) {
         if (var3 && this._layoutOnSizeChange) {
            this.updateLayout();
         }

         this.invalidate();
         this.focusAdd(false);
      }

      this.updateScrollbar();
   }

   protected void setTopRow(int var1, int var2) {
      if (var1 != this._topRow) {
         Graphics var3 = this.getGraphics0();
         int var4 = this._topRow;
         int var5 = this.getLastVisibleRow();
         this._topRow = var1;
         int var6 = this.getLastVisibleRow();
         this._topRow = var4;
         if (var1 < var4 && var6 >= var4) {
            int var13 = this.getYForRow(var6 + 1) + this.getRowHeight(var6 + 1);
            this._topRow = var1;
            int var15 = this.getYForRow(var4);
            this.scrollRegionVertically(var3, 0, var13, var15, var4);
            this.invalidate(0, 0, this.getContentWidth(), var15);
            int var17 = Math.min(this._cursor, this._cursor + this._selectionRange);
            int var19 = Math.max(this._cursor, this._cursor + this._selectionRange);
            if (var17 < var4 && var19 >= var4 || var17 <= var6 && var19 > var6) {
               this.invalidate();
            }
         } else if (var6 > var5 && var1 < var5) {
            int var7 = this.getYForRow(var1);
            int var8 = this.getContentHeight();
            int var9 = var8 - var7;
            this._topRow = var1;
            this.scrollRegionVertically(var3, var7, var9, -var7, var4);
            int var10 = this.getManager().getHeight();
            this.invalidate(0, var10 - var7, this.getContentWidth(), var8);
            int var11 = Math.min(this._cursor, this._cursor + this._selectionRange);
            int var12 = Math.max(this._cursor, this._cursor + this._selectionRange);
            if (var11 < var1 && var12 >= var1 || var11 <= var5 && var12 > var5) {
               this.invalidate();
            }
         } else {
            this._topRow = var1;
            this.invalidate();
         }

         if (var6 >= this._size - 1) {
            int var14 = this._size - 1;
            int var16 = this.getYForRow(var14);
            int var18 = this.getRowHeight(var14);
            int var20 = this.getContentHeight();
            if (var16 + var18 < var20) {
               this.invalidate(0, var16, this.getContentWidth(), var20);
            }
         }
      }
   }

   private void updateScrollbar() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   private static long validateStyle(long var0) {
      if ((var0 & 54043195528445952L) == 0) {
         var0 |= 18014398509481984L;
      }

      return var0;
   }
}
