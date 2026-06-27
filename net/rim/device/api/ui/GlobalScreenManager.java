package net.rim.device.api.ui;

import java.util.Stack;
import java.util.Vector;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.ApplicationManager;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.GlobalEventListener;
import net.rim.device.api.system.RIMGlobalMessagePoster;
import net.rim.device.api.ui.accessibility.AccessibleEventListener;
import net.rim.device.internal.proxy.Proxy;
import net.rim.device.internal.system.ApplicationManagerInternal;
import net.rim.device.internal.system.InternalServices;
import net.rim.device.internal.ui.BackingStore;
import net.rim.device.internal.ui.UiInternalListener;
import net.rim.vm.Array;
import net.rim.vm.WeakReference;

final class GlobalScreenManager implements GlobalEventListener {
   private Vector _statusQueue;
   private ApplicationManagerInternal _appManager;
   private UiInternalListener _uiInternalListener;
   private AccessibleEventListener _accessibleEventListener;
   private WeakReference _focusScreen;
   private UiEngineImpl _paintControlEngine;
   private UiEngineImpl _foregroundEngine;
   private Stack _backingStoreStack = (Stack)(new Object());
   private Stack _transparentBackingStoreStack = (Stack)(new Object());
   public static final long GUID_GLOBAL_SCREEN;
   static final long GUID_LOCAL_WRAPPED_SCREEN;
   static final long GUID_LOCAL_WRAPPED_SCREENS_SET;
   public static final int EVENT_DISPLAYED;
   public static final int EVENT_DISMISSED;
   public static final int EVENT_REPAINT;
   private static final long REGISTRY_NAME;
   private static GlobalScreenManager _statusManager;
   private static final int PAINT_CONTROL_FOREGROUND;
   private static final int PAINT_CONTROL_QUEUE;
   private static final int PAINT_CONTROL_DISMISS;

   private GlobalScreenManager() {
      this._statusQueue = (Vector)(new Object());
      this._appManager = (ApplicationManagerInternal)ApplicationManager.getApplicationManager();
      Proxy.getInstance().addGlobalEventListener(this);
   }

   static final void assertHaveLock() {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void copyGlobalScreens(Screen[] var0, int var1, Screen[] var2) {
      assertHaveLock();
      int var3 = _statusManager._statusQueue.size();
      int var4 = _statusManager.getVisibleScreenCountImpl();
      Array.resize(var0, var1 + var4);

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var4 - 1 - var5;
         GlobalScreenManager$StatusData var7 = (GlobalScreenManager$StatusData)_statusManager._statusQueue.elementAt(var6);
         var0[var5 + var1] = var7.screen;
      }

      Array.resize(var2, var3 - var4);

      for (int var8 = var4; var8 < var3; var8++) {
         int var9 = var3 - 1 - (var8 - var4);
         GlobalScreenManager$StatusData var10 = (GlobalScreenManager$StatusData)_statusManager._statusQueue.elementAt(var9);
         var2[var8 - var4] = var10.screen;
      }
   }

   static final boolean dismiss(Screen var0, UiEngineImpl var1, boolean var2, int var3) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized boolean dismissInternal(Screen var1, UiEngineImpl var2, boolean var3, int var4) {
      if (!var1.isGlobal()) {
         return false;
      }

      var1.setUiEngine(null);
      var1.setGlobal(false);
      GlobalScreenManager$StatusData var5 = null;
      int var6 = this._statusQueue.size();

      int var7;
      for (var7 = 0; var7 < var6; var7++) {
         var5 = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var7);
         if (var5.screen == var1) {
            break;
         }
      }

      if (var7 == var6 || var5 == null) {
         return false;
      }

      if (var5.engine != null && !var5.engine.equals(var2)) {
         return false;
      }

      XYRect var8 = Ui.getTmpXYRect();
      var8.set(var1.getExtent());
      this._statusQueue.removeElementAt(var7);

