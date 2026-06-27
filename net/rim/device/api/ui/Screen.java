package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.BackdoorKeyProcessor;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.system.StylusListener;
import net.rim.device.api.system.TrackwheelListener;
import net.rim.device.api.ui.accessibility.AccessibleContext;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.internal.media.MediaPlayerState;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.Background;
import net.rim.device.internal.ui.BackingStore;
import net.rim.tid.awt.im.InputContext;
import net.rim.vm.TraceBack;

public class Screen extends Manager {
   private Object[] _focusChangeListeners;
   private Object[] _keyListeners;
   private Object[] _paintabilityListeners;
   private Object[] _screenUiEngineAttachedListeners;
   private Object[] _stylusListeners;
   private Object[] _trackwheelListeners;
   private Manager _delegate;
   private XYRect _invalid = (XYRect)(new Object());
   int _layoutGeneration;
   private int _themeGeneration;
   private int _inPaint;
   private int _inLayout;
   private UiEngineImpl _uiEngine;
   private boolean _globalScreen;
   private boolean _acceptsInput = true;
   private boolean _inputStartReceived;
   private boolean _transparentBorder;
   private boolean _transparentBackground;
   private int _backdoorCode;
   private int _backdoorAltStatus = 1;
   private int _screenState;
   private Graphics _graphicsInUse;
   private Menu _menu;
   private BackingStore _backingStore;
   private boolean _scrollBehaviourView;
   private boolean _scrollBehaviourSelect;
   private int _trackballSensitivityXOffset = Integer.MAX_VALUE;
   private int _trackballSensitivityYOffset = Integer.MAX_VALUE;
   private int _trackballFilter = -1;
   private boolean _noSynchOnInvalidate;
   private XYRect _lastInvalid;
   private boolean _saveLastInvalid;
   private boolean _clickHandled;
   private int _pushMethod;
   private boolean _dismissing = false;
   private boolean _clearBackingStore;
   private boolean _paintController;
   private boolean _backingStoreUpdated;
   private int _superCalled;
   private static Tag TAG;
   private static Tag TAG_ROOT;
   public static final long DEFAULT_MENU;
   public static final long TRANSPARENT;
   public static final long DEFAULT_CLOSE;
   private static final long SCREEN_STYLES;
   private static final int STATE_UI_ENGINE_ATTACHED;
   private static final int STATE_NO_GATE_INPUT;
   private static final int STATE_IS_OBSCURED;
   private static final int STATE_IS_VISIBLE;
   private static final int STATE_IS_PAINTABLE;
   private static final int STATE_CATCH_PAINT_EXCEPTIONS;
   private static final int STATE_HAS_FOCUS;
   private static final int STATE_STATUS_TOGGLE;
   private static final int STATUS_LEGACY_MASK;
   private static final int STATUS_TOGGLE_BIT;
   private static final boolean DEBUG_INVALID_REGION;
   private static final int SUPER_UNUSED;
   private static final int SUPER_ON_UI_ENGINE_ATTACHED;
   private static final int FIELD_VISITOR_TYPE_STATE_IS_PAINTABLE;

   public void addFocusChangeListener(FocusChangeListener var1) {
      this._focusChangeListeners = ListenerUtilities.addListener(this._focusChangeListeners, var1);
   }

