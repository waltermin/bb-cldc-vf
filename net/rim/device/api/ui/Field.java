package net.rim.device.api.ui;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.Clipboard;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.accessibility.AccessibleText;
import net.rim.device.api.ui.accessibility.AccessibleValue;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.menu.MenuItemPrefab;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.Theme;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.ui.Background;
import net.rim.device.internal.ui.Border;
import net.rim.device.internal.ui.Cursor;
import net.rim.tid.awt.Event;
import net.rim.tid.awt.event.InputMethodEvent;
import net.rim.tid.awt.im.InputContext;
import net.rim.tid.awt.im.InputMethodRequests;
import net.rim.tid.itie.EventHandler;
import net.rim.tid.itie.IComponent;

public class Field implements IComponent, AccessibleContext {
   private Manager _manager;
   private XYRect _extent = (XYRect)(new Object());
   private XYRect _content = (XYRect)(new Object());
   private Object _cookie;
   private int _index = -1;
   private long _style;
   private int _state = 64;
   private FieldChangeListener _changeListener;
   private FocusChangeListener _focusListener;
   private Font _font;
   private Font _fontSet;
   private ThemeAttributeSet _themeAttributes;
   private ThemeAttributeSet _themeAttributesAll;
   private ThemeAttributeSet _themeAttributesFocus;
   private ThemeAttributeSet _themeAttributesSpecial;
   private Tag _tag;
   private String _idName;
   private boolean _inDrawHighlightRegion;
   private boolean _borderSet;
   private int _borderTop;
   private int _borderRight;
   private int _borderBottom;
   private int _borderLeft;
   private Border _border;
   private boolean _marginSet;
   private int _marginTop;
   private int _marginRight;
   private int _marginBottom;
   private int _marginLeft;
   private boolean _paddingSet;
   private int _paddingTop;
   private int _paddingRight;
   private int _paddingBottom;
   private int _paddingLeft;
   private InputContext _inputContext;
   private Field$UpdateLayoutRunnable _layoutRunnable;
   private int _accessibleStateSet = 1;
   private static Tag TAG;
   public static final int SYSTEM_STYLE_SHIFT;
   protected static final long FIELD_HALIGN_MASK;
   public static final long FIELD_LEFT;
   public static final long FIELD_RIGHT;
   public static final long FIELD_HCENTER;
   protected static final long FIELD_VALIGN_MASK;
   public static final long FIELD_TOP;
   public static final long FIELD_BOTTOM;
   public static final long FIELD_VCENTER;
   public static final long USE_ALL_WIDTH;
   public static final long USE_ALL_HEIGHT;
   protected static final long EDITABLE_MASK;
   public static final long EDITABLE;
   public static final long READONLY;
   private static final long EDITABLE_DEFAULT;
   protected static final long SPELLCHECKABLE_MASK;
   public static final long SPELLCHECKABLE;
   public static final long NON_SPELLCHECKABLE;
   private static final long SPELLCHECKABLE_DEFAULT;
   protected static final long FOCUSABLE_MASK;
   public static final long FOCUSABLE;
   public static final long NON_FOCUSABLE;
   private static final long FOCUSABLE_DEFAULT;
   public static final int HIGHLIGHT_FOCUS;
   public static final int HIGHLIGHT_SELECT;
   private static final int STATE_DIRTY;
   private static final int STATE_MUDDY;
   private static final int STATE_FOCUS;
   private static final int STATE_CLIPBOARD_ENABLED;
   private static final int STATE_CLIPBOARD_DETERMINED;
   private static final int STATE_THEME_SPECIAL_CLEAR;
   private static final int STATE_IM_ENABLED;
   private static final int STATE_LAYOUT_SETPOSITION;
   private static final int STATE_LAYOUT_SETEXTENT;
   private static final int STATE_DEFAULT;
   public static final long OPAQUE;
   public static final long RIGHT_TO_LEFT;
   public static final long LEFT_TO_RIGHT;
   public static final int ACTION_INVOKE;
   public static final int STATUS_MOVE_FOCUS_HORIZONTALLY;
   public static final int STATUS_MOVE_FOCUS_VERTICALLY;
   public static final int AXIS_SEQUENTIAL;
   public static final int AXIS_HORIZONTAL;
   public static final int AXIS_VERTICAL;
   public static final int DEBUG_NO_EXTENT;
   public static final int DEBUG_PACKAGE;
   public static final int DEBUG_NO_TAG;
   public static final int DEBUG_NO_FOCUS;

