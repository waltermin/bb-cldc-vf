package net.rim.device.internal.browser.util;

public class PipePtr {
   private byte[] _bytes;
   private int _length;
   private int _offset;
   private boolean _ref;

   public PipePtr() {
   }

   public PipePtr(byte[] var1, int var2, int var3, boolean var4) {
      this.setData(var1, var2, var3, var4);
   }

   public void setData(byte[] var1, int var2, int var3, boolean var4) {
      this._bytes = var1;
      this._offset = var2;
      this._length = var3;
      this._ref = var4;
   }

   public void setLength(int var1) {
      throw new RuntimeException("cod2jar: field: receiver depth");
   }

   public int getOffset() {
      return this._offset;
   }

   public int getLength() {
      return this._length;
   }

   public byte[] getData() {
      return this._bytes;
   }

   public boolean isRef() {
      return this._ref;
   }
}
