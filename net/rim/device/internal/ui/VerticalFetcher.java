package net.rim.device.internal.ui;

class VerticalFetcher implements Fetcher {
   int[] _data;
   int _width;
   int _x;

   VerticalFetcher(int[] data, int width) {
      this._data = data;
      this._width = width;
   }

   void set(int x) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public int get(int index) {
      return this._data[index * this._width + this._x];
   }
}
