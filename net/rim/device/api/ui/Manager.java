package net.rim.device.api.ui;

import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.ui.Cursor;
import net.rim.device.internal.ui.component.Scrollbar;
import net.rim.vm.Array;

public class Manager extends Field {
   private int _verticalQuanta = 1;
   private int _horizontalQuanta = 1;
   private int _virtualWidth;
   private int _virtualHeight;
   private int _horizontalScroll;
   private int _verticalScroll;
   private Field[] _fields = new Field[5];
   private int _fieldsCount;
   private Field _fieldWithFocus;
   private int _fieldWithFocusIndex = -1;
   private boolean _validLayout;
   private int _indicatorX;
   private int _indicatorY;
   private int _indicatorWidth;
   private int _indicatorHeight;
   private int _scrollBarX;
   private int _scrollBarY;
   private int _scrollBarWidth;
   private int _scrollBarHeight;
   private int _stylusDownY;
   private int _stylusDownVerticalScroll;
   private boolean _stylusDraggingIndicator;
   private boolean _nonfocusableArrowOverride;
   private ScrollChangeListener _scrollListener;
   private Scrollbar _verticalScrollbar;
   private Scrollbar _horizontalScrollbar;
   private static final boolean haveStylus;
   private static Tag TAG;
   private static Tag THEME_SCROLLBAR_TAG;
   protected static final long VERTICAL_SCROLL_MASK;
   public static final long VERTICAL_SCROLL;
   public static final long NO_VERTICAL_SCROLL;
   protected static final long HORIZONTAL_SCROLL_MASK;
   public static final long HORIZONTAL_SCROLL;
   public static final long NO_HORIZONTAL_SCROLL;
   protected static final long VERTICAL_SCROLLBAR_MASK;
   public static final long VERTICAL_SCROLLBAR;
   public static final long NO_VERTICAL_SCROLLBAR;
   protected static final long HORIZONTAL_SCROLLBAR_MASK;
   public static final long HORIZONTAL_SCROLLBAR;
   public static final long NO_HORIZONTAL_SCROLLBAR;
   public static final long LEAVE_BLANK_SPACE;
   public static final long INCREMENTAL_LAYOUT;
   public static final long NO_SCROLL_RESET;
   public static final long SCROLLBAR_ELEVATOR;
   public static final long USE_ALL_SCREEN_HEIGHT;
   private static final long MANAGER_STYLES;
   public static final int UPWARD;
   public static final int DOWNWARD;
   public static final int LEFTWARD;
   public static final int RIGHTWARD;
   public static final int TOPMOST;
   public static final int BOTTOMMOST;
   public static final int LEFTMOST;
   public static final int RIGHTMOST;
   public static final int QUANTA_FONT;
   private static final int INDICATOR_WIDTH;
   private static final int SCROLLBAR_WIDTH;
   private static final int SCROLLBAR_GAP;
   private static final int SCROLLBAR_TOTAL_WIDTH;
   private static final int SCROLLBAR_TOTAL_HEIGHT;
   private static final int MIN_INDICATOR_HEIGHT;
   private static final int OUTSIDE_SCROLLBAR;
   private static final int ABOVE_INDICATOR;
   private static final int ON_INDICATOR;
   private static final int BELOW_INDICATOR;

   protected Manager(long var1) {
      super(validateStyle(var1));
      if (!this.validateFieldStyle(var1)) {
         throw new Object();
      }

      this.setTag(TAG);
   }

   @Override
   public boolean acceptVisitor(FieldVisitor var1) {
      if (!var1.visit(this, 1)) {
         return false;
      }

      int var2 = this.getFieldCount();

      for (int var3 = 0; var3 < var2; var3++) {
         if (!this.getField(var3).acceptVisitor(var1)) {
            return false;
         }
      }

      return var1.visit(this, 2);
   }

   public void add(Field var1) {
      this.insertInternal(var1, this._fieldsCount);
   }

   @Override
   protected void applyFont() {
      super.applyFont();

      for (int var1 = this._fieldsCount - 1; var1 >= 0; var1--) {
         this._fields[var1].applyFont();
      }
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();

      for (int var1 = this._fieldsCount - 1; var1 >= 0; var1--) {
         this._fields[var1].applyTheme();
      }
   }

