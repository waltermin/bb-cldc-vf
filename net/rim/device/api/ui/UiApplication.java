package net.rim.device.api.ui;

import net.rim.device.api.system.Application;
import net.rim.vm.Message;

public class UiApplication extends Application implements UiEngine {
   private UiEngineImpl _uiEngine;
   private int _style;
   private int _stylusX = -1;
   private int _stylusY = -1;
   public static final int STYLE_NO_SCREEN_CAPABLE;

   public final void publicProcessMessage(Message var1) {
      this._uiEngine.processMessage(this.getAppEventLock(), var1, false);
   }

   public final void popScreen() {
      throw new RuntimeException("cod2jar: ldc");
   }

   public void setStyle(int var1) {
      this._style &= ~var1;
      this._style |= var1;
   }

   public int getStyle() {
      return this._style;
   }

   public final void doPainting() {
      this._uiEngine.doPainting();
   }

   @Override
   public int getStylusY() {
      return this._stylusY;
   }

   @Override
   public final void suspendPainting(boolean var1) {
      this._uiEngine.suspendPainting(var1);
   }

   @Override
   public final boolean isPaintingSuspended() {
      return this._uiEngine.isPaintingSuspended();
   }

   @Override
   public int getStylusX() {
      return this._stylusX;
   }

   @Override
   public final void updateDisplay() {
      this._uiEngine.updateDisplay();
   }

   @Override
   public int getGlobalPriority(Screen var1) {
      return this._uiEngine.getGlobalPriority(var1);
   }

   @Override
   public final void popScreen(Screen var1) {
      this._uiEngine.popScreen(var1);
   }

   @Override
   public final void pushGlobalScreen(Screen var1, int var2, int var3) {
      this._uiEngine.pushGlobalScreen(var1, var2, var3);
   }

   @Override
   public final void pushScreen(Screen var1) {
      this._uiEngine.pushScreen(var1);
   }

   @Override
   public final void pushModalScreen(Screen var1) {
      this._uiEngine.pushModalScreen(var1);
   }

   @Override
   public final int getScreenCount() {
      return this._uiEngine.getScreenCount();
   }

   @Override
   public final void repaint() {
      this._uiEngine.repaint();
   }

   @Override
   public final void relayout() {
      this._uiEngine.relayout();
   }

   @Override
   public final Screen getActiveScreen() {
      return this._uiEngine.getActiveScreen();
   }

   @Override
   public final void queueStatus(Screen var1, int var2, boolean var3) {
      this._uiEngine.queueStatus(var1, var2, var3);
   }

   @Override
   public final void pushGlobalScreen(Screen var1, int var2, boolean var3) {
      this._uiEngine.pushGlobalScreen(var1, var2, var3);
   }

   @Override
   public final void dismissStatus(Screen var1) {
      this._uiEngine.dismissStatus(var1);
   }

   @Override
   public void addUserInputEventListener(UserInputEventListener var1) {
      this._uiEngine.addUserInputEventListener(var1);
   }

   @Override
   public int getLayoutGeneration() {
      return this._uiEngine.getLayoutGeneration();
   }

   @Override
   public void removeUserInputEventListener(UserInputEventListener var1) {
      this._uiEngine.removeUserInputEventListener(var1);
   }

   @Override
   public void setStylusPos(int var1, int var2) {
      this._stylusX = var1;
      this._stylusY = var2;
   }

   @Override
   public boolean isTopmostGlobal() {
      return this._uiEngine.isTopmostGlobal();
   }

   @Override
   public int getTopmostGlobalPriority() {
      return this._uiEngine.getTopmostGlobalPriority();
   }

   protected UiApplication() {
      this._uiEngine = UiEngineImpl.getUiEngine();
   }

   @Override
   public void activate() {
      super.activate();
      Trackball.updateDeviceWithAppSettings();
   }

   @Override
   protected boolean acceptsForeground() {
      return true;
   }

   public static final UiApplication getUiApplication() {
      return (UiApplication)Application.getApplication();
   }
}