      for (int var10 = 0; var10 < _statusManager._statusQueue.size(); var10++) {
         GlobalScreenManager$StatusData var9 = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var10);
         var8.unionNoEmpty(var9.screen.getExtent());
         if (var9.suppress) {
            break;
         }
      }

      if (var3) {
         var5.engine.statusDismissedEvent(var5.screen);
      } else {
         RIMGlobalMessagePoster.postGlobalEvent(5961289116197897667L, 2, 0, var8, new Object(var4));
      }

      this.updatePaintControl(2, false, var2);
      this.redirectInput();
      return true;
   }

   public static final int getGlobalPriority(Screen var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Screen getCurrentGlobalScreen() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Screen getScreenWithFocus() {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final UiInternalListener getUiInternalListener() {
      return _statusManager._uiInternalListener;
   }

   private final int getVisibleScreenCountImpl() {
      int var1 = 0;

      for (int var2 = 0; var2 < this._statusQueue.size(); var2++) {
         var1++;
         GlobalScreenManager$StatusData var3 = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var2);
         if (var3.suppress) {
            return var1;
         }
      }

      return var1;
   }

   public static final int getVisibleScreenCount() {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final boolean isGlobalScreenWithInputDisplayed() {
      throw new RuntimeException("cod2jar: exception table");
   }

   private final void redirectInput() {
      for (int var1 = 0; var1 < this._statusQueue.size(); var1++) {
         GlobalScreenManager$StatusData var2 = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var1);
         if (var2.inputRequired) {
            this._appManager.redirectInput(var2.process, false);
            return;
         }

         if (var2.suppress) {
            break;
         }
      }

      this._appManager.redirectInput(null, false);
   }

   @Override
   public final void eventOccurred(long var1, int var3, int var4, Object var5, Object var6) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void getExtent(XYRect var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public static final Object getLock() {
      return _statusManager;
   }

   static final void queue(Screen var0, int var1, boolean var2, int var3, UiEngineImpl var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void push(Screen var0, int var1, boolean var2, int var3, UiEngineImpl var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void push(Screen var0, int var1, int var2, int var3, UiEngineImpl var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized void queueInternal(Screen var1, int var2, boolean var3, boolean var4, int var5, UiEngineImpl var6, boolean var7, boolean var8) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void setScreenWithFocus(Screen var0) {
      throw new RuntimeException("cod2jar: exception table");
   }

   static final void setUiInternalListener(UiInternalListener var0) {
      _statusManager._uiInternalListener = var0;
   }

   static final void setAccessibleEventListener(AccessibleEventListener var0) {
      _statusManager._accessibleEventListener = var0;
   }

   static final BackingStore getBackingStore() {
      return (BackingStore)(_statusManager._backingStoreStack.isEmpty()
         ? new Object(Display.getWidth(), Display.getHeight(), false)
         : _statusManager._backingStoreStack.pop());
   }

   static final BackingStore getTransparentBackingStore() {
      return (BackingStore)(_statusManager._transparentBackingStoreStack.isEmpty()
         ? new Object(Display.getWidth(), Display.getHeight(), true)
         : _statusManager._transparentBackingStoreStack.pop());
   }

   static final void returnBackingStore(BackingStore var0) {
      _statusManager._backingStoreStack.push(var0);
   }

   static final void returnTransparentBackingStore(BackingStore var0) {
      _statusManager._transparentBackingStoreStack.push(var0);
   }

   static final void removeAccessibleEventListener(AccessibleEventListener var0) {
      if (var0 != null && _statusManager != null && _statusManager._accessibleEventListener != null && _statusManager._accessibleEventListener.equals(var0)) {
         _statusManager._accessibleEventListener = null;
      }
   }

   static final AccessibleEventListener getAccessibleEventListener() {
      return _statusManager._accessibleEventListener;
   }

   static final void setForegroundEngine(UiEngineImpl var0) {
      _statusManager._foregroundEngine = var0;
      _statusManager.updatePaintControl(0, true, null);
   }

   static final UiEngineImpl getPaintControlEngine() {
      return _statusManager._paintControlEngine;
   }

   private final void updatePaintControl(int var1, boolean var2, UiEngineImpl var3) {
      UiEngineImpl var4 = this._foregroundEngine;
      UiEngineImpl var5 = var4;
      this._paintControlEngine = var4;
      boolean var6 = false;
      int var7 = this._statusQueue.size();

      for (int var8 = 0; var8 < var7; var8++) {
         GlobalScreenManager$StatusData var9 = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var8);
         Screen var10 = var9.screen;
         if (var10.isPaintController()) {
            var5 = var10.getUiEngineImpl();
            this._paintControlEngine = var5;
            var6 = true;
            break;
         }

         if (var9.suppress) {
            break;
         }
      }

      if (var4 != null && var5 != null) {
         Application var11 = var5.getApplication();
         if (!var5.equals(var4) && var11 != null) {
            if (var1 == 0) {
               RIMGlobalMessagePoster.postGlobalEvent(var11.getProcessId(), 3160755958169834551L, 0, 0, var4.getLocalWrappedScreens(), null);
            } else if (var1 == 1 && !var2 && var3 != null && var3.equals(var5)) {
               var3.injectLocalWrappedScreens(var4.getLocalWrappedScreens());
            } else if (var1 == 2 && var3 != null) {
               if (var3.equals(var5) && var3.shouldAddLocalWrappedScreens()) {
                  var3.injectLocalWrappedScreens(var4.getLocalWrappedScreens());
               } else if (!var3.equals(var5)) {
                  RIMGlobalMessagePoster.postGlobalEvent(var11.getProcessId(), 3160755958169834551L, 0, 0, var4.getLocalWrappedScreens(), null);
               }
            }
         } else if (var1 == 0 && var6) {
            var5.removeLocalWrappedScreens();
         } else if (var1 == 1 && !var6) {
            var3.removeLocalWrappedScreens();
         }

         if (var1 == 2 && var3 != null && var3.shouldRemoveLocalWrappedScreens()) {
            var3.removeLocalWrappedScreens();
         }
      }

      if (var5 != null) {
         Application var12 = var5.getApplication();
         if (var12 != null) {
            if (_statusManager._statusQueue.isEmpty() && ApplicationManager.getApplicationManager().getForegroundProcessId() != var12.getProcessId()) {
               return;
            }

            InternalServices.setVisibleProcess(var12.getProcessId());
         }
      }
   }

   static final void injectLocalWrappedScreens(UiEngineImpl var0) {
      if (var0 != null && !var0.getApplication().isForeground() && _statusManager._foregroundEngine != null) {
         RIMGlobalMessagePoster.postGlobalEvent(
            var0.getApplication().getProcessId(), 3160755958169834551L, 0, 0, _statusManager._foregroundEngine.getLocalWrappedScreens(), null
         );
      }
   }

   static final void updateInjectedScreens(UiEngineImpl var0) {
      if (var0 != null && var0.equals(_statusManager._foregroundEngine) && !var0.equals(_statusManager._paintControlEngine)) {
         RIMGlobalMessagePoster.postGlobalEvent(
            _statusManager._paintControlEngine.getApplication().getProcessId(), 3160755958169834551L, 0, 0, var0.getLocalWrappedScreens(), null
         );
      }
   }
}
