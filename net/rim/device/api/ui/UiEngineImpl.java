package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.ApplicationProcess;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.ControlledAccessException;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.HolsterListener;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.applicationcontrol.ApplicationControl;
import net.rim.device.internal.system.CodeStore;
import net.rim.device.internal.system.MessageListener;
import net.rim.device.internal.system.UnhandledGlobalKeyListener;
import net.rim.device.internal.ui.BackingStore;
import net.rim.device.internal.ui.MIDletApplication;
import net.rim.device.internal.ui.UiInternalListener;
import net.rim.vm.Message;
import net.rim.vm.Monitor;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

final class UiEngineImpl implements GlobalEventListener, HolsterListener, MessageListener, SystemListener2, UiEngine {
   private UiEngineImpl$ScreenList _screenList = new UiEngineImpl$ScreenList(this);
   private Screen _inputScreen;
   private XYRect _appInvalid = new XYRect();
   private boolean _somethingInvalid;
   private XYRect _fullScreenRect = new XYRect(0, 0, Display.getWidth(), Display.getHeight());
   private UiEngineImpl$BottomScreen _bottomScreen;
   private int _suspendPainting;
   private Application _app;
   private boolean _isMidlet;
   private boolean _isInPopScreen;
   private Object[] _uiEngineListener;
   private Object[] _userInputEventListener;
   private GlobalRepaintNotifier _globalRepaintNotifier = new GlobalRepaintNotifier();
   Graphics _fbGraphics = new Graphics();
   private int _stylusX = -1;
   private int _stylusY = -1;
   static final boolean DEBUG_PAINT;
   private static final int NO_PAINT_WATERMARK;
   static int _layoutGeneration;
   private static UiEngineImpl _uiEngine;

   public final void addUiEngineListener(UiEngineListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._uiEngineListener = ListenerUtilities.addListener(this._uiEngineListener, listener);
   }

   final Screen[] getLocalWrappedScreens() {
      return this._screenList.wrapLocalScreens();
   }

   final boolean allowImmediate(Screen screen) {
      return !this._appInvalid.isEmpty() ? false : this._screenList.isTopmost(screen);
   }

   public final void doPainting() {
      if (this._somethingInvalid) {
         if (this._suspendPainting == 0) {
            if (!this._app.isForeground() && !this.equals(GlobalScreenManager.getPaintControlEngine())) {
               this.paintInProcessGlobalScreens();
               this._somethingInvalid = false;
            } else if (GlobalScreenManager.getPaintControlEngine() != null && !this.equals(GlobalScreenManager.getPaintControlEngine())) {
               this.checkForExtentChanges();
               this.paintWrappedLocalScreens();
               this._somethingInvalid = false;
            } else {
               this.checkForExtentChanges();
               boolean hasAppInvalid = !this._appInvalid.isEmpty();
               XYRect screenInvalid = Ui.getTmpXYRect();
               this.gatherInvalidRegions(screenInvalid, false, 0);
               XYRect totalInvalid = Ui.getTmpXYRect();
               totalInvalid.set(this._appInvalid);
               totalInvalid.unionNoEmpty(screenInvalid);
               if (totalInvalid.isEmpty()) {
                  this._somethingInvalid = false;
                  Ui.returnTmpXYRect(screenInvalid);
                  Ui.returnTmpXYRect(totalInvalid);
               } else {
                  int highest = this._screenList.highestOpaqueRegionContaining(totalInvalid);
                  Ui.returnTmpXYRect(screenInvalid);
                  Ui.returnTmpXYRect(totalInvalid);
                  this.gatherInvalidRegions(this._appInvalid, true, highest);
                  XYRect paintedRect = Ui.getTmpXYRect();
                  XYRect invalid = Ui.getTmpXYRect();
                  XYRect tmp = Ui.getTmpXYRect();
                  Screen screen = null;
                  XYRect appInvalid = Ui.getTmpXYRect();
                  appInvalid.set(this._appInvalid);
                  this._appInvalid.set(0, 0, 0, 0);
                  this._somethingInvalid = false;
                  int screenCount = this._screenList.getScreenCount();

                  for (int i = highest; i < screenCount; i++) {
                     if (i == -1) {
                        screen = this._bottomScreen;
                     } else {
                        screen = this._screenList.getScreen(i);
                     }

                     XYRect extent = this.getScreenExtent(screen);
                     invalid.set(this.getScreenInvalid(screen));
                     tmp.set(appInvalid);
                     tmp.intersect(extent);
                     invalid.unionNoEmpty(tmp);
                     if (this.isScreenTransparent(screen) || this.isScreenTransparentBorder(screen)) {
                        tmp.set(paintedRect);
                        tmp.intersect(extent);
                        invalid.unionNoEmpty(tmp);
                     }

                     if (hasAppInvalid && i == screenCount - 1) {
                        Graphics.resetOverlays();
                     }

                     invalid.intersect(this._fullScreenRect);
                     if (!invalid.isEmpty() && this.isScreenDisplayed(screen)) {
                        screen.doPaintInternal(invalid);
                        paintedRect.unionNoEmpty(invalid);
                        paintedRect.intersect(this._fullScreenRect);
                     }
                  }

                  if (this._somethingInvalid && !invalid.isEmpty() && this._app.getMessageQueueSize() == 0) {
                     this._app.invokeLater(Ui.nullRunnable);
                  }

                  Ui.returnTmpXYRect(appInvalid);
                  Ui.returnTmpXYRect(paintedRect);
                  Ui.returnTmpXYRect(invalid);
                  Ui.returnTmpXYRect(tmp);
                  this.updateDisplay();
               }
            }
         }
      }
   }

