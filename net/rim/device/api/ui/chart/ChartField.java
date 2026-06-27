package net.rim.device.api.ui.chart;

import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.util.Arrays;

public class ChartField extends Field {
   private ResourceBundle _resources;
   private Axis[] _axes;
   private ChartRenderer[] _renderers;
   private int _preferredWidth;
   private int _preferredHeight;
   private int[] _colors;
   private int _focusX;
   private int _focusY;
   private Font _previousFont;
   private boolean _needsSpaceCalculations;
   private static final int STATUS_MOVE_FOCUS_MASK;
   public static final int AXIS_TOP;
   public static final int AXIS_RIGHT;
   public static final int AXIS_BOTTOM;
   public static final int AXIS_LEFT;

   public ChartField() {
      this(0);
   }

   public ChartField(long var1) {
   }

   public void addRenderer(ChartRenderer var1) {
      Arrays.add(this._renderers, var1);
   }

   protected static void assertLegal(boolean var0) {
      if (!var0) {
         throw new Object();
      }
   }

   private void doSpaceCalculations() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   public int getNumColors() {
      return this._colors.length;
   }

   public int getColor(int var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   @Override
   public int getPreferredHeight() {
      this.doSpaceCalculations();
      return this._preferredHeight;
   }

   @Override
   public int getPreferredWidth() {
      this.doSpaceCalculations();
      return this._preferredWidth;
   }

   public boolean isEmpty() {
      return this._renderers.length == 0;
   }

   @Override
   public boolean isFocusable() {
      return true;
   }

   @Override
   public void getFocusRect(XYRect var1) {
      Manager var4 = this.getManager();
      int var2;
      int var3;
      if (var4 == null) {
         var2 = Display.getWidth();
         var3 = Display.getHeight();
      } else {
         var2 = var4.getVisibleWidth();
         var3 = var4.getVisibleHeight();
      }

      var1.x = this._focusX;
      var1.y = this._focusY;
      var1.width = Math.min(var2, this.getPreferredWidth());
      var1.height = Math.min(var3, this.getPreferredHeight());
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      int var4 = this.getFont().getHeight();
      var1 *= var4;
      if ((var2 & 196608) != 0) {
         switch (var2 & 196608) {
            case 65536:
               var1 = this.moveFocusHorizontally(var1);
               break;
            case 131072:
               var1 = this.moveFocusVertically(var1);
         }
      } else {
         switch (var2 & 1) {
            case 1:
               var1 = this.moveFocusHorizontally(var1);
               break;
            default:
               var1 = this.moveFocusVertically(var1);
         }
      }

      return super.moveFocus(var1 / var4, var2, var3);
   }

   private int moveFocusHorizontally(int var1) {
      XYRect var2 = Ui.getTmpXYRect();
      this.getFocusRect(var2);
      int var4;
      if (var1 < 0) {
         int var3 = -var2.x;
         var4 = Math.max(var1, var3);
      } else {
         int var5 = this.getPreferredWidth() - var2.X2();
         var4 = Math.min(var1, var5);
      }

      this._focusX += var4;
      Ui.returnTmpXYRect(var2);
      return var1 - var4;
   }

   private int moveFocusVertically(int var1) {
      XYRect var2 = Ui.getTmpXYRect();
      this.getFocusRect(var2);
      int var4;
      if (var1 < 0) {
         int var3 = -var2.y;
         var4 = Math.max(var1, var3);
      } else {
         int var5 = this.getPreferredHeight() - var2.Y2();
         var4 = Math.min(var1, var5);
      }

      this._focusY += var4;
      Ui.returnTmpXYRect(var2);
      return var1 - var4;
   }

   @Override
   protected void layout(int var1, int var2) {
      var1 = Math.min(var1, this.getPreferredWidth());
      var2 = Math.min(var2, this.getPreferredHeight());
      this.setExtent(var1, var2);
   }

   @Override
   protected void paint(Graphics var1) {
      if (this.isEmpty()) {
         var1.drawText(this._resources.getString(32), 0, 0, 4, this._preferredWidth);
      } else {
         this.doSpaceCalculations();

         for (int var2 = 0; var2 < this._axes.length; var2++) {
            Axis var3 = this._axes[var2];
            if (var3 != null) {
               var1.pushContext(var3.getLeft(), var3.getTop(), var3.getWidth(), var3.getHeight(), var3.getLeft(), var3.getTop());
               var3.paint(var1);
               var1.popContext();
            }
         }

         int var5 = this._renderers.length;

         for (int var6 = 0; var6 < var5; var6++) {
            ChartRenderer var4 = this._renderers[var6];
            var1.pushContext(var4.getLeft(), var4.getTop(), var4.getWidth(), var4.getHeight(), var4.getLeft(), var4.getTop());
            var4.paint(var1);
            var1.popContext();
         }
      }
   }

   public void setAxis(int var1, Axis var2) {
      this._axes[var1] = var2;
      if (var2 != null) {
         var2.setEdge(var1);
      }
   }

   protected void setColor(int var1, int var2) {
      throw new RuntimeException("cod2jar: exception table");
   }

   protected void setChartData(int[] var1) {
      this._colors = new int[var1.length];
      System.arraycopy(var1, 0, this._colors, 0, var1.length);
   }

   protected void updateChart() {
      if (this.getWidth() != this.getPreferredWidth() || this.getHeight() != this.getPreferredHeight()) {
         this.updateLayout();
      }

      this.invalidate();
   }
}
