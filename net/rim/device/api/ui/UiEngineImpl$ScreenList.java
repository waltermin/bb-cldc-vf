package net.rim.device.api.ui;

import net.rim.device.api.system.Display;
import net.rim.device.api.util.Arrays;

class UiEngineImpl$ScreenList {
   private Screen[] _screens;
   private XYRect[] _extents;
   private XYRect[] _opaqueRegions;
   private int _localScreenCount;
   private int _globalScreenCount;
   private Screen[] _hiddenGlobalScreens;
   private final UiEngineImpl this$0;

   public UiEngineImpl$ScreenList(UiEngineImpl _1) {
      this.this$0 = _1;
      this._screens = new Screen[0];
      this._extents = new XYRect[0];
      this._opaqueRegions = new XYRect[0];
      this._hiddenGlobalScreens = new Screen[0];
   }

   public synchronized void copyGlobalScreens() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen[] getHiddenGlobalScreens() {
      return this._hiddenGlobalScreens;
   }

   public synchronized int getScreenCount() {
      return this._localScreenCount + this._globalScreenCount;
   }

   public synchronized int getLocalScreenCount() {
      return this._localScreenCount;
   }

   public synchronized int getGlobalScreenCount() {
      return this._globalScreenCount;
   }

   public synchronized int getInProcessGlobalScreenCount() {
      int count = 0;
      int screenCount = this.getScreenCount();

      for (int i = screenCount - 1; i >= screenCount - this._globalScreenCount; i--) {
         Screen next = this.getScreen(i);
         if (this.this$0.isInProcess(next) || next.getUiEngineImpl() == null && next.isDismissing() && !(next instanceof UiEngineImpl$ProxyScreen)) {
            count++;
         }
      }

      return count;
   }

   public synchronized Screen getScreen(int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreen(int index) {
      return index >= this._localScreenCount ? null : this.getScreen(index);
   }

   public synchronized XYRect getExtent(int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized XYRect getOpaqueRegion(int index) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getScreenInNonEventThread(int index) {
      try {
         return this._screens[index];
      } catch (ArrayIndexOutOfBoundsException var3) {
         return null;
      }
   }

   public synchronized int getIndex(Screen screen) {
      return Arrays.getIndex(this._screens, screen);
   }

   public synchronized int getLocalIndex(Screen screen) {
      int index = this.getIndex(screen);
      return index >= this._localScreenCount ? -1 : index;
   }

   public synchronized Screen getTopmostScreen() {
      int count = this._screens.length;
      return count == 0 ? null : this._screens[count - 1];
   }

   public synchronized Screen getTopmostLocalScreen() {
      if (this._localScreenCount == 0) {
         return null;
      }

      int i = this._localScreenCount - 1;

      while (i >= 0 && this._screens[i] instanceof UiEngineImpl$ProxyScreen) {
         i--;
      }

      return i < 0 ? null : this._screens[i];
   }

   public synchronized Screen getTopmostGlobalScreen() {
      return this._globalScreenCount == 0 ? null : this.getTopmostScreen();
   }

   public synchronized int highestOpaqueRegionContaining(XYRect region) {
      for (int i = this._opaqueRegions.length - 1; i >= 0; i--) {
         if (this._opaqueRegions[i].contains(region)) {
            return i;
         }
      }

      return -1;
   }

   public synchronized boolean isTopmost(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized boolean isTopmostLocal(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void push(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void pop(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void updateExtent(Screen screen) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized Screen getScreenAbove(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreenAbove(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getScreenBelow(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreenBelow(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized XYRect[] getOpaqueRegionsArray() {
      return this._opaqueRegions;
   }

   private void wrapOutOfProcessGlobalScreens() {
      for (int i = 0; i < this._globalScreenCount; i++) {
         this._screens[i + this._localScreenCount] = this.wrapGlobalScreen(this._screens[i + this._localScreenCount], false);
      }

      for (int i = 0; i < this._hiddenGlobalScreens.length; i++) {
         this._hiddenGlobalScreens[i] = this.wrapGlobalScreen(this._hiddenGlobalScreens[i], true);
      }
   }

   private Screen wrapGlobalScreen(Screen screen, boolean hidden) {
      if (screen instanceof UiEngineImpl$ProxyScreen) {
         return screen;
      }

      if (this.this$0.isInProcess(screen)) {
         return screen;
      }

      screen = new UiEngineImpl$ProxyScreen(screen);
      screen.setUiEngine(this.this$0);
      screen.doLayoutNoSynch();
      return screen;
   }

   private Screen[] wrapLocalScreens() {
      Screen[] wrappedLocalScreens = new Screen[0];

      for (int i = this._localScreenCount - 1; i >= 0; i--) {
         Screen local = this._screens[i];
         if (!(local instanceof UiEngineImpl$ProxyScreen)) {
            Arrays.insertAt(wrappedLocalScreens, this.wrapLocalScreen(local), 0);
            if (local.getExtent().contains(0, 0, Display.getWidth(), Display.getHeight())
               && !this.this$0.isScreenTransparent(local)
               && !this.this$0.isScreenTransparentBorder(local)) {
               return wrappedLocalScreens;
            }

            if (this.this$0.isScreenTransparent(local) && i == 0) {
               Arrays.insertAt(wrappedLocalScreens, this.wrapLocalScreen(this.this$0._bottomScreen), 0);
            }
         }
      }

      return wrappedLocalScreens;
   }

   private Screen wrapLocalScreen(Screen screen) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private String getScreenListDebugging() {
      throw new RuntimeException("cod2jar: type check");
   }
}