   final void invalidateTransparentScreens(Screen modified) {
      int index = this._screenList.getIndex(modified);
      if (index >= 0) {
         int numScreens = this._screenList.getScreenCount();
         XYRect rect = this.getScreenExtent(modified);

         for (int lv = index + 1; lv < numScreens; lv++) {
            Screen screen = this._screenList.getScreenInNonEventThread(lv);
            if (screen == null) {
               return;
            }

            if (this.isInProcess(screen) && this.isScreenTransparent(screen)) {
               screen.invalidateAll(rect.x - screen.getLeft(), rect.y - screen.getTop(), rect.width, rect.height);
            }
         }
      }
   }

   public final Application getApplication() {
      assertIpcOrDependency();
      return this._app;
   }

   final void removeLocalWrappedScreens() {
      throw new RuntimeException("cod2jar: type check");
   }

   final void injectLocalWrappedScreens(Screen[] screens) {
      if (this.isMessageValid(screens)) {
         this.removeLocalWrappedScreens();
      }

      for (int i = 0; i < screens.length; i++) {
         UiEngineImpl$ProxyScreen next = (UiEngineImpl$ProxyScreen)screens[i];
         if (next.getWrappedScreen().getUiEngineImpl() != null) {
            if (!next.getWrappedScreen().getUiEngineImpl().getApplication().isForeground()) {
               return;
            }

            next.setUiEngine(this);
            next.doLayoutNoSynch();
            this._screenList.push(next);
            this._screenList.updateExtent(next);
            Screen wrapped = next.getWrappedScreen();
            wrapped.invalidateInternal();
            wrapped.cleanBackingStore();
         }
      }

      this.notifyPaintableScreens(true);
      this.notifyVisibleScreens(true);
   }

   final boolean isValid() {
      return this._appInvalid.width == 0 || this._appInvalid.height == 0;
   }

   final XYRect[] getOpaqueRegionsArray() {
      return this._screenList.getOpaqueRegionsArray();
   }

   final boolean isEventLockRequired() {
      return this._app.isHandlingEvents() && !Monitor.monitorOwned(this._app.getAppEventLock());
   }

   final void assertHaveEventLock() {
      if (this._app.isHandlingEvents() && !Monitor.monitorOwned(this._app.getAppEventLock())) {
         throw new IllegalStateException("UI engine accessed without holding the event lock.");
      }
   }

   final void notifyUserInputEventListener(int device) {
      Object[] listeners = this._userInputEventListener;
      if (listeners != null) {
         for (int i = listeners.length - 1; i >= 0; i--) {
            ((UserInputEventListener)listeners[i]).onUserInput(device, 0);
         }
      }
   }

   final void forceRepaintIfNotOnEventThread() {
      if (!this._app.isEventThread() && this._app.getMessageQueueSize() == 0) {
         this._app.invokeLater(Ui.nullRunnable);
      }
   }

   final void statusDismissedEvent(Screen screen) {
      GlobalScreenManager.assertHaveLock();
      if (screen.getPushMethod() == 0) {
         throw new IllegalStateException("Cannot mix pushGlobalScreen-popScreen with queueStatus-dismissStatus.");
      }

      this._app.invokeLater(new UiEngineImpl$StatusDismissedHandler(this, screen));
      RIMGlobalMessagePoster.postGlobalEvent(5961289116197897667L, 2, 0, screen.getExtent(), null);
   }

   final void statusDisplayedEvent(Screen screen, boolean inputRequired, boolean redisplay, boolean isTopmost, XYRect revokedInvalid) {
      GlobalScreenManager.assertHaveLock();
      screen.setPushMethod(1);
      this._app.invokeLater(new UiEngineImpl$StatusDisplayedHandler(this, screen, inputRequired, redisplay, revokedInvalid));
   }

   final void setSomethingInvalid() {
      this._somethingInvalid = true;
   }

   public final void removeUiEngineListener(UiEngineListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._uiEngineListener = ListenerUtilities.removeListener(this._uiEngineListener, listener);
   }

   final Screen getActiveLocalGlobalScreen() {
      return this._screenList.getTopmostScreen();
   }

