package net.rim.device.internal.ui;

class HorizontalFetcher implements Fetcher {
   int[] _data;

   void set(int[] data) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public int get(int index) {
      return this._data[index];
   }
}
