package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.Backlight;
import net.rim.device.api.system.ControlledAccess;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.HolsterListener;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.system.SystemListener2;
import net.rim.device.api.util.Arrays;
import net.rim.device.api.util.ListenerUtilities;
import net.rim.device.internal.system.MessageListener;
import net.rim.device.internal.system.UnhandledGlobalKeyListener;
import net.rim.device.internal.ui.BackingStore;
import net.rim.device.internal.ui.UiInternalListener;
import net.rim.vm.Message;
import net.rim.vm.Monitor;
import net.rim.vm.Process;
import net.rim.vm.TraceBack;

final class UiEngineImpl implements GlobalEventListener, HolsterListener, MessageListener, SystemListener2, UiEngine {
   private UiEngineImpl$ScreenList _screenList;
   private Screen _inputScreen;
   private XYRect _appInvalid;
   private boolean _somethingInvalid;
   private XYRect _fullScreenRect;
   private UiEngineImpl$BottomScreen _bottomScreen;
   private int _suspendPainting;
   private Application _app;
   private boolean _isMidlet;
   private boolean _isInPopScreen;
   private Object[] _uiEngineListener;
   private Object[] _userInputEventListener;
   private GlobalRepaintNotifier _globalRepaintNotifier;
   Graphics _fbGraphics;
   private int _stylusX;
   private int _stylusY;
   static final boolean DEBUG_PAINT;
   private static final int NO_PAINT_WATERMARK;
   static int _layoutGeneration;
   private static UiEngineImpl _uiEngine;

   public final void addUiEngineListener(UiEngineListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._uiEngineListener = ListenerUtilities.addListener(this._uiEngineListener, var1);
   }

   final Screen[] getLocalWrappedScreens() {
      return this._screenList.wrapLocalScreens();
   }