   final void appInvalidate(Screen screen) {
      XYRect extent = this.getScreenExtent(screen);
      this.appInvalidate(extent.x, extent.y, extent.width, extent.height);
   }

   final Screen getGlobalScreen() {
      return this._screenList.getTopmostGlobalScreen();
   }

   final Screen getInputScreen() {
      return this._inputScreen;
   }

   final Screen getScreen(int index) {
      return this._screenList.getLocalScreen(index);
   }

   final Screen getScreenAbove(Screen screen) {
      assertIpcOrDependency();
      return this._screenList.getLocalScreenAbove(screen);
   }

   final Screen getScreenBelow(Screen screen) {
      assertIpcOrDependency();
      return this._screenList.getLocalScreenBelow(screen);
   }

   final boolean isOutOfProcessGlobalScreen(Screen screen) {
      return screen != null && screen.isGlobal() && !this.isInProcess(screen);
   }

   final boolean hasNonNullBackingStore(Screen screen) {
      return this.isOutOfProcessGlobalScreen(screen) && ((UiEngineImpl$ProxyScreen)screen).getWrappedScreen().getBackingStore() != null;
   }

   final void appInvalidate(int x, int y, int width, int height) {
      synchronized (this._appInvalid) {
         this._appInvalid.unionNoEmpty(x, y, width, height);
         this._somethingInvalid = true;
         this.forceRepaintIfNotOnEventThread();
      }
   }

   final boolean shouldRemoveLocalWrappedScreens() {
      Screen[] hidden = this._screenList.getHiddenGlobalScreens();
      int count = 0;

      for (int i = 0; i < hidden.length; i++) {
         Screen next = hidden[i];
         if (this.isInProcess(next) && next.acceptsInput() || next.getUiEngine() == null) {
            count++;
         }
      }

      return this.getLocalInProcessGlobalScreenCount() + count - 1 == this.getScreenCount();
   }

   final boolean shouldAddLocalWrappedScreens() {
      Screen[] hidden = this._screenList.getHiddenGlobalScreens();

      for (int i = 0; i < hidden.length; i++) {
         Screen next = hidden[i];
         if (this.isInProcess(next) && next.acceptsInput()) {
            return true;
         }
      }

      return false;
   }

   final int getLocalGlobalScreenCount() {
      return this._screenList.getScreenCount();
   }

   public final int getUiApplicationStyle() {
      throw new RuntimeException("cod2jar: type check");
   }

   final int getScreenIndex(Screen screen) {
      return this._screenList.getLocalIndex(screen);
   }

   final int getLocalGlobalScreenIndex(Screen screen) {
      return this._screenList.getIndex(screen);
   }

   @Override
   public final void queueStatus(Screen screen, int priority, boolean inputRequired) {
      if (screen.isPaintController()) {
         GlobalScreenManager.injectLocalWrappedScreens(this);
      }

      GlobalScreenManager.queue(screen, priority, inputRequired, Process.currentProcess().getProcessId(), this);
      UiInternalListener listener = GlobalScreenManager.getUiInternalListener();
      if (listener != null) {
         listener.onPushGlobalScreen(this, screen, priority, 1073741826);
      }
   }

   @Override
   public final void pushGlobalScreen(Screen screen, int priority, boolean inputRequired) {
      GlobalScreenManager.push(screen, priority, inputRequired, Process.currentProcess().getProcessId(), this);
      UiInternalListener listener = GlobalScreenManager.getUiInternalListener();
      if (listener != null) {
         listener.onPushGlobalScreen(this, screen, priority, 1073741824);
      }
   }

   @Override
   public final void pushGlobalScreen(Screen screen, int priority, int flags) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void dismissStatus(Screen screen) {
      screen.setDismissing(true);
      GlobalScreenManager.dismiss(screen, this, true, Process.currentProcess().getProcessId());
      screen.setDismissing(false);
   }

   @Override
   public final void processMessage(Object eventLock, Message message, boolean consumed) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final int getScreenCount() {
      return this._screenList.getLocalScreenCount();
   }

   @Override
   public final int getTopmostGlobalPriority() {
      for (int i = 0; i < this._screenList.getScreenCount(); i++) {
         Screen next = this._screenList.getScreen(i);
         if (!next.isGlobal()) {
            break;
         }

         if (this.isInProcess(next)) {
            return GlobalScreenManager.getGlobalPriority(next);
         }
      }

      for (int i = 0; i < this._screenList.getHiddenGlobalScreens().length; i++) {
         Screen next = this._screenList.getHiddenGlobalScreens()[i];
         if (this.isInProcess(next)) {
            return GlobalScreenManager.getGlobalPriority(next);
         }
      }

      return 50;
   }

   @Override
   public final void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void inHolster() {
      if (this._app.isForeground()) {
         this.notifyVisibleScreens(false);
      }

      this.notifyVisibleGlobalScreens(false);
   }

