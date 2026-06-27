package net.rim.device.internal.ui;

class HorizontalFetcher implements Fetcher {
   int[] _data;

   void set(int[] var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   @Override
   public int get(int var1) {
      return this._data[var1];
   }
}
