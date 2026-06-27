package net.rim.device.api.ui.text;

import net.rim.device.api.ui.DrawTextParam;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.XYRect;
import net.rim.device.internal.ui.ArticInterface$LineInfo;
import net.rim.device.internal.ui.Formatter;
import net.rim.tid.text.AttributedString$Iterator;

public class TextRect extends TextArea {
   private AttributedString$Iterator _iterator;

   public TextRect(Field var1) {
      this(var1, null, 6);
   }

   public TextRect(Field var1, int var2) {
      this(var1, null, var2);
   }

   public TextRect(Field var1, Object var2, int var3) {
      super(var1, var3 != 0 ? var3 | 8 : 14);
      this.setText(var2);
      this._iterator = this.getTextIterator();
   }

   @Override
   public void layout(int var1, int var2) {
      throw new RuntimeException("cod2jar: tail call (jumpspecial)");
   }

   @Override
   protected void paint(Graphics var1) {
      XYRect var2 = var1.getClippingRect();
      DrawTextParam var3 = Ui.getTmpDrawTextParam();
      this.setDrawTextParamFromStyle(var3);
      ArticInterface$LineInfo var4 = this.getLineInfoForYPos(var2.y);
      Formatter.paint(var1, var3, var4, this._iterator, this.getField(), this);
      Ui.returnTmpDrawTextParam(var3);
   }
}