   @Override
   public final void outOfHolster() {
      if (this._app.isForeground()) {
         this.notifyVisibleScreens(true);
         if (this._inputScreen != null && !this._inputScreen.equals(GlobalScreenManager.getScreenWithFocus())) {
            UiEngineImpl$FocusNotifier focusNotifier = null;
            synchronized (GlobalScreenManager.getLock()) {
               this._inputScreen = null;
               focusNotifier = this.setInputScreen();
            }

            if (focusNotifier != null) {
               focusNotifier.run();
            }

            this.repaint();
         }
      }

      this.notifyVisibleGlobalScreens(true);
   }

   @Override
   public final boolean isTopmostGlobal() {
      for (int i = 0; i < this._screenList.getScreenCount(); i++) {
         Screen next = this._screenList.getScreen(i);
         if (!next.isGlobal()) {
            break;
         }

         if (this.isInProcess(next)) {
            return true;
         }
      }

      for (int i = 0; i < this._screenList.getHiddenGlobalScreens().length; i++) {
         Screen next = this._screenList.getHiddenGlobalScreens()[i];
         if (this.isInProcess(next)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public final Screen getActiveScreen() {
      assertIpcOrDependency();
      return this._screenList.getTopmostLocalScreen();
   }

   @Override
   public final void removeUserInputEventListener(UserInputEventListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._userInputEventListener = ListenerUtilities.removeListener(this._userInputEventListener, listener);
   }

   @Override
   public final void relayout() {
      int numScreens = this._screenList.getScreenCount();
      _layoutGeneration++;

      for (int i = 0; i < numScreens; i++) {
         Screen screen = this._screenList.getScreen(i);
         if (this.isInProcess(screen)) {
            screen.invalidateLayout0();
            screen.doLayout();
         }
      }

      this.repaint();
   }

   @Override
   public final void repaint() {
      this._appInvalid.set(0, 0, Display.getWidth(), Display.getHeight());
      this._somethingInvalid = true;
      this.doPainting();
   }

   @Override
   public final void popScreen(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void powerOff() {
   }

   @Override
   public final void powerUp() {
   }

   @Override
   public final void batteryLow() {
   }

   @Override
   public final void batteryGood() {
   }

   @Override
   public final void batteryStatusChange(int status) {
   }

   @Override
   public final void powerOffRequested(int reason) {
   }

   @Override
   public final void cradleMismatch(boolean mismatch) {
   }

   @Override
   public final void fastReset() {
      Trackball.updateDeviceWithAppSettings();
   }

   @Override
   public final void backlightStateChange(boolean on) {
      boolean val = on;
      if ((Display.getProperties() & 16384) != 0) {
         if (val && !this._app.isForeground()) {
            val = false;
         }

         this.notifyVisibleScreens(val);
         this.notifyVisibleGlobalScreens(on);
      }
   }

   @Override
   public final void usbConnectionStateChange(int state) {
   }

   @Override
   public final void pushModalScreen(Screen screen) {
      if (!this._app.isEventThread()) {
         throw new RuntimeException("pushModalScreen called by a non-event thread");
      }

      if (!this.equals(GlobalScreenManager.getPaintControlEngine()) && this.getApplication().isForeground()) {
         GlobalScreenManager.setForegroundEngine(this);
      }

      this.pushScreen(screen);
      this.doPainting();
      UiModalEventThread thread = new UiModalEventThread(screen);
      this._app.startModalEventThread(thread);
   }

   @Override
   public final void pushScreen(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   @Override
   public final void updateDisplay() {
      UiInternalListener listener = GlobalScreenManager.getUiInternalListener();
      if (listener != null) {
         listener.onUpdateDisplay(this);
      }

      if (this._screenList.getScreenCount() > 0) {
         Graphics.updateDisplay();
      }
   }

   @Override
   public final int getLayoutGeneration() {
      return _layoutGeneration;
   }

   @Override
   public final void setStylusPos(int x, int y) {
      this._stylusX = x;
      this._stylusY = y;
   }

   @Override
   public final void suspendPainting(boolean suspend) {
      synchronized (this._screenList) {
         if (suspend) {
            this._suspendPainting++;
         } else {
            if (this._suspendPainting == 0) {
               throw new IllegalStateException("suspendPainting: extra suspend");
            }

            this._suspendPainting--;
         }
      }
   }

   @Override
   public final int getStylusX() {
      return this._stylusX;
   }

   @Override
   public final int getStylusY() {
      return this._stylusY;
   }

   @Override
   public final boolean isPaintingSuspended() {
      return this._suspendPainting > 0;
   }

   @Override
   public final int getGlobalPriority(Screen screen) {
      return GlobalScreenManager.getGlobalPriority(screen);
   }

   @Override
   public final void addUserInputEventListener(UserInputEventListener listener) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._userInputEventListener = ListenerUtilities.addListener(this._userInputEventListener, listener);
   }

   static final Screen getTopmostScreen() {
      return _uiEngine.getActiveScreen();
   }

   private final UiEngineImpl$FocusNotifier setInputScreen() {
      UiEngineImpl$FocusNotifier focusNotifier = null;
      GlobalScreenManager.assertHaveLock();
      Screen newInputScreen = null;

      for (int i = this._screenList.getScreenCount() - 1; i >= 0; i--) {
         Screen screen = this._screenList.getScreen(i);
         if (screen.acceptsInput()) {
            newInputScreen = screen;
            break;
         }
      }

      if (newInputScreen != null && !this.isInProcess(newInputScreen)) {
         newInputScreen = null;
      }

      if (!this._app.isForeground()
         && (!DeviceInfo.isInHolster() || !ApplicationManager.getApplicationManager().isInHolsterInputProcess())
         && newInputScreen != null
         && !newInputScreen.isGlobal()) {
         newInputScreen = null;
      }

      if (this._inputScreen != newInputScreen) {
         focusNotifier = new UiEngineImpl$FocusNotifier(this._inputScreen, newInputScreen, this.getApplication());
         UiInternalListener listener = GlobalScreenManager.getUiInternalListener();
         if (listener != null) {
            listener.onFocus(this, this._inputScreen, newInputScreen);
         }

         Object[] listeners = this._uiEngineListener;
         if (listeners != null) {
            for (int i = listeners.length - 1; i >= 0; i--) {
               ((UiEngineListener)listeners[i]).onFocus(this._inputScreen, newInputScreen);
            }
         }

         this._inputScreen = newInputScreen;
         Screen oldScreenWithFocus = GlobalScreenManager.getScreenWithFocus();
         if (newInputScreen != null || oldScreenWithFocus != null && oldScreenWithFocus.getUiEngine() == this) {
            GlobalScreenManager.setScreenWithFocus(newInputScreen);
         }
      }

      return focusNotifier;
   }

   private final int getLocalInProcessGlobalScreenCount() {
      return this._screenList.getLocalScreenCount() + this._screenList.getInProcessGlobalScreenCount();
   }

   private final void notifyNewlyHiddenGlobalScreens() {
      Screen[] hiddenGlobals = this._screenList.getHiddenGlobalScreens();

      for (int i = 0; i < hiddenGlobals.length; i++) {
         hiddenGlobals[i].doPaintabilityWalk(false);
         hiddenGlobals[i].doVisibilityWalk(false);
      }
   }

   private final void notifyVisibleGlobalScreens(boolean visible) {
      if (visible && (DeviceInfo.isInHolster() || !Backlight.isEnabled() && (Display.getProperties() & 16384) != 0)) {
         visible = false;
      }

      int lv = this._screenList.getScreenCount() - 1;
      if (visible) {
         XYRect rect = new XYRect();
         int screenWidth = Display.getWidth();
         int screenHeight = Display.getHeight();

         while (lv >= 0) {
            Screen screen = this._screenList.getScreen(lv);
            if (screen.isGlobal()) {
               screen.doVisibilityWalk(visible);
               this.getScreenExtent(screen, rect);
               if (!this.isScreenTransparent(screen) && rect.contains(0, 0, screenWidth, screenHeight)) {
                  visible = false;
                  lv--;
                  break;
               }
            }

            lv--;
         }
      }

      for (; lv >= 0; lv--) {
         Screen screen = this._screenList.getScreen(lv);
         if (screen.isGlobal()) {
            screen.doVisibilityWalk(visible);
         }
      }
   }

   private final void notifyPaintableScreens(boolean paintable) {
      int lv = this._screenList.getScreenCount() - 1;
      if (paintable) {
         XYRect rect = new XYRect();
         int screenWidth = Display.getWidth();
         int screenHeight = Display.getHeight();

         while (lv >= 0) {
            Screen screen = this._screenList.getScreen(lv);
            screen.doPaintabilityWalk(paintable);
            this.getScreenExtent(screen, rect);
            if (!this.isScreenTransparent(screen) && rect.contains(0, 0, screenWidth, screenHeight)) {
               paintable = false;
               lv--;
               break;
            }

            lv--;
         }
      }

      while (lv >= 0) {
         this._screenList.getScreen(lv).doPaintabilityWalk(paintable);
         lv--;
      }

      this._bottomScreen.doPaintabilityWalk(paintable);
   }

   private final void notifyPaintableGlobalScreens(boolean paintable) {
      int lv = this._screenList.getScreenCount() - 1;
      if (paintable) {
         XYRect rect = new XYRect();
         int screenWidth = Display.getWidth();
         int screenHeight = Display.getHeight();

         while (lv >= 0) {
            Screen screen = this._screenList.getScreen(lv);
            if (screen.isGlobal()) {
               screen.doPaintabilityWalk(paintable);
               this.getScreenExtent(screen, rect);
               if (!this.isScreenTransparent(screen) && rect.contains(0, 0, screenWidth, screenHeight)) {
                  paintable = false;
                  lv--;
                  break;
               }
            }

            lv--;
         }
      }

      for (; lv >= 0; lv--) {
         Screen screen = this._screenList.getScreen(lv);
         if (screen.isGlobal()) {
            screen.doPaintabilityWalk(paintable);
         }
      }
   }

   private final void notifyPaintableWrappedLocalScreens(boolean paintable) {
      int lv = this._screenList.getScreenCount() - 1;
      if (paintable) {
         XYRect rect = new XYRect();
         int screenWidth = Display.getWidth();
         int screenHeight = Display.getHeight();

         while (lv >= 0) {
            Screen screen = this._screenList.getScreen(lv);
            if (!screen.isGlobal() && screen instanceof UiEngineImpl$ProxyScreen) {
               screen.doPaintabilityWalk(paintable);
               this.getScreenExtent(screen, rect);
               if (!this.isScreenTransparent(screen) && rect.contains(0, 0, screenWidth, screenHeight)) {
                  paintable = false;
                  lv--;
                  break;
               }
            }

            lv--;
         }
      }

      for (; lv >= 0; lv--) {
         Screen screen = this._screenList.getScreen(lv);
         if (screen.isGlobal() && screen instanceof UiEngineImpl$ProxyScreen) {
            screen.doPaintabilityWalk(paintable);
         }
      }
   }

   private final void releaseBackingStore(Screen screen) {
      BackingStore backingStore = screen.getBackingStore();
      XYRect extent = screen.getExtent();
      if (backingStore != null && this._fullScreenRect.equals(extent)) {
         if (!screen.isTransparent() && !screen.isTransparentBorder()) {
            GlobalScreenManager.returnBackingStore(backingStore);
         } else {
            GlobalScreenManager.returnTransparentBackingStore(backingStore);
         }

         screen.clearBackingStore();
      }
   }

   private final void keyNotHandled(Message message) {
      int key = message.getData0() >> 16;
      if (Arrays.getIndex(UnhandledGlobalKeyListener.GLOBAL_KEYS, key) != -1) {
         message.setDevice(56);
         message.post();
      } else {
         if (key == 261) {
            Keypad.changeShiftState(message.getEvent());
         }
      }
   }

   private final void notifyNewlyExposedGlobalScreens() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void globalScreenEventCommon(int type, XYRect appInvalid, Integer processId) {
      if (processId != null && processId == Process.currentProcess().getProcessId()) {
         UiEngineImpl$FocusNotifier focusNotifier = null;
         synchronized (GlobalScreenManager.getLock()) {
            focusNotifier = this.setInputScreen();
         }

         if (focusNotifier != null) {
            focusNotifier.run();
         }

         this.globalScreenEventPainting(type, appInvalid);
      } else {
         Screen oldTopmost = this._screenList.getTopmostScreen();
         synchronized (GlobalScreenManager.getLock()) {
            this._screenList.copyGlobalScreens();
         }

         Screen newTopmost = this._screenList.getTopmostScreen();
         UiEngineImpl$FocusNotifier focusNotifier = null;
         synchronized (GlobalScreenManager.getLock()) {
            focusNotifier = this.setInputScreen();
         }

         if (type == 1) {
            if ((this._app.isForeground() || oldTopmost != null && oldTopmost.isGlobal())
               && oldTopmost != null
               && oldTopmost != newTopmost
               && oldTopmost.getUiEngine() != null
               && oldTopmost.isUiEngineAttached()) {
               oldTopmost.callOnObscured();
            }

            if (focusNotifier != null) {
               focusNotifier.setEnableFocusNotificationForIM(true);
            }
         } else if (type == 2) {
            if ((this._app.isForeground() || newTopmost != null && newTopmost.isGlobal()) && newTopmost != null && oldTopmost != newTopmost) {
               newTopmost.callOnExposed();
            }

            if (focusNotifier != null) {
               focusNotifier.setEnableFocusNotificationForIM(true);
            }

            this.notifyNewlyExposedGlobalScreens();
         }

         if (focusNotifier != null) {
            focusNotifier.run();
         }

         this.globalScreenEventPainting(type, appInvalid);
      }
   }

   private final void applyTheme() {
      int numScreens = this._screenList.getScreenCount();

      for (int lv = 0; lv < numScreens; lv++) {
         Screen screen = this._screenList.getScreen(lv);
         if (this.isInProcess(screen)) {
            screen.applyTheme();
         }
      }
   }

   private final void layoutOutOfProcessGlobalScreens() {
      int screenCount = this._screenList.getScreenCount();

      for (int i = this._screenList.getLocalScreenCount(); i < screenCount; i++) {
         Screen globalScreen = this._screenList.getScreen(i);
         if (globalScreen instanceof UiEngineImpl$ProxyScreen) {
            globalScreen.invalidateLayout0();
            globalScreen.doLayout();
         }
      }
   }

   private final void paintWrappedLocalScreens() {
      boolean screenChanged = false;
      boolean sendNotification = false;

      for (int i = this._screenList.getLocalScreenCount() - 1; i >= 0; i--) {
         Screen local = this._screenList.getScreen(i);
         XYRect extent = local.getExtent();
         XYRect cachedExtent = this._screenList.getExtent(i);
         if (!extent.equals(cachedExtent)) {
            screenChanged = true;
            local.invalidateLayout0();
            local.doLayout();
            local.invalidateInternal();
            local.clearBackingStore();
            cachedExtent.set(extent);
         }

         if (!this._appInvalid.isEmpty() && local.getExtent().intersects(this._appInvalid)) {
            screenChanged = true;
            local.invalidateInternal(this._appInvalid.x, this._appInvalid.y, this._appInvalid.width, this._appInvalid.height);
            if (local.getContentRect().contains(this._appInvalid)) {
               this._appInvalid.set(0, 0, 0, 0);
            }
         }

         if (!local.getInvalid().isEmpty()) {
            XYRect tmp = Ui.getTmpXYRect();
            tmp.set(local.getInvalid());
            tmp.intersect(local.getContentRect());
            if (this._screenList.highestOpaqueRegionContaining(tmp) == i || this._screenList.highestOpaqueRegionContaining(tmp) == -1) {
               screenChanged = true;
            }

            Ui.returnTmpXYRect(tmp);
         }

         if (screenChanged) {
            sendNotification = true;
            local.doPaint0(true, true);
            screenChanged = false;
         }
      }

      if (sendNotification && GlobalScreenManager.getPaintControlEngine().getApplication().getMessageQueueSize() < 5) {
         RIMGlobalMessagePoster.postGlobalEvent(
            GlobalScreenManager.getPaintControlEngine().getApplication().getProcessId(), 1286649819098130486L, 3, 0, null, null
         );
      }
   }

   private final boolean isInProcess(Screen screen) {
      return screen instanceof UiEngineImpl$ProxyScreen ? false : screen != null && this != null && this.equals(screen.getUiEngineImpl());
   }

   private final boolean isScreenDisplayed(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   static final UiEngineImpl getUiEngine() {
      assertIpcOrDependency();
      if (_uiEngine == null) {
         Application app = Application.getApplication();
         if (app == null) {
            throw new RuntimeException("No application instance");
         }

         synchronized (app.getAppEventLock()) {
            if (_uiEngine == null) {
               _uiEngine = new UiEngineImpl(app);
            }
         }
      }

      return _uiEngine;
   }

   public static final void assertIpcOrDependency() {
      if (!CodeStore.isPartOfCurrentApp(TraceBack.getCallingModule(3)) && !ApplicationControl.isIPCAllowed(true)) {
         throw new ControlledAccessException("Unauthorized attempt to attach to this application");
      }
   }

   private final void applyFont() {
      int numScreens = this._screenList.getScreenCount();

      for (int lv = 0; lv < numScreens; lv++) {
         Screen screen = this._screenList.getScreen(lv);
         if (this.isInProcess(screen)) {
            screen.applyFont();
         }
      }
   }

   private final XYRect getScreenExtent(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void getScreenExtent(Screen screen, XYRect extent) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final XYRect getScreenInvalid(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean isScreenTransparent(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean isScreenTransparentBorder(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void notifyVisibleScreens(boolean visible) {
      if (visible && (DeviceInfo.isInHolster() || !Backlight.isEnabled() && (Display.getProperties() & 16384) != 0)) {
         visible = false;
      }

      int lv = this._screenList.getScreenCount() - 1;
      if (visible) {
         XYRect rect = new XYRect();
         int screenWidth = Display.getWidth();
         int screenHeight = Display.getHeight();

         while (lv >= 0) {
            Screen screen = this._screenList.getScreen(lv);
            screen.doVisibilityWalk(visible);
            this.getScreenExtent(screen, rect);
            if (!this.isScreenTransparent(screen) && rect.contains(0, 0, screenWidth, screenHeight)) {
               visible = false;
               lv--;
               break;
            }

            lv--;
         }
      }

      while (lv >= 0) {
         this._screenList.getScreen(lv).doVisibilityWalk(visible);
         lv--;
      }
   }

   private final void paintInProcessGlobalScreens() {
      boolean screenChanged = false;
      boolean screenExtentChanged = false;
      int screenCount = this._screenList.getScreenCount();

      for (int i = this._screenList.getLocalScreenCount(); i < screenCount; i++) {
         Screen globalScreen = this._screenList.getScreen(i);
         if (this.isInProcess(globalScreen)) {
            XYRect extent = globalScreen.getExtent();
            XYRect cachedExtent = this._screenList.getExtent(i);
            if (!extent.equals(cachedExtent) || globalScreen.isClearBackingStore()) {
               screenExtentChanged = true;
               globalScreen.invalidateLayout0();
               globalScreen.doLayout();
               globalScreen.invalidate();
               globalScreen.clearBackingStore();
               cachedExtent.set(extent);
               globalScreen.setClearBackingStore(false);
            }

            if (screenExtentChanged || !globalScreen.getInvalid().isEmpty()) {
               screenChanged = true;
               globalScreen.doPaint0(true, true);
            }
         }
      }

      if (screenChanged) {
         this._globalRepaintNotifier.post(screenExtentChanged);
      }
   }

   static final Screen getTopmostLocalGlobalScreen() {
      return _uiEngine.getActiveLocalGlobalScreen();
   }

   private final void globalScreenEventPainting(int type, XYRect appInvalid) {
      if (this._app.isForeground()) {
         this.notifyPaintableScreens(true);
         this.notifyVisibleScreens(true);
         if (type == 1) {
            this.notifyNewlyHiddenGlobalScreens();
         } else if (type == 2) {
            this.notifyNewlyExposedGlobalScreens();
         }

         if (appInvalid != null) {
            this._appInvalid.unionNoEmpty(appInvalid);
            this._somethingInvalid = true;
         }

         this.doPainting();
      }

      if (this.equals(GlobalScreenManager.getPaintControlEngine()) && !this._app.isForeground()) {
         this.notifyPaintableScreens(true);
         if (appInvalid != null) {
            this._appInvalid.unionNoEmpty(appInvalid);
         }

         this._somethingInvalid = true;
         this.doPainting();
      }
   }

   private final boolean isMessageValid(Screen[] screens) {
      if (screens.length == 0) {
         return false;
      }

      UiEngineImpl$ProxyScreen next = (UiEngineImpl$ProxyScreen)screens[0];
      UiEngineImpl engine = next.getWrappedScreen().getUiEngineImpl();
      return engine != null && engine.getApplication().isForeground();
   }

   private final void checkForExtentChanges() {
      int screenCount = this._screenList.getScreenCount();

      for (int i = 0; i < screenCount; i++) {
         Screen screen = this._screenList.getScreen(i);
         XYRect extent = this.getScreenExtent(screen);
         XYRect cached = this._screenList.getExtent(i);
         if (!extent.equals(cached)) {
            this._appInvalid.unionNoEmpty(extent);
            this._appInvalid.unionNoEmpty(cached);
            this._screenList.updateExtent(screen);
         }
      }

      this._appInvalid.intersect(this._fullScreenRect);
   }

   private final void gatherInvalidRegions(XYRect accumulator, boolean transparentOnly, int startIndex) {
      int screenCount = this._screenList.getScreenCount();
      boolean includeTransparentBorder = false;
      XYRect appInvalidIn = Ui.getTmpXYRect();
      appInvalidIn.set(accumulator);

      for (int i = startIndex; i < screenCount; i++) {
         if (i != -1) {
            Screen screen = this._screenList.getScreen(i);
            XYRect invalid = Ui.getTmpXYRect();
            invalid.set(this.getScreenInvalid(screen));
            if (this.isScreenTransparentBorder(screen) && this._screenList.getScreenCount() > 0) {
               for (Screen below = this._screenList.getScreenBelow(screen); below != null; below = this._screenList.getScreenBelow(below)) {
                  XYRect invalidBelow = this.getScreenInvalid(below);
                  if (!invalidBelow.isEmpty()) {
                     invalid.unionNoEmpty(invalidBelow);
                     includeTransparentBorder = true;
                  }
               }

               if (!appInvalidIn.isEmpty()) {
                  invalid.unionNoEmpty(appInvalidIn);
                  includeTransparentBorder = true;
               }
            }

            if (invalid.isEmpty()) {
               Ui.returnTmpXYRect(invalid);
            } else {
               if (!transparentOnly || this.isScreenTransparent(screen) || includeTransparentBorder) {
                  accumulator.unionNoEmpty(invalid);
                  includeTransparentBorder = false;
               }

               Ui.returnTmpXYRect(invalid);
            }
         }
      }

      Ui.returnTmpXYRect(appInvalidIn);
      accumulator.intersect(this._fullScreenRect);
   }

   private UiEngineImpl(Application app) {
      this._app = app;
      if (app instanceof MIDletApplication) {
         this._isMidlet = true;
      }

      app.setMessageListener(this);
      app.addGlobalEventListenerInternal(this);
      app.addHolsterListener(this);
      if ((Display.getProperties() & 16384) != 0) {
         app.addSystemListener(this);
      }

      this._bottomScreen = new UiEngineImpl$BottomScreen();
      this._bottomScreen.setUiEngine(this);
      this._bottomScreen.doLayout();
      if (app.isForeground() && ((ApplicationProcess)Process.getProcess(app.getProcessId())).acceptsForeground()) {
         GlobalScreenManager.setForegroundEngine(this);
      }

      synchronized (GlobalScreenManager.getLock()) {
         this._screenList.copyGlobalScreens();
      }
   }
}
