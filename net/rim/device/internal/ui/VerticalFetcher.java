package net.rim.device.internal.ui;

class VerticalFetcher implements Fetcher {
   int[] _data;
   int _width;
   int _x;

   VerticalFetcher(int[] var1, int var2) {
      this._data = var1;
      this._width = var2;
   }

   void set(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public int get(int var1) {
      return this._data[var1 * this._width + this._x];
   }
}