   final boolean allowImmediate(Screen var1) {
      return !this._appInvalid.isEmpty() ? false : this._screenList.isTopmost(var1);
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
               boolean var1 = !this._appInvalid.isEmpty();
               XYRect var2 = Ui.getTmpXYRect();
               this.gatherInvalidRegions(var2, false, 0);
               XYRect var3 = Ui.getTmpXYRect();
               var3.set(this._appInvalid);
               var3.unionNoEmpty(var2);
               if (var3.isEmpty()) {
                  this._somethingInvalid = false;
                  Ui.returnTmpXYRect(var2);
                  Ui.returnTmpXYRect(var3);
               } else {
                  int var4 = this._screenList.highestOpaqueRegionContaining(var3);
                  Ui.returnTmpXYRect(var2);
                  Ui.returnTmpXYRect(var3);
                  this.gatherInvalidRegions(this._appInvalid, true, var4);
                  XYRect var5 = Ui.getTmpXYRect();
                  XYRect var6 = Ui.getTmpXYRect();
                  XYRect var7 = Ui.getTmpXYRect();
                  Screen var8 = null;
                  XYRect var9 = Ui.getTmpXYRect();
                  var9.set(this._appInvalid);
                  this._appInvalid.set(0, 0, 0, 0);
                  this._somethingInvalid = false;
                  int var10 = this._screenList.getScreenCount();

                  for (int var11 = var4; var11 < var10; var11++) {
                     if (var11 == -1) {
                        var8 = this._bottomScreen;
                     } else {
                        var8 = this._screenList.getScreen(var11);
                     }

                     XYRect var12 = this.getScreenExtent(var8);
                     var6.set(this.getScreenInvalid(var8));
                     var7.set(var9);
                     var7.intersect(var12);
                     var6.unionNoEmpty(var7);
                     if (this.isScreenTransparent(var8) || this.isScreenTransparentBorder(var8)) {
                        var7.set(var5);
                        var7.intersect(var12);
                        var6.unionNoEmpty(var7);
                     }

                     if (var1 && var11 == var10 - 1) {
                        Graphics.resetOverlays();
                     }

                     var6.intersect(this._fullScreenRect);
                     if (!var6.isEmpty() && this.isScreenDisplayed(var8)) {
                        var8.doPaintInternal(var6);
                        var5.unionNoEmpty(var6);
                        var5.intersect(this._fullScreenRect);
                     }
                  }

                  if (this._somethingInvalid && !var6.isEmpty() && this._app.getMessageQueueSize() == 0) {
                     this._app.invokeLater(Ui.nullRunnable);
                  }

                  Ui.returnTmpXYRect(var9);
                  Ui.returnTmpXYRect(var5);
                  Ui.returnTmpXYRect(var6);
                  Ui.returnTmpXYRect(var7);
                  this.updateDisplay();
               }
            }
         }
      }
   }

   final void invalidateTransparentScreens(Screen var1) {
      int var2 = this._screenList.getIndex(var1);
      if (var2 >= 0) {
         int var3 = this._screenList.getScreenCount();
         XYRect var4 = this.getScreenExtent(var1);

         for (int var5 = var2 + 1; var5 < var3; var5++) {
            Screen var6 = this._screenList.getScreenInNonEventThread(var5);
            if (var6 == null) {
               return;
            }

            if (this.isInProcess(var6) && this.isScreenTransparent(var6)) {
               var6.invalidateAll(var4.x - var6.getLeft(), var4.y - var6.getTop(), var4.width, var4.height);
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

   final void injectLocalWrappedScreens(Screen[] var1) {
      if (this.isMessageValid(var1)) {
         this.removeLocalWrappedScreens();
      }

      for (int var2 = 0; var2 < var1.length; var2++) {
         UiEngineImpl$ProxyScreen var3 = (UiEngineImpl$ProxyScreen)var1[var2];
         if (var3.getWrappedScreen().getUiEngineImpl() != null) {
            if (!var3.getWrappedScreen().getUiEngineImpl().getApplication().isForeground()) {
               return;
            }

            var3.setUiEngine(this);
            var3.doLayoutNoSynch();
            this._screenList.push(var3);
            this._screenList.updateExtent(var3);
            Screen var4 = var3.getWrappedScreen();
            var4.invalidateInternal();
            var4.cleanBackingStore();
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
      throw new RuntimeException("cod2jar: ldc");
   }

   final void notifyUserInputEventListener(int var1) {
      Object[] var2 = this._userInputEventListener;
      if (var2 != null) {
         for (int var3 = var2.length - 1; var3 >= 0; var3--) {
            ((UserInputEventListener)var2[var3]).onUserInput(var1, 0);
         }
      }
   }

   final void forceRepaintIfNotOnEventThread() {
      if (!this._app.isEventThread() && this._app.getMessageQueueSize() == 0) {
         this._app.invokeLater(Ui.nullRunnable);
      }
   }

   final void statusDismissedEvent(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   final void statusDisplayedEvent(Screen var1, boolean var2, boolean var3, boolean var4, XYRect var5) {
      GlobalScreenManager.assertHaveLock();
      var1.setPushMethod(1);
      this._app.invokeLater(new UiEngineImpl$StatusDisplayedHandler(this, var1, var2, var3, var5));
   }

   final void setSomethingInvalid() {
      this._somethingInvalid = true;
   }

   public final void removeUiEngineListener(UiEngineListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._uiEngineListener = ListenerUtilities.removeListener(this._uiEngineListener, var1);
   }

   final Screen getActiveLocalGlobalScreen() {
      return this._screenList.getTopmostScreen();
   }

   final void appInvalidate(Screen var1) {
      XYRect var2 = this.getScreenExtent(var1);
      this.appInvalidate(var2.x, var2.y, var2.width, var2.height);
   }

   final Screen getGlobalScreen() {
      return this._screenList.getTopmostGlobalScreen();
   }

   final Screen getInputScreen() {
      return this._inputScreen;
   }

   final Screen getScreen(int var1) {
      return this._screenList.getLocalScreen(var1);
   }

   final Screen getScreenAbove(Screen var1) {
      assertIpcOrDependency();
      return this._screenList.getLocalScreenAbove(var1);
   }

   final Screen getScreenBelow(Screen var1) {
      assertIpcOrDependency();
      return this._screenList.getLocalScreenBelow(var1);
   }

   final boolean isOutOfProcessGlobalScreen(Screen var1) {
      return var1 != null && var1.isGlobal() && !this.isInProcess(var1);
   }

   final boolean hasNonNullBackingStore(Screen var1) {
      return this.isOutOfProcessGlobalScreen(var1) && ((UiEngineImpl$ProxyScreen)var1).getWrappedScreen().getBackingStore() != null;
   }

   final void appInvalidate(int var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: exception table");
   }

   final boolean shouldRemoveLocalWrappedScreens() {
      Screen[] var1 = this._screenList.getHiddenGlobalScreens();
      int var2 = 0;

      for (int var3 = 0; var3 < var1.length; var3++) {
         Screen var4 = var1[var3];
         if (this.isInProcess(var4) && var4.acceptsInput() || var4.getUiEngine() == null) {
            var2++;
         }
      }

      return this.getLocalInProcessGlobalScreenCount() + var2 - 1 == this.getScreenCount();
   }

   final boolean shouldAddLocalWrappedScreens() {
      Screen[] var1 = this._screenList.getHiddenGlobalScreens();

      for (int var2 = 0; var2 < var1.length; var2++) {
         Screen var3 = var1[var2];
         if (this.isInProcess(var3) && var3.acceptsInput()) {
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

   final int getScreenIndex(Screen var1) {
      return this._screenList.getLocalIndex(var1);
   }

   final int getLocalGlobalScreenIndex(Screen var1) {
      return this._screenList.getIndex(var1);
   }

   @Override
   public final void queueStatus(Screen var1, int var2, boolean var3) {
      if (var1.isPaintController()) {
         GlobalScreenManager.injectLocalWrappedScreens(this);
      }

      GlobalScreenManager.queue(var1, var2, var3, Process.currentProcess().getProcessId(), this);
      UiInternalListener var4 = GlobalScreenManager.getUiInternalListener();
      if (var4 != null) {
         var4.onPushGlobalScreen(this, var1, var2, 1073741826);
      }
   }

   @Override
   public final void pushGlobalScreen(Screen var1, int var2, boolean var3) {
      GlobalScreenManager.push(var1, var2, var3, Process.currentProcess().getProcessId(), this);
      UiInternalListener var4 = GlobalScreenManager.getUiInternalListener();
      if (var4 != null) {
         var4.onPushGlobalScreen(this, var1, var2, 1073741824);
      }
   }

   @Override
   public final void pushGlobalScreen(Screen var1, int var2, int var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void dismissStatus(Screen var1) {
      var1.setDismissing(true);
      GlobalScreenManager.dismiss(var1, this, true, Process.currentProcess().getProcessId());
      var1.setDismissing(false);
   }

   @Override
   public final void processMessage(Object var1, Message var2, boolean var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final int getScreenCount() {
      return this._screenList.getLocalScreenCount();
   }

   @Override
   public final int getTopmostGlobalPriority() {
      for (int var1 = 0; var1 < this._screenList.getScreenCount(); var1++) {
         Screen var2 = this._screenList.getScreen(var1);
         if (!var2.isGlobal()) {
            break;
         }

         if (this.isInProcess(var2)) {
            return GlobalScreenManager.getGlobalPriority(var2);
         }
      }

      for (int var3 = 0; var3 < this._screenList.getHiddenGlobalScreens().length; var3++) {
         Screen var4 = this._screenList.getHiddenGlobalScreens()[var3];
         if (this.isInProcess(var4)) {
            return GlobalScreenManager.getGlobalPriority(var4);
         }
      }

      return 50;
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
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
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final boolean isTopmostGlobal() {
      for (int var1 = 0; var1 < this._screenList.getScreenCount(); var1++) {
         Screen var2 = this._screenList.getScreen(var1);
         if (!var2.isGlobal()) {
            break;
         }

         if (this.isInProcess(var2)) {
            return true;
         }
      }

      for (int var3 = 0; var3 < this._screenList.getHiddenGlobalScreens().length; var3++) {
         Screen var4 = this._screenList.getHiddenGlobalScreens()[var3];
         if (this.isInProcess(var4)) {
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
   public final void removeUserInputEventListener(UserInputEventListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._userInputEventListener = ListenerUtilities.removeListener(this._userInputEventListener, var1);
   }

   @Override
   public final void relayout() {
      int var2 = this._screenList.getScreenCount();
      _layoutGeneration++;

      for (int var3 = 0; var3 < var2; var3++) {
         Screen var1 = this._screenList.getScreen(var3);
         if (this.isInProcess(var1)) {
            var1.invalidateLayout0();
            var1.doLayout();
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
   public final void popScreen(Screen var1) {
      throw new RuntimeException("cod2jar: exception table");
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
   public final void batteryStatusChange(int var1) {
   }

   @Override
   public final void powerOffRequested(int var1) {
   }

   @Override
   public final void cradleMismatch(boolean var1) {
   }

   @Override
   public final void fastReset() {
      Trackball.updateDeviceWithAppSettings();
   }

   @Override
   public final void backlightStateChange(boolean var1) {
      boolean var2 = var1;
      if ((Display.getProperties() & 16384) != 0) {
         if (var2 && !this._app.isForeground()) {
            var2 = false;
         }

         this.notifyVisibleScreens(var2);
         this.notifyVisibleGlobalScreens(var1);
      }
   }

   @Override
   public final void usbConnectionStateChange(int var1) {
   }

   @Override
   public final void pushModalScreen(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public final void pushScreen(Screen var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public final void updateDisplay() {
      UiInternalListener var1 = GlobalScreenManager.getUiInternalListener();
      if (var1 != null) {
         var1.onUpdateDisplay(this);
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
   public final void setStylusPos(int var1, int var2) {
      this._stylusX = var1;
      this._stylusY = var2;
   }

   @Override
   public final void suspendPainting(boolean var1) {
      throw new RuntimeException("cod2jar: exception table");
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
   public final int getGlobalPriority(Screen var1) {
      return GlobalScreenManager.getGlobalPriority(var1);
   }

   @Override
   public final void addUserInputEventListener(UserInputEventListener var1) {
      ControlledAccess.assertRRISignature(TraceBack.getCallingModule(0));
      this._userInputEventListener = ListenerUtilities.addListener(this._userInputEventListener, var1);
   }

   static final Screen getTopmostScreen() {
      return _uiEngine.getActiveScreen();
   }

   private final UiEngineImpl$FocusNotifier setInputScreen() {
      UiEngineImpl$FocusNotifier var1 = null;
      GlobalScreenManager.assertHaveLock();
      Screen var2 = null;

      for (int var3 = this._screenList.getScreenCount() - 1; var3 >= 0; var3--) {
         Screen var4 = this._screenList.getScreen(var3);
         if (var4.acceptsInput()) {
            var2 = var4;
            break;
         }
      }

      if (var2 != null && !this.isInProcess(var2)) {
         var2 = null;
      }

      if (!this._app.isForeground()
         && (!DeviceInfo.isInHolster() || !ApplicationManager.getApplicationManager().isInHolsterInputProcess())
         && var2 != null
         && !var2.isGlobal()) {
         var2 = null;
      }

      if (this._inputScreen != var2) {
         var1 = new UiEngineImpl$FocusNotifier(this._inputScreen, var2, this.getApplication());
         UiInternalListener var6 = GlobalScreenManager.getUiInternalListener();
         if (var6 != null) {
            var6.onFocus(this, this._inputScreen, var2);
         }

         Object[] var7 = this._uiEngineListener;
         if (var7 != null) {
            for (int var5 = var7.length - 1; var5 >= 0; var5--) {
               ((UiEngineListener)var7[var5]).onFocus(this._inputScreen, var2);
            }
         }

         this._inputScreen = var2;
         Screen var8 = GlobalScreenManager.getScreenWithFocus();
         if (var2 != null || var8 != null && var8.getUiEngine() == this) {
            GlobalScreenManager.setScreenWithFocus(var2);
         }
      }

      return var1;
   }

   private final int getLocalInProcessGlobalScreenCount() {
      return this._screenList.getLocalScreenCount() + this._screenList.getInProcessGlobalScreenCount();
   }

   private final void notifyNewlyHiddenGlobalScreens() {
      Screen[] var1 = this._screenList.getHiddenGlobalScreens();

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2].doPaintabilityWalk(false);
         var1[var2].doVisibilityWalk(false);
      }
   }

   private final void notifyVisibleGlobalScreens(boolean var1) {
      if (var1 && (DeviceInfo.isInHolster() || !Backlight.isEnabled() && (Display.getProperties() & 16384) != 0)) {
         var1 = false;
      }

      int var2 = this._screenList.getScreenCount() - 1;
      if (var1) {
         XYRect var3 = new XYRect();
         int var4 = Display.getWidth();
         int var5 = Display.getHeight();

         while (var2 >= 0) {
            Screen var6 = this._screenList.getScreen(var2);
            if (var6.isGlobal()) {
               var6.doVisibilityWalk(var1);
               this.getScreenExtent(var6, var3);
               if (!this.isScreenTransparent(var6) && var3.contains(0, 0, var4, var5)) {
                  var1 = false;
                  var2--;
                  break;
               }
            }

            var2--;
         }
      }

      for (; var2 >= 0; var2--) {
         Screen var7 = this._screenList.getScreen(var2);
         if (var7.isGlobal()) {
            var7.doVisibilityWalk(var1);
         }
      }
   }

   private final void notifyPaintableScreens(boolean var1) {
      int var2 = this._screenList.getScreenCount() - 1;
      if (var1) {
         XYRect var3 = new XYRect();
         int var4 = Display.getWidth();
         int var5 = Display.getHeight();

         while (var2 >= 0) {
            Screen var6 = this._screenList.getScreen(var2);
            var6.doPaintabilityWalk(var1);
            this.getScreenExtent(var6, var3);
            if (!this.isScreenTransparent(var6) && var3.contains(0, 0, var4, var5)) {
               var1 = false;
               var2--;
               break;
            }

            var2--;
         }
      }

      while (var2 >= 0) {
         this._screenList.getScreen(var2).doPaintabilityWalk(var1);
         var2--;
      }

      this._bottomScreen.doPaintabilityWalk(var1);
   }

   private final void notifyPaintableGlobalScreens(boolean var1) {
      int var2 = this._screenList.getScreenCount() - 1;
      if (var1) {
         XYRect var3 = new XYRect();
         int var4 = Display.getWidth();
         int var5 = Display.getHeight();

         while (var2 >= 0) {
            Screen var6 = this._screenList.getScreen(var2);
            if (var6.isGlobal()) {
               var6.doPaintabilityWalk(var1);
               this.getScreenExtent(var6, var3);
               if (!this.isScreenTransparent(var6) && var3.contains(0, 0, var4, var5)) {
                  var1 = false;
                  var2--;
                  break;
               }
            }

            var2--;
         }
      }

      for (; var2 >= 0; var2--) {
         Screen var7 = this._screenList.getScreen(var2);
         if (var7.isGlobal()) {
            var7.doPaintabilityWalk(var1);
         }
      }
   }

   private final void notifyPaintableWrappedLocalScreens(boolean var1) {
      int var2 = this._screenList.getScreenCount() - 1;
      if (var1) {
         XYRect var3 = new XYRect();
         int var4 = Display.getWidth();
         int var5 = Display.getHeight();

         while (var2 >= 0) {
            Screen var6 = this._screenList.getScreen(var2);
            if (!var6.isGlobal() && var6 instanceof UiEngineImpl$ProxyScreen) {
               var6.doPaintabilityWalk(var1);
               this.getScreenExtent(var6, var3);
               if (!this.isScreenTransparent(var6) && var3.contains(0, 0, var4, var5)) {
                  var1 = false;
                  var2--;
                  break;
               }
            }

            var2--;
         }
      }

      for (; var2 >= 0; var2--) {
         Screen var7 = this._screenList.getScreen(var2);
         if (var7.isGlobal() && var7 instanceof UiEngineImpl$ProxyScreen) {
            var7.doPaintabilityWalk(var1);
         }
      }
   }

   private final void releaseBackingStore(Screen var1) {
      BackingStore var2 = var1.getBackingStore();
      XYRect var3 = var1.getExtent();
      if (var2 != null && this._fullScreenRect.equals(var3)) {
         if (!var1.isTransparent() && !var1.isTransparentBorder()) {
            GlobalScreenManager.returnBackingStore(var2);
         } else {
            GlobalScreenManager.returnTransparentBackingStore(var2);
         }

         var1.clearBackingStore();
      }
   }

   private final void keyNotHandled(Message var1) {
      int var2 = var1.getData0() >> 16;
      if (Arrays.getIndex(UnhandledGlobalKeyListener.GLOBAL_KEYS, var2) != -1) {
         var1.setDevice(56);
         var1.post();
      } else {
         if (var2 == 261) {
            Keypad.changeShiftState(var1.getEvent());
         }
      }
   }

   private final void notifyNewlyExposedGlobalScreens() {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void globalScreenEventCommon(int var1, XYRect var2, Integer var3) {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void applyTheme() {
      int var1 = this._screenList.getScreenCount();

      for (int var2 = 0; var2 < var1; var2++) {
         Screen var3 = this._screenList.getScreen(var2);
         if (this.isInProcess(var3)) {
            var3.applyTheme();
         }
      }
   }

   private final void layoutOutOfProcessGlobalScreens() {
      int var1 = this._screenList.getScreenCount();

      for (int var2 = this._screenList.getLocalScreenCount(); var2 < var1; var2++) {
         Screen var3 = this._screenList.getScreen(var2);
         if (var3 instanceof UiEngineImpl$ProxyScreen) {
            var3.invalidateLayout0();
            var3.doLayout();
         }
      }
   }

   private final void paintWrappedLocalScreens() {
      boolean var1 = false;
      boolean var2 = false;

      for (int var3 = this._screenList.getLocalScreenCount() - 1; var3 >= 0; var3--) {
         Screen var4 = this._screenList.getScreen(var3);
         XYRect var5 = var4.getExtent();
         XYRect var6 = this._screenList.getExtent(var3);
         if (!var5.equals(var6)) {
            var1 = true;
            var4.invalidateLayout0();
            var4.doLayout();
            var4.invalidateInternal();
            var4.clearBackingStore();
            var6.set(var5);
         }

         if (!this._appInvalid.isEmpty() && var4.getExtent().intersects(this._appInvalid)) {
            var1 = true;
            var4.invalidateInternal(this._appInvalid.x, this._appInvalid.y, this._appInvalid.width, this._appInvalid.height);
            if (var4.getContentRect().contains(this._appInvalid)) {
               this._appInvalid.set(0, 0, 0, 0);
            }
         }

         if (!var4.getInvalid().isEmpty()) {
            XYRect var7 = Ui.getTmpXYRect();
            var7.set(var4.getInvalid());
            var7.intersect(var4.getContentRect());
            if (this._screenList.highestOpaqueRegionContaining(var7) == var3 || this._screenList.highestOpaqueRegionContaining(var7) == -1) {
               var1 = true;
            }

            Ui.returnTmpXYRect(var7);
         }

         if (var1) {
            var2 = true;
            var4.doPaint0(true, true);
            var1 = false;
         }
      }

      if (var2 && GlobalScreenManager.getPaintControlEngine().getApplication().getMessageQueueSize() < 5) {
         RIMGlobalMessagePoster.postGlobalEvent(
            GlobalScreenManager.getPaintControlEngine().getApplication().getProcessId(), 1286649819098130486L, 3, 0, null, null
         );
      }
   }

   private final boolean isInProcess(Screen var1) {
      return var1 instanceof UiEngineImpl$ProxyScreen ? false : var1 != null && this != null && this.equals(var1.getUiEngineImpl());
   }

   private final boolean isScreenDisplayed(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   static final UiEngineImpl getUiEngine() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final void assertIpcOrDependency() {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final void applyFont() {
      int var1 = this._screenList.getScreenCount();

      for (int var2 = 0; var2 < var1; var2++) {
         Screen var3 = this._screenList.getScreen(var2);
         if (this.isInProcess(var3)) {
            var3.applyFont();
         }
      }
   }

   private final XYRect getScreenExtent(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void getScreenExtent(Screen var1, XYRect var2) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final XYRect getScreenInvalid(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean isScreenTransparent(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final boolean isScreenTransparentBorder(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   private final void notifyVisibleScreens(boolean var1) {
      if (var1 && (DeviceInfo.isInHolster() || !Backlight.isEnabled() && (Display.getProperties() & 16384) != 0)) {
         var1 = false;
      }

      int var2 = this._screenList.getScreenCount() - 1;
      if (var1) {
         XYRect var3 = new XYRect();
         int var4 = Display.getWidth();
         int var5 = Display.getHeight();

         while (var2 >= 0) {
            Screen var6 = this._screenList.getScreen(var2);
            var6.doVisibilityWalk(var1);
            this.getScreenExtent(var6, var3);
            if (!this.isScreenTransparent(var6) && var3.contains(0, 0, var4, var5)) {
               var1 = false;
               var2--;
               break;
            }

            var2--;
         }
      }

      while (var2 >= 0) {
         this._screenList.getScreen(var2).doVisibilityWalk(var1);
         var2--;
      }
   }

   private final void paintInProcessGlobalScreens() {
      boolean var1 = false;
      boolean var2 = false;
      int var3 = this._screenList.getScreenCount();

      for (int var4 = this._screenList.getLocalScreenCount(); var4 < var3; var4++) {
         Screen var5 = this._screenList.getScreen(var4);
         if (this.isInProcess(var5)) {
            XYRect var6 = var5.getExtent();
            XYRect var7 = this._screenList.getExtent(var4);
            if (!var6.equals(var7) || var5.isClearBackingStore()) {
               var2 = true;
               var5.invalidateLayout0();
               var5.doLayout();
               var5.invalidate();
               var5.clearBackingStore();
               var7.set(var6);
               var5.setClearBackingStore(false);
            }

            if (var2 || !var5.getInvalid().isEmpty()) {
               var1 = true;
               var5.doPaint0(true, true);
            }
         }
      }

      if (var1) {
         this._globalRepaintNotifier.post(var2);
      }
   }

   static final Screen getTopmostLocalGlobalScreen() {
      return _uiEngine.getActiveLocalGlobalScreen();
   }

   private final void globalScreenEventPainting(int var1, XYRect var2) {
      if (this._app.isForeground()) {
         this.notifyPaintableScreens(true);
         this.notifyVisibleScreens(true);
         if (var1 == 1) {
            this.notifyNewlyHiddenGlobalScreens();
         } else if (var1 == 2) {
            this.notifyNewlyExposedGlobalScreens();
         }

         if (var2 != null) {
            this._appInvalid.unionNoEmpty(var2);
            this._somethingInvalid = true;
         }

         this.doPainting();
      }

      if (this.equals(GlobalScreenManager.getPaintControlEngine()) && !this._app.isForeground()) {
         this.notifyPaintableScreens(true);
         if (var2 != null) {
            this._appInvalid.unionNoEmpty(var2);
         }

         this._somethingInvalid = true;
         this.doPainting();
      }
   }

   private final boolean isMessageValid(Screen[] var1) {
      if (var1.length == 0) {
         return false;
      }

      UiEngineImpl$ProxyScreen var2 = (UiEngineImpl$ProxyScreen)var1[0];
      UiEngineImpl var3 = var2.getWrappedScreen().getUiEngineImpl();
      return var3 != null && var3.getApplication().isForeground();
   }

   private final void checkForExtentChanges() {
      int var1 = this._screenList.getScreenCount();

      for (int var2 = 0; var2 < var1; var2++) {
         Screen var3 = this._screenList.getScreen(var2);
         XYRect var4 = this.getScreenExtent(var3);
         XYRect var5 = this._screenList.getExtent(var2);
         if (!var4.equals(var5)) {
            this._appInvalid.unionNoEmpty(var4);
            this._appInvalid.unionNoEmpty(var5);
            this._screenList.updateExtent(var3);
         }
      }

      this._appInvalid.intersect(this._fullScreenRect);
   }

   private final void gatherInvalidRegions(XYRect var1, boolean var2, int var3) {
      int var4 = this._screenList.getScreenCount();
      boolean var5 = false;
      XYRect var6 = Ui.getTmpXYRect();
      var6.set(var1);

      for (int var7 = var3; var7 < var4; var7++) {
         if (var7 != -1) {
            Screen var8 = this._screenList.getScreen(var7);
            XYRect var9 = Ui.getTmpXYRect();
            var9.set(this.getScreenInvalid(var8));
            if (this.isScreenTransparentBorder(var8) && this._screenList.getScreenCount() > 0) {
               for (Screen var10 = this._screenList.getScreenBelow(var8); var10 != null; var10 = this._screenList.getScreenBelow(var10)) {
                  XYRect var11 = this.getScreenInvalid(var10);
                  if (!var11.isEmpty()) {
                     var9.unionNoEmpty(var11);
                     var5 = true;
                  }
               }

               if (!var6.isEmpty()) {
                  var9.unionNoEmpty(var6);
                  var5 = true;
               }
            }

            if (var9.isEmpty()) {
               Ui.returnTmpXYRect(var9);
            } else {
               if (!var2 || this.isScreenTransparent(var8) || var5) {
                  var1.unionNoEmpty(var9);
                  var5 = false;
               }

               Ui.returnTmpXYRect(var9);
            }
         }
      }

      Ui.returnTmpXYRect(var6);
      var1.intersect(this._fullScreenRect);
   }

   private UiEngineImpl(Application var1) {
   }
}
