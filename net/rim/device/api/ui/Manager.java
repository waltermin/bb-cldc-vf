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

   protected Manager(long style) {
      super(validateStyle(style));
      if (!this.validateFieldStyle(style)) {
         throw new Object();
      }

      this.setTag(TAG);
   }

   @Override
   public boolean acceptVisitor(FieldVisitor visitor) {
      if (!visitor.visit(this, 1)) {
         return false;
      }

      int total = this.getFieldCount();

      for (int i = 0; i < total; i++) {
         if (!this.getField(i).acceptVisitor(visitor)) {
            return false;
         }
      }

      return visitor.visit(this, 2);
   }

   public void add(Field field) {
      this.insertInternal(field, this._fieldsCount);
   }

   @Override
   protected void applyFont() {
      super.applyFont();

      for (int lv = this._fieldsCount - 1; lv >= 0; lv--) {
         this._fields[lv].applyFont();
      }
   }

   @Override
   protected void applyTheme() {
      super.applyTheme();

      for (int lv = this._fieldsCount - 1; lv >= 0; lv--) {
         this._fields[lv].applyTheme();
      }
   }

   protected int calculateHorizontalScrollAmount(XYRect region) {
      if (!this.isStyle(1125899906842624L)) {
         return 0;
      }

      int width = this.getContentWidth();
      int xAmount = 0;
      int delta = region.x + region.width - (this.getHorizontalScroll() + width);
      if (delta > 0) {
         xAmount += delta;
      }

      delta = region.x - (this.getHorizontalScroll() + xAmount);
      if (delta < 0) {
         int deltaDelta = this.getHorizontalScroll() + xAmount + delta;
         if (deltaDelta < 0) {
            delta -= deltaDelta;
         }

         xAmount += delta;
      }

      return xAmount;
   }

   protected int calculateVerticalScrollAmount(XYRect region) {
      if (!this.isStyle(281474976710656L)) {
         return 0;
      }

      int height = this.getContentHeight();
      int yAmount = 0;
      int delta = region.y + region.height - (this.getVerticalScroll() + height);
      if (delta > 0) {
         yAmount += delta;
      }

      delta = region.y - (this.getVerticalScroll() + yAmount);
      if (delta < 0) {
         int deltaDelta = this.getVerticalScroll() + yAmount + delta;
         if (deltaDelta < 0) {
            delta -= deltaDelta;
         }

         yAmount += delta;
      }

      return yAmount;
   }

   @Override
   void callOnDisplayOrUndisplay(boolean attached) {
      super.callOnDisplayOrUndisplay(attached);

      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].callOnDisplayOrUndisplay(attached);
      }
   }

   public void delete(Field field) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void deleteAll() {
      this.deleteRange(0, this.getFieldCount());
   }

   public void deleteRange(int start, int count) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   void doVisibilityWalk(boolean visible) {
      this.onVisibilityChange(visible);

      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].doVisibilityWalk(visible);
      }
   }

   private void ensureFieldCapacity() {
      int capacity = this._fields.length;
      if (this._fieldsCount == capacity) {
         Array.resize(this._fields, capacity * 2);
      }
   }

   @Override
   void getDebugTreeHelper(int treeStyle, StringBuffer buffer, int indent) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int index) {
      return this.getField(index);
   }

   @Override
   public int getAccessibleChildCount() {
      return this.getFieldWithFocus().getAccessibleSelectionAt(0) != null ? this.getFieldWithFocus().getAccessibleChildCount() : this.getFieldCount();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int index) {
      return this.getFieldWithFocus().getAccessibleSelectionAt(index) != null
         ? this.getFieldWithFocus().getAccessibleSelectionAt(index)
         : this.getFieldWithFocus();
   }

   @Override
   public boolean isAccessibleChildSelected(int index) {
      return this.getFieldWithFocus().getAccessibleSelectionAt(0) != null
         ? this.getFieldWithFocus().isAccessibleChildSelected(index)
         : this.getFieldWithFocusIndex() == index;
   }

   public Field getField(int index) {
      if (index >= this._fieldsCount) {
         throw new Object();
      } else {
         return this._fields[index];
      }
   }

   public int getFieldAtLocation(int x, int y) {
      for (int lv = this._fieldsCount - 1; lv >= 0; lv--) {
         Field field = this._fields[lv];
         XYRect rect = field.getExtent();
         if (rect.y <= y && rect.x <= x && y <= rect.y + rect.height && x <= rect.x + rect.width) {
            return lv;
         }
      }

      return -1;
   }

   public int getFieldClosestToLocation(int x, int y, int status) {
      int start = 0;
      int end = this._fieldsCount;
      int increment = 1;
      int search = 0;
      if ((status & 256) != 0 || (status & 1024) != 0) {
         start = this._fieldsCount - 1;
         end = -1;
         increment = -1;
         search = -1;
      } else if ((status & 512) != 0 || (status & 2048) != 0) {
         search = 1;
      }

      int index = -1;
      int deltaY = Integer.MAX_VALUE;
      int deltaX = Integer.MAX_VALUE;

      for (int lv = start; lv != end; lv += increment) {
         Field field = this._fields[lv];
         XYRect rect = field.getExtent();
         if (rect.y <= y && y <= rect.y + rect.height) {
            deltaY = 0;
            if (rect.x <= x && x <= rect.x + rect.width) {
               return lv;
            }

            int deltaXNew = Math.abs(rect.x - x);
            if (deltaXNew < deltaX) {
               deltaX = deltaXNew;
               index = lv;
            }
         } else {
            int deltaYNew;
            if (search == -1) {
               deltaYNew = y - rect.y;
            } else if (search == 1) {
               deltaYNew = rect.y - y;
            } else {
               deltaYNew = Math.abs(rect.y - y);
            }

            if (deltaYNew >= 0 && deltaYNew < deltaY) {
               deltaY = deltaYNew;
               deltaX = Math.abs(rect.x - x);
               index = lv;
            } else if (deltaYNew == deltaY) {
               int deltaXNew = Math.abs(rect.x - x);
               if (deltaXNew < deltaX) {
                  deltaX = deltaXNew;
                  index = lv;
               }
            }
         }
      }

      return index;
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
   public void getFocusRect(XYRect rect) {
      if (this._fieldWithFocus != null) {
         this._fieldWithFocus.getFocusRect(rect);
         rect.translate(this._fieldWithFocus.getContentLeft() - this.getHorizontalScroll(), this._fieldWithFocus.getContentTop() - this.getVerticalScroll());
      } else {
         rect.set(this.getHorizontalScroll(), this.getVerticalScroll(), this.getContentWidth(), this.getContentHeight());
      }
   }

   @Override
   public void getFocusRectPhantom(XYRect rect) {
      if (this._fieldWithFocus != null) {
         this._fieldWithFocus.getFocusRectPhantom(rect);
         rect.translate(this._fieldWithFocus.getContentLeft() - this.getHorizontalScroll(), this._fieldWithFocus.getContentTop() - this.getVerticalScroll());
      } else {
         rect.set(this.getHorizontalScroll(), this.getVerticalScroll(), this.getContentWidth(), this.getContentHeight());
      }
   }

   @Override
   Graphics getGraphics0(XYRect clip, boolean drawBackground) {
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

   public int getNavigationAxis(int status) {
      if ((status & 536870912) != 0) {
         return (status & 65536) != 0 ? 1 : 2;
      } else {
         return (status & 1) == 0 ? 0 : 2;
      }
   }

   protected final int getPreferredHeightOfChild(Field field) {
      return field.getPreferredHeight() + field.getPaddingTop() + field.getPaddingBottom() + field.getBorderTop() + field.getBorderBottom();
   }

   protected final int getPreferredWidthOfChild(Field field) {
      int preferredWidth = field.getPreferredWidth() + field.getPaddingRight() + field.getPaddingLeft() + field.getBorderRight() + field.getBorderLeft();
      if (this._verticalScrollbar != null) {
         preferredWidth -= SCROLLBAR_TOTAL_WIDTH;
      }

      return preferredWidth;
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

   int getVisibleHeight(int min, int y) {
      if (0 > y) {
         min += y;
         y = 0;
      }

      if (this.getContentHeight() < min + y) {
         min = this.getContentHeight() - y;
      }

      return this.getManager().getVisibleHeight(min, y + this.getContentTop());
   }

   public int getVisibleWidth() {
      return this.getVisibleWidth(Integer.MAX_VALUE, 0);
   }

   int getVisibleWidth(int min, int x) {
      if (0 > x) {
         min += x;
         x = 0;
      }

      if (this.getContentWidth() < min + x) {
         min = this.getContentWidth() - x;
      }

      return this.getManager().getVisibleWidth(min, x + this.getContentLeft());
   }

   private int getXForScrollArrowLeft() {
      return this.getHorizontalScroll() - this.getPaddingLeft();
   }

   private int getXForScrollArrowRight(int width) {
      int xPosition = this.getHorizontalScroll() + this.getContentWidth() + this.getPaddingRight();
      return xPosition - width;
   }

   protected boolean incrementalLayout(int index, int added, int deleted) {
      return false;
   }

   public void insert(Field field, int index) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   private void insertInternal(Field field, int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public void invalidate() {
      int widthAdjust = 0;
      int heightAdjust = 0;
      if (this._verticalScrollbar != null) {
         widthAdjust = SCROLLBAR_TOTAL_WIDTH;
      }

      if (this._horizontalScrollbar != null) {
         heightAdjust = SCROLLBAR_TOTAL_HEIGHT;
      }

      this.invalidate(this.getHorizontalScroll(), this.getVerticalScroll(), this.getWidth() + widthAdjust, this.getHeight() + heightAdjust);
   }

   @Override
   protected void invalidate(int x, int y, int width, int height) {
      super.invalidate(x - this.getHorizontalScroll(), y - this.getVerticalScroll(), width, height);
   }

   protected void invalidateFieldRange(int lower, int upper) {
      lower = lower < 0 ? 0 : lower;
      upper = upper < this._fieldsCount ? upper + 1 : this._fieldsCount;

      for (int i = lower; i < upper; i++) {
         this._fields[i].invalidate();
      }
   }

   @Override
   void invalidateLayout0() {
      this.setValidLayout(false);

      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].invalidateLayout0();
      }
   }

   @Override
   protected boolean invokeAction(int action) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.invokeAction(action) : false;
   }

   @Override
   public boolean isDataValid() {
      for (int i = 0; i < this._fieldsCount; i++) {
         if (!this._fields[i].isDataValid()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public boolean isDirty() {
      for (int i = 0; i < this._fieldsCount; i++) {
         if (this._fields[i].isDirty()) {
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
      for (int i = this._fieldsCount - 1; i >= 0; i--) {
         if (this._fields[i].isFocusable()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isMuddy() {
      for (int i = 0; i < this._fieldsCount; i++) {
         if (this._fields[i].isMuddy()) {
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
   protected boolean keyChar(char ch, int status, int time) {
      boolean handled = super.keyChar(ch, status, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyChar(ch, status, time);
      }

      return handled;
   }

   @Override
   protected boolean keyControl(char ch, int status, int time) {
      boolean handled = super.keyControl(ch, status, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyControl(ch, status, time);
      }

      return handled;
   }

   @Override
   protected boolean keyDown(int keycode, int time) {
      boolean handled = super.keyDown(keycode, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyDown(keycode, time);
      }

      return handled;
   }

   @Override
   protected boolean keyRepeat(int keycode, int time) {
      boolean handled = super.keyRepeat(keycode, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyRepeat(keycode, time);
      }

      return handled;
   }

   @Override
   protected boolean keyStatus(int keycode, int time) {
      boolean handled = super.keyStatus(keycode, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyStatus(keycode, time);
      }

      return handled;
   }

   @Override
   protected boolean keyUp(int keycode, int time) {
      boolean handled = super.keyUp(keycode, time);
      if (this._fieldWithFocus != null && !handled) {
         handled = this._fieldWithFocus.keyUp(keycode, time);
      }

      return handled;
   }

   @Override
   protected final void layout(int width, int height) {
      this._virtualHeight = -1;
      int widthAdjust = 0;
      int heightAdjust = 0;
      long temp = this.getStyle();
      Theme theme = ThemeManager.getActiveTheme();
      if (theme.getAttributeSet(THEME_SCROLLBAR_TAG) != null) {
         if (this.isStyle(17592186044416L) && (temp & 281474976710656L) > 0) {
            widthAdjust = SCROLLBAR_TOTAL_WIDTH;
            this._verticalScrollbar = (Scrollbar)(new Object(true));
            this._verticalScrollbar.setManager(this, 0);
            if (this._scrollListener == null) {
               this._verticalScrollbar.setClient(this);
            }
         }

         if (this.isStyle(70368744177664L) && (temp & 1125899906842624L) > 0) {
            heightAdjust = SCROLLBAR_TOTAL_HEIGHT;
            this._horizontalScrollbar = (Scrollbar)(new Object(true, true));
            this._horizontalScrollbar.setManager(this, 0);
            if (this._scrollListener == null) {
               this._horizontalScrollbar.setClient(this);
            }
         }
      }

      this.sublayout(width - widthAdjust, height - heightAdjust);
      if (this._verticalScrollbar != null) {
         XYRect tmp = this.getExtent();
         this.setExtent(tmp.width + widthAdjust, tmp.height);
      }

      if (this._horizontalScrollbar != null) {
         XYRect tmp = this.getExtent();
         this.setExtent(tmp.width, tmp.height + heightAdjust);
      }

      if (this._verticalQuanta != 1) {
         int denominator = this._verticalQuanta == -1 ? this.getFont().getHeight() : this._verticalQuanta;
         int remainder = this.getContentHeight() % denominator;
         this.setExtent(this.getContentWidth(), this.getContentHeight() - remainder);
      }

      if (this._horizontalQuanta != 1) {
         int denominator = this._horizontalQuanta == -1 ? this.getFont().getHeight() : this._horizontalQuanta;
         int remainder = this.getContentWidth() % denominator;
         this.setExtent(this.getContentWidth() - remainder, this.getContentHeight());
      }

      if (this._virtualHeight == -1) {
         this.setVirtualExtent(this.getContentWidth(), this.getContentHeight());
      }

      this.removeBlankSpace();
      this.setValidLayout(true);
   }

   protected final void layoutChild(Field field, int width, int height) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected int moveFocus(int amount, int status, int time) {
      boolean notify = false;
      int axis = this.getNavigationAxis(status);

      label74:
      while (amount != 0) {
         if (this._fieldWithFocus == null) {
            return amount;
         }

         int remaining = this._fieldWithFocus.moveFocus(amount, status, time);
         if ((status & 8) != 0) {
            remaining = 0;
         }

         if (remaining != amount) {
            if (!(this._fieldWithFocus instanceof Manager)) {
               this._fieldWithFocus.focusChangeNotify(2);
            }

            if (remaining == 0) {
               amount = 0;
               break;
            }
         }

         amount = remaining;
         int oldIndex = this._fieldWithFocusIndex;
         Field oldFocus = this._fieldWithFocus;
         int amountSign = MathUtilities.clamp(-1, amount, 1);

         do {
            this._fieldWithFocusIndex = this.nextFocus(amountSign, axis);
            if (this._fieldWithFocusIndex == -1 || this._fieldWithFocusIndex == oldIndex) {
               this._fieldWithFocusIndex = oldIndex;
               this._fieldWithFocus = oldFocus;
               break label74;
            }

            this._fieldWithFocus = this._fields[this._fieldWithFocusIndex];
         } while (!this._fieldWithFocus.isFocusable());

         oldFocus.onUnfocus();
         oldFocus.focusChangeNotify(3);
         this._fieldWithFocus.onFocus(amountSign);
         this._fieldWithFocus.focusChangeNotify(1);
         amount -= amountSign;
         notify = true;
      }

      if (notify) {
         this.focusChangeNotify(2);
      }

      if (amount != 0 && !this.isStyle(144115188075855872L)) {
         if (amount < 0) {
            if (this.getVerticalScroll() > 0 && axis != 1) {
               XYRect rect = Ui.getTmpXYRect();
               rect.set(this.getHorizontalScroll(), 0, 0, 0);
               this.makeRegionVisible(true, rect, true);
               Ui.returnTmpXYRect(rect);
               return amount;
            }

            if (this.getHorizontalScroll() > 0 && axis == 1) {
               XYRect rect = Ui.getTmpXYRect();
               rect.set(0, this.getVerticalScroll(), 0, 0);
               this.makeRegionVisible(true, rect, true);
               Ui.returnTmpXYRect(rect);
               return amount;
            }
         } else if (this.getVerticalScroll() + this.getContentHeight() < this._virtualHeight && axis != 1) {
            XYRect rect = Ui.getTmpXYRect();
            rect.set(this.getHorizontalScroll(), this._virtualHeight, 0, 0);
            this.makeRegionVisible(true, rect, true);
            Ui.returnTmpXYRect(rect);
         }
      }

      return amount;
   }

   private void notifyVisibilityChange(int start, int count, boolean visible) {
      if (this.isValidLayout()) {
         Screen scr = this.getScreen();
         if (scr != null) {
            if (scr.isDisplayed()) {
               if (visible) {
                  for (int i = start; i < start + count; i++) {
                     this._fields[i].callOnDisplayOrUndisplay(true);
                  }
               } else {
                  for (int i = start; i < start + count; i++) {
                     this._fields[i].callOnDisplayOrUndisplay(false);
                  }
               }
            }

            if (scr.isVisible()) {
               for (int i = start; i < start + count; i++) {
                  this._fields[i].doVisibilityWalk(visible);
               }
            }
         }
      }
   }

   @Override
   protected void paint(Graphics graphics) {
      if (this.isOkayForPainting()) {
         this.subpaint(graphics);
         if (this.isOkayForPainting()) {
            this.paintScrollbars(graphics);
         }
      }
   }

   private boolean isOkayForPainting() {
      UiEngineImpl engine = UiEngineImpl.getUiEngine();
      return engine == null || engine.getGlobalScreen() != null || engine.getApplication().isForeground();
   }

   protected final void paintChild(Graphics graphics, Field field) {
      field.paintSelf(graphics, true, 0, 0);
   }

   @Override
   void paintSelf(Graphics graphics, boolean addExtent, int xContentAdjust, int yContentAdjust) {
      super.paintSelf(graphics, addExtent, -this.getHorizontalScroll(), -this.getVerticalScroll());
   }

   protected final void clearCustomScrollArrows() {
      Graphics.resetOverlays();
   }

   protected boolean drawLeafFocus(boolean drawBackground, boolean drawFocus) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setNonfocusableOverride(boolean override) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   private void paintScrollbars(Graphics graphics) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void replace(Field oldField, Field newField) {
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

   private void removeField(int index, Field field) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void removeFieldHelper(int index, Field field) {
      for (int i = index + 1; i < this._fieldsCount; i++) {
         this._fields[i].setIndex(i - 1);
      }

      System.arraycopy(this._fields, index + 1, this._fields, index, this._fieldsCount - index - 1);
      this._fieldsCount--;
      this._fields[this._fieldsCount] = null;
      if (index < this._fieldWithFocusIndex) {
         this._fieldWithFocusIndex--;
      }

      field.setManager(null, -1);
   }

   final void runLayoutUpdate(int index, int added, int deleted) {
      if (this.isValidLayout()) {
         Screen screen = this.getScreen();
         if (screen != null) {
            this.runLayoutUpdate0(index, added, deleted);
            if (added > 0 && screen.getFieldWithFocus() == null) {
               boolean newfocus = false;

               for (int i = 0; i < added; i++) {
                  if (this._fields[index + i].isFocusable()) {
                     newfocus = true;
                     break;
                  }
               }

               if (newfocus) {
                  screen.setFocus();
               }
            }
         }
      }
   }

   void runLayoutUpdate0(int index, int added, int deleted) {
      if (!this.incrementalLayout(index, added, deleted)) {
         this.getManager().runLayoutUpdate0(this.getIndex(), 1, 1);
      } else {
         this.getScreen().ensureFocusVisible();
      }
   }

   public void setFieldWithFocus(Field child) {
      this._fieldWithFocusIndex = child.getIndex();
      this._fieldWithFocus = child;
   }

   final void setValidLayout(boolean validLayout) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected void sublayout(int _1, int _2) {
      throw null;
   }

   protected void subpaint(Graphics graphics) {
      int numFields = this.getFieldCount();

      for (int i = 0; i < numFields; i++) {
         Field field = this.getField(i);
         this.paintChild(graphics, field);
      }
   }

   @Override
   protected void onFocus(int direction) {
      super.onFocus(direction);
      this._fieldWithFocusIndex = this.firstFocus(direction);
      if (this._fieldWithFocusIndex != -1) {
         this._fieldWithFocus = this._fields[this._fieldWithFocusIndex];
         this._fieldWithFocus.onFocus(direction);
         this._fieldWithFocus.focusChangeNotify(1);
      }
   }

   protected int firstFocus(int direction) {
      switch (direction) {
         case -1:
            for (int i = this._fieldsCount - 1; i >= 0; i--) {
               if (this._fields[i].isFocusable()) {
                  return i;
               }
            }
            break;
         case 1:
            for (int i = 0; i < this._fieldsCount; i++) {
               if (this._fields[i].isFocusable()) {
                  return i;
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

   protected int nextFocus(int direction, boolean alt) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   protected int nextFocus(int direction, int axis) {
      if (this._fieldWithFocusIndex <= -1) {
         return -1;
      }

      switch (direction) {
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
   protected void moveFocus(int x, int y, int status, int time) {
      this.moveFocusToPoint(x, y, status, time, 0, false);
   }

   @Override
   boolean moveFocusToPoint(int x, int y, int status, int time) {
      int direction = status & 3855;
      return this.moveFocusToPoint(x, y, status, time, direction, true);
   }

   private boolean moveFocusToPoint(int x, int y, int status, int time, int direction, boolean isInManagersCoordinates) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected boolean moveFocus(int where) {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean setFocus(int x, int y, int status) {
      return this.getScreen().setFocus(this, x, y, status, 0);
   }

   @Override
   protected boolean navigationClick(int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationClick(status, time) : super.navigationClick(status, time);
   }

   @Override
   protected boolean navigationMovement(int dx, int dy, int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationMovement(dx, dy, status, time) : super.navigationMovement(dx, dy, status, time);
   }

   @Override
   protected boolean navigationUnclick(int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.navigationUnclick(status, time) : super.navigationUnclick(status, time);
   }

   @Override
   protected boolean trackwheelRoll(int amount, int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelRoll(amount, status, time) : super.trackwheelRoll(amount, status, time);
   }

   @Override
   public int processKeyEvent(int event, char key, int keycode, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.processKeyEvent(event, key, keycode, time) : super.processKeyEvent(event, key, keycode, time);
   }

   @Override
   public boolean processNavigationEvent(int event, int dx, int dy, int status, int time) {
      return this._fieldWithFocus != null
         ? this._fieldWithFocus.processNavigationEvent(event, dx, dy, status, time)
         : super.processNavigationEvent(event, dx, dy, status, time);
   }

   protected void makeFocusVisible(boolean immediate, XYRect region, boolean draw, boolean reset) {
      if (this._horizontalScrollbar != null) {
         region.height = region.height + SCROLLBAR_TOTAL_HEIGHT;
      }

      if (this._verticalScrollbar != null) {
         region.width = region.width + SCROLLBAR_TOTAL_WIDTH;
      }

      this.makeRegionVisible(immediate, region, draw);
   }

   private final void makeRegionVisible(boolean immediate, XYRect region, boolean draw) {
      int yAmount = this.calculateVerticalScrollAmount(region);
      int xAmount = this.calculateHorizontalScrollAmount(region);
      this.scroll(immediate, xAmount, yAmount, draw);
   }

   protected final void scroll(boolean immediate, int xAmount, int yAmount, boolean draw) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void setDirty(boolean dirty) {
      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].setDirty(dirty);
      }
   }

   public void setHorizontalQuantization(int horizontalQuanta) {
      if (horizontalQuanta != -1 && horizontalQuanta <= 0) {
         throw new Object();
      }

      this._horizontalQuanta = horizontalQuanta;
   }

   public void setHorizontalScroll(int position) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected final void setPositionChild(Field field, int x, int y) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setScrollListener(ScrollChangeListener listener) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setVerticalQuantization(int verticalQuanta) {
      if (verticalQuanta != -1 && verticalQuanta <= 0) {
         throw new Object();
      }

      this._verticalQuanta = verticalQuanta;
   }

   public void setVerticalScroll(int position) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected final void setVirtualExtent(int virtualWidth, int virtualHeight) {
      this._virtualWidth = virtualWidth;
      this._virtualHeight = virtualHeight;
   }

   @Override
   protected boolean trackwheelClick(int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelClick(status, time) : false;
   }

   @Override
   protected void makeMenu(Menu menu, int instance) {
   }

   private int pageSize() {
      int pageSize = this.getContentHeight();
      int fontHeight = this.getFont().getHeight();
      return pageSize < 2 * fontHeight ? pageSize : pageSize - fontHeight;
   }

   private int getStylusPos(int x, int y) {
      if (this._indicatorHeight == 0) {
         return 0;
      } else {
         int barX = Math.min(this._scrollBarX, this._indicatorX) - 2;
         if (x < barX) {
            return 0;
         } else if (x > this._scrollBarX + this._scrollBarWidth) {
            return 0;
         } else if (y < this._scrollBarY) {
            return 0;
         } else if (y > this._scrollBarY + this._scrollBarHeight) {
            return 0;
         } else if (y < this._indicatorY) {
            return 1;
         } else {
            return y > this._indicatorY + this._indicatorHeight ? 3 : 2;
         }
      }
   }

   private int mapStylusX(int x) {
      return x - this._fieldWithFocus.getExtent().x;
   }

   private int mapStylusY(int y) {
      return y - this._fieldWithFocus.getExtent().y;
   }

   @Override
   protected boolean stylusDown(int x, int y, int status, int time) {
      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDown(this.mapStylusX(x), this.mapStylusY(y), status, time);
            }

            return false;
         case 2:
            this._stylusDownY = y - this.getVerticalScroll();
            this._stylusDownVerticalScroll = this.getVerticalScroll();
            this._stylusDraggingIndicator = true;
            return true;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusDrag(int x, int y, int status, int time) {
      if (this._stylusDraggingIndicator) {
         int delta = this._virtualHeight * (y - this._stylusDownY) / this._scrollBarHeight;
         this.setVerticalScroll(MathUtilities.clamp(0, this._stylusDownVerticalScroll + delta, this._virtualHeight - this.getExtent().height));
         return true;
      }

      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDrag(this.mapStylusX(x), this.mapStylusY(y), status, time);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusUp(int x, int y, int status, int time) {
      if (this._stylusDraggingIndicator) {
         this._stylusDraggingIndicator = false;
         return true;
      }

      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusUp(this.mapStylusX(x), this.mapStylusY(y), status, time);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusTap(int x, int y, int status, int time) {
      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case -1:
            return false;
         case 0:
         default:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusTap(this.mapStylusX(x), this.mapStylusY(y), status, time);
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
   protected boolean stylusDoubleTap(int x, int y, int status, int time) {
      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusDoubleTap(this.mapStylusX(x), this.mapStylusY(y), status, time);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean stylusTapHold(int x, int y, int status, int time) {
      x += this.getHorizontalScroll();
      y += this.getVerticalScroll();
      switch (this.getStylusPos(x, y)) {
         case 0:
            if (this._fieldWithFocus != null) {
               return this._fieldWithFocus.stylusTapHold(this.mapStylusX(x), this.mapStylusY(y), status, time);
            }

            return false;
         default:
            return true;
      }
   }

   @Override
   protected boolean onCursorHover(int x, int y) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.onCursorHover(this.mapStylusX(x), this.mapStylusY(y)) : false;
   }

   @Override
   public Cursor getFocusCursor() {
      return this._fieldWithFocus != null ? this._fieldWithFocus.getFocusCursor() : Cursor.getPredefinedCursor(0);
   }

   @Override
   protected boolean trackwheelUnclick(int status, int time) {
      return this._fieldWithFocus != null ? this._fieldWithFocus.trackwheelUnclick(status, time) : false;
   }

   @Override
   boolean validateFieldStyle(long style) {
      return super.validateFieldStyle(style & -5624987138492727297L);
   }

   private static long validateStyle(long style) {
      if ((style & 844424930131968L) == 0) {
         style |= 562949953421312L;
      }

      if ((style & 844424930131968L) == 562949953421312L) {
         style &= -52776558133249L;
         style |= 35184372088832L;
      }

      if ((style & 3377699720527872L) == 0) {
         style |= 2251799813685248L;
      }

      if ((style & 3377699720527872L) == 2251799813685248L) {
         style &= -211106232532993L;
         style |= 140737488355328L;
      }

      style &= -67553994410557441L;
      if (haveStylus && (style & 1407374883553280L) != 0) {
         style |= 8796093022208L;
      }

      return style;
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

      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].callOnExposed();
      }
   }

   @Override
   void callOnObscured() {
      this.onObscured();

      for (int i = 0; i < this._fieldsCount; i++) {
         this._fields[i].callOnObscured();
      }
   }

   int getHorizontalScrollHeight() {
      return 0;
   }
}
