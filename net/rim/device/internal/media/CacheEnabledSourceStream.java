package net.rim.device.internal.media;

import javax.microedition.media.Control;
import javax.microedition.media.protocol.ContentDescriptor;
import javax.microedition.media.protocol.SourceStream;

public class CacheEnabledSourceStream implements SourceStream {
   private SourceStream _stream;
   private int _seekType;
   private byte[] _cache;
   private long _position;
   private long _streamPosition;
   private static final int USE_DEFAULT_VALUE;
   private static final long MAX_CACHE_SIZE;

   public CacheEnabledSourceStream(SourceStream var1) {
      this._stream = var1;
      this._seekType = -1;
      if (this._stream.getSeekType() == 0 || this._stream.getSeekType() == 1) {
         long var2 = this._stream.getContentLength();
         if (var2 > 0 && var2 <= 1048576) {
            this._cache = new byte[(int)var2];
            this._seekType = 2;
            this._position = 0;
            this._streamPosition = 0;
         }
      }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      if (this._seekType == -1) {
         return this._stream.read(var1, var2, var3);
      }

      int var4 = 0;
      if (this._position < this._streamPosition) {
         int var5 = (int)Math.min(this._streamPosition - this._position, var3);
         System.arraycopy(this._cache, (int)this._position, var1, var2, var5);
         var4 += var5;
         this._position += var5;
         var2 += var5;
      }

      if (var4 < var3) {
         int var7 = var3 - var4;
         int var6 = this._stream.read(var1, var2, var7);
         if (var6 == -1) {
            if (var4 == 0) {
               return var6;
            }

            return var4;
         }

         System.arraycopy(var1, var2, this._cache, (int)this._position, var6);
         this._position += var6;
         this._streamPosition += var6;
         var4 += var6;
      }

      return var4;
   }

   @Override
   public long seek(long var1) {
      if (this._seekType == -1) {
         return this._stream.seek(var1);
      }

      if (var1 < 0) {
         var1 = 0;
      }

      if (var1 > this._stream.getContentLength()) {
         var1 = this._stream.getContentLength();
      }

      if (var1 <= this._streamPosition) {
         this._position = var1;
      } else {
         int var3 = (int)(var1 - this._streamPosition);
         int var4 = this._stream.read(this._cache, (int)this._position, var3);
         this._position += var4;
         this._streamPosition += var4;
      }

      return this._position;
   }

   @Override
   public ContentDescriptor getContentDescriptor() {
      return this._stream.getContentDescriptor();
   }

   @Override
   public long getContentLength() {
      return this._stream.getContentLength();
   }

   @Override
   public int getSeekType() {
      return this._seekType == -1 ? this._stream.getSeekType() : this._seekType;
   }

   @Override
   public int getTransferSize() {
      return this._stream.getTransferSize();
   }

   @Override
   public long tell() {
      return this._seekType == -1 ? this._stream.tell() : this._position;
   }

   @Override
   public Control getControl(String var1) {
      return this._stream.getControl(var1);
   }

   @Override
   public Control[] getControls() {
      return this._stream.getControls();
   }
}
