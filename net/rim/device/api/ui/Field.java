package net.rim.device.api.ui;

import net.rim.device.api.itpolicy.ITPolicy;
import net.rim.device.api.system.Application;
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
import net.rim.tid.awt.event.FocusEvent;
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

   protected void accessibleEventOccurred(int event, Object oldValue, Object newValue, AccessibleContext context) {
      AccessibleEventDispatcher.dispatchAccessibleEvent(event, oldValue, newValue, context);
   }

   public boolean acceptVisitor(FieldVisitor visitor) {
      return visitor.visit(this, 3);
   }

   public int adjustVolume(int volumeLevelChange) {
      return -1;
   }

   void assertLayoutComplete() {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void removeAccessibleState(int state) {
      this._accessibleStateSet &= ~state;
   }

   protected void addAccessibleState(int state) {
      if (this.isAccessibleStateSet(1)) {
         this.removeAccessibleState(1);
      }

      this._accessibleStateSet |= state;
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
         Font font = null;
         if (this.getState() == 6 && this._themeAttributesFocus != null) {
            font = this._themeAttributesFocus.getFont();
         }

         if (font == null && this._themeAttributes != null) {
            font = this._themeAttributes.getFont();
         }

         if (font == null) {
            Manager manager = this.getManager();
            if (manager != null) {
               font = manager._font;
            }

            if (font == null) {
               font = Font.getDefault();
            }
         }

         this._font = font;
      }
   }

   protected void applyTheme() {
      this.assertHaveEventLock();
      Theme theme = ThemeManager.getActiveTheme();
      this._themeAttributesAll = theme.getAttributeSet(this, 0);
      this._themeAttributesFocus = theme.getAttributeSet(this, 6);
      this.applyThemeOnStateChange();
   }

   protected void applyThemeOnStateChange() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   protected void applyTheme(Graphics graphics, boolean drawBackground) {
      this.assertHaveEventLock();
      if (this._themeAttributes != null) {
         this._themeAttributes.applyToGraphics(graphics);
      }

      if (this._themeAttributesSpecial != null) {
         this._themeAttributesSpecial.applyToGraphics(graphics);
      }

      if (drawBackground) {
         this.paintBackground(graphics);
      }

      graphics.setFont(this.getFont());
   }

   final void applyThemeSpecial(Graphics graphics, boolean drawBackground) {
      this.assertHaveEventLock();
      Font font = null;
      if (this._themeAttributesSpecial != null) {
         this._themeAttributesSpecial.applyToGraphics(graphics);
         font = this._themeAttributesSpecial.getFont();
         boolean drawFocus = graphics.isDrawingStyleSet(8);
         boolean drawSelect = graphics.isDrawingStyleSet(16);
         if (!drawFocus && !drawSelect) {
            if (drawBackground) {
               this.paintBackground(graphics);
            }
         } else {
            if (drawFocus) {
               graphics.setColor(ThemeAttributeSet.getColor(this, 3));
               graphics.setBackgroundColor(ThemeAttributeSet.getColor(this, 2));
            } else {
               graphics.setColor(ThemeAttributeSet.getColor(this, 5));
               graphics.setBackgroundColor(ThemeAttributeSet.getColor(this, 4));
            }

            graphics.setBackgroundImage(null, 0, 0);
            if (drawBackground) {
               graphics.clear();
            }
         }
      }

      if (font == null) {
         font = this.getFont();
      }

      graphics.setFont(font);
   }

   void callOnDisplayOrUndisplay(boolean attached) {
      if (attached) {
         this.onDisplay();
      } else {
         this.onUndisplay();
      }
   }

   public void setThemeAttributeSet(ThemeAttributeSet themeAttributes) {
      this._themeAttributes = themeAttributes;
      this._themeAttributesAll = themeAttributes;
   }

   protected void setThemeAttributesAll(ThemeAttributeSet themeAttributes, ThemeAttributeSet themeAttributesFocus) {
      this._themeAttributesAll = themeAttributes;
      this._themeAttributesFocus = themeAttributesFocus;
   }

   public void setThemeAttributesSpecial(ThemeAttributeSet themeAttributesSpecial, Graphics graphics) {
      this._themeAttributesSpecial = themeAttributesSpecial;
      if (graphics != null) {
         this.applyThemeSpecial(graphics, this.isState(32));
      }
   }

   public void setThemeAttributesSpecialClear(boolean setThemeAttributesSpecialClear) {
      this.setState(32, setThemeAttributesSpecialClear);
   }

   protected void drawFocus(Graphics graphics, boolean on) {
      XYRect rect = Ui.getTmpXYRect();
      this.getFocusRect(rect);
      this.drawHighlightRegion(graphics, 1, on, rect.x, rect.y, rect.width, rect.height);
      Ui.returnTmpXYRect(rect);
   }

   protected final void drawHighlightRegion(Graphics graphics, int style, boolean on, int x, int y, int width, int height) {
      throw new RuntimeException("cod2jar: ldc");
   }

   protected void fieldChangeNotify(int context) {
      if ((context & -2147483648) != 0) {
         this.setDirty(false);
      } else {
         this.setMuddy(true);
      }

      if (this._changeListener != null) {
         try {
            this._changeListener.fieldChanged(this, context);
            return;
         } catch (Throwable var3) {
         }
      }
   }

   protected void focusAdd(boolean draw) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void focusChangeNotify(int action) {
      Screen screen = this.getScreen();
      if (action == 1
         && screen != null
         && screen.isDisplayed()
         && !(screen instanceof Object)
         && screen.isScreenFocus()
         && (this instanceof Object || !(this instanceof Object))) {
         EventHandler.getInstance().focusGained(this, (int)System.currentTimeMillis(), Application.getApplication().getProcessId());
      }

      if (this._focusListener != null) {
         try {
            this._focusListener.focusChanged(this, action);
         } catch (Throwable var4) {
         }
      }

      if (screen != null) {
         screen.focusChangeNotifyListeners(this, action);
      }
   }

   protected void focusRemove() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final Border getBorder() {
      return this._border;
   }

   public final void getBorder(XYEdges border) {
      border.top = this._borderTop;
      border.right = this._borderRight;
      border.bottom = this._borderBottom;
      border.left = this._borderLeft;
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

   public final void getContentRect(XYRect rect) {
      rect.set(this._content);
   }

   public final int getContentTop() {
      return this._content.y;
   }

   public final int getContentWidth() {
      return this._content.width;
   }

   public ContextMenu getContextMenu() {
      ContextMenu contextMenu = ContextMenu.getInstance();
      contextMenu.setTarget(this);
      this.makeContextMenu(contextMenu);
      this.addIMActions(contextMenu, 0);
      return contextMenu;
   }

   public ContextMenu getContextMenu(int instance) {
      ContextMenu contextMenu = ContextMenu.getInstance();
      contextMenu.setTarget(this);
      this.makeContextMenu(contextMenu, instance);
      this.addIMActions(contextMenu, instance);
      return contextMenu;
   }

   public final Object getCookie() {
      return this.getCookieInternal();
   }

   protected Object getCookieInternal() {
      return this._cookie;
   }

   public String getDebugTree(int treeStyle) {
      StringBuffer buffer = (StringBuffer)(new Object());
      this.getDebugTreeHelper(treeStyle, buffer, 0);
      return buffer.toString();
   }

   void getDebugTreeHelper(int treeStyle, StringBuffer buffer, int indent) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final XYRect getExtent() {
      return this._extent;
   }

   public final void getExtent(XYRect extent) {
      extent.set(this._extent);
   }

   public final void assertHaveEventLock() {
      Screen screen = this.getScreen();
      if (screen != null) {
         UiEngineImpl engine = screen.getUiEngineImpl();
         if (engine != null) {
            engine.assertHaveEventLock();
         }
      }
   }

   public FocusChangeListener getFocusListener() {
      return this._focusListener;
   }

   public void getFocusRect(XYRect rect) {
      rect.set(this._extent.x - this._content.x, this._extent.y - this._content.y, this._extent.width, this._extent.height);
   }

   public void getFocusRectPhantom(XYRect rect) {
      this.getFocusRect(rect);
   }

   void doVisibilityWalk(boolean visible) {
      this.onVisibilityChange(visible);
   }

   Font getFont0() {
      Field step = this;

      while (step._fontSet == null) {
         ThemeAttributeSet theme = step._themeAttributes;
         if (theme != null && theme.getFont() != null) {
            return theme.getFont();
         }

         step = step._manager;
         if (step == null) {
            return Font.getDefault();
         }
      }

      return step._fontSet;
   }

   public Font getFontIfSet() {
      return this._fontSet;
   }

   protected final Graphics getGraphics0() {
      XYRect clip = Ui.getTmpXYRect();
      clip.set(0, 0, this.getContentWidth(), this.getContentHeight());
      Graphics graphics = this.getGraphics0(clip, false);
      Ui.returnTmpXYRect(clip);
      return graphics;
   }

   Graphics getGraphics0(XYRect clip, boolean drawBackground) {
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

   public final void getMargin(XYEdges margin) {
      margin.top = this._marginTop;
      margin.right = this._marginRight;
      margin.bottom = this._marginBottom;
      margin.left = this._marginLeft;
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

   public void getNextFocus(int direction, XYRect rect) {
      this.getFocusRect(rect);
   }

   public Field getOriginal() {
      return this;
   }

   public final void getPadding(XYEdges padding) {
      padding.top = this._paddingTop;
      padding.right = this._paddingRight;
      padding.bottom = this._paddingBottom;
      padding.left = this._paddingLeft;
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
      Manager manager = this.getManager();
      if (manager != null) {
         XYRect extent = this.getExtent();
         manager.invalidate(extent.x, extent.y, extent.width, extent.height);
      }
   }

   protected void invalidate(int x, int y, int width, int height) {
      XYRect content = Ui.getTmpXYRect();
      this.getContentRect(content);
      this.invalidateCommon(x, y, width, height, content);
      Ui.returnTmpXYRect(content);
   }

   protected void invalidateAll(int x, int y, int width, int height) {
      this.invalidateCommon(x, y, width, height, this.getExtent());
   }

   void invalidateLayout0() {
   }

   protected boolean invokeAction(int action) {
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
      Background background = this.getBackground();
      return background == null || background.isTransparent();
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

   protected void onVisibilityChange(boolean visible) {
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

   public final boolean isStyle(long style) {
      return (this._style & style) == style;
   }

   public final boolean isVisible() {
      return this.isVisible0();
   }

   boolean isVisible0() {
      Screen scr = this.getScreen();
      return scr != null && scr.isVisible0();
   }

   protected boolean keyChar(char character, int status, int time) {
      if (this.isSelecting() && !(this instanceof Object)) {
         switch (character) {
            case '\b':
            case '\u007f':
               if (this.isSelectionDeleteable()) {
                  if ((status & 2) != 0) {
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

   protected boolean keyControl(char character, int status, int time) {
      if (this.isSelecting()) {
         switch (character) {
            case '\u001b':
               this.select(false);
               return true;
            case '\u007f':
               if (this.isSelectionDeleteable()) {
                  if ((status & 2) != 0) {
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

   protected boolean keyDown(int keycode, int time) {
      return false;
   }

   protected boolean keyRepeat(int keycode, int time) {
      return false;
   }

   protected boolean keyStatus(int keycode, int time) {
      return false;
   }

   protected boolean keyUp(int keycode, int time) {
      return false;
   }

   protected void layout(int _1, int _2) {
      throw null;
   }

   protected void makeContextMenu(ContextMenu contextMenu) {
      this.makeContextMenu(contextMenu, 0);
   }

   protected void makeContextMenu(ContextMenu contextMenu, int instance) {
      if (this.isSelectable()) {
         if (this.isSelecting()) {
            contextMenu.addItem(MenuItem.getPrefab(5));
         } else if (!this.getScreen().isScrollBehaviourView() && instance != 65536) {
            contextMenu.addItem(MenuItem.getPrefab(4));
         }
      }

      if (this.isSelectionCopyable() && this.isCutCopyPasteEnabled()) {
         MenuItem copyItem = MenuItem.getPrefab(1);
         contextMenu.addItem(copyItem);
         if (this.isSelecting()) {
            contextMenu.setDefaultItem(copyItem);
         }
      }

      if (this.isSelectionCutable() && this.isCutCopyPasteEnabled()) {
         contextMenu.addItem(MenuItem.getPrefab(2));
      }

      if (this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         MenuItem pasteItem = MenuItem.getPrefab(3);
         contextMenu.addItem(pasteItem);
         Clipboard clip = Clipboard.getClipboard();
         if (clip.isNotYetPasted() && clip.isTimeForPasteAsDefaultNotPassed()) {
            contextMenu.setDefaultItem(pasteItem);
         }
      }
   }

   protected void makeMenu(Menu menu, int instance) {
   }

   protected void onMenuDismissed(Menu menu) {
      this.onMenuDismissed();
   }

   protected void onMenuDismissed() {
   }

   protected int moveFocus(int amount, int status, int time) {
      this.setState(0, 2);
      return amount;
   }

   protected void moveFocus(int x, int y, int status, int time) {
   }

   boolean moveFocusToPoint(int x, int y, int status, int time) {
      return this.isFocusable();
   }

   protected void onFocus(int direction) {
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

   protected void paint(Graphics _1) {
      throw null;
   }

   Background getBackground() {
      Background background = null;
      if (this._themeAttributesSpecial != null) {
         return this._themeAttributesSpecial.getBackground();
      }

      if (this._themeAttributes != null) {
         background = this._themeAttributes.getBackground();
      }

      return background;
   }

   protected void paintBackground(Graphics graphics) {
      throw new RuntimeException("cod2jar: type check");
   }

   void paintBorder(Graphics graphics) {
      Border border = this.getBorder();
      if (border != null) {
         int fgPrevious = graphics.getColor();
         int bgPrevious = graphics.getBackgroundColor();
         graphics.setColor(ThemeAttributeSet.getColor(this, 1));
         graphics.setBackgroundColor(ThemeAttributeSet.getColor(this, 0));
         XYRect rect = Ui.getTmpXYRect();
         rect.set(this._extent);
         border.paint(graphics, rect);
         Ui.returnTmpXYRect(rect);
         graphics.setColor(fgPrevious);
         graphics.setBackgroundColor(bgPrevious);
      }
   }

   void paintSelf(Graphics graphics, boolean addExtent, int xContentAdjust, int yContentAdjust) {
      throw new RuntimeException("cod2jar: type check");
   }

   public boolean paste(Clipboard cb) {
      return false;
   }

   public int processKeyEvent(int event, char key, int keycode, int time) {
      switch (event) {
         case 513:
         case 514:
         case 520:
            return EventHandler.getInstance().processKeyEvent(event, keycode, key, keycode, time, false);
         default:
            return 0;
      }
   }

   public boolean processNavigationEvent(int event, int dx, int dy, int status, int time) {
      return false;
   }

   protected final void setExtent(int width, int height) {
      throw new RuntimeException("cod2jar: type check");
   }

   boolean validateFieldStyle(long style) {
      if ((style & 54043195528445952L) == 54043195528445952L) {
         return false;
      } else if ((style & 13510798882111488L) == 13510798882111488L) {
         return false;
      } else {
         return (style & 3298534883328L) == 3298534883328L ? false : (style & -3526322837558132736L) == 0;
      }
   }

   public void setFont(Font font, boolean layout) {
      if (this._fontSet != font) {
         this._fontSet = font;
         if (this._font != null) {
            this.applyFont();
         }

         if (layout) {
            this.updateLayout();
         }
      }
   }

   protected final void setPosition(int x, int y) {
      throw new RuntimeException("cod2jar: type check");
   }

   final void setStyleSystem(long on, long off) {
      this._style |= on & -1;
      this._style &= off & -1 ^ -1;
   }

   public void setTag(Tag tag) {
      this._tag = tag;
      if (this._manager != null && this._manager.isValidLayout() && this.getScreen() != null) {
         this.applyTheme();
         this.updateLayout();
      }
   }

   public final void transformToScreen(XYRect rect) {
      throw new RuntimeException("cod2jar: type check");
   }

   protected final void updateLayout() {
      this.assertHaveEventLock();
      this.updateLayoutHelper();
   }

   protected final void updateLayoutNowOrLater() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void select(boolean enable) {
   }

   public void selectionCopy(Clipboard cb) {
   }

   public void selectionCut(Clipboard cb) {
      this.selectionCopy(cb);
      this.selectionDelete();
   }

   public void selectionDelete() {
   }

   public void setBorder(int top, int right, int bottom, int left) {
      this._borderSet = true;
      this._borderTop = top;
      this._borderRight = right;
      this._borderBottom = bottom;
      this._borderLeft = left;
      this.updateLayout();
   }

   public void setBorder(XYEdges border) {
      this._borderSet = true;
      this._borderTop = border.top;
      this._borderRight = border.right;
      this._borderBottom = border.bottom;
      this._borderLeft = border.left;
      this.updateLayout();
   }

   public void setBorder(Border border) {
      this.setBorderWithoutLayout(border);
      this.updateLayout();
   }

   public void setBorderWithoutLayout(Border border) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setBorder(int state, Border border) {
      if (state == 0) {
         this.setBorder(border);
      }
   }

   public void setChangeListener(FieldChangeListener listener) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final void setCookie(Object cookie) {
      this.setCookieInternal(cookie);
   }

   protected void setCookieInternal(Object cookie) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void setDirty(boolean dirty) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setEditable(boolean editable) {
      if (editable) {
         this._style |= 4503599627370496L;
         this._style &= -9007199254740993L;
      } else {
         this._style |= 9007199254740992L;
         this._style &= -4503599627370497L;
      }

      if (this.isFocus() && this.getInputContext() != null) {
         boolean isInputComponent = this.getInputContext().getInputComponent() == this;
         if (editable != isInputComponent) {
            FocusEvent updateFocus = (FocusEvent)(new Object(this, 1004, Event.FOCUS_EVENT_MASK, 0));
            this.dispatchEvent(updateFocus);
         }
      }
   }

   public void setFocus() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setFocusListener(FocusChangeListener listener) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setId(String idName) {
      throw new RuntimeException("cod2jar: string-special");
   }

   final void setIndex(int index) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setManager(Manager manager, int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setMargin(int top, int right, int bottom, int left) {
      this._marginSet = true;
      this._marginTop = top;
      this._marginRight = right;
      this._marginBottom = bottom;
      this._marginLeft = left;
   }

   public void setMargin(XYEdges margin) {
      this._marginSet = true;
      this._marginTop = margin.top;
      this._marginRight = margin.right;
      this._marginBottom = margin.bottom;
      this._marginLeft = margin.left;
   }

   public void setPadding(int top, int right, int bottom, int left) {
      this._paddingSet = true;
      this._paddingTop = top;
      this._paddingRight = right;
      this._paddingBottom = bottom;
      this._paddingLeft = left;
      this.updateExtent();
   }

   public void setPadding(XYEdges padding) {
      this._paddingSet = true;
      this._paddingTop = padding.top;
      this._paddingRight = padding.right;
      this._paddingBottom = padding.bottom;
      this._paddingLeft = padding.left;
      this.updateExtent();
   }

   public void setMuddy(boolean muddy) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setNonSpellCheckable(boolean nonSpellCheckable) {
      if (nonSpellCheckable) {
         this._style |= 2199023255552L;
         this._style &= -1099511627777L;
      } else {
         this._style |= 1099511627776L;
         this._style &= -2199023255553L;
      }
   }

   protected boolean stylusDown(int x, int y, int status, int time) {
      return false;
   }

   protected boolean stylusDrag(int x, int y, int status, int time) {
      return false;
   }

   protected boolean stylusUp(int x, int y, int status, int time) {
      return false;
   }

   protected boolean stylusTap(int x, int y, int status, int time) {
      return false;
   }

   protected boolean onCursorHover(int x, int y) {
      return false;
   }

   public Cursor getFocusCursor() {
      return Cursor.getPredefinedCursor(0);
   }

   protected boolean stylusDoubleTap(int x, int y, int status, int time) {
      return false;
   }

   protected boolean stylusTapHold(int x, int y, int status, int time) {
      return false;
   }

   protected boolean navigationClick(int status, int time) {
      if ((status & 2) != 0 && (status & 4) == 0 && this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         this.paste(Clipboard.getClipboard());
         Clipboard.getClipboard().setNotYetPasted(false);
         return true;
      }

      if ((status & 1) == 0 || (status & 16) != 0 || !this.isSelectable()) {
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

   protected boolean navigationUnclick(int status, int time) {
      return false;
   }

   protected boolean navigationMovement(int dx, int dy, int status, int time) {
      return false;
   }

   protected boolean trackwheelClick(int status, int time) {
      if ((status & 2) != 0 && (status & 4) == 0 && this.isPasteable() && Clipboard.getClipboard().get() != null && this.isCutCopyPasteEnabled()) {
         this.paste(Clipboard.getClipboard());
         Clipboard.getClipboard().setNotYetPasted(false);
         return true;
      }

      if ((status & 1) == 0 || (status & 16) != 0 || !this.isSelectable()) {
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

   protected boolean trackwheelUnclick(int status, int time) {
      return false;
   }

   protected boolean trackwheelRoll(int amount, int status, int time) {
      return false;
   }

   @Override
   public void setFont(Font font) {
      this.setFont(font, true);
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
   public int caretPositionChanged(InputMethodEvent event) {
      return 1;
   }

   @Override
   public void setIMCookieCache(Object cookie) {
   }

   @Override
   public int inputMethodTextChanged(InputMethodEvent event) {
      return 1;
   }

   @Override
   public XYRect getBounds() {
      return null;
   }

   @Override
   public void dispatchEvent(Event rEvent) {
      if (this.isState(64)) {
         if (rEvent instanceof Object) {
            InputContext ic = this.getInputContext();
            if (ic != null) {
               ic.dispatchEvent(rEvent);
            }
         }
      }
   }

   @Override
   public void enableInputMethods(boolean enable) {
      this.setState(64, enable);
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
   public AccessibleContext getAccessibleChildAt(int index) {
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
   public boolean isAccessibleStateSet(int state) {
      return (this._accessibleStateSet & state) != 0;
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
   public AccessibleContext getAccessibleSelectionAt(int index) {
      return null;
   }

   @Override
   public boolean isAccessibleChildSelected(int index) {
      return false;
   }

   @Override
   public void actionPerformed(int action, Object parameter) {
   }

   private void invalidateCommon(int x, int y, int width, int height, XYRect extent) {
      if (width > 0 && height > 0) {
         Manager manager = this.getManager();
         if (manager != null) {
            int clipx1 = extent.x;
            int clipx2 = extent.x + extent.width;
            int clipy1 = extent.y;
            int clipy2 = extent.y + extent.height;
            x += clipx1;
            y += clipy1;
            int newx1 = MathUtilities.clamp(clipx1, x, clipx2);
            int newx2 = MathUtilities.clamp(clipx1, x + width, clipx2);
            int newy1 = MathUtilities.clamp(clipy1, y, clipy2);
            int newy2 = MathUtilities.clamp(clipy1, y + height, clipy2);
            manager.invalidate(newx1, newy1, newx2 - newx1, newy2 - newy1);
         }
      }
   }

   private boolean isBackgroundDefined() {
      return this._themeAttributes != null && this._themeAttributes.isBackgroundDefined()
         || this._themeAttributesSpecial != null && this._themeAttributesSpecial.isBackgroundDefined();
   }

   private boolean isState(int state) {
      return (this._state & state) == state;
   }

   private boolean isCutCopyPasteEnabled() {
      if (!this.isState(16)) {
         boolean isClipboardEnabled = !ITPolicy.getBoolean(24, 36, false);
         this.setState(8, isClipboardEnabled);
         this.setState(16, 0);
      }

      return this.isState(8);
   }

   private boolean isEventLockRequired() {
      Screen screen = this.getScreen();
      if (screen != null) {
         UiEngineImpl engine = screen.getUiEngineImpl();
         if (engine != null) {
            return engine.isEventLockRequired();
         }
      }

      return false;
   }

   protected Field(long style) {
      this.setTag(TAG);
      if ((style & 54043195528445952L) == 0) {
         style |= 36028797018963968L;
      }

      if ((style & 13510798882111488L) == 0) {
         style |= 9007199254740992L;
      }

      if ((style & 3298534883328L) == 0) {
         style |= 2199023255552L;
      }

      if (!this.validateFieldStyle(style)) {
         throw new Object();
      }

      this._style = style;
   }

   private void addIMActions(ContextMenu contextMenu, int instance) {
      if (instance == 0) {
         InvokableAction[] actions = this.getInputContext().getIMActions(this);
         if (actions != null && actions.length > 0) {
            if (!contextMenu.isEmpty()) {
               contextMenu.addSeparatorInternal();
            }

            for (int i = 0; i < actions.length; i++) {
               MenuItem item = MenuItemPrefab.get(actions[i]);
               contextMenu.addItem(item);
               if (actions[i].isDefault()) {
                  contextMenu.setDefaultItem(item);
               }
            }
         }
      }
   }

   private final void setState(int on, int off) {
      this._state |= on;
      this._state &= ~off;
   }

   private final void setState(int state, boolean on) {
      if (on) {
         this._state |= state;
      } else {
         this._state &= ~state;
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
