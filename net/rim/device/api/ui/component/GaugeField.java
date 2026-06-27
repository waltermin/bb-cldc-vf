package net.rim.device.api.ui.component;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.ContextMenu;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldLabelProvider;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.theme.Tag;
import net.rim.device.api.ui.theme.ThemeAttributeSet;
import net.rim.device.api.ui.theme.ThemeManager;
import net.rim.device.api.util.MathUtilities;
import net.rim.device.api.util.StringProvider;
import net.rim.device.internal.ui.Border;

public class GaugeField extends Field implements FieldLabelProvider {
   private int _min;
   private int _max;
   private int _current;
   private String _label;
   private boolean _alt = true;
   private int _barStart;
   private int _barEnd;
   private int _barCurrent;
   private Border _borderBar;
   private ThemeAttributeSet _tasBar;
   private ThemeAttributeSet _tasFill;
   private int _barBorderTop = 0;
   private int _barBorderBottom = 0;
   private int _barBorderLeft = 0;
   private StringBuffer _value = (StringBuffer)(new Object());
   private static Tag TAG;
   private static Tag TAG_FILL;
   private static Tag TAG_BAR;
   public static final int NO_TEXT;
   public static final int PERCENT;
   public static final long LABEL_AS_PROGRESS;
   public static final int CURRENT_WITH_MAX;
   private static final int BAR_CLEARANCE;
   private static MenuItem _changeOptionsItem;

   public void reset(String var1, int var2, int var3, int var4) {
      this.$init0(var1, var2, var3, var4);
      this.fieldChangeNotify(Integer.MIN_VALUE);
   }

   public int getValueMin() {
      return this._min;
   }

   public int getValueMax() {
      return this._max;
   }

   public int getValue() {
      return this._current;
   }

   public void setValue(int var1) {
      this.reset(this._label, this._min, this._max, var1);
   }

   boolean changeOptionDialog() {
      if (this.getOriginal() != this) {
         return false;
      } else {
         GaugeField var1 = this.getChangeOptionGaugeField(null);
         GaugeField$GaugeFieldPopupScreen var2 = new GaugeField$GaugeFieldPopupScreen(var1);
         boolean var3 = var2.doModal();
         if (var3) {
            this.setNonProgrammaticValue(var1.getValue());
            return true;
         } else {
            var1.setValue(this.getValue());
            return true;
         }
      }
   }

   protected GaugeField getChangeOptionGaugeField(String var1) {
      GaugeField var2 = new GaugeField(var1, this._min, this._max, this._current, this.getStyle() | 18014398509481984L | 4503599627370496L);
      var2.setCookie(this.getCookie());
      var2.setChangeListener(this.getChangeListener());
      var2._alt = false;
      return var2;
   }

   @Override
   public void setLabel(String var1) {
      this.$init0(var1, this._min, this._max, this._current);
   }