   protected int calculateHorizontalScrollAmount(XYRect var1) {
      if (!this.isStyle(1125899906842624L)) {
         return 0;
      }

      int var2 = this.getContentWidth();
      int var3 = 0;
      int var4 = var1.x + var1.width - (this.getHorizontalScroll() + var2);
      if (var4 > 0) {
         var3 += var4;
      }

      var4 = var1.x - (this.getHorizontalScroll() + var3);
      if (var4 < 0) {
         int var5 = this.getHorizontalScroll() + var3 + var4;
         if (var5 < 0) {
            var4 -= var5;
         }

         var3 += var4;
      }

      return var3;
   }

   protected int calculateVerticalScrollAmount(XYRect var1) {
      if (!this.isStyle(281474976710656L)) {
         return 0;
      }

      int var2 = this.getContentHeight();
      int var3 = 0;
      int var4 = var1.y + var1.height - (this.getVerticalScroll() + var2);
      if (var4 > 0) {
         var3 += var4;
      }

      var4 = var1.y - (this.getVerticalScroll() + var3);
      if (var4 < 0) {
         int var5 = this.getVerticalScroll() + var3 + var4;
         if (var5 < 0) {
            var4 -= var5;
         }

         var3 += var4;
      }

      return var3;
   }

   @Override
   void callOnDisplayOrUndisplay(boolean var1) {
      super.callOnDisplayOrUndisplay(var1);

      for (int var2 = 0; var2 < this._fieldsCount; var2++) {
         this._fields[var2].callOnDisplayOrUndisplay(var1);
      }
   }

