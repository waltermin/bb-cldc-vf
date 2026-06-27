package net.rim.device.internal.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.util.MathUtilities;

public class FrameLayout extends Manager implements FocusChangeListener {
   private static final int BORDER_WIDTH;
   private static final int DOUBLE_BORDER_WIDTH;
   private static final int SUB_BORDER_PADDING;
   private static final int DOUBLE_SUB_BORDER_PADDING;
   public static final long USE_ROUNDED_CORNERS;

   public FrameLayout() {
      this(0);
   }

   public FrameLayout(long var1) {
      super(var1);
   }

   @Override
   public int getFieldAtLocation(int var1, int var2) {
      var1 = MathUtilities.clamp(3, var1, this.getWidth() - 3);
      var2 = MathUtilities.clamp(3, var2, this.getHeight() - 3);
      return super.getFieldAtLocation(var1, var2);
   }

   @Override
   public int getFieldClosestToLocation(int var1, int var2, int var3) {
      var1 = MathUtilities.clamp(3, var1, this.getWidth() - 3);
      var2 = MathUtilities.clamp(3, var2, this.getHeight() - 3);
      return super.getFieldClosestToLocation(var1, var2, var3);
   }

   @Override
   public void add(Field var1) {
      this.deleteAll();
      super.add(var1);
      var1.setFocusListener(null);
      var1.setFocusListener(this);
   }

   @Override
   public void insert(Field var1, int var2) {
      this.deleteAll();
      super.add(var1);
      var1.setFocusListener(null);
      var1.setFocusListener(this);
   }

   @Override
   public int getPreferredHeight() {
      return this.getField(0).getPreferredHeight() + 4 + 2;
   }

   @Override
   public int getPreferredWidth() {
      return this.getField(0).getPreferredWidth() + 4 + 2;
   }

   @Override
   protected void subpaint(Graphics var1) {
      int var2 = this.getVirtualWidth();
      int var3 = this.getVirtualHeight();
      int var4 = this.getVerticalScroll();
      int var5 = this.getHorizontalScroll();
      var1.pushContext(-var5, -var4, var2, var3, -var5, -var4);
      if (this.isStyle(1)) {
         var1.drawRoundRect(1, 1, var2 - 2, var3 - 2, 4, 4);
      } else {
         var1.drawRect(1, 1, var2 - 2, var3 - 2);
      }

      if (this.getFieldWithFocus() != null) {
         if (this.isStyle(1)) {
            var1.drawRoundRect(0, 0, var2, var3, 4, 4);
         } else {
            var1.drawRect(0, 0, var2, var3);
         }
      }

      var1.popContext();
      super.subpaint(var1);
   }

   @Override
   protected void sublayout(int var1, int var2) {
      Field var3 = this.getField(0);
      this.setPositionChild(var3, 3, 3);
      this.layoutChild(var3, Math.max(var1 - 4 - 2, 0), Math.max(var2 - 4 - 2, 0));
      int var4 = var3.getHeight() + 4 + 2;
      int var5 = var3.getWidth() + 4 + 2;
      this.setExtent(Math.min(var1, var5), var4);
      this.setVirtualExtent(var5, var4);
   }

   @Override
   public void focusChanged(Field var1, int var2) {
      if (var1 == this.getField(0)) {
         this.invalidate();
      }
   }
}