   public final synchronized void addKeyListener(KeyListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void addPaintabilityListener(PaintabilityListener var1) {
      this._paintabilityListeners = ListenerUtilities.addListener(this._paintabilityListeners, var1);
   }

   public void addScreenUiEngineAttachedListener(ScreenUiEngineAttachedListener var1) {
      this._screenUiEngineAttachedListeners = ListenerUtilities.addListener(this._screenUiEngineAttachedListeners, var1);
   }

   public final synchronized void addStylusListener(StylusListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final synchronized void addTrackwheelListener(TrackwheelListener var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void callOnUiEngineAttached(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final void callOnUiEngineDettachedWithoutNotify() {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void clearBackingStore() {
      this._backingStore = null;
   }

   final void cleanBackingStore() {
      if (this._backingStore != null) {
         this._backingStore.cleanBackingStore();
      }
   }

   final void setClearBackingStore(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public void close() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public boolean dispatchKeyEvent(int var1, char var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean dispatchStylusEvent(int var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   boolean dispatchNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean dispatchTrackwheelEvent(int var1, int var2, int var3, int var4) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this.dispatchNavigationEvent(var1, 0, var2, var3 | 1073741824, var4);
   }

   public void doLayout() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public final void doPaint() {
      UiEngineImpl var1 = this._uiEngine;
      if (var1 != null) {
         var1.doPainting();
      }
   }

   final void doPaintInternal(XYRect var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final boolean isBackingStoreUpdated() {
      return this._backingStoreUpdated;
   }

   final void setBackingStoreUpdated(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   final void doPaint0(boolean var1, boolean var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public boolean doSaveInternal() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this.onSave();
   }

   void ensureFocusVisible() {
      Field var1 = this.getLeafFieldWithFocus();
      if (var1 != null) {
         XYRect var2 = Ui.getTmpXYRect();
         var1.getFocusRectPhantom(var2);
         this.ensureRegionVisible(var1, var2.x, var2.y, var2.width, var2.height, false, true);
         Ui.returnTmpXYRect(var2);
      }
   }

   public void ensureRegionVisible(Field var1, int var2, int var3, int var4, int var5) {
      boolean var6 = this._invalid.width == 0 || this._invalid.height == 0;
      this.ensureRegionVisible(var1, var2, var3, var4, var5, var6, true);
   }

   void focusChangeNotifyListeners(Field var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final boolean acceptsInput() {
      return this._acceptsInput;
   }

   public Application getApplication() {
      return this._uiEngine != null ? this._uiEngine.getApplication() : null;
   }

   final BackingStore getBackingStore() {
      return this._backingStore;
   }

   public Manager getDelegate() {
      return this._delegate;
   }

   public Graphics getGraphics() {
      throw new RuntimeException("cod2jar: ldc");
   }

   Graphics getGraphics(XYRect var1, boolean var2) {
      this.assertHaveEventLock();
      if (!var2) {
         UiEngine var3 = this.getUiEngine();
         if (var3 == null || !this.isScreenState(16)) {
            return Graphics.getNullGraphics();
         }
      }

      Graphics var5 = Graphics.getGraphics(this);
      XYRect var4 = this.getExtent();
      if (var1 != null) {
         var5.pushContext(var1, var4.x, var4.y);
         return var5;
      } else {
         var5.pushRegion(var4);
         return var5;
      }
   }

   final XYRect getInvalid() {
      return this._invalid;
   }

   XYRect getLastInvalid() {
      return this._lastInvalid;
   }

   public Menu getMenu(int var1) {
      Object var2 = new Object(65536);
      Menu.setTargetScreen(this);
      ((Menu)var2).setTargetScreenVirtual(this);
      ((Menu)var2).setInstance(var1);
      this.makeMenuWithContext((Menu)var2, var1);
      return (Menu)var2;
   }

   public Screen getScreenAbove() {
      UiEngineImpl var1 = this._uiEngine;
      if (var1 == null) {
         throw new Object();
      } else {
         return var1.getScreenAbove(this);
      }
   }

   public Screen getScreenBelow() {
      UiEngineImpl var1 = this._uiEngine;
      if (var1 == null) {
         throw new Object();
      } else {
         return var1.getScreenBelow(this);
      }
   }

   public int getTrackballSensitivityXOffset() {
      return this._trackballSensitivityXOffset;
   }

   public int getTrackballSensitivityYOffset() {
      return this._trackballSensitivityYOffset;
   }

   public final UiEngine getUiEngine() {
      UiEngineImpl.assertIpcOrDependency();
      return this._uiEngine;
   }

   final UiEngineImpl getUiEngineImpl() {
      return this._uiEngine;
   }

   public void invalidateLayout() {
      if (this._uiEngine != null) {
         throw new Object();
      }

      this.invalidateLayout0();
   }

   boolean isCatchPaintExceptions() {
      return this.isScreenState(32);
   }

   final boolean isClearBackingStore() {
      return this._clearBackingStore;
   }

   boolean isDismissing() {
      return this._dismissing;
   }

   public boolean isGlobal() {
      return this._globalScreen;
   }

   public boolean isGlobalStatus() {
      return this.isGlobal();
   }

   boolean isInLayout() {
      return this._inLayout != 0;
   }

   public boolean isObscured() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this.isScreenState(4);
   }

   boolean isPaintController() {
      return this._paintController;
   }

   public boolean isScrollBehaviourView() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      return this.isScrollBehaviourViewInternal();
   }

   public final boolean isTransparent() {
      return this.isStyle(68719476736L) || this.isTransparentBackground();
   }

   protected boolean isTransparentBackground() {
      return this._transparentBackground;
   }

   public final boolean isTransparentBorder() {
      return this._transparentBorder;
   }

   public boolean isUiEngineAttached() {
      return this.isScreenState(1);
   }

   final boolean isValid() {
      UiEngineImpl var1 = this._uiEngine;
      return var1 != null && !var1.isValid() ? false : this._invalid.width == 0 || this._invalid.height == 0;
   }

   void setSaveLastInvalid(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   boolean isScreenFocus() {
      return this.isScreenState(64);
   }

   public boolean invokeDefaultMenuItem(int var1) {
      boolean var2 = false;
      if (this.isStyle(65536) && !DeviceInfo.isInHolster()) {
         MenuItem var3 = this.getDefaultMenuItem(var1);
         if (var3 != null) {
            var3.run();
            var2 = true;
         }

         ContextMenu.getInstance().setTarget(null);
         Menu.setTargetScreen(null);
      }

      return var2;
   }

   public MenuItem getDefaultMenuItem(int var1) {
      MenuItem var2 = null;
      if (this.isStyle(65536)) {
         Menu var3 = this.getMenu(var1);
         var2 = var3.getDefault();
      }

      return var2;
   }

   final void invalidateInternal() {
      XYRect var1 = this.getExtent();
      this.invalidateCommon(0, 0, var1.width, var1.height, var1);
   }

   final void invalidateInternal(int var1, int var2, int var3, int var4) {
      XYRect var5 = Ui.getTmpXYRect();
      this.getContentRect(var5);
      this.invalidateCommon(var1, var2, var3, var4, var5);
      Ui.returnTmpXYRect(var5);
   }

   protected boolean openDevelopmentBackdoor(int var1) {
      return false;
   }

   protected boolean openProductionBackdoor(int var1) {
      return false;
   }

   protected boolean keyCharUnhandled(char var1, int var2, int var3) {
      boolean var4 = false;
      if (var1 == 27) {
         if (this._scrollBehaviourSelect) {
            this._scrollBehaviourSelect = false;
            this.invalidate();
         } else {
            var4 = this.onClose();
         }
      } else if (var1 == 149 && Trackball.isSupported()) {
         if (Ui.getTrackballClickAction() != 0) {
            var4 = this.onMenu(1073741824);
         } else {
            var4 = this.invokeAction(1);
         }
      }

      return var4;
   }

   protected boolean blockInputEvents(int var1, int var2) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      switch (var1) {
         case 513:
         case 520:
            this._inputStartReceived = true;
            return false;
         case 514:
         case 515:
         case 32768:
            if (var1 == 514 && Keypad.key(var2) == 20 && InputContext.getInstance().isSureType()) {
               return false;
            }

            if (!this._inputStartReceived && !this.isScreenState(2)) {
               return true;
            }
            break;
         case 6914:
            this._inputStartReceived = true;
            return false;
         case 6915:
            if (!this._inputStartReceived) {
               return true;
            }
      }

      return false;
   }

   protected boolean ignoreBacklightOffKeyEvent(int var1, char var2, int var3, int var4) {
      return true;
   }

   protected final void layoutDelegate(int var1, int var2) {
      this.layoutChild(this._delegate, var1, var2);
   }

   protected final void makeMenuWithContext(Menu var1, int var2) {
      Field var3 = this.getLeafFieldWithFocus();
      if (var3 != null) {
         if (var2 != 0) {
            ContextMenu var4 = var3.getContextMenu(var2);
            var1.add(var4, true);
         } else {
            ContextMenu var5 = var3.getContextMenu();
            var1.add(var5, true);
         }
      } else {
         var3 = this;
      }

      while (var3 != null) {
         var3.makeMenu(var1, var2);
         if (var3 == this) {
            return;
         }

         var3 = var3.getManager();
      }
   }

   public boolean onClose() {
      boolean var1 = true;
      if (this.isStyle(131072)) {
         if (this.isDirty()) {
            var1 = this.onSavePrompt();
         }

         if (var1) {
            this.close();
            return true;
         }
      }

      return false;
   }

   protected void onFocusNotify(boolean var1) {
      this._inputStartReceived = false;
      this.setScreenState(64, var1);
      this.applyTrackballOffsets();
      if (Ui.isTTSEnabled()) {
         if (var1) {
            this.accessibleEventOccurred(1, new Object(this.getAccessibleStateSet()), new Object(2), this);
            this.addAccessibleState(2);
            return;
         }

         this.removeAccessibleState(2);
      }
   }

   public boolean onMenu(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean onSave() {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected boolean onSavePrompt() {
      return true;
   }

   protected void onUiEngineAttached(boolean var1) {
   }

   public void save() {
   }

   void doRemoveFocus() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public void removeFocus() {
      this.doRemoveFocus();
      this.onUnfocus();
   }

   void doAddFocus(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected final void setAcceptsInput(boolean var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._acceptsInput = var1;
      this.setPaintController(false);
   }

   protected void setBackdoorAltStatus(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setCatchPaintExceptions(boolean var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.setScreenState(32, var1);
   }

   protected void setDefaultClose(boolean var1) {
      if (var1) {
         this.setStyleSystem(131072, 0);
      } else {
         this.setStyleSystem(0, 131072);
      }
   }

   public void setScrollBehaviourView(boolean var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public void setScrollBehaviourSelect(boolean var1) {
      if (this._scrollBehaviourSelect != var1) {
         this._scrollBehaviourSelect = var1;
         this.invalidate();
      }
   }

   void findNewFocus(boolean var1) {
      if (this._delegate.getFieldWithFocus() != null) {
         this.doFocusMove(true, true, Screen$FindNewFocusSelector.getSelector(this, var1));
      }
   }

   void setFocus(Field var1) {
      if (var1 instanceof Manager) {
         for (Field var2 = this.getFieldWithFocus(); var2 instanceof Manager; var2 = ((Manager)var2).getFieldWithFocus()) {
            if (var2 == var1) {
               return;
            }
         }
      } else if (this.getLeafFieldWithFocus() == var1) {
         return;
      }

      this.doFocusMove(true, true, Screen$SetFocusSelector.getSelector(this, var1));
   }

   protected boolean navigationUnclickUnhandled(int var1, int var2) {
      boolean var3 = false;
      if (!var3 && !DeviceInfo.isInHolster()) {
         if (Ui.getTrackballClickAction() == 0) {
            return this.onMenu(0);
         }

         Field var4 = this.getLeafFieldWithFocus();
         if (var4 != null && var4.isSelecting()) {
            this.onMenu(65537);
            return true;
         }

         var3 = this.invokeAction(1);
         if (!var3) {
            var3 = this.onMenu(65536);
         }
      }

      return var3;
   }

   protected boolean trackwheelClickUnhandled(int var1, int var2) {
      boolean var3 = false;
      if (!var3 && !DeviceInfo.isInHolster()) {
         if (Ui.getTrackwheelClickAction() == 0) {
            return this.onMenu(0);
         }

         var3 = this.invokeAction(1);
      }

      return var3;
   }

   public boolean defaultStylusAction(int var1) {
      boolean var2 = false;
      if (this.isStyle(65536) && !DeviceInfo.isInHolster()) {
         MenuItem var3 = this.getDefaultMenuItem(var1);
         if (var3 != null) {
            var3.run();
            var2 = true;
         }

         ContextMenu.getInstance().setTarget(null);
         Menu.setTargetScreen(null);
      }

      return var2;
   }

   public void removeFocusChangeListener(FocusChangeListener var1) {
      this._focusChangeListeners = ListenerUtilities.addListener(this._focusChangeListeners, var1);
   }

   public final synchronized void removeKeyListener(KeyListener var1) {
      this._keyListeners = ListenerUtilities.removeListener(this._keyListeners, var1);
   }

   public void removePaintabilityListener(PaintabilityListener var1) {
      this._paintabilityListeners = ListenerUtilities.addListener(this._paintabilityListeners, var1);
   }

   public final synchronized void removeScreenUiEngineAttachedListener(ScreenUiEngineAttachedListener var1) {
      this._screenUiEngineAttachedListeners = ListenerUtilities.removeListener(this._screenUiEngineAttachedListeners, var1);
   }

   public final synchronized void removeStylusListener(StylusListener var1) {
      this._stylusListeners = ListenerUtilities.removeListener(this._stylusListeners, var1);
   }

   public final synchronized void removeTrackwheelListener(TrackwheelListener var1) {
      this._trackwheelListeners = ListenerUtilities.removeListener(this._trackwheelListeners, var1);
   }

   public final boolean scroll(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void setUiEngine(UiEngineImpl var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public final boolean setFocus(Field var1, int var2, int var3, int var4, int var5) {
      if (var1.getScreen() != this) {
         throw new Object();
      }

      Screen$LocationFocusSelector var6 = Screen$LocationFocusSelector.getSelector(var1, var2, var3, var4, var5);
      this.doFocusMove(true, true, var6);
      return var6.getSuccess();
   }

   public void setGateInput(boolean var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.setScreenState(2, !var1);
   }

   final void setGlobal(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   void setPaintController(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected final void setPositionDelegate(int var1, int var2) {
      this._delegate.setPosition(var1, var2);
   }

   public void setTrackballFilter(int var1) {
      if (var1 != -1 && (var1 & -8) != 0) {
         throw new Object();
      }

      this._trackballFilter = var1;
      if (this.isScreenState(64)) {
         this.applyTrackballOffsets();
      }
   }

   public void setTrackballSensitivityXOffset(int var1) {
      if (var1 >= -100 && (100 >= var1 || var1 == Integer.MAX_VALUE)) {
         this._trackballSensitivityXOffset = var1;
         if (this.isScreenState(64)) {
            this.applyTrackballOffsets();
         }
      } else {
         throw new Object();
      }
   }

   public void setTrackballSensitivityYOffset(int var1) {
      if (var1 >= -100 && (100 >= var1 || var1 == Integer.MAX_VALUE)) {
         this._trackballSensitivityYOffset = var1;
         if (this.isScreenState(64)) {
            this.applyTrackballOffsets();
         }
      } else {
         throw new Object();
      }
   }

   public final void updateDisplay() {
      Graphics.updateDisplay();
   }

   void doPaintabilityWalk(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   void doLayoutNoSynch() {
      this._noSynchOnInvalidate = true;
      this.doLayout();
      this._noSynchOnInvalidate = false;
   }

   void setPushMethod(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   protected int getPushMethod() {
      return this._pushMethod;
   }

   void setDismissing(boolean var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final boolean isDisplayed() {
      return this._uiEngine != null;
   }

   @Override
   protected void onDisplay() {
      this._inputStartReceived = false;
      Field var1 = this.getLeafFieldWithFocus();
      if (var1 == null) {
         if (!this._delegate.isFocusable()) {
            return;
         }

         this.onFocus(1);
         var1 = this.getLeafFieldWithFocus();
      }

      XYRect var2 = Ui.getTmpXYRect();
      var1.getFocusRectPhantom(var2);
      this.ensureRegionVisible(var1, var2.x, var2.y, var2.width, var2.height, false, false);
      Ui.returnTmpXYRect(var2);
   }

   @Override
   protected void onExposed() {
   }

   @Override
   final void callOnExposed() {
      if (this.isScreenState(4)) {
         this.setScreenState(4, false);
         this.onExposed();
         this._delegate.callOnExposed();
      }
   }

   @Override
   protected void onFocus(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public boolean isMuddy() {
      return this._delegate.isMuddy();
   }

   @Override
   public void delete(Field var1) {
      this._delegate.delete(var1);
   }

   @Override
   protected void onMenuDismissed(Menu var1) {
      this.onMenuDismissed();

      for (Field var2 = this.getLeafFieldWithFocus(); var2 != null && var2 != this; var2 = var2.getManager()) {
         var2.onMenuDismissed(var1);
      }
   }

   @Override
   protected void onMenuDismissed() {
   }

   @Override
   protected void onObscured() {
   }

   @Override
   final void callOnObscured() {
      if (!this.isScreenState(4)) {
         this.setScreenState(4, true);
         this.onObscured();
         this._delegate.callOnObscured();
      }
   }

   private void assertLayoutCompleteOnScreen() {
      this.acceptVisitor(new Screen$ScreenFieldVisitor(this, 0));
   }

   private boolean isScreenState(int var1) {
      return (this._screenState & var1) == var1;
   }

   @Override
   protected void onUndisplay() {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void onUnfocus() {
      this._delegate.onUnfocus();
      this._delegate.focusChangeNotify(3);
   }

   private void assertSuperCalled(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void paint(Graphics var1) {
      this.paintChild(var1, this._delegate);
   }

   @Override
   void paintBorder(Graphics var1) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected void paintBackground(Graphics var1) {
      XYRect var2 = Ui.getTmpXYRect();
      var2.set(0, 0, this.getWidth() - this.getBorderLeft() - this.getBorderRight(), this.getHeight() - this.getBorderTop() - this.getBorderBottom());
      Background var3 = ThemeAttributeSet.getBackground(this);
      if (var3 != null) {
         var3.draw(var1, var2);
      } else if (this.getThemeAttributeSet() == null) {
         Background.createSolidBackground(16777215).draw(var1, var2);
      }

      Ui.returnTmpXYRect(var2);
   }

   @Override
   public void replace(Field var1, Field var2) {
      this._delegate.replace(var1, var2);
   }

   private boolean isScrollBehaviourViewInternal() {
      return this._scrollBehaviourView && !this._scrollBehaviourSelect;
   }

   @Override
   public boolean isSelecting() {
      return this._delegate.isSelecting();
   }

   private void beginSuperCalled(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private void ensureRegionVisible(Field var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7) {
      XYRect var8 = Ui.getTmpXYRect();

      while (true) {
         Manager var9 = var1.getManager();
         if (var9 == this || var9 == null) {
            Ui.returnTmpXYRect(var8);
            return;
         }

         var2 += var1.getContentLeft();
         var3 += var1.getContentTop();
         var8.set(var2, var3, var4, var5);
         if (!this.isScrollBehaviourViewInternal()) {
            var9.makeFocusVisible(var6, var8, var7, false);
         }

         int var10 = var9.getHorizontalScroll();
         int var11 = var9.getVerticalScroll();
         int var12 = var9.getWidth();
         int var13 = var9.getHeight();
         if (var2 < var10) {
            var4 -= var10 - var2;
            var2 = var10;
         }

         if (var3 < var11) {
            var5 -= var11 - var3;
            var3 = var11;
         }

         int var14 = var2 + var4 - (var10 + var12);
         if (var14 > 0) {
            var4 -= var14;
         }

         int var15 = var3 + var5 - (var11 + var13);
         if (var15 > 0) {
            var5 -= var15;
         }

         var1 = var9;
      }
   }

   private void doFocusMove(boolean var1, boolean var2, Screen$FocusSelector var3) {
      this.assertHaveEventLock();
      Ui.DRAW_FOCUS_IN_PAINT = false;
      if (var1) {
         this.doRemoveFocus();
      }

      var3.select();
      this.doAddFocus(var2);
      Ui.DRAW_FOCUS_IN_PAINT = true;
      if (var2) {
         UiEngineImpl var4 = this.getUiEngineImpl();
         if (var4 != null) {
            var4.setSomethingInvalid();
         }
      }
   }

   public Screen(Manager var1) {
      this(var1, 0);
   }

   @Override
   public void deleteRange(int var1, int var2) {
      this._delegate.deleteRange(var1, var2);
   }

   public Screen(Manager var1, long var2) {
      super(var2);
      this.setTag(TAG);
      this._delegate = var1;
      this._delegate.setManager(this, 0);
      this.setPaintController(true);
   }

   @Override
   final boolean isVisible0() {
      return this.isScreenState(8);
   }

   private void setFocusChain(Field var1) {
      byte var2 = 0;
      Manager var3 = var1.getManager();
      if (var1 instanceof Manager) {
         var2 = 1;
      }

      if (var1.isFocusable()) {
         var1.onFocus(var2);
         var1.focusChangeNotify(1);
         if (var1 != this._delegate) {
            while (true) {
               var3.onFocus(0);
               var3.setFieldWithFocus(var1);
               var3.focusChangeNotify(1);
               if (var3 == this._delegate) {
                  return;
               }

               var1 = var3;
               var3 = var1.getManager();
            }
         }
      }
   }

   private final void setScreenState(int var1, boolean var2) {
      if (var2) {
         this._screenState |= var1;
      } else {
         this._screenState &= ~var1;
      }
   }

   @Override
   public void add(Field var1) {
      this._delegate.add(var1);
   }

   @Override
   int getVisibleHeight(int var1, int var2) {
      if (0 > var2) {
         var1 += var2;
         var2 = 0;
      }

      if (this.getHeight() < var1 + var2) {
         var1 = this.getHeight() - var2;
      }

      return var1;
   }

   @Override
   public void insert(Field var1, int var2) {
      this._delegate.insert(var1, var2);
   }

   @Override
   protected boolean invokeAction(int var1) {
      if (this.getDelegate().invokeAction(var1)) {
         return true;
      }

      switch (var1) {
         case 1:
            Field var2 = this.getLeafFieldWithFocus();
            if (var2 != null && var2.isSelecting()) {
               this.onMenu(65537);
               return true;
            } else if (this._scrollBehaviourSelect) {
               if (var2 != null) {
                  var2.select(true);
               }

               return true;
            }
         default:
            return super.invokeAction(var1);
      }
   }

   @Override
   protected boolean navigationClick(int var1, int var2) {
      return this._delegate.navigationClick(var1, var2);
   }

   @Override
   protected boolean navigationMovement(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   protected boolean navigationUnclick(int var1, int var2) {
      return this._delegate.navigationUnclick(var1, var2);
   }

   @Override
   int getVisibleWidth(int var1, int var2) {
      if (0 > var2) {
         var1 += var2;
         var2 = 0;
      }

      if (this.getWidth() < var1 + var2) {
         var1 = this.getWidth() - var2;
      }

      return var1;
   }

   @Override
   protected boolean trackwheelClick(int var1, int var2) {
      return this._delegate.trackwheelClick(var1, var2) ? true : super.trackwheelClick(var1, var2);
   }

   @Override
   protected void applyFont() {
      super.applyFont();
      this._delegate.applyFont();
   }

   @Override
   protected boolean trackwheelRoll(int var1, int var2, int var3) {
      if (this._delegate.trackwheelRoll(var1, var2, var3)) {
         return true;
      } else if (this._delegate.getFieldWithFocus() == null) {
         return false;
      } else if (this.isScrollBehaviourViewInternal()) {
         Screen$ViewFocusSelector var5 = Screen$ViewFocusSelector.getSelector(this, var1, var2, var3);
         this.doFocusMove(true, true, var5);
         return var5.getSuccess();
      } else {
         Screen$TrackwheelRollFocusSelector var4 = Screen$TrackwheelRollFocusSelector.getSelector(this, var1, var2, var3);
         this.doFocusMove(true, true, var4);
         return var4.getSuccess();
      }
   }

   @Override
   protected boolean trackwheelUnclick(int var1, int var2) {
      return this._delegate.trackwheelUnclick(var1, var2);
   }

   @Override
   protected void invalidateLayout0() {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this.setValidLayout(false);
      this._delegate.invalidateLayout0();
   }

   private int mapStylusX(int var1) {
      return var1 - this._delegate.getLeft();
   }

   private int mapStylusY(int var1) {
      return var1 - this._delegate.getTop();
   }

   @Override
   protected boolean stylusDown(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusDown(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   protected boolean stylusUp(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusUp(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   protected boolean stylusDrag(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusDrag(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   protected boolean stylusTap(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusTap(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   protected boolean stylusDoubleTap(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusDoubleTap(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   protected boolean stylusTapHold(int var1, int var2, int var3, int var4) {
      return this._delegate.stylusTapHold(this.mapStylusX(var1), this.mapStylusY(var2), var3, var4);
   }

   @Override
   public int getFieldCount() {
      return this._delegate.getFieldCount();
   }

   @Override
   public Field getField(int var1) {
      return this._delegate.getField(var1);
   }

   @Override
   public Field getFieldWithFocus() {
      return this._delegate.getFieldWithFocus();
   }

   @Override
   public int getFieldWithFocusIndex() {
      return this._delegate.getFieldWithFocusIndex();
   }

   @Override
   public Field getLeafFieldWithFocus() {
      return this._delegate.getLeafFieldWithFocus();
   }

   @Override
   void runLayoutUpdate0(int var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void invalidate() {
      this.invalidateInternal();
   }

   @Override
   public int getFieldAtLocation(int var1, int var2) {
      return this._delegate.getFieldAtLocation(var1, var2);
   }

   @Override
   public void invalidate(int var1, int var2, int var3, int var4) {
      this.invalidateInternal(var1, var2, var3, var4);
   }

   @Override
   public void invalidateAll(int var1, int var2, int var3, int var4) {
      this.invalidateCommon(var1, var2, var3, var4, this.getExtent());
   }

   private void invalidateCommon(int var1, int var2, int var3, int var4, XYRect var5) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public void getFocusRect(XYRect var1) {
      this._delegate.getFocusRect(var1);
      XYRect var2 = this._delegate.getContentRect();
      var1.translate(var2.x, var2.y);
   }

   @Override
   public boolean isDataValid() {
      return this._delegate.isDataValid();
   }

   @Override
   protected boolean keyChar(char var1, int var2, int var3) {
      return this._delegate.keyChar(var1, var2, var3);
   }

   @Override
   public void setDirty(boolean var1) {
      this._delegate.setDirty(var1);
   }

   @Override
   public void setFocus() {
      this._delegate.setFocus();
   }

   @Override
   public boolean isDirty() {
      return this._delegate.isDirty();
   }

   @Override
   protected boolean keyControl(char var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: invokevirtual: unknown receiver");
   }

   @Override
   public int adjustVolume(int var1) {
      return MediaPlayerState.areAnyPlayersRegistered() ? -1 : 0;
   }

   @Override
   protected boolean keyDown(int var1, int var2) {
      boolean var3 = false;
      if ((Keypad.status(var1) & 1) == this._backdoorAltStatus) {
         this._backdoorCode = BackdoorKeyProcessor.appendKeyDetectingMultitap(this._backdoorCode, Keypad.key(var1));
         var3 = this.openProductionBackdoor(this._backdoorCode);
         if (!var3 && BackdoorKeyProcessor.isDevelopmentDevice()) {
            var3 = this.openDevelopmentBackdoor(this._backdoorCode);
         }
      } else {
         this._backdoorCode = 0;
      }

      if (!var3) {
         var3 = this._delegate.keyDown(var1, var2);
      }

      if (!var3) {
         var3 = super.keyDown(var1, var2);
      }

      return var3;
   }

   @Override
   public void setHorizontalQuantization(int var1) {
      this._delegate.setHorizontalQuantization(var1);
   }

   @Override
   void setManager(Manager var1, int var2) {
      throw new Object();
   }

   @Override
   protected boolean keyUp(int var1, int var2) {
      return this._delegate.keyUp(var1, var2);
   }

   @Override
   protected boolean keyRepeat(int var1, int var2) {
      return this._delegate.keyRepeat(var1, var2);
   }

   @Override
   protected boolean keyStatus(int var1, int var2) {
      return this._delegate.keyStatus(var1, var2);
   }

   @Override
   Graphics getGraphics0(XYRect var1, boolean var2) {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public void setVerticalQuantization(int var1) {
      this._delegate.setVerticalQuantization(var1);
   }

   @Override
   public int processKeyEvent(int var1, char var2, int var3, int var4) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      if (this.blockInputEvents(var1, var3)) {
         return 0;
      }

      if (Keypad.key(var3) == 0) {
         var3 = 32768;
      }

      int var5 = this._delegate.processKeyEvent(var1, var2, var3, var4);
      boolean var6 = (var5 & 65536) == 65536;
      if (!var6 || var1 == 513) {
         var6 |= this.dispatchKeyEvent(var1, (char)(var5 & 65535), var3, var4);
      }

      if (!var6 && (var1 == 513 || var1 == 514)) {
         char var7 = (char)(var5 & 65535);
         if (Ui.getTrackballClickAction() == 1 && Keypad.key(var3) == 4098) {
            var7 = 149;
         }

         if (var7 == 130 && InternalServices.isReducedFormFactor()) {
            var7 = 132;
         }

         if (var7 != 0) {
            var6 = this.dispatchKeyEvent(32768, var7, var3, var4);
         }
      }

      if (var6) {
         var5 |= 65536;
      }

      return var5;
   }

   @Override
   public int getAccessibleRole() {
      return 1;
   }

   @Override
   public String getAccessibleName() {
      return null;
   }

   @Override
   public AccessibleContext getAccessibleChildAt(int var1) {
      return this.getField(var1);
   }

   @Override
   public int getAccessibleChildCount() {
      return this._delegate.getAccessibleChildCount();
   }

   @Override
   public AccessibleContext getAccessibleSelectionAt(int var1) {
      return this._delegate.getAccessibleSelectionAt(var1);
   }

   @Override
   public int getAccessibleSelectionCount() {
      return 1;
   }

   @Override
   public boolean isAccessibleChildSelected(int var1) {
      return this._delegate.isAccessibleChildSelected(var1);
   }

   @Override
   protected void accessibleEventOccurred(int var1, Object var2, Object var3, AccessibleContext var4) {
      AccessibleEventDispatcher.dispatchAccessibleEvent(var1, var2, var3, var4);
   }

   @Override
   boolean validateFieldStyle(long var1) {
      return super.validateFieldStyle(var1 & -68719476737L);
   }

   @Override
   public boolean processNavigationEvent(int var1, int var2, int var3, int var4, int var5) {
      return this._delegate != null
         ? this._delegate.processNavigationEvent(var1, var2, var3, var4, var5)
         : super.processNavigationEvent(var1, var2, var3, var4, var5);
   }

   @Override
   void doVisibilityWalk(boolean var1) {
      if (this.isScreenState(8) != var1) {
         if (Trackball.isSupported()) {
            this._inputStartReceived = false;
         }

         this.setScreenState(8, var1);
         this.onVisibilityChange(var1);
         this._delegate.doVisibilityWalk(var1);
      }
   }

   @Override
   public boolean isFocusable() {
      return this._delegate.isFocusable();
   }

   @Override
   protected void applyTheme() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   protected void makeMenu(Menu var1, int var2) {
      super.makeMenu(var1, var2);
      if (var2 == 0) {
         if (this.isStyle(131072) && !this._scrollBehaviourSelect) {
            var1.add(MenuItem.getPrefab(14));
         }

         if (this.isScrollBehaviourViewInternal()) {
            var1.add(MenuItem.getPrefab(16));
         }

         if (this._scrollBehaviourSelect) {
            var1.add(MenuItem.getPrefab(17));
            return;
         }
      } else {
         var1.add(MenuItem.getPrefab(18));
      }
   }

   private void applyTrackballOffsets() {
      if (Trackball.isSupported()) {
         boolean var1 = this.isScreenState(64);
         if (var1 && this._trackballSensitivityXOffset != Integer.MAX_VALUE) {
            int var2 = MathUtilities.clamp(0, Trackball.getSensitivityXForSystem() + this._trackballSensitivityXOffset, 100);
            Trackball.setSensitivityXInternal(var2);
         } else {
            Trackball.setSensitivityXInternal(Integer.MAX_VALUE);
         }

         if (var1 && this._trackballSensitivityYOffset != Integer.MAX_VALUE) {
            int var3 = MathUtilities.clamp(0, Trackball.getSensitivityYForSystem() + this._trackballSensitivityYOffset, 100);
            Trackball.setSensitivityYInternal(var3);
         } else {
            Trackball.setSensitivityYInternal(Integer.MAX_VALUE);
         }

         if (var1) {
            Trackball.setFilterInternal(this._trackballFilter);
         } else {
            Trackball.setFilterInternal(-1);
         }

         Trackball.updateDeviceWithAppSettings();
      }
   }

   @Override
   public void getFocusRectPhantom(XYRect var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._delegate.getFocusRectPhantom(var1);
      XYRect var2 = this._delegate.getContentRect();
      var1.translate(var2.x, var2.y);
   }
}
