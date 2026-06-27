package javax.microedition.lcdui;

public class Ticker {
   private String _str;
   private net.rim.device.api.ui.Font _font;
   private int _pixelLength;
   private int _pixelOffset;
   private int _width;
   private static final int TICKER_ADVANCE;
   static final int TICKER_DELAY;
   static final int TICKER_IDLE;

   public Ticker(String var1) {
   }

   final void setStuff(net.rim.device.api.ui.Font var1) {
      this._font = var1;
      this._pixelLength = this._font.getAdvance(this._str);
   }

   final int getHeight() {
      return this._font.getHeight();
   }

   final void draw(net.rim.device.api.ui.Graphics var1, int var2) {
      var1.setFont(this._font);
      var1.drawText(this._str, this._width - this._pixelOffset, var2);
   }

   final void advanceTicker() {
      this._pixelOffset += 4;
      if (this._pixelOffset > this._pixelLength + this._width) {
         this._pixelOffset = 0;
      }
   }

   public void setString(String var1) {
      throw new RuntimeException("cod2jar: exception table");
   }

   public String getString() {
      return this._str;
   }
}