   protected void accessibleEventOccurred(int var1, Object var2, Object var3, AccessibleContext var4) {
      AccessibleEventDispatcher.dispatchAccessibleEvent(var1, var2, var3, var4);
   }

   public boolean acceptVisitor(FieldVisitor var1) {
      return var1.visit(this, 3);
   }

   public int adjustVolume(int var1) {
      return -1;
   }

   void assertLayoutComplete() {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void removeAccessibleState(int var1) {
      this._accessibleStateSet &= ~var1;
   }

   protected void addAccessibleState(int var1) {
      if (this.isAccessibleStateSet(1)) {
         this.removeAccessibleState(1);
      }

      this._accessibleStateSet |= var1;
   }

   void callOnObscured() {
      this.onObscured();
   }

   protected void onObscured() {
   }

   void callOnExposed() {
      this.onExposed();
   }

   protected void onExposed() {
   }

   protected void onUndisplay() {
   }

   protected void onDisplay() {
   }

   protected void applyFont() {
      this.assertHaveEventLock();
      if (this._fontSet != null) {
         this._font = this._fontSet;
      } else {
         Font var1 = null;
         if (this.getState() == 6 && this._themeAttributesFocus != null) {
            var1 = this._themeAttributesFocus.getFont();
         }

         if (var1 == null && this._themeAttributes != null) {
            var1 = this._themeAttributes.getFont();
         }

         if (var1 == null) {
            Manager var2 = this.getManager();
            if (var2 != null) {
               var1 = var2._font;
            }

            if (var1 == null) {
               var1 = Font.getDefault();
            }
         }

         this._font = var1;
      }
   }

   protected void applyTheme() {
      this.assertHaveEventLock();
      Theme var1 = ThemeManager.getActiveTheme();
      this._themeAttributesAll = var1.getAttributeSet(this, 0);
      this._themeAttributesFocus = var1.getAttributeSet(this, 6);
      this.applyThemeOnStateChange();
   }

   protected void applyThemeOnStateChange() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void applyTheme(Graphics var1, boolean var2) {
      this.assertHaveEventLock();
      if (this._themeAttributes != null) {
         this._themeAttributes.applyToGraphics(var1);
      }

      if (this._themeAttributesSpecial != null) {
         this._themeAttributesSpecial.applyToGraphics(var1);
      }

      if (var2) {
         this.paintBackground(var1);
      }

      var1.setFont(this.getFont());
   }

   final void applyThemeSpecial(Graphics var1, boolean var2) {
      this.assertHaveEventLock();
      Font var3 = null;
      if (this._themeAttributesSpecial != null) {
         this._themeAttributesSpecial.applyToGraphics(var1);
         var3 = this._themeAttributesSpecial.getFont();
         boolean var4 = var1.isDrawingStyleSet(8);
         boolean var5 = var1.isDrawingStyleSet(16);
         if (!var4 && !var5) {
            if (var2) {
               this.paintBackground(var1);
            }
         } else {
            if (var4) {
               var1.setColor(ThemeAttributeSet.getColor(this, 3));
               var1.setBackgroundColor(ThemeAttributeSet.getColor(this, 2));
            } else {
               var1.setColor(ThemeAttributeSet.getColor(this, 5));
               var1.setBackgroundColor(ThemeAttributeSet.getColor(this, 4));
            }

            var1.setBackgroundImage(null, 0, 0);
            if (var2) {
               var1.clear();
            }
         }
      }

      if (var3 == null) {
         var3 = this.getFont();
      }

      var1.setFont(var3);
   }

   void callOnDisplayOrUndisplay(boolean var1) {
      if (var1) {
         this.onDisplay();
      } else {
         this.onUndisplay();
      }
   }

   public void setThemeAttributeSet(ThemeAttributeSet var1) {
      this._themeAttributes = var1;
      this._themeAttributesAll = var1;
   }

   protected void setThemeAttributesAll(ThemeAttributeSet var1, ThemeAttributeSet var2) {
      this._themeAttributesAll = var1;
      this._themeAttributesFocus = var2;
   }

   public void setThemeAttributesSpecial(ThemeAttributeSet var1, Graphics var2) {
      this._themeAttributesSpecial = var1;
      if (var2 != null) {
         this.applyThemeSpecial(var2, this.isState(32));
      }
   }

   public void setThemeAttributesSpecialClear(boolean var1) {
      this.setState(32, var1);
   }

   protected void drawFocus(Graphics var1, boolean var2) {
      XYRect var3 = Ui.getTmpXYRect();
      this.getFocusRect(var3);
      this.drawHighlightRegion(var1, 1, var2, var3.x, var3.y, var3.width, var3.height);
      Ui.returnTmpXYRect(var3);
   }

   protected final void drawHighlightRegion(Graphics var1, int var2, boolean var3, int var4, int var5, int var6, int var7) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void fieldChangeNotify(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void focusAdd(boolean var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void focusChangeNotify(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void focusRemove() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final Border getBorder() {
      return this._border;
   }

   public final void getBorder(XYEdges var1) {
      var1.top = this._borderTop;
      var1.right = this._borderRight;
      var1.bottom = this._borderBottom;
      var1.left = this._borderLeft;
   }

   public final int getBorderBottom() {
      return this._borderBottom;
   }

   public final int getBorderLeft() {
      return this._borderLeft;
   }

   public final int getBorderRight() {
      return this._borderRight;
   }

   public final int getBorderTop() {
      return this._borderTop;
   }

   public FieldChangeListener getChangeListener() {
      return this._changeListener;
   }

   public final int getContentHeight() {
      return this._content.height;
   }

   public final int getContentLeft() {
      return this._content.x;
   }

   public final XYRect getContentRect() {
      return (XYRect)(new Object(this._content));
   }

   public final void getContentRect(XYRect var1) {
      var1.set(this._content);
   }

   public final int getContentTop() {
      return this._content.y;
   }

   public final int getContentWidth() {
      return this._content.width;
   }

   public ContextMenu getContextMenu() {
      ContextMenu var1 = ContextMenu.getInstance();
      var1.setTarget(this);
      this.makeContextMenu(var1);
      this.addIMActions(var1, 0);
      return var1;
   }

   public ContextMenu getContextMenu(int var1) {
      ContextMenu var2 = ContextMenu.getInstance();
      var2.setTarget(this);
      this.makeContextMenu(var2, var1);
      this.addIMActions(var2, var1);
      return var2;
   }

   public final Object getCookie() {
      return this.getCookieInternal();
   }

   protected Object getCookieInternal() {
      return this._cookie;
   }

   public String getDebugTree(int var1) {
      Object var2 = new Object();
      this.getDebugTreeHelper(var1, (StringBuffer)var2, 0);
      return ((StringBuffer)var2).toString();
   }

   void getDebugTreeHelper(int var1, StringBuffer var2, int var3) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final XYRect getExtent() {
      return this._extent;
   }

   public final void getExtent(XYRect var1) {
      var1.set(this._extent);
   }

   public final void assertHaveEventLock() {
      Screen var1 = this.getScreen();
      if (var1 != null) {
         UiEngineImpl var2 = var1.getUiEngineImpl();
         if (var2 != null) {
            var2.assertHaveEventLock();
         }
      }
   }

   public FocusChangeListener getFocusListener() {
      return this._focusListener;
   }

   public void getFocusRect(XYRect var1) {
      var1.set(this._extent.x - this._content.x, this._extent.y - this._content.y, this._extent.width, this._extent.height);
   }

   public void getFocusRectPhantom(XYRect var1) {
      this.getFocusRect(var1);
   }

   void doVisibilityWalk(boolean var1) {
      this.onVisibilityChange(var1);
   }

   Font getFont0() {
      Field var1 = this;

      while (var1._fontSet == null) {
         ThemeAttributeSet var2 = var1._themeAttributes;
         if (var2 != null && var2.getFont() != null) {
            return var2.getFont();
         }

         var1 = var1._manager;
         if (var1 == null) {
            return Font.getDefault();
         }
      }

      return var1._fontSet;
   }

   public Font getFontIfSet() {
      return this._fontSet;
   }

   protected final Graphics getGraphics0() {
      XYRect var1 = Ui.getTmpXYRect();
      var1.set(0, 0, this.getContentWidth(), this.getContentHeight());
      Graphics var2 = this.getGraphics0(var1, false);
      Ui.returnTmpXYRect(var1);
      return var2;
   }

   Graphics getGraphics0(XYRect var1, boolean var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final int getHeight() {
      return this._extent.height;
   }

   public String getId() {
      return this._idName;
   }

   public final int getIndex() {
      return this._index;
   }

   public Field getLeafFieldWithFocus() {
      return this;
   }

   public final int getLeft() {
      return this._extent.x;
   }

   public final void getMargin(XYEdges var1) {
      var1.top = this._marginTop;
      var1.right = this._marginRight;
      var1.bottom = this._marginBottom;
      var1.left = this._marginLeft;
   }

   public final int getMarginBottom() {
      return this._marginBottom;
   }

   public final int getMarginLeft() {
      return this._marginLeft;
   }

   public final int getMarginRight() {
      return this._marginRight;
   }

   public final int getMarginTop() {
      return this._marginTop;
   }

   public final Manager getManager() {
      return this._manager;
   }

   public void getNextFocus(int var1, XYRect var2) {
      this.getFocusRect(var2);
   }

   public Field getOriginal() {
      return this;
   }

   public final void getPadding(XYEdges var1) {
      var1.top = this._paddingTop;
      var1.right = this._paddingRight;
      var1.bottom = this._paddingBottom;
      var1.left = this._paddingLeft;
   }

   public final int getPaddingBottom() {
      return this._paddingBottom;
   }

   public final int getPaddingLeft() {
      return this._paddingLeft;
   }

   public final int getPaddingRight() {
      return this._paddingRight;
   }

   public final int getPaddingTop() {
      return this._paddingTop;
   }

   public int getPreferredHeight() {
      return 0;
   }

   public int getPreferredWidth() {
      return 0;
   }

   public final Screen getScreen() {
      throw new RuntimeException("cod2jar: type check");
   }

   public int getState() {
      return this.isState(4) ? 6 : 0;
   }

   public final long getStyle() {
      return this._style;
   }

   public Tag getTag() {
      return this._tag;
   }

   public final ThemeAttributeSet getThemeAttributeSet() {
      return this._themeAttributes;
   }

   public final ThemeAttributeSet getThemeAttributeSetSpecial() {
      return this._themeAttributesSpecial;
   }

   public final int getTop() {
      return this._extent.y;
   }

   public final int getWidth() {
      return this._extent.width;
   }

   protected void invalidate() {
      Manager var1 = this.getManager();
      if (var1 != null) {
         XYRect var2 = this.getExtent();
         var1.invalidate(var2.x, var2.y, var2.width, var2.height);
      }
   }

   protected void invalidate(int var1, int var2, int var3, int var4) {
      XYRect var5 = Ui.getTmpXYRect();
      this.getContentRect(var5);
      this.invalidateCommon(var1, var2, var3, var4, var5);
      Ui.returnTmpXYRect(var5);
   }

   protected void invalidateAll(int var1, int var2, int var3, int var4) {
      this.invalidateCommon(var1, var2, var3, var4, this.getExtent());
   }

   void invalidateLayout0() {
   }

   protected boolean invokeAction(int var1) {
      return false;
   }

   public boolean isDataValid() {
      return true;
   }

   public boolean isDirty() {
      return this.isState(1);
   }

   public boolean isEditable() {
      return (this._style & 13510798882111488L) == 4503599627370496L;
   }

   boolean isFieldTransparent() {
      Background var1 = this.getBackground();
      return var1 == null || var1.isTransparent();
   }

   public boolean isFocus() {
      return this.isState(4);
   }

   public boolean isFocusable() {
      return (this._style & 18014398509481984L) != 0;
   }

   protected boolean isFocusDrawn() {
      return true;
   }

   protected void onVisibilityChange(boolean var1) {
   }

   public boolean isMuddy() {
      return this.isState(2);
   }

   public boolean isPasteable() {
      return false;
   }

   public boolean isSelectable() {
      return false;
   }

   public boolean isSelecting() {
      return false;
   }

   public boolean isSelectionCopyable() {
      return false;
   }

   public final boolean isSelectionCutable() {
      return this.isSelectionCopyable() && this.isSelectionDeleteable();
   }

   public boolean isSelectionDeleteable() {
      return false;
   }

   public boolean isSpellCheckable() {
      return (this._style & 3298534883328L) == 1099511627776L;
   }

   public final boolean isStyle(long var1) {
      return (this._style & var1) == var1;
   }

   public final boolean isVisible() {
      return this.isVisible0();
   }

   boolean isVisible0() {
      Screen var1 = this.getScreen();
      return var1 != null && var1.isVisible0();
   }

   protected boolean keyChar(char var1, int var2, int var3) {
      if (this.isSelecting() && !(this instanceof Object)) {
         switch (var1) {
            case '\b':
            case '\u007f':
               if (this.isSelectionDeleteable()) {
                  if ((var2 & 2) != 0) {
                     if (this.isSelectionCutable() && this.isCutCopyPasteEnabled()) {
                        this.selectionCut(Clipboard.getClipboard());
                     }
                  } else {
                     this.selectionDelete();
                  }

                  this.select(false);
               }

               return true;
            case '\u001b':
               this.select(false);
               return true;
         }
      }

      return false;
   }

   protected boolean keyControl(char var1, int var2, int var3) {
      if (this.isSelecting()) {
         switch (var1) {
            case '\u001b':
               this.select(false);
               return true;
            case '\u007f':
               if (this.isSelectionDeleteable()) {
                  if ((var2 & 2) != 0) {
                     if (this.isSelectionCutable() && this.isCutCopyPasteEnabled()) {
                        this.selectionCut(Clipboard.getClipboard());
                        Clipboard.getClipboard().setNotYetPasted(true);
                     }
                  } else {
                     this.selectionDelete();
                  }

                  this.select(false);
               }

               return true;
         }
      }

      return false;
   }

   protected boolean keyDown(int var1, int var2) {
      return false;
   }

   protected boolean keyRepeat(int var1, int var2) {
      return false;
   }

   protected boolean keyStatus(int var1, int var2) {
      return false;
   }

   protected boolean keyUp(int var1, int var2) {
      return false;
   }

   protected void layout(int var1, int var2) {
      throw null;
   }

   protected void makeContextMenu(ContextMenu var1) {
      this.makeContextMenu(var1, 0);
   }

   protected void makeContextMenu(ContextMenu var1, int var2) {
      if (this.isSelectable()) {
         if (this.isSelecting()) {
            var1.addItem(MenuItem.getPrefab(5));
         } else if (!this.getScreen().isScrollBehaviourView() && var2 != 65536) {
            var1.addItem(MenuItem.getPrefab(4));
         }
      }

      if (this.isSelectionCopyable() && this.isCutCopyPasteEnabled()) {
         MenuItem var3 = MenuItem.getPrefab(1);
         var1.addItem(var3);
         if (this.isSelecting()) {
            var1.setDefaultItem(var3);
         }
      }

      if (this.isSelectionCutable() && this.isCutCopyPasteEnabled()) {
         var1.addItem(MenuItem.getPrefab(2));
      }

      if (this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         MenuItem var5 = MenuItem.getPrefab(3);
         var1.addItem(var5);
         Clipboard var4 = Clipboard.getClipboard();
         if (var4.isNotYetPasted() && var4.isTimeForPasteAsDefaultNotPassed()) {
            var1.setDefaultItem(var5);
         }
      }
   }

   protected void makeMenu(Menu var1, int var2) {
   }

   protected void onMenuDismissed(Menu var1) {
      this.onMenuDismissed();
   }

   protected void onMenuDismissed() {
   }

   protected int moveFocus(int var1, int var2, int var3) {
      this.setState(0, 2);
      return var1;
   }

   protected void moveFocus(int var1, int var2, int var3, int var4) {
   }

   boolean moveFocusToPoint(int var1, int var2, int var3, int var4) {
      return this.isFocusable();
   }

   protected void onFocus(int var1) {
      this.setState(4, 0);
      this.applyThemeOnStateChange();
      if (Ui.isTTSEnabled()) {
         this.addAccessibleState(2);
         this.accessibleEventOccurred(1, null, new Object(2), this);
      }
   }

   protected void onUnfocus() {
      this.setMuddy(false);
      if (this.isSelecting()) {
         this.select(false);
      }

      this.setState(0, 4);
      this.applyThemeOnStateChange();
   }

   protected void paint(Graphics var1) {
      throw null;
   }

   Background getBackground() {
      Background var1 = null;
      if (this._themeAttributesSpecial != null) {
         return this._themeAttributesSpecial.getBackground();
      }

      if (this._themeAttributes != null) {
         var1 = this._themeAttributes.getBackground();
      }

      return var1;
   }

   protected void paintBackground(Graphics var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   void paintBorder(Graphics var1) {
      Border var2 = this.getBorder();
      if (var2 != null) {
         int var3 = var1.getColor();
         int var4 = var1.getBackgroundColor();
         var1.setColor(ThemeAttributeSet.getColor(this, 1));
         var1.setBackgroundColor(ThemeAttributeSet.getColor(this, 0));
         XYRect var5 = Ui.getTmpXYRect();
         var5.set(this._extent);
         var2.paint(var1, var5);
         Ui.returnTmpXYRect(var5);
         var1.setColor(var3);
         var1.setBackgroundColor(var4);
      }
   }

   void paintSelf(Graphics var1, boolean var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean paste(Clipboard var1) {
      return false;
   }

   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      switch (var1) {
         case 513:
         case 514:
         case 520:
            return EventHandler.getInstance().processKeyEvent(var1, var3, var2, var3, var4, false);
         default:
            return 0;
      }
   }

   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      return false;
   }

   protected final void setExtent(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   boolean validateFieldStyle(long var1) {
      if ((var1 & 54043195528445952L) == 54043195528445952L) {
         return false;
      } else if ((var1 & 13510798882111488L) == 13510798882111488L) {
         return false;
      } else {
         return (var1 & 3298534883328L) == 3298534883328L ? false : (var1 & -3526322837558132736L) == 0;
      }
   }

   public void setFont(Font var1, boolean var2) {
      if (this._fontSet != var1) {
         this._fontSet = var1;
         if (this._font != null) {
            this.applyFont();
         }

         if (var2) {
            this.updateLayout();
         }
      }
   }

   protected final void setPosition(int var1, int var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   final void setStyleSystem(long var1, long var3) {
      this._style |= var1 & -1;
      this._style &= var3 & -1 ^ -1;
   }

   public void setTag(Tag var1) {
      this._tag = var1;
      if (this._manager != null && this._manager.isValidLayout() && this.getScreen() != null) {
         this.applyTheme();
         this.updateLayout();
      }
   }

   public final void transformToScreen(XYRect var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected final void updateLayout() {
      this.assertHaveEventLock();
      this.updateLayoutHelper();
   }

   protected final void updateLayoutNowOrLater() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void select(boolean var1) {
   }

   public void selectionCopy(Clipboard var1) {
   }

   public void selectionCut(Clipboard var1) {
      this.selectionCopy(var1);
      this.selectionDelete();
   }

   public void selectionDelete() {
   }

   public void setBorder(int var1, int var2, int var3, int var4) {
      this._borderSet = true;
      this._borderTop = var1;
      this._borderRight = var2;
      this._borderBottom = var3;
      this._borderLeft = var4;
      this.updateLayout();
   }

   public void setBorder(XYEdges var1) {
      this._borderSet = true;
      this._borderTop = var1.top;
      this._borderRight = var1.right;
      this._borderBottom = var1.bottom;
      this._borderLeft = var1.left;
      this.updateLayout();
   }

   public void setBorder(Border var1) {
      this.setBorderWithoutLayout(var1);
      this.updateLayout();
   }

   public void setBorderWithoutLayout(Border var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setBorder(int var1, Border var2) {
      if (var1 == 0) {
         this.setBorder(var2);
      }
   }

   public void setChangeListener(FieldChangeListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setCookie(Object var1) {
      this.setCookieInternal(var1);
   }

   protected void setCookieInternal(Object var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDirty(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setEditable(boolean var1) {
      if (var1) {
         this._style |= 4503599627370496L;
         this._style &= -9007199254740993L;
      } else {
         this._style |= 9007199254740992L;
         this._style &= -4503599627370497L;
      }

      if (this.isFocus() && this.getInputContext() != null) {
         boolean var2 = this.getInputContext().getInputComponent() == this;
         if (var1 != var2) {
            Object var3 = new Object(this, 1004, Event.FOCUS_EVENT_MASK, 0);
            this.dispatchEvent((Event)var3);
         }
      }
   }

   public void setFocus() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setFocusListener(FocusChangeListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setId(String var1) {
      throw new RuntimeException("cod2jar: string-special");
   }

   final void setIndex(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setManager(Manager var1, int var2) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setMargin(int var1, int var2, int var3, int var4) {
      this._marginSet = true;
      this._marginTop = var1;
      this._marginRight = var2;
      this._marginBottom = var3;
      this._marginLeft = var4;
   }

   public void setMargin(XYEdges var1) {
      this._marginSet = true;
      this._marginTop = var1.top;
      this._marginRight = var1.right;
      this._marginBottom = var1.bottom;
      this._marginLeft = var1.left;
   }

   public void setPadding(int var1, int var2, int var3, int var4) {
      this._paddingSet = true;
      this._paddingTop = var1;
      this._paddingRight = var2;
      this._paddingBottom = var3;
      this._paddingLeft = var4;
      this.updateExtent();
   }

   public void setPadding(XYEdges var1) {
      this._paddingSet = true;
      this._paddingTop = var1.top;
      this._paddingRight = var1.right;
      this._paddingBottom = var1.bottom;
      this._paddingLeft = var1.left;
      this.updateExtent();
   }

   public void setMuddy(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setNonSpellCheckable(boolean var1) {
      if (var1) {
         this._style |= 2199023255552L;
         this._style &= -1099511627777L;
      } else {
         this._style |= 1099511627776L;
         this._style &= -2199023255553L;
      }
   }

   protected boolean stylusDown(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean stylusDrag(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean stylusUp(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean onCursorHover(int var1, int var2) {
      return false;
   }

   public Cursor getFocusCursor() {
      return Cursor.getPredefinedCursor(0);
   }

   protected boolean stylusDoubleTap(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean stylusTapHold(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean navigationClick(int var1, int var2) {
      if ((var1 & 2) != 0 && (var1 & 4) == 0 && this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         this.paste(Clipboard.getClipboard());
         Clipboard.getClipboard().setNotYetPasted(false);
         return true;
      }

      if ((var1 & 1) == 0 || (var1 & 16) != 0 || !this.isSelectable()) {
         return false;
      }

      if (this.isSelecting()) {
         if (this.isSelectionCopyable() && this.isCutCopyPasteEnabled()) {
            this.selectionCopy(Clipboard.getClipboard());
            Clipboard.getClipboard().setNotYetPasted(true);
            this.select(false);
         }

         return true;
      } else {
         this.select(true);
         return true;
      }
   }

   protected boolean navigationUnclick(int var1, int var2) {
      return false;
   }

   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      return false;
   }

   protected boolean trackwheelClick(int var1, int var2) {
      if ((var1 & 2) != 0 && (var1 & 4) == 0 && this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         this.paste(Clipboard.getClipboard());
         Clipboard.getClipboard().setNotYetPasted(false);
         return true;
      }

      if ((var1 & 1) == 0 || (var1 & 16) != 0 || !this.isSelectable()) {
         return false;
      }

      if (this.isSelecting()) {
         if (this.isSelectionCopyable() && this.isCutCopyPasteEnabled()) {
            this.selectionCopy(Clipboard.getClipboard());
            Clipboard.getClipboard().setNotYetPasted(true);
            this.select(false);
         }

         return true;
      } else {
         this.select(true);
         return true;
      }
   }

   protected boolean trackwheelUnclick(int var1, int var2) {
      return false;
   }

   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      return false;
   }

   @Override
   public void setFont(Font var1) {
      this.setFont(var1, true);
   }

   @Override
   public boolean isInputMethodEnabled() {
      return this.isState(64) && !this.isStyle(9007199254740992L);
   }

   public Font getFont() {
      return this._font == null ? this.getFont0() : this._font;
   }

   @Override
   public final int getFieldStyle() {
      return (int)this._style;
   }

   @Override
   public int caretPositionChanged(InputMethodEvent var1) {
      return 1;
   }

   @Override
   public void setIMCookieCache(Object var1) {
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent var1) {
      return 1;
   }

   @Override
   public XYRect getBounds() {
      return null;
   }

   @Override
   public void dispatchEvent(Event var1) {
      if (this.isState(64)) {
         if (var1 instanceof Object) {
            InputContext var2 = this.getInputContext();
            if (var2 != null) {
               var2.dispatchEvent(var1);
            }
         }
      }
   }

   @Override
   public void enableInputMethods(boolean var1) {
      this.setState(64, var1);
   }

   @Override
   public String getAccessibleName() {
      return null;
   }

   @Override
   public String getAccessibleDescription() {
      return null;
   }

   @Override
   public String getAccessibleIconDescription() {
      return null;
   }

   @Override
   public AccessibleText getAccessibleText() {
      return null;
   }

   @Override
   public AccessibleValue getAccessibleValue() {
      return null;
   }

   @Override
   public AccessibleContext getAccessibleParent() {
      return this.getScreen();
   }

   @Override
   public int getAccessibleChildCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return null;
   }

   @Override
   public int getAccessibleStateSet() {
      return this._accessibleStateSet;
   }

   @Override
   public InputContext getInputContext() {
      if (this._inputContext == null) {
         this._inputContext = InputContext.getInstance();
      }

      return this._inputContext;
   }

   @Override
   public InputMethodRequests getInputMethodRequests() {
      return null;
   }

   @Override
   public boolean isAccessibleStateSet(int var1) {
      return (this._accessibleStateSet & var1) != 0;
   }

   @Override
   public int getAccessibleRole() {
      return 0;
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 0;
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return false;
   }

   @Override
   public void actionPerformed(int var1, Object var2) {
   }

   private void invalidateCommon(int var1, int var2, int var3, int var4, XYRect var5) {
      if (var3 > 0 && var4 > 0) {
         Manager var6 = this.getManager();
         if (var6 != null) {
            int var7 = var5.x;
            int var8 = var5.x + var5.width;
            int var9 = var5.y;
            int var10 = var5.y + var5.height;
            var1 += var7;
            var2 += var9;
            int var11 = MathUtilities.clamp(var7, var1, var8);
            int var12 = MathUtilities.clamp(var7, var1 + var3, var8);
            int var13 = MathUtilities.clamp(var9, var2, var10);
            int var14 = MathUtilities.clamp(var9, var2 + var4, var10);
            var6.invalidate(var11, var13, var12 - var11, var14 - var13);
         }
      }
   }

   private boolean isBackgroundDefined() {
      return this._themeAttributes != null && this._themeAttributes.isBackgroundDefined()
         || this._themeAttributesSpecial != null && this._themeAttributesSpecial.isBackgroundDefined();
   }

   private boolean isState(int var1) {
      return (this._state & var1) == var1;
   }

   private boolean isCutCopyPasteEnabled() {
      if (!this.isState(16)) {
         boolean var1 = !ITPolicy.getBoolean(24, 36, false);
         this.setState(8, var1);
         this.setState(16, 0);
      }

      return this.isState(8);
   }

   private boolean isEventLockRequired() {
      Screen var1 = this.getScreen();
      if (var1 != null) {
         UiEngineImpl var2 = var1.getUiEngineImpl();
         if (var2 != null) {
            return var2.isEventLockRequired();
         }
      }

      return false;
   }

   protected Field(long var1) {
      this.setTag(TAG);
      if ((var1 & 54043195528445952L) == 0) {
         var1 |= 36028797018963968L;
      }

      if ((var1 & 13510798882111488L) == 0) {
         var1 |= 9007199254740992L;
      }

      if ((var1 & 3298534883328L) == 0) {
         var1 |= 2199023255552L;
      }

      if (!this.validateFieldStyle(var1)) {
         throw new Object();
      }

      this._style = var1;
   }

   private void addIMActions(ContextMenu var1, int var2) {
      if (var2 == 0) {
         InvokableAction[] var3 = this.getInputContext().getIMActions(this);
         if (var3 != null && var3.length > 0) {
            if (!var1.isEmpty()) {
               var1.addSeparatorInternal();
            }

            for (int var4 = 0; var4 < var3.length; var4++) {
               MenuItemPrefab var5 = MenuItemPrefab.get(var3[var4]);
               var1.addItem(var5);
               if (var3[var4].isDefault()) {
                  var1.setDefaultItem(var5);
               }
            }
         }
      }
   }

   private final void setState(int var1, int var2) {
      this._state |= var1;
      this._state &= ~var2;
   }

   private final void setState(int var1, boolean var2) {
      if (var2) {
         this._state |= var1;
      } else {
         this._state &= ~var1;
      }
   }

   private boolean isPaddingDefined() {
      return (this._paddingTop | this._paddingRight | this._paddingBottom | this._paddingLeft) != 0;
   }

   private final void updateLayoutHelper() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void updateExtent() {
      this._extent.width = this._content.width + this._borderLeft + this._borderRight + this._paddingLeft + this._paddingRight;
      this._extent.height = this._content.height + this._borderTop + this._borderBottom + this._paddingTop + this._paddingBottom;
   }

   protected Field() {
      this(45038195296960512L);
   }
}