   public void delete(Field var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void deleteAll() {
      this.deleteRange(0, this.getFieldCount());
   }

   public void deleteRange(int var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   void doVisibilityWalk(boolean var1) {
      this.onVisibilityChange(var1);

      for (int var2 = 0; var2 < this._fieldsCount; var2++) {
         this._fields[var2].doVisibilityWalk(var1);
      }
   }

   private void ensureFieldCapacity() {
      int var1 = this._fields.length;
      if (this._fieldsCount == var1) {
         Array.resize(this._fields, var1 * 2);
      }
   }

   @Override
   void getDebugTreeHelper(int var1, StringBuffer var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return this.getField(var1);
   }

   @Override
   public int getAccessibleChildCount() {
      return this.getFieldWithFocus().getAccessibleSelectionAt(0) != null ? this.getFieldWithFocus().getAccessibleChildCount() : this.getFieldCount();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return this.getFieldWithFocus().getAccessibleSelectionAt(var1) != null
         ? this.getFieldWithFocus().getAccessibleSelectionAt(var1)
         : this.getFieldWithFocus();
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this.getFieldWithFocus().getAccessibleSelectionAt(0) != null
         ? this.getFieldWithFocus().isAccessibleChildSelected(var1)
         : this.getFieldWithFocusIndex() == var1;
   }

   public Field getField(int var1) {
      if (var1 >= this._fieldsCount) {
         throw new Object();
      } else {
         return this._fields[var1];
      }
   }

   public int getFieldAtLocation(int var1, int var2) {
      for (int var3 = this._fieldsCount - 1; var3 >= 0; var3--) {
         Field var4 = this._fields[var3];
         XYRect var5 = var4.getExtent();
         if (var5.y <= var2 && var5.x <= var1 && var2 <= var5.y + var5.height && var1 <= var5.x + var5.width) {
            return var3;
         }
      }

      return -1;
   }

   public int getFieldClosestToLocation(int var1, int var2, int var3) {
      int var4 = 0;
      int var5 = this._fieldsCount;
      byte var6 = 1;
      byte var7 = 0;
      if ((var3 & 256) != 0 || (var3 & 1024) != 0) {
         var4 = this._fieldsCount - 1;
         var5 = -1;
         var6 = -1;
         var7 = -1;
      } else if ((var3 & 512) != 0 || (var3 & 2048) != 0) {
         var7 = 1;
      }

      int var8 = -1;
      int var9 = Integer.MAX_VALUE;
      int var10 = Integer.MAX_VALUE;

      for (int var11 = var4; var11 != var5; var11 += var6) {
         Field var12 = this._fields[var11];
         XYRect var13 = var12.getExtent();
         if (var13.y <= var2 && var2 <= var13.y + var13.height) {
            var9 = 0;
            if (var13.x <= var1 && var1 <= var13.x + var13.width) {
               return var11;
            }

            int var16 = Math.abs(var13.x - var1);
            if (var16 < var10) {
               var10 = var16;
               var8 = var11;
            }
         } else {
            int var14;
            if (var7 == -1) {
               var14 = var2 - var13.y;
            } else if (var7 == 1) {
               var14 = var13.y - var2;
            } else {
               var14 = Math.abs(var13.y - var2);
            }

            if (var14 >= 0 && var14 < var9) {
               var9 = var14;
               var10 = Math.abs(var13.x - var1);
               var8 = var11;
            } else if (var14 == var9) {
               int var15 = Math.abs(var13.x - var1);
               if (var15 < var10) {
                  var10 = var15;
                  var8 = var11;
               }
            }
         }
      }

      return var8;
   }

   public int getFieldCount() {
      return this._fieldsCount;
   }

   public Field getFieldWithFocus() {
      return this._fieldWithFocus;
   }

   public int getFieldWithFocusIndex() {
      return this._fieldWithFocusIndex;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      if (this._fieldWithFocus != null) {
         this._fieldWithFocus.getFocusRect(var1);
         var1.translate(this._fieldWithFocus.getContentLeft() - this.getHorizontalScroll(), this._fieldWithFocus.getContentTop() - this.getVerticalScroll());
      } else {
         var1.set(this.getHorizontalScroll(), this.getVerticalScroll(), this.getContentWidth(), this.getContentHeight());
      }
   }

   @Override
   public void getFocusRectPhantom(XYRect var1) {
      if (this._fieldWithFocus != null) {
         this._fieldWithFocus.getFocusRectPhantom(var1);
         var1.translate(this._fieldWithFocus.getContentLeft() - this.getHorizontalScroll(), this._fieldWithFocus.getContentTop() - this.getVerticalScroll());
      } else {
         var1.set(this.getHorizontalScroll(), this.getVerticalScroll(), this.getContentWidth(), this.getContentHeight());
      }
   }

   @Override
   Graphics getGraphics0(XYRect var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   public int getHorizontalQuantization() {
      return this._horizontalQuanta;
   }

   public final int getHorizontalScroll() {
      return this._horizontalScroll;
   }

   @Override
   public Field getLeafFieldWithFocus() {
      return this._fieldWithFocus == null ? null : this._fieldWithFocus.getLeafFieldWithFocus();
   }

   public int getNavigationAxis(int var1) {
      if ((var1 & 536870912) != 0) {
         return (var1 & 65536) != 0 ? 1 : 2;
      } else {
         return (var1 & 1) == 0 ? 0 : 2;
      }
   }

   protected final int getPreferredHeightOfChild(Field var1) {
      return var1.getPreferredHeight() + var1.getPaddingTop() + var1.getPaddingBottom() + var1.getBorderTop() + var1.getBorderBottom();
   }

   protected final int getPreferredWidthOfChild(Field var1) {
      int var2 = var1.getPreferredWidth() + var1.getPaddingRight() + var1.getPaddingLeft() + var1.getBorderRight() + var1.getBorderLeft();
      if (this._verticalScrollbar != null) {
         var2 -= SCROLLBAR_TOTAL_WIDTH;
      }

      return var2;
   }

   public int getVerticalQuantization() {
      return this._verticalQuanta;
   }

   public final int getVerticalScroll() {
      return this._verticalScroll;
   }

   public final int getVirtualHeight() {
      return this._virtualHeight;
   }

   public final int getVirtualWidth() {
      return this._virtualWidth;
   }

   public int getVisibleHeight() {
      return this.getVisibleHeight(Integer.MAX_VALUE, 0);
   }

   int getVisibleHeight(int var1, int var2) {
      if (0 > var2) {
         var1 += var2;
         var2 = 0;
      }

      if (this.getContentHeight() < var1 + var2) {
         var1 = this.getContentHeight() - var2;
      }

      return this.getManager().getVisibleHeight(var1, var2 + this.getContentTop());
   }

   public int getVisibleWidth() {
      return this.getVisibleWidth(Integer.MAX_VALUE, 0);
   }

   int getVisibleWidth(int var1, int var2) {
      if (0 > var2) {
         var1 += var2;
         var2 = 0;
      }

      if (this.getContentWidth() < var1 + var2) {
         var1 = this.getContentWidth() - var2;
      }

      return this.getManager().getVisibleWidth(var1, var2 + this.getContentLeft());
   }

   private int getXForScrollArrowLeft() {
      return this.getHorizontalScroll() - this.getPaddingLeft();
   }

   private int getXForScrollArrowRight(int var1) {
      int var2 = this.getHorizontalScroll() + this.getContentWidth() + this.getPaddingRight();
      return var2 - var1;
   }

   protected boolean incrementalLayout(int var1, int var2, int var3) {
      return false;
   }

   public void insert(Field var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private void insertInternal(Field var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void invalidate() {
      int var1 = 0;
      int var2 = 0;
      if (this._verticalScrollbar != null) {
         var1 = SCROLLBAR_TOTAL_WIDTH;
      }

      if (this._horizontalScrollbar != null) {
         var2 = SCROLLBAR_TOTAL_HEIGHT;
      }

      this.invalidate(this.getHorizontalScroll(), this.getVerticalScroll(), this.getWidth() + var1, this.getHeight() + var2);
   }

   @Override
   protected void invalidate(int var1, int var2, int var3, int var4) {
      super.invalidate(var1 - this.getHorizontalScroll(), var2 - this.getVerticalScroll(), var3, var4);
   }

   protected void invalidateFieldRange(int var1, int var2) {
      var1 = var1 < 0 ? 0 : var1;
      var2 = var2 < this._fieldsCount ? var2 + 1 : this._fieldsCount;

      for (int var3 = var1; var3 < var2; var3++) {
         this._fields[var3].invalidate();
      }
   }

   @Override
   void invalidateLayout0() {
      this.setValidLayout(false);

      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         this._fields[var1].invalidateLayout0();
      }
   }

   @Override
   protected boolean invokeAction(int var1) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.invokeAction(var1) : false;
   }

   @Override
   public boolean isDataValid() {
      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         if (!this._fields[var1].isDataValid()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public boolean isDirty() {
      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         if (this._fields[var1].isDirty()) {
            return true;
         }
      }

      return false;
   }

   protected boolean isDownArrowShown() {
      return this.getVerticalScroll() + this.getContentHeight() < this._virtualHeight;
   }

   @Override
   public boolean isFocusable() {
      for (int var1 = this._fieldsCount - 1; var1 >= 0; var1--) {
         if (this._fields[var1].isFocusable()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isMuddy() {
      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         if (this._fields[var1].isMuddy()) {
            return true;
         }
      }

      return false;
   }

   protected boolean isScrollCopyable() {
      return !this.isFieldTransparent();
   }

   @Override
   public boolean isSelecting() {
      return this._fieldWithFocus != null ? this._fieldWithFocus.isSelecting() : false;
   }

   protected boolean isUpArrowShown() {
      return this.getVerticalScroll() > 0;
   }

   public final boolean isValidLayout() {
      return this._validLayout;
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      boolean var4 = super.keyChar(var1, var2, var3);
      if (this._fieldWithFocus != null && !var4) {
         var4 = this._fieldWithFocus.keyChar(var1, var2, var3);
      }

      return var4;
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      boolean var4 = super.keyControl(var1, var2, var3);
      if (this._fieldWithFocus != null && !var4) {
         var4 = this._fieldWithFocus.keyControl(var1, var2, var3);
      }

      return var4;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      boolean var3 = super.keyDown(var1, var2);
      if (this._fieldWithFocus != null && !var3) {
         var3 = this._fieldWithFocus.keyDown(var1, var2);
      }

      return var3;
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      boolean var3 = super.keyRepeat(var1, var2);
      if (this._fieldWithFocus != null && !var3) {
         var3 = this._fieldWithFocus.keyRepeat(var1, var2);
      }

      return var3;
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      boolean var3 = super.keyStatus(var1, var2);
      if (this._fieldWithFocus != null && !var3) {
         var3 = this._fieldWithFocus.keyStatus(var1, var2);
      }

      return var3;
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      boolean var3 = super.keyUp(var1, var2);
      if (this._fieldWithFocus != null && !var3) {
         var3 = this._fieldWithFocus.keyUp(var1, var2);
      }

      return var3;
   }

   @Override
   protected final void layout(int var1, int var2) {
      this._virtualHeight = -1;
      int var3 = 0;
      int var4 = 0;
      long var5 = this.getStyle();
      Theme var7 = ThemeManager.getActiveTheme();
      if (var7.getAttributeSet(THEME_SCROLLBAR_TAG) != null) {
         if (this.isStyle(17592186044416L) && (var5 & 281474976710656L) > 0) {
            var3 = SCROLLBAR_TOTAL_WIDTH;
            this._verticalScrollbar = (Scrollbar)(new Object(true));
            this._verticalScrollbar.setManager(this, 0);
            if (this._scrollListener == null) {
               this._verticalScrollbar.setClient(this);
            }
         }

         if (this.isStyle(70368744177664L) && (var5 & 1125899906842624L) > 0) {
            var4 = SCROLLBAR_TOTAL_HEIGHT;
            this._horizontalScrollbar = (Scrollbar)(new Object(true, true));
            this._horizontalScrollbar.setManager(this, 0);
            if (this._scrollListener == null) {
               this._horizontalScrollbar.setClient(this);
            }
         }
      }

      this.sublayout(var1 - var3, var2 - var4);
      if (this._verticalScrollbar != null) {
         XYRect var8 = this.getExtent();
         this.setExtent(var8.width + var3, var8.height);
      }

      if (this._horizontalScrollbar != null) {
         XYRect var10 = this.getExtent();
         this.setExtent(var10.width, var10.height + var4);
      }

      if (this._verticalQuanta != 1) {
         int var11 = this._verticalQuanta == -1 ? this.getFont().getHeight() : this._verticalQuanta;
         int var9 = this.getContentHeight() % var11;
         this.setExtent(this.getContentWidth(), this.getContentHeight() - var9);
      }

      if (this._horizontalQuanta != 1) {
         int var12 = this._horizontalQuanta == -1 ? this.getFont().getHeight() : this._horizontalQuanta;
         int var13 = this.getContentWidth() % var12;
         this.setExtent(this.getContentWidth() - var13, this.getContentHeight());
      }

      if (this._virtualHeight == -1) {
         this.setVirtualExtent(this.getContentWidth(), this.getContentHeight());
      }

      this.removeBlankSpace();
      this.setValidLayout(true);
   }

   protected final void layoutChild(Field var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      boolean var4 = false;
      int var5 = this.getNavigationAxis(var2);

      label74:
      while (var1 != 0) {
         if (this._fieldWithFocus == null) {
            return var1;
         }

         int var6 = this._fieldWithFocus.moveFocus(var1, var2, var3);
         if ((var2 & 8) != 0) {
            var6 = 0;
         }

         if (var6 != var1) {
            if (!(this._fieldWithFocus instanceof Manager)) {
               this._fieldWithFocus.focusChangeNotify(2);
            }

            if (var6 == 0) {
               var1 = 0;
               break;
            }
         }

         var1 = var6;
         int var7 = this._fieldWithFocusIndex;
         Field var8 = this._fieldWithFocus;
         int var9 = MathUtilities.clamp(-1, var1, 1);

         do {
            this._fieldWithFocusIndex = this.nextFocus(var9, var5);
            if (this._fieldWithFocusIndex == -1 || this._fieldWithFocusIndex == var7) {
               this._fieldWithFocusIndex = var7;
               this._fieldWithFocus = var8;
               break label74;
            }

            this._fieldWithFocus = this._fields[this._fieldWithFocusIndex];
         } while (!this._fieldWithFocus.isFocusable());

         var8.onUnfocus();
         var8.focusChangeNotify(3);
         this._fieldWithFocus.onFocus(var9);
         this._fieldWithFocus.focusChangeNotify(1);
         var1 -= var9;
         var4 = true;
      }

      if (var4) {
         this.focusChangeNotify(2);
      }

      if (var1 != 0 && !this.isStyle(144115188075855872L)) {
         if (var1 < 0) {
            if (this.getVerticalScroll() > 0 && var5 != 1) {
               XYRect var11 = Ui.getTmpXYRect();
               var11.set(this.getHorizontalScroll(), 0, 0, 0);
               this.makeRegionVisible(true, var11, true);
               Ui.returnTmpXYRect(var11);
               return var1;
            }

            if (this.getHorizontalScroll() > 0 && var5 == 1) {
               XYRect var10 = Ui.getTmpXYRect();
               var10.set(0, this.getVerticalScroll(), 0, 0);
               this.makeRegionVisible(true, var10, true);
               Ui.returnTmpXYRect(var10);
               return var1;
            }
         } else if (this.getVerticalScroll() + this.getContentHeight() < this._virtualHeight && var5 != 1) {
            XYRect var12 = Ui.getTmpXYRect();
            var12.set(this.getHorizontalScroll(), this._virtualHeight, 0, 0);
            this.makeRegionVisible(true, var12, true);
            Ui.returnTmpXYRect(var12);
         }
      }

      return var1;
   }

   private void notifyVisibilityChange(int var1, int var2, boolean var3) {
      if (this.isValidLayout()) {
         Screen var4 = this.getScreen();
         if (var4 != null) {
            if (var4.isDisplayed()) {
               if (var3) {
                  for (int var6 = var1; var6 < var1 + var2; var6++) {
                     this._fields[var6].callOnDisplayOrUndisplay(true);
                  }
               } else {
                  for (int var5 = var1; var5 < var1 + var2; var5++) {
                     this._fields[var5].callOnDisplayOrUndisplay(false);
                  }
               }
            }

            if (var4.isVisible()) {
               for (int var7 = var1; var7 < var1 + var2; var7++) {
                  this._fields[var7].doVisibilityWalk(var3);
               }
            }
         }
      }
   }

   @Override
   protected void paint(Graphics var1) {
      if (this.isOkayForPainting()) {
         this.subpaint(var1);
         if (this.isOkayForPainting()) {
            this.paintScrollbars(var1);
         }
      }
   }

   private boolean isOkayForPainting() {
      UiEngineImpl var1 = UiEngineImpl.getUiEngine();
      return var1 == null || var1.getGlobalScreen() != null || var1.getApplication().isForeground();
   }

   protected final void paintChild(Graphics var1, Field var2) {
      var2.paintSelf(var1, true, 0, 0);
   }

   @Override
   void paintSelf(Graphics var1, boolean var2, int var3, int var4) {
      super.paintSelf(var1, var2, -this.getHorizontalScroll(), -this.getVerticalScroll());
   }

   protected final void clearCustomScrollArrows() {
      Graphics.resetOverlays();
   }

   protected boolean drawLeafFocus(boolean var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setNonfocusableOverride(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void paintScrollbars(Graphics var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void replace(Field var1, Field var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected final void removeBlankSpace() {
      if (!this.isStyle(288230376151711744L)) {
         if (this.getVerticalScroll() + this.getContentHeight() > this._virtualHeight) {
            this._verticalScroll = this._virtualHeight - this.getContentHeight();
            if (this.getVerticalScroll() < 0) {
               this._verticalScroll = 0;
            }

            this.invalidate();
         }

         if (this.getHorizontalScroll() + this.getContentWidth() > this._virtualWidth) {
            this._horizontalScroll = this._virtualWidth - this.getContentWidth();
            if (this.getHorizontalScroll() < 0) {
               this._horizontalScroll = 0;
            }

            this.invalidate();
         }
      }
   }

   private void removeField(int var1, Field var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void removeFieldHelper(int var1, Field var2) {
      for (int var3 = var1 + 1; var3 < this._fieldsCount; var3++) {
         this._fields[var3].setIndex(var3 - 1);
      }

      System.arraycopy(this._fields, var1 + 1, this._fields, var1, this._fieldsCount - var1 - 1);
      this._fieldsCount--;
      this._fields[this._fieldsCount] = null;
      if (var1 < this._fieldWithFocusIndex) {
         this._fieldWithFocusIndex--;
      }

      var2.setManager(null, -1);
   }

   final void runLayoutUpdate(int var1, int var2, int var3) {
      if (this.isValidLayout()) {
         Screen var4 = this.getScreen();
         if (var4 != null) {
            this.runLayoutUpdate0(var1, var2, var3);
            if (var2 > 0 && var4.getFieldWithFocus() == null) {
               boolean var5 = false;

               for (int var6 = 0; var6 < var2; var6++) {
                  if (this._fields[var1 + var6].isFocusable()) {
                     var5 = true;
                     break;
                  }
               }

               if (var5) {
                  var4.setFocus();
               }
            }
         }
      }
   }

   void runLayoutUpdate0(int var1, int var2, int var3) {
      if (!this.incrementalLayout(var1, var2, var3)) {
         this.getManager().runLayoutUpdate0(this.getIndex(), 1, 1);
      } else {
         this.getScreen().ensureFocusVisible();
      }
   }

   public void setFieldWithFocus(Field var1) {
      this._fieldWithFocusIndex = var1.getIndex();
      this._fieldWithFocus = var1;
   }

   final void setValidLayout(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void sublayout(int var1, int var2) {
      throw null;
   }

   protected void subpaint(Graphics var1) {
      int var2 = this.getFieldCount();

      for (int var3 = 0; var3 < var2; var3++) {
         Field var4 = this.getField(var3);
         this.paintChild(var1, var4);
      }
   }

   @Override
   protected void onFocus(int var1) {
      super.onFocus(var1);
      this._fieldWithFocusIndex = this.firstFocus(var1);
      if (this._fieldWithFocusIndex != -1) {
         this._fieldWithFocus = this._fields[this._fieldWithFocusIndex];
         this._fieldWithFocus.onFocus(var1);
         this._fieldWithFocus.focusChangeNotify(1);
      }
   }

   protected int firstFocus(int var1) {
      switch (var1) {
         case -1:
            for (int var3 = this._fieldsCount - 1; var3 >= 0; var3--) {
               if (this._fields[var3].isFocusable()) {
                  return var3;
               }
            }
            break;
         case 1:
            for (int var2 = 0; var2 < this._fieldsCount; var2++) {
               if (this._fields[var2].isFocusable()) {
                  return var2;
               }
            }
      }

      return -1;
   }

   @Override
   protected void onUnfocus() {
      if (this._fieldWithFocus != null) {
         this._fieldWithFocus.onUnfocus();
         this._fieldWithFocus.focusChangeNotify(3);
         this._fieldWithFocus = null;
         this._fieldWithFocusIndex = -1;
      }

      super.onUnfocus();
   }

   protected int nextFocus(int var1, boolean var2) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   protected int nextFocus(int var1, int var2) {
      if (this._fieldWithFocusIndex <= -1) {
         return -1;
      }

      switch (var1) {
         case -1:
            return this._fieldWithFocusIndex - 1;
         case 1:
            if (this._fieldWithFocusIndex + 1 == this.getFieldCount()) {
               return -1;
            }

            return this._fieldWithFocusIndex + 1;
         default:
            throw new Object();
      }
   }

   @Override
   protected void moveFocus(int var1, int var2, int var3, int var4) {
      this.moveFocusToPoint(var1, var2, var3, var4, 0, false);
   }

   @Override
   boolean moveFocusToPoint(int var1, int var2, int var3, int var4) {
      int var5 = var3 & 3855;
      return this.moveFocusToPoint(var1, var2, var3, var4, var5, true);
   }

   private boolean moveFocusToPoint(int var1, int var2, int var3, int var4, int var5, boolean var6) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected boolean moveFocus(int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean setFocus(int var1, int var2, int var3) {
      return this.getScreen().setFocus(this, var1, var2, var3, 0);
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationClick(var1, var2) : super.navigationClick(var1, var2);
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationMovement(var1, var2, var3, var4) : super.navigationMovement(var1, var2, var3, var4);
   }

   @Override
   protected boolean navigationUnclick(int var1, int var2) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationUnclick(var1, var2) : super.navigationUnclick(var1, var2);
   }

   @Override
   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelRoll(var1, var2, var3) : super.trackwheelRoll(var1, var2, var3);
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.processKeyEvent(var1, var2, var3, var4) : super.processKeyEvent(var1, var2, var3, var4);
   }

   @Override
   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      return this._fieldWithFocus != null
         ? this._fieldWithFocus.processNavigationEvent(var1, var2, var3, var4, var5)
         : super.processNavigationEvent(var1, var2, var3, var4, var5);
   }

   protected void makeFocusVisible(boolean var1, XYRect var2, boolean var3, boolean var4) {
      if (this._horizontalScrollbar != null) {
         var2.height = var2.height + SCROLLBAR_TOTAL_HEIGHT;
      }

      if (this._verticalScrollbar != null) {
         var2.width = var2.width + SCROLLBAR_TOTAL_WIDTH;
      }

      this.makeRegionVisible(var1, var2, var3);
   }

   private final void makeRegionVisible(boolean var1, XYRect var2, boolean var3) {
      int var4 = this.calculateVerticalScrollAmount(var2);
      int var5 = this.calculateHorizontalScrollAmount(var2);
      this.scroll(var1, var5, var4, var3);
   }

   protected final void scroll(boolean var1, int var2, int var3, boolean var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void setDirty(boolean var1) {
      for (int var2 = 0; var2 < this._fieldsCount; var2++) {
         this._fields[var2].setDirty(var1);
      }
   }

   public void setHorizontalQuantization(int var1) {
      if (var1 != -1 && var1 <= 0) {
         throw new Object();
      }

      this._horizontalQuanta = var1;
   }

   public void setHorizontalScroll(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected final void setPositionChild(Field var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setScrollListener(ScrollChangeListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setVerticalQuantization(int var1) {
      if (var1 != -1 && var1 <= 0) {
         throw new Object();
      }

      this._verticalQuanta = var1;
   }

   public void setVerticalScroll(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected final void setVirtualExtent(int var1, int var2) {
      this._virtualWidth = var1;
      this._virtualHeight = var2;
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelClick(var1, var2) : false;
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
   }

   private int pageSize() {
      int var1 = this.getContentHeight();
      int var2 = this.getFont().getHeight();
      return var1 < 2 * var2 ? var1 : var1 - var2;
   }

   private int getStylusPos(int var1, int var2) {
      if (this._indicatorHeight == 0) {
         return 0;
      } else {
         int var3 = Math.min(this._scrollBarX, this._indicatorX) - 2;
         if (var1 < var3) {
            return 0;
         } else if (var1 > this._scrollBarX + this._scrollBarWidth) {
            return 0;
         } else if (var2 < this._scrollBarY) {
            return 0;
         } else if (var2 > this._scrollBarY + this._scrollBarHeight) {
            return 0;
         } else if (var2 < this._indicatorY) {
            return 1;
         } else {
            return var2 > this._indicatorY + this._indicatorHeight ? 3 : 2;
         }
      }
   }

   private int mapStylusX(int var1) {
      return var1 - this._fieldWithFocus.getExtent().x;
   }

   private int mapStylusY(int var1) {
      return var1 - this._fieldWithFocus.getExtent().y;
   }

   @Override
   protected boolean stylusDown(int var1, int var2, int var3, int var4) {
      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDown(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         case 2:
            this._stylusDownY = var2 - this.getVerticalScroll();
            this._stylusDownVerticalScroll = this.getVerticalScroll();
            this._stylusDraggingIndicator = true;
            return true;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusDrag(int var1, int var2, int var3, int var4) {
      if (this._stylusDraggingIndicator) {
         int var5 = this._virtualHeight * (var2 - this._stylusDownY) / this._scrollBarHeight;
         this.setVerticalScroll(MathUtilities.clamp(0, this._stylusDownVerticalScroll + var5, this._virtualHeight - this.getExtent().height));
         return true;
      }

      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDrag(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusUp(int var1, int var2, int var3, int var4) {
      if (this._stylusDraggingIndicator) {
         this._stylusDraggingIndicator = false;
         return true;
      }

      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusUp(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case -1:
            return false;
         case 0:
         default:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusTap(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         case 1:
            this.setVerticalScroll(Math.max(this.getVerticalScroll() - this.pageSize(), 0));
            return true;
         case 2:
            return true;
         case 3:
            this.setVerticalScroll(Math.min(this.getVerticalScroll() + this.pageSize(), this._virtualHeight - this.getExtent().height));
            return true;
      }
   }

   @Override
   protected boolean stylusDoubleTap(int var1, int var2, int var3, int var4) {
      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDoubleTap(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusTapHold(int var1, int var2, int var3, int var4) {
      var1 += this.getHorizontalScroll();
      var2 += this.getVerticalScroll();
      switch (this.getStylusPos(var1, var2)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusTapHold(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean onCursorHover(int var1, int var2) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.onCursorHover(this.mapStylusX(var1), this.mapStylusY(var2)) : false;
   }

   @Override
   public Cursor getFocusCursor() {
      return this._fieldWithFocus != null ? this._fieldWithFocus.getFocusCursor() : Cursor.getPredefinedCursor(0);
   }

   @Override
   protected boolean trackwheelUnclick(int var1, int var2) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelUnclick(var1, var2) : false;
   }

   @Override
   boolean validateFieldStyle(long var1) {
      return super.validateFieldStyle(var1 & -5624987138492727297L);
   }

   private static long validateStyle(long var0) {
      if ((var0 & 844424930131968L) == 0) {
         var0 |= 562949953421312L;
      }

      if ((var0 & 844424930131968L) == 562949953421312L) {
         var0 &= -52776558133249L;
         var0 |= 35184372088832L;
      }

      if ((var0 & 3377699720527872L) == 0) {
         var0 |= 2251799813685248L;
      }

      if ((var0 & 3377699720527872L) == 2251799813685248L) {
         var0 &= -211106232532993L;
         var0 |= 140737488355328L;
      }

      var0 &= -67553994410557441L;
      if (haveStylus && (var0 & 1407374883553280L) != 0) {
         var0 |= 8796093022208L;
      }

      return var0;
   }

   @Override
   protected void onUndisplay() {
      super.onUndisplay();
      if (this._nonfocusableArrowOverride) {
         this.clearCustomScrollArrows();
      }
   }

   @Override
   void callOnExposed() {
      this.onExposed();

      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         this._fields[var1].callOnExposed();
      }
   }

   @Override
   void callOnObscured() {
      this.onObscured();

      for (int var1 = 0; var1 < this._fieldsCount; var1++) {
         this._fields[var1].callOnObscured();
      }
   }

   int getHorizontalScrollHeight() {
      return 0;
   }
}
