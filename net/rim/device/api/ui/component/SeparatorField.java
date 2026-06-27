package net.rim.device.api.ui.component;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.theme.Tag;

public class SeparatorField extends Field {
   private int _fieldHeight;
   private int _linePosition;
   private static Tag TAG;
   private static final int HEIGHT_THIN;
   private static final int HEIGHT_THICK;
   public static final long LINE_HORIZONTAL;
   public static final long LINE_VERTICAL;
   public static final long LINE_MASK;
   public static final long CHECK_FONT;

   public SeparatorField() {
      this(0);
   }

   public SeparatorField(long var1) {
      super(var1);
      this.setTag(TAG);
   }

   @Override
   public int getPreferredHeight() {
      return this.isThin() ? 1 : 3;
   }

   private final boolean isThin() {
      return this.isStyle(8388608) && this.getFont().getHeight() == 8;
   }

   private boolean isHorizontal() {
      long var1 = this.getStyle();
      if ((var1 & 196608) == 65536) {
         return true;
      } else {
         return (var1 & 196608) == 131072 ? false : !(this.getManager() instanceof Object);
      }
   }

   @Override
   protected void layout(int var1, int var2) {
      if (this.isThin()) {
         this._fieldHeight = 1;
         this._linePosition = 0;
      } else {
         this._fieldHeight = 3;
         this._linePosition = 1;
      }

      if (this.isHorizontal()) {
         this.setExtent(var1, this._fieldHeight);
      } else {
         this.setExtent(this._fieldHeight, var2);
      }
   }

   protected int getLinePosition() {
      return this._linePosition;
   }

   @Override
   public String toString() {
      throw new RuntimeException("cod2jar: ldc");
   }

   @Override
   protected void paint(Graphics var1) {
      if (this.isHorizontal()) {
         var1.drawLine(0, this._linePosition, this.getContentWidth(), this._linePosition);
      } else {
         var1.drawLine(this._linePosition, 0, this._linePosition, this.getContentHeight());
      }
   }
}
