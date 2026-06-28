package net.rim.device.api.ui.container;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYPoint;
import net.rim.device.api.ui.theme.Tag;

public final class Tooltip {
   private int _duration = 2000;
   Field _content;
   XYPoint _calloutPoint;
   final XYPoint _position = (XYPoint)(new Object());
   private final Tooltip$PopScreenRunnable _popScreenRunnable = new Tooltip$PopScreenRunnable(this);
   private final Tooltip$TooltipScreen _screen = new Tooltip$TooltipScreen(this);
   private static final Tag TAG;
   private static final int DEFAULT_TIP_DURATION;
   private static Tooltip _tooltip;

   private Tooltip() {
   }

   private final void dismiss() {
      this._popScreenRunnable.cancelInvokeLater();
      this._popScreenRunnable.run();
   }

   final XYPoint getPosition() {
      return this._position;
   }

   public static final void init() {
      Ui.addUiEngineListener(new Tooltip$MyUiEngineListener());
   }

   private final void reset() {
      this._duration = 2000;
      this._content = null;
      this._calloutPoint = null;
   }

   public final void setContent(Field field) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setContent(String text) {
      throw new RuntimeException("cod2jar: type check");
   }

   public final void setDuration(int duration) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public final void setPosition(int x, int y) {
      this._position.set(x, y);
   }

   static final void show(Tooltip$TooltipProvider tooltipProvider) {
      if (_tooltip._screen.isDisplayed()) {
         _tooltip._popScreenRunnable.cancelInvokeLater();
         Application.getApplication().invokeLater(_tooltip._popScreenRunnable);
      }

      _tooltip.reset();
      tooltipProvider.provideTooltip(_tooltip);
      if (_tooltip._content != null) {
         Application.getApplication().invokeLater(new Tooltip$TooltipInvoker(_tooltip));
      }
   }
}
