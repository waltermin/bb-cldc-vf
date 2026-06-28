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

   static final void copyGlobalScreens(Screen[] screenArray, int index, Screen[] hiddenScreenArray) {
      assertHaveLock();
      int screenCount = _statusManager._statusQueue.size();
      int visibleScreenCount = _statusManager.getVisibleScreenCountImpl();
      Array.resize(screenArray, index + visibleScreenCount);

      for (int i = 0; i < visibleScreenCount; i++) {
         int queueIndex = visibleScreenCount - 1 - i;
         GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)_statusManager._statusQueue.elementAt(queueIndex);
         screenArray[i + index] = data.screen;
      }

      Array.resize(hiddenScreenArray, screenCount - visibleScreenCount);

      for (int i = visibleScreenCount; i < screenCount; i++) {
         int queueIndex = screenCount - 1 - (i - visibleScreenCount);
         GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)_statusManager._statusQueue.elementAt(queueIndex);
         hiddenScreenArray[i - visibleScreenCount] = data.screen;
      }
   }

   static final boolean dismiss(Screen screen, UiEngineImpl engine, boolean oldNotification, int processId) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized boolean dismissInternal(Screen screen, UiEngineImpl engine, boolean oldNotification, int processId) {
      if (!screen.isGlobal()) {
         return false;
      }

      screen.setUiEngine(null);
      screen.setGlobal(false);
      GlobalScreenManager$StatusData current = null;
      int numStatus = this._statusQueue.size();

      int index;
      for (index = 0; index < numStatus; index++) {
         current = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(index);
         if (current.screen == screen) {
            break;
         }
      }

      if (index == numStatus || current == null) {
         return false;
      }

      if (current.engine != null && !current.engine.equals(engine)) {
         return false;
      }

      XYRect exposedExtents = Ui.getTmpXYRect();
      exposedExtents.set(screen.getExtent());
      this._statusQueue.removeElementAt(index);

      for (int var10 = 0; var10 < _statusManager._statusQueue.size(); var10++) {
         GlobalScreenManager$StatusData temp = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(var10);
         exposedExtents.unionNoEmpty(temp.screen.getExtent());
         if (temp.suppress) {
            break;
         }
      }

      if (oldNotification) {
         current.engine.statusDismissedEvent(current.screen);
      } else {
         RIMGlobalMessagePoster.postGlobalEvent(5961289116197897667L, 2, 0, exposedExtents, new Object(processId));
      }

      this.updatePaintControl(2, false, engine);
      this.redirectInput();
      return true;
   }

   public static final int getGlobalPriority(Screen screen) {
      synchronized (_statusManager) {
         for (int lv = _statusManager._statusQueue.size() - 1; lv >= 0; lv--) {
            GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)_statusManager._statusQueue.elementAt(lv);
            if (data.screen == screen) {
               return data.priority;
            }
         }
      }

      throw new Object();
   }

   public static final Screen getCurrentGlobalScreen() {
      synchronized (_statusManager) {
         return _statusManager._statusQueue.size() > 0 ? ((GlobalScreenManager$StatusData)_statusManager._statusQueue.firstElement()).screen : null;
      }
   }

   public static final Screen getScreenWithFocus() {
      synchronized (_statusManager) {
         return _statusManager._focusScreen != null ? (Screen)_statusManager._focusScreen.get() : null;
      }
   }

   static final UiInternalListener getUiInternalListener() {
      return _statusManager._uiInternalListener;
   }

   private final int getVisibleScreenCountImpl() {
      int visibleCount = 0;

      for (int i = 0; i < this._statusQueue.size(); i++) {
         visibleCount++;
         GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(i);
         if (data.suppress) {
            return visibleCount;
         }
      }

      return visibleCount;
   }

   public static final int getVisibleScreenCount() {
      synchronized (_statusManager) {
         return _statusManager.getVisibleScreenCountImpl();
      }
   }

   public static final boolean isGlobalScreenWithInputDisplayed() {
      synchronized (_statusManager) {
         return _statusManager._statusQueue.size() > 0 ? ((GlobalScreenManager$StatusData)_statusManager._statusQueue.firstElement()).inputRequired : false;
      }
   }

   private final void redirectInput() {
      for (int lv = 0; lv < this._statusQueue.size(); lv++) {
         GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(lv);
         if (data.inputRequired) {
            this._appManager.redirectInput(data.process, false);
            return;
         }

         if (data.suppress) {
            break;
         }
      }

      this._appManager.redirectInput(null, false);
   }

   @Override
   public final void eventOccurred(long guid, int data0, int data1, Object object0, Object object1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void getExtent(XYRect rect) {
      synchronized (_statusManager) {
         if (_statusManager._statusQueue.size() != 0) {
            GlobalScreenManager$StatusData current = (GlobalScreenManager$StatusData)_statusManager._statusQueue.firstElement();
            current.screen.getExtent(rect);
         } else {
            rect.set(0, 0, 0, 0);
         }
      }
   }

   public static final Object getLock() {
      return _statusManager;
   }

   static final void queue(Screen screen, int priority, boolean inputRequired, int processId, UiEngineImpl engine) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void push(Screen screen, int priority, boolean inputRequired, int processId, UiEngineImpl engine) {
      throw new RuntimeException("cod2jar: ldc");
   }

   static final void push(Screen screen, int priority, int flags, int processId, UiEngineImpl engine) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private final synchronized void queueInternal(
      Screen screen,
      int priority,
      boolean inputRequired,
      boolean suppress,
      int processId,
      UiEngineImpl engine,
      boolean insertBeforeSamePriority,
      boolean oldNotofication
   ) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public static final void setScreenWithFocus(Screen screen) {
      synchronized (_statusManager) {
         _statusManager._focusScreen = (WeakReference)(new Object(screen));
      }
   }

   static final void setUiInternalListener(UiInternalListener listener) {
      _statusManager._uiInternalListener = listener;
   }

   static final void setAccessibleEventListener(AccessibleEventListener listener) {
      _statusManager._accessibleEventListener = listener;
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

   static final void returnBackingStore(BackingStore backingStore) {
      _statusManager._backingStoreStack.push(backingStore);
   }

   static final void returnTransparentBackingStore(BackingStore backingStore) {
      _statusManager._transparentBackingStoreStack.push(backingStore);
   }

   static final void removeAccessibleEventListener(AccessibleEventListener listener) {
      if (listener != null
         && _statusManager != null
         && _statusManager._accessibleEventListener != null
         && _statusManager._accessibleEventListener.equals(listener)) {
         _statusManager._accessibleEventListener = null;
      }
   }

   static final AccessibleEventListener getAccessibleEventListener() {
      return _statusManager._accessibleEventListener;
   }

   static final void setForegroundEngine(UiEngineImpl engine) {
      _statusManager._foregroundEngine = engine;
      _statusManager.updatePaintControl(0, true, null);
   }

   static final UiEngineImpl getPaintControlEngine() {
      return _statusManager._paintControlEngine;
   }

   private final void updatePaintControl(int type, boolean grabLock, UiEngineImpl engine) {
      UiEngineImpl foregroundEngine = this._foregroundEngine;
      UiEngineImpl paintControlEngine = foregroundEngine;
      this._paintControlEngine = foregroundEngine;
      boolean hasPaintControlGlobal = false;
      int numStatus = this._statusQueue.size();

      for (int index = 0; index < numStatus; index++) {
         GlobalScreenManager$StatusData data = (GlobalScreenManager$StatusData)this._statusQueue.elementAt(index);
         Screen screen = data.screen;
         if (screen.isPaintController()) {
            paintControlEngine = screen.getUiEngineImpl();
            this._paintControlEngine = paintControlEngine;
            hasPaintControlGlobal = true;
            break;
         }

         if (data.suppress) {
            break;
         }
      }

      if (foregroundEngine != null && paintControlEngine != null) {
         Application paintControlApp = paintControlEngine.getApplication();
         if (!paintControlEngine.equals(foregroundEngine) && paintControlApp != null) {
            if (type == 0) {
               RIMGlobalMessagePoster.postGlobalEvent(
                  paintControlApp.getProcessId(), 3160755958169834551L, 0, 0, foregroundEngine.getLocalWrappedScreens(), null
               );
            } else if (type == 1 && !grabLock && engine != null && engine.equals(paintControlEngine)) {
               engine.injectLocalWrappedScreens(foregroundEngine.getLocalWrappedScreens());
            } else if (type == 2 && engine != null) {
               if (engine.equals(paintControlEngine) && engine.shouldAddLocalWrappedScreens()) {
                  engine.injectLocalWrappedScreens(foregroundEngine.getLocalWrappedScreens());
               } else if (!engine.equals(paintControlEngine)) {
                  RIMGlobalMessagePoster.postGlobalEvent(
                     paintControlApp.getProcessId(), 3160755958169834551L, 0, 0, foregroundEngine.getLocalWrappedScreens(), null
                  );
               }
            }
         } else if (type == 0 && hasPaintControlGlobal) {
            paintControlEngine.removeLocalWrappedScreens();
         } else if (type == 1 && !hasPaintControlGlobal) {
            engine.removeLocalWrappedScreens();
         }

         if (type == 2 && engine != null && engine.shouldRemoveLocalWrappedScreens()) {
            engine.removeLocalWrappedScreens();
         }
      }

      if (paintControlEngine != null) {
         Application paintControlApp = paintControlEngine.getApplication();
         if (paintControlApp != null) {
            if (_statusManager._statusQueue.isEmpty() && ApplicationManager.getApplicationManager().getForegroundProcessId() != paintControlApp.getProcessId()) {
               return;
            }

            InternalServices.setVisibleProcess(paintControlApp.getProcessId());
         }
      }
   }

   static final void injectLocalWrappedScreens(UiEngineImpl engine) {
      if (engine != null && !engine.getApplication().isForeground() && _statusManager._foregroundEngine != null) {
         RIMGlobalMessagePoster.postGlobalEvent(
            engine.getApplication().getProcessId(), 3160755958169834551L, 0, 0, _statusManager._foregroundEngine.getLocalWrappedScreens(), null
         );
      }
   }

   static final void updateInjectedScreens(UiEngineImpl engine) {
      if (engine != null && engine.equals(_statusManager._foregroundEngine) && !engine.equals(_statusManager._paintControlEngine)) {
         RIMGlobalMessagePoster.postGlobalEvent(
            _statusManager._paintControlEngine.getApplication().getProcessId(), 3160755958169834551L, 0, 0, engine.getLocalWrappedScreens(), null
         );
      }
   }
}