   @Override
   public void setLabelStringProvider(StringProvider var1) {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   public String getLabel() {
      return this._label;
   }

   @Override
   protected void applyTheme() {
      this._tasBar = ThemeManager.getActiveTheme().getAttributeSet(TAG_BAR);
      this._tasFill = ThemeManager.getActiveTheme().getAttributeSet(TAG_FILL);
      if (this._tasBar != null) {
         this.setThemeAttributesSpecial(this._tasBar, null);
         this._borderBar = ThemeAttributeSet.getBorder(this);
         this.setThemeAttributesSpecial(null, null);
      }

      super.applyTheme();
   }

   @Override
   protected void layout(int var1, int var2) {
      this.setThemeAttributesSpecial(this._tasBar, null);
      Border var3 = ThemeAttributeSet.getBorder(this);
      if (var3 != null) {
         this._barBorderTop = var3.getTop();
         this._barBorderBottom = var3.getBottom();
         this._barBorderLeft = var3.getLeft();
         var2 = this.getFont().getHeight() + this._barBorderTop + this._barBorderBottom;
      } else {
         var2 = this.getFont().getHeight();
      }

      this.setThemeAttributesSpecial(null, null);
      this.setExtent(var1, var2);
      this.barLayout();
   }

   @Override
   public int getPreferredHeight() {
      return this.getFont().getHeight();
   }

   @Override
   public int getPreferredWidth() {
      return Display.getWidth() - 2;
   }

   @Override
   protected void makeContextMenu(ContextMenu var1) {
      super.makeContextMenu(var1);
      if (this.isStyle(4503599627370496L) && Ui.getMode() < 2) {
         var1.addItem(_changeOptionsItem);
      }
   }

   @Override
   protected void paint(Graphics var1) {
      long var2 = this.getStyle();
      if ((var2 & 65536) == 0 && this._label != null) {
         var1.drawText(this._label, 0, 1 + this._barBorderTop);
      }

      this.setThemeAttributesSpecial(this._tasBar, var1);
      var1.setColor(ThemeAttributeSet.getColor(this, 0));
      var1.fillRect(this._barStart, 1, this._barEnd - this._barStart, this.getHeight() - 2);
      this._borderBar = ThemeAttributeSet.getBorder(this);
      if (this._borderBar != null) {
         var1.setColor(ThemeAttributeSet.getColor(this, 3));
         XYRect var4 = Ui.getTmpXYRect();
         var4.set(this._barStart, 1, this._barEnd - this._barStart, this.getHeight() - 2);
         this._borderBar.paint(var1, var4);
         Ui.returnTmpXYRect(var4);
      }

      if ((var2 & 2) == 0) {
         var1.setColor(ThemeAttributeSet.getColor(this, 1));
         var1.drawText(this._value, 0, this._value.length(), this._barStart, 1 + this._barBorderTop, 52, this._barEnd - this._barStart);
      }

      this.setThemeAttributesSpecial(this._tasFill, var1);
      this._borderBar = ThemeAttributeSet.getBorder(this);
      var1.pushRegion(
         this._barStart + this._barBorderLeft,
         1 + this._barBorderTop,
         this._barCurrent - this._barStart,
         this.getHeight() - 2 - this._barBorderTop - this._barBorderBottom,
         0,
         0
      );
      var1.setColor(ThemeAttributeSet.getColor(this, 4));
      var1.fillRect(0, 0, this._barCurrent - this._barStart, this.getHeight() - 2 - this._barBorderTop - this._barBorderBottom);
      if (this._borderBar != null) {
         var1.setColor(ThemeAttributeSet.getColor(this, 3));
         XYRect var5 = Ui.getTmpXYRect();
         var5.set(0, 0, this._barCurrent - this._barStart, this.getHeight() - 2 - this._barBorderTop - this._barBorderBottom);
         this._borderBar.paint(var1, var5);
         Ui.returnTmpXYRect(var5);
      }

      if ((var2 & 2) == 0) {
         var1.setColor(ThemeAttributeSet.getColor(this, 5));
         var1.drawText(this._value, 0, this._value.length(), -this._barBorderLeft, 0, 52, this._barEnd - this._barStart);
      }

      var1.popContext();
      this.setThemeAttributesSpecial(null, var1);
   }

   @Override
   protected int moveFocus(int var1, int var2, int var3) {
      if (!this.isEditable()) {
         return var1;
      }

      if (this._alt && (var2 & 1) != 1) {
         return var1;
      }

      this.$init0(this._label, this._min, this._max, MathUtilities.clamp(this._min, this._current + var1, this._max));
      this.fieldChangeNotify(0);
      return 0;
   }

   private void barLayout() {
      this._barStart = 0;
      if (this._label != null && !this.isStyle(65536)) {
         this._barStart = this.getFont().getBounds(this._label);
      }

      this._barStart++;
      this._barEnd = this.getWidth() - 1;
      if (this._current == this._max) {
         this._barCurrent = this._barEnd;
      } else {
         long var1 = this._barEnd - this._barStart;
         long var3 = this._max - this._min;
         this._barCurrent = (int)((var1 << 32) / var3 * (this._current - this._min) >> 32) + this._barStart;
      }
   }

   @Override
   protected void drawFocus(Graphics var1, boolean var2) {
      if (this._borderBar != null) {
         if (var2) {
            var1.setColor(ThemeAttributeSet.getColor(this, 1));
         } else {
            var1.setColor(ThemeAttributeSet.getColor(this, 0));
         }

         XYRect var3 = Ui.getTmpXYRect();
         var3.set(this._barStart - 1, 0, this._barEnd - this._barStart + 1, this.getHeight());
         this._borderBar.paint(var1, var3);
         Ui.returnTmpXYRect(var3);
      } else {
         if (var2) {
            var1.setColor(ThemeAttributeSet.getColor(this, 1));
            var1.drawRect(this._barStart - 1, 0, this._barEnd - this._barStart + 1, this.getHeight());
         }
      }
   }

   public GaugeField(String var1, int var2, int var3, int var4, long var5) {
      super(var5);
      this.setTag(TAG);
      this.$init0(var1, var2, var3, var4);
   }

   private void $init0(String var1, int var2, int var3, int var4) {
      throw new RuntimeException("cod2jar: ldc");
   }

   public GaugeField() {
      this(null, 0, 100, 0, 4);
   }

   private void setNonProgrammaticValue(int var1) {
      this.$init0(this._label, this._min, this._max, var1);
      this.fieldChangeNotify(0);
   }
}
