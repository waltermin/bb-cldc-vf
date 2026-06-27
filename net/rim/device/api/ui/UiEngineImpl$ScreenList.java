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

   public UiEngineImpl$ScreenList(UiEngineImpl var1) {
      this.this$0 = var1;
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
      int var1 = 0;
      int var2 = this.getScreenCount();

      for (int var3 = var2 - 1; var3 >= var2 - this._globalScreenCount; var3--) {
         Screen var4 = this.getScreen(var3);
         if (this.this$0.isInProcess(var4) || var4.getUiEngineImpl() == null && var4.isDismissing() && !(var4 instanceof UiEngineImpl$ProxyScreen)) {
            var1++;
         }
      }

      return var1;
   }

   public synchronized Screen getScreen(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreen(int var1) {
      return var1 >= this._localScreenCount ? null : this.getScreen(var1);
   }

   public synchronized XYRect getExtent(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized XYRect getOpaqueRegion(int var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getScreenInNonEventThread(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public synchronized int getIndex(Screen var1) {
      return Arrays.getIndex(this._screens, var1);
   }

   public synchronized int getLocalIndex(Screen var1) {
      int var2 = this.getIndex(var1);
      return var2 >= this._localScreenCount ? -1 : var2;
   }

   public synchronized Screen getTopmostScreen() {
      int var1 = this._screens.length;
      return var1 == 0 ? null : this._screens[var1 - 1];
   }

   public synchronized Screen getTopmostLocalScreen() {
      if (this._localScreenCount == 0) {
         return null;
      }

      int var1 = this._localScreenCount - 1;

      while (var1 >= 0 && this._screens[var1] instanceof UiEngineImpl$ProxyScreen) {
         var1--;
      }

      return var1 < 0 ? null : this._screens[var1];
   }

   public synchronized Screen getTopmostGlobalScreen() {
      return this._globalScreenCount == 0 ? null : this.getTopmostScreen();
   }

   public synchronized int highestOpaqueRegionContaining(XYRect var1) {
      for (int var2 = this._opaqueRegions.length - 1; var2 >= 0; var2--) {
         if (this._opaqueRegions[var2].contains(var1)) {
            return var2;
         }
      }

      return -1;
   }

   public synchronized boolean isTopmost(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized boolean isTopmostLocal(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void push(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void pop(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized void updateExtent(Screen var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public synchronized Screen getScreenAbove(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreenAbove(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getScreenBelow(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized Screen getLocalScreenBelow(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public synchronized XYRect[] getOpaqueRegionsArray() {
      return this._opaqueRegions;
   }

   private void wrapOutOfProcessGlobalScreens() {
      for (int var1 = 0; var1 < this._globalScreenCount; var1++) {
         this._screens[var1 + this._localScreenCount] = this.wrapGlobalScreen(this._screens[var1 + this._localScreenCount], false);
      }

      for (int var2 = 0; var2 < this._hiddenGlobalScreens.length; var2++) {
         this._hiddenGlobalScreens[var2] = this.wrapGlobalScreen(this._hiddenGlobalScreens[var2], true);
      }
   }

   private Screen wrapGlobalScreen(Screen var1, boolean var2) {
      if (var1 instanceof UiEngineImpl$ProxyScreen) {
         return var1;
      }

      if (this.this$0.isInProcess(var1)) {
         return var1;
      }

      var1 = new UiEngineImpl$ProxyScreen(var1);
      var1.setUiEngine(this.this$0);
      var1.doLayoutNoSynch();
      return var1;
   }

   private Screen[] wrapLocalScreens() {
      Screen[] var1 = new Screen[0];

      for (int var2 = this._localScreenCount - 1; var2 >= 0; var2--) {
         Screen var3 = this._screens[var2];
         if (!(var3 instanceof UiEngineImpl$ProxyScreen)) {
            Arrays.insertAt(var1, this.wrapLocalScreen(var3), 0);
            if (var3.getExtent().contains(0, 0, Display.getWidth(), Display.getHeight())
               && !this.this$0.isScreenTransparent(var3)
               && !this.this$0.isScreenTransparentBorder(var3)) {
               return var1;
            }

            if (this.this$0.isScreenTransparent(var3) && var2 == 0) {
               Arrays.insertAt(var1, this.wrapLocalScreen(this.this$0._bottomScreen), 0);
            }
         }
      }

      return var1;
   }

   private Screen wrapLocalScreen(Screen var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   private String getScreenListDebugging() {
      throw new RuntimeException("cod2jar: type check");
   }
}
