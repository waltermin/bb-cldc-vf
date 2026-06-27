package net.rim.device.api.io;

import java.io.InputStream;

public class SharedInputStream extends InputStream {
   private SharedInputStreamSource _source;
   private int _currentPosition;
   private int _maxPosition;
   private int _startPosition;

   public SharedInputStream(byte[] var1) {
      this(new SharedInputStreamSource(var1), 0, var1.length);
   }

   public SharedInputStream(SharedInputStream var1) {
      this(var1._source, var1._currentPosition, Integer.MAX_VALUE - var1._currentPosition);
   }

   public SharedInputStream(SharedInputStream var1, int var2) {
      this(var1._source, var1._currentPosition, var2);
   }

   private SharedInputStream(SharedInputStreamSource var1) {
      this(var1, 0, Integer.MAX_VALUE);
   }

   private SharedInputStream(SharedInputStreamSource var1, int var2, int var3) {
      this._source = var1;
      this._currentPosition = var2;
      this._startPosition = var2;
      if (var3 + var2 >= 0 && var3 >= 0) {
         this._maxPosition = var2 + var3;
      } else {
         this._maxPosition = Integer.MAX_VALUE;
      }
   }

   public static SharedInputStream getSharedInputStream(InputStream var0) {
      throw new RuntimeException("cod2jar: type check");
   }

   public static SharedInputStream getSharedInputStream(byte[] var0) {
      if (var0 == null) {
         throw new Object();
      } else {
         return new SharedInputStream(new SharedInputStreamSource(var0), 0, var0.length);
      }
   }

   public static SharedInputStream getSharedInputStream(InputStream var0, int var1) {
      throw new RuntimeException("cod2jar: type check");
   }

   public void setLength(int var1) {
      if (var1 + this._startPosition >= 0 && var1 >= 0) {
         this._maxPosition = this._startPosition + var1;
      } else {
         throw new Object();
      }
   }

   public int peek() {
      return this._currentPosition >= this._maxPosition ? -1 : this._source.read(this._currentPosition);
   }

   @Override
   public int read() {
      throw new RuntimeException("cod2jar: field: unknown receiver");
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (var3 < 0) {
         throw new Object();
      }

      if (this._currentPosition >= this._maxPosition) {
         return -1;
      }

      var3 = this._source.read(this._currentPosition, var1, var2, Math.min(var3, this._maxPosition - this._currentPosition));
      if (var3 > 0) {
         this._currentPosition += var3;
      }

      return var3;
   }

   @Override
   public long skip(long var1) {
      if (var1 < 0) {
         throw new Object();
      }

      var1 = this._source.skip(this._currentPosition, var1);
      if (this._currentPosition + var1 >= this._maxPosition) {
         var1 = this._maxPosition - this._currentPosition;
      }

      this._currentPosition = (int)(this._currentPosition + var1);
      return var1;
   }

   @Override
   public int available() {
      return Math.min(this._maxPosition - this._currentPosition, this._source.available(this._currentPosition));
   }

   public SharedInputStream readInputStream() {
      return new SharedInputStream(this._source, this._currentPosition, this._maxPosition - this._currentPosition);
   }

   public SharedInputStream readInputStream(int var1) {
      SharedInputStream var2 = new SharedInputStream(this._source, this._currentPosition, var1);
      this.skip(var1);
      return var2;
   }

   public int getCurrentPosition() {
      return this._currentPosition;
   }

   public void setCurrentPosition(int var1) {
      if (var1 < 0) {
         throw new Object();
      }

      this._currentPosition = var1;
   }
}
